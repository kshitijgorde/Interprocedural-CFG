import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class QMLine
{
    private String text;
    private Point loc;
    private Dimension dim;
    private Font font;
    private FontMetrics fm;
    
    public QMLine(final String text, final Point loc, final Font font, final FontMetrics fm) {
        this.text = text;
        this.loc = loc;
        this.font = font;
        this.fm = fm;
        this.dim = new Dimension(fm.stringWidth(text), font.getSize());
    }
    
    public boolean isPartOf(final Point point) {
        return point.x >= this.loc.x && point.y >= this.loc.y && point.x < this.loc.x + this.dim.width && point.y < this.loc.y + this.dim.height;
    }
    
    public boolean isPartOf(final Point point, final QMLine qmLine) {
        return this.isPartOf(point) || (point.x >= this.loc.x && point.y >= qmLine.loc.y + qmLine.dim.height && point.x < this.loc.x + Math.min(this.dim.width, qmLine.dim.width) && point.y < this.loc.y);
    }
    
    public void paint(final Graphics graphics, final Color color) {
        graphics.setFont(this.font);
        graphics.setColor(color);
        graphics.drawString(this.text, this.loc.x, this.loc.y + this.fm.getMaxAscent());
    }
    
    public static Point addLines(final Vector vector, final String s, Point point, final int n, final Font font, final FontMetrics fontMetrics) {
        point = new Point(point.x, point.y);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int n2 = 0;
        while (i < s.length() && s.charAt(i) != '\n' && s.charAt(i) != ' ') {
            sb.append(s.charAt(i));
            ++i;
        }
        StringBuffer sb2 = new StringBuffer(sb.toString());
        while (i < s.length()) {
            n2 = 1;
            while (n2 != 0 && i < s.length() && s.charAt(i) != '\n') {
                ++i;
                sb = new StringBuffer();
                while (i < s.length() && s.charAt(i) != '\n' && s.charAt(i) != ' ') {
                    sb.append(s.charAt(i));
                    ++i;
                }
                if (fontMetrics.stringWidth(sb2.toString() + " " + sb.toString()) < n) {
                    sb2.append(" " + sb.toString());
                }
                else {
                    n2 = 0;
                }
            }
            vector.addElement(new QMLine(sb2.toString(), point, font, fontMetrics));
            point = new Point(point.x, point.y + font.getSize() + 5);
            if (i < s.length() && n2 != 0 && s.charAt(i) == '\n') {
                ++i;
                sb = new StringBuffer();
                while (i < s.length() && s.charAt(i) != '\n' && s.charAt(i) != ' ') {
                    sb.append(s.charAt(i));
                    ++i;
                }
                if (i >= s.length()) {
                    n2 = 0;
                }
            }
            sb2 = new StringBuffer(sb.toString());
        }
        if (n2 == 0) {
            vector.addElement(new QMLine(sb2.toString(), point, font, fontMetrics));
            point = new Point(point.x, point.y + font.getSize() + 5);
        }
        if (0 > s.length()) {
            point = new Point(point.x, point.y - 5);
        }
        return point;
    }
}
