package event;

import domain.Member;
import repository.MemberRepository;
import ui.LoginForm;
import ui.RegisterUserUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterEvent implements ActionListener {

  private List<JTextField> textFieldList;
  private MemberRepository memberRepository;
  private RegisterUserUI registerUserUI;

  public RegisterEvent(List<JTextField> textFieldList, RegisterUserUI registerUserUI) {
    this.textFieldList = textFieldList;
    this.memberRepository = new MemberRepository();
    this.registerUserUI = registerUserUI;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String idValue = textFieldList.get(0).getText();
    String passwordValue = textFieldList.get(1).getText();
    String passwordCheckValue = textFieldList.get(2).getText();
    String nameValue = textFieldList.get(3).getText();
    String phoneNumValue = textFieldList.get(4).getText();
    String addressValue = textFieldList.get(5).getText();

    for (int i = 0; i < textFieldList.size(); i++) {
      String value = textFieldList.get(i).getText();
      if (!isEmpty(value)) {
        setValue(i);
        return;
      }
    }

    // 성공했을 때
    new LoginForm().setVisible(true);
    registerUserUI.setVisible(false);

    if (!passwordValue.equals(passwordCheckValue)) {
      JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.", "Message", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int result = memberRepository.insertMember(new Member(idValue, passwordValue, nameValue, phoneNumValue, addressValue, null));
    if (result == 0) {
      JOptionPane.showMessageDialog(null, "아이디를 변경해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
      return;
    }

  }

  public boolean isEmpty(String value) {
    if (value.isEmpty()) return false;
    else return true;
  }

  public void setValue(int idx) {
    String answer = "";
    if (idx == 0) {
      answer = JOptionPane.showInputDialog("아이디를 입력해주세요");
      textFieldList.get(0).setText(answer);
    }
    if (idx == 1) {
      answer = JOptionPane.showInputDialog("비밀번호를 입력해주세요");
      textFieldList.get(1).setText(answer);
    }
    if (idx == 2) {
      answer = JOptionPane.showInputDialog("비밀번호 확인을 입력해주세요");
      textFieldList.get(2).setText(answer);
    }
    if (idx == 3) {
      answer = JOptionPane.showInputDialog("이름을 입력해주세요");
      textFieldList.get(3).setText(answer);
    }
    if (idx == 4) {
      answer = JOptionPane.showInputDialog("핸드폰번호를 입력해주세요");
      textFieldList.get(4).setText(answer);
    }
    if (idx == 5) {
      answer = JOptionPane.showInputDialog("주소를 입력해주세요");
      textFieldList.get(5).setText(answer);
    }
  }
}
