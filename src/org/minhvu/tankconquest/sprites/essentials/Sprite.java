package org.minhvu.tankconquest.sprites.essentials;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.minhvu.tankconquest.Game;

public class Sprite
{
	private static BufferedImage spritesheet;

	public static void loadSprite(String file)
	{
		BufferedImage sprite = null;

		try
		{
			sprite = ImageIO.read(Game.getInstance().getClass().getResourceAsStream(file));
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}

		spritesheet = sprite;
	}

	public static BufferedImage getSprite(int x, int y, int width, int height)
	{
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	public static BufferedImage getSprite(int x, int y, int size)
	{
		return spritesheet.getSubimage(x * size, y * size, size, size);
	}
	
	protected Point location;
	protected Dimension dimension;

	protected double initialangle;
	protected double angle;
	
	public Sprite()
	{
		location = new Point();
		dimension = new Dimension();
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public Dimension getDimensions()
	{
		return dimension;
	}
	
	public void setDimensions(Dimension dimension)
	{
		this.dimension = dimension;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(location, dimension);
	}
	
	public double getInitialAngle()
	{
		return initialangle;
	}
	
	public double getAngle()
	{
		return angle;
	}
}