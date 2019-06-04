package awtStudy;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class Second {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPanel myPanel = new MyPanel("My Second demo awt", 400, 400, 400, 300);

	}

}

class MyPanel extends Frame {
	private Panel p1,p2,p3,p4;
	MyPanel(String str, int x, int y, int width, int height){
		super(str);
		setLayout(null);
		p1 = new Panel(null);
		p2 = new Panel(null);
		p3 = new Panel(null);
		p4 = new Panel(null);
		p1.setBounds(0, 0, width/2, height/2);
		p2.setBounds(width/2, 0, width/2, height/2);
		p3.setBounds(0, height/2, width/2, height/2);
		p4.setBounds(width/2, height/2, width/2, height/2);
		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.red);
		p3.setBackground(Color.GRAY);
		p4.setBackground(Color.GREEN);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}
}
