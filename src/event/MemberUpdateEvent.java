package event;

import ui.MemberUpdate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MemberUpdateEvent implements ActionListener {
  private MemberUpdate memberUpdate;
  private String jbName;

  public MemberUpdateEvent(MemberUpdate memberUpdate) {
    this.memberUpdate = memberUpdate;
  }

  public MemberUpdateEvent(String jbName) {
    this.jbName = jbName;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(jbName + " asd");
    if (jbName.equals("b_Child_Reg")) {
      String userInput = JOptionPane.showInputDialog(null, "자녀의 ID를 입력해주세요", "자녀 추가", JOptionPane.PLAIN_MESSAGE);
      if (userInput != null) {
        JOptionPane.showMessageDialog(null, "자녀가 추가되었습니다");
      } else {
        JOptionPane.showMessageDialog(null, "자녀를 추가하지 않습니다");
      }
    } else if (jbName.equals("b_User_Edit")) {
      MemberUpdate memberUpdate = new MemberUpdate();
      memberUpdate.init();
    }
  }
}