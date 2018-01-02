// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

public final class ei
{
    public Graphics a;
    public int a;
    public int b;
    
    public final void a(final Color color) {
        this.a.setColor(color);
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        this.a.drawLine(n + this.a, n2 + this.b, n3 + this.a, n4 + this.b);
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        switch (n9) {
            case 0: {
                this.a(n + n3, n2 + n4, n + n5, n2 + n6);
            }
            case 1: {
                this.a(n + n8 - 1 - n4, n2 + n3, n + n8 - 1 - n6, n2 + n5);
            }
            case 2: {
                this.a(n + n7 - 1 - n3, n2 + n8 - 1 - n4, n + n7 - 1 - n5, n2 + n8 - 1 - n6);
            }
            case 3: {
                this.a(n + n4, n2 + n7 - 1 - n3, n + n6, n2 + n7 - 1 - n5);
                break;
            }
        }
    }
    
    public final void a(final Font font) {
        this.a.setFont(font);
    }
    
    public final void a(final String s, final int n, final int n2) {
        this.a.drawString(s, n + this.a, n2 + this.b);
    }
    
    public final void a(final char[] array, final int n, int n2, final int n3, final int n4, final int n5, final FontMetrics fontMetrics) {
        while (fontMetrics.charsWidth(array, n, n2) > n5 && n2 > 0) {
            --n2;
        }
        this.a.drawChars(array, n, n2, n3 + this.a, n4 + this.b);
    }
    
    public final void b(final int n, final int n2, final int n3, final int n4) {
        this.a.fillRect(n + this.a, n2 + this.b, n3, n4);
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.a.fill3DRect(n + this.a, n2 + this.b, n3, n4, b);
    }
    
    public final void c(final int n, final int n2, final int n3, final int n4) {
        this.a.drawRect(n + this.a, n2 + this.b, n3, n4);
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i < 2; ++i) {
            this.a(0, 0, n3, n4, n5, n6, n, n2, i << 1);
            this.a(0, 0, n3, n4, n6, n5, n, n2, i << 1);
        }
        for (int j = 0; j < 2; ++j) {
            this.a(0, 0, n3, n4, n5, n6, n2, n, (j << 1) + 1);
            this.a(0, 0, n3, n4, n6, n5, n2, n, (j << 1) + 1);
        }
    }
    
    public final void a(int i, final int n, final int n2, final Color color, final Color color2) {
        this.a(color);
        for (i = 0; i < 2; ++i) {
            this.a(0, 0, 2, 0, n - 3, 0, n, n2, i << 1);
            this.a(0, 0, 0, 2, 0, n2 - 3, n, n2, i << 1);
        }
        this.a(n, n2, 2, 0, 0, 2);
        this.a(n, n2, 3, 0, 0, 3);
        this.a(color2);
        this.a(n, n2, 1, 3, 3, 1);
        for (i = 0; i < 2; ++i) {
            this.a(0, 0, 3, 1, n - 4, 1, n, n2, i << 1);
            this.a(0, 0, 1, 3, 1, n2 - 4, n, n2, i << 1);
        }
    }
    
    public final void a(final Image image, final int n, final int n2, final ImageObserver imageObserver) {
        this.a.drawImage(image, n + this.a, n2 + this.b, imageObserver);
    }
    
    public final ei a(final int n, final int n2, final int n3) {
        final ei ei;
        (ei = new ei()).a = this.a.create(0 + this.a, 0 + this.b, n2, n3);
        return ei;
    }
}
