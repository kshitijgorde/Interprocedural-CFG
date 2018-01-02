import javax.swing.event.ChangeEvent;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

// 
// Decompiled by Procyon v0.5.30
// 

public class CSlider extends JSlider implements ChangeListener
{
    private int _width;
    private int _height;
    private int _oldValue;
    
    public CSlider(final int x, final int y, final int width, final int height, final int min, final int max, final int value) {
        super(0, min, max, value);
        this._width = width;
        this._height = height;
        this._oldValue = 0;
        this.setBounds(x, y, this._width, this._height);
        this.addChangeListener(this);
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final int markerWidth = 28;
        final int width = markerWidth + ((this.toString().length() > 2) ? 6 : 0) - ((this.toString().length() < 2) ? 6 : 0);
        final int height = 20;
        final int mouseY = 0;
        int mouseX = (this.getValue() - this.getMinimum()) / (this.getMaximum() - this.getMinimum()) * this._width - width / 2;
        mouseX = ((mouseX < 0) ? 0 : mouseX);
        mouseX = ((mouseX + width + 1 > this._width) ? (this._width - width - 1) : mouseX);
        g.setColor(this.isEnabled() ? Color.ORANGE : Color.LIGHT_GRAY);
        g.fillRect(mouseX + 1, mouseY + 1, width - 1, height - 1);
        g.setColor(this.isEnabled() ? Color.BLACK : Color.DARK_GRAY);
        g.drawRect(mouseX, mouseY, width, height);
        g.drawString(this.toString(), mouseX + 7, mouseY + 15);
    }
    
    public String toString() {
        return "" + this.getValue();
    }
    
    public void stateChanged(final ChangeEvent e) {
        if (this._oldValue != this.getValue()) {
            this._oldValue = this.getValue();
            this.repaint();
        }
    }
    
    public boolean isChanged() {
        return this._oldValue != this.getValue();
    }
    
    public int getOldValue() {
        return this._oldValue;
    }
    
    public void setOldValue(final int value) {
        this._oldValue = value;
    }
}
