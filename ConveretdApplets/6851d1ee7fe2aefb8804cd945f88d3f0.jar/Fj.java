// 
// Decompiled by Procyon v0.5.30
// 

class Fj extends Thread
{
    private final yj oa;
    
    Fj(final yj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (yj._(this.oa)) {
            boolean b = false;
            try {
                final boolean b2 = n.a(yj._(this.oa))._().b();
                n.a(yj._(this.oa))._()._(const.b(n.a(yj._(this.oa)).a(), n.a(yj._(this.oa)).getSelectedItem()));
                if (b2 != n.a(yj._(this.oa))._().b()) {
                    n._(yj._(this.oa)).b();
                    b = true;
                }
                if (n.a(yj._(this.oa))._().h()) {
                    n._(yj._(this.oa)).b();
                    b = true;
                }
                if (yj._(this.oa)._()) {
                    n._(yj._(this.oa)).b();
                }
            }
            finally {
                if (b) {
                    yj._(this.oa).b(n.a(yj._(this.oa))._()._() + ": " + n.a(yj._(this.oa)).a().b("msgLoadingData"));
                    n.a(yj._(this.oa))._().h();
                }
                boolean a = false;
                if (yj._(this.oa)._()) {
                    a = n.a(yj._(this.oa));
                }
                if (!a) {
                    n._(yj._(this.oa))._();
                }
                yj._(this.oa).b(n.a(yj._(this.oa))._().getMessage());
                n._(yj._(this.oa)).repaint();
                if (b) {
                    n._(yj._(this.oa)).a();
                }
                yj._(this.oa).b(true);
            }
        }
    }
}
