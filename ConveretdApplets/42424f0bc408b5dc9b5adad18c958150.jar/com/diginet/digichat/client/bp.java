// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.t;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Enumeration;
import com.diginet.digichat.common.k;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.network.v;
import com.diginet.digichat.common.j;
import java.awt.Event;
import com.diginet.digichat.awt.bm;
import java.awt.Checkbox;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import java.awt.Image;
import com.diginet.digichat.awt.a8;
import com.diginet.digichat.awt.r;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Canvas;
import com.diginet.digichat.awt.bj;
import java.awt.Color;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public class bp extends Panel implements s
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    private bj e;
    private Canvas f;
    private Canvas g;
    private Canvas h;
    private h i;
    private Hashtable j;
    private Component k;
    
    private final void a(final Canvas canvas) {
        if (canvas instanceof r) {
            ((r)canvas).c();
        }
        else {
            ((a8)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private final void b(final Canvas canvas) {
        if (canvas instanceof r) {
            ((r)canvas).b();
        }
        else {
            ((a8)canvas).setEnabled(true);
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        s a;
        if (!this.i.ca.l() || s == null) {
            a = new r(28, 28);
            ((r)a).a(this.i.a(s3, false, 20));
        }
        else {
            final Image a2 = this.i.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.i.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.i.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new r(28, 28);
                ((r)a).a(this.i.a(s3, false, 20));
            }
            else {
                a = a8.a(a2, a3, a4);
            }
        }
        return (Canvas)a;
    }
    
    public String a(final Object o) {
        if (o instanceof r || o instanceof a8) {
            final av av = (av)this.e.g();
            if (o == this.h) {
                return com.esial.util.d.a("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (av == null) {
                return com.esial.util.d.a("This button is disabled because no buddy is selected.");
            }
            if (o == this.g) {
                return ap.a(com.esial.util.d.a("Click here to remove %1 from your Buddy List."), new String[] { av.r() });
            }
            if (o == this.f && av.a) {
                return ap.a(com.esial.util.d.a("Click here to enter a private conversation with %1."), new String[] { av.r() });
            }
            if (o == this.f && !av.a) {
                return ap.a(com.esial.util.d.a("%1 is not available for private messages now."), new String[] { av.r() });
            }
        }
        return null;
    }
    
    private final boolean c() {
        if (this.k instanceof Checkbox) {
            return ((Checkbox)this.k).getState();
        }
        return ((bm)this.k).a();
    }
    
    public void show() {
        super.show();
        this.e.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final av av = (av)this.e.g();
                if (((this.i.i(43) && event.target == this.e) || event.target == this.f) && av != null && av.a) {
                    this.i.a(null, (j)av);
                    return true;
                }
                if (event.target == this.g && av != null) {
                    this.a(av);
                    return true;
                }
                if (event.target == this.k) {
                    this.a(this.c());
                }
                if (event.target == this.h) {
                    new cd(this.i.x.b(), this.i).setVisible(true);
                }
                break;
            }
            case 701: {
                this.b(this.g);
                if (((av)this.e.g()).a) {
                    this.b(this.f);
                }
                else {
                    this.a(this.f);
                }
                return true;
            }
            case 702: {
                this.a(this.g);
                this.a(this.f);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean a(final av av) {
        this.j.remove(av.r());
        final v v = new v(33621774, 1);
        v.a(0, 0, this.i.r());
        v.a(0, 1, av.r());
        v.a(0, 2, "remove");
        this.i.ad(v);
        this.i.a8 = this.b();
        return this.e.c(av);
    }
    
    public void a(final av av, final boolean b, final boolean b2) {
        if (this.i.r().equals(av.r())) {
            return;
        }
        k k = null;
        if (av.r() != null) {
            k = this.j.get(av.r());
        }
        if (k == null && b) {
            this.j.put(av.r(), av);
            if (!b2) {
                final v v = new v(33621774, 1);
                v.a(0, 0, this.i.r());
                v.a(0, 1, av.r());
                v.a(0, 2, "add");
                this.i.ad(v);
            }
            if (av.a || !this.c()) {
                this.e.b(av);
                this.b(av);
            }
            this.i.a8 = this.b();
        }
        else if (k != null) {
            this.j.remove(k.r());
            this.j.put(av.r(), av);
            this.e.c(k);
            if (av.a || !this.c()) {
                this.e.b(av);
            }
            this.b(av);
        }
    }
    
    private final void b(final av av) {
        if (av.c) {
            this.e.a(av, Color.red, Color.pink);
        }
        else if (av.i(59)) {
            this.e.a(av, bp.c, bp.d);
        }
        else if (av.i(61) || av.i(62)) {
            this.e.a(av, bp.a, bp.b);
        }
        else {
            this.e.a(av, Color.black, Color.white);
        }
        if (av.a) {
            this.e.a(av, false);
        }
        else {
            this.e.a(av, true);
        }
        this.e.b();
    }
    
    protected void a() {
        final String a8 = this.i.a8;
        if (a8 != null && !a8.equals("")) {
            final int n = 44;
            int n2 = 0;
            for (int i = a8.indexOf(n, n2); i != -1; i = a8.indexOf(n, n2)) {
                final av av = new av(-999, a8.substring(n2, i).trim());
                av.a = false;
                this.a(av, true, true);
                n2 = i + 1;
            }
            final av av2 = new av(-999, a8.substring(n2, a8.length()).trim());
            av2.a = false;
            this.a(av2, true, true);
        }
    }
    
    public String b() {
        String s = "";
        final Enumeration<av> elements = this.j.elements();
        if (elements.hasMoreElements()) {
            s = elements.nextElement().r();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().r();
        }
        return s;
    }
    
    public void a(final boolean b) {
        final Enumeration<av> elements = this.j.elements();
        while (elements.hasMoreElements()) {
            final av av = elements.nextElement();
            if (b) {
                if (av.a) {
                    continue;
                }
                this.e.c(av);
            }
            else {
                if (this.e.a(av) != -1) {
                    continue;
                }
                this.e.b(av);
                this.b(av);
            }
        }
    }
    
    public bp(final h i) {
        this.e = new bj();
        this.k = new Checkbox(com.esial.util.d.a("Show only online buddies"));
        this.i = i;
        this.j = new Hashtable(30);
        this.setBackground(i.ca.j);
        this.setForeground(i.ca.i);
        final String f = i.ca.f();
        this.h = this.a(f, "addbuddy", "addBuddyIcon.GIF");
        this.g = this.a(f, "removebuddy", "removeBuddyIcon.GIF");
        this.f = this.a(f, "private", "whisperIcon.GIF");
        if (i.ca.l()) {
            final Image a = i.a(f + "buddies_checked.gif", true);
            final Image a2 = i.a(f + "buddies_unchecked.gif", true);
            if (a != null && a2 != null) {
                this.k = new bm(a, a2);
            }
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.h, gridBagConstraints);
        this.add(this.h);
        if (!i.i(43)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            this.add(this.g);
        }
        else {
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            this.add(this.g);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.f, gridBagConstraints);
            this.add(this.f);
        }
        if (this.k instanceof bm) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.k, gridBagConstraints);
            panel.add(this.k);
            final Label label = new Label(com.esial.util.d.a("Show only online buddies"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(label, gridBagConstraints);
            panel.add(label);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            this.add(panel);
        }
        else {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.k, gridBagConstraints);
            ((Checkbox)this.k).setState(true);
            this.add(this.k);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final t t = new t(this.e);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final ImageObserver imageObserver = new ImageObserver(null, "icon") {
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
        final ImageObserver imageObserver2 = new ImageObserver(com.esial.util.d.a("Nickname"), "name") {
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
        imageObserver.c(28);
        imageObserver.b(0);
        imageObserver2.b(true);
        this.e.a(true);
        this.e.b(imageObserver);
        this.e.b(imageObserver2);
        this.e.a(imageObserver2);
        this.e.l(26);
        this.a();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
    }
}
