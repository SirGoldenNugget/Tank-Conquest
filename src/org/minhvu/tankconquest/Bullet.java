package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite // TODO NEED TO FIX THE POSITIONING BUG AT THE BEGGINING.
{
	private static BufferedImage image = Sprite.getSprite(454, 202, 16, 16);

	private static final int firerate = 500;
	
	private int speed;
	private double range;
	
	private boolean exploded;
	
	private double angle;
	private Point initial;
	
	
	public Bullet()
	{
		dimension = new Dimension(16, 16);

		location = new Point(
				(int) Math.round(Game.getInstance().getPlayer().getBounds().getCenterX() - dimension.width / 2),
				(int) Math.round(Game.getInstance().getPlayer().getBounds().getCenterY() - dimension.height / 2));

		speed = 15;
		range = 1000;
		
		exploded = false;
		
		angle = Game.getInstance().getPlayer().getAngle();
		initial = Game.getInstance().getPlayer().getLocation();
	}
	
	public void move()
	{
		if (!exploded)
		{
		    location.x -= Math.round(speed * Math.cos(Math.toRadians(angle)));
		    location.y -= Math.round(speed * Math.sin(Math.toRadians(angle)));
		}
		
		if (location.distance(initial) > range)
		{
			exploded = true;
		}
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.rotate(angle - 90, getBounds().getCenterX(), getBounds().getCenterY());
		g2d.drawImage(image, location.x, location.y, Game.getInstance());
		g2d.rotate(-(angle - 90), getBounds().getCenterX(), getBounds().getCenterY());
	}
	
	public boolean hasExploded()
	{
		return exploded;
	}
	
	public static int getFireRate()
	{
		return firerate;
	}
}
