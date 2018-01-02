// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import flaxchat.e.e;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Component;

class l
{
    private final h a;
    Component b;
    String c;
    Image d;
    Dimension e;
    Rectangle f;
    int g;
    Component h;
    int i;
    int j;
    
    public l(final h h, final Component h2, final Component b, final String c, final Image d) {
        this.a = h;
        this.a = h;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = new Dimension();
        this.f = new Rectangle();
        this.g = 0;
        this.i = 5;
        this.j = 8;
        this.h = h2;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public void a(final FontMetrics fontMetrics) {
        this.e.width = fontMetrics.stringWidth(this.c);
        this.e.height = fontMetrics.getHeight();
        if (this.d != null) {
            final Dimension e = this.e;
            e.width += this.d.getWidth(this.h) + this.j;
            this.e.height = Math.max(this.d.getHeight(this.h), this.e.height);
        }
        final Dimension e2 = this.e;
        e2.width += 2 * this.j;
        final Dimension e3 = this.e;
        e3.height += 2 * this.i;
        this.f.width = this.e.width;
        this.f.height = this.e.height;
    }
    
    public void a(final Graphics graphics) {
        final boolean g = flaxchat.d.i.g;
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        graphics.setColor(flaxchat.d.h.e);
        array[0] = this.f.x + 6;
        array2[0] = this.f.y + 2;
        array[1] = this.f.x + this.f.width;
        array2[1] = this.f.y + 2;
        array[2] = this.f.x + this.f.width;
        array2[2] = this.f.y + this.f.height - 2;
        array[3] = this.f.x + 2;
        array2[3] = this.f.y + this.f.height - 2;
        array[4] = this.f.x + 2;
        array2[4] = this.f.y + 6;
        graphics.fillPolygon(array, array2, 5);
        graphics.setColor(flaxchat.d.h.h);
        graphics.drawLine(this.f.x, this.f.y + this.f.height - 2, this.f.x, this.f.y + 6);
        graphics.drawLine(this.f.x, this.f.y + 6, this.f.x + 6, this.f.y);
        graphics.drawLine(this.f.x + 6, this.f.y, this.f.x + this.f.width - 1, this.f.y);
        graphics.drawLine(this.f.x + this.f.width, this.f.y + 1, this.f.x + this.f.width, this.f.y + this.f.height - 2);
        graphics.setColor(flaxchat.d.h.g);
        graphics.drawLine(this.f.x + 1, this.f.y + this.f.height - 3, this.f.x + 1, this.f.y + 6);
        graphics.drawLine(this.f.x + 1, this.f.y + 6, this.f.x + 6, this.f.y + 1);
        graphics.drawLine(this.f.x + 6, this.f.y + 1, this.f.x + this.f.width - 1, this.f.y + 1);
        if (flaxchat.e.e.c) {
            flaxchat.d.i.g = !g;
        }
    }
    
    public void b(final Graphics graphics) {
        final boolean g = flaxchat.d.i.g;
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        graphics.setColor(flaxchat.d.h.e);
        array[0] = this.f.x + 2;
        array2[0] = this.f.y + 6;
        array[1] = this.f.x + 2;
        array2[1] = this.f.y + this.f.height;
        array[2] = this.f.x + this.f.width - 2;
        array2[2] = this.f.y + this.f.height;
        array[3] = this.f.x + this.f.width - 2;
        array2[3] = this.f.y + 2;
        array[4] = this.f.x + 6;
        array2[4] = this.f.y + 2;
        graphics.fillPolygon(array, array2, 5);
        graphics.setColor(flaxchat.d.h.h);
        graphics.drawLine(this.f.x + this.f.width - 2, this.f.y, this.f.x + 6, this.f.y);
        graphics.drawLine(this.f.x + 6, this.f.y, this.f.x, this.f.y + 6);
        graphics.drawLine(this.f.x, this.f.y + 6, this.f.x, this.f.y + this.f.height - 1);
        graphics.drawLine(this.f.x + 1, this.f.y + this.f.height, this.f.x + this.f.width - 2, this.f.y + this.f.height);
        graphics.setColor(flaxchat.d.h.g);
        graphics.drawLine(this.f.x + this.f.width - 3, this.f.y + 1, this.f.x + 6, this.f.y + 1);
        graphics.drawLine(this.f.x + 6, this.f.y + 1, this.f.x + 1, this.f.y + 6);
        graphics.drawLine(this.f.x + 1, this.f.y + 6, this.f.x + 1, this.f.y + this.f.height - 1);
        if (g) {
            flaxchat.e.e.c = !flaxchat.e.e.c;
        }
    }
    
    public void c(final Graphics graphics) {
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        graphics.setColor(flaxchat.d.h.a);
        array[0] = this.f.x + 6;
        array2[0] = this.f.y + 2;
        array[1] = this.f.x + this.f.width;
        array2[1] = this.f.y + 2;
        array[2] = this.f.x + this.f.width;
        array2[2] = this.f.y + this.f.height;
        array[3] = this.f.x + 2;
        array2[3] = this.f.y + this.f.height;
        array[4] = this.f.x + 2;
        array2[4] = this.f.y + 6;
        graphics.fillPolygon(array, array2, 5);
        graphics.setColor(flaxchat.d.h.d);
        graphics.drawLine(this.f.x, this.f.y + this.f.height - 2, this.f.x, this.f.y + 6);
        graphics.drawLine(this.f.x, this.f.y + 6, this.f.x + 6, this.f.y);
        graphics.drawLine(this.f.x + 6, this.f.y, this.f.x + this.f.width - 1, this.f.y);
        graphics.drawLine(this.f.x + this.f.width, this.f.y + 1, this.f.x + this.f.width, this.f.y + this.f.height - 2);
        graphics.setColor(flaxchat.d.h.c);
        graphics.drawLine(this.f.x + 1, this.f.y + this.f.height - 2, this.f.x + 1, this.f.y + 6);
        graphics.drawLine(this.f.x + 1, this.f.y + 6, this.f.x + 6, this.f.y + 1);
        graphics.drawLine(this.f.x + 6, this.f.y + 1, this.f.x + this.f.width - 1, this.f.y + 1);
    }
    
    public void d(final Graphics graphics) {
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        graphics.setColor(flaxchat.d.h.a);
        array[0] = this.f.x + 2;
        array2[0] = this.f.y + 6;
        array[1] = this.f.x + 2;
        array2[1] = this.f.y + this.f.height;
        array[2] = this.f.x + this.f.width;
        array2[2] = this.f.y + this.f.height;
        array[3] = this.f.x + this.f.width;
        array2[3] = this.f.y + 2;
        array[4] = this.f.x + 6;
        array2[4] = this.f.y + 2;
        graphics.fillPolygon(array, array2, 5);
        graphics.setColor(flaxchat.d.h.d);
        graphics.drawLine(this.f.x + this.f.width - 2, this.f.y, this.f.x + 6, this.f.y);
        graphics.drawLine(this.f.x + 6, this.f.y, this.f.x, this.f.y + 6);
        graphics.drawLine(this.f.x, this.f.y + 6, this.f.x, this.f.y + this.f.height - 1);
        graphics.drawLine(this.f.x + 1, this.f.y + this.f.height, this.f.x + this.f.width - 2, this.f.y + this.f.height);
        graphics.setColor(flaxchat.d.h.c);
        graphics.drawLine(this.f.x + this.f.width - 2, this.f.y + 1, this.f.x + 6, this.f.y + 1);
        graphics.drawLine(this.f.x + 6, this.f.y + 1, this.f.x + 1, this.f.y + 6);
        graphics.drawLine(this.f.x + 1, this.f.y + 6, this.f.x + 1, this.f.y + this.f.height - 1);
    }
}
