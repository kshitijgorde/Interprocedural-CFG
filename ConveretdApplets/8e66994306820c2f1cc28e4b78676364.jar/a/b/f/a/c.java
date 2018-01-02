// 
// Decompiled by Procyon v0.5.30
// 

package a.b.f.a;

import java.io.IOException;
import a.b.g.b;
import a.b.f.f;
import org.a.d.c.d;

public abstract class c extends d implements f
{
    private static org.a.c.f d;
    private long e;
    private boolean f;
    private boolean g;
    private Thread h;
    private long i;
    private static String[] z;
    
    public c() {
        this.e = 5000L;
        this.f = false;
        this.g = true;
        this.i = 25L;
        if (c.d == null) {
            c.d = a.b.g.b.a().d().a(this.getClass().getName());
        }
    }
    
    public c(final ThreadGroup threadGroup) {
        super(threadGroup, c.z[0]);
        this.e = 5000L;
        this.f = false;
        this.g = true;
        this.i = 25L;
        if (c.d == null) {
            c.d = a.b.g.b.a().d().a(this.getClass().getName());
        }
    }
    
    protected abstract String a();
    
    public long b() {
        return this.e;
    }
    
    public long c() {
        return this.i;
    }
    
    public int available() {
        if (this.a()) {
            return 1;
        }
        if (!this.g) {
            return 0;
        }
        return -1;
    }
    
    public byte[] a(final int n) throws IOException {
        final byte[] array = new byte[n];
        final int read = this.read(array);
        final byte[] array2 = new byte[read];
        System.arraycopy(array, 0, array2, 0, read);
        return array2;
    }
    
    public void a(final byte[] array) throws IOException {
        this.write(array);
    }
    
    public void close() throws IOException {
        super.close();
        if (this.h != null) {
            this.h.interrupt();
        }
    }
    
    public void writeObject(final Object o) throws IOException {
        super.writeObject(o);
        if (this.h != null) {
            this.h.interrupt();
        }
    }
    
    protected void a(final boolean g) {
        this.g = g;
    }
    
    public void run() {
        try {
            final c h = (c)Thread.currentThread();
            h.setName(this.a());
            this.h = h;
            this.a(false);
            long n;
            if (this.b() >= 0L) {
                n = System.currentTimeMillis() + this.b();
            }
            else {
                n = -1L;
            }
            boolean b = false;
            c.d.a(c.z[1]);
            while (!h.a() && !b && !h.b()) {
                if (n >= 0L) {
                    b = (System.currentTimeMillis() > n);
                }
                if (!h.b() && !h.a() && !b) {
                    try {
                        Thread.sleep(this.c());
                    }
                    catch (InterruptedException ex) {}
                }
            }
            while (h.a() && !h.b()) {
                c.d.a(c.z[2]);
                synchronized (h) {
                    try {
                        h.wait();
                    }
                    catch (InterruptedException ex2) {}
                    continue;
                }
                break;
            }
            this.a(true);
        }
        catch (ClassCastException ex3) {}
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = ";Kg\n\u0011\nsa,\u0011\tq".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'd';
                    break;
                }
                case 1: {
                    c2 = '\u0014';
                    break;
                }
                case 2: {
                    c2 = '\u0004';
                    break;
                }
                case 3: {
                    c2 = 'b';
                    break;
                }
                default: {
                    c2 = 'p';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "3um\u0016\u0019\ns$\u0004\u001f\u00164g\r\u001e\nqg\u0016\u0019\u000bz$\u0004\u0002\u000by$*?7@".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'd';
                    break;
                }
                case 1: {
                    c4 = '\u0014';
                    break;
                }
                case 2: {
                    c4 = '\u0004';
                    break;
                }
                case 3: {
                    c4 = 'b';
                    break;
                }
                default: {
                    c4 = 'p';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "'XM'>04g\r\u001e\nqg\u0016\u0015\u00004p\rP,[W6".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'd';
                    break;
                }
                case 1: {
                    c6 = '\u0014';
                    break;
                }
                case 2: {
                    c6 = '\u0004';
                    break;
                }
                case 3: {
                    c6 = 'b';
                    break;
                }
                default: {
                    c6 = 'p';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        c.z = z;
    }
}
