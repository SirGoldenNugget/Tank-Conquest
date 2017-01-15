package org.minhvu.tankconquest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Map
{
	private int tilesize;
	
	private Dimension dimensions;

	private Point[][] map;
	private BufferedImage[][] spritemap;

	public Map(String filename)
	{
		FileReader filereader = null;
		BufferedReader bufferedreader = null;
		
		try
		{
			filereader = new FileReader(filename);
			bufferedreader = new BufferedReader(filereader);
			
			String line;
			
			while ((line = bufferedreader.readLine()) != null)
			{
				System.out.println(line);
				
				List<String> points = Arrays.asList(line.split(" "));
			}
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				if (filereader != null)
				{
					filereader.close();
				}
				
				if (bufferedreader != null)
				{
					bufferedreader.close();
				}
			}
			
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
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
