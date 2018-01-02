import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import java.io.InputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class SoundPlayer implements Runnable
{
    private AudioInputStream stream;
    private DataLine.Info info;
    private Clip soundeffect;
    private InputStream musicfile;
    private Thread player;
    private int volumeType;
    
    public SoundPlayer(final InputStream musicfile, final int volumeType) {
        this.musicfile = musicfile;
        this.volumeType = volumeType;
        (this.player = new Thread(this)).start();
    }
    
    @Override
    public void run() {
        try {
            this.stream = AudioSystem.getAudioInputStream(this.musicfile);
            this.info = new DataLine.Info(Clip.class, this.stream.getFormat());
            (this.soundeffect = (Clip)AudioSystem.getLine(this.info)).open(this.stream);
            if (this.soundeffect.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                ((FloatControl)this.soundeffect.getControl(FloatControl.Type.MASTER_GAIN)).setValue((float)(Math.log((new double[] { 0.0, 0.25, 0.5, 0.75, 1.0 })[this.volumeType]) / Math.log(10.0) * 20.0));
            }
            this.soundeffect.start();
            while (this.soundeffect.isActive()) {
                Thread.sleep(250L);
            }
            Thread.sleep(10000L);
            this.soundeffect.close();
            this.stream.close();
            this.player.interrupt();
        }
        catch (Exception ex) {
            this.player.interrupt();
        }
    }
}
