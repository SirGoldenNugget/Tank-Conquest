package org.minhvu.tankconquest;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound
{
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("/gameover.wav"));
	public static final AudioClip FIRE = Applet.newAudioClip(Sound.class.getResource("/fire.wav"));
}