// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import java.io.InputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Clip;

public class PSound2 extends PSound
{
    Clip clip;
    FloatControl gainControl;
    static /* synthetic */ Class class$javax$sound$sampled$Clip;
    static /* synthetic */ Class class$processing$core$PSound;
    
    public void play() {
        this.clip.start();
    }
    
    public void loop() {
        this.clip.loop(-1);
    }
    
    public void noLoop() {
        this.clip.loop(0);
    }
    
    public void pause() {
        this.clip.stop();
    }
    
    public void stop() {
        this.clip.stop();
        this.clip.setFramePosition(0);
    }
    
    public void dispose() {
        this.stop();
        this.clip = null;
    }
    
    public float time() {
        return (float)(this.clip.getMicrosecondPosition() / 1000000.0);
    }
    
    public float duration() {
        return this.clip.getBufferSize() / (this.clip.getFormat().getFrameSize() * this.clip.getFormat().getFrameRate());
    }
    
    public void volume(final float n) {
        this.gainControl.setValue((float)(Math.log(n) / Math.log(10.0) * 20.0));
    }
    
    protected void error(final String s, final Exception ex) {
        this.parent.die("Error inside PSound2." + s + "()", ex);
    }
    
    static /* synthetic */ Class class(final String s, final boolean b) {
        try {
            final Class<?> forName = Class.forName(s);
            if (!b) {
                forName.getComponentType();
            }
            return forName;
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public PSound2(final PApplet parent, final InputStream inputStream) {
        this.parent = parent;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            AudioFormat format = audioInputStream.getFormat();
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2, format.getFrameRate(), true);
                audioInputStream = AudioSystem.getAudioInputStream(format, audioInputStream);
            }
            final int n = (int)audioInputStream.getFrameLength();
            final int frameSize = format.getFrameSize();
            Class class$javax$sound$sampled$Clip;
            if ((class$javax$sound$sampled$Clip = PSound2.class$javax$sound$sampled$Clip) == null) {
                class$javax$sound$sampled$Clip = (PSound2.class$javax$sound$sampled$Clip = class("[Ljavax.sound.sampled.Clip;", false));
            }
            this.clip = (Clip)AudioSystem.getLine(new DataLine.Info(class$javax$sound$sampled$Clip, audioInputStream.getFormat(), n * frameSize));
            this.gainControl = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.clip.open(audioInputStream);
            this.parent.registerDispose(this);
            try {
                final Class<? extends PApplet> class1 = this.parent.getClass();
                final String s = "soundEvent";
                final Class[] array = { null };
                final int n2 = 0;
                Class class$processing$core$PSound;
                if ((class$processing$core$PSound = PSound2.class$processing$core$PSound) == null) {
                    class$processing$core$PSound = (PSound2.class$processing$core$PSound = class("[Lprocessing.core.PSound;", false));
                }
                array[n2] = class$processing$core$PSound;
                this.soundEventMethod = class1.getMethod(s, (Class[])array);
                this.clip.addLineListener(new LineListener() {
                    public final void update(final LineEvent lineEvent) {
                        if (lineEvent.getType() == LineEvent.Type.STOP) {
                            try {
                                PSound2.this.soundEventMethod.invoke(PSound2.this.parent, PSound2.this);
                            }
                            catch (Exception ex) {
                                System.err.println("error, disabling soundEvent()");
                                ex.printStackTrace();
                                PSound2.this.soundEventMethod = null;
                            }
                        }
                    }
                });
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex) {
            this.error("<init>", ex);
        }
    }
}
