// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.diginet.digichat.awt.LayeredContainer;
import java.awt.Event;
import java.awt.Container;
import com.diginet.digichat.awt.z;

public class b0 extends z
{
    private static int a;
    private bq b;
    
    public Container a() {
        return this.b;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.b.g.u();
        }
        if (event.id == 1004 && event.target == this.b) {
            this.b.txpMess.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public void a(final bo bo) {
        this.setTitle(String.valueOf(String.valueOf(String.valueOf(String.valueOf(DigiChatAppletAbstract.OEM_DigiChat).concat(String.valueOf(": "))).concat(String.valueOf(bo.x()))).concat(String.valueOf(": "))).concat(String.valueOf(this.b.g.x())));
    }
    
    public b0(final h h) {
        ++b0.a;
        this.b = new bq(h, this);
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
        this.b();
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + b0.a * 20, bounds.y + b0.a * 20);
        this.show();
    }
}
