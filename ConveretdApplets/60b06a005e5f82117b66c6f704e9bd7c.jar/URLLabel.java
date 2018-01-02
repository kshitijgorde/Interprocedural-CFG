import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.net.URL;
import java.applet.Applet;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class URLLabel extends Label
{
    private Applet _applet;
    private URL _url;
    private String _target;
    private Color _unvisitedURL;
    
    public URLLabel(final Applet applet, final String url, final String text) {
        this(applet, url, text, "_self");
    }
    
    public URLLabel(final Applet applet, final String url, final String text, final String target) {
        super(text);
        this._target = "";
        this.setForeground(this._unvisitedURL = Color.blue);
        try {
            this._applet = applet;
            this._url = new URL(url);
            this._target = target;
            this.addMouseListener(new Clicked());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    class Clicked extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent me) {
            URLLabel.this._applet.getAppletContext().showDocument(URLLabel.this._url, URLLabel.this._target);
        }
    }
}
