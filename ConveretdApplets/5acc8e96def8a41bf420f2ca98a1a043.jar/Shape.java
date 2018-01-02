import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class Shape
{
    double x;
    double y;
    double x_speed;
    double y_speed;
    double angle;
    double v_speed;
    double random_v_speed;
    double random_x_speed;
    double random_y_speed;
    double[] shp_x;
    double[] shp_y;
    double[] v_len;
    double[] v_ang;
    int[] poly_x;
    int[] poly_y;
    int size;
    double v_len_max;
    int width;
    int height;
    int red;
    int green;
    int blue;
    boolean filled;
    boolean r_on;
    Polygon poly;
    Rectangle rec;
    Color col;
    
    public void random() {
        if (!this.r_on) {
            this.v_speed = this.random_v_speed;
            this.x_speed = this.random_x_speed;
            this.y_speed = this.random_y_speed;
            this.r_on = true;
        }
    }
    
    public void setCol(final int red, final int green, final int blue) {
        this.col = new Color(this.red = red, this.green = green, this.blue = blue);
    }
    
    public void setCol(final double n) {
        this.col = new Color((int)(this.red * n), (int)(this.green * n), (int)(this.blue * n));
    }
    
    public void setNullSpeed() {
        if (this.r_on) {
            this.v_speed = 0.0;
            this.x_speed = 0.0;
            this.y_speed = 0.0;
            this.r_on = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.col);
        if (this.filled) {
            graphics.fillPolygon(this.poly = new Polygon(this.poly_x, this.poly_y, this.size));
            return;
        }
        graphics.drawPolygon(this.poly = new Polygon(this.poly_x, this.poly_y, this.size));
    }
    
    public Shape(final int n, final int n2, final double[] array, final double[] array2, final boolean filled, final int width, final int height, final Color col) {
        this.rec = new Rectangle(0, 0, 0, 0);
        this.x = n;
        this.y = n2;
        this.x_speed = 0.0;
        this.y_speed = 0.0;
        this.filled = filled;
        this.width = width;
        this.height = height;
        this.col = col;
        this.red = this.col.getRed();
        this.green = this.col.getGreen();
        this.blue = this.col.getBlue();
        this.v_speed = 0.0;
        this.angle = 0.0;
        this.size = array.length;
        this.random_v_speed = (Math.random() - 0.5) * 3.141592653589793 / 10.0;
        this.random_x_speed = (Math.random() - 0.5) * 10.0;
        this.random_y_speed = (Math.random() - 0.5) * 10.0;
        this.poly_x = new int[this.size];
        this.poly_y = new int[this.size];
        this.shp_x = new double[this.size];
        this.shp_y = new double[this.size];
        this.v_len = new double[this.size];
        this.v_ang = new double[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.shp_x[i] = array[i];
            this.shp_y[i] = array2[i];
            this.poly_x[i] = (int)(array[i] + this.x);
            this.poly_y[i] = (int)(array2[i] + this.y);
            this.v_len[i] = Math.sqrt(this.shp_x[i] * this.shp_x[i] + this.shp_y[i] * this.shp_y[i]);
            this.v_ang[i] = Math.atan2(this.shp_y[i], this.shp_x[i]);
        }
        this.poly = new Polygon(this.poly_x, this.poly_y, this.size);
    }
    
    public void xySpeed(final double n) {
        this.x_speed -= Math.cos(this.angle) * n;
        this.y_speed -= Math.sin(this.angle) * n;
    }
    
    public void move() {
        this.x += this.x_speed;
        this.y += this.y_speed;
        this.angle += this.v_speed;
        for (int i = 0; i < this.size; ++i) {
            this.poly_x[i] = (int)(this.v_len[i] * Math.cos(this.v_ang[i] + this.angle) + this.x);
            this.poly_y[i] = (int)(this.v_len[i] * Math.sin(this.v_ang[i] + this.angle) + this.y);
        }
        if ((int)this.x > this.width || (int)this.y > this.height || (int)this.y < 0 || (int)this.x < 0) {
            this.rec = this.poly.getBoundingBox();
            if (this.x > this.width + this.rec.width / 2) {
                this.x = -(this.rec.width / 2);
                return;
            }
            if (this.y > this.height + this.rec.height / 2) {
                this.y = -(this.rec.height / 2);
                return;
            }
            if (this.x + this.rec.width / 2 < 0.0) {
                this.x = this.width + this.rec.width / 2;
                return;
            }
            if (this.y + this.rec.height / 2 < 0.0) {
                this.y = this.height + this.rec.height / 2;
            }
        }
    }
    
    public boolean intersect(final Shape shape) {
        for (int i = 0; i < shape.size; ++i) {
            if (this.poly.inside(shape.poly_x[i], shape.poly_y[i])) {
                return true;
            }
        }
        for (int j = 0; j < this.size; ++j) {
            if (shape.poly.inside(this.poly_x[j], this.poly_y[j])) {
                return true;
            }
        }
        return false;
    }
}
