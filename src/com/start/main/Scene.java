package com.start.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.start.component.Card;
import com.start.component.CardStatus;
import com.start.component.Chrono;


@SuppressWarnings("serial")
public class Scene extends JPanel{

	private List<Card> cards;
	public static List<Card> pair ;
	private static int tries ;
	private JLabel label ;
	private Chrono chrono ;
	
	public Scene(JLabel label ) {
		this.label = label ;
		Scene.tries = 0 ;
		initScene();
	}
	
	public void paintComponent(Graphics g){
		
		 super.paintComponent(g);
		 if(pair.size() == 2) {
			 
			 if(!(pair.get(0).getPair() == pair.get(1).getPair())) {
				 setTimeout(() -> timeOut(), 200);
			 }
			 else {
				 pair.clear();
			 }
			 
			 label.setText("Number of tries : "+tries);
		 }
	}
	
	public void initScene() {
		
		this.cards = initCards();
		Scene.pair = new ArrayList<>();
		
		GridLayout gl = new GridLayout(4, 4);
		gl.setHgap(25);
		gl.setVgap(25); 
		this.setLayout(gl);
		this.setBorder(BorderFactory.createMatteBorder(
                15, 15, 15, 15, Color.black));
		this.setBackground(Color.black);
		for(Card card : cards) {
			this.add(card);
		}
		
		chrono = new Chrono(this);
	}
	public List<Card> initCards() {
		int index = 1 ;
		List<String> emojies = new ArrayList<>(
				Arrays.asList
					(
					"love", 
	                "zen",
	                "cute",
	                "interessed" ,
	                "nerved",
	                "come",
	                "cool",
	                "clever"
	                )
				); 
		List<Card> cards = new ArrayList<>();
		for(String str : emojies) {
			cards.add(new Card(str,index));
			cards.add(new Card(str,index));
			index ++ ;
		}
		
		Collections.shuffle(cards);
		
		return cards ;
	}
	
	public void timeOut() {
		 for(Card c : pair) {
			 c.setStatus(CardStatus.hidden);
		 }
		 pair.clear();
		}
	
	public static void setTimeout(Runnable runnable, int delay){
	    new Thread(() -> {
	        try {
	            Thread.sleep(delay);
	            runnable.run();
	        }
	        catch (Exception e){
	            System.err.println(e);
	        }
	    }).start();
	}
	
	public static void handleCardClick(Card card) {
		if(Scene.pair.size() < 2) {
			Scene.pair.add(card);
			card.setStatus(CardStatus.visible);
		}
		if(Scene.pair.size() == 2) {
			Scene.tries ++ ;
		}
	}
	
	public void resetAll() {
		
		for(Card c : cards) {
			c.setStatus(CardStatus.hidden);
			for (MouseListener listener : c.getMouseListeners())
			    c.removeMouseListener(listener);			
			
		}
		
		Scene.tries = 0 ;
		Scene.pair.clear();
		chrono.setRun(false);
	}

}
