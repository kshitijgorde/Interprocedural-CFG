// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Point;
import java.awt.Color;
import java.net.URLConnection;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;
import java.io.Serializable;

class v extends m implements Serializable
{
    private boolean I;
    private Vector H;
    private boolean J;
    j F;
    private Vector G;
    
    v(final j f, final boolean j) {
        this.I = false;
        this.F = f;
        this.H = new Vector();
        this.G = new Vector();
        this.J = j;
    }
    
    void a(final m m) {
    }
    
    void if(final m m) {
        this.H.addElement(m);
    }
    
    m c() {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                return m;
            }
        }
        return null;
    }
    
    m do(final int n, final int n2) {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.do(n, n2) != null) {
                return m;
            }
        }
        return null;
    }
    
    void a(final Vector vector) {
        for (int i = this.H.size() - 1; i >= 0; --i) {
            ((m)this.H.elementAt(i)).a(vector);
        }
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                m.a(n, n2, n3, n4);
            }
        }
    }
    
    void for(final Graphics graphics) {
        for (int i = this.H.size() - 1; i >= 0; --i) {
            final m m = this.H.elementAt(i);
            if (m.a) {
                m.a = false;
            }
        }
    }
    
    void goto() {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                m.a = false;
            }
        }
    }
    
    void new(final Graphics graphics) {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                m.a = false;
            }
        }
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    void a(final Graphics graphics, final boolean b) {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(graphics, b);
        }
    }
    
    void int(final Graphics graphics) {
        final Enumeration<p> elements = this.G.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(graphics);
        }
    }
    
    void a(final Graphics graphics) {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                m.a(graphics);
            }
        }
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    boolean null() {
        boolean b = false;
        if (!this.H.isEmpty()) {
            this.H.removeElement(this.H.elementAt(this.H.size() - 1));
            b = true;
        }
        return b;
    }
    
    boolean b() {
        boolean b = false;
        for (int i = this.H.size() - 1; i >= 0; --i) {
            final m m = this.H.elementAt(i);
            if (m.a) {
                this.H.removeElement(m);
                b = true;
            }
        }
        return b;
    }
    
    public void e() {
        this.H.removeAllElements();
        this.I = false;
    }
    
    String a(final String s, final String s2, final int n) {
        String s3 = "";
        final String property = System.getProperty("line.separator");
        final String encode = URLEncoder.encode(System.getProperty("line.separator"));
        final Rectangle f = this.f();
        if (f.x < 0) {
            f.x = 0;
        }
        if (f.y < 0) {
            f.y = 0;
        }
        if (f.width < 0 || f.width > 550) {
            f.width = 550;
        }
        if (f.height < 0 || f.height > 450) {
            f.height = 450;
        }
        final int size = this.H.size();
        final int n2 = 200;
        int n3 = size / n2;
        if (size % n2 > 0) {
            ++n3;
        }
        final String[] array = new String[n3];
        for (int i = 0; i < n3; ++i) {
            array[i] = "";
        }
        int n4 = 0;
        int n5 = 0;
        final Enumeration<m> elements = (Enumeration<m>)this.H.elements();
        while (elements.hasMoreElements()) {
            final String if1 = elements.nextElement().if();
            final StringBuffer sb = new StringBuffer();
            final String[] array2 = array;
            final int n6 = n5;
            array2[n6] = sb.append(array2[n6]).append(if1).append(property).toString();
            if (++n4 % n2 == 0) {
                ++n5;
            }
        }
        URL url = null;
        try {
            url = new URL(s + "&type_aff=3&numgraphedite=" + n);
        }
        catch (MalformedURLException ex) {
            s3 = "Erreur: " + ex.toString();
        }
        String substring = "";
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        for (int j = 0; j < n3; ++j) {
            try {
                final URLConnection openConnection = url.openConnection();
                openConnection.setDoInput(true);
                openConnection.setDoOutput(true);
                openConnection.setUseCaches(false);
                String s4 = "" + j + "###" + n3 + "###" + n9 + "###" + encode + URLEncoder.encode(array[j].toString());
                if (j == n3 - 1) {
                    s4 = s4 + encode + "zonetexte;" + "";
                }
                openConnection.setRequestProperty("CONTENT_LENGTH", "" + s4.length());
                final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
                outputStreamWriter.write("donnees=" + s4);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                String line;
                while ((line = new BufferedReader(new InputStreamReader(openConnection.getInputStream())).readLine()) != null) {
                    substring = line;
                }
                final boolean b = true;
                int n10 = 0;
                while (b) {
                    final int index = substring.indexOf(35);
                    if (index == -1) {
                        break;
                    }
                    final String substring2 = substring.substring(0, index);
                    substring = substring.substring(index + 1);
                    final int intValue = Integer.valueOf(substring2);
                    if (n10 == 0) {
                        n7 = intValue;
                    }
                    else if (n10 == 1) {
                        n8 = intValue;
                    }
                    else if (n10 == 2) {
                        n9 = intValue;
                    }
                    ++n10;
                }
                this.F.if("" + n7 + " " + n8 + " " + n9);
                this.F.a(n7, n8);
            }
            catch (IOException ex2) {
                s3 = "Erreur: " + ex2.toString();
            }
        }
        return s3;
    }
    
    public Rectangle f() {
        double n = 500.0;
        double n2 = 0.0;
        double n3 = 500.0;
        double n4 = 0.0;
        for (int i = 0; i < this.H.size(); ++i) {
            final m m = this.H.elementAt(i);
            n = ((m.for().x <= n) ? m.for().x : n);
            n3 = ((m.for().y <= n3) ? m.for().y : n3);
            n2 = ((m.for().x + m.for().width >= n2) ? (m.for().x + m.for().width) : n2);
            n4 = ((m.for().y + m.for().height >= n4) ? (m.for().y + m.for().height) : n4);
        }
        return new Rectangle((int)n, (int)n3, (int)(n2 - n), (int)(n4 - n3));
    }
    
    public Rectangle for() {
        return null;
    }
    
    void a() {
    }
    
    int char() {
        int n = 0;
        for (int i = this.H.size() - 1; i >= 0; --i) {
            final m m = this.H.elementAt(i);
            ++n;
        }
        return n;
    }
    
    int void() {
        int n = 0;
        for (int i = this.H.size() - 1; i >= 0; --i) {
            if (((m)this.H.elementAt(i)).a) {
                ++n;
            }
        }
        return n;
    }
    
    int else() {
        for (int i = 0; i < this.H.size(); ++i) {
            if (((m)this.H.elementAt(i)).a) {
                return i;
            }
        }
        return -1;
    }
    
    Color do() {
        for (int i = this.H.size() - 1; i >= 0; --i) {
            final m m = this.H.elementAt(i);
            if (m.a) {
                return m.do();
            }
        }
        return Color.gray;
    }
    
    String if() {
        int n = 0;
        final Enumeration<m> elements = (Enumeration<m>)this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                ++n;
                return m.if();
            }
            if (n == 1) {
                break;
            }
        }
        return null;
    }
    
    void if(final int n, final int n2) {
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int n2, final int n3) {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a) {
                m.a(n, n2, n3);
            }
        }
    }
    
    String long() {
        return null;
    }
    
    public void case() {
        final Enumeration<m> elements = this.H.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (!m.a) {
                m.a = true;
            }
        }
    }
    
    boolean for(final Point point, final Point point2) {
        return point.x <= point2.x + 3 && point.x >= point2.x - 3 && point.y <= point2.y + 3 && point.y >= point2.y - 3;
    }
    
    public boolean d() {
        return this.I;
    }
}
