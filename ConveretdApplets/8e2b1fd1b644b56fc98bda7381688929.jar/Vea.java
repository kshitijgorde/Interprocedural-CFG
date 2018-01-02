// 
// Decompiled by Procyon v0.5.30
// 

class Vea extends Thread
{
    private final pea N;
    
    Vea(final pea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (pea._(this.N)) {
            super.a(pea._(this.N));
        }
    }
}
