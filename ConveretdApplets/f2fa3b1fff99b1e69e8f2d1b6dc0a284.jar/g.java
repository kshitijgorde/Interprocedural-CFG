import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Canvas
{
    private int else;
    private FontMetrics for;
    private String if;
    private String do;
    private String goto;
    private int new;
    private int byte;
    private boolean int;
    private Rectangle case;
    private Applet char;
    private Color try;
    private Color a;
    
    public g(final boolean int1, final Applet char1) {
        this.if = "Saxo Bank";
        this.do = "http://www.saxobank.com";
        this.goto = "_blank";
        this.case = null;
        this.try = Color.black;
        this.a = Color.white;
        this.int = int1;
        this.char = char1;
    }
    
    public void a(final String if1, final String s, final String goto1) {
        try {
            this.if = new String(if1.getBytes("UTF8"), "UTF8");
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println("Unsupported Encoding");
            this.if = if1;
        }
        this.do = s.trim();
        this.goto = goto1;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final Color a, final Color try1) {
        this.a = a;
        this.try = try1;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        graphics.setColor(this.try);
        graphics.fillRect(0, 0, width, this.else);
        graphics.setColor(this.a);
        this.byte = this.for.stringWidth(this.if);
        graphics.drawString(this.if, (width - this.byte) / 2, this.new);
        if (!this.do.equals("")) {
            this.case = new Rectangle((width - this.byte) / 2, 0, this.byte, this.new);
        }
    }
    
    public void a() {
        this.for = this.getGraphics().getFontMetrics(this.getFont());
        this.else = this.for.getHeight();
        this.resize(this.size().width, this.else);
        this.new = this.else - this.for.getMaxDescent() - 1;
        this.byte = this.for.stringWidth(this.if);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.case != null && !this.do.equals("") && this.case.contains(n, n2)) {
            try {
                this.char.getAppletContext().showDocument(new URL(this.do), this.goto);
            }
            catch (MalformedURLException ex) {}
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.int && this.case != null && !this.do.equals("")) {
            if (this.case.contains(n, n2)) {
                this.setCursor(new Cursor(12));
            }
            else {
                this.setCursor(new Cursor(0));
            }
        }
        return true;
    }
}
