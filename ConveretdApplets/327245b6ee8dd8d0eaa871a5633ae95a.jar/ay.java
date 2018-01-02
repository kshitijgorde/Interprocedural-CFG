// 
// Decompiled by Procyon v0.5.30
// 

public class ay extends u
{
    boolean a;
    u b;
    ar[] c;
    
    ay() {
        super.a = 4;
        this.a = false;
        this.b(3);
        this.a(0, "_up");
        this.a(1, "_over");
        this.a(2, "_down");
        (this.b = new u()).b(1);
        this.c = new ar[19];
    }
    
    int a(final x x) {
        final boolean l = c.l;
        super.d = x.e();
        this.a = ((x.d() & 0x1) != 0x0);
        final int e = x.e();
        int d;
    Label_0328:
        while ((d = x.d()) != 0) {
            final int e2 = x.e();
            final int e3 = x.e();
            ac i = x.l();
            ag ag = new ag();
            x.a(ag);
            final int n = d & 0x7;
            int n2 = 0;
            int n3 = 2;
            int n4 = 0;
            do {
                Label_0219: {
                    if (n4 == 1) {
                        n3 = 0;
                        if (!l) {
                            break Label_0219;
                        }
                    }
                    if (n == 2) {
                        n2 = 1;
                        n3 = 1;
                        if (!l) {
                            break Label_0219;
                        }
                    }
                    if (n == 3) {
                        n3 = 1;
                        if (!l) {
                            break Label_0219;
                        }
                    }
                    if (n == 4) {
                        n2 = 2;
                        if (!l) {
                            break Label_0219;
                        }
                    }
                    if (n == 5) {
                        this.a(new af(0, 0, e2, e3, new ac(i), new ag(ag), -1, "", -1, null));
                        n2 = 2;
                        if (!l) {
                            break Label_0219;
                        }
                    }
                    if (n == 6) {
                        n2 = 1;
                    }
                }
                if (n != 0) {
                    this.a(new af(n2, n3, e2, e3, i, ag, -1, "", -1, null));
                }
                if ((d & 0x8) == 0x0) {
                    continue Label_0328;
                }
                n4 = n;
            } while (l);
            if (n4 != 0) {
                i = new ac(i);
                ag = new ag(ag);
            }
            this.b.a(new af(0, 0, e2, e3, i, ag, -1, "", -1, null));
        }
        if (e != 0) {
            int j = 0;
        Label_0451_Outer:
            do {
                final int e4 = x.e();
                int e5 = x.b + e4 - 2;
                if (e4 == 0) {
                    e5 = x.e;
                }
                x.e();
                final v v = new v(x.a, x.b, x.b, e5 - x.b);
                x.b.r.addElement(v);
                final ar ar = new ar(v, 0, e5 - x.b, null, 4, 0, null, null, 0);
                int n5 = 0;
                while (true) {
                    Label_0618: {
                        if (!l) {
                            break Label_0618;
                        }
                        if (j != 0) {
                            if (n5 <= 8) {
                                int n6 = 0;
                                Label_0606: {
                                    if (n5 == 0) {
                                        n6 = 13;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 1) {
                                        n6 = 14;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 2) {
                                        n6 = 10;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 3) {
                                        n6 = 11;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 4) {
                                        n6 = 16;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 5) {
                                        n6 = 15;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 6) {
                                        n6 = 12;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 7) {
                                        n6 = 15;
                                        if (!l) {
                                            break Label_0606;
                                        }
                                    }
                                    if (n5 == 8) {
                                        n6 = 16;
                                    }
                                }
                                this.c[n6] = ar;
                            }
                        }
                        ++n5;
                    }
                    if (n5 < 16) {
                        continue;
                    }
                    x.b = e5;
                    j = e4;
                    if (!l) {
                        continue Label_0451_Outer;
                    }
                    continue;
                }
            } while (j != 0);
        }
        this.f();
        this.b.f();
        this.b.b = x.b;
        return super.d;
    }
}
