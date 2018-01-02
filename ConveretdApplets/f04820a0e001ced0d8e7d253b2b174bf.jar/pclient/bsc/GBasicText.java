// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class GBasicText extends GBasicItem
{
    public static final int REGULAR = 0;
    public int type;
    public String string;
    public Font font;
    
    public GBasicText(final int type, final String string, final Color color, final Font font) {
        super(color);
        super.width = this.calculateLength(font, string);
        super.height = this.calculateFontHeight(font);
        this.type = type;
        this.string = string;
        this.font = font;
    }
    
    public String toString() {
        return this.getBounds() + "string=" + this.string;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        graphics.setFont(this.font);
        graphics.setColor(super.pcolor);
        graphics.drawString(this.string, n, n2 + this.getBaseline(this.font));
    }
}
