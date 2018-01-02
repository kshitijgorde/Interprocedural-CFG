// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;
import com.diginet.digichat.awt.aa;

public class bs extends aa
{
    private static int a;
    private be b;
    
    public Container a() {
        return this.b;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof MenuItem) {
            return this.b.handleEvent(event);
        }
        if (event.id == 201) {
            this.b.g.o();
        }
        if (event.id == 1004 && event.target == this.b) {
            this.b.h.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public void a(final bc bc) {
        this.setTitle(DigiChatAppletAbstract.OEM_DigiChat + ": " + bc.r() + ": " + this.b.g.r());
    }
    
    public bs(final h h) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        ++bs.a;
        this.b = new be(h, this);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width > 800) {
            screenSize.width = 800;
        }
        if (screenSize.height > 600) {
            screenSize.height = 600;
        }
        final Dimension dimension = screenSize;
        dimension.width -= 80;
        final Dimension dimension2 = screenSize;
        dimension2.height -= 80;
        if (DigiChatAppletAbstract.initialWindowHeight > 0) {
            screenSize.height = DigiChatAppletAbstract.initialWindowHeight;
        }
        if (DigiChatAppletAbstract.initialWindowWidth > 0) {
            screenSize.width = DigiChatAppletAbstract.initialWindowWidth;
        }
        this.resize(screenSize.width, screenSize.height);
        this.d();
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + bs.a * 20, bounds.y + bs.a * 20);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.b, gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(this.b);
        this.show();
        this.b.setVisible(true);
    }
}
