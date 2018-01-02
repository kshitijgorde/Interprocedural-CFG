// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.a;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import zp.a.a.a.c.e;
import java.awt.Image;
import java.applet.AppletContext;
import java.awt.Canvas;

public final class a extends Canvas implements Runnable
{
    private static final String long = "max_zoom";
    private static final String new = "sand_watch";
    private static final int byte = 1000;
    AppletContext a;
    boolean for;
    Thread char;
    Image else;
    Image int;
    Image try;
    private int goto;
    private int do;
    private int if;
    private int case;
    
    public a(final zp.a.a.a.b.a a, final AppletContext a2, final boolean for1) {
        this.goto = 0;
        this.do = 0;
        this.if = 0;
        this.case = 0;
        this.for = for1;
        this.a = a2;
        this.else = e.a("max_zoom");
        this.int = e.a("sand_watch");
        this.try = this.int;
        this.goto = a.size().width;
        this.if = a.size().height;
        this.do = 70;
        this.case = 24;
        a.add(this);
        this.reshape(this.goto + 100, this.if + 100, this.do, this.case);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.try, 0, 0, this.getBackground(), null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a() {
        this.try = this.int;
        this.move(this.goto - this.do, this.if - this.case);
        this.a("Submit Request.");
    }
    
    public void if() {
        this.move(this.goto + 100, this.if + 100);
        this.a("Document Done.");
    }
    
    public boolean do() {
        if (!this.for) {
            return false;
        }
        if (this.char == null || !this.char.isAlive()) {
            (this.char = new Thread(this)).start();
        }
        return true;
    }
    
    public void run() {
        this.try = this.else;
        this.move(this.goto - this.do, this.if - this.case);
        try {
            Thread.sleep(1000L);
        }
        catch (Exception ex) {}
        this.move(this.goto + 100, this.if + 100);
    }
    
    private void a(final String s) {
        if (this.a != null) {
            this.a.showStatus(s);
        }
    }
}
