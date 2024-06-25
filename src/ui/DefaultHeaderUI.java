package ui;

import domain.SelectMember;
import event.HeaderEvent;

import javax.swing.*;
import java.awt.*;

public class DefaultHeaderUI extends JPanel {
  private String status;
  private Object currentObj;
  private Object previousUiobj;
  private SelectMember member;


  /**
   * @param status (login | logout);
   *               <p>
   *               logout일 때 로그아웃과 회원이름이 보이지 않는다.
   */
  public DefaultHeaderUI(String status, Object currentObj, Object previousUiobj, SelectMember member) {
    this.status = status;
    this.currentObj = currentObj;
    this.previousUiobj = previousUiobj;
    if (member != null) {
      this.member = member;
    }

    setBounds(0, 0, 400, 65);
    setBorder(BorderFactory.createLineBorder(Color.black));
    setLayout(null);

    JLabel backImageBtn = new JLabel("뒤로가기");
    Image originalBackImage = new ImageIcon("D:\\suwan\\java\\swing\\images\\뒤로가기.png.png").getImage();
    Image resizeBackImage = originalBackImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    backImageBtn.setIcon(new ImageIcon(resizeBackImage));
    backImageBtn.setBounds(25, 65 / 2 - 10, 25, 20);
    backImageBtn.addMouseListener(new HeaderEvent(currentObj, previousUiobj, member));

    if (status.equals("first")) {
      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(200 - 55 / 2, 65 / 2 - 10, 55, 20);
      add(centerLabel);
    }

    if (status.equals("register")) {
      add(backImageBtn);
      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(200 - 55 / 2, 65 / 2 - 10, 55, 20);
      add(centerLabel);
    }

    if (status.equals("login-main")) {
      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(200 - 55 / 2, 65 / 2 - 10, 55, 20);
      add(centerLabel);

      JLabel userLabel = new JLabel(member.getName());
      userLabel.setBounds(275, 65 / 2 - 10, 50, 20);
      add(userLabel);
      userLabel.addMouseListener(new HeaderEvent(currentObj, null, member));

      JLabel logoutLabel = new JLabel("로그아웃");
      logoutLabel.setBounds(327, 65 / 2 - 10, 55, 20);
      logoutLabel.addMouseListener(new HeaderEvent(currentObj, previousUiobj, member));
      add(logoutLabel);
    }

    if (status.equals("login")) {
      add(backImageBtn);

      JLabel centerLabel = new JLabel("TSPOON");
      centerLabel.setBounds(200 - 55 / 2, 65 / 2 - 10, 55, 20);
      add(centerLabel);

      JLabel userLabel = new JLabel(member.getName());
      userLabel.setBounds(275, 65 / 2 - 10, 50, 20);
      add(userLabel);
      userLabel.addMouseListener(new HeaderEvent(currentObj, null, member));

      JLabel logoutLabel = new JLabel("로그아웃");
      logoutLabel.setBounds(327, 65 / 2 - 10, 55, 20);
      logoutLabel.addMouseListener(new HeaderEvent(currentObj, previousUiobj, member));
      add(logoutLabel);
    }
  }
}
