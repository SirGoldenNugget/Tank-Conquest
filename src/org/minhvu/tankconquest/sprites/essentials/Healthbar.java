package org.minhvu.tankconquest.sprites.essentials;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Healthbar
{
	private int health;
	private int maxhealth;
	
	private RoundRectangle2D healthbar;
	private Rectangle2D middlebar;
	private Ellipse2D frontend;
	private Ellipse2D backend;
	
	private Font healthfont = new Font("calibri", Font.BOLD, 14);
	private Font namefont = new Font("calibri", Font.BOLD, 16);
	
	private Sprite sprite;
	
	private String name;
	
	public Healthbar(Sprite sprite, String name, int health)
	{
		this.sprite = sprite;
		this.name = name;
		this.maxhealth = health;
		this.health = 100;
		
		healthbar = new RoundRectangle2D.Float(0, 0, 60, 14, 14, 14);
		middlebar = new Rectangle2D.Float(0, 0, 46, 14);
		frontend = new Ellipse2D.Float(0, 0, 14, 14);
		backend = new Ellipse2D.Float(0, 0, 14, 14);
	}
	
	public void paint(Graphics2D g2d)
	{
		healthbar.setFrame(new Point((int) (sprite.getLocation().x + (sprite.getDimensions().getWidth() - healthbar.getBounds().getSize().getWidth()) / 2), (int) (sprite.getLocation().y + sprite.getDimensions().getHeight() + 10)), healthbar.getBounds().getSize());
		
		frontend.setFrame(healthbar.getBounds().getLocation(), frontend.getBounds().getSize());
		middlebar.setFrame(new Point((int) (frontend.getX() + frontend.getBounds().getSize().width / 2), (int) (frontend.getY())), new Dimension((int) ((double) (health) / (double) (maxhealth) * 46), (int) (middlebar.getBounds().getHeight())));
		backend.setFrame(new Point((int) (middlebar.getX() + middlebar.getWidth() - backend.getWidth() / 2), (int) (middlebar.getY())), backend.getBounds().getSize());

		g2d.setColor(Color.GRAY);
		g2d.fill(healthbar);
		g2d.draw(healthbar);
		
		if (health > (int) (maxhealth / 4 * 3))
		{
			g2d.setColor(Color.GREEN);
		}
		
		else if (health > (int) (maxhealth / 4 * 2))
		{
			g2d.setColor(Color.YELLOW);
		}
		
		else if (health > (int) (maxhealth / 4))
		{
			g2d.setColor(Color.ORANGE);
		}
		
		else if (health > 0)
		{
			g2d.setColor(Color.RED);
		}

		g2d.fill(middlebar);
		g2d.draw(middlebar);
		g2d.fill(frontend);
		g2d.draw(frontend);
		g2d.fill(backend);
		g2d.draw(backend);

		g2d.setColor(Color.GRAY);
		g2d.draw(healthbar);
		
		g2d.setFont(healthfont);
		g2d.setColor(Color.BLACK);
		g2d.drawString(health + "", (int) (healthbar.getCenterX() - g2d.getFontMetrics().stringWidth(health + "") / 2), (int) (healthbar.getCenterY() + 5));
		
		g2d.setFont(namefont);
		g2d.drawString(name, (int) (healthbar.getCenterX() - g2d.getFontMetrics().stringWidth(name) / 2), (int) (sprite.getLocation().y - 5));
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getMaxhealth()
	{
		return maxhealth;
	}

	public void setMaxhealth(int maxhealth)
	{
		this.maxhealth = maxhealth;
	}
}
