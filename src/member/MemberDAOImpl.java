package member;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl { //회원관리 jdbc
    Connection con = null;// db연결
    PreparedStatement ps = null; //쿼리문 수행
    ResultSet rs = null;
    DataSource ds = null;
    String sql = null;

    public MemberDAOImpl() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xe");//server.xml의 xe를 JNDI 디렉토리 서비스로 찾아서 커넥슌 풀 관리 ds 생성
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberVo idCheck(String id) {
        MemberVo m = null;
        try {
            con = ds.getConnection();
            sql = "slect * from member where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);//춰리문 첫번째 물음표에 문자열로  아이디 저장
            rs = ps.executeQuery();

            if (rs.next()) {//다음 레코드 행이 존재하면 참
                m = new MemberVo();
                m.setId(rs.getString("id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return m;
    }//idCheck()

    //회원저장
    public int insertMember(MemberVo m) {
        int re = -1; //삭제 실패시 반환값

        try {
            con = ds.getConnection();
            sql = "insert into member (no, id, password, name, birth, gender, email, phoneNum) values(memberSequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, m.getId());
            ps.setString(2, m.getPassword());
            ps.setString(3, m.getName());
            ps.setString(4, m.getBirth());
            ps.setString(5, m.getGender());
            ps.setString(6, m.getEmail());
            ps.setString(7, m.getPhoneNum());

            re = ps.executeUpdate();//저장 쿼리문 수행후 성공한 레코드 행의 개수를 반환


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return re;
    }//insertMember()


    public MemberVo pwdMember(MemberVo m) {
        MemberVo pm = null;

        try {
            con = ds.getConnection();
            sql = "select mem_pwd form member where mem_id=? and mem_name=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getId());
            ps.setString(2, m.getName());
            rs = ps.executeQuery();

            if (rs.next()) {
                pm = new MemberVo();
                pm.setPassword(rs.getString("mem_pwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pm;
    }//PwdMember()

    public MemberVo logincheck(String id) {
        MemberVo m = null;

        try {
            con = ds.getConnection();
            sql = "select * from member where id=?";
            ps = con.prepareStatement(sql);//쿼리문을 미리 컴파일 해서 수행할 pt생성
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                m = new MemberVo();
                m.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return m;
    }

    //아이디에 해당하는 회원정보 가져오기
    public MemberVo getMember(String id) {
        MemberVo m = null;
        try {
            con = ds.getConnection();//커넥션 풀로 db 연결 con 생성
            sql = "select * from member where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);//쿼리문의 첫번째 물음펴ㅛ에 문자열로 id를 저장
            rs = ps.executeQuery();//select문 수행후 결과 레코드를 rs에 저장
            if (rs.next()) {//검색된 다음레코드 행이 존재하면 참. 검색결과가 하나인 경우 실행
                m = new MemberVo();
                m.setId(rs.getString("id"));//mem_id컬럼으로부터 문자열로 회원 아이디 레코드를 가져와서 해당 setter()에 저장
                m.setPassword(rs.getString("password"));
                m.setName(rs.getString("name"));
                m.setBirth(rs.getString("birth"));
                m.setGender(rs.getString("gender"));
                m.setEmail(rs.getString("email"));
                m.setPhoneNum(rs.getString("phoneNum"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return m;
    }

    //정보수정
    public void updateMember(MemberVo m) {
        try {
            con = ds.getConnection();
            sql = "update member set password=?,name=?,birth=?,gender=?,email=?,phoneNum=? where id=?";
            ps = con.prepareStatement(sql);//쿼리문을 미리 컴파일 해서 수행할 pt생성
            ps.setString(1, m.getPassword());
            ps.setString(2, m.getName());
            ps.setString(3, m.getBirth());
            ps.setString(4, m.getGender());
            ps.setString(5, m.getEmail());
            ps.setString(6, m.getPhoneNum());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    //updqtdMember()

//    //회원탈퇴
//    public void delMember(MemberVo dm) {
//        try {
//            con = ds.getConnection();
//            sql = "update member set mem_delcont=?,mem_deldate=sysdate,mem_state=2 where mem_id=?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, dm.getMem_delcont());
//            ps.setString(2, dm.getMem_id());
//            ps.executeUpdate();
//        } catch (Exception e) {e.printStackTrace();}
//        finally {
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (Exception e) {e.printStackTrace();}
//        }
//    }
  }
}
