package domain;

import java.util.Date;

public class ReceiveMember {
  private int tspoon_no;
  private String id;
  private String name;
  private String phoneNum;
  private String address;
  private Date joinDate;

  public ReceiveMember(int tspoon_no, String id, String name, String phoneNum, String address, Date joinDate) {
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
}
