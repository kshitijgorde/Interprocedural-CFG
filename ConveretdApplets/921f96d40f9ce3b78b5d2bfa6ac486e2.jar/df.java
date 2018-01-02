import java.applet.AudioClip;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class df
{
    private static df p;
    
    public static final Image p(final Applet applet, final String s) {
        final URL resource = df.p.getClass().getResource(s);
        if (resource != null) {
            return applet.getImage(resource);
        }
        return applet.getImage(applet.getCodeBase(), s);
    }
    
    public static final AudioClip p(final Applet applet, final String s) {
        final URL resource = df.p.getClass().getResource(s);
        if (resource != null) {
            return applet.getAudioClip(resource);
        }
        return applet.getAudioClip(applet.getCodeBase(), s);
    }
    
    static {
        df.p = new df();
    }
}
