// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class b extends Canvas
{
    private static final String do = "";
    private static final int int = 0;
    g new;
    Image[] byte;
    Image[] case;
    String[] if;
    String for;
    int try;
    int a;
    
    b(final int try1, final String[] if1, final Image[] byte1, final Image[] case1, final g new1) {
        this.a = 0;
        this.try = try1;
        this.if = if1;
        this.byte = byte1;
        this.case = case1;
        this.new = new1;
        if (new1.if) {
            this.a = 2;
        }
    }
    
    public void a() {
        this.new = null;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.try; ++i) {
            if (i == this.a) {
                graphics.drawImage(this.byte[i], i * 70, 0, this);
            }
            else {
                graphics.drawImage(this.case[i], i * 70, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501 || event.id == 504 || event.id == 503 || event.id == 505) {
            return super.handleEvent(event);
        }
        return event.id != 403 && event.id != 401;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int a = this.a(n);
        if (a <= this.try - 1 && this.if[a] != this.for) {
            this.for = this.if[a];
            this.new.a(this.for);
        }
        return true;
    }
    
    private int a(final int n) {
        return (int)Math.ceil(n / 70);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        final int a = this.a(n);
        if (a <= this.try - 1) {
            this.for = this.if[a];
            this.new.a(this.for);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.new.a("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int a = this.a(n);
        if (a <= this.try - 1) {
            this.a = a;
            this.repaint();
        }
        this.new.if(this.a);
        return true;
    }
    
    public void if() {
        this.a = 0;
        if (this.new.if) {
            this.a = 2;
        }
        this.repaint();
        this.new.if(this.a);
    }
}
