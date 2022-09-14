package member.login.controller;

import member.MemberDAOImpl;
import member.MemberVo;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class SignInOkController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        System.out.println(id);

        MemberDAOImpl mdao = new MemberDAOImpl();
        MemberVo dm = mdao.logincheck(id);//로그인 인증 처리

        if (dm == null) {
            out.println("<script>");
            out.println("alert('회원정보가 검색되지 않습니다.')");
            out.println("history.go(-1);"); //뒤로 한칸 이동
            out.println("</script>");
        } else {
            if (!dm.getPassword().equals(password)) {
                out.println("</script>");
                out.println("alert('비번이 다릅니다')");
                out.println("history.back();");//뒤로 한칸 이동
                out.println("</script>");
            } else {//비번이 같아서 로그인 인증 된 경우
                HttpSession session = request.getSession();//세선 객체 생성
                session.setAttribute("id", id); //세션 id에 아이디 저장

                ActionForward forward = new ActionForward();
                forward.setRedirect(true);
                forward.setPath("index.do");
                return forward;
            }
        }
        return null;
    }
}
