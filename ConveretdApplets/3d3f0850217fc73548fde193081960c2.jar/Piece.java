import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Piece
{
    UFO_Attack a;
    int px;
    int py;
    int opx;
    int opy;
    int w;
    int h;
    int vx;
    int vy;
    Color c;
    boolean active;
    Image img;
    
    public void set_pos(final int n, final int n2) {
        this.opx = n;
        this.px = n;
        this.opy = n2;
        this.py = n2;
    }
    
    public void set_vel(final int vx, final int vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    public void set_size(final int w, final int h) {
        this.w = w;
        this.h = h;
    }
    
    public void set_color(final Color c) {
        this.c = c;
    }
    
    public void set_draw_rectangles(final Rectangle rectangle, final Rectangle rectangle2) {
        final int height = this.a.window_size.height;
        final int n = this.px - this.w / 2;
        final int n2 = height - this.py - this.h / 2;
        rectangle.reshape(this.opx - this.w / 2, height - this.opy - this.h / 2, this.w, this.h);
        rectangle2.reshape(n, n2, this.w, this.h);
    }
    
    public boolean active() {
        return this.active;
    }
    
    public void active(final boolean active) {
        this.active = active;
    }
    
    public boolean collision(final Piece piece) {
        final int abs = Math.abs(this.px - piece.px);
        final int abs2 = Math.abs(this.py - piece.py);
        return abs < Math.max(this.w / 2, piece.w / 2) + 1 && abs2 < Math.max(this.h / 2, piece.h / 2) + 1;
    }
    
    public void draw() {
        this.set_draw_rectangles(this.a.paint_area, this.a.new_area);
        final Graphics graphics = this.a.buffer.getGraphics();
        graphics.clipRect(this.a.paint_area.x, this.a.paint_area.y, this.w, this.h);
        graphics.drawImage(this.a.backdrop, 0, 0, (ImageObserver)this.a);
        graphics.dispose();
        this.a.buf_g.setColor(this.c);
        this.a.buf_g.fillRect(this.a.new_area.x, this.a.new_area.y, this.w, this.h);
        this.a.paint_area.add(this.a.new_area);
        final Graphics graphics2 = ((Component)this.a).getGraphics();
        graphics2.clipRect(this.a.paint_area.x, this.a.paint_area.y, this.a.paint_area.width, this.a.paint_area.height);
        graphics2.drawImage(this.a.buffer, 0, 0, (ImageObserver)this.a);
        graphics2.dispose();
    }
    
    public void erase() {
        this.set_draw_rectangles(this.a.paint_area, this.a.new_area);
        this.a.paint_area.add(this.a.new_area);
        final Graphics graphics = this.a.buffer.getGraphics();
        graphics.clipRect(this.a.paint_area.x, this.a.paint_area.y, this.a.paint_area.width, this.a.paint_area.height);
        graphics.drawImage(this.a.backdrop, 0, 0, (ImageObserver)this.a);
        graphics.dispose();
        final Graphics graphics2 = ((Component)this.a).getGraphics();
        graphics2.clipRect(this.a.paint_area.x, this.a.paint_area.y, this.a.paint_area.width, this.a.paint_area.height);
        graphics2.drawImage(this.a.buffer, 0, 0, (ImageObserver)this.a);
        graphics2.dispose();
    }
    
    Piece() {
        this.active = false;
    }
}
