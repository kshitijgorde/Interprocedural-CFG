// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import com.diginet.digichat.network.t;
import java.awt.FontMetrics;
import com.diginet.digichat.awt.m;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Canvas;
import java.awt.Toolkit;
import java.net.URL;
import com.diginet.ui.z;
import java.awt.Image;
import com.diginet.ui.ac;
import com.diginet.digichat.awt.aa;
import java.awt.Panel;

public class y extends Panel
{
    private aa a;
    private ac b;
    private ac c;
    private Image d;
    private Image e;
    private Image f;
    private Image g;
    private Image h;
    private h i;
    private boolean j;
    private z k;
    private String l;
    
    private final Image a(final String s) {
        try {
            final URL url = new URL(this.i.ba, "Resources/" + this.i.y + "/" + s);
            if (url == null) {
                return null;
            }
            final Image image = Toolkit.getDefaultToolkit().getImage(url);
            final MediaTracker mediaTracker = new MediaTracker(new Canvas());
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            return image;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final void a(final z z) {
        final FontMetrics fontMetrics = z.getFontMetrics(m.d);
        final int n = (int)(Object)new Double(this.getMinimumSize().width) - ((this.c != null) ? ((int)(Object)new Double(this.c.getSize().width)) : 0) - (int)(Object)new Double(this.b.getSize().width) - fontMetrics.stringWidth("..") - 4;
        if (this.l == null || this.l.length() == 0) {
            this.l = this.i.getName();
        }
        String l = this.l;
        if (fontMetrics.stringWidth(l) > n) {
            for (int i = 0; i < this.l.length(); ++i) {
                if (fontMetrics.stringWidth(l) > n) {
                    l = l.substring(0, l.length() - 1);
                    if (fontMetrics.stringWidth(l) <= n) {
                        l += "..";
                    }
                }
            }
            z.a(this.l = l);
        }
        else {
            z.a(this.l);
        }
    }
    
    public final void a() {
        this.i.a(5, !this.b.a());
        final t t = new t(67334, 1);
        t.m = -1;
        t.l = -1;
        t.n = this.i.x();
        t.a(0, 0, this.i.x());
        t.a(0, 1, this.i.cc);
        t.a(0, 0, this.i.getName());
        t.a(0, this.i.z());
        this.i.ap(t);
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target == this.b) {
            this.a();
            return true;
        }
        return super.action(event, o);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.b) {
                    this.a();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public y(final h h) {
        this(h, true);
    }
    
    public y(final h i, final boolean j) {
        this.i = i;
        this.l = i.getName();
        i.bd = new MediaTracker(this);
        this.j = j;
        this.k = new z((this.l == null) ? i.getName() : this.l);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.k.setFont(m.d);
        this.h = this.a("chatLogo.gif");
        (this.a = new aa()).b(this.h);
        this.d = this.a("messagesAvailableImage.gif");
        this.e = this.a("messagesAwayImage.gif");
        this.b = ac.b(this.d, this.e, null);
        if (i instanceof g && ((g)i).k && i.j) {
            this.f = this.a("setupAvImage_up.gif");
            this.g = this.a("setupAvImage_dn.gif");
            this.c = ac.a(this.f, this.g, null);
        }
        gridBagConstraints.anchor = 16;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a(this.k);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.k, gridBagConstraints);
        this.add(this.k);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.0;
        if (i instanceof g && ((g)i).k && i.j) {
            this.c.setBackground(Color.RED);
            layout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.0;
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        if (this.isValid()) {
            this.invalidate();
        }
        else {
            this.validate();
        }
        if (!this.isValid()) {
            this.validate();
        }
    }
}
