// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;

public class StyledTextToken extends RichTextToken
{
    private String contents;
    
    public Dimension getSize() {
        if (super.size == null) {
            final char[] charArray = this.contents.toCharArray();
            final FontMetrics fontMetrics = this.getTextStyle().getFontMetrics();
            super.size = new Dimension(fontMetrics.charsWidth(charArray, 0, charArray.length), fontMetrics.getHeight());
        }
        return super.size;
    }
    
    protected int getAscent() {
        return this.getTextStyle().getFontMetrics().getMaxAscent();
    }
    
    public void paint(final Graphics graphics, final Point point) {
        final char[] charArray = this.contents.toCharArray();
        this.getTextStyle().drawText(graphics, charArray, 0, charArray.length, point.x, point.y, super.size.width, super.size.height, (super.container.getSize().height + super.container.getAscent()) / 2);
    }
    
    public final String getContents() {
        return this.contents;
    }
    
    public StyledTextToken(final String contents, final TextStyle textStyle) {
        super(textStyle);
        this.contents = contents;
    }
}
