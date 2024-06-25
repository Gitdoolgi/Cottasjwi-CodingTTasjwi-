package ui;


import event.RegisterEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class RegisterUserUI extends JFrame {
  private List<JTextField> textFieldList;
  private JTextField idTextFiedl;
  private JTextField passwordTextField;
  private JTextField nameTextField;
  private JTextField passwordCheckTextField;
  private JTextField addressTextField;
  private JTextField phoneNumTextfield;
  private DefaultHeaderUI defaultHeaderUI;

  private LoginFormUI loginFormUI;

  public RegisterUserUI(LoginFormUI loginFormUI) {
    this.loginFormUI = loginFormUI;

    defaultHeaderUI = new DefaultHeaderUI("register", this, loginFormUI, null);
    add(defaultHeaderUI);


    // 로고 패널
    JPanel logoPanel = new JPanel();
    logoPanel.setBounds(45, 62, 300, 175);
    add(logoPanel);

    Image logo = new ImageIcon("images/티스푼.png").getImage();
    Image resizeLogo = logo.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
    JLabel logoLabel = new JLabel(new ImageIcon(resizeLogo));
    logoPanel.add(logoLabel);

    // 회원가입 패널
    JPanel joinPanel = new JPanel();
    joinPanel.setBounds(20, 235, 400, 404);
    joinPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
    joinPanel.setLayout(null);
    joinPanel.setBackground(new Color(192, 220, 233));
    add(joinPanel);

    // 아이디 라벨, 텍스트필드
    JLabel idLabel = new JLabel("아이디");
    idLabel.setBounds(-5, 10, 85, 45);
    idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    joinPanel.add(idLabel);

    idTextFiedl = new JTextField();
    idTextFiedl.setBounds(83, 10, 242, 45);
    idTextFiedl.setColumns(10);
    joinPanel.add(idTextFiedl);

    // 비밀번호 라벨, 텍스트필드
    JLabel passwordLabel = new JLabel("비밀번호");
    passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    passwordLabel.setBounds(-5, 65, 85, 45);
    joinPanel.add(passwordLabel);

    passwordTextField = new JTextField();
    passwordTextField.setColumns(10);
    passwordTextField.setBounds(83, 65, 242, 45);
    joinPanel.add(passwordTextField);

    // 비밀번호 확인 라벨, 텍스트필드
    JLabel passworCheckdLabel = new JLabel("비밀번호확인");
    passworCheckdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    passworCheckdLabel.setBounds(-5, 120, 85, 45);
    joinPanel.add(passworCheckdLabel);

    passwordCheckTextField = new JTextField();
    passwordCheckTextField.setColumns(10);
    passwordCheckTextField.setBounds(83, 120, 242, 45);
    joinPanel.add(passwordCheckTextField);

    // 이름 라벨, 텍스트필드
    JLabel nameLabel = new JLabel("이름");
    nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    nameLabel.setBounds(-5, 175, 85, 45);
    joinPanel.add(nameLabel);

    nameTextField = new JTextField();
    nameTextField.setColumns(10);
    nameTextField.setBounds(83, 175, 242, 45);
    joinPanel.add(nameTextField);

    // 핸드폰번호 라벨, 텍스프필드
    JLabel phoneNumLabel = new JLabel("핸드폰번호");
    phoneNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    phoneNumLabel.setBounds(-5, 230, 85, 45);
    joinPanel.add(phoneNumLabel);

    phoneNumTextfield = new JTextField();
    phoneNumTextfield.setColumns(10);
    phoneNumTextfield.setBounds(83, 230, 242, 45);
    joinPanel.add(phoneNumTextfield);

    // 주소 라벨, 텍스트필드
    JLabel addressLabel = new JLabel("주소");
    addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    addressLabel.setBounds(-5, 285, 85, 45);
    joinPanel.add(addressLabel);

    addressTextField = new JTextField();
    addressTextField.setColumns(10);
    addressTextField.setBounds(83, 285, 242, 45);
    joinPanel.add(addressTextField);

    // 회원가입 버튼
    JButton joinBtn = new JButton("회원가입");
    joinBtn.setBounds(130, 359, 110, 35);
    joinPanel.add(joinBtn);

    textFieldList = new ArrayList<>();
    textFieldList.add(idTextFiedl);
    textFieldList.add(passwordTextField);
    textFieldList.add(passwordCheckTextField);
    textFieldList.add(nameTextField);
    textFieldList.add(phoneNumTextfield);
    textFieldList.add(addressTextField);

    joinBtn.addActionListener(new RegisterEvent(textFieldList, this, loginFormUI));
    for (JTextField jtf : textFieldList) {
      jtf.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            joinBtn.doClick();
          }
        }
      });
    }
    setVisible(false);
    setUI();
  }


  private void setUI() {
    setLayout(new BorderLayout());
    getContentPane().setBackground(new Color(192, 220, 233));
    setLocationRelativeTo(null);
    setVisible(true);
    setSize(400, 710);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}