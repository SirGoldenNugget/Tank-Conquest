package org.minhvu.tankconquest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound
{
	public Clip FIRE;
	public Clip HIT;
	public Clip EXPLOSION;
	public Clip GAMEOVER;
	
	private InputStream inputstream;
	private AudioInputStream audioinputstream;
	
	public Sound()
	{
		GAMEOVER = getAudioClip("/gameover.wav");
		FIRE = getAudioClip("/fire.wav");
		HIT = getAudioClip("/hit.wav");
		EXPLOSION = getAudioClip("/explosion.wav");
		
		
	}
	
	private Clip getAudioClip(String path)
	{
		Clip clip = null;
		
		inputstream = getClass().getResourceAsStream(path);

		try
		{
			audioinputstream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputstream));

			try
			{
				clip = AudioSystem.getClip();
				clip.open(audioinputstream);
			}

			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
		}

		catch (UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
		
		return clip;
	}
	
	/*private Clip getAudioClip(String path)
	{
		File file = new File(path);
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
		}
		
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		AudioInputStream inputstream = null;
		
		try
		{
			inputstream = AudioSystem.getAudioInputStream(file);
		}
		
		catch (UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			clip.open(inputstream);
		}
		
		catch (LineUnavailableException | IOException e)
		{
			e.printStackTrace();
		}
		
		return clip;
	}*/
}