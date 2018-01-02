// 
// Decompiled by Procyon v0.5.30
// 

class Uea extends Thread
{
    private final pea N;
    
    Uea(final pea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (pea._(this.N)) {
            super.b(pea._(this.N));
        }
    }
}
