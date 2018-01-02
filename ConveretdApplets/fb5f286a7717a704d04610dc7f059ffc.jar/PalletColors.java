import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class PalletColors
{
    Vector colors;
    
    public PalletColors() {
        (this.colors = new Vector(1, 16)).addElement(new Color(0, 100, 254));
        this.colors.addElement(new Color(0, 80, 154));
        this.colors.addElement(new Color(254, 110, 154));
        this.colors.addElement(Color.red);
        this.colors.addElement(Color.yellow);
        this.colors.addElement(Color.green);
        this.colors.addElement(Color.blue);
        this.colors.addElement(Color.orange);
        this.colors.addElement(Color.pink);
        this.colors.addElement(Color.cyan);
        this.colors.addElement(Color.magenta);
        this.colors.addElement(Color.lightGray);
        this.colors.addElement(Color.gray);
        this.colors.addElement(Color.darkGray);
        this.colors.addElement(Color.black);
        this.colors.addElement(Color.white);
    }
    
    public Color getColor(final int n) {
        return this.colors.elementAt(n);
    }
}
