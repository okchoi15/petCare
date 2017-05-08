package com.petcare.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StoryController {

	@RequestMapping(path = "/getBoardList", method = RequestMethod.GET)
	public String getCity(@RequestParam(value="page") int page) throws IOException{
		
		
		String url = "http://www.mafra.go.kr/list.jsp?SEARCH_TEXT=%C3%E0%BB%EA&SEARCH_SELECT=&SEARCH_CATEGORY=&FILE_NAME=&group_id=4&menu_id=72&link_menu_id=&division=B&board_kind=C&board_skin_id=C1&parent_code=71&link_url=&depth=2&code=left&link_target_yn=&menu_introduction=&menu_name=%C1%A4%C3%A5%BA%D0%BE%DF%BA%B0%C0%DA%B7%E1&popup_yn=N&reference=3&tab_yn=N&pageNo=" +page+"&nowUrl=&str=list&dateSearchFrom=&dateSearchTo=&chkSearchTitle=on&chkSearchBody=on&text_search=%C3%E0%BB%EA";
		
		Document doc = Jsoup.connect(url).timeout(5000).get();
	
		String table=null;
		Elements boardList=null;
		StringBuilder temp = new StringBuilder("");
			if(page==1){
				boardList=doc.select(".board_list_style02");
				table = boardList.html();

			}
			else {
				boardList=doc.select(".board_list_style02 table tbody");
			}
			
			table = boardList.html().replaceAll("/list.jsp", "http://www.mafra.go.kr/list.jsp");
			table = table.replace("정책분야별자료 목록", "");
			
			
			Pattern p = Pattern.compile("<img .*?>", Pattern.CASE_INSENSITIVE); 
			Matcher m = p.matcher(table); 
			table = m.replaceAll(""); 

			
		return table;
	}
	
	
}
