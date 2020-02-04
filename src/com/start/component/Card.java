package com.start.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import com.start.main.Scene;

@SuppressWarnings("serial")
public class Card extends JButton implements MouseListener{
	
	private String currentEmojie ;
	private String emojie ;
	private int pair ; 
	private Image img;
	private CardStatus status ;
	public Card(String emojie , int pair) {
		this.emojie = emojie ;
		this.currentEmojie = "/emojies/"+emojie+".jpg" ;
		this.pair = pair ;
		this.status = CardStatus.hidden;
		
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		getAppropriatedImage();
		try {
			img = ImageIO.read(getClass().getResource(this.currentEmojie));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		
		Graphics2D g2d = (Graphics2D)g;
	    getAppropriatedImage();
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    g2d.setColor(Color.black);
	  }
	
	private void getAppropriatedImage() {
		if(this.status == CardStatus.hidden) {
			this.currentEmojie = "/emojies/question.jpg" ;
		}
		else if (this.status == CardStatus.visible) {
			this.currentEmojie = "/emojies/"+emojie+".jpg" ;
		}
	}
	
	public String getEmojie() {
		return currentEmojie;
	}
	public int getPair() {
		return pair;
	}
	public void setStatus(CardStatus status) {
		this.status = status ;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Scene.handleCardClick(this);
	}
	
}
