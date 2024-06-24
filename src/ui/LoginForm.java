package ui;

import domain.ReceiveMember;
import event.LoginEvent;
import repository.MemberRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.logging.Logger;

public class LoginForm extends JFrame {

  private static final Logger logger = Logger.getLogger(LoginForm.class.getName());
  private MemberRepository memberRepository;
  //id, pwd 입력
  private static final JLabel idLabel = new JLabel("ID : ");
  private static final JTextField idField = new JTextField(10);
  private static final JLabel pwdLabel = new JLabel("PWD : ");
  private static final JPasswordField pwdField = new JPasswordField(10);
  //로그인,회원가입 버튼
  static final JButton loginButton = new JButton("Login");
  private static final JButton registerButton = new JButton("Register");
  private static LoginForm loginForm;

  public LoginForm() {
    memberRepository = new MemberRepository();
    loginForm = this;
    init();
  }

  public static LoginForm getLoginForm() {
    if (loginForm == null) {
      loginForm = new LoginForm();
    }
    return loginForm;
  }

  void clearFields() {
    idField.setText("");
    pwdField.setText("");
  }

  void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  //로그인
  private boolean isPasswordMatch(char[] enteredPassword, char[] storedPassword) {
    try {
      return Arrays.equals(storedPassword, enteredPassword);
    } finally {
      Arrays.fill(enteredPassword, '\0');
      Arrays.fill(storedPassword, '\0');
    }
  }

  void login() {
    String userId = idField.getText();
    ReceiveMember userInformation = memberRepository.selectMember(userId);
    char[] enteredPassword = pwdField.getPassword();
    char[] storedPassword = memberRepository.selectUserPassword(userId);
    if (userId.isEmpty() || storedPassword == null || enteredPassword.length == 0) {
      showMessage("Login Failed");
      logger.info("storedPassword is empty or enteredPassword is empty");
      return;
    }
    if (isPasswordMatch(enteredPassword, storedPassword)) {
      showMessage("Login Successful");
      MainForm mainForm = MainForm.getMainForm(userInformation);
      mainForm.setVisible(true); // MainForm 초기화
      setVisible(false);
    } else {
      showMessage("Login Failed");
    }
  }

  KeyAdapter enterKeyListener() {
    return new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          loginButton.doClick();
        }
      }
    };
  }

  void init() {
    Container cp = getContentPane();
    cp.setLayout(null);
    //버튼, 입력
    cp.add(idLabel);
    idLabel.setBounds(80, 285, 70, 30);

    cp.add(idField);
    idField.setBounds(160, 285, 160, 30);
    idField.addKeyListener(new LoginEvent(loginButton));

    cp.add(pwdLabel);
    pwdLabel.setBounds(80, 330, 70, 30);

    cp.add(pwdField);
    pwdField.setBounds(160, 330, 160, 30);
    pwdField.addKeyListener(enterKeyListener());

    cp.add(loginButton);
    loginButton.setBounds(80, 375, 115, 30);
    loginButton.addActionListener(e -> login());

    cp.add(registerButton);
    registerButton.setBounds(205, 375, 115, 30);
    registerButton.addActionListener(e -> {
      new RegisterUser(this);
      setVisible(false);
    });

    setUI();
  }

  void setUI() {
    setTitle("Tspoon");
    setSize(400, 710);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
