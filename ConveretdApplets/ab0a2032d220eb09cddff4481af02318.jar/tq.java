import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class tq extends Thread
{
    private final Object Ga;
    private final qp va;
    
    tq(final qp va, final Object ga) {
        this.va = va;
        this.Ga = ga;
    }
    
    public void run() {
        synchronized (qp._(this.va)) {
            try {
                qp._(this.va).setEnabled(false);
                qp._(this.va).setCursor(Cursor.getPredefinedCursor(3));
                String s = "";
                final String a = var.a(qp._(this.va));
                if (this.Ga == var._(qp._(this.va))) {
                    s = var._(qp._(this.va)).getText().toUpperCase();
                    if (s.length() > 0) {
                        var.b(qp._(this.va), s);
                        if (var.a(qp._(this.va)).isMultipleMode()) {
                            if (var._(qp._(this.va)).a(s)) {
                                var._(qp._(this.va)).remove(s);
                                if (var.a(qp._(this.va)) != null) {
                                    var._(qp._(this.va)).b(var.a(qp._(this.va)));
                                }
                            }
                            else if (var.a(qp._(this.va)) != null && !s.equals(var.a(qp._(this.va)))) {
                                var._(qp._(this.va)).b(var.a(qp._(this.va)));
                            }
                        }
                    }
                    else if (var.a(qp._(this.va)).isMultipleMode()) {
                        s = var._(qp._(this.va))._();
                        if (s == null) {
                            s = "";
                        }
                        var._(qp._(this.va)).setText(s);
                    }
                    var._(qp._(this.va), s);
                    var._(qp._(this.va))._().b(var._(qp._(this.va)).b());
                    var._(qp._(this.va))._().a(var.a(qp._(this.va)));
                    var._(qp._(this.va)).selectAll();
                }
                else if (this.Ga == var.a(qp._(this.va))) {
                    s = var.a(qp._(this.va)).getText().toUpperCase();
                    var.a(qp._(this.va)).setText(s);
                    var.b(qp._(this.va), s);
                    if (var.a(qp._(this.va)) != null && var.a(qp._(this.va)).length() > 0) {
                        var._(qp._(this.va), var.a(qp._(this.va)));
                    }
                    var.a(qp._(this.va), s);
                    var._(qp._(this.va))._()._(s);
                }
                if (s.length() > 0) {
                    qp._(this.va).b(s + ": " + var._(qp._(this.va)).b()._("msgLoadingData"));
                }
                else {
                    qp._(this.va).b(var._(qp._(this.va)).b()._("msgLoadingData"));
                }
                if (this.Ga == var._(qp._(this.va)) && (a == null || !a.equals(var.a(qp._(this.va))))) {
                    var.b(qp._(this.va)).a();
                }
                var.b(qp._(this.va)).b();
                qp._(this.va).b(var._(qp._(this.va))._().getMessage());
            }
            finally {
                qp._(this.va).setCursor(Cursor.getPredefinedCursor(0));
                qp._(this.va).setEnabled(true);
                var.b(qp._(this.va)).repaint();
                var._(qp._(this.va))._();
                var._(qp._(this.va)).a(!var.a(qp._(this.va)).getState());
            }
        }
    }
}
