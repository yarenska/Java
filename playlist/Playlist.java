package playlist;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Playlist {
	
	private Clip clip;
	private Queue<File> playlist = new LinkedList<File>();
	
	// Add your musics to queue like that: 
	// private File Track(n) = new File("Name of the music in wav format");
	
	private File Track1 = new File("The Cranberries - Dreams.wav");
	private File Track2 = new File("U2 - Electrical Storm.wav");
	private File Track3  = new File("Duran Duran - Come Undone.wav");
	private File Track4  = new File("Sting - Fields of Gold.wav");
	
	public Playlist(){
		playlist.add(Track1);
		playlist.add(Track2);
		playlist.add(Track3);
		playlist.add(Track4);
		play();
	}

	private void play() {
		
		try{
		    Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(playlist.element()));
			this.clip = clip;
			this.clip.start();
			boolean flag = false;
			while(flag == false)
			{	
				TimeUnit.SECONDS.sleep(2);
				flag = isNotRunning();
			}
			replay();
		}
		catch(Exception e){
			System.out.println("I couldn't find it.");
		}
	}
	
	public void stop(){
		clip.stop();
	}
	
	public boolean isNotRunning(){
		return !clip.isRunning();
	}
	
	public void replay(){
		playlist.remove();
		play();
	}
	
	public static void main(String[] args){
		new Playlist();
	}
}


