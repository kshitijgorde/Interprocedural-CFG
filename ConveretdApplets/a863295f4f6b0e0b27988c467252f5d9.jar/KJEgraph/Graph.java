// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.image.ImageObserver;
import java.awt.Event;
import KJEgui.Fmt;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;

public class Graph extends Canvas
{
    public Dimension _dSize;
    public static final int BORDER_WIDTH = 8;
    public static final int MAX_DATASERIES_NBR = 25;
    public static final int MIN_DATASERIES_NBR = 1;
    public static final Color DEFAULT_BACKGROUND;
    public static final Color DEFAULT_GRIDBACKGROUND;
    public static final Color DEFAULT_FOREGROUND;
    public Font FONT_TITLE;
    public Font FONT_BOLD;
    public Font FONT_PLAIN;
    public Legend _legend;
    public Grid _grid;
    public ValueAxis _axisY;
    public CatagoryAxis _axisX;
    public Title _titleGraph;
    public Title _titleXAxis;
    public Title _titleYAxis;
    public Color[] _colorList;
    public boolean _showItemLabel;
    public int _showItemLabelDecimals;
    public boolean _showItemLabelDollars;
    public boolean _showItemLabelPercent;
    public boolean _showItemLabelOnTop;
    public boolean _showZeros;
    public String[][] _showItemValues;
    public boolean _bUseTextImages;
    public int _iArea;
    protected Vector _vDataSeries;
    protected String[] _sGraphCatagories;
    public String[] _sGraphCatagoriesBold;
    public String[] _sValueLabels;
    public GraphType _gtGraphType;
    private String[] sScaleLabel;
    private int _iGraphUnits;
    private boolean bUseOld;
    private boolean bMouse;
    private int xMouse;
    private int yMouse;
    protected Image _imageBackground;
    protected Color _cBackground;
    protected Color _cForeground;
    protected int _iDataSeriesCount;
    public int _iLimit;
    protected boolean _bDataChanged;
    private int _iOWidth;
    private int _iOHeight;
    private Image _iImage;
    private Graphics _gNew;
    
    static {
        DEFAULT_BACKGROUND = Color.white;
        DEFAULT_GRIDBACKGROUND = new Color(224, 224, 224);
        DEFAULT_FOREGROUND = Color.black;
    }
    
    public Graph(final GraphType gtGraphType) {
        this._dSize = new Dimension(0, 0);
        this.FONT_TITLE = new Font("Helvetica", 1, 13);
        this.FONT_BOLD = new Font("Helvetica", 1, 11);
        this.FONT_PLAIN = new Font("Helvetica", 0, 11);
        this._legend = new Legend(1);
        this._grid = new Grid();
        this._axisY = new ValueAxis(0);
        this._axisX = new CatagoryAxis(4);
        this._titleGraph = new Title(0, "");
        this._titleXAxis = new Title(0, "");
        this._titleYAxis = new Title(1, "");
        this._showItemLabel = false;
        this._showItemLabelDecimals = 0;
        this._showItemLabelDollars = true;
        this._showItemLabelPercent = false;
        this._showItemLabelOnTop = true;
        this._showZeros = true;
        this._showItemValues = null;
        this._bUseTextImages = false;
        this._iArea = 0;
        this._vDataSeries = new Vector();
        this.sScaleLabel = new String[] { "", "k", "m", "b" };
        this._iGraphUnits = 0;
        this.bUseOld = false;
        this.bMouse = false;
        this.yMouse = 0;
        this._cBackground = Graph.DEFAULT_BACKGROUND;
        this._cForeground = Graph.DEFAULT_FOREGROUND;
        this._iDataSeriesCount = 0;
        this._iLimit = 0;
        this._bDataChanged = true;
        (this._gtGraphType = gtGraphType).syncSetup(this);
    }
    
    public Graph(final GraphType gtGraphType, final Image imageBackground) {
        this._dSize = new Dimension(0, 0);
        this.FONT_TITLE = new Font("Helvetica", 1, 13);
        this.FONT_BOLD = new Font("Helvetica", 1, 11);
        this.FONT_PLAIN = new Font("Helvetica", 0, 11);
        this._legend = new Legend(1);
        this._grid = new Grid();
        this._axisY = new ValueAxis(0);
        this._axisX = new CatagoryAxis(4);
        this._titleGraph = new Title(0, "");
        this._titleXAxis = new Title(0, "");
        this._titleYAxis = new Title(1, "");
        this._showItemLabel = false;
        this._showItemLabelDecimals = 0;
        this._showItemLabelDollars = true;
        this._showItemLabelPercent = false;
        this._showItemLabelOnTop = true;
        this._showZeros = true;
        this._showItemValues = null;
        this._bUseTextImages = false;
        this._iArea = 0;
        this._vDataSeries = new Vector();
        this.sScaleLabel = new String[] { "", "k", "m", "b" };
        this._iGraphUnits = 0;
        this.bUseOld = false;
        this.bMouse = false;
        this.yMouse = 0;
        this._cBackground = Graph.DEFAULT_BACKGROUND;
        this._cForeground = Graph.DEFAULT_FOREGROUND;
        this._iDataSeriesCount = 0;
        this._iLimit = 0;
        this._bDataChanged = true;
        this._gtGraphType = gtGraphType;
        this._imageBackground = imageBackground;
        gtGraphType.syncSetup(this);
    }
    
