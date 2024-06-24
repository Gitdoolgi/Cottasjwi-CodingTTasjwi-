package learning;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Status {
	String db = "org.mariadb.jdbc.Driver";
	String url = "jdbc:mariadb://10.41.2.94:3306/tspoon";
	String dbId = "ttasjwi";
	String dbPwd = "ttasjwi";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
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
	String milkid = "hooo";

	Status(){
		try {
			Class.forName(db);
			con = DriverManager.getConnection(url, dbId, dbPwd);
			pstmt = con.prepareStatement(sql);
		} catch (ClassNotFoundException cn) {
		} catch (SQLException se) {
		}
		
		select(milkid);
	}
	
	List<Study> al;
	String name;
	String grade;
	String teacher;
	String subject;
	String cname;
	int mcnt;
	int ccnt;
	int progress;
	Date sdate;
	Date edate;
	
	List<Study> select(String milkid) {
		al = new ArrayList();
		try {
			pstmt.setString(1, milkid);
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				name = rs.getString(1);
				grade = rs.getString(2);
				teacher = rs.getString(3);
				subject = rs.getString(4);
				cname = rs.getString(5);
				mcnt = rs.getInt(6);
				ccnt = rs.getInt(7);
				progress = rs.getInt(8);
				sdate = rs.getDate(9);
				edate = rs.getDate(10);
				
				al.add(new Study(name, grade, teacher, subject, cname, mcnt, ccnt, progress, sdate, edate));
			}
		} catch (SQLException se) {
			System.out.println("읽기 실패");
		}
		return al;
	}
}