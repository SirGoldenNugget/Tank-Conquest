package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Map
{
	private int tilesize;
	
	private Dimension dimensions;

	private Point tile;
	
	private int[][] map = 
	{
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
	};
	
	private BufferedImage[][] spritemap;

	public Map()
	{
		tilesize = 84;
		
		dimensions = new Dimension(23, 13);
		
		tile = new Point();
		
		spritemap = new BufferedImage[dimensions.height][dimensions.width];

		for (int i = 0; i < dimensions.height; ++i)
		{
			for (int j = 0; j < dimensions.width; ++j)
			{
				switch (map[i][j])
				{
					case 0:
						tile.x = 0;
						tile.y = 0;
						break;
					case 1:
						tile.x = 0;
						tile.y = 3;
						break;
					case 2:
						tile.x = 1;
						tile.y = 3;
						break;
					case 3:
						tile.x = 2;
						tile.y = 3;
						break;
					case 4:
						tile.x = 3;
						tile.y = 3;
						break;
					case 5:
						tile.x = 4;
						tile.y = 3;
						break;
					case 6:
						tile.x = 5;
						tile.y = 3;
						break;
					case 7:
						tile.x = 6;
						tile.y = 3;
						break;
					case 8:
						tile.x = 7;
						tile.y = 3;
						break;
					case 9:
						tile.x = 8;
						tile.y = 3;
						break;
					default:
						break;
				}

				spritemap[i][j] = Sprite.getSprite(tile.x, tile.y, tilesize);
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
