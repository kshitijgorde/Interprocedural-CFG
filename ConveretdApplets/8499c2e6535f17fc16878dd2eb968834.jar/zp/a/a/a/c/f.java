// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import zp.a.a.a.b.a;
import java.awt.Canvas;

public class f extends Canvas
{
    private static final String if = "logo";
    a try;
    Image new;
    Image int;
    boolean a;
    public int do;
    public int for;
    
    f(final a try1) {
        this.int = null;
        this.a = false;
        this.do = 250;
        this.for = 24;
        this.try = try1;
        this.new = e.a("logo");
    }
    
    public void a() {
        this.try = null;
        this.new = null;
        this.int = null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.new, 0, 0, this.do, this.for, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 502 || event.id == 504 || event.id == 503) {
            return super.handleEvent(event);
        }
        if (event.id != 403 && event.id != 401) {
            return true;
        }
        this.try.requestFocus();
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (n < 100 || (n > 110 && n < 160)) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n < 100 || (n > 110 && n < 160)) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.try.a(n);
        return true;
    }
    
    public boolean if() {
        return this.a;
    }
}
