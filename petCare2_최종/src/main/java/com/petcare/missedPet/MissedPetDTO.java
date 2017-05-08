package com.petcare.missedPet;

public class MissedPetDTO {
	private int happenDt;
	private String happenPlace;
	private String kindCd;
	private String popfile;
	private String processState;
	private Character sexCd;
	private String specialMark;
	private String careNm;
	private String careTel;
	
	


	
	public String getCareTel() {
		return careTel;
	}
	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}
	private String officetel;
	public int getHappenDt() {
		return happenDt;
	}
	public void setHappenDt(int happenDt) {
		this.happenDt = happenDt;
	}
	public String getHappenPlace() {
		return happenPlace;
	}
	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
	public String getKindCd() {
		return kindCd;
	}
	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}
	public String getPopfile() {
		return popfile;
	}
	public void setPopfile(String popfile) {
		this.popfile = popfile;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public Character getSexCd() {
		return sexCd;
	}
	public void setSexCd(Character sexCd) {
		this.sexCd = sexCd;
	}
	public String getSpecialMark() {
		return specialMark;
	}
	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}
	public String getCareNm() {
		return careNm;
	}
	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}

	public String getOfficetel() {
		return officetel;
	}
	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}
	
}
