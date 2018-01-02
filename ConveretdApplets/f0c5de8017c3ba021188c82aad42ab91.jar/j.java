import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class j
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    private String g;
    private boolean h;
    public static final f i;
    private Vector j;
    public Hashtable k;
    private boolean l;
    
    public j(final int n, final int n2, final String g) {
        this(n, n2);
        this.g = g;
    }
    
    public j(final int a, int b) {
        this.g = "";
        this.h = false;
        this.j = new Vector();
        this.k = new Hashtable();
        this.l = false;
        if (b < 1) {
            b = 1;
        }
        if (b < a) {
            b = a;
        }
        this.b = b;
        this.a = a;
    }
    
    public final void a(final int a, int b) {
        synchronized (this.j) {
            if (b < 1) {
                b = 1;
            }
            if (b < a) {
                b = a;
            }
            this.b = b;
            this.a = a;
        }
    }
    
    public final int a(final e e, final long n, final String s) {
        return this.a(e, n, false, s);
    }
    
    public final int a(final e e, final String s) {
        return this.a(e, Long.MAX_VALUE, true, s);
    }
    
    public final int a(final e e) {
        return this.a(e, Long.MAX_VALUE, true, "no_waiter");
    }
    
    public final int a(final e e, final long n, final boolean b, final String s) {
        final Thread b2 = this.b(e, n, b, s);
        if (b2 == null) {
            return 3;
        }
        if (b) {
            return 0;
        }
        return this.a(b2, n);
    }
    
    public final m b(final e e, final long n, final boolean d, final String s) {
        if (this.h) {
            if (j.i.g()) {
                j.i.e("Threadpool destroyed; returning no thread");
            }
            return null;
        }
        Thread a = null;
        synchronized (this.j) {
            while (this.j.size() > 0) {
                a = this.j.elementAt(this.j.size() - 1);
                --this.f;
                this.j.removeElementAt(this.j.size() - 1);
                synchronized (a.f) {
                    if (a.f.b() != 3) {
                        break;
                    }
                    a = null;
                }
                if (j.i.k() && a != null) {
                    j.i.i("Caller:" + a.toString() + " getting pooled creator, pos:" + (this.j.size() - 1));
                }
            }
        }
        if (a == null) {
            a = this.a(n, s);
            if (a == null) {
                return null;
            }
        }
        if (n.e()) {
            n.a(a.f.b() == 1, " state is not idle ");
        }
        a.b(s);
        a.a(e);
        synchronized (a.f) {
            if (j.i.l()) {
                j.i.j("Caller:" + a.toString() + " putting to work with " + e);
            }
            a.d = d;
            a.f.a(2);
            a.f.notify();
        }
        return a;
    }
    
    public final int a(final m thread, final long n) {
        final long currentTimeMillis = System.currentTimeMillis();
        long n2 = n;
        synchronized (thread.f) {
            while (true) {
                try {
                    if (thread.f.b() == 2) {
                        thread.f.wait(n2);
                    }
                }
                catch (InterruptedException ex) {}
                if (j.i.k()) {
                    j.i.i("Caller:" + thread.toString() + " returned from work with state=" + thread.f + " after " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                }
                if (thread.f.b() == 1) {
                    break;
                }
                if (thread.f.b() == 3) {
                    --this.d;
                    thread.b("dead");
                    if (j.i.k()) {
                        j.i.i("Caller:" + thread.toString() + " return with CREATE_EXCEPTION for " + thread.e);
                    }
                    return 2;
                }
                n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                if (n2 <= 0L) {
                    --this.d;
                    thread.b("timed-out");
                    thread.destroy();
                    if (j.i.k()) {
                        j.i.i("Caller:" + thread.toString() + " return with CREATE_TIMEOUT for " + thread.e);
                    }
                    return 1;
                }
            }
        }
        if (n.e()) {
            n.a(thread.f.b() == 1, " state is not idle ");
        }
        this.a(thread);
        if (j.i.k()) {
            j.i.i("Caller:" + thread.toString() + " return with CREATE_OK");
        }
        return 0;
    }
    
    public void a(final m thread) {
        if (!this.h) {
            synchronized (this.j) {
                if (this.j.size() < this.a) {
                    this.j.addElement(thread);
                    thread.a("pool");
                    ++this.f;
                    if (j.i.l()) {
                        j.i.j("Caller:" + thread.toString() + " putting back to pool at pos=" + (this.j.size() - 1));
                    }
                }
                else {
                    if (j.i.k()) {
                        j.i.i("Caller:" + thread.toString() + " destroying since pool is full");
                    }
                    --this.d;
                    thread.destroy();
                }
            }
        }
    }
    
    public m a(final long n, final String s) {
        Thread thread = null;
        if (j.i.k()) {
            j.i.i("Trying to get new creator: nrAlive=" + this.c + " maxAlive-nrAlive=" + (this.b - this.c) + " nrAliveVirtual=" + this.d + " purpose:" + s);
        }
        synchronized (this) {
            if (this.c >= this.b) {
                try {
                    this.wait(n);
                }
                catch (Exception ex) {}
            }
            if (this.c < this.b) {
                thread = new Thread(this.e, this.g) {
                    private int a;
                    private Integer b;
                    private Vector c = new Vector(4);
                    public boolean d = false;
                    public e e;
                    public l f = new l(1);
                    
                    {
                        this.a("C");
                        this.a = a;
                        this.b = new Integer(this.a);
                        try {
                            this.setDaemon(true);
                        }
                        catch (Exception ex) {}
                        this.start();
                    }
                    
                    public String toString() {
                        return "Creator:" + this.a;
                    }
                    
                    public void a(final e e) {
                        if (j.i.l()) {
                            j.i.j(this.toString() + " init with " + e);
                        }
                        this.e = e;
                    }
                    
                    public Integer a() {
                        return this.b;
                    }
                    
                    public void a(final String s) {
                        synchronized (this.c) {
                            (this.c = new Vector()).addElement(s);
                            j.a(j.this, true);
                        }
                    }
                    
                    public void b(final String s) {
                        synchronized (this.c) {
                            this.c.addElement(s);
                            j.a(j.this, true);
                        }
                    }
                    
                    public Vector b() {
                        return this.c;
                    }
                    
                    public void run() {
                        if (j.i.k()) {
                            j.i.i(this.toString() + " start running");
                        }
                        try {
                            this.b("sR");
                            while (true) {
                                boolean d = false;
                                synchronized (this.f) {
                                    if (j.i.l()) {
                                        j.i.j(this.toString() + " BeforeWait status=" + this.f);
                                    }
                                    while (this.f.b() == 1) {
                                        try {
                                            this.f.wait(2000L);
                                        }
                                        catch (InterruptedException ex2) {}
                                    }
                                    if (j.i.l()) {
                                        j.i.j(this.toString() + " AfterWait status=" + this.f);
                                    }
                                    if (this.f.b() == 3) {
                                        return;
                                    }
                                }
                                if (j.i.l()) {
                                    j.i.j(this.toString() + " start producing with " + this.e);
                                }
                                this.e.produce();
                                if (j.i.l()) {
                                    j.i.j(this.toString() + " finished producing with " + this.e);
                                }
                                synchronized (this.f) {
                                    if (j.i.l()) {
                                        j.i.j(this.toString() + " finished producing state=" + this.f);
                                    }
                                    if (this.f.b() == 3) {
                                        return;
                                    }
                                    this.f.a(1);
                                    try {
                                        this.f.notify();
                                    }
                                    catch (IllegalMonitorStateException ex3) {}
                                    d = this.d;
                                }
                                if (d) {
                                    j.this.a(this);
                                }
                            }
                        }
                        catch (Exception ex) {
                            if (j.i.g()) {
                                j.i.b("An exception occured while calling producer ", ex);
                            }
                        }
                        finally {
                            this.b("sF");
                            synchronized (this.f) {
                                if (j.i.k()) {
                                    j.i.i(this.toString() + " stop running with state=" + this.f);
                                }
                                if (this.f.b() != 3) {
                                    this.f.a(3);
                                    try {
                                        this.f.notify();
                                    }
                                    catch (IllegalMonitorStateException ex4) {}
                                }
                            }
                            j.this.b(this);
                            this.b("fF");
                        }
                    }
                    
                    public void destroy() {
                        this.b("D");
                        synchronized (this.f) {
                            if (j.i.j()) {
                                j.i.h(this.toString() + " destroy");
                            }
                            this.f.a(3);
                            this.f.notifyAll();
                        }
                    }
                };
                this.k.put(thread, thread);
                ++this.c;
                ++this.d;
                ++this.e;
            }
        }
        if (thread != null) {
            if (j.i.k()) {
                j.i.i("Caller:" + thread.toString() + " constructed new creator: nrAlive=" + this.c + " maxAlive-nrAlive=" + (this.b - this.c) + " nrAliveVirtual=" + this.d);
            }
        }
        else if (j.i.g()) {
            j.i.d(this.g + ": Can not spawn creator thread. maxAlive=" + this.b + " reached (nrAliveVirtual=" + this.d + " nrAlive=" + this.c + " keepAlive=" + this.a + ") purpose: [" + s + "] status: " + this.c());
        }
        return thread;
    }
    
    public void b(final m thread) {
        synchronized (this) {
            --this.c;
            this.k.remove(thread);
            this.notifyAll();
        }
        if (j.i.k()) {
            j.i.i("Caller:" + thread.toString() + " released creator: nrAlive=" + this.c + " maxAlive-nrAlive=" + (this.b - this.c) + " nrAliveVirtual=" + this.d);
        }
    }
    
    public static final String a(final int n) {
        switch (n) {
            case 2: {
                return "CREATE_EXCEPTION";
            }
            case 3: {
                return "CREATE_NO_THREADS";
            }
            case 1: {
                return "CREATE_TIMEOUT";
            }
            default: {
                return "CREATE_OK";
            }
        }
    }
    
    private String c() {
        if (this.l) {
            synchronized (this.k) {
                final StringBuffer sb = new StringBuffer();
                final Enumeration<m> keys = (Enumeration<m>)this.k.keys();
                int n = 1;
                while (keys.hasMoreElements()) {
                    final Thread thread = keys.nextElement();
                    sb.append("[" + n + ":" + thread.a() + ":");
                    final Vector b = thread.b();
                    for (int size = b.size(), i = 0; i < size; ++i) {
                        sb.append(b.elementAt(i) + ":");
                    }
                    sb.append("]");
                    ++n;
                }
                this.l = false;
                return sb.toString();
            }
        }
        return "(unchanged)";
    }
    
    public final void a() {
        this.h = true;
        final Enumeration<Object> keys = this.k.keys();
        while (keys.hasMoreElements()) {
            final Thread thread = this.k.get(keys.nextElement());
            if (thread != null) {
                thread.destroy();
            }
        }
        this.j = null;
    }
    
    public final boolean b() {
        return this.h;
    }
    
    public static /* synthetic */ boolean a(final j j, final boolean l) {
        return j.l = l;
    }
    
    static {
        i = f.a("threads");
    }
}
