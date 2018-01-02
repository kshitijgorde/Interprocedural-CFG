import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a3 extends c
{
    blaze3d a;
    Vector b;
    
    a3(final blaze3d a) {
        this.b = new Vector();
        this.a = a;
    }
    
    void a(final int n) {
        int n2 = 0;
        while (true) {
            Label_0065: {
                if (!c.l) {
                    break Label_0065;
                }
                final c c = this.b.elementAt(n2);
                if ((n & 0x1) != 0x0) {
                    c.a("onMouseMove", null);
                }
                if ((n & 0x2) != 0x0) {
                    c.a("onMouseDown", null);
                }
                if ((n & 0x4) != 0x0) {
                    c.a("onMouseUp", null);
                }
                ++n2;
            }
            if (n2 >= this.b.size()) {
                return;
            }
            continue;
        }
    }
}
