package event;

import dbutil.MariaConnection;
import domain.SelectMember;
import ui.MemberUpdateUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberUpdateEvent implements ActionListener {

  private Connection con = MariaConnection.getInstance().getConnection();
  private SelectMember member;
  private MemberUpdateUI memberUpdate;

  public MemberUpdateEvent(MemberUpdateUI memberUpdate, SelectMember member) {
    this.memberUpdate = memberUpdate;
    this.member = member;
  }

  String sql1 = "UPDATE TSPOON_MEMBER set PASSWORD = ? WHERE TSPOON_ID = ? "; //각각 입력값, 로긴중인 회원아이디 드가야함
  String sql2 = "UPDATE TSPOON_MEMBER set PHOEN_NUM = ? WHERE TSPOON_ID = ? "; //각각 입력값, 로긴중인 회원아이디 드가야함

  @Override
  public void actionPerformed(ActionEvent e) {
    char[] newPasswordChars = memberUpdate.getPasswordFromField();
    String newPassword = new String(newPasswordChars);
    String newPhoneNumber = memberUpdate.getPhoneNumFromField();

    if (newPassword != null) {
      if (newPhoneNumber != null) { //비밀번호, 전화번호 둘 다 수정
        if (newPhoneNumber.length() >= 10 && newPhoneNumber.length() <= 11) {
          try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
            pstmt1.setString(1, newPassword);
            pstmt1.setString(2, member.getId());
            int i = pstmt1.executeUpdate();
            if (i > 0) {
              System.out.println("비밀번호 수정 성공");
            } else {
              System.out.println("비밀번호 수정 실패");
            }
          } catch (SQLException se) {
          }
          try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
            pstmt2.setString(1, newPhoneNumber);
            pstmt2.setString(2, member.getId());
            int i = pstmt2.executeUpdate();
            if (i > 0) {
              System.out.println("전화번호 수정 성공");
            } else {
              System.out.println("전화번호 수정 실패");
            }
          } catch (SQLException se) {
          }
          JOptionPane.showMessageDialog(null, "정보(비밀번호, 전화번호)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);

        } else {
          JOptionPane.showMessageDialog(null, "정확한 전화번호를 입력해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
        }
      } else { //비밀번호만 수정
        try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
          pstmt1.setString(1, newPassword);
          pstmt1.setString(2, member.getId());
          int i = pstmt1.executeUpdate();
          if (i > 0) {
            System.out.println("비밀번호 수정 성공");
          } else {
            System.out.println("비밀번호 수정 실패");
          }
        } catch (SQLException se) {
        }
        JOptionPane.showMessageDialog(null, "정보(비밀번호)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
      }
    } else {
      if (newPhoneNumber != null) { //전화번호만 수정
        if (newPhoneNumber.length() >= 10 && newPhoneNumber.length() <= 11) {
          try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
            pstmt2.setString(1, newPhoneNumber);
            pstmt2.setString(2, member.getId());
            int i = pstmt2.executeUpdate();
            if (i > 0) {
              System.out.println("전화번호 수정 성공");
            } else {
              System.out.println("전화번호 수정 실패");
            }
          } catch (SQLException se) {
          }
        } else {
          JOptionPane.showMessageDialog(null, "정확한 전화번호를 입력해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "정보(전화번호)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
      } else {
      } //둘 다 수정 안 함
      JOptionPane.showMessageDialog(null, "정보를 수정하지 않습니다", "경고!", JOptionPane.WARNING_MESSAGE);
    }
  }
}

