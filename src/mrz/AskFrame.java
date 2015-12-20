package mrz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AskFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH=300;
	public static int HEIGHT=200;
	private MainFrame frame;
	private JTextField nField;
	private JLabel nLabel;
	private JTextField mField;
	private JLabel mLabel;
	private JButton ok;
	
	public AskFrame(MainFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame=frame;
		settings();
		initComponents();
		ok.addActionListener(new OkListener(this, frame));
	}
	
	public void settings(){
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setTitle("ChooseParametres");
		this.setLayout(new GridLayout(4,2));
		this.setLocation(100,100);
	}
	
	public void initComponents(){
		nLabel=new JLabel("enter n (<"+frame.getImage().getWidth()+")");
		nField=new JTextField(20);
		mLabel=new JLabel("enter m (<"+frame.getImage().getHeight()+")");
		mField=new JTextField(20);
		ok=new JButton("ok");
		
		this.add(nLabel);
		this.add(nField);
		this.add(mLabel);
		this.add(mField);
		this.add(ok);
	}
	public int getN(){
		return Integer.parseInt(nField.getText());
	}
	public int getM(){
		return Integer.parseInt(mField.getText());
	}
}
