import java.net.URL;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class AudioThread2 extends Thread
{
    Applet ap;
    AudioClip au;
    boolean loaded;
    boolean auto_play;
    URL url;
    
    AudioThread2(final Applet applet, final URL url) {
        this(applet, url, false);
    }
    
    AudioThread2(final Applet ap, final URL url, final boolean auto_play) {
        this.loaded = false;
        this.auto_play = false;
        this.ap = ap;
        this.url = url;
        this.auto_play = auto_play;
        this.start();
    }
    
    public void run() {
        this.au = this.ap.getAudioClip(this.url);
        if (this.au != null) {
            if (!this.auto_play) {
                this.au.play();
                this.au.stop();
            }
            else {
                this.au.play();
            }
        }
        this.loaded = true;
    }
    
    public void playAudio() {
        if (this.au != null && this.loaded) {
            this.au.play();
        }
    }
    
    public void stopAudio() {
        if (this.au != null && this.loaded) {
            this.au.stop();
        }
    }
    
    public void loopAudio() {
        if (this.au != null && this.loaded) {
            this.au.loop();
        }
    }
    
    public boolean isLoaded() {
        return this.loaded;
    }
}
