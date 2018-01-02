// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public class Legend
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    public static final int TOP_LEFT = 5;
    public static final int TOP_RIGHT = 6;
    public static final int BOTTOM_LEFT = 7;
    public static final int BOTTOM_RIGHT = 8;
    public static final int GRID_TOP_RIGHT = 9;
    public static final int GRID_TOP_LEFT = 10;
    public static final int GRID_BOTTOM_RIGHT = 11;
    public static final int GRID_BOTTOM_LEFT = 12;
    public static final int GRID_TOP = 13;
    public static final int GRID_BOTTOM = 14;
    public static final int GRID_RIGHT = 15;
    public static final int GRID_LEFT = 16;
    public static final int TOP_GRID_ALIGN = 17;
    public static final int BANDED_LEGEND = 0;
    public static final int DATA_SERIES_LEGEND = 1;
    public static final int CATAGORY_LEGEND = 2;
    public static final int ROUND_LEGEND = 0;
    public static final int SQUARE_LEGEND = 1;
    private static final int SIDE_BORDER_WIDTH = 3;
    private static final int TOPBOTTOM_BORDER_WIDTH = 2;
    private static final int BOX_SIDE_LENGTH = 8;
    private static final int BOX_TO_LABEL_SPACE = 3;
    public int _iShape;
    protected int _iOrientation;
    public int _legendType;
    public boolean _border;
    public Color _cBGColor;
    public int _iLegendWidth;
    public int LINE_SPACE;
    protected boolean _bShow;
    protected Color _cColor;
    protected Font _fFont;
    protected Font _fFont2;
    private FontMetrics fm2;
    private FontMetrics fm;
    protected int _iXOffset;
    protected int _iYOffset;
    protected int _iWidthOffset;
    protected int _iHeightOffset;
    protected int _iMaxLegendLength;
    protected Graph _gGraph;
    protected int _iWidth;
    protected int _iHeight;
    protected int _iFontHeight;
    protected int _iFontDescent;
    protected int _iColorBoxYOffset;
    protected int _iLabelOffset;
    protected int _iColorBoxOffset;
    
    public Legend() {
        this._iShape = 1;
        this._iOrientation = 1;
        this._legendType = 1;
        this._border = false;
        this._iLegendWidth = 0;
        this.LINE_SPACE = 0;
        this._bShow = true;
    }
    
    public Legend(final int iOrientation) {
        this._iShape = 1;
        this._iOrientation = 1;
        this._legendType = 1;
        this._border = false;
        this._iLegendWidth = 0;
        this.LINE_SPACE = 0;
        this._bShow = true;
        this._iOrientation = iOrientation;
    }
    
    private void drawCatagoryLegend(final Graphics graphics, final int n, final int n2) {
        int n3 = n2 + 2;
        for (int i = 0; i < this._gGraph._sGraphCatagories.length; ++i) {
            if (this._iShape == 1) {
                graphics.setColor(this._gGraph.getColorList(i));
                graphics.fillRect(n + this._iColorBoxOffset, n3 + this._iColorBoxYOffset - 1, 8, 8);
                graphics.setColor(this._gGraph.getForeground());
                graphics.drawRect(n + this._iColorBoxOffset, n3 + this._iColorBoxYOffset - 1, 8, 8);
            }
            else {
                graphics.setColor(this._gGraph.getColorList(i));
                graphics.fillOval(n + this._iColorBoxOffset, n3 + this._iColorBoxYOffset - 1, 9, 9);
                graphics.setColor(this._gGraph.getForeground());
            }
            graphics.drawString(this._gGraph._sGraphCatagories[i], n + this._iLabelOffset, n3 - this._iFontDescent + this._iFontHeight - 1);
            if (this._gGraph._sGraphCatagoriesBold != null && this._gGraph._sGraphCatagoriesBold.length > i && this._gGraph._sGraphCatagoriesBold[i] != null) {
                graphics.setFont(this._fFont2);
                graphics.drawString(this._gGraph._sGraphCatagoriesBold[i], n + this._iLabelOffset + this.fm.stringWidth(this._gGraph._sGraphCatagories[i]), n3 - this._iFontDescent + this._iFontHeight - 1);
                graphics.setFont(this._fFont);
            }
            n3 += this.LINE_SPACE + this._iFontHeight;
        }
    }
    
    private void drawDataSeriesLegend(final Graphics graphics, final int n, final int n2) {
        while (true) {
            Enumeration dataSeries = null;
            Label_0292: {
                int n3;
                try {
                    dataSeries = this._gGraph.getDataSeries();
                    n3 = n2 + 2;
                    break Label_0292;
                }
                catch (Exception ex) {
                    return;
                }
                final GraphDataSeries graphDataSeries = dataSeries.nextElement();
                if (this._iShape == 1) {
                    graphics.setColor(graphDataSeries.getColor());
                    graphics.fillRect(n + this._iColorBoxOffset, n3 + this._iColorBoxYOffset - 1, 8, 8);
                    graphics.setColor(this._gGraph.getForeground());
                    graphics.drawRect(n + this._iColorBoxOffset, n3 + this._iColorBoxYOffset - 1, 8, 8);
                }
                else {
                    graphics.setColor(graphDataSeries.getColor());
                    graphics.fillOval(n + this._iColorBoxOffset - 1, n3 + this._iColorBoxYOffset - 2, 12, 12);
                    graphics.setColor(this._gGraph.getForeground());
                }
                graphics.drawString(graphDataSeries.getSeriesTitle(), n + this._iLabelOffset, n3 - this._iFontDescent + this._iFontHeight - 1);
                if (!graphDataSeries.getSeriesTitleBold().equals("")) {
                    graphics.setFont(this._fFont2);
                    graphics.drawString(graphDataSeries.getSeriesTitleBold(), this.fm.stringWidth(graphDataSeries.getSeriesTitle()) + n + this._iLabelOffset, n3 - this._iFontDescent + this._iFontHeight - 1);
                    graphics.setFont(this._fFont);
                }
                n3 += this.LINE_SPACE + this._iFontHeight;
                if (this._legendType == 0 && dataSeries.hasMoreElements()) {
                    dataSeries.nextElement();
                }
            }
            if (!dataSeries.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public int getHeightOffset() {
        return this._iHeightOffset;
    }
    
    public String getProperty(final Graph graph) {
        if (this._cBGColor == null) {
            this._cBGColor = graph.getBackground();
        }
        return String.valueOf(Graph.getHEX(this._cBGColor)) + "|" + this._iShape + "|" + this._iOrientation + "|" + this._border + "|" + this._iLegendWidth + "|" + this.LINE_SPACE + "|" + this._bShow;
    }
    
    public int getWidthOffset() {
        return this._iWidthOffset;
    }
    
    public int getXOffset() {
        return this._iXOffset;
    }
    
    public int getYOffset() {
        return this._iYOffset;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.paint(graphics, n, n2, n3, n4, 0, 0, 0, 0);
    }
    
    public void paint(final Graphics graphics, int n, int n2, int n3, int n4, final int n5, final int n6, final int n7, final int n8) {
        switch (this._iOrientation) {
            case 3: {
                n4 = this._iHeight;
                break;
            }
            case 17: {
                n4 = this._iHeight;
                n3 = this._iWidth;
                n = n5;
                break;
            }
            case 4: {
                n2 += n4 - this._iHeight;
                n4 = this._iHeight;
                break;
            }
            case 0: {
                if (n6 > 0) {
                    n2 = n6;
                }
                if (n8 > 0) {
                    n4 = n8;
                }
                n3 = this._iWidth;
                break;
            }
            case 1: {
                if (n6 > 0) {
                    n2 = n6;
                }
                if (n8 > 0) {
                    n4 = n8;
                }
                n += n3 - this._iWidth;
                n3 = this._iWidth;
                break;
            }
            case 5: {
                n4 = this._iHeight;
                n3 = this._iWidth;
                break;
            }
            case 7: {
                n2 += n4 - this._iHeight;
                n4 = this._iHeight;
                n3 = this._iWidth;
                break;
            }
            case 6: {
                n += n3 - this._iWidth;
                n4 = this._iHeight;
                n3 = this._iWidth;
                break;
            }
            case 8: {
                n2 += n4 - this._iHeight;
                n += n3 - this._iWidth;
                n4 = this._iHeight;
                n3 = this._iWidth;
                break;
            }
            case 9: {
                n = n5 - 8 + n7 - this._iWidth;
                n2 = n6 + 8;
                n3 = this._iWidth;
                n4 = this._iHeight;
                break;
            }
            case 10: {
                n = n5 + 8;
                n2 = n6 + 8;
                n3 = this._iWidth;
                n4 = this._iHeight;
                break;
            }
            case 11: {
                n = n5 - 8 + n7 - this._iWidth;
                n2 = n6 - 8 + n8 - this._iHeight;
                n3 = this._iWidth;
                n4 = this._iHeight;
                break;
            }
            case 12: {
                n = n5 + 8;
                n2 = n6 - 8 + n8 - this._iHeight;
                n3 = this._iWidth;
                n4 = this._iHeight;
                break;
            }
            case 13: {
                n = n5;
                n2 = n6 + 8;
                n3 = n7;
                n4 = this._iHeight;
                break;
            }
            case 14: {
                n = n5;
                n2 = n6 - 8 + n8 - this._iHeight;
                n3 = n7;
                n4 = this._iHeight;
                break;
            }
            case 16: {
                n = n5 + 8;
                n2 = n6;
                n3 = this._iWidth;
                n4 = n8;
                break;
            }
            case 15: {
                n = n5 - 8 + n7 - this._iWidth;
                n2 = n6;
                n3 = this._iWidth;
                n4 = n8;
                break;
            }
        }
        if (this._bShow) {
            final int n9 = n + n3 / 2 - this._iWidth / 2;
            final int n10 = n2 + n4 / 2 - this._iHeight / 2;
            graphics.setFont(this._fFont);
            if (this._border) {
                graphics.setColor(this._cBGColor);
                graphics.fillRect(n9, n10, this._iWidth, this._iHeight);
                graphics.setColor(this._gGraph._cForeground);
                graphics.drawRect(n9, n10, this._iWidth, this._iHeight);
            }
            if (this._legendType <= 1) {
                this.drawDataSeriesLegend(graphics, n9, n10);
                return;
            }
            this.drawCatagoryLegend(graphics, n9, n10);
        }
    }
    
    public void setINF(final Graphics graphics, final Graph gGraph) {
        this._gGraph = gGraph;
        this._cColor = gGraph.getForeground();
        if (this._cBGColor == null) {
            this._cBGColor = this._gGraph.getBackground();
        }
        this._iMaxLegendLength = 0;
        this._iFontHeight = 0;
        this._iFontDescent = 0;
        this._fFont = gGraph.FONT_PLAIN;
        this._fFont2 = gGraph.FONT_BOLD;
        this.fm = graphics.getFontMetrics(this._fFont);
        this.fm2 = graphics.getFontMetrics(this._fFont2);
        if (this._bShow) {
            graphics.setColor(this._cColor);
            graphics.setFont(this._fFont);
            if (this._iLegendWidth > 0) {
                this._iMaxLegendLength = this._iLegendWidth;
            }
            else {
                graphics.setColor(this._cColor);
                graphics.setFont(this._fFont);
                if (this._legendType != 2) {
                    try {
                        final Enumeration dataSeries = gGraph.getDataSeries();
                        while (dataSeries.hasMoreElements()) {
                            final GraphDataSeries graphDataSeries = dataSeries.nextElement();
                            final int iMaxLegendLength = this.fm.stringWidth(graphDataSeries.getSeriesTitle()) + this.fm2.stringWidth(graphDataSeries.getSeriesTitleBold());
                            if (iMaxLegendLength > this._iMaxLegendLength) {
                                this._iMaxLegendLength = iMaxLegendLength;
                            }
                        }
                    }
                    catch (Exception ex) {
                        this._iMaxLegendLength = 0;
                    }
                }
                else if (gGraph._sGraphCatagories != null) {
                    for (int i = 0; i < gGraph._sGraphCatagories.length; ++i) {
                        int stringWidth = this.fm.stringWidth(gGraph._sGraphCatagories[i]);
                        if (gGraph._sGraphCatagoriesBold != null && gGraph._sGraphCatagoriesBold.length > i && gGraph._sGraphCatagoriesBold[i] != null) {
                            stringWidth += this.fm2.stringWidth(gGraph._sGraphCatagoriesBold[i]);
                            graphics.setFont(this._fFont);
                        }
                        if (stringWidth > this._iMaxLegendLength) {
                            this._iMaxLegendLength = stringWidth;
                        }
                    }
                }
            }
            this._iFontHeight = this.fm.getHeight();
            this._iFontDescent = this.fm.getDescent();
        }
    }
    
    public void setOrientation(final int iOrientation) {
        this._iOrientation = iOrientation;
    }
    
    public void setProperty(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            switch (n) {
                case 0: {
                    this._cBGColor = Graph.sgv(nextToken, this._cBGColor);
                    break;
                }
                case 1: {
                    this._iShape = Graph.sgv(nextToken, this._iShape);
                    break;
                }
                case 2: {
                    this._iOrientation = Graph.sgv(nextToken, this._iOrientation);
                    break;
                }
                case 3: {
                    this._border = Graph.sgv(nextToken, this._border);
                    break;
                }
                case 4: {
                    this._iLegendWidth = Graph.sgv(nextToken, this._iLegendWidth);
                    break;
                }
                case 5: {
                    this.LINE_SPACE = Graph.sgv(nextToken, this.LINE_SPACE);
                    break;
                }
                case 6: {
                    this._bShow = Graph.sgv(nextToken, this._bShow);
                    break;
                }
            }
            ++n;
        }
    }
    
    public void setSize(final Graphics graphics) {
        this._iWidth = 0;
        this._iHeight = 0;
        this._iXOffset = 0;
        this._iYOffset = 0;
        this._iWidthOffset = 8;
        this._iHeightOffset = 0;
        if (this._bShow) {
            this._iColorBoxOffset = 4;
            this._iLabelOffset = 16;
            this._iWidth = this._iLabelOffset + this._iMaxLegendLength + 3;
            this._iHeight = 2 + (this._iFontHeight + this.LINE_SPACE) * ((this._legendType == 2) ? ((this._gGraph._sGraphCatagories == null) ? 0 : this._gGraph._sGraphCatagories.length) : this._gGraph.getDataSeriesCount()) - this.LINE_SPACE - this._iFontDescent + 2;
            this._iColorBoxYOffset = (int)Math.ceil((this._iFontHeight - this._iFontDescent - 8) / 2.0f) + 1;
            switch (this._iOrientation) {
                case 3:
                case 17: {
                    this._iYOffset = this._iHeight + 8;
                }
                case 4: {
                    this._iHeightOffset = this._iHeight + 8;
                    break;
                }
                case 0:
                case 5:
                case 7: {
                    this._iXOffset = this._iWidth + 8;
                    this._iWidthOffset = this._iWidth + 8 + 8;
                    break;
                }
                case 1:
                case 6:
                case 8: {
                    this._iWidthOffset = this._iWidth + 8;
                    break;
                }
            }
        }
    }
    
    public void setVisible(final boolean bShow) {
        this._bShow = bShow;
    }
}
