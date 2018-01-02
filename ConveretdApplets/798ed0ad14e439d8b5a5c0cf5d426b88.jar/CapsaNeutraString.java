import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraString extends NeuterBox
{
    protected String charAt;
    protected int drawString;
    protected int em;
    
    public CapsaNeutraString(final String charAt) {
        this.charAt = charAt;
        if (this.charAt.length() > 0) {
            final char char1 = this.charAt.charAt(0);
            if ('\u0388' < char1 && char1 < '\u03ce') {
                this.setFontName("Serif");
            }
        }
    }
    
    public final void calculRect(final BoxComponent boxComponent) {
        super.width = this.drawString;
        super.height = this.em(1.0f);
        super.baseline = this.em(0.75f);
    }
    
    public final void onSetFont(final BoxComponent boxComponent, final Font font) {
        this.drawString = boxComponent.getFontUtils().width(boxComponent, font, this.charAt);
    }
    
    public final void onPaint(final Graphics graphics) {
        final Font font = graphics.getFont();
        graphics.setFont(super.B);
        graphics.drawString(this.charAt, this.em, super.baseline);
        graphics.setFont(font);
    }
}
