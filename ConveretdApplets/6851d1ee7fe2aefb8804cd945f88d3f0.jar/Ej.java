// 
// Decompiled by Procyon v0.5.30
// 

class Ej extends Thread
{
    private final yj oa;
    
    Ej(final yj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (yj._(this.oa)) {
            try {
                n.b(yj._(this.oa)).setVisible(false);
                n.b(yj._(this.oa)).removeAll();
                n.a(yj._(this.oa))._().a(n.b(yj._(this.oa)).a());
                n.b(yj._(this.oa)).removeAll();
                if ("".equals(n.b(yj._(this.oa)).get(n.b(yj._(this.oa)).getSelectedItem()))) {
                    n.a(yj._(this.oa), n._(yj._(this.oa)), n.b(yj._(this.oa)).get(n.b(yj._(this.oa)).getSelectedItem()));
                }
                else {
                    n.a(yj._(this.oa), n.a(yj._(this.oa)), n.b(yj._(this.oa)).get(n.b(yj._(this.oa)).getSelectedItem()));
                }
            }
            finally {
                n._(yj._(this.oa))._();
                n.b(yj._(this.oa)).setVisible(n.b(yj._(this.oa)));
                yj._(this.oa).invalidate();
                yj._(this.oa).validate();
                n._(yj._(this.oa)).repaint();
                yj._(this.oa).b(true);
            }
        }
    }
}
