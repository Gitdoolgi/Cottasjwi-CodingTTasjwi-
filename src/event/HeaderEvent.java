package event;

import domain.SelectMember;
import ui.LoginFormUI;
import ui.MemberInfoUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeaderEvent implements MouseListener {
  private Object currentObj;
  private Object previousObj;
  private SelectMember member;

  public HeaderEvent(Object currentObj, Object previousObj, SelectMember member) {
    this.currentObj = currentObj;
    this.previousObj = previousObj;
    this.member = member;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    String type = ((JLabel) e.getSource()).getText();
    JFrame currUI = null;
    JFrame prevUI = null;
    if (previousObj != null) {
      currUI = ((JFrame) currentObj);
      currUI.setVisible(false);
      prevUI = ((JFrame) previousObj);
      prevUI.setVisible(true);
      if (type.equals("로그아웃")) {
        currUI.dispose();
        prevUI.setVisible(true);
      }
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    String type = ((JLabel) e.getSource()).getText();
    if (!type.equals("뒤로가기") && !type.equals("로그아웃")) {
      new MemberInfoUI(member);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
