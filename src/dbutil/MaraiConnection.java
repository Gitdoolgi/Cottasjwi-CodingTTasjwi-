package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaraiConnection {

  private static MaraiConnection instance;
  private Connection con;
  private String url = "jdbc:mariadb://localhost:3306/tspoon";
  private String username = "ttasjwi";
  private String password = "ttasjwi";

  private MaraiConnection(){
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection(){
    return con;
  }

  public static MaraiConnection getInstance(){
    try {
      if(instance == null || instance.getConnection().isClosed()) instance = new MaraiConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return instance;
  }

}
