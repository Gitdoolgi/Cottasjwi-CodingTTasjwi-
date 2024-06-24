package ui;

import domain.ReceiveMember;
import event.MemberUpdateEvent;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainForm extends JFrame {

  //컴포넌트 생성
  private static final JButton logoutButton = new JButton("Logout");
  private final JButton nameButton;
  private static final JButton boardButton = new JButton("게시판");
  private static final JButton classButton = new JButton("학습현황");
  private ReceiveMember userInformation;
  private static MainForm mainForm;

  private MainForm(ReceiveMember member) {
    mainForm = this;
    this.userInformation = member;
    nameButton = new JButton(member.getName());
    init();
  }

  public static MainForm getMainForm(ReceiveMember member) {
    if (mainForm == null) {
      mainForm = new MainForm(member);
    }
    return mainForm;
  }

  void logoutAndShowLoginForm() {
    LoginForm loginForm = LoginForm.getLoginForm();
    loginForm.clearFields(); // LoginForm의 입력 필드 초기화 메서드 호출
    loginForm.setVisible(true); // LoginForm을 다시 보이도록 설정
    setVisible(false); // 현재 MainForm 닫기
  }

  //UI
  void init() {
    Container cp = getContentPane();
    cp.setLayout(null);
    cp.add(logoutButton); //로그아웃 버튼
    logoutButton.setBounds(220, 0, 80, 30);
    logoutButton.addActionListener(e -> logoutAndShowLoginForm());

    cp.add(nameButton); //로그인한 사람 이름, 버튼으로 바꿔야할 것 같음
    nameButton.setBounds(320, 0, 80, 30);
    nameButton.addActionListener(e -> {
      new MemberInfo().init();
    });

    cp.add(boardButton); //게시판 버튼
    boardButton.setBounds(40, 400, 150, 50);
    boardButton.addActionListener(e -> {
      setVisible(false);
      new Board();
    });

    cp.add(classButton); //학습현황 버튼
    classButton.setBounds(200, 400, 150, 50);
    classButton.addActionListener(e -> JOptionPane.showMessageDialog(null, classButton.getText()));
    //좌표
    setUI();
  }

  //버튼 액션
  void setUI() {
    setTitle("Tspoon");
    setSize(400, 710);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}