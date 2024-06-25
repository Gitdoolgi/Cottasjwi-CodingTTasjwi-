package ui;

import domain.Study;
import repository.StatusRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusUI extends JFrame {
  private Container cp, cp2;
  private JTabbedPane pane;
  private GridLayout g;
  private JPanel p1, p2, pa, pb;
  private JComboBox box1, box2;
  private JButton btn;
  private String[] sub = {"영역 전체", "국어", "수학", "영어", "과학", "사회"};
  private String[] desc = {"최근 수강순", "오래된 수강순"};
  private JLabel label1, label2, label3, label4, label5, label6, label7;
  private String milktId = "hooo";

  private Color color = new Color(192, 220, 233);
  private StatusRepository StatusRepository;

  public StatusUI() {
    this.StatusRepository = new StatusRepository();
    init();
  }

  void init() {
    cp = getContentPane();
    pane = new JTabbedPane();
    p1 = new JPanel(new GridLayout(6, 2)); //전체 레이아웃
    pa = new JPanel();
    box1 = new JComboBox(sub);
    box2 = new JComboBox(desc);

    int no = 0;
    List<Study> result = StatusRepository.select(milktId, sub[no]);

    pa.add(box1);
    pa.add(box2);
    p1.add(pa);

    for (Study s : result) {
      pb = new JPanel(new GridLayout(4, 2)); //수강 한 묶음
      label1 = new JLabel("    " + s.getTeacher() + " 선생님");
      label2 = new JLabel(" " + s.getGrade() + " " + s.getCname());
      label3 = new JLabel("    총 " + s.getCcnt() + "강 중 " + s.getMcnt() + "강 달성(" + s.getProgress() + "%)");
      label4 = new JLabel(" ");
      label5 = new JLabel("    학습 시작일 " + s.getSdate());
      label6 = new JLabel("마지막 학습일 " + s.getEdate());
      label7 = new JLabel(" ");
      btn = new JButton("수강 연장");

      pb.add(label1);
      pb.add(label2);
      pb.add(label3);
      pb.add(label4);
      pb.add(label5);
      pb.add(label6);
      pb.add(label7);
      pb.add(btn);
      p1.add(pb);

      pb.setBackground(color);
    }
    pane.addTab("       수강중       ", p1);

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

}