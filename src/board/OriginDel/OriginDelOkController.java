package board.OriginDel;

import board.originWrite.BoardDAOImpl;
import member.action.Action;
import member.action.ActionForward;
import board.vo.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class OriginDelOkController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        int board_no = Integer.parseInt(request.getParameter("board_no"));
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        HttpSession session = request.getSession();
        ActionForward forward = new ActionForward();

        if (session != null) {
            BoardDAOImpl bdao = new BoardDAOImpl();
            bdao.delBoard(board_no);
            forward.setRedirect(true);
            forward.setPath("board_list.do?page=" + page);
            return forward;
        }
        else{
            forward.setRedirect(true);
            forward.setPath("board_list.do?page=" + page);
            return null;
        }

    }
}
