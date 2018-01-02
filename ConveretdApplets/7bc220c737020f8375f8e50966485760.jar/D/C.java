// 
// Decompiled by Procyon v0.5.30
// 

package D;

import java.io.BufferedReader;
import java.net.URLConnection;
import core.T;
import java.util.Vector;
import core.E;
import core.RevolverEngine;
import java.net.URL;

final class C implements Runnable
{
    private I a;
    private final URL b;
    private final String c;
    private final RevolverEngine d;
    private final URL e;
    
    C(final I a, final URL b, final String c, final RevolverEngine d, final URL e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public final void run() {
        if (this.b.getHost().endsWith(I.I.I(246))) {
            final URLConnection openConnection;
            (openConnection = E.a(this.b, I.I.I(787) + this.c + I.I.I(807) + this.d.getParameter(I.I.I(263)) + I.I.I(660) + E.a(this.e.toExternalForm())).openConnection()).setDoInput(true);
            openConnection.setDoOutput(false);
            final BufferedReader a2;
            final String[] a = Z.a((a2 = E.a(openConnection, 0 != 0)).readLine());
            I.a(this.a, a[0].equals(I.I.I(12)));
            I.b(this.a, a[1].equals(I.I.I(12)));
            I.c(this.a, a[2].equals(I.I.I(12)));
            I.a(this.a, Integer.parseInt(a[4]));
            final Vector vector = new Vector<T>();
            String line;
            while ((line = a2.readLine()) != null) {
                final String[] a3;
                vector.addElement(new T(Float.valueOf((a3 = Z.a(line))[0]), Float.valueOf(a3[1]), a3[3], a3[2], a3[4]));
            }
            final int size = vector.size();
            I.a(this.a, new T[size]);
            for (int i = 0; i < size; ++i) {
                I.a(this.a)[i] = vector.elementAt(i);
            }
            a2.close();
        }
    }
}
