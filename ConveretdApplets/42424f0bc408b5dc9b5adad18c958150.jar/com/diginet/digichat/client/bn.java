// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import com.diginet.digichat.common.a4;
import com.diginet.digichat.util.s;

public class bn extends bo implements s
{
    public String a(final Object o) {
        if (o != super.c) {
            return null;
        }
        final a4 a4 = (a4)super.e.g();
        if (super.c.a()) {
            return ap.a(com.esial.util.d.a("Click here to enter %1: %2."), new String[] { a4.r(), a4.a });
        }
        if (a4 == null) {
            return com.esial.util.d.a("This button is disabled because no room is selected.");
        }
        return ap.a(com.esial.util.d.a("This button is disabled because you are already in %1."), new String[] { a4.r() });
    }
    
    public bn(final i f) {
        super.f = f;
        super.e.resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (f.i(29) || f.i(38) || f.i(27) || f.i(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.d, gridBagConstraints);
            super.d.a(com.esial.util.d.a("Click here to create a new room."), null);
            this.add(super.d);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.c, gridBagConstraints);
        this.add(super.c);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final t t = new t(super.e);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final ImageObserver imageObserver = new ImageObserver(com.esial.util.d.a("Name"), "name") {
            protected boolean a;
            protected boolean b;
            protected int c;
            protected int d;
            protected int e;
            protected String f;
            protected String g;
            protected String h;
            protected Object i;
            bj j;
            
            public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x20) != 0x0) {
                    this.j.c(this);
                    return false;
                }
                return true;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void a(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.i();
                }
            }
            
            public final void b(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void c(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.i();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void b(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
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
                    bj.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final m m) {
                return false;
            }
            
            void a(final Graphics graphics, final bq object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
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
            
            void a(final Graphics graphics, final bq object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bj.j());
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            void a(final Graphics graphics, final bq object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
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
                bj.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            void a(final Graphics graphics, final bq object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
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
        final ImageObserver imageObserver2 = new ImageObserver(i.c, "lock") {
            protected boolean a;
            protected boolean b;
            protected int c;
            protected int d;
            protected int e;
            protected String f;
            protected String g;
            protected String h;
            protected Object i;
            bj j;
            
            public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x20) != 0x0) {
                    this.j.c(this);
                    return false;
                }
                return true;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void a(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.i();
                }
            }
            
            public final void b(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void c(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.i();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void b(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
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
                    bj.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final m m) {
                return false;
            }
            
            void a(final Graphics graphics, final bq object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
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
            
            void a(final Graphics graphics, final bq object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bj.j());
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            void a(final Graphics graphics, final bq object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
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
                bj.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            void a(final Graphics graphics, final bq object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
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
            bj j;
            
            public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x20) != 0x0) {
                    this.j.c(this);
                    return false;
                }
                return true;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void a(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.i();
                }
            }
            
            public final void b(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void c(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.i();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void b(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
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
                    bj.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final m m) {
                return false;
            }
            
            void a(final Graphics graphics, final bq object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
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
            
            void a(final Graphics graphics, final bq object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bj.j());
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            void a(final Graphics graphics, final bq object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
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
                bj.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            void a(final Graphics graphics, final bq object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
                }
            }
        };
        imageObserver2.b(0);
        imageObserver2.c(12);
        imageObserver.a(100);
        imageObserver3.b(true);
        imageObserver.b(true);
        super.e.a(true);
        super.e.b(imageObserver2);
        super.e.b(imageObserver);
        super.e.b(imageObserver3);
        super.e.a(imageObserver3);
    }
}
