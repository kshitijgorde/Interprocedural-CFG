import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    boolean case;
    public String int;
    public int try;
    int for;
    int else;
    int do;
    int if;
    l byte;
    an char;
    boolean a;
    boolean new;
    int goto;
    
    f(final l byte1, final int try1) {
        this.case = true;
        this.int = "";
        this.try = -16777216;
        this.for = 0;
        this.else = 0;
        this.do = 0;
        this.if = 0;
        this.char = null;
        this.a = false;
        this.new = false;
        this.goto = 12;
        this.byte = byte1;
        this.try = try1;
    }
    
    f(final l byte1, final int try1, final int goto1) {
        this.case = true;
        this.int = "";
        this.try = -16777216;
        this.for = 0;
        this.else = 0;
        this.do = 0;
        this.if = 0;
        this.char = null;
        this.a = false;
        this.new = false;
        this.goto = 12;
        this.byte = byte1;
        this.try = try1;
        this.goto = goto1;
    }
    
    public void do() {
        if (this.char != null) {
            this.char.a();
        }
        this.char = null;
        this.int = null;
    }
    
    public void a(final String s) {
        this.int = s.trim();
        this.a();
    }
    
    public void a() {
        this.case = true;
        this.new = true;
        final String replace = this.int.replace('\n', ' ');
        final Font font = new Font("Arial", 1, this.goto);
        final Graphics graphics = this.byte.e.getGraphics();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.char = new an();
        this.char.long = 0;
        this.char.e = 0;
        this.char.long = fontMetrics.stringWidth(replace);
        this.char.e = fontMetrics.getHeight();
        if (this.char.e <= 0 || this.char.long <= 0) {
            return;
        }
        final an char1 = this.char;
        char1.long += 8;
        this.char.f = true;
        final int n = this.char.long * this.char.e;
        final Image image = this.byte.e.createImage(this.char.long, this.char.e);
        this.for = this.char.long;
        this.else = this.char.e;
        final int[] array = null;
        this.char.c = new int[n];
        final int[] array2 = new int[n];
        for (int i = 0; i < n; ++i) {
            this.char.c[i] = 0;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(font);
        this.a(graphics2, image, array2, this.char.c, this.for, this.else, replace, this.try, false, fontMetrics.getHeight(), fontMetrics.getAscent());
        this.for = this.char.long;
        this.else = this.char.e;
        this.case = false;
    }
    
    private void a(final Graphics graphics, final Image image, final int[] array, final int[] array2, final int n, final int n2, final String s, final int n3, final boolean b, final int n4, final int n5) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n, n2);
        graphics.setColor(Color.white);
        graphics.drawString(s, 4, n5);
        try {
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.char.long, this.char.e, array, 0, this.char.long);
            pixelGrabber.grabPixels();
            pixelGrabber.getStatus();
        }
        catch (Exception ex) {}
        final int n6 = array[0];
        for (int n7 = n * n2, i = 0; i < n7; ++i) {
            if (array[i] != n6) {
                array2[i] = n3;
            }
        }
    }
    
    public boolean a(final long n) {
        if (this.a) {
            this.a();
            this.a = false;
            this.new = true;
        }
        final boolean new1 = this.new;
        this.new = false;
        return new1;
    }
    
    public void if() {
        if (!this.case) {
            d.a(this.char, 0, this.char.long, this.byte.d, this.do, this.if, this.char.long, this.char.e);
        }
    }
}
