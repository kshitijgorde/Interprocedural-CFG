// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.s;
import com.diginet.digichat.util.q;
import java.io.InputStream;
import java.util.Properties;
import java.io.ByteArrayInputStream;
import com.diginet.digichat.awt.aa;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.util.StringSubst;
import com.diginet.digichat.common.a1;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.a6;
import com.diginet.digichat.awt.ay;
import com.diginet.digichat.awt.a9;
import com.diginet.digichat.network.t;
import java.awt.Frame;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import com.diginet.digichat.awt.an;
import java.awt.Component;
import com.diginet.digichat.awt.m;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.common.User;
import com.diginet.ui.z;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public class bd extends ShadedDialog
{
    private o a;
    private o b;
    private z c;
    protected User d;
    private g e;
    
    private final boolean a(final String s, final String s2, final boolean b, String string, final GridBagConstraints gridBagConstraints, final GridBagLayout gridBagLayout, final Panel panel) {
        if (s == null) {
            return false;
        }
        final z z = new z(s2);
        z.setFont(m.d);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(z, gridBagConstraints);
        panel.add(z);
        if (b || string != null) {
            final an an = new an(s);
            an.setFont(m.d);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(an, gridBagConstraints);
            panel.add(an);
            if (string != null) {
                try {
                    an.a(new URL(string));
                }
                catch (MalformedURLException ex) {
                    if (!string.substring(1, 8).equalsIgnoreCase("http://")) {
                        string = "http://" + string;
                        try {
                            an.a(new URL(string));
                        }
                        catch (MalformedURLException ex2) {}
                    }
                }
            }
            return true;
        }
        final z z2 = new z(s);
        z2.setFont(m.d);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(z2, gridBagConstraints);
        panel.add(z2);
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
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
                    this.e.o(this.d.b);
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
    
    public bd(final Frame frame, final g e, final User d, final t t, final int n) {
        super(frame, false);
        this.a = new o(70, 20);
        this.b = new o(115, 20);
        this.setBackground(e.df.outerBackground);
        this.d = d;
        this.e = e;
        final a9 a9 = new a9();
        a9.setBackground(e.df.tabsBackground);
        a9.setForeground(e.df.tabsText);
        final ay ay = new ay();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String c = e.c(d.getName());
        final String c2 = e.c(t.c(n, 0));
        final String c3 = e.c(t.c(n, 1));
        final String c4 = e.c(t.c(n, 2));
        final String c5 = e.c(t.c(n, 4));
        final String c6 = e.c(t.c(n, 3));
        String value = null;
        String s = null;
        String e2 = null;
        String f = null;
        String c7 = null;
        final boolean b = false;
        final a6 a10 = (a6)e.al.d(d.b);
        if (a10 != null) {
            c7 = e.c(a10.getName());
        }
        final int a11 = t.a(n, 1);
        if (a11 > 0) {
            value = String.valueOf(a11);
        }
        if (t.e(n, 1)) {
            s = LanguageSupport.translate("Male");
        }
        else if (t.e(n, 0)) {
            s = LanguageSupport.translate("Female");
        }
        if (e.u(41)) {
            e2 = d.e;
            f = d.f;
            if (f != null && f.equals(e2)) {
                f = null;
            }
        }
        final a1 a12 = (a1)e.ai.d(d.a);
        this.setResizable(true);
        this.setTitle(StringSubst.Substitute(LanguageSupport.translate("Profile of %1"), new String[] { c }));
        this.setLayout(gridBagLayout);
        a9.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (a12 != null) {
            final aa aa = new aa();
            aa.b(a12.a);
            gridBagLayout.setConstraints(aa, gridBagConstraints);
            a9.add(aa);
        }
        gridBagConstraints.gridwidth = 0;
        final z z = new z(c);
        z.setFont(m.a);
        gridBagLayout.setConstraints(z, gridBagConstraints);
        a9.add(z);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(ay, gridBagConstraints);
        a9.add(ay);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        boolean b2 = b | this.a(c2, LanguageSupport.translate("Real Name"), false, null, gridBagConstraints, gridBagLayout, a9) | this.a(value, LanguageSupport.translate("Age"), false, null, gridBagConstraints, gridBagLayout, a9) | this.a(s, LanguageSupport.translate("Gender"), false, null, gridBagConstraints, gridBagLayout, a9);
        if (c6 != null) {
            String s2 = c6.toLowerCase();
            if (!s2.startsWith("mailto:")) {
                s2 = "mailto:" + s2;
            }
            b2 |= this.a(c6, LanguageSupport.translate("E-mail"), true, s2, gridBagConstraints, gridBagLayout, a9);
        }
        boolean b3 = b2 | this.a((c5 == null) ? c4 : (c5.equals("") ? c4 : c5), LanguageSupport.translate("URL"), true, c4, gridBagConstraints, gridBagLayout, a9) | this.a(c3, LanguageSupport.translate("Comments"), true, null, gridBagConstraints, gridBagLayout, a9) | this.a(c7, LanguageSupport.translate("Room"), true, "file:room", gridBagConstraints, gridBagLayout, a9) | this.a(e2, LanguageSupport.translate("IP Address"), false, null, gridBagConstraints, gridBagLayout, a9) | this.a(f, LanguageSupport.translate("Host Name"), false, null, gridBagConstraints, gridBagLayout, a9);
        if (t.d() != null) {
            try {
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(t.d());
                final Properties properties = new Properties();
                properties.load(byteArrayInputStream);
                for (int n2 = 1; properties.getProperty("CustomParam" + n2) != null && properties.getProperty("CustomValue" + n2) != null; ++n2) {
                    b3 |= this.a(e.c(properties.getProperty("CustomValue" + n2)), e.c(properties.getProperty("CustomParam" + n2)), true, null, gridBagConstraints, gridBagLayout, a9);
                }
            }
            catch (Exception ex) {}
        }
        if (!b3) {
            final Component a13 = q.a(LanguageSupport.translate("No profile is available."));
            a13.setFont(m.d);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(a13, gridBagConstraints);
            a9.add(a13);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(a9, gridBagConstraints);
        this.add(a9);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (e.u(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(LanguageSupport.translate("Send Message"));
            this.b.resize(this.b.getFontMetrics(this.b.getFont()).stringWidth(LanguageSupport.translate("Send Message")) + 20, 20);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.a.a(LanguageSupport.translate("OK"));
        this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(LanguageSupport.translate("OK")) + 20, 20);
        final s s3 = new s(this.a);
        gridBagLayout.setConstraints(s3, gridBagConstraints);
        this.add(s3);
        this.pack();
        this.setVisible(true);
    }
}
