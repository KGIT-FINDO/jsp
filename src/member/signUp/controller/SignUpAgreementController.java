package member.signUp.controller;

import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpAgreementController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./front2/html/signup_agreement.jsp");
        return  forward;
    }
}
