// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class o implements cb
{
    private static f a;
    int b;
    Object c;
    Object d;
    o e;
    private static /* synthetic */ Class f;
    private static String z;
    
    static o a(final int n, final Object o, final Object o2, final o o3) {
        final o o4 = (o)o.a.a();
        o4.b(n, o, o2, o3);
        return o4;
    }
    
    public void f() {
        o.a.a(this);
    }
    
    public void g() {
    }
    
    public void h() {
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    protected void b(final int b, final Object c, final Object d, final o e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public Object a() {
        return this.c;
    }
    
    public Object b() {
        return this.d;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof o)) {
            return false;
        }
        final o o2 = (o)o;
        return ((this.c == null) ? (o2.a() == null) : this.c.equals(o2.a())) && ((this.d == null) ? (o2.b() == null) : this.d.equals(o2.b()));
    }
    
    public int hashCode() {
        return this.b ^ ((this.d == null) ? 0 : this.d.hashCode());
    }
    
    public String toString() {
        return this.c.toString() + "=" + this.d.toString();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public o() {
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    static {
        final char[] charArray = "j\u001f\u0017ikk".toCharArray();
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
                            c2 = '\u0004';
                            break;
                        }
                        case 1: {
                            c2 = 'z';
                            break;
                        }
                        case 2: {
                            c2 = 'v';
                            break;
                        }
                        case 3: {
                            c2 = '\u001d';
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
                o.z = new String(charArray).intern();
                o.a = new f((o.f != null) ? o.f : (o.f = a(o.z)));
                return;
            }
            continue;
        }
    }
}
