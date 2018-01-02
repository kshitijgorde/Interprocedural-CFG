// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.qb;
import neat.kb;
import neat.system.gb;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import neat.system.f;

public class p implements h
{
    static a a;
    private static f b;
    private byte[] c;
    private int d;
    private int e;
    private InputStream f;
    private static Class g;
    private static String z;
    
    InputStream a(final boolean b) {
        if (b) {
            return null;
        }
        if (this.f == null) {
            if (this.c == null || this.e == 0) {
                return null;
            }
            (this.f = new ByteArrayInputStream(this.c, this.d, this.e)).mark(0);
        }
        return this.f;
    }
    
    public void a(final gb gb, final kb kb) {
        final qb d = gb.d(kb);
        if (d == null) {
            return;
        }
        if (d.a() < 8) {
            return;
        }
        this.c = d.b();
        final byte[] c = new byte[d.a()];
        for (int i = 0; i < c.length; ++i) {
            c[i] = this.c[i];
        }
        d.c();
        this.c = c;
        this.d = ((this.c[4] & 0xFF) << 24 | (this.c[5] & 0xFF) << 16 | (this.c[6] & 0xFF) << 8 | (this.c[7] & 0xFF));
        this.e = d.a() - this.d;
        this.f = null;
    }
    
    public h a() {
        final p a = a();
        a.c = this.c;
        a.d = this.d;
        a.e = this.e;
        a.f = this.f;
        return a;
    }
    
    public static p a() {
        return (p)p.b.a();
    }
    
    public void f() {
        p.b.a(this);
    }
    
    public void g() {
        this.d = 0;
        this.e = 0;
        this.f = p.a;
    }
    
    public void h() {
        this.c = null;
        this.f = null;
    }
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public p() {
        this.c = null;
        this.f = null;
    }
    
    static {
        final char[] charArray = "q=F\\4l!T\\\u007frv@Z{o0NKi1(".toCharArray();
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
                            c2 = '\u001f';
                            break;
                        }
                        case 1: {
                            c2 = 'X';
                            break;
                        }
                        case 2: {
                            c2 = '\'';
                            break;
                        }
                        case 3: {
                            c2 = '(';
                            break;
                        }
                        default: {
                            c2 = '\u001a';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                p.z = new String(charArray).intern();
                p.a = new a();
                p.b = new f((p.g != null) ? p.g : (p.g = a(p.z)));
                return;
            }
            continue;
        }
    }
}
