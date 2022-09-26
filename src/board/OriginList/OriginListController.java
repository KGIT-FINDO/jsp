package board.OriginList;

import board.originWrite.BoardDAOImpl;
import board.reply.ReplyDAOImpl;
import board.vo.BoardVO;
import board.vo.ReplyVO;
import member.action.Action;
import member.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OriginListController implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int page = 1;//쪽번호(페이징 쪽나누기에서 페이지 번호)
        int limit = 10;
        String find_field = null;//검색필드(board_title, board_cont)
        String find_name = null;//검색어

        if (request.getParameter("page") != null) {//get으로 전달된 쪽번호가 있는 경우 실행
            page = Integer.parseInt(request.getParameter("page"));//페이지 번호를 정수 숫자로 변경해서 저장.
        }
        find_field = request.getParameter("find_field");
        if (request.getParameter("find_name") != null) {//검색어가 있는 경우
            find_name = request.getParameter("find_name").trim();// trim()메서드로 양쪽 공백을 제거해서 저장.
        }

        BoardVO findB = new BoardVO();
        ReplyVO findR = new ReplyVO();

        findB.setFind_field(find_field);
        findB.setFind_name("%" + find_name + "%");// '%'는 쿼리문 와일드 카드 문자로서 검색어에서 하나 이상의 임의의 모르는 문자와 매핑 대응한다.

        BoardDAOImpl bdao = new BoardDAOImpl();
        ReplyDAOImpl rdao = new ReplyDAOImpl();
        int listcount = bdao.getListCount(findB);//검색 전 후 레코드 개수
        List<BoardVO> blist = bdao.getBoardList(page, limit, findB);//검색 전 후 목록
        List<ReplyVO> rlist = rdao.getReplyList(findR);

        int maxpage = (int) ((double) listcount / limit + 0.95);//총 페이지 수
        int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;//시작페이지
        int endpage = maxpage;//마지막 페이지.

        if (endpage > startpage + 10 - 1) endpage = startpage + 10 - 1;
        request.setAttribute("blist", blist); //blist속성 키 이름에 목록을 저장
        request.setAttribute("page", page);//쪽번호 저장
        request.setAttribute("startpage", startpage);
        request.setAttribute("endpage", endpage);
        request.setAttribute("maxpage", maxpage);
        request.setAttribute("listcount", listcount);//레코드 갯수
        request.setAttribute("find_field", find_field);//board_title, board_count 검색필드 저장
        request.setAttribute("find_name", find_name);//검색어

        ActionForward forward = new ActionForward();

        forward.setRedirect(false);
        forward.setPath("./view/board/board_list.jsp");

        return forward;
    }
}
