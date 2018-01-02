// 
// Decompiled by Procyon v0.5.30
// 

class Wea extends Thread
{
    private final pea N;
    
    Wea(final pea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (pea._(this.N)) {
            pea._(this.N).a(false);
            super.a(pea._(this.N));
            pea._(this.N).a(true);
        }
    }
}
