// 
// Decompiled by Procyon v0.5.30
// 

class Hj extends Thread
{
    private final int Ja;
    private final int Ka;
    private final wj oa;
    
    Hj(final wj oa, final int ja, final int ka) {
        this.oa = oa;
        this.Ja = ja;
        this.Ka = ka;
    }
    
    public void run() {
        synchronized (wj._(this.oa)) {
            n.a(wj._(this.oa))._().a(this.Ja);
            n.a(wj._(this.oa))._().b(this.Ka);
            n.a(wj._(this.oa))._().i();
            n._(wj._(this.oa))._();
            n._(wj._(this.oa)).repaint();
            n._(wj._(this.oa)).a();
            wj._(this.oa).b(true);
        }
    }
}
