import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class aq implements MouseListener
{
    private final rp n;
    
    aq(final rp n) {
        this.n = n;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        rp.b(this.n);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.n) {
            rp.a(this.n);
            rp._(this.n);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        synchronized (this.n) {
            if (mouseEvent.isPopupTrigger()) {
                rp._(this.n, mouseEvent);
                return;
            }
            if (mouseEvent.getModifiers() == 4) {
                return;
            }
            if (mouseEvent.getModifiers() == 8) {
                return;
            }
            if (rp.b(this.n)) {
                return;
            }
            if (this.n.o.a() instanceof jq && rp._(this.n)) {
                return;
            }
            if (this.n.o.a() instanceof jq && ((jq)this.n.o.a()).d() < 2) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            int b = rp.b(this.n);
            rp.b(this.n, -1);
            for (int i = 0; i < this.n.i(); ++i) {
                if (y >= rp._(this.n)[i].y && y <= rp._(this.n)[i].y + rp._(this.n)[i].height) {
                    rp.b(this.n, i);
                }
            }
            if (rp.a(this.n) == -1 || this.n.a(rp.a(this.n)) == 0) {
                return;
            }
            if (b == 4) {
                if (mouseEvent.isShiftDown()) {
                    b = 2;
                }
                else if (mouseEvent.isControlDown()) {
                    b = 3;
                }
                else {
                    b = 1;
                }
            }
            switch (b) {
                case 1: {
                    try {
                        rp.b(this.n, rp.b(this.n).getConstructor((Class<?>[])null).newInstance((Object[])null));
                        rp.a(this.n).setColor(rp.b(this.n));
                        rp.a(this.n).k(rp._(this.n));
                        if (rp.a(this.n) instanceof cq) {
                            ((cq)rp.a(this.n)).a(this.n.o.j() + 1, this.n.o.u() - 1);
                        }
                    }
                    catch (Exception ex) {
                        return;
                    }
                    rp.a(this.n).m(this.n.b().e(x), this.n.b(rp.a(this.n)).e(y));
                    rp.a(this.n).a(x, y, this.n.b(), this.n.b(rp.a(this.n)));
                    rp.a(this.n).a(this.n.getGraphics(), rp._(this.n)[rp.a(this.n)], rp.a(this.n), this.n.b(), this.n.b(rp.a(this.n)));
                    this.n.b(rp.a(this.n), rp.a(this.n));
                    break;
                }
                case 2: {
                    for (int j = 0; j < this.n.b(rp.a(this.n)); ++j) {
                        if (this.n._(rp.a(this.n), j)._(x, y, this.n.b(), this.n.b(rp.a(this.n)))) {
                            rp.b(this.n, this.n._(rp.a(this.n), j));
                            break;
                        }
                    }
                    if (rp.a(this.n) != null) {
                        rp.a(this.n).a(x, y, this.n.b(), this.n.b(rp.a(this.n)));
                        break;
                    }
                    break;
                }
                case 3: {
                    for (int k = 0; k < this.n.b(rp.a(this.n)); ++k) {
                        if (this.n._(rp.a(this.n), k)._(x, y, this.n.b(), this.n.b(rp.a(this.n)))) {
                            rp.b(this.n, this.n._(rp.a(this.n), k));
                            break;
                        }
                    }
                    if (rp.a(this.n) != null) {
                        this.n._(rp.a(this.n), rp.a(this.n));
                        rp.a(this.n).b(this.n.getGraphics(), rp._(this.n)[rp.a(this.n)], rp.a(this.n), this.n.b(), this.n.b(rp.a(this.n)));
                        rp.b(this.n, null);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        synchronized (this.n) {
            if (rp.b(this.n)) {
                return;
            }
            if (this.n.o.a() instanceof jq && rp._(this.n)) {
                return;
            }
            if (rp.a(this.n) != null) {
                rp.a(this.n)._(this.n.getGraphics(), rp._(this.n)[rp.a(this.n)], rp.a(this.n), this.n.b(), this.n.b(rp.a(this.n)));
                rp.b(this.n, null);
            }
            if (mouseEvent.isPopupTrigger()) {
                rp._(this.n, mouseEvent);
            }
        }
    }
}
