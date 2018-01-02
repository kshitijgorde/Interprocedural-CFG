// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.Stack;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.Component;

public class a
{
    private int a;
    private int b;
    private int[] c;
    private Component d;
    
    public a(final Component d, final ImageProducer imageProducer, final int n, final int n2, final int a, final int b) throws Exception {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = new int[a * b];
        final d d2 = new d(d, imageProducer, n, n2, a, b, this.c, 0, a);
        d2.grabPixels();
        if ((d2.getStatus() & 0x80) != 0x0) {
            throw new Exception("MemoryImage: error grabbing pixels");
        }
    }
    
    public Image a() {
        return this.d.createImage(new MemoryImageSource(this.a, this.b, this.c, 0, this.a));
    }
    
    public int b() {
        return this.a;
    }
    
    public int c() {
        return this.b;
    }
    
    private boolean b(final int n, final int n2) {
        return n >= 0 && n < this.a && n2 >= 0 && n2 < this.b;
    }
    
    public int a(final int n, final int n2) {
        return this.c[n2 * this.a + n];
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.c[n2 * this.a + n] = n3;
    }
    
    private void a(final int n, final int n2, final int n3, final int n4) {
        if (n3 == n4) {
            return;
        }
        final Stack stack = new Stack<Point>();
        stack.push(new Point(n, n2));
        while (!stack.empty()) {
            final Point point = stack.pop();
            int x = point.x;
            final int y = point.y;
            if (!this.b(x, y)) {
                continue;
            }
            this.a(x, y, n4);
            final int n5 = x;
            ++x;
            while (this.b(x, y) && this.a(x, y) == n3) {
                this.a(x, y, n4);
                ++x;
            }
            final int n6 = x - 1;
            int n7 = n5;
            --n7;
            while (this.b(n7, y) && this.a(n7, y) == n3) {
                this.a(n7, y, n4);
                --n7;
            }
            final int n8 = n7 + 1;
            final int[] array = { y - 1, y + 1 };
            for (int i = 0; i < 2; ++i) {
                final int n9 = array[i];
                int j = n8;
                while (j <= n6) {
                    while (this.b(j, n9) && this.a(j, n9) != n3 && j <= n6) {
                        ++j;
                    }
                    if (j <= n6) {
                        stack.push(new Point(j, n9));
                        do {
                            ++j;
                            if (this.b(j, n9) && this.a(j, n9) == n3) {
                                continue;
                            }
                            break;
                        } while (j <= n6);
                    }
                }
            }
        }
    }
    
    public void a(final int n, final int n2, final Color color) {
        if (this.b(n, n2)) {
            this.a(n, n2, this.a(n, n2), color.getRGB());
        }
    }
}
