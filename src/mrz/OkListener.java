package mrz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OkListener implements ActionListener{

	private AskFrame askFrame;
	private MainFrame frame;
	private int n;
	private int m;
	private int N;
	private int w;
	private int h;
	private int L_n;
	private int L_m;
	private int L;
	private Area area;
	private int p;
	private double er;
	public OkListener(AskFrame askFrame, MainFrame frame){
		this.askFrame=askFrame;
		this.frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		n=askFrame.getN();
		m=askFrame.getM();
		if (n<frame.getImage().getWidth() || m<frame.getImage().getHeight()){
			askFrame.dispose();
		}
		N=n*m;
		w=frame.getImage().getWidth();
		h=frame.getImage().getHeight();
		if (w%n==0) L_n=w/n;
			else L_n=w/n+1;
		if (h%m==0) L_m=h/m;
			else L_m=h/m+1;
		L=L_n*L_m;
		
		String neuronNumber = JOptionPane.showInputDialog(null, "введите количество нейронов на втором слое(должно быть менльше"+m*n+")", JOptionPane.QUESTION_MESSAGE);
        setP(Integer.parseInt(neuronNumber));        
        if(p>m*n){
          askFrame.dispose();
        }else{
            String error1 = JOptionPane.showInputDialog(null,"максимальная ошибка (больше 0 и меньше"+(double)(p/10)+")", JOptionPane.QUESTION_MESSAGE);
            setE(Double.parseDouble(error1));
        }

        area=new Area(frame.getImage(), n*m*3, L);
        double E=99999;
        int ik=0;
        int jk=0;
        int f=0;
        area.setW(p);
        while (f<L){
        	area.initXW();
        	for (int i=ik; i<ik+m; i++){//прохождение одного прямоугольника
    			for (int j=jk; j<jk+n; j++){
					Color color = new Color(frame.getImage().getRGB(i,j)); 
		            int red = color.getRed();
		            int green = color.getGreen();
		            int blue = color.getBlue();
		            area.add(2*(double)red/255-1);   
		            area.add(2*(double)green/255 -1);
		            area.add(2*(double)blue/255 -1);
    			}
        	}
        	area.setX0();
        	int r=frame.getImage().getWidth();
        	if (jk+n<r) {jk+=n;}
    			else {ik+=m; jk=0;}
        	f++;
        }
        int iter=0;
        while(E>er /*&& (G-E)>0.09*p*/){
        	area.empty();
        	area.normalize();
        	area.study(p);
        	area.change();
        	E=area.error();
        	System.out.println(E+" "+iter);
        	iter++;
        }
        

        List<Double> A;
        List<Integer> colors=new ArrayList<Integer>();
        
        for (int i=0; i<L; i++){
        	A=area.getX1(i);
        	for (int j=0; j<N*3; j+=3){
    				 double red = A.get(j);
                     double green = A.get(j+1);
                     double blue = A.get(j+2);
                     
                     double r = (255*(red+1)/2);
                     if(r>255){r=255;} else if(r<0){r=0;} 
                     double g = (255*(green+1)/2);
                     if(g>255){g=255;} else if(g<0){g=0;}
                     double b = (255*(blue+1)/2);
                     if(b>255){b=255;} else if(b<0){b=0;}
                     
                     Color color = new Color((int)r, (int)g, (int)b);
                     int rgb = color.getRGB();
                     colors.add(rgb);
    			}
        	}
        ik=0;
        jk=0;
        f=0;
        int u = 0;
        while (f<L){
        	for (int i=ik; i<ik+m; i++){//прохождение одного прямоугольника
    			for (int j=jk; j<jk+n; j++){
    			frame.getOutImage().setRGB(i, j, colors.get(u));
    				u++;
    			}
        	}
        	if (jk+n<w) {jk+=n;}
    			else {ik+=m; jk=0;}
        	f++;
        }
        double z= 3*n*m*L/(double)((3*n*m+L)*p+2);
        String s="итераций: " + iter + "достигнутая ошибка: " + E  + "количество нейронов: " + p + "коэффициент сжатия"+ z;
        System.out.println(s);
        frame.update(frame.getGraphics());
        }
	
	public void setP(int p){
		this.p=p;
	}
	
	public void setE(double e){
		this.er=e;
	}
}
