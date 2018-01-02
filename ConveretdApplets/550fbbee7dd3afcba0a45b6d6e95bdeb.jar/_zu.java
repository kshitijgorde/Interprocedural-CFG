import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zu extends _zh
{
    public String p;
    private int p;
    private int d;
    
    public _zu(final String s, final String s2, final _zs zs, final PixScreen pixScreen, final double n, final double n2, final String p9, final int p10, final int d) {
        super(s, s2, zs, pixScreen, n, n2, 1, 2, 0);
        this.p = p9;
        this.p = p10;
        this.d = d;
    }
    
    public final void n() {
        if (this.d == 4) {
            return;
        }
        final AppletContext appletContext = _zh.p.getAppletContext();
        try {
            appletContext.showDocument(new URL(_zh.p.getCodeBase(), this.p), (this.p == 0) ? "_self" : "_blank");
        }
        catch (MalformedURLException ex) {}
    }
}
