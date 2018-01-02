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

public final class c extends Canvas
{
    f a;
    Image[] b;
    Image[] c;
    String[] d;
    String e;
    int f;
    int g;
    private a h;
    private h i;
    
    c(final int f, final String[] d, final Image[] b, final Image[] c, final f a, final a h, final h i) {
        this.g = 0;
        this.h = h;
        this.f = f;
        this.d = d;
        this.b = b;
        this.c = c;
        this.a = a;
        this.i = i;
        this.enableEvents(48L);
    }
    
    public final void a() {
        this.a = null;
        this.i = null;
    }
    
    public final void paint(final Graphics graphics) {
        for (int i = 0; i < this.f; ++i) {
            if (i == this.g) {
                graphics.drawImage(this.b[i], i * this.h.b, 0, this);
            }
            else {
                graphics.drawImage(this.c[i], i * this.h.b, 0, this);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void a(final int g) {
        this.g = g;
        this.repaint();
    }
    
    private int b(final int n) {
        return (int)Math.ceil(n / this.h.b);
    }
    
    public final void b() {
        if (this.g != 0) {
            this.g = 0;
            this.repaint();
        }
        this.a.b(this.g);
    }
    
    public final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                    final int b;
                    if ((b = this.b(mouseEvent.getX())) <= this.f - 1) {
                        this.g = b;
                        this.repaint();
                    }
                    this.a.b(this.g);
                }
                this.i.getParent().requestFocus();
                this.i.requestFocus();
                break;
            }
            case 502: {
                this.i.getParent().requestFocus();
                this.i.requestFocus();
                break;
            }
            case 504: {
                com.iseemedia.apps.tourclients40.players.h.J = false;
                this.i.v().setCursor(Cursor.getPredefinedCursor(0));
                final int b2;
                if ((b2 = this.b(mouseEvent.getX())) <= this.f - 1) {
                    this.e = this.d[b2];
                    this.a.a(this.e);
                    break;
                }
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
                final int b;
                if ((b = this.b(mouseEvent.getX())) <= this.f - 1 && this.d[b] != this.e) {
                    this.e = this.d[b];
                    this.a.a(this.e);
                    break;
                }
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
}
