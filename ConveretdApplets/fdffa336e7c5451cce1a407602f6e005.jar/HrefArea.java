import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class HrefArea extends ImageMapArea
{
    URL anchor;
    
    public void handleArg(final String s) {
        try {
            this.anchor = new URL(super.parent.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            this.anchor = null;
        }
    }
    
    public void highlight(final Graphics graphics, final boolean b) {
        super.highlight(graphics, b);
        this.showStatus((b && this.anchor != null) ? ("Go To " + this.anchor.toExternalForm()) : null);
    }
    
    public void lift(final int n, final int n2) {
        if (this.inside(n, n2) && this.anchor != null) {
            this.showDocument(this.anchor);
        }
    }
}
