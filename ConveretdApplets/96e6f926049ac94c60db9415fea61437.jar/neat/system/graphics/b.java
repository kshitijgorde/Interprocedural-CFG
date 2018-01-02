// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.graphics.renderer.j;
import neat.kb;
import neat.system.gb;
import java.applet.AudioClip;
import neat.system.f;

public class b implements h
{
    private static f a;
    private AudioClip b;
    private static /* synthetic */ Class c;
    private static String z;
    
    AudioClip a() {
        return this.b;
    }
    
    public void a(final gb gb, final kb kb) {
        final j c = gb.c(kb);
        if (c instanceof c) {
            this.b = ((c)c).b();
        }
        if (c != null) {
            c.f();
        }
    }
    
    public h a() {
        final b b = b();
        b.b = this.b;
        return b;
    }
    
    public static b b() {
        return (b)b.a.a();
    }
    
    public void f() {
        neat.system.graphics.b.a.a(this);
    }
    
    public void g() {
    }
    
    public void h() {
        this.b = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public b() {
        this.b = null;
    }
    
    static {
        final char[] charArray = "*x%6A7d76\n)3#0\u000e4u-!\u001cj\u007f".toCharArray();
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
                            c2 = 'D';
                            break;
                        }
                        case 1: {
                            c2 = '\u001d';
                            break;
                        }
                        case 2: {
                            c2 = 'D';
                            break;
                        }
                        case 3: {
                            c2 = 'B';
                            break;
                        }
                        default: {
                            c2 = 'o';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                b.z = new String(charArray).intern();
                b.a = new f((b.c != null) ? b.c : (b.c = a(b.z)));
                return;
            }
            continue;
        }
    }
}
