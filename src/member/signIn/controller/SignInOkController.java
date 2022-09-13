package member.signIn.controller;

import member.MemberDAOImpl;
import member.MemberVo;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class SignInOkController implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");


        PrintWriter out = response.getWriter();

        MemberVo m = new MemberVo();

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNum = request.getParameter("phoneNum");


        m.setId(id);
        m.setPassword(password);
        m.setName(name);
        m.setBirth(birth);
        m.setGender(gender);
        m.setEmail(email);
        m.setPhoneNum(phoneNum);




        MemberDAOImpl mdao = new MemberDAOImpl();
        int re = mdao.insertMember(m);//회원저장

        if(re == 1){
            ActionForward forward = new ActionForward();
            forward.setRedirect(true);//새로운 매핑주소로 이동
            forward.setPath("member_login.do");
            return forward;
        }

        return null;
    }
}

