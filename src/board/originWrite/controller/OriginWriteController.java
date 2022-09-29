package board.originWrite.controller;

import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

public class OriginWriteController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int page = 1;
        if(request.getParameter("page") != null){//get으로 전달된 쪽번호가 있는 경우
            page = Integer.parseInt(request.getParameter("page"));//쪽번호(페이지번호)를 정수 숫자로 변경해서 저장.
        }
        request.setAttribute("page", page);//내가 본 페이지 번호를 바로 이동하기 위한 책갈피 기능
        HttpSession session = request.getSession();


        String id = (String) session.getAttribute("id");
        if(id == null){
            out.println("<script>");
            out.println("alert('로그인후 이용해주세요!);");
            out.println("location='member_login.do';");
            out.println("</script>");
        }else{
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./front2/html/index_BoardWrite.jsp");
            return forward;
        }
        return null;

    }
}
