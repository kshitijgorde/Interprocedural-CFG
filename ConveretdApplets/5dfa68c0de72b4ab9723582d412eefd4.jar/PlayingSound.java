import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

class PlayingSound
{
    LContext owner;
    ScratchSound snd;
    Sprite sprite;
    SourceDataLine line;
    int i;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    PlayingSound(final ScratchSound snd, final Sprite sprite) {
        this.snd = snd;
        this.sprite = sprite;
    }
    
    void startPlaying(final LContext owner) {
        this.owner = owner;
        this.i = 0;
        if (this.line == null) {
            this.openLine();
        }
        this.writeSomeSamples();
    }
    
    boolean isPlaying() {
        return this.line != null && this.line.getFramePosition() < this.snd.samples.length / 2;
    }
    
    void writeSomeSamples() {
        if (this.line == null) {
            return;
        }
        final int min = Math.min(this.line.available(), this.snd.samples.length - this.i);
        if (min > 0) {
            if (!this.line.isRunning()) {
                this.line.start();
            }
            this.i += this.line.write(this.snd.samples, this.i, min);
        }
    }
    
    void openLine() {
        try {
            final AudioFormat audioFormat = new AudioFormat(this.snd.rate, 16, 1, true, true);
            (this.line = (SourceDataLine)AudioSystem.getLine(new DataLine.Info((PlayingSound.class$javax$sound$sampled$SourceDataLine == null) ? (PlayingSound.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : PlayingSound.class$javax$sound$sampled$SourceDataLine, audioFormat))).open(audioFormat);
        }
        catch (LineUnavailableException ex) {
            this.line = null;
            return;
        }
        this.line.start();
    }
    
    void closeLine() {
        if (this.line != null) {
            this.line.close();
            this.line = null;
        }
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
