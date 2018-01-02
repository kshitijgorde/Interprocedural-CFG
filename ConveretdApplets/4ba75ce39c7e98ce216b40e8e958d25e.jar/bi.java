import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class bi extends bh
{
    protected static final Point[] D;
    protected Image E;
    protected Point F;
    
    protected Point[] d() {
        return bi.D;
    }
    
    bi(final bn bn, final Rectangle rectangle) {
        super(bn, rectangle);
        this.E = null;
        final Rectangle z = super.z;
        z.x -= 7;
        this.E = super.d.b().n.b(52);
        final Dimension dimension = new Dimension(this.E.getWidth(null), this.E.getHeight(null));
        this.F = new Point(super.x.x + super.x.width - dimension.width - 2, super.x.y + super.x.height / 2 - dimension.height / 2);
    }
    
    protected int f() {
        return 27;
    }
    
    boolean a(final Graphics graphics) {
        try {
            graphics.setColor(new Color(120, 122, 123));
            graphics.fillRect(0, super.x.y, super.d.b().size().width, super.x.height);
            graphics.setColor(new Color(88, 100, 104));
            graphics.drawLine(0, super.x.y, super.d.b().size().width, super.x.y);
            graphics.drawImage(this.E, this.F.x, this.F.y, null);
            return super.a(graphics);
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
        return false;
    }
    
    static {
        D = new Point[] { new Point(72, 1), new Point(0, -10), new Point(-9, -1), new Point(0, 9), new Point(10, -1), new Point(0, -1), new Point(-54, 0), new Point(-84, 0) };
    }
    
    protected int h() {
        return 1;
    }
}
