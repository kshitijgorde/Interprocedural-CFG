// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.bp;
import com.diginet.digichat.awt.u;
import java.net.MalformedURLException;
import com.diginet.digichat.awt.dw;
import java.awt.Label;
import java.awt.Component;
import com.diginet.digichat.awt.a9;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import com.diginet.digichat.common.bg;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.bb;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.network.v;
import java.awt.Frame;
import java.net.URL;
import java.awt.Event;
import com.diginet.digichat.common.j;
import com.diginet.digichat.awt.aw;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ag;

public class bl extends ag
{
    private r a;
    private r b;
    private aw c;
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
    
    public bl(final Frame frame, final h e, final j d, final v v, final int n) {
        super(frame, false);
        this.a = new r(70, 20);
        this.b = new r(115, 20);
        this.setBackground(e.cc.c);
        this.d = d;
        this.e = e;
        final bj bj = new bj();
        bj.setBackground(e.cc.j);
        bj.setForeground(e.cc.i);
        final bb bb = new bb();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String b = e.b(d.x());
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
        final bg bg = (bg)e.ac.e(d.b);
        if (bg != null) {
            b6 = e.b(bg.x());
        }
        final int a = v.a(n, 1);
        if (a != -999) {
            value = String.valueOf(a);
        }
        if (v.e(n, 1)) {
            s = com.esial.util.c.a("Male");
        }
        else if (v.e(n, 0)) {
            s = com.esial.util.c.a("Female");
        }
        e.a(41, false);
        if (e.i(41)) {
            e2 = d.e;
            f = d.f;
            if (f != null && f.equals(e2)) {
                f = null;
            }
        }
        final bp icon = e.getIcon(d.a);
        this.setResizable(false);
        this.setTitle(a5.a(com.esial.util.c.a("Profile of %1"), new String[] { b }));
        this.setLayout(gridBagLayout);
        bj.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (icon != null) {
            final a9 a2 = new a9(false);
            a2.b(icon.a);
            gridBagLayout.setConstraints(a2, gridBagConstraints);
            bj.add(a2);
        }
        gridBagConstraints.gridwidth = 0;
        final Label label = new Label(b);
        label.setFont(dw.a);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        bj.add(label);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(bb, gridBagConstraints);
        bj.add(bb);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (b2 != null) {
            final Label label2 = new Label(b2);
            final Label label3 = new Label(com.esial.util.c.a("Real Name"));
            label3.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            bj.add(label3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            bj.add(label2);
            b7 = true;
        }
        if (value != null) {
            final Label label4 = new Label(value);
            final Label label5 = new Label(com.esial.util.c.a("Age"));
            label5.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            bj.add(label5);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label4, gridBagConstraints);
            bj.add(label4);
            b7 = true;
        }
        if (s != null) {
            final Label label6 = new Label(s);
            final Label label7 = new Label(com.esial.util.c.a("Gender"));
            label7.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label7, gridBagConstraints);
            bj.add(label7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label6, gridBagConstraints);
            bj.add(label6);
            b7 = true;
        }
        if (b5 != null) {
            final aw aw = new aw(b5);
            final Label label8 = new Label(com.esial.util.c.a("E-mail"));
            label8.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label8, gridBagConstraints);
            bj.add(label8);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aw, gridBagConstraints);
            try {
                String s2 = b5.toLowerCase();
                if (!s2.startsWith("mailto:")) {
                    s2 = String.valueOf("mailto:").concat(String.valueOf(s2));
                }
                aw.a(new URL(s2));
            }
            catch (MalformedURLException ex2) {}
            bj.add(aw);
            b7 = true;
        }
        if (b4 != null) {
            final aw aw2 = new aw(b4);
            final Label label9 = new Label(com.esial.util.c.a("URL"));
            label9.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label9, gridBagConstraints);
            bj.add(label9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aw2, gridBagConstraints);
            try {
                aw2.a(new URL(b4));
            }
            catch (MalformedURLException ex3) {}
            bj.add(aw2);
            b7 = true;
        }
        if (b3 != null) {
            final aw aw3 = new aw(b3);
            final Label label10 = new Label(com.esial.util.c.a("Comments"));
            label10.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label10, gridBagConstraints);
            bj.add(label10);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aw3, gridBagConstraints);
            bj.add(aw3);
            b7 = true;
        }
        if (b6 != null) {
            this.c = new aw(b6);
            final Label label11 = new Label(com.esial.util.c.a("Room"));
            try {
                this.c.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            label11.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label11, gridBagConstraints);
            bj.add(label11);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            bj.add(this.c);
            b7 = true;
        }
        if (e2 != null) {
            final Label label12 = new Label(e2);
            final Label label13 = new Label(com.esial.util.c.a("IP Address"));
            label13.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label13, gridBagConstraints);
            bj.add(label13);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label12, gridBagConstraints);
            bj.add(label12);
            b7 = true;
        }
        if (f != null) {
            final Label label14 = new Label(f);
            final Label label15 = new Label(com.esial.util.c.a("Host Name"));
            label15.setFont(dw.d);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label15, gridBagConstraints);
            bj.add(label15);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label14, gridBagConstraints);
            bj.add(label14);
            b7 = true;
        }
        if (!b7) {
            final Label label16 = new Label(com.esial.util.c.a("No profile is available."));
            label16.setFont(dw.d);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label16, gridBagConstraints);
            bj.add(label16);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(bj, gridBagConstraints);
        this.add(bj);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (e.i(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(com.esial.util.c.a("Send Message"));
            this.b.resize(this.b.getFontMetrics(this.b.getFont()).stringWidth(com.esial.util.c.a("Send Message")) + 20, 20);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.a.a(com.esial.util.c.a("OK"));
        this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(com.esial.util.c.a("OK")) + 20, 20);
        final u u = new u(this.a);
        gridBagLayout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.pack();
        this.setVisible(true);
    }
}
