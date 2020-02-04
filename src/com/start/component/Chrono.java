package com.start.component;

import com.start.main.Scene;

public class Chrono implements Runnable {

	private Scene scene ;
	private boolean run = true  ;
	private final int PAUSE = 100 ;
	
	public Chrono(Scene scene) {
		this.scene = scene ;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		
		while(run) {
			scene.repaint();
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRun() {
		return run ;
	}
	public void setRun(boolean run) {
		this.run = run ;
	}

}
