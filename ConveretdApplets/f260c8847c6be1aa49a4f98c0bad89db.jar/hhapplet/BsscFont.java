// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Font;
import java.awt.Color;

public class BsscFont
{
    private boolean m_bUnderline;
    private Color m_color;
    private Font m_font;
    
    public BsscFont(final String s, final String s2, final String s3, final String s4, final Color color, final String s5) {
        final String lowerCase = s.toLowerCase();
        String s6 = "Dialog";
        if (lowerCase.indexOf("batang") == 0 || lowerCase.indexOf("bookman old style") == 0 || lowerCase.indexOf("courier") == 0 || lowerCase.indexOf("garamond") == 0 || lowerCase.indexOf("georgia") == 0 || lowerCase.indexOf("mingliu") == 0 || lowerCase.indexOf("monotype corsiva") == 0 || lowerCase.indexOf("times") == 0 || lowerCase.indexOf("palatino linotype") == 0 || lowerCase.indexOf("pmingliu") == 0) {
            s6 = "Serif";
        }
        else if (lowerCase.indexOf("marlett") == 0 || lowerCase.indexOf("ms outlook") == 0 || lowerCase.indexOf("symbol") == 0 || lowerCase.indexOf("webdings") == 0 || lowerCase.indexOf("wingdings") == 0) {
            s6 = "ZapfDingbats";
        }
        this.m_color = color;
        int n = 0;
        if (s4.equals("bolder") || s4.equals("bold")) {
            n |= 0x1;
        }
        else {
            try {
                if (Integer.parseInt(s4) >= 700) {
                    n |= 0x1;
                }
            }
            catch (NumberFormatException ex) {}
        }
        if (s3.equals("italic")) {
            n |= 0x2;
        }
        final int index = s2.indexOf("pt");
        int int1 = 10;
        if (index >= 0) {
            try {
                int1 = Integer.parseInt(s2.substring(0, index));
                int1 = (int)Math.ceil(int1 * 1.323);
            }
            catch (NumberFormatException ex2) {}
        }
        else if (s2.equals("xx-small")) {
            int1 = 4;
        }
        else if (s2.equals("x-small")) {
            int1 = 6;
        }
        else if (s2.equals("small")) {
            int1 = 8;
        }
        else if (s2.equals("medium")) {
            int1 = 10;
        }
        else if (s2.equals("large")) {
            int1 = 12;
        }
        else if (s2.equals("x-large")) {
            int1 = 15;
        }
        else if (s2.equals("xx-large")) {
            int1 = 18;
        }
        else {
            int1 = 10;
        }
        this.m_font = new Font(s6, n, int1);
        if (s5.equals("underline")) {
            this.m_bUnderline = true;
            return;
        }
        this.m_bUnderline = false;
    }
    
    public Color getColor() {
        return this.m_color;
    }
    
    public Font getFont() {
        return this.m_font;
    }
    
    public boolean isUnderline() {
        return this.m_bUnderline;
    }
}
