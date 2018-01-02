// 
// Decompiled by Procyon v0.5.30
// 

package a.a.c;

import a.b.o.c.e;
import a.b.h.c.d;
import a.b.i.k;
import a.b.i.i;
import a.b.e.g;
import a.b.g.b;
import a.b.e.c;

public class f extends a.b.e.f
{
    private static org.a.c.f p;
    private c q;
    private Object r;
    private static String[] z;
    
    public f() {
        super(f.z[1]);
        this.r = new Integer(0);
        if (f.p == null) {
            f.p = a.b.g.b.a().d().a(this.getClass().getName());
        }
        try {
            this.a(new a.a.c.b());
        }
        catch (c q) {
            f.p.c(f.z[0], q);
            this.q = q;
        }
        final i a = this.a();
        if (a != null && a instanceof b) {
            this.a((b)a);
        }
    }
    
    protected d[] b(final k[] array) throws c {
        if (this.q != null) {
            throw new c(f.z[2], this.q);
        }
        d[] b = null;
        c c = null;
        synchronized (this.r) {
            try {
                b = super.b(array);
            }
            catch (c c2) {
                c = c2;
            }
        }
        if (c != null) {
            throw c;
        }
        if (b == null) {
            return null;
        }
        for (int n = 0; n < array.length && n < b.length; ++n) {
            try {
                ((a.b.o.c.d)b[n].a()).a(2);
                ((a.b.o.c.d)b[n].a()).b(1);
                ((a.b.o.c.e)b[n].a()).a();
                ((a.b.o.c.e)b[n].a()).a(2);
                ((a.b.o.c.e)b[n].a()).a();
            }
            catch (ClassCastException ex) {}
        }
        return b;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u001d!\f4hx:\u0010{s6:\n2{4:\u0004:n1<\u0010a".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'X';
                    break;
                }
                case 1: {
                    c2 = 'S';
                    break;
                }
                case 2: {
                    c2 = '~';
                    break;
                }
                case 3: {
                    c2 = '[';
                    break;
                }
                default: {
                    c2 = '\u001a';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\b\u001c-\u000f".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'X';
                    break;
                }
                case 1: {
                    c4 = 'S';
                    break;
                }
                case 2: {
                    c4 = '~';
                    break;
                }
                case 3: {
                    c4 = '[';
                    break;
                }
                default: {
                    c4 = '\u001a';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\r=\u001f9v=s\n4:1=\u0017/s9?\u0017!\u007fx'\u0016>:;<\u0011)~1=\u001f/u*}".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'X';
                    break;
                }
                case 1: {
                    c6 = 'S';
                    break;
                }
                case 2: {
                    c6 = '~';
                    break;
                }
                case 3: {
                    c6 = '[';
                    break;
                }
                default: {
                    c6 = '\u001a';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        f.z = z;
    }
}
