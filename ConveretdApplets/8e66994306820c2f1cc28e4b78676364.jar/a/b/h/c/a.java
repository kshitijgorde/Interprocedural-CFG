// 
// Decompiled by Procyon v0.5.30
// 

package a.b.h.c;

import a.b.i.k;
import a.b.b.d;
import org.a.d.g;
import java.util.Vector;
import org.a.d.i;

public class a extends i implements Runnable, c
{
    private String b;
    private e c;
    private int d;
    private int e;
    private String f;
    private int g;
    private long h;
    private Vector i;
    private int j;
    private long k;
    private static String z;
    
    public a(final String s) {
        this(s, null, 4, 3);
    }
    
    public a(final String s, final ThreadGroup threadGroup, final int n, final int n2) {
        this(s, threadGroup, n, n2, 500L, 0L);
    }
    
    public a(final String s, final ThreadGroup threadGroup, final int n, final int n2, final long n3, final long n4) {
        this.a(s);
        this.i = new Vector();
        this.a(n);
        this.b(n2);
        this.a(n3);
        this.b(n4);
        this.j = this.d + this.e;
        this.b();
        if (threadGroup != null) {
            this.c = new e(threadGroup, this);
        }
        else {
            this.c = new e(this);
        }
        this.c.start();
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        if (this.c != null) {
            this.c.a();
            this.c.finalize();
            this.c = null;
        }
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public String a() {
        return this.b;
    }
    
    public void a(final Object o) throws ClassCastException {
        if (!(o instanceof b)) {
            throw new ClassCastException(a.z);
        }
        this.a((b)o);
    }
    
    public void a(final b b) {
        synchronized (this) {
            if (b != null) {
                this.f = b.a();
            }
        }
        if (b != null) {
            super.a(b);
        }
    }
    
    public synchronized Object b() throws g {
        return super.b();
    }
    
    public synchronized void b() {
        synchronized (this) {
            this.f = "";
            this.g = 0;
        }
    }
    
    public void a(final long h) {
        if (h >= 0L) {
            this.h = h;
        }
        else {
            this.h = 500L;
        }
    }
    
    public void b(final long k) {
        if (k >= 0L) {
            this.k = k;
        }
        else {
            this.k = 0L;
        }
    }
    
    public void a(final int d) {
        if (d >= 1) {
            this.d = d;
        }
        else {
            this.d = 1;
        }
    }
    
    public void b(final int e) {
        if (e >= 1) {
            this.e = e;
        }
        else {
            this.e = 1;
        }
    }
    
    public void a(final a.b.b.a a) {
        if (a != null) {
            this.i.addElement(a);
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        if (currentThread instanceof e) {
            final e e = (e)currentThread;
            while (!e.b()) {
                if (this.a() < this.d && this.i.size() > 0) {
                    int g = 0;
                    String s = null;
                    int e2 = 0;
                    synchronized (this) {
                        if (this.a() + this.g < this.d - 1 + this.e && this.g < this.j) {
                            e2 = this.e;
                            g = this.g;
                            this.g += e2;
                            s = new String(this.f);
                        }
                    }
                    if (e2 > 0) {
                        this.a(e2, s, g);
                    }
                }
                if (!e.b() && this.h > 0L) {
                    try {
                        Thread.sleep(this.h);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
    }
    
    protected a.b.i.g a(final String s, final String s2, final int n) {
        return new a.b.i.g(s, s2, n);
    }
    
    protected void b(final String s, final String s2, final int n) {
        final d d = new d(this, 124, this.a(s, s2, n));
        for (int i = 0; i < this.i.size(); ++i) {
            try {
                ((a.b.b.a)this.i.elementAt(i)).a(d);
            }
            catch (NullPointerException ex) {
                this.i.removeElementAt(i);
                --i;
            }
        }
    }
    
    private void a(final int n, final String s, final int n2) {
        for (int i = 0; i < n; ++i) {
            this.b(this.a(), s, n2 + i);
            if (i < n && this.k > 0L) {
                try {
                    Thread.sleep(this.k);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void a(final a.b.h.c.d d) {
        synchronized (this) {
            if (this.g > 0) {
                --this.g;
            }
        }
        if (d != null) {
            this.a((Object)d);
        }
    }
    
    static {
        final char[] charArray = "n\b7\u0012US\u00167\\\u0012u\u00028WQN@=\u0012[I@<]F\u001a\u000f4\u0012FC\u00107\u0012vS\u0013\"^SC37F\u001c".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = ':';
                    break;
                }
                case 1: {
                    c2 = '`';
                    break;
                }
                case 2: {
                    c2 = 'R';
                    break;
                }
                case 3: {
                    c2 = '2';
                    break;
                }
                default: {
                    c2 = '2';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        a.z = new String(charArray).intern();
    }
}
