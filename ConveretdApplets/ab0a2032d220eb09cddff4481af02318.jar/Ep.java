import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class Ep extends Thread
{
    private final wp va;
    
    Ep(final wp va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (wp.a(this.va)) {
            var._(wp.a(this.va)).a(true);
            wp.a(this.va).setEnabled(false);
            wp.a(this.va).setCursor(Cursor.getPredefinedCursor(3));
            final Op op = new Op(var._(wp.a(this.va)), var._(wp.a(this.va)).b()._("strTimeframeSettings"), var._(wp.a(this.va))._().E(), var._(wp.a(this.va))._().G(), var._(wp.a(this.va)).b());
            op.show();
            if (op.l()) {
                final int e = op.E();
                final int f = op.F();
                var._(wp.a(this.va))._().K(e);
                var._(wp.a(this.va))._().L(f);
                var._(wp.a(this.va))._().U();
                var.b(wp.a(this.va)).b();
                var.b(wp.a(this.va)).repaint();
            }
            wp.a(this.va).setCursor(Cursor.getPredefinedCursor(0));
            wp.a(this.va).setEnabled(true);
            var._(wp.a(this.va))._();
            var._(wp.a(this.va)).a(!var.a(wp.a(this.va)).getState());
        }
    }
}
