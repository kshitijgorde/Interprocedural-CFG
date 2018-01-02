// 
// Decompiled by Procyon v0.5.30
// 

class Xea extends Thread
{
    private final pea N;
    
    Xea(final pea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (pea._(this.N)) {
            pea._(this.N).a(false);
            super._(pea._(this.N));
            pea._(this.N).a(true);
        }
    }
}
