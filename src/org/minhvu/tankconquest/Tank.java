package org.minhvu.tankconquest;

import java.awt.Graphics2D;
import java.awt.Rectangle;

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
	
	protected boolean hasCollision()
	{
		for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
		{
			for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
			{
				if (Game.getInstance().getMap().getMap()[i][j] != 0
						&& new Rectangle(j * 84, i * 84, 84, 84).intersects(getBounds()))
				{
					return true;
				}
			}
		}

		return false;
	}
	
	protected boolean hasCollisionEnemy()
	{
		for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
		{
			if (!Game.getInstance().getEnemies().get(i).equals(this) && Game.getInstance().getEnemies().get(i).getBounds().intersects(getBounds()))
			{
				return true;
			}
		}
		
		return false;
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
