import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class Rocket extends Applet
{
    double pie;
    double x;
    double y;
    double xSpeed;
    double ySpeed;
    double radius;
    double time;
    int xsize;
    int ysize;
    Color color;
    Color originalColor;
    int timer;
    static double[] cos;
    static double[] sin;
    static int xoffset;
    static int yoffset;
    
    Rocket(final double radius, final Color color, final int xsize, final int ysize) {
        this.pie = 3.14159265359;
        for (int i = 0; i < 360; ++i) {
            Rocket.cos[i] = Math.cos(i * (this.pie / 180.0));
        }
        for (int j = 0; j < 360; ++j) {
            Rocket.sin[j] = Math.sin(j * (this.pie / 180.0));
        }
        this.originalColor = color;
        this.color = color;
        this.xsize = xsize;
        this.ysize = ysize;
        this.radius = radius;
        this.initialize();
    }
    
    void initialize() {
        this.x = (int)(Math.random() * this.xsize);
        this.y = this.ysize;
        this.xSpeed = -2.0 + Math.random() * 4.0;
        this.ySpeed = -3.0 - Math.random() * 3.0;
        this.timer = 0;
        this.time = 0.0;
        this.color = this.originalColor;
    }
    
    void draw(final Graphics graphics) {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
        this.ySpeed += 0.1;
        this.xSpeed /= 1.01;
        if (this.ySpeed < -1.0) {
            graphics.setColor(this.color.darker());
            graphics.drawLine(Rocket.xoffset + (int)this.x, Rocket.yoffset + (int)this.y, Rocket.xoffset + (int)this.x, Rocket.yoffset + (int)this.y);
            return;
        }
        ++this.timer;
        graphics.setColor(this.color);
        Color color = this.color;
        if (this.timer % 6 == 0) {
            this.color = this.color.darker();
        }
        for (double n = this.timer; n < this.timer + 4; ++n) {
            if (n % 2.0 == 0.0) {
                color = color.darker();
                graphics.setColor(color);
            }
            this.time += 0.06 / this.timer;
            final double n2 = this.radius * this.time;
            for (double radius = this.radius; radius < this.pie * 2.0 + this.radius; radius += 0.6) {
                final double n3 = Rocket.cos[(int)(radius * 180.0 / this.pie % 360.0)] * n2;
                final double n4 = Rocket.sin[(int)(radius * 180.0 / this.pie % 360.0)] * n2;
                graphics.drawLine(Rocket.xoffset + (int)(this.x + n3), Rocket.yoffset + (int)(this.y + n4), Rocket.xoffset + (int)(this.x + n3), Rocket.yoffset + (int)(this.y + n4));
            }
        }
        if (this.timer == 26) {
            this.initialize();
        }
    }
    
    static {
        Rocket.cos = new double[360];
        Rocket.sin = new double[360];
    }
}
