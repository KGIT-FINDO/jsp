package board.originWrite;

import board.vo.BoardVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

public class OriginWriteOkController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");//post방식으로 전달된 한글 자료를 안꺠지게 한다.
        HttpSession session = request.getSession();

        String board_name = (String) session.getAttribute("id");
        String board_title = request.getParameter("board_title");
        String board_cont = request.getParameter("board_cont");

        if (board_name == null) {
            out.println("<script>");
            out.println("alert('다시 로그인하세요!);");
            out.println("location='member_login.do';");
            out.println("</script>");
        } else {

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
        return null;
    }
}
