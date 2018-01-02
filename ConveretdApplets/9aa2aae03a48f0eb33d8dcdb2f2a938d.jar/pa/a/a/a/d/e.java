// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class e extends Canvas
{
    private static final String new = "fill";
    Image do;
    Image if;
    boolean int;
    public int a;
    public int for;
    public int try;
    public int width;
    
    e(final int width) {
        this.if = null;
        this.int = false;
        this.a = 50;
        this.for = 24;
        this.try = 2;
        this.width = 0;
        this.do = b.a("fill");
        this.width = width;
    }
    
    public void a() {
        this.do = null;
        this.if = null;
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
    
    public void paint(final Graphics graphics) {
        if (this.do != null) {
            for (int i = 0; i < this.width; i += this.try) {
                graphics.drawImage(this.do, i, 0, this.try, this.for, this.getBackground(), this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
