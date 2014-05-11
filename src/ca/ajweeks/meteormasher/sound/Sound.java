package ca.ajweeks.meteormasher.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

public class Sound {
	//	private AudioClip clip;
	
	public Sound(String filename) {
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixInfos) {
			//			System.out.println(info.getName() + ", " + info.getDescription());
		}
		//		clip = Applet.newAudioClip(Sound.class.getResource(filename));
	}
	
	public void play() {
		//		clip.play();
	}
	
	public void stop() {
		//		clip.stop();
	}
	
}
