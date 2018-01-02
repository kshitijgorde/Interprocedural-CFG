// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

public class d
{
    public e a;
    private int b;
    
    public d() {
        this.b = 0;
        this.a = null;
    }
    
    public void a(final Object o, final int n) {
        final int d = flaxchat.h.a.d;
        if (n == 0) {
            this.a = new e(o, this.a);
            ++this.b;
            return;
        }
        e e = this.a;
        int n2 = 1;
        while (true) {
            Label_0057: {
                if (d == 0) {
                    break Label_0057;
                }
                e = e.b;
                ++n2;
            }
            if (n2 >= n || n2 >= this.b) {
                e.b = new e(o, e.b);
                ++this.b;
                return;
            }
            continue;
        }
    }
    
    public e a(final e e) {
        final int d = flaxchat.h.a.d;
        if (e == this.a) {
            this.a = this.a.b;
            --this.b;
            return e;
        }
        e e2 = this.a;
        while (true) {
            Label_0049: {
                if (d == 0) {
                    break Label_0049;
                }
                e2 = e2.b;
            }
            if (e2.b == null || e2.b == e) {
                Label_0095: {
                    if (e2 == this.a) {
                        this.a.b = e.b;
                        if (d == 0) {
                            break Label_0095;
                        }
                    }
                    e2.b = e.b;
                }
                --this.b;
                return e;
            }
            continue;
        }
    }
    
    public void a() {
        this.a = null;
        this.b = 0;
    }
    
    public int b() {
        return this.b;
    }
    
    public void a(final Object o) {
        final int d = flaxchat.h.a.d;
        if (this.a == null) {
            this.a = new e(o, null);
            ++this.b;
            return;
        }
        e e = this.a;
        while (true) {
            Label_0049: {
                if (d == 0) {
                    break Label_0049;
                }
                e = e.b;
            }
            if (e.b == null) {
                e.b = new e(o, null);
                ++this.b;
                return;
            }
            continue;
        }
    }
}
