// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.common.bg;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;
import java.awt.Insets;
import com.diginet.digichat.awt.ae;

public class b4 extends ae
{
    private static int a;
    private bh b;
    
    public final Insets insets() {
        if (this.b.h.df.borderImgs != null) {
            final Insets insets = super.insets();
            return new Insets(insets.top - 3, insets.left - 3, insets.bottom - 3, insets.right - 3);
        }
        return super.insets();
    }
    
    public final Container a() {
        return this.b;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.target instanceof MenuItem) {
            return this.b.handleEvent(event);
        }
        if (event.id == 201) {
            this.b.h.h();
        }
        if (event.id == 1004 && event.target == this.b && this.b.i != null) {
            this.b.i.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public final void a(final bf bf) {
        String s = new String(DigiChatAppletAbstract.OEM_DigiChat) + ": ";
        final bg bg = (bg)this.b.h.ak.d(bf.e);
        if (bg != null && this.b.h.c2) {
            s = s + bg.getName() + ": ";
        }
        this.setTitle(s + bf.getName() + ": " + this.b.h.getName());
    }
    
    public b4(final g g) {
        if (g.da) {
            g.t = true;
            g.bp = true;
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        ++b4.a;
        this.b = new bh(g, this, g.da);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width > 800) {
            screenSize.width = 800;
        }
        if (screenSize.height > 600) {
            screenSize.height = 600;
        }
        final Dimension dimension = screenSize;
        dimension.height -= 80;
        final Dimension dimension2 = screenSize;
        dimension2.width -= 80;
        if (DigiChatAppletAbstract.initialWindowHeight > 0) {
            screenSize.height = DigiChatAppletAbstract.initialWindowHeight;
        }
        if (DigiChatAppletAbstract.initialWindowWidth > 0) {
            screenSize.width = DigiChatAppletAbstract.initialWindowWidth;
        }
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + b4.a * 20, bounds.y + b4.a * 20);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        if (g.da) {
            this.resize(Math.min(g.ak, Toolkit.getDefaultToolkit().getScreenSize().width - 40), Math.min(g.al, Toolkit.getDefaultToolkit().getScreenSize().height - 40));
        }
        else {
            this.resize(screenSize.width, screenSize.height);
        }
        this.h();
        this.show();
        if (g.da) {
            this.b.d(g.ai);
        }
        this.b.setVisible(true);
    }
}
