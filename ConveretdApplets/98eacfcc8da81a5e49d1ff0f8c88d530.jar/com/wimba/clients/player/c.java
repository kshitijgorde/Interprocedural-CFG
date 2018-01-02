// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.player;

import javax.swing.SwingUtilities;
import VT_6_1_0_11.dv;
import java.io.InputStream;
import VT_6_1_0_11.B;
import java.util.Observable;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import com.hw.client.util.a;
import java.net.URL;
import java.applet.Applet;
import VT_6_1_0_11.ca;
import javax.swing.border.Border;
import VT_6_1_0_11.du;
import VT_6_1_0_11.l;
import VT_6_1_0_11.t;
import VT_6_1_0_11.dz;
import java.util.Observer;
import VT_6_1_0_11.U;

public final class c extends U implements Observer
{
    private dz a;
    private t b;
    private boolean k;
    private String l;
    private String m;
    private String n;
    
    public c(final l l, final du du) {
        super(l);
        this.setBorder(null);
        final String parameter = l.t.getParameter("autostart");
        this.k = (parameter != null && parameter.trim().equalsIgnoreCase("true"));
        this.a = new dz(l, du);
        this.n();
        URL url;
        try {
            url = new URL(ca.a(l.t, "filename", (String)null));
        }
        catch (Exception ex2) {
            url = null;
        }
        if (url == null) {
            this.n = ca.a(l.t, "nid", "").trim();
            if (this.n == null || this.n.length() == 0) {
                this.m = ca.a(l.t, "mid", "").trim();
                this.l = ca.a(l.t, "rid", "recorder").trim();
            }
            final String s = "?";
            String s2;
            if (this.n.length() > 0) {
                s2 = s + "nid=" + this.n;
            }
            else {
                if (this.m.length() <= 0 || this.l.length() <= 0) {
                    return;
                }
                s2 = s + "rid=" + this.l + "&mid=" + this.m;
            }
            try {
                final String parameter2 = l.t.getParameter("server_url");
                URL codeBase;
                if ((codeBase = l.t.getCodeBase()).toExternalForm().startsWith("file:/") && parameter2 != null && parameter2.length() != 0) {
                    codeBase = new URL(parameter2);
                }
                url = new URL(codeBase, ca.a(l.t) + "/audio" + s2);
            }
            catch (MalformedURLException ex) {
                com.hw.client.util.a.b("PlayerContentPane.init: can not build url", ex);
                url = null;
            }
        }
        if (url != null) {
            (this.b = new t(url, false, this, 0, 0)).a();
        }
    }
    
    protected final void n() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.a);
    }
    
    public final dz c() {
        return this.a;
    }
    
    public final void update(final Observable observable, final Object o) {
        if (this.b != null && observable == this.b.b && o == VT_6_1_0_11.t.a) {
            com.hw.client.util.a.c("Sets stream to player");
            this.a.a(new B(this.b.b(), this.b), this.b);
            if (this.n != null && this.n.length() > 0) {
                this.a.r().b(this.n);
                this.a.g(true);
            }
            else if (this.m != null && this.m.length() > 0) {
                this.a.r().c(this.l);
                this.a.r().a(this.m);
                this.a.g(true);
            }
            if (this.k) {
                SwingUtilities.invokeLater(new b(this));
            }
        }
    }
    
    static dz a(final c c) {
        return c.a;
    }
}
