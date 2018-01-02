// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import KJEgui.Fmt;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class Graph extends JPanel implements MouseMotionListener
{
    public static final int BORDER_WIDTH = 8;
    private static final int[] R;
    private static final int[] G;
    private static final int[] B;
    public Dimension _dSize;
    public Legend _legend;
    public Grid _grid;
    public ValueAxis _axisY;
    public CatagoryAxis _axisX;
    public Title _titleGraph;
    public Title _titleXAxis;
    public Title _titleYAxis;
    protected Vector _vDataSeries;
    public GraphType _gtGraphType;
    public Font FONT_TITLE;
    public Font FONT_BOLD;
    public Font FONT_PLAIN;
    public boolean _showItemLabel;
    public int _showItemLabelFmt;
    public int _showItemLabelDecimals;
    public boolean _showItemLabelOnTop;
    public boolean _showZeros;
    public boolean _bUseTextImages;
    public int _iArea;
    protected Color _cBackground;
    public Color _cForeground;
    public Color _cGrid;
    public Color _cGridBackground;
    public Color _cAxisLine;
    public Color _cTextColor;
    public Color _cLblColor;
    public int _iLimit;
    private int _iGraphUnits;
    public String[] _sGraphCatagories;
    public String[] _sGraphCatagoriesBold;
    public String[] _sValueLabels;
    protected Image _imageBackground;
    public Color[] _colorList;
    public String[][] _showItemValues;
    private String[] sScaleLabel;
    private int xMouse;
    private int yMouse;
    protected int _iDataSeriesCount;
    protected boolean _bDataChanged;
    private int _iOWidth;
    private int _iOHeight;
    private Image _iImage;
    private Graphics _gNew;
    
    static {
        R = new int[] { 127, 127, 255, 159, 95, 255, 0, 191, 0, 255, 255, 0, 127, 127, 0, 0, 0, 96, 191, 255, 160 };
        G = new int[] { 127, 31, 255, 223, 0, 127, 127, 191, 0, 0, 255, 255, 0, 0, 127, 0, 200, 255, 255, 255, 200 };
        B = new int[] { 255, 95, 191, 223, 127, 127, 191, 255, 127, 255, 0, 255, 127, 0, 127, 255, 255, 255, 191, 144, 239 };
    }
    
    public Graph(final GraphType gtGraphType, final Image imageBackground) {
        this._dSize = new Dimension(0, 0);
        this._legend = new Legend(1);
        this._grid = new Grid();
        this._axisY = new ValueAxis(0);
        this._axisX = new CatagoryAxis(4);
        this._titleGraph = new Title(0, "");
        this._titleXAxis = new Title(0, "");
        this._titleYAxis = new Title(1, "");
        this._vDataSeries = new Vector();
        this.FONT_TITLE = new Font("Helvetica", 1, 13);
        this.FONT_BOLD = new Font("Helvetica", 1, 11);
        this.FONT_PLAIN = new Font("Helvetica", 0, 11);
        this._showItemLabel = false;
        this._showItemLabelFmt = 3;
        this._showItemLabelDecimals = 0;
        this._showItemLabelOnTop = true;
        this._showZeros = true;
        this._bUseTextImages = false;
        this._iArea = 0;
        this._cBackground = Color.white;
        this._cForeground = Color.black;
        this._cGrid = Color.gray;
        this._cGridBackground = Color.white;
        this._cAxisLine = Color.gray;
        this._cTextColor = Color.black;
        this._cLblColor = Color.black;
        this._iLimit = 0;
        this._iGraphUnits = 0;
        this._showItemValues = null;
        this.sScaleLabel = new String[] { "", "k", "m", "b" };
        this.yMouse = 0;
        this._iDataSeriesCount = 0;
        this._bDataChanged = true;
        this._gtGraphType = gtGraphType;
        this._imageBackground = imageBackground;
        gtGraphType.syncSetup(this);
        this.addMouseMotionListener(this);
    }
    
    public void add(final GraphDataSeries graphDataSeries) throws Exception {
        if (graphDataSeries.getValues() != null) {
            this._vDataSeries.addElement(graphDataSeries);
            ++this._iDataSeriesCount;
            this.dataChanged(true);
        }
    }
    
    public void add(final String s) throws Exception {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.add(new GraphDataSeries(stringTokenizer.nextToken()));
            ++n;
        }
    }
    
    public void blank() {
        this._iOWidth = -1;
        this._iOHeight = -1;
        this.repaint();
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
    
    public static Color getColor(int n) {
        n %= Graph.R.length;
        return new Color(Graph.R[n], Graph.G[n], Graph.B[n]);
    }
    
    public Color getColorList(final int n) {
        Color color = null;
        if (this._colorList != null && this._colorList.length > n) {
            color = this._colorList[n];
        }
        if (color == null) {
            color = getColor(n);
        }
        return color;
    }
    
    public String getDataProperties() {
        String string = "";
        while (true) {
            Enumeration dataSeries = null;
            Label_0050: {
                try {
                    dataSeries = this.getDataSeries();
                    break Label_0050;
                }
                catch (Exception ex) {
                    return "";
                }
                string = String.valueOf(string) + "|" + dataSeries.nextElement().getProperty();
            }
            if (!dataSeries.hasMoreElements()) {
                return string.substring(1);
            }
            continue;
        }
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
    
    public static String getFloatList(final float[] array) {
        if (array == null) {
            return "null";
        }
        String s = Fmt.input(array[0], 5);
        for (int i = 1; i < array.length; ++i) {
            s = String.valueOf(s) + "," + Fmt.input(array[i], 5);
        }
        return s;
    }
    
    public Color getForeground() {
        return this._cForeground;
    }
    
    protected String getFormat(final float n, final int n2, final int n3) {
        String fmt = "";
        if (this._showItemValues == null) {
            if (this._showZeros || n != 0.0f) {
                fmt = Fmt.fmt(this._showItemLabelFmt, (this._showItemLabelFmt == 4) ? (n * 100.0f) : n, this._showItemLabelDecimals);
            }
        }
        else if (this._showItemValues.length > n2 && this._showItemValues[n2].length > n3) {
            fmt = this._showItemValues[n2][n3];
        }
        return String.valueOf(fmt) + this.sScaleLabel[this._iGraphUnits];
    }
    
    public GraphType getGraphType() {
        return this._gtGraphType;
    }
    
    public static String getHEX(final Color color) {
        if (color == null) {
            return null;
        }
        final String hexString = Integer.toHexString(color.getRGB());
        return hexString.substring(2, hexString.length());
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
    
    public String getProperty() {
        return String.valueOf(this._gtGraphType.getGraphType()) + "|" + this._showItemLabel + "|" + this._showItemLabelFmt + "|" + this._showItemLabelDecimals + "|" + this._showItemLabelOnTop + "|" + this._showZeros + "|" + this._bUseTextImages + "|" + this._iArea + "|" + getHEX(this._cBackground) + "|" + getHEX(this._cForeground) + "|" + getHEX(this._cGrid) + "|" + getHEX(this._cGridBackground) + "|" + getHEX(this._cAxisLine) + "|" + getHEX(this._cTextColor) + "|" + getHEX(this._cLblColor) + "|" + this._iLimit + "|" + this._iGraphUnits + "|" + putStringList(this._sGraphCatagories) + "|" + putStringList(this._sGraphCatagoriesBold) + "|" + putStringList(this._sValueLabels) + "|" + putColorList(this._colorList) + "|" + putStringList2(this._showItemValues);
    }
    
    public static String getStr(final String s) {
        if (s == null) {
            return null;
        }
        return s.replace('¶', '\"').replace('±', '|').replace('§', ',');
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
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.repaint(0, 0, this.getSize().width, this.getSize().height + 1);
        this.xMouse = mouseEvent.getX();
        this.yMouse = mouseEvent.getY();
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this._iDataSeriesCount == 0) {
            graphics.setColor(this._cBackground);
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this._iOWidth = -1;
            this._iOHeight = -1;
        }
        else {
            if (this._iOWidth != this.getSize().width || this._iOHeight != this.getSize().height) {
                this._iImage = this.createImage(this.getSize().width, this.getSize().height);
                this._gNew = this._iImage.getGraphics();
            }
            if (this._bDataChanged || this._iOWidth != this.getSize().width || this._iOHeight != this.getSize().height) {
                this._gNew.setColor(this.getBackground());
                this._gNew.fillRect(0, 0, this.getSize().width, this.getSize().height);
                this._gtGraphType.paint(this._gNew, this, this.getSize(), false);
            }
            this._iOWidth = this.getSize().width;
            this._iOHeight = this.getSize().height;
            graphics.drawImage(this._iImage, 0, 0, this);
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
    
    public static String putColorList(final Color[] array) {
        if (array == null) {
            return "null";
        }
        String s = getHEX(array[0]);
        for (int i = 1; i < array.length; ++i) {
            s = String.valueOf(s) + "," + getHEX(array[i]);
        }
        return s;
    }
    
    public static String putStr(final String s) {
        if (s == null) {
            return null;
        }
        return s.replace('\"', '¶').replace('|', '±').replace(',', '§');
    }
    
    public static String putStringList(final String[] array) {
        if (array == null) {
            return "null";
        }
        String s = putStr(array[0]);
        for (int i = 1; i < array.length; ++i) {
            s = String.valueOf(s) + "," + putStr(array[i]);
        }
        return s;
    }
    
    public static String putStringList2(final String[][] array) {
        if (array == null) {
            return "null";
        }
        String s = putStringList(array[0]);
        for (int i = 1; i < array.length; ++i) {
            s = String.valueOf(s) + "~" + putStringList(array[i]);
        }
        return s;
    }
    
    public void removeAll() {
        this._vDataSeries.removeAllElements();
        this._iDataSeriesCount = 0;
        this._iLimit = 0;
        this._gtGraphType.syncSetup(this);
        this._legend._cBGColor = null;
        this.dataChanged(true);
    }
    
    public void setBackground(final Color cBackground) {
        super.setBackground(this._cBackground = cBackground);
        this.dataChanged(true);
    }
    
    public void setForeground(final Color cForeground) {
        this._cForeground = cForeground;
        this.dataChanged(true);
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
    
    public void setProperty(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String nextToken = stringTokenizer.nextToken();
        if (nextToken.equals("GraphColumn")) {
            this.setGraphType(new GraphColumn());
        }
        else if (nextToken.equals("GraphPie")) {
            this.setGraphType(new GraphPie());
        }
        else if (nextToken.equals("GraphCatagories")) {
            this.setGraphType(new GraphCatagories());
        }
        else if (nextToken.equals("GraphStacked")) {
            this.setGraphType(new GraphStacked());
        }
        else {
            this.setGraphType(new GraphLine());
        }
        this.removeAll();
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer.nextToken();
            switch (n) {
                case 0: {
                    this._showItemLabel = sgv(nextToken2, this._showItemLabel);
                    break;
                }
                case 1: {
                    this._showItemLabelFmt = sgv(nextToken2, this._showItemLabelFmt);
                    break;
                }
                case 2: {
                    this._showItemLabelDecimals = sgv(nextToken2, this._showItemLabelDecimals);
                    break;
                }
                case 3: {
                    this._showItemLabelOnTop = sgv(nextToken2, this._showItemLabelOnTop);
                    break;
                }
                case 4: {
                    this._showZeros = sgv(nextToken2, this._showZeros);
                    break;
                }
                case 5: {
                    this._bUseTextImages = sgv(nextToken2, this._bUseTextImages);
                    break;
                }
                case 6: {
                    this._iArea = sgv(nextToken2, this._iArea);
                    break;
                }
                case 7: {
                    this._cBackground = sgv(nextToken2, this._cBackground);
                    break;
                }
                case 8: {
                    this._cForeground = sgv(nextToken2, this._cForeground);
                    break;
                }
                case 9: {
                    this._cGrid = sgv(nextToken2, this._cGrid);
                    break;
                }
                case 10: {
                    this._cGridBackground = sgv(nextToken2, this._cGridBackground);
                    break;
                }
                case 11: {
                    this._cAxisLine = sgv(nextToken2, this._cAxisLine);
                    break;
                }
                case 12: {
                    this._cTextColor = sgv(nextToken2, this._cTextColor);
                    break;
                }
                case 13: {
                    this._cLblColor = sgv(nextToken2, this._cLblColor);
                    break;
                }
                case 14: {
                    this._iLimit = sgv(nextToken2, this._iLimit);
                    break;
                }
                case 15: {
                    this._iGraphUnits = sgv(nextToken2, this._iGraphUnits);
                    break;
                }
                case 16: {
                    this._sGraphCatagories = sgv(nextToken2, this._sGraphCatagories);
                    break;
                }
                case 17: {
                    this._sGraphCatagoriesBold = sgv(nextToken2, this._sGraphCatagoriesBold);
                    break;
                }
                case 18: {
                    this._sValueLabels = sgv(nextToken2, this._sValueLabels);
                    break;
                }
                case 19: {
                    this._colorList = sgv(nextToken2, this._colorList);
                    break;
                }
                case 20: {
                    this._showItemValues = sgv(nextToken2, this._showItemValues);
                    break;
                }
            }
            ++n;
        }
    }
    
    public void setTitleGraph(final Title titleGraph) {
        if (this._titleGraph != titleGraph) {
            this._titleGraph = titleGraph;
            this.dataChanged(true);
        }
    }
    
    public static String sgv(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equals("")) {
            return " ";
        }
        return getStr(s);
    }
    
    public static float sgv(final String s, final float n) {
        float floatValue;
        if (s == null) {
            floatValue = 0.0f;
        }
        else if (s.trim().equals("")) {
            floatValue = 0.0f;
        }
        else {
            try {
                floatValue = new Float(s);
            }
            catch (NumberFormatException ex) {
                floatValue = 0.0f;
            }
        }
        return floatValue;
    }
    
    public static int sgv(final String s, final int n) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return int1;
    }
    
    public static Color sgv(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        if (s.equals("")) {
            return color;
        }
        return Color.decode("#" + s);
    }
    
    public static String sgv(final String s, final String s2) {
        if (s == null) {
            return s2;
        }
        if (s.equals(" ")) {
            return s2;
        }
        return getStr(s);
    }
    
    public static boolean sgv(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        if (s.equals("")) {
            return b;
        }
        return s.trim().equals("true");
    }
    
    public static float[] sgv(final String s, final float[] array) {
        if (s.equals("null")) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final float[] array2 = new float[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                array2[n] = Float.parseFloat(nextToken);
            }
            catch (NumberFormatException ex) {
                array2[n] = 0.0f;
            }
            ++n;
        }
        return array2;
    }
    
    public static Color[] sgv(final String s, final Color[] array) {
        if (s.equals("null")) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final Color[] array2 = new Color[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array2[n++] = sgv(stringTokenizer.nextToken(), getColor(n));
        }
        return array2;
    }
    
    public static String[] sgv(final String s, final String[] array) {
        if (s.equals("null")) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final String[] array2 = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array2[n++] = getStr(stringTokenizer.nextToken());
        }
        return array2;
    }
    
    public static String[][] sgv(final String s, final String[][] array) {
        if (s.equals("null")) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "~");
        final String[][] array2 = new String[stringTokenizer.countTokens()][];
        final String[] array3 = { null };
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array2[n++] = sgv(stringTokenizer.nextToken(), array3);
        }
        return array2;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
