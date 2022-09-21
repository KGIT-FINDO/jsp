package board.originEdit;

import board.originWrite.BoardDAOImpl;
import board.vo.BoardVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class OriginEditOkController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");//브라우저로 출력되는 문자/태그, 언어코딩 타입설정
        PrintWriter out = response.getWriter();//출력 스트림 out 생성

        request.setCharacterEncoding("UTF-8");//post로 전달되는 한글을 안깨지게 함

        int board_no = Integer.parseInt(request.getParameter("board_no"));//히든으로 전달된 게시글 번호값을 받아서 정수숫자로 변경하여 저장
        int page = Integer.parseInt(request.getParameter("page"));//히든으로 전달된 번호값을 페이지 받아서 정수숫자로 변경하여 저장

        if(request.getParameter("page") != null){
            page=Integer.parseInt(request.getParameter("page"));
        }
        HttpSession session = request.getSession();
        String board_name = (String) session.getAttribute("board_name");
        String board_title = request.getParameter("board_title");
        String board_cont = request.getParameter("board_cont");

        if(board_name == null){
            out.println("<script>");
            out.println("alert('비번이 다릅니다')");
            out.println("history.back();");
            out.println("</script>");
        }else {
            BoardDAOImpl bdao = new BoardDAOImpl();
            BoardVO eb = new BoardVO();
            eb.setBoard_no(board_no);
            eb.setBoard_name(board_name);
            eb.setBoard_title(board_title);
            eb.setBoard_cont(board_cont);

            bdao.updateBoard(eb);//게시판 수정

            ActionForward forward = new ActionForward();
            forward.setRedirect(true);//새로운 매핑주소로 이동
            forward.setPath("board_cont.do?board_no="+board_no+"&page="+page+"&state=cont");
            return forward;
        }

        return null;

    }
}