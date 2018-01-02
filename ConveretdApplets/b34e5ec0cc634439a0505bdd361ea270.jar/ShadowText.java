import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShadowText extends Applet
{
    String text;
    Color bgColor;
    Color fgColor;
    String font;
    int size;
    int shadowX;
    int shadowY;
    int textX;
    int textY;
    
    public ShadowText() {
        this.bgColor = Color.white;
        this.fgColor = Color.black;
    }
    
    public void init() {
        final String parameter = this.getParameter("text");
        this.text = ((parameter != null) ? parameter : "Text");
        final String parameter2 = this.getParameter("bgcolor");
        if (parameter2 != null) {
            this.bgColor = new Color(Integer.parseInt(parameter2, 16));
        }
        this.setBackground(this.bgColor);
        final String parameter3 = this.getParameter("textcolor");
        if (parameter3 != null) {
            this.fgColor = new Color(Integer.parseInt(parameter3, 16));
        }
        final String parameter4 = this.getParameter("font");
        this.font = ((parameter4 != null) ? parameter4 : "Times Roman");
        final String parameter5 = this.getParameter("size");
        this.size = ((parameter5 != null) ? Integer.parseInt(parameter5) : 36);
        final String parameter6 = this.getParameter("shadowX");
        this.shadowX = ((parameter6 != null) ? Integer.parseInt(parameter6) : 4);
        final String parameter7 = this.getParameter("shadowY");
        this.shadowY = ((parameter7 != null) ? Integer.parseInt(parameter7) : 4);
    }
    
    public void paint(final Graphics graphics) {
        final Font font = new Font(this.font, 1, this.size);
        graphics.setFont(font);
        graphics.setColor(Color.lightGray);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.textX = this.getSize().width / 2 - fontMetrics.stringWidth(this.text) / 2;
        this.textY = this.getSize().height / 2 + fontMetrics.getHeight() / 3;
        this.showStatus("ShadowText applet from virtig01.cjb.net");
        graphics.drawString(this.text, this.textX - this.shadowX, this.textY - this.shadowY);
        graphics.setColor(this.fgColor);
        graphics.drawString(this.text, this.textX, this.textY);
    }
}
