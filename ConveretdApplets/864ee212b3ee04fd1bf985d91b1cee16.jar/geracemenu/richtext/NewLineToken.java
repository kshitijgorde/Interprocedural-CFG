// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;

public class NewLineToken extends RichTextToken
{
    public Dimension getSize() {
        final FontMetrics fontMetrics = this.getTextStyle().getFontMetrics();
        return new Dimension(fontMetrics.charWidth(' '), fontMetrics.getHeight());
    }
    
    public void paint(final Graphics graphics, final Point point) {
    }
    
    public NewLineToken(final TextStyle textStyle) {
        super(textStyle);
    }
}
