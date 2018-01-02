import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Shot
{
    Shape s;
    Color col;
    int width;
    int height;
    int life;
    boolean alive;
    boolean shooting;
    boolean trigger;
    
    public Shot(final int n, final int n2, final int width, final int height, final int n3, final Color color) {
        this.width = width;
        this.height = height;
        final double[] array = { -2.0, -1.0, 0.0, 1.0, 2.0, 1.0, 0.0, -1.0 };
        final double[] array2 = { 0.0, -1.0, -2.0, -1.0, 0.0, 1.0, 2.0, 1.0 };
        for (int i = 0; i < array.length; ++i) {
            array[i] *= n3;
            array2[i] *= n3;
        }
        this.s = new Shape(n, n2, array, array2, true, width, height, color);
    }
    
    public void paint(final Graphics graphics) {
        if (this.alive) {
            this.s.paint(graphics);
            if (this.life-- <= 0) {
                this.alive = false;
            }
            this.trigger = false;
            return;
        }
        if (this.trigger) {
            this.alive = true;
        }
    }
    
    public void hit() {
        this.alive = false;
        this.trigger = false;
        this.s.x_speed = 0.0;
        this.s.y_speed = 0.0;
    }
    
    public boolean check(final Shape shape) {
        if (this.alive && this.s.intersect(shape)) {
            this.hit();
            return true;
        }
        return false;
    }
    
    public void move() {
        if (this.alive) {
            this.s.move();
        }
    }
}
