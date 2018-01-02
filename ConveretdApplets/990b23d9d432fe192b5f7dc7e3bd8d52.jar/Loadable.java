import java.awt.Image;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public interface Loadable
{
    AudioClip loadAudio(final String p0);
    
    Image loadImage(final String p0);
    
    void repaint();
    
    void startUp();
}
