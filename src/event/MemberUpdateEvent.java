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

  public MemberUpdateEvent(MemberUpdateUI memberUpdate) {
    this.memberUpdate = memberUpdate;
  }

  public MemberUpdateEvent(SelectMember member) {
    this.member = member;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    //ResultSet rs;
    char[] newPasswordChars = memberUpdate.getPasswordFromField();
    String newPassword = new String(newPasswordChars);
    String newPhoneNumber = memberUpdate.getPhoneNumFromField();
    String newAddress = memberUpdate.getAddressFromField();
    String sql1 = "UPDATE TSPOON_MEMBER set PASSWORD = ? WHERE TSPOON_ID = ? "; //비밀번호 수정
    String sql2 = "UPDATE TSPOON_MEMBER set PHOEN_NUM = ? WHERE TSPOON_ID = ? "; //전화번호 수정
    String sql3 = "UPDATE TSPOON_MEMBER set ADDRESS = ? WHERE TSPOON_ID = ? "; //주소 수정
    PreparedStatement pstmt1, pstmt2, pstmt3;

    if (newPassword != null) {
      if (newPhoneNumber != null) {
        if (newPhoneNumber.length() >= 10 && newPhoneNumber.length() <= 11) {
          if (newAddress != null) { //비밀번호, 전화번호, 주소
            try {
              pstmt1 = con.prepareStatement(sql1); //비밀번호 수정 코드
              pstmt1.setString(1, newPassword);
              pstmt1.setString(2, member.getId());
              int i = pstmt1.executeUpdate();
              if (i > 0) {
                System.out.println("비밀번호 수정 성공");
              } else {
                System.out.println("비밀번호 수정 실패");
              }
              pstmt2 = con.prepareStatement(sql2); //전화번호 수정 코드
              pstmt2.setString(1, newPhoneNumber);
              pstmt2.setString(2, member.getId());
              int j = pstmt2.executeUpdate();
              if (j > 0) {
                System.out.println("전화번호 수정 성공");
              } else {
                System.out.println("전화번호 수정 실패");
              }
              pstmt3 = con.prepareStatement(sql3); //주소 수정 코드
              pstmt3.setString(1, newAddress);
              pstmt3.setString(2, member.getId());
              int k = pstmt3.executeUpdate();
              if (k > 0) {
                System.out.println("주소 수정 성공");
              } else {
                System.out.println("주소 수정 실패");
              }
              JOptionPane.showMessageDialog(null, "정보(비밀번호, 전화번호, 주소)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException se) {
              System.out.println("정보를 수정할 수 없습니다");
              JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);

            }
          } else { //비밀번호, 전화번호
            try {
              pstmt1 = con.prepareStatement(sql1); //비밀번호 수정 코드
              pstmt1.setString(1, newPassword);
              pstmt1.setString(2, member.getId());
              int i = pstmt1.executeUpdate();
              if (i > 0) {
                System.out.println("비밀번호 수정 성공");
              } else {
                System.out.println("비밀번호 수정 실패");
              }
              pstmt2 = con.prepareStatement(sql2); //전화번호 수정 코드
              pstmt2.setString(1, newPhoneNumber);
              pstmt2.setString(2, member.getId());
              int j = pstmt2.executeUpdate();
              if (j > 0) {
                System.out.println("전화번호 수정 성공");
              } else {
                System.out.println("전화번호 수정 실패");
              }
              JOptionPane.showMessageDialog(null, "정보(비밀번호, 전화번호)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException se) {
              System.out.println("정보를 수정할 수 없습니다");
              JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);
            }
          }
        } else {
          JOptionPane.showMessageDialog(null, "정확한 전화번호를 입력해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
        }
      } else {
        if (newAddress != null) { //비밀번호, 주소
          try {
            pstmt1 = con.prepareStatement(sql1); //비밀번호 수정 코드
            pstmt1.setString(1, newPassword);
            pstmt1.setString(2, member.getId());
            int i = pstmt1.executeUpdate();
            if (i > 0) {
              System.out.println("비밀번호 수정 성공");
            } else {
              System.out.println("비밀번호 수정 실패");
            }
            pstmt3 = con.prepareStatement(sql3); //주소 수정 코드
            pstmt3.setString(1, newAddress);
            pstmt3.setString(2, member.getId());
            int k = pstmt3.executeUpdate();
            if (k > 0) {
              System.out.println("주소 수정 성공");
            } else {
              System.out.println("주소 수정 실패");
            }
            JOptionPane.showMessageDialog(null, "정보(비밀번호, 주소)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
          } catch (SQLException se) {
            System.out.println("정보를 수정할 수 없습니다");
            JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);
          }
        } else { //비밀번호
          try {
            pstmt1 = con.prepareStatement(sql1); //비밀번호 수정 코드
            pstmt1.setString(1, newPassword);
            pstmt1.setString(2, member.getId());
            int i = pstmt1.executeUpdate();
            if (i > 0) {
              System.out.println("비밀번호 수정 성공");
            } else {
              System.out.println("비밀번호 수정 실패");
            }
            JOptionPane.showMessageDialog(null, "정보(비밀번호)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
          } catch (SQLException se) {
            System.out.println("정보를 수정할 수 없습니다");
            JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    } else { //비밀번호 null
      if (newPhoneNumber != null) {
        if (newPhoneNumber.length() >= 10 && newPhoneNumber.length() <= 11) {
          if (newAddress != null) { //전화번호, 주소
            try {
              pstmt2 = con.prepareStatement(sql2); //전화번호 수정 코드
              pstmt2.setString(1, newPhoneNumber);
              pstmt2.setString(2, member.getId());
              int j = pstmt2.executeUpdate();
              if (j > 0) {
                System.out.println("전화번호 수정 성공");
              } else {
                System.out.println("전화번호 수정 실패");
              }
              pstmt3 = con.prepareStatement(sql3); //주소 수정 코드
              pstmt3.setString(1, newAddress);
              pstmt3.setString(2, member.getId());
              int k = pstmt3.executeUpdate();
              if (k > 0) {
                System.out.println("주소 수정 성공");
              } else {
                System.out.println("주소 수정 실패");
              }
              JOptionPane.showMessageDialog(null, "정보(전화번호, 주소)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException se) {
              System.out.println("정보를 수정할 수 없습니다");
              JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);
            }
          } else { //전화번호
            try {
              pstmt2 = con.prepareStatement(sql2); //전화번호 수정 코드
              pstmt2.setString(1, newPhoneNumber);
              pstmt2.setString(2, member.getId());
              int j = pstmt2.executeUpdate();
              if (j > 0) {
                System.out.println("전화번호 수정 성공");
              } else {
                System.out.println("전화번호 수정 실패");
              }
              JOptionPane.showMessageDialog(null, "정보(전화번호)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException se) {
              System.out.println("정보를 수정할 수 없습니다");
              JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);
            }
          }
        } else {
          JOptionPane.showMessageDialog(null, "정확한 전화번호를 입력해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
        }
      } else {
        if (newAddress != null) { //주소
          try {
            pstmt3 = con.prepareStatement(sql3); //주소 수정 코드
            pstmt3.setString(1, newAddress);
            pstmt3.setString(2, member.getId());
            int k = pstmt3.executeUpdate();
            if (k > 0) {
              System.out.println("주소 수정 성공");
            } else {
              System.out.println("주소 수정 실패");
            }
            JOptionPane.showMessageDialog(null, "정보(주소)가 수정되었습니다", "", JOptionPane.PLAIN_MESSAGE);
          } catch (SQLException se) {
            System.out.println("정보를 수정할 수 없습니다");
            JOptionPane.showMessageDialog(null, "정보를 수정할 수 없습니다", "경고!", JOptionPane.WARNING_MESSAGE);
          }
        } else { //아무것도 수정 안 함
          JOptionPane.showMessageDialog(null, "정보를 수정하지 않습니다", "경고!", JOptionPane.WARNING_MESSAGE);
        }
      }
    }
  }
}




