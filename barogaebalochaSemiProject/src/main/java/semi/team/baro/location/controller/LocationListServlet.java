package semi.team.baro.location.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.team.baro.location.model.service.LocationService;
import semi.team.baro.location.model.vo.LocationPageData;

/**
 * Servlet implementation class LocationLIstServlet
 */
@WebServlet(name = "LocationList", urlPatterns = { "/locationList.do" })
public class LocationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationListServlet() {
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
		int reqPage = Integer.parseInt(request.getParameter("requestPage"));
		//3.비즈니스로직
		LocationService service = new LocationService();
		LocationPageData lpd = service.selectLocationList(reqPage);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/location/locationList.jsp");
		request.setAttribute("list", lpd.getList());
		request.setAttribute("pageNavi", lpd.getPageNavi());
		request.setAttribute("start",lpd.getStart());
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
