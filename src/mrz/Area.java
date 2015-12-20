package mrz;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class Area {
	private int N;
	private int L;
	private double[][] X;
	private List<Matrix> XList;
	private List<Matrix> YList; 
	private List<Matrix> X1List;
	private List<Matrix> XdList;
	private Matrix X0;
	private double[][] W0; 
	private Matrix W;
	private Matrix W1;
	private int q;
	private Matrix Y;
	private Matrix X1;
	private Matrix Xd;
	private double alpha=0.00003; 
	public Area(BufferedImage image, int N, int L){
		XList=new ArrayList<Matrix>();
		YList=new ArrayList<Matrix>();
		X1List= new ArrayList<Matrix>();
		XdList=new ArrayList<Matrix>();
		this.N=N;
		this.L=L;
	}
	
	public void initXW(){
		X=new double[1][N];
		q=0;
	}
	public void add(double a){
		X[0][q]=a;
		q++;
		return;
	}
	public double[][] getX0(){
		return X;
	}
	
	public void setW(int p){
		W0=new double[N][p];
		for (int i=0; i<N; i++){
			for (int j=0; j<p; j++){
			W0[i][j]=(Math.random()*(1-(-1))) - 1;
			}
		}
		W= new Matrix(W0);
		W1=W.transpose();
	}
	public void setX0(){
		X0=new Matrix(X);
		XList.add(X0);
	}
	
	public void study(int p){
		int k=0;
		while (k<L){
			Y=XList.get(k).times(W);
			X1=Y.times(W1);
			X1List.add(X1);
			Xd=X1List.get(k).minus(XList.get(k));
			XdList.add(Xd);
			k++;
		}
	}
	
	public void change(){
		int k=0;
		while(k<L){
			W= W.minus(XList.get(k).transpose().times(alpha).times(XdList.get(k)).times(W1.transpose()));
			W1=W1.minus(Y.transpose().times(alpha).times(XdList.get(k)));
			k++;
		}
	}
	
	public void empty(){
		X1List=new ArrayList<Matrix>();
		XdList=new ArrayList<Matrix>();
	}
	
	public void normalize(){
		double[][] War1=W.transpose().getArray();
		List<Double> M=new ArrayList<Double>();
		double s;
		for (int i=0; i<W.getColumnDimension(); i++){
			s=0;
			for (int j=0; j<W.getRowDimension(); j++){
				s+=War1[i][j]*War1[i][j];
			}
			M.add(Math.sqrt(s));
		}
		for (int i=0; i<W.getColumnDimension(); i++){
			for (int j=0; j<W.getRowDimension(); j++){
				War1[i][j]=War1[i][j]/M.get(i);
			}
		}
		W=new Matrix(War1);
		W=W.transpose();
		double[][] War2=W1.transpose().getArray();
		M=new ArrayList<Double>();
		for (int i=0; i<W1.getColumnDimension(); i++){
			s=0;
			for (int j=0; j<W1.getRowDimension(); j++){
				s+=War2[i][j]*War2[i][j];
			}
			M.add(Math.sqrt(s));
		}
		for (int i=0; i<W1.getColumnDimension(); i++){
			for (int j=0; j<W1.getRowDimension(); j++){
				War2[i][j]=War2[i][j]/M.get(i);
			}
		}
		W1=new Matrix(War2);
		W1=W1.transpose();
	}
	public double error(){//возвращает ошибку в пределах 1 прямоугольника
		double E=0;
		int k=0;
		while (k<L){
			W= W.minus(XList.get(k).transpose().times(alpha/100).times(XdList.get(k)).times(W1.transpose()));
			W1=W1.minus(Y.transpose().times(alpha/100).times(XdList.get(k)));
			Matrix mis=XdList.get(k).times(XdList.get(k).transpose());
			double mistake=mis.get(0, 0);
			E+=0.5*mistake;
			k++;
		}
		/*double [][] mis = XdList.get(k).getArray();
		double s=0;
		for (int i=0; i<N; i++){
			s+=mis[0][i]*mis[0][i];
		}*/
		return E;
	}
	
	public List<Double> getX1(int k){
		List<Double> B=new ArrayList<Double>();
		for (int i=0; i<N; i++){
			B.add(X1List.get(k).get(0,i));
		}
		return B;
	}
}
