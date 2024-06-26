package event;

import domain.SelectMember;
import ui.MemberInfoUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeaderEvent implements MouseListener {
  private Object currentObj;
  private Object previousUiobj;
  private SelectMember member;

  public HeaderEvent(Object currentObj, Object previousUiobj, SelectMember member) {
    this.currentObj = currentObj;
    this.previousUiobj = previousUiobj;
    this.member = member;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (previousUiobj != null) {
      ((JFrame) currentObj).setVisible(false);
      ((JFrame) previousUiobj).setVisible(true);
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    String type = ((JLabel) e.getSource()).getText();
    if (!type.equals("뒤로가기")) {
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
