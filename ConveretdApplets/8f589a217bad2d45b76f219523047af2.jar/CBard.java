import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CBard extends Applet
{
    private int p;
    private StringBuffer d;
    private Font a;
    
    public final synchronized void stop() {
    }
    
    public final synchronized void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.d != null) {
            if (this.a == null) {
                final Font font = graphics.getFont();
                this.a = new Font(font.getName(), font.getStyle(), this.p);
            }
            graphics.setFont(this.a);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            StringBuffer sb = new StringBuffer();
            int height;
            final int n = height = fontMetrics.getHeight();
            for (int i = 0; i < this.d.length(); ++i) {
                if (this.d.charAt(i) == '\n') {
                    graphics.drawString(sb.toString(), 2, height);
                    height += n;
                    sb = new StringBuffer();
                }
                else {
                    sb.append(this.d.charAt(i));
                }
            }
            graphics.drawString(sb.toString(), 2, height);
        }
    }
    
    public final synchronized void SetStory(final String s) {
        if (s == null) {
            return;
        }
        this.d = new StringBuffer(s);
        this.repaint();
    }
    
    public final synchronized void start() {
        final String parameter = this.getParameter("backgroundColor");
        int intValue = -1;
        if (parameter != null) {
            intValue = Integer.valueOf(parameter, 16);
        }
        this.setBackground(new Color(intValue));
        this.p = 8;
        final String parameter2 = this.getParameter("fontSize");
        if (parameter2 != null) {
            this.p = Integer.valueOf(parameter2, 16);
        }
    }
    
    public final synchronized void init() {
    }
}
