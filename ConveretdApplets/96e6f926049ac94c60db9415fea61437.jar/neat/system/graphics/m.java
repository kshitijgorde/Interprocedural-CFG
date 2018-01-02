// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.graphics.renderer.j;
import neat.system.graphics.renderer.e;
import neat.system.gb;
import java.awt.Color;
import java.awt.image.ImageObserver;
import neat.system.graphics.renderer.g;
import neat.system.graphics.renderer.a;
import java.awt.Graphics;
import java.awt.Image;
import neat.system.f;
import neat.system.graphics.renderer.h;

public class m extends h
{
    private static f w;
    private boolean x;
    private Image y;
    private Graphics z;
    private a A;
    private static /* synthetic */ Class B;
    private static String[] C;
    
    private void a(final Image y) {
        this.y = y;
        this.z = y.getGraphics();
        this.A = neat.system.graphics.renderer.a.h();
    }
    
    void b(final a a) {
        if (this.A.b()) {
            a.b(this.A);
            this.A.a();
        }
        else {
            a.a();
        }
    }
    
    Image a() {
        return this.y;
    }
    
    boolean b() {
        final boolean x = this.x;
        this.x = false;
        return x;
    }
    
    public void a(final g g, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        super.a(g, n, n2, n3, n4, n5, n6, n7, n8);
        Image image;
        if (g instanceof l) {
            image = ((l)g).a();
        }
        else {
            if (!(g instanceof k)) {
                throw new RuntimeException(m.C[0] + g.getClass());
            }
            image = ((k)g).a();
        }
        this.z.drawImage(image, n, n2, n3, n4, n5, n6, n7, n8, null);
        this.A.b(n, n2, n3 - n, n4 - n2);
        this.x = true;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Graphics z = this.z;
        z.setColor(new Color(n5));
        z.fillRect(n, n2, n3, n4);
        this.A.b(n, n2, n3, n4);
        this.x = true;
    }
    
    public static m a(final gb gb, final int n, final int n2) {
        final m m = (m)neat.system.graphics.m.w.a();
        if (n <= 0 || n2 <= 0) {
            throw new RuntimeException(neat.system.graphics.m.C[2] + n + neat.system.graphics.m.C[1] + n2);
        }
        final e a = e.a(gb, n, n2);
        m.a(a);
        final j d = a.d();
        if (d instanceof c) {
            m.a(((c)d).a());
        }
        m.x = false;
        return m;
    }
    
    public void f() {
        m.w.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
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
        final String[] c = new String[4];
        final int n = 0;
        final char[] charArray = "#HY8,#B\u000f*/?TL<`#KN>%".toCharArray();
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
                    final char c2 = charArray[n3];
                    char c3 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c3 = 'J';
                            break;
                        }
                        case 1: {
                            c3 = '&';
                            break;
                        }
                        case 2: {
                            c3 = '/';
                            break;
                        }
                        case 3: {
                            c3 = 'Y';
                            break;
                        }
                        default: {
                            c3 = '@';
                            break;
                        }
                    }
                    charArray[length] = (char)(c2 ^ c3);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        c[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "jNJ0'\"R\u0012".toCharArray();
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
                    final char c4 = charArray2[n7];
                    char c5 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c5 = 'J';
                            break;
                        }
                        case 1: {
                            c5 = '&';
                            break;
                        }
                        case 2: {
                            c5 = '/';
                            break;
                        }
                        case 3: {
                            c5 = 'Y';
                            break;
                        }
                        default: {
                            c5 = '@';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c4 ^ c5);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        c[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "#HY8,#B\u000f=)'CA*)%H\\c`=OK-(w".toCharArray();
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
                    final char c6 = charArray3[n11];
                    char c7 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c7 = 'J';
                            break;
                        }
                        case 1: {
                            c7 = '&';
                            break;
                        }
                        case 2: {
                            c7 = '/';
                            break;
                        }
                        case 3: {
                            c7 = 'Y';
                            break;
                        }
                        default: {
                            c7 = '@';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c6 ^ c7);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        c[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "$CN-n9_\\-%'\bH+!:NF:3dK".toCharArray();
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
                    final char c8 = charArray4[n15];
                    char c9 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c9 = 'J';
                            break;
                        }
                        case 1: {
                            c9 = '&';
                            break;
                        }
                        case 2: {
                            c9 = '/';
                            break;
                        }
                        case 3: {
                            c9 = 'Y';
                            break;
                        }
                        default: {
                            c9 = '@';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c8 ^ c9);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                c[n13] = new String(charArray4).intern();
                m.C = c;
                m.w = new f((m.B != null) ? m.B : (m.B = a(m.C[3])));
                return;
            }
            continue;
        }
    }
}
