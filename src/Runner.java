import ui.LoginForm;

import javax.swing.*;

public class Runner {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(LoginForm::getLoginForm);
  }
}