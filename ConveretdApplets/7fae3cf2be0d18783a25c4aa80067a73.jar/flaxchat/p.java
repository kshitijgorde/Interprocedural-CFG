// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.Point;
import flaxchat.d.a;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import flaxchat.a.k;
import java.awt.event.ActionEvent;
import flaxchat.a.h;
import java.awt.Component;
import flaxchat.d.d;
import java.awt.Color;
import flaxchat.d.b;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import flaxchat.a.e;

public class p extends e implements ActionListener, LayoutManager
{
    private TextField d;
    private TextField e;
    private Choice f;
    private flaxchat.i.e g;
    private FlaxChat h;
    private static String[] z;
    
    public p(final FlaxChat h) {
        int w = n.w;
        this.d = new TextField(30);
        this.e = new TextField(30);
        this.f = new Choice();
        this.g = new flaxchat.i.e(p.z[7]);
        this.setFont(flaxchat.d.b.d(p.z[8]));
        this.setBackground(Color.white);
        this.h = h;
        this.d.setEchoChar('*');
        flaxchat.d.d.b(p.z[0]);
        this.setLayout(this);
        this.add(this.e);
        this.add(this.d);
        this.add(this.f);
        this.add(this.g);
        this.a();
        flaxchat.a.h.a(this.e, this.d);
        flaxchat.a.h.a(this.d, this.f);
        flaxchat.a.h.a(this.f, this.g);
        this.g.a(this);
        this.g.setFocusable(true);
        if (flaxchat.a.e.c != 0) {
            n.w = ++w;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.a(this.e)) {
            this.e.requestFocus();
            k.b(this, p.z[6]);
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
        final int i = n.w;
        int n = 1;
        do {
            final String parameter = this.h.getParameter(p.z[5] + n);
            if (parameter == null) {
                break;
            }
            if (parameter.trim().length() == 0) {
                return;
            }
            this.f.add(parameter);
            ++n;
        } while (i == 0);
    }
    
    public void a(final Graphics graphics) {
        super.a(graphics);
        final Image b = flaxchat.d.d.b(p.z[0]);
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
        final Point b = flaxchat.d.a.b(p.z[3]);
        final Point b2 = flaxchat.d.a.b(p.z[2]);
        final Point b3 = flaxchat.d.a.b(p.z[4]);
        final Point b4 = flaxchat.d.a.b(p.z[1]);
        final Image b5 = flaxchat.d.d.b(p.z[0]);
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
        final Image b = flaxchat.d.d.b(p.z[0]);
        final Dimension size = this.getSize();
        component.setBounds(size.width / 2 - b.getWidth(this) / 2 + point.x, size.height / 2 - b.getHeight(this) / 2 + point.y, 170, 22);
    }
    
    private final void b(final Component component, final Point point, final float n, final float n2) {
        final Image b = flaxchat.d.d.b(p.z[0]);
        final Dimension size = this.getSize();
        component.setBounds(size.width / 2 + 22 - b.getWidth(this) / 2 + point.x, size.height / 2 + 10 - b.getHeight(this) / 2 + point.y, 140, component.getPreferredSize().height);
    }
    
    static {
        p.z = new String[] { z(z("\u000f\u000eKX\u000bM\u0006EW")), z(z(" \u000eB_\u0000\u0000\u0015|^\f\r\u0015")), z(z("3\u0000_B\u0012\f\u0013Ha\n\n\u000fX")), z(z("-\bOZ+\u0002\fIa\n\n\u000fX")), z(z(" \tM_\u000b\u0006\r|^\f\r\u0015")), z(z("\u0000\tM_\u000b\u0006\r")), z(z("1\u0014AD\u001f\u0016\u000fYK\u0010C\u0018MK\f\r\bV")), z(z("\u0010\u000eDS\u0000\u0017\u0004\fS\u0004\u0010\rM")), z(z("\u0002\u0011\\w\n\r\u0015")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'e';
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
                    c2 = 'c';
                    break;
                }
                case 1: {
                    c2 = 'a';
                    break;
                }
                case 2: {
                    c2 = ',';
                    break;
                }
                case 3: {
                    c2 = '1';
                    break;
                }
                default: {
                    c2 = 'e';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
