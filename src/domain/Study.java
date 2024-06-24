package learning;

import java.sql.Date;

public class Study {
	private String name;
	private String grade;
	private String teacher;
	private String subject;
	private String cname;
	private int mcnt;
	private int ccnt;
	private int progress;
	private Date sdate;
	private Date edate;
	
	public Study(String name, String grade, String teacher,	String subject,
			String cname, int mcnt, int ccnt, int progress, Date sdate,	Date edate) {
		super();
		this.name = name;
		this.grade = grade;
		this.teacher = teacher;
		this.subject = subject;
		this.cname = cname;
		this.mcnt = mcnt;
		this.ccnt = ccnt;
		this.progress = progress;
		this.sdate = sdate;
		this.edate = edate;
	}	
}