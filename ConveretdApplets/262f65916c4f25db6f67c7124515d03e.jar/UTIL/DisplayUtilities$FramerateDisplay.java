// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class DisplayUtilities$FramerateDisplay implements DisplayUtilities$OverlayDrawer
{
    private Font a;
    private boolean b;
    private float c;
    private PercentOfTotal d;
    
    public DisplayUtilities$FramerateDisplay() {
        this.a = null;
    }
    
    public void setAudioUnderrun(final boolean b) {
        this.b = b;
    }
    
    public void setComponentPerformanceComparison(final PercentOfTotal d) {
        this.d = d;
    }
    
    public void setFrameRate(final float c) {
        this.c = c;
    }
    
    public void drawOverlay(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.a == null) {
            this.a = Font.decode("MONOSPACED-BOLD-16");
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.a);
        graphics.setFont(this.a);
        graphics.setXORMode(Color.BLACK);
        graphics.setColor(Color.WHITE);
        String format = "";
        if (this.d != null) {
            format = String.format("C %02d A %02d V %02d I %02d", new Integer((int)this.d.getPercentPerf(PercentOfTotal.CPU)), new Integer((int)this.d.getPercentPerf(PercentOfTotal.AUDIO) + (int)this.d.getPercentPerf(PercentOfTotal.AUDIOBUFFER)), new Integer((int)this.d.getPercentPerf(PercentOfTotal.VIDEO) + (int)this.d.getPercentPerf(PercentOfTotal.BLIT)), new Integer((int)this.d.getPercentPerf(PercentOfTotal.IDLE)));
        }
        final String format2 = String.format("FPS %05.2f %s", new Float(this.c), format);
        graphics.drawString(format2, n + 3, n2 + fontMetrics.getHeight());
        final Rectangle2D stringBounds = fontMetrics.getStringBounds(format2, graphics);
        if (this.b) {
            graphics.setColor(Color.YELLOW);
            final String s = "Audio Will Clip, Underrun!";
            graphics.drawString(s, (int)(n + stringBounds.getWidth() + 3.0), n2 + fontMetrics.getHeight());
            stringBounds.setRect(stringBounds.getX(), stringBounds.getY(), stringBounds.getWidth() + fontMetrics.getStringBounds(s, graphics).getWidth(), stringBounds.getHeight());
        }
        graphics.setColor(Color.WHITE);
        graphics.setPaintMode();
    }
}
