// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class pb implements cb
{
    private static f a;
    private Object[] b;
    private int c;
    private int d;
    private int e;
    private int f;
    private static /* synthetic */ Class g;
    private static String[] z;
    
    public void a(final Object o) {
        if (o == null) {
            throw new NullPointerException(pb.z[0]);
        }
        this.a(1);
        this.b[this.e++] = o;
        if (this.e >= this.b.length) {
            this.e = 0;
        }
        ++this.c;
        ++this.d;
    }
    
    public Object a() {
        while (this.c != 0) {
            --this.c;
            final Object o = this.b[this.f];
            this.b[this.f++] = null;
            if (this.f >= this.b.length) {
                this.f = 0;
            }
            if (o != null) {
                --this.d;
                return o;
            }
        }
        return null;
    }
    
    public void b() {
        int f = this.f;
        for (int i = 0; i < this.c; ++i) {
            this.b[f] = null;
            if (++f >= this.b.length) {
                f = 0;
            }
        }
        final boolean b = false;
        this.d = (b ? 1 : 0);
        this.c = (b ? 1 : 0);
        this.e = (b ? 1 : 0);
        this.f = (b ? 1 : 0);
    }
    
    public boolean b(final Object o) {
        int f = this.f;
        for (int i = 0; i < this.c; ++i) {
            if (this.b[f].equals(o)) {
                --this.d;
                this.b[f] = null;
                return true;
            }
            if (++f >= this.b.length) {
                f = 0;
            }
        }
        return false;
    }
    
    public int c() {
        return this.d;
    }
    
    private void a(final int n) {
        if (this.c + n <= this.b.length) {
            return;
        }
        final Object[] c = pb.a.c(this.c + n + 5);
        final int n2 = c.length - this.b.length;
        if (this.e != 0) {
            System.arraycopy(this.b, 0, c, 0, this.e);
        }
        System.arraycopy(this.b, this.e, c, this.e + n2, this.b.length - this.e);
        pb.a.a(this.b);
        this.b = c;
        if (this.f >= this.e) {
            this.f += n2;
        }
    }
    
    public static pb d() {
        return (pb)pb.a.a();
    }
    
    public void f() {
        pb.a.a(this);
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
    
    public pb() {
        this.b = pb.a.c(5);
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "5H=ODVY&\u001c\u0010\u0017\t=\u001d\\\u001a\t<\nZ\u0013J'HD\u0019\t\u001f\u0001C\u0002\tr".toCharArray();
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
                            c2 = 'v';
                            break;
                        }
                        case 1: {
                            c2 = ')';
                            break;
                        }
                        case 2: {
                            c2 = 'S';
                            break;
                        }
                        case 3: {
                            c2 = 'h';
                            break;
                        }
                        default: {
                            c2 = '0';
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
        final char[] charArray2 = "\u0018L2\u001c\u001e\u0006K".toCharArray();
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
                            c4 = 'v';
                            break;
                        }
                        case 1: {
                            c4 = ')';
                            break;
                        }
                        case 2: {
                            c4 = 'S';
                            break;
                        }
                        case 3: {
                            c4 = 'h';
                            break;
                        }
                        default: {
                            c4 = '0';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                pb.z = z;
                pb.a = new f((pb.g != null) ? pb.g : (pb.g = a(pb.z[1])));
                return;
            }
            continue;
        }
    }
}
