package event;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeaderEvent implements MouseListener {
  private Object currentObj;
  private Object previousUiobj;

  public HeaderEvent(Object currentObj, Object previousUiobj) {
    this.currentObj = currentObj;
    this.previousUiobj = previousUiobj;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    ((JFrame) currentObj).setVisible(false);
    ((JFrame) previousUiobj).setVisible(true);
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
