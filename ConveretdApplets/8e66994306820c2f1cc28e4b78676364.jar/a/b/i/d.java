// 
// Decompiled by Procyon v0.5.30
// 

package a.b.i;

import java.io.IOException;
import a.b.c.a;
import a.b.f.h;
import a.b.p.c;

public class d extends c
{
    private static String[] z;
    
    public d(final h h) {
        super(h);
    }
    
    public k a(final k k) throws a.b.i.b {
        if (k == null) {
            throw new a.b.i.b(d.z[2]);
        }
        try {
            k.a(this.a(k.a()));
            return k;
        }
        catch (a a) {
            throw new a.b.i.b(d.z[3], a);
        }
    }
    
    protected byte[] a(final int n) throws a.b.i.c {
        if (super.a() == null) {
            throw new a.b.i.c(d.z[0]);
        }
        try {
            return super.a().a(n);
        }
        catch (IOException ex) {
            throw new a.b.i.c(d.z[1], ex);
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "\u0005te;#\u0011g~,j%pcze.g1>b5t1.lasx6oa|\u007f.laGt+v$fe\nb3xt.f3f13pa{d6oz5u;w 5x4p$ge3l/5r;m/zezl\"vd(-".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'A';
                    break;
                }
                case 1: {
                    c2 = '\u0015';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = 'Z';
                    break;
                }
                default: {
                    c2 = '\u0003';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0007tx6f%5e5#2ad<eaqp.ba|\u007f.lagt+v$fezs gp7f5pc)-".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'A';
                    break;
                }
                case 1: {
                    c4 = '\u0015';
                    break;
                }
                case 2: {
                    c4 = '\u0011';
                    break;
                }
                case 3: {
                    c4 = 'Z';
                    break;
                }
                default: {
                    c4 = '\u0003';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0007tx6f%5e5#2ad<eaqp.ba|\u007f.lagt+v$fezs gp7f5pc)8agt+v$fezs gp7f5pc)# gtzm4y}t".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'A';
                    break;
                }
                case 1: {
                    c6 = '\u0015';
                    break;
                }
                case 2: {
                    c6 = '\u0011';
                    break;
                }
                case 3: {
                    c6 = 'Z';
                    break;
                }
                default: {
                    c6 = '\u0003';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0007tx6f%5e5#2ad<eaqp.ba|\u007f.laay?#3p`/f2a1*b3t|?w$gbt".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'A';
                    break;
                }
                case 1: {
                    c8 = '\u0015';
                    break;
                }
                case 2: {
                    c8 = '\u0011';
                    break;
                }
                case 3: {
                    c8 = 'Z';
                    break;
                }
                default: {
                    c8 = '\u0003';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        d.z = z;
    }
}
