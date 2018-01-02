// 
// Decompiled by Procyon v0.5.30
// 

package ru.sakva.bsh;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;

public class Book implements ImageObserver
{
    static final int TURNED = 1;
    static final int UPWARD = 2;
    static final int SELECTED = 4;
    static final int BOX = 8;
    static final int CHINESE = 16;
    static int hmin;
    static int hmax;
    static int volume;
    public static int width;
    public static int height;
    public static int thickness;
    public static Color defaultCoverColor;
    public static Color defaultTitleColor;
    public Color coverColor;
    public Color titleColor;
    public Image icon;
    public int a;
    public int b;
    public int c;
    public int u;
    public int v;
    String title;
    int flags;
    static Font[] font;
    static FontMetrics[] fontMetrics;
    
    static void prepareFonts(final String s) {
        for (int i = 0; i < 12; ++i) {
            Book.font[i] = new Font(s, 1, 2 * i + 8);
            Book.fontMetrics[i] = Toolkit.getDefaultToolkit().getFontMetrics(Book.font[i]);
        }
    }
    
    public static void setMinimum(final int hmin) {
        Book.hmin = hmin;
    }
    
    public static void setMaximum(final int hmax) {
        Book.hmax = hmax;
    }
    
    public Book() {
        this.a = Book.width;
        this.b = Book.height;
        this.c = Book.thickness;
        this.title = "";
        this.coverColor = Book.defaultCoverColor;
        this.titleColor = Book.defaultTitleColor;
    }
    
    public Book(final String title) {
        this();
        this.setTitle(title);
    }
    
    public Book(final String s, final int volume) {
        this(s);
        this.setVolume(volume);
    }
    
    public boolean selected() {
        return (this.flags & 0x4) == 0x4;
    }
    
    public void select(final boolean b) {
        if (b) {
            this.flags |= 0x4;
            return;
        }
        this.flags &= 0xFFFFFFFB;
    }
    
    public boolean turned() {
        return (this.flags & 0x1) == 0x1;
    }
    
    public void turn(final boolean b) {
        if (b) {
            this.flags |= 0x1;
            return;
        }
        this.flags &= 0xFFFFFFFE;
    }
    
    public boolean upward() {
        return (this.flags & 0x2) == 0x2;
    }
    
    public void upward(final boolean b) {
        if (b) {
            this.flags |= 0x2;
            return;
        }
        this.flags &= 0xFFFFFFFD;
    }
    
    public boolean chinese() {
        return (this.flags & 0x10) == 0x10;
    }
    
    public void chinese(final boolean b) {
        if (b) {
            this.flags |= 0x10;
            return;
        }
        this.flags &= 0xFFFFFFEF;
    }
    
    public boolean box() {
        return (this.flags & 0x8) == 0x8;
    }
    
    public void box(final boolean b) {
        if (b) {
            this.flags |= 0x8;
            return;
        }
        this.flags &= 0xFFFFFFF7;
    }
    
    public void setVolume(final int n) {
        this.c = (int)(n / Book.volume * (Book.hmin - 2) + 2.0f);
        if (this.c < Book.hmin) {
            this.c = Book.hmin;
            return;
        }
        if (Book.hmax != 0) {
            if (this.c > Book.hmax) {
                this.c = Book.hmax;
            }
        }
        else if (this.c > this.b) {
            this.c = this.b;
        }
    }
    
    public void setTitle(final String s) {
        this.title = new String(s);
    }
    
    public void setReference(final String s) {
    }
    
    public void setIcon(final Image icon) {
        this.icon = icon;
    }
    
