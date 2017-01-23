package org.minhvu.tankconquest.sprites;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.minhvu.tankconquest.Game;
import org.minhvu.tankconquest.sprites.essentials.Sprite;

public class Bullet extends Sprite
{
	private static BufferedImage image = Sprite.getSprite(454, 202, 16, 16);
	
	private int speed;
	//private double range;
	
	private boolean exploded;
	
	@SuppressWarnings("unused")
	private double intialangle;
	private double angle;
	//private Point initial;
	
	private Sprite sprite;
	
	public Bullet(Sprite sprite)
	{
		this.sprite = sprite;
		
		dimension = new Dimension(16, 16);

		location = new Point(
				(int) Math.round(sprite.getBounds().getCenterX() - dimension.width / 2),
				(int) Math.round(sprite.getBounds().getCenterY() - dimension.height / 2));

		speed = 15;
		//range = 1000;
		
		exploded = false;
		
		initialangle = sprite.getInitialAngle();
		angle = sprite.getAngle();
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

		
		
		for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
		{
			for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
			{
				if (Game.getInstance().getMap().getMap()[i][j] != 0 && getBounds().intersects(new Rectangle(j * 84, i * 84, 84, 84)))
				{
					exploded = true;
				}
			}
		}

		if (location.x > Game.getInstance().getWidth() || location.x + dimension.width < 0 || location.y > Game.getInstance().getHeight() || location.y + dimension.height < 0)
		{
			exploded = true;
		}
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.rotate(angle - initialangle, getBounds().getCenterX(), getBounds().getCenterY());
		g2d.drawImage(image, location.x, location.y, Game.getInstance());
		g2d.rotate(-(angle - initialangle), getBounds().getCenterX(), getBounds().getCenterY());
	}
	
	public boolean hasExploded()
	{
		return exploded;
	}
}
