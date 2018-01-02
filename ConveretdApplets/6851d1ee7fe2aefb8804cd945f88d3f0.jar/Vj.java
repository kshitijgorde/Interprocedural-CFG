// 
// Decompiled by Procyon v0.5.30
// 

class Vj extends Thread
{
    private final jj oa;
    
    Vj(final jj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (jj._(this.oa)) {
            try {
                final String a = n.a(jj._(this.oa));
                final int[] selectedIndexes = n.b(jj._(this.oa)).getSelectedIndexes();
                for (int i = 0; i < selectedIndexes.length; ++i) {
                    n.b(jj._(this.oa)).deselect(selectedIndexes[i]);
                }
                n._(jj._(this.oa), (String)null);
                n._(jj._(this.oa)).setText("");
                n.b(jj._(this.oa)).removeAll();
                n.a(jj._(this.oa))._().a(n.b(jj._(this.oa)).a());
                n.a(jj._(this.oa))._().a(n.a(jj._(this.oa)));
                if (n.a(jj._(this.oa)) != null) {
                    jj._(this.oa).b(n.a(jj._(this.oa)) + ": " + n.a(jj._(this.oa)).a().b("msgLoadingData"));
                }
                if (a == null || !a.equals(n.a(jj._(this.oa)))) {
                    n._(jj._(this.oa)).b();
                }
                n._(jj._(this.oa));
                n._(jj._(this.oa))._();
                if (n.a(jj._(this.oa)) != null) {
                    jj._(this.oa).b(n.a(jj._(this.oa))._().getMessage());
                }
            }
            finally {
                n._(jj._(this.oa)).repaint();
                n._(jj._(this.oa)).a();
                jj._(this.oa).b(true);
            }
        }
    }
}
