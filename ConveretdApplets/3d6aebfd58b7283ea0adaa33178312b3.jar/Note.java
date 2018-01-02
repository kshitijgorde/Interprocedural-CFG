import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class Note
{
    private AudioClip tone;
    private boolean playing;
    
    public Note(final AudioClip tone) {
        this.playing = false;
        this.tone = tone;
    }
    
    public boolean isPlaying() {
        return this.playing;
    }
    
    public void play() {
        this.tone.loop();
        this.playing = true;
    }
    
    public void stop() {
        this.tone.stop();
        this.playing = false;
    }
}
