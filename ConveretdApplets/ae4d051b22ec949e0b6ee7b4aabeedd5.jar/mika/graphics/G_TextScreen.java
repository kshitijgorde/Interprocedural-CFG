// 
// Decompiled by Procyon v0.5.30
// 

package mika.graphics;

import java.awt.Graphics;
import java.util.Vector;
import mika.system.S_TextReader;
import java.awt.Color;
import java.awt.Font;

public class G_TextScreen
{
    int m_iLineCnt;
    String[][] m_strText;
    Font[][] m_fntFont;
    int[][] m_iStyle;
    Color[][] m_clrColor;
    int m_iFontCnt;
    G_Font[] m_fntFontList;
    final int RIGHT_ALIGN = 0;
    final int LEFT_ALIGN = 1;
    final int CENTER = 2;
    String[] m_strToken;
    int[] m_iToken;
    int m_iTokenCnt;
    
    public G_TextScreen() {
        this.m_strToken = new String[] { "<right>", "<left>", "<center>" };
        this.m_iToken = new int[] { 0, 1, 2 };
        this.m_iTokenCnt = this.m_strToken.length;
    }
    
    public void readTextScreen(final S_TextReader s_TextReader) throws Exception {
        this.readFonts(s_TextReader);
        this.readText(s_TextReader);
    }
    
    public void readFonts(final S_TextReader s_TextReader) throws Exception {
        this.m_iFontCnt = s_TextReader.readIntegerValue();
        this.m_fntFontList = new G_Font[this.m_iFontCnt];
        for (int i = 0; i < this.m_iFontCnt; ++i) {
            this.m_fntFontList[i] = new G_Font(s_TextReader);
        }
    }
    
    public void cloneFonts(final G_TextScreen g_TextScreen) throws Exception {
        this.m_iFontCnt = g_TextScreen.m_iFontCnt;
        this.m_fntFontList = new G_Font[this.m_iFontCnt];
        for (int i = 0; i < this.m_iFontCnt; ++i) {
            this.m_fntFontList[i] = new G_Font(g_TextScreen.m_fntFontList[i]);
        }
    }
    
