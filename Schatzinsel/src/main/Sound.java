package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL path[] = new URL[2];
	
	public Sound() {
		path[1] = getClass().getResource("/sound/fanfare.wav");

	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(path[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch (Exception e) {
			
		}
	}
	
	public void play() {
		FloatControl gainControl = 
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
