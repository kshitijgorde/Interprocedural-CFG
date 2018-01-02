import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class WobbleMenu extends KLMenu
{
    int a133;
    int a134;
    int a135;
    int a136;
    Color[] a137;
    
    public void init() {
        this.a133 = this.a72("xAmount", 1);
        this.a135 = this.a72("yAmount", 1);
        this.a134 = this.a72("xFreq", 10);
        this.a136 = this.a72("yFreq", 10);
        super.init("WobbleMenu http://www.javabase.fsnet.co.uk/");
        this.a137 = this.a69(super.a43, super.a41[0], super.a31);
    }
    
    protected void a82() {
        if (super.a53 != null) {
            super.a39.drawImage(super.a53, 0, 0, this);
        }
        else {
            super.a39.setColor(super.a43);
            super.a39.fillRect(0, 0, super.width, super.height);
        }
        for (int i = 0; i < super.a55.length; ++i) {
            this.a82(i);
        }
        if (super.a34 != 0) {
            super.a39.setColor(super.a43);
            for (int j = 0; j < super.height; ++j) {
                final int n = (int)(this.a133 * (super.a31 - super.a35 - 1) * Math.sin((super.a35 * this.a134 + j) / 10.0));
                super.a39.copyArea(0, j, super.width, 1, n, 0);
                super.a39.fillRect((n > 0) ? 0 : (super.width + n), j, (n > 0) ? n : (-n), 1);
            }
            for (int k = 0; k < super.width; ++k) {
                final int n2 = (int)(this.a135 * (super.a31 - super.a35 - 1) * Math.sin((super.a35 * this.a134 + k) / 10.0));
                super.a39.copyArea(k, 0, 1, super.height, 0, n2);
                super.a39.fillRect(k, (n2 > 0) ? 0 : (super.height + n2), 1, (n2 > 0) ? n2 : (-n2));
            }
            this.repaint();
        }
    }
    
    protected void a82(final int n) {
        final Rectangle rectangle = new Rectangle(super.a47[n].x, super.a47[n].y, super.a47[n].width, super.a47[n].height);
        final Graphics create = super.a39.create();
        create.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (super.a34 == 2) {
            this.a90(super.a49, rectangle, super.a35, super.a31, n);
        }
        else if (super.a34 == 1) {
            this.a90(super.a50, rectangle, super.a35, super.a31, n);
        }
        final int n2 = rectangle.y + (rectangle.height - create.getFontMetrics().getHeight()) / 2 + create.getFontMetrics().getAscent();
        int x = rectangle.x + (rectangle.width - create.getFontMetrics().stringWidth(this.a102(n, 0))) / 2;
        if (super.a52.equals("left")) {
            x = rectangle.x;
        }
        else if (super.a52.equals("right")) {
            x = rectangle.x + rectangle.width - create.getFontMetrics().stringWidth(this.a102(n, 0));
        }
        if (super.a34 == 0) {
            if (super.a53 == null) {
                create.clearRect(super.a47[n].x, super.a47[n].y, super.a47[n].width, super.a47[n].height);
            }
            else {
                create.create(super.a47[n].x, super.a47[n].y, super.a47[n].width, super.a47[n].height).drawImage(super.a53, -super.a47[n].x, -super.a47[n].y, this);
            }
        }
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (super.a48 != null) {
            this.a90(super.a48, rectangle2, super.a32[n], super.a30, n);
        }
        if (super.a34 == 0 && this.a102(n, 0).length() > 0 && this.a102(n, 1).length() > 0) {
            this.a14(rectangle2.intersection(rectangle), n);
        }
        final Graphics create2 = create.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (super.a34 == 0) {
            create2.setColor((this.a102(n, 1).length() > 0) ? super.a41[super.a32[n]] : super.a41[0]);
        }
        else {
            create2.setColor(this.a137[super.a35]);
        }
        create2.drawString(this.a102(n, 0), x - rectangle.x - (super.a46[n] ? 2 : 0), n2 - rectangle.y + (super.a46[n] ? 2 : 0));
        if (super.a34 == 0) {
            this.repaint(super.a47[n].x, super.a47[n].y, super.a47[n].width, super.a47[n].height);
        }
    }
}
