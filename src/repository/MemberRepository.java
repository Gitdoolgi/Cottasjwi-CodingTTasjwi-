package repository;

import dbutil.MariaConnection;
import domain.InsertMember;
import domain.SelectMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MemberRepository {
  public static ConcurrentHashMap<String, SelectMember> members = new ConcurrentHashMap<>();
  private Connection con = MariaConnection.getInstance().getConnection();
  private static final Logger logger = Logger.getLogger(MemberRepository.class.getName());

  private static final String INFOLOGGER = "Database connection established";

  public SelectMember selectMember(String userId) {
    logger.info(INFOLOGGER);
    SelectMember selectMember = null;
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

        selectMember = new SelectMember(tspoonNo, id, name, phoneNum, address, joinDate);
        members.put(id, selectMember);
      } else {
        logger.severe("일치하는 회원이 없습니다.");
      }
    } catch (SQLException se) {
      logger.severe("Error executing SQL query" + se.getMessage());
    } finally {
      if (rs != null) {
        MariaConnection.closeAll(pstmt, rs);
      }
    }
    return selectMember;
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
        MariaConnection.closeAll(pstmt, rs);
      }
    }
    return userPassword;
  }

  public int insertMember(InsertMember insertMember) {
    String sql = "insert into tspoon_member(id, password, name, phone_num, address, join_date) values (?,?,?,?,?,now())";
    int result = 0;
    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, insertMember.getId());
      pstmt.setString(2, insertMember.getPassword());
      pstmt.setString(3, insertMember.getName());
      pstmt.setString(4, insertMember.getPhone_num());
      pstmt.setString(5, insertMember.getAddress());

      result = pstmt.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("아이디 중복");
    }
    return result;
  }

}
