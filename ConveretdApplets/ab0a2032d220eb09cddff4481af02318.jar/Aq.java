import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class Aq extends Thread
{
    private final kp va;
    
    Aq(final kp va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (kp.b(this.va)) {
            try {
                kp.b(this.va).setEnabled(false);
                kp.b(this.va).setCursor(Cursor.getPredefinedCursor(3));
                final String a = var.a(kp.b(this.va));
                String s = var.a(kp.b(this.va));
                if (var.a(kp.b(this.va)).isMultipleMode()) {
                    final String[] selectedItems = var.a(kp.b(this.va)).getSelectedItems();
                    for (int i = 0; i < selectedItems.length; ++i) {
                        if (!selectedItems[i].equals(var.a(kp.b(this.va))) && !var._(kp.b(this.va)).a(selectedItems[i])) {
                            s = selectedItems[i];
                            break;
                        }
                    }
                }
                else {
                    s = var.a(kp.b(this.va)).getSelectedItem();
                }
                if (s != null) {
                    if (s.equals(var.a(kp.b(this.va)))) {
                        var._(kp.b(this.va))._().a(var.a(kp.b(this.va)));
                    }
                    else {
                        if (var.a(kp.b(this.va)) != null && var.a(kp.b(this.va)).isMultipleMode()) {
                            var._(kp.b(this.va)).b(var.a(kp.b(this.va)));
                        }
                        var.a(kp.b(this.va), s);
                        var._(kp.b(this.va)).setText(var.a(kp.b(this.va)));
                        var._(kp.b(this.va))._().a(var.a(kp.b(this.va)));
                        var._(kp.b(this.va)).remove(var.a(kp.b(this.va)));
                        var._(kp.b(this.va))._().b(var._(kp.b(this.va)).b());
                    }
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
