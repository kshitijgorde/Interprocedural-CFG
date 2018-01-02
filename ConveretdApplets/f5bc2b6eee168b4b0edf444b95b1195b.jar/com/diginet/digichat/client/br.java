// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.j;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import com.diginet.digichat.awt.bm;
import com.diginet.digichat.awt.r;
import java.awt.Insets;
import com.diginet.digichat.util.q;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.c3;
import java.awt.Font;
import java.awt.Image;
import com.diginet.digichat.awt.ba;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.a6;
import java.awt.Component;
import com.diginet.digichat.util.p;

public class br extends bs implements p
{
    boolean a;
    private Component b;
    private Component c;
    
    public final String a(final Object o) {
        if (o == super.c) {
            final a6 a6 = (a6)super.e.g();
            if (this.c(super.c)) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to enter %1: %2."), new String[] { a6.getName(), a6.a });
            }
            if (a6 == null) {
                return StringSubst.Substitute(LanguageSupport.translate("This button is disabled because no %1 is selected."), new String[] { this.a ? "channel" : "room" });
            }
            return StringSubst.Substitute(LanguageSupport.translate("This button is disabled because you are already in %1."), new String[] { a6.getName() });
        }
        else {
            if (o == super.d) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to create a new %1."), new String[] { this.a ? "channel" : "room" });
            }
            return null;
        }
    }
    
    public final void a(final int g) {
        if (g != -999) {
            this.a();
            super.g = g;
            for (int i = 0; i < super.f.al.b(); ++i) {
                final bf bf = (bf)super.f.al.c(i);
                if (bf.e == g) {
                    this.b(bf);
                }
            }
        }
    }
    
    private final Component a(final String s, final String s2, final String s3) {
        p a;
        if (!super.f.df.getImageButtons() || s == null) {
            a = new o(s3, 28, 28);
        }
        else {
            final Image a2 = super.f.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.f.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.f.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new o(s3, 28, 28);
            }
            else {
                this.prepareImage(a2, this);
                this.prepareImage(a3, this);
                this.prepareImage(a4, this);
                a = ba.a(a2, a3, a4);
            }
        }
        return (Component)a;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint(n2, n3, n4, n5);
            return false;
        }
        if ((n & 0x40) != 0x0) {
            return false;
        }
        this.repaint(n2, n3, n4, n5);
        return true;
    }
    
    public br(final h h, final Font font) {
        this(h, false, font);
    }
    
    public br(final h f, final boolean a, Font font) {
        this.a = false;
        super.f = f;
        this.a = a;
        super.e.resize(141, 140);
        if (!c3.e) {
            font = new Font(font.getName(), font.getStyle(), font.getSize() - 2);
        }
        final String fullDirectory = f.df.getFullDirectory();
        super.c = this.a(fullDirectory, "clear", "Enter");
        final GridBagLayout layout = new GridBagLayout();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        this.setLayout(layout);
        final Panel panel = new Panel();
        panel.setLayout(layout2);
        if (f.u(29) || f.u(38) || f.u(27) || f.u(28)) {
            super.d = this.a(fullDirectory, "clear", "Create");
            if (super.d instanceof ba) {
                (this.c = q.a("CREATE")).setFont(font);
                final Panel panel2 = new Panel();
                panel2.add(super.d);
                panel2.add(this.c);
                gridBagConstraints2.fill = 2;
                gridBagConstraints2.weightx = 0.5;
                layout2.setConstraints(panel2, gridBagConstraints2);
                panel.add(panel2);
            }
            else {
                gridBagConstraints2.fill = 2;
                gridBagConstraints2.weightx = 0.5;
                layout2.setConstraints(super.d, gridBagConstraints2);
                panel.add(super.d);
            }
        }
        if (super.c instanceof ba) {
            (this.b = q.a("ENTER")).setFont(font);
            final Panel panel3 = new Panel();
            panel3.add(super.c);
            panel3.add(this.b);
            gridBagConstraints2.fill = 2;
            gridBagConstraints2.weightx = 0.5;
            layout2.setConstraints(panel3, gridBagConstraints2);
            panel.add(panel3);
        }
        else {
            gridBagConstraints2.gridwidth = 0;
            gridBagConstraints2.fill = 2;
            gridBagConstraints2.weightx = 0.5;
            layout2.setConstraints(super.c, gridBagConstraints2);
            panel.add(super.c);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final r r = new r(super.e);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
        final ImageObserver imageObserver = new ImageObserver(LanguageSupport.translate(a ? "Channels" : "Rooms"), "name") {
            protected boolean a;
            protected boolean b;
            protected int c;
            protected int d;
            protected int e;
            protected String f;
            protected String g;
            protected String h;
            protected Object i;
            bm j;
            
            public final void a(final int e) {
                this.e = e;
                if (this.j != null) {
                    this.j.c(this);
                }
            }
            
            public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x40) != 0x0) {
                    return false;
                }
                this.j.c(this);
                return (n & 0x20) == 0x0;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public final String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void b(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.j();
                }
            }
            
            public final void c(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void d(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.j();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final void b(final boolean a) {
                this.a = a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void c(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public final void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
                if (this.i instanceof Image) {
                    final Image image = (Image)this.i;
                    final int height = image.getHeight(this.j);
                    final int width = image.getWidth(this.j);
                    if (height <= 0 || height <= 0) {
                        graphics.drawImage(image, -1, -1, 1, 1, this.j);
                    }
                    else {
                        graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.j);
                    }
                }
                else if (this.i != null) {
                    graphics.setColor(b ? Color.white : this.j.getForeground());
                    bm.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final j j) {
                return false;
            }
            
            final void a(final Graphics graphics, final bu object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (o instanceof Boolean) {
                    this.a(graphics, object, (boolean)o, n, n2, n3, n4, b);
                }
                else if (o instanceof Image) {
                    this.a(graphics, object, (Image)o, n, n2, n3, n4, b);
                }
                else if (o != null) {
                    this.a(graphics, object, o.toString(), n, n2, n3, n4, b);
                }
            }
            
            final void a(final Graphics graphics, final bu object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bm.k());
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            final void a(final Graphics graphics, final bu object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (object.b == null) {
                    graphics.setFont(this.j.getFont());
                }
                else {
                    graphics.setFont(object.b);
                }
                if (b) {
                    graphics.setColor(object.e ? object.d : Color.lightGray);
                }
                else {
                    graphics.setColor(object.e ? object.c : Color.gray);
                }
                graphics.getFontMetrics().stringWidth(s);
                bm.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            final void a(final Graphics graphics, final bu object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else if (this.e == 1) {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
                }
                else if (this.e == 2) {
                    graphics.drawImage(image, n + n3 - width, n2 + (n4 - height) / 2 - 1, this);
                }
                else {
                    graphics.drawImage(image, n, n2 + (n4 - height) / 2 - 1, this);
                }
            }
            
            {
                this.a = false;
                this.b = false;
                this.c = 50;
                this.d = 50;
                this.f = f;
                this.i = i;
            }
        };
        final ImageObserver imageObserver2 = new ImageObserver(h.c, "lock") {
            protected boolean a;
            protected boolean b;
            protected int c;
            protected int d;
            protected int e;
            protected String f;
            protected String g;
            protected String h;
            protected Object i;
            bm j;
            
            public final void a(final int e) {
                this.e = e;
                if (this.j != null) {
                    this.j.c(this);
                }
            }
            
            public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x40) != 0x0) {
                    return false;
                }
                this.j.c(this);
                return (n & 0x20) == 0x0;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public final String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void b(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.j();
                }
            }
            
            public final void c(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void d(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.j();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final void b(final boolean a) {
                this.a = a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void c(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public final void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
                if (this.i instanceof Image) {
                    final Image image = (Image)this.i;
                    final int height = image.getHeight(this.j);
                    final int width = image.getWidth(this.j);
                    if (height <= 0 || height <= 0) {
                        graphics.drawImage(image, -1, -1, 1, 1, this.j);
                    }
                    else {
                        graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.j);
                    }
                }
                else if (this.i != null) {
                    graphics.setColor(b ? Color.white : this.j.getForeground());
                    bm.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final j j) {
                return false;
            }
            
            final void a(final Graphics graphics, final bu object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (o instanceof Boolean) {
                    this.a(graphics, object, (boolean)o, n, n2, n3, n4, b);
                }
                else if (o instanceof Image) {
                    this.a(graphics, object, (Image)o, n, n2, n3, n4, b);
                }
                else if (o != null) {
                    this.a(graphics, object, o.toString(), n, n2, n3, n4, b);
                }
            }
            
            final void a(final Graphics graphics, final bu object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bm.k());
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            final void a(final Graphics graphics, final bu object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (object.b == null) {
                    graphics.setFont(this.j.getFont());
                }
                else {
                    graphics.setFont(object.b);
                }
                if (b) {
                    graphics.setColor(object.e ? object.d : Color.lightGray);
                }
                else {
                    graphics.setColor(object.e ? object.c : Color.gray);
                }
                graphics.getFontMetrics().stringWidth(s);
                bm.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            final void a(final Graphics graphics, final bu object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else if (this.e == 1) {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
                }
                else if (this.e == 2) {
                    graphics.drawImage(image, n + n3 - width, n2 + (n4 - height) / 2 - 1, this);
                }
                else {
                    graphics.drawImage(image, n, n2 + (n4 - height) / 2 - 1, this);
                }
            }
            
            {
                this.a = false;
                this.b = false;
                this.c = 50;
                this.d = 50;
                this.f = f;
                this.i = i;
            }
        };
        final ImageObserver imageObserver3 = new ImageObserver(f.a("usersIcon.gif", false, 20), "users") {
            protected boolean a = false;
            protected boolean b = false;
            protected int c = 50;
            protected int d = 50;
            protected int e;
            protected String f = f;
            protected String g;
            protected String h;
            protected Object i = i;
            bm j;
            
            public final void a(final int e) {
                this.e = e;
                if (this.j != null) {
                    this.j.c(this);
                }
            }
            
            public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x40) != 0x0) {
                    return false;
                }
                this.j.c(this);
                return (n & 0x20) == 0x0;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public final String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void b(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.j();
                }
            }
            
            public final void c(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void d(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.j();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final void b(final boolean a) {
                this.a = a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void c(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public final void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
                if (this.i instanceof Image) {
                    final Image image = (Image)this.i;
                    final int height = image.getHeight(this.j);
                    final int width = image.getWidth(this.j);
                    if (height <= 0 || height <= 0) {
                        graphics.drawImage(image, -1, -1, 1, 1, this.j);
                    }
                    else {
                        graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.j);
                    }
                }
                else if (this.i != null) {
                    graphics.setColor(b ? Color.white : this.j.getForeground());
                    bm.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final j j) {
                return false;
            }
            
            final void a(final Graphics graphics, final bu object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (o instanceof Boolean) {
                    this.a(graphics, object, (boolean)o, n, n2, n3, n4, b);
                }
                else if (o instanceof Image) {
                    this.a(graphics, object, (Image)o, n, n2, n3, n4, b);
                }
                else if (o != null) {
                    this.a(graphics, object, o.toString(), n, n2, n3, n4, b);
                }
            }
            
            final void a(final Graphics graphics, final bu object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bm.k());
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            final void a(final Graphics graphics, final bu object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (object.b == null) {
                    graphics.setFont(this.j.getFont());
                }
                else {
                    graphics.setFont(object.b);
                }
                if (b) {
                    graphics.setColor(object.e ? object.d : Color.lightGray);
                }
                else {
                    graphics.setColor(object.e ? object.c : Color.gray);
                }
                graphics.getFontMetrics().stringWidth(s);
                bm.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            final void a(final Graphics graphics, final bu object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else if (this.e == 1) {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
                }
                else if (this.e == 2) {
                    graphics.drawImage(image, n + n3 - width, n2 + (n4 - height) / 2 - 1, this);
                }
                else {
                    graphics.drawImage(image, n, n2 + (n4 - height) / 2 - 1, this);
                }
            }
        };
        imageObserver2.c(0);
        imageObserver2.d(12);
        if (f instanceof g) {}
        imageObserver.b(100);
        imageObserver3.c(true);
        imageObserver3.a(1);
        imageObserver.c(true);
        super.e.a(true);
        if (!(f instanceof g) || ((g)f).k) {}
        super.e.b(imageObserver2);
        super.e.b(imageObserver);
        super.e.b(imageObserver3);
        super.e.a(imageObserver3);
    }
}
