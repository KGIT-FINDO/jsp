package board;

import board.originWrite.BoardDAOImpl;
import board.vo.BoardVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

public class ContController implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        int board_no = Integer.parseInt(request.getParameter("board_no"));// get으로 전달된 게시물 번호를 받아서 정수 숫자로 변경해서 저장
        String state = request.getParameter("state");

        int page = 1;
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        BoardDAOImpl bdao = new BoardDAOImpl();

        if(state.equals("cont")){//내용보기 일때만 조회수가 증가
            bdao.updateHit(board_no);
        }
        BoardVO bc = bdao.getBoardCont(board_no);//번호에 해당하는 DB레코드값을 가져옴
        String board_cont = bc.getBoard_cont().replace("\n", "<br/>");//textarea에서 엔터키  친 부분을 다음줄로 줄바꿈(개행)

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String board_name = request.getParameter("board_name");

        out.println(id);
        out.println(board_name);

        request.setAttribute("bc", bc);
        request.setAttribute("page", page);//책갈피 기능 때문에 쪽번호 저장
        request.setAttribute("board_cont", board_cont);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);//기존 매핑주소와 키값을 유지

        if(state.equals("cont")){//내용보기
            forward.setPath("./front2/html/index_BoardCont.jsp"); //뷰페이지 경로 설정
        } else if(state.equals("edit")){//수정폼
            if(id== null){
                out.println("<script>");
                out.println("alert('로그인하세요.')");
                out.println("history.back();");
                out.println("</script>");
            } else if (!id.equals(board_name)) {
                out.println("<script>");
                out.println("alert('권한이 없습니다.')");
                out.println("history.back();");
                out.println("</script>");
            } else{
                forward.setPath("/front2/html/index_board_edit.jsp");
            }
        }else if(state.equals("del")) {//삭제폼
            if(id== null){
                out.println("<script>");
                out.println("alert('로그인하세요.')");
                out.println("history.back();");
                out.println("</script>");
            } else if (!id.equals(board_name)) {
                out.println("<script>");
                out.println("alert('권한이 없습니다.')");
                out.println("history.back();");
                out.println("</script>");
            }else{
                forward.setPath("./front2/html/index_board_del.jsp");
            }

        }
        return forward;
    }
}