    public String getAuthor() {
        return "";
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void drawTitle(final Graphics graphics, final int n, final int n2, final int n3) {
        final int length = this.title.length();
        if (length > 0) {
            if (this.chinese()) {
                final char[] charArray = this.title.toCharArray();
                for (int i = 0; i < length; ++i) {
                    int n4;
                    if ((n4 = (this.b - n3) / (length + 1)) > 30) {
                        n4 = 30;
                    }
                    final int n5 = n4 / 2 - 4;
                    graphics.setFont(Book.font[n5]);
                    graphics.drawChars(charArray, i, 1, (this.c - Book.fontMetrics[n5].charWidth(charArray[i])) / 2, n4 * (i + 1) + n4 / 2);
                }
                return;
            }
            final int index = this.title.indexOf(59);
            String s;
            String string;
            if (index < 0) {
                s = " " + this.title + " ";
                string = null;
            }
            else {
                s = " " + this.title.substring(0, index);
                string = " " + this.title.substring(index + 1, length) + " ";
            }
            int c = this.c;
            String s2 = s;
            if (string != null) {
                c /= 2;
                if (Book.fontMetrics[0].stringWidth(s) < Book.fontMetrics[0].stringWidth(string)) {
                    s2 = string;
                }
            }
            if (c > 30) {
                c = 30;
            }
            int n6 = c / 2 - 4;
            int stringWidth = 0;
            int n7;
            for (n7 = this.b - n3; n6 >= 0 && (stringWidth = Book.fontMetrics[n6].stringWidth(s2)) >= n7; --n6, c -= 2) {}
            if (n6 >= 0) {
                graphics.setFont(Book.font[n6]);
                final int n8 = (n7 - stringWidth) / 2 + n;
                if (string == null) {
                    graphics.drawString(s, n8, n2 + (this.c + c) / 2 - 2);
                    return;
                }
                graphics.drawString(s, n8, n2 + this.c / 2 - 2);
                graphics.drawString(string, n8, n2 + this.c / 2 + c - 3);
            }
        }
    }
    
    void drawVertical(final Graphics graphics) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        if (!this.box()) {
            array[0] = this.u;
            array[2] = (array[1] = this.u + this.a);
            array[3] = this.u;
            array2[0] = this.v;
            array2[1] = this.v - this.a;
            array2[2] = array2[1] + this.b;
            array2[3] = this.v + this.b;
        }
        else {
            array[0] = this.u;
            array[1] = this.u + this.c;
            array[2] = this.u + this.a + this.c;
            array[3] = this.u + this.a;
            array2[0] = (array2[1] = this.v);
            array2[2] = (array2[3] = this.v - this.a);
        }
        graphics.setColor(this.coverColor);
        graphics.fillPolygon(array, array2, 4);
        graphics.setColor(Color.black);
        graphics.drawPolygon(array, array2, 4);
        if (!this.box()) {
            for (int i = this.u + 3; i < this.u + this.c - 2; ++i) {
                graphics.setColor(Color.white);
                graphics.drawLine(i, this.v, i + this.a - 3, this.v - this.a + 3);
                ++i;
                graphics.setColor(Color.black);
                graphics.drawLine(i, this.v, i + this.a - 3, this.v - this.a + 3);
            }
        }
        if (this.icon != null) {
            graphics.drawImage(this.icon, this.u, this.v, this.c, this.b, this);
        }
        else {
            graphics.setColor(this.coverColor);
            graphics.fillRect(this.u, this.v, this.c, this.b);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(this.u, this.v, this.c, this.b);
        array[0] = this.u + this.c;
        array[2] = (array[1] = this.u + this.c + this.a);
        array[3] = array[0];
        array2[0] = this.v;
        array2[1] = this.v - this.a;
        array2[2] = array2[1] + this.b;
        array2[3] = this.v + this.b;
        graphics.setColor(this.coverColor);
        graphics.fillPolygon(array, array2, 4);
        graphics.setColor(Color.black);
        graphics.drawPolygon(array, array2, 4);
    }
    
    void drawHorisontal(final Graphics graphics) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        if (!this.box()) {
            array[0] = this.u;
            array[1] = this.u + this.b;
            array[2] = array[1] + this.a;
            array[3] = this.u + this.a;
            array2[0] = (array2[1] = this.v + this.c);
            array2[2] = (array2[3] = array2[1] - this.a);
        }
        else {
            array[0] = (array[1] = this.u + this.b);
            array[2] = (array[3] = this.u + this.b + this.a);
            array2[0] = this.v + this.c;
            array2[1] = this.v;
            array2[2] = this.v - this.a;
            array2[3] = this.v + this.c - this.a;
        }
        graphics.setColor(this.coverColor);
        graphics.fillPolygon(array, array2, 4);
        graphics.setColor(Color.black);
        graphics.drawPolygon(array, array2, 4);
        if (!this.box()) {
            for (int i = this.v; i < this.v + this.c - 2; ++i) {
                graphics.setColor(Color.white);
                graphics.drawLine(this.u + this.b, i, this.u + this.b + this.a - 3, i - this.a + 3);
                ++i;
                graphics.setColor(Color.black);
                graphics.drawLine(this.u + this.b, i, this.u + this.b + this.a - 3, i - this.a + 3);
            }
        }
        array[0] = this.u;
        array[1] = this.u + this.b;
        array[2] = array[1] + this.a;
        array[3] = this.u + this.a;
        array2[0] = (array2[1] = this.v);
        array2[2] = (array2[3] = this.v - this.a);
        graphics.setColor(this.coverColor);
        graphics.fillPolygon(array, array2, 4);
        graphics.setColor(this.coverColor);
        graphics.fillRect(this.u, this.v, this.b, this.c);
        int n = 0;
        if (this.icon != null) {
            n = this.icon.getWidth(this) / this.icon.getHeight(this) * this.c;
            graphics.setColor(Color.black);
            graphics.drawImage(this.icon, this.upward() ? (this.u + this.b - n) : this.u, this.v, n, this.c, this);
            graphics.setColor(this.coverColor);
            graphics.drawPolygon(array, array2, 4);
            graphics.setColor(Color.black);
            graphics.drawRect(this.u, this.v, this.b, this.c);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(this.u, this.v, this.b, this.c);
        graphics.drawPolygon(array, array2, 4);
        if (!this.chinese()) {
            graphics.setColor(this.titleColor);
            this.drawTitle(graphics, this.upward() ? this.u : (this.u + n), this.v, n);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) == 0x20) {
            synchronized (this) {
                this.notify();
            }
            return false;
        }
        if ((n & 0xC0) != 0x0) {
            synchronized (this) {
                this.notify();
            }
            return false;
        }
        return true;
    }
    
    static {
        Book.hmin = 12;
        Book.volume = 16384;
        Book.width = 25;
        Book.height = 100;
        Book.thickness = 16;
        Book.defaultCoverColor = new Color(128);
        Book.defaultTitleColor = new Color(16760896);
        Book.font = new Font[12];
        Book.fontMetrics = new FontMetrics[12];
        prepareFonts("serif");
    }
}
