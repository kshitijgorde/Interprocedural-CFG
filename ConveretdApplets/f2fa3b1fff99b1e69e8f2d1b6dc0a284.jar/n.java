import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class n
{
    protected Point for;
    protected Image int;
    protected Graphics a;
    public boolean if;
    public String new;
    public boolean do;
    
    public n() {
        this.if = true;
        this.new = null;
    }
    
    public int if() {
        return 0;
    }
    
    public int for() {
        return 0;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void a(final int n, final int n2) {
    }
    
    public void a(final Point for1) {
        this.for = for1;
    }
    
    public Point do() {
        return this.for;
    }
    
    public void a(final boolean b) {
    }
    
    public void if(final boolean b) {
    }
    
    public String a() {
        return "";
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final Dimension dimension) {
    }
    
    protected void a(final Graphics graphics, final String s, final int n, final int n2, final Color color, final Font font) {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(s, n, n2);
    }
}
