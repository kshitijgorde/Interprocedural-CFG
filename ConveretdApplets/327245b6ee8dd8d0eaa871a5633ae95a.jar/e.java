import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

final class e
{
    Vector a;
    
    e() {
        this.a = new Vector();
    }
    
    e(final int n, final int n2, final int n3, final int n4) {
        this.a = new Vector();
        this.a(new f(n, n2, n3, n4));
    }
    
    final void a() {
        this.a.removeAllElements();
    }
    
    final boolean b() {
        return this.a.size() == 0;
    }
    
    final void a(final f f) {
        this.a.addElement(new f(f));
    }
    
    final void a(final e e) {
        int n = 0;
        while (true) {
            Label_0026: {
                if (!c.l) {
                    break Label_0026;
                }
                this.a.addElement(e.a.elementAt(n));
                ++n;
            }
            if (n >= e.a.size()) {
                return;
            }
            continue;
        }
    }
    
    final void c() {
        final boolean l = c.l;
        if (this.a.size() == 0) {
            return;
        }
        boolean b;
    Label_0041_Outer:
        do {
            b = false;
            final Vector a = new Vector<f>();
            int n = 0;
            while (true) {
                while (true) {
                    Label_0132: {
                        if (!l) {
                            break Label_0132;
                        }
                        final Object element = this.a.elementAt(n);
                        final f f = (f)element;
                        if (!f.b()) {
                            boolean b2 = false;
                            int n2 = 0;
                        Label_0118:
                            while (true) {
                                Label_0109: {
                                    if (!l) {
                                        break Label_0109;
                                    }
                                    final f f2 = a.elementAt(n2);
                                    if (f2.b(f)) {
                                        f2.a(f);
                                        b2 = true;
                                        b = true;
                                        if (!l) {
                                            break Label_0118;
                                        }
                                    }
                                    ++n2;
                                }
                                if (n2 < a.size()) {
                                    continue;
                                }
                                break;
                            }
                            if (!b2) {
                                a.addElement(f);
                            }
                        }
                        ++n;
                    }
                    if (n < this.a.size()) {
                        continue Label_0041_Outer;
                    }
                    break;
                }
                this.a.removeAllElements();
                final Object element = this;
                if (l) {
                    continue;
                }
                break;
            }
            this.a = a;
        } while (b);
    }
}
