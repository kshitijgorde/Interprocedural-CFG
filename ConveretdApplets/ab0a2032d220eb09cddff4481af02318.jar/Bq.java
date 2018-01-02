import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class Bq extends Thread
{
    private final kp va;
    
    Bq(final kp va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (kp.b(this.va)) {
            try {
                kp.b(this.va).setEnabled(false);
                kp.b(this.va).setCursor(Cursor.getPredefinedCursor(3));
                final String a = var.a(kp.b(this.va));
                final int[] selectedIndexes = var.a(kp.b(this.va)).getSelectedIndexes();
                for (int i = 0; i < selectedIndexes.length; ++i) {
                    var.a(kp.b(this.va)).deselect(selectedIndexes[i]);
                }
                var.a(kp.b(this.va), (String)null);
                var._(kp.b(this.va)).setText("");
                var._(kp.b(this.va)).removeAll();
                var._(kp.b(this.va))._().b(var._(kp.b(this.va)).b());
                var._(kp.b(this.va))._().a(var.a(kp.b(this.va)));
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
