package ui;

import domain.SelectMember;
import event.HeaderEvent;
import style.ColorSet;

import javax.swing.*;
import java.awt.*;

public class DefaultHeaderUI extends JPanel {
  private String status;
  private Object currentObj;
  private Object previousObj;
  private SelectMember member;


  public DefaultHeaderUI(String status, Object currentObj, Object previousObj, SelectMember member) {
    this.status = status;
    this.currentObj = currentObj;
    this.previousObj = previousObj;
    if (member != null) {
      this.member = member;
    }

    setBounds(0, 0, 400, 65);
    setLayout(null);
    setBackground(ColorSet.HEADER);

    JLabel backImageBtn = new JLabel("뒤로가기");
    Image originalBackImage = new ImageIcon("./images/뒤로가기.png").getImage();
    Image resizeBackImage = originalBackImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    backImageBtn.setIcon(new ImageIcon(resizeBackImage));
    backImageBtn.setBounds(25, 23, 25, 20);
    backImageBtn.addMouseListener(new HeaderEvent(currentObj, previousObj, member));

    if (status.equals("first")) {
      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(173, 23, 55, 20);
      add(centerLabel);
    }

    if (status.equals("register")) {
      add(backImageBtn);
      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(173, 23, 55, 20);
      add(centerLabel);
    }

    if (status.equals("login-main")) {
      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(173, 23, 55, 20);
      add(centerLabel);

      JLabel userLabel = new JLabel(member.getName());
      userLabel.setBounds(275, 23, 50, 20);
      add(userLabel);
      userLabel.addMouseListener(new HeaderEvent(currentObj, null, member));

      JLabel logoutLabel = new JLabel("로그아웃");
      logoutLabel.setBounds(327, 23, 55, 20);
      logoutLabel.addMouseListener(new HeaderEvent(currentObj, previousObj, member));
      add(logoutLabel);
    }

    if (status.equals("login")) {
      add(backImageBtn);

      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(173, 23, 55, 20);
      add(centerLabel);

      JLabel userLabel = new JLabel(member.getName());
      userLabel.setBounds(275, 23, 50, 20);
      add(userLabel);
      userLabel.addMouseListener(new HeaderEvent(currentObj, null, member));

      JLabel logoutLabel = new JLabel("로그아웃");
      logoutLabel.setBounds(327, 23, 55, 20);
      logoutLabel.addMouseListener(new HeaderEvent(currentObj, previousObj, member));
      add(logoutLabel);
    }
  }
}
