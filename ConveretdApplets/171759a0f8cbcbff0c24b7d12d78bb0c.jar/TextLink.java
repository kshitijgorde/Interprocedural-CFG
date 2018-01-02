import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Point;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class TextLink
{
    private static final int MAX_STRINGS = 20;
    private URL urlLink;
    private String target;
    private Point blockStart;
    private Point blockEnd;
    private int leading;
    private int fontHeight;
    private Color textColor;
    private Color rollColor;
    private int sCount;
    private String[] stringArray;
    private boolean rolledOver;
    
    public TextLink(final String s, final String s2, final String target, final int n, final int n2, final int n3, final int leading, final Color textColor, final Color rollColor, final FontMetrics fontMetrics) {
        this.sCount = 0;
        this.stringArray = new String[20];
        this.textColor = textColor;
        this.rollColor = rollColor;
        this.leading = leading;
        this.wrapString(s, fontMetrics, n3);
        this.fontHeight = fontMetrics.getHeight();
        this.blockStart = new Point(n, n2);
        this.blockEnd = new Point(n3 + n, (this.getNumStrings() - 1) * this.leading + n2);
        this.rolledOver = false;
        this.target = target;
        try {
            this.urlLink = new URL(s2);
        }
        catch (MalformedURLException ex) {
            System.out.println("URL Error: " + ex);
        }
    }
    
    public boolean mouseWithin(final int n, final int n2) {
        return n >= this.blockStart.x && n2 >= this.blockStart.y - this.fontHeight && n <= this.blockEnd.x && n2 <= this.blockEnd.y;
    }
    
    public String getString(final int n) {
        try {
            if (this.stringArray[n] != null) {
                return this.stringArray[n];
            }
            return "";
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public int getLeading(final int n) {
        return this.blockStart.y + this.leading * n;
    }
    
    public Point getPos() {
        return this.blockStart;
    }
    
    public int getNumStrings() {
        return this.sCount + 1;
    }
    
    public Color getTextColor() {
        if (!this.rolledOver) {
            return this.textColor;
        }
        return this.rollColor;
    }
    
    public void setTextColor(final Color textColor) {
        this.textColor = textColor;
    }
    
    public boolean fadeTo(final Color textColor, final int n) {
        int red = this.textColor.getRed();
        int green = this.textColor.getGreen();
        int blue = this.textColor.getBlue();
        boolean b;
        if (red < textColor.getRed() - n || red > textColor.getRed() + n) {
            red = ((red < textColor.getRed()) ? (red + n) : (red - n));
            b = false;
        }
        else {
            b = true;
        }
        boolean b2;
        if (green < textColor.getGreen() - n || green > textColor.getGreen() + n) {
            green = ((green < textColor.getGreen()) ? (green + n) : (green - n));
            b2 = false;
        }
        else {
            b2 = true;
        }
        boolean b3;
        if (blue < textColor.getBlue() - n || blue > textColor.getBlue() + n) {
            blue = ((blue < textColor.getBlue()) ? (blue + n) : (blue - n));
            b3 = false;
        }
        else {
            b3 = true;
        }
        this.textColor = new Color(red, green, blue);
        if (b && b2 && b3) {
            this.textColor = textColor;
            return true;
        }
        return false;
    }
    
    public URL getUrl() {
        return this.urlLink;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void setHighlight(final boolean rolledOver) {
        this.rolledOver = rolledOver;
    }
    
    public boolean getHighlight() {
        return this.rolledOver;
    }
    
    private void wrapString(final String s, final FontMetrics fontMetrics, final int n) {
        String nextToken = "";
        String string = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            if (n2 == 0) {
                nextToken = stringTokenizer.nextToken();
            }
            if (fontMetrics.stringWidth(string) + fontMetrics.stringWidth(nextToken) >= n && stringTokenizer.countTokens() == 0) {
                this.stringArray[this.sCount] = string;
                this.stringArray[++this.sCount] = nextToken;
                return;
            }
            if (fontMetrics.stringWidth(string) + fontMetrics.stringWidth(nextToken) >= n) {
                n2 = 1;
                this.stringArray[this.sCount++] = string;
                string = "";
            }
            else {
                if (stringTokenizer.countTokens() == 0) {
                    this.stringArray[this.sCount] = string + nextToken;
                    return;
                }
                n2 = 0;
                string = string + nextToken + " ";
            }
        }
    }
}
