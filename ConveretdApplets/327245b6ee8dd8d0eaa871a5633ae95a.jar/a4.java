import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a4 extends c
{
    private static boolean[] a;
    Vector b;
    int c;
    int d;
    static int[] e;
    static int[] f;
    
    a4() {
        a4.a = new boolean[256];
        this.b = new Vector();
        this.c("BACKSPACE", new aq(8.0f));
        this.c("CAPSLOCK", new aq(20.0f));
        this.c("CONTROL", new aq(17.0f));
        this.c("DELETEKEY", new aq(46.0f));
        this.c("DOWN", new aq(40.0f));
        this.c("END", new aq(35.0f));
        this.c("ENTER", new aq(13.0f));
        this.c("ESCAPE", new aq(27.0f));
        this.c("HOME", new aq(36.0f));
        this.c("INSERT", new aq(45.0f));
        this.c("LEFT", new aq(37.0f));
        this.c("PGDN", new aq(34.0f));
        this.c("PGUP", new aq(33.0f));
        this.c("RIGHT", new aq(39.0f));
        this.c("SHIFT", new aq(16.0f));
        this.c("SPACE", new aq(32.0f));
        this.c("TAB", new aq(9.0f));
        this.c("UP", new aq(38.0f));
    }
    
    void a(final int n) {
        int n2 = 0;
        while (true) {
            Label_0051: {
                if (!c.l) {
                    break Label_0051;
                }
                final c c = this.b.elementAt(n2);
                if ((n & 0x1) != 0x0) {
                    c.a("onKeyUp", null);
                }
                if ((n & 0x2) != 0x0) {
                    c.a("onKeyDown", null);
                }
                ++n2;
            }
            if (n2 >= this.b.size()) {
                return;
            }
            continue;
        }
    }
    
    void a(int d, int c) {
        c = c(c);
        if (c < 256) {
            d = d(d);
            this.c = d;
            this.d = c;
            a4.a[this.d] = true;
        }
    }
    
    void b(final int n, int c) {
        c = c(c);
        if (c < 256) {
            a4.a[c] = false;
        }
    }
    
    private static int c(final int n) {
        final boolean l = c.l;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0030: {
                    if (!l) {
                        break Label_0030;
                    }
                    final int n3 = a4.e[n2];
                    if (n3 == n) {
                        return a4.e[n2 + 1];
                    }
                    n2 += 2;
                }
                if (n2 < a4.e.length) {
                    continue;
                }
                break;
            }
            final int n3 = n;
            if (!l) {
                return n;
            }
            continue;
        }
    }
    
    private static int d(final int n) {
        final boolean l = c.l;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0030: {
                    if (!l) {
                        break Label_0030;
                    }
                    final int n3 = a4.f[n2];
                    if (n3 == n) {
                        return a4.f[n2 + 1];
                    }
                    n2 += 2;
                }
                if (n2 < a4.f.length) {
                    continue;
                }
                break;
            }
            final int n3 = n;
            if (!l) {
                return n;
            }
            continue;
        }
    }
    
    static {
        a4.e = new int[] { 10, 13, 155, 45, 127, 46 };
        a4.f = new int[] { 10, 13 };
    }
}
