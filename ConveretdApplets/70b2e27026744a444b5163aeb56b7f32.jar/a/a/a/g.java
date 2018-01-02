// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import b.a.c.k;
import javax.swing.SwingUtilities;
import java.io.IOException;
import b.a.d.c;
import b.a.d.d;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import b.a.b.a;

public class g extends Thread
{
    int a;
    private final n b;
    
    public g(final n b, final int a) {
        super("EventReader");
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        final StringBuffer sb = new StringBuffer();
        final String c = this.b.p.c("MARQUEE_MESSAGE_SEPARATOR");
        String s = "ISO-8859-1";
        BufferedReader bufferedReader = null;
        try {
            final a a = new a("$/events/events.txt");
            if (a.markSupported()) {
                a.mark(3);
                if (a.read() == 239 && a.read() == 187 && a.read() == 191) {
                    s = "UTF-8";
                }
                a.reset();
            }
            bufferedReader = new BufferedReader(new InputStreamReader(a, s));
            String a2;
            while ((a2 = d.a(bufferedReader)) != null) {
                final String[] a3 = b.a.d.c.a(a2, '\t');
                if (a3.length == 3) {
                    final int a4 = this.a(a3[0]);
                    final int a5 = this.a(a3[1]);
                    if (a4 > this.a || this.a > a5) {
                        continue;
                    }
                    if (sb.length() > 0) {
                        sb.append(c);
                    }
                    sb.append(a3[2]);
                }
            }
            bufferedReader.close();
            if (sb.length() == 0) {
                sb.append(this.b.p.c("MARQUEE_MESSAGE_NO_EVENTS"));
            }
        }
        catch (IOException ex) {
            sb.setLength(0);
            sb.append("ERROR: Could not read event file");
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
        }
        SwingUtilities.invokeLater(new a.a.a.d(this, sb.toString()));
    }
    
    protected int a(String string) {
        if (string == null || string.length() < 7 || string.length() > 8) {
            return 0;
        }
        if (string.length() == 7) {
            string = "0" + string;
        }
        return k.a(d.a((Object)string.substring(4)), d.a((Object)string.substring(0, 2)), d.a((Object)string.substring(2, 4)));
    }
    
    static n a(final g g) {
        return g.b;
    }
}
