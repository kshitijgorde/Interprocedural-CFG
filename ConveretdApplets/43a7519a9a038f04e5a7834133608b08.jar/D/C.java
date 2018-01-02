// 
// Decompiled by Procyon v0.5.30
// 

package D;

import java.io.BufferedReader;
import java.net.URLConnection;
import core.N;
import java.util.Vector;
import core.K;
import core.RE;
import java.net.URL;

final class C implements Runnable
{
    private I a;
    private final URL b;
    private final String c;
    private final RE d;
    private final URL e;
    
    C(final I a, final URL b, final String c, final RE d, final URL e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public final void run() {
        if (this.b.getHost().endsWith(I.I.I(237))) {
            final URLConnection openConnection;
            (openConnection = K.a(this.b, I.I.I(768) + this.c + I.I.I(781) + this.d.getParameter(I.I.I(254)) + I.I.I(648) + K.a(this.e.toExternalForm())).openConnection()).setDoInput(true);
            openConnection.setDoOutput(false);
            final BufferedReader a2;
            final String[] a = Z.a((a2 = K.a(openConnection, 0 != 0)).readLine());
            I.a(this.a, a[0].equals(I.I.I(12)));
            I.b(this.a, a[1].equals(I.I.I(12)));
            I.c(this.a, a[2].equals(I.I.I(12)));
            I.a(this.a, Integer.parseInt(a[4]));
            final Vector vector = new Vector<N>();
            String line;
            while ((line = a2.readLine()) != null) {
                final String[] a3;
                vector.addElement(new N(Float.valueOf((a3 = Z.a(line))[0]), Float.valueOf(a3[1]), a3[3], a3[2], a3[4]));
            }
            final int size = vector.size();
            I.a(this.a, new N[size]);
            for (int i = 0; i < size; ++i) {
                I.a(this.a)[i] = vector.elementAt(i);
            }
            a2.close();
        }
    }
}
