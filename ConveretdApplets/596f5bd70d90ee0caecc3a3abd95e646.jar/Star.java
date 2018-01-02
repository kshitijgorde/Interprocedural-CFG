import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Star
{
    int H;
    int V;
    int x;
    int y;
    int z;
    int type;
    
    Star(final int n, final int n2, final int n3, final int type) {
        this.type = type;
        this.H = n / 2;
        this.V = n2 / 2;
        this.x = (int)(Math.random() * n) - this.H;
        this.y = (int)(Math.random() * n2) - this.V;
        if (this.x == 0 && this.y == 0) {
            this.x = 10;
        }
        this.z = (int)(Math.random() * n3);
    }
    
    public void Draw(final Graphics graphics, final double n) {
        this.z -= 2;
        if (this.z < -63) {
            this.z = 100;
        }
        final int n2 = this.x * 64 / (64 + this.z);
        final int n3 = this.y * 64 / (64 + this.z);
        final double n4 = n2 * Math.cos(n) - n3 * Math.sin(n);
        final double n5 = n2 * Math.sin(n) + n3 * Math.cos(n);
        final int n6 = (int)n4 + this.H;
        final int n7 = (int)n5 + this.V;
        if (n6 < 0 || n6 > 2 * this.H) {
            this.z = 100;
        }
        if (n7 < 0 || n7 > 2 * this.H) {
            this.z = 100;
        }
        this.GrayMe(graphics);
        if (this.type == 0) {
            int n8 = (100 - this.z) / 50;
            if (n8 == 0) {
                n8 = 1;
            }
            graphics.fillRect(n6, n7, n8, n8);
            return;
        }
        final int n9 = (100 - this.z) / 20;
        graphics.drawLine(n6 - n9, n7, n6 + n9, n7);
        graphics.drawLine(n6, n7 - n9, n6, n7 + n9);
        if (this.z < 50) {
            final int n10 = n9 / 2;
            graphics.drawLine(n6 - n10, n7 - n10, n6 + n10, n7 + n10);
            graphics.drawLine(n6 + n10, n7 - n10, n6 - n10, n7 + n10);
        }
    }
    
    public void GrayMe(final Graphics graphics) {
        if (this.z > 50) {
            graphics.setColor(Color.gray);
            return;
        }
        if (this.z > 25) {
            graphics.setColor(Color.lightGray);
            return;
        }
        graphics.setColor(Color.white);
    }
}
