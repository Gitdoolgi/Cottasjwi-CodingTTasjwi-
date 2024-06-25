package event;

import ui.MemberUpdateUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MemberUpdateEvent implements ActionListener {
  private MemberUpdateUI memberUpdateUI;
  private String jbName;

  public MemberUpdateEvent(MemberUpdateUI memberUpdateUI) {
    this.memberUpdateUI = memberUpdateUI;
  }

  public MemberUpdateEvent(String jbName) {
    this.jbName = jbName;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (jbName.equals("b_Child_Reg")) {
      String userInput = JOptionPane.showInputDialog(null, "자녀의 ID를 입력해주세요", "자녀 추가", JOptionPane.PLAIN_MESSAGE);
      if (userInput != null) {
        JOptionPane.showMessageDialog(null, "자녀가 추가되었습니다");
        // 1. 요청 유저 아이디로 디비에서 불러오기
        // 2. 해당 유저에 세터로 밀크티아이디 추가
        // member.setMiltkId(userInput);
      } else {
        JOptionPane.showMessageDialog(null, "자녀를 추가하지 않습니다");
      }
    } else if (jbName.equals("b_User_Edit")) {
      MemberUpdateUI memberUpdateUI = new MemberUpdateUI();
      memberUpdateUI.init();
    }
  }
}