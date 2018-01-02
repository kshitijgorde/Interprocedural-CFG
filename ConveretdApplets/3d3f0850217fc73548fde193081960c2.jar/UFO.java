import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class UFO extends Piece
{
    int seq;
    int seq2;
    
    public UFO(final UFO_Attack a) {
        super.a = a;
        super.vx = ((Math.random() > 0.5) ? 1 : -1);
        super.vy = -2;
        super.w = 20;
        super.h = 8;
        final int n = (int)(super.w / 2 + 1 + (a.window_size.width - super.w - 2) * Math.random());
        super.opx = n;
        super.px = n;
        final int n2 = a.window_size.height + super.h / 2 + 1;
        super.opy = n2;
        super.py = n2;
        super.active = true;
        super.img = a.ufostrip;
    }
    
    public void move() {
        super.opx = super.px;
        super.opy = super.py;
        super.px += super.vx;
        super.py += super.vy;
        if (super.py < -super.h / 2) {
            super.active = false;
        }
        if (super.px <= super.w / 2 || super.px >= super.a.window_size.width - super.w / 2 || Math.random() > 0.96) {
            super.vx = -super.vx;
        }
    }
    
    public void draw() {
        this.set_draw_rectangles(super.a.paint_area, super.a.new_area);
        final Graphics graphics = super.a.buffer.getGraphics();
        graphics.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.w, super.h);
        graphics.drawImage(super.a.backdrop, 0, 0, (ImageObserver)super.a);
        graphics.dispose();
        if (++this.seq2 % 4 == 0) {
            this.seq = ++this.seq % 4;
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
