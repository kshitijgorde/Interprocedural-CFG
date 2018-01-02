import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Rocket
{
    Bullet[] bullets;
    int _nof_bullets;
    int _xmax;
    int _ymax;
    int _size;
    double _g;
    int _trail;
    int _life_len;
    Color _fg;
    Color _bg;
    
    Rocket(final int nof_bullets, final int n, final int n2, final double n3, final double n4, final double g, final int size, final int xmax, final int ymax, final int trail, final int life_len, final Color fg, final Color bg) {
        this._fg = fg;
        this._bg = bg;
        this._nof_bullets = nof_bullets;
        this._g = g;
        this._xmax = xmax;
        this._ymax = ymax;
        this._size = size;
        this._trail = trail;
        this._life_len = life_len;
        this.bullets = new Bullet[this._nof_bullets];
        this.reinit(n, n2, n3, n4);
    }
    
    void reinit(final int n, final int n2, final double n3, final double n4) {
        for (int i = 0; i < this._nof_bullets; ++i) {
            final double n5 = Math.random() * 2.0 * 3.1414999961853027;
            final double random = Math.random();
            this.bullets[i] = new Bullet(n, n2, n3 + Math.cos(n5) * random, n4 + Math.sin(n5) * random, this._g, this._size, this._xmax, this._ymax, this._trail, this._life_len, this._fg, this._bg);
        }
    }
    
    void draw(final Graphics graphics) {
        boolean b = false;
        for (int i = 0; i < this._nof_bullets; ++i) {
            b = (this.bullets[i].draw(graphics) || b);
            this.bullets[i].move();
        }
        if (!b) {
            this.reinit(10 + (int)(Math.random() * (this._xmax - 20)), this._ymax - (int)(Math.random() * 20.0), (Math.random() - 0.5) * 2.0, -(Math.random() * 6.0));
        }
    }
    
    void update(final Graphics graphics) {
        boolean b = false;
        for (int i = 0; i < this._nof_bullets; ++i) {
            this.bullets[i].undraw(graphics);
            this.bullets[i].move();
            b = (this.bullets[i].draw(graphics) || b);
        }
        if (!b) {
            final int n = 10 + (int)(Math.random() * (this._xmax - 20));
            final int n2 = this._ymax - (int)(Math.random() * 20.0);
            final double n3 = (Math.random() - 0.5) * 2.0;
            final double n4 = -(Math.random() * 6.0);
            for (int j = 0; j < this._nof_bullets; ++j) {
                this.bullets[j].clearall(graphics);
            }
            this.reinit(n, n2, n3, n4);
        }
    }
}
