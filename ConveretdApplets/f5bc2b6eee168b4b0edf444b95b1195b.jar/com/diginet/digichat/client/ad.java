// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.liteclient.c4;
import com.diginet.digichat.awt.m;
import java.awt.Panel;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.util.StringSubst;
import java.awt.Frame;
import java.awt.Event;
import com.diginet.digichat.util.c3;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ba;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.net.MalformedURLException;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.net.URL;
import com.diginet.digichat.awt.aa;
import com.diginet.digichat.common.User;
import com.diginet.digichat.awt.a9;
import java.awt.Component;
import java.awt.TextArea;
import com.diginet.digichat.util.p;
import com.diginet.digichat.awt.ae;

public class ad extends ae implements p
{
    protected TextArea a;
    protected Component b;
    protected Component c;
    protected Component d;
    protected Component e;
    protected h f;
    protected a2 g;
    protected a9 h;
    protected int i;
    protected User j;
    long k;
    long l;
    private af m;
    private Thread n;
    private aa o;
    private aa p;
    protected boolean q;
    
    protected final void a(final String s) {
        if (!this.f.de || !this.f.db) {
            return;
        }
        if (s == null || s.equals("")) {
            if (!this.f.df.getPMLogoEnabled() && !this.f.dd) {
                this.remove(this.h);
            }
            return;
        }
        try {
            if (this.f.df.getPMLogoEnabled() || this.f.dd) {
                this.f.df.pmLogo = this.f.a(this.f.df.getFullDirectory() + "pmLogo.gif", true, 10);
                this.p.b(this.f.df.pmLogo);
            }
            this.o = new aa();
            final Image a = this.f.a(new URL(s));
            this.prepareImage(a, this.o);
            final MediaTracker mediaTracker = new MediaTracker(this.o);
            mediaTracker.addImage(a, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {
                return;
            }
            if (a == null || (mediaTracker.statusID(0, false) & 0x4) != 0x0 || (mediaTracker.statusID(0, false) & 0x2) != 0x0) {
                return;
            }
            this.o.b(a);
        }
        catch (MalformedURLException ex2) {}
        catch (Exception ex3) {}
        this.h.invalidate();
        this.h.removeAll();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.h.setLayout(layout);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.o, gridBagConstraints);
        this.o.resize(this.o.a().getWidth(this.o), this.o.a().getHeight(this.o));
        this.h.add(this.o);
        if (this.f.df.getPMLogoEnabled() || this.f.dd) {
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.p, gridBagConstraints);
            this.h.add(this.p);
        }
        this.validate();
    }
    
    private final Component a(final String s, final String s2, final String s3, final String s4) {
        p a = null;
        if (this.f.df.getImageButtons() && s != null) {
            final Image a2 = this.f.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.f.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.f.a(s + s2 + "_button_disabled.gif", true);
            if (a2 != null && a3 != null && a4 != null) {
                a = ba.a(a2, a3, a4);
                ((ba)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new o(70, 20);
            }
            else {
                a = new o(s3, 70, 20);
            }
            ((o)a).a(s4, null);
        }
        return (Component)a;
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return LanguageSupport.translate("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public final void a(final ChatMessage chatMessage) {
        if (this.f.c9 && c3.c > 66048) {
            try {
                if (this.getState() == 1) {
                    this.setState(0);
                }
            }
            catch (Exception ex) {}
            this.show(true);
        }
        this.g.a(chatMessage);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.appendText("\n");
                }
                else if (event.key == 10 || event.key == c3.a) {
                    this.f.a(this.i, this.a.getText().trim());
                    this.a();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.d) {
                    this.f.b(this.j);
                    return true;
                }
                if (event.target == this.c) {
                    this.f();
                    break;
                }
                if (event.target == this.b) {
                    if (!(this.f instanceof g)) {
                        return true;
                    }
                    ((g)this.f).b((User2)this.j);
                    break;
                }
                else {
                    if (event.target == this.e) {
                        ((g)this.f).a((User2)this.j, true, true);
                        break;
                    }
                    if (event.arg instanceof URL) {
                        this.f.a((URL)event.arg, "_blank");
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void a() {
        final String trim = this.a.getText().trim();
        this.a.setText("");
        if (trim.length() > 0) {
            this.f.b(trim, this.i, -1);
            this.g.e();
        }
        if (c3.d) {
            this.a.requestFocus();
        }
    }
    
    public final void b() {
        this.g.d();
    }
    
    public void dispose() {
        super.dispose();
        this.f.ag.f(this.i);
        if (this.m != null) {
            this.m.a();
            this.m = null;
        }
    }
    
    protected final void c() {
        this.q = false;
        this.k = this.f.n;
        if (this.k == 0L) {
            return;
        }
        if (this.m != null) {
            this.m.a();
        }
        this.l = System.currentTimeMillis();
        if (this == null) {
            throw null;
        }
        this.m = new Runnable() {
            protected boolean a = false;
            
            public final void a() {
                this.a = true;
            }
            
            public final void run() {
                this.a = false;
                while (ad.this.l + ad.this.k >= System.currentTimeMillis() && !this.a) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
                if (!this.a) {
                    ad.this.e();
                }
            }
        };
        (this.n = new Thread(this.m)).start();
    }
    
    protected final void d() {
        if (this.m != null) {
            this.m.a();
        }
        this.m = null;
    }
    
    protected void e() {
    }
    
    public void f() {
    }
    
    public boolean g() {
        return false;
    }
    
    public ad(final Frame frame, final h f, final int i, final a2 g) {
        this.q = false;
        this.setBackground(f.df.outerBackground);
        this.f = f;
        this.i = i;
        this.j = (User)f.aj.d(i);
        this.setTitle(StringSubst.Substitute(LanguageSupport.translate("Private Conversation with %1"), new String[] { this.j.getName() }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final a9 a9 = new a9();
        this.setLayout(gridBagLayout);
        a9.setLayout(gridBagLayout);
        if (f.df.borderImgs != null) {
            a9.a(f.df.borderImgs);
        }
        try {
            this.a = new TextArea("", 2, 10, 1);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        (this.g = g).setFont(f.df.getFont());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final r r = new r(g);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        a9.add(r);
        final String fullDirectory = f.df.getFullDirectory();
        final Panel panel = new Panel();
        panel.setLayout(gridBagLayout);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 12, 0, 12);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        if (f instanceof g && ((g)f).k) {
            gridBagLayout.setConstraints(this.b = this.a(fullDirectory, "video", LanguageSupport.translate("Video Chat"), StringSubst.Substitute(LanguageSupport.translate("Click here to engage %1 in a video chat."), new String[] { this.j.getName() })), gridBagConstraints);
            panel.add(this.b, gridBagConstraints);
        }
        boolean b = (!c3.e || c3.b != 1) && f.u(20) && this.j.u(21) && this.j.x() != f.x();
        if (!this.g()) {
            b = false;
        }
        if (b) {
            gridBagLayout.setConstraints(this.c = this.a(fullDirectory, "file_transfer", LanguageSupport.translate("File Transfer"), StringSubst.Substitute(LanguageSupport.translate("Click here to send a file to %1."), new String[] { this.j.getName() })), gridBagConstraints);
            panel.add(this.c);
        }
        gridBagLayout.setConstraints(this.d = this.a(fullDirectory, "profile", LanguageSupport.translate("Profile"), StringSubst.Substitute(LanguageSupport.translate("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { this.j.getName() })), gridBagConstraints);
        panel.add(this.d);
        if (f.e()) {
            gridBagLayout.setConstraints(this.e = this.a(fullDirectory, "addbuddy", LanguageSupport.translate("Add Buddy"), StringSubst.Substitute(LanguageSupport.translate("Click here to add %1 to your buddy list."), new String[] { this.j.getName() })), gridBagConstraints);
            panel.add(this.e);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 5, 0, 5);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        a9.add(panel);
        this.a.setFont(com.diginet.digichat.awt.m.c);
        final r r2 = new r(this.a);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(r2, gridBagConstraints);
        a9.add(r2);
        a9.setBackground(f.df.tabsBackground);
        a9.setForeground(f.df.tabsText);
        if (!(f instanceof c4) && (f.df.getPMLogoEnabled() || (f.db && (f.dd || f.de)))) {
            (this.h = new a9()).setLayout(gridBagLayout);
            if (f.df.borderImgs != null) {
                this.h.a(f.df.borderImgs);
            }
            this.h.setBackground(f.df.tabsBackground);
            this.h.setForeground(f.df.tabsText);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.anchor = 10;
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.gridheight = 0;
            gridBagConstraints2.gridwidth = 0;
            gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints2.ipadx = 0;
            gridBagConstraints2.ipady = 0;
            gridBagConstraints2.weightx = 1.0;
            gridBagConstraints2.weighty = 1.0;
            if (f.df.getPMLogoEnabled() || f.dd) {
                this.p = new aa();
                f.df.pmLogo = f.a(f.df.getFullDirectory() + "pmLogo.gif", true, 10);
                if (f.df.pmLogo != null) {
                    this.p.b(f.df.pmLogo);
                    gridBagConstraints2.fill = 0;
                    gridBagLayout.setConstraints(this.p, gridBagConstraints2);
                    this.h.add(this.p);
                }
            }
            gridBagConstraints2.gridheight = -1;
            gridBagConstraints2.fill = 1;
            gridBagConstraints2.weightx = 0.0;
            gridBagConstraints2.weighty = 0.0;
            gridBagLayout.setConstraints(this.h, gridBagConstraints2);
            if (f.df.pmLogo != null || f.de) {
                this.validate();
                this.h.setVisible(true);
            }
            else {
                this.h.setVisible(false);
            }
            this.add(this.h);
        }
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.gridheight = 0;
        gridBagConstraints3.fill = 1;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        gridBagLayout.setConstraints(a9, gridBagConstraints3);
        this.add(a9);
        if (f.de) {
            f.r(i);
        }
    }
}
