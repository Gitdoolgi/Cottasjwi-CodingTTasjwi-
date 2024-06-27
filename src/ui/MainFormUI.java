package ui;

import domain.SelectMember;
import event.MainEvent;
import style.ColorSet;

import java.awt.*;
import javax.swing.*;

public class MainFormUI extends JFrame {

  //컴포넌트 생성
  private static final JButton boardButton = new JButton("게시판");
  private static final JButton classButton = new JButton("학습현황");

  private static final JLabel adLabel = new JLabel();

  private SelectMember userInformation;
  private DefaultHeaderUI defaultHeader;
  private static LoginFormUI loginForm;

  public MainFormUI(LoginFormUI loginForm, SelectMember member) {
    this.userInformation = member;
    this.loginForm = loginForm;
    init();
  }

  public static LoginFormUI getLoginForm() {
    return loginForm;
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
    boardButton.setBounds(40, 500, 150, 50);
    boardButton.addMouseListener(new MainEvent(this, loginForm, userInformation));


    cp.add(classButton); //학습현황 버튼
    classButton.setBounds(200, 500, 150, 50);
    classButton.addActionListener(e -> {
      if (userInformation.getMilktId() != null) {
        new StatusUI(this, userInformation);
      } else {
        JOptionPane.showMessageDialog(null, "자식을 등록해주세요");
      }
    });
    cp.add(adLabel);
    ImageIcon ad1 = new ImageIcon("images/icon/tatg3.gif");
    adLabel.setIcon(ad1);
    adLabel.setSize(400, 400);
    adLabel.setLocation(0, 50);
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