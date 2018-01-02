import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Fly_by_Star
{
    int Xmax;
    int Ymax;
    int z;
    double x;
    double y;
    
    Fly_by_Star(final int n, final int n2) {
        this.Xmax = n / 2;
        this.Ymax = n2 / 2;
        this.x = Math.random() * n - this.Xmax;
        this.y = Math.random() * n2 - this.Ymax;
        if (this.x == 0.0 && this.y == 0.0) {
            this.x = 10.0;
        }
        this.z = (int)(Math.random() * 100.0);
    }
    
    public void Draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        this.z -= 2;
        this.x -= (n * n5 / 25.0 - n2 * n6 / 25.0) * (this.z + 62) / 162.0;
        this.y += (n * n6 / 25.0 - n2 * n5 / 25.0) * (this.z + 62) / 162.0;
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
        final double n7 = this.x * 128.0 / (64 + this.z);
        final double n8 = this.y * 128.0 / (64 + this.z);
        if (n7 < -this.Xmax || n7 > this.Xmax) {
            this.z = 100;
        }
        if (n8 < -this.Ymax || n8 > this.Xmax) {
            this.z = 100;
        }
        final int n9 = (int)(n7 * n5 - n8 * n6) + this.Xmax;
        final int n10 = (int)(n7 * n6 + n8 * n5) + this.Ymax;
        if (this.z > 50) {
            graphics.setColor(Color.gray);
        }
        else if (this.z > 25) {
            graphics.setColor(Color.lightGray);
        }
        else {
            graphics.setColor(Color.white);
        }
        int n11 = (100 - this.z) / 40;
        if (n11 == 0) {
            n11 = 1;
        }
        if (n11 == 1) {
            graphics.setColor(Color.white);
        }
        graphics.fillRect(n9, n10, n11, n11);
    }
}
