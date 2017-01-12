package org.minhvu.tankconquest;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound
{
	public final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("/gameover.wav"));
	//public final AudioClip FIRE = Applet.newAudioClip(Sound.class.getResource("/fire.wav"));
	
	public Clip FIRE;
	
	public Sound()
	{
		File file = new File("res/fire.wav");
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
		
		FIRE = clip;
	}
}