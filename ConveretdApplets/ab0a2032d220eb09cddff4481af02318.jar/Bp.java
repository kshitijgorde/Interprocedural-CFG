import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class Bp extends Thread
{
    private final zp va;
    
    Bp(final zp va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (zp._(this.va)) {
            try {
                zp._(this.va).setEnabled(false);
                zp._(this.va).setCursor(Cursor.getPredefinedCursor(3));
                zp._(this.va).b(var._(zp._(this.va)).b()._("msgIndicatorCalculationBegin"));
                zp._(this.va);
            }
            finally {
                zp._(this.va).b(var._(zp._(this.va)).b()._("msgIndicatorCalculationEnd"));
                zp._(this.va).setCursor(Cursor.getPredefinedCursor(0));
                zp._(this.va).setEnabled(true);
                var.b(zp._(this.va)).repaint();
                var._(zp._(this.va)).a(!var.a(zp._(this.va)).getState());
            }
        }
    }
}
