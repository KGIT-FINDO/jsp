package board.reply.replyDelete;

import board.reply.ReplyDAOImpl;
import board.vo.ReplyVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

public class ReplyDeleteOKController implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession httpSession = request.getSession();


        int rno = Integer.parseInt(request.getParameter("rno"));
        int board_no = Integer.parseInt(request.getParameter("board_no"));
        String id = (String) httpSession.getAttribute("id");
        String reply_name = request.getParameter("reply_name");
        if(id == null){
            out.println("<script>");
            out.println("alert('로그인하세요')");
            out.println("history.back();");
            out.println("</script>");
        }
        if(id != reply_name ){
            out.println("<script>");
            out.println("alert('본인이 작성한 댓글이 아닙니다.')");
            out.println("history.back();");
            out.println("</script>");
        }

        else {
            ReplyDAOImpl rdao = new ReplyDAOImpl();
            ReplyVO rvo = new ReplyVO();

            rdao.delReply(rno);
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("board_cont.do?board_no="+board_no);
            return forward;

        }

        return null;
    }
}
