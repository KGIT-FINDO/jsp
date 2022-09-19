package board.originWrite;

import board.originWrite.vo.BoardVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OriginWriteOkController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");//post방식으로 전달된 한글 자료를 안꺠지게 한다.

        String board_name = request.getParameter("board_name");//글쓴이
        String board_title = request.getParameter("board_title");
        String board_cont = request.getParameter("board_cont");

        BoardVO b = new BoardVO();
        b.setBoard_name(board_name);
        b.setBoard_title(board_title);
        b.setBoard_cont(board_cont);

        BoardDAOImpl bdao = new BoardDAOImpl();
        bdao.insertBoard(b);//게시판 저장

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);//새로운 매핑주소로 이동
        forward.setPath("board_list.do");

        return forward;
    }
}
