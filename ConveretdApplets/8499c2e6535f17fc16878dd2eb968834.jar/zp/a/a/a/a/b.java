// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.a;

import java.net.URL;
import java.applet.AppletContext;

public class b
{
    public float if;
    public float try;
    public float byte;
    public float new;
    public String for;
    public String do;
    public String int;
    public boolean a;
    
    public b() {
        final float n = 0.0f;
        this.new = n;
        this.byte = n;
        this.try = n;
        this.if = n;
        final String for1 = null;
        this.int = for1;
        this.do = for1;
        this.for = for1;
    }
    
    public boolean a(final float n, final float n2, final int n3, final int n4, final int n5, final int n6, final AppletContext appletContext) {
        boolean b = false;
        final int n7 = (n3 - n5) * (n3 - n5) + (n4 - n6) * (n4 - n6);
        if (this.a) {
            if (this.byte > this.if) {
                if ((n > this.if && n < this.byte && n2 > this.try && n2 < this.new) || n7 <= 144) {
                    b = true;
                }
            }
            else if (((n < this.byte || n > this.if) && n2 > this.try && n2 < this.new) || n7 <= 144) {
                b = true;
            }
        }
        if (b) {
            if (this.do != null) {
                appletContext.showStatus(this.do);
            }
            else {
                appletContext.showStatus(this.for);
            }
        }
        return b;
    }
    
    public boolean a(final float n, final float n2, final int n3, final int n4, final int n5, final int n6, final AppletContext appletContext, final URL url) {
        final int n7 = (n3 - n5) * (n3 - n5) + (n4 - n6) * (n4 - n6);
        try {
            if (this.byte > this.if) {
                if ((n > this.if && n < this.byte && n2 > this.try && n2 < this.new) || n7 <= 144) {
                    if (this.int == null) {
                        appletContext.showDocument(new URL(url, this.for));
                    }
                    else {
                        appletContext.showDocument(new URL(url, this.for), this.int);
                    }
                    return true;
                }
            }
            else if (((n < this.byte || n > this.if) && n2 > this.try && n2 < this.new) || n7 <= 144) {
                if (this.int == null) {
                    appletContext.showDocument(new URL(url, this.for));
                }
                else {
                    appletContext.showDocument(new URL(url, this.for), this.int);
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
}
