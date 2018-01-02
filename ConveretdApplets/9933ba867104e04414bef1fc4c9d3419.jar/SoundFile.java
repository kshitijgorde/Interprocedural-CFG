import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class SoundFile extends Thread
{
    private static final int EXTERNAL_BUFFER_SIZE = 128000;
    private String filename;
    private boolean isURL;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public SoundFile(final String filename, final boolean isURL) {
        this.filename = filename;
        this.isURL = isURL;
    }
    
    public void run() {
        AudioInputStream audioInputStream;
        try {
            if (this.isURL) {
                audioInputStream = AudioSystem.getAudioInputStream(new URL(this.filename));
            }
            else {
                System.out.println(this.filename);
                audioInputStream = AudioSystem.getAudioInputStream(new File(this.filename));
            }
        }
        catch (Exception ex2) {
            return;
        }
        final AudioFormat format = audioInputStream.getFormat();
        SourceDataLine sourceDataLine = null;
        final DataLine.Info info = new DataLine.Info((SoundFile.class$javax$sound$sampled$SourceDataLine == null) ? (SoundFile.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : SoundFile.class$javax$sound$sampled$SourceDataLine, format);
        try {
            sourceDataLine = (SourceDataLine)AudioSystem.getLine(info);
            sourceDataLine.open(format);
        }
        catch (Exception ex3) {}
        sourceDataLine.start();
        int i = 0;
        final byte[] array = new byte[128000];
        while (i != -1) {
            try {
                i = audioInputStream.read(array, 0, array.length);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (i >= 0) {
                sourceDataLine.write(array, 0, i);
            }
        }
        sourceDataLine.drain();
        sourceDataLine.close();
    }
    
    public static void play(final String s, final boolean b) {
        new SoundFile(s, b).start();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
