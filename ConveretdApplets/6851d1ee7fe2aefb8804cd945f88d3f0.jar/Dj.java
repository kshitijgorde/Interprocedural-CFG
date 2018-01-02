import java.awt.Choice;
import java.awt.event.ItemEvent;

// 
// Decompiled by Procyon v0.5.30
// 

class Dj extends Thread
{
    private final ItemEvent pa;
    private final zj oa;
    
    Dj(final zj oa, final ItemEvent pa) {
        this.oa = oa;
        this.pa = pa;
    }
    
    public void run() {
        synchronized (zj.a(this.oa)) {
            try {
                zj.a(this.oa).b(n.a(zj.a(this.oa)).a().b("msgIndicatorCalculationBegin"));
                final String selectedItem = ((Choice)this.pa.getItemSelectable()).getSelectedItem();
                if (this.pa.getStateChange() == 1) {
                    if (this.pa.getSource() == n._(zj.a(this.oa))) {
                        if (selectedItem.equals(n.a(zj.a(this.oa)).a().b("cbNoIndicator"))) {
                            n.a(zj.a(this.oa)).n(null);
                        }
                        else {
                            n.a(zj.a(this.oa)).n(selectedItem);
                        }
                    }
                    else if (this.pa.getSource() == n.d(zj.a(this.oa))) {
                        if (selectedItem.equals(n.a(zj.a(this.oa)).a().b("cbNoIndicator"))) {
                            n.a(zj.a(this.oa)).c(null);
                        }
                        else {
                            n.a(zj.a(this.oa)).c(selectedItem);
                        }
                    }
                    final implements a = n.a(zj.a(this.oa)).a();
                    final implements b = n.a(zj.a(this.oa)).b();
                    if (a != null && b != null) {
                        zj.a(this.oa)._(4, n._(zj.a(this.oa)));
                    }
                    else if (a != null || b != null) {
                        zj.a(this.oa)._(3, n._(zj.a(this.oa)));
                    }
                    else {
                        zj.a(this.oa)._(2, n._(zj.a(this.oa)));
                    }
                }
                n.b(zj.a(this.oa), (Choice)this.pa.getSource());
                if (this.pa.getSource() == n.e(zj.a(this.oa))) {
                    n.n(zj.a(this.oa));
                }
            }
            finally {
                n._(zj.a(this.oa))._();
                zj.a(this.oa).b(n.a(zj.a(this.oa)).a().b("msgIndicatorCalculationEnd"));
                n._(zj.a(this.oa)).repaint();
                zj.a(this.oa).b(true);
            }
        }
    }
}
