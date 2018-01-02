// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import com.diginet.digichat.util.Finder;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public class InternalFrame extends Panel implements s
{
    private String strInfo;
    private Rectangle recFrame;
    private Rectangle recComp;
    
    public InternalFrame(final String strInfo) {
        this.setLayout(null);
        this.strInfo = strInfo;
        this.recComp = new Rectangle();
        this.recFrame = new Rectangle();
    }
    
    public InternalFrame() {
        this((String)null);
    }
    
    public Component add(final Component component) {
        this.removeAll();
        super.add(component);
        final Dimension preferredSize = component.preferredSize();
        this.setSize(preferredSize.width + 13, preferredSize.height + 13);
        component.setLocation(7, 7);
        this.recComp.setBounds(component.getBounds());
        this.recFrame.setBounds(3, 3, preferredSize.width + 8, preferredSize.height + 8);
        return component;
    }
    
    public String a(final Object o) {
        return this.strInfo;
    }
    
    public boolean handleEvent(final Event event) {
        Container parent = this;
        while (parent != null) {
            if ((parent = parent.getParent()) instanceof DragContainer) {
                switch (event.id) {
                    case 503:
                    case 504:
                    case 505: {
                        final Object[] component = Finder.findComponent(event, this);
                        ((DragContainer)parent).setInfo((Component)component[0], (String)component[1]);
                        break;
                    }
                    case 501: {
                        if (this.recFrame.contains(event.x, event.y) && !this.recComp.contains(event.x, event.y)) {
                            ((DragContainer)parent).dragLayer(this, event.x, event.y);
                            return true;
                        }
                        break;
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.width - 1;
        final int n2 = size.height - 1;
        graphics.setColor(Color.black);
        graphics.drawLine(3, 0, n - 2, 0);
        graphics.drawLine(n - 1, 1, n, 2);
        graphics.drawLine(n, 3, n, n2 - 2);
        graphics.drawLine(n - 1, n2 - 1, n - 2, n2);
        graphics.drawLine(n - 3, n2, 2, n2);
        graphics.drawLine(1, n2 - 1, 0, n2 - 2);
        graphics.drawLine(0, n2 - 3, 0, 2);
        graphics.drawLine(1, 1, 2, 0);
        final Color background;
        graphics.setColor(background = this.getBackground());
        graphics.drawLine(1, n2 - 2, 1, 2);
        graphics.drawLine(2, 1, n - 2, 1);
        graphics.setColor(background.brighter());
        graphics.drawLine(2, n2 - 3, 2, 2);
        graphics.drawLine(3, 2, n - 2, 2);
        final Color darker;
        graphics.setColor((darker = background.darker()).darker());
        graphics.drawLine(2, n2 - 1, n - 2, n2 - 1);
        graphics.drawLine(n - 1, n2 - 2, n - 1, 2);
        graphics.setColor(darker);
        graphics.drawLine(2, n2 - 2, n - 2, n2 - 2);
        graphics.drawLine(n - 2, n2 - 3, n - 2, 3);
    }
}
