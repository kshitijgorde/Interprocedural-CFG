// 
// Decompiled by Procyon v0.5.30
// 

class Counter implements Manager
{
    public int count;
    public int maxCount;
    public int delay;
    public int[] maxDelay;
    public boolean toggleOn;
    public boolean toggle;
    public boolean recycle;
    
    public Counter() {
        this.set(0, 0, false, true);
    }
    
    public Counter(final int n) {
        this.set(n, 0, false, true);
    }
    
    public Counter(final boolean b, final int n) {
        this.set(n, 0, false, b);
    }
    
    public Counter(final int n, final boolean b) {
        this.set(n, 0, b, true);
    }
    
    public Counter(final int n, final int n2) {
        this.set(n, n2, false, true);
    }
    
    public Counter(final int n, final int n2, final boolean b) {
        this.set(n, n2, b, true);
    }
    
    public Counter(final boolean b, final int n, final boolean b2) {
        this.set(n, 0, b2, b);
    }
    
    public Counter(final int n, final int[] maxDelay) {
        this.set(n, 0, false, true);
        this.maxDelay = maxDelay;
    }
    
    public Counter(final int n, final int[] array, final boolean toggleOn) {
        this(n, array);
        this.toggleOn = toggleOn;
    }
    
    public final void set(final int maxCount, final int n, final boolean toggleOn, final boolean recycle) {
        this.maxCount = maxCount;
        this.toggleOn = toggleOn;
        this.maxDelay = new int[maxCount + 1];
        this.set(n);
        this.recycle = recycle;
    }
    
    public final void set(final int n) {
        for (int i = 0; i < this.maxDelay.length; ++i) {
            this.maxDelay[i] = n;
        }
    }
    
    public final void increment() {
        ++this.delay;
        if (this.delay > this.maxDelay[this.count]) {
            this.delay = 0;
            if ((this.toggleOn && !this.toggle) || !this.toggleOn) {
                if (this.recycle || this.count <= this.maxCount) {
                    ++this.count;
                }
                if (this.toggleOn) {
                    if (this.count >= this.maxCount) {
                        this.toggle = true;
                    }
                }
                else {
                    if (this.count <= this.maxCount) {
                        return;
                    }
                    this.count = 0;
                }
                return;
            }
            --this.count;
            if (this.count <= 0) {
                this.toggle = false;
            }
        }
    }
    
    public final void reset() {
        this.count = 0;
        this.delay = 0;
        this.toggle = false;
    }
}
