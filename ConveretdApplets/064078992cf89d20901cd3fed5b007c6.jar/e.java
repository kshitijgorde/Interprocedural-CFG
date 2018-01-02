// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    l j;
    ak g;
    an i;
    an h;
    an f;
    int else;
    int char;
    int e;
    int d;
    an long;
    i case;
    f c;
    String[] a;
    int new;
    int byte;
    int k;
    an try;
    f m;
    f l;
    an do;
    public boolean for;
    byte[] b;
    byte[] void;
    boolean goto;
    boolean int;
    boolean if;
    
    e(final l j, final ak g) {
        this.else = 0;
        this.char = 0;
        this.e = 0;
        this.d = 0;
        this.a = new String[] { "Zoom Out", "Zoom In", "Show/hide hotspots", "Show/Hide lights", "Play/Stop autopath", "Fullscreen", "Exit fullscreen", "About", "Help" };
        this.new = 9;
        this.byte = 19;
        this.k = 17;
        this.for = true;
        this.b = new byte[] { 80, 85, 82, 69, 32, 80, 108, 97, 121, 101, 114 };
        this.void = new byte[] { 98, 121, 32, 73, 109, 109, 101, 114, 86, 105, 115, 105, 111, 110 };
        this.goto = false;
        this.int = false;
        this.if = true;
        this.j = j;
        this.g = g;
        this.case = g.w;
        int n = -9208962;
        final int p2 = g.p;
        if (Math.abs((n & 0xFF) - (p2 & 0xFF)) + Math.abs((n >> 8 & 0xFF) - (p2 >> 8 & 0xFF)) + Math.abs((n >> 16 & 0xFF) - (p2 >> 16 & 0xFF)) < 150 || (n & 0xFF) + (n >> 8 & 0xFF) + (n >> 16 & 0xFF) > (p2 & 0xFF) + (p2 >> 8 & 0xFF) + (p2 >> 16 & 0xFF)) {
            n = -1;
        }
        this.m = new f(this.j, n, 33);
        this.l = new f(this.j, n, 20);
        this.m.a(new String(this.b));
        this.l.a(new String(this.void));
        final aa a = this.j.n.a("immerbt3.gif", null, false, true, false);
        while (!a.void || a.case == null || !a.case.for) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        this.h = a.case;
        final aa a2 = this.j.n.a("immerlg.gif", null, false, true, false);
        while (!a2.void || a2.case == null || !a2.case.for) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
        }
        byte b = 0;
        for (int i = 0; i < a2.if; ++i) {
            b += a2.k[i];
        }
        if (b != -8874) {
            return;
        }
        this.do = a2.case;
        final aa a3 = this.j.n.a("immerbt2.gif", null, false, true, false);
        while (!a3.void || a3.case == null || !a3.case.for) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex3) {}
        }
        this.f = a3.case;
        final aa a4 = this.j.n.a("immerbar.gif", null, false, true, false);
        while (!a4.void || a4.case == null || !a4.case.for) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex4) {}
        }
        this.i = a4.case;
        final aa a5 = this.j.n.a("immerfs.gif", null, false, true, false);
        while (!a5.void || a5.case == null || !a5.case.for) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex5) {}
        }
        this.try = a5.case;
        final String parameter = this.g.getParameter("zoomintext");
        if (parameter != null) {
            this.a[0] = parameter;
        }
        final String parameter2 = this.g.getParameter("zoomouttext");
        if (parameter2 != null) {
            this.a[0] = parameter2;
        }
        final String parameter3 = this.g.getParameter("hospotstext");
        if (parameter3 != null) {
            this.a[2] = parameter3;
        }
        final String parameter4 = this.g.getParameter("hotspotstext");
        if (parameter4 != null) {
            this.a[2] = parameter4;
        }
        final String parameter5 = this.g.getParameter("lightstext");
        if (parameter5 != null) {
            this.a[3] = parameter5;
        }
        final String parameter6 = this.g.getParameter("autopathtext");
        if (parameter6 != null) {
            this.a[3] = parameter6;
        }
        final String parameter7 = this.g.getParameter("fullscreentext");
        if (parameter7 != null) {
            this.a[4] = parameter7;
        }
        final String parameter8 = this.g.getParameter("exitfullscreentext");
        if (parameter8 != null) {
            this.a[5] = parameter8;
        }
        final String parameter9 = this.g.getParameter("infotext");
        if (parameter9 != null) {
            this.a[6] = parameter9;
        }
        final String parameter10 = this.g.getParameter("helptext");
        if (parameter10 != null) {
            this.a[7] = parameter10;
        }
        this.c = new f(this.j, this.i.c[0]);
        this.a(" ");
    }
    
    public String if() {
        return this.c.int;
    }
    
    public void a(final String s) {
        this.c.a(s);
        this.c.if = this.char + (this.d - this.char - this.c.else) / 2;
    }
    
    public boolean a(final long n) {
        this.c.if = this.char + (this.d - this.char - this.c.else) / 2;
        return this.case.a(n) | this.c.a(n);
    }
    
    public void a(final an long1) {
        this.long = long1;
        if (this.g.do) {
            this.char = long1.e;
        }
        else {
            this.char = long1.e - this.k;
        }
        this.e = long1.long;
        this.d = long1.e;
        this.try.goto = this.e - this.try.long - 16;
        this.try.else = 16;
        if (this.g.do) {
            this.case.for = 0;
            this.case.else = 8;
            this.case.a = this.char - 8;
            this.case.int = this.e;
            this.case.if = -16777018;
            this.c.do = 0;
            this.c.if = -30;
        }
        else {
            this.case.for = this.h.long + 4;
            this.case.else = 8;
            this.case.a = this.char + (this.d - this.char - this.case.else) / 2 + 1;
            this.case.int = this.e - 8 - this.h.long - this.f.long;
            this.c.do = this.case.for;
            this.c.if = this.char + (long1.e - this.char - this.c.else) / 2;
        }
        final int long2 = this.do.long;
        final int for1 = this.m.for;
        final int e = this.do.e;
        final int else1 = this.m.else;
        final int goto1 = (this.e - for1 - long2 - 5) / 2;
        final int else2 = this.d / 2 - 40 - 10;
        this.do.goto = goto1;
        this.do.else = else2;
        final int do1 = goto1 + long2 + 5;
        final int if1 = else2 + (e - else1) / 2 + 2;
        this.m.do = do1;
        this.m.if = if1;
        final int do2 = (this.e - this.l.for) / 2;
        final int if2 = this.d / 2 - 10;
        this.l.do = do2;
        this.l.if = if2;
    }
    
    public void a() {
        d.a(this.h, this.long, 0, this.char, this.h.long, this.h.e);
        d.a(this.f, this.long, this.e - this.f.long, this.char, this.f.long, this.f.e);
        d.a(this.i, this.long, this.h.long, this.char, this.e - this.h.long - this.f.long, this.i.e);
        if (this.g.a) {
            d.a(this.try, this.long, this.try.goto, this.try.else, this.try.long, this.try.e);
        }
        this.case.if();
        this.c.if();
        if (this.for) {
            d.a(this.do, this.long, this.do.goto, this.do.else, this.do.long, this.do.e);
            this.m.if();
            this.l.if();
        }
    }
    
    void a(final ah ah) {
        if ((((ah.goto > this.else && ah.goto < this.else + this.h.long) || (ah.goto > this.e - this.f.long && ah.goto < this.e)) && ah.else > this.char && ah.else < this.char + this.h.e) || (this.g.a && ah.goto > this.try.goto && ah.goto < this.try.goto + this.try.long && ah.else > this.try.else && ah.else < this.try.else + this.try.e)) {
            if (this.int && !ah.h && ah.if != 4) {
                ah.for = 0;
            }
            ah.h = true;
            this.int = true;
            int n = ah.goto / this.byte;
            if (ah.goto > this.try.goto && ah.goto < this.try.goto + this.try.long && ah.else > this.try.else && ah.else < this.try.else + this.try.e) {
                n = 6;
            }
            else if (ah.goto > this.e - this.f.long && ah.goto < this.e) {
                n = (this.byte * 2 + ah.goto - this.e) / this.byte + 7;
            }
            this.c.a(this.a[n]);
            if (this.int && ah.if != 2) {
                if (ah.if == 0) {
                    switch (n) {
                        case 0: {
                            if (this.g.n != null) {
                                this.g.n.i.goto();
                                break;
                            }
                            break;
                        }
                        case 1: {
                            if (this.g.n != null) {
                                this.g.n.i.try();
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (this.g.n != null) {
                                this.g.n.if();
                                break;
                            }
                            break;
                        }
                        case 3: {
                            if (this.g.n != null) {
                                this.g.n.int();
                                break;
                            }
                            break;
                        }
                        case 4: {
                            if (this.g.n == null) {
                                break;
                            }
                            this.g.n.i.aB = !this.g.n.i.aB;
                            if (this.g.n.i.aB) {
                                this.g.n.i.a(System.currentTimeMillis(), true);
                                this.g.n.i.bE = false;
                                break;
                            }
                            break;
                        }
                        case 5:
                        case 6: {
                            this.g.a = !this.g.a;
                            this.g.a(true);
                            break;
                        }
                        case 7: {
                            this.g.if();
                            break;
                        }
                        case 8: {
                            this.g.a();
                            break;
                        }
                    }
                }
                else if (ah.if == 1) {
                    switch (n) {
                        case 0:
                        case 1: {
                            if (this.g.n != null) {
                                this.g.n.i.char();
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        else if (this.int) {
            final String if1 = this.g.r.if();
            for (int i = 0; i < this.new; ++i) {
                if (if1.compareTo(this.a[i]) == 0) {
                    this.g.r.a("");
                }
            }
            this.int = false;
        }
    }
}
