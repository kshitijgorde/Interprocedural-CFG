import java.awt.List;
import java.awt.Cursor;
import java.awt.event.ItemEvent;

// 
// Decompiled by Procyon v0.5.30
// 

class zq extends Thread
{
    private final ItemEvent wa;
    private final kp va;
    
    zq(final kp va, final ItemEvent wa) {
        this.va = va;
        this.wa = wa;
    }
    
    public void run() {
        synchronized (kp.b(this.va)) {
            try {
                kp.b(this.va).setEnabled(false);
                kp.b(this.va).setCursor(Cursor.getPredefinedCursor(3));
                final String a = var.a(kp.b(this.va));
                final List list = (List)this.wa.getItemSelectable();
                if (this.wa.getStateChange() == 1) {
                    final int intValue = (int)this.wa.getItem();
                    if (!list.isMultipleMode() || !var._(kp.b(this.va)).a(list.getItem(intValue)) || (var.a(kp.b(this.va)) != null && !var.a(kp.b(this.va)).equals(list.getItem(intValue)))) {
                        if (var.a(kp.b(this.va)) != null && list.isMultipleMode()) {
                            var._(kp.b(this.va)).b(var.a(kp.b(this.va)));
                        }
                        var.a(kp.b(this.va), list.getItem(intValue));
                        var._(kp.b(this.va)).setText(var.a(kp.b(this.va)));
                        var._(kp.b(this.va))._().a(var.a(kp.b(this.va)));
                        var._(kp.b(this.va)).remove(var.a(kp.b(this.va)));
                        var._(kp.b(this.va))._().b(var._(kp.b(this.va)).b());
                        if (var.a(kp.b(this.va)) != null) {
                            kp.b(this.va).b(var.a(kp.b(this.va)) + ": " + var._(kp.b(this.va)).b()._("msgLoadingData"));
                        }
                        if (a == null || !a.equals(var.a(kp.b(this.va)))) {
                            var.b(kp.b(this.va)).a();
                        }
                        var.b(kp.b(this.va)).b();
                        if (var.a(kp.b(this.va)) != null) {
                            kp.b(this.va).b(var._(kp.b(this.va))._().getMessage());
                        }
                    }
                }
                else {
                    final int intValue2 = (int)this.wa.getItem();
                    if (var._(kp.b(this.va)).a(list.getItem(intValue2)) || (var.a(kp.b(this.va)) != null && var.a(kp.b(this.va)).equals(list.getItem(intValue2)))) {
                        if (var.a(kp.b(this.va)) != null && !var.a(kp.b(this.va)).equals(list.getItem(intValue2))) {
                            var._(kp.b(this.va)).remove(list.getItem(intValue2));
                            var._(kp.b(this.va)).b(var.a(kp.b(this.va)));
                        }
                        var.a(kp.b(this.va), var._(kp.b(this.va))._());
                        if (var.a(kp.b(this.va)) != null) {
                            var._(kp.b(this.va)).setText(var.a(kp.b(this.va)));
                        }
                        else {
                            var._(kp.b(this.va)).setText("");
                        }
                        var._(kp.b(this.va))._().a(var.a(kp.b(this.va)));
                        var._(kp.b(this.va))._().b(var._(kp.b(this.va)).b());
                        if (var.a(kp.b(this.va)) != null) {
                            kp.b(this.va).b(var.a(kp.b(this.va)) + ": " + var._(kp.b(this.va)).b()._("msgLoadingData"));
                        }
                        if (a == null || !a.equals(var.a(kp.b(this.va)))) {
                            var.b(kp.b(this.va)).a();
                        }
                        var.b(kp.b(this.va)).b();
                        if (var.a(kp.b(this.va)) != null) {
                            kp.b(this.va).b(var._(kp.b(this.va))._().getMessage());
                        }
                    }
                }
            }
            finally {
                kp.b(this.va).setCursor(Cursor.getPredefinedCursor(0));
                kp.b(this.va).setEnabled(true);
                var.b(kp.b(this.va)).repaint();
                var._(kp.b(this.va))._();
                var._(kp.b(this.va)).a(!var.a(kp.b(this.va)).getState());
            }
        }
    }
}
