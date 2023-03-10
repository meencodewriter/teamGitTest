package semi.team.baro.member.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.team.baro.member.model.service.MemberService;
import semi.team.baro.member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet(name = "updateMember", urlPatterns = { "/updateMember.do" })
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
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
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/photo";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		String newMemberPw = mRequest.getParameter("newMemberPw");
		
		Member m = new Member();
		
		m.setMemberId(mRequest.getParameter("memberId"));
		if(newMemberPw.equals("")) {
			m.setMemberPw(mRequest.getParameter("memberPw"));
		}else {
			m.setMemberPw(mRequest.getParameter("newMemberPw"));			
		}
		String status = mRequest.getParameter("status");
		//새 첨부파일이 있으면 새 첨부파일값, 없으면 null
		String filepath = mRequest.getFilesystemName("imgFile");
		//기존 첨부파일이 있었으면 기존 첨부파일값, 없었으면 null
		String oldFilepath = mRequest.getParameter("oldFilepath");
		if(filepath == null && status.equals("stay")) {
			filepath = oldFilepath;
		}
		m.setFilepath(filepath);
		
		m.setMemberName(mRequest.getParameter("memberName"));
		m.setMemberMail(mRequest.getParameter("memberMail"));
		m.setMemberPhone(mRequest.getParameter("memberPhone1")+"-"+mRequest.getParameter("memberPhone2")+"-"+mRequest.getParameter("memberPhone3"));
		m.setMemberContent(mRequest.getParameter("memberContent"));
		m.setMemberAddr(mRequest.getParameter("memberAddr"));
		
		//3.비즈니스로직
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		MemberService service = new MemberService();
		int result = service.updateMember(m);
		
		//4.결과처리
		if(result>0) {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("m");
			member.setMemberAddr(m.getMemberAddr());
			member.setMemberPhone(m.getMemberPhone());
			member.setFilepath(m.getFilepath());
			member.setMemberContent(m.getMemberContent());
			member.setMemberPw(m.getMemberPw());
			
			request.setAttribute("title", "회원정보 수정 성공");
			request.setAttribute("msg", "정보가 수정되었습니다.");
			request.setAttribute("icon", "success");
						
		}else {
			request.setAttribute("title", "회원정보 수정 실패");
			request.setAttribute("msg", "관리자에게 문의하세요.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/mypage.do");
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
