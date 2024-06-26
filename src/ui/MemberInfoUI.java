package ui;

import domain.SelectMember;
import event.MemberInfoEvent;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class MemberInfoUI extends JFrame {
  private DefaultHeaderUI defaultHeader;
  private SelectMember member;

  public MemberInfoUI(SelectMember member) {
    this.member = member;
    init();
  }

  Color c0 = new Color(255, 255, 255);//화이트
  Color c1 = new Color(139, 73, 39);//갈
  Color c2 = new Color(251, 119, 121);//빨
  Color c3 = new Color(232, 92, 95);//찐빨
  Color c4 = new Color(250, 177, 132);//주
  Color c5 = new Color(239, 149, 96);//찐주
  Color c6 = new Color(249, 207, 206);//연핑
  Color c7 = new Color(251, 172, 204);//찐핑
  Color c8 = new Color(223, 143, 174);//찐찐핑
  Color c9 = new Color(252, 248, 206);//연노
  Color c10 = new Color(250, 228, 145);//노
  Color c11 = new Color(248, 206, 116);//찐노
  Color c12 = new Color(249, 207, 206);//연핑
  Color c13 = new Color(251, 172, 204);//찐핑
  Color c14 = new Color(223, 143, 174);//찐찐핑
  Color c15 = new Color(141, 198, 129);//연두
  Color c16 = new Color(219, 242, 201);//연연두
  Color c17 = new Color(139, 196, 127);//찐두
  Color c18 = new Color(204, 228, 251);//하늘

  Container cp = getContentPane();
  JPanel p1;
  JButton bChildUpdate, bMemberUpdate;
  JLabel label_info, label_ID, label_ID1, label_PWD, label_PWD1, label_NAME, label_NAME1, label_PN, label_PN1, label_ADDRESS, label_ADDRESS1, label_JOIN_DATE, label_JOIN_DATE1, label_C_ID, label_C_ID1;

  void init() {
    label_info = new JLabel();
    label_info.setText("☆나의 정보★");
    label_info.setHorizontalAlignment(SwingConstants.CENTER);
    label_info.setVerticalAlignment(SwingConstants.BOTTOM);
    label_info.setForeground(c1);// 색상

    label_ID = new JLabel();
    label_ID.setText("아이디  :  ");
    label_ID.setHorizontalAlignment(SwingConstants.RIGHT);
    label_ID.setForeground(c1);

    label_ID1 = new JLabel();
    label_ID1.setText(member.getId());
    label_ID1.setHorizontalAlignment(SwingConstants.LEFT);


    label_NAME = new JLabel();
    label_NAME.setText("이름  :  ");
    label_NAME.setHorizontalAlignment(SwingConstants.RIGHT);
    label_NAME.setForeground(c1);

    label_NAME1 = new JLabel();
    label_NAME1.setText(member.getName());

    label_PN = new JLabel();
    label_PN.setText("전화번호  :  ");
    label_PN.setHorizontalAlignment(SwingConstants.RIGHT);
    label_PN.setForeground(c1);

    label_PN1 = new JLabel();
    label_PN1.setText(member.getPhoneNum());

    label_ADDRESS = new JLabel();
    label_ADDRESS.setText("주소 :  ");
    label_ADDRESS.setHorizontalAlignment(SwingConstants.RIGHT);
    label_ADDRESS.setForeground(c1);

    label_ADDRESS1 = new JLabel();
    label_ADDRESS1.setText(member.getAddress());


    label_JOIN_DATE = new JLabel();
    label_JOIN_DATE.setText("가입일  :  ");
    label_JOIN_DATE.setHorizontalAlignment(SwingConstants.RIGHT);
    label_JOIN_DATE.setForeground(c1);

    label_JOIN_DATE1 = new JLabel();
    Date memberJoinDate = member.getJoinDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = dateFormat.format(memberJoinDate);
    label_JOIN_DATE1.setText(formattedDate);


    label_C_ID = new JLabel();
    label_C_ID.setText("자녀 아이디  :  ");
    label_C_ID.setHorizontalAlignment(SwingConstants.RIGHT);
    label_C_ID.setForeground(c1);

    label_C_ID1 = new JLabel();
    label_C_ID1.setText(member.getMilktId());

    cp.setBackground(c9);
    cp.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH; //X,Y 축 다 채울지 정하기
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    //제목
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    cp.add(label_info, gbc);

    //아이디
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ID, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ID1, gbc);

    //이름
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_NAME, gbc); //수정

    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_NAME1, gbc);

    //전화번호
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_PN, gbc); //수정

    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_PN1, gbc);

    //주소
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ADDRESS, gbc);

    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ADDRESS1, gbc);

    //가입일
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_JOIN_DATE, gbc); //수정

    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_JOIN_DATE1, gbc);

    //자녀 아이디
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_C_ID, gbc);

    gbc.gridx = 1;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;

    cp.add(label_C_ID1, gbc);

    bChildUpdate = new JButton("자녀등록");
    bChildUpdate.setBackground(c16);
    bChildUpdate.setForeground(c17);
    bChildUpdate.setPreferredSize(new Dimension(150, 40));
    bChildUpdate.addActionListener(new MemberInfoEvent("bChildUpdate", member));

    bMemberUpdate = new JButton("회원정보 수정");
    bMemberUpdate.setBackground(c16);
    bMemberUpdate.setForeground(c17);
    bMemberUpdate.setPreferredSize(new Dimension(150, 40));
    bMemberUpdate.addActionListener(new MemberInfoEvent("bMemberUpdate", member));

    gbc.fill = GridBagConstraints.NONE;
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    cp.add(bChildUpdate, gbc);

    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    cp.add(bMemberUpdate, gbc);

    //폰트설정
    try {
      InputStream inputStream1 = new BufferedInputStream(new FileInputStream("./font/Jalnan2TTF.ttf"));
      Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
      bChildUpdate.setFont(f1.deriveFont(Font.PLAIN, 11));
      bMemberUpdate.setFont(f1.deriveFont(Font.PLAIN, 11));
      label_info.setFont(f1.deriveFont(Font.PLAIN, 20));
      label_ID.setFont(f1.deriveFont(Font.PLAIN, 11));
      label_ID1.setFont(f1.deriveFont(Font.PLAIN, 14));
      label_NAME.setFont(f1.deriveFont(Font.PLAIN, 11));
      label_NAME1.setFont(f1.deriveFont(Font.PLAIN, 14));
      label_PN.setFont(f1.deriveFont(Font.PLAIN, 11));
      label_PN1.setFont(f1.deriveFont(Font.PLAIN, 14));
      label_JOIN_DATE.setFont(f1.deriveFont(Font.PLAIN, 11));
      label_JOIN_DATE1.setFont(f1.deriveFont(Font.PLAIN, 14));
      label_C_ID.setFont(f1.deriveFont(Font.PLAIN, 11));
      label_C_ID1.setFont(f1.deriveFont(Font.PLAIN, 14));
      UIManager.put("OptionPane.background", c18);
      UIManager.put("Panel.background", c18);
      SwingUtilities.updateComponentTreeUI(new JOptionPane());

    } catch (FontFormatException | IOException e) {
      System.out.println("폰트 설정 안됨");
    }
    setUI();
  }

  void setUI() {

    setTitle("티스푼 마이페이지");
    setSize(400, 710);
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);
  }
}
