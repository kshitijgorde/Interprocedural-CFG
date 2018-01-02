// 
// Decompiled by Procyon v0.5.30
// 

package a.a.e;

import a.b.i.g;
import com.md.request.DataSpecs;
import a.b.i.a;
import a.b.i.k;
import a.b.f.h;
import a.b.i.d;
import a.b.i.j;

public class b extends j
{
    private d a;
    private static String[] z;
    
    public b(final a.b.f.h h) {
        this.a = null;
        this.a(h);
    }
    
    public void a(final a.b.f.h h) {
        this.a = new d(h);
    }
    
    public byte[] a(final k[] array) throws a {
        if (array == null) {
            throw new a(b.z[4]);
        }
        final DataSpecs[] array2 = new DataSpecs[array.length];
        DataSpecs dataSpecs = null;
        try {
            for (int i = 0; i < array.length; ++i) {
                final g g = (g)array[i];
                try {
                    dataSpecs = (DataSpecs)g;
                }
                catch (ClassCastException ex2) {
                    dataSpecs = new DataSpecs(g.a(), g.b(), g.c());
                }
                if (dataSpecs == null) {
                    throw new a(b.z[5]);
                }
                if (this.a == null) {
                    throw new a(b.z[2]);
                }
                this.a.a(dataSpecs);
                array2[i] = dataSpecs;
            }
            return this.b(array2);
        }
        catch (ClassCastException ex3) {
            throw new a(b.z[3]);
        }
        catch (NullPointerException ex) {
            throw new a(b.z[6] + dataSpecs.a() + b.z[0] + dataSpecs.b() + b.z[1], ex);
        }
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = "H\"kA)Ank\\9AKN\u000fe".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'a';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = '\n';
                    break;
                }
                case 3: {
                    c2 = '/';
                    break;
                }
                default: {
                    c2 = 'M';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "H\"eIm\u0015jo\u000f\u0005\bebC,\u000ffo]\u001f\u0004s\u007fJ>\u0015KdI\"\u0013ok[$\u000el*L,\u000fle[m\u0003g*A8\rn1\u000f#\u000e\"xJ<\u0014gy[m\u0002cd\u000f/\u0004\"mJ#\u0004pk[(\u0005,".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'a';
                    break;
                }
                case 1: {
                    c4 = '\u0002';
                    break;
                }
                case 2: {
                    c4 = '\n';
                    break;
                }
                case 3: {
                    c4 = '/';
                    break;
                }
                default: {
                    c4 = 'M';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "/m*k,\u0015cY[8\u0007do]m\u0002m\u007fC)A`o\u000f+\u000ewdKm\u0016k~Gm\u0016jcL%Ave\u000f+\bnf\u000f9\tg*](\u0010wo\\9Ark],\fg~J?\u0012,".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'a';
                    break;
                }
                case 1: {
                    c6 = '\u0002';
                    break;
                }
                case 2: {
                    c6 = '\n';
                    break;
                }
                case 3: {
                    c6 = '/';
                    break;
                }
                default: {
                    c6 = 'M';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "5jo\u000f\u001f\u0004s\u007fJ>\u0015Rk],\fg~J?\u0012\"eM'\u0004a~\u000f=\u0000qyJ)Ave\u000f9\tg*}(\u0010wo\\9&gdJ?\u0000ve]m\u0016cy\u000f#\u000ev*N#Akd\\9\u0000liJm\u000ed*k$\u0012rfN43g{Z(\u0012vCA+\u000epgN9\bmd\u0003m\u0003w~\u000f>\tm\u007fC)AjkY(A`oJ#O".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'a';
                    break;
                }
                case 1: {
                    c8 = '\u0002';
                    break;
                }
                case 2: {
                    c8 = '\n';
                    break;
                }
                case 3: {
                    c8 = '/';
                    break;
                }
                default: {
                    c8 = 'M';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "/m*\\8\u0011re]9Ade]m\u000fwfCm\u0013g{Z(\u0012v*K,\u0015c$".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'a';
                    break;
                }
                case 1: {
                    c10 = '\u0002';
                    break;
                }
                case 2: {
                    c10 = '\n';
                    break;
                }
                case 3: {
                    c10 = '/';
                    break;
                }
                default: {
                    c10 = 'M';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\"cdA\"\u0015\"mJ#\u0004pk[(Ac*](\u0010wo\\9Adx@ Ac*A8\rn*_,\u0013cgJ9\u0004py\u000f$\u000fr\u007f[c".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'a';
                    break;
                }
                case 1: {
                    c12 = '\u0002';
                    break;
                }
                case 2: {
                    c12 = '\n';
                    break;
                }
                case 3: {
                    c12 = '/';
                    break;
                }
                default: {
                    c12 = 'M';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "-miN9\bmd\u000f;\u0000n\u007fJmI".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'a';
                    break;
                }
                case 1: {
                    c14 = '\u0002';
                    break;
                }
                case 2: {
                    c14 = '\n';
                    break;
                }
                case 3: {
                    c14 = '/';
                    break;
                }
                default: {
                    c14 = 'M';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        b.z = z;
    }
}
