package ui;

import javax.swing.*;

public class WriteUI extends JFrame {
  private JPanel contentPane;
  private JTextField articleTitle;

  private String tagName[] = {"자유", "질문", "정보", "수업", "공지"};
  JComboBox<String> tagBox;

  public WriteUI() {

    setUI();
  }

  private void setUI() {
    setResizable(false);
    setVisible(true);
    setLocationRelativeTo(null);
    setBounds(100, 100, 400, 710);

  }

}
