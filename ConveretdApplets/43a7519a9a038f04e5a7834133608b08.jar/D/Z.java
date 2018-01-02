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
import java.net.URL;
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
    
    Z(final RE c, final URL url, final C.Z d, final N[] array, final boolean k, final boolean b, final boolean b2, final int a) {
        this.e = 0;
        this.g = P.b;
        this.l = 30000;
        this.c = c;
        this.b = url.toExternalForm();
        if (c.h) {
            try {
                this.l = (int)(10000.0f * Float.valueOf(c.getParameter(I.I(524))));
            }
            catch (Exception ex) {}
            this.l = ((this.l < 10000) ? 30000 : this.l);
        }
        this.d = d;
        this.j = c.a(I.I(526), false);
        this.f = Q.a(c, array, this.j);
        for (int i = 0; i < array.length; ++i) {
            d.a(array[i].a, array[i].b);
        }
        this.k = k;
        this.a = a;
        this.h = (b && c.a(I.I(532), true));
        this.i = (b2 && c.a(I.I(540), false));
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
            if (this.k) {
                final boolean b = this.e == 0;
                final URLConnection c;
                if ((c = K.c(String.valueOf(this.b) + I.I(4) + (b ? I.I(12) : I.I(14)) + I.I(16) + this.e)) != null) {
                    c.setUseCaches(false);
                    c.setDoInput(true);
                    c.setDoOutput(false);
                    final BufferedReader a = K.a(c, b);
                    try {
                        this.e = Integer.parseInt(a.readLine());
                        final String line = a.readLine();
                        String s = "";
                        if (this.h) {
                            s = String.valueOf(s) + Integer.parseInt(line) + I.I(21);
                        }
                        final Hashtable<String, Q> hashtable = new Hashtable<String, Q>();
                        for (int i = 0; i < this.f.length; ++i) {
                            final B.Z b2 = this.f[i].b;
                            hashtable.put(String.valueOf(b2.a) + I.I(29) + b2.b + I.I(29) + b2.c, this.f[i]);
                        }
                        int n = 0;
                        String line2;
                        while ((line2 = a.readLine()) != null) {
                            final String[] a2;
                            if ((a2 = a(line2)).length == 2 || a2.length == 6) {
                                final float floatValue = Float.valueOf(a2[0]);
                                final float floatValue2 = Float.valueOf(a2[1]);
                                this.d.a(floatValue, floatValue2);
                                if (a2.length == 6) {
                                    final Q q;
                                    final B.Z b3 = (q = new Q(this.c, floatValue, floatValue2, a2[3], a2[4], a2[5], this.j, Integer.parseInt(a2[2]))).b;
                                    hashtable.put(String.valueOf(b3.a) + I.I(29) + b3.b + I.I(29) + b3.c, q);
                                }
                                if (n == 5) {
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
                                s = String.valueOf(s) + I.I(31);
                            }
                            s = String.valueOf(s) + this.f.length + I.I(35);
                            if (this.f.length != 1) {
                                s = String.valueOf(s) + I.I(47);
                            }
                        }
                        this.g = this.c.e.a(s);
                    }
                    catch (Exception ex3) {}
                    try {
                        a.close();
                    }
                    catch (Exception ex4) {}
                }
            }
            final int m = this.l;
            try {
                Thread.sleep(m);
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
