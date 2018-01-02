import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Tapiz extends Panel
{
    int an;
    int al;
    static Color cuno;
    static Color cdos;
    static Color ctre;
    
    public Tapiz(final int an, final int al, final Color ctre) {
        this.an = 260;
        this.al = 200;
        this.an = an;
        this.al = al;
        Tapiz.ctre = ctre;
        this.setLayout(null);
        this.setBackground(Tapiz.cuno);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Tapiz.ctre);
        graphics.fillRect(3, 3, this.an - 5, this.al - 5);
        graphics.setColor(Tapiz.cdos);
        for (int i = 0; i < 3; ++i) {
            graphics.drawLine(this.an - i, i, this.an - i, this.al - i);
            graphics.drawLine(i, this.al - i, this.an - i, this.al - i);
        }
    }
    
    static {
        Tapiz.cuno = new Color(210, 210, 210);
        Tapiz.cdos = new Color(100, 100, 100);
        Tapiz.ctre = new Color(220, 100, 10);
    }
}
