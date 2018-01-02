// 
// Decompiled by Procyon v0.5.30
// 

class Cj extends Thread
{
    private final Aj oa;
    
    Cj(final Aj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (Aj.a(this.oa)) {
            try {
                Aj.a(this.oa).b(n.a(Aj.a(this.oa)).a().b("msgIndicatorCalculationBegin"));
                Aj.a(this.oa);
            }
            finally {
                Aj.a(this.oa).b(n.a(Aj.a(this.oa)).a().b("msgIndicatorCalculationEnd"));
                n._(Aj.a(this.oa)).repaint();
                Aj.a(this.oa).b(true);
            }
        }
    }
}
