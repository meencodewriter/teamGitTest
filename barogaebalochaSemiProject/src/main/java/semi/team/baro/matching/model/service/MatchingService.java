package semi.team.baro.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import semi.team.baro.matching.model.dao.MatchingDao;
import semi.team.baro.matching.model.vo.Matching;
import semi.team.baro.matching.model.vo.MatchingPageData;

public class MatchingService {
	private MatchingDao dao;

	public MatchingService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchingPageData selectMatchingList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		//1.페이지당 게시물은 10개
		int numPerPage = 10;
		//1.요청페이지 1페이지 -> 최신글 1~10
		//2페이지 -> 11~20
		//3페이지 -> 21~30
		int end = numPerPage*reqPage;
		int start = end - numPerPage+1;
		
		
		ArrayList<Matching> list = dao.selectMatchingList(conn, start, end);
		//페이징제작 시작
		//전체 페이지 수를 계산 -> 총 게시물 수를 조회
		int totalCount = dao.selectMatchingCount(conn);
		//전체페이지 수 계산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		//네비게이션 사이즈
		int pageNaviSize = 5;
		
		//페이지네비게이션 시작번호
		//reqPage 1~5 : 1 2 3 4 5 = 1
		//reqPage 6~10 : 6 7 8 9 10 = 2
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/matchingList.do?requestPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron-left</span>";
			pageNavi += "</a></li>";
		}else {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/matchingList.do?requestPage="+(pageNo)+"'>";
			pageNavi += pageNo;
			pageNavi += "</a></li>";
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/matchingList.do?requestPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		JDBCTemplate.close(conn);
		MatchingPageData mpd = new MatchingPageData(list, pageNavi, start);
		
		return mpd;
	}
	
}