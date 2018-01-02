import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Font;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineWrapManager extends TextScroll
{
    private StringTokenizer tokenizer;
    private int lineNum;
    private String[] data;
    private Font font;
    private int fontSize;
    private int fontStyle;
    private String fontFace;
    private DirectiveManager directiveManager;
    private int textInset;
    
    public LineWrapManager(final String[] data, final Font font) {
        this.data = data;
        this.fontFace = font.getName();
        this.fontSize = font.getSize();
        this.font = font;
        this.lineNum = 0;
        this.tokenizer = new StringTokenizer(this.data[this.lineNum], " \t\n\r", true);
        this.directiveManager = new DirectiveManager(this);
    }
    
    public void setRightForegroundColor(final String s) {
    }
    
    public void setLeftForegroundColor(final String s) {
    }
    
    public void setRightBackgroundColor(final String s) {
    }
    
    public void setLeftBackgroundColor(final String s) {
    }
    
    public void setSpeed(final String s) {
    }
    
    public void pause() {
    }
    
    public void pause(final String s) {
    }
    
    public void setLeftFontFace(final String s) {
    }
    
    public void setRightFontFace(final String s) {
        this.font = new Font(s, this.fontStyle, this.fontSize);
    }
    
    public void setFontFace(final String rightFontFace) {
        this.setRightFontFace(rightFontFace);
    }
    
    public void setLeftFontSize(final String s) {
    }
    
    public void setRightFontSize(final String s) {
        try {
            this.fontSize = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            this.fontSize = 10;
        }
        this.font = new Font(this.fontFace, this.fontStyle, this.fontSize);
    }
    
    public void setFontSize(final String rightFontSize) {
        this.setRightFontSize(rightFontSize);
    }
    
    public void setLeftCenter(final String s) {
    }
    
    public void setRightCenter(final String s) {
    }
    
    public void setCenter(final String s) {
    }
    
    public void setLeftBold(final String s) {
    }
    
    public void setRightBold(final String s) {
        if (Boolean.valueOf(s)) {
            this.fontStyle |= 0x1;
        }
        else {
            this.fontStyle &= 0xFFFFFFFE;
        }
        this.font = new Font(this.fontFace, this.fontStyle, this.fontSize);
    }
    
    public void setBold(final String rightBold) {
        this.setRightBold(rightBold);
    }
    
    public void setLeftItalic(final String s) {
    }
    
    public void setRightItalic(final String s) {
        if (Boolean.valueOf(s)) {
            this.fontStyle |= 0x2;
        }
        else {
            this.fontStyle &= 0xFFFFFFFD;
        }
        this.font = new Font(this.fontFace, this.fontStyle, this.fontSize);
    }
    
    public void setItalic(final String rightItalic) {
        this.setRightItalic(rightItalic);
    }
    
    public void setInset(final String s) {
        try {
            this.textInset = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {}
    }
    
    public String[] wrapForWidth(final int n) {
        final Vector vector = new Vector<String>();
        String s = "";
        int n2 = 0;
        FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.font);
        while (true) {
            final String nextToken = this.nextToken();
            if (nextToken == null) {
                break;
            }
            if (nextToken.equals("\n")) {
                if (n2 != 0) {
                    vector.addElement(s.trim());
                    vector.addElement("");
                    s = "";
                }
                else {
                    n2 = 1;
                    s = String.valueOf(s.trim()) + " ";
                }
            }
            else {
                n2 = 0;
                if (nextToken.startsWith("^^")) {
                    if (s.length() > 0) {
                        vector.addElement(s.trim());
                        s = "";
                    }
                    String string = nextToken;
                    while (true) {
                        final String nextToken2 = this.nextToken();
                        if (nextToken2 == null || nextToken2.equals("\n")) {
                            break;
                        }
                        string = String.valueOf(string) + nextToken2;
                    }
                    this.directiveManager.performDirective(string);
                    fontMetrics = this.getToolkit().getFontMetrics(this.font);
                    s = s.trim();
                    vector.addElement(string);
                }
                else if (fontMetrics.stringWidth(String.valueOf(s) + nextToken) > n - 2 - this.textInset) {
                    if (s.length() == 0) {
                        vector.addElement(nextToken);
                    }
                    else {
                        vector.addElement(s.trim());
                        s = nextToken;
                    }
                }
                else {
                    s = String.valueOf(s.trim()) + " " + nextToken.trim();
                }
            }
        }
        if (s.length() > 0) {
            vector.addElement(s.trim());
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public String nextToken() {
        while (!this.tokenizer.hasMoreTokens()) {
            if (this.lineNum == this.data.length - 1) {
                return null;
            }
            this.tokenizer = new StringTokenizer(this.data[++this.lineNum], " \t\n\r", true);
        }
        return this.tokenizer.nextToken();
    }
    
    public void unGet(final String s) {
        String string = "";
        while (this.tokenizer.hasMoreTokens()) {
            string = String.valueOf(string) + this.tokenizer.nextToken();
        }
        this.tokenizer = new StringTokenizer(String.valueOf(s) + string, " \t\n\r", true);
    }
}
