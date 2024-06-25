package event;

import repository.BoardRepository;
import ui.BoardUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class BoardEvent extends KeyAdapter {
  private BoardRepository boardRepository;
  private JTextField textSearch;
  private JTable table;
  DefaultTableModel model;

  public BoardEvent(JTextField textSearch, JTable table, DefaultTableModel model) {
    this.boardRepository = new BoardRepository();
    this.textSearch = textSearch;
    this.table = table;
    this.model = model;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      model.setNumRows(0);
      List<Object[]> newModel = boardRepository.selectArticle(textSearch.getText(), model);

      for (Object[] o : newModel) {
        model.addRow(o);
      }

      table.setModel(model); // JTable을 검색한 값으로 새로 바꿔서 집어넣는 과정
      table.revalidate();
      table.repaint();
    }
  }
}
