package gameClient;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MusicBack {

    /*This class play music in the background in the open screen
        and in the playground field
     */
        void playMusic(String musicLocation) {
            try {
                File musicPath = new File(musicLocation);
                if (musicPath.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                   // clip.loop(Clip.LOOP_CONTINUOUSLY); // loop the music until pressing OK
//                    JOptionPane.showMessageDialog(null,"Hit OK to pause the harmony");
//                    long clipTimePosition = clip.getMicrosecondPosition(); // pause the music
//                    clip.stop();
//
//                    JOptionPane.showMessageDialog(null,"Hit OK to resume the harmony");
//                    clip.setMicrosecondPosition(clipTimePosition); // resume the music from where we pause
//                    clip.start();
//                    JOptionPane.showMessageDialog(null,"Press OK to stop playing");
                } else {
                    System.out.println("Cant find the file");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

