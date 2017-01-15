package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Tank
{
	private static BufferedImage[] moving =
	{
		Sprite.getSprite(1, 1, 84),
		Sprite.getSprite(2, 1, 84),
		Sprite.getSprite(3, 1, 84),
		Sprite.getSprite(4, 1, 84),
		Sprite.getSprite(5, 1, 84),
		Sprite.getSprite(6, 1, 84),
		Sprite.getSprite(7, 1, 84),
		Sprite.getSprite(0, 2, 84)
	};
	
	private int rotate;

	private boolean direction;
	private int maxrotation;
	private int minrotation;
	
	private boolean turn = false;
	private boolean left = false;
	
	private long timer = System.currentTimeMillis();
	
	public Enemy()
	{
		super();
		
		movement = new Animation(moving, 10);
		movement.start();
		
		rotate = (int) (Math.random()  * 40 + 1);
		
		direction = true;
		maxrotation = 2500;
		minrotation = 1000;

		location = new Point((int) (Math.random() * 1800), (int) (Math.random() * 900));
		dimension = new Dimension(84, 84);
		
		forward = 2;
		reverse = 1;
		
		firerate = (int) (Math.random() * 2000) + 1000;
		
		initialangle = 90; // TODO Make The Angle Able To Become Random.
		angle = initialangle;
		rotation = 1;
	}
	
	public void move()
	{
		boolean collision = false;
		
		// TODO FIX COLLISION DETECTION WITH ENEMIES.
		
		/*for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
		{
			if (Game.getInstance().getEnemies().get(i).getBounds().intersects(getBounds()))
			{
				collision = true;
			}
		}*/

		if (!collision)
		{
			if (direction)
			{
				location.x -= Math.round(forward * Math.cos(Math.toRadians(angle)));
				location.y -= Math.round(forward * Math.sin(Math.toRadians(angle)));
			}

			else
			{
				location.x += Math.round(reverse * Math.cos(Math.toRadians(angle)));
				location.y += Math.round(reverse * Math.sin(Math.toRadians(angle)));
			}
		}
		
		if (getBounds().getCenterX() - dimension.width / 2 < 0)
		{
			location.x = 0;
			
			turn();
		}
		
		if (getBounds().getCenterX() + dimension.width / 2 > Game.getInstance().getWidth())
		{
			location.x = Game.getInstance().getWidth() - dimension.width;
			
			turn();
		}
		
		if (getBounds().getCenterY() - dimension.height / 2 < 0)
		{
			location.y = 0;
			
			turn();
		}
		
		if (getBounds().getCenterY() + dimension.height / 2 > Game.getInstance().getHeight())
		{
			location.y = Game.getInstance().getHeight() - dimension.height;
			
			turn();
		}
		
		if (turn)
		{
			if (rotate > 0)
			{
				if (left)
				{
					angle -= rotation;
				}
				
				else
				{
					angle += rotation;
				}
				
				--rotate;
				
				if (rotate <= 0)
				{
					rotate = (int) (Math.random()  * 40 + 1);
					turn = false;
				}
			}
		}
		
		else
		{
			if ((int) (Math.random() * 10) + 1 == 1)
			{
				turn = true;
				
				if (Math.round(Math.random()) == 0)
				{
					left = true;
				}
				
				else
				{
					left = false;
				}
			}
		}
		
		movement.update();
	}
	
	public void fire()
	{
		if (System.currentTimeMillis() - timer > firerate)
		{
			timer = System.currentTimeMillis();
			Game.getInstance().getBullets().add(new Bullet(this));
		}
	}
	
	private void turn()
	{
		direction = false;
		rotate = 180;
		
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				direction = true;
			}
		}, (int) (Math.random() * (maxrotation - minrotation)) + minrotation);
	}
}
