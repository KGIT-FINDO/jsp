package board;

import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");


        HttpSession session = request.getSession(); //세션 객체 생성
        String board_title = request.getParameter("board_title");
        String board_cont = request.getParameter("board_cont");


        return null;
    }
}
