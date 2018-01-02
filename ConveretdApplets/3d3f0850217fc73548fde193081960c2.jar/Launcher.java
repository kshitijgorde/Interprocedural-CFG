import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class Launcher extends Piece
{
    public Launcher(final UFO_Attack a) {
        super.a = a;
        super.w = 12;
        super.h = 22;
        final int n = a.window_size.width / 2;
        super.opx = n;
        super.px = n;
        final int n2 = super.w / 2 + 1;
        super.opy = n2;
        super.py = n2;
        super.active = true;
        super.img = a.missile;
    }
    
    public void move() {
        super.opx = super.px;
        super.opy = super.py;
        final int n = super.a.mouse_x - super.px;
        final int abs = Math.abs(n);
        int n2 = 1;
        if (abs > 10) {
            n2 = 5;
        }
        else if (abs > 1) {
            n2 = abs / 2;
        }
        if (n != 0) {
            super.px += n2 * (n / abs);
            if (super.px < super.w / 2) {
                super.px = super.w / 2;
                return;
            }
            if (super.px > super.a.window_size.width - super.w / 2) {
                super.px = super.a.window_size.width - super.w / 2;
            }
        }
    }
    
    public boolean has_moved() {
        return super.px - super.opx != 0;
    }
    
    public void draw() {
        this.set_draw_rectangles(super.a.paint_area, super.a.new_area);
        final Graphics graphics = super.a.buffer.getGraphics();
        graphics.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.w, super.h);
        graphics.drawImage(super.a.backdrop, 0, 0, (ImageObserver)super.a);
        graphics.dispose();
        if (((Piece)super.a.M).active()) {
            super.a.buf_g.setColor(super.c);
            super.a.buf_g.fillRect(super.a.new_area.x, super.a.new_area.y, super.w, super.h);
        }
        else {
            final Graphics graphics2 = super.a.buffer.getGraphics();
            graphics2.clipRect(super.a.new_area.x, super.a.new_area.y, super.w, super.h);
            graphics2.drawImage(super.img, super.a.new_area.x, super.a.new_area.y, (ImageObserver)super.a);
            graphics2.dispose();
        }
        super.a.paint_area.add(super.a.new_area);
        final Graphics graphics3 = ((Component)super.a).getGraphics();
        graphics3.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.a.paint_area.width, super.a.paint_area.height);
        graphics3.drawImage(super.a.buffer, 0, 0, (ImageObserver)super.a);
        graphics3.dispose();
    }
}
