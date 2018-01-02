// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

public class AppletImageLoader implements ImageLoader
{
    private Applet _app;
    
    public AppletImageLoader(final Applet app) {
        this._app = app;
    }
    
    public Image getImage(final String s) {
        try {
            return this._app.getImage(new URL(this._app.getCodeBase(), s));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
}
