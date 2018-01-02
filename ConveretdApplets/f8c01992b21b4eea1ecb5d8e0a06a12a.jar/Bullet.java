import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Bullet
{
    double xx;
    double yy;
    double[] x;
    double[] y;
    double dx;
    double dy;
    double g;
    double f;
    int lastx;
    int lasty;
    int dotsize;
    int _trail;
    int _life_len;
    int t;
    int t2;
    int count;
    Color col;
    Color remcol;
    double xmax;
    double ymax;
    
    Bullet(final int n, final int n2, final double dx, final double dy, final double g, final int dotsize, final int n3, final int n4, final int trail, final int life_len, final Color col, final Color remcol) {
        this.f = 1.0;
        this._trail = 10;
        this._life_len = 100;
        this.col = Color.black;
        this.remcol = Color.white;
        this.col = col;
        this.remcol = remcol;
        this._trail = trail;
        this._life_len = life_len;
        this.t = 0;
        this.t2 = 1;
        this.x = new double[this._trail];
        this.y = new double[this._trail];
        for (int i = 0; i < this._trail; ++i) {
            this.x[i] = -this.dotsize * 2.0f;
            this.y[i] = -this.dotsize * 2.0f;
        }
        this.xx = n;
        this.yy = n2;
        this.dx = dx;
        this.dy = dy;
        this.g = g;
        this.dotsize = dotsize;
        this.xmax = n3;
        this.ymax = n4;
    }
    
    void move() {
        this.xx += this.dx;
        this.yy += this.dy;
        this.dx *= this.f;
        this.dy *= this.f;
        this.dy += this.g;
    }
    
    boolean draw(final Graphics graphics) {
        if (this.count++ < this._life_len) {
            this.x[this.t] = this.xx;
            this.y[this.t] = this.yy;
            final int n = (int)this.xx;
            final int n2 = (int)this.yy;
            graphics.setColor(this.col);
            graphics.fillRect(n, n2, this.dotsize, this.dotsize);
            ++this.t;
            if (this.t >= this._trail) {
                this.t = 0;
            }
            return n >= 0 && n2 >= 0 && n < this.xmax && n2 < this.ymax;
        }
        return this.t2 != this.t;
    }
    
    void undraw(final Graphics graphics) {
        final int n = (int)this.x[this.t2];
        final int n2 = (int)this.y[this.t2];
        ++this.t2;
        if (this.t2 >= this._trail) {
            this.t2 = 0;
        }
        graphics.setColor(this.remcol);
        graphics.fillRect(n, n2, this.dotsize, this.dotsize);
    }
    
    void clearall(final Graphics graphics) {
        for (int i = 0; i < this._trail; ++i) {
            this.undraw(graphics);
        }
    }
}
