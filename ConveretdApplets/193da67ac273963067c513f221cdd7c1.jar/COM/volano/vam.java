// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Menu;

public class vam extends Menu
{
    public vd a;
    public String[] b;
    
    public vam(final vd a) {
        super(a.fk);
        this.a = a;
        final StringTokenizer stringTokenizer = new StringTokenizer(a.fl, "|");
        while (stringTokenizer.hasMoreTokens()) {
            this.add(stringTokenizer.nextToken().trim());
        }
        final Vector vector = new Vector<String>();
        final StringTokenizer stringTokenizer2 = new StringTokenizer(a.fm, "|");
        while (stringTokenizer2.hasMoreTokens()) {
            vector.addElement(stringTokenizer2.nextToken().trim());
        }
        vector.copyInto(this.b = new String[vector.size()]);
    }
    
    private int c(final Object o) {
        int n = -1;
        for (int countItems = this.countItems(), n2 = 0; n == -1 && n2 < countItems; ++n2) {
            if (o == this.getItem(n2)) {
                n = n2;
            }
        }
        return n;
    }
    
    public boolean a(final Object o) {
        return this.c(o) != -1;
    }
    
    public URL b(final Object o) {
        URL url = null;
        final int c = this.c(o);
        if (c != -1 && c < this.b.length) {
            try {
                url = new URL(this.b[c]);
            }
            catch (MalformedURLException ex) {
                System.out.println(ex);
            }
        }
        return url;
    }
}
