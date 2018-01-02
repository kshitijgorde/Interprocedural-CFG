import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends Canvas
{
    private int if;
    private FontMetrics do;
    private String for;
    private String char;
    private String else;
    private String b;
    private String void;
    private int byte;
    private int null;
    private int try;
    private boolean new;
    private Applet long;
    private Rectangle case;
    private Rectangle int;
    private Color goto;
    private Color a;
    
    public j(final boolean new1, final Applet long1) {
        this.for = "Saxo Bank";
        this.char = "http://www.saxobank.com";
        this.else = "Right Link";
        this.b = "http://www.saxobank.com";
        this.void = "_blank";
        this.case = null;
        this.int = null;
        this.goto = Color.black;
        this.a = Color.white;
        this.new = new1;
        this.long = long1;
    }
    
    public void a(final String for1, final String s, final String else1, final String s2, final String void1) {
        this.for = for1;
        this.char = s.trim();
        this.else = else1;
        this.b = s2.trim();
        this.void = void1;
    }
    
    public void a(final String for1, final String s, final String void1) {
        this.for = for1;
        this.char = s.trim();
        this.else = "";
        this.b = "";
        this.void = void1;
    }
    
    public void a(final Color a, final Color goto1) {
        this.a = a;
        this.goto = goto1;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int n = 1;
        graphics.setColor(this.goto);
        graphics.fillRect(0, 0, width, this.if);
        graphics.setColor(this.a);
        this.null = this.do.stringWidth(this.for);
        this.try = this.do.stringWidth(this.else);
        boolean b = false;
        if (this.null + this.try > width - 4) {
            b = true;
        }
        if (!this.else.equals("") && !b) {
            this.try = this.do.stringWidth(this.else);
            graphics.drawString(this.for, n + 2, this.byte);
            if (!this.char.equals("")) {
                this.case = new Rectangle(0, 0, this.null + 2, this.byte);
            }
            graphics.drawString(this.else, width - this.try - 2, this.byte);
            if (!this.b.equals("")) {
                this.int = new Rectangle(width - this.try - 2, 0, this.try, this.byte);
            }
        }
        else {
            graphics.drawString(this.for, (width - this.null) / 2, this.byte);
            if (!this.char.equals("")) {
                this.case = new Rectangle((width - this.null) / 2, 0, this.null, this.byte);
            }
        }
    }
    
    public void a() {
        this.do = this.getGraphics().getFontMetrics(this.getFont());
        this.if = this.do.getHeight();
        this.setSize(this.getSize().width, this.if);
        this.byte = this.if - this.do.getMaxDescent() - 1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.case != null && !this.char.equals("") && this.case.contains(n, n2)) {
            try {
                this.long.getAppletContext().showDocument(new URL(this.char), this.void);
            }
            catch (MalformedURLException ex) {}
        }
        if (this.int != null && !this.b.equals("") && this.int.contains(n, n2)) {
            try {
                this.long.getAppletContext().showDocument(new URL(this.b), this.void);
            }
            catch (MalformedURLException ex2) {}
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.new) {
            if ((this.case != null && !this.char.equals("") && this.case.contains(n, n2)) || (this.int != null && !this.b.equals("") && this.int.contains(n, n2))) {
                this.setCursor(new Cursor(12));
            }
            else {
                this.setCursor(new Cursor(0));
            }
        }
        return true;
    }
}
