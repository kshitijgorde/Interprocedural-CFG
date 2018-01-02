import java.awt.Choice;
import java.awt.event.ItemEvent;

// 
// Decompiled by Procyon v0.5.30
// 

class Gea extends Thread
{
    private final ItemEvent O;
    private final Cea N;
    
    Gea(final Cea n, final ItemEvent o) {
        this.N = n;
        this.O = o;
    }
    
    public void run() {
        synchronized (Cea.a(this.N)) {
            try {
                Cea.a(this.N).a(super.b(Cea.a(this.N)).a().a("msgIndicatorCalculationBegin"));
                final String selectedItem = ((Choice)this.O.getItemSelectable()).getSelectedItem();
                if (this.O.getStateChange() == 1) {
                    if (this.O.getSource() == super.b(Cea.a(this.N))) {
                        if (selectedItem.equals(super.b(Cea.a(this.N)).a().a("cbNoIndicator"))) {
                            super.b(Cea.a(this.N)).m(null);
                        }
                        else {
                            super.b(Cea.a(this.N)).m(selectedItem);
                        }
                    }
                    else if (this.O.getSource() == super._(Cea.a(this.N))) {
                        if (selectedItem.equals(super.b(Cea.a(this.N)).a().a("cbNoIndicator"))) {
                            super.b(Cea.a(this.N)).n(null);
                        }
                        else {
                            super.b(Cea.a(this.N)).n(selectedItem);
                        }
                    }
                    final public _ = super.b(Cea.a(this.N))._();
                    final public a = super.b(Cea.a(this.N)).a();
                    if (_ != null && a != null) {
                        Cea.a(this.N)._(4, super._(Cea.a(this.N)));
                    }
                    else if (_ != null || a != null) {
                        Cea.a(this.N)._(3, super._(Cea.a(this.N)));
                    }
                    else {
                        Cea.a(this.N)._(2, super._(Cea.a(this.N)));
                    }
                }
                super.b(Cea.a(this.N), (Choice)this.O.getSource());
                if (this.O.getSource() == super.n(Cea.a(this.N))) {
                    super.e(Cea.a(this.N));
                }
            }
            finally {
                super._(Cea.a(this.N)).b();
                Cea.a(this.N).a(super.b(Cea.a(this.N)).a().a("msgIndicatorCalculationEnd"));
                super._(Cea.a(this.N)).repaint();
                Cea.a(this.N).a(true);
            }
        }
    }
}
