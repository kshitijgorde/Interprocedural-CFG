// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.client.av.bq;
import com.diginet.digichat.awt.j;
import java.awt.Color;
import java.awt.Graphics;
import com.diginet.digichat.awt.r;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.client.chatmaster.cv;
import java.awt.Event;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.User;
import java.awt.Image;
import com.diginet.digichat.awt.ba;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.util.q;
import com.diginet.digichat.awt.bp;
import com.diginet.digichat.awt.bm;
import java.awt.Component;
import java.awt.image.ImageObserver;
import com.diginet.digichat.util.p;

public class bk extends bl implements p, ImageObserver
{
    private Component a;
    private Component b;
    private Component c;
    private Component d;
    private Component e;
    private Component f;
    private Component g;
    private Component h;
    private Component i;
    private bm.bo j;
    
    public final boolean a() {
        if (this.h instanceof bp) {
            return ((bp)this.h).a();
        }
        return q.c(this.h);
    }
    
    public final void a(final boolean b) {
        if (this.h instanceof bp) {
            ((bp)this.h).a(b);
        }
        else {
            q.c(this.h, b);
        }
    }
    
    private final void b(final Component component) {
        if (component == null) {
            return;
        }
        if (component instanceof o) {
            ((o)component).c();
        }
        else {
            ((ba)component).setEnabled(false);
        }
        component.enable(true);
    }
    
    private final void c(final Component component) {
        if (component == null) {
            return;
        }
        if (component instanceof o) {
            ((o)component).b();
        }
        else {
            ((ba)component).setEnabled(true);
        }
    }
    
    public final boolean a(final Component component) {
        if (component == null) {
            return false;
        }
        if (component instanceof o) {
            return ((o)component).a();
        }
        return ((ba)component).isEnabled();
    }
    
