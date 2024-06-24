package event;

import domain.BoardRepository;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardEvent implements KeyListener {
  private BoardRepository boardRepository;
  private JTextField textField;

  public BoardEvent(JTextField textField) {
    this.boardRepository = new BoardRepository();
    this.textField = textField;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_ENTER) {
      String title = textField.getText();
      boardRepository.selectArticle(title);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
