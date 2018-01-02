import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class Text extends Sprite
{
    public static final Font PT12;
    public static final Font PT20;
    public static final Font PT40;
    public static final Font PT60;
    public static final Font PT80;
    public static final int TEXT = 13;
    public static final int FONT = 14;
    public static final int COLOR = 15;
    public static final int SHADOWOFFSET = 16;
    public static final int SHADOWCOLOR = 17;
    public String text;
    public Font font;
    public Color color;
    public Color shadowColor;
    public int shadowOffset;
    
    public Text() {
        this.set(new int[] { 13, 14, 15, 16, 17 }, new Object[] { "", Text.PT12, Color.white, Sprite.ZERO, Color.black });
    }
    
    public final void set(final int n, final Object o) {
        switch (n) {
            case 13: {
                this.text = (String)o;
            }
            case 14: {
                this.font = (Font)o;
            }
            case 15: {
                this.color = (Color)o;
            }
            case 16: {
                this.shadowOffset = (int)o;
            }
            case 17: {
                this.shadowColor = (Color)o;
            }
            default: {
                super.set(n, o);
            }
        }
    }
    
    public final void draw(final Graphics graphics) {
        if (super.visible) {
            graphics.setFont(this.font);
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.text);
            if (this.shadowOffset != 0) {
                graphics.setColor(this.shadowColor);
                graphics.drawString(this.text, super.x + ((super.xAnchor == Sprite.WEST) ? 0 : ((super.xAnchor == Sprite.EAST) ? (super.width - stringWidth) : ((super.width - stringWidth) / 2))) + this.shadowOffset, super.y + ((super.yAnchor == Sprite.NORTH) ? this.font.getSize() : ((super.yAnchor == Sprite.SOUTH) ? (-this.font.getSize()) : ((super.height + this.font.getSize()) / 2))) + this.shadowOffset);
            }
            graphics.setColor(this.color);
            graphics.drawString(this.text, super.x + ((super.xAnchor == Sprite.WEST) ? 0 : ((super.xAnchor == Sprite.EAST) ? (super.width - stringWidth) : ((super.width - stringWidth) / 2))), super.y + ((super.yAnchor == Sprite.NORTH) ? this.font.getSize() : ((super.yAnchor == Sprite.SOUTH) ? (-this.font.getSize()) : ((super.height + this.font.getSize()) / 2))));
        }
    }
    
    static {
        PT12 = new Font("Verdana", 1, 12);
        PT20 = new Font("Verdana", 1, 20);
        PT40 = new Font("Verdana", 1, 40);
        PT60 = new Font("Verdana", 1, 60);
        PT80 = new Font("Verdana", 1, 80);
    }
}
