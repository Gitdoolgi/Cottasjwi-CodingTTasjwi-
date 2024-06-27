package ui;

import domain.SelectMember;
import event.MemberInfoEvent;
import repository.StatusRepository;
import style.ColorSet;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class MemberInfoUI extends JFrame {
  private DefaultHeaderUI defaultHeader;
  private StatusRepository statusRepository;
  private SelectMember member;

  public MemberInfoUI(SelectMember member) {
    this.member = member;
    statusRepository = new StatusRepository();
    init();
  }

  Container cp = getContentPane();
  JPanel p1;
  JButton bChildUpdate, bMemberUpdate;
  JLabel label_info, label_ID, label_ID1, label_NAME, label_NAME1, label_PN, label_PN1, label_ADDRESS, label_ADDRESS1, label_JOIN_DATE, label_JOIN_DATE1, label_C_ID, label_C_ID1;

  void init() {
    label_info = new JLabel();
    label_info.setText("☆나의 정보★");
    label_info.setHorizontalAlignment(SwingConstants.CENTER);
    label_info.setVerticalAlignment(SwingConstants.BOTTOM);
    //label_info.setForeground(ColorSet.BACKGROUND);// 색상
    label_ID = new JLabel();
    label_ID.setText("♣ 아이디  :  ");
    //label_ID.setForeground(ColorSet.BACKGROUND);

    label_ID1 = new JLabel();
    label_ID1.setText(member.getId());

    label_NAME = new JLabel();
    label_NAME.setText("♣ 이름  :  ");
    //label_NAME.setForeground(ColorSet.BACKGROUND);

    label_NAME1 = new JLabel();
    label_NAME1.setText(member.getName());

    label_PN = new JLabel();
    label_PN.setText("♣ 전화번호  :  ");
    //label_PN.setForeground(ColorSet.BACKGROUND);

    label_PN1 = new JLabel();
    label_PN1.setText(member.getPhoneNum());

    label_ADDRESS = new JLabel();
    label_ADDRESS.setText("♣ 주소 :  ");
    //label_ADDRESS.setForeground(ColorSet.BACKGROUND);

    label_ADDRESS1 = new JLabel();
    label_ADDRESS1.setText(member.getAddress());


    label_JOIN_DATE = new JLabel();
    label_JOIN_DATE.setText("♣ 가입일  :  ");
    //label_JOIN_DATE.setForeground(ColorSet.BACKGROUND);

    label_JOIN_DATE1 = new JLabel();
    Date memberJoinDate = member.getJoinDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = dateFormat.format(memberJoinDate);
    label_JOIN_DATE1.setText(formattedDate);


    label_C_ID = new JLabel();
    label_C_ID.setText("♣ 자녀 이름  :  ");
    //label_C_ID.setForeground(ColorSet.BACKGROUND);

    label_C_ID1 = new JLabel();
    String childName = statusRepository.findChildName(member.getTspoonNo());
    label_C_ID1.setText(childName);

    cp.setBackground(ColorSet.BACKGROUND);
    cp.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH; //X,Y 축 다 채울지 정하기
    gbc.weightx = 1;
    gbc.weighty = 1;

    //제목
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 2;
    cp.add(label_info, gbc);


    gbc.insets = new Insets(10, 60, 10, 10);
    //아이디
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ID, gbc);

    //이름
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_NAME, gbc); //수정

    //전화번호
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_PN, gbc); //수정

    //주소
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ADDRESS, gbc);

    //가입일
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_JOIN_DATE, gbc); //수정

    //자녀 이름
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_C_ID, gbc);


    //여기부터 (2열)
    gbc.insets = new Insets(10, 0, 10, 10);
    //아이디
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ID1, gbc);

    //이름
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_NAME1, gbc);

    //전화번호
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_PN1, gbc);

    //주소
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_ADDRESS1, gbc);

    //가입일
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_JOIN_DATE1, gbc);

    //자녀 이름
    gbc.gridx = 1;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    cp.add(label_C_ID1, gbc);


    bChildUpdate = new JButton("자녀등록");
    //bChildUpdate.setBackground(ColorSet.BACKGROUND);
    //bChildUpdate.setForeground(ColorSet.BACKGROUND);
    bChildUpdate.setPreferredSize(new Dimension(150, 40));
    bChildUpdate.addActionListener(new MemberInfoEvent("bChildUpdate", member));

    bMemberUpdate = new JButton("회원정보 수정");
    //bMemberUpdate.setBackground(ColorSet.BACKGROUND);
    //bMemberUpdate.setForeground(ColorSet.BACKGROUND);
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

    try {
      InputStream inputStream1 = new BufferedInputStream(new FileInputStream("Jalnan2TTF.ttf"));
      Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
      bChildUpdate.setFont(f1.deriveFont(Font.PLAIN, 13));
      bMemberUpdate.setFont(f1.deriveFont(Font.PLAIN, 13));
      label_info.setFont(f1.deriveFont(Font.PLAIN, 20));
      label_ID.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_ID1.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_NAME.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_NAME1.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_PN.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_PN1.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_ADDRESS.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_ADDRESS1.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_JOIN_DATE.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_JOIN_DATE1.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_C_ID.setFont(f1.deriveFont(Font.PLAIN, 15));
      label_C_ID1.setFont(f1.deriveFont(Font.PLAIN, 15));
      UIManager.put("OptionPane.background", ColorSet.BACKGROUND);
      UIManager.put("Panel.background", ColorSet.BACKGROUND);
      //UIManager.put("OptionPane.messageForeground", ColorSet.BACKGROUND);
      //UIManager.put("OptionPane.messageFont", f1);
      //UIManager.put("OptionPane.buttonFont", f1);

      SwingUtilities.updateComponentTreeUI(new JOptionPane());

    } catch (FontFormatException | IOException e) {
      System.out.println("폰트 설정 안됨");
    }

    setUI();
  }

  void setUI() {

    setTitle("티스푼 마이페이지");
    setSize(400, 710);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (screenSize.width - getSize().width) / 2;
    int centerY = (screenSize.height - getSize().height) / 2;
    setLocation(centerX + 390, centerY - 23);
    setVisible(true);
    setResizable(false);
  }
}
