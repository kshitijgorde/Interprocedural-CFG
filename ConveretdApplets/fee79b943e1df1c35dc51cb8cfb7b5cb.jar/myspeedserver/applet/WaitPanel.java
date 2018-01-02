// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class WaitPanel extends JPanel
{
    private static final int QU = 15;
    private int NT;
    private int PT;
    private String OT;
    
    public WaitPanel() {
        this.NT = -1;
    }
    
    public void setLabel(final String ot) {
        this.OT = ot;
    }
    
    public void setWait(final int pt) {
        this.PT = pt;
    }
    
    public void doWait() {
        final long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2;
        while ((currentTimeMillis2 = System.currentTimeMillis()) < currentTimeMillis + this.PT) {
            this.NT = (int)(currentTimeMillis2 - currentTimeMillis) * 100 / this.PT;
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
            this.repaint();
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(new Color(15658751));
        graphics.fillRect(1, 1, width - 2, height - 2);
        graphics.setColor(Color.black);
        final int stringWidth = fontMetrics.stringWidth(this.OT);
        graphics.drawString(this.OT, 5, 13 - fontMetrics.getHeight() / 2 + fontMetrics.getAscent());
        graphics.setColor(new Color(190, 190, 145));
        graphics.fillRect(5 + stringWidth + 5, height / 2 - 7, this.NT, 15);
        graphics.setColor(new Color(190, 190, 145).darker());
        graphics.drawRect(5 + stringWidth + 5, height / 2 - 7, 100, 15);
    }
    
    public Dimension getPreferredSize() {
        int stringWidth = this.OT.length() * 15;
        try {
            stringWidth = this.getGraphics().getFontMetrics().stringWidth(this.OT);
        }
        catch (Exception ex) {}
        return new Dimension(stringWidth + 10 + ((this.PT > 0) ? 105 : 0), 26);
    }
}
