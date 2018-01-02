// 
// Decompiled by Procyon v0.5.30
// 

public final class at
{
    public Object[] e7;
    public Object hy;
    public boolean hx;
    public boolean hw;
    public int hv;
    public int hu;
    public int ht;
    
    public at() {
        this.e7 = new Object[513];
        this.hy = new Object();
        this.hx = false;
        this.hw = false;
        this.hv = 0;
        this.hu = 0;
        this.ht = 512;
    }
    
    public final void hr(final ay ay) {
        synchronized (this.hy) {
            this.hn();
            this.e7[this.hu++] = ay;
            if (this.hu == 513) {
                this.hu = 0;
            }
            if (this.hx) {
                this.hy.notify();
            }
        }
        // monitorexit(this.hy)
    }
    
    public final void hq(final ay ay) {
        synchronized (this.hy) {
            this.hn();
            --this.hv;
            if (this.hv == -1) {
                this.hv = 512;
            }
            this.e7[this.hv] = ay;
            if (this.hx) {
                this.hy.notify();
            }
        }
        // monitorexit(this.hy)
    }
    
    public final void hp() {
        synchronized (this.hy) {
            if (this.hx) {
                this.hy.notify();
            }
        }
        // monitorexit(this.hy)
    }
    
    public final boolean ho() {
        final boolean b;
        synchronized (this.hy) {
            b = (this.hv == this.hu);
        }
        // monitorexit(this.hy)
        return b;
    }
    
    private final void hn() {
        int n = this.hv - this.hu;
        if (n <= 0) {
            n += 513;
        }
        if (--n == 512 - this.ht) {
            this.hw = true;
        }
        if (this.hw) {
            try {
                this.hy.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final ay hm() {
        ay ay = null;
        synchronized (this.hy) {
            if (this.ho()) {
                this.hx = true;
                try {
                    this.hy.wait();
                }
                catch (InterruptedException ex) {}
            }
            this.hx = false;
            ay = (ay)this.e7[this.hv];
            this.e7[this.hv++] = null;
            if (this.hv == 513) {
                this.hv = 0;
            }
            if (this.hw) {
                int n = this.hv - this.hu;
                if (n <= 0) {
                    n += 513;
                }
                if (--n > 128) {
                    this.hy.notifyAll();
                    this.hw = false;
                }
            }
        }
        // monitorexit(this.hy)
        return ay;
    }
}
