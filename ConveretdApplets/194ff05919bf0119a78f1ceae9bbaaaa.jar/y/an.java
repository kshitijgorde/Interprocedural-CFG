// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Image;

public final class an extends av
{
    private dz a;
    private u a;
    an a;
    public boolean a;
    boolean b;
    boolean j;
    Image a;
    
    public an(final String s, final an an, final boolean b) {
        this(new es(s), an, b);
    }
    
    private an(final u a, final an an, final boolean a2) {
        super((byte)0);
        this.a = this;
        this.j = true;
        this.a = y.j.a("\f\f\u0005\uffff\uffff\uff00\u0000\uff7f\u7f7f\u00ff\uffff\uffdf\udfdf\u000f~<n?{'\u007fc\u007fx\u001f~'\u007f\f\u000f\u0007p\u0007\u000f\u007f\u007fC\u001f\u000fwg~S\u007ft\u001f}g~\u001c\u000f\u0007p\u0007\u000f\u0000\u0000\u0000\u0000\u0000\u0000\b\u0000\u0004\u0000\u0001 \u0000\b\u0000a0`\u0003\u0000\u0000");
        if (an != null) {
            this.a = an.a;
            an.a = this;
        }
        this.a = new dz(this);
        this.a = a;
        this.a((u)(this.a = a2), 18, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0);
        this.a(this.a, 17, 2, 2, 1, 1, 1, 0, 0, 2, 0, 0);
    }
    
    public an(final String s) {
        this(s, null, false);
    }
    
    public final void a(final boolean b) {
        if (b != this.a) {
            this.a();
        }
    }
    
    private void a() {
        this.l();
        if (this.a) {
            for (an an = this.a; an != this; an = an.a) {
                if (an.a) {
                    an.l();
                }
            }
        }
        this.a.h();
    }
    
    private void l() {
        this.a = !this.a;
        this.h();
    }
    
    public final void c(final boolean j) {
        this.j = j;
        this.h();
    }
    
    public final void a(final ei ei) {
        super.a(ei);
        if (!this.j) {
            ei.a(this.a());
            bj.c(ei, this.a.a(this), this.a.b(this), this.a.c(), this.a.d());
        }
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        if (this.j) {
            this.b = true;
            this.a.h();
        }
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        if (this.b) {
            this.b = false;
            if (n >= 0 && n2 >= 0 && n < super.e && n2 < super.f && (!this.a || this.a == this)) {
                this.a();
                this.a(new Event(this, 1001, new Boolean(this.a)));
            }
            this.a.h();
        }
        return true;
    }
}
