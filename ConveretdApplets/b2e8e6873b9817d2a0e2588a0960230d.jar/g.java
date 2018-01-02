// 
// Decompiled by Procyon v0.5.30
// 

public final class g implements Runnable
{
    private volatile Thread YJc;
    private long ZJc;
    private long _Kc;
    private long aKc;
    private StringBuffer bKc;
    private long cKc;
    private d OJc;
    private int dKc;
    private int eKc;
    private int fKc;
    private int gKc;
    private long hKc;
    private static String ta = "\u3330\u332b\u3332\u3332";
    
    public g() {
        this(0L);
    }
    
    public g(final long cKc) {
        this.bKc = new StringBuffer(8);
        this.cKc = cKc;
    }
    
    public g(final d d) {
        this(0L, d);
    }
    
    public g(final long n, final d oJc) {
        this(0L);
        if (oJc == null) {
            throw new NullPointerException(g.ta);
        }
        this.OJc = oJc;
    }
    
    public long a() {
        return this.cKc + this._Kc;
    }
    
    public String e() {
        return this._(this.a());
    }
    
    public String _(final long hKc) {
        this.bKc.setLength(0);
        this.hKc = hKc;
        this.hKc %= 3600000L;
        this.eKc = (int)(this.hKc / 60000L);
        if (this.eKc < 10) {
            this.bKc.append('0');
        }
        this.bKc.append(String.valueOf(this.eKc));
        this.bKc.append('.');
        this.hKc %= 60000L;
        this.fKc = (int)(this.hKc / 1000L);
        if (this.fKc < 10) {
            this.bKc.append('0');
        }
        this.bKc.append(String.valueOf(this.fKc));
        return this.bKc.toString();
    }
    
    public void start() {
        if (this.YJc == null) {
            (this.YJc = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.isAlive()) {
            this.YJc = null;
            this._Kc = System.currentTimeMillis() - this.ZJc;
            if (this._Kc < this.aKc) {
                this._Kc = -1L;
            }
        }
        this.YJc = null;
    }
    
    public boolean isAlive() {
        return this.YJc != null && this.YJc.isAlive();
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        this.ZJc = System.currentTimeMillis();
        while (this.YJc == currentThread) {
            this._Kc = System.currentTimeMillis() - this.ZJc;
            if (this._Kc < this.aKc) {
                this._Kc = -1L;
                break;
            }
            this.aKc = this._Kc;
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                break;
            }
            finally {
                if (this.OJc != null) {
                    this.OJc.repaint();
                }
            }
        }
    }
    
    public boolean d() {
        return this._Kc == -1L;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF335E);
        }
        return new String(array);
    }
    
    static {
        g.ta = a(g.ta);
    }
}
