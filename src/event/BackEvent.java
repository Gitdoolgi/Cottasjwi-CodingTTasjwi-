package event;

import domain.SelectMember;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BackEvent extends MouseAdapter {
  private Object currentObj;
  private Object previousObj;
  private SelectMember member;

  public BackEvent(Object currentObj, Object previousObj, SelectMember member) {
    this.currentObj = currentObj;
    this.previousObj = previousObj;
    this.member = member;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    ((JFrame) currentObj).setVisible(false);
    ((JFrame) previousObj).setVisible(true);
  }
}
