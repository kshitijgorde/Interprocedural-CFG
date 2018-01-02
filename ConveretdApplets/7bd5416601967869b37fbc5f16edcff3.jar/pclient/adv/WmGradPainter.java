// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Color;

public class WmGradPainter extends WmPainter
{
    private Color gradColor1;
    private Color gradColor2;
    
    public WmGradPainter() {
        this.gradColor1 = Color.WHITE;
        this.gradColor2 = new Color(128, 128, 255);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getComponent().getWidth();
        final int height = this.getComponent().getHeight();
        final GradientPaint paint = new GradientPaint(0.0f, 0.0f, this.gradColor1, width, height, this.gradColor2, true);
        Graphics2D graphics2D;
        try {
            graphics2D = (Graphics2D)graphics;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        final Paint paint2 = graphics2D.getPaint();
        graphics2D.setPaint(paint);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setPaint(paint2);
    }
    
    public void setFrom(final Color gradColor1) {
        this.gradColor1 = gradColor1;
    }
    
    public void setTo(final Color gradColor2) {
        this.gradColor2 = gradColor2;
    }
}
