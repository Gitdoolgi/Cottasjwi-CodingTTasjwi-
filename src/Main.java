import domain.Member;
import ui.JoinUserUI;

import java.util.concurrent.ConcurrentHashMap;

public class Main {
  public static ConcurrentHashMap<String, Member> members = new ConcurrentHashMap<>();

  public static void main(String[] args) {
    new JoinUserUI();
  }
}