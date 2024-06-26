package event;

import repository.BoardRepository;
import ui.BoardUI;
import ui.WriteUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BoardEvent extends KeyAdapter implements MouseListener {
  private BoardRepository boardRepository;
  private JTextField textSearch;
  private JTable table;
  DefaultTableModel model;

  public BoardEvent(JTextField textSearch, JTable table, DefaultTableModel model) {
    if (boardRepository == null) {
      this.boardRepository = new BoardRepository();
    }
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

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    new WriteUI();

    int clickTextBox = e.getClickCount();
    if (clickTextBox >= 1) {
      textSearch.setText("");
      clickTextBox = 0;
    }
  }
}
