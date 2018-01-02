// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.liteclient;

import java.awt.Rectangle;
import java.awt.Component;
import com.diginet.digichat.client.h;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.client.bf;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Frame;

public class c9 extends Frame
{
    private static int a;
    public c5 b;
    
    public final void a() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        int n = (screenSize.height - size.height) / 2;
        if (n > 100) {
            n = 100;
        }
        this.move((screenSize.width - size.width) / 2, n);
    }
    
    public final Container b() {
        return this.b;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.target instanceof MenuItem) {
            return this.b.handleEvent(event);
        }
        if (event.id == 201) {
            this.b.c.h();
        }
        if (event.id == 1004) {
            this.b.d.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public final void a(final bf bf) {
        this.setTitle(DigiChatAppletAbstract.OEM_DigiChat + ": " + bf.getName() + ": " + this.b.c.getName());
    }
    
    public c9(final h h) {
        ++c9.a;
        this.b = new c5(h, this);
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
        this.a();
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + c9.a * 20, bounds.y + c9.a * 20);
        this.add(this.b);
        this.show();
    }
}
