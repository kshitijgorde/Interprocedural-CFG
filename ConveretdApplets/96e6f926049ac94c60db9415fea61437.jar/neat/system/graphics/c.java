// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import java.awt.image.ImageObserver;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import neat.system.f;
import neat.system.graphics.renderer.j;
import neat.system.cb;

public class c implements cb, j
{
    private static f a;
    private Image b;
    private Graphics c;
    private byte[] d;
    private int e;
    private AudioClip f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public void a(final Image b) {
        this.b = b;
    }
    
    public Image a() {
        return this.b;
    }
    
    public void a(final AudioClip f) {
        this.f = f;
    }
    
    public AudioClip b() {
        return this.f;
    }
    
    public int a() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getWidth(null);
    }
    
    public int b() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getHeight(null);
    }
    
    public static c c() {
        return (c)c.a.a();
    }
    
    public void f() {
        neat.system.graphics.c.a.a(this);
    }
    
    public void g() {
        this.e = 0;
    }
    
    public void h() {
        if (this.b != null) {
            this.b.flush();
            this.b = null;
        }
        this.c = null;
        this.d = null;
        this.f = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public c() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
    }
    
    static {
        final char[] charArray = "vZ??fkF-?-u\u001199)hW7(;6\\".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0018';
                            break;
                        }
                        case 1: {
                            c2 = '?';
                            break;
                        }
                        case 2: {
                            c2 = '^';
                            break;
                        }
                        case 3: {
                            c2 = 'K';
                            break;
                        }
                        default: {
                            c2 = 'H';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                c.z = new String(charArray).intern();
                c.a = new f((c.g != null) ? c.g : (c.g = a(c.z)));
                return;
            }
            continue;
        }
    }
}
