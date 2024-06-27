package ui;

import domain.SelectMember;
import event.BtnTest;
import style.ColorSet;

import java.awt.*;
import javax.swing.*;

public class MainFormUI extends JFrame {

  //컴포넌트 생성
  private static final JButton boardButton = new JButton("게시판");
  private static final JButton classButton = new JButton("학습현황");
  private SelectMember userInformation;
  private DefaultHeaderUI defaultHeader;
  private LoginFormUI loginForm;

  public MainFormUI(LoginFormUI loginForm, SelectMember member) {
    this.userInformation = member;
    this.loginForm = loginForm;
    init();
  }


  //UI
  void init() {
    Container cp = getContentPane();
    cp.setLayout(null);
    cp.setBackground(ColorSet.BACKGROUND);
    // 헤더
    defaultHeader = new DefaultHeaderUI("login-main", this, loginForm, userInformation);
    add(defaultHeader);

    cp.add(boardButton); //게시판 버튼
    boardButton.setBounds(40, 400, 150, 50);
    boardButton.addMouseListener(new BtnTest(this, loginForm, userInformation));


    cp.add(classButton); //학습현황 버튼
    classButton.setBounds(200, 400, 150, 50);
    classButton.addActionListener(e -> {
      setVisible(false);
      new StatusUI(this, userInformation);
    });

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