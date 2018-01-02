import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class PhaseTimeView extends BufferedCanvas
{
    String timeStr;
    Font timeFont;
    
    public PhaseTimeView() {
        this.timeFont = new Font(FontNameConvertor.monospaced(), 0, 12);
    }
    
    public void setTimeString(final String timeStr) {
        this.timeStr = timeStr;
    }
    
    public void draw(final Graphics g) {
        final int width = this.size().width;
        final int height = this.size().height;
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setFont(this.timeFont);
        g.setColor(Color.green);
        if (this.timeStr != null) {
            g.drawString(this.timeStr, width / 2 - g.getFontMetrics().stringWidth(this.timeStr) / 2, height / 2 + g.getFontMetrics().getAscent() / 2);
        }
    }
}
