import sun.audio.AudioPlayer;
import java.io.InputStream;
import java.io.IOException;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioDataStream;
import sun.audio.AudioData;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class RCLoadAudio implements AudioClip
{
    private AudioData audioData;
    private AudioDataStream audioStream;
    private ContinuousAudioDataStream continuousAudioStream;
    
    public RCLoadAudio(final Object o, final String s) throws IOException {
        final InputStream resourceAsStream = o.getClass().getResourceAsStream(s);
        final AudioStream audioStream = new AudioStream(resourceAsStream);
        try {
            this.audioData = audioStream.getData();
        }
        catch (IOException ex) {
            throw ex;
        }
        finally {
            resourceAsStream.close();
            audioStream.close();
        }
    }
    
    public void play() {
        this.audioStream = new AudioDataStream(this.audioData);
        AudioPlayer.player.start(this.audioStream);
    }
    
    public void loop() {
        this.continuousAudioStream = new ContinuousAudioDataStream(this.audioData);
        AudioPlayer.player.start(this.continuousAudioStream);
    }
    
    public void stop() {
        if (this.audioStream != null) {
            AudioPlayer.player.stop(this.audioStream);
        }
        if (this.continuousAudioStream != null) {
            AudioPlayer.player.stop(this.continuousAudioStream);
        }
    }
}
