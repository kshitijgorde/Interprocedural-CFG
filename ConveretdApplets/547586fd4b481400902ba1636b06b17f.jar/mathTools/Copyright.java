// 
// Decompiled by Procyon v0.5.30
// 

package mathTools;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

public class Copyright extends Canvas
{
    public Copyright() {
        this.setBackground(new Color(255, 255, 200));
        this.setSize(350, 25);
    }
    
    public void paint(final Graphics graphics) {
        final Font font = new Font("serif", 0, 9);
        final Font font2 = new Font("serif", 2, 9);
        graphics.setColor(Color.blue);
        graphics.setFont(font);
        graphics.drawString("Copyright 2001 by Donald L. Kreider and C. Dwight Lahr, Dartmouth College", 2, 10);
        graphics.drawString("Refer to authors' book,", 2, 20);
        graphics.setFont(font2);
        graphics.drawString("Principles of Calculus Modeling: An Interactive Approach", 100, 20);
    }
}
