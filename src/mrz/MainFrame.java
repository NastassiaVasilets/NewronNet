package mrz;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int SIZE=500;
	
	private JLabel sourceImageLabel;
	private JLabel resultImageLabel;
	
	private BufferedImage sourceImage;
	private BufferedImage outImage;
	
	private JPanel finishPanel;
	private JButton start;
	private JPanel buttonPanel;
	private JLabel r;
	private JTextArea textLabel;
	private String textString;
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		settings();
		initComponents();
		start.addActionListener(new StartListener(this));
		pack();
	}
	
	public void settings(){
		this.setSize(600, SIZE);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2, 2));
		this.setVisible(true);
		this.setTitle("NeuronNetwork");
	}
	
	public void initComponents(){
		this.setLayout(new FlowLayout());
		openImage("D:\\3.png");
		sourceImageLabel = new JLabel();
		sourceImageLabel.setIcon(new ImageIcon(sourceImage));
        this.add(sourceImageLabel);
        
        resultImageLabel = new JLabel();
        resultImageLabel.setIcon(new ImageIcon(getOutImage()));
        this.add(resultImageLabel);
        
        buttonPanel= new JPanel();
        start=new JButton("start");
        buttonPanel.add(start);
        this.add(buttonPanel);
	}
	
	public void openImage(String s){
		 try{
	            sourceImage = ImageIO.read(new File(s));
	            if(sourceImage.getHeight()<256 || sourceImage.getWidth()<256){
	                JOptionPane.showMessageDialog(null, "Too small size of image");
	            }
	            setOutImage(new BufferedImage(sourceImage.getWidth(),sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB));
	        }catch(IOException e){
	        }
	}
	public BufferedImage getImage(){
		return sourceImage;
	}
	public JPanel getFinishPanel(){
		return finishPanel;
	}

	public JLabel getResultImageLabel() {
		return resultImageLabel;
	}

	public void setResultImageLabel(JLabel resultImageLabel) {
		this.resultImageLabel = resultImageLabel;
	}

	public BufferedImage getOutImage() {
		return outImage;
	}

	public void setOutImage(BufferedImage outImage) {
		this.outImage = outImage;
	}	
}
