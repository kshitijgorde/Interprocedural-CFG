// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.t;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.client.chatmaster.cb;
import com.diginet.digichat.network.v;
import java.awt.Event;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import com.diginet.digichat.common.j;
import java.awt.Image;
import com.diginet.digichat.awt.a8;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.bm;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Canvas;
import com.diginet.digichat.util.s;

public class bh extends bi implements s
{
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Canvas e;
    private Canvas f;
    private Component g;
    
    public boolean a() {
        if (this.g instanceof Checkbox) {
            return ((Checkbox)this.g).getState();
        }
        return ((bm)this.g).a();
    }
    
    public void a(final boolean state) {
        if (this.g instanceof Checkbox) {
            ((Checkbox)this.g).setState(state);
        }
        else {
            ((bm)this.g).a(state);
        }
    }
    
    private final void b(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof r) {
            ((r)canvas).c();
        }
        else {
            ((a8)canvas).setEnabled(false);
        }
    }
    
    private final void c(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof r) {
            ((r)canvas).b();
        }
        else {
            ((a8)canvas).setEnabled(true);
        }
    }
    
    public boolean a(final Canvas canvas) {
        if (canvas == null) {
            return false;
        }
        if (canvas instanceof r) {
            return ((r)canvas).a();
        }
        return ((a8)canvas).isEnabled();
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        s a;
        if (!super.g.ca.l() || s == null) {
            a = new r(28, 28);
            ((r)a).a(super.g.a(s3, false, 20));
        }
        else {
            final Image a2 = super.g.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.g.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.g.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new r(28, 28);
                ((r)a).a(super.g.a(s3, false, 20));
            }
            else {
                a = a8.a(a2, a3, a4);
            }
        }
        return (Canvas)a;
    }
    
    public String a(final Object o) {
        if (o instanceof r || o instanceof a8) {
            final j j = (j)super.f.g();
            if (j == null) {
                return com.esial.util.d.a("This button is disabled because no user is selected.");
            }
            if (o == this.b) {
                return ap.a(com.esial.util.d.a("Click here to enter a private conversation with %1."), new String[] { j.r() });
            }
            if (o == this.e) {
                if (!j.r().equals(super.g.r())) {
                    return ap.a(com.esial.util.d.a("Click here to add %1 to your Buddy List."), new String[] { j.r() });
                }
                return ap.a(com.esial.util.d.a("This button is disabled because you can not add yourself to your Buddy List."), new String[] { j.r() });
            }
            else {
                if (o == this.c) {
                    return j.c ? ap.a(com.esial.util.d.a("Click here to stop flagging messages from %1."), new String[] { j.r() }) : ap.a(com.esial.util.d.a("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { j.r() });
                }
                if (o == this.f) {
                    return ap.a(com.esial.util.d.a("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { j.r() });
                }
                if (o == this.a) {
                    if (this.a(this.a)) {
                        return j.d ? ap.a(com.esial.util.d.a("Click here to stop ignoring messages from %1."), new String[] { j.r() }) : ap.a(com.esial.util.d.a("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { j.r() });
                    }
                    if (j.i(33)) {
                        return ap.a(com.esial.util.d.a("This button is disabled because %1 cannot be ignored."), new String[] { j.r() });
                    }
                }
                if (o == this.d) {
                    return ap.a(com.esial.util.d.a("Click here to kick %1."), new String[] { j.r() });
                }
            }
        }
        return null;
    }
    
    public boolean handleEvent(final Event event) {
        final aw aw = (aw)super.f.g();
        switch (event.id) {
            case 1001: {
                if (aw != null) {
                    final String r = aw.r();
                    if ((super.g.i(43) && event.target == super.f) || event.target == this.b) {
                        super.g.a(null, (j)aw);
                        return true;
                    }
                    if (event.target == this.f) {
                        final v v = new v(67074, 1);
                        v.a(0, 0, super.g.q());
                        v.k = aw.q();
                        super.g.ad(v);
                        return true;
                    }
                    if (event.target == this.a) {
                        aw.d = !aw.d;
                        aw.c = false;
                        if (aw.d) {
                            if (!super.g.ao.contains(r)) {
                                super.g.ao.addElement(r);
                            }
                        }
                        else {
                            super.g.ao.removeElement(r);
                        }
                        this.postEvent(new Event(this.a, 7689, this.a((Object)this.a)));
                        this.b(aw);
                        return true;
                    }
                    if (event.target == this.e) {
                        final av av = new av(aw.q(), aw.r());
                        av.a = true;
                        av.a = aw.a;
                        av.a = aw.a;
                        av.b = aw.b;
                        av.a(aw.s());
                        if (super.g.x != null) {
                            ((aq)super.g.x).a(av, true, false);
                        }
                        super.g.a(super.g.bt);
                    }
                    else {
                        if (event.target == this.c) {
                            aw.c = !aw.c;
                            aw.d = false;
                            super.g.ao.removeElement(r);
                            if (aw.c) {
                                if (!super.g.ap.contains(r)) {
                                    super.g.ap.addElement(r);
                                }
                            }
                            else {
                                super.g.ap.removeElement(r);
                            }
                            this.postEvent(new Event(this.c, 7689, this.a((Object)this.c)));
                            this.b(aw);
                            return true;
                        }
                        if (event.target == this.d) {
                            new cb(super.g, aw);
                        }
                    }
                }
                if (event.target == this.g) {
                    super.g.b(this.a());
                }
                break;
            }
            case 701: {
                final j j = (j)event.arg;
                this.c(this.c);
                this.c(this.b);
                this.c(this.f);
                if (!j.r().equals(super.g.r())) {
                    this.c(this.e);
                }
                else {
                    this.b(this.e);
                }
                if (j.i(33)) {
                    this.b(this.a);
                }
                else {
                    this.c(this.a);
                }
                if (j.i(34) || aw.q() == super.g.q()) {
                    if (this.d != null) {
                        this.b(this.d);
                    }
                }
                else if (super.g.i(44) && this.d != null) {
                    this.c(this.d);
                }
                return true;
            }
            case 702: {
                this.b(this.c);
                this.b(this.b);
                this.b(this.f);
                this.b(this.a);
                this.b(this.e);
                if (this.d != null) {
                    this.b(this.d);
                }
                return true;
            }
            default: {
                if (aw == null) {
                    break;
                }
                if (aw.i(33)) {
                    this.b(this.a);
                }
                else {
                    this.c(this.a);
                }
                if (aw.i(34) || aw.q() == super.g.q()) {
                    if (this.d != null) {
                        this.b(this.d);
                    }
                    break;
                }
                if (aw.i(44) && this.d != null) {
                    this.c(this.d);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bh(final h g) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        super.g = g;
        this.setBackground(g.ca.j);
        this.setForeground(g.ca.i);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String f = g.ca.f();
        this.a = this.a(f, "mute", "muteUserIcon.GIF");
        if (g.i(43)) {
            this.b = this.a(f, "private", "whisperIcon.GIF");
        }
        this.c = this.a(f, "flag", "flagUserIcon.GIF");
        if (g.i(44)) {
            this.d = this.a(f, "kick", "kickUserIcon.GIF");
        }
        if (g.e()) {
            this.e = this.a(f, "addbuddy", "addBuddyIcon.GIF");
        }
        this.f = this.a(f, "profile", "userInfoIcon.GIF");
        if (g.ca.l() && g.l()) {
            final Image a = g.a(f + "allusers_unchecked.gif", true);
            final Image a2 = g.a(f + "allusers_checked.gif", true);
            if (a != null && a2 != null) {
                this.g = new bm(a, a2);
            }
        }
        if (this.g == null) {
            this.g = new Checkbox(com.esial.util.d.a("Show users in all rooms"));
        }
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        if (g.i(43)) {
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        if (!g.e() && !g.i(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        else {
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (g.e() && !g.i(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        else if (g.e()) {
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        if (g.i(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        if (g.l() && this.g instanceof bm) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            panel.add(this.g);
            final Label label = new Label(com.esial.util.d.a("Show users in all rooms"));
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
        else if (g.l()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            this.add(this.g);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final t t = new t(super.f);
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
        super.f.a(true);
        super.f.b(imageObserver);
        super.f.b(imageObserver2);
        super.f.a(imageObserver2);
        super.f.l(26);
    }
}
