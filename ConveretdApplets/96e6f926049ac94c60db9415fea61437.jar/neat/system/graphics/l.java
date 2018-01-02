// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.pb;
import neat.system.graphics.renderer.e;
import neat.system.gb;
import neat.system.graphics.renderer.j;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import neat.system.f;
import neat.system.graphics.renderer.g;

public class l extends g
{
    private static f w;
    private static /* synthetic */ Class x;
    private static String[] z;
    
    public void a(final g g, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        super.a(g, n, n2, n3, n4, n5, n6, n7, n8);
        Image image;
        if (g instanceof l) {
            image = ((l)g).a();
        }
        else {
            if (!(g instanceof k)) {
                throw new RuntimeException(l.z[1] + g.getClass());
            }
            image = ((k)g).a();
        }
        final Graphics graphics = this.a().getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.drawImage(image, n, n2, n3, n4, n5, n6, n7, n8, null);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Graphics graphics = this.a().getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.setColor(new Color(n5));
        graphics.fillRect(n, n2, n3, n4);
    }
    
    public Image a() {
        final j e = this.e();
        if (!(e instanceof c)) {
            return null;
        }
        return ((c)e).a();
    }
    
    public static g a(final gb gb, final int n, final int n2) {
        final l l = (l)neat.system.graphics.l.w.a();
        l.a(e.a(gb, n, n2));
        return l;
    }
    
    public static g a(final gb gb, final int n, final int n2, final int n3) {
        final l l = (l)neat.system.graphics.l.w.a();
        l.a(e.a(gb, n, n2, n3));
        return l;
    }
    
    public static g a(final pb pb, final e e) {
        final l l = (l)neat.system.graphics.l.w.a();
        l.a(e);
        return l;
    }
    
    public void f() {
        l.w.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        final e a = this.a();
        if (a == null) {
            throw new RuntimeException(l.z[0]);
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
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "&\u0010\u0013\tU)\u001bV\u0001S%\u001eT\u0000".toCharArray();
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
                            c2 = 'H';
                            break;
                        }
                        case 1: {
                            c2 = '\u007f';
                            break;
                        }
                        case 2: {
                            c2 = '3';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = ':';
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
        final char[] charArray2 = "!\u0011E\u0004V!\u001b\u0013\u0016U=\rP\u0000\u001a!\u0012R\u0002_".toCharArray();
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
                            c4 = 'H';
                            break;
                        }
                        case 1: {
                            c4 = '\u007f';
                            break;
                        }
                        case 2: {
                            c4 = '3';
                            break;
                        }
                        case 3: {
                            c4 = 'e';
                            break;
                        }
                        default: {
                            c4 = ':';
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
        final char[] charArray3 = "&\u001aR\u0011\u0014;\u0006@\u0011_%QT\u0017[8\u0017Z\u0006If\u0013".toCharArray();
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
                            c6 = 'H';
                            break;
                        }
                        case 1: {
                            c6 = '\u007f';
                            break;
                        }
                        case 2: {
                            c6 = '3';
                            break;
                        }
                        case 3: {
                            c6 = 'e';
                            break;
                        }
                        default: {
                            c6 = ':';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                l.z = z;
                l.w = new f((l.x != null) ? l.x : (l.x = a(l.z[2])));
                return;
            }
            continue;
        }
    }
}
