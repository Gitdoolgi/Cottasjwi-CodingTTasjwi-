package domain;

import dbutil.MaraiConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class BoardRepository {
  private Connection con = MaraiConnection.getInstance().getConnection();

  public int selectArticle(String title) {
    PreparedStatement pstmt;
    ResultSet rs;
    ResultSetMetaData rsmd;

    String sql = "select board_no,title,article,id,board_date from board join tspoon_member using(tspoon_no) " +
            "where article like '%?%' order by board_no desc";

    int result = 0;
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, title);
      rs = pstmt.executeQuery(sql);

      rsmd = rs.getMetaData();
      DefaultTableModel dtm = new DefaultTableModel();
      while (rs.next()) {
        dtm.addRow(new Object[]{rs.getInt("BOARD_NO"), rs.getString("TITLE"), rs.getString("ARTICLE"), rs.getString("id"), rs.getDate("BOARD_DATE"),});
      }

    } catch (SQLException se) {
      se.printStackTrace();
    }
    return result;
  }
}
