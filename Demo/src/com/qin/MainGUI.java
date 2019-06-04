package com.qin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.Core;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setFont(new Font("Dialog", Font.BOLD, 18));
		setTitle("\u89C6\u9891\u6587\u4EF6\u8BFB\u53D6\u4E0E\u4FDD\u5B58");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 92);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 10));
		
		JButton btnNewButton = new JButton("\u6253\u5F00\u6587\u4EF6");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				// Note: source for ExampleFileFilter can be found in FileChooserDemo,
				    // under the demo/jfc directory in the JDK.
				    MyFileFilter filter = new MyFileFilter();
				    filter.addExtension("avi");
				    filter.addExtension("mp4");
				    filter.addExtension("mkv");
				    filter.addExtension("flv");
				    filter.setDescription("视频文件");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(null);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				    		   chooser.getSelectedFile().getAbsolutePath().replace('\\', '/'));
				       VideoPlayer vp = new VideoPlayer("正在播放:"+chooser.getSelectedFile().getName(),
				    		   chooser.getSelectedFile().getAbsolutePath().replace('\\', '/'), 50);
				      vp.start();
				    }
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53E6\u5B58\u4E3A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
		contentPane.add(btnNewButton_1);
	}

}
