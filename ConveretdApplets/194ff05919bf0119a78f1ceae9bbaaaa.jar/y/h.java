// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.util.Hashtable;

final class h extends av
{
    private bj a;
    private bj b;
    private bj c;
    private c a;
    private dm a;
    private bf a;
    private static int[] a;
    private static int[] b;
    
    h(final dm a, final bf a2) {
        super((byte)0);
        this.a = new c();
        new Hashtable();
        this.a = a;
        this.a = a2;
        this.a = new bj(a.a(1716524594));
        this.b = new bj(a.a(1716524595));
        this.c = new bj(a.a(1716524593));
        for (int i = 0; i < h.a.length; ++i) {
            this.a.a(a.a(h.a[i]));
        }
        this.a(new es(a.a(1716524596)), 11, 0, 1, 1, 1, 0, 0);
        this.a(this.a, 17, 0, 0, 1, 1, 0, 1);
        this.a(this.a, 17, 15, 3, 1, 1, 0, 2, 32, 0, 0, 0);
        final av av;
        (av = new av(1)).a(this.b, 0, 0, 0);
        av.a(this.c, 1, 0, 0);
        this.a(av, 17, 0, 0, 1, 1, 0, 3);
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.c) {
            this.a.a(this.a.a, h.b[this.a.a]);
        }
        else if (event.target == this.b) {
            this.a.b(this.a.a, h.b[this.a.a]);
        }
        else {
            if (event.target != this.a) {
                return false;
            }
            this.a.a(this.a.a, 10);
        }
        return true;
    }
    
    static {
        h.a = new int[] { 1716524587, 1716524592, 1716524588, 1716524589, 1716524591, 1716524586, 1716524590, 1716524585 };
        h.b = new int[] { 60, 300, 600, 900, 1800, 6000, 10800, 86400 };
    }
}
