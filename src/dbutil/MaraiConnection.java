package dbutil;


import java.sql.*;
import java.util.logging.Logger;

public class MaraiConnection {
  private static final Logger logger = Logger.getLogger(MaraiConnection.class.getName());
  private static MaraiConnection instance;
  private Connection con;
  private String url = "jdbc:mariadb://localhost:3306/tspoon";
  private String username = "ttasjwi";
  private String password = "ttasjwi";

  private MaraiConnection() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection(url, username, password);
      con.setAutoCommit(false);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return con;
  }

  public static MaraiConnection getInstance() {
    try {
      if (instance == null || instance.getConnection().isClosed()) instance = new MaraiConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return instance;
  }

  public static void closeAll(PreparedStatement pstmt, ResultSet rs) {
    try {
      if (pstmt != null) {
        pstmt.close();
      }
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      logger.warning("Error closing resources" + e.getMessage());
    }
  }
}
