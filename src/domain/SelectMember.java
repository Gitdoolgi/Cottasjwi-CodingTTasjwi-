package domain;

import java.util.Date;

public class SelectMember {
  private int tspoon_no;
  private String id;
  private String name;
  private String phoneNum;
  private String address;
  private Date joinDate;
  private String milktId;

  public SelectMember(int tspoon_no, String id, String name, String phoneNum, String address, Date joinDate) {
    this.tspoon_no = tspoon_no;
    this.id = id;
    this.name = name;
    this.phoneNum = phoneNum;
    this.address = address;
    this.joinDate = joinDate;
  }

  public int getTspoon_no() {
    return tspoon_no;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public String getAddress() {
    return address;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setMilktId(String milktId) {
    this.milktId = milktId;
  }

  public String getMilktId() {
    return milktId;
  }
}
