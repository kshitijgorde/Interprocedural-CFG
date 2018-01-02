// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;

public class b
{
    private int do;
    private int if;
    private Font a;
    private Color new;
    private FontMetrics try;
    private int int;
    private int case;
    private int byte;
    private int for;
    
    public b(final int do1) {
        this.for = 0;
        this.do = do1;
    }
    
    public b(final int n, final int int1, final int case1, final int byte1, final Font a, final Color new1, final int for1) {
        this.for = 0;
        this.do = n;
        this.if = n;
        this.int = int1;
        this.case = case1;
        this.byte = byte1;
        this.a = a;
        this.new = new1;
        this.for = for1;
    }
    
    public void a(final int n) {
        this.do += n;
    }
    
    public int if() {
        return this.do;
    }
    
    public int a() {
        return this.int;
    }
    
    public void a(final Graphics graphics, final String s, final Color color) {
        graphics.setFont(this.a);
        this.try = graphics.getFontMetrics(this.a);
        graphics.setColor(this.new);
        if (this.try.stringWidth(s) + 15 + this.do > this.for) {
            this.int += this.a.getSize() + 6;
            this.do = this.if - 1;
        }
        graphics.drawString(s, this.do, this.int);
        this.do += this.try.stringWidth(s) + 4;
        if (color != null) {
            graphics.setColor(color);
            graphics.fill3DRect(this.do, this.int - 6, this.case, this.byte, true);
            graphics.setColor(Color.white);
            graphics.drawRect(this.do, this.int - 6, this.case, this.byte);
            this.do += this.case;
        }
        this.do += 5;
    }
    
    public void a(final Graphics graphics, final String s, final Color color, final boolean b) {
        graphics.setFont(this.a);
        this.try = graphics.getFontMetrics(this.a);
        graphics.setColor(this.new);
        if (this.try.stringWidth(s) + 15 + this.do > this.for) {
            this.int += this.a.getSize() + 6;
            this.do = this.if - 1;
        }
        graphics.drawString(s, this.do, this.int);
        this.do += this.try.stringWidth(s) + 4;
        if (color != null && b) {
            graphics.setColor(color);
            graphics.fill3DRect(this.do, this.int - 6, this.case, this.byte, true);
            graphics.setColor(Color.white);
            graphics.drawRect(this.do, this.int - 6, this.case, this.byte);
            this.do += this.case;
            this.do += 5;
        }
    }
}
