package mrz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener{

	private AskFrame askFrame;
	private MainFrame frame;
	
	public StartListener(MainFrame frame) {
		this.frame=frame;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		askFrame=new AskFrame(frame);
	}

}