    private final Component a(final String s, final String s2, final String s3) {
        p a;
        if (!super.h.df.getImageButtons() || s == null) {
            a = new o(28, 28);
            ((o)a).a(super.h.a(s3, false, 20));
        }
        else {
            final Image a2 = super.h.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.h.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.h.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new o(28, 28);
                ((o)a).a(super.h.a(s3, false, 20));
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
    
    public final void b(final boolean b) {
        super.g.a(b);
        if (b) {
            super.g.a(this.j);
        }
    }
    
    public final String a(final Object o) {
        if (o instanceof o || o instanceof ba) {
            final User user = (User)super.g.g();
            if (o != this.i && user == null) {
                return LanguageSupport.translate("This button is disabled because no user is selected.");
            }
            if (o == this.b) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to enter a private conversation with %1."), new String[] { user.getName() });
            }
            if (o == this.e) {
                if (!user.getName().equals(super.h.getName())) {
                    return StringSubst.Substitute(LanguageSupport.translate("Click here to add %1 to your Buddy List."), new String[] { user.getName() });
                }
                return StringSubst.Substitute(LanguageSupport.translate("This button is disabled because you can not add yourself to your Buddy List."), new String[] { user.getName() });
            }
            else if (o == this.g) {
                if (!user.getName().equals(super.h.getName())) {
                    return StringSubst.Substitute(LanguageSupport.translate("Click here to initiate a %1 Session with %2."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, user.getName() });
                }
                return StringSubst.Substitute(LanguageSupport.translate("This button is disabled because you can not initiate a %1 Session with yourself."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
            }
            else {
                if (o == this.c) {
                    return user.c ? StringSubst.Substitute(LanguageSupport.translate("Click here to stop flagging messages from %1."), new String[] { user.getName() }) : StringSubst.Substitute(LanguageSupport.translate("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { user.getName() });
                }
                if (o == this.f) {
                    return StringSubst.Substitute(LanguageSupport.translate("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { user.getName() });
                }
                if (o == this.a) {
                    if (this.a(this.a)) {
                        return user.d ? StringSubst.Substitute(LanguageSupport.translate("Click here to stop ignoring messages from %1."), new String[] { user.getName() }) : StringSubst.Substitute(LanguageSupport.translate("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { user.getName() });
                    }
                    if (user.u(33)) {
                        return StringSubst.Substitute(LanguageSupport.translate("This button is disabled because %1 cannot be ignored."), new String[] { user.getName() });
                    }
                }
                if (o == this.d) {
                    return StringSubst.Substitute(LanguageSupport.translate("Click here to kick %1."), new String[] { user.getName() });
                }
                if (o == this.i) {
                    return LanguageSupport.translate("Click here to display the \"Categories and Rooms\" window.");
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final User2 user2 = (User2)super.g.g();
        switch (event.id) {
            case 1001: {
                if (user2 != null) {
                    final String name = user2.getName();
                    if ((super.h.u(43) && event.target == super.g) || event.target == this.b) {
                        super.h.a(null, (User)user2);
                        return true;
                    }
                    if (event.target == this.f) {
                        super.h.b(user2);
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
                        this.postEvent(new Event(this.a, 7689, this.a((Object)this.a)));
                        this.b(user2);
                        return true;
                    }
                    if (event.target == this.e) {
                        if (super.h instanceof g) {
                            ((g)super.h).a(user2, true, true);
                        }
                    }
                    else {
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
                            this.postEvent(new Event(this.c, 7689, this.a((Object)this.c)));
                            this.b(user2);
                            return true;
                        }
                        if (event.target == this.d) {
                            new cv(super.h, user2);
                        }
                        else if (event.target == this.g) {
                            ((g)super.h).d(user2);
                        }
                    }
                }
                if (event.target == this.h) {
                    super.h.c(this.a());
                }
                if (event.target == this.i) {
                    super.h.k();
                    return true;
                }
                break;
            }
            case 701: {
                final User user3 = (User)event.arg;
                this.c(this.c);
                this.c(this.b);
                this.c(this.f);
                if (!user3.getName().equals(super.h.getName())) {
                    this.c(this.e);
                    this.c(this.g);
                }
                else {
                    this.b(this.e);
                    this.b(this.g);
                }
                if (user3.u(33)) {
                    this.b(this.a);
                }
                else {
                    this.c(this.a);
                }
                if (user3.u(34) || user2.x() == super.h.x()) {
                    if (this.d != null) {
                        this.b(this.d);
                    }
                }
                else if (super.h.u(44) && this.d != null) {
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
                this.b(this.g);
                if (this.d != null) {
                    this.b(this.d);
                }
                break;
            }
            default: {
                if (user2 == null) {
                    break;
                }
                if (user2.u(33)) {
                    this.b(this.a);
                }
                else {
                    this.c(this.a);
                }
                if (user2.u(34) || user2.x() == super.h.x()) {
                    if (this.d != null) {
                        this.b(this.d);
                    }
                    break;
                }
                if (user2.u(44) && this.d != null) {
                    this.c(this.d);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            try {
                this.getParent().doLayout();
                this.getParent().validate();
            }
            catch (Exception ex) {
                return true;
            }
            this.doLayout();
            this.resize(n4, n5);
            return false;
        }
        return (n & 0x40) == 0x0;
    }
    
    public bk(final g h, final boolean b) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        super.h = h;
        this.setBackground(h.df.tabsBackground);
        this.setForeground(h.df.tabsText);
        final String fullDirectory = h.df.getFullDirectory();
        this.f = this.a(fullDirectory, "profile", "userInfoIcon.GIF");
        this.a = this.a(fullDirectory, "block", "muteUserIcon.GIF");
        if (!b) {
            this.c = this.a(fullDirectory, "flag", "flagUserIcon.GIF");
        }
        if (h.u(43)) {
            this.b = this.a(fullDirectory, "private", "whisperIcon.GIF");
        }
        if (h.u(44)) {
            this.d = this.a(fullDirectory, "kick", "kickUserIcon.GIF");
        }
        if (h.e()) {
            this.e = this.a(fullDirectory, "addbuddy", "addBuddyIcon.GIF");
        }
        if (h.cb != null) {
            this.g = this.a(fullDirectory, "initiate", "initiateIcon.GIF");
        }
        if (h.df.getImageButtons() && h.q()) {
            final Image a = h.a(fullDirectory + "allusers_unchecked.gif", true);
            final Image a2 = h.a(fullDirectory + "allusers_checked.gif", true);
            if (a != null && a2 != null) {
                this.prepareImage(a, this);
                this.prepareImage(a2, this);
                this.h = new bp(a, a2);
                if (h.c2) {
                    this.i = ba.a(a, a2, null);
                }
            }
        }
        if (this.h == null) {
            this.h = q.b(StringSubst.Substitute(LanguageSupport.translate("Show users in all %1"), new String[] { b ? "channels" : "rooms" }));
        }
        if (this.i == null && h.c2) {
            this.i = new o("Rooms");
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagLayout layout2 = new GridBagLayout();
        this.setLayout(layout);
        final Panel panel = new Panel();
        panel.setLayout(layout2);
        gridBagConstraints2.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints2.weightx = 1.0;
        layout2.setConstraints(this.f, gridBagConstraints2);
        panel.add(this.f);
        if (h.u(43)) {
            layout2.setConstraints(this.b, gridBagConstraints2);
            panel.add(this.b);
        }
        if (this.c != null) {
            layout2.setConstraints(this.c, gridBagConstraints2);
            panel.add(this.c);
        }
        if (!h.e() && !h.u(44) && this.g == null) {
            gridBagConstraints2.gridwidth = 0;
            layout2.setConstraints(this.a, gridBagConstraints2);
            panel.add(this.a);
        }
        else {
            layout2.setConstraints(this.a, gridBagConstraints2);
            panel.add(this.a);
        }
        if (h.e()) {
            if (!h.u(44) && this.g == null) {
                gridBagConstraints2.gridwidth = 0;
            }
            layout2.setConstraints(this.e, gridBagConstraints2);
            panel.add(this.e);
        }
        if (h.u(44)) {
            if (this.g == null) {
                gridBagConstraints2.gridwidth = 0;
            }
            layout2.setConstraints(this.d, gridBagConstraints2);
            panel.add(this.d);
        }
        if (this.g != null) {
            gridBagConstraints2.gridwidth = 0;
            layout2.setConstraints(this.g, gridBagConstraints2);
            panel.add(this.g);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 17;
        if (h.c2 && this.i != null && this.i instanceof ba) {
            final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            final GridBagLayout layout3 = new GridBagLayout();
            final Panel panel2 = new Panel();
            panel2.setLayout(layout3);
            gridBagConstraints3.weightx = 0.0;
            gridBagConstraints3.gridwidth = -1;
            gridBagConstraints3.anchor = 17;
            gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
            layout3.setConstraints(this.i, gridBagConstraints3);
            panel2.add(this.i);
            final Component a3 = q.a(LanguageSupport.translate("Change Rooms"));
            gridBagConstraints3.weightx = 1.0;
            gridBagConstraints3.gridwidth = 0;
            gridBagConstraints3.fill = 2;
            gridBagConstraints3.anchor = 17;
            layout3.setConstraints(a3, gridBagConstraints3);
            panel2.add(a3);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel2, gridBagConstraints);
            this.add(panel2);
        }
        else if (h.c2 && this.i != null) {
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.i, gridBagConstraints);
            this.add(this.i);
        }
        if (h.q() && this.h instanceof bp) {
            final Panel panel3 = new Panel();
            final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            final GridBagLayout layout4 = new GridBagLayout();
            panel3.setLayout(layout4);
            gridBagConstraints4.weightx = 0.0;
            gridBagConstraints4.gridwidth = -1;
            gridBagConstraints4.anchor = 17;
            gridBagConstraints4.insets = new Insets(0, 5, 0, 0);
            layout4.setConstraints(this.h, gridBagConstraints4);
            panel3.add(this.h);
            final Component a4 = q.a(StringSubst.Substitute(LanguageSupport.translate("Show users in all %1"), new String[] { b ? "channels" : "rooms" }));
            gridBagConstraints4.weightx = 1.0;
            gridBagConstraints4.gridwidth = 0;
            gridBagConstraints4.fill = 2;
            gridBagConstraints4.anchor = 17;
            layout4.setConstraints(a4, gridBagConstraints4);
            panel3.add(a4);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel3, gridBagConstraints);
            this.add(panel3);
        }
        else if (h.q()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.h, gridBagConstraints);
            this.add(this.h);
        }
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final r r = new r(super.g);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
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
        this.j = new ImageObserver("Name", "name") {
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
        super.g.a(true);
        if (b) {
            final ImageObserver imageObserver2 = new ImageObserver(null, "availability") {
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
            imageObserver2.d(22);
            imageObserver2.c(22);
            super.g.b(imageObserver2);
            imageObserver.d(24);
        }
        else {
            imageObserver.d(28);
        }
        imageObserver.c(0);
        super.g.b(imageObserver);
        this.j.c(true);
        super.g.b(this.j);
        if (h.k) {
            final bq bq = new bq(h, null, "audio");
            ((bm.bo)bq).d(23);
            ((bm.bo)bq).c(0);
            final bq bq2 = new bq(h, null, "video");
            ((bm.bo)bq2).d(20);
            ((bm.bo)bq2).c(0);
            this.j.b(true);
            this.j.c(105);
            this.j.d(105);
            super.g.b((bm.bo)bq);
            super.g.b((bm.bo)bq2);
        }
        super.g.a(this.j);
        super.g.l(26);
        this.c(this.f);
        this.c(this.a);
        this.c(this.c);
        if (this.b != null) {
            this.c(this.b);
        }
        if (this.d != null) {
            this.c(this.d);
        }
        if (this.e != null) {
            this.c(this.e);
        }
        if (this.g != null) {
            this.c(this.g);
        }
    }
}
