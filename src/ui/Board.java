package ui;

import domain.BoardRepository;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class Board extends JFrame implements ActionListener {

  private BoardRepository boardRepository;

  private JPanel contentPane;
  private JTextField textSearch;
  private JTable table;
  private String colNames[] = {"글번호", "제목", "글내용", "글쓴이", "작성일"};
  private DefaultTableModel model = new DefaultTableModel(colNames, 0);


  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Board frame = new Board();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    new Board();
  }

  public Board() {

    boardRepository = new BoardRepository();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 400, 710);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    textSearch = new JTextField();
    textSearch.setBounds(30, 52, 208, 27);
    contentPane.add(textSearch);
    textSearch.setColumns(10);
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
          String title = textSearch.getText();
          boardRepository.selectArticle(title);
        }
      }
    });


    JButton btnNewButton = new JButton("글쓰기");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnNewButton.setBounds(250, 52, 109, 27);
    contentPane.add(btnNewButton);

    table = new JTable(model);
    table.setSurrendersFocusOnKeystroke(true);
    table.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 12));
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setBounds(30, 108, 327, 534);
    model.addRow(colNames);
    //table.setE(false);
    contentPane.add(table);

    select();

  }

  void select() {
    // 글제목, 내용, id, 작성일
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
