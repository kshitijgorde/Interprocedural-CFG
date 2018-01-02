// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class jb implements cb
{
    private static f a;
    protected Object[] b;
    protected int c;
    private static /* synthetic */ Class d;
    private static String z;
    
    public void a(final Object o) {
        this.a(1);
        this.b[this.c++] = o;
    }
    
    public Object a() {
        if (this.c == 0) {
            return null;
        }
        final Object[] b = this.b;
        final int c = this.c - 1;
        this.c = c;
        final Object o = b[c];
        this.b[this.c] = null;
        return o;
    }
    
    public void b() {
        for (int i = 0; i < this.c; ++i) {
            this.b[i] = null;
        }
        this.c = 0;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof jb)) {
            return false;
        }
        final jb jb = (jb)o;
        if (jb.c != this.c) {
            return false;
        }
        for (int i = 0; i < this.c; ++i) {
            if (!this.b[i].equals(jb.b[i])) {
                return false;
            }
        }
        return true;
    }
    
    public boolean c() {
        return this.c == 0;
    }
    
    private void a(final int n) {
        if (this.c + n <= this.b.length) {
            return;
        }
        final Object[] c = jb.a.c(this.c + n + 5);
        System.arraycopy(this.b, 0, c, 0, this.c);
        jb.a.a(this.b);
        this.b = c;
    }
    
    public static jb d() {
        return (jb)jb.a.a();
    }
    
    public void f() {
        jb.a.a(this);
    }
    
    public void g() {
    }
    
    public void h() {
        this.b();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public jb() {
        this.b = jb.a.c(5);
        this.c = 0;
    }
    
    static {
        final char[] charArray = "*\u001dN~\u0001.\u001a".toCharArray();
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
                            c2 = 'x';
                            break;
                        }
                        case 2: {
                            c2 = '/';
                            break;
                        }
                        case 3: {
                            c2 = '\n';
                            break;
                        }
                        default: {
                            c2 = '/';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                jb.z = new String(charArray).intern();
                jb.a = new f((jb.d != null) ? jb.d : (jb.d = a(jb.z)));
                return;
            }
            continue;
        }
    }
}
