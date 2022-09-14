package member.login.controller;

import member.MemberDAOImpl;
import member.MemberVo;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginConnectController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();//세션 객체 생성
        String id=(String) session.getAttribute("id");//세션아이디 저장
        if(id == null){
            out.println("<script>");
            out.println("alert('다시 로그인 하세요');");
            out.println("location = 'member_login.do");
            out.println("</script>");
        }
        else{
            MemberDAOImpl mdao = new MemberDAOImpl();
            MemberVo m = mdao.logincheck(id);//아이디에 해당하는 프로필 사진을 가져옴
            //request.setAttribute("profile", m.getMem_file());//profile 속성 키이름에 첨부파일 경로를 구함.

            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/front/view/login_connect.jsp");
            return forward;

        }
        return null;
    }
}
