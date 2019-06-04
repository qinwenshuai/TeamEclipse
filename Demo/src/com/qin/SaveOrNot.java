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
		1)������ͷ��ȡ��Ƶ��
		2)��ȡ��Ƶ֡����ΪͼƬ
		3)��ȡ��Ƶ֡����Ϊ
		*/
			VideoCapture capture = new VideoCapture(str);//��ȡ��Ƶ�ļ�
			if (!capture.isOpened()){
				
			}
			//��Ƶ������
			//VideoCaptureProperties ------> CV_CAP_PROP_XXX
			//��Ҫ��ȡ�����Ե�������Ϣ��ͨ��get()������ȡ
			double fps = capture.get(5);//֡��Ϣ

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
			http://www.fourcc.org/codecs.php ��ҳ�г���Ӧ�ı����ʽ
			���õ��У�
			CV_FOURCC('P','I','M','1') = MPEG-1 codec
			CV_FOURCC('M','J','P','G') = motion-jpeg codec
			CV_FOURCC('M', 'P', '4', '2') = MPEG-4.2 codec
			CV_FOURCC('D', 'I', 'V', '3') = MPEG-4.3 codec
			CV_FOURCC('D', 'I', 'V', 'X') = MPEG-4 codec
			CV_FOURCC('U', '2', '6', '3') = H263 codec
			CV_FOURCC('I', '2', '6', '3') = H263I codec
			CV_FOURCC('F', 'L', 'V', '1') = FLV1 codec

			*/
			//д���Size()�ߴ�Ҫ��ǰ���ȡ����֡�ߴ��С��ͬ�������޷�д����Ƶ,��СһֱΪ0KB����6KB
			//VideoWriter writer(string, CV_FOURCC('D', 'I', 'V', 'X'), 25.0, Size(frameWidth, frameHeight), true);//���캯����ʽ
			VideoWriter writer = new VideoWriter(string, VideoWriter.fourcc('M', 'J', 'P', 'G'), fps, new Size(frameWidth, frameHeight), true);
			//VideoWriter writer;
			//writer.open("av_2.avi", CV_FOURCC('D' 'I', 'V', 'X'), 30.0, Size(frameWidth, frameHeight0), true);//����open������ʽ

			Mat frame = new Mat();
			//Ptr<BackgroundSubtractor> pMOG2 = createBackgroundSubtractorMOG2();
			//pMOG2->apply(frame, bgmask, 0.5);
			int count = 0;
			while (capture.read(frame))
			{
				writer.write(frame);//д����Ƶ�ļ�
			}
			writer.release();//�ͷ���Դ
			capture.release();//�ͷ���Դ
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
						// ����һ��JFileChooser����
						JFileChooser chooser = new JFileChooser();
						/**
						 * ����һ�������ļ��ĶԻ��� ��Ҫָ��һ�� �������壬��ָ��Ϊ null ����ֵ�� int ���ͣ������Խ��շ���ֵ
						 */
						int value = chooser.showSaveDialog(null);
						/**
						 * ������� APPROVE_OPTION��Ҳ����ʹ�ö�����ã��� chooser.APPROVE_OPTION 
						 * ˵�����ļ����ɹ����أ��� �ɹ������ļ�
						 * ����ʵ�����ǽ�һ�������ڵ��ļ���װ����һ��������ڵ��ļ���Ȼ��֮����
						 * �����ļ���û����ʵ�ı������������Ǵ�����һ���ļ����󣬲����趨·��
						 * ��Ҫʹ�� createNewFile() �����ļ�
						 */
						if (value == JFileChooser.APPROVE_OPTION) {
							// ��ӡ�����ļ��ľ���·��
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
