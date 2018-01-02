// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;

public class bH extends r
{
    private u b;
    
    public void a() {
        this.b.o(new cD(67840, 0));
    }
    
    public bH(final Frame frame, final u b) {
        super(frame, am.a(ao.e("%1 Server Options"), new String[] { z.G }), b);
        this.b = b;
        if (b.d(62)) {
            this.a(new bG(b));
        }
        if (b.d(53)) {
            this.a(new bE(b, true));
        }
        if (b.d(49)) {
            this.a(new bq(b));
        }
        if (b.d(62)) {
            this.a(new bD(b));
        }
        if (this.b() > 5) {
            this.a(2);
        }
    }
}
