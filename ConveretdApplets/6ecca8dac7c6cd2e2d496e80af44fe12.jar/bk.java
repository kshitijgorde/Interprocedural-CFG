import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class bk
{
    static int a;
    int b;
    String[] c;
    String[] d;
    bw e;
    bf f;
    g g;
    
    void a(final bf f) {
        this.f = f;
    }
    
    void a() {
        this.g.b();
    }
    
    void a(String s) {
        final boolean dx = bm.dX;
        boolean b = false;
        int n = s.indexOf(" ");
        if (s.toLowerCase().startsWith(e("B("))) {
            b = true;
            final int index = s.indexOf(" ");
            if (index < 0) {
                return;
            }
            s = s.substring(index + 1);
            n = s.indexOf(" ");
        }
        do {
            String substring = null;
            Label_0089: {
                if (n < 0) {
                    substring = s;
                    s = "";
                    if (!dx) {
                        break Label_0089;
                    }
                }
                substring = s.substring(0, n);
                s = s.substring(n + 1);
            }
            Label_0280: {
                if (!b) {
                    final String c = this.c(substring);
                    if (c.length() != 0) {
                        this.e.a(irc.R + c, bn.e, false);
                        if (!dx) {
                            break Label_0280;
                        }
                    }
                    this.e.a(irc.R + substring + bm.de, bn.e, false);
                    if (!dx) {
                        break Label_0280;
                    }
                }
                final String d = this.d(substring);
                if (d.length() != 0) {
                    this.e.a(irc.R + d, bn.e, false);
                    if (!dx) {
                        break Label_0280;
                    }
                }
                this.e.a(irc.R + substring + bm.df, bn.e, false);
            }
            n = s.indexOf(" ");
        } while (s.length() != 0);
    }
    
    bk() {
        this.b = 0;
        this.c = new String[bk.a];
        this.d = new String[bk.a];
        this.g = new g(new Frame(bm.db), true, this);
    }
    
    void a(final bw e) {
        this.e = e;
    }
    
    int b(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            while (true) {
                Label_0034: {
                    if (!dx) {
                        break Label_0034;
                    }
                    s.toUpperCase().equals(this.c[n].toUpperCase());
                    final int n2;
                    if (n2 != 0) {
                        return n;
                    }
                    ++n;
                }
                if (n != this.b) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dx) {
                return n2;
            }
            continue;
        }
    }
    
    void b() {
        final boolean dx = bm.dX;
        String string = "";
        int n = 0;
        while (true) {
            while (true) {
                Label_0062: {
                    if (!dx) {
                        break Label_0062;
                    }
                    string = string + this.c[n] + e("Or") + this.d[n] + ")";
                    ++n;
                }
                if (n != this.b) {
                    continue;
                }
                break;
            }
            this.e.a(irc.R + bm.dh + string, bn.e, false);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    static {
        bk.a = 30;
    }
    
    void c() {
        this.g.dispose();
    }
    
    void a(final int n, final String s) {
        this.d[n] = s;
    }
    
    String c(final String s) {
        final int b = this.b;
        if (this.b + 1 == bk.a) {
            return bm.dc;
        }
        if (this.b(s) >= 0) {
            return s + bm.dd;
        }
        this.c[b] = new String(s);
        this.d[b] = "";
        ++this.b;
        this.f.a(e(":\t\u000fAV \t\u001e3$") + s + "\n");
        this.f.g.d();
        return s + bm.de;
    }
    
    String d(String upperCase) {
        final boolean dx = bm.dX;
        if (this.b(upperCase) < 0) {
            return upperCase + bm.dg;
        }
        final String s = upperCase;
        upperCase = upperCase.toUpperCase();
        int n = 0;
        while (true) {
        Label_0104_Outer:
            while (true) {
                Label_0165: {
                    if (!dx) {
                        break Label_0165;
                    }
                    this.c[n].toUpperCase();
                    final String s2;
                    if (s2.equals(upperCase)) {
                        int n2 = n;
                        while (true) {
                            while (true) {
                                Label_0107: {
                                    if (!dx) {
                                        break Label_0107;
                                    }
                                    this.c[n2] = this.c[n2 + 1];
                                    this.d[n2] = this.d[n2 + 1];
                                    ++n2;
                                }
                                if (n2 < this.b) {
                                    continue Label_0104_Outer;
                                }
                                break;
                            }
                            --this.b;
                            this.f.g.d();
                            if (!dx) {
                                return s + bm.df;
                            }
                            continue;
                        }
                    }
                    else {
                        ++n;
                    }
                }
                if (n != this.b) {
                    continue;
                }
                break;
            }
            final String s2 = "";
            if (!dx) {
                return s2;
            }
            continue;
        }
    }
    
    private static String e(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'o';
                    break;
                }
                case 1: {
                    c2 = 'Z';
                    break;
                }
                case 2: {
                    c2 = 'J';
                    break;
                }
                case 3: {
                    c2 = '\u0013';
                    break;
                }
                default: {
                    c2 = '\u001e';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
