// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class c extends Canvas
{
    private static final String case = "";
    private static final int new = 0;
    g if;
    Image[] a;
    Image[] do;
    String[] try;
    String for;
    int byte;
    int int;
    
    c(final int byte1, final String[] try1, final Image[] a, final Image[] do1, final g if1) {
        this.int = 0;
        this.byte = byte1;
        this.try = try1;
        this.a = a;
        this.do = do1;
        this.if = if1;
        if (if1.a) {
            this.int = 2;
        }
    }
    
    private int a(final int n) {
        return (int)Math.ceil(n / 24);
    }
    
    public void if() {
        this.int = 0;
        if (this.if.a) {
            this.int = 2;
        }
        this.repaint();
        this.if.do(this.int);
    }
    
    public void a() {
        this.if = null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501 || event.id == 504 || event.id == 503 || event.id == 505) {
            return super.handleEvent(event);
        }
        return event.id != 403 && event.id != 401;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int a = this.a(n);
        if (a <= this.byte - 1) {
            this.int = a;
            this.repaint();
        }
        this.if.do(this.int);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        final int a = this.a(n);
        if (a <= this.byte - 1) {
            this.for = this.try[a];
            this.if.a(this.for);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.if.a("");
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int a = this.a(n);
        if (a <= this.byte - 1 && this.try[a] != this.for) {
            this.for = this.try[a];
            this.if.a(this.for);
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.byte; ++i) {
            if (i == this.int) {
                graphics.drawImage(this.a[i], i * 24, 0, this);
            }
            else {
                graphics.drawImage(this.do[i], i * 24, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
