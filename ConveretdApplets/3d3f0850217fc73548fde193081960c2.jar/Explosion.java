import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class Explosion extends Piece
{
    int seq;
    int seq2;
    
    public Explosion(final UFO_Attack a, final int n, final int n2) {
        super.a = a;
        super.w = 30;
        super.h = 30;
        super.opx = n;
        super.px = n;
        super.opy = n2;
        super.py = n2;
        super.active = true;
        super.img = a.missile_explosion;
    }
    
    public void draw() {
        this.set_draw_rectangles(super.a.paint_area, super.a.new_area);
        final Graphics graphics = super.a.backdrop.getGraphics();
        graphics.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.w, super.h);
        graphics.drawImage(super.a.bgimg, 0, 0, (ImageObserver)super.a);
        if (++this.seq2 % 2 == 0) {
            this.seq = ++this.seq % 5;
        }
        if (this.seq == 4) {
            super.active = false;
        }
        graphics.drawImage(super.img, super.a.new_area.x - super.w * this.seq, super.a.new_area.y, (ImageObserver)super.a);
        graphics.dispose();
        final Graphics graphics2 = super.a.buffer.getGraphics();
        graphics2.clipRect(super.a.new_area.x, super.a.new_area.y, super.w, super.h);
        graphics2.drawImage(super.a.backdrop, 0, 0, (ImageObserver)super.a);
        graphics2.dispose();
        final Graphics graphics3 = ((Component)super.a).getGraphics();
        graphics3.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.a.paint_area.width, super.a.paint_area.height);
        graphics3.drawImage(super.a.buffer, 0, 0, (ImageObserver)super.a);
        graphics3.dispose();
    }
    
    public void erase() {
        this.set_draw_rectangles(super.a.paint_area, super.a.new_area);
        final Graphics graphics = super.a.backdrop.getGraphics();
        graphics.clipRect(super.a.paint_area.x, super.a.paint_area.y, super.w, super.h);
        graphics.drawImage(super.a.bgimg, 0, 0, (ImageObserver)super.a);
        graphics.dispose();
        super.erase();
    }
}
