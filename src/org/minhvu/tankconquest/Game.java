package org.minhvu.tankconquest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable
{
	public static void main(String[] args)
	{
		new Game();
	}
	
	private static Game instance;

	private boolean running = false;
	private Thread thread;
	
	public static enum STATE
	{
		MENU,
		PLAY,
		HELP,
		END
	};
	
	private STATE state;
	private Menu menu;
	private Map map;
	private Sound sound;
	private End end;
	private Score score;
	
	private Player player;
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Bullet> bullets = new ArrayList<Bullet>();
	
	public Game()
	{
		instance = this;
		
		state = STATE.MENU;
		
		KeyListener keylistener = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (state.equals(STATE.PLAY))
				{
					player.keyPressed(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (state.equals(STATE.PLAY))
				{
					player.keyReleased(e);
				}
			}
		};
		
		MouseListener mouselistener = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (state.equals(STATE.MENU) || state.equals(STATE.HELP))
				{
					menu.mousePressed(e);
				}
				
				if (state.equals(STATE.END))
				{
					end.mousePressed(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
			}
		};

		addKeyListener(keylistener);
		addMouseListener(mouselistener);
		setFocusable(true);

		Sprite.loadSprite("/spritesheetnt.png");

		//Sound.BACKGROUND.loop();
		
		JFrame frame = new JFrame("Tank Conquest");
		frame.add(this);
		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new Menu();
		map = new Map();
		end = new End();
		sound = new Sound();
		score = new Score();
		player = new Player();
		enemies.add(new Enemy());
		
		start();
	}
	
	private synchronized void start()
	{
		if (running)
		{
			return;
		}
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop()
	{
		if (!running)
		{
			return;
		}
		
		running = false;
		
		try
		{
			thread.join();
		}
		
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.exit(ABORT);
	}
	
	@Override
	public void run()
	{
		long lasttime = System.nanoTime();
		final double ticks = 60.0;
		double nanoseconds = 1000000000 / ticks;
		double delta = 0;
		
		while (running)
		{
			long time = System.nanoTime();
			delta += (time - lasttime) / nanoseconds;
			lasttime = time;
			
			if (delta >= 1)
			{
				update();
				delta--;
			}
		}
		
		stop();
	}
	
	private void update()
	{
		this.setBackground(Color.BLACK);
		
		if (state.equals(STATE.PLAY))
		{
			player.move();
			
			for (int i = 0; i < enemies.size(); ++i)
			{
				enemies.get(i).move();
				enemies.get(i).fire();
			}
			
			for (int i = 0; i < bullets.size(); ++i)
			{
				bullets.get(i).move();

				if (bullets.get(i).hasExploded())
				{
					bullets.remove(i);
				}
			}
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		super.paint(g2d);
		
		if (state.equals(STATE.PLAY) || state.equals(STATE.END))
		{
			map.paint(g2d);
			
			for (int i = 0; i < bullets.size(); ++i)
			{
				bullets.get(i).paint(g2d);
			}

			for (int i = 0; i < enemies.size(); ++i)
			{
				enemies.get(i).paint(g2d);
			}
			
			player.paint(g2d);			
			
			if (state.equals(STATE.END))
			{
				end.paint(g2d);
			}
			
			else
			{
				score.paint(g2d);
			}
		}
		
		else if (state.equals(STATE.MENU) || state.equals(STATE.HELP))
		{
			menu.paint(g2d);
		}
		
	}

	public void end()
	{
		sound.GAMEOVER.start();
		state = STATE.END;
	}
	
	public void restart()
	{
		state = STATE.PLAY;
		
		score = new Score();
		player = new Player();
	}

	public static Game getInstance()
	{
		return instance;
	}
	
	public STATE getState()
	{
		return state;
	}
	
	public Sound getSound()
	{
		return sound;
	}
	
	public Score getScore()
	{
		return score;
	}

	public void setState(STATE state)
	{
		this.state = state;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public List<Enemy> getEnemies()
	{
		return enemies;
	}
	
	public List<Bullet> getBullets()
	{
		return bullets;
	}
}
