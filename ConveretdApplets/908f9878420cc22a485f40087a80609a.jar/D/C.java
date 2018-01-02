// 
// Decompiled by Procyon v0.5.30
// 

package D;

import java.net.URLConnection;
import java.io.BufferedReader;
import core.N;
import java.util.Vector;
import core.K;
import java.net.URL;
import core.RE;

final class C implements Runnable
{
    private I a;
    private final RE b;
    private final String c;
    private final boolean d;
    private final URL e;
    
    C(final I a, final RE b, final String c, final boolean d, final URL e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public final void run() {
        if (this.b.a().getHost().endsWith(I.I.I(247))) {
            BufferedReader a = null;
            try {
                final boolean a2 = this.b.a(I.I.I(726), false);
                String s = String.valueOf(a2 ? I.I.I(730) : I.I.I(736)) + I.I.I(742) + this.c;
                if (a2) {
                    s = String.valueOf(s) + (this.d ? K.a() : "") + I.I.I(746) + K.a(this.e.toExternalForm());
                }
                final URLConnection openConnection;
                (openConnection = K.a(this.b.a(), s).openConnection()).setUseCaches(!a2);
                final String[] a3 = Z.a((a = K.a(openConnection, 0 != 0)).readLine());
                I.a(this.a, a3[0].equals(I.I.I(267)));
                I.b(this.a, a3[1].equals(I.I.I(267)));
                I.c(this.a, a3[2].equals(I.I.I(267)));
                I.a(this.a, Integer.parseInt(a3[4]));
                final Vector vector = new Vector<N>();
                String line;
                while ((line = a.readLine()) != null) {
                    try {
                        final String[] a4;
                        vector.addElement(new N(Float.valueOf((a4 = Z.a(line))[0]), Float.valueOf(a4[1]), a4[3], a4[2], a4[4]));
                    }
                    catch (Exception ex) {}
                }
                final int size = vector.size();
                I.a(this.a, new N[size]);
                for (int i = 0; i < size; ++i) {
                    I.a(this.a)[i] = vector.elementAt(i);
                }
            }
            catch (Exception ex2) {}
            try {
                a.close();
            }
            catch (Exception ex3) {}
        }
    }
}
