// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player.advanced;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import java.io.File;

public class jlap
{
    public static void main(final String[] array) {
        final jlap jlap = new jlap();
        if (array.length != 1) {
            jlap.showUsage();
            System.exit(0);
        }
        else {
            try {
                jlap.play(array[0]);
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
                System.exit(0);
            }
        }
    }
    
    public void play(final String s) throws JavaLayerException, IOException {
        playMp3(new File(s), new InfoListener());
    }
    
    public void showUsage() {
        System.out.println("Usage: jla <filename>");
        System.out.println("");
        System.out.println(" e.g. : java javazoom.jl.player.advanced.jlap localfile.mp3");
    }
    
    public static AdvancedPlayer playMp3(final File file, final PlaybackListener playbackListener) throws IOException, JavaLayerException {
        return playMp3(file, 0, Integer.MAX_VALUE, playbackListener);
    }
    
    public static AdvancedPlayer playMp3(final File file, final int n, final int n2, final PlaybackListener playbackListener) throws IOException, JavaLayerException {
        return playMp3(new BufferedInputStream(new FileInputStream(file)), n, n2, playbackListener);
    }
    
    public static AdvancedPlayer playMp3(final InputStream inputStream, final int n, final int n2, final PlaybackListener playBackListener) throws JavaLayerException {
        final AdvancedPlayer advancedPlayer = new AdvancedPlayer(inputStream);
        advancedPlayer.setPlayBackListener(playBackListener);
        new Thread() {
            public void run() {
                try {
                    advancedPlayer.play(n, n2);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            }
        }.start();
        return advancedPlayer;
    }
    
    public class InfoListener extends PlaybackListener
    {
        public void playbackStarted(final PlaybackEvent playbackEvent) {
            System.out.println("Play started from frame " + playbackEvent.getFrame());
        }
        
        public void playbackFinished(final PlaybackEvent playbackEvent) {
            System.out.println("Play completed at frame " + playbackEvent.getFrame());
            System.exit(0);
        }
    }
}
