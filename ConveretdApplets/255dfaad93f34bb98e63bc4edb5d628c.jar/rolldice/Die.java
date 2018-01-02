// 
// Decompiled by Procyon v0.5.30
// 

package rolldice;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JComponent;

public class Die extends JComponent
{
    private static final int SPOT_DIAM = 9;
    private int _faceValue;
    
    public Die() {
        this.setPreferredSize(new Dimension(60, 60));
        this.roll();
    }
    
    public int roll() {
        final int val = (int)(6.0 * Math.random() + 1.0);
        this.setValue(val);
        return val;
    }
    
    public int getValue() {
        return this._faceValue;
    }
    
    public void setValue(final int spots) {
        this._faceValue = spots;
        this.repaint();
    }
    
    public void paintComponent(final Graphics g) {
        final int w = this.getWidth();
        final int h = this.getHeight();
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, w - 1, h - 1);
        switch (this._faceValue) {
            case 1: {
                this.drawSpot(g2, w / 2, h / 2);
                break;
            }
            case 3: {
                this.drawSpot(g2, w / 2, h / 2);
            }
            case 2: {
                this.drawSpot(g2, w / 4, h / 4);
                this.drawSpot(g2, 3 * w / 4, 3 * h / 4);
                break;
            }
            case 5: {
                this.drawSpot(g2, w / 2, h / 2);
            }
            case 4: {
                this.drawSpot(g2, w / 4, h / 4);
                this.drawSpot(g2, 3 * w / 4, 3 * h / 4);
                this.drawSpot(g2, 3 * w / 4, h / 4);
                this.drawSpot(g2, w / 4, 3 * h / 4);
                break;
            }
            case 6: {
                this.drawSpot(g2, w / 4, h / 4);
                this.drawSpot(g2, 3 * w / 4, 3 * h / 4);
                this.drawSpot(g2, 3 * w / 4, h / 4);
                this.drawSpot(g2, w / 4, 3 * h / 4);
                this.drawSpot(g2, w / 4, h / 2);
                this.drawSpot(g2, 3 * w / 4, h / 2);
                break;
            }
        }
    }
    
    private void drawSpot(final Graphics2D g2, final int x, final int y) {
        g2.fillOval(x - 4, y - 4, 9, 9);
    }
}
