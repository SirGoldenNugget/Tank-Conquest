package org.minhvu.tankconquest;

import java.io.File;
import java.io.IOException;

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
	
	public Sound()
	{
		GAMEOVER = getAudioClip("res/gameover.wav");
		FIRE = getAudioClip("res/fire.wav");
		HIT = getAudioClip("res/hit.wav");
		EXPLOSION = getAudioClip("res/explosion.wav");
	}
	
	private Clip getAudioClip(String path)
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
	}
}