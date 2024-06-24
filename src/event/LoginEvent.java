package event;

import ui.LoginForm;

import javax.swing.*;
import java.awt.event.*;

public class LoginEvent extends KeyAdapter {
  private LoginForm loginForm;

  public LoginEvent(LoginForm loginForm) {
    this.loginForm = loginForm;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      //loginButton.doClick();
    }
  }
}
