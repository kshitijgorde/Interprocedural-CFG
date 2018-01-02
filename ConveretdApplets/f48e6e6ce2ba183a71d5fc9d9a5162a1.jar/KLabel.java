import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class KLabel extends Component
{
    public static final int CENTER = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private String text;
    private int alignment;
    
    public KLabel() {
        this.alignment = 2;
    }
    
    public KLabel(final String text) {
        this.alignment = 2;
        this.text = text;
    }
    
    public KLabel(final String text, final int alignment) {
        this.alignment = 2;
        this.text = text;
        this.alignment = alignment;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            this.paint(graphics);
        }
    }
    
    public void setAlignment(final int alignment) {
        this.alignment = alignment;
        this.update();
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.update();
    }
    
    private void update() {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            this.update(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setFont(this.getFont());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(this.getForeground());
        switch (this.alignment) {
            case 1: {
                graphics.drawString(this.text, (this.getSize().width - fontMetrics.stringWidth(this.text)) / 2, (this.getSize().height + fontMetrics.getAscent()) / 2);
            }
            case 2: {
                graphics.drawString(this.text, 0, (this.getSize().height + fontMetrics.getAscent()) / 2);
            }
            case 3: {
                graphics.drawString(this.text, this.getSize().width - fontMetrics.stringWidth(this.text), (this.getSize().height + fontMetrics.getAscent()) / 2);
            }
            default: {}
        }
    }
}
