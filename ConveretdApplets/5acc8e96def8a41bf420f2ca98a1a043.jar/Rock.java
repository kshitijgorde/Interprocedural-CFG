import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Rock
{
    Shape s;
    Color col;
    int width;
    int height;
    int exptime;
    int re;
    int gr;
    int bl;
    int count;
    double scale;
    boolean alive;
    boolean explode;
    boolean revive;
    
    public void paint(final Graphics graphics) {
        if (this.alive) {
            this.s.paint(graphics);
            this.revive = false;
            return;
        }
        if (this.revive) {
            this.alive = true;
        }
    }
    
    public Rock(final int n, final int n2, final int width, final int height, final double n3, final boolean b) {
        this.width = width;
        this.height = height;
        this.scale = n3 * (this.width / 640.0);
        final double[] array = { -7.0, -4.0, -1.0, 6.0, 6.0, -5.0 };
        final double[] array2 = { 1.0, -6.0, -8.0, -2.0, 3.0, 4.0 };
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i] = (array[i] + (Math.random() - 0.5) * 3.0) * this.scale;
            array2[i] = (array2[i] + (Math.random() - 0.5) * 3.0) * this.scale;
        }
        final int n4 = (int)(Math.random() * 155.0 + 100.0);
        this.col = new Color(n4, n4 - 20, n4);
        this.s = new Shape(n, n2, array, array2, b, width, height, this.col);
        this.s.v_speed = (Math.random() - 0.5) / (5.0 * this.scale);
        this.s.x_speed = (Math.random() - 0.5) * (14.0 / this.scale);
        this.s.y_speed = (Math.random() - 0.5) * (14.0 / this.scale);
    }
    
    public boolean check(final Shape shape) {
        return this.alive && this.s.intersect(shape);
    }
    
    public void move() {
        if (this.alive) {
            this.s.move();
        }
        if (this.explode) {
            this.gr = 255 - this.exptime * 10;
            this.re = 255 - this.exptime * 6;
            this.bl = 255 - this.exptime * 10;
            this.s.setCol(this.re, this.gr, this.bl);
            if (this.exptime++ > 25) {
                this.explode = false;
                this.alive = false;
                this.exptime = 0;
            }
            this.revive = this.explode;
        }
    }
}
