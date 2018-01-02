// 
// Decompiled by Procyon v0.5.30
// 

class bv extends Thread
{
    bu a;
    bh b;
    bf c;
    bq d;
    irc e;
    int f;
    be g;
    br h;
    byte i;
    byte j;
    byte k;
    
    bv(final bu a, final bh b, final bf c, final bq d, final irc e, final be g, final br h) {
        this.f = 1;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.g = g;
        this.h = h;
    }
    
    String a() {
        return bh.a(this.j) + ":" + bh.a(this.k);
    }
    
    public void run() {
        final boolean dx = bm.dX;
    Label_0004:
        while (p.d) {
            this.b.a(this.a);
            final String b = this.d.b();
            final boolean x = irc.X;
            int f = 0;
            int cl = 0;
        Label_0033:
            while (true) {
                Label_0251: {
                    final int a;
                    if (a == 0) {
                        if (this.i != 5) {
                            ++this.i;
                            if (!dx) {
                                break Label_0251;
                            }
                        }
                        this.g.a(irc.R + bm.dE, false);
                        this.h.a(irc.R + bm.dE, false);
                        this.e.cF.e.a(irc.R + bm.dE, bn.e, false);
                        try {
                            Thread.sleep(10000L);
                        }
                        catch (InterruptedException ex) {}
                        irc.d = true;
                        this.e.cF.e.a(irc.R + bm.bU, bn.e, false);
                        this.c.a(a("\t\u001fJ[\u0005b") + this.e.cG + "\n");
                    }
                }
                b.length();
                do {
                    if (f > cl) {
                        this.c.a(b + "\n");
                    }
                    try {
                        Thread.sleep(60000L);
                    }
                    catch (InterruptedException ex2) {}
                    ++this.k;
                    if (this.k == 60) {
                        ++this.j;
                        this.k = 0;
                    }
                    if (this.e.cL == 0) {
                        continue Label_0004;
                    }
                    final int a = this.g.a();
                    if (dx) {
                        continue Label_0033;
                    }
                    if (a == 0 && this.h.c() == 0) {
                        continue Label_0004;
                    }
                    f = this.f;
                    cl = this.e.cL;
                } while (dx);
                break;
            }
            if (f == cl) {
                this.f = 1;
                this.e.c();
                if (!dx) {
                    continue;
                }
            }
            ++this.f;
        }
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'X';
                    break;
                }
                case 1: {
                    c2 = 'J';
                    break;
                }
                case 2: {
                    c2 = '\u0003';
                    break;
                }
                case 3: {
                    c2 = '\u000f';
                    break;
                }
                default: {
                    c2 = '%';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
