// 
// Decompiled by Procyon v0.5.30
// 

class Lj extends Thread
{
    private final wj oa;
    
    Lj(final wj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (wj._(this.oa)) {
            n._(wj._(this.oa)).a();
            try {
                n.a(wj._(this.oa))._().setUseCache(false);
                wj._(this.oa).b(n.a(wj._(this.oa)).a().b("msgRefreshingData"));
                n.a(wj._(this.oa))._().a(n.a(wj._(this.oa)));
                n.a(wj._(this.oa))._()._(n.b(wj._(this.oa)));
                n.a(wj._(this.oa))._().h();
                if (n.a(wj._(this.oa)) == null) {
                    n._(wj._(this.oa)).b();
                }
            }
            finally {
                n._(wj._(this.oa))._();
                wj._(this.oa).b(n.a(wj._(this.oa))._().getMessage());
                n._(wj._(this.oa)).repaint();
                n.a(wj._(this.oa))._().setUseCache(n.a(wj._(this.oa)).getState());
                n._(wj._(this.oa)).a();
                wj._(this.oa).b(true);
            }
        }
    }
}
