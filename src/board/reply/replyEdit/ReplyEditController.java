package board.reply.replyEdit;

import board.originWrite.BoardDAOImpl;
import board.reply.ReplyDAOImpl;
import board.vo.BoardVO;
import board.vo.ReplyVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

public class ReplyEditController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String id = (String) session.getAttribute("id");
        int reply_no = Integer.parseInt(request.getParameter("reply_no"));
        String reply_name = request.getParameter("reply_name");
        String board_no = request.getParameter("board_no");
        String reply_title = request.getParameter("reply_title");
        String reply_cont = request.getParameter("reply_cont");

        if(id == null){
            out.println("<script>");
            out.println("alert('로그인하세요')");
            out.println("history.back();");
            out.println("</script>");
        }
        if(id != reply_name ){
            out.println("<script>");
            out.println("alert('로그인 하세요')");
            out.println("history.back();");
            out.println("</script>");
        }

        else {
            ReplyDAOImpl rdao = new ReplyDAOImpl();
            ReplyVO rvo = new ReplyVO();

            rvo.setReply_title(reply_title);
            rvo.setReply_cont(reply_cont);
            rvo.setReply_no(reply_no);

            rdao.updateReply(rvo);

            ActionForward forward = new ActionForward();
            forward.setRedirect(false);//새로운 매핑주소로 이동
            forward.setPath("board_cont.do?board_no="+board_no);
            return forward;
        }




        return null;
    }
}
