import java.awt.Choice;
import java.awt.Cursor;
import java.awt.event.ItemEvent;

// 
// Decompiled by Procyon v0.5.30
// 

class Cp extends Thread
{
    private final ItemEvent wa;
    private final yp va;
    
    Cp(final yp va, final ItemEvent wa) {
        this.va = va;
        this.wa = wa;
    }
    
    public void run() {
        synchronized (yp.a(this.va)) {
            try {
                yp.a(this.va).setEnabled(false);
                yp.a(this.va).setCursor(Cursor.getPredefinedCursor(3));
                yp.a(this.va).b(var._(yp.a(this.va)).b()._("msgIndicatorCalculationBegin"));
                final String selectedItem = ((Choice)this.wa.getItemSelectable()).getSelectedItem();
                if (this.wa.getStateChange() == 1) {
                    if (this.wa.getSource() == var._(yp.a(this.va))) {
                        if (selectedItem.equals(var._(yp.a(this.va)).b()._("cbNoIndicator"))) {
                            var._(yp.a(this.va)).i(null);
                        }
                        else {
                            var._(yp.a(this.va)).i(selectedItem);
                        }
                    }
                    else if (this.wa.getSource() == var.a(yp.a(this.va))) {
                        if (selectedItem.equals(var._(yp.a(this.va)).b()._("cbNoIndicator"))) {
                            var._(yp.a(this.va)).j(null);
                        }
                        else {
                            var._(yp.a(this.va)).j(selectedItem);
                        }
                    }
                    final o b = var._(yp.a(this.va)).b();
                    final o _ = var._(yp.a(this.va))._();
                    if (b != null && _ != null) {
                        yp.a(this.va).e(4, var.b(yp.a(this.va)));
                    }
                    else if (b != null || _ != null) {
                        yp.a(this.va).e(3, var.b(yp.a(this.va)));
                    }
                    else {
                        yp.a(this.va).e(2, var.b(yp.a(this.va)));
                    }
                    var.b(yp.a(this.va)).V();
                    for (int i = 1; i < var.b(yp.a(this.va)).a().H(); ++i) {
                        var.b(yp.a(this.va)).a().N(i);
                    }
                    var.b(yp.a(this.va)).W();
                }
                var._(yp.a(this.va), (Choice)this.wa.getSource());
                if (this.wa.getSource() == var.f(yp.a(this.va))) {
                    var.b(yp.a(this.va));
                }
            }
            finally {
                var.b(yp.a(this.va)).b();
                yp.a(this.va).b(var._(yp.a(this.va)).b()._("msgIndicatorCalculationEnd"));
                yp.a(this.va).setCursor(Cursor.getPredefinedCursor(0));
                yp.a(this.va).setEnabled(true);
                var.b(yp.a(this.va)).repaint();
                var._(yp.a(this.va)).a(!var.a(yp.a(this.va)).getState());
            }
        }
    }
}
