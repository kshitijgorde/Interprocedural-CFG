// 
// Decompiled by Procyon v0.5.30
// 

package Lb2_0;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Canvas;

public class pageCanvas extends Canvas
{
    String[] sText;
    String sFont;
    String sSans;
    String sSerif;
    Font[] f;
    Font fo;
    int iTextCount;
    int[] iX;
    int[] iY;
    int[] iTypeFace;
    int[] iTypeStyle;
    int[] iTypeSize;
    FlowLayout fl;
    
    public pageCanvas(final int i) {
        this.iTextCount = i + 1;
        this.sText = new String[this.iTextCount];
        this.f = new Font[this.iTextCount];
        this.iX = new int[this.iTextCount];
        this.iY = new int[this.iTextCount];
        this.iTypeFace = new int[this.iTextCount];
        this.iTypeStyle = new int[this.iTextCount];
        this.iTypeSize = new int[this.iTextCount];
        this.sSans = "Arial";
        this.sSerif = "TimesRoman";
    }
    
    public void putStuff(final int i, final String s, final int iFace, final int iStyle, final int iSize, final int x, final int y) {
        this.sText[i] = s;
        this.iX[i] = x;
        this.iY[i] = y;
        this.iTypeFace[i] = iFace;
        this.iTypeStyle[i] = iStyle;
        this.iTypeSize[i] = iSize;
        this.sFont = this.sSans;
        if (this.iTypeFace[i] == 1) {
            this.sFont = this.sSerif;
        }
        this.fo = new Font(this.sFont, this.iTypeStyle[i], this.iTypeSize[i]);
        this.f[i] = this.fo;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(600, 390);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMaximumSize() {
        return this.getMinimumSize();
    }
    
    public int getLineCount() {
        return this.iTextCount;
    }
    
    public String getString(final int i) {
        return this.sText[i];
    }
    
    public int getCursorX(final int i) {
        return this.iX[i];
    }
    
    public int getCursorY(final int i) {
        return this.iY[i];
    }
    
    public int getFace(final int i) {
        return this.iTypeFace[i];
    }
    
    public int getStyle(final int i) {
        return this.iTypeStyle[i];
    }
    
    public int getSize(final int i) {
        return this.iTypeSize[i];
    }
    
    public void paint(final Graphics g) {
        for (int i = 0; i < this.iTextCount; ++i) {
            g.setFont(this.f[i]);
            g.drawString(this.sText[i], this.iX[i], this.iY[i]);
        }
    }
}
