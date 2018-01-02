import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class abljemtc extends Canvas
{
    Color a;
    Image b;
    int c;
    int d;
    int e;
    
    abljemtc() {
        this.hide();
    }
    
    void a(final Image b) {
        this.b = b;
        this.c = 0;
        this.d = 0;
    }
    
    public void b(final Image b) {
        this.b = b;
        this.c = this.b.getWidth(this);
        this.d = this.b.getHeight(this);
    }
    
    public boolean c(final Image image) {
        this.b(image);
        if (this.c == 0) {
            return false;
        }
        this.resize(this.c, this.d);
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.e > 0) {
            graphics.setColor(Color.black);
            graphics.drawLine(0, 0, this.e, 0);
            graphics.setColor(Color.white);
            graphics.drawLine(0, 2, this.e, 2);
        }
        if (this.b == null) {
            return;
        }
        if (this.c > 0) {
            if (this.a == null) {
                graphics.drawImage(this.b, (this.e > 0) ? 5 : 0, (this.e > 0) ? 4 : 0, this.c, this.d, this);
            }
            else {
                graphics.drawImage(this.b, (this.e > 0) ? 5 : 0, (this.e > 0) ? 4 : 0, this.c, this.d, this.a, this);
            }
        }
    }
    
    public int a() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getWidth(this);
    }
    
    public int b() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getHeight(this);
    }
}
