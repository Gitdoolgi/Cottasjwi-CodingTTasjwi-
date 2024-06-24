package repository;

import dbutil.MaraiConnection;
import domain.Member;
import domain.ReceiveMember;
import ui.LoginForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MemberRepository {
  public static ConcurrentHashMap<String, ReceiveMember> members = new ConcurrentHashMap<>();
  private Connection con = MaraiConnection.getInstance().getConnection();
  private static final Logger logger = Logger.getLogger(MemberRepository.class.getName());

  private static final String INFOLOGGER = "Database connection established";

  public ReceiveMember selectMember(String userId) {
    logger.info(INFOLOGGER);
    ReceiveMember receiveMember = null;
    String sql = "select TSPOON_NO, ID, NAME, PHONE_NUM, ADDRESS, JOIN_DATE from tspoon_member where ID=?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, userId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        int tspoonNo = rs.getInt("TSPOON_NO");
        String id = rs.getString("ID");
        String name = rs.getString("NAME");
        String phoneNum = rs.getString("PHONE_NUM");
        String address = rs.getString("ADDRESS");
        Date joinDate = rs.getDate("JOIN_DATE");

        receiveMember = new ReceiveMember(tspoonNo, id, name, phoneNum, address, joinDate);
        members.put("id", receiveMember);
      } else {
        logger.severe("일치하는 회원이 없습니다.");
      }
    } catch (SQLException se) {
      logger.severe("Error executing SQL query" + se.getMessage());
    } finally {
      if (rs != null) {
        MaraiConnection.closeAll(pstmt, rs);
      }
    }
    return receiveMember;
  }

  public char[] selectUserPassword(String id) {
    logger.info(INFOLOGGER);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    char[] userPassword = {};
    try {
      pstmt = con.prepareStatement("select password from TSPOON_MEMBER where ID=?");
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        String pwd = rs.getString("password");
        userPassword = pwd.toCharArray();
      }
    } catch (SQLException se) {
      logger.severe("Error executing SQL query" + se.getMessage());
    } finally {
      if (rs != null) {
        MaraiConnection.closeAll(pstmt, rs);
      }
    }
    return userPassword;
  }

  public int insertMember(Member member) {
    String sql = "insert into tspoon_member(id, password, name, phone_num, address, join_date) values (?,?,?,?,?,now())";
    int result = 0;
    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, member.getId());
      pstmt.setString(2, member.getPassword());
      pstmt.setString(3, member.getName());
      pstmt.setString(4, member.getPhone_num());
      pstmt.setString(5, member.getAddress());

      result = pstmt.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("아이디 중복");
    }
    return result;
  }

}
