package event;

import domain.SelectMember;
import ui.BoardUI;
import ui.LoginFormUI;
import ui.MainFormUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BtnTest implements MouseListener {
  MainFormUI mainFormUI;
  LoginFormUI loginFormUI;
  SelectMember member;

  public BtnTest(MainFormUI mainFormUI, LoginFormUI loginFormUI, SelectMember member) {
    this.mainFormUI = mainFormUI;
    this.loginFormUI = loginFormUI;
    this.member = member;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    mainFormUI.setVisible(false);
    new BoardUI(loginFormUI, member);

  }

  @Override
  public void mousePressed(MouseEvent e) {

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
