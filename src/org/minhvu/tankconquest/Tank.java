package org.minhvu.tankconquest;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Tank extends Sprite
{
	protected Animation movement;
	protected Healthbar healthbar;
	
	protected int damage;
	
	protected int speed;
	protected int forward;
	protected int reverse;
	
	protected int firerate;

	protected long movementtimer;
	protected long bullettimer;

	protected double rotation;
	
	public Tank()
	{
		super();
		
		movementtimer = System.currentTimeMillis();
		bullettimer = System.currentTimeMillis();
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.draw(this.getBounds());
		g2d.rotate(Math.toRadians(angle - initialangle), getBounds().getCenterX(), getBounds().getCenterY());
		g2d.draw(this.getBounds());
		g2d.drawImage(movement.getSprite(), location.x, location.y, Game.getInstance());
		g2d.rotate(Math.toRadians(-(angle - initialangle)), getBounds().getCenterX(), getBounds().getCenterY());
		
		healthbar.paint(g2d);
	}
	
	protected boolean hasCollision()
	{
		for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
		{
			for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
			{
				if (Game.getInstance().getMap().getMap()[i][j] != 0 && getBounds().intersects(new Rectangle2D.Float(j * 84, i * 84, 84, 84)))
				{
					return true;
				}
			}
		}

		return false;
		
		/*List<Line2D> lines = new ArrayList<Line2D>();
		lines.add(new Line2D.Float(84, 84 * 5, 84 * 3, 84 * 5));
		lines.add(new Line2D.Float(84, 84 * 5, 84, 84 * 7));
		lines.add(new Line2D.Float(84 * 3, 84 * 5, 84 * 3, 84 * 7));
		lines.add(new Line2D.Float(84, 84 * 7, 84 * 3, 84 * 7));
		
		
		for (int i = 0; i < lines.size(); ++i)
		{
			Line2D line = lines.get(i);
			
			if (getBounds().intersectsLine(line))
			{
				if (line.getX1() == line.getX2())
				{
				    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
				}
				
				if (line.getY1() == line.getY2())
				{
				    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
				}
			}
		}
		
		return false;*/
		
		/*
		List<Rectangle2D> tiles = new ArrayList<Rectangle2D>();
		tiles.add(new Rectangle2D.Float(84, 84 * 5, 84 * 2, 84 * 2));*/
		
		/*for (int i = 0; i < tiles.size(); ++i)
		{
			Rectangle2D tile = tiles.get(i);
			
			if (tile.getBounds2D().intersects(getBounds().getBounds2D()))
			{
				
				if (location.x + dimension.width > tile.getX() || location.x < tile.getX() + tile.getWidth())
				{
				    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
				}
				
				if (location.y + dimension.height > tile.getY() || location.y < tile.getY() + tile.getHeight())
				{
				    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
				}
				
				if (location.x + dimension.width > tile.getX() && angle >= 270 && angle <= 90)
				{
				    location.x += speed;
				}
				
				if (location.x < tile.getX() + tile.getWidth() && angle <= 270 && angle >= 90)
				{
				    location.x += -speed;
				}
				
				if (location.y + dimension.height > tile.getY() && angle <= 360 && angle >= 180)
				{
				    location.y += -speed;
				}
				
				if (location.y < tile.getY() + tile.getHeight() && angle >= 0 && angle <= 180)
				{
				    location.y += speed;
				}
				
				if (location.x + dimension.width > tile.getX() && angle >= 270 && angle <= 90)
				{
				    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
				}
				
				if (location.x < tile.getX() + tile.getWidth() && angle <= 270 && angle >= 90)
				{
				    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
				}
				
				if (location.y + dimension.height > tile.getY() && angle <= 360 && angle >= 180)
				{
				    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
				}
				
				if (location.y < tile.getY() + tile.getHeight() && angle >= 0 && angle <= 180)
				{
				    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
				}
			}
		}*/
		
		//return false;
		
		/*for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
		{
			for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
			{	
				if (Game.getInstance().getMap().getMap()[i][j] != 0)
				{
					Ellipse2D tile = new Ellipse2D.Float(j * 84, i * 84, 84, 84);
					Ellipse2D tank = new Ellipse2D.Float(location.x, location.y, 84, 84);
					
					Area tilearea = new Area(tile);
					Area tankarea = new Area(tank);
					
					tilearea.intersect(tankarea);
					tankarea.intersect(tilearea);
					
					if (!tilearea.isEmpty() && !tankarea.isEmpty())
					{
						if (location.x + dimension.width > tile.getX() || location.x < tile.getX() + tile.getWidth())
						{
						    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
						    return true;
						}
						
						if (location.y + dimension.height > tile.getY() || location.y < tile.getY() + tile.getHeight())
						{
						    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
						    return true;
						}
					}
				}
			}
		}

		return false;*/
		
		/*for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
		{
			for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
			{
				Rectangle tile = new Rectangle(j * 84, i * 84, 84, 84);
				
				if (Game.getInstance().getMap().getMap()[i][j] != 0 && tile.intersects(getBounds()))
				{
					if (location.x + dimension.width > tile.x || location.x < tile.x + tile.width)
					{
					    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
					}
					
					if (location.y + dimension.height > tile.y || location.y < tile.y + tile.height)
					{
					    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
					}
					
					if (location.x + dimension.width > tile.x)
					{
						location.x = tile.x - dimension.width;
					}
					
					else if (location.x < tile.x + tile.width)
					{
						location.x = tile.x + tile.width;
					}
					
					if (location.y + dimension.height > tile.y)
					{
						location.y = tile.y - dimension.height;
					}
					
					else if (location.y < tile.y + tile.height)
					{
						location.y = tile.y + tile.height;
					}
				}
			}
		}
		
		return false;*/
		
		/*for (int i = 0; i < Game.getInstance().getMap().getMap().length; ++i)
		{
			for (int j = 0; j < Game.getInstance().getMap().getMap()[i].length; ++j)
			{	
				if (Game.getInstance().getMap().getMap()[i][j] != 0)
				{
		            AffineTransform at = new AffineTransform();
		            at.translate(getBounds().getCenterX(), getBounds().getCenterY());
		            at.rotate(Math.toRadians(angle - initialangle), getBounds().getWidth() / 2, getBounds().getHeight() / 2);;
					
		            GeneralPath tankpath = new GeneralPath();
		            tankpath.append(getBounds().getPathIterator(at), true);
		            
		            Rectangle tile = new Rectangle(j * 84, i * 84, 84, 84);
		            
		            at = new AffineTransform();
		            at.translate(tile.getCenterX(), tile.getCenterY());
		            
		            GeneralPath tilepath = new GeneralPath();
		            tilepath.append(tile.getPathIterator(at), true);
		            
		            Area tankarea = new Area(tankpath);
		            Area tilearea = new Area(tilepath);
		            
		            tilearea.intersect(tankarea);
		            
		            if (!tilearea.isEmpty())
		            {
		            	if (location.x + dimension.width > tilearea.getBounds().x || location.x < tilearea.getBounds().x + tilearea.getBounds().width)
						{
						    location.x += Math.round(speed * Math.cos(Math.toRadians(angle)));
						}
						
						if (location.y + dimension.height > tilearea.getBounds().y || location.y < tilearea.getBounds().y + tilearea.getBounds().height)
						{
						    location.y += Math.round(speed * Math.sin(Math.toRadians(angle)));
						}
		            	
		            	return true;
		            }
				}
			}
		}

		return false;*/
		
		
	}
	
	protected boolean hasCollisionEnemy()
	{
		for (int i = 0; i < Game.getInstance().getEnemies().size(); ++i)
		{
			if (!Game.getInstance().getEnemies().get(i).equals(this) && Game.getInstance().getEnemies().get(i).getBounds().intersects(getBounds()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(location.x + 10, location.y + 2, 64, 77);
	}
	
	public Healthbar getHealthbar()
	{
		return healthbar;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void update()
	{
		if (angle < 0)
		{
			angle += 360;
		}
		
		else if (angle >= 360)
		{
			angle -= 360;
		}
	}
	
	public abstract void move();
	public abstract void end();
}
