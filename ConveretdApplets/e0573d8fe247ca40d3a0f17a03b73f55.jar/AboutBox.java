import java.awt.Dimension;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AboutBox extends Frame
{
    public AboutBox() {
        super("About");
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setResizable(false);
        this.pack();
        this.show();
    }
    
    public void paint(final Graphics graphics) {
        final int n = 2;
        final int n2 = 20;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final String s = "Bochi's Chat";
        graphics.drawString(s, (this.size().width - fontMetrics.stringWidth(s)) / 2, this.insets().top + n2);
        final String s2 = "Georgi Botchukov <gb30@gmx.net>";
        graphics.drawString(s2, (this.size().width - fontMetrics.stringWidth(s2)) / 2, this.insets().top + fontMetrics.getHeight() + n + n2);
        final String s3 = "http://bochi.dir.bg/   --- Demo Version ---- ";
        graphics.drawString(s3, (this.size().width - fontMetrics.stringWidth(s3)) / 2, this.insets().top + 2 * (fontMetrics.getHeight() + n) + n2);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501 || event.id == 201) {
            this.hide();
        }
        return super.handleEvent(event);
    }
    
    public Dimension preferredSize() {
        return new Dimension(300, 150);
    }
}
