package member.edit.controller;

import member.MemberDAOImpl;
import member.MemberVo;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class EditController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");// 웹브라우저에 응답하는 문자/태그, 언어코딩 타입을 지정
        PrintWriter out = response.getWriter();//출력 스트림 out을 생성
        HttpSession session = request.getSession();
        MemberDAOImpl mdao = new MemberDAOImpl();

        String id = (String) session.getAttribute("id");//세션 아이디를 구함

        if(id == null){
            out.println("<script>");
            out.println("alert('다시 로그인 하세요!');");
            out.println("location='member_login.do';");
            out.println("</script>");
        }else{


            MemberVo m = mdao.getMember(id);//오라클DB로 부터 id에 해당하는 회원정보를 가져온다.
            request.setAttribute("m", m);
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./front2/html/edit.jsp");//뷰페이지 경로 설정
            return forward;
        }

        return null;
    }
}