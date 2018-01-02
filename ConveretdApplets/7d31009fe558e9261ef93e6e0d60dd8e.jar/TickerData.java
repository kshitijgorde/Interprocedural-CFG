import java.util.StringTokenizer;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class TickerData
{
    protected TickerInfo ticker;
    protected Image image;
    protected int width;
    protected int height;
    protected String fontname;
    protected int fontsize;
    protected int smallFontsize;
    protected int tinyFontsize;
    protected Font font;
    protected int ascent;
    protected int descent;
    protected int currentPositionX;
    protected int currentPositionY;
    protected String link;
    protected String target;
    protected boolean interrupted;
    private Hashtable fields;
    
    public TickerData() {
        this.ticker = null;
        this.image = null;
        this.width = 0;
        this.height = 0;
        this.fontname = null;
        this.fontsize = 0;
        this.smallFontsize = 0;
        this.tinyFontsize = 0;
        this.font = null;
        this.ascent = 0;
        this.descent = 0;
        this.currentPositionX = -1;
        this.currentPositionY = -1;
        this.link = null;
        this.target = null;
        this.interrupted = false;
        this.fields = null;
    }
    
    protected abstract int redraw(final Graphics p0, final int p1, final int p2, final boolean p3);
    
    protected String getField(final String s, final String s2) {
        final String s3 = this.fields.get(s);
        return (s3 != null) ? s3 : s2;
    }
    
    protected String getField(final String s) {
        return this.getField(s, null);
    }
    
    protected void parse(final String s) {
        if (this.fields == null) {
            this.fields = new Hashtable();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(61);
            final int length = nextToken.length();
            if (index > 0) {
                final String upperCase = nextToken.substring(0, index).toUpperCase();
                String s2 = nextToken.substring(index + 1, length);
                final char char1 = s2.charAt(0);
                switch (char1) {
                    case 34:
                    case 39: {
                        s2 = s2.substring(1);
                        final int lastIndex = s2.lastIndexOf(char1);
                        if (lastIndex > 0) {
                            s2 = s2.substring(0, lastIndex);
                            break;
                        }
                        while (stringTokenizer.hasMoreTokens()) {
                            final String nextToken2 = stringTokenizer.nextToken();
                            final int lastIndex2 = nextToken2.lastIndexOf(char1);
                            if (lastIndex2 == nextToken2.length() - 1) {
                                s2 = s2 + " " + nextToken2.substring(0, lastIndex2);
                                break;
                            }
                            s2 = s2 + " " + nextToken2;
                        }
                        break;
                    }
                }
                this.fields.put(upperCase, s2);
            }
        }
    }
    
    protected Font smallFont(final Font font, final int n) {
        return new Font(font.getName(), n, (int)Math.round(font.getSize() * 0.9));
    }
    
    protected Font tinyFont(final Font font, final int n) {
        return new Font(font.getName(), n, (int)Math.round(font.getSize() * 0.7));
    }
}
