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

    JLabel centerLabel = new JLabel();
    ImageIcon logo = imageSetSize("icon/logo1.png", 50, 50);
    centerLabel.setIcon(logo);
    centerLabel.setBounds(173, 0, 55, 65);

    if (status.equals("first")) {
      add(centerLabel);
    }

    if (status.equals("register")) {
      add(backImageBtn);
      add(centerLabel);
    }

    if (status.equals("login-main")) {
      add(centerLabel);

      JLabel userLabel = new JLabel(member.getName());
      userLabel.setBounds(275, 23, 50, 20);

      ImageIcon userIcon = imageSetSize("icon/user.png", 40, 40);
      userLabel.setIcon(userIcon);
      userLabel.setBounds(280, 10, 40, 40);


      add(userLabel);
      userLabel.addMouseListener(new HeaderEvent(currentObj, null, member));

      JLabel logoutLabel = new JLabel("asdasd");
      logoutLabel.setBounds(327, 23, 55, 20);
      logoutLabel.addMouseListener(new HeaderEvent(currentObj, previousObj, member));

      ImageIcon logoutIcon = imageSetSize("icon/logoutS.png", 40, 40);
      logoutLabel.setIcon(logoutIcon);
      logoutLabel.setBounds(340, 10, 40, 40);


      add(logoutLabel);
    }

    if (status.equals("login")) {
      add(backImageBtn);

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

  private ImageIcon imageSetSize(String imagePath, int w, int h) {
    Image originImage = new ImageIcon("./images/" + imagePath).getImage();
    Image resizeBackImage = originImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    return new ImageIcon(resizeBackImage);
  }
}
