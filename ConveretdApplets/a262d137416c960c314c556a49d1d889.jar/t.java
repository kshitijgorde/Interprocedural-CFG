// 
// Decompiled by Procyon v0.5.30
// 

public final class t implements Runnable
{
    private volatile Thread p2;
    private long q2;
    private long r2;
    private long s2;
    private StringBuffer t2;
    private long u2;
    private q f2;
    private int v2;
    private int w2;
    private int x2;
    private int y2;
    private long z2;
    private static String z = "\u77cc\u77d7\u77ce\u77ce";
    
    public t() {
        this(0L);
    }
    
    public t(final long u2) {
        this.t2 = new StringBuffer(8);
        this.u2 = u2;
    }
    
    public t(final q q) {
        this(0L, q);
    }
    
    public t(final long n, final q f2) {
        this(0L);
        if (f2 == null) {
            throw new NullPointerException(t.z);
        }
        this.f2 = f2;
    }
    
    public long a() {
        return this.u2 + this.r2;
    }
    
    public String k() {
        return this.a(this.a());
    }
    
    public String a(final long z2) {
        this.t2.setLength(0);
        this.z2 = z2;
        this.z2 %= 3600000L;
        this.w2 = (int)(this.z2 / 60000L);
        if (this.w2 < 10) {
            this.t2.append('0');
        }
        this.t2.append(String.valueOf(this.w2));
        this.t2.append('.');
        this.z2 %= 60000L;
        this.x2 = (int)(this.z2 / 1000L);
        if (this.x2 < 10) {
            this.t2.append('0');
        }
        this.t2.append(String.valueOf(this.x2));
        return this.t2.toString();
    }
    
    public void start() {
        if (this.p2 == null) {
            (this.p2 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.isAlive()) {
            this.p2 = null;
            this.r2 = System.currentTimeMillis() - this.q2;
            if (this.r2 < this.s2) {
                this.r2 = -1L;
            }
        }
        this.p2 = null;
    }
    
    public boolean isAlive() {
        return this.p2 != null && this.p2.isAlive();
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        this.q2 = System.currentTimeMillis();
        while (this.p2 == currentThread) {
            this.r2 = System.currentTimeMillis() - this.q2;
            if (this.r2 < this.s2) {
                this.r2 = -1L;
                break;
            }
            this.s2 = this.r2;
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                break;
            }
            finally {
                if (this.f2 != null) {
                    this.f2.repaint();
                }
            }
        }
    }
    
    public boolean e() {
        return this.r2 == -1L;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF77A2);
        }
        return new String(array);
    }
    
    static {
        t.z = _(t.z);
    }
}
