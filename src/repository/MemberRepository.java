package repository;

import dbutil.MaraiConnection;
import domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepository {
  private Connection con = MaraiConnection.getInstance().getConnection();

  public void resetAutoincrement(int lastNo) {
    PreparedStatement pstmt = null;
    String sql = "ALTER TABLE tspoon_member AUTO_INCREMENT = ?";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, lastNo);
      pstmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int getLastAutoincrement() {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from tspoon_member order by tspoon_no desc limit 2";
    int lastNo = 0;
    try {
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      rs.last();
      lastNo = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lastNo;
  }

  public int insertMember(Member member) {
    System.out.println(member + " ?? ");
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
    } catch (SQLException e) {
      System.out.println("아이디 중복");
    }
    return result;
  }

}
