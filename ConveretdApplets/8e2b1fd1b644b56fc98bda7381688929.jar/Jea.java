// 
// Decompiled by Procyon v0.5.30
// 

class Jea extends Thread
{
    private final int Ea;
    private final int Fa;
    private final zea N;
    
    Jea(final zea n, final int ea, final int fa) {
        this.N = n;
        this.Ea = ea;
        this.Fa = fa;
    }
    
    public void run() {
        synchronized (zea.b(this.N)) {
            super.b(zea.b(this.N))._()._(this.Ea);
            super.b(zea.b(this.N))._().a(this.Fa);
            super.b(zea.b(this.N))._().i();
            super._(zea.b(this.N)).b();
            super._(zea.b(this.N)).repaint();
            super._(zea.b(this.N))._();
            zea.b(this.N).a(true);
        }
    }
}
