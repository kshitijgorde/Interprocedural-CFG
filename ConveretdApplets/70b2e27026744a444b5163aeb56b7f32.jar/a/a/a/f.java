// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.Vector;
import b.a.d.d;
import b.a.d.a;
import b.a.c.b;
import java.util.Arrays;
import b.a.a.e;
import b.a.c.k;
import b.a.c.q;
import b.a.c.r;
import b.a.c.o;

public class f extends Thread
{
    private final n a;
    
    public f(final n a) {
        super("Calculations");
        this.a = a;
    }
    
    public void run() {
        final long n = 0L;
        boolean b = true;
        final n a = this.a;
        final o o = new o();
        final r r = new r();
        final String[] d = this.a.p.d("MONTHS");
        final String[] d2 = this.a.p.d("PHASE_NAMES");
        while (this.a.n) {
            final q q;
            final int d3;
            final int f;
            final boolean q2;
            synchronized (a) {
                q = (q)a.m.clone();
                d3 = a.d;
                f = a.f;
                q2 = a.q;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final double n2 = k.a(currentTimeMillis) + f;
            final int r2 = q.r(n2);
            if (d3 != r2 || currentTimeMillis >= n + 600000L || q2) {
                final double t = r.t(n2);
                final String string = e.d(r.b(10, n2) * 100.0) + "%";
                synchronized (a) {
                    a.u = t;
                    a.x = string;
                }
            }
            if (d3 != r2 || q2) {
                new g(this.a, r2).start();
                final int[] b2 = k.b(r2);
                final int n3 = b2[0];
                final int n4 = b2[1];
                final int n5 = b2[2];
                final double a2 = q.a(n3, n4, n5, 0, 0, 0.0);
                final double a3 = q.a(n3, n4, n5 + 1, 0, 0, 0.0);
                final double n6 = a3 - a2;
                double n7 = a3;
                double n8 = a2;
                boolean b3 = false;
                boolean b4 = false;
                final String[] z = new String[a.a.a.n.bb.length];
                final String[] a4 = new String[a.a.a.n.bb.length];
                final String[] b5 = new String[a.a.a.n.bb.length];
                Arrays.fill(z, "\u2014");
                Arrays.fill(a4, "\u2014");
                Arrays.fill(b5, "");
                final String string2 = d[n4 - 1] + " " + n5 + ", " + n3;
                for (int i = 0; i < a.a.a.n.bb.length; ++i) {
                    final int n9 = a.a.a.n.bb[i];
                    final Vector a5 = o.a(n9, n3, n4, n5, q);
                    double a6 = Double.MAX_VALUE;
                    double a7 = Double.MAX_VALUE;
                    boolean b6 = true;
                    for (int j = 0; j < a5.size(); ++j) {
                        final b b7 = a5.get(j);
                        final String a8 = b7.a(7);
                        final int b8 = b7.b();
                        if (b8 == 200 && a6 == Double.MAX_VALUE) {
                            z[i] = a8;
                            a6 = b7.a();
                            if (n9 == 0 && a6 - 0.020833333333333332 >= a2) {
                                n8 = a6 - 0.020833333333333332;
                            }
                        }
                        else if (b8 == 201 && a7 == Double.MAX_VALUE) {
                            a4[i] = a8;
                            a7 = b7.a();
                            if (n9 == 0 && a7 + 0.020833333333333332 <= a3) {
                                n7 = a7 + 0.020833333333333332;
                            }
                        }
                        else if (n9 == 0 && b8 == 202) {
                            b3 = true;
                        }
                        else if (b8 == 203) {
                            b6 = false;
                            if (n9 == 0) {
                                b4 = true;
                            }
                        }
                    }
                    if (n9 == 0) {
                        if (a6 != Double.MAX_VALUE && a7 != Double.MAX_VALUE) {
                            if (a7 - a6 < 0.041666666666666664) {
                                b4 = true;
                            }
                            else if (a7 - a6 > n6 - 0.041666666666666664) {
                                b3 = true;
                            }
                        }
                    }
                    else if (n9 != 10) {
                        b5[i] = this.a.p.c("VISIBILITY_NONE");
                        if (b6) {
                            if (a7 != Double.MAX_VALUE && a6 == Double.MAX_VALUE) {
                                a6 = a2;
                            }
                            else if (a6 != Double.MAX_VALUE && a7 == Double.MAX_VALUE) {
                                a7 = a3;
                            }
                            if (b4) {
                                b5[i] = this.a.p.c("VISIBILITY_GENERAL");
                            }
                            else if (!b3 && a6 < a7) {
                                if (a6 > n7) {
                                    b5[i] = this.a.p.c("VISIBILITY_GENERAL");
                                }
                                if (a6 < n8) {
                                    b5[i] = this.a.p.c("VISIBILITY_MORNING");
                                }
                                else if (a7 > n7) {
                                    b5[i] = this.a.p.c("VISIBILITY_EVENING");
                                }
                            }
                            else if (a6 == Double.MAX_VALUE && a7 == Double.MAX_VALUE) {
                                b5[i] = this.a.p.c("VISIBILITY_ALL_NIGHT");
                            }
                            else if (!b3 && a6 > a7) {
                                if (a6 < n8) {
                                    b5[i] = this.a.p.c("VISIBILITY_GENERAL");
                                }
                                else if (a6 > n7 && a7 < n8) {
                                    b5[i] = this.a.p.c("VISIBILITY_MIDNIGHT");
                                }
                                else if (a6 < n7 && a7 > n8) {
                                    b5[i] = this.a.p.c("VISIBILITY_ALL_NIGHT");
                                }
                                else if (a6 > n7) {
                                    b5[i] = this.a.p.c("VISIBILITY_MORNING");
                                }
                                else if (a7 < n8) {
                                    b5[i] = this.a.p.c("VISIBILITY_EVENING");
                                }
                            }
                        }
                    }
                }
                String v = d2[e.c(a.u / 90.0) * 2 + 1];
                String a9 = "";
                final b a10 = o.a(n3, n4, n5, q);
                if (a10 != null) {
                    final int b9 = a10.b();
                    switch (b9) {
                        case 0: {
                            v = d2[0];
                            break;
                        }
                        case 1: {
                            v = d2[2];
                            break;
                        }
                        case 2: {
                            v = d2[4];
                            break;
                        }
                        case 3: {
                            v = d2[6];
                            break;
                        }
                    }
                    if (this.a.o) {
                        String s = a10.a(7);
                        if (s.startsWith("0")) {
                            s = s.substring(1);
                        }
                        a9 = b.a.d.a.a(this.a.p.c("PHASE_CAPTION"), new Integer((b9 == 3) ? 50 : (b9 * 50)), s);
                    }
                }
                synchronized (a) {
                    a.d = r2;
                    a.s = string2;
                    a.t = b.a.d.a.a(this.a.p.c("DATA_SHOWN_FOR"), q.g());
                    a.v = v;
                    a.w = a9;
                    a.z = z;
                    a.A = a4;
                    a.B = b5;
                    a.q = false;
                }
                this.a.repaint();
            }
            long n10 = 60000L - currentTimeMillis % 60000L + 250L;
            if (!b && n10 < 2000L) {
                n10 += 60000L;
            }
            b = !b.a.d.d.a(n10);
        }
        synchronized (this.a) {
            this.a.c = null;
        }
    }
}
