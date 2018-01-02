// 
// Decompiled by Procyon v0.5.30
// 

class jb
{
    private final esChat a;
    kb b;
    int c;
    
    jb(final esChat a) {
        this.a = a;
        this.c = 0;
        this.b = null;
    }
    
    public void a(final Object o) {
        final int m = fb.m;
        final kb b = this.b;
        if (m == 0) {
            if (b == null) {
                this.b = new kb(this.a, o, null, this.b);
                ++this.c;
                return;
            }
            final kb b2 = this.b;
        }
        kb kb = b;
        while (true) {
            while (true) {
                Label_0061: {
                    if (m == 0) {
                        break Label_0061;
                    }
                    final kb c = kb.c;
                    final kb kb2;
                    kb = kb2;
                }
                if (kb.c != null) {
                    continue;
                }
                break;
            }
            final kb kb2 = kb;
            if (m == 0) {
                kb2.c = new kb(this.a, o, kb, kb.c);
                return;
            }
            continue;
        }
    }
    
    public void a(final Object o, final int n) {
        final int m = fb.m;
        if (n == 0) {
            this.b = new kb(this.a, o, null, this.b);
            jb jb = this;
            jb jb2 = this;
            if (m == 0) {
                if (this.b.c != null) {
                    this.b.c.d = this.b;
                }
                jb = this;
                jb2 = this;
            }
            jb.c = jb2.c + 1;
            return;
        }
        kb kb = this.b;
        int n2 = 1;
        while (true) {
            Label_0091: {
                if (m == 0) {
                    break Label_0091;
                }
                kb = kb.c;
                ++n2;
            }
            if (n2 >= n || n2 >= this.c) {
                kb.c = new kb(this.a, o, kb, kb.c);
                ++this.c;
                final kb c = kb.c.c;
                if (m == 0) {
                    if (c == null) {
                        return;
                    }
                    final kb c2 = kb.c.c;
                }
                c.d = kb.c;
                return;
            }
            continue;
        }
    }
    
    public void a() {
        this.b = null;
        this.c = 0;
    }
    
    public kb a(final kb kb) {
        final int m = fb.m;
        kb kb3;
        final kb kb2 = kb3 = kb.d;
        if (m == 0) {
            if (kb2 == null) {
                final kb c = kb.c;
                if (m == 0) {
                    if (c == null) {
                        this.b = null;
                        this.c = 0;
                        return null;
                    }
                    this.b = kb.c;
                    this.b.d = null;
                    final kb b = this.b;
                }
                return c;
            }
            final kb c2;
            kb3 = (c2 = kb.c);
        }
        if (m == 0) {
            if (kb2 == null) {
                --this.c;
                kb.d.c = kb.c;
                return kb.c;
            }
            kb.d.c = kb.c;
            kb.c.d = kb.d;
            kb3 = kb.c;
        }
        return kb3;
    }
}
