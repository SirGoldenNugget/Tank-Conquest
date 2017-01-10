package org.minhvu.tankconquest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class End
{
	Font gameover;
	Font textfont;
	Font buttonfont;
	
	Rectangle playbutton;
	Rectangle quitbutton;
	
	public End()
	{
		gameover = new Font("calibri", Font.BOLD, 300);
		textfont = new Font("calibri", Font.BOLD, 100);
		buttonfont = new Font("calibri", Font.BOLD, 100);
		
		playbutton = new Rectangle(250, 700, 600, 100);
		quitbutton = new Rectangle(Game.getInstance().getWidth() - 850, 700, 600, 100);
	}
	
	public void paint(Graphics2D g2d)
	{
		String finalscore = "FINAL SCORE: " + Game.getInstance().getScore().getScore();
		
		g2d.setFont(gameover);
		g2d.setColor(Color.WHITE);
		g2d.drawString("GAME OVER", (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth("GAME OVER")) / 2), 400);
		
		g2d.setFont(textfont);
		g2d.drawString(finalscore, (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth(finalscore)) / 2), 600);
		g2d.setFont(buttonfont);
		
		g2d.fill(playbutton);
		g2d.fill(quitbutton);
		
		g2d.draw(playbutton);
		g2d.draw(quitbutton);

		g2d.setColor(Color.BLACK);
		g2d.drawString("PLAY", playbutton.x + (playbutton.width - g2d.getFontMetrics().stringWidth("PLAY")) / 2, playbutton.y + 85);
		g2d.drawString("QUIT", quitbutton.x + (quitbutton.width - g2d.getFontMetrics().stringWidth("QUIT")) / 2, quitbutton.y + 85);
	}
	
	public void mousePressed(MouseEvent e)
	{
		Point location = new Point(e.getX(), e.getY());
		
		if (playbutton.contains(location))
		{
			Game.getInstance().restart();
		}
		
		else if (quitbutton.contains(location))
		{
			System.exit(1);
		}
	}
}
