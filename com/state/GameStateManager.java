package com.state;

import java.awt.Graphics2D;


public class GameStateManager {
	
	private static final byte NUM_STATES = 1;
	public static final byte PLAY = 0;
	
	private byte currentState;
	private State[] states;
	
	public GameStateManager() {
		states = new State[NUM_STATES];
		currentState = PLAY;
		states[currentState] = new PlayState(this);
	}
	
	private void unloadState() {
		states[currentState] = null;
	}
	
	private void loadState(byte state) {
		if (state == PLAY) {
			currentState = PLAY;
			states[currentState] = new PlayState(this);
		}
	}
	
	public void setState(byte state) {
		unloadState();
		loadState(state);
	}
	
	public void update() {
		try {
			states[currentState].update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g2d) {
		try {
			states[currentState].render(g2d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(int key) {
		try {
			states[currentState].keyPressed(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyReleased(int key) {
		try {
			states[currentState].keyReleased(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
