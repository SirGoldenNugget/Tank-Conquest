package org.minhvu.tankconquest.sprites;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.minhvu.tankconquest.Game;
import org.minhvu.tankconquest.sprites.essentials.Animation;
import org.minhvu.tankconquest.sprites.essentials.Sprite;

public class Explosion extends Sprite
{
	private static BufferedImage[] exploding =
	{
		Sprite.getSprite(1, 2, 84),
		Sprite.getSprite(2, 2, 84),
		Sprite.getSprite(3, 2, 84)
	};
	
	private Animation explode = new Animation(exploding, 10);
	
	public Explosion(Sprite sprite)
	{
		super();
		
		location = (Point) sprite.getLocation().clone();
		dimension = new Dimension(84, 84);

		initialangle = sprite.getInitialAngle();
		angle = sprite.getAngle();
		
		explode.start();
	}
	
	public void paint(Graphics2D g2d)
	{
		g2d.rotate(Math.toRadians(angle - initialangle), getBounds().getCenterX(), getBounds().getCenterY());
		g2d.drawImage(explode.getSprite(), location.x, location.y, Game.getInstance());
		g2d.rotate(Math.toRadians(-(angle - initialangle)), getBounds().getCenterX(), getBounds().getCenterY());
	}
	
	public Animation getAnimation()
	{
		return explode;
	}
}
