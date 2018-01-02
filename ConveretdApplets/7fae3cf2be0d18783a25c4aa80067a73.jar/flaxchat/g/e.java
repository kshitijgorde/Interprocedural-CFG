// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import flaxchat.a.j;
import java.util.Enumeration;
import java.awt.Component;
import flaxchat.a.k;
import java.awt.Dimension;
import flaxchat.a.d;
import flaxchat.d.b;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Hashtable;
import flaxchat.f.g;
import flaxchat.n;

public class e extends c
{
    private static String[] z;
    
    public e(final n n) {
        super(n);
    }
    
    public void a(final Object o, String nextToken, final g g, String s, final Hashtable hashtable) {
        final int b = c.b;
        if (this.a(s, e.z[3])) {
            s = s.substring(e.z[3].length()).trim();
            super.a.l().i(s);
            return;
        }
        if (this.a(s, e.z[18])) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            s = stringTokenizer.nextToken();
            super.a.a(new g("", stringTokenizer.nextToken()));
            return;
        }
        if (this.a(s, e.z[8])) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(s);
            s = stringTokenizer2.nextToken();
            nextToken = stringTokenizer2.nextToken();
            super.a.e(nextToken);
            return;
        }
        if (this.a(s, e.z[21])) {
            super.a.l().f(s.substring(s.indexOf(" ")));
            return;
        }
        if (this.a(s, e.z[12])) {
            super.a.h().v(g.g());
            super.a.e().c(e.z[23] + g.g() + e.z[10]);
            return;
        }
        if (this.a(s, e.z[14])) {
            this.a();
            return;
        }
        if (this.a(s, e.z[22])) {
            String s2 = e.z[17];
            Label_0357: {
                if (super.a.h().a()) {
                    super.a.h().c(false);
                    if (b == 0) {
                        break Label_0357;
                    }
                }
                super.a.h().c(true);
                s2 = e.z[5];
            }
            super.a.e().c(e.z[19] + s2);
            return;
        }
        if (this.a(s, e.z[16])) {
            super.a.h().w(g.g());
            super.a.e().c(e.z[6] + g.g() + e.z[11]);
            return;
        }
        if (this.a(s, e.z[7])) {
            final String[] array = new String[3];
            final StringTokenizer stringTokenizer3 = new StringTokenizer(s);
            int n = 0;
            while (true) {
                Label_0517: {
                    if (b == 0) {
                        break Label_0517;
                    }
                    array[n] = stringTokenizer3.nextElement().toString();
                    ++n;
                }
                if (n >= 3) {
                    super.a.h().a(array[1], e.z[20] + array[2]);
                    super.a.e().c(e.z[13] + array[2] + "]");
                    return;
                }
                continue;
            }
        }
        else {
            if (!this.a(s, e.z[9])) {
                this.a(s);
                return;
            }
            final String[] array2 = new String[3];
            final StringTokenizer stringTokenizer4 = new StringTokenizer(s);
            int n2 = 0;
            while (true) {
                Label_0655: {
                    if (b == 0) {
                        break Label_0655;
                    }
                    array2[n2] = stringTokenizer4.nextElement().toString();
                    ++n2;
                }
                if (n2 >= 3) {
                    Label_0791: {
                        if (array2[2].toUpperCase(Locale.US).equals(e.z[15])) {
                            super.a.h().a(array2[1], "\u0001" + array2[2].toUpperCase(Locale.US) + " " + System.currentTimeMillis() / 1000L + "\u0001");
                            if (b == 0) {
                                break Label_0791;
                            }
                        }
                        super.a.h().a(array2[1], "\u0001" + array2[2].toUpperCase(Locale.US) + "\u0001");
                    }
                    super.a.e().c(e.z[4] + array2[1] + " " + array2[2] + "]");
                    return;
                }
                continue;
            }
        }
    }
    
    private void a(final String s) {
        if (this.a(s, e.z[28])) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, e.z[26]);
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                super.a.e().a(flaxchat.d.b.b(e.z[25], e.z[23]), "<" + super.a.h().g() + e.z[27] + this.a(stringTokenizer));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        super.a.h().e(s);
    }
    
    private void a() {
        final int b = c.b;
        final d d = new d();
        d.a(new Dimension(200, 150));
        final Enumeration b2 = super.a.l().p().b();
        while (true) {
            Label_0064: {
                if (b == 0) {
                    break Label_0064;
                }
                d.add(b2.nextElement());
            }
            if (b2.hasMoreElements()) {
                continue;
            }
            break;
        }
        if (d.getItemCount() == 0) {
            k.b(super.a, e.z[0]);
            return;
        }
        k.a(super.a, d, e.z[2], new String[] { e.z[1] });
    }
    
    private String a(final StringTokenizer stringTokenizer) {
        final int b = c.b;
        final j j = new j();
        while (true) {
            Label_0032: {
                if (b == 0) {
                    break Label_0032;
                }
                j.a(stringTokenizer.nextElement());
                j.a(' ');
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return j.toString();
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
        return new String[] { e.z[3], e.z[16], e.z[18], e.z[8], e.z[21], e.z[12], e.z[14], e.z[24], e.z[9] };
    }
    
    static {
        e.z = new String[] { z(z("B\t`\u0019ck\u000e'\u0010ft\u0013b\u0012f}Ge\u0013|)")), z(z("W\u0002i\u001fju\u0002~\u0015/L\u0006w\u001d{")), z(z("B\t`\u0019ck\u000e'0ft\u0013b\u0012f}")), z(z("t\fn\u0012")), z(z("\u0004W3\\\"9G\\")), z(z("l\u0006w\u001dcn")), z(z("\u0004W3")), z(z("t\br\u0012k")), z(z("w\u0006u\b")), z(z("d\u0013d\f")), z(z("'\u0002i\u001bjk\u000e'\u0017nk\u0003n\u000efk\u0003nR")), z(z("'\b}\u0019c'\u0017b\u0012lb\u0015b\u000ffi\u000e'\u0017nw\u0006c\u0015hn\tn\u0006/f\tc\u001da'\u000es\u0015mf\u0015b\u0012/b\t`\u0019ck\u0002i\u0019lb\fs\u0015})")), z(z("c\u0002a\u0013}d\u0002")), z(z("\u0004W3\\\\b\u0014'\u001b\u00f9i\u0003b\u000efk\u0003n\\T")), z(z("a\bu\u001fjK\u000et\b")), z(z("W.I;")), z(z("a\bu\u001fj")), z(z("f\u0004n\u0017")), z(z("v\u0012b\u000ev")), z(z("H\u001db\u0010/J\u0002t\u001de',f\u001ezkG=\\\f7S'")), z(z("\u00117K=V'")), z(z("b\td\u0013kb")), z(z("h\u001db\u0010Bb\u0014f\u0016")), z(z("\u0004W4")), z(z("c\u0002a\u001dzk\u0013")), z(z("t\u0002i\bBt\u0000D\u0013ch\u0015")), z(z("']")), z(z("9G")), z(z("w\u0015n\nbt\u0000")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000f';
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
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = 'g';
                    break;
                }
                case 2: {
                    c2 = '\u0007';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
