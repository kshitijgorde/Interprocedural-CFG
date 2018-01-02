import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class bq extends MouseMotionAdapter
{
    private final rp n;
    
    bq(final rp n) {
        this.n = n;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (rp.b(this.n)) {
            return;
        }
        if (this.n.o.a() instanceof jq && rp._(this.n)) {
            return;
        }
        synchronized (this.n) {
            if (rp.b(this.n)) {
                return;
            }
            if (this.n.o.a() instanceof jq && rp._(this.n)) {
                return;
            }
            final int _ = rp._(this.n, mouseEvent.getX(), mouseEvent.getY());
            if (rp.a(this.n)) {
                rp._(this.n);
                if (_ >= 0) {
                    rp._(this.n, this.n.b().e(mouseEvent.getX()), this.n.b(_).e(mouseEvent.getY()), _);
                }
            }
            if (rp.f(this.n)) {
                rp.a(this.n);
            }
            if (rp.a(this.n) != null) {
                this.n.p = mouseEvent.getX();
                this.n.q = mouseEvent.getY();
                rp.a(this.n).a(mouseEvent.getX(), mouseEvent.getY(), this.n.getGraphics(), rp._(this.n)[rp.a(this.n)], rp.a(this.n), this.n.b(), this.n.b(rp.a(this.n)));
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (rp.b(this.n)) {
            return;
        }
        if (this.n.o.a() instanceof jq && rp._(this.n)) {
            return;
        }
        synchronized (this.n) {
            if (rp.b(this.n)) {
                return;
            }
            if (this.n.o.a() instanceof jq && rp._(this.n)) {
                return;
            }
            int n = -1;
            final int _ = rp._(this.n, mouseEvent.getX(), mouseEvent.getY());
            if (rp.a(this.n)) {
                rp._(this.n);
                if (_ >= 0) {
                    n = rp._(this.n, this.n.b().e(mouseEvent.getX()), this.n.b(_).e(mouseEvent.getY()), _);
                }
            }
            if (rp.f(this.n)) {
                rp.a(this.n);
                if (mouseEvent.getX() > 0 && mouseEvent.getY() > 0 && n < 0 && this.n.a(0) > 0) {
                    n = this.n.a(0, this.n.a(0) - 1).b(this.n.b().e(mouseEvent.getX()));
                }
                if (n >= 0) {
                    rp.a(this.n, n, mouseEvent.getY());
                }
            }
        }
    }
}