    public void add(final GraphDataSeries graphDataSeries) throws Exception {
        if (this._iDataSeriesCount == 25) {
            throw new Exception("");
        }
        if (graphDataSeries.getValues() != null) {
            this._vDataSeries.addElement(graphDataSeries);
            ++this._iDataSeriesCount;
            this.dataChanged(true);
            this.invalidate();
        }
    }
    
    public void add(final float[] array, final String s, final Color color) throws Exception {
        this.add(new GraphDataSeries(array, s, color));
    }
    
    public boolean dataChanged() {
        return this._bDataChanged;
    }
    
    public void dataChanged(final boolean bDataChanged) {
        if ((bDataChanged && !this._bDataChanged) || (this._iOWidth == -1 && this._iOHeight == -1)) {
            this.repaint();
        }
        this._bDataChanged = bDataChanged;
    }
    
    public Color getBackground() {
        return this._cBackground;
    }
    
    public Color[] getColorList() {
        final Color[] array = new Color[this._sGraphCatagories.length];
        final AutoColor autoColor = new AutoColor();
        if (this._colorList == null) {
            for (int i = 0; i < this._sGraphCatagories.length; ++i) {
                array[i] = autoColor.nextColor();
            }
        }
        else {
            for (int j = 0; j < this._sGraphCatagories.length; ++j) {
                if (this._colorList.length > j) {
                    array[j] = this._colorList[j];
                }
                else {
                    array[j] = autoColor.nextColor();
                }
            }
        }
        return array;
    }
    
    public Color getColorList(final int n) {
        Color color;
        if (this._colorList == null) {
            color = AutoColor.indexColor(n);
        }
        else if (this._colorList.length > n) {
            color = this._colorList[n];
        }
        else {
            color = AutoColor.indexColor(n);
        }
        return color;
    }
    
    public Enumeration getDataSeries() throws Exception {
        if (this.getDataSeriesCount() < 1) {
            throw new Exception("");
        }
        return this._vDataSeries.elements();
    }
    
    public int getDataSeriesCount() {
        return this._iDataSeriesCount;
    }
    
    public Color getForeground() {
        return this._cForeground;
    }
    
    protected String getFormat(final float n, final int n2, final int n3) {
        String s = "";
        if (this._showItemValues == null) {
            if (this._showZeros || n != 0.0f) {
                if (this._showItemLabelDollars) {
                    s = Fmt.dollars(n, this._showItemLabelDecimals);
                }
                else if (this._showItemLabelPercent) {
                    s = Fmt.percent(n, this._showItemLabelDecimals);
                }
                else {
                    s = Fmt.number(n, this._showItemLabelDecimals);
                }
            }
        }
        else if (this._showItemValues.length > n2 && this._showItemValues[n2].length > n3) {
            s = this._showItemValues[n2][n3];
        }
        return String.valueOf(s) + this.sScaleLabel[this._iGraphUnits];
    }
    
    public String[] getGraphCatagories() {
        return this._sGraphCatagories;
    }
    
    public GraphType getGraphType() {
        return this._gtGraphType;
    }
    
