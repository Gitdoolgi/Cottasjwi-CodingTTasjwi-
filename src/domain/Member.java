package domain;

import java.util.Date;

public class Member {
  private int tspoon_no;
  private String id;
  private String password;
  private String name;
  private String phone_num;
  private String address;
  private Date join_date;

  public Member(String id, String password, String name, String phone_num, String address) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.phone_num = phone_num;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getPhone_num() {
    return phone_num;
  }

  public String getAddress() {
    return address;
  }
}
