import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends Canvas
{
    private String bM;
    private Image X;
    
    private String a(String substring, final int n) {
        final StringBuffer sb = new StringBuffer("");
        final int n2 = 76;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        int min = Math.min(substring.length(), n2);
        while (true) {
            final String substring2 = substring.substring(0, min);
            if (fontMetrics.stringWidth(substring2) <= n) {
                sb.append(substring2);
                final int length = substring.length();
                if (min == length) {
                    break;
                }
                if (!Character.isWhitespace(substring.charAt(min))) {
                    --min;
                }
                substring = substring.substring(min + 1);
                sb.append("\n");
                final int n3 = length - (min + 1);
                if (n3 <= n2) {
                    min = n3;
                    continue;
                }
                min = n2;
            }
            else {
                --min;
            }
            int n4 = substring.lastIndexOf("\n", min);
            if (n4 == -1) {
                n4 = substring.lastIndexOf(32, min);
            }
            if (n4 == -1) {
                for (n4 = min; n4 > 0 && Character.isLetter(substring.charAt(n4)); --n4) {}
                if (n4 == 0) {
                    sb.append(substring);
                    break;
                }
            }
            min = n4;
        }
        return sb.toString();
    }
    
    public Image a(final int n, String a, int n2) {
        this.bM = a;
        a = this.a(a, n2 - 28);
        Color red;
        String s;
        if (n > 0) {
            red = Color.red;
            s = ((n == 1) ? "Error Message" : "Warning");
        }
        else {
            red = new Color(32896);
            s = "Downloading...";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a, "\n");
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n3 = fontMetrics.getHeight() * (stringTokenizer.countTokens() + 2) + (4 * (stringTokenizer.countTokens() - 1) + 20);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (fontMetrics.stringWidth(nextToken) > n2 - 40) {
                n2 = fontMetrics.stringWidth(nextToken) + 40;
            }
        }
        this.X = this.createImage(n2, n3);
        final Graphics graphics = this.X.getGraphics();
        graphics.setColor(new Color(14011579));
        graphics.fillRect(0, 0, n2, n3);
        int n4 = fontMetrics.getAscent() + 20;
        final StringTokenizer stringTokenizer2 = new StringTokenizer(a, "\n");
        graphics.setColor(Color.black);
        graphics.setFont(new Font("SanSerif", 0, 12));
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer2.nextToken();
            n4 += fontMetrics.getHeight() + 4;
            graphics.drawString(nextToken2, 20, n4 - 4);
        }
        graphics.setColor(red);
        graphics.fillRect(3, 3, n2 - 6, 20);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Dialog", 1, 13));
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        graphics.drawString(s, (n2 - fontMetrics2.stringWidth(s) + 1) / 2, fontMetrics2.getAscent() + 5);
        graphics.dispose();
        return this.X;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.X, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
