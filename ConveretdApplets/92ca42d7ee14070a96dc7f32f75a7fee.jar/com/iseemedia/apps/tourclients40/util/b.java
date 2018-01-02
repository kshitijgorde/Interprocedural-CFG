// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.util;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import com.iseemedia.apps.tourclients40.players.h;
import com.iseemedia.apps.tourclients40.toolbar.a;
import java.awt.Image;
import java.applet.AppletContext;
import java.awt.Canvas;

public final class b extends Canvas implements Runnable
{
    AppletContext a;
    boolean b;
    Thread c;
    Image d;
    Image e;
    Image f;
    private int g;
    private int h;
    private int i;
    private int j;
    private a k;
    private h l;
    
    public b(final h l, final AppletContext a, final boolean b, final a k) {
        this.k = k;
        this.b = b;
        this.a = a;
        this.d = com.iseemedia.apps.tourclients40.toolbar.a.a("max_zoom");
        this.e = com.iseemedia.apps.tourclients40.toolbar.a.a("sand_watch");
        this.f = this.e;
        this.l = l;
        this.g = this.l.size().width;
        this.i = this.l.size().height;
        this.h = this.k.b;
        this.j = this.k.c;
        this.l.add(this);
        this.reshape(this.g + 100, this.i + 100, this.h, this.j);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, this.getBackground(), null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void a() {
        if (com.iseemedia.apps.tourclients40.players.h.l == 1) {
            this.f = this.e;
            this.move(this.g - this.h, this.i - this.j);
            this.a("Submit Request.");
        }
    }
    
    public final void b() {
        this.move(this.g + 100, this.i + 100);
        this.a("Document Done.");
    }
    
    public final boolean c() {
        if (com.iseemedia.apps.tourclients40.players.h.l != 1) {
            return false;
        }
        if (!this.b) {
            return false;
        }
        if (this.c == null || !this.c.isAlive()) {
            (this.c = new Thread(this)).start();
        }
        return true;
    }
    
    public final void run() {
        this.f = this.d;
        this.move(this.g - this.h, this.i - this.j);
        this.a("Maximum zoom in!");
        try {
            Thread.sleep(1000L);
        }
        catch (Exception ex) {}
        this.move(this.g + 100, this.i + 100);
        this.a("");
        this.f = this.e;
    }
    
    private void a(final String s) {
        if (this.a != null) {
            this.a.showStatus(s);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        return (event.id == 504 || event.id == 503 || event.id == 505) && super.handleEvent(event);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        com.iseemedia.apps.tourclients40.players.h.J = false;
        this.l.v().setCursor(Cursor.getPredefinedCursor(0));
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        this.l.v().setCursor(Cursor.getPredefinedCursor(0));
        return true;
    }
}
