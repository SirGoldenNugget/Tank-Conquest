package org.minhvu.tankconquest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;

public class Healthbar
{
	private int health;
	private int maxhealth;
	
	private RoundRectangle2D healthbar;
	
	private Font healthfont = new Font("calibri", Font.BOLD, 14);
	private Font namefont = new Font("calibri", Font.BOLD, 16);
	
	private Sprite sprite;
	
	private String name;
	
	public Healthbar(Sprite sprite, String name, int health)
	{
		this.sprite = sprite;
		this.name = name;
		this.maxhealth = health;
		this.health = this.maxhealth;
		
		healthbar = new RoundRectangle2D.Float(0, 0, 60, 14, 14, 14);
	}
	
	public void paint(Graphics2D g2d)
	{
		healthbar.setFrame(new Point((int) (sprite.getLocation().x + (sprite.getDimensions().getWidth() - healthbar.getBounds().getSize().getWidth()) / 2),
				(int) (sprite.getLocation().y + sprite.getDimensions().getHeight() + 10)), healthbar.getBounds().getSize());
		
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
		
		g2d.fill(healthbar);
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
