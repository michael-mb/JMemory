package com.start.main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Window extends JFrame{

	public static Scene myScene ; 
	public JLabel label ;
	public JMenuBar menuBar ;
	private JMenu mFile ;
	private JMenuItem mReplay;
	private JMenuItem mQuit ;
	
	private final int windowsWidth = 700 ;
	private final int windowsHeight = 600 ;
	public Window() {

		initParts();
		initMenu();
		initListener();
		initWindow();
		
		this.setVisible(true);
	}
	
	
	private void initParts() {
		label = new JLabel("Number of tries : 0") ;
		label.setPreferredSize(new Dimension(270,50));
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setHorizontalAlignment(JLabel.HORIZONTAL);
		
		menuBar = new JMenuBar();
		mFile = new JMenu("Game");
		mReplay = new JMenuItem("Play/Replay");
		mQuit = new JMenuItem("Quit");
		
		myScene = new Scene(label);
	}
	
	private void initMenu() {
		mQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		
		mFile.add(mReplay);
		mFile.add(mQuit);
		menuBar.add(mFile);
		
		this.setJMenuBar(menuBar);
	}
	
	private void initListener() {
		
		mReplay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				replay();
				
			}
			
		});
		mQuit.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent event){
		          System.exit(0);
		        }
		      });
	}
	private void initWindow() {
	
		this.setTitle("Java Memory");
		this.setSize(windowsWidth,windowsHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setLocationRelativeTo(null);
		this.add(label , BorderLayout.NORTH);
		this.add(myScene , BorderLayout.CENTER);
		
	} 
	
	private void replay() {
		this.getContentPane().remove(myScene);
		this.getContentPane().remove(label);
		myScene.resetAll();
		
		myScene = new Scene(label);
		label.setText("Number of tries : 0");
		
		this.add(label , BorderLayout.NORTH);
		this.add(myScene , BorderLayout.CENTER);
		this.getContentPane().invalidate();
		this.getContentPane().validate();
		this.repaint();
	}
}
