// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import java.net.MalformedURLException;
import com.diginet.digichat.awt.p;
import java.awt.Label;
import java.awt.Component;
import com.diginet.digichat.awt.as;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.util.ap;
import com.diginet.digichat.common.bd;
import com.esial.util.d;
import com.diginet.digichat.common.a4;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.au;
import com.diginet.digichat.awt.a7;
import com.diginet.digichat.network.v;
import java.awt.Frame;
import java.net.URL;
import java.awt.Event;
import com.diginet.digichat.common.j;
import com.diginet.digichat.awt.ai;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ShadedDialog;

public class a9 extends ShadedDialog
{
    private r a;
    private r b;
    private ai c;
    protected j d;
    private h e;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.a.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.c) {
                    this.e.f(this.d.b);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.e.a((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    this.e.a(null, this.d);
                    return true;
                }
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public a9(final Frame frame, final h e, final j d, final v v, final int n) {
        super(frame, false);
        this.a = new r(70, 20);
        this.b = new r(115, 20);
        this.setBackground(e.ca.c);
        this.d = d;
        this.e = e;
        final a7 a7 = new a7();
        a7.setBackground(e.ca.j);
        a7.setForeground(e.ca.i);
        final au au = new au();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String b = e.b(d.r());
        final String b2 = e.b(v.c(n, 0));
        final String b3 = e.b(v.c(n, 1));
        final String b4 = e.b(v.c(n, 2));
        final String b5 = e.b(v.c(n, 3));
        String value = null;
        String s = null;
        String e2 = null;
        String f = null;
        String b6 = null;
        boolean b7 = false;
        final a4 a8 = (a4)e.ab.d(d.b);
        if (a8 != null) {
            b6 = e.b(a8.r());
        }
        final int a9 = v.a(n, 1);
        if (a9 != -999) {
            value = String.valueOf(a9);
        }
        if (v.e(n, 1)) {
            s = com.esial.util.d.a("Male");
        }
        else if (v.e(n, 0)) {
            s = com.esial.util.d.a("Female");
        }
        if (e.i(41)) {
            e2 = d.e;
            f = d.f;
            if (f != null && f.equals(e2)) {
                f = null;
            }
        }
        final bd bd = (bd)e.z.d(d.a);
        this.setResizable(false);
        this.setTitle(ap.a(com.esial.util.d.a("Profile of %1"), new String[] { b }));
        this.setLayout(gridBagLayout);
        a7.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (bd != null) {
            final as as = new as();
            as.b(bd.a);
            gridBagLayout.setConstraints(as, gridBagConstraints);
            a7.add(as);
        }
        gridBagConstraints.gridwidth = 0;
        final Label label = new Label(b);
        label.setFont(p.a);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        a7.add(label);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(au, gridBagConstraints);
        a7.add(au);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (b2 != null) {
            final Label label2 = new Label(b2);
            final Label label3 = new Label(com.esial.util.d.a("Real Name"));
            label3.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            a7.add(label3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            a7.add(label2);
            b7 = true;
        }
        if (value != null) {
            final Label label4 = new Label(value);
            final Label label5 = new Label(com.esial.util.d.a("Age"));
            label5.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            a7.add(label5);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label4, gridBagConstraints);
            a7.add(label4);
            b7 = true;
        }
        if (s != null) {
            final Label label6 = new Label(s);
            final Label label7 = new Label(com.esial.util.d.a("Gender"));
            label7.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label7, gridBagConstraints);
            a7.add(label7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label6, gridBagConstraints);
            a7.add(label6);
            b7 = true;
        }
        if (b5 != null) {
            final ai ai = new ai(b5);
            final Label label8 = new Label(com.esial.util.d.a("E-mail"));
            label8.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label8, gridBagConstraints);
            a7.add(label8);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(ai, gridBagConstraints);
            try {
                String s2 = b5.toLowerCase();
                if (!s2.startsWith("mailto:")) {
                    s2 = "mailto:" + s2;
                }
                ai.a(new URL(s2));
            }
            catch (MalformedURLException ex2) {}
            a7.add(ai);
            b7 = true;
        }
        if (b4 != null) {
            final ai ai2 = new ai(b4);
            final Label label9 = new Label(com.esial.util.d.a("URL"));
            label9.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label9, gridBagConstraints);
            a7.add(label9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(ai2, gridBagConstraints);
            try {
                ai2.a(new URL(b4));
            }
            catch (MalformedURLException ex3) {}
            a7.add(ai2);
            b7 = true;
        }
        if (b3 != null) {
            final ai ai3 = new ai(b3);
            final Label label10 = new Label(com.esial.util.d.a("Comments"));
            label10.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label10, gridBagConstraints);
            a7.add(label10);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(ai3, gridBagConstraints);
            a7.add(ai3);
            b7 = true;
        }
        if (b6 != null) {
            this.c = new ai(b6);
            final Label label11 = new Label(com.esial.util.d.a("Room"));
            try {
                this.c.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            label11.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label11, gridBagConstraints);
            a7.add(label11);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            a7.add(this.c);
            b7 = true;
        }
        if (e2 != null) {
            final Label label12 = new Label(e2);
            final Label label13 = new Label(com.esial.util.d.a("IP Address"));
            label13.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label13, gridBagConstraints);
            a7.add(label13);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label12, gridBagConstraints);
            a7.add(label12);
            b7 = true;
        }
        if (f != null) {
            final Label label14 = new Label(f);
            final Label label15 = new Label(com.esial.util.d.a("Host Name"));
            label15.setFont(p.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label15, gridBagConstraints);
            a7.add(label15);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label14, gridBagConstraints);
            a7.add(label14);
            b7 = true;
        }
        if (!b7) {
            final Label label16 = new Label(com.esial.util.d.a("No profile is available."));
            label16.setFont(p.d);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label16, gridBagConstraints);
            a7.add(label16);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(a7, gridBagConstraints);
        this.add(a7);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (e.i(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(com.esial.util.d.a("Send Message"));
            this.b.resize(this.b.getFontMetrics(this.b.getFont()).stringWidth(com.esial.util.d.a("Send Message")) + 20, 20);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.a.a(com.esial.util.d.a("OK"));
        this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(com.esial.util.d.a("OK")) + 20, 20);
        final u u = new u(this.a);
        gridBagLayout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.pack();
        this.setVisible(true);
    }
}
