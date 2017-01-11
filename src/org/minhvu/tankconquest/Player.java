package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Sprite
{
	private static BufferedImage[] moving =
	{
		Sprite.getSprite(1, 0, 84),
		Sprite.getSprite(2, 0, 84),
		Sprite.getSprite(3, 0, 84),
		Sprite.getSprite(4, 0, 84),
		Sprite.getSprite(5, 0, 84),
		Sprite.getSprite(6, 0, 84),
		Sprite.getSprite(7, 0, 84),
		Sprite.getSprite(0, 1, 84)
	};
	
	private Animation movement;
	
	private int forward;
	private int reverse;

	private boolean uppressed;
	private boolean downpressed;
	private boolean leftpressed;
	private boolean rightpressed;
	
	private double angle;
	private double rotation;
	
	public Player()
	{
		super();
		
		movement = new Animation(moving, 10);
		movement.start();
		
		location = new Point(700, 700);
		dimension = new Dimension(84, 84);
		
		forward = 5;
		reverse = 3;
		
		uppressed = false;
		downpressed = false;
		leftpressed = false;
		rightpressed = false;
		
		angle = 90;
		rotation = 2;
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.rotate(Math.toRadians(angle - 90), getBounds().getCenterX(), getBounds().getCenterY());
		g2d.drawImage(movement.getSprite(), location.x, location.y, Game.getInstance());
		g2d.rotate(Math.toRadians(-(angle - 90)), getBounds().getCenterX(), getBounds().getCenterY());
	}
	
	public void move()
	{	
		if (uppressed)
		{
		    location.x -= Math.round(forward * Math.cos(Math.toRadians(angle)));
		    location.y -= Math.round(forward * Math.sin(Math.toRadians(angle)));
		}
		
		if (downpressed)
		{
		    location.x += Math.round(reverse * Math.cos(Math.toRadians(angle)));
		    location.y += Math.round(reverse * Math.sin(Math.toRadians(angle)));
		}
		
		if (getBounds().getCenterX() - dimension.width / 2 < 0)
		{
			location.x = 0;
		}
		
		if (getBounds().getCenterX() + dimension.width / 2 > Game.getInstance().getWidth())
		{
			location.x = Game.getInstance().getWidth() - dimension.width;
		}
		
		if (getBounds().getCenterY() - dimension.height / 2 < 0)
		{
			location.y = 0;
		}
		
		if (getBounds().getCenterY() + dimension.height / 2 > Game.getInstance().getHeight())
		{
			location.y = Game.getInstance().getHeight() - dimension.height;
		}
		
		if (leftpressed)
		{
			angle -= rotation;
		}
		
		if (rightpressed)
		{
			angle += rotation;
		}
		
		if (uppressed || downpressed || leftpressed || rightpressed)
		{
			movement.update();
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
	
	public double getAngle()
	{
		return angle;
	}
}
