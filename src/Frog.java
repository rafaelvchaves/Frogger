import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by michael_hopps on 2/13/17.
 */
public class Frog extends Sprite {

    public Frog(int x, int y){
        super(x, y, NORTH);
        setPic("frog1.png", NORTH);
        setSpeed(30);
    }

    @Override
    public void update() {
        super.update();


        //This makes a hopping sound when the frog is told to move.
        try {
            // open the sound file as a Java input stream
            String hop = "res/hop.wav";
            InputStream in = new FileInputStream(hop);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error loading sound file.");
        }

    }
}
