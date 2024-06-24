package learning;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.color.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class StatusUI extends JFrame {
	Container cp, cp2;
	JTabbedPane pane;
	GridLayout g;
	JPanel p1, p2, pa, pb;
	JComboBox box1, box2;
	JButton btn;
	String[] sub = {"영역 전체", "국어", "수학", "영어", "사회", "과학"}; //db..?
	String[] desc = {"최근 수강순", "오래된 수강순"};
	JLabel label1, label2, label3, label4, label5, label6, label7;
	String a;
	
	Color color = new Color(192, 220, 233);
	
	static Status status = new Status();
	Status s;
	StatusUI(Status s){
		this.s = s;
		init();
	}
	
	void init() {
		cp = getContentPane();
		pane = new JTabbedPane();
		g = new GridLayout(5,2);
		p1 = new JPanel(g);
		pa = new JPanel();
		g = new GridLayout(4,2);
		pb = new JPanel(g);
		
		box1 = new JComboBox(sub);
		box2 = new JComboBox(desc);
		a = "현우지";
		label1 = new JLabel("    "+s.teacher+" 선생님"); //선생
		label2 = new JLabel(" "+s.grade + " "+s.cname); //과목+강좌명
		label3 = new JLabel("    총 "+s.ccnt+"강 중 "+s.mcnt+"강 달성("+s.progress+"%)");
		label4 = new JLabel(" ");
		label5 = new JLabel("    학습 시작일 "+s.sdate);
		label6 = new JLabel("마지막 학습일"+s.edate);
		label7 = new JLabel(" ");
		btn = new JButton("수강 연장");
		
		pa.add(box1);
		pa.add(box2);
		p1.add(pa);
		
		pb.add(label1);
		pb.add(label2);
		pb.add(label3);
		pb.add(label4);
		pb.add(label5);
		pb.add(label6);
		pb.add(label7);
		pb.add(btn);
		p1.add(pb);
		
		pane.addTab("       수강중       ", p1);
		p1.setBackground(color);
		pa.setBackground(color);
		pb.setBackground(color);
		pane.setBackground(color);
		
		p2 = new JPanel();
		pane.addTab("       수강 완료       ", p2);
		
		cp.add(pane);
		
		setUi();
	}
  
	void setUi() {
		setVisible(true);
		setSize(400, 710);
		setLocation(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		StatusUI s = new StatusUI(status);
	}
}