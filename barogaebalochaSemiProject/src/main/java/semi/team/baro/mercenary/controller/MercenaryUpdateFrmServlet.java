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
 * Servlet implementation class MercenaryUpdateFrmServlet
 */
@WebServlet(name = "MercenaryUpdateFrm", urlPatterns = { "/mercenaryUpdateFrm.do" })
public class MercenaryUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MercenaryUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int mercenaryNo = Integer.parseInt(request.getParameter("mercenaryNo"));
		//3. 비즈니스로직
		MercenaryService service = new MercenaryService();
		Mercenary mc = service.mercenaryView(mercenaryNo);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/mercenary/mercenaryUpdateFrm.jsp");
		request.setAttribute("mc", mc);
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
