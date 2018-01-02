import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class ErrMsg
{
    private static final int FRAME = 1;
    private static final int GAP = 3;
    private static final String PREFIX = "ERROR: ";
    private int chHeight;
    private int closeBoxLeftX;
    private int closeBoxTopY;
    private int height;
    private int leftX;
    private int topY;
    private int maxDescent;
    private int xWidth;
    private int width;
    private Font font;
    private String procName;
    private String text;
    
    public ErrMsg(final String text) {
        this.text = text;
        if (this.text.charAt(0) == '(' && this.text.indexOf(41) > 0) {
            this.procName = this.text.substring(1, this.text.indexOf(41));
        }
        this.font = null;
    }
    
    private void initDisplayStuff(final Graphics graphics, final Font font, final int n, final int topY) {
        this.topY = topY;
        this.font = font;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.font);
        this.chHeight = fontMetrics.getHeight();
        this.maxDescent = fontMetrics.getMaxDescent();
        this.height = 4 + this.chHeight + 3 + 1;
        this.width = fontMetrics.stringWidth("ERROR: ") + fontMetrics.stringWidth(this.text);
        this.width += 7 + this.chHeight + 3 + 1;
        if (n - this.width < 0) {
            this.width = n + 1;
        }
        this.leftX = n + 1 - this.width;
        this.closeBoxLeftX = n + 1 - (4 + this.chHeight);
        this.closeBoxTopY = topY - 1 + 1 + 3;
        this.xWidth = fontMetrics.stringWidth("X");
    }
    
    public String getProcName() {
        return this.procName;
    }
    
    public String getText() {
        return this.text;
    }
    
    public boolean mouseInCloseBox(final int n, final int n2) {
        return n >= this.closeBoxLeftX && n2 < this.closeBoxTopY + this.height && n < this.closeBoxLeftX + this.width && n2 >= this.closeBoxTopY;
    }
    
    public boolean mouseInErrMsg(final int n, final int n2) {
        return n >= this.leftX && n2 < this.topY + this.height && n < this.leftX + this.width && n2 >= this.topY;
    }
    
    public void paint(final Graphics graphics, final Font font, final int n, final int n2) {
        if (font != this.font) {
            this.initDisplayStuff(graphics, font, n, n2);
        }
        graphics.setFont(this.font);
        graphics.clearRect(this.leftX, n2, this.width - 1, this.height - 1);
        final Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.drawRect(this.leftX, n2, this.width - 1, this.height - 1);
        graphics.drawString("ERROR: " + this.text, this.leftX + 1 + 3, 4 + this.chHeight - this.maxDescent);
        graphics.clearRect(this.closeBoxLeftX, this.closeBoxTopY, this.chHeight, this.chHeight);
        graphics.drawRect(this.closeBoxLeftX, this.closeBoxTopY, this.chHeight, this.chHeight);
        graphics.drawString("X", this.closeBoxLeftX + (this.chHeight - this.xWidth) / 2, this.closeBoxTopY + (this.chHeight - this.maxDescent));
        graphics.setColor(color);
    }
}
