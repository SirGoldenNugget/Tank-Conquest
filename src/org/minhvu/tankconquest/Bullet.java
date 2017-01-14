package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite
{
	private static BufferedImage image = Sprite.getSprite(454, 202, 16, 16);

	private static final int firerate = 500;
	
	private int speed;
	//private double range;
	
	private boolean exploded;
	
	private double angle;
	//private Point initial;
	
	
	private Sprite sprite;
	
	public Bullet(Sprite sprite)
	{
		this.sprite = sprite;
		
		dimension = new Dimension(16, 16);

		location = new Point(
				(int) Math.round(Game.getInstance().getPlayer().getBounds().getCenterX() - dimension.width / 2),
				(int) Math.round(Game.getInstance().getPlayer().getBounds().getCenterY() - dimension.height / 2));

		speed = 15;
		//range = 1000;
		
		exploded = false;
		
		angle = Game.getInstance().getPlayer().getAngle();
		//initial = Game.getInstance().getPlayer().getLocation();

		Game.getInstance().getSound().FIRE.stop();
		Game.getInstance().getSound().FIRE.setFramePosition(0);
		Game.getInstance().getSound().FIRE.start();
	}
	
	public void move()
	{
		if (!exploded)
		{
		    location.x -= Math.round(speed * Math.cos(Math.toRadians(angle)));
		    location.y -= Math.round(speed * Math.sin(Math.toRadians(angle)));
		}
		
		//if (location.distance(initial) > range) { exploded = true; }

		if (sprite instanceof Player)
		{
			Player player = (Player) sprite;
			
			for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
			{
				if (player.getBounds().intersects(Game.getInstance().getEnemies().get(i).getBounds()))
				{
					
				}
			}
		}
		
		else if (sprite instanceof Enemy)
		{
			
		}

		if (location.x > Game.getInstance().getWidth() || location.x + dimension.width < 0
				|| location.y > Game.getInstance().getHeight() || location.y + dimension.height < 0)
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
