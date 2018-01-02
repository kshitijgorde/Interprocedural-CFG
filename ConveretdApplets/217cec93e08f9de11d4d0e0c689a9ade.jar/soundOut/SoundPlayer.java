// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioDataStream;
import sun.audio.AudioData;
import java.io.InputStream;

public class SoundPlayer
{
    private InputStream soundStream;
    private boolean mute;
    private byte[] sound;
    private double volume;
    private double[] yVec;
    
    public SoundPlayer() {
        this(100, false);
    }
    
    public SoundPlayer(final int n, final boolean b) {
        this.mute = true;
        this.sound = null;
        this.volume = 1.0;
        this.sound = new byte[n];
        if (b) {
            this.soundStream = new AudioDataStream(new AudioData(this.sound));
        }
        else {
            this.soundStream = new ContinuousAudioDataStream(new AudioData(this.sound));
        }
    }
    
    public void setYVec(final double[] yVec) {
        this.yVec = yVec;
        this.calcSound();
    }
    
    public void closeStream() {
        try {
            this.soundStream.close();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void calcSound() {
        if (!this.mute) {
            AudioPlayer.player.stop(this.soundStream);
        }
        final int length = this.yVec.length;
        if (this.sound == null || length != this.sound.length) {
            this.sound = new byte[length];
            this.soundStream = new ContinuousAudioDataStream(new AudioData(this.sound));
        }
        for (int i = 0; i < length; ++i) {
            this.sound[i] = int2ulaw((int)(this.volume * 8160 * this.yVec[i]));
        }
        if (!this.mute) {
            AudioPlayer.player.start(this.soundStream);
        }
    }
    
    public void setVolume(final double volume) {
        this.volume = volume;
        this.calcSound();
    }
    
    public double getVolume() {
        return this.volume;
    }
    
    public void setMute(final boolean mute) {
        this.mute = mute;
        if (this.mute) {
            AudioPlayer.player.stop(this.soundStream);
        }
        else {
            AudioPlayer.player.start(this.soundStream);
        }
    }
    
    public static byte int2ulaw(int n) {
        int n2;
        if (n < 0) {
            n = -n;
            n2 = 127;
        }
        else {
            n2 = 255;
        }
        if (n < 32) {
            n = (0xF0 | 15 - n / 2);
        }
        else if (n < 96) {
            n = (0xE0 | 15 - (n - 32) / 4);
        }
        else if (n < 224) {
            n = (0xD0 | 15 - (n - 96) / 8);
        }
        else if (n < 480) {
            n = (0xC0 | 15 - (n - 224) / 16);
        }
        else if (n < 992) {
            n = (0xB0 | 15 - (n - 480) / 32);
        }
        else if (n < 2016) {
            n = (0xA0 | 15 - (n - 992) / 64);
        }
        else if (n < 4064) {
            n = (0x90 | 15 - (n - 2016) / 128);
        }
        else if (n < 8160) {
            n = (0x80 | 15 - (n - 4064) / 256);
        }
        else {
            n = 128;
        }
        return (byte)(n2 & n);
    }
}
