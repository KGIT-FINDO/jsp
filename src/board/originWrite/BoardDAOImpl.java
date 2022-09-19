package board.originWrite;

import board.originWrite.vo.BoardVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            sql="insert into boardT (board_no,board_name,board_title,board_cont,board_date)" +
                    " values(boardT_no_seq.nextval,?,?,?,sysdate)";

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
}