package board.originWrite;

import board.vo.BoardVO;

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

    public void updateBoard(BoardVO eb) {
        try {
            con = ds.getConnection();
            sql = "update memberT set board_title=?,board_cont=? where board_no=?";
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
            sql = "delete from boardT where board_no=?";
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
    }
