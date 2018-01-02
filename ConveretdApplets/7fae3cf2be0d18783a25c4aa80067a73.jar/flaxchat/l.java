// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import flaxchat.d.d;
import java.awt.Image;
import flaxchat.i.f;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import flaxchat.i.e;

class l extends e implements ActionListener, LayoutManager
{
    private final o n;
    private f o;
    private static String[] z;
    
    public l(final o o, final f o2) {
        super(l.z[1], null, null);
        this.n = o;
        this.n = o;
        super.b = flaxchat.d.d.f(l.z[0]);
        this.o = o2;
        this.a(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        flaxchat.o.a(this.n).e(this.o.h());
    }
    
    protected void a(final Graphics graphics, final Dimension dimension, final Image image, final Color color) {
    }
    
    public void a(final Graphics graphics, final int n) {
        if (this.o == null) {
            return;
        }
        if (!this.k()) {
            if (this.o.j() || this.j()) {
                super.b = flaxchat.d.d.f(l.z[0]);
                if (super.b == null) {
                    return;
                }
                final Dimension size = this.getSize();
                graphics.drawImage(super.b, 0, this.a(size.height), size.width, size.height, this);
            }
            return;
        }
        super.b = flaxchat.d.d.f(l.z[0]);
        if (super.b == null) {
            return;
        }
        final Dimension size2 = this.getSize();
        graphics.drawImage(super.b, 0, this.a(size2.height), size2.width, size2.height, this);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
        this.o.b(true);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        super.mouseExited(mouseEvent);
        this.o.b(false);
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return null;
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return null;
    }
    
    public void layoutContainer(final Container container) {
        final Dimension size = container.getSize();
        final int n = 15;
        this.setBounds(size.width - n - 2, (size.height - n) / 2, n, n);
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    static {
        l.z = new String[] { z(z("0Zxr)\u0015Dvl)")), z(z(".Qc")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'L';
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
                    c2 = 'V';
                    break;
                }
                case 1: {
                    c2 = '(';
                    break;
                }
                case 2: {
                    c2 = '\u0019';
                    break;
                }
                case 3: {
                    c2 = '\u001f';
                    break;
                }
                default: {
                    c2 = 'L';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
