package board.reply.replyWrite;

import board.originWrite.BoardDAOImpl;
import board.reply.ReplyDAOImpl;
import board.vo.BoardVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReplyWriteOKController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        String board_name = request.getParameter("board_name");//글쓴이
        String board_title = request.getParameter("board_title");
        String board_cont = request.getParameter("board_cont");

        BoardVO b = new BoardVO();
        b.setBoard_name(board_name);
        b.setBoard_title(board_title);
        b.setBoard_cont(board_cont);

        ReplyDAOImpl rdao = new ReplyDAOImpl();
        rdao.insertReply(b);//게시판 저장

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);//새로운 매핑주소로 이동
        forward.setPath("board_list.do");

        return forward;
    }
}