    public void readText(final S_TextReader s_TextReader) throws Exception {
        this.m_iLineCnt = s_TextReader.readIntegerValue();
        this.m_strText = new String[this.m_iLineCnt][];
        this.m_fntFont = new Font[this.m_iLineCnt][];
        this.m_iStyle = new int[this.m_iLineCnt][];
        this.m_clrColor = new Color[this.m_iLineCnt][];
        Integer n2;
        Integer n = n2 = new Integer(1);
        Font font2;
        Font font = font2 = this.m_fntFontList[0].getFont();
        Color color2;
        Color color = color2 = new Color(0, 0, 0);
        final Vector vector = new Vector<String>();
        final Vector<Font> vector2 = new Vector<Font>();
        final Vector<Integer> vector3 = new Vector<Integer>();
        final Vector<Color> vector4 = new Vector<Color>();
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < this.m_iLineCnt; ++i) {
            final String line = s_TextReader.readLine();
            int j = 0;
            if (n3 != 0) {
                j = n4;
            }
            n3 = 0;
            int n5 = j;
            while (j < line.length()) {
                boolean b = false;
                if (line.charAt(j) == '<') {
                    if (line.length() > j + 1 && (line.charAt(j + 1) == 'f' || line.charAt(j + 1) == 'F')) {
                        if (j - n5 > 0) {
                            vector.addElement(line.substring(n5, j));
                            vector2.addElement(font);
                            vector3.addElement(n);
                            vector4.addElement(color);
                        }
                        int n6;
                        for (n6 = j + 2; n6 < line.length() && line.charAt(n6) != '>'; ++n6) {}
                        font2 = this.m_fntFontList[Integer.valueOf(line.substring(j + 2, n6))].getFont();
                        j = (n5 = n6 + 1);
                        b = true;
                    }
                    else if (line.length() > j + 1 && (line.charAt(j + 1) == 'c' || line.charAt(j + 1) == 'C')) {
                        if (j - n5 > 0) {
                            vector.addElement(line.substring(n5, j));
                            vector2.addElement(font);
                            vector3.addElement(n);
                            vector4.addElement(color);
                        }
                        int n7;
                        for (n7 = j + 2; n7 < line.length() && line.charAt(n7) == ' '; ++n7) {}
                        final int n8 = n7;
                        while (n7 < line.length() && line.charAt(n7) >= '0' && line.charAt(n7) <= '9') {
                            ++n7;
                        }
                        final int intValue = Integer.valueOf(line.substring(n8, n7));
                        while (n7 < line.length() && line.charAt(n7) == ' ') {
                            ++n7;
                        }
                        final int n9 = n7;
                        while (n7 < line.length() && line.charAt(n7) >= '0' && line.charAt(n7) <= '9') {
                            ++n7;
                        }
                        final int intValue2 = Integer.valueOf(line.substring(n9, n7));
                        while (n7 < line.length() && line.charAt(n7) == ' ') {
                            ++n7;
                        }
                        final int n10 = n7;
                        while (n7 < line.length() && line.charAt(n7) >= '0' && line.charAt(n7) <= '9') {
                            ++n7;
                        }
                        final int intValue3 = Integer.valueOf(line.substring(n10, n7));
                        while (n7 < line.length() && line.charAt(n7) == ' ') {
                            ++n7;
                        }
                        while (n7 < line.length() && line.charAt(n7) != '>') {
                            ++n7;
                        }
                        color2 = new Color(intValue, intValue2, intValue3);
                        j = (n5 = n7 + 1);
                        b = true;
                    }
                    else if (line.length() > j + 2 && (line.charAt(j + 1) == 'b' || line.charAt(j + 1) == 'B') && (line.charAt(j + 2) == 'r' || line.charAt(j + 2) == 'R')) {
                        for (j += 3; j < line.length() && line.charAt(j) != '>'; ++j) {}
                        n4 = j + 1;
                        n3 = 1;
                        b = true;
                    }
                    else {
                        final int k = 0;
                        while (k < this.m_iTokenCnt) {
                            if (line.regionMatches(true, j, this.m_strToken[k], 0, this.m_strToken[k].length())) {
                                if (j - n5 > 0) {
                                    vector.addElement(line.substring(n5, j));
                                    vector2.addElement(font);
                                    vector3.addElement(n);
                                    vector4.addElement(color);
                                }
                                n2 = new Integer(this.m_iToken[k]);
                                b = true;
                                j = (n5 = j + this.m_strToken[k].length());
                                break;
                            }
                            ++j;
                        }
                    }
                }
                if (!b && j == line.length() - 1 && j - n5 > 1) {
                    vector.addElement(line.substring(n5, j + 1));
                    vector2.addElement(font);
                    vector3.addElement(n);
                    vector4.addElement(color);
                }
                if (n3 != 0) {
                    break;
                }
                n = n2;
                font = font2;
                color = color2;
                ++j;
            }
            int size = vector.size();
            if (size == 0) {
                size = 1;
            }
            this.m_strText[i] = new String[size];
            this.m_fntFont[i] = new Font[size];
            this.m_iStyle[i] = new int[size];
            this.m_clrColor[i] = new Color[size];
            if (vector.size() == 0) {
                vector.addElement("");
                vector2.addElement(font);
                vector3.addElement(n);
                vector4.addElement(color);
            }
            else {
                for (int l = 0; l < size; ++l) {
                    this.m_strText[i][l] = vector.elementAt(l);
                    this.m_fntFont[i][l] = vector2.elementAt(l);
                    this.m_iStyle[i][l] = vector3.elementAt(l);
                    this.m_clrColor[i][l] = vector4.elementAt(l);
                }
            }
            vector.removeAllElements();
            vector2.removeAllElements();
            vector3.removeAllElements();
            vector4.removeAllElements();
        }
    }
    
    public void print(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n2;
        if (this.m_fntFont.length > 0 && this.m_fntFont[0].length > 0) {
            graphics.setFont(this.m_fntFont[0][0]);
            n5 += graphics.getFontMetrics().getHeight();
        }
        for (int i = 0; i < this.m_strText.length; ++i) {
            int j = 0;
            while (j < this.m_strText[i].length) {
                final int n6 = j;
                int n8;
                if (this.m_iStyle[i][j] == 2) {
                    int n7 = 0;
                    while (j < this.m_strText[i].length && this.m_iStyle[i][j] == 2) {
                        graphics.setFont(this.m_fntFont[i][j]);
                        n7 += graphics.getFontMetrics().stringWidth(this.m_strText[i][j]);
                        ++j;
                    }
                    n8 = n + (n3 - n7) / 2;
                }
                else if (this.m_iStyle[i][j] == 0) {
                    int n9 = 0;
                    while (j < this.m_strText[i].length && this.m_iStyle[i][j] == 0) {
                        graphics.setFont(this.m_fntFont[i][j]);
                        n9 += graphics.getFontMetrics().stringWidth(this.m_strText[i][j]);
                        ++j;
                    }
                    n8 = n + n3 - n9;
                }
                else {
                    int n10 = 0;
                    while (j < this.m_strText[i].length && this.m_iStyle[i][j] == 1) {
                        graphics.setFont(this.m_fntFont[i][j]);
                        n10 += graphics.getFontMetrics().stringWidth(this.m_strText[i][j]);
                        ++j;
                    }
                    n8 = n;
                }
                for (int k = n6; k < j; ++k) {
                    graphics.setFont(this.m_fntFont[i][k]);
                    graphics.setColor(this.m_clrColor[i][k]);
                    graphics.drawString(this.m_strText[i][k], n8, n5);
                    n8 += graphics.getFontMetrics().stringWidth(this.m_strText[i][k]);
                }
            }
            n5 += graphics.getFontMetrics().getHeight();
        }
    }
}
