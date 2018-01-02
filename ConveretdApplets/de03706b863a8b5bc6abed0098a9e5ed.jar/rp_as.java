import javax.swing.JComponent;
import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_as implements rp_dX
{
    private HashMap a;
    private static Object a;
    private static Object b;
    
    public rp_as() {
        this.a = new HashMap(2);
    }
    
    public final void a(final int n, final String s) {
        this.a.put(new Integer(n), (s != null) ? s : rp_as.a);
    }
    
    public final void a(final int n, final URL url) {
        this.a.put(new Integer(n), (url != null) ? url : rp_as.a);
    }
    
    public final Image a(final int n) {
        final Image value;
        if ((value = this.a.get(new Integer(n))) instanceof Image) {
            return value;
        }
        return null;
    }
    
    public final boolean a(final int n) {
        return this.a.get(new Integer(n)) == rp_as.b;
    }
    
    public final boolean b(final int n) {
        return this.a.get(new Integer(n)) == rp_as.a;
    }
    
    public final URL a(final int n, final rp_fK rp_fK) {
        final String value;
        if ((value = this.a.get(new Integer(n))) instanceof URL) {
            return (URL)value;
        }
        if (value == rp_as.a) {
            return null;
        }
        if (value == rp_as.b) {
            return null;
        }
        if (rp_fK != null && value instanceof String) {
            final URL a = rp_fK.a((String)value);
            this.a(n, a);
            return a;
        }
        return null;
    }
    
    public final void a(final int n, final rp_fK rp_fK, final JComponent component) {
        final URL a;
        if ((a = this.a(n, rp_fK)) != null) {
            this.a.put(new Integer(n), rp_as.b);
            new rp_gb(rp_fK, a, this, n, component).c();
        }
    }
    
    public final void a(final Image image, final int n) {
        (this = this).a.put(new Integer(n), (image != null) ? image : rp_as.a);
    }
    
    static {
        rp_as.a = new Object();
        rp_as.b = new Object();
    }
}
