// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;
import java.awt.Image;

public final class t extends av
{
    int a;
    private bh a;
    private av b;
    es a;
    String a;
    
    public t(final u u, final dm dm, final String a) {
        super(2);
        this.a = 0;
        this.a = new bh(null, 300, 200, true);
        this.b = new av((byte)0);
        this.a = new es("", 0);
        this.a(u, 0);
        this.a(this.b, 1);
        this.b.a(new es(dm.a(1716523313)), 15, 0, 3, 1, 1, 0, 0);
        this.b.a(this.a, 1, 0, 1, 0, 0);
        this.b.b(Color.white);
        this.b.a(this.a, 15, 2, 3, 2, 1, 0, 2);
        this.a = a;
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        return false;
    }
}
