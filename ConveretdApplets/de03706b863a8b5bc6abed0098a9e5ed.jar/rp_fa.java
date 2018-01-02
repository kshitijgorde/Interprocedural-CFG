import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fa
{
    Rectangle a;
    String a;
    public int a;
    private Image a;
    
    public rp_fa(final Point point, final String a, int height, final Image a2) {
        this.a = new Rectangle();
        this.a = a;
        this.a = height;
        this.a = a2;
        int width = 14;
        height = 14;
        if (a2 != null) {
            width = a2.getWidth(null);
            height = a2.getHeight(null);
        }
        this.a.x = point.x - width / 2;
        this.a.y = point.y - height / 2;
        this.a.width = width;
        this.a.height = height;
    }
    
    public final void a(final Graphics graphics) {
        if (this.a != null) {
            graphics.drawImage(this.a, this.a.x, this.a.y, null);
            return;
        }
        ((Graphics2D)graphics).setColor(Color.RED);
        graphics.drawRect(this.a.x, this.a.y, this.a.width, this.a.height);
    }
    
    public final boolean a(final Point point) {
        return this.a.contains(point);
    }
    
    public final int a() {
        if (this.a != null) {
            return this.a.getWidth(null);
        }
        return 14;
    }
    
    public final int b() {
        if (this.a != null) {
            return this.a.getHeight(null);
        }
        return 14;
    }
}
