import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class zzzpb extends Canvas
{
    private String zzzkb;
    private String zzzlb;
    private String zzzmb;
    private String zzznb;
    private String zzzob;
    
    public zzzpb() {
        this.zzzkb = "default";
        this.zzzlb = "default";
        this.zzzmb = "default";
        this.zzznb = "default";
        this.zzzob = "default";
        this.setBackground(Color.red);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
        final int stringWidth = fontMetrics.stringWidth(this.zzzkb);
        final int stringWidth2 = fontMetrics.stringWidth(this.zzzlb);
        final int stringWidth3 = fontMetrics.stringWidth(this.zzzmb);
        final int stringWidth4 = fontMetrics.stringWidth(this.zzznb);
        final int height2 = fontMetrics.getHeight();
        final int n = height / 2;
        graphics.setColor(Color.yellow);
        graphics.drawString(this.zzzkb, (width - stringWidth) / 2, n);
        final int n2 = n + height2;
        graphics.drawString(this.zzzlb, (width - stringWidth2) / 2, n2);
        final int n3 = n2 + height2;
        graphics.drawString(this.zzzmb, (width - stringWidth3) / 2, n3);
        graphics.drawString(this.zzznb, (width - stringWidth4) / 2, n3 + height2);
    }
    
    public void zzzjb(final String zzzkb, final String zzzlb, final String zzzmb, final String zzznb) {
        this.zzzkb = zzzkb;
        this.zzzlb = zzzlb;
        this.zzzmb = zzzmb;
        this.zzznb = zzznb;
    }
}
