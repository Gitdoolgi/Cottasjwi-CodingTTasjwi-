import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Action implements ActionListener {
	Myuser myuser;
	public Action(Myuser myuser){
		this.myuser = myuser;
	}
 
	Editor editor;
	public Action(Editor editor) {
		// TODO Auto-generated constructor stub
	}
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myuser.b_Child_Reg) {
            String userInput = JOptionPane.showInputDialog(null, "자녀의 ID를 입력해주세요", "자녀 추가", JOptionPane.PLAIN_MESSAGE);
            if (userInput != null) {
                JOptionPane.showMessageDialog(null, "자녀가 추가되었습니다");
            }else{
                JOptionPane.showMessageDialog(null, "자녀를 추가하지 않습니다");
            }
        }else if(e.getSource() == myuser.b_User_Edit) {
        	Editor editor = new Editor();
        	editor.init();
        } 
    }
}