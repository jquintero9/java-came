package com.state;

import com.main.Game;
import com.sprite.Player;
import com.sprite.Bullet;

import java.awt.Graphics2D;

import java.util.ArrayList;


public class PlayState extends State {
	
	private Player player;
	private ArrayList<Bullet> bullets;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		bullets = new ArrayList<Bullet>();
		player = new Player((short) (Game.WIDTH / 2 - Player.DEFAULT_SIZE),
				(short) (Game.HEIGHT - Player.DEFAULT_SIZE),
				Player.DEFAULT_SIZE, Player.DEFAULT_SIZE,
				bullets);
	}
	
	private void updateBullets() {
		if (!bullets.isEmpty()) {
			for (Bullet bullet : bullets) {
				if (!bullet.shouldDeleted())
					bullet.update();
				else {
					bullets.remove(bullet);
					break;
				}
			}
		}
	}
	
	private void renderBullets(Graphics2D g2d) {
		if (!bullets.isEmpty())
			for (Bullet bullet : bullets)
				bullet.render(g2d);
	}
	
	@Override
	public void update() {
		player.update();
		updateBullets();
	}
	
	@Override
	public void render(Graphics2D g2d) {
		player.render(g2d);
		renderBullets(g2d);
	}
	
	@Override
	public void keyPressed(int key) {
		player.keyPressed(key);
	}
	
	@Override
	public void keyReleased(int key) {
		player.keyReleased(key);
	}
}
