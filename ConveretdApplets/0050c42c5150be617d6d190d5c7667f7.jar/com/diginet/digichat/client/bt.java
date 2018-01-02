// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.client.av.bq;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.r;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Enumeration;
import com.diginet.digichat.common.DigiItem;
import com.diginet.digichat.awt.j;
import com.diginet.digichat.network.t;
import com.diginet.digichat.common.User;
import java.awt.Event;
import com.diginet.digichat.util.q;
import com.diginet.digichat.awt.bp;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.awt.Image;
import com.diginet.digichat.awt.ba;
import com.diginet.digichat.awt.o;
import java.util.Hashtable;
import java.awt.Component;
import com.diginet.digichat.awt.bm;
import java.awt.Color;
import com.diginet.digichat.util.p;
import java.awt.Panel;

public class bt extends Panel implements p
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    private bm e;
    private Component f;
    private Component g;
    private Component h;
    private Component i;
    private g j;
    private Hashtable k;
    private Component l;
    
    private final void a(final Component component) {
        if (component instanceof o) {
            ((o)component).c();
        }
        else {
            ((ba)component).setEnabled(false);
        }
        component.enable(true);
    }
    
    private final void b(final Component component) {
        if (component instanceof o) {
            ((o)component).b();
        }
        else {
            ((ba)component).setEnabled(true);
        }
    }
    
    private final Component a(final String s, final String s2, final String s3) {
        p a;
        if (!this.j.df.getImageButtons() || s == null) {
            a = new o(28, 28);
            ((o)a).a(this.j.a(s3, false, 20));
        }
        else {
            final Image a2 = this.j.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.j.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.j.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new o(28, 28);
                ((o)a).a(this.j.a(s3, false, 20));
            }
            else {
                a = ba.a(a2, a3, a4);
            }
        }
        return (Component)a;
    }
    
    public final String a(final Object o) {
        if (o instanceof o || o instanceof ba) {
            final az az = (az)this.e.g();
            if (o == this.g) {
                return LanguageSupport.translate("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (az == null) {
                return LanguageSupport.translate("This button is disabled because no buddy is selected.");
            }
            if (o == this.f) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to remove %1 from your Buddy List."), new String[] { az.getName() });
            }
            if (o == this.h) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to get the profile of %1.  This will return information entered by the user, such as his or her real name."), new String[] { az.getName() });
            }
            if (o == this.i && az.u(33)) {
                return StringSubst.Substitute(LanguageSupport.translate("This button is disabled because %1 cannot be blocked."), new String[] { az.getName() });
            }
            if (o == this.i) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { az.getName() });
            }
        }
        return null;
    }
    
    private final boolean c() {
        if (this.l instanceof bp) {
            return ((bp)this.l).a();
        }
        return q.c(this.l);
    }
    
    public final void show() {
        super.show();
        this.e.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final az az = (az)this.e.g();
                if (this.j.u(43) && event.target == this.e && az != null && az.a) {
                    this.j.a(null, (User)az);
                    return true;
                }
                if (event.target == this.f && az != null) {
                    this.a(az);
                    return true;
                }
                if (event.target == this.l) {
                    this.a(this.c());
                }
                if (event.target == this.g) {
                    new cx(this.j.af.b(), this.j).setVisible(true);
                }
                if (event.target == this.h) {
                    this.j.b(az);
                    return true;
                }
                if (event.target == this.i) {}
                break;
            }
            case 701: {
                this.b(this.f);
                this.b(this.h);
                this.b(this.i);
                final az az2 = (az)this.e.g();
                if (az2.u(33)) {
                    this.a(this.i);
                }
                else {
                    this.b(this.i);
                }
                if (az2.a) {}
                return true;
            }
            case 702: {
                this.a(this.f);
                this.a(this.h);
                this.a(this.i);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean a(final az az) {
        this.k.remove(az.getName());
        if (this.j.b2 != null && this.j.b2.length() != 0) {
            final t t = new t(33621774, 1);
            t.a(0, 0, this.j.getName());
            t.a(0, 1, az.getName());
            t.a(0, 2, "remove");
            this.j.ap(t);
        }
        this.j.bo = this.b();
        if (this.j.e && this.j.j) {
            try {
                this.j.i();
            }
            catch (Exception ex) {
                System.err.println("Error saving buddy list");
            }
        }
        return this.e.d(az);
    }
    
    public final boolean a(final az az, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (this.j.getName().equals(az.getName())) {
            return b3;
        }
        DigiItem digiItem = null;
        if (az.getName() != null) {
            digiItem = this.k.get(az.getName());
        }
        if (digiItem == null && b) {
            this.k.put(az.getName(), az);
            if (!b2 && this.j.b2 != null && this.j.b2.length() != 0) {
                final t t = new t(33621774, 1);
                t.a(0, 0, this.j.getName());
                t.a(0, 1, az.getName());
                t.a(0, 2, "add");
                this.j.ap(t);
            }
            if (az.a || !this.c()) {
                this.e.c(az);
                this.b(az);
            }
            this.j.bo = this.b();
            if (this.j.e && this.j.j) {
                try {
                    this.j.i();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (digiItem != null) {
            b3 = true;
            this.k.remove(digiItem.getName());
            this.k.put(az.getName(), az);
            this.e.d(digiItem);
            if (az.a || !this.c()) {
                this.e.c(az);
            }
            this.b(az);
        }
        return b3;
    }
    
    private final void b(final az az) {
        if (az.c) {
            this.e.a(az, Color.red, Color.pink);
        }
        else if (az.u(59)) {
            this.e.a(az, bt.c, bt.d);
        }
        else if (az.u(61) || az.u(62)) {
            this.e.a(az, bt.a, bt.b);
        }
        else {
            this.e.a(az, Color.black, Color.white);
        }
        if (az.a) {
            this.e.g(az);
        }
        else {
            this.e.f(az);
        }
        this.e.b();
    }
    
    protected final void a() {
        final String bo = this.j.bo;
        if (bo != null && !bo.equals("")) {
            final int n = 44;
            int n2 = 0;
            for (int i = bo.indexOf(n, n2); i != -1; i = bo.indexOf(n, n2)) {
                final az az = new az(-999, bo.substring(n2, i).trim());
                az.a = false;
                this.a(az, true, true);
                n2 = i + 1;
            }
            final az az2 = new az(-999, bo.substring(n2, bo.length()).trim());
            az2.a = false;
            this.a(az2, true, true);
        }
    }
    
    public final String b() {
        String s = "";
        final Enumeration<az> elements = this.k.elements();
        if (elements.hasMoreElements()) {
            s = elements.nextElement().getName();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().getName();
        }
        return s;
    }
    
    public final void a(final boolean b) {
        final Enumeration<az> elements = this.k.elements();
        while (elements.hasMoreElements()) {
            final az az = elements.nextElement();
            if (b) {
                if (az.a) {
                    continue;
                }
                this.e.d(az);
            }
            else {
                if (this.e.a(az) != -1) {
                    continue;
                }
                this.e.c(az);
                this.b(az);
            }
        }
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.doLayout();
            this.resize(n4, n5);
            this.repaint(n2, n3, n4, n5);
            this.validate();
            return false;
        }
        return (n & 0x40) == 0x0;
    }
    
    public bt(final g j, final boolean b) {
        this.e = new bm();
        this.l = q.b(LanguageSupport.translate("Show only online buddies"));
        this.j = j;
        this.k = new Hashtable(30);
        this.setBackground(j.df.tabsBackground);
        this.setForeground(j.df.tabsText);
        final String fullDirectory = j.df.getFullDirectory();
        this.h = this.a(fullDirectory, "profile", "userInfoIcon.GIF");
        this.i = this.a(fullDirectory, "block", "muteUserIcon.GIF");
        this.g = this.a(fullDirectory, "addbuddy", "addBuddyIcon.GIF");
        this.f = this.a(fullDirectory, "removebuddy", "removeBuddyIcon.GIF");
        if (j.df.getImageButtons()) {
            final Image a = j.a(fullDirectory + "buddies_unchecked.gif", true);
            final Image a2 = j.a(fullDirectory + "buddies_checked.gif", true);
            if (a != null && a2 != null) {
                this.l = new bp(a, a2);
            }
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(this.h, gridBagConstraints);
        this.add(this.h);
        gridBagLayout.setConstraints(this.i, gridBagConstraints);
        this.add(this.i);
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        this.add(this.g);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        if (this.l instanceof bp) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.l, gridBagConstraints);
            panel.add(this.l);
            final Component a3 = q.a(LanguageSupport.translate("Show only online buddies"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(a3, gridBagConstraints);
            panel.add(a3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            this.add(panel);
        }
        else {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.l, gridBagConstraints);
            this.add(this.l);
        }
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final r r = new r(this.e);
        gridBagLayout.setConstraints(r, gridBagConstraints);
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
        final ImageObserver imageObserver2 = new ImageObserver(LanguageSupport.translate("Name"), "name") {
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
        ImageObserver imageObserver3 = null;
        if (!j.da) {
            imageObserver3 = new ImageObserver(LanguageSupport.translate("Room"), "roomName") {
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
        }
        imageObserver.d(28);
        imageObserver.c(0);
        imageObserver2.c(true);
        this.e.a(true);
        if (b) {
            final ImageObserver imageObserver4 = new ImageObserver(null, "availability") {
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
            imageObserver4.d(22);
            imageObserver4.c(22);
            this.e.b(imageObserver4);
            imageObserver.d(24);
        }
        this.e.b(imageObserver);
        this.e.b(imageObserver2);
        if (imageObserver3 != null) {
            imageObserver3.c(true);
            this.e.b(imageObserver3);
        }
        if (j.k) {
            final bq bq = new bq(j, null, "audio");
            ((bm.bo)bq).d(23);
            ((bm.bo)bq).c(0);
            final bq bq2 = new bq(j, null, "video");
            ((bm.bo)bq2).d(20);
            ((bm.bo)bq2).c(0);
            imageObserver2.c(105);
            imageObserver2.d(105);
            this.e.b((bm.bo)bq);
            this.e.b((bm.bo)bq2);
        }
        this.e.a(imageObserver2);
        this.e.l(26);
        this.a();
        this.b(this.f);
        this.b(this.h);
        this.b(this.i);
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
    }
}
