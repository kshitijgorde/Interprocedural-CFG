// 
// Decompiled by Procyon v0.5.30
// 

package D;

import core.T;
import java.util.Enumeration;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.util.Vector;
import java.util.Hashtable;
import core.K;
import I.I;
import core.P;
import core.N;
import core.J;
import core.Q;
import core.RE;

public final class Z extends Thread
{
    private final int a;
    private final String b;
    private final RE c;
    private final C.Z d;
    private int e;
    private Q[] f;
    private J g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private int l;
    private int m;
    private boolean n;
    
    Z(final RE c, final String b, final C.Z d, final N[] array, final boolean k, final boolean b2, final boolean b3, final int a) {
        this.e = 0;
        this.g = P.b;
        this.l = 0;
        this.c = c;
        this.b = b;
        if (c.h) {
            try {
                this.l = (int)(10000.0f * Float.valueOf(c.getParameter(I.I(551))));
            }
            catch (Exception ex) {}
        }
        this.d = d;
        this.j = c.a(I.I(553), true);
        this.f = Q.a(c, array, this.j);
        for (int i = 0; i < array.length; ++i) {
            d.a(array[i].a, array[i].b);
        }
        this.k = k;
        this.a = a;
        this.h = (b2 && c.a(I.I(559), true));
        this.i = (b3 && c.a(I.I(561), false));
        this.setPriority(1);
        this.setDaemon(true);
    }
    
    public final void start() {
        if (this.k) {
            super.start();
        }
    }
    
    public final void run() {
        while (this.c.b()) {
            this.n = false;
            final URLConnection c = K.c(K.a(this.c.a(), I.I(3) + this.b) + I.I(12) + Integer.toString(this.e, 36) + ((this.n || this.e == 0) ? K.a() : ""));
            BufferedReader a = null;
            try {
                c.setUseCaches(false);
                a = K.a(c, I.I(16).equals(c.getContentEncoding()));
                if (!I.I(24).equals(a.readLine())) {
                    throw new Exception();
                }
                this.e = Integer.parseInt(a.readLine(), 36);
                this.m = Integer.parseInt(a.readLine()) * 10000;
                this.m = ((this.l > 0) ? this.l : this.m);
                final String line = a.readLine();
                String s = "";
                if (this.h) {
                    s = String.valueOf(s) + Integer.parseInt(line) + I.I(27);
                }
                final Hashtable<String, Q> hashtable = new Hashtable<String, Q>();
                for (int i = 0; i < this.f.length; ++i) {
                    final B.Z b = this.f[i].b;
                    hashtable.put(String.valueOf(b.a) + I.I(35) + b.b + I.I(35) + b.c, this.f[i]);
                }
                int n = 0;
                String line2;
                while ((line2 = a.readLine()) != null) {
                    final String[] a2;
                    if ((a2 = a(line2)).length == 2 || a2.length == 6) {
                        final float n2 = Float.valueOf(a2[0]) / 1024.0f;
                        final float n3 = Float.valueOf(a2[1]) / 512.0f;
                        this.d.a(n2, n3);
                        if (a2.length == 6) {
                            final Q q;
                            final B.Z b2 = (q = new Q(this.c, n2, n3, a2[3], a2[4], a2[5], this.j, Integer.parseInt(a2[2]))).b;
                            hashtable.put(String.valueOf(b2.a) + I.I(35) + b2.b + I.I(35) + b2.c, q);
                        }
                        if (n == 7) {
                            try {
                                Thread.sleep(1L);
                            }
                            catch (InterruptedException ex) {}
                            n = 0;
                        }
                        ++n;
                    }
                }
                final Vector vector = new Vector<Q>();
                final Enumeration<Q> elements = hashtable.elements();
                while (elements.hasMoreElements()) {
                    final Q q2;
                    if ((q2 = elements.nextElement()).a > this.e - this.a) {
                        vector.addElement(q2);
                    }
                }
                final int size;
                final Q[] f = new Q[size = vector.size()];
                for (int j = 0; j < size; ++j) {
                    f[j] = vector.elementAt(j);
                }
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex2) {}
                for (int k = 0; k < size; ++k) {
                    for (int l = k + 1; l < size; ++l) {
                        if (f[k].a > f[l].a) {
                            final Q q3 = f[k];
                            f[k] = f[l];
                            f[l] = q3;
                        }
                    }
                }
                this.f = f;
                if (this.i) {
                    if (s.length() > 0) {
                        s = String.valueOf(s) + I.I(37);
                    }
                    s = String.valueOf(s) + this.f.length + I.I(41);
                    if (this.f.length != 1) {
                        s = String.valueOf(s) + I.I(53);
                    }
                }
                this.g = this.c.e.a(s);
            }
            catch (Exception ex3) {
                this.n = true;
            }
            try {
                a.close();
            }
            catch (Exception ex4) {}
            final int n4 = (this.m < 10000) ? 30000 : this.m;
            try {
                Thread.sleep(n4);
            }
            catch (InterruptedException ex5) {}
        }
    }
    
    public static final String[] a(String s) {
        s = s;
        final Vector vector = new Vector<String>();
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            final char char1;
            if ((char1 = s.charAt(i)) != '\t') {
                string = String.valueOf(string) + char1;
            }
            else {
                vector.addElement(string);
                string = "";
            }
        }
        vector.addElement(string);
        final int size;
        final String[] array = new String[size = vector.size()];
        for (int j = 0; j < size; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    public final void a(final T t) {
        try {
            for (int length = this.f.length, i = 0; i < length; ++i) {
                this.f[i].a(t, this.c);
            }
            if (this.g != P.b) {
                final int a = this.c.f.a(t);
                final J g = this.g;
                final int a2 = this.c.a;
                this.c.getClass();
                g.a(t, a2 - 5, a, false);
            }
        }
        catch (Exception ex) {}
    }
}
