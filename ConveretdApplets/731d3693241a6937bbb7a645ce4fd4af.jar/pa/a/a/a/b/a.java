// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.b;

import java.net.URL;
import java.applet.AppletContext;

public class a
{
    public float byte;
    public float new;
    public float a;
    public float if;
    public String do;
    public String try;
    public String int;
    public boolean for;
    
    public a() {
        final float n = 0.0f;
        this.if = n;
        this.a = n;
        this.new = n;
        this.byte = n;
        final String do1 = null;
        this.int = do1;
        this.try = do1;
        this.do = do1;
    }
    
    public boolean a(final float n, final float n2, final int n3, final int n4, final int n5, final int n6, final AppletContext appletContext) {
        boolean b = false;
        final int n7 = (n3 - n5) * (n3 - n5) + (n4 - n6) * (n4 - n6);
        if (this.for) {
            if (this.a > this.byte) {
                if ((n > this.byte && n < this.a && n2 > this.new && n2 < this.if) || n7 <= 144) {
                    b = true;
                }
            }
            else if (((n < this.a || n > this.byte) && n2 > this.new && n2 < this.if) || n7 <= 144) {
                b = true;
            }
        }
        if (b) {
            if (this.try != null) {
                appletContext.showStatus(this.try);
            }
            else {
                appletContext.showStatus(this.do);
            }
        }
        return b;
    }
    
    public boolean a(final float n, final float n2, final int n3, final int n4, final int n5, final int n6, final AppletContext appletContext, final URL url) {
        final int n7 = (n3 - n5) * (n3 - n5) + (n4 - n6) * (n4 - n6);
        try {
            if (this.a > this.byte) {
                if ((n > this.byte && n < this.a && n2 > this.new && n2 < this.if) || n7 <= 144) {
                    if (this.int == null) {
                        appletContext.showDocument(new URL(url, this.do));
                    }
                    else {
                        appletContext.showDocument(new URL(url, this.do), this.int);
                    }
                    return true;
                }
            }
            else if (((n < this.a || n > this.byte) && n2 > this.new && n2 < this.if) || n7 <= 144) {
                if (this.int == null) {
                    appletContext.showDocument(new URL(url, this.do));
                }
                else {
                    appletContext.showDocument(new URL(url, this.do), this.int);
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
}
