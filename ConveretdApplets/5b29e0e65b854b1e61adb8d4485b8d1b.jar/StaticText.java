import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class StaticText extends Panel
{
    private String text;
    private Color textColor;
    private Font theFont;
    private int theFontHeight;
    private int theFontAscent;
    private int theFontDescent;
    private int theFontMaxAdvance;
    private FontMetrics theFM;
    private Dimension minimumSize;
    private Dimension preferredSize;
    private int xLeftIndent;
    
    public StaticText(final String text) {
        this.minimumSize = new Dimension();
        this.preferredSize = new Dimension();
        this.xLeftIndent = 3;
        this.text = text;
        this.textColor = Color.black;
        this.theFont = new Font("sansserif", 0, 9);
        this.setMySize();
    }
    
    public void setText(final String text) {
        this.text = text;
        this.setMySize();
        this.update(this.getGraphics());
    }
    
    private void setMySize() {
        this.setFont(this.theFont);
        this.theFM = this.getFontMetrics(this.theFont);
        this.theFontHeight = this.theFM.getHeight();
        this.theFontAscent = this.theFM.getAscent();
        this.theFontDescent = this.theFM.getDescent();
        this.theFontMaxAdvance = this.theFM.getMaxAdvance();
        final int n = this.xLeftIndent / 2 + 3 + this.theFM.stringWidth(this.text);
        final int n2 = this.theFontHeight * 3 / 2;
        this.resize(n, n2);
        this.minimumSize.width = n;
        this.minimumSize.height = n2;
        this.preferredSize.width = n;
        this.preferredSize.height = n2;
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        final int width = bounds.width;
        final int height = bounds.height;
        graphics.setColor(this.textColor);
        graphics.drawString(this.text, 3, height * 2 / 3);
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize;
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize;
    }
    
    public void setPreferredWidth(final int width) {
        this.preferredSize.width = width;
    }
}
