// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.net.URL;

class u implements Runnable
{
    private ob a;
    private long b;
    private URL c;
    private kb d;
    private int[] e;
    private int[] f;
    private int g;
    private int h;
    private final s i;
    private static String[] z;
    
    void a(int n) {
        synchronized (this) {
            if (n < 0) {
                n = 0;
            }
            if (n < this.g || this.g < 0) {
                this.g = n;
            }
            if (n > this.h || this.h < 0) {
                this.h = n;
            }
        }
    }
    
    void b(final int n) {
        synchronized (this) {
            if (n < 0 || n >= this.e.length) {
                // monitorexit(this)
                return;
            }
            final int[] e = this.e;
            ++e[n];
        }
    }
    
    void a() {
    }
    
    void b() {
        URL c = null;
        kb d = null;
        int g = 0;
        int h = 0;
        synchronized (this) {
            if (this.a == null) {}
            c = this.c;
            if (this.d != null) {
                d = this.d;
            }
            for (int i = 0; i < this.f.length; ++i) {
                this.f[i] = this.e[i];
            }
            g = this.g;
            h = this.h;
            this.g = -1;
            this.h = -1;
        }
        if (c != null) {
            final lb a = lb.a();
            a.c(u.z[1]);
            if (h >= 0) {
                a.d(g);
                a.c("-");
                a.d(h);
            }
            a.c(u.z[2]);
            final kb a2 = nb.a((int)(System.currentTimeMillis() - this.b), true);
            a.a(a2);
            a2.f();
            a.c(u.z[0]);
            for (int j = 0; j < this.f.length; ++j) {
                a.d(this.f[j]);
                if (j < this.f.length - 1) {
                    a.c(",");
                }
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final kb b = a.b();
            s.a(this.i, c, d, b);
            b.f();
            this.a((int)(System.currentTimeMillis() - currentTimeMillis));
        }
    }
    
    public void run() {
    }
    
    private u(final s i) {
        this.i = i;
        this.a = null;
        this.e = new int[5];
        this.f = new int[this.e.length];
        this.b = System.currentTimeMillis();
        this.c = null;
        this.d = null;
        for (int j = 0; j < this.e.length; ++j) {
            this.e[j] = 0;
        }
        this.g = -1;
        this.h = -1;
    }
    
    u(final s s, final t t) {
        this(s);
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u0012@W".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = '#';
                            break;
                        }
                        case 2: {
                            c2 = 'm';
                            break;
                        }
                        case 3: {
                            c2 = '_';
                            break;
                        }
                        default: {
                            c2 = ' ';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "NJ\u00038\u001a".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '>';
                            break;
                        }
                        case 1: {
                            c4 = '#';
                            break;
                        }
                        case 2: {
                            c4 = 'm';
                            break;
                        }
                        case 3: {
                            c4 = '_';
                            break;
                        }
                        default: {
                            c4 = ' ';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0012\u0003\u0019e".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '>';
                            break;
                        }
                        case 1: {
                            c6 = '#';
                            break;
                        }
                        case 2: {
                            c6 = 'm';
                            break;
                        }
                        case 3: {
                            c6 = '_';
                            break;
                        }
                        default: {
                            c6 = ' ';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                u.z = z;
                return;
            }
            continue;
        }
    }
}
