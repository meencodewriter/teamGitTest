package semi.team.baro.mercenary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.team.baro.mercenary.model.service.MercenaryService;
import semi.team.baro.mercenary.model.vo.Mercenary;

/**
 * Servlet implementation class MercenaryInsertServlet
 */
@WebServlet(name = "MercenaryInsert", urlPatterns = { "/mercenaryInsert.do" })
public class MercenaryInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MercenaryInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출 - 지역 / 경기장 / 경기날짜 / 경기시간 / 실력 / 참가비 / 게시글내용
		Mercenary mc = new Mercenary();	
		mc.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
		mc.setLocation(request.getParameter("location"));
		mc.setGroundNo(Integer.parseInt(request.getParameter("groundNo")));
		mc.setGameDate(request.getParameter("gameDate"));
		mc.setGameTime(Integer.parseInt(request.getParameter("gameTime")));
		mc.setLevel(Integer.parseInt(request.getParameter("level")));
		mc.setMercenaryPay(Integer.parseInt(request.getParameter("mercenaryPay")));
		mc.setMercenaryContent(request.getParameter("mercenaryContent"));
		//3. 비즈니스로직
		MercenaryService service = new MercenaryService();
		int result = service.mercenaryInsert(mc);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("icon", "success");
			request.setAttribute("title", "작성완료");
			request.setAttribute("msg", "게시글이 작성되었습니다.");
		}else {
			request.setAttribute("icon", "error");
			request.setAttribute("title", "작성실패");
			request.setAttribute("msg", "오류가 발생했습니다.");
		}
		request.setAttribute("loc", "/mercenaryList.do?reqPage=1");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
