package org.minhvu.tankconquest.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import org.minhvu.tankconquest.Game;
import org.minhvu.tankconquest.Game.STATE;

public class Menu
{
	private RoundRectangle2D playbutton;
	private RoundRectangle2D helpbutton;
	private RoundRectangle2D quitbutton;
	private RoundRectangle2D backbutton;
	
	public Menu()
	{
		playbutton = new RoundRectangle2D.Float((Game.getInstance().getWidth() - 600) / 2, 700, 600, 100, 100, 100);
		helpbutton = new RoundRectangle2D.Float((Game.getInstance().getWidth() - 600) / 2, 825, 600, 100, 100, 100);
		quitbutton = new RoundRectangle2D.Float((Game.getInstance().getWidth() - 600) / 2, 950, 600, 100, 100, 100);
		backbutton = new RoundRectangle2D.Float((Game.getInstance().getWidth() - 600) / 2, 950, 600, 100, 100, 100);
	}
	
	public void paint(Graphics2D g2d)
	{
		Font titlefont = new Font("calibri", Font.BOLD, 525);
		Font subtitlefont = new Font("calibri", Font.BOLD, 300);
		
		g2d.setFont(titlefont);
		g2d.setColor(Color.WHITE);
		g2d.drawString("TANK", (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth("TANK")) / 2), 400);
		
		g2d.setFont(subtitlefont);
		g2d.drawString("CONQUEST", (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth("CONQUEST")) / 2), 645);

		Font buttonfont = new Font("calibri", Font.BOLD, 100);
		g2d.setFont(buttonfont);
		
		if (Game.getInstance().getState().equals(Game.STATE.MENU))
		{
			g2d.setColor(Color.WHITE);
			g2d.fill(playbutton);
			g2d.fill(helpbutton);
			g2d.fill(quitbutton);
			
			/*g2d.draw(playbutton);
			g2d.draw(helpbutton);
			g2d.draw(quitbutton);*/
			
			g2d.setColor(Color.BLACK);
			g2d.drawString("PLAY", (int) playbutton.getFrame().getX() + ((int) playbutton.getFrame().getWidth() - g2d.getFontMetrics().stringWidth("PLAY")) / 2, (int) playbutton.getFrame().getY() + 85);
			g2d.drawString("HELP", (int) helpbutton.getFrame().getX() + ((int) helpbutton.getFrame().getWidth() - g2d.getFontMetrics().stringWidth("HELP")) / 2, (int) helpbutton.getFrame().getY() + 85);
			g2d.drawString("QUIT", (int) quitbutton.getFrame().getX() + ((int) quitbutton.getFrame().getWidth() - g2d.getFontMetrics().stringWidth("QUIT")) / 2, (int) quitbutton.getFrame().getY() + 85);
		}
		
		else if (Game.getInstance().getState().equals(Game.STATE.HELP))
		{
			Font helpfont = new Font("calibri", Font.PLAIN, 40);
			
			String helptextad = "Movement: Arrow Keys To Move";
			String helptextlr = "Space: Fires Bullet";
			String helpobjective = "Objective: Destroy Enemies, Stay Alive";
			
			g2d.setColor(Color.WHITE);
			g2d.fill(backbutton);
			//g2d.draw(backbutton);

			g2d.setFont(helpfont);
			g2d.drawString(helptextad, (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth(helptextad)) / 2), 750);
			g2d.drawString(helptextlr, (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth(helptextlr)) / 2), 800);
			g2d.drawString(helpobjective, (int) ((Game.getInstance().getWidth() - g2d.getFontMetrics().stringWidth(helpobjective)) / 2), 850);

			g2d.setFont(buttonfont);
			g2d.setColor(Color.BLACK);
			g2d.drawString("BACK", (int) backbutton.getFrame().getX() + ((int) backbutton.getFrame().getWidth() - g2d.getFontMetrics().stringWidth("BACK")) / 2, (int) backbutton.getFrame().getY() + 85);
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
		Point location = new Point(e.getX(), e.getY());
		
		if (Game.getInstance().getState().equals(Game.STATE.MENU))
		{
			if (playbutton.contains(location))
			{	
				Game.getInstance().setState(Game.STATE.PLAY);
			}
			
			else if (helpbutton.contains(location))
			{
				Game.getInstance().setState(Game.STATE.HELP);
			}
			
			else if (quitbutton.contains(location))
			{
				System.exit(1);
			}
		}
		
		else if (Game.getInstance().getState().equals(Game.STATE.HELP))
		{
			if (backbutton.contains(location))
			{
				Game.getInstance().setState(Game.STATE.MENU);
			}
		}
	}
}
