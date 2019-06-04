package com.qin;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

public class Main {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        VideoCapture cap = new VideoCapture("E:/Megamind.avi");
        //VideoCapture cap = new VideoCapture("D:/迅雷下载/[电影天堂www.dy2018.com]碟中谍6：全面瓦解HD韩版中英双字.mp4");
        System.out.println(cap.isOpened());
        while(cap.isOpened()){
            boolean flag;
            Mat image = new Mat();
            flag = cap.read(image);
            if(!flag){
                System.out.println("--- not able to get image ---");
                break;
            }
            HighGui.imshow("video", image);
            HighGui.waitKey(50);
        }
        System.out.println("--- video capture closed ---");
    }
}
