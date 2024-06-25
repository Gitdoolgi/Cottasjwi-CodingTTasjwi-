import ui.LoginFormUI;

import javax.swing.*;

public class Runner {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(LoginFormUI::getLoginForm);
  }
}