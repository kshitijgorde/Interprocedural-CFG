import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class tipProducer
{
    String s;
    Font f;
    int is;
    Color back;
    Color ink;
    String[] langage;
    
    public tipProducer() {
        this.s = "";
        this.back = Color.yellow;
        this.ink = Color.black;
        this.langage = new String[] { "<b>", "<i>", "</b>", "</i>", "" };
    }
    
    public tipProducer(final String string) {
        this.s = "";
        this.back = Color.yellow;
        this.ink = Color.black;
        this.langage = new String[] { "<b>", "<i>", "</b>", "</i>", "" };
        this.setString(string);
    }
    
    public void setString(final String s) {
        this.s = s;
    }
    
    public String getString(final String s) {
        return s;
    }
    
    public void setBackground(final Color back) {
        this.back = back;
    }
    
    public Color getBackground() {
        return this.back;
    }
    
    public void setForeground(final Color ink) {
        this.ink = ink;
    }
    
    public Color getForeground() {
        return this.ink;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.draw(graphics, n, n2, 0);
    }
    
    public void draw(final Graphics graphics, int n, int n2, final int n3) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n4 = 0;
        this.is = 0;
        int stringWidth = 0;
        String line;
        while ((line = this.getLine()) != null) {
            ++n4;
            if (fontMetrics.stringWidth(line) > stringWidth) {
                stringWidth = fontMetrics.stringWidth(line);
            }
        }
        graphics.setColor(this.back);
        stringWidth += 4;
        if (n3 > 0 && n + stringWidth >= n3) {
            n = n3 - stringWidth;
        }
        graphics.fillRect(n, n2, stringWidth, n4 * fontMetrics.getHeight() + 4);
        n2 += fontMetrics.getHeight() - 2;
        graphics.setColor(this.ink);
        this.is = 0;
        String line2;
        while ((line2 = this.getLine()) != null) {
            this.drawStringHtml(graphics, line2, n + 2, n2);
            n2 += fontMetrics.getHeight();
        }
    }
    
    protected String getLine() {
        if (this.is >= this.s.length()) {
            return null;
        }
        final int index = this.s.indexOf("<br>", this.is);
        String s;
        if (index == -1) {
            s = this.s.substring(this.is);
            this.is = this.s.length();
        }
        else {
            s = this.s.substring(this.is, index);
            this.is = index + 4;
        }
        return s;
    }
    
    void drawStringHtml(final Graphics graphics, String s, int n, final int n2) {
        for (int i = this.indexOfMark(s); i >= 0; i = this.indexOfMark(s)) {
            final String substring = s.substring(0, i);
            graphics.drawString(substring, n, n2);
            n += graphics.getFontMetrics().stringWidth(substring);
            s = s.substring(i);
            if (s.startsWith("<b>")) {
                final Font font = graphics.getFont();
                graphics.setFont(new Font(font.getName(), 1, font.getSize()));
                s = s.substring(3);
            }
            if (s.startsWith("<i>")) {
                final Font font2 = graphics.getFont();
                graphics.setFont(new Font(font2.getName(), 2, font2.getSize()));
                s = s.substring(3);
            }
            if (s.startsWith("</b>") || s.startsWith("</i>")) {
                final Font font3 = graphics.getFont();
                graphics.setFont(new Font(font3.getName(), 0, font3.getSize()));
                s = s.substring(4);
            }
        }
        if (s.length() > 0) {
            graphics.drawString(s, n, n2);
        }
    }
    
    int indexOfMark(final String s) {
        int n = 0;
        int n2 = -1;
        while (this.langage[n].length() > 0) {
            final int index = s.indexOf(this.langage[n]);
            if (index >= 0 && (index < n2 || n2 == -1)) {
                n2 = index;
            }
            ++n;
        }
        return n2;
    }
}
