package view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Load audio and make sound effect when needed.
 */
public class Sound implements LineListener {
  
  Clip clip;
   
  /**
   * Play a given audio file.
   * 
   * @param path the path of the audio file.
   */
  void play(String path) {
    File file = new File(path);

    try {
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);

      clip = (Clip) AudioSystem.getLine(info);
      clip.addLineListener(this);
      clip.open(stream);
      clip.start();
    } catch (UnsupportedAudioFileException ex) {
      System.out.println("The specified audio file is not supported.");
      ex.printStackTrace();
    } catch (LineUnavailableException ex) {
      System.out.println("Audio line for playing back is unavailable.");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Error playing the audio file.");
      ex.printStackTrace();
    }
       
  }
   
  /**
   * Listens to the START and STOP events of the audio line.
   */
  @Override
  public void update(LineEvent event) {
    LineEvent.Type type = event.getType();
    if (type == LineEvent.Type.STOP) {
      clip.close();
    }
  }
}