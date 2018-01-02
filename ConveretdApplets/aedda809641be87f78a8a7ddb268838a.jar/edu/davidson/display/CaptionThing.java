// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import edu.davidson.tools.SApplet;

public class CaptionThing extends Thing
{
    private String text;
    
    public CaptionThing(final SApplet applet, final SScalable sScalable, final String text, final double n, final double n2) {
        super(sScalable, n, n2);
        super.font = new Font("Helvetica", 1, 14);
        this.text = text;
        super.applet = applet;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final Font font = graphics.getFont();
        graphics.setFont(super.font);
        graphics.setColor(super.color);
        graphics.drawString(this.text, (super.canvas.getPixWidth() - graphics.getFontMetrics(super.font).stringWidth(this.text)) / 2 + super.xDisplayOff, -super.yDisplayOff);
        graphics.setColor(Color.black);
        graphics.setFont(font);
    }
}
