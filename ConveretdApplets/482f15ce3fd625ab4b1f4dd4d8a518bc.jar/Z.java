import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Z implements MouseMotionListener, MouseListener
{
    private au a;
    private aE a;
    
    public Z(final au a, final aE a2) {
        this.a = a;
        this.a = a2;
    }
    
    private void a(final MouseEvent mouseEvent) {
        if (this.a == null || this.a.a == null) {
            return;
        }
        final double a = this.a.a.a;
        final double n = (mouseEvent.getX() - this.a.a.f) / a;
        final double n2 = (mouseEvent.getY() - this.a.a.g) / a;
        int b;
        if ((b = (int)n) < 0) {
            b = 0;
        }
        if (b > 255) {
            b = 255;
        }
        this.a.a.b = b;
        int c;
        if ((c = (int)n2) < 0) {
            c = 0;
        }
        if (c > 239) {
            c = 239;
        }
        this.a.a.c = c;
    }
    
    private int a(final MouseEvent mouseEvent) {
        if (this.a == null || this.a.a == null) {
            return 0;
        }
        int n;
        if ((n = (int)((mouseEvent.getX() - this.a.a.f) / this.a.a.a)) < 0) {
            n = 0;
        }
        if (n > 255) {
            n = 255;
        }
        return n;
    }
    
    private int b(final MouseEvent mouseEvent) {
        if (this.a == null || this.a.a == null) {
            return 0;
        }
        int n;
        if ((n = (int)((mouseEvent.getY() - this.a.a.g) / this.a.a.a)) < 0) {
            n = 0;
        }
        if (n > 239) {
            n = 239;
        }
        return n;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (this.a != null && this.a.a != null) {
            if (this.a.a != null && this.a.a.b() && !this.a.a.a.a) {
                if (mouseEvent.getButton() == 1 && this.a.a.a.a(this.a(mouseEvent), this.b(mouseEvent), mouseEvent.getClickCount() == 2)) {
                    if (this.a.b()) {
                        this.a.b();
                    }
                    if (!this.a.b()) {
                        this.a.a(false);
                    }
                }
                return;
            }
            try {
                if (mouseEvent.getButton() == 3 && (this.a.c() || (this.a.a() != 8 && !this.a.a.a.a))) {
                    if (this.a.a.q) {
                        this.a.a("Menu Access Disabled...", true);
                        return;
                    }
                    this.a.a(new an(1, this.a));
                    return;
                }
            }
            catch (Throwable t) {}
            if (this.a.a.g) {
                if (this.a.a.e && this.a.a.d == 0) {
                    final an an = new an(12, this.a);
                    this.a.a.a(true);
                    an.c = 15;
                    this.a.a(an);
                    return;
                }
                this.a(mouseEvent);
                this.a.a.a = 16;
            }
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.a != null && this.a.a != null && this.a.a.g) {
            this.a(mouseEvent);
            this.a.a.a = 0;
        }
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (this.a.a != null && this.a.a.b() && !this.a.a.a.a) {
            this.a.a.a.a(this.a(mouseEvent), this.b(mouseEvent));
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
}
