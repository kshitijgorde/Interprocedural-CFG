// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.util.e;
import java.awt.Component;
import ji.util.d;
import java.awt.Rectangle;
import ji.font.ct;
import ji.image.cy;
import java.awt.Font;

public class og
{
    static Font[] a;
    boolean b;
    boolean c;
    String d;
    boolean e;
    
    public og() {
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = false;
    }
    
    public Font a(final cy cy, final String s, final Font font, final String s2, final ct ct, final Rectangle rectangle, final String s3) {
        try {
            final oh oh = new oh(ji.util.d.b((Component)cy), s, font, ct, rectangle, cy, s3);
            oh.a(font);
            oh.setVisible(true);
            final Font b = oh.b();
            this.b = oh.d();
            this.d = oh.c();
            this.e = oh.g();
            if (ji.util.e.av()) {
                this.c = oh.e();
            }
            return b;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean a() {
        return this.e;
    }
    
    public boolean b() {
        return this.b;
    }
    
    public boolean c() {
        return this.c;
    }
    
    public String d() {
        return this.d;
    }
    
    static {
        og.a = null;
    }
}
