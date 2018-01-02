// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.io.InputStream;
import sun.audio.AudioPlayer;
import java.io.IOException;
import irc.EIRC;
import sun.audio.AudioStream;

public class ThreadPlayer extends Thread
{
    int i;
    AudioStream audio;
    
    public ThreadPlayer(final int i) {
        this.i = i;
        this.setPriority(1);
        this.start();
    }
    
    @Override
    public void run() {
        final String conf = Resources.getConf("sound." + this.i);
        try {
            this.audio = new AudioStream(EIRC.class.getResourceAsStream("gui/sfx/" + conf));
        }
        catch (IOException ex) {}
        AudioPlayer.player.start(this.audio);
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex2) {}
        AudioPlayer.player.stop(this.audio);
    }
}
