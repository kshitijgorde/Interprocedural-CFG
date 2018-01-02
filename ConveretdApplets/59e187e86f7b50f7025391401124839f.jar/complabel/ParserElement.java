// 
// Decompiled by Procyon v0.5.30
// 

package complabel;

import gfx.Colors;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Vector;

public class ParserElement
{
    public String text;
    public Vector flags;
    public String font;
    public String size;
    public String fgString;
    public String bgString;
    
    public ParserElement() {
        this.flags = new Vector();
    }
    
    public ParserElement(final ParserElement parserElement) {
        this.flags = new Vector();
        if (parserElement != null) {
            if (parserElement.flags != null) {
                this.flags = (Vector)parserElement.flags.clone();
            }
            if (parserElement.font != null) {
                this.font = new String(parserElement.font);
            }
            if (parserElement.size != null) {
                this.size = new String(parserElement.size);
            }
            if (parserElement.fgString != null) {
                this.fgString = new String(parserElement.fgString);
            }
            if (parserElement.bgString != null) {
                this.bgString = new String(parserElement.bgString);
            }
        }
    }
    
    public String toString() {
        String s = this.text + ":";
        final Enumeration<String> elements = this.flags.elements();
        while (elements.hasMoreElements()) {
            s = s + elements.nextElement() + " ";
        }
        if (this.font != null) {
            s = s + " f:" + this.font;
        }
        if (this.size != null) {
            s = s + " s:" + this.size;
        }
        if (this.fgString != null) {
            s = s + " c:" + this.fgString;
        }
        if (this.bgString != null) {
            s = s + " d:" + this.bgString;
        }
        return s;
    }
    
    public TextComp toTextComp() {
        if (this.text == null || this.text.length() < 1) {
            return null;
        }
        int n = 0;
        if (this.flags.contains("b")) {
            n |= 0x1;
        }
        if (this.flags.contains("i")) {
            n |= 0x2;
        }
        if (n == 0) {
            n = 0;
        }
        int int1 = 13;
        if (this.size != null) {
            int1 = Integer.parseInt(this.size);
        }
        return new TextComp(this.text, new Font(this.getFontName(this.font), n, int1), Colors.getAWTColor(this.fgString), Colors.getAWTColor(this.bgString));
    }
    
    String getFontName(final String s) {
        if (s == null) {
            return "SansSerif";
        }
        if (s.equals("sans")) {
            return "SansSerif";
        }
        if (s.equals("serif")) {
            return "Serif";
        }
        if (s.equals("mono")) {
            return "Courier";
        }
        if (s.equals("courier")) {
            return "Courier";
        }
        return s;
    }
}
