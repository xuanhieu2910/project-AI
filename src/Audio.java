/*
This class is managing all audio operations in Game, like eating, dying & clicking the button.
 */

import javax.sound.sampled.*;
import java.io.*;

public class Audio {
    Clip audio;
    Audio(String path){         //Path of audio file
        File file =  new File(path);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            audio = AudioSystem.getClip();
            audio.open(audioInputStream);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}
