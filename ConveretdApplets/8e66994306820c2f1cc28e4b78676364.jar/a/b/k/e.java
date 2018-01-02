// 
// Decompiled by Procyon v0.5.30
// 

package a.b.k;

import java.io.IOException;
import a.b.h.a.b;
import a.b.p.d;

public class e extends d
{
    private static String[] z;
    
    public e(final a.b.f.e e) {
        super(e);
    }
    
    public void a(final a.b.h.a.b b) throws a {
        if (b == null) {
            throw new a(e.z[0]);
        }
        try {
            this.a(b.a());
        }
        catch (a.b.c.a a) {
            throw new a(e.z[1], a);
        }
    }
    
    protected void a(final byte[] array) throws c {
        if (array == null) {
            throw new c(e.z[3]);
        }
        try {
            super.a().a(array);
        }
        catch (IOException ex) {
            throw new c(e.z[2], ex);
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "%\tU%V\u0007HH&\u0013\u0016\u0006O=F\u0005\u000e\u001c-R\u0017\t\u001c/A\f\u0005\u001c;V\u0010\u0018S'@\u0006HX(G\u0002S\u001c;V\u0010\u0018S'@\u0006HX @\u0013\u0004]0\u0013\u0010\rHiZ\u0010HR<_\u000fF".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'c';
                    break;
                }
                case 1: {
                    c2 = 'h';
                    break;
                }
                case 2: {
                    c2 = '<';
                    break;
                }
                case 3: {
                    c2 = 'I';
                    break;
                }
                default: {
                    c2 = '3';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "%\tU%V\u0007HH&\u0013\u0016\u0006O=F\u0005\u000e\u001c-R\u0017\t\u001c/A\f\u0005\u001c=[\u0006HN,@\u0013\u0007R:VC\u0007^#V\u0000\u001c\u0012".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'c';
                    break;
                }
                case 1: {
                    c4 = 'h';
                    break;
                }
                case 2: {
                    c4 = '<';
                    break;
                }
                case 3: {
                    c4 = 'I';
                    break;
                }
                default: {
                    c4 = '3';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "&\u001aN&AC\u0001RiP\f\u0006O<^\n\u0006[iG\u000b\r\u001c9R\u0011\u001bY-\u0013\u0007\tH(\u001d".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'c';
                    break;
                }
                case 1: {
                    c6 = 'h';
                    break;
                }
                case 2: {
                    c6 = '<';
                    break;
                }
                case 3: {
                    c6 = 'I';
                    break;
                }
                default: {
                    c6 = '3';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "3\u001aS+_\u0006\u0005\u001c ]C\u000bS'@\u0016\u0005U'TC\u001bY*F\u0011\r\u001c-R\u0017\t\u0007iW\u0002\u001c]i@\u0006\u001c\u001c @C\u0006I%_M".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'c';
                    break;
                }
                case 1: {
                    c8 = 'h';
                    break;
                }
                case 2: {
                    c8 = '<';
                    break;
                }
                case 3: {
                    c8 = 'I';
                    break;
                }
                default: {
                    c8 = '3';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        e.z = z;
    }
}
