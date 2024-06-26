package ui;

import domain.SelectMember;
import event.BoardEvent;
import event.WriteEvent;
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

public class BoardUI extends JFrame {

  private BoardRepository boardRepository;
  private JPanel boardPanel;
  private JPanel boardPanelParent;
  private JTextField textSearch;
  private JTable table;
  private JScrollPane scrollPane;
  private DefaultHeaderUI defaultHeader;
  private String colNames[] = {"글번호", "제목", "글내용", "글쓴이", "작성일"};
  private DefaultTableModel model;

  private MainFormUI mainForm;

  public BoardUI(MainFormUI mainFormUI, SelectMember member) {
    mainForm = mainFormUI;
    boardRepository = new BoardRepository();
    model = new DefaultTableModel(colNames, 0) {
      public boolean isCellEditable(int r, int c) {
        return (c == 5) ? true : false;
      }
    };

    //setBounds(100, 100, 400, 710);
    boardPanel = new JPanel();

    boardPanelParent = new JPanel();
    boardPanelParent.add(boardPanel);
    boardPanel.setBounds(10, 65, 400, 665);
    boardPanelParent.setLayout(null);

    setContentPane(boardPanelParent);

    // 헤더 추가
    defaultHeader = new DefaultHeaderUI("login", this, mainForm, member);
    add(defaultHeader);

    textSearch = new JTextField();
    textSearch.setBounds(30, 52, 208, 27);
    textSearch.setText("내용 검색");
    boardPanel.add(textSearch);
    textSearch.setColumns(10);
    textSearch.addMouseListener(new BoardEvent(textSearch, table, model));


    JButton writeButton = new JButton("글쓰기");
    writeButton.setBounds(250, 52, 109, 27);
    writeButton.addMouseListener(new WriteEvent(member, writeButton));
    boardPanel.add(writeButton);


    scrollPane = new JScrollPane();
    boardPanel.add(scrollPane);

    table = new JTable(model);
    table.setToolTipText("클릭하면 상세 보기");
    scrollPane.setViewportView(table);
    table.setSurrendersFocusOnKeystroke(true);
    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    boardRepository.selectAllArticle(model);

    table.getColumn("글번호").setPreferredWidth(10);
    table.getColumn("제목").setPreferredWidth(45);
    table.getColumn("글내용").setPreferredWidth(100);
    table.getColumn("글쓴이").setPreferredWidth(20);
    table.getColumn("작성일").setPreferredWidth(40);

    textSearch.addKeyListener(new BoardEvent(textSearch, table, model));

    setVisible(true);
    setSize(400, 710);
  }

}
