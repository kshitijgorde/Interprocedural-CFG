import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class CollideBall
{
    int width;
    int height;
    public static final int diameter = 20;
    double x;
    double y;
    double xinc;
    double yinc;
    double coll_x;
    double coll_y;
    boolean collide;
    Color color;
    Graphics g;
    Rectangle r;
    
    public CollideBall(final int w, final int h, final int x, final int y, final double xinc, final double yinc, final Color c) {
        this.width = w;
        this.height = h;
        this.x = x;
        this.y = y;
        this.xinc = xinc;
        this.yinc = yinc;
        this.color = c;
        this.r = new Rectangle(150, 80, 130, 90);
    }
    
    public void alterRect(final int x, final int y, final int w, final int h) {
        this.r.move(x, y);
        this.r.resize(w, h);
    }
    
    public void hit(final CollideBall b) {
        if (!this.collide) {
            this.coll_x = b.getCenterX();
            this.coll_y = b.getCenterY();
            this.collide = true;
        }
    }
    
    public void paint(final Graphics gr) {
        (this.g = gr).setColor(this.color);
        this.g.fillOval((int)this.x, (int)this.y, 20, 20);
        this.g.setColor(Color.white);
        this.g.drawArc((int)this.x, (int)this.y, 20, 20, 45, 180);
        this.g.setColor(Color.darkGray);
        this.g.drawArc((int)this.x, (int)this.y, 20, 20, 225, 180);
    }
    
    public double getCenterY() {
        return this.y + 10.0;
    }
    
    public double getCenterX() {
        return this.x + 10.0;
    }
    
    public void move() {
        if (this.collide) {
            final double xvect = this.coll_x - this.getCenterX();
            final double yvect = this.coll_y - this.getCenterY();
            if ((this.xinc > 0.0 && xvect > 0.0) || (this.xinc < 0.0 && xvect < 0.0)) {
                this.xinc = -this.xinc;
            }
            if ((this.yinc > 0.0 && yvect > 0.0) || (this.yinc < 0.0 && yvect < 0.0)) {
                this.yinc = -this.yinc;
            }
            this.collide = false;
        }
        this.x += this.xinc;
        this.y += this.yinc;
        if (this.x < 6.0 || this.x > this.width - 20) {
            this.xinc = -this.xinc;
            this.x += this.xinc;
        }
        if (this.y < 6.0 || this.y > this.height - 20) {
            this.yinc = -this.yinc;
            this.y += this.yinc;
        }
        int x = (int)this.x;
        int y = (int)this.y;
        if (x > this.r.x - 20 && x < this.r.x - 20 + 7 && this.xinc > 0.0 && y > this.r.y - 20 && y < this.r.y + this.r.height) {
            this.xinc = -this.xinc;
            x += (int)this.xinc;
        }
        if (x < this.r.x + this.r.width && x > this.r.x + this.r.width - 7 && this.xinc < 0.0 && y > this.r.y - 20 && y < this.r.y + this.r.height) {
            this.xinc = -this.xinc;
            x += (int)this.xinc;
        }
        if (y > this.r.y - 20 && y < this.r.y - 20 + 7 && this.yinc > 0.0 && x > this.r.x - 20 && x < this.r.x + this.r.width) {
            this.yinc = -this.yinc;
            y += (int)this.yinc;
        }
        if (y < this.r.y + this.r.height && y > this.r.y + this.r.height - 7 && this.yinc < 0.0 && x > this.r.x - 20 && x < this.r.x + this.r.width) {
            this.yinc = -this.yinc;
            y += (int)this.yinc;
        }
    }
}
