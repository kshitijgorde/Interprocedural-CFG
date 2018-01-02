import java.net.URL;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class myspeed extends Applet
{
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        final Font font = new Font("Helvetica", 1, 14);
        final Font font2 = new Font("Helvetica", 0, 12);
        graphics.setColor(new Color(14540287));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
        final int n = height / 2 - 20;
        final int n2 = n + 2 * drawCentredString(graphics, "Upgrading From MySpeed Server v7 (and previous)", n, width, font);
        int n3 = n2 + drawCentredString(graphics, "The <APPLET> tag syntax has changed and you are using the old syntax.", n2, width, font2);
        n3 += 10;
        final int n4 = n3 + drawCentredString(graphics, "Please click this message for more information.", n3, width, font2);
    }
    
    private static int drawCentredString(final Graphics graphics, final String s, final int n, final int n2, final Font font) {
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(s, n2 / 2 - fontMetrics.stringWidth(s) / 2, n);
        return fontMetrics.getHeight();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.myconnectionserver.com/support/v8/appletsyntax.html"), "_self");
        }
        catch (Exception ex) {}
        return true;
    }
}
