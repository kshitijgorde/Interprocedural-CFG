// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.AppletContext;

public class AppletURLHandler implements URLHandler
{
    private AppletContext _ctx;
    
    public AppletURLHandler(final AppletContext ctx) {
        this._ctx = ctx;
    }
    
    private String replace(String string, final String s, final String s2) {
        for (int i = string.indexOf(s); i >= 0; i = string.indexOf(s)) {
            string = string.substring(0, i) + s2 + string.substring(i + s.length());
        }
        return string;
    }
    
    private URL decodeURL(String string) throws MalformedURLException {
        if (string.indexOf("://") == -1) {
            string = "http://" + string;
        }
        this.replace(string, " ", "%20");
        return new URL(string);
    }
    
    public void stateURL(final String s) {
        try {
            this._ctx.showStatus(this.decodeURL(s).toString());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public void openURL(final String s) {
        this.openURL(s, "_blank");
    }
    
    public void openURL(final String s, final String s2) {
        try {
            this._ctx.showDocument(this.decodeURL(s), s2);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
}
