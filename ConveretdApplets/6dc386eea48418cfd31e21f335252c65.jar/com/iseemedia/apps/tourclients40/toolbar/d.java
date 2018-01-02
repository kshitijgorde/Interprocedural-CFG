// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.toolbar;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.iseemedia.apps.tourclients40.players.h;
import java.awt.Image;
import java.awt.Canvas;

public final class d extends Canvas
{
    Image a;
    public int b;
    public int c;
    public int d;
    private h e;
    
    d(final int d, final a a, final h e) {
        this.c = 2;
        this.d = 0;
        this.e = e;
        this.b = a.c;
        this.a = com.iseemedia.apps.tourclients40.toolbar.a.a("fill");
        this.d = d;
        this.enableEvents(16L);
    }
    
    public final void a() {
        this.a = null;
        this.e = null;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.a != null) {
            for (int i = 0; i < this.d; i += this.c) {
                graphics.drawImage(this.a, i, 0, this.c, this.b, this.getBackground(), this);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.e.getParent().requestFocus();
                this.e.requestFocus();
                break;
            }
            case 502: {
                this.e.getParent().requestFocus();
                this.e.requestFocus();
                break;
            }
            case 504: {
                h.J = false;
                this.e.v().setCursor(Cursor.getPredefinedCursor(0));
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
}
