// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Component;
import KJEgui.Fmt;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

public class ValueAxis extends Axis
{
    public static final int DOLLARS = 1;
    public static final int PERCENT = 2;
    public static final int DEFAULT = 0;
    protected int _iFormat;
    protected float _fAbsoluteRange;
    protected float _fAbsoluteMinValue;
    protected float _fAbsoluteMaxValue;
    protected float _fRange;
    protected float _fMinValue;
    protected float _fMaxValue;
    protected int _iIncrement;
    private int _iFactor;
    protected float _fIncrement;
    private boolean _bDescending;
    public boolean _bAutoMaximum;
    public boolean _bAutoMinimum;
    public float _axisMinimum;
    public float _axisMaximum;
    public int _iZeroIntersects;
    
    public ValueAxis(final int n) {
        super(n);
        this._iFormat = 1;
        this._bDescending = true;
        this._bAutoMaximum = true;
        this._bAutoMinimum = false;
        this._axisMinimum = 0.0f;
        this._axisMaximum = 100.0f;
    }
    
    protected void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        switch (super._iOrientation) {
            case 4: {
                graphics.drawLine(n4 + n, super._iWidth - n3 + n2, n6 + n, super._iWidth - n5 + n2);
                break;
            }
            case 1: {
                graphics.drawLine(super._iWidth - n3 + n, n4 + n2, super._iWidth - n5 + n, n6 + n2);
                break;
            }
            case 0: {
                graphics.drawLine(n3 + n, n4 + n2, n5 + n, n6 + n2);
                break;
            }
        }
    }
    
    public void drawText(final Graphics graphics, final int n, final int n2, final String s, final Image image, final int n3, final int n4, final int n5) {
        final int stringWidth = graphics.getFontMetrics().stringWidth(s);
        final int n6 = (super._iOrientation == 0 || super._iOrientation == 1) ? (n5 + (super._iFontHeight - super._iFontDescent) / 2) : ((super._iOrientation == 4) ? (super._bUseTextImages ? n3 : (n3 + super._iFontHeight - super._iFontDescent)) : (super._bUseTextImages ? (super._iWidth - n3 - stringWidth) : (super._iWidth - n3 - super._iFontDescent)));
        final int n7 = (super._iOrientation == 3 || super._iOrientation == 4) ? (super._bUseTextImages ? n4 : (n5 - stringWidth / 2)) : ((super._iOrientation == 1) ? n3 : (super._iWidth - n3 - stringWidth));
        if (super._bUseTextImages) {
            if (image != null) {
                graphics.drawImage(image, n + n7, n2 + n6, super._gGraph);
            }
        }
        else {
            graphics.drawString(s, n + n7, n2 + n6);
        }
    }
    
    public int getHeight() {
        return (super._iOrientation == 3 || super._iOrientation == 4) ? super._iWidth : super._iHeight;
    }
    
    public String getLabel(final float n) {
        return (super._gGraph._sValueLabels == null) ? ((this._iFormat == 2) ? Fmt.percent(n, 1) : ((this._iFormat == 1) ? ((this._fAbsoluteMaxValue - this._fAbsoluteMinValue <= 5.0f && n != 0.0f) ? Fmt.dollars(n, 2) : Fmt.dollars(n, 0)) : ((this._fIncrement >= 1.0f) ? Fmt.number(n) : new Float(n).toString()))) : super._gGraph._sValueLabels[(int)n];
    }
    
    public float getMaxValue() {
        return this._fMaxValue;
    }
    
    private int getMaximumLabelLength(final Graphics graphics) {
        float n = this.getStartingPlotValue();
        int n2 = 0;
        super._iTextImages = new Image[super._iLabelCount + 1];
        for (int i = 0; i <= super._iLabelCount; ++i) {
            if ((super._iOrientation == 3 || super._iOrientation == 4) && super._bUseTextImages) {
                super._iTextImages[i] = Axis.getVerticalText(this.getLabel(n), graphics, super._gGraph, super._gGraph.getBackground());
            }
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.getLabel(n));
            n2 = ((stringWidth > n2) ? stringWidth : n2);
            n = this.getNextIncrement(n);
        }
        return n2;
    }
    
    public float getMinValue() {
        return this._fMinValue;
    }
    
    public float getNextIncrement(final float n) {
        final int n2 = this._bDescending ? this._iIncrement : (-1 * this._iIncrement);
        return (super._gGraph._sValueLabels == null) ? ((this._fRange > 100.0f) ? (n - n2 * this._iFactor) : (Math.round(n * this._iFactor - n2) / this._iFactor)) : (n + (this._bDescending ? -1 : 1));
    }
    
    protected float getStartingPlotValue() {
        return (super._gGraph._sValueLabels == null) ? (this._bDescending ? this._fMaxValue : this._fMinValue) : (this._bDescending ? (super._gGraph._sValueLabels.length - 1) : 0);
    }
    
    public int getWidth() {
        return (super._iOrientation == 3 || super._iOrientation == 4) ? super._iHeight : super._iWidth;
    }
    
    public int getZeroIntersects() {
        return this._iZeroIntersects;
    }
    
    public boolean isDescending() {
        return this._bDescending;
    }
    
    public void paint(final Graphics graphics, int n, int n2, final int n3, final int n4) {
        if (super._bShow) {
            if (super._iOrientation == 4) {
                n2 += n4 - 1;
            }
            else if (super._iOrientation == 1) {
                n += n3 - 1;
            }
            graphics.setColor(super._cColor);
            graphics.setFont(super._fFont);
            final int n5 = super._iWidth - 3 - 1;
            final int n6 = 8;
            int n7 = 0;
            float n8 = this.getStartingPlotValue();
            for (int i = 0; i <= super._iLabelCount; ++i) {
                if (super._iPixelsPerIncrement < graphics.getFontMetrics().getHeight()) {
                    if (i % 2 != 0) {
                        this.drawText(graphics, n, n2, this.getLabel(n8), super._iTextImages[i], n6, n7 - super._iFontHeight / 2, n7);
                    }
                }
                else {
                    this.drawText(graphics, n, n2, this.getLabel(n8), super._iTextImages[i], n6, n7 - super._iFontHeight / 2, n7);
                }
                this.drawLine(graphics, n, n2, n5, n7, n5 + 3, n7);
                n7 += super._iPixelsPerIncrement;
                n8 = this.getNextIncrement(n8);
            }
            this.drawLine(graphics, n, n2, n5 + 3, 0, n5 + 3, super._iDataLength);
        }
    }
    
    public void setDescending(final boolean bDescending) {
        this._bDescending = bDescending;
    }
    
    public void setFormat(final int iFormat) {
        this._iFormat = iFormat;
    }
    
    public void setINF(final Graphics graphics, final Graph gGraph) {
        super._gGraph = gGraph;
        super._fFont = gGraph.FONT_PLAIN;
        graphics.setColor(super._cColor = gGraph.getForeground());
        graphics.setFont(super._fFont);
        this._fAbsoluteMaxValue = (this._bAutoMaximum ? gGraph.getMaxValue() : this._axisMaximum);
        this._fAbsoluteMinValue = (this._bAutoMinimum ? gGraph.getMinValue() : this._axisMinimum);
        if (this._fAbsoluteMaxValue < 1.0E-5f && this._fAbsoluteMaxValue > -1.0E-5 && this._fAbsoluteMinValue < 1.0E-5f && this._fAbsoluteMinValue > -1.0E-5) {
            this._fAbsoluteMaxValue = 25.0f;
        }
        else if (this._fAbsoluteMaxValue < 1.0f && this._fAbsoluteMaxValue > -1.0f && this._fAbsoluteMinValue < 1.0f && this._fAbsoluteMinValue > -1.0f && this._iFormat == 1) {
            this._fAbsoluteMaxValue = 1.0f;
        }
        this.setIncrement(this._fAbsoluteRange = this._fAbsoluteMaxValue - this._fAbsoluteMinValue);
        if (this._fAbsoluteMinValue == 0.0f) {
            this._fMinValue = 0.0f;
        }
        else if (this._fAbsoluteMinValue % this._fIncrement == 0.0f) {
            this._fMinValue = this._fAbsoluteMinValue;
        }
        else {
            this._fMinValue = (float)Math.floor(this._fAbsoluteMinValue / this._fIncrement) * this._fIncrement;
        }
        if (this._fAbsoluteMaxValue == 0.0f) {
            this._fMaxValue = 0.0f;
        }
        else if (this._fAbsoluteMaxValue % this._fIncrement == 0.0f) {
            this._fMaxValue = this._fAbsoluteMaxValue;
        }
        else {
            this._fMaxValue = (float)Math.ceil(this._fAbsoluteMaxValue / this._fIncrement);
            this._fMaxValue = Math.round(this._iFactor * this._fMaxValue * this._fIncrement) / this._iFactor;
        }
        this._fRange = this._fMaxValue - this._fMinValue;
        if (this._fRange == 0.0f) {
            this._fRange = this._fIncrement;
        }
        if (super._gGraph._sValueLabels == null) {
            super._iLabelCount = Math.round(this._fRange / this._fIncrement);
        }
        else {
            super._iLabelCount = super._gGraph._sValueLabels.length - 1;
        }
        if (super._bShow) {
            super._iMaxLabelLength = this.getMaximumLabelLength(graphics);
            super._iFontHeight = graphics.getFontMetrics().getHeight();
            super._iFontDescent = graphics.getFontMetrics().getDescent();
            if (super._iOrientation == 1 || super._iOrientation == 0) {
                super._iWidth = super._iMaxLabelLength + 5 + 3 + 1;
            }
        }
        else {
            super._iMaxLabelLength = 0;
            super._iWidth = 0;
            super._iFontHeight = 0;
            super._iFontDescent = 0;
        }
    }
    
    private void setIncrement(final float n) {
        this._iFactor = 1;
        this._iIncrement = 1;
        this._fIncrement = 1.0f;
        if (super._gGraph._sValueLabels != null) {
            return;
        }
        float n2 = n;
        if (n2 > 100.0f) {
            while (n2 > 200.0f) {
                this._iFactor *= 10;
                n2 = n / this._iFactor;
            }
        }
        else {
            while (n2 <= 20.0f) {
                this._iFactor *= 10;
                n2 = n * this._iFactor;
            }
        }
        this._iIncrement = ((n2 > 100.0f) ? 20 : ((n2 > 50.0f) ? 10 : 5));
        this._fIncrement = ((n > 100.0f) ? (this._iIncrement * this._iFactor) : (this._iIncrement / this._iFactor));
    }
    
    public void setSize(final Graphics graphics, final int iMaxLength) {
        graphics.setColor(super._cColor);
        graphics.setFont(super._fFont);
        super._iFontOffset = (super._iFontHeight - super._iFontDescent) / 2;
        super._iPixelsPerIncrement = iMaxLength / super._iLabelCount;
        super._iDataLength = super._iPixelsPerIncrement * super._iLabelCount;
        super._iMaxLength = iMaxLength;
        super._iHeight = super._iDataLength;
        int iZeroIntersects = 0;
        float n = this.getStartingPlotValue();
        for (int i = 0; i <= super._iLabelCount; ++i) {
            if (n == 0.0f) {
                this._iZeroIntersects = iZeroIntersects;
                break;
            }
            n = this.getNextIncrement(n);
            iZeroIntersects += super._iPixelsPerIncrement;
        }
        super._bUseTextImages = false;
        if (super._iOrientation == 3 || super._iOrientation == 4) {
            if (super._iPixelsPerIncrement - super._iFontOffset < super._iMaxLabelLength) {
                super._iWidth = super._iMaxLabelLength + 5 + 3 + 1;
                super._bUseTextImages = true;
            }
            else {
                super._iWidth = super._iFontHeight - super._iFontDescent + 5 + 3 + 1;
            }
        }
        super._iYOffset = ((super._iOrientation == 3 && super._bShow) ? this.getHeight() : 0);
        super._iXOffset = ((super._iOrientation == 0 && super._bShow) ? this.getWidth() : 0);
    }
}
