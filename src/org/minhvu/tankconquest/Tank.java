package org.minhvu.tankconquest;

import java.awt.Graphics2D;

public abstract class Tank extends Sprite
{
	protected Animation movement;
	
	protected Healthbar healthbar;
	
	protected int damage;
	
	protected int speed;
	protected int forward;
	protected int reverse;
	
	protected int firerate;

	protected long movementtimer;
	protected long bullettimer;

	protected double rotation;
	
	public Tank()
	{
		movementtimer = System.currentTimeMillis();
		bullettimer = System.currentTimeMillis();
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.rotate(Math.toRadians(angle - initialangle), getBounds().getCenterX(), getBounds().getCenterY());
		g2d.drawImage(movement.getSprite(), location.x, location.y, Game.getInstance());
		g2d.rotate(Math.toRadians(-(angle - initialangle)), getBounds().getCenterX(), getBounds().getCenterY());
		
		healthbar.paint(g2d);
	}
	
	public Healthbar getHealthbar()
	{
		return healthbar;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public abstract void move();
	public abstract void end();
}
