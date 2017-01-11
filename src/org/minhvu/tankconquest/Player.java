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
	
	private int speed;

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
		
		speed = 5;
		
		uppressed = false;
		downpressed = false;
		leftpressed = false;
		rightpressed = false;
		
		angle = 90;
		rotation = 2;
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.rotate(Math.toRadians(angle - 90), location.x + dimension.width / 2, location.y + dimension.height / 2);
		g2d.drawImage(movement.getSprite(), location.x, location.y, Game.getInstance());
		g2d.rotate(Math.toRadians(-(angle - 90)), location.x + dimension.width / 2, location.y + dimension.height / 2);
	}
	
	public void move()
	{
		if (uppressed)
		{
			if (location.y - speed > 0)
			{
			    location.x -= Math.round(speed * Math.cos(Math.toRadians(angle)));
			    location.y -= Math.round(speed * Math.sin(Math.toRadians(angle)));
			}
		}
		
		if (downpressed)
		{
			if (location.y + speed < Game.getInstance().getHeight() - dimension.height)
			{
			    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
			    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
			}
		}
		
		if (leftpressed)
		{
			if (location.x - speed > 0)
			{
				angle -= rotation;
			}
		}
		
		if (rightpressed)
		{
			if (location.x + speed < Game.getInstance().getWidth() - dimension.width)
			{
				angle += rotation;
			}
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
}
