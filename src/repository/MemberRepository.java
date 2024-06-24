package repository;

import dbutil.MaraiConnection;
import domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepository {
  private Connection con = MaraiConnection.getInstance().getConnection();

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
    } catch (SQLException e) {
      System.out.println("아이디 중복");
    }
    return result;
  }

}
