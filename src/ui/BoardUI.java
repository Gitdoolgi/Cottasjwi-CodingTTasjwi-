package ui;

import domain.SelectMember;
import event.BoardEvent;
import repository.BoardRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

public class BoardUI extends JFrame implements ActionListener {

  private BoardRepository boardRepository;
  private JPanel boardPanel;
  private JPanel boardPanelParent;
  private JTextField textSearch;
  private JTable table;
  private JScrollPane scrollPane;
  private JButton writeButton;
  private DefaultHeaderUI defaultHeader;
  private String colNames[] = {"글번호", "제목", "글내용", "글쓴이", "작성일"};
  private DefaultTableModel model = new DefaultTableModel(colNames, 0);

  private MainFormUI prevObj;

  public BoardUI(MainFormUI mainFormUI, SelectMember member) {
    prevObj = mainFormUI;
    boardRepository = new BoardRepository();

    setBounds(100, 100, 400, 710);
    boardPanel = new JPanel();

    boardPanelParent = new JPanel();
    boardPanelParent.add(boardPanel);
    boardPanel.setBounds(30, 65, 380, 665);
    boardPanelParent.setLayout(null);

    setContentPane(boardPanelParent);

    // 헤더 추가
    defaultHeader = new DefaultHeaderUI("login", this, prevObj, member);
    add(defaultHeader);
    textSearch = new JTextField();
    textSearch.setBounds(30, 52, 208, 27);
    textSearch.setText("내용 검색");
    boardPanel.add(textSearch);
    textSearch.setColumns(10);


    JButton writeButton = new JButton("글쓰기");
    writeButton.setBounds(250, 52, 109, 27);
    writeButton.addActionListener(this);
    boardPanel.add(writeButton);

    scrollPane = new JScrollPane();

    boardPanel.add(scrollPane);

    table = new JTable(model);

    table.setToolTipText("클릭하면 상세 보기");
    scrollPane.setViewportView(table);
    table.setSurrendersFocusOnKeystroke(true);
    table.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 12));
    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    boardRepository.selectAllArticle(model);
    textSearch.addKeyListener(new BoardEvent(textSearch, table, model));
    System.out.println(table.hashCode());
    setVisible(true);
    setSize(400, 710);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == writeButton) {
      boardPanel.setVisible(false);
    }
  }
}
