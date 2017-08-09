package com.sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.main.Game;


public class Player extends Sprite {

	public static final byte DEFAULT_SIZE = 8;

	private Color color;

	private short dx;
	private short dy;
	private short speed;

	private boolean up;
	private boolean right;
	private boolean down;
	private boolean left;
	
	private long startTimeShot;
	private short delay;
	private boolean isShooting;
	
	private ArrayList<Bullet> bullets;

	public Player(short x, short y, short width, short height, ArrayList<Bullet> bullets) {
		super(x, y, width, height);
		this.bullets = bullets;
		init();
	}
	
	private void init() {
		color = new Color(255, 255, 59);
		
		speed = 6;
		dx = 0;
		dy = 0;
		
		up = false;
		right = false;
		down = false;
		left = false;
		
		startTimeShot = System.nanoTime();
		delay = 200;
		isShooting = false;
	}

	private void checkBounds() {
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x + width > Game.WIDTH)
			x = (short) ((short) Game.WIDTH - width);
		if (y + height > Game.HEIGHT)
			y = (short) ((short) Game.HEIGHT - height);
	
		dx = dy = 0;
	}

	private void setNextPosition() {
		if (left)
			dx = (short) -speed;
		if (right)
			dx = speed;
		if (down)
			dy = speed;
		if (up)
			dy = (short) -speed;
		
		x += dx;
		y += dy;
	}
	
	private void shotTimer() {
		if (isShooting) {
			long elapseTime = (System.nanoTime() - startTimeShot) / 1000000;
			
			if (elapseTime > delay) {
				startTimeShot = System.nanoTime();
				bullets.add(new Bullet(
						(short)((short) x + width / 2), y,
						Bullet.DEFAULT_SIZE, Bullet.DEFAULT_SIZE));
			}
		}
	}
	
	@Override
	public void update() {
		setNextPosition();
		checkBounds();
		shotTimer();
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height);
	}
	
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_A)
			left = true;
		if (key == KeyEvent.VK_W)
			up = true;
		if (key == KeyEvent.VK_D)
			right = true;
		if (key == KeyEvent.VK_S)
			down = true;
		if (key == KeyEvent.VK_SPACE)
			isShooting = true;
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_A)
			left = false;
		if (key == KeyEvent.VK_W)
			up = false;
		if (key == KeyEvent.VK_D)
			right = false;
		if (key == KeyEvent.VK_S)
			down = false;
		if (key == KeyEvent.VK_SPACE)
			isShooting = false;
	}
}
