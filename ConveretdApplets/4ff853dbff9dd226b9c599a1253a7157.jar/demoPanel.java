import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class demoPanel extends suiPanel
{
    suiPanel dp;
    String paras;
    String methods;
    
    demoPanel(final String paras, final String methods) {
        super(450, 280);
        this.setBackground(new Color(143, 143, 192));
        this.setSpecial(1);
        (this.dp = new suiPanel(400, 180)).setSpecial(2);
        this.add(new suiPanel(400, 10), "North");
        this.add(this.dp, "Center");
        this.paras = paras;
        this.methods = methods;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(new Color(250, 250, 90));
        graphics.setFont(new Font("Helvetica", 0, 11));
        graphics.drawString("Customizable Parameters:", 25, 220);
        graphics.drawString("Methods:", 25, 250);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Helvetica", 2, 10));
        graphics.drawString(this.paras, 30, 235);
        graphics.drawString(this.methods, 30, 265);
    }
}
