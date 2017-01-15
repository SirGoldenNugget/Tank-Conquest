package org.minhvu.tankconquest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation
{
	private int framecount;
	private int framedelay;
	private int currentframe;
	private int direction;
	private int totalframes;

	private boolean stopped;
	private boolean cycled;

	private List<Frame> frames = new ArrayList<Frame>();

	public Animation(BufferedImage[] frames, int framedelay)
	{
		this.framedelay = framedelay;
		this.stopped = true;
		this.cycled = false;

		for (int i = 0; i < frames.length; i++)
		{
			addFrame(frames[i], framedelay);
		}

		this.framecount = 0;
		this.framedelay = framedelay;
		this.currentframe = 0;
		this.direction = 1;
		this.totalframes = this.frames.size();
	}

	public void start()
	{
		if (!stopped)
		{
			return;
		}

		if (frames.size() == 0)
		{
			return;
		}

		stopped = false;
	}

	public void stop()
	{
		if (frames.size() == 0)
		{
			return;
		}

		stopped = true;
	}

	public void restart()
	{
		if (frames.size() == 0)
		{
			return;
		}

		stopped = false;
		currentframe = 0;
	}

	public void reset()
	{
		this.stopped = true;
		this.framecount = 0;
		this.currentframe = 0;
	}

	private void addFrame(BufferedImage frame, int duration)
	{
		if (duration <= 0)
		{
			System.err.println("Invalid Duration: " + duration);
			throw new RuntimeException("Invalid Duration: " + duration);
		}

		frames.add(new Frame(frame, duration));
		currentframe = 0;
	}

	public BufferedImage getSprite()
	{
		return frames.get(currentframe).getFrame();
	}

	public void update()
	{
		if (!stopped)
		{
			framecount++;

			if (framecount > framedelay)
			{
				framecount = 0;
				currentframe += direction;

				if (currentframe > totalframes - 1)
				{
					currentframe = 0;
					cycled = true;
				}
				
				else if (currentframe < 0)
				{
					currentframe = totalframes - 1;
				}
			}
		}
	}
	
	public boolean isCycled()
	{
		return cycled;
	}
}