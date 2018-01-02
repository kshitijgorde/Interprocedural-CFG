// 
// Decompiled by Procyon v0.5.30
// 

class Fea extends Thread
{
    private final Dea N;
    
    Fea(final Dea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (Dea._(this.N)) {
            try {
                Dea._(this.N).a(super.b(Dea._(this.N)).a().a("msgIndicatorCalculationBegin"));
                Dea.b(this.N);
            }
            finally {
                Dea._(this.N).a(super.b(Dea._(this.N)).a().a("msgIndicatorCalculationEnd"));
                super._(Dea._(this.N)).repaint();
                Dea._(this.N).a(true);
            }
        }
    }
}
