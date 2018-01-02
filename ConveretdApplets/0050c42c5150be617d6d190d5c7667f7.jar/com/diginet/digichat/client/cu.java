// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.r;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import com.esial.util.LanguageSupport;
import java.awt.Event;
import com.diginet.digichat.awt.j;
import com.diginet.digichat.common.bg;
import com.diginet.digichat.awt.bm;
import com.diginet.digichat.awt.o;
import java.awt.Color;
import java.awt.Panel;

public class cu extends Panel
{
    protected static final Color a;
    protected static final Color b;
    protected o c;
    protected bm d;
    protected br e;
    protected h f;
    
    public final void a(final bg bg) {
        final bg bg2 = (bg)this.d.g();
        this.c(bg);
        if (bg.a.size() > 0) {
            for (int i = 0; i < bg.a.size(); ++i) {
                final bg bg3 = (bg)this.f.ak.d(bg.a.elementAt(i));
                if (bg3 != null) {
                    this.a(bg3);
                }
            }
        }
        if (bg2 != null) {
            this.d.b(bg2);
        }
    }
    
    private final void c(final bg bg) {
        synchronized (this.d) {
            if (bg.b == -999) {
                this.d.i();
                this.d.c(bg);
            }
            else {
                int i = this.d.a((j)this.f.ak.d(bg.b)) + 1;
                final String name = bg.getName();
                while (i < this.d.e()) {
                    final bg bg2 = (bg)this.d.j(i);
                    if (bg2.e > bg.e) {
                        ++i;
                    }
                    else {
                        if (bg.b != bg2.b || name.compareTo(bg2.getName()) <= 0) {
                            break;
                        }
                        ++i;
                    }
                }
                this.d.a(bg, i);
            }
            this.b(bg);
        }
        // monitorexit(this.d)
    }
    
    public final void b(final bg bg) {
        if (bg.c) {
            this.d.a(bg, cu.a, cu.b);
        }
        else {
            this.d.a(bg, Color.black, Color.white);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bg) {
                    for (int e = this.d.e(), i = 0; i < e; ++i) {
                        ((bg)this.d.j(i)).d = false;
                    }
                    final bg bg = (bg)event.arg;
                    bg.d = true;
                    if (this.c != null) {
                        this.c.b();
                    }
                    if (this.e != null) {
                        this.e.a(bg.x());
                        this.e.b(this.e.d);
                    }
                }
                return true;
            }
            case 702: {
                for (int e2 = this.d.e(), j = 0; j < e2; ++j) {
                    ((bg)this.d.j(j)).d = false;
                }
                if (this.c != null) {
                    this.c.c();
                }
                if (this.e != null) {
                    this.e.a(this.e.d);
                    this.e.e.i();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.c) {
                    final bg bg2 = (bg)this.d.g();
                    if (bg2 != null) {
                        this.f.a(bg2);
                    }
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public cu(final h h, final br br) {
        this(h, br, true);
    }
    
    public cu(final h f, final br e, final boolean b) {
        this.c = null;
        this.d = new bm();
        this.e = null;
        this.f = null;
        this.f = f;
        this.e = e;
        this.d.resize(200, 200);
        final ImageObserver imageObserver = new ImageObserver(LanguageSupport.translate("Categories"), "categoryName") {
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
        imageObserver.c(false);
        this.d.a(false);
        this.d.b(imageObserver);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        if (b && f.u(19)) {
            this.c = new o(LanguageSupport.translate("New"));
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(this.c, gridBagConstraints);
            this.c.a(LanguageSupport.translate("Click here to create a new sub-category for the currently selected category."), null);
            this.add(this.c);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 10;
        final r r = new r(this.d);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
    }
}
