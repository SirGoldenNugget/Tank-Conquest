package org.minhvu.tankconquest;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

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
				if (Game.getInstance().getMap().getMap()[i][j] != 0)
				{
					Ellipse2D tile = new Ellipse2D.Float(j * 84, i * 84, 84, 84);
					Ellipse2D tank = new Ellipse2D.Float(location.x, location.y, 84, 84);
					
					Area tilearea = new Area(tile);
					Area tankarea = new Area(tank);
					
					tilearea.intersect(tankarea);
					
					if (!tilearea.isEmpty())
					{
						return true;
					}
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
