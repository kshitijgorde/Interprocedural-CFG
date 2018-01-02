import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class RaisedPanel extends Panel
{
    private boolean raised;
    private boolean bordered;
    private int width;
    private int height;
    private int style;
    private Insets insets;
    private String label;
    
    public void setInsets(final int n, final int n2, final int n3, final int n4) {
        this.insets = new Insets(n, n2, n3, n4);
    }
    
    public RaisedPanel() {
        this(8, 8, 8, 8, 6, true);
    }
    
    public RaisedPanel(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n, n2, n3, n4, n5, true);
    }
    
    public RaisedPanel(final int n, final int n2, final int n3, final int n4, final int style, final boolean raised) {
        this.width = -1;
        this.height = -1;
        this.insets = new Insets(n, n2, n3, n4);
        this.style = style;
        this.raised = raised;
    }
    
    public void paint(final Graphics graphics) {
        this.width = this.size().width - 1;
        this.height = this.size().height - 1;
        if (this.style == 4) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(0, 0, this.width, this.height, this.raised);
        }
        else if (this.style == 5) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.width - 2, this.height - 2, this.raised);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, this.width, this.height, 5, 5);
        }
        else if (this.style == 6) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.width - 2, this.height - 2, this.raised);
            graphics.draw3DRect(2, 2, this.width - 4, this.height - 4, this.raised);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, this.width, this.height, 5, 5);
        }
        if (this.bordered) {
            graphics.setColor(this.getBackground().darker());
            graphics.drawRoundRect(this.insets.left / 2 + 1, this.insets.top / 2 + 1, this.width - this.insets.left / 2 - this.insets.right / 2 - 1, this.height - this.insets.top / 2 - this.insets.bottom / 2 - 1, 7, 7);
            graphics.setColor(this.getBackground().brighter());
            graphics.drawRoundRect(this.insets.left / 2, this.insets.top / 2, this.width - this.insets.left / 2 - this.insets.right / 2 - 1, this.height - this.insets.top / 2 - this.insets.bottom / 2 - 1, 7, 7);
        }
        if (this.bordered && this.label != null) {
            graphics.setFont(new Font("Verdana", 2, 11));
            final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
            graphics.setColor(this.getBackground());
            graphics.fillRect(this.insets.left, this.insets.top / 2 - fontMetrics.getHeight() / 2, fontMetrics.stringWidth(this.label) + fontMetrics.getMaxDescent() * 2, fontMetrics.getHeight());
            graphics.setColor(Color.white);
            graphics.drawString(this.label, this.insets.left + fontMetrics.getMaxDescent(), this.insets.top / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
        }
    }
    
    public void setBorder(final boolean bordered) {
        this.bordered = bordered;
    }
    
    public Dimension minimumSize() {
        if (this.width != -1) {
            return new Dimension(this.width, this.height);
        }
        return super.minimumSize();
    }
    
    public Dimension prefferedSize() {
        return this.minimumSize();
    }
    
    public Insets insets() {
        return this.insets;
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
}
