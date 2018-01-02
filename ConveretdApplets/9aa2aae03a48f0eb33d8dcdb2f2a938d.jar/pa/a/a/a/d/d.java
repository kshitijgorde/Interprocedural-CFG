// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import pa.a.a.a.a.a;
import java.awt.Canvas;

public class d extends Canvas
{
    private static final String new = "logo";
    a try;
    Image do;
    Image if;
    boolean int;
    public int a;
    public int for;
    
    d(final a try1) {
        this.if = null;
        this.int = false;
        this.a = 50;
        this.for = 24;
        this.try = try1;
        this.do = b.a("logo");
    }
    
    public boolean a() {
        return this.int;
    }
    
    public void if() {
        this.try = null;
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
        this.try.goto();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.do, 0, 0, this.a, this.for, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
