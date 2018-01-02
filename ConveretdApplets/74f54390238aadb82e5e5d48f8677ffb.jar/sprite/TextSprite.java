// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

public class TextSprite extends Sprite
{
    public static final int CENTER = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    boolean m_bDoFill;
    boolean m_bShadow;
    boolean m_bUnderline;
    Color m_clrBG;
    Color m_clrFG;
    FontMetrics m_fm;
    Font m_font;
    int m_nAlign;
    String m_strText;
    
    public TextSprite(final Font font, final Color clrFg, final Color clrBg, final boolean bShadow, final boolean doFill) {
        this(font, clrFg, clrBg, bShadow);
        this.m_bDoFill = doFill;
    }
    
    public TextSprite(final Font font, final Color clrFg, final Color clrBg, final boolean bShadow) {
        this.m_strText = null;
        this.m_font = null;
        this.m_clrFG = null;
        this.m_clrBG = null;
        this.m_fm = null;
        this.m_nAlign = 0;
        this.m_bShadow = true;
        this.m_bDoFill = true;
        this.m_bUnderline = false;
        this.m_fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        this.m_font = font;
        this.m_clrFG = clrFg;
        this.m_clrBG = clrBg;
        this.m_bShadow = bShadow;
        this.m_bDoFill = true;
    }
    
    public void drawSprite(final Graphics g) {
        this.drawSprite(g, this.m_strText);
    }
    
    public void drawSprite(final Graphics g, final String txt) {
        if (txt == null || !super.m_bVisible) {
            return;
        }
        final int nDescent = this.m_fm.getMaxDescent();
        g.setFont(this.m_font);
        g.setColor(this.m_clrBG);
        int tx = super.m_nX;
        final int tw = this.m_fm.stringWidth(txt) + 4;
        if (this.m_nAlign == 1) {
            tx = super.m_nX - tw / 2;
        }
        else if (this.m_nAlign == 2) {
            tx = super.m_nX - tw;
        }
        if (this.m_bShadow) {
            g.drawString(txt, tx + 2, super.m_nY + 2 - nDescent);
            g.drawString(txt, tx, super.m_nY + 2 - nDescent);
            g.drawString(txt, tx + 2, super.m_nY - nDescent);
            g.drawString(txt, tx, super.m_nY - nDescent);
        }
        else if (this.m_bDoFill) {
            g.fillRect(super.m_rTemp.x, super.m_rTemp.y, super.m_rTemp.width, super.m_rTemp.height);
        }
        final int nY = super.m_nY + 1 - nDescent;
        g.setColor(this.m_clrFG);
        if (this.m_bUnderline) {
            g.drawLine(tx, nY, tx + tw - 2, nY);
        }
        g.drawString(txt, tx + 1, nY);
    }
    
    public Rectangle getBounding() {
        if (this.m_strText == null || this.m_strText.length() == 0) {
            super.m_rTemp.reshape(super.m_nX, super.m_nY, 0, 0);
        }
        else {
            int tx = super.m_nX;
            final int tw = this.m_fm.stringWidth(this.m_strText) + 4;
            if (this.m_nAlign == 1) {
                tx = super.m_nX - tw / 2;
            }
            else if (this.m_nAlign == 2) {
                tx = super.m_nX - tw;
            }
            super.m_rTemp.reshape(tx, super.m_nY - this.m_fm.getHeight(), tw, this.m_fm.getHeight() + 2);
        }
        return super.m_rTemp;
    }
    
    public String getText() {
        return this.m_strText;
    }
    
    public void setAlignment(final int nAlign) {
        this.m_nAlign = nAlign;
    }
    
    public void setColors(final Color clrFg, final Color clrBg) {
        this.m_clrFG = clrFg;
        this.m_clrBG = clrBg;
        this.addToBounding();
    }
    
    public void setText(final String str) {
        this.addToBounding();
        this.m_strText = str;
        this.addToBounding();
    }
    
    public void setUnderline(final boolean b) {
        this.m_bUnderline = true;
    }
}
