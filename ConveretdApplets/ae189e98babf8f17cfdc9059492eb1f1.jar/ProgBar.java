import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ProgBar extends Canvas
{
    int curValue;
    int maxValue;
    Dimension d;
    Color barColor;
    Color pctColor;
    
    public ProgBar() {
        this.maxValue = 100;
        this.barColor = Color.red;
        this.pctColor = Color.black;
        if (System.getProperty("java.version").startsWith("1.0") && !System.getProperty("java.vendor").startsWith("Netscape")) {
            this.d = new Dimension(175, 40);
        }
        else {
            this.d = new Dimension(175, 20);
        }
        this.resize(this.d);
    }
    
    public void setProgress(final int curValue) {
        this.curValue = curValue;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics);
        }
    }
    
    public Dimension preferredSize() {
        return this.d;
    }
    
    public Dimension minimumSize() {
        return this.d;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.barColor);
        graphics.fillRect(0, 0, this.curValue * this.d.width / this.maxValue, this.d.height);
    }
}
