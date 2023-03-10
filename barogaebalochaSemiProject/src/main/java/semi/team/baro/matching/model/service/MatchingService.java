package semi.team.baro.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import semi.team.baro.matching.model.dao.MatchingDao;
import semi.team.baro.matching.model.vo.Matching;
import semi.team.baro.matching.model.vo.MatchingMemberCheck;
import semi.team.baro.matching.model.vo.MatchingPageData;
import semi.team.baro.matching.model.vo.MatchingViewData;

public class MatchingService {
	private MatchingDao dao;

	public MatchingService() {
		super();
		dao = new MatchingDao();
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
//		System.out.println(list.get(9).getGroundName());
		
		System.out.println();
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
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/matchingList.do?requestPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/matchingList.do?requestPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
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

	public int matchingListInsert(Matching mc) {
		Connection conn = JDBCTemplate.getConnection();
		mc = dao.groundSearch(conn,mc);
		//System.out.println("groundNo test"+groundNo);
		int result = dao.reservationInsert(conn, mc);
		if(result>0) {
			int reservationNo = dao.getReservationNo(conn, mc);
			result = dao.matchingListInsert(conn, reservationNo, mc);
			int payResult = dao.payCredit(conn, mc);
			System.out.println(payResult);
			System.out.println(mc.getGroundPrice());
			System.out.println(mc.getMemberNo());
			if(result>0 && payResult > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MatchingMemberCheck selectOneMatch(int reservationNo, int matchingBoardNo, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		//1.맴버 체크
		int memberCheck = dao.searchApplyMember(conn, matchingBoardNo, memberNo);
		//2.매치정보 리스트
		Matching mc = dao.selectOneMatch(conn, reservationNo);
		MatchingMemberCheck mmc = new MatchingMemberCheck(mc, memberCheck);
		return mmc;
	}

	public int matchingMemberInsert(int matchingBoardNo, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.matchingMemberInsert(conn, matchingBoardNo, memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MatchingPageData MatchingMemberList(int matchingBoardNo, int reqPage) {
		Connection conn=JDBCTemplate.getConnection();
		//1.페이지당 게시물은 10개
				int numPerPage = 10;
				//1.요청페이지 1페이지 -> 최신글 1~10
				//2페이지 -> 11~20
				//3페이지 -> 21~30
				int end = numPerPage*reqPage;
				int start = end - numPerPage+1;
				
				
				ArrayList<Matching> list = dao.selectMatchingMemberList(conn, start, end, matchingBoardNo);

				
				System.out.println();
				//페이징제작 시작
				//전체 페이지 수를 계산 -> 총 게시물 수를 조회
				int totalCount = dao.selectMatchingMemberListCount(conn, matchingBoardNo);
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
					pageNavi += "<span class='material-icons'>chevron_left</span>";
					pageNavi += "</a></li>";
				}
				//페이지숫자
				for(int i=0;i<pageNaviSize;i++) {
					if(pageNo == reqPage) {
						pageNavi += "<li>";
						pageNavi += "<a class='page-item active-page' href='/matchingList.do?requestPage="+(pageNo)+"'>";
						pageNavi += pageNo;
						pageNavi += "</a></li>";
					}else {
						pageNavi += "<li>";
						pageNavi += "<a class='page-item' href='/matchingList.do?requestPage="+(pageNo)+"'>";
						pageNavi += pageNo;
						pageNavi += "</a></li>";
					}
					pageNo++;
					if(pageNo>totalPage) {
						break;
					}
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

	public int applyInsert(int memberNo, int matchingBoardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.applyInsert(conn, memberNo, matchingBoardNo);
		if(result>0) {
			int result2 = dao.applyInsert2(conn, matchingBoardNo);
				if(result2>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int applyCancel(int memberNo, int matchingBoardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.applyCancel(conn, memberNo, matchingBoardNo);
		if(result>0) {
			int result2 = dao.applyCancel2(conn, matchingBoardNo);
				if(result2>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int MatchingCancel(int memberNo, int sum, int matchingBoardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int statusResult = dao.MatchingCancelStatus(conn, matchingBoardNo);
		if(statusResult>0) {
			int result = dao.MatchingCancel(conn, memberNo, sum);
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return statusResult;
	}
}
