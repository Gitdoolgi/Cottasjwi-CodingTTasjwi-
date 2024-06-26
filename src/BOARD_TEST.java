/*

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;

public class BOARD_TEST extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;
  private JPanel boardPanel;
  private JTextField textSearch;
  private JTable table;
  private JScrollPane scrollPane;
  private JButton writeButton;

  private String colNames[] = {"번호", "제목", "내용", "작성자", "작성일"};
  private DefaultTableModel model;

  Connection con;
  Statement stmt;
  PreparedStatement pstmt1; // select 메소드에서 사용하는 pstmt
  PreparedStatement pstmt2; // search용으로 사용한 pstmt

  ResultSet rs;
  ResultSetMetaData rsmd; //일단은 필요없는 상태

  String driver = "org.mariadb.jdbc.Driver";
  String url = "jdbc:mariadb://10.41.2.94/tspoon";


  @SuppressWarnings("serial")
  public BOARD_TEST() {
    model = new DefaultTableModel(colNames, 0) {
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


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 400, 711);
    boardPanel = new JPanel();
    boardPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(boardPanel);
    boardPanel.setLayout(null);

    textSearch = new JTextField();
    textSearch.setBounds(30, 52, 208, 27);
    textSearch.setText("내용 검색");
    boardPanel.add(textSearch);
    textSearch.setColumns(10);

    textSearch.addMouseListener(new MouseListener() {
      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        int clickTextBox = e.getClickCount();
        if (clickTextBox >= 1) {
          textSearch.setText("");
          clickTextBox = 0;
        }
      }
    });

    textSearch.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
          String t1 = textSearch.getText();
          String sql2 = "select board_no,title,article,id,board_date from board join tspoon_member using(tspoon_no) where article like '%" + t1 + "%' and board_yn = 0 order by board_no desc";

          //DefaultTableModel model = (DefaultTableModel)table.getModel();
          model.setNumRows(0);

          try {
            pstmt2 = con.prepareStatement(sql2);
            rs = pstmt2.executeQuery();
            while (rs.next()) {
              model.addRow(new Object[]{rs.getInt("BOARD_NO"), rs.getString("TITLE"), rs.getString("ARTICLE"), rs.getString("id"), rs.getDate("BOARD_DATE")});
            }
          } catch (SQLException se) {
            System.out.print("연결 실패");
          } catch (NullPointerException npe) {
          }
        }

      }
    });

    JButton writeButton = new JButton("글쓰기");
    writeButton.setBounds(250, 52, 109, 27);
    writeButton.addActionListener(this);
    boardPanel.add(writeButton);


    scrollPane = new JScrollPane();
    scrollPane.setBounds(30, 108, 327, 534);

    boardPanel.add(scrollPane);

    table = new JTable(model);
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {

        TableModel model = table.getModel();
        int row = table.getSelectedRow();

        int dataNo = (int) model.getValueAt(row, 0);
        String dataId = (String) model.getValueAt(row, 3); // 넘겨받고 형변환할것

        //WRITE_PANEL selectUser = new WRITE_PANEL(dataNo,dataId); // 다른 클래스에 boardNo 넘기기

        boardPanel.setVisible(false);
      }
    });
    table.setToolTipText("클릭하면 상세 보기");
    scrollPane.setViewportView(table);
    table.setSurrendersFocusOnKeystroke(true);
    table.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 12));
    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    table.getColumn("번호").setPreferredWidth(10);
    table.getColumn("제목").setPreferredWidth(45);
    table.getColumn("내용").setPreferredWidth(100);
    table.getColumn("작성자").setPreferredWidth(20);
    table.getColumn("작성일").setPreferredWidth(40);


    select();

  }

  void select() {
    String sql1 = "select board_no,title,article,id,board_date from board join tspoon_member using(tspoon_no) where board_yn=0 order by board_no desc";
    try {
      pstmt1 = con.prepareStatement(sql1);
      rs = pstmt1.executeQuery();
      //rsmd = rs.getMetaData();
      while (rs.next()) {
        model.addRow(new Object[]{rs.getInt("BOARD_NO"), rs.getString("TITLE"), rs.getString("ARTICLE"), rs.getString("id"), rs.getDate("BOARD_DATE")});
      }
    } catch (SQLException se) {
      System.out.print("잘못된 쿼리문입니다.");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == writeButton) {
      boardPanel.setVisible(false);
    }
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          BOARD_TEST frame = new BOARD_TEST();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

  }
}
*/
