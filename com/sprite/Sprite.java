package com.sprite;

import java.awt.Graphics2D;

public abstract class Sprite {
	
	protected short x;
	protected short y;
	
	protected short width;
	protected short height;
	
	public Sprite(short x, short y, short width, short height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setX(short x) {
		this.x = x;
	}
	
	public void setY(short y) {
		this.y = y;
	}
	
	public void setWidth(short width) {
		if (width > 0)
			this.width = width;
	}
	
	public void setHeight(short height) {
		if (height > 0)
			this.height = height;
	}
	
	public short getX() {
		return this.x;
	}
	
	public short getY() {
		return this.y;
	}
	
	public short getWidth() {
		return this.width;
	}
	
	public short getHeight() {
		return this.height;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g2d);
}
