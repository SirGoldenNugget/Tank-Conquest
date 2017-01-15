package org.minhvu.tankconquest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score
{
	private int score;
	
	public Score()
	{
		score = 0;
	}
	
	public void increment()
	{
		++score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	// Rendering Image
	public void paint(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(score), 10, 30);
	}
}
