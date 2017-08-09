package com.sprite;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite {
	
	public static final byte DEFAULT_SIZE = 4;
	
	private short speed;
	private short dy;
	
	private Color color;
	
	public Bullet(short x, short y, short width, short height) {
		super(x, y, width, height);
		speed = -10;
		dy = speed;
		color = new Color(255, 255, 59);
	}
	
	public boolean shouldDeleted() {
		return (y < 0);
	}

	@Override
	public void update() {
		y += dy;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillOval(x, y, width, height);
	}
}
