// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public final class f extends Canvas
{
    g try;
    Image char;
    Image if;
    Image case;
    Image byte;
    int a;
    String else;
    boolean int;
    boolean for;
    boolean do;
    int new;
    
    f(final String else1, final int a, final Image if1, final Image image, final Image byte1, final boolean do1, final g try1) {
        this.int = false;
        this.for = false;
        this.do = false;
        this.new = 4;
        this.else = else1;
        this.a = a;
        this.if = if1;
        this.char = image;
        this.case = image;
        this.byte = byte1;
        this.do = do1;
        this.try = try1;
    }
    
    public void a() {
        if (this.byte != null) {
            this.for = true;
            this.char = this.byte;
            this.repaint();
        }
    }
    
    public void do() {
        if (this.for) {
            this.for = false;
            this.char = this.case;
            this.repaint();
        }
    }
    
    public void a(final int n) {
        if (!this.do || this.for) {
            return;
        }
        if (n == 3) {
            this.new = 3;
            this.char = this.if;
            this.repaint();
        }
        else {
            this.new = 4;
            this.char = this.case;
            this.repaint();
        }
    }
    
    public void if() {
        this.try = null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 502 || event.id == 501 || event.id == 505 || event.id == 504) {
            return super.handleEvent(event);
        }
        return event.id != 403 && event.id != 401;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.for) {
            if (!this.do) {
                this.int = true;
                this.char = this.if;
                this.repaint();
                this.try.a(this.a, 0);
            }
            else if (this.new == 4) {
                this.new = 3;
                this.char = this.if;
                this.repaint();
                this.try.a(this.a, 3);
            }
            else {
                this.new = 4;
                this.char = this.case;
                this.repaint();
                this.try.a(this.a, 4);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.try.a(this.else);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.try.a("");
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.try.a(this.else);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.int && !this.for && !this.do) {
            this.int = false;
            this.char = this.case;
            this.repaint();
            this.try.a(this.a, 2);
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.char, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
