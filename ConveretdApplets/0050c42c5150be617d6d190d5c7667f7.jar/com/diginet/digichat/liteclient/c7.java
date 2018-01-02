// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.liteclient;

import com.diginet.digichat.awt.j;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.bm;
import com.diginet.digichat.awt.r;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.client.h;
import com.diginet.digichat.common.User;
import com.diginet.digichat.client.ChatMessage;
import com.diginet.digichat.client.User2;
import java.awt.Event;
import java.awt.Checkbox;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.client.bl;

public class c7 extends bl
{
    private o a;
    private o b;
    private o c;
    private Checkbox d;
    
    public final boolean a() {
        return this.d.getState();
    }
    
    public final void a(final boolean state) {
        this.d.setState(state);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final User2 user2 = (User2)super.g.g();
                if (user2 != null) {
                    final String name = user2.getName();
                    if ((super.h.u(43) && event.target == super.g) || event.target == this.b) {
                        super.h.a(null, (User)user2);
                        return true;
                    }
                    if (event.target == this.a) {
                        user2.d = !user2.d;
                        user2.c = false;
                        if (user2.d) {
                            if (!super.h.a2.contains(name)) {
                                super.h.a2.addElement(name);
                            }
                        }
                        else {
                            super.h.a2.removeElement(name);
                        }
                        this.b(user2);
                        return true;
                    }
                    if (event.target == this.c) {
                        user2.c = !user2.c;
                        user2.d = false;
                        super.h.a2.removeElement(name);
                        if (user2.c) {
                            if (!super.h.a3.contains(name)) {
                                super.h.a3.addElement(name);
                            }
                        }
                        else {
                            super.h.a3.removeElement(name);
                        }
                        this.b(user2);
                        return true;
                    }
                }
                if (event.target == this.d) {
                    super.h.c(this.a());
                }
                break;
            }
            case 701: {
                final User user3 = (User)event.arg;
                this.c.b();
                this.b.b();
                if (user3.u(33)) {
                    this.a.c();
                }
                else {
                    this.a.b();
                }
                return true;
            }
            case 702: {
                this.c.c();
                this.b.c();
                this.a.c();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    static /* synthetic */ void a(final c7 c7, final h h) {
        c7.h = h;
    }
    
    public c7(final h h) {
        this.a = new o(LanguageSupport.translate("Ignore"));
        this.b = new o(LanguageSupport.translate("Private Message"));
        this.c = new o(LanguageSupport.translate("Flag"));
        this.d = new Checkbox(LanguageSupport.translate("Show users in all rooms"));
        a(this, h);
        this.setBackground(h.df.tabsBackground);
        this.setForeground(h.df.tabsText);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        if (h.u(43)) {
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        if (h.q()) {
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 10.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        final r r = new r(super.g);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        this.add(r);
        final ImageObserver imageObserver = new ImageObserver(LanguageSupport.translate("Nickname"), "name") {
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
        imageObserver.c(true);
        super.g.a(true);
        super.g.b(imageObserver);
        super.g.a(imageObserver);
        super.g.l(26);
    }
}
