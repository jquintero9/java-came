package com.state;

import com.state.GameStateManager;

import java.awt.Graphics2D;


public abstract class State {
	
	protected GameStateManager gsm;
	
	public State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g2d);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
}