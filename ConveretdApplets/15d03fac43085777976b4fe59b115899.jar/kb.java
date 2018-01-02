// 
// Decompiled by Procyon v0.5.30
// 

class kb
{
    private final esChat a;
    lb b;
    int c;
    
    kb(final esChat a) {
        this.a = a;
        this.c = 0;
        this.b = null;
    }
    
    public void a(final Object o) {
        final boolean r = d.r;
        final lb b = this.b;
        if (!r) {
            if (b == null) {
                this.b = new lb(this.a, o, null, this.b);
                ++this.c;
                return;
            }
            final lb b2 = this.b;
        }
        lb lb = b;
        while (true) {
            while (true) {
                Label_0061: {
                    if (!r) {
                        break Label_0061;
                    }
                    final lb c = lb.c;
                    final lb lb2;
                    lb = lb2;
                }
                if (lb.c != null) {
                    continue;
                }
                break;
            }
            final lb lb2 = lb;
            if (!r) {
                lb2.c = new lb(this.a, o, lb, lb.c);
                return;
            }
            continue;
        }
    }
    
    public void a(final Object o, final int n) {
        final boolean r = d.r;
        if (n == 0) {
            this.b = new lb(this.a, o, null, this.b);
            kb kb = this;
            kb kb2 = this;
            if (!r) {
                if (this.b.c != null) {
                    this.b.c.d = this.b;
                }
                kb = this;
                kb2 = this;
            }
            kb.c = kb2.c + 1;
            return;
        }
        lb lb = this.b;
        int n2 = 1;
        while (true) {
            Label_0091: {
                if (!r) {
                    break Label_0091;
                }
                lb = lb.c;
                ++n2;
            }
            if (n2 >= n || n2 >= this.c) {
                lb.c = new lb(this.a, o, lb, lb.c);
                ++this.c;
                final lb c = lb.c.c;
                if (!r) {
                    if (c == null) {
                        return;
                    }
                    final lb c2 = lb.c.c;
                }
                c.d = lb.c;
                return;
            }
            continue;
        }
    }
    
    public void a() {
        this.b = null;
        this.c = 0;
    }
    
    public lb a(final lb lb) {
        final boolean r = d.r;
        lb lb3;
        final lb lb2 = lb3 = lb.d;
        if (!r) {
            if (lb2 == null) {
                final lb c = lb.c;
                if (!r) {
                    if (c == null) {
                        this.b = null;
                        this.c = 0;
                        return null;
                    }
                    this.b = lb.c;
                    this.b.d = null;
                    final lb b = this.b;
                }
                return c;
            }
            final lb c2;
            lb3 = (c2 = lb.c);
        }
        if (!r) {
            if (lb2 == null) {
                --this.c;
                lb.d.c = lb.c;
                return lb.c;
            }
            lb.d.c = lb.c;
            lb.c.d = lb.d;
            lb3 = lb.c;
        }
        return lb3;
    }
}
