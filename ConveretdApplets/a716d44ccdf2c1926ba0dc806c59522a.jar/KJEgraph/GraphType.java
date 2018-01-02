// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Component;
import KJEgui.GetGraphics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Color;

public abstract class GraphType
{
    public float fSpacingPercent;
    public Legend _legend;
    public Grid _grid;
    public ValueAxis _axisY;
    public CatagoryAxis _axisX;
    public Title _titleGraph;
    public Title _titleXAxis;
    public Title _titleYAxis;
    public Color cBack;
    protected int _XBdr;
    protected int _iYBdr;
    protected int _iWBdr;
    protected int _iHBdr;
    protected int _iXTB;
    protected int _iYTB;
    protected int _iWTB;
    protected int _iHTB;
    protected int _iXLegend;
    protected int _iYLegend;
    protected int _iWLegend;
    protected int _iHLegend;
    protected int _iXATB;
    protected int _iYATB;
    protected int _iWATB;
    protected int _iHATB;
    protected int _iXAxis;
    protected int _iYAxis;
    protected int _iWAxis;
    protected int _iHAxis;
    protected int _iXGrid;
    protected int _iYGrid;
    protected int _iWGrid;
    protected int _iHGrid;
    protected int _iXData;
    protected int _iYData;
    protected int _iWData;
    protected int _iHData;
    int _iValueCount;
    private int _iOW;
    private int _iOH;
    protected Rectangle[][] rItems;
    protected Polygon[][] pItems;
    protected String[][] sItems;
    
    public GraphType() {
        this.fSpacingPercent = 0.75f;
        this.cBack = Color.white;
        this._iValueCount = 0;
    }
    
    protected String getValue(final int n, final int n2, final Graph graph) {
        if (this.rItems == null || this.sItems == null) {
            return null;
        }
        for (int n3 = this.rItems.length - 1; n3 >= 0 && this.rItems[n3] != null && this.sItems[n3] != null; --n3) {
            for (int i = 0; i < this.rItems[n3].length; ++i) {
                if (this.rItems[n3][i] != null && this.rItems[n3][i].inside(n, n2) && this.sItems[n3].length > i) {
                    return this.sItems[n3][i];
                }
            }
        }
        return null;
    }
    
    protected final int getYDraw(final float n, final int n2, final float n3, final int n4, final float n5, final float n6, final int n7) {
        int n8 = n4 - Math.round(n * n3);
        if (n < 0.0f) {
            if (n5 < 0.0f && n7 != -1) {
                n8 = -1 * Math.round((n + n5) * n3);
            }
            else if (n6 > 0.0f && n7 == -1) {
                n8 = -1 * Math.round((n + n6) * n3);
            }
        }
        else if (n6 > 0.0f && n7 != -1) {
            n8 = n2 - Math.round((n - n6) * n3);
        }
        else if (n5 < 0.0f && n7 == -1) {
            n8 = n2 - Math.round((n + n5) * n3);
        }
        return n8;
    }
    
    public synchronized void paint(final Graphics graphics, final Graph graph, final Dimension dimension, final boolean b) {
        GetGraphics.setGraphics(graphics);
        boolean b2 = false;
        if (graph.dataChanged() || b) {
            this._iValueCount = graph.getValueCount();
            this.setBorderINF(graphics, graph);
            this.setTitleINF(graphics, graph);
            this.setLegendINF(graphics, graph);
            this.setAxisTitleINF(graphics, graph);
            this.setAxisINF(graphics, graph);
            this.setGridINF(graphics, graph);
            this.setDataINF(graphics, graph);
            if (!b) {
                graph.dataChanged(false);
            }
            b2 = true;
        }
        if (dimension.width != this._iOW || dimension.height != this._iOH || b2) {
            this.setBdr(graphics, 0, 0, dimension.width, dimension.height, graph);
            this.setTB(graphics, this._XBdr, this._iYBdr, this._iWBdr, this._iHBdr, graph);
            this.setLegend(graphics, this._iXTB, this._iYTB, this._iWTB, this._iHTB, graph);
            this.setATB(graphics, this._iXLegend, this._iYLegend, this._iWLegend, this._iHLegend, graph);
            this.setAxis(graphics, this._iXATB, this._iYATB, this._iWATB, this._iHATB, graph);
            this.setGrid(graphics, this._iXAxis, this._iYAxis, this._iWAxis, this._iHAxis, graph);
            this.setData(graphics, this._iXGrid, this._iYGrid, this._iWGrid, this._iHGrid, graph);
        }
        this._iOW = dimension.width;
        this._iOH = dimension.height;
        this.paintGrid(graphics, this._iXGrid, this._iYGrid, this._iWGrid, this._iHGrid, graph);
        this.paintData(graphics, this._iXData, this._iYData, this._iWData, this._iHData, graph);
        this.paintBorder(graphics, 0, 0, dimension.width, dimension.height, graph);
        GetGraphics.setText(graphics);
        this.paintTitles(graphics, this._iXTB, this._iYTB, this._iWTB, this._iHTB, graph);
        this.paintLegend(graphics, this._iXLegend, this._iYLegend, this._iWLegend, this._iHLegend, graph);
        this.paintAxisTitle(graphics, this._iXATB, this._iYATB, this._iWATB, this._iHATB, graph);
        this.paintAxis(graphics, this._iXAxis, this._iYAxis, this._iWAxis, this._iHAxis, graph);
    }
    
