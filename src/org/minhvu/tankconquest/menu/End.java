package org.minhvu.tankconquest.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import org.minhvu.tankconquest.Game;

public class End
{
	Font gameover;
	Font textfont;
	Font buttonfont;
	
	RoundRectangle2D playbutton;
	RoundRectangle2D quitbutton;
	
	public End()
	{
		gameover = new Font("calibri", Font.BOLD, 300);
		textfont = new Font("calibri", Font.BOLD, 100);
		buttonfont = new Font("calibri", Font.BOLD, 100);
		
		playbutton = new RoundRectangle2D.Float(250, 700, 600, 100, 100, 100);
		quitbutton = new RoundRectangle2D.Float(Game.getInstance().getWidth() - 850, 700, 600, 100, 100, 100);
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
		g2d.drawString("PLAY", (int) playbutton.getFrame().getX() + ((int) playbutton.getFrame().getWidth() - g2d.getFontMetrics().stringWidth("PLAY")) / 2, (int) playbutton.getFrame().getY() + 85);
		g2d.drawString("QUIT", (int) quitbutton.getFrame().getX() + ((int) quitbutton.getFrame().getWidth() - g2d.getFontMetrics().stringWidth("QUIT")) / 2, (int) quitbutton.getFrame().getY() + 85);
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
