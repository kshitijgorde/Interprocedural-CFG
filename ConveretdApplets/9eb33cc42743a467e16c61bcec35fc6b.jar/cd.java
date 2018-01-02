import mindbright.security.MD5;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class cd extends Random
{
    public MD5 mw;
    public int mp;
    public Thread mv;
    public static int mu;
    public byte[] mt;
    public int ms;
    public Object mr;
    public int mq;
    
    public cd() {
        this.mw = new MD5();
        this.mp = cb.mt(1024);
        for (int n = (cd.mu > 0) ? 2 : 1, i = 0; i < n; ++i) {
            for (int j = this.mw.m9.length - 1; j >= 0; --j) {
                this.mw.m9[j] = (byte)cb.mv(this.mp);
                if (cd.mu < 2) {
                    this.mw.m9[--j] = (byte)System.currentTimeMillis();
                }
            }
            this.mw.nh(this.mw.m9, 0);
        }
        this.mt = new byte[16];
        this.ms = 16;
        this.mr = new Object();
    }
    
    public cd(final byte[] array) {
        this.mw = new MD5();
        try {
            final MD5 mw = new MD5();
            mw.c3(array);
            this.mw = mw;
        }
        catch (Exception ex) {
            System.out.println("Can't operate, MD5 not available...");
        }
        this.mp = cb.mt(1024);
        this.mt = new byte[16];
        this.ms = 16;
        this.mr = new Object();
    }
    
    public final synchronized void c3() {
        if (cd.mu > 1) {
            final byte[] m9 = this.mw.m9;
            final int n = this.mq++;
            m9[n] += (byte)(cb.mv(this.mp) + 1);
            final byte[] m10 = this.mw.m9;
            final int n2 = this.mq++;
            m10[n2] += (byte)(cb.mv(this.mp) + 1);
        }
        else {
            final byte[] m11 = this.mw.m9;
            final int n3 = this.mq++;
            m11[n3] += (byte)cb.mu();
            final byte[] m12 = this.mw.m9;
            final int mq = this.mq;
            m12[mq] += this.mw.m9[this.mq - 1];
            ++this.mq;
        }
        this.mq %= 64;
        final byte[] mt = new byte[16];
        this.mw.nh(this.mw.m9, 0);
        mw(this.mw.na[0], mt, 0, 4);
        mw(this.mw.na[1], mt, 4, 4);
        mw(this.mw.na[2], mt, 8, 4);
        mw(this.mw.na[3], mt, 12, 4);
        synchronized (this.mr) {
            this.mt = mt;
            this.ms = 0;
        }
        // monitorexit(this.mr)
    }
    
    public final synchronized int next(final int n) {
        int n2 = 0;
        synchronized (this.mr) {
            for (int i = 0; i < n; i += 8) {
                if (this.ms == 16) {
                    this.c3();
                }
                n2 = (n2 << 8) + this.mt[this.ms++];
            }
        }
        // monitorexit(this.mr)
        return n2;
    }
    
    public final synchronized void my() {
        if (this.mv != null) {
            return;
        }
        (this.mv = new Thread(new cc(this))).start();
    }
    
    public final void mx(final byte[] array, final int n) {
        this.mx(array, 0, n);
    }
    
    public final void mx(final byte[] array, final int n, final int n2) {
        synchronized (this.mr) {
            for (int i = 0; i < n2; ++i) {
                this.ms %= 16;
                array[n + i] = this.mt[this.ms++];
            }
        }
        // monitorexit(this.mr)
    }
    
    public static final void mw(long n, final byte[] array, final int n2, final int n3) {
        for (int i = n2 + n3 - 1; i >= n2; --i) {
            array[i] = (byte)n;
            n >>>= 8;
        }
    }
}
