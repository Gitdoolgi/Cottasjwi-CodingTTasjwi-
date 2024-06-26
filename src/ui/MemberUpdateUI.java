package ui;

import domain.SelectMember;
import event.MemberUpdateEvent;

import java.awt.*;
import javax.swing.*;

public class MemberUpdateUI extends JFrame {
  private Container cp2 = getContentPane();
  private JPanel p2;
  private JButton bUpdateCheck;
  private JLabel label_Edit_Main, label_PWD, label_PN, label_ADDRESS;
  private JPasswordField passwordField;
  private JTextField textField_PN, textField_ADDRESS;
  private SelectMember member;
  Color c1 = new Color(139, 73, 39);//갈
  Color c13 = new Color(251, 172, 204);
  Color c14 = new Color(223, 143, 174);//찐찐핑
  Color c15 = new Color(141, 198, 129);//연두
  Color c16 = new Color(219, 242, 201);//연연두
  Color c18 = new Color(204, 228, 251);//하늘

  public MemberUpdateUI(SelectMember member) {
    this.member = member;
  }

  public void init() {
    cp2.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 2;
    gbc.insets = new Insets(20, 5, 20, 20);
    label_Edit_Main = new JLabel();
    label_Edit_Main.setText("회원정보 수정 페이지");
    label_Edit_Main.setHorizontalAlignment(SwingConstants.CENTER);
    label_Edit_Main.setVerticalAlignment(SwingConstants.BOTTOM);
    cp2.add(label_Edit_Main, gbc);
    cp2.setBackground(c18);
    cp2.setForeground(c1);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    label_PWD = new JLabel();
    label_PWD.setText("새 비밀번호 :  ");
    cp2.add(label_PWD, gbc);
    label_PWD.setForeground(c1);

    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    passwordField = new JPasswordField(14);
    cp2.add(passwordField, gbc);
    passwordField.setForeground(c14);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    label_PN = new JLabel();
    label_PN.setText("새 전화번호 :  ");
    cp2.add(label_PN, gbc);
    label_PN.setForeground(c1);

    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    textField_PN = new JTextField(14);
    cp2.add(textField_PN, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    label_ADDRESS = new JLabel();
    label_ADDRESS.setText("새 주소 :  ");
    cp2.add(label_ADDRESS, gbc);
    label_ADDRESS.setForeground(c1);

    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    textField_ADDRESS = new JTextField(14);
    cp2.add(textField_ADDRESS, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    bUpdateCheck = new JButton("수정하기");
    cp2.add(bUpdateCheck, gbc);
    bUpdateCheck.setBackground(c16);

    bUpdateCheck.addActionListener(new MemberUpdateEvent(this, member));

    setUI();
  }

  private void setUI() {
    setTitle("내 정보 수정");
    setSize(315, 560);
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public char[] getPasswordFromField() { //비밀번호 수정
    return passwordField.getPassword();
  }

  public String getPhoneNumFromField() {
    return textField_PN.getText();
  }

  public String getAddressFromField() {
    return textField_ADDRESS.getText();
  }
}