// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.toolbar;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.AppletContext;
import java.awt.Image;
import com.iseemedia.apps.tourclients40.players.h;
import java.awt.Canvas;

public final class b extends Canvas
{
    h a;
    Image b;
    boolean c;
    public int d;
    public int e;
    private AppletContext f;
    
    b(final h a, final a a2, final AppletContext f) {
        this.c = false;
        this.a = a;
        this.b = com.iseemedia.apps.tourclients40.toolbar.a.a("logo");
        this.d = a2.a;
        this.e = a2.c;
        this.f = f;
        this.enableEvents(48L);
    }
    
    public final void a() {
        this.a = null;
        this.b = null;
        this.f = null;
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.b, 0, 0, this.d, this.e, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean b() {
        return this.c;
    }
    
    public final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.a.getParent().requestFocus();
                this.a.requestFocus();
                break;
            }
            case 502: {
                if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                    this.a.t();
                }
                this.a.getParent().requestFocus();
                this.a.requestFocus();
                break;
            }
            case 504: {
                h.J = false;
                this.a.v().setCursor(Cursor.getPredefinedCursor(0));
                this.f.showStatus("Information");
                break;
            }
            case 505: {
                this.f.showStatus("");
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public final void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                this.f.showStatus("Information");
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
}
