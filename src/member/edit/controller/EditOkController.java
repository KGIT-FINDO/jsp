package member.edit.controller;

import member.MemberDAOImpl;
import member.MemberVo;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class EditOkController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");

        if (id == null) {
            out.println("<script>");
            out.println("alert('다시 로그인하세요!);");
            out.println("location='member_login.do';");
            out.println("</script>");
        } else {
            request.setCharacterEncoding("UTF-8");//post 방식으로 전달된 한글 데이터를 안 깨지게 한다.
            String password = request.getParameter("signup_password");
            String name = request.getParameter("signup_name");
            String birth = request.getParameter("signup_birth_text");
            String gender = request.getParameter("signup_gender_text");
            String email = request.getParameter("signup_email_text");
            String phoneNum = request.getParameter("signup_phonenumber_text");

            MemberDAOImpl mdao = new MemberDAOImpl();
            MemberVo m = new MemberVo();

            m.setId(id);//기준이 되는 아이디를 저장
            m.setPassword(password);//수정할 비번을 저장
            m.setName(name);
            m.setBirth(birth);
            m.setGender(gender);
            m.setEmail(email);
            m.setPhoneNum(phoneNum);

            mdao.updateMember(m);//회원정보 수정

            out.println("<script>");
            out.println("alert('회원정보 수정정공');");
            out.println("location='member_login.do';");
            out.println("</script>");
        }


        return null;
    }
}