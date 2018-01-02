// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class d extends Canvas
{
    private static final String do = "fill";
    Image try;
    Image new;
    boolean if;
    public int for;
    public int int;
    public int a;
    public int width;
    
    d(final int width) {
        this.new = null;
        this.if = false;
        this.for = 250;
        this.int = 24;
        this.a = 2;
        this.width = 0;
        this.try = e.a("fill");
        this.width = width;
    }
    
    public void a() {
        this.try = null;
        this.new = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.try != null) {
            for (int i = 0; i < this.width; i += this.a) {
                graphics.drawImage(this.try, i, 0, this.a, this.int, this.getBackground(), this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 502) {
            return super.handleEvent(event);
        }
        return event.id != 403 && event.id != 401;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return true;
    }
}
