// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import sun.audio.AudioPlayer;
import java.io.IOException;
import java.io.InputStream;
import neat.system.f;

public class g extends e
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    private void a(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.reset();
            }
            catch (IOException ex) {}
            AudioPlayer.player.start(inputStream);
        }
    }
    
    private void b(final InputStream inputStream) {
        if (inputStream != null) {
            AudioPlayer.player.stop(inputStream);
        }
    }
    
    protected h c() {
        return p.a();
    }
    
    protected void c(final d d) {
        final h a = d.a();
        if (a instanceof p) {
            this.a(((p)a).a(d.b()));
        }
    }
    
    protected void d(final d d) {
        final h a = d.a();
        if (a instanceof p) {
            this.b(((p)a).a(d.b()));
        }
    }
    
    public static g a() {
        return (g)g.j.a();
    }
    
    public void f() {
        if (!this.d()) {
            return;
        }
        g.j.a(this);
    }
    
    public void g() {
        super.g();
        this.a(p.a);
    }
    
    public void h() {
        this.b(p.a);
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
        final char[] charArray = "\u0013)0[k\u000e5\"[ \u0010b6]$\r$8L6S+".toCharArray();
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
                            c2 = '}';
                            break;
                        }
                        case 1: {
                            c2 = 'L';
                            break;
                        }
                        case 2: {
                            c2 = 'Q';
                            break;
                        }
                        case 3: {
                            c2 = '/';
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
                g.z = new String(charArray).intern();
                g.j = new f((g.k != null) ? g.k : (g.k = a(g.z)));
                return;
            }
            continue;
        }
    }
}
