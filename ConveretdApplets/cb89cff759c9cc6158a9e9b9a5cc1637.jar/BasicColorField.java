import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class BasicColorField extends Canvas implements MouseListener
{
    Color color;
    HexColorTool hct;
    
    public BasicColorField(final Color color, final HexColorTool hct) {
        this.color = color;
        this.hct = hct;
        this.setBackground(color);
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setColor(this.color);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.lightGray);
        graphics.draw3DRect(0, 0, width - 1, height - 1, false);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.hct.updateColor(this.color, true, true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
