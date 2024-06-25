package ui;

import domain.SelectMember;

import java.awt.*;
import javax.swing.*;

public class MainFormUI extends JFrame {

  //컴포넌트 생성
  private static final JButton logoutButton = new JButton("Logout");
  private static final JButton boardButton = new JButton("게시판");
  private static final JButton classButton = new JButton("학습현황");
  private SelectMember userInformation;
  private static MainFormUI mainFormUI;
  private DefaultHeaderUI defaultHeader;

  private MainFormUI(SelectMember member) {
    mainFormUI = this;
    this.userInformation = member;
    init();
  }

  public static MainFormUI getMainForm(SelectMember member) {
    if (mainFormUI == null) {
      mainFormUI = new MainFormUI(member);
    }
    return mainFormUI;
  }


  //UI
  void init() {
    Container cp = getContentPane();
    cp.setLayout(null);

    // 헤더
    defaultHeader = new DefaultHeaderUI("login-main", this, LoginFormUI.getLoginForm(), userInformation);
    add(defaultHeader);

    cp.add(boardButton); //게시판 버튼
    boardButton.setBounds(40, 400, 150, 50);
    boardButton.addActionListener(e -> {
      setVisible(false);
      new BoardUI(this, userInformation);
    });

    cp.add(classButton); //학습현황 버튼
    classButton.setBounds(200, 400, 150, 50);
    classButton.addActionListener(e -> {
      setVisible(false);
      new StatusUI(this, userInformation);
    });
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