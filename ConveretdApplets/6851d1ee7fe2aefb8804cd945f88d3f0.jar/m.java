import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class m extends MouseMotionAdapter
{
    private final continue ta;
    
    m(final continue ta) {
        this.ta = ta;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (continue.b(this.ta)) {
            return;
        }
        if (this.ta.rka.b() instanceof try && continue._(this.ta)) {
            return;
        }
        synchronized (this.ta) {
            if (continue.b(this.ta)) {
                return;
            }
            if (this.ta.rka.b() instanceof try && continue._(this.ta)) {
                return;
            }
            final int b = continue.b(this.ta, mouseEvent.getX(), mouseEvent.getY());
            if (continue.a(this.ta)) {
                continue.b(this.ta);
                if (b >= 0) {
                    continue._(this.ta, this.ta._().a(mouseEvent.getX()), this.ta._(b).a(mouseEvent.getY()), b);
                }
            }
            if (continue.m(this.ta)) {
                continue._(this.ta);
            }
            if (continue.b(this.ta) != null) {
                this.ta.ska = mouseEvent.getX();
                this.ta.tka = mouseEvent.getY();
                continue.b(this.ta)._(mouseEvent.getX(), mouseEvent.getY(), this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (continue.b(this.ta)) {
            return;
        }
        if (this.ta.rka.b() instanceof try && continue._(this.ta)) {
            return;
        }
        synchronized (this.ta) {
            if (continue.b(this.ta)) {
                return;
            }
            if (this.ta.rka.b() instanceof try && continue._(this.ta)) {
                return;
            }
            int n = -1;
            final int b = continue.b(this.ta, mouseEvent.getX(), mouseEvent.getY());
            if (continue.a(this.ta)) {
                continue.b(this.ta);
                if (b >= 0) {
                    n = continue._(this.ta, this.ta._().a(mouseEvent.getX()), this.ta._(b).a(mouseEvent.getY()), b);
                }
            }
            if (continue.m(this.ta)) {
                continue._(this.ta);
                if (mouseEvent.getX() > 0 && mouseEvent.getY() > 0 && n < 0 && this.ta.a(0) > 0) {
                    n = this.ta.a(0, this.ta.a(0) - 1).b(this.ta._().a(mouseEvent.getX()));
                }
                if (n >= 0) {
                    continue.b(this.ta, n, mouseEvent.getY());
                }
            }
        }
    }
}
