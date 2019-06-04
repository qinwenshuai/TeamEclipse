package com.qin;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

public class VideoPlayer extends Thread{
	
	private String str1;
	private String str2;
	private int delay;
	
	
	
	public VideoPlayer(String str1, String str2, int delay) {
		super();
		this.str1 = str1;
		this.str2 = str2;
		this.delay = delay;
	}



	public  void run() {
	
		VideoCapture vc = new VideoCapture(str2);
		while(vc.isOpened()){
            boolean flag;
            Mat image = new Mat();
            flag = vc.read(image);
            if(!flag){
                System.out.println("--- not able to get image ---");
                //JFrame jjj = new JFrame("hhh");
                //JDialog j = new JDialog(jjj, "ÊÇ·ñÁí´æÎª£º", true);
                //jjj.setVisible(true);
                break;
            }
            HighGui.imshow(str1, image);
            HighGui.waitKey(delay);
        }
		SaveOrNot sn = new SaveOrNot(str2);
		Thread th = new Thread(sn);
		th.start();
	}

}
