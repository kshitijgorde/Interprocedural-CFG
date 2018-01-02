// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

public final class ba extends bx
{
    private bj a;
    private bj b;
    private fb a;
    private es a;
    public Object a;
    
    public ba(final ak ak, final av av, final String s, final fb fb) {
        this(ak, av, s, fb, null);
    }
    
    public ba(final ak ak, final av av, final String s, final fb fb, final Object o) {
        this(ak.a(1716518977), ak.a(1716518978), av, s, fb, o);
    }
    
    private ba(final String s, final String s2, av av, final String s3, final fb a, final Object a2) {
        super(av);
        this.a = a;
        this.a = a2;
        this.a(this.a = new es(s3), 10, 2, 0, 1, 1, 0, 0, 16, 0, 0, 0);
        av = new av(1);
        this.a(av, 10, 0, 0, 1, 1, 0, 1, 0, 0, 16, 0);
        av.a(this.a = new bj(s), 0, 0, 2);
        av.a(this.b = new bj(s2), 1, 0, 2);
        this.a();
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a.a(this);
        }
        else {
            if (event.target != this.b) {
                return super.a(event, o);
            }
            this.a.b(this);
        }
        this.l();
        return true;
    }
}
