package member.logout.controller;



import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LogOutController implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");// 브라우저에 출력되는 문자와 태그, 언어 코딩 타입을 설정
        PrintWriter out = response.getWriter(); //출력 스트림 객체 생성
        HttpSession session = request.getSession(); //세션 객체 생성
        session.invalidate();//세션만료 = > 로그아웃

        out.println("<script>");
        out.println("alert('로그아웃 됨');");
        out.println("location = 'member_login.do';");
        out.println("</script>");

        out.close();
        return null;
    }
}
