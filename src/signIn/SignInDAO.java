package signIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignInDAO {
    String driver = "oracle.jdbc.OracleDriver";
    String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    String user = "system";
    String pwd = "oracle";

    Connection con = null;//DB연결 con
    PreparedStatement pstmt = null;//
    ResultSet rs = null;//select문 수행후 검색 결과 레코드를 저장할 rs
    String sql = null;// 쿼리문 저장

    public int insertSignIn(SignInDTO s) {
        int re = -1; //저장 실패시 반환값

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
            sql = "insert into member (no, id, password, name, birth, gender, email, phoneNum) values(memberSequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, s.getId());
            pstmt.setString(2, s.getPassword());
            pstmt.setString(3, s.getName());
            pstmt.setString(4, s.getBirth());
            pstmt.setString(5, s.getGender());
            pstmt.setString(6, s.getEmail());
            pstmt.setString(7, s.getPhoneNum());

            re = pstmt.executeUpdate();//저장 쿼리문 수행후 성공한 레코드 행의 갯수 반환.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return re;
    }

    public Integer findNo(String findId) {
        Integer re = -1;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
            sql = "select id from member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, findId);//쿼리문의 첫번째 물음표에 정수 숫자로 번호 저장
            rs = pstmt.executeQuery();

            if (rs.next()) {//검색 레코드가 있으면 re를 1로 바꿈.
                re = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return re;
    }//findNo()



}
