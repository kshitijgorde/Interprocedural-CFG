import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Background_Star
{
    int Xmax;
    int Ymax;
    int z;
    double x;
    double y;
    
    public void BDraw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        this.x = this.x - n * n5 / 25.0 - n2 * n6 / 25.0;
        this.y = this.y + n * n6 / 25.0 - n2 * n5 / 25.0;
        if (this.x < -this.Xmax) {
            this.x += 2 * this.Xmax;
        }
        if (this.x > this.Xmax) {
            this.x -= 2 * this.Xmax;
        }
        if (this.y < -this.Ymax) {
            this.y += 2 * this.Ymax;
        }
        if (this.y > this.Ymax) {
            this.y -= 2 * this.Ymax;
        }
        int n7 = (int)(this.x * n5 - this.y * n6) + this.Xmax;
        int n8 = (int)(this.x * n6 + this.y * n5) + this.Ymax;
        if (n7 < 0) {
            n7 += 2 * this.Xmax;
        }
        if (n7 > 2 * this.Xmax) {
            n7 -= 2 * this.Xmax;
        }
        if (n8 < 0) {
            n8 += 2 * this.Ymax;
        }
        if (n8 > 2 * this.Ymax) {
            n8 -= 2 * this.Ymax;
        }
        if (this.z > 50) {
            graphics.setColor(Color.gray);
        }
        else if (this.z > 25) {
            graphics.setColor(Color.lightGray);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        int n9 = (100 - this.z) / 50;
        if (n9 == 0) {
            n9 = 1;
        }
        graphics.fillRect(n7, n8, n9, n9);
    }
    
    Background_Star(final int n, final int n2) {
        this.Xmax = n / 2;
        this.Ymax = n2 / 2;
        this.x = Math.random() * n - this.Xmax;
        this.y = Math.random() * n2 - this.Ymax;
        if (this.x == 0.0 && this.y == 0.0) {
            this.x = 10.0;
        }
        this.z = (int)(Math.random() * 100.0);
    }
}
