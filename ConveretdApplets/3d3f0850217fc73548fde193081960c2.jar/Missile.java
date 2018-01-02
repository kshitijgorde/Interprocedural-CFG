import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class Missile extends Piece
{
    int seq;
    
    public Missile(final UFO_Attack a) {
        super.a = a;
        final boolean b = false;
        super.opx = (b ? 1 : 0);
        super.px = (b ? 1 : 0);
        final boolean b2 = false;
        super.opy = (b2 ? 1 : 0);
        super.py = (b2 ? 1 : 0);
        super.vx = 0;
        super.vy = 7;
        super.w = 12;
        super.h = 22;
        super.active = false;
        super.img = a.missile;
    }
    
    public void move() {
        super.opx = super.px;
        super.opy = super.py;
        super.px = super.a.L.px;
        final int n = super.px - super.opx;
        int n2 = super.vy * super.vy - n * n;
        if (n2 > 0) {
            n2 = (int)Math.sqrt(n2);
        }
        if (n2 < 1) {
            n2 = 1;
        }
        super.py += n2;
        if (super.py > super.a.window_size.height + 2 * super.h) {
            super.active = false;
        }
    }
    
    public void draw() {
        this.set_draw_rectangles(super.a.paint_area, super.a.new_area);
        final Graphics graphics = super.a.buffer.getGraphics();
        graphics.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.w, super.h);
        graphics.drawImage(super.a.backdrop, 0, 0, (ImageObserver)super.a);
        graphics.dispose();
        final int n = super.px - super.opx;
        this.seq = 0;
        if (n > 0) {
            this.seq = 1;
        }
        else if (n < 0) {
            this.seq = 2;
        }
        final Graphics graphics2 = super.a.buffer.getGraphics();
        graphics2.clipRect(super.a.new_area.x, super.a.new_area.y, super.w, super.h);
        graphics2.drawImage(super.img, super.a.new_area.x - super.w * this.seq, super.a.new_area.y, (ImageObserver)super.a);
        graphics2.dispose();
        super.a.paint_area.add(super.a.new_area);
        final Graphics graphics3 = ((Component)super.a).getGraphics();
        graphics3.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.a.paint_area.width, super.a.paint_area.height);
        graphics3.drawImage(super.a.buffer, 0, 0, (ImageObserver)super.a);
        graphics3.dispose();
    }
}
