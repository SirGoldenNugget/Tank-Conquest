package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
	@SuppressWarnings("unused")
	private int maxrotation;
	@SuppressWarnings("unused")
	private int minrotation;
	
	private boolean turn = false;
	private boolean left = false;
	
	private long timer = System.currentTimeMillis();
	
	private static String[] names = 
	{
		"Minh",
		"Cory",
		"Quin",
		"Reda",
		"Julian",
		"Nico",
		"Annaliese",
		"Adam",
		"Paul",
		"Alex",
		"Brandon",
		"Brad",
		"Julia",
		"Olivia",
		"Snake",
		"Alexander",
		"Will",
		"Evan"
	};
	
	public Enemy()
	{
		super();
		
		movement = new Animation(moving, 10);
		movement.start();
		
		healthbar = new Healthbar(this, names[(int)(Math.random() * names.length)], 100);
		
		damage = (int) (Math.random() * 10) + 1;
		
		rotate = (int) (Math.random()  * 40 + 1);
		
		direction = true;
		maxrotation = 2500;
		minrotation = 1000;

		dimension = new Dimension(84, 84);
		location = new Point(((int) (Math.random() * 23)) * 84, ((int) (Math.random() * 13)) * 84);
		
		for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
		{
			while (hasCollision())
			{
				location = new Point(((int) (Math.random() * 23)) * 84, ((int) (Math.random() * 13)) * 84);
			}
		}
		
		forward = 2;
		reverse = 1;
		
		firerate = (int) (Math.random() * 2000) + 1000;
		
		initialangle = 90; // TODO Make The Angle Able To Become Random.
		angle = initialangle;
		rotation = 1;
	}
	
	@Override
	public void move()
	{	
		if (direction)
		{
			location.x -= Math.round(forward * Math.cos(Math.toRadians(angle)));
			
			if (hasCollision())
			{
				location.x += Math.round(forward * Math.cos(Math.toRadians(angle)));
			}
			
			location.y -= Math.round(forward * Math.sin(Math.toRadians(angle)));
			
			if (hasCollision())
			{
				location.y += Math.round(forward * Math.sin(Math.toRadians(angle)));
			}
		}

		else
		{
			location.x += Math.round(reverse * Math.cos(Math.toRadians(angle)));
			
			if (hasCollision())
			{
				location.x -= Math.round(reverse * Math.cos(Math.toRadians(angle)));
			}
			
			location.y += Math.round(reverse * Math.sin(Math.toRadians(angle)));
			
			if (hasCollision())
			{
				location.y -= Math.round(reverse * Math.sin(Math.toRadians(angle)));
			}
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
	
	/*private void turn()
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
	}*/
	
	@Override
	public void end()
	{
		Game.getInstance().getScore().increment();
		Game.getInstance().getExplosions().add(new Explosion(this));
		Game.getInstance().getEnemies().remove(this);
	}
}
