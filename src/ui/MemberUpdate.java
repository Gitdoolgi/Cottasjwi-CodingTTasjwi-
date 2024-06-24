package ui;

import event.MemberUpdateEvent;

import java.awt.*;
import javax.swing.*;

public class MemberUpdate extends JFrame {
  private Container cp2 = getContentPane();
  private JPanel p2;
  private JButton b_PWD, b_User_Edit_Check;
  private JLabel label_Edit_Main, label_PWD, label_PN;
  private JPasswordField passwordField;
  private JTextField textField_PN;

  Color c1 = new Color(139, 73, 39);//갈
  Color c13 = new Color(251, 172, 204);
  Color c14 = new Color(223, 143, 174);//찐찐핑
  Color c15 = new Color(141, 198, 129);//연두
  Color c16 = new Color(219, 242, 201);//연연두
  Color c18 = new Color(204, 228, 251);//하늘


  @SuppressWarnings("deprecation")
  public void init() {
    cp2.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
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
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    gbc.insets = new Insets(10, 5, 20, 20);
    b_User_Edit_Check = new JButton("수정하기");
    cp2.add(b_User_Edit_Check, gbc);
    b_User_Edit_Check.setBackground(c16);

    b_User_Edit_Check.addActionListener(new MemberUpdateEvent(this));

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

  public static void main(String[] args) {
    MemberUpdate a = new MemberUpdate();
    a.init();
  }
}