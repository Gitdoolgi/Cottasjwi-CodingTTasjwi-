/*

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WRITE_PANEL extends JFrame {

  private static final long serialVersionUID = 1L;


  //// GUI관련 선언부
  String getArticle;
  private DefaultTableModel replySet;
  private JPanel contentPane;
  private JTable replyCollection;
  private JTextArea textArticle, textReply;
  private String colReply[] = {"번호", "댓글", "ID", "등록일"};

  @SuppressWarnings("serial")
  public WRITE_PANEL() {
    replySet = new DefaultTableModel(colReply, 0) {
      public boolean isCellEditable(int r, int c) {
        return (c == 5) ? true : false;
      }
    };

    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url, "ttasjwi", "ttasjwi");
    } catch (ClassNotFoundException cnfe) {
      System.out.print("클래스를 찾지 못했습니다");
    } catch (SQLException se) {
      System.out.print("연결 실패");
    }

    replyCollection = new JTable(replySet);
    replyCollection.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        TableModel replySetTM = replyCollection.getModel();
        int replyRow = replyCollection.getSelectedRow();
        //System.out.print(replyRow);

        replyNoData = (int) replySetTM.getValueAt(replyRow, 0);
        int choiceDelete = -1;
        choiceDelete = JOptionPane.showConfirmDialog(null, "댓글을 삭제하시겠습니까?", "댓글 삭제?", JOptionPane.YES_OPTION);
        if (choiceDelete == JOptionPane.YES_OPTION) {
          deleteReply();
          JOptionPane.showMessageDialog(null, "삭제되었습니다", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
          replySet.setNumRows(0);
          selectReply();
        }


      }
    });

    replyCollection.setBackground(new Color(255, 255, 204));
    replyCollection.getColumn("번호").setPreferredWidth(0);
    replyCollection.getColumn("댓글").setPreferredWidth(200);
    replyCollection.getColumn("ID").setPreferredWidth(20);
    replyCollection.getColumn("등록일").setPreferredWidth(70);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 400, 711);

    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);


    JScrollPane scrollPane2 = new JScrollPane();
    scrollPane2.setFont(new Font("굴림", Font.BOLD, 16));
    scrollPane2.setBounds(12, 293, 360, 300);
    contentPane.add(scrollPane2);

    scrollPane2.setViewportView(replyCollection);

    textArticle = new JTextArea();
    textArticle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == textArticle) {
          textArticle.setEditable(true);
        }
      }
    });
    textArticle.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
    textArticle.setSelectionColor(SystemColor.textHighlight);

    textArticle.append(getArticle);
    textArticle.setEnabled(true);
    textArticle.setEditable(false);
    textArticle.setBounds(12, 38, 360, 184);
    contentPane.add(textArticle);

    textReply = new JTextArea();
    textReply.setBounds(12, 604, 251, 24);
    textReply.setLineWrap(true);
    contentPane.add(textReply);

/////////////////////////////버튼 시작////////////////////

    JButton insertReplyButton = new JButton("댓글달기");
    insertReplyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == insertReplyButton) {
          insertReply();
        }
      }
    });
    insertReplyButton.setBounds(275, 605, 97, 23);
    contentPane.add(insertReplyButton);

    JButton deleteButton = new JButton("삭제"); // 로그인 비교해서 button의 setVisible의 값을 바꿔야함
    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        int choice = -1;
        if (obj == deleteButton) {
          choice = JOptionPane.showConfirmDialog(deleteButton, "작성글을 지우시겠습니까?", "작성글 삭제?", JOptionPane.YES_OPTION);
          if (choice == JOptionPane.YES_OPTION) {
            deleteArticle(); //다음으로 게시판으로 돌아가는 로직 필요
            contentPane.setVisible(false);
          }
        }
      }
    });
    deleteButton.setBounds(166, 233, 97, 23);
    contentPane.add(deleteButton);

    JButton updateButton = new JButton("수정");
    updateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == updateButton) {
          updateArticle();
        }
      }
    });
    updateButton.setHorizontalTextPosition(SwingConstants.LEFT);
    updateButton.setBounds(275, 233, 97, 23);
    contentPane.add(updateButton);

    showArticle();
    selectReply();

  }

  int boardNo = 5;

  void showArticle() {

    String sqlArticle = "select ARTICLE,BOARD_NO from BOARD where board_no = 5"; //where board no = x 로 비교

    try {
      pstmt3 = con.prepareStatement(sqlArticle);
      rsArticle = pstmt3.executeQuery();

      while (rsArticle.next()) {
        getArticle = rsArticle.getString("ARTICLE");
        boardNo = rsArticle.getInt("BOARD_NO");
      }
    } catch (SQLException se) {
    } catch (NullPointerException npe) {
    }
  }

  void selectReply() { // 번호, 댓글내용, 아이디 (일단 전체 댓글)
    //int tspoonNo = 0;

    String sqlReply = "select REPLY_NO,CONTENT,ID,REPLY_DATE from reply join tspoon_member using(tspoon_no) where BOARD_NO = " + boardNo + " and REPLY_YN=0 order by REPLY_DATE"; //using 뒤에 board_no = x 로 비교
    try {
      pstmt4 = con.prepareStatement(sqlReply);
      rsReply = pstmt4.executeQuery();
      rsmd = rsReply.getMetaData();

      while (rsReply.next()) {
        replySet.addRow(new Object[]{rsReply.getInt("REPLY_NO"), rsReply.getString("CONTENT"), rsReply.getString("ID"), rsReply.getDate("REPLY_DATE")});
      }
    } catch (SQLException se) {
    }

  }

  void deleteArticle() {
    String sqlDel = "update BOARD set BOARD_YN = 1 where BOARD_NO = " + boardNo;
    try {
      pstmtDel = con.prepareStatement(sqlDel);
      pstmtDel.executeUpdate();
      System.out.println("삭제 성공");
    } catch (SQLException se) {
      System.out.println("삭제 실패");
    }

  }

  void updateArticle() {

    String updateText = "";
    updateText = textArticle.getText();
    updateText = "\"" + updateText + "\"";

    String sqlUp = "update BOARD set ARTICLE = " + updateText + " where BOARD_NO = " + boardNo;
    try {
      pstmtUpd = con.prepareStatement(sqlUp);
      pstmtUpd.executeUpdate();
    } catch (SQLException se) {
    }

    showArticle();
  }

  void insertReply() {
    String insertReply = "";
    insertReply = textReply.getText();
    insertReply = "\"" + insertReply + "\"";

    String sqlInReply = "insert into REPLY(CONTENT,REPLY_DATE,REPLY_YN,BOARD_NO,TSPOON_NO) values (" + insertReply + ",now(),0," + boardNo + "," + tspoonNo + ")"; // 꼭 수정 요망
    try {
      pstmtInsRe = con.prepareStatement(sqlInReply);
      pstmtInsRe.executeUpdate();
    } catch (SQLException se) {
    }

    //DefaultTableModel replySet = (DefaultTableModel)replyCollection.getModel();
    replySet.setNumRows(0);

    selectReply();

    textReply.setText("");
  }

  void deleteReply() {
    String deleteReSql = "update REPLY set REPLY_YN = 1 where REPLY_NO=" + replyNoData + " and TSPOON_NO=" + tspoonNo;

    try {
      pstmtDelReply = con.prepareStatement(deleteReSql);
      pstmtDelReply.executeUpdate();
    } catch (SQLException se) {
    }
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WRITE_PANEL frame = new WRITE_PANEL();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
*/
