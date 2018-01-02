// 
// Decompiled by Procyon v0.5.30
// 

public class at
{
    private int a;
    int b;
    int c;
    aw[] d;
    int e;
    
    at() {
        this.a = -1;
        this.b = 0;
        this.d = new aw[10];
        this.e = 10;
        this.c = 8;
    }
    
    c a(final as as, final boolean b) {
        final int c = as.c;
        aw f = this.d[(c & Integer.MAX_VALUE) % this.e];
        while (true) {
            Label_0129: {
                if (!c.l) {
                    break Label_0129;
                }
                if (b) {
                    if (!f.d) {
                        f.c = f.b.toLowerCase();
                        f.d = true;
                    }
                    if (f.a == c && f.c.equals(as.b)) {
                        return f.e;
                    }
                }
                else if (f.a == c && f.b.equals(as.a)) {
                    return f.e;
                }
                f = f.f;
            }
            if (f == null) {
                return null;
            }
            continue;
        }
    }
    
    void a(final as as, final c e) {
        final boolean l = c.l;
        final int c = as.c;
        int n = (c & Integer.MAX_VALUE) % this.e;
        aw f = this.d[n];
        while (true) {
            while (true) {
                Label_0073: {
                    if (!l) {
                        break Label_0073;
                    }
                    final int a = f.a;
                    final int b;
                    final int c2;
                    if (b == c2 && f.b.equals(as.a)) {
                        f.e = e;
                        return;
                    }
                    f = f.f;
                }
                if (f != null) {
                    continue;
                }
                break;
            }
            final int b = this.b;
            final int c2 = this.c;
            if (!l) {
                if (b > c2) {
                    this.c();
                    n = (c & Integer.MAX_VALUE) % this.e;
                }
                this.d[n] = new aw(this, c, as.a, e, this.d[n]);
                ++this.b;
                return;
            }
            continue;
        }
    }
    
    void a(final as as) {
        final int c = as.c;
        final int n = (c & Integer.MAX_VALUE) % this.e;
        aw aw = null;
        for (aw f = this.d[n]; f != null; f = f.f) {
            if (f.a == c && f.b.equals(as.a)) {
                Label_0085: {
                    if (aw != null) {
                        aw.f = f.f;
                        if (!c.l) {
                            break Label_0085;
                        }
                    }
                    this.d[n] = f.f;
                }
                f.e = null;
                --this.b;
            }
            aw = f;
        }
    }
    
    void a() {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            while (true) {
                Label_0020: {
                    if (!l) {
                        break Label_0020;
                    }
                    this.d[n] = null;
                    ++n;
                }
                if (n < this.e) {
                    continue;
                }
                break;
            }
            this.b = 0;
            this.a = -1;
            if (!l) {
                return;
            }
            continue;
        }
    }
    
    private void c() {
        final boolean l = c.l;
        final int e = this.e;
        final int e2 = e * 2 + 1;
        final aw[] d = this.d;
        this.d = new aw[e2];
        this.e = e2;
        int n = e;
        while (true) {
            Label_0102: {
                if (!l) {
                    break Label_0102;
                }
                aw f = d[n];
                while (true) {
                    Label_0097: {
                        if (!l) {
                            break Label_0097;
                        }
                        final aw aw = f;
                        f = f.f;
                        final int n2 = (aw.a & Integer.MAX_VALUE) % e2;
                        aw.f = this.d[n2];
                        this.d[n2] = aw;
                    }
                    if (f != null) {
                        continue;
                    }
                    break;
                }
            }
            if (n-- <= 0) {
                this.c = (int)(e2 * 0.75f);
                return;
            }
            continue;
        }
    }
    
    String b() {
        final boolean l = c.l;
        ++this.a;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0086: {
                    if (!l) {
                        break Label_0086;
                    }
                    final at at = this;
                    aw f = at.d[n2];
                    while (true) {
                        Label_0079: {
                            if (!l) {
                                break Label_0079;
                            }
                            if (n == this.a) {
                                if (!f.b.equals("__proto__")) {
                                    return f.b;
                                }
                                ++this.a;
                            }
                            ++n;
                            f = f.f;
                        }
                        if (f != null) {
                            continue;
                        }
                        break;
                    }
                    ++n2;
                }
                if (n2 < this.e) {
                    continue;
                }
                break;
            }
            final at at = this;
            if (!l) {
                this.a = -1;
                return null;
            }
            continue;
        }
    }
}
