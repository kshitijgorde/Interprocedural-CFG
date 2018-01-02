import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class Ip extends Thread
{
    private final wp va;
    
    Ip(final wp va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (wp.a(this.va)) {
            var._(wp.a(this.va))._();
            try {
                var._(wp.a(this.va))._().g(false);
                wp.a(this.va).setEnabled(false);
                wp.a(this.va).setCursor(Cursor.getPredefinedCursor(3));
                wp.a(this.va).b(var._(wp.a(this.va)).b()._("msgRefreshingData"));
                var._(wp.a(this.va))._().a(var.a(wp.a(this.va)));
                var._(wp.a(this.va))._()._(var.b(wp.a(this.va)));
                var._(wp.a(this.va))._().T();
                if (var.a(wp.a(this.va)) == null) {
                    var.b(wp.a(this.va)).a();
                }
            }
            finally {
                var.b(wp.a(this.va)).b();
                wp.a(this.va).b(var._(wp.a(this.va))._().getMessage());
                wp.a(this.va).setCursor(Cursor.getPredefinedCursor(0));
                wp.a(this.va).setEnabled(true);
                var.b(wp.a(this.va)).repaint();
                var._(wp.a(this.va))._().g(var.b(wp.a(this.va)).getState());
                var._(wp.a(this.va))._();
                var._(wp.a(this.va)).a(!var.a(wp.a(this.va)).getState());
            }
        }
    }
}
