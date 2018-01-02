import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetDialogPanel extends Panel
{
    public int width;
    public int height;
    private String panelLabel;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        final int width = this.width;
        int height = this.height;
        height -= 6;
        if (this.panelLabel != null) {
            graphics.setColor(Color.white);
            graphics.drawRect(1, 4, width - 2, height - 2);
            graphics.setColor(Color.gray);
            graphics.drawRect(0, 3, width - 2, height - 2);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawRect(1, 4, width - 2, height - 2);
            graphics.setColor(Color.gray);
            graphics.drawRect(0, 3, width - 2, height - 2);
        }
        if (this.panelLabel != null) {
            graphics.setFont(new Font("Helvetica", 0, 10));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(IRCQNetColors.controlColor);
            graphics.fillRect(5, 0, fontMetrics.stringWidth(this.panelLabel) + 1, 10);
            graphics.setColor(Color.black);
            graphics.drawString(this.panelLabel, 6, 7);
        }
    }
    
    public void setPanelLabel(final String panelLabel) {
        this.panelLabel = panelLabel;
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
}
