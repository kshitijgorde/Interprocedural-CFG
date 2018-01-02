// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.FontMetrics;
import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.p;
import java.awt.Component;
import com.diginet.digichat.awt.t;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.awt.a7;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.ap;
import java.awt.Frame;
import java.net.URL;
import com.diginet.digichat.network.v;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import com.esial.util.d;
import com.diginet.digichat.awt.r;
import java.awt.Image;
import com.diginet.digichat.awt.a8;
import com.diginet.digichat.common.j;
import java.awt.Canvas;
import java.awt.TextArea;
import com.diginet.digichat.util.s;
import com.diginet.digichat.awt.aa;

public class z extends aa implements s
{
    protected TextArea a;
    protected Canvas b;
    protected Canvas c;
    protected Canvas d;
    protected i e;
    protected az f;
    protected int g;
    protected j h;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        s a = null;
        if (this.e.ca.l() && s != null) {
            final Image a2 = this.e.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.e.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = a8.a(a2, a3, null);
                ((a8)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new r(70, 20);
            }
            else {
                a = new r(s3, 70, 20);
            }
            ((r)a).a(s4, null);
        }
        return (Canvas)a;
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return com.esial.util.d.a("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public void a(final ay ay) {
        this.f.a(ay);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.appendText("\n");
                }
                else if (event.key == 10 || event.key == ch.a) {
                    if (this.b instanceof r) {
                        ((r)this.b).e();
                    }
                    else {
                        ((a8)this.b).a();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    final String trim = this.a.getText().trim();
                    this.a.setText("");
                    if (trim.length() > 0) {
                        this.e.a(trim, this.g, -1);
                        this.f.e();
                    }
                    if (ch.d) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.c) {
                    final v v = new v(67074, 1);
                    v.a(0, 0, this.e.q());
                    v.k = this.g;
                    this.e.ad(v);
                    return true;
                }
                if (event.target == this.d) {
                    this.b();
                    break;
                }
                if (event.arg instanceof URL) {
                    this.e.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a() {
        this.f.d();
    }
    
    public void dispose() {
        super.dispose();
        this.e.y.f(this.g);
    }
    
    public void b() {
    }
    
    public boolean c() {
        return false;
    }
    
    public z(final Frame frame, final i e, final int g, final az f) {
        this.setBackground(e.ca.c);
        this.e = e;
        this.g = g;
        this.h = (j)e.aa.d(g);
        this.setTitle(ap.a(com.esial.util.d.a("Private Conversation with %1"), new String[] { this.h.r() }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final a7 a7 = new a7();
        this.setLayout(gridBagLayout);
        a7.setLayout(gridBagLayout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t3) {
            this.a = new TextArea(2, 10);
        }
        (this.f = f).setFont(e.ca.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final t t = new t(f);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        a7.add(t);
        this.a.setFont(p.c);
        final t t2 = new t(this.a);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(t2, gridBagConstraints);
        a7.add(t2);
        a7.setBackground(e.ca.j);
        a7.setForeground(e.ca.i);
        boolean b = (!ch.e || ch.b != 1) && e.i(20) && this.h.i(21) && this.h.q() != e.q();
        if (!this.c()) {
            b = false;
        }
        final String f2 = e.ca.f();
        final String s = b ? "_3" : "_2";
        this.c = this.a(f2, "profile_request" + s, com.esial.util.d.a("Profile"), ap.a(com.esial.util.d.a("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { this.h.r() }));
        this.b = this.a(f2, "send_small" + s, com.esial.util.d.a("Send"), com.esial.util.d.a("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics = this.c.getFontMetrics(p.b);
        int stringWidth = fontMetrics.stringWidth(com.esial.util.d.a("Send"));
        final int stringWidth2 = fontMetrics.stringWidth(com.esial.util.d.a("Profile"));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.d = this.a(f2, "file" + s, com.esial.util.d.a("File Transfer"), ap.a(com.esial.util.d.a("Click here to send a file to %1."), new String[] { this.h.r() }));
        }
        gridBagConstraints.insets = ((this.c instanceof a8 || this.b instanceof a8 || this.d instanceof a8) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.b instanceof a8) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.b instanceof r) {
            final u u = new u(this.b);
            gridBagLayout.setConstraints(u, gridBagConstraints);
            a7.add(u);
        }
        else {
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            a7.add(this.b);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.c instanceof a8) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        if (this.c instanceof r) {
            this.c.resize(stringWidth2, 20);
        }
        a7.add(this.c);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            a7.add(this.d);
        }
        if (this.b instanceof r) {
            this.b.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(a7, gridBagConstraints);
        this.add(a7);
    }
}
