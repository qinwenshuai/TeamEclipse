package com.qin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

public class SaveOrNot extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private String str;
	
//	public static void main(String[] args) {
//		SaveOrNot sn = new SaveOrNot();
//		sn.run();
//	}
	
	

	/**
	 * Create the dialog.
	 */
	public void run() {
		lanch();

	}

	public SaveOrNot(String str) {
	this.str = str;
}
	
	public void save(String string) {
		/*
		1)从摄像头读取视频流
		2)读取视频帧保存为图片
		3)读取视频帧保存为
		*/
			VideoCapture capture = new VideoCapture(str);//读取视频文件
			if (!capture.isOpened()){
				
			}
			//视频流属性
			//VideoCaptureProperties ------> CV_CAP_PROP_XXX
			//想要获取视屏对的属性信息，通过get()方法获取
			double fps = capture.get(5);//帧信息

			double frameWidth = (double) capture.get(WIDTH);
			double frameHeight = (double) capture.get(HEIGHT);
			

			/* VideoWriter:
			 The constructors / functions initialize video writers.
			- On Linux FFMPEG is used to write videos;
			-On Windows FFMPEG or VFW is used;
			-On MacOSX QTKit is used.

			VideoWriter(const String& filename, int fourcc, double fps,
					Size frameSize, bool isColor = true);
			*/

			/*
			http://www.fourcc.org/codecs.php 网页列出相应的编码格式
			常用的有：
			CV_FOURCC('P','I','M','1') = MPEG-1 codec
			CV_FOURCC('M','J','P','G') = motion-jpeg codec
			CV_FOURCC('M', 'P', '4', '2') = MPEG-4.2 codec
			CV_FOURCC('D', 'I', 'V', '3') = MPEG-4.3 codec
			CV_FOURCC('D', 'I', 'V', 'X') = MPEG-4 codec
			CV_FOURCC('U', '2', '6', '3') = H263 codec
			CV_FOURCC('I', '2', '6', '3') = H263I codec
			CV_FOURCC('F', 'L', 'V', '1') = FLV1 codec

			*/
			//写入的Size()尺寸要和前面读取到的帧尺寸大小相同，否则无法写入视频,大小一直为0KB或者6KB
			//VideoWriter writer(string, CV_FOURCC('D', 'I', 'V', 'X'), 25.0, Size(frameWidth, frameHeight), true);//构造函数方式
			VideoWriter writer = new VideoWriter(string, VideoWriter.fourcc('M', 'J', 'P', 'G'), fps, new Size(frameWidth, frameHeight), true);
			//VideoWriter writer;
			//writer.open("av_2.avi", CV_FOURCC('D' 'I', 'V', 'X'), 30.0, Size(frameWidth, frameHeight0), true);//调用open函数方式

			Mat frame = new Mat();
			//Ptr<BackgroundSubtractor> pMOG2 = createBackgroundSubtractorMOG2();
			//pMOG2->apply(frame, bgmask, 0.5);
			int count = 0;
			while (capture.read(frame))
			{
				writer.write(frame);//写入视频文件
			}
			writer.release();//释放资源
			capture.release();//释放资源
	}

	public void lanch() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// 创建一个JFileChooser对象
						JFileChooser chooser = new JFileChooser();
						/**
						 * 弹出一个保存文件的对话框 需要指定一个 父级窗体，或指定为 null 返回值是 int 类型，创建以接收返回值
						 */
						int value = chooser.showSaveDialog(null);
						/**
						 * 如果返回 APPROVE_OPTION，也可以使用对象调用，即 chooser.APPROVE_OPTION 
						 * 说明有文件被成功返回，即 成功保存文件
						 * 这里实际上是将一个不存在的文件包装成了一个假设存在的文件，然后将之返回
						 * 但该文件并没有真实的被创建，仅仅是创建了一个文件对象，并可设定路径
						 * 需要使用 createNewFile() 创建文件
						 */
						if (value == JFileChooser.APPROVE_OPTION) {
							// 打印返回文件的绝对路径
							// System.out.println(chooser.getSelectedFile().getAbsolutePath());
							try {
								File newFile = chooser.getSelectedFile();
								if (!newFile.exists()) {
									newFile.createNewFile();
									System.out.println(newFile.getName());
									System.out.println(str);
									save(newFile.getAbsolutePath().replace('\\', '/'));
									
								}

							} catch (IOException e1) {
								e1.printStackTrace();
							}

						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		//getContentPane().setVisible(true);
		this.setVisible(true);

	}

}
