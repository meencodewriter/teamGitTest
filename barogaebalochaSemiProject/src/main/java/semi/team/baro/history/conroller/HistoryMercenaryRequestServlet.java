package semi.team.baro.history.conroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.team.baro.history.model.service.HistoryService;
import semi.team.baro.history.model.vo.HistoryPageData;
import semi.team.baro.mercenary.model.vo.MercenaryRequest;

/**
 * Servlet implementation class HistoryMercenaryRequestServlet
 */
@WebServlet(name = "HistoryMercenaryRequest", urlPatterns = { "/historyMercenaryRequest.do" })
public class HistoryMercenaryRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryMercenaryRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String categoryName = request.getParameter("categoryName");
		//3.비즈니스로직
		HistoryService service = new HistoryService();
		HistoryPageData hpd = service.merceneryRequestMyHistory(memberNo, reqPage);
		//ArrayList<MercenaryRequest> mcReqList = service.mercenaryRequsetHistory(memberNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/history.jsp");
		request.setAttribute("mcReqList", hpd.getMcReqList());
		request.setAttribute("pageNavi", hpd.getPageNavi());
		request.setAttribute("categoryName", categoryName);
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
