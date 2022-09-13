package member.signIn.controller;

import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInController implements Action{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);//기존 매핑주소 값을 유지하고, 속성키 이름과 값도 유지한다.
        forward.setPath("./jsp/signIn/test.jsp");
        return forward;

    }
}
