// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.e.i;
import java.util.Enumeration;
import java.awt.Component;
import flaxchat.e.j;
import java.awt.Dimension;
import flaxchat.e.d;
import flaxchat.i.b;
import java.util.Locale;
import java.util.StringTokenizer;
import flaxchat.h.g;
import flaxchat.m;

public class e extends c
{
    private static String[] z;
    
    public e(final m m) {
        super(m);
    }
    
    public void a(final Object o, String nextToken, final g g, String s) {
        final boolean b = c.b;
        if (this.a(s, e.z[20])) {
            s = s.substring(e.z[20].length()).trim();
            super.a.l().i(s);
            return;
        }
        if (this.a(s, e.z[13])) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            s = stringTokenizer.nextToken();
            super.a.a(new g("", stringTokenizer.nextToken()));
            return;
        }
        if (this.a(s, e.z[15])) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(s);
            s = stringTokenizer2.nextToken();
            nextToken = stringTokenizer2.nextToken();
            super.a.e(nextToken);
            return;
        }
        if (this.a(s, e.z[8])) {
            super.a.l().f(s.substring(s.indexOf(" ")));
            return;
        }
        if (this.a(s, e.z[11])) {
            super.a.h().v(g.d());
            super.a.e().c(e.z[6] + g.d() + e.z[17]);
            return;
        }
        if (this.a(s, e.z[12])) {
            this.a();
            return;
        }
        if (this.a(s, e.z[14])) {
            super.a.h().w(g.d());
            super.a.e().c(e.z[16] + g.d() + e.z[19]);
            return;
        }
        if (!this.a(s, e.z[10])) {
            this.a(s);
            return;
        }
        final String[] array = new String[3];
        final StringTokenizer stringTokenizer3 = new StringTokenizer(s);
        int n = 0;
        while (true) {
            Label_0417: {
                if (!b) {
                    break Label_0417;
                }
                array[n] = stringTokenizer3.nextElement().toString();
                ++n;
            }
            if (n >= 3) {
                Label_0553: {
                    if (array[2].toUpperCase(Locale.US).equals(e.z[9])) {
                        super.a.h().a(array[1], "\u0001" + array[2].toUpperCase(Locale.US) + " " + System.currentTimeMillis() / 1000L + "\u0001");
                        if (!b) {
                            break Label_0553;
                        }
                    }
                    super.a.h().a(array[1], "\u0001" + array[2].toUpperCase(Locale.US) + "\u0001");
                }
                super.a.e().c(e.z[18] + array[1] + " " + array[2] + "]");
                return;
            }
            continue;
        }
    }
    
    private void a(final String s) {
        if (this.a(s, e.z[7])) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, e.z[3]);
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                super.a.e().a(flaxchat.i.b.b(e.z[5], e.z[6]), "<" + super.a.h().g() + e.z[4] + this.a(stringTokenizer));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        super.a.h().e(s);
    }
    
    private void a() {
        final boolean b = c.b;
        final d d = new d();
        d.a(new Dimension(200, 150));
        final Enumeration a = super.a.l().o().a();
        while (true) {
            Label_0064: {
                if (!b) {
                    break Label_0064;
                }
                d.add(a.nextElement());
            }
            if (a.hasMoreElements()) {
                continue;
            }
            break;
        }
        if (d.getItemCount() == 0) {
            j.b(super.a, e.z[0]);
            return;
        }
        j.a(super.a, d, e.z[2], new String[] { e.z[1] });
    }
    
    private String a(final StringTokenizer stringTokenizer) {
        final boolean b = c.b;
        final i i = new i();
        while (true) {
            Label_0032: {
                if (!b) {
                    break Label_0032;
                }
                i.a(stringTokenizer.nextElement());
                i.a(' ');
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return i.toString();
            }
            continue;
        }
    }
    
    public boolean a(final String s, final String s2) {
        return s.regionMatches(true, 0, s2, 0, s2.length());
    }
    
    public String a() {
        return "c";
    }
    
    public String[] b() {
        return new String[] { e.z[20], e.z[14], e.z[13], e.z[15], e.z[8], e.z[11], e.z[12], e.z[21], e.z[10] };
    }
    
    static {
        e.z = new String[] { z(z("\u001c\r5p:5\nry?*\u00177{?#C0z%w")), z(z("\t\u0006<v3+\u0006+|v\u0012\u0002\"t\"")), z(z("\u001c\r5p:5\nrY?*\u00177{?#")), z(z("yY")), z(z("gC")), z(z("*\u0006<a\u001b*\u0004\u0011z:6\u0011")), z(z("ZSa")), z(z(")\u0011;c;*\u0004")), z(z("<\r1z2<")), z(z("\t*\u001cR")), z(z(":\u00171e")), z(z("=\u00064z$:\u0006")), z(z("?\f v3\u0015\n!a")), z(z("(\u00167g/")), z(z("?\f v3")), z(z(")\u0002 a")), z(z("ZSf")), z(z("y\u0006<r35\nr~75\u0007;g?5\u0007;;")), z(z("ZSf5{gC\t")), z(z("y\f(p:y\u00137{5<\u00117f?7\nr~7)\u00026|10\r;ov8\r6t8y\n&|48\u00117{v<\r5p:5\u0006<p5<\b&|$w")), z(z("*\b;{")), z(z("=\u00064t#5\u0017")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'V';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'Y';
                    break;
                }
                case 1: {
                    c2 = 'c';
                    break;
                }
                case 2: {
                    c2 = 'R';
                    break;
                }
                case 3: {
                    c2 = '\u0015';
                    break;
                }
                default: {
                    c2 = 'V';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
