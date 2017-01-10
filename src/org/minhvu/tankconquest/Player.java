package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player
{
	private static BufferedImage image = Sprite.getSprite(0, 1, 84);
	
	private Point location;
	private int speed;

	private boolean uppressed;
	private boolean downpressed;
	private boolean leftpressed;
	private boolean rightpressed;
	
	public Player()
	{
		location = new Point((Game.getInstance().getWidth() - image.getWidth(Game.getInstance())) / 2, Game.getInstance().getHeight() - 200);
		speed = 10;
		
		uppressed = false;
		downpressed = false;
		leftpressed = false;
		rightpressed = false;
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.drawImage(image, location.x, location.y, Game.getInstance());
	}
	
	public void move()
	{
		if (uppressed)
		{
			if (location.y - speed > 0)
			{
				location.y -= speed;
			}
		}
		
		if (downpressed)
		{
			if (location.y + speed < Game.getInstance().getHeight() - image.getHeight(Game.getInstance()))
			{
				location.y += speed;
			}
		}
		
		if (leftpressed)
		{
			if (location.x - speed > 0)
			{
				location.x -= speed;
			}
		}
		
		if (rightpressed)
		{
			if (location.x + speed < Game.getInstance().getWidth() - image.getWidth(Game.getInstance()))
			{
				location.x += speed;
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			uppressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downpressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftpressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightpressed = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			uppressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downpressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftpressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightpressed = true;
		}
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public Dimension getDimensions()
	{
		return new Dimension(image.getWidth(Game.getInstance()), image.getHeight(Game.getInstance()));
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(location.x + 35, location.y + 55, 55, 80);
	}
}
