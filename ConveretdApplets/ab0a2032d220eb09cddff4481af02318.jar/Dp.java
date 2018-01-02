import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

class Dp extends Thread
{
    private final xp va;
    
    Dp(final xp va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (xp.a(this.va)) {
            boolean b = false;
            try {
                xp.a(this.va).setEnabled(false);
                xp.a(this.va).setCursor(Cursor.getPredefinedCursor(3));
                final boolean n = var._(xp.a(this.va))._().n();
                if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday"))) {
                    var._(xp.a(this.va))._().M(0);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday1s"))) {
                    var._(xp.a(this.va))._().M(1);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday2s"))) {
                    var._(xp.a(this.va))._().M(2);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday3s"))) {
                    var._(xp.a(this.va))._().M(3);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday4s"))) {
                    var._(xp.a(this.va))._().M(4);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday5s"))) {
                    var._(xp.a(this.va))._().M(5);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday10s"))) {
                    var._(xp.a(this.va))._().M(10);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday15s"))) {
                    var._(xp.a(this.va))._().M(15);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday20s"))) {
                    var._(xp.a(this.va))._().M(20);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday30s"))) {
                    var._(xp.a(this.va))._().M(30);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday1"))) {
                    var._(xp.a(this.va))._().M(60);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday2"))) {
                    var._(xp.a(this.va))._().M(120);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday3"))) {
                    var._(xp.a(this.va))._().M(180);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday4"))) {
                    var._(xp.a(this.va))._().M(240);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday5"))) {
                    var._(xp.a(this.va))._().M(300);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday10"))) {
                    var._(xp.a(this.va))._().M(600);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday15"))) {
                    var._(xp.a(this.va))._().M(900);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday20"))) {
                    var._(xp.a(this.va))._().M(1200);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday30"))) {
                    var._(xp.a(this.va))._().M(1800);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbIntraday60"))) {
                    var._(xp.a(this.va))._().M(3600);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbDaily"))) {
                    var._(xp.a(this.va))._().M(100001);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbWeekly"))) {
                    var._(xp.a(this.va))._().M(100002);
                }
                else if (var.b(xp.a(this.va)).getSelectedItem().equals(var._(xp.a(this.va)).b()._("cbMonthly"))) {
                    var._(xp.a(this.va))._().M(100003);
                }
                if (n != var._(xp.a(this.va))._().n()) {
                    var.b(xp.a(this.va)).a();
                    b = true;
                }
            }
            finally {
                if (b) {
                    xp.a(this.va).b(var._(xp.a(this.va))._().i() + ": " + var._(xp.a(this.va)).b()._("msgLoadingData"));
                    var._(xp.a(this.va))._().T();
                }
                var.b(xp.a(this.va)).b();
                xp.a(this.va).b(var._(xp.a(this.va))._().getMessage());
                xp.a(this.va).setCursor(Cursor.getPredefinedCursor(0));
                xp.a(this.va).setEnabled(true);
                var.b(xp.a(this.va)).repaint();
                if (b) {
                    var._(xp.a(this.va))._();
                }
                var._(xp.a(this.va)).a(!var.a(xp.a(this.va)).getState());
            }
        }
    }
}
