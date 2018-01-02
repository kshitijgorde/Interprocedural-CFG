// 
// Decompiled by Procyon v0.5.30
// 

package a.a.b.a;

import java.io.IOException;
import a.b.f.l;
import a.b.g.b;
import a.b.f.h;
import org.a.c.f;
import a.b.f.i;
import a.b.f.a.c;

public class a extends c implements i
{
    private static boolean j;
    private static a k;
    private static org.a.c.f l;
    private String m;
    private h n;
    private int o;
    private static String[] z;
    
    protected a() {
        this.m = a.z[0];
        this.b();
    }
    
    protected a(final ThreadGroup threadGroup) {
        super(threadGroup);
        this.m = a.z[0];
        this.b();
    }
    
    private void b() {
        if (a.l == null) {
            a.l = a.b.g.b.a().d().a(this.getClass().getName());
        }
        a.j = false;
        this.o = 0;
        this.n = new l();
        this.start();
    }
    
    public static a c() {
        if (a.k == null || !a.k.isAlive()) {
            final ThreadGroup a = a.b.g.b.a().c().a();
            if (a == null) {
                a.a.b.a.a.k = new a();
            }
            else {
                a.a.b.a.a.k = new a(a);
            }
        }
        return a.k;
    }
    
    protected String a() {
        return this.m;
    }
    
    public byte[] a(final int n) throws IOException {
        byte[] a = new byte[0];
        byte[] a2 = new byte[0];
        if (this.a()) {
            try {
                a = super.a(n);
            }
            catch (IOException ex) {
                a.a.b.a.a.l.b(a.a.b.a.a.z[1]);
            }
        }
        if (this.n != null) {
            a2 = this.n.a(n - a.length);
        }
        final byte[] array = new byte[a.length + a2.length];
        System.arraycopy(a, 0, array, 0, a.length);
        System.arraycopy(a2, 0, array, a.length, a2.length);
        this.o = a.length;
        return array;
    }
    
    public void a(final byte[] array) throws IOException {
        if (this.a()) {
            super.a(array);
        }
    }
    
    public int available() {
        int available = -1;
        int available2 = -1;
        if (this.a()) {
            available = super.available();
        }
        if (this.n != null) {
            available2 = this.n.available();
        }
        if (available > 0 || available2 > 0) {
            return 1;
        }
        if (available == 0 || available2 == 0) {
            return 0;
        }
        return -1;
    }
    
    public void run() {
        final boolean b;
        synchronized (this) {
            b = !a.j;
            if (b) {
                a.j = true;
            }
        }
        if (b) {
            super.run();
            a.j = false;
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "3\u0007\u0017;RM6\u000b<G\u0001\u0006".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '`';
                    break;
                }
                case 1: {
                    c2 = 'b';
                    break;
                }
                case 2: {
                    c2 = 'c';
                    break;
                }
                case 3: {
                    c2 = 'N';
                    break;
                }
                default: {
                    c2 = '\"';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "%\u0010\u0011!P@\u0015\u000b'N\u0005B\u0011+C\u0004\u000b\r)\u0002\u0006\u0010\f#\u0002(-0\u001a\u000e@\u0015\n\"N@\f\f:\u0002\u0012\u0007\u0017;P\u000eB+!Q\u0014B\u0007/V\u0001L".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '`';
                    break;
                }
                case 1: {
                    c4 = 'b';
                    break;
                }
                case 2: {
                    c4 = 'c';
                    break;
                }
                case 3: {
                    c4 = 'N';
                    break;
                }
                default: {
                    c4 = '\"';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        a.z = z;
    }
}
