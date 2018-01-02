import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class VecPanel extends Panel
{
    private static final char LINE_DELIMITER = '|';
    private static final int CHAR_DEFINITION_WIDTH = 6;
    private static final int CHAR_DEFINITION_HEIGHT = 6;
    private int m_iScaleX;
    private int m_iScaleY;
    private Image m_imgBuffer;
    private Vector m_vecTextLines;
    private Vector m_vecTextLayout;
    private Vector m_vecTextColour;
    private Color m_clrBorder;
    private String m_strText;
    private int m_iTextLen;
    private boolean m_bCentreHorizontally;
    private boolean m_bCentreVertically;
    private boolean m_bShowBorder;
    private int m_iBorderOffset;
    private String m_strMessage;
    Point[][] m_arrCharSet;
    
    public void setBorderColour(final Color clrBorder) {
        this.m_clrBorder = clrBorder;
    }
    
    public int getBorderOffset() {
        return this.m_iBorderOffset;
    }
    
    public void setBorderOffset(final int newVal) {
        this.m_iBorderOffset = newVal;
    }
    
    public void paint(final Graphics g) {
        if (this.m_imgBuffer != null) {
            g.drawImage(this.m_imgBuffer, 0, 0, this);
        }
        else {
            final FontMetrics fm = g.getFontMetrics();
            final Dimension dimSize = this.getSize();
            g.drawString(this.m_strMessage, dimSize.width / 2 - fm.stringWidth(this.m_strMessage) / 2, dimSize.height / 2 - fm.getHeight() / 2);
        }
    }
    
    public boolean getCentreVertically() {
        return this.m_bCentreVertically;
    }
    
    public void setCentreVertically(final boolean newVal) {
        this.m_bCentreVertically = newVal;
        this.layoutText(this.m_strText, this.m_bCentreHorizontally, this.m_bCentreVertically);
    }
    
    public VecPanel() {
        this.m_iScaleX = 3;
        this.m_iScaleY = 5;
        this.m_vecTextLayout = new Vector();
        this.m_vecTextColour = new Vector();
        this.m_clrBorder = Color.black;
        this.m_iTextLen = 0;
        this.m_bCentreHorizontally = true;
        this.m_bCentreVertically = true;
        this.m_bShowBorder = true;
        this.m_iBorderOffset = 10;
        this.m_strMessage = "";
        this.m_arrCharSet = new Point[][] { { new Point(0, 4), new Point(0, 2), new Point(2, 0), new Point(4, 2), new Point(4, 4), null, new Point(0, 2), new Point(4, 2) }, { new Point(0, 0), new Point(3, 0), new Point(4, 1), new Point(3, 2), new Point(4, 3), new Point(3, 4), new Point(0, 4), new Point(0, 0), null, new Point(0, 2), new Point(3, 2) }, { new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1) }, { new Point(0, 0), new Point(3, 0), new Point(4, 1), new Point(4, 3), new Point(3, 4), new Point(0, 4), new Point(0, 0) }, { new Point(4, 0), new Point(0, 0), new Point(0, 4), new Point(4, 4), null, new Point(0, 2), new Point(3, 2) }, { new Point(4, 0), new Point(0, 0), new Point(0, 4), null, new Point(0, 2), new Point(3, 2) }, { new Point(3, 2), new Point(4, 2), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1) }, { new Point(0, 0), new Point(0, 4), null, new Point(0, 2), new Point(4, 2), null, new Point(4, 0), new Point(4, 4) }, { new Point(0, 0), new Point(4, 0), null, new Point(2, 0), new Point(2, 4), null, new Point(0, 4), new Point(4, 4) }, { new Point(4, 0), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3) }, { new Point(0, 0), new Point(0, 4), null, new Point(0, 2), new Point(2, 2), null, new Point(4, 0), new Point(2, 2), new Point(4, 4) }, { new Point(0, 0), new Point(0, 4), new Point(4, 4) }, { new Point(0, 4), new Point(0, 0), new Point(2, 2), new Point(4, 0), new Point(4, 4) }, { new Point(0, 4), new Point(0, 0), new Point(4, 4), new Point(4, 0) }, { new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1), new Point(4, 3) }, { new Point(0, 4), new Point(0, 0), new Point(3, 0), new Point(4, 1), new Point(3, 2), new Point(0, 2) }, { new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1), new Point(4, 3), null, new Point(2, 2), new Point(4, 4) }, { new Point(0, 4), new Point(0, 0), new Point(3, 0), new Point(4, 1), new Point(3, 2), new Point(0, 2), null, new Point(3, 2), new Point(4, 3), new Point(4, 4) }, { new Point(4, 1), new Point(3, 0), new Point(1, 0), new Point(0, 1), new Point(1, 2), new Point(3, 2), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3) }, { new Point(0, 0), new Point(4, 0), null, new Point(2, 0), new Point(2, 4) }, { new Point(0, 0), new Point(0, 4), new Point(4, 4), new Point(4, 0) }, { new Point(0, 0), new Point(0, 2), new Point(2, 4), new Point(4, 2), new Point(4, 0) }, { new Point(0, 0), new Point(0, 4), new Point(2, 2), new Point(4, 4), new Point(4, 0) }, { new Point(0, 0), new Point(4, 4), null, new Point(0, 4), new Point(4, 0) }, { new Point(0, 0), new Point(2, 2), new Point(4, 0), null, new Point(2, 2), new Point(2, 4) }, { new Point(0, 0), new Point(4, 0), new Point(0, 4), new Point(4, 4) }, { new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1), new Point(4, 3), null, new Point(1, 4), new Point(3, 0) }, { new Point(0, 4), new Point(4, 4), null, new Point(0, 2), new Point(2, 0), new Point(2, 4) }, { new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1), new Point(3, 2), new Point(1, 2), new Point(0, 3), new Point(0, 4), new Point(4, 4) }, { new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), null, new Point(2, 2), new Point(4, 2) }, { new Point(0, 0), new Point(0, 2), new Point(4, 2), null, new Point(2, 0), new Point(2, 4) }, { new Point(4, 0), new Point(0, 0), new Point(0, 2), new Point(3, 2), new Point(4, 3), new Point(3, 4), new Point(0, 4) }, { new Point(0, 2), new Point(3, 2), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 1) }, { new Point(0, 0), new Point(4, 0), new Point(0, 4) }, { new Point(1, 0), new Point(3, 0), new Point(4, 1), new Point(3, 2), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3), new Point(1, 2), new Point(0, 1), new Point(1, 0), null, new Point(1, 2), new Point(3, 2) }, { new Point(4, 2), new Point(1, 2), new Point(0, 1), new Point(1, 0), new Point(3, 0), new Point(4, 3), new Point(3, 4), new Point(1, 4), new Point(0, 3) } };
    }
    
    public void setScaleY(final int y) {
        this.m_iScaleY = y;
    }
    
    public void setMessage(final String strText) {
        this.m_strMessage = strText;
        this.m_imgBuffer = null;
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public int getTextLen() {
        return this.m_iTextLen;
    }
    
    public boolean getCentreHorizontally() {
        return this.m_bCentreHorizontally;
    }
    
    public void setCentreHorizontally(final boolean newVal) {
        this.m_bCentreHorizontally = newVal;
        this.layoutText(this.m_strText, this.m_bCentreHorizontally, this.m_bCentreVertically);
    }
    
    private void layoutText(final String strText, final boolean bCentreHorizontally, final boolean bCentreVertically) {
        this.m_iTextLen = 0;
        if (strText == null) {
            return;
        }
        final Color clrText = this.getForeground();
        this.m_vecTextLines = new Vector();
        this.m_vecTextColour = new Vector();
        int iLineBegin = 0;
        int iMaxLineLength = 0;
        int iPos;
        for (iPos = 0; iPos < strText.length(); ++iPos) {
            if (strText.charAt(iPos) == '|') {
                this.m_vecTextLines.addElement(strText.substring(iLineBegin, iPos));
                iMaxLineLength = Math.max(iPos - iLineBegin, iMaxLineLength);
                iLineBegin = iPos + 1;
            }
            else {
                ++this.m_iTextLen;
            }
        }
        iMaxLineLength = Math.max(iPos - iLineBegin, iMaxLineLength);
        this.m_vecTextLines.addElement(strText.substring(iLineBegin, iPos));
        this.m_vecTextLayout = new Vector();
        int iY = 0;
        int iBoundsY1;
        int iBoundsY2;
        if (bCentreVertically) {
            iY = -(6 * this.m_vecTextLines.size()) / 2;
            iBoundsY1 = -(6 * this.m_vecTextLines.size()) / 2 - this.m_iBorderOffset;
            iBoundsY2 = 6 * this.m_vecTextLines.size() / 2 + this.m_iBorderOffset;
        }
        else {
            iBoundsY1 = -6 - this.m_iBorderOffset;
            iBoundsY2 = 6 * this.m_vecTextLines.size() + this.m_iBorderOffset;
        }
        final int iBoundsX1 = -(6 * iMaxLineLength) / 2 - this.m_iBorderOffset;
        final int iBoundsX2 = 6 * iMaxLineLength / 2 + this.m_iBorderOffset;
        if (this.m_bShowBorder) {
            final Point[] arrBorder = { new Point(this.m_iScaleX * iBoundsX1, this.m_iScaleY * iBoundsY1), new Point(this.m_iScaleX * iBoundsX2, this.m_iScaleY * iBoundsY1), new Point(this.m_iScaleX * iBoundsX2, this.m_iScaleY * iBoundsY2), new Point(this.m_iScaleX * iBoundsX1, this.m_iScaleY * iBoundsY2), new Point(this.m_iScaleX * iBoundsX1, this.m_iScaleY * iBoundsY1) };
            this.m_vecTextLayout.addElement(arrBorder);
            this.m_vecTextColour.addElement(this.m_clrBorder);
        }
        for (int iLine = 0; iLine < this.m_vecTextLines.size(); ++iLine) {
            final String strLine = this.m_vecTextLines.elementAt(iLine);
            int iX;
            if (bCentreHorizontally) {
                iX = -(6 * strLine.length()) / 2;
            }
            else {
                iX = -(6 * iMaxLineLength) / 2;
            }
            for (int iChar = 0; iChar < strLine.length(); ++iChar) {
                final Point[] arrCharDef = this.getCharDefinition(strLine.charAt(iChar));
                if (arrCharDef != null) {
                    final Point[] arrCharLayout = new Point[arrCharDef.length];
                    for (int iPoint = 0; iPoint < arrCharDef.length; ++iPoint) {
                        final Point pt = arrCharDef[iPoint];
                        if (pt == null) {
                            arrCharLayout[iPoint] = null;
                        }
                        else {
                            final int iPointX = this.m_iScaleX * (iX + pt.x);
                            final int iPointY = this.m_iScaleY * (iY + pt.y);
                            arrCharLayout[iPoint] = new Point(iPointX, iPointY);
                        }
                    }
                    this.m_vecTextLayout.addElement(arrCharLayout);
                    this.m_vecTextColour.addElement(clrText);
                }
                iX += 6;
            }
            iY += 6;
        }
    }
    
    public void transformText(final float flRotX, final float flRotY, final float flRotZ, final int iOriginX, final int iOriginY, final int iOriginZ, int iLen) {
        final Dimension dimSize = this.getSize();
        this.m_imgBuffer = this.createImage(dimSize.width, dimSize.height);
        if (this.m_imgBuffer == null) {
            return;
        }
        final Graphics g = this.m_imgBuffer.getGraphics();
        final int iCentreX = dimSize.width / 2;
        final int iCentreY = dimSize.height / 2;
        final float flSinX = (float)Math.sin(flRotX);
        final float flCosX = (float)Math.cos(flRotX);
        final float flSinY = (float)Math.sin(flRotY);
        final float flCosY = (float)Math.cos(flRotY);
        final float flSinZ = (float)Math.sin(flRotZ);
        final float flCosZ = (float)Math.cos(flRotZ);
        if (iLen > this.m_vecTextLayout.size()) {
            iLen = this.m_vecTextLayout.size();
        }
        for (int iChar = 0; iChar < iLen; ++iChar) {
            final Point[] arrChar = this.m_vecTextLayout.elementAt(iChar);
            final Color clr = this.m_vecTextColour.elementAt(iChar);
            g.setColor(clr);
            boolean bMoveTo = true;
            int iCurrentX = 0;
            int iCurrentY = 0;
            for (int i = 0; i < arrChar.length; ++i) {
                final Point pt = arrChar[i];
                if (pt == null) {
                    bMoveTo = true;
                }
                else {
                    final float flX1 = pt.x;
                    final float flY1 = pt.y;
                    final float flZ1 = 0.0f;
                    final float flX2 = flX1 * flCosZ - flY1 * flSinZ;
                    final float flY2 = flX1 * flSinZ + flY1 * flCosZ;
                    float flX3 = flX2 * flCosY - flZ1 * flSinY;
                    final float flZ2 = flX2 * flSinY + flZ1 * flCosY;
                    float flY3 = flY2 * flCosX - flZ2 * flSinX;
                    final float flZ3 = flY2 * flSinX + flZ2 * flCosX;
                    flX3 += iOriginX;
                    flY3 += iOriginY;
                    final float flPerspectiveScale = 500.0f / (iOriginZ + flZ3);
                    final int iNewX = (int)(iCentreX + this.m_iScaleX * flPerspectiveScale * flX3);
                    final int iNewY = (int)(iCentreY + this.m_iScaleY * flPerspectiveScale * flY3);
                    if (bMoveTo) {
                        bMoveTo = false;
                    }
                    else {
                        g.drawLine(iCurrentX, iCurrentY, iNewX, iNewY);
                    }
                    iCurrentX = iNewX;
                    iCurrentY = iNewY;
                }
            }
        }
        this.repaint();
    }
    
    Point[] getCharDefinition(final char ch) {
        final int ALPHA_START = 0;
        final int DIGIT_START = 26;
        if (Character.isLetter(ch)) {
            final int index = Character.toUpperCase(ch) - 'A';
            return this.m_arrCharSet[index];
        }
        if (Character.isDigit(ch)) {
            final int index = ch - '0' + '\u001a';
            return this.m_arrCharSet[index];
        }
        return null;
    }
    
    public boolean getShowBorder() {
        return this.m_bShowBorder;
    }
    
    public void setShowBorder(final boolean newVal) {
        this.m_bShowBorder = newVal;
        this.layoutText(this.m_strText, this.m_bCentreHorizontally, this.m_bCentreVertically);
    }
    
    public void setScaleX(final int x) {
        this.m_iScaleX = x;
    }
    
    public void setText(final String strText) {
        this.layoutText(this.m_strText = strText, this.m_bCentreHorizontally, this.m_bCentreVertically);
    }
    
    public String getText() {
        return this.m_strText;
    }
}
