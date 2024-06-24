package repository;

import dbutil.MaraiConnection;
import domain.Study;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
  private Connection con = MaraiConnection.getInstance().getConnection();


  public List<Study> select(String milkid) {
    String sql = "SELECT mm.name '학생이름'"
            + "		,cl.grade '학년'"
            + "		,cl.teacher'선생님'"
            + "		,cl.subject '과목'"
            + "		,cl.class_name '수강명'"
            + "		,max(ml.progress) '수강수'"
            + "   ,cl.class_count '총강의수'"
            + "		,round(max(ml.progress)/cl.class_count,1) *100 '진도율'"
            + "		,mc.myclass_start_date '학습시작일'"
            + "		,mc.myclass_end_date '마지막 학습일'"
            + "  FROM milk_member mm"
            + "  JOIN myclass mc"
            + "    ON mm.milktid = mc.milktid"
            + "  JOIN class_list cl"
            + "    ON cl.class_no = mc.class_no"
            + "  JOIN myclass_log ml"
            + "    ON ml.myclass_no = mc.MYCLASS_NO"
            + " WHERE 1=1"
            + "   AND mc.milktid = ?"
            + "   AND mc.complete = 0"
            + "  GROUP BY ml.myclass_no";
    PreparedStatement pstmt;
    ResultSet rs;
    List<Study> al = new ArrayList();
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, milkid);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        String name = rs.getString(1);
        String grade = rs.getString(2);
        String teacher = rs.getString(3);
        String subject = rs.getString(4);
        String cname = rs.getString(5);
        int mcnt = rs.getInt(6);
        int ccnt = rs.getInt(7);
        int progress = rs.getInt(8);
        Date sdate = rs.getDate(9);
        Date edate = rs.getDate(10);

        al.add(new Study(name, grade, teacher, subject, cname, mcnt, ccnt, progress, sdate, edate));
      }
    } catch (SQLException se) {
      System.out.println("읽기 실패");
    }
    return al;
  }
}