    public Image getImage(final int n, final int n2) {
        this._iOWidth = 0;
        this._iOHeight = 0;
        final Image image = this.createImage(n, n2);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, n, n2);
        this._gtGraphType.paint(graphics, this);
        return image;
    }
    
    public Image getImageBackground() {
        return this._imageBackground;
    }
    
    public float getMaxValue() {
        float n = Float.MIN_VALUE;
        while (true) {
            Enumeration dataSeries = null;
            Label_0042: {
                try {
                    dataSeries = this.getDataSeries();
                    break Label_0042;
                }
                catch (Exception ex) {
                    return 0.0f;
                }
                final float maxValue = dataSeries.nextElement().getMaxValue(this._iLimit);
                if (maxValue > n) {
                    n = maxValue;
                }
            }
            if (!dataSeries.hasMoreElements()) {
                return n;
            }
            continue;
        }
    }
    
    public float getMinValue() {
        float n = Float.MAX_VALUE;
        while (true) {
            Enumeration dataSeries = null;
            Label_0042: {
                try {
                    dataSeries = this.getDataSeries();
                    break Label_0042;
                }
                catch (Exception ex) {
                    return 0.0f;
                }
                final float minValue = dataSeries.nextElement().getMinValue(this._iLimit);
                if (minValue < n) {
                    n = minValue;
                }
            }
            if (!dataSeries.hasMoreElements()) {
                return n;
            }
            continue;
        }
    }
    
    public Dimension getMinimumSize() {
        return this._dSize;
    }
    
    public Dimension getPreferredSize() {
        return this._dSize;
    }
    
    public Dimension getSize() {
        if (this.bUseOld) {
            return new Dimension(this._iOWidth, this._iOHeight);
        }
        return this.size();
    }
    
    public Title getTitleGraph() {
        return this._titleGraph;
    }
    
    public int getValueCount() {
        int iLimit = Integer.MIN_VALUE;
        while (true) {
            Enumeration dataSeries = null;
            Label_0037: {
                try {
                    dataSeries = this.getDataSeries();
                    break Label_0037;
                }
                catch (Exception ex) {
                    return 0;
                }
                final int valueCount = dataSeries.nextElement().getValueCount();
                if (valueCount > iLimit) {
                    iLimit = valueCount;
                }
            }
            if (!dataSeries.hasMoreElements()) {
                if (iLimit > this._iLimit && this._iLimit != 0) {
                    iLimit = this._iLimit;
                }
                return iLimit;
            }
            continue;
        }
    }
    
    public boolean mouseMove(final Event event, final int xMouse, final int yMouse) {
        this.repaint(0, 0, this.size().width, this.size().height + 1);
        this.bMouse = true;
        this.xMouse = xMouse;
        this.yMouse = yMouse;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.repaint(0, 0, this.size().width, this.size().height + 1);
        this.bMouse = false;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this._iDataSeriesCount == 0) {
            this._iOWidth = -1;
            this._iOHeight = -1;
        }
        else {
            if (this._iOWidth != this.size().width || this._iOHeight != this.size().height) {
                this._iImage = this.createImage(this.getSize().width, this.getSize().height);
                this._gNew = this._iImage.getGraphics();
            }
            if (this._bDataChanged || this._iOWidth != this.size().width || this._iOHeight != this.size().height) {
                this._gNew.setColor(this.getBackground());
                this._gNew.fillRect(0, 0, this.getSize().width, this.getSize().height);
                this._gtGraphType.paint(this._gNew, this);
            }
            this._iOWidth = this.size().width;
            this._iOHeight = this.size().height;
            graphics.drawImage(this._iImage, 0, 0, this);
            if (this.bMouse) {
                final String value = this._gtGraphType.getValue(this.xMouse, this.yMouse, this);
                if (value != null) {
                    graphics.setFont(this.FONT_PLAIN);
                    final int height = graphics.getFontMetrics().getHeight();
                    final int descent = graphics.getFontMetrics().getDescent();
                    final int stringWidth = graphics.getFontMetrics().stringWidth(value);
                    graphics.setColor(this._cBackground);
                    graphics.fillRect(this.xMouse - (stringWidth + height) / 2, this.yMouse - height + descent, stringWidth + height, height);
                    graphics.setColor(this._cForeground);
                    graphics.drawRect(this.xMouse - (stringWidth + height) / 2, this.yMouse - height + descent, stringWidth + height, height);
                    graphics.drawString(value, this.xMouse - stringWidth / 2, this.yMouse);
                }
            }
        }
    }
    
    public void removeAll() {
        this._vDataSeries.removeAllElements();
        this._iDataSeriesCount = 0;
        this._iLimit = 0;
        this._gtGraphType.syncSetup(this);
        this.dataChanged(true);
    }
    
    public void setBackground(final Color cBackground) {
        if (!this._cBackground.equals(cBackground)) {
            super.setBackground(this._cBackground = cBackground);
            this.dataChanged(true);
        }
    }
    
    public void setForeground(final Color cForeground) {
        if (!this._cForeground.equals(cForeground)) {
            this._cForeground = cForeground;
            this.dataChanged(true);
        }
    }
    
    public void setGraphCatagories(final String[] sGraphCatagories) {
        if (this._sGraphCatagories == null || !this._sGraphCatagories.equals(sGraphCatagories)) {
            this._sGraphCatagories = sGraphCatagories;
            this.dataChanged(true);
        }
    }
    
    public void setGraphSize(final int n, final int n2) {
        this._dSize = new Dimension(n, n2);
        this.invalidate();
        this.doLayout();
    }
    
    public void setGraphType(final GraphType gtGraphType) {
        (this._gtGraphType = gtGraphType).syncSetup(this);
        this.dataChanged(true);
    }
    
    public String setGraphUnits(final double n) {
        return this.setGraphUnits(n, 0.0);
    }
    
    public String setGraphUnits(final double n, final double n2) {
        if (n > 1.0E11 || n2 < -1.0E11) {
            this._iGraphUnits = 3;
        }
        else if (n > 1.0E8 || n2 < -1.0E8) {
            this._iGraphUnits = 2;
        }
        else if (n > 100000.0 || n2 < -100000.0) {
            this._iGraphUnits = 1;
        }
        else {
            this._iGraphUnits = 0;
        }
        return this.sScaleLabel[this._iGraphUnits];
    }
    
    public void setTitleGraph(final Title titleGraph) {
        if (this._titleGraph != titleGraph) {
            this._titleGraph = titleGraph;
            this.dataChanged(true);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
