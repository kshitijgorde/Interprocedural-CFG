// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public final class c extends Canvas
{
    g byte;
    Image if;
    Image case;
    Image else;
    Image for;
    int int;
    String a;
    boolean do;
    boolean char;
    boolean try;
    int new;
    
    c(final String a, final int int1, final Image case1, final Image image, final Image for1, final boolean try1, final g byte1) {
        this.do = false;
        this.char = false;
        this.try = false;
        this.new = 4;
        this.a = a;
        this.int = int1;
        this.case = case1;
        this.if = image;
        this.else = image;
        this.for = for1;
        this.try = try1;
        this.byte = byte1;
    }
    
    public void if() {
        this.byte = null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.if, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 502 || event.id == 501 || event.id == 505 || event.id == 504) {
            return super.handleEvent(event);
        }
        return event.id != 403 && event.id != 401;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.byte.a(this.a);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.byte.a(this.a);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.byte.a("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.byte.a(3, 0);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.do = false;
        this.if = this.else;
        this.repaint();
        this.byte.for();
        this.byte.a(0, 2);
        return true;
    }
    
    public void a(final int n) {
        if (!this.try || this.char) {
            return;
        }
        if (n == 3) {
            this.new = 3;
            this.if = this.case;
            this.repaint();
        }
        else {
            this.new = 4;
            this.if = this.else;
            this.repaint();
        }
    }
    
    public void a() {
        if (this.for != null) {
            this.char = true;
            this.if = this.for;
            this.repaint();
        }
    }
    
    public void do() {
        if (this.char) {
            this.char = false;
            this.if = this.else;
            this.repaint();
        }
    }
}
