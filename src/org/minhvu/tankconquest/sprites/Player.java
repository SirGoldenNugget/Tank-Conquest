package org.minhvu.tankconquest.sprites;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import org.minhvu.tankconquest.Game;
import org.minhvu.tankconquest.sprites.essentials.Animation;
import org.minhvu.tankconquest.sprites.essentials.Healthbar;
import org.minhvu.tankconquest.sprites.essentials.Sprite;

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
		
		healthbar = new Healthbar(this, JOptionPane.showInputDialog("Please Enter A Name:"), 100);
		
		damage = 20;
		
		location = new Point(0, 0);
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
		rotation = 2;
	}
	
	@Override
	public void move()
	{
		if (uppressed)
		{
			if (speed < forward && System.currentTimeMillis() - movementtimer > revspeed)
			{
				movementtimer = System.currentTimeMillis();
				++speed;
			}
		    
			location.x -= Math.round(speed * Math.cos(Math.toRadians(angle)));
			
			if (hasCollision())
			{
				location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
				
				movementtimer = System.currentTimeMillis();
				speed = reverse;
			}
			
		    location.y -= Math.round(speed * Math.sin(Math.toRadians(angle)));
			
		    if (hasCollision())
		    {
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
