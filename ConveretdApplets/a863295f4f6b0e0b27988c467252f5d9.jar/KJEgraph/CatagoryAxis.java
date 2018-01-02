// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

public class CatagoryAxis extends Axis
{
    public float _fSpacingPercent;
    public float _fDataGapPercent;
    private String[] _sCatagories;
    private int _iCatagoryCount;
    private int _iBarCount;
    private int _iSpaceCount;
    private int _iGapCount;
    private int _iBarWidth;
    private int _iSpaceWidth;
    private int _iGapWidth;
    private int _iBarsPerCatagory;
    private int _iAxisSegmentLength;
    
    public CatagoryAxis(final int n) {
        super(n);
        this._fSpacingPercent = 0.75f;
        this._fDataGapPercent = 0.0f;
    }
    
    protected void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        switch (super._iOrientation) {
            case 1: {
                graphics.drawLine(n4 + n, n3 + n2, n6 + n, n5 + n2);
                break;
            }
            case 0: {
                graphics.drawLine(super._iHeight - n4 + n, n3 + n2, super._iHeight - n6 + n, n5 + n2);
                break;
            }
            case 4: {
                graphics.drawLine(n3 + n, n4 + n2, n5 + n, n6 + n2);
                break;
            }
        }
    }
    
    public void drawText(final Graphics graphics, final int n, final int n2, final String s, final Image image, final int n3, final int n4, final int n5) {
        switch (super._iOrientation) {
            case 1: {
                graphics.drawString(s, n + n4, n2 + n3 + (super._iFontHeight - super._iFontDescent) / 2);
                break;
            }
            case 0: {
                graphics.drawString(s, n + super._iHeight - n4 - graphics.getFontMetrics().stringWidth(s), n2 + n3 + (super._iFontHeight - super._iFontDescent) / 2);
                break;
            }
            case 4: {
                if (super._bUseTextImages) {
                    graphics.drawImage(image, n + n3 - super._iFontHeight / 2, n2 + n4, super._gGraph);
                    break;
                }
                graphics.drawString(s, n + n3 - graphics.getFontMetrics().stringWidth(s) / 2, n2 + n5);
                break;
            }
        }
    }
    
    public int getBarWidth() {
        return this._iBarWidth;
    }
    
    public int getCatagoryLeft(final int n) {
        return Math.round(super._iWidth / this._iCatagoryCount * n);
    }
    
    public int getCatagoryMid(final int n) {
        return Math.round(super._iWidth / this._iCatagoryCount * n) + Math.round((this._iBarsPerCatagory * this._iBarWidth + (this._iBarsPerCatagory - 1) * this._iGapWidth) / 2) + this._iSpaceWidth;
    }
    
    public int getDataWidth() {
        return this._iGapWidth + this._iBarWidth;
    }
    
    public int getGapWidth() {
        return this._iGapWidth;
    }
    
    public int getHeight() {
        return (super._iOrientation == 1 || super._iOrientation == 0) ? super._iWidth : super._iHeight;
    }
    
    private int getMaximumCatagoryLength(final Graphics graphics, final int n, final String[] array) {
        if (array == null) {
            return 0;
        }
        int n2 = 0;
        super._iTextImages = new Image[array.length];
        for (int n3 = 0; n3 < n && n3 < array.length; ++n3) {
            if ((super._iOrientation == 3 || super._iOrientation == 4) && super._gGraph._bUseTextImages) {
                super._iTextImages[n3] = Axis.getVerticalText(array[n3], graphics, super._gGraph, super._gGraph.getBackground());
            }
            final int stringWidth = graphics.getFontMetrics().stringWidth(array[n3]);
            if (stringWidth > n2) {
                n2 = stringWidth;
            }
        }
        return n2;
    }
    
    public int getSpaceWidth() {
        return this._iSpaceWidth;
    }
    
    public int getWidth() {
        return (super._iOrientation == 1 || super._iOrientation == 0) ? super._iHeight : super._iWidth;
    }
    
    public void paint(final Graphics graphics, int n, int n2, final int n3, final int n4) {
        if (super._bShow) {
            if (super._iOrientation == 4) {
                n2 += n4;
            }
            else if (super._iOrientation == 1) {
                n += n3;
            }
            graphics.setColor(super._cColor);
            graphics.setFont(super._fFont);
            final int n5 = super._iFontHeight - super._iFontDescent + 3 + 5;
            final int n6 = 8;
            int n7 = 1;
            if (super._iFontHeight > this._iAxisSegmentLength) {
                n7 = super._iFontHeight / ((this._iAxisSegmentLength > 0) ? this._iAxisSegmentLength : 1) + 1;
            }
            for (int i = 0; i < this._iCatagoryCount; ++i) {
                int catagoryLeft = this.getCatagoryLeft(i);
                final int catagoryMid = this.getCatagoryMid(i);
                if (i == 0) {
                    --catagoryLeft;
                }
                this.drawLine(graphics, n, n2, catagoryLeft, 0, catagoryLeft, 3);
                if (i % n7 == 0 && i < this._sCatagories.length) {
                    this.drawText(graphics, n, n2, this._sCatagories[i], super._iTextImages[i], catagoryMid, n6, n5);
                }
            }
            this.drawLine(graphics, n, n2, 0, 0, super._iWidth, 0);
        }
    }
    
    public void setINF(final Graphics graphics, final Graph gGraph) {
        super._gGraph = gGraph;
        this._iCatagoryCount = gGraph.getValueCount();
        this._sCatagories = gGraph.getGraphCatagories();
        super._fFont = gGraph.FONT_PLAIN;
        super._cColor = gGraph.getForeground();
        super._iFontHeight = 0;
        super._iFontDescent = 0;
        super._iMaxLabelLength = 0;
        super._iHeight = 0;
        if (super._bShow) {
            super._iFontHeight = graphics.getFontMetrics().getHeight();
            super._iFontDescent = graphics.getFontMetrics().getDescent();
            graphics.setColor(super._cColor);
            graphics.setFont(super._fFont);
            super._iMaxLabelLength = this.getMaximumCatagoryLength(graphics, this._iCatagoryCount, this._sCatagories);
            super._iHeight = super._iMaxLabelLength + 5 + 3 + 1;
            super._bUseTextImages = gGraph._bUseTextImages;
        }
    }
    
    public void setSize(final Graphics graphics, final int iMaxLength) {
        graphics.setFont(super._fFont);
        this._iBarsPerCatagory = super._gGraph.getDataSeriesCount();
        this._iBarCount = this._iBarsPerCatagory * super._gGraph.getValueCount();
        this._iSpaceCount = 2 * super._gGraph.getValueCount();
        this._iGapCount = (this._iBarsPerCatagory - 1) * super._gGraph.getValueCount();
        super._iWidth = iMaxLength;
        super._iDataLength = iMaxLength;
        super._iMaxLength = iMaxLength;
        this._iBarWidth = super._iWidth / Math.round(this._iSpaceCount * this._fSpacingPercent + this._iBarCount + this._iGapCount * this._fDataGapPercent);
        this._iGapWidth = (int)(this._iBarWidth * this._fDataGapPercent);
        this._iSpaceWidth = (super._iDataLength - this._iBarWidth * this._iBarCount - this._iGapWidth * this._iGapCount) / this._iSpaceCount;
        this._iAxisSegmentLength = this._iSpaceWidth + this._iGapWidth * (this._iBarsPerCatagory - 1) + this._iBarWidth * this._iBarsPerCatagory;
        super._iXOffset = 0;
        super._iYOffset = 0;
        if (super._bShow) {
            if ((super._iMaxLabelLength > this._iAxisSegmentLength || super._iOrientation == 1 || super._iOrientation == 0) && super._gGraph._bUseTextImages) {
                super._iHeight = super._iMaxLabelLength + 5 + 3 + 1;
                super._bUseTextImages = true;
            }
            else {
                super._iHeight = super._iFontHeight + 5 + 3 + 1;
                super._bUseTextImages = false;
            }
            super._iXOffset = ((super._iOrientation == 0) ? this.getWidth() : 0);
        }
        else {
            super._iHeight = 0;
            super._bUseTextImages = false;
        }
    }
}
