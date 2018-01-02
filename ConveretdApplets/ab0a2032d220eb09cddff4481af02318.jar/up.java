import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class up extends Thread
{
    private final var n;
    
    up(final var n) {
        this.n = n;
    }
    
    public void run() {
        synchronized (this.n) {
            o o = null;
            if (var.f(this.n) == var._(this.n)) {
                o = var._(this.n).b();
            }
            else if (var.f(this.n) == var.a(this.n)) {
                o = var._(this.n)._();
            }
            if (o != null && o instanceof n) {
                try {
                    this.n.setEnabled(false);
                    this.n.setCursor(Cursor.getPredefinedCursor(3));
                    final Sp sp = new Sp(var._(this.n), var._(this.n).b()._("strConfirmIndicatorRemovalTitle"), var._(this.n).b()._("strConfirmIndicatorRemovalMessage") + ": " + o.toString() + " ?", var._(this.n).b()._("btnYes"), var._(this.n).b()._("btnNo"));
                    sp.show();
                    if (sp.l()) {
                        var._(this.n).a(o);
                        var.c(this.n, o.toString());
                        var.b(this.n);
                        var.b(this.n).b();
                    }
                }
                finally {
                    this.n.setCursor(Cursor.getPredefinedCursor(0));
                    this.n.setEnabled(true);
                    var.b(this.n).repaint();
                }
            }
            var._(this.n).a(!var.a(this.n).getState());
        }
    }
}
