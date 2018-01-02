// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public class DisplayGrid extends Panel
{
    private String[][] _sGrid;
    private Color[] _cGridColumns;
    private Color _cGridForeground;
    private Color _cGridBackground;
    private Font _fNormal;
    private Font _fBold;
    private boolean _bColumnHeadings;
    private boolean _bRowHeadings;
    private boolean _bGridLines;
    private int _iRows;
    private int _iCols;
    private FontMetrics fmNormal;
    private FontMetrics fmBold;
    private int iStringMaxWidth;
    private int iRowHeight;
    private int iBorderWidth;
    public int iOutsidePadWidth;
    public int iSpecialCol;
    public int iSpecialRow;
    public int iSpecialCol2;
    public int iSpecialRow2;
    public Color _cGridSpecial;
    public Color _cGridSpecial2;
    
    public DisplayGrid(final int n, final int n2, final String[][] array, final Color[] array2, final Color cGridForeground, final Color color, final Font fNormal, final Font fBold, final boolean bColumnHeadings, final boolean bRowHeadings, final boolean bGridLines) {
        this._fNormal = new Font("Helvetica", 0, 9);
        this._fBold = new Font("Helvetica", 0, 9);
        this._bColumnHeadings = true;
        this._bRowHeadings = true;
        this._bGridLines = true;
        this.iStringMaxWidth = 0;
        this.iRowHeight = 0;
        this.iBorderWidth = 1;
        this.iOutsidePadWidth = 0;
        this.iSpecialCol = -1;
        this.iSpecialRow = 100;
        this.iSpecialCol2 = -1;
        this.iSpecialRow2 = 100;
        this._cGridSpecial = Color.red;
        this._cGridSpecial2 = Color.green;
        this._bGridLines = bGridLines;
        this._cGridForeground = cGridForeground;
        this._cGridBackground = color;
        this._fNormal = fNormal;
        this._fBold = fBold;
        this._bColumnHeadings = bColumnHeadings;
        this._bRowHeadings = bRowHeadings;
        this.setBackground(color);
        this.fmNormal = this.getFontMetrics(this._fNormal);
        this.fmBold = this.getFontMetrics(this._fBold);
        this.setAttributes(n, n2, array, array2);
    }
    
    public static String[][] getGrid(final int n, final int n2) {
        final String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            array[i] = new String[n2];
            for (int j = 0; j < n2; ++j) {
                array[i][j] = "";
            }
        }
        return array;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(0, this._iRows * this.iRowHeight + this.iBorderWidth);
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.size().width - this.iOutsidePadWidth * 2;
        final int height = this.size().height;
        final int n2 = this._iRows * this.iRowHeight + this.iBorderWidth;
        final int n3 = (height - n2) / 2;
        final int iOutsidePadWidth = this.iOutsidePadWidth;
        final int n4 = (((this._bColumnHeadings || this._bRowHeadings) ? (this.fmBold.getHeight() - this.fmBold.getDescent()) : (this.fmNormal.getHeight() - this.fmNormal.getDescent())) + this.iRowHeight) / 2 - 1;
        final int n5 = (this.iStringMaxWidth + this.iOutsidePadWidth * 2 < n / this._iCols) ? (n / this._iCols) : (this.iStringMaxWidth + this.iOutsidePadWidth * 2);
        final int n6 = (this._iCols > 1) ? ((n - n5) / (this._iCols - 1)) : n5;
        final int n7 = (this._iCols > 1) ? (n - n6 * (this._iCols - 1)) : n5;
        for (int i = 0; i < this._iCols; ++i) {
            if (this._cGridColumns.length > i && this._cGridColumns[i] != null) {
                graphics.setColor(this._cGridColumns[i]);
                if (i == 0) {
                    graphics.fillRect(iOutsidePadWidth, n3, n7, n2);
                }
                else {
                    graphics.fillRect(iOutsidePadWidth + n7 + (i - 1) * n6, n3, n6, n2);
                }
            }
        }
        graphics.setColor(this._cGridForeground);
        for (int j = 0; j < this._iCols; ++j) {
            for (int k = 0; k < this._iRows; ++k) {
                if ((j == this.iSpecialCol || this.iSpecialCol2 == -1) && k == this.iSpecialRow) {
                    graphics.setColor(this._cGridSpecial);
                }
                else if ((j == this.iSpecialCol2 || this.iSpecialCol2 == -1) && k == this.iSpecialRow2) {
                    graphics.setColor(this._cGridSpecial2);
                }
                else {
                    graphics.setColor(this._cGridForeground);
                }
                if (this._bGridLines) {
                    if (j == 0) {
                        graphics.drawRect(iOutsidePadWidth, n3 + this.iRowHeight * k, n7, this.iRowHeight);
                    }
                    else {
                        graphics.drawRect(iOutsidePadWidth + n7 + (j - 1) * n6, n3 + this.iRowHeight * k, n6, this.iRowHeight);
                    }
                }
                if (j == 0 && this._bRowHeadings) {
                    graphics.setFont(this._fBold);
                    graphics.drawString(this._sGrid[k][j], iOutsidePadWidth + this.iOutsidePadWidth, n3 + this.iRowHeight * k + n4);
                }
                else if (k == 0 && this._bColumnHeadings) {
                    graphics.setFont(this._fBold);
                    graphics.drawString(this._sGrid[k][j], iOutsidePadWidth + n7 + (j - 1) * n6 + (n6 - this.fmBold.stringWidth(this._sGrid[k][j])) / 2, n3 + this.iRowHeight * k + n4);
                }
                else {
                    graphics.setFont(this._fNormal);
                    graphics.drawString(this._sGrid[k][j], iOutsidePadWidth + n7 + (j - 1) * n6 + (n6 - this.fmNormal.stringWidth(this._sGrid[k][j])) / 2, n3 + this.iRowHeight * k + n4);
                }
            }
        }
    }
    
    public void setAttributes(final int iRows, final int iCols, final String[][] sGrid, final Color[] cGridColumns) {
        this._iRows = iRows;
        this._iCols = iCols;
        this._sGrid = sGrid;
        this._cGridColumns = cGridColumns;
        this.iRowHeight = (int)(this.fmNormal.getHeight() * (this._bGridLines ? 1.2 : 1.0)) + (this._bGridLines ? 0 : this.iBorderWidth);
        if (this._bColumnHeadings || this._bRowHeadings) {
            this.iRowHeight = (int)(this.fmBold.getHeight() * (this._bGridLines ? 1.2 : 1.0)) + (this._bGridLines ? 0 : this.iBorderWidth);
        }
        this.iOutsidePadWidth = this.fmBold.getHeight() / 2;
        this.iStringMaxWidth = 0;
        if (this._bColumnHeadings) {
            for (int i = 0; i < iRows; ++i) {
                final int stringWidth = this.fmBold.stringWidth(sGrid[i][0]);
                if (this.iStringMaxWidth < stringWidth) {
                    this.iStringMaxWidth = stringWidth;
                }
            }
        }
        this.repaint();
    }
}
