package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Map
{
	private int tilesize;
	
	private Dimension dimensions;

	private Point[][] map;
	private BufferedImage[][] spritemap;

	public Map()
	{
		tilesize = 84;
		
		dimensions = new Dimension(23, 13);
		
		map = new Point[dimensions.height][dimensions.width];
		spritemap = new BufferedImage[dimensions.height][dimensions.width];

		for (int i = 0; i < dimensions.height; ++i)
		{
			for (int j = 0; j < dimensions.width; ++j)
			{
				map[i][j] = new Point(0, 0);
				spritemap[i][j] = Sprite.getSprite(map[i][j].x, map[i][j].y, tilesize);
			}
		}
	}

	public void paint(Graphics g2d)
	{
		for (int i = 0; i < dimensions.height; ++i)
		{
			for (int j = 0; j < dimensions.width; ++j)
			{
				g2d.drawImage(spritemap[i][j], j * tilesize, i * tilesize, Game.getInstance());
			}
		}
	}
}
