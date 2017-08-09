package com.main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.state.GameStateManager;


public class Game extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	public static final short WIDTH = 480;
	public static final short HEIGHT = 480;
	public static final byte SCALE = 1;
	
	private BufferedImage dbImage;
	private Graphics g;
	private Graphics2D g2d;
	private Color backgroundColor;
	
	private Thread gameLoop;
	private boolean isRunning;
	private static byte fps = 24;
	private int targetTime = 1000 / fps;
	
	private GameStateManager gsm;
	
	public Game() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		requestFocus();
		setFocusable(true);
	}
	
	private void init() {
		dbImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) dbImage.getGraphics();
		backgroundColor = new Color(34, 34, 34);
		isRunning = true;
		gsm = new GameStateManager();
	}
	
	private void update() {
		gsm.update();
	}
	
	private void render() {
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		gsm.render(g2d);
	}
	
	private void renderToScreen() {
		g = getGraphics();
		g.drawImage(dbImage, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		
		if (gameLoop ==  null) {
			gameLoop = new Thread(this);
			addKeyListener(this);
			gameLoop.start();
		}
	}
	
	@Override
	public void run() {
		long startTime, elapseTime, waitTime;
		init();
		
		while (isRunning) {
			startTime = System.nanoTime();
			update();
			render();
			renderToScreen();
			elapseTime = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - elapseTime;
			
			if (waitTime < 0)
				waitTime = 5;
			
			try {
				Thread.sleep(waitTime);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ev) {
		gsm.keyPressed(ev.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent ev) {
		gsm.keyReleased(ev.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}