    public void paintAxis(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._axisY.paint(graphics, n, n2 + this._axisX.getYOffset(), this._iWGrid, this._iHGrid);
        this._axisX.paint(graphics, n + this._axisY.getXOffset(), n2, this._iWGrid, this._iHGrid);
    }
    
    public void paintAxisTitle(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._titleXAxis.paint(graphics, this._iXGrid, n2 + n4 - this._titleXAxis._iHeight, this._iWGrid, this._titleXAxis._iHeight, graph);
        this._titleYAxis.paint(graphics, n, this._iYGrid, this._titleYAxis._iWidth, this._iHGrid, graph);
    }
    
    public void paintBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
    }
    
    public void paintData(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
    }
    
    public void paintGrid(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._grid.paint(graphics, n, n2);
    }
    
    public void paintLegend(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._legend.paint(graphics, n, n2, n3, n4, this._iXGrid, this._iYGrid, this._iWGrid, this._iHGrid);
    }
    
    public void paintTitles(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._titleGraph.paint(graphics, n, n2, n3, this._titleGraph._iHeight, graph);
    }
    
    public void setATB(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._iXATB = n + this._legend.getXOffset();
        this._iYATB = n2 + this._legend.getYOffset();
        this._iWATB = n3 - this._legend.getWidthOffset();
        this._iHATB = n4 - this._legend.getHeightOffset();
    }
    
    public void setAxis(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._iWAxis = n3 - this._titleYAxis._iWidth - 8;
        this._iHAxis = n4 - this._titleXAxis._iHeight - 8;
        this._axisX.setSize(graphics, this._iWAxis - this._axisY.getWidth());
        this._axisY.setSize(graphics, this._iHAxis - this._axisX.getHeight());
        this._iXAxis = n + this._titleYAxis._iWidth + 8;
        this._iYAxis = n2 + (this._axisY.getMaxLength() - this._axisY.getHeight());
    }
    
    protected void setAxisINF(final Graphics graphics, final Graph graph) {
        this._axisX.setINF(graphics, graph);
        this._axisY.setINF(graphics, graph);
    }
    
    protected void setAxisTitleINF(final Graphics graphics, final Graph graph) {
        this._titleXAxis.setTitle(graphics, graph, this.cBack);
        this._titleYAxis.setTitle(graphics, graph, this.cBack);
    }
    
    public void setBdr(final Graphics graphics, final int xBdr, final int iyBdr, final int iwBdr, final int ihBdr, final Graph graph) {
        this._XBdr = xBdr;
        this._iYBdr = iyBdr;
        this._iWBdr = iwBdr;
        this._iHBdr = ihBdr;
    }
    
    protected void setBorderINF(final Graphics graphics, final Graph graph) {
    }
    
    public void setData(final Graphics graphics, final int ixData, final int iyData, final int iwData, final int ihData, final Graph graph) {
        this._iXData = ixData;
        this._iYData = iyData;
        this._iWData = iwData;
        this._iHData = ihData;
    }
    
    protected void setDataINF(final Graphics graphics, final Graph graph) {
    }
    
    public void setGrid(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._iXGrid = n + this._axisY.getXOffset();
        this._iYGrid = n2 + this._axisX.getYOffset();
        this._iWGrid = this._axisX.getDataLength();
        this._iHGrid = this._axisY.getDataLength();
        this._grid.setSize(graphics, this._axisY, this._axisX);
    }
    
    protected void setGridINF(final Graphics graphics, final Graph graph) {
        this._grid.setINF(graphics, graph);
    }
    
    public void setLegend(final Graphics size, final int ixLegend, final int n, final int iwLegend, final int n2, final Graph graph) {
        this._iXLegend = ixLegend;
        this._iYLegend = n + this._titleGraph._iHeight + 8;
        this._iWLegend = iwLegend;
        this._iHLegend = n2 - (this._titleGraph._iHeight + 8);
        this._legend.setSize(size);
    }
    
    protected void setLegendINF(final Graphics graphics, final Graph graph) {
        this._legend.setINF(graphics, graph);
    }
    
    public void setTB(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        this._iXTB = n + 8;
        this._iYTB = n2 + 8;
        this._iWTB = n3 - 16;
        this._iHTB = n4 - 16;
    }
    
    protected void setTitleINF(final Graphics graphics, final Graph graph) {
        this._titleGraph.setTitle(graphics, graph, this.cBack);
    }
    
    public void sync(final Graph graph) {
        this._legend = graph._legend;
        this._grid = graph._grid;
        this._axisY = graph._axisY;
        this._axisX = graph._axisX;
        this._titleGraph = graph._titleGraph;
        this._titleXAxis = graph._titleXAxis;
        this._titleYAxis = graph._titleYAxis;
        this._legend._legendType = 1;
        this._axisX._fDataGapPercent = 0.0f;
        this._axisY.setDescending(true);
        this._axisX.setOrientation(4);
        this._axisY.setOrientation(0);
        this._titleXAxis._iOrientation = 0;
        this._titleYAxis._iOrientation = 1;
    }
    
    public void syncSetup(final Graph graph) {
        graph._titleGraph.setFont(graph.FONT_TITLE);
        graph._titleXAxis.setFont(graph.FONT_BOLD);
        graph._titleYAxis.setFont(graph.FONT_BOLD);
        graph._titleGraph.setColor(graph.getForeground());
        graph._titleXAxis.setColor(graph.getForeground());
        graph._titleYAxis.setColor(graph.getForeground());
        graph._axisY._cLine = graph._cAxisLine;
        graph._axisX._cLine = graph._cAxisLine;
        this.cBack = graph.getBackground();
        this.sync(graph);
    }
}
