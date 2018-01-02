// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.image.ImageObserver;
import java.awt.Graphics;

public class Grid
{
    public static final float SPACING_PERCENT = 0.75f;
    public boolean _showYGridLines;
    public boolean _showXGridLines;
    private Graph _gGraph;
    protected boolean _bHorizontalValues;
    private ValueAxis _AxisY;
    private CatagoryAxis _AxisX;
    private int _iHeight;
    private int _iWidth;
    private int _iAxisIncrementCount;
    private int _iPixelsPerIncrement;
    private float _fAxisRange;
    private float _fValuePerPixel;
    
    public Grid() {
        this._showYGridLines = true;
        this._showXGridLines = false;
        this._bHorizontalValues = false;
    }
    
    public float getValuePerPixel() {
        return this._fValuePerPixel;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2) {
        if (this._gGraph.getImageBackground() != null) {
            graphics.drawImage(this._gGraph.getImageBackground(), n, n2, this._iWidth, this._iHeight + 1, this._gGraph);
        }
        else {
            graphics.setColor(this._gGraph._cBackground);
            graphics.fillRect(n, n2, this._iWidth, this._iHeight + 1);
        }
        graphics.setColor(this._gGraph._cGrid);
        if (this._bHorizontalValues) {
            graphics.drawRect(n - 1, n2, this._iWidth + 1, this._iHeight);
        }
        else {
            graphics.drawRect(n, n2 - 1, this._iWidth, this._iHeight + 1);
        }
        graphics.setColor(this._gGraph._cGrid);
        if (this._showXGridLines) {
            int n3 = 0;
            for (int i = 0; i < this._gGraph.getValueCount(); ++i) {
                final int catagoryLeft = this._AxisX.getCatagoryLeft(i);
                if (i == 0) {
                    --n3;
                }
                if (this._bHorizontalValues) {
                    n3 = n + catagoryLeft;
                    graphics.drawLine(n3, n2, n3, n2 + this._iHeight);
                }
                else {
                    final int n4 = n2 + catagoryLeft;
                    graphics.drawLine(n, n4, n + this._iWidth, n4);
                }
            }
        }
        if (this._showYGridLines) {
            if (this._bHorizontalValues) {
                int n5 = n2;
                for (int j = 0; j < this._iAxisIncrementCount; ++j) {
                    if (j != 0) {
                        graphics.drawLine(n, n5, n + this._iWidth, n5);
                    }
                    n5 += this._iPixelsPerIncrement;
                }
            }
            else {
                int n6 = n;
                for (int k = 0; k < this._iAxisIncrementCount; ++k) {
                    if (k != 0) {
                        graphics.drawLine(n6, n2, n6, n2 + this._iHeight);
                    }
                    n6 += this._iPixelsPerIncrement;
                }
            }
        }
    }
    
    public void setINF(final Graphics graphics, final Graph gGraph) {
        this._gGraph = gGraph;
    }
    
    public void setSize(final Graphics graphics, final ValueAxis axisY, final CatagoryAxis axisX) {
        this._AxisY = axisY;
        this._AxisX = axisX;
        this._iAxisIncrementCount = this._AxisY._iLabelCount;
        this._iPixelsPerIncrement = this._AxisY._iPixelsPerIncrement;
        this._fAxisRange = this._AxisY._fRange;
        this._fValuePerPixel = this._AxisY.getDataLength() / this._fAxisRange;
        if (this._AxisX._iOrientation == 3 || this._AxisX._iOrientation == 4) {
            this._iWidth = this._AxisX.getDataLength();
            this._iHeight = this._AxisY.getDataLength();
            this._bHorizontalValues = true;
        }
        else {
            this._iWidth = this._AxisY.getDataLength();
            this._iHeight = this._AxisX.getDataLength();
            this._bHorizontalValues = false;
        }
    }
}
