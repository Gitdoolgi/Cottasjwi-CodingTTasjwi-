package event;

import dbutil.MaraiConnection;
import domain.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JoinEvent implements ActionListener {
  private Connection con = MaraiConnection.getInstance().getConnection();

  private List<JTextField> textFieldList;

  public JoinEvent(List<JTextField> textFieldList) {
    this.textFieldList = textFieldList;
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

    if (!passwordValue.equals(passwordCheckValue)) {
      JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.", "Message", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int result = insertMember(new Member(idValue, passwordValue, nameValue, phoneNumValue, addressValue));
    if (result == 0) {
      JOptionPane.showMessageDialog(null, "아이디를 변경해주세요.", "Message", JOptionPane.ERROR_MESSAGE);

      int lastNo = getLastAutoincrement();
      resetAutoincrement(lastNo);

      System.out.println(getLastAutoincrement() + " 실패");
      return;
    }
    System.out.println("회원가입 성공");
    System.out.println(getLastAutoincrement() + " 성공");
  }

  private void resetAutoincrement(int lastNo) {
    PreparedStatement pstmt = null;
    String sql = "ALTER TABLE tspoon_member AUTO_INCREMENT = ?";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, lastNo);
      pstmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private int getLastAutoincrement() {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from tspoon_member order by tspoon_no desc limit 2";
    int lastNo = 0;
    try {
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      rs.last();
      lastNo = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lastNo;
  }

  private int insertMember(Member member) {
    String sql = "insert into tspoon_member(id, password, name, phone_num, address, join_date) values (?,?,?,?,?,now())";
    int result = 0;
    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, member.getId());
      pstmt.setString(2, member.getPassword());
      pstmt.setString(3, member.getName());
      pstmt.setString(4, member.getPhone_num());
      pstmt.setString(5, member.getAddress());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("아이디 중복");
    }
    return result;
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
