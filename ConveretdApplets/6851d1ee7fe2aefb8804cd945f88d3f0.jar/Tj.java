import java.awt.List;
import java.awt.event.ItemEvent;

// 
// Decompiled by Procyon v0.5.30
// 

class Tj extends Thread
{
    private final ItemEvent pa;
    private final jj oa;
    
    Tj(final jj oa, final ItemEvent pa) {
        this.oa = oa;
        this.pa = pa;
    }
    
    public void run() {
        synchronized (jj._(this.oa)) {
            try {
                final String a = n.a(jj._(this.oa));
                final List list = (List)this.pa.getItemSelectable();
                if (this.pa.getStateChange() == 1) {
                    final int intValue = (int)this.pa.getItem();
                    if (!list.isMultipleMode() || !n.b(jj._(this.oa))._(list.getItem(intValue)) || (n.a(jj._(this.oa)) != null && !n.a(jj._(this.oa)).equals(list.getItem(intValue)))) {
                        if (n.a(jj._(this.oa)) != null && list.isMultipleMode()) {
                            n.b(jj._(this.oa)).a(n.a(jj._(this.oa)));
                        }
                        n._(jj._(this.oa), list.getItem(intValue));
                        n._(jj._(this.oa)).setText(n.a(jj._(this.oa)));
                        n.a(jj._(this.oa))._().a(n.a(jj._(this.oa)));
                        n.b(jj._(this.oa)).remove(n.a(jj._(this.oa)));
                        n.a(jj._(this.oa))._().a(n.b(jj._(this.oa)).a());
                        if (n.a(jj._(this.oa)) != null) {
                            jj._(this.oa).b(n.a(jj._(this.oa)) + ": " + n.a(jj._(this.oa)).a().b("msgLoadingData"));
                        }
                        if (a == null || !a.equals(n.a(jj._(this.oa)))) {
                            n._(jj._(this.oa)).b();
                        }
                        n._(jj._(this.oa));
                        boolean a2 = false;
                        if (jj._(this.oa)._()) {
                            a2 = n.a(jj._(this.oa));
                        }
                        if (!a2) {
                            n._(jj._(this.oa))._();
                        }
                        if (n.a(jj._(this.oa)) != null) {
                            jj._(this.oa).b(n.a(jj._(this.oa))._().getMessage());
                        }
                    }
                }
                else {
                    final int intValue2 = (int)this.pa.getItem();
                    if (n.b(jj._(this.oa))._(list.getItem(intValue2)) || (n.a(jj._(this.oa)) != null && n.a(jj._(this.oa)).equals(list.getItem(intValue2)))) {
                        if (n.a(jj._(this.oa)) != null && !n.a(jj._(this.oa)).equals(list.getItem(intValue2))) {
                            n.b(jj._(this.oa)).remove(list.getItem(intValue2));
                            n.b(jj._(this.oa)).a(n.a(jj._(this.oa)));
                        }
                        n._(jj._(this.oa), n.b(jj._(this.oa)).b());
                        if (n.a(jj._(this.oa)) != null) {
                            n._(jj._(this.oa)).setText(n.a(jj._(this.oa)));
                        }
                        else {
                            n._(jj._(this.oa)).setText("");
                        }
                        n.a(jj._(this.oa))._().a(n.a(jj._(this.oa)));
                        n.a(jj._(this.oa))._().a(n.b(jj._(this.oa)).a());
                        if (n.a(jj._(this.oa)) != null) {
                            jj._(this.oa).b(n.a(jj._(this.oa)) + ": " + n.a(jj._(this.oa)).a().b("msgLoadingData"));
                        }
                        if (a == null || !a.equals(n.a(jj._(this.oa)))) {
                            n._(jj._(this.oa)).b();
                        }
                        n._(jj._(this.oa));
                        boolean a3 = false;
                        if (jj._(this.oa)._()) {
                            a3 = n.a(jj._(this.oa));
                        }
                        if (!a3) {
                            n._(jj._(this.oa))._();
                        }
                        if (n.a(jj._(this.oa)) != null) {
                            jj._(this.oa).b(n.a(jj._(this.oa))._().getMessage());
                        }
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
