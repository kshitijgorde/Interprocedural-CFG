// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Font;
import java.awt.Color;

public class pen
{
    public Object a;
    public Color b;
    public Color c;
    public Font d;
    public boolean e;
    public int f;
    
    public pen(final Color b, final Font d) {
        this.b = b;
        this.d = d;
    }
    
    public pen(final Font d, final Color b, final Color c) {
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public pen(final Font d, final Color b, final Color c, final int f) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
    }
    
    public pen(final String s, final String s2) {
        this.a(s, 0);
        this.a(s2);
    }
    
    public pen a() {
        final pen pen = new pen(this.d, this.b, this.c, this.f);
        pen.a = this.a;
        pen.e = this.e;
        return pen;
    }
    
    private void a(String s) {
        if (s == null) {
            s = "";
        }
        this.d = utils.c(s);
        if (s.endsWith("OVERSTRIKE")) {
            this.e = true;
        }
    }
    
    private void a(final String s, final int f) {
        final Color[] d = utils.d(s);
        this.b = d[0];
        this.c = d[1];
        this.f = f;
    }
}
