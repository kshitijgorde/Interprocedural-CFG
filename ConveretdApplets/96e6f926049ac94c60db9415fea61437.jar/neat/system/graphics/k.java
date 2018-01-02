// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.graphics.renderer.e;
import neat.system.gb;
import neat.system.graphics.renderer.j;
import java.awt.Image;
import neat.system.f;
import neat.system.graphics.renderer.g;

public class k extends g
{
    private static f w;
    private m x;
    private static /* synthetic */ Class y;
    private static String[] z;
    
    void a(final m x) {
        if (x == null) {
            throw new RuntimeException(k.z[2]);
        }
        if (this.x != null) {
            throw new RuntimeException(k.z[3]);
        }
        this.x = x;
    }
    
    public void a(final g g, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (!(g instanceof l)) {
            throw new RuntimeException(k.z[1]);
        }
        this.x.a(g, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.x.a(n, n2, n3, n4, n5);
    }
    
    public Image a() {
        final j e = this.e();
        if (!(e instanceof c)) {
            return null;
        }
        return ((c)e).a();
    }
    
    public static k a(final gb gb, final int n, final int n2) {
        final k k = (k)neat.system.graphics.k.w.a();
        k.a(e.a(gb, n, n2));
        return k;
    }
    
    public static k a(final gb gb, final e e) {
        final k k = (k)neat.system.graphics.k.w.a();
        k.a(e);
        return k;
    }
    
    public void f() {
        k.w.a(this);
    }
    
    public void g() {
        super.g();
        this.x = null;
    }
    
    public void h() {
        final e a = this.a();
        if (a == null) {
            throw new RuntimeException(k.z[0]);
        }
        a.a();
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "y\u001eQ*0v\u0015\u0014\u0002\u0016z\u0010\u0016\u0003".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = 'q';
                            break;
                        }
                        case 2: {
                            c2 = 'q';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
                            break;
                        }
                        default: {
                            c2 = '_';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "t\u001e\u0001\u001f\u007ft\u0010\u001fF0y\u001d\bF=rQ\u0010\u0016/{\u0018\u0014\u0002\u007fc\u001eQ5/e\u0018\u0005\u0003,".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0017';
                            break;
                        }
                        case 1: {
                            c4 = 'q';
                            break;
                        }
                        case 2: {
                            c4 = 'q';
                            break;
                        }
                        case 3: {
                            c4 = 'f';
                            break;
                        }
                        default: {
                            c4 = '_';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "~\u001f\u0007\u00073~\u0015Q\u0016>e\u0010\u001c\u0003+r\u0003".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0017';
                            break;
                        }
                        case 1: {
                            c6 = 'q';
                            break;
                        }
                        case 2: {
                            c6 = 'q';
                            break;
                        }
                        case 3: {
                            c6 = 'f';
                            break;
                        }
                        default: {
                            c6 = '_';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "v\u001d\u0003\u0003>s\bQ\u000e>dQ\u0010F\rr\u001f\u0015\u0003-r\u00035\u000f,g\u001d\u0010\u001f".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0017';
                            break;
                        }
                        case 1: {
                            c8 = 'q';
                            break;
                        }
                        case 2: {
                            c8 = 'q';
                            break;
                        }
                        case 3: {
                            c8 = 'f';
                            break;
                        }
                        default: {
                            c8 = '_';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "y\u0014\u0010\u0012qd\b\u0002\u0012:z_\u0016\u0014>g\u0019\u0018\u0005,9\u001a".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0017';
                            break;
                        }
                        case 1: {
                            c10 = 'q';
                            break;
                        }
                        case 2: {
                            c10 = 'q';
                            break;
                        }
                        case 3: {
                            c10 = 'f';
                            break;
                        }
                        default: {
                            c10 = '_';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                k.z = z;
                k.w = new f((k.y != null) ? k.y : (k.y = a(k.z[4])));
                return;
            }
            continue;
        }
    }
}
