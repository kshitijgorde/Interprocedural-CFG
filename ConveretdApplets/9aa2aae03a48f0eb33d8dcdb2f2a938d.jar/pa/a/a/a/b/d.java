// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.b;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import pa.a.a.a.d.b;
import pa.a.a.a.a.a;
import java.awt.Image;
import java.applet.AppletContext;
import java.awt.Canvas;

public final class d extends Canvas implements Runnable
{
    private static final String try = "max_zoom";
    private static final String for = "sand_watch";
    private static final int byte = 1000;
    AppletContext if;
    boolean do;
    Thread int;
    Image case;
    Image goto;
    Image a;
    private int char;
    private int long;
    private int else;
    private int new;
    
    public d(final a a, final AppletContext if1, final boolean do1) {
        this.char = 0;
        this.long = 0;
        this.else = 0;
        this.new = 0;
        this.do = do1;
        this.if = if1;
        this.case = b.a("max_zoom");
        this.goto = b.a("sand_watch");
        this.a = this.goto;
        this.char = a.size().width;
        this.else = a.size().height;
        this.long = 24;
        this.new = 24;
        a.add(this);
        this.reshape(this.char + 100, this.else + 100, this.long, this.new);
    }
    
    public boolean a() {
        if (!this.do) {
            return false;
        }
        if (this.int == null || !this.int.isAlive()) {
            (this.int = new Thread(this)).start();
        }
        return true;
    }
    
    public void do() {
        this.move(this.char + 100, this.else + 100);
        this.a("Document Done.");
    }
    
    public void if() {
        this.a = this.goto;
        this.move(this.char - this.long, this.else - this.new);
        this.a("Submit Request.");
    }
    
    private void a(final String s) {
        if (this.if != null) {
            this.if.showStatus(s);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, this.getBackground(), null);
    }
    
    public void run() {
        this.a = this.case;
        this.move(this.char - this.long, this.else - this.new);
        try {
            Thread.sleep(1000L);
        }
        catch (Exception ex) {}
        this.move(this.char + 100, this.else + 100);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
