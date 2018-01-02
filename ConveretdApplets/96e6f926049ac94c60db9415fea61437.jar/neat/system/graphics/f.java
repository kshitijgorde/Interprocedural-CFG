// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import java.applet.AudioClip;

public class f extends e
{
    private static neat.system.f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    protected h c() {
        return neat.system.graphics.b.b();
    }
    
    protected void c(final d d) {
        final h a = d.a();
        if (a instanceof b) {
            final AudioClip a2 = ((b)a).a();
            if (a2 != null) {
                if (d.b()) {
                    a2.loop();
                }
                else {
                    a2.play();
                }
            }
        }
    }
    
    protected void d(final d d) {
        final h a = d.a();
        if (a instanceof b) {
            final AudioClip a2 = ((b)a).a();
            if (a2 != null) {
                a2.stop();
            }
        }
    }
    
    public static f a() {
        return (f)f.j.a();
    }
    
    public void f() {
        if (!this.d()) {
            return;
        }
        f.j.a(this);
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
        final char[] charArray = "=tPwk hBw >?Vq$#yX`6}w".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'S';
                            break;
                        }
                        case 1: {
                            c2 = '\u0011';
                            break;
                        }
                        case 2: {
                            c2 = '1';
                            break;
                        }
                        case 3: {
                            c2 = '\u0003';
                            break;
                        }
                        default: {
                            c2 = 'E';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                f.z = new String(charArray).intern();
                f.j = new neat.system.f((f.k != null) ? f.k : (f.k = a(f.z)));
                return;
            }
            continue;
        }
    }
}
