package board.originWrite;

import board.vo.BoardVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl {
    Connection con = null;
    PreparedStatement pt = null;//쿼리문 수행 pt
    ResultSet rs = null;//검색결과 레코드를 저장할 Rs
    DataSource ds = null;//커넥션 풀 관리 ds
    String sql = null;//쿼리문 저장 변수

    public BoardDAOImpl() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xe");//커넥션 풀 관리 ds 생성
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//기본 생성자

    public void insertBoard(BoardVO b) {
        try {
            con=ds.getConnection();
            sql="insert into board (board_no,board_name,board_title,board_cont,board_date)" +
                    " values(board_seq.nextval,?,?,?,sysdate)";

            pt=con.prepareStatement(sql);
            pt.setString(1, b.getBoard_name());
            pt.setString(2, b.getBoard_title());
            pt.setString(3, b.getBoard_cont());

            pt.executeUpdate();//저장 쿼리문을 수행
        }catch(Exception e) {e.printStackTrace();}
        finally {
            try {
                if(pt != null) pt.close();
                if(con != null) con.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBoard(BoardVO eb) {
        try {
            con = ds.getConnection();
            sql = "update member set board_title=?,board_cont=? where board_no=?";
            pt = con.prepareStatement(sql);//쿼리문을 미리 컴파일 해서 수행할 pt생성
            pt.setString(1, eb.getBoard_title());
            pt.setString(2, eb.getBoard_cont());
            pt.setInt(3, eb.getBoard_no());
            pt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pt != null) pt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void delBoard(int board_no) {
        try{
            con = ds.getConnection();
            sql = "delete from board where board_no=?";
            pt=con.prepareStatement(sql);
            pt.setInt(1, board_no);

            pt.executeUpdate();


        } catch (Exception e){e.printStackTrace();}
        finally {
            try{
                if(pt != null) pt.close();
                if(con != null) con.close();
            }catch (Exception e){e.printStackTrace();}
        }
    }

    public int getListCount(BoardVO findB) {
        int count = 0;//검색 레코드 갯수
        try {
            con = ds.getConnection();
            sql = "select count(board_no) from board ";
            if (findB.getFind_field() == null) {//검색전 총 레코드 갯수
                sql += "";

            } else if (findB.getFind_field().equals("board_title")) {//검색필드가 글 제목일 경우
                sql += " where board_title like ?";
            } else if (findB.getFind_field().equals("board_cont")) {//검색필드가 글 내용인 경우
                sql += " where board_cont like ?";
            }

            pt = con.prepareStatement(sql);
            if (findB.getFind_field() != null) {//검색 필드가 있는 경우
                pt.setString(1, findB.getFind_name());//쿼리문의 첫번째 물음표에 문자열로 검색어를 저장
            }

            rs = pt.executeQuery();//검색 쿼리문 수행 후 결과 레코드를 rs에 저장
            if (rs.next()) {
                count = rs.getInt(1);//검색 전후 레코드 갯수를 저장
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pt != null) pt.close();
                if (con != null) con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }//getListCount()

    public List<BoardVO> getBoardList(int page,int limit,BoardVO findB){
        List<BoardVO> blist=new ArrayList<BoardVO>();

        try {
            con=ds.getConnection();
            sql="select * from (select rowNum rNum,board_no,board_name,board_title,"
                    +"board_hit,board_date from (select * from board ";
            if(findB.getFind_field() == null) {//검색전
                sql+="";
            }else if(findB.getFind_field().equals("board_title")) {//제목을 검색할 경우
                sql+=" where board_title like ?";
            }else if(findB.getFind_field().equals("board_cont")) {//글내용을 검색할 경우
                sql+=" where board_cont like ?";
            }
            sql+=")) where rNum >= ? and rNum<=? order by board_no desc";
            /* 페이징과 검색관련 쿼리문. rowNum컬럼은 오라클에서 테이블 생성시 추가해 주는 컬럼으로 최초 레코드 저장시
             * 일련 번호값이 알아서 저장된다.rNum은 rowNum컬럼의 별칭이름이다.
             */

            pt=con.prepareStatement(sql);

            int startrow=(page-1)*10+1;//읽기 시작할 행번호
            int endrow=startrow+limit-1;//읽을 마지막 행번호

            if(findB.getFind_field() != null) {//검색하는 경우
                pt.setString(1,findB.getFind_name());
                pt.setInt(2,startrow);
                pt.setInt(3,endrow);
            }else {//검색하지 않는 경우
                pt.setInt(1,startrow);
                pt.setInt(2,endrow);
            }

            rs=pt.executeQuery();

            while(rs.next()) {
                BoardVO b=new BoardVO();
                b.setBoard_no(rs.getInt("board_no"));//board_no컬럼으로 부터 정수 숫자로 번호 레코드값을 가져와
                //서 setter()메서드에 저장
                b.setBoard_name(rs.getString("board_name"));
                b.setBoard_title(rs.getString("board_title"));
                b.setBoard_hit(rs.getInt("board_hit"));
                b.setBoard_date(rs.getString("board_date"));

                blist.add(b);//컬렉션에 추가
            }
        }catch(Exception e) {e.printStackTrace();}
        finally {
            try {
                if(rs != null)rs.close();
                if(pt != null) pt.close();
                if(con != null) con.close();
            }catch(Exception e) {e.printStackTrace();}
        }
        return blist;
    }//getBoardList()

    public BoardVO getBoardCont(int board_no) {
        BoardVO bc = null;
        try {
            con = ds.getConnection();
            sql = "select * from board where board_no=?";
            pt = con.prepareStatement(sql);
            pt.setInt(1, board_no);
            rs = pt.executeQuery();

            if(rs.next()){
                bc = new BoardVO();
                bc.setBoard_no(rs.getInt("board_no"));
                bc.setBoard_name(rs.getString("board_name"));
                bc.setBoard_title(rs.getString("board_title"));
                bc.setBoard_cont(rs.getString("board_cont"));
                bc.setBoard_hit(rs.getInt("board_hit"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) rs.close();
                if(pt != null) pt.close();
                if(con != null) con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  bc;

    }

    public void updateHit(int board_no) {
        try{
            con = ds.getConnection();
            sql="update board set board_hit=board_hit+1 where board_no = ?";
            pt = con.prepareStatement(sql);
            pt.setInt(1, board_no);
            pt.executeUpdate();

        } catch (Exception e){e.printStackTrace();}
        finally {
            try{
                if(pt != null) pt.close();
                if(con != null) con.close();
            }catch (Exception e){e.printStackTrace();}
        }
    }
}
