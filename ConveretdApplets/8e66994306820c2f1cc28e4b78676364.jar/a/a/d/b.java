// 
// Decompiled by Procyon v0.5.30
// 

package a.a.d;

import a.b.k.a;
import a.b.h.c.d;
import a.b.k.e;
import a.b.k.i;

public class b extends i
{
    private e a;
    private static String z;
    
    public b(final a.b.f.e e) {
        this.a = null;
        this.a(e);
    }
    
    public void a(final a.b.f.e e) {
        this.a = new e(e);
    }
    
    public d[] a(final byte[] array) throws a {
        final a.b.h.a.b[] a = super.a(array);
        if (this.a != null) {
            for (int i = 0; i < a.length; ++i) {
                this.a.a(a[i]);
            }
        }
        final d[] array2 = new d[a.length];
        for (int j = 0; j < a.length; ++j) {
            try {
                array2[j] = a[j].d();
            }
            catch (a.b.o.d d) {
                throw new a(b.z + d.getMessage(), d);
            }
        }
        return array2;
    }
    
    static {
        final char[] charArray = "Q\u001e\u0012\u0016\u0005f_\u001f\u0017\u0004d\u001a\u000e\fJV\u0016\u000f\b\u0006s\u00068\u0019\u001es,\u0019\fP2".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0012';
                    break;
                }
                case 1: {
                    c2 = '\u007f';
                    break;
                }
                case 2: {
                    c2 = '|';
                    break;
                }
                case 3: {
                    c2 = 'x';
                    break;
                }
                default: {
                    c2 = 'j';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        b.z = new String(charArray).intern();
    }
}
