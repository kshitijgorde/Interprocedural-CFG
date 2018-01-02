import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiProgress extends suiBar
{
    int percent;
    
    public suiProgress(final int x1, final int y1, final int x2, final int y2, final int width, final int height, final boolean convex) {
        super(x1, y1, x2, y2, width, height, convex);
        super.x1 = x1;
        super.y1 = y1;
        super.x2 = x2;
        super.y2 = y2;
        super.width = width;
        super.height = height;
        super.convex = convex;
        this.repaint();
    }
    
    public void setPercentage(final int percent) {
        this.percent = percent;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (super.background != null) {
            final Dimension size = this.getSize();
            for (int i = 0; i < size.height - 1; i += super.hh) {
                for (int j = 0; j < size.width - 1; j += super.ww) {
                    graphics.drawImage(super.background, j, i, this);
                }
            }
        }
        if (!super.convex) {
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(super.x1, super.y2, super.x2, super.y2);
            graphics.drawLine(super.x2, super.y1, super.x2, super.y2);
            graphics.setColor(this.getBackground().darker());
            graphics.drawLine(super.x1, super.y1, super.x2, super.y1);
            graphics.drawLine(super.x1, super.y1, super.x1, super.y2);
        }
        else {
            graphics.setColor(this.getBackground().darker());
            graphics.drawLine(super.x1, super.y2, super.x2, super.y2);
            graphics.drawLine(super.x2, super.y1, super.x2, super.y2);
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(super.x1, super.y1, super.x2, super.y1);
            graphics.drawLine(super.x1, super.y1, super.x1, super.y2);
        }
        graphics.setFont(new Font("Helvetica", 0, 9));
        graphics.setColor(this.getForeground());
        final String string = String.valueOf(this.percent) + "%";
        final int n = super.x2 - super.x1 - 1;
        final int n2 = super.y2 - super.y1 - 1;
        graphics.fillRect(super.x1 + 1, super.y1 + 1, n * this.percent / 100, n2);
        final int n3 = (n - graphics.getFontMetrics().stringWidth(string)) / 2 + super.x1;
        final int height = graphics.getFontMetrics().getHeight();
        final int n4 = height + ((n2 - height) / 2 - graphics.getFontMetrics().getDescent() + super.y1);
        graphics.drawString(string, n3 - 1, n4);
        graphics.drawString(string, n3 + 1, n4);
        graphics.drawString(string, n3, n4 - 1);
        graphics.drawString(string, n3, n4 + 1);
        graphics.setColor(this.getBackground());
        graphics.drawString(string, n3, n4);
    }
}
