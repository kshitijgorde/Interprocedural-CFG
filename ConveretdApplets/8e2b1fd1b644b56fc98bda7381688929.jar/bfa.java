// 
// Decompiled by Procyon v0.5.30
// 

class bfa extends Thread
{
    private final mea N;
    
    bfa(final mea n) {
        this.N = n;
    }
    
    public void run() {
        synchronized (mea.a(this.N)) {
            try {
                final String a = super.a(mea.a(this.N));
                String s = super.a(mea.a(this.N));
                if (super.b(mea.a(this.N)).isMultipleMode()) {
                    final String[] selectedItems = super.b(mea.a(this.N)).getSelectedItems();
                    for (int i = 0; i < selectedItems.length; ++i) {
                        if (!selectedItems[i].equals(super.a(mea.a(this.N))) && !super.b(mea.a(this.N)).a(selectedItems[i])) {
                            s = selectedItems[i];
                            break;
                        }
                    }
                }
                else {
                    s = super.b(mea.a(this.N)).getSelectedItem();
                }
                if (s != null) {
                    if (s.equals(super.a(mea.a(this.N)))) {
                        super.b(mea.a(this.N))._()._(super.a(mea.a(this.N)));
                    }
                    else {
                        if (super.a(mea.a(this.N)) != null && super.b(mea.a(this.N)).isMultipleMode()) {
                            super.b(mea.a(this.N)).b(super.a(mea.a(this.N)));
                        }
                        super._(mea.a(this.N), s);
                        super.a(mea.a(this.N)).setText(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N))._()._(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N)).remove(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N))._().a(super.b(mea.a(this.N))._());
                    }
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
            }
            finally {
                super._(mea.a(this.N)).repaint();
                super._(mea.a(this.N))._();
                mea.a(this.N).a(true);
            }
        }
    }
}
