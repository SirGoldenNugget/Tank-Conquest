package org.minhvu.tankconquest;

import java.awt.Graphics;
import java.awt.Point;

public class Map
{
	private int tilesize;

	private Point[][] map = new Point[13][23];

	public Map()
	{
		tilesize = 84;

		for (int i = 0; i < map.length; ++i)
		{
			for (int j = 0; j < map[i].length; ++j)
			{
				map[i][j] = new Point(0, 0);
			}
		}
	}

	public void paint(Graphics g2d)
	{
		for (int i = 0; i < map.length; ++i)
		{
			for (int j = 0; j < map[i].length; ++j)
			{
				g2d.drawImage(Sprite.getSprite(map[i][j].x, map[i][j].y, tilesize), j * tilesize, i * tilesize, Game.getInstance());
			}
		}
	}
}
