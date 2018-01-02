// 
// Decompiled by Procyon v0.5.30
// 

class Uj extends Thread
{
    private final jj oa;
    
    Uj(final jj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (jj._(this.oa)) {
            try {
                final String a = n.a(jj._(this.oa));
                String s = n.a(jj._(this.oa));
                if (n.b(jj._(this.oa)).isMultipleMode()) {
                    final String[] selectedItems = n.b(jj._(this.oa)).getSelectedItems();
                    for (int i = 0; i < selectedItems.length; ++i) {
                        if (!selectedItems[i].equals(n.a(jj._(this.oa))) && !n.b(jj._(this.oa))._(selectedItems[i])) {
                            s = selectedItems[i];
                            break;
                        }
                    }
                }
                else {
                    s = n.b(jj._(this.oa)).getSelectedItem();
                }
                if (s != null) {
                    if (s.equals(n.a(jj._(this.oa)))) {
                        n.a(jj._(this.oa))._().a(n.a(jj._(this.oa)));
                    }
                    else {
                        if (n.a(jj._(this.oa)) != null && n.b(jj._(this.oa)).isMultipleMode()) {
                            n.b(jj._(this.oa)).a(n.a(jj._(this.oa)));
                        }
                        n._(jj._(this.oa), s);
                        n._(jj._(this.oa)).setText(n.a(jj._(this.oa)));
                        n.a(jj._(this.oa))._().a(n.a(jj._(this.oa)));
                        n.b(jj._(this.oa)).remove(n.a(jj._(this.oa)));
                        n.a(jj._(this.oa))._().a(n.b(jj._(this.oa)).a());
                    }
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
            }
            finally {
                n._(jj._(this.oa)).repaint();
                n._(jj._(this.oa)).a();
                jj._(this.oa).b(true);
            }
        }
    }
}
