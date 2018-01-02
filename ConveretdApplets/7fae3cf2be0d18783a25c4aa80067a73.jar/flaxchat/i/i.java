// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import flaxchat.d.d;
import java.awt.Component;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.ScrollPane;
import java.awt.Dimension;
import flaxchat.a.c;
import java.awt.Container;
import flaxchat.a.h;
import flaxchat.d.b;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Image;
import java.util.Hashtable;
import flaxchat.a.e;

public class i extends e
{
    private String d;
    private boolean e;
    private Hashtable f;
    private Image g;
    private Insets h;
    private boolean i;
    public static boolean j;
    private static String z;
    
    public i() {
        this(true);
    }
    
    public i(final boolean e) {
        this.f = new Hashtable();
        this.i = true;
        this.e = e;
    }
    
    public void a(final Graphics graphics) {
        this.c(graphics);
        this.b(graphics);
    }
    
    private void b(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.d == null) {
            return;
        }
        graphics.setFont(flaxchat.d.b.d(flaxchat.i.i.z));
        flaxchat.a.c.a(graphics, this.d, flaxchat.a.h.a(this) + 10, 0, size.height);
    }
    
    private void c(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (!this.e) {
            graphics.clearRect(0, 0, size.width, size.height);
            return;
        }
        final Image d = this.d();
        if (d == null) {
            return;
        }
        if (this.getParent() instanceof ScrollPane) {
            final ScrollPane scrollPane = (ScrollPane)this.getParent();
            final Dimension viewportSize = scrollPane.getViewportSize();
            final Point scrollPosition = scrollPane.getScrollPosition();
            graphics.drawImage(d, scrollPosition.x, scrollPosition.y, viewportSize.width, viewportSize.height, this);
            return;
        }
        if (this.a()) {
            graphics.drawImage(d, 0, 0, size.width, size.height + 2, this);
            return;
        }
        graphics.drawImage(d, 0, 0, this);
    }
    
    public boolean a() {
        return this.i;
    }
    
    public void b() {
        this.invalidate();
        this.validate();
    }
    
    protected void c() {
        final Container parent = this.getParent();
        parent.remove(this);
        parent.invalidate();
        parent.validate();
    }
    
    protected Image d() {
        if (this.g != null) {
            return this.g;
        }
        return flaxchat.d.d.o();
    }
    
    public Object a(final Object o) {
        return this.f.get(o);
    }
    
    public void a(final String d) {
        this.d = d;
        this.repaint();
    }
    
    public Insets getInsets() {
        if (this.h == null) {
            return super.getInsets();
        }
        return this.h;
    }
    
    public boolean isFocusTraversable() {
        return false;
    }
    
    static {
        i.z = z(z("ZOKS\u0000ORbP\fZ"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'b';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '.';
                    break;
                }
                case 1: {
                    c2 = ' ';
                    break;
                }
                case 2: {
                    c2 = '$';
                    break;
                }
                case 3: {
                    c2 = '?';
                    break;
                }
                default: {
                    c2 = 'b';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
