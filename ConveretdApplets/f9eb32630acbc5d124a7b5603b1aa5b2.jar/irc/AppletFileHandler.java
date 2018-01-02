// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.net.URL;
import java.io.InputStream;
import java.applet.Applet;

public class AppletFileHandler implements FileHandler
{
    private Applet _app;
    
    public AppletFileHandler(final Applet app) {
        this._app = app;
    }
    
    public InputStream getInputStream(final String s) {
        try {
            return new URL(this._app.getCodeBase(), s).openStream();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
