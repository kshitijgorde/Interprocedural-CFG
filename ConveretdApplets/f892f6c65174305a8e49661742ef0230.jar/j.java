// 
// Decompiled by Procyon v0.5.30
// 

public abstract class j
{
    public static final int try = -1000;
    public static final int null = 50;
    public static final int b = 60;
    public static final int e = 10;
    public static final int long = 20;
    protected a do;
    protected int case;
    protected int d;
    protected int a;
    protected int byte;
    protected int void;
    protected int new;
    protected int int;
    protected long for;
    protected int goto;
    protected int else;
    protected boolean if;
    protected boolean c;
    protected int char;
    
    public j() {
        this.case = 10;
        this.new = 40;
        this.int = 50;
        this.goto = Math.max(5, 1000 / this.new);
        this.else = -1;
        this.if = false;
        this.c = false;
    }
    
    protected synchronized void finalize() throws Throwable {
        this.do = null;
    }
    
    public synchronized void a(final boolean if1) {
        this.if = if1;
    }
    
    public synchronized void a(final boolean c, final int char1) {
        this.char = char1;
        this.c = c;
    }
    
    public abstract void a();
    
    public abstract void a(final int p0, final int p1);
    
    public synchronized void a(final long n) {
        this.for = System.currentTimeMillis() + n;
        if (this.do == null) {
            (this.do = new a()).start();
        }
    }
    
    public synchronized void int() {
        this.do = null;
        this.for = 0L;
    }
    
    public void do(final int case1) {
        this.case = case1;
        this.do();
        this.if();
    }
    
    public void int(final int byte1) {
        if (byte1 > 0) {
            this.byte = byte1;
            this.do();
        }
    }
    
    public void for(final int a) {
        if (a > 0) {
            this.a = a;
            this.do();
        }
    }
    
    public void if(final int n) {
        if (this.d != n) {
            if (n < 0) {
                this.else = 0;
                this.d = 0;
            }
            else if (n <= this.void) {
                this.else = n;
                this.d = n;
            }
            else if (this.case == 10) {
                this.else = 0;
                this.d = 0;
            }
            if (this.for != -1000L) {
                this.for = 0L;
            }
        }
    }
    
    public void a(final int new1) {
        if (new1 > 0) {
            this.new = new1;
            this.goto = Math.max(5, 1000 / new1);
        }
    }
    
    public void new(final int int1) {
        this.int = int1;
    }
    
    public void if(final long for1) {
        this.for = for1;
    }
    
    public int for() {
        return this.d;
    }
    
    public void if() {
        if (this.byte >= this.a) {
            this.d = 0;
            return;
        }
        if (this.d > this.void) {
            this.d = this.void;
            return;
        }
        if (this.d < 0) {
            this.d = 0;
        }
    }
    
    private void do() {
        if (this.case == 20) {
            this.void = this.a - 1;
        }
        else {
            this.void = this.a - this.byte;
        }
        if (this.void < 0) {
            this.void = 0;
        }
    }
    
    private final class a extends Thread
    {
        public void run() {
            final Thread currentThread = Thread.currentThread();
            int d = j.this.d;
            final int byte1 = j.this.byte;
            final int a = j.this.a;
            final int void1 = j.this.void;
            currentThread.setPriority(10);
            while (j.this.do == currentThread) {
                while (j.this.do == currentThread && j.this.c) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
                if (j.this.char > 0) {
                    final int char1 = j.this.char;
                    j.this.char = 0;
                    try {
                        Thread.sleep(char1);
                    }
                    catch (InterruptedException ex2) {}
                }
                if (j.this.else >= 0) {
                    j.this.d = j.this.else;
                    j.this.else = -1;
                    d = j.this.d;
                }
                if (j.this.do != currentThread) {
                    return;
                }
                j.this.d = d;
                if (j.this.byte < j.this.a) {
                    j.this.a(d, j.this.int);
                    if (j.this.do == currentThread) {
                        j.this.a();
                    }
                }
                try {
                    Thread.sleep(j.this.goto);
                }
                catch (InterruptedException ex3) {}
                while (j.this.for > System.currentTimeMillis() || j.this.for == -1000L) {
                    if (j.this.do != currentThread) {
                        break;
                    }
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex4) {}
                }
                while (j.this.do == currentThread && j.this.if) {
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex5) {}
                }
                if (j.this.do != currentThread) {
                    return;
                }
                d = j.this.d;
                final int byte2 = j.this.byte;
                final int a2 = j.this.a;
                final int void2 = j.this.void;
                if (j.this.int == 50) {
                    if (++d > void2) {
                        d = 0;
                    }
                }
                else if (j.this.int == 60 && --d < 0) {
                    d = void2;
                }
                if (byte2 < a2) {
                    continue;
                }
                d = 0;
            }
        }
    }
}
