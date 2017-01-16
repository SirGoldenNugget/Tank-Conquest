package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Tank
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

	private boolean uppressed;
	private boolean downpressed;
	private boolean leftpressed;
	private boolean rightpressed;
	
	private int revspeed;
	
	public Player()
	{
		super();
		
		movement = new Animation(moving, 10);
		movement.start();
		
		healthbar = new Healthbar(this, "SirGoldenNugget", 100);
		
		damage = 20;
		
		location = new Point(700, 700);
		dimension = new Dimension(84, 84);
		
		forward = 5;
		reverse = 2;
		
		firerate = 1000;
		
		uppressed = false;
		downpressed = false;
		leftpressed = false;
		rightpressed = false;
		
		revspeed = 500;
		
		initialangle = 90;
		angle = initialangle;
		rotation = 1;
	}
	
	@Override
	public void move()
	{
		boolean collision = false;
		
		if (uppressed)
		{
			if (speed < forward && System.currentTimeMillis() - movementtimer > revspeed)
			{
				movementtimer = System.currentTimeMillis();
				++speed;
			}
			
		    location.x -= Math.round(speed * Math.cos(Math.toRadians(angle)));
		    location.y -= Math.round(speed * Math.sin(Math.toRadians(angle)));
		    
		    for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
			{
				if (getBounds().intersects(Game.getInstance().getEnemies().get(i).getBounds()))
				{
					collision = true;
				}
			}
		    
		    for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
			{
				for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
				{
					if (Game.getInstance().getMap().getMap()[i][j] != 0 && new Rectangle(j * 84, i * 84, 84, 84).intersects(getBounds()))
					{
						collision = true;
					}
				}
			}
		    
		    if (collision)	
		    {
			    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
			    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));

				movementtimer = System.currentTimeMillis();
				speed = reverse;
		    }
		}
		
		else
		{
			movementtimer = System.currentTimeMillis();
			speed = reverse;
		}
		
		if (downpressed)
		{
		    location.x += Math.round(reverse * Math.cos(Math.toRadians(angle)));
		    location.y += Math.round(reverse * Math.sin(Math.toRadians(angle)));
		    
		    for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
			{
				if (getBounds().intersects(Game.getInstance().getEnemies().get(i).getBounds()))
				{
					collision = true;
				}
			}
		    
		    for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
			{
				for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
				{
					if (Game.getInstance().getMap().getMap()[i][j] != 0 && new Rectangle(j * 84, i * 84, 84, 84).intersects(getBounds()))
					{
						collision = true;
					}
				}
			}
		    
		    if (collision)
		    {
			    location.x -= Math.round(reverse * Math.cos(Math.toRadians(angle)));
			    location.y -= Math.round(reverse * Math.sin(Math.toRadians(angle)));
		    }
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
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE && System.currentTimeMillis() - bullettimer > firerate)
		{
			Game.getInstance().getBullets().add(new Bullet(this));
			bullettimer = System.currentTimeMillis();
		}
	}

	@Override
	public void end()
	{
		Game.getInstance().getExplosions().add(new Explosion(Game.getInstance().getPlayer()));
		Game.getInstance().end();
	}
}
