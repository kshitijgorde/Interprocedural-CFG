import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class n extends MouseMotionAdapter
{
    private final d da;
    
    n(final d da) {
        this.da = da;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (d.a(this.da)) {
            return;
        }
        if (this.da.q._() instanceof u && d.b(this.da)) {
            return;
        }
        synchronized (this.da) {
            if (d.a(this.da)) {
                return;
            }
            if (this.da.q._() instanceof u && d.b(this.da)) {
                return;
            }
            final int _ = d._(this.da, mouseEvent.getX(), mouseEvent.getY());
            if (d._(this.da)) {
                d.b(this.da);
                if (_ >= 0) {
                    d.b(this.da, this.da.a()._(mouseEvent.getX()), this.da._(_)._(mouseEvent.getY()), _);
                }
            }
            if (d.h(this.da)) {
                d._(this.da);
            }
            if (d._(this.da) != null) {
                this.da.Hra = mouseEvent.getX();
                this.da.Ira = mouseEvent.getY();
                d._(this.da)._(mouseEvent.getX(), mouseEvent.getY(), this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (d.a(this.da)) {
            return;
        }
        if (this.da.q._() instanceof u && d.b(this.da)) {
            return;
        }
        synchronized (this.da) {
            if (d.a(this.da)) {
                return;
            }
            if (this.da.q._() instanceof u && d.b(this.da)) {
                return;
            }
            int n = -1;
            final int _ = d._(this.da, mouseEvent.getX(), mouseEvent.getY());
            if (d._(this.da)) {
                d.b(this.da);
                if (_ >= 0) {
                    n = d.b(this.da, this.da.a()._(mouseEvent.getX()), this.da._(_)._(mouseEvent.getY()), _);
                }
            }
            if (d.h(this.da)) {
                d._(this.da);
                if (mouseEvent.getX() > 0 && mouseEvent.getY() > 0 && n < 0 && this.da.a(0) > 0) {
                    n = this.da._(0, this.da.a(0) - 1).b(this.da.a()._(mouseEvent.getX()));
                }
                if (n >= 0) {
                    d.b(this.da, n, mouseEvent.getY());
                }
            }
        }
    }
}
