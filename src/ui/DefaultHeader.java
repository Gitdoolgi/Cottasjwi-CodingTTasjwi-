package ui;

import javax.swing.*;
import java.awt.*;

public class DefaultHeader extends JPanel {

  public DefaultHeader() {
    setBounds(0, 0, 400, 65);
    setBorder(BorderFactory.createLineBorder(Color.black));
    setLayout(null);

    JLabel backImageBtn = new JLabel();
    Image originalBackImage = new ImageIcon("D:\\suwan\\java\\swing\\images\\뒤로가기.png.png").getImage();
    Image resizeBackImage = originalBackImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    backImageBtn.setIcon(new ImageIcon(resizeBackImage));
    backImageBtn.setBounds(25, 65 / 2 - 10, 25, 20);
    add(backImageBtn);

    JLabel centerLabel = new JLabel("TSPOON");
    centerLabel.setBounds(200 - 55 / 2, 65 / 2 - 10, 55, 20);
    add(centerLabel);

    JLabel userLabel = new JLabel("이수완");
    userLabel.setBounds(280, 65 / 2 - 10, 45, 20);
    add(userLabel);

    JLabel logoutLabel = new JLabel("로그아웃");
    logoutLabel.setBounds(327, 65 / 2 - 10, 55, 20);
    add(logoutLabel);
  }
}
