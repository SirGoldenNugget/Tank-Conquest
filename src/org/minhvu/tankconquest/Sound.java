package org.minhvu.tankconquest;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound
{
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
	public static final AudioClip HIT = Applet.newAudioClip(Sound.class.getResource("hit.wav"));
	public static final AudioClip CRASH = Applet.newAudioClip(Sound.class.getResource("crash.wav"));
}