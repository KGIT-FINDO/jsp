package member.login.controller;


import member.action.Action;
import member.action.ActionForward;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*로그인 페이지로 이동하는 실제 컨트롤러 */
public class SignInController implements Action{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        forward.setRedirect(false); //기본매핑주소를 유지하면서 뷰페이지로 이동
        forward.setPath("./front2/html/signin.jsp");
        return forward;// return값을 null로 하면 해당 view 페이지로 안가고 화면이 하얗게 나온다.

    }
}