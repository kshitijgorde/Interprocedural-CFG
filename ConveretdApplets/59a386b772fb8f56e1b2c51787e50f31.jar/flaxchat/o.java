// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.Point;
import flaxchat.i.a;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import flaxchat.e.j;
import java.awt.event.ActionEvent;
import flaxchat.e.g;
import java.awt.Component;
import flaxchat.i.d;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import flaxchat.e.e;

public class o extends e implements ActionListener, LayoutManager
{
    private TextField d;
    private TextField e;
    private Choice f;
    private flaxchat.d.e g;
    private FlaxChat h;
    private static String[] z;
    
    public o(final FlaxChat h) {
        final boolean s = m.s;
        this.d = new TextField(20);
        this.e = new TextField(20);
        this.f = new Choice();
        this.g = new flaxchat.d.e(o.z[6]);
        this.h = h;
        this.d.setEchoChar('*');
        flaxchat.i.d.b(o.z[3]);
        this.setLayout(this);
        this.add(this.e);
        this.add(this.d);
        this.add(this.f);
        this.add(this.g);
        this.a();
        flaxchat.e.g.a(this.e, this.d);
        flaxchat.e.g.a(this.d, this.f);
        flaxchat.e.g.a(this.f, this.g);
        this.g.a(this);
        this.g.a(true);
        if (flaxchat.e.e.c) {
            m.s = !s;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.a(this.e)) {
            this.e.requestFocus();
            j.b(this, o.z[5]);
            return;
        }
        this.h.setEnabled(false);
        this.h.a(this.e.getText(), this.d.getText(), this.f.getSelectedItem());
        this.e.setText("");
        this.d.setText("");
        this.h.setEnabled(true);
    }
    
    public boolean a(final TextField textField) {
        final String text = textField.getText();
        return text == null || text.trim().length() == 0;
    }
    
    private void a() {
        final boolean s = m.s;
        int n = 1;
        do {
            final String parameter = this.h.getParameter(o.z[7] + n);
            if (parameter == null) {
                break;
            }
            if (parameter.trim().length() == 0) {
                return;
            }
            this.f.add(parameter);
            ++n;
        } while (!s);
    }
    
    public void a(final Graphics graphics) {
        super.a(graphics);
        final Image b = flaxchat.i.d.b(o.z[3]);
        final Dimension size = this.getSize();
        graphics.drawImage(b, size.width / 2 - b.getWidth(this) / 2, size.height / 2 - b.getHeight(this) / 2, this);
    }
    
    public final void addLayoutComponent(final String s, final Component component) {
    }
    
    public final void removeLayoutComponent(final Component component) {
    }
    
    public final Dimension preferredLayoutSize(final Container container) {
        return container.getSize();
    }
    
    public final Dimension minimumLayoutSize(final Container container) {
        return container.getSize();
    }
    
    public final void layoutContainer(final Container container) {
        final Point b = flaxchat.i.a.b(o.z[2]);
        final Point b2 = flaxchat.i.a.b(o.z[1]);
        final Point b3 = flaxchat.i.a.b(o.z[0]);
        final Point b4 = flaxchat.i.a.b(o.z[4]);
        final Image b5 = flaxchat.i.d.b(o.z[3]);
        final Dimension size = this.getSize();
        int width = b5.getWidth(this);
        int height = b5.getHeight(this);
        if (width <= 0) {
            width = 1;
        }
        if (height <= 0) {
            height = 1;
        }
        float n = size.width / width;
        final float n2 = size.height / height;
        if (n <= 0.0f) {
            n = 1.0f;
        }
        if (n <= 0.0f) {
            n = 1.0f;
        }
        this.a(this.e, b, n, n2);
        this.a(this.d, b2, n, n2);
        this.a(this.f, b3, n, n2);
        this.b(this.g, b4, n, n2);
    }
    
    private final void a(final Component component, final Point point, final float n, final float n2) {
        final Image b = flaxchat.i.d.b(o.z[3]);
        final Dimension size = this.getSize();
        component.setBounds(size.width / 2 - b.getWidth(this) / 2 + point.x, size.height / 2 - b.getHeight(this) / 2 + point.y, 80, component.getPreferredSize().height);
    }
    
    private final void b(final Component component, final Point point, final float n, final float n2) {
        final Image b = flaxchat.i.d.b(o.z[3]);
        final Dimension size = this.getSize();
        component.setBounds(size.width / 2 - b.getWidth(this) / 2 + point.x, size.height / 2 - b.getHeight(this) / 2 + point.y, 140, component.getPreferredSize().height);
    }
    
    static {
        o.z = new String[] { z(z("*\u0005 \u0015c\f\u0001\u0011\u0014d\u0007\u0019")), z(z("9\f2\bz\u0006\u001f%+b\u0000\u00035")), z(z("'\u0004\"\u0010C\b\u0000$+b\u0000\u00035")), z(z("\u0005\u0002&\u0012cG\n(\u001d")), z(z("*\u0002/\u0015h\n\u0019\u0011\u0014d\u0007\u0019")), z(z(";\u0018,\u000ew\u001c\u00034\u0001xI\u0014 \u0001d\u0007\u0004;")), z(z("\u001a\u0002)\u0019h\u001d\ba\u0019l\u000e\u0001 \u0015")), z(z("\n\u0005 \u0015c\f\u0001")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\r';
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
                    c2 = 'i';
                    break;
                }
                case 1: {
                    c2 = 'm';
                    break;
                }
                case 2: {
                    c2 = 'A';
                    break;
                }
                case 3: {
                    c2 = '{';
                    break;
                }
                default: {
                    c2 = '\r';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
