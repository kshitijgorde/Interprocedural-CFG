import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class vp extends Thread
{
    private final boolean gEb;
    private final var n;
    
    vp(final var n, final boolean gEb) {
        this.n = n;
        this.gEb = gEb;
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
            String string = null;
            if (o != null) {
                string = o.toString();
            }
            To b = null;
            if (this.gEb && o != null && o instanceof n) {
                b = ((n)o).b();
            }
            try {
                this.n.setEnabled(false);
                this.n.setCursor(Cursor.getPredefinedCursor(3));
                final Qp qp = new Qp(var._(this.n), var._(this.n).b()._("strJSIndicatorDefinition"), b, var._(this.n).i(), var._(this.n).b());
                qp.show();
                if (qp.l()) {
                    final o _ = var._(this.n)._(qp.a());
                    String string2 = null;
                    if (_ != null) {
                        string2 = _.toString();
                    }
                    if (var.f(this.n) == var._(this.n)) {
                        if (_ != null) {
                            var._(this.n).i(_.toString());
                        }
                        else {
                            var._(this.n).i(null);
                        }
                    }
                    else if (var.f(this.n) == var.a(this.n)) {
                        if (_ != null) {
                            var._(this.n).j(_.toString());
                        }
                        else {
                            var._(this.n).j(null);
                        }
                    }
                    var.a(this.n, string, string2);
                    var.b(this.n);
                    var.b(this.n).b();
                }
            }
            finally {
                this.n.setCursor(Cursor.getPredefinedCursor(0));
                this.n.setEnabled(true);
                var.b(this.n).repaint();
            }
            var._(this.n).a(!var.a(this.n).getState());
        }
    }
}
