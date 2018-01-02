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
    
    public _zu(final String s, final String s2, final _zy zy, final PixScreen pixScreen, final double n, final double n2, final String p9, final int p10, final int d) {
        super(s, s2, zy, pixScreen, n, n2, 0, 1, 2, 3);
        this.p = p9;
        this.p = p10;
        this.d = d;
    }
    
    public final void p() {
        if (this.d == 4) {
            return;
        }
        final AppletContext appletContext = _zh.p.getAppletContext();
        _zh.p.d(null);
        _zh.p.as = null;
        super.i = 30;
        _zh.p.dr = 0;
        try {
            appletContext.showDocument(new URL(_zh.p.getCodeBase(), this.p), (this.p == 0) ? "_self" : "_blank");
        }
        catch (MalformedURLException ex) {}
    }
}
