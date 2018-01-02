// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.Font;
import com.diginet.digichat.network.t;
import java.awt.Event;
import com.diginet.digichat.awt.ba;
import com.diginet.digichat.awt.aa;
import java.awt.Panel;

public class bx extends Panel
{
    private g a;
    private aa b;
    private aa c;
    private by d;
    private by e;
    private ba f;
    
    public final boolean handleEvent(final Event event) {
        if (event.target == this.f && event.id == 1001) {
            this.e.a(this.f.a() ? "Unavailable" : "Available");
            this.a.a(5, !this.f.a());
            final t t = new t(67334, 1);
            t.m = -1;
            t.l = -1;
            t.a(0, 0, this.a.x());
            t.a(0, 1, this.a.cc);
            t.a(0, 0, this.a.getName());
            t.a(0, this.a.z());
            this.a.ap(t);
        }
        return super.handleEvent(event);
    }
    
    public final void a(final User2 user2) {
        this.d.a(user2.getName());
        this.e.a(user2.u(5) ? "Available" : "Unavailable");
        if (user2.a != null) {
            this.b.b(user2.a.a);
        }
        this.c.b(user2.u(6) ? user2.c : (user2.u(65) ? user2.b : (user2.u(64) ? user2.d : null)));
        if ((this.f.a() && user2.u(5)) || (!this.f.a() && !user2.u(5))) {
            this.f.b();
        }
    }
    
    public bx(final g a, final Font font) {
        this.a = a;
        this.b = new aa();
        this.c = new aa();
        (this.f = ba.b(a.df.available, a.df.unavailable, null)).a("Click here to switch your availability", null);
        if (this == null) {
            throw null;
        }
        this.d = new Canvas(font) {
            private String a;
            
            public final void a(final String a) {
                this.a = a;
                final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
                this.resize(fontMetrics.stringWidth(a), fontMetrics.getHeight());
                this.repaint();
            }
            
            public final void paint(final Graphics graphics) {
                graphics.drawString(this.a, 0, graphics.getFontMetrics().getAscent());
            }
            
            {
                this.setFont(font);
            }
        };
        if (this == null) {
            throw null;
        }
        this.e = new Canvas(font) {
            private String a;
            
            public final void a(final String a) {
                this.a = a;
                final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
                this.resize(fontMetrics.stringWidth(a), fontMetrics.getHeight());
                this.repaint();
            }
            
            public final void paint(final Graphics graphics) {
                graphics.drawString(this.a, 0, graphics.getFontMetrics().getAscent());
            }
            
            {
                this.setFont(font);
            }
        };
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(4, 4, 0, 0);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        final Panel panel = new Panel();
        panel.setLayout(gridBagLayout);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 6);
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        panel.add(this.e);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 12, 0, 10);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
    }
}
