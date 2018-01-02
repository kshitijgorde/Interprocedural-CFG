// 
// Decompiled by Procyon v0.5.30
// 

class cfa extends Thread
{
    private final mea N;
    
    cfa(final mea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (mea.a(this.N)) {
            try {
                final String a = super.a(mea.a(this.N));
                final int[] selectedIndexes = super.b(mea.a(this.N)).getSelectedIndexes();
                for (int i = 0; i < selectedIndexes.length; ++i) {
                    super.b(mea.a(this.N)).deselect(selectedIndexes[i]);
                }
                super._(mea.a(this.N), (String)null);
                super.a(mea.a(this.N)).setText("");
                super.b(mea.a(this.N)).removeAll();
                super.b(mea.a(this.N))._().a(super.b(mea.a(this.N))._());
                super.b(mea.a(this.N))._()._(super.a(mea.a(this.N)));
                if (super.a(mea.a(this.N)) != null) {
                    mea.a(this.N).a(super.a(mea.a(this.N)) + ": " + super.b(mea.a(this.N)).a().a("msgLoadingData"));
                }
                if (a == null || !a.equals(super.a(mea.a(this.N)))) {
                    super._(mea.a(this.N)).a();
                }
                super._(mea.a(this.N)).b();
                if (super.a(mea.a(this.N)) != null) {
                    mea.a(this.N).a(super.b(mea.a(this.N))._().getMessage());
                }
            }
            finally {
                super._(mea.a(this.N)).repaint();
                super._(mea.a(this.N))._();
                mea.a(this.N).a(true);
            }
        }
    }
}
