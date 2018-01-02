import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextTool extends UserDrawTool
{
    private g qqa;
    private int Fra;
    private int Gra;
    
    public TextTool() {
        super(1, 1);
        this.qqa = new g("");
        this.Fra = 0;
        this.Gra = 0;
        this.qqa.setFont(new Font("SansSerif", 1, 12));
        this.qqa.K(0);
        this.qqa.L(1);
        this.qqa.e(false);
        this.qqa.f(false);
    }
    
    public boolean a(final int n, final int n2, final o o, final o o2) {
        super.Yqa[0].x = (int)o.b(super._ra[0]);
        super.Yqa[0].y = (int)o2.b(super.Zqa[0]);
        final int x = super.Yqa[0].x;
        final int n3 = super.Yqa[0].x + this.Fra;
        final int n4 = super.Yqa[0].y - this.Gra / 2;
        final int n5 = super.Yqa[0].y + this.Gra / 2;
        return n >= x && n <= n3 && n2 >= n4 && n2 <= n5;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final o o, final o o2) {
        graphics.setXORMode(xorMode);
        this.qqa.setColor(super.T);
        this.Fra = this.qqa.b(graphics);
        this.Gra = this.qqa._(graphics);
        this.qqa.a(graphics, super.Yqa[0].x, super.Yqa[0].y);
        graphics.setPaintMode();
    }
    
    public void setText(String s) {
        s = s.replace('|', '_');
        s = s.replace(',', '_');
        s = s.replace('~', '_');
        this.qqa.setText(s.trim());
    }
    
    public String getText() {
        return this.qqa.getText();
    }
}
