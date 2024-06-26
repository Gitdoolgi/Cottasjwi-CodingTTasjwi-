/*

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WRITE_BOARD extends JFrame {

  //////////GUI 관련 선언부//////////////
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField articleTitle;

  String tagName[] = {"자유", "질문", "정보", "수업", "공지"};
  JComboBox<String> tagBox;

  ///////////JDBC 관련 선언부///////////////

  Connection con;
  PreparedStatement pstmtInText;
  //ResultSet rsArticle; // 자세히보기쪽 class와 이름이 일치하니 합칠때 빼도 괜찮음

*/
/*  String driver = "org.mariadb.jdbc.Driver";
  String url = "jdbc:mariadb://10.41.2.94/tspoon"; //


  public WRITE_BOARD() {
    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url, "ttasjwi", "ttasjwi");
    } catch (ClassNotFoundException cnfe) {
      System.out.println("클래스를 찾지 못했습니다");
    } catch (SQLException se) {
      System.out.println("연결 실패");
    }*//*



    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 400, 710);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    articleTitle = new JTextField();
    articleTitle.setBounds(124, 85, 248, 30);
    //articleTitle.
    contentPane.add(articleTitle);
    articleTitle.setColumns(10);

    JTextArea articleDetail = new JTextArea();
    articleDetail.setBounds(12, 145, 360, 380);
    contentPane.add(articleDetail);

    JButton registerArticle = new JButton("게시글 등록");
    registerArticle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();

        if (obj == registerArticle) {
          JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "글 올리기", JOptionPane.YES_NO_OPTION);
        }
      }
    });
    registerArticle.setBounds(253, 543, 119, 39);
    contentPane.add(registerArticle);

    tagBox = new JComboBox<String>(tagName);

    tagBox.setBounds(12, 85, 100, 30);
    contentPane.add(tagBox);

  }

  void registerArticle() {

  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WRITE_BOARD frame = new WRITE_BOARD();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }


}


*/
