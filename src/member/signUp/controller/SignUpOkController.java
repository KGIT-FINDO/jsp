package member.signUp.controller;

import member.MemberDAOImpl;
import member.MemberVo;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class SignUpOkController implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");


        PrintWriter out = response.getWriter();

        MemberVo m = new MemberVo();

        String signup_id = request.getParameter("signup_id");
        String signup_password = request.getParameter("signup_password");
        String signup_name = request.getParameter("signup_name");
        String signup_birth_text = request.getParameter("signup_birth_text");
        String signup_gender_text = request.getParameter("signup_gender_text");
        String signup_email_text = request.getParameter("signup_email_text");
        String signup_phonenumber_text = request.getParameter("signup_phonenumber_text");


        m.setId(signup_id);
        m.setPassword(signup_password);
        m.setName(signup_name);
        m.setBirth(signup_birth_text);
        m.setGender(signup_gender_text);
        m.setEmail(signup_email_text);
        m.setPhoneNum(signup_phonenumber_text);


        MemberDAOImpl mdao = new MemberDAOImpl();
        int re = mdao.insertMember(m);//회원저장

        if(re == 1){
            ActionForward forward = new ActionForward();
            forward.setRedirect(true);//새로운 매핑주소로 이동
            forward.setPath("/front2/html/signup_complete.jsp");
            return forward;
        }

        return null;
    }
}

