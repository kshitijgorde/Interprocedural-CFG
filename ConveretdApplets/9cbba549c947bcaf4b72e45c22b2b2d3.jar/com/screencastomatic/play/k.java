// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.Iterator;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class k implements MouseListener, MouseMotionListener
{
    Map a;
    Map b;
    t c;
    a d;
    a e;
    private boolean f;
    private long g;
    private Thread h;
    
    public k() {
        this.a = new HashMap();
        this.b = new HashMap();
        (this.h = new Thread(new d(this))).start();
    }
    
    public void a() {
        this.h.interrupt();
    }
    
    public boolean b() {
        return this.f;
    }
    
    public void a(final Rectangle rectangle, final a a) {
        this.a.put(rectangle, a);
    }
    
    public void a(final Rectangle rectangle, final l l) {
        this.a.put(rectangle, l);
    }
    
    public void a(final Rectangle rectangle) {
        this.a.remove(rectangle);
    }
    
    public void a(final a d) {
        this.d = d;
    }
    
    public void a(final Rectangle rectangle, final e e) {
        this.b.put(rectangle, new t(e));
    }
    
    public void b(final Rectangle rectangle) {
        this.b.remove(rectangle);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        final t c = (t)this.a(this.b, point);
        if (c != null) {
            c.a = point;
            c.b.a(point);
            this.c = c;
            return;
        }
        final a e = (a)this.a(this.a, point);
        this.e = e;
        if (e != null && e instanceof l) {
            ((l)e).b();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.c != null) {
            this.c.b.c(point);
            this.c = null;
            return;
        }
        final a a = (a)this.a(this.a, point);
        if (a == this.e) {
            a.a();
        }
        if (this.e != null && this.e instanceof l) {
            ((l)this.e).c();
        }
        this.e = null;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.f = true;
        this.d.a();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.f = false;
        this.d.a();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.c != null) {
            this.c.b.b(point);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!this.f) {
            this.mouseEntered(mouseEvent);
        }
        this.g = System.currentTimeMillis();
    }
    
    private Object a(final Map map, final Point point) {
        Object o = null;
        for (final Map.Entry<Rectangle, V> entry : map.entrySet()) {
            final Rectangle rectangle = entry.getKey();
            if (rectangle.contains(point) && (o == null || ((Map.Entry<Rectangle, V>)o).getKey().contains(rectangle))) {
                o = entry;
            }
        }
        return (o != null) ? ((Map.Entry)o).getValue() : null;
    }
}
