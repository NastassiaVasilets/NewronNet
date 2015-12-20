package mrz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SecondAskFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH=300;
	public static int HEIGHT=200;
	private double n;
	private JTextField pField;
	private JLabel pLabel;
	private JTextField eField;
	private JLabel eLabel;
	private JButton ok;
	private OkListener o;
	public SecondAskFrame(double n, OkListener o){
		this.n=n;
		this.o=o;
		settings();
		initComponents();
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				o.setP(getP());
				o.setE(getE());
			}});
		pack();
	}
	public void settings(){
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setTitle("ChooseParametres");
		this.setLayout(new GridLayout(4,2));
		this.setLocation(100,100);
	}
	
	public void initComponents(){
		pLabel=new JLabel("enter p (<"+n+")");
		pField=new JTextField(20);
		eLabel=new JLabel("enter error");
		eField=new JTextField(20);
		ok=new JButton("ok");
		
		this.add(pLabel);
		this.add(pField);
		this.add(eLabel);
		this.add(eField);
		this.add(ok);
	}
	public int getP(){
		return Integer.parseInt(eField.getText());
	}
	public int getE(){
		return Integer.parseInt(eField.getText());
	}
}

