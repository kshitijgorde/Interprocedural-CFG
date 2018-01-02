import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextTool extends UserDrawTool
{
    private goto Cja;
    private int fka;
    private int gka;
    
    public TextTool() {
        super(1, 1);
        this.Cja = new goto("");
        this.fka = 0;
        this.gka = 0;
        this.Cja.setFont(new Font("SansSerif", 1, 12));
        this.Cja.i(0);
        this.Cja.j(1);
        this.Cja.h(false);
        this.Cja.i(false);
    }
    
    public boolean _(final int n, final int n2, final throws throws1, final throws throws2) {
        super.Wja[0].x = (int)throws1.b(super.Yja[0]);
        super.Wja[0].y = (int)throws2.b(super.Xja[0]);
        final int x = super.Wja[0].x;
        final int n3 = super.Wja[0].x + this.fka;
        final int n4 = super.Wja[0].y - this.gka / 2;
        final int n5 = super.Wja[0].y + this.gka / 2;
        return n >= x && n <= n3 && n2 >= n4 && n2 <= n5;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final throws throws1, final throws throws2) {
        graphics.setXORMode(xorMode);
        this.Cja.setColor(super.xa);
        this.fka = this.Cja.b(graphics);
        this.gka = this.Cja._(graphics);
        this.Cja._(graphics, super.Wja[0].x, super.Wja[0].y);
        graphics.setPaintMode();
    }
    
    public void setText(String s) {
        s = s.replace('|', '_');
        s = s.replace(',', '_');
        s = s.replace('~', '_');
        this.Cja.setText(s.trim());
    }
    
    public String getText() {
        return this.Cja.getText();
    }
}
