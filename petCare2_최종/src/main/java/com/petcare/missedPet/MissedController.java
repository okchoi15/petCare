package com.petcare.missedPet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.disease.GcmDTO;
import com.petcare.disease.LateDiseaseDTO;


@RestController
public class MissedController {

	@RequestMapping(path = "/getCity", method = RequestMethod.GET)
	public List<CityDTO> getCity() throws IOException{

		Document doc = Jsoup.connect("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sido?ServiceKey=qK1psrABUse%2B6Tww%2FOK5CxjWAGvy4TvUqb45X%2BPfcy5rPmaANYIhQbIXxobp1H7TYAeFDE%2BYSQsbndxWweL9zA%3D%3D").get();


			Elements cityList=doc.select("item");

			
			ArrayList<CityDTO> cityDtoList = new ArrayList<CityDTO>();
			CityDTO generateCity;
			for(Element city: cityList){
				generateCity = new CityDTO();
				
				generateCity.setOrgCd(Integer.parseInt(city.select("orgCd").text()));
				generateCity.setOrgdownNm(city.select("orgdownNm").text());
				
				cityDtoList.add(generateCity);
			}


		
		return cityDtoList;
	}
	
	@RequestMapping(path = "/getDistrict", method = RequestMethod.GET)
	public List<CityDTO> getDistrict(@RequestParam(value="uprCd") int uprCd) throws IOException{

		Document doc = Jsoup.connect("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sigungu?ServiceKey=qK1psrABUse%2B6Tww%2FOK5CxjWAGvy4TvUqb45X%2BPfcy5rPmaANYIhQbIXxobp1H7TYAeFDE%2BYSQsbndxWweL9zA%3D%3D&upr_cd=" + uprCd).get();


			Elements cityList=doc.select("item");

			
			ArrayList<CityDTO> districtDtoList = new ArrayList<CityDTO>();
			CityDTO generateDistrict;
			for(Element city: cityList){
				generateDistrict = new CityDTO();
				
				generateDistrict.setOrgCd(Integer.parseInt(city.select("orgCd").text()));
				generateDistrict.setOrgdownNm(city.select("orgdownNm").text());
				
				districtDtoList.add(generateDistrict);
			}

		
		return districtDtoList;
	}
	
	
	@RequestMapping(path = "/getMissedPetList", method = RequestMethod.GET)
	public List<MissedPetDTO> getMissedPetList(@RequestParam(value="uprCd") int uprCd, @RequestParam(value="orgCd") int orgCd, @RequestParam(value="state") String state, @RequestParam(value="pageNo") int pageNo) throws IOException{

		StringBuilder apiUrl = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?ServiceKey=qK1psrABUse%2B6Tww%2FOK5CxjWAGvy4TvUqb45X%2BPfcy5rPmaANYIhQbIXxobp1H7TYAeFDE%2BYSQsbndxWweL9zA%3D%3D");
		Document doc = null;
		
		if(uprCd!=0){
			apiUrl.append("&upr_cd="+uprCd);
		}
		if(orgCd!=0) {
			apiUrl.append("&org_cd="+orgCd);
		}
		if(!("0".equals(state))) {
			apiUrl.append("&state="+state);
		}
		apiUrl.append("&pageNo="+pageNo);
		
		doc = Jsoup.connect(apiUrl.toString()).timeout(5000).get();
		/*
		kindCd + sexCd 이름+성
		processState 사진 왼쪽상단에 위치할것
		popfile 사진
		happenDt 발견날짜
		happenPlace 발견장소
		specialMark 특징
		*/
			Elements missedPetList=doc.select("item");

			ArrayList<MissedPetDTO> missedPetDtoList = new ArrayList<MissedPetDTO>();
			MissedPetDTO generateMissedPet;
			for(Element pet: missedPetList){
				generateMissedPet = new MissedPetDTO();
				
				generateMissedPet.setKindCd(pet.select("kindCd").text());
				generateMissedPet.setProcessState((pet.select("processState").text()));
				generateMissedPet.setSexCd(pet.select("sexCd").text().charAt(0));
				generateMissedPet.setPopfile(pet.select("popfile").text());
				generateMissedPet.setHappenDt(Integer.parseInt(pet.select("happenDt").text()));
				generateMissedPet.setHappenPlace(pet.select("happenPlace").text());
				generateMissedPet.setSpecialMark(pet.select("specialMark").text());
				generateMissedPet.setCareNm(pet.select("careNm").text());
				generateMissedPet.setCareTel(pet.select("careTel").text());
				generateMissedPet.setOfficetel(pet.select("officetel").text());
				
				missedPetDtoList.add(generateMissedPet);
			}

		return missedPetDtoList;
	}
	
}

