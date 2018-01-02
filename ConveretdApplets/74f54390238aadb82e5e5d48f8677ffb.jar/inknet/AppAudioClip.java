// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import sun.audio.AudioDataStream;
import sun.audio.AudioData;
import java.applet.AudioClip;

public class AppAudioClip implements AudioClip
{
    private AudioData m_data;
    private AudioDataStream m_str;
    
    public AppAudioClip(final String strFile) {
        this.m_data = null;
        this.m_str = null;
        try {
            final FileInputStream is = new FileInputStream(strFile);
            final AudioStream as = new AudioStream(is);
            this.m_data = as.getData();
            as.close();
        }
        catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public void loop() {
        this.m_str = new ContinuousAudioDataStream(this.m_data);
        AudioPlayer.player.start(this.m_str);
    }
    
    public void play() {
        this.m_str = new AudioDataStream(this.m_data);
        AudioPlayer.player.start(this.m_str);
    }
    
    public void stop() {
        if (this.m_str != null) {
            AudioPlayer.player.stop(this.m_str);
        }
    }
}
