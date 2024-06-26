package event;

import dbutil.MariaConnection;
import domain.SelectMember;
import repository.MemberRepository;
import ui.MemberUpdateUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class MemberUpdateEvent implements ActionListener {
  private Connection con = MariaConnection.getInstance().getConnection();
  private MemberRepository memberRepository;
  private SelectMember member;
  private MemberUpdateUI memberUpdate;
  private List<JTextField> textFieldList;

  public MemberUpdateEvent(MemberUpdateUI memberUpdate, SelectMember member, List<JTextField> textFieldList) {
    this.memberUpdate = memberUpdate;
    this.member = member;
    this.textFieldList = textFieldList;
    memberRepository = new MemberRepository();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String getPassword = textFieldList.get(0).getText();
    String getPhoneNum = textFieldList.get(1).getText();
    String getAddress = textFieldList.get(2).getText();

    int result = memberRepository.updateMember(member.getId(), getPassword, getPhoneNum, getAddress);
    if (result == 1) {
      System.out.println("수정 완료");
    }
  }
}




