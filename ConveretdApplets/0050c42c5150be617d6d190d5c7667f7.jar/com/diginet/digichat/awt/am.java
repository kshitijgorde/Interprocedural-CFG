// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.FontMetrics;
import java.awt.Panel;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.client.h;
import java.awt.Frame;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import com.diginet.digichat.common.Theme;
import com.diginet.digichat.util.aj;

public class am extends ShadedDialog
{
    private aj a;
    private boolean b;
    private o c;
    private String d;
    private Theme e;
    
    private final void a(final Theme e) {
        this.e = e;
    }
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.a();
        }
        super.setVisible(visible);
    }
    
    public final void a(final boolean b) {
        this.b = b;
    }
    
    public final String b() {
        return this.d;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (!this.b) {
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, null);
                    }
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof o) {
                    final String d = ((o)event.target).d();
                    this.d = d;
                    if (!this.b) {
                        this.dispose();
                    }
                    if (this.a != null) {
                        this.a.a(this, d);
                    }
                    return true;
                }
                break;
            }
            case 401: {
                if (event.key == 10 || event.key == c3.a) {
                    this.c.e();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void c() {
        if (this.c == null) {
            return;
        }
        this.c.e();
    }
    
    public am(final Frame frame, final String s, final Throwable t, final h h) {
        this(frame, LanguageSupport.translate("Error"), new String[] { LanguageSupport.translate("OK") }, new String[] { s, t.toString() }, null, null, h);
    }
    
    public am(final Frame frame, final String s, final String s2, final h h) {
        this(frame, s, new String[] { LanguageSupport.translate("OK") }, new String[] { s2 }, null, null, h);
    }
    
    public am(final Frame frame, final String s, final String[] array, final h h) {
        this(frame, s, new String[] { LanguageSupport.translate("OK") }, array, null, null, h);
    }
    
    public am(final Frame frame, final String s, final String[] array, final String[] array2, final Theme theme) {
        this(frame, s, array, array2, null, null, null);
        this.a(theme);
    }
    
    public am(final Frame frame, final String s, final String[] array, final String[] array2, final aj aj, final h h) {
        this(frame, s, array, array2, null, aj, h);
    }
    
    public am(final Frame frame, final String s, final String[] array, final String[] array2, final String[] array3, final aj a, final h h) {
        super(frame, s, true);
        this.b = false;
        if (h == null && this.e == null) {
            this.a(Theme.defaultTheme);
        }
        else {
            this.a(h.df);
        }
        this.setBackground(this.e.tabsBackground);
        this.setForeground(this.e.tabsText);
        this.a = a;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        for (int i = 0; i < array2.length; ++i) {
            int n = 300;
            an an;
            if (array3 == null || array3.length != 2) {
                an = new an(array2[i]);
            }
            else if (array2[i].equalsIgnoreCase(array3[0])) {
                try {
                    an = new an(array3[0], new URL(array3[1]));
                }
                catch (MalformedURLException ex) {
                    an = new an(array2[i]);
                }
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.weightx = 0.0;
                n = this.getFontMetrics(m.a).stringWidth(array2[i]) + 5;
                an.a(false);
                an.a(2);
            }
            else if (array2[i - 1].equalsIgnoreCase(array3[0])) {
                gridBagConstraints.gridwidth = 0;
                gridBagConstraints.weightx = 1.0;
                an = new an(array2[i]);
            }
            else {
                an = new an(array2[i]);
            }
            if (array3 != null && (i == 0 || (i == 1 && array2[0].equalsIgnoreCase(array3[0])))) {
                an.setFont(m.a);
            }
            else {
                an.setFont(m.c);
            }
            an.resize(n, 20);
            gridBagLayout.setConstraints(an, gridBagConstraints);
            this.add(an);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(m.b);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        final Panel panel = new Panel();
        panel.setLayout(gridBagLayout);
        for (int j = array.length - 1; j >= 0; --j) {
            int n2 = fontMetrics.stringWidth(array[j]) + 20;
            if (n2 < 60) {
                n2 = 60;
            }
            final o c = new o(array[j], n2, 20);
            if (j == 0) {
                this.c = c;
                final s s2 = new s(c);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(s2, gridBagConstraints);
                panel.add(s2);
            }
            else {
                gridBagLayout.setConstraints(c, gridBagConstraints);
                panel.add(c);
            }
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
}
