import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Message
{
    Color borderColor;
    Color textColor;
    String message;
    Font font;
    String fontName;
    int fontType;
    int fontSize;
    int begin;
    int end;
    int speed1;
    int speed2;
    int pause;
    URL urlLink;
    Color urlColor;
    boolean bSetColor;
    boolean bUrlLink;
    boolean brolledOver;
    
    Message(final Color borderColor, final Color textColor, final Font font, final String fontName, final int fontType, final int fontSize, final String message, final int speed1, final int speed2, final int pause, final int begin, final int end, final String s, final Color urlColor) {
        this.borderColor = borderColor;
        this.textColor = textColor;
        this.message = message;
        this.font = font;
        this.fontName = fontName;
        this.fontType = fontType;
        this.fontSize = fontSize;
        this.begin = begin;
        this.end = end;
        this.speed1 = speed1;
        this.speed2 = speed2;
        this.pause = pause;
        this.urlColor = urlColor;
        this.bSetColor = false;
        try {
            this.urlLink = new URL(s);
            this.bUrlLink = true;
        }
        catch (MalformedURLException ex) {
            this.bUrlLink = false;
        }
    }
    
    public boolean getHighlight() {
        return this.bSetColor;
    }
    
    public URL getUrl() {
        return this.urlLink;
    }
    
    public boolean mouseBrowse(final int n, final int n2, final int n3, final int n4, final String s, final FontMetrics fontMetrics) {
        final int n5 = n3 + fontMetrics.stringWidth(s);
        final int n6 = n4 + fontMetrics.getHeight();
        return n >= n3 && n2 >= n4 - fontMetrics.getHeight() && n <= n5 && n2 <= n6;
    }
    
    public void setHighlight(final boolean bSetColor) {
        this.bSetColor = bSetColor;
    }
    
    public Color setUrlColor(final Color color) {
        if (this.bSetColor) {
            return this.urlColor;
        }
        return color;
    }
}
