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

public final class e extends Canvas
{
    f a;
    Image b;
    Image c;
    Image d;
    int e;
    String f;
    boolean g;
    boolean h;
    boolean i;
    int j;
    private h k;
    
    e(final String f, final int e, final Image c, final Image image, final Image image2, final boolean i, final f a, final h k) {
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = 4;
        this.f = f;
        this.e = e;
        this.c = c;
        this.b = image;
        this.d = image;
        this.i = i;
        this.a = a;
        this.k = k;
        this.enableEvents(48L);
    }
    
    public final void a() {
        this.a = null;
        this.k = null;
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.b, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                if ((mouseEvent.getModifiers() & 0x10) == 0x10 && !this.h) {
                    if (!this.i) {
                        this.g = true;
                        this.b = this.c;
                        this.repaint();
                        this.a.a(this.e, 0);
                    }
                    else if (this.j == 4) {
                        this.j = 3;
                        this.b = this.c;
                        this.repaint();
                        this.a.a(this.e, 3);
                    }
                    else {
                        this.j = 4;
                        this.b = this.d;
                        this.repaint();
                        this.a.a(this.e, 4);
                    }
                }
                this.k.getParent().requestFocus();
                this.k.requestFocus();
                break;
            }
            case 502: {
                if ((mouseEvent.getModifiers() & 0x10) == 0x10 && this.g && !this.h && !this.i) {
                    this.g = false;
                    this.b = this.d;
                    this.repaint();
                    this.a.a(this.e, 2);
                }
                this.k.getParent().requestFocus();
                this.k.requestFocus();
                break;
            }
            case 504: {
                com.iseemedia.apps.tourclients40.players.h.J = false;
                this.k.v().setCursor(Cursor.getPredefinedCursor(0));
                this.a.a(this.f);
                break;
            }
            case 505: {
                this.a.a("");
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public final void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                this.a.a(this.f);
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
}
