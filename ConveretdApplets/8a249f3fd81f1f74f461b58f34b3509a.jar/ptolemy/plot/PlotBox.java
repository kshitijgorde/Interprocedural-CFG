// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.awt.Rectangle;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Button;
import java.util.Vector;
import java.text.NumberFormat;
import java.awt.FontMetrics;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;
import java.awt.Panel;

public class PlotBox extends Panel
{
    protected transient double _yMax;
    protected transient double _yMin;
    protected transient double _xMax;
    protected transient double _xMin;
    protected static final transient double _PADDING = 0.05;
    protected transient boolean _xRangeGiven;
    protected transient boolean _yRangeGiven;
    protected double _xBottom;
    protected double _xTop;
    protected double _yBottom;
    protected double _yTop;
    protected boolean _xlog;
    protected boolean _ylog;
    protected static final double _LOG10SCALE;
    protected boolean _grid;
    protected Color _background;
    protected Color _foreground;
    protected int _topPadding;
    protected int _bottomPadding;
    protected int _rightPadding;
    protected int _leftPadding;
    protected int _ulx;
    protected int _uly;
    protected int _lrx;
    protected int _lry;
    protected double _yscale;
    protected double _xscale;
    protected boolean _usecolor;
    protected static Color[] _colors;
    protected int _width;
    protected int _height;
    protected URL _documentBase;
    private String _filespec;
    private double _xlowgiven;
    private double _xhighgiven;
    private double _ylowgiven;
    private double _yhighgiven;
    private boolean _binary;
    private double _ytickMax;
    private double _ytickMin;
    private double _xtickMax;
    private double _xtickMin;
    private int _yExp;
    private int _xExp;
    private double _ytickscale;
    private double _xtickscale;
    private Font _labelfont;
    private Font _superscriptfont;
    private Font _titlefont;
    private FontMetrics _labelFontMetrics;
    private FontMetrics _superscriptFontMetrics;
    private FontMetrics _titleFontMetrics;
    private transient NumberFormat _numberFormat;
    private transient int _gridCurJuke;
    private transient double _gridBase;
    private transient String[] _errorMsg;
    private String _xlabel;
    private String _ylabel;
    private String _title;
    private Vector _legendStrings;
    private Vector _legendDatasets;
    private Vector _xticks;
    private Vector _xticklabels;
    private Vector _yticks;
    private Vector _yticklabels;
    private transient Button _fillButton;
    private transient int _zoomx;
    private transient int _zoomy;
    private transient int _zoomxn;
    private transient int _zoomyn;
    private transient boolean _zoomin;
    private transient boolean _zoomout;
    private transient boolean _drawn;
    
    public PlotBox() {
        this._yMax = 0.0;
        this._yMin = 0.0;
        this._xMax = 0.0;
        this._xMin = 0.0;
        this._xRangeGiven = false;
        this._yRangeGiven = false;
        this._xBottom = Double.MAX_VALUE;
        this._xTop = -1.7976931348623157E308;
        this._yBottom = Double.MAX_VALUE;
        this._yTop = -1.7976931348623157E308;
        this._xlog = false;
        this._ylog = false;
        this._grid = true;
        this._background = Color.white;
        this._foreground = Color.black;
        this._topPadding = 10;
        this._bottomPadding = 5;
        this._rightPadding = 10;
        this._leftPadding = 10;
        this._ulx = 1;
        this._uly = 1;
        this._lrx = 100;
        this._lry = 100;
        this._yscale = 1.0;
        this._xscale = 1.0;
        this._usecolor = true;
        this._width = 400;
        this._height = 400;
        this._documentBase = null;
        this._filespec = null;
        this._binary = false;
        this._ytickMax = 0.0;
        this._ytickMin = 0.0;
        this._xtickMax = 0.0;
        this._xtickMin = 0.0;
        this._yExp = 0;
        this._xExp = 0;
        this._ytickscale = 0.0;
        this._xtickscale = 0.0;
        this._labelfont = null;
        this._superscriptfont = null;
        this._titlefont = null;
        this._labelFontMetrics = null;
        this._superscriptFontMetrics = null;
        this._titleFontMetrics = null;
        this._numberFormat = null;
        this._gridCurJuke = 0;
        this._gridBase = 0.0;
        this._legendStrings = new Vector();
        this._legendDatasets = new Vector();
        this._xticks = null;
        this._xticklabels = null;
        this._yticks = null;
        this._yticklabels = null;
        this._fillButton = null;
        this._zoomx = -1;
        this._zoomy = -1;
        this._zoomxn = -1;
        this._zoomyn = -1;
        this._zoomin = false;
        this._zoomout = false;
        this._drawn = false;
        this.setLayout(new FlowLayout(2));
        this.addMouseListener(new ZoomListener());
        this.addMouseMotionListener(new DragListener());
        this._measureFonts();
    }
    
    public void addLegend(final int n, final String s) {
        this._legendStrings.addElement(s);
        this._legendDatasets.addElement(new Integer(n));
    }
    
    public void addXTick(final String s, final double n) {
        if (this._xticks == null) {
            this._xticks = new Vector();
            this._xticklabels = new Vector();
        }
        this._xticks.addElement(new Double(n));
        this._xticklabels.addElement(s);
    }
    
    public void addYTick(final String s, final double n) {
        if (this._yticks == null) {
            this._yticks = new Vector();
            this._yticklabels = new Vector();
        }
        this._yticks.addElement(new Double(n));
        this._yticklabels.addElement(s);
    }
    
    public synchronized void clear(final boolean b) {
        this._xBottom = Double.MAX_VALUE;
        this._xTop = -1.7976931348623157E308;
        this._yBottom = Double.MAX_VALUE;
        this._yTop = -1.7976931348623157E308;
        if (b) {
            this._yMax = 0.0;
            this._yMin = 0.0;
            this._xMax = 0.0;
            this._xMin = 0.0;
            this._xRangeGiven = false;
            this._yRangeGiven = false;
            this._xlog = false;
            this._ylog = false;
            this._grid = true;
            this._usecolor = true;
            this._filespec = null;
            this._xlabel = null;
            this._ylabel = null;
            this._title = null;
            this._legendStrings = new Vector();
            this._legendDatasets = new Vector();
            this._xticks = null;
            this._xticklabels = null;
            this._yticks = null;
            this._yticklabels = null;
        }
    }
    
    public synchronized void fillPlot() {
        this._setXRange(this._xBottom, this._xTop);
        this._setYRange(this._yBottom, this._yTop);
        this.repaint();
    }
    
    public static Color getColorByName(final String s) {
        try {
            return new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            final String[][] array = { { "black", "00000" }, { "white", "ffffff" }, { "red", "ff0000" }, { "green", "00ff00" }, { "blue", "0000ff" } };
            for (int i = 0; i < array.length; ++i) {
                if (s.equals(array[i][0])) {
                    try {
                        return new Color(Integer.parseInt(array[i][1], 16));
                    }
                    catch (NumberFormatException ex2) {}
                }
            }
            return null;
        }
    }
    
    public String getDataurl() {
        return this._filespec;
    }
    
    public URL getDocumentBase() {
        return this._documentBase;
    }
    
    public String getLegend(final int n) {
        final int index = this._legendDatasets.indexOf(new Integer(n), 0);
        if (index != -1) {
            return (String)this._legendStrings.elementAt(index);
        }
        return null;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this._width, this._height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this._width, this._height);
    }
    
    public void init() {
        this.setButtons(true);
        if (this._filespec != null) {
            this.parseFile(this._filespec, this._documentBase);
        }
    }
    
    public void paint(final Graphics graphics) {
        this._drawPlot(graphics, true);
    }
    
    public void parseFile(final String s) {
        this.parseFile(s, null);
    }
    
    public void parseFile(final String s, URL documentBase) {
        Label_0275: {
            if (s == null || s.length() == 0) {
                final DataInputStream dataInputStream = new DataInputStream(System.in);
                break Label_0275;
            }
            DataInputStream dataInputStream;
            try {
                if (documentBase == null && this._documentBase != null) {
                    documentBase = this._documentBase;
                }
                URL url;
                if (documentBase == null) {
                    url = new URL(s);
                }
                else {
                    try {
                        url = new URL(documentBase, s);
                    }
                    catch (NullPointerException ex6) {
                        url = new URL(s);
                    }
                }
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (MalformedURLException ex7) {
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(s));
                }
                catch (FileNotFoundException ex) {
                    (this._errorMsg = new String[2])[0] = "File not found: " + s;
                    this._errorMsg[1] = ex.getMessage();
                    return;
                }
                catch (SecurityException ex2) {
                    (this._errorMsg = new String[2])[0] = "Security Exception: " + s;
                    this._errorMsg[1] = ex2.getMessage();
                    return;
                }
            }
            catch (IOException ex3) {
                (this._errorMsg = new String[3])[0] = "Failure opening URL: ";
                this._errorMsg[1] = " " + s;
                this._errorMsg[2] = ex3.getMessage();
                return;
            }
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
                for (String s2 = bufferedReader.readLine(); s2 != null; s2 = bufferedReader.readLine()) {
                    this._parseLine(s2);
                }
            }
            catch (MalformedURLException ex4) {
                (this._errorMsg = new String[2])[0] = "Malformed URL: " + s;
                this._errorMsg[1] = ex4.getMessage();
            }
            catch (IOException ex5) {
                (this._errorMsg = new String[2])[0] = "Failure reading data: " + s;
                this._errorMsg[1] = ex5.getMessage();
                this._errorMsg[1] = ex5.getMessage();
            }
            finally {
                try {
                    dataInputStream.close();
                }
                catch (IOException ex8) {}
            }
        }
    }
    
    public void read(final InputStream inputStream) throws IOException {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
                    this._parseLine(s);
                }
            }
            finally {
                bufferedReader.close();
            }
        }
        catch (IOException ex) {
            (this._errorMsg = new String[2])[0] = "Failure reading input data.";
            this._errorMsg[1] = ex.getMessage();
            throw ex;
        }
    }
    
    public void setBounds(final int n, final int n2, final int width, final int height) {
        this._width = width;
        this._height = height;
        super.setBounds(n, n2, this._width, this._height);
    }
    
    public void setButtons(final boolean visible) {
        if (this._fillButton == null) {
            (this._fillButton = new Button("fill")).addActionListener(new FillButtonListener());
            this.add(this._fillButton);
        }
        this._fillButton.setVisible(visible);
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(this._width = width, this._height = height);
    }
    
    public void setBackground(final Color background) {
        super.setBackground(this._background = background);
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(this._foreground = foreground);
    }
    
    public void setBinary(final boolean binary) {
        this._binary = binary;
    }
    
    public void setDataurl(final String filespec) {
        this._filespec = filespec;
    }
    
    public void setDocumentBase(final URL documentBase) {
        this._documentBase = documentBase;
    }
    
    public void setGrid(final boolean grid) {
        this._grid = grid;
    }
    
    public void setLabelFont(final String s) {
        this._labelfont = Font.decode(s);
    }
    
    public void setTitle(final String title) {
        this._title = title;
    }
    
    public void setTitleFont(final String s) {
        this._titlefont = Font.decode(s);
        this._titleFontMetrics = this.getFontMetrics(this._titlefont);
    }
    
    public void setXLabel(final String xlabel) {
        this._xlabel = xlabel;
    }
    
    public void setXLog(final boolean xlog) {
        this._xlog = xlog;
    }
    
    public void setXRange(final double xlowgiven, final double xhighgiven) {
        this._xRangeGiven = true;
        this._setXRange(this._xlowgiven = xlowgiven, this._xhighgiven = xhighgiven);
    }
    
    public void setYLabel(final String ylabel) {
        this._ylabel = ylabel;
    }
    
    public void setYLog(final boolean ylog) {
        this._ylog = ylog;
    }
    
    public void setYRange(final double ylowgiven, final double yhighgiven) {
        this._yRangeGiven = true;
        this._setYRange(this._ylowgiven = ylowgiven, this._yhighgiven = yhighgiven);
    }
    
    public void write(final OutputStream outputStream) {
        final PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(outputStream), false);
        this._write(printWriter);
        printWriter.flush();
        printWriter.close();
    }
    
    public synchronized void zoom(final double n, final double n2, final double n3, final double n4) {
        this.setXRange(n, n3);
        this.setYRange(n2, n4);
        this.repaint();
    }
    
    protected synchronized void _drawPlot(final Graphics graphics, final boolean b) {
        if (graphics == null) {
            throw new RuntimeException("PlotBox._drawPlot: Attempt to draw axes without a Graphics object.");
        }
        final Rectangle bounds = this.getBounds();
        graphics.setPaintMode();
        if (this._errorMsg != null) {
            int n2;
            final int n = n2 = this._labelFontMetrics.getHeight() + 2;
            graphics.setColor(Color.black);
            for (int i = 0; i < this._errorMsg.length; ++i) {
                graphics.drawString(this._errorMsg[i], 10, n2);
                n2 += n;
                System.err.println(this._errorMsg[i]);
            }
            return;
        }
        if (!this._xRangeGiven) {
            if (this._xBottom > this._xTop) {
                this._setXRange(0.0, 0.0);
            }
            else {
                this._setXRange(this._xBottom, this._xTop);
            }
        }
        if (!this._yRangeGiven) {
            if (this._yBottom > this._yTop) {
                this._setYRange(0.0, 0.0);
            }
            else {
                this._setYRange(this._yBottom, this._yTop);
            }
        }
        int n3 = 0;
        final int height = this._titleFontMetrics.getHeight();
        if (this._title == null) {
            this._title = "";
        }
        if (this._title != null || this._yExp != 0) {
            n3 = height + this._topPadding;
        }
        graphics.setFont(this._labelfont);
        final int height2 = this._labelFontMetrics.getHeight();
        final int n4 = height2 / 2;
        final int n5 = bounds.height - 5;
        int n6 = bounds.width - this._rightPadding;
        if (this._xlog) {
            this._xExp = (int)Math.floor(this._xtickMin);
        }
        if (this._xExp != 0 && this._xticks == null) {
            final String string = Integer.toString(this._xExp);
            n6 -= this._superscriptFontMetrics.stringWidth(string);
            graphics.setFont(this._superscriptfont);
            if (!this._xlog) {
                graphics.drawString(string, n6, n5 - n4);
                n6 -= this._labelFontMetrics.stringWidth("x10");
                graphics.setFont(this._labelfont);
                graphics.drawString("x10", n6, n5);
            }
            this._bottomPadding = 3 * height2 / 2 + 5;
        }
        if (this._xlabel != null && this._bottomPadding < height2 + 5) {
            this._bottomPadding = height + 5;
        }
        this._uly = n3 + 5;
        this._lry = bounds.height - height2 - this._bottomPadding - 3;
        final int n7 = this._lry - this._uly;
        this._yscale = n7 / (this._yMax - this._yMin);
        this._ytickscale = n7 / (this._ytickMax - this._ytickMin);
        int n8 = 2 + n7 / (height2 + 10);
        final double roundUp = this._roundUp((this._ytickMax - this._ytickMin) / n8);
        final double n9 = roundUp * Math.ceil(this._ytickMin / roundUp);
        int n10 = 0;
        final String[] array = new String[n8];
        final int[] array2 = new int[n8];
        int n11 = 0;
        if (this._yticks == null) {
            Vector gridInit = null;
            if (this._ylog) {
                gridInit = this._gridInit(n9, roundUp, true, null);
            }
            final int numFracDigits = this._numFracDigits(roundUp);
            double gridStep = n9;
            if (this._ylog) {
                gridStep = this._gridStep(gridInit, n9, roundUp, this._ylog);
            }
            for (double gridStep2 = gridStep; gridStep2 <= this._ytickMax; gridStep2 = this._gridStep(gridInit, gridStep2, roundUp, this._ylog)) {
                if (n11 >= n8) {
                    break;
                }
                String s;
                if (this._ylog) {
                    s = this._formatLogNum(gridStep2, numFracDigits);
                }
                else {
                    s = this._formatNum(gridStep2, numFracDigits);
                }
                array[n11] = s;
                final int stringWidth = this._labelFontMetrics.stringWidth(s);
                array2[n11++] = stringWidth;
                if (stringWidth > n10) {
                    n10 = stringWidth;
                }
            }
        }
        else {
            final Enumeration<String> elements = (Enumeration<String>)this._yticklabels.elements();
            while (elements.hasMoreElements()) {
                final int stringWidth2 = this._labelFontMetrics.stringWidth(elements.nextElement());
                if (stringWidth2 > n10) {
                    n10 = stringWidth2;
                }
            }
        }
        if (this._ylabel != null) {
            this._ulx = n10 + this._labelFontMetrics.stringWidth("W") + this._leftPadding;
        }
        else {
            this._ulx = n10 + this._leftPadding;
        }
        this._lrx = bounds.width - this._drawLegend(graphics, bounds.width - this._rightPadding, this._uly) - this._rightPadding;
        final int n12 = this._lrx - this._ulx;
        this._xscale = n12 / (this._xMax - this._xMin);
        this._xtickscale = n12 / (this._xtickMax - this._xtickMin);
        graphics.setColor(Color.white);
        graphics.fillRect(this._ulx, this._uly, n12, n7);
        graphics.setColor(this._foreground);
        graphics.drawRect(this._ulx, this._uly, n12, n7);
        final int n13 = 5;
        final int n14 = this._ulx + n13;
        final int n15 = this._lrx - n13;
        if (this._yticks == null) {
            Vector gridInit2 = null;
            double gridStep3 = n9;
            if (this._ylog) {
                gridInit2 = this._gridInit(n9, roundUp, true, null);
                gridStep3 = this._gridStep(gridInit2, n9, roundUp, this._ylog);
                n8 = n11;
            }
            int n16 = 0;
            boolean ylog = this._ylog;
            for (double gridStep4 = gridStep3; gridStep4 <= this._ytickMax && n16 < n8; gridStep4 = this._gridStep(gridInit2, gridStep4, roundUp, this._ylog)) {
                final int n17 = this._lry - (int)((gridStep4 - this._ytickMin) * this._ytickscale);
                int n18 = 0;
                if (n16 > 0 && !this._ylog) {
                    n18 = n4;
                }
                graphics.drawLine(this._ulx, n17, n14, n17);
                graphics.drawLine(this._lrx, n17, n15, n17);
                if (this._grid && n17 != this._uly && n17 != this._lry) {
                    graphics.setColor(Color.lightGray);
                    graphics.drawLine(n14, n17, n15, n17);
                    graphics.setColor(this._foreground);
                }
                if (this._ylog && array[n16].indexOf(101) != -1) {
                    ylog = false;
                }
                graphics.drawString(array[n16], this._ulx - array2[n16++] - 4, n17 + n18);
            }
            if (this._ylog) {
                final Vector gridInit3 = this._gridInit(n9, roundUp, false, gridInit2);
                if (gridInit3.size() > 0) {
                    for (double n19 = (roundUp > 1.0) ? 1.0 : roundUp, n20 = this._gridStep(gridInit3, n9, n19, this._ylog); n20 <= this._ytickMax; n20 = this._gridStep(gridInit3, n20, n19, this._ylog)) {
                        final int n21 = this._lry - (int)((n20 - this._ytickMin) * this._ytickscale);
                        if (this._grid && n21 != this._uly && n21 != this._lry) {
                            graphics.setColor(Color.lightGray);
                            graphics.drawLine(this._ulx + 1, n21, this._lrx - 1, n21);
                            graphics.setColor(this._foreground);
                        }
                    }
                }
                if (ylog) {
                    this._yExp = (int)Math.floor(gridStep3);
                }
                else {
                    this._yExp = 0;
                }
            }
            if (this._yExp != 0) {
                graphics.drawString("x10", 2, n3);
                graphics.setFont(this._superscriptfont);
                graphics.drawString(Integer.toString(this._yExp), this._labelFontMetrics.stringWidth("x10") + 2, n3 - n4);
                graphics.setFont(this._labelfont);
            }
        }
        else {
            final Enumeration<Double> elements2 = this._yticks.elements();
            final Enumeration<String> elements3 = (Enumeration<String>)this._yticklabels.elements();
            while (elements3.hasMoreElements()) {
                final String s2 = elements3.nextElement();
                final double doubleValue = elements2.nextElement();
                if (doubleValue <= this._yMax) {
                    if (doubleValue < this._yMin) {
                        continue;
                    }
                    final int n22 = this._lry - (int)((doubleValue - this._yMin) * this._yscale);
                    int n23 = 0;
                    if (doubleValue < this._lry - height2) {
                        n23 = n4;
                    }
                    graphics.drawLine(this._ulx, n22, n14, n22);
                    graphics.drawLine(this._lrx, n22, n15, n22);
                    if (this._grid && n22 != this._uly && n22 != this._lry) {
                        graphics.setColor(Color.lightGray);
                        graphics.drawLine(n14, n22, n15, n22);
                        graphics.setColor(this._foreground);
                    }
                    graphics.drawString(s2, this._ulx - this._labelFontMetrics.stringWidth(s2) - 3, n22 + n23);
                }
            }
        }
        final int n24 = this._uly + n13;
        final int n25 = this._lry - n13;
        if (this._xticks == null) {
            int n26 = 10;
            final int stringWidth3 = this._labelFontMetrics.stringWidth("8");
            if (this._xlog) {
                n26 = 2 + n12 / (stringWidth3 * 6 + 10);
            }
            else {
                int n27 = 0;
                while (n27++ <= 10) {
                    final int numFracDigits2 = this._numFracDigits(this._roundUp((this._xtickMax - this._xtickMin) / n26));
                    int numIntDigits = this._numIntDigits(this._xtickMax);
                    final int numIntDigits2 = this._numIntDigits(this._xtickMin);
                    if (numIntDigits < numIntDigits2) {
                        numIntDigits = numIntDigits2;
                    }
                    final int n28 = stringWidth3 * (numFracDigits2 + 2 + numIntDigits);
                    final int n29 = n26;
                    n26 = 2 + n12 / (n28 + 10);
                    if (n26 - n29 <= 1) {
                        break;
                    }
                    if (n29 - n26 <= 1) {
                        break;
                    }
                }
            }
            final double roundUp2 = this._roundUp((this._xtickMax - this._xtickMin) / n26);
            final int numFracDigits3 = this._numFracDigits(roundUp2);
            final double n30 = roundUp2 * Math.ceil(this._xtickMin / roundUp2);
            Vector gridInit4 = null;
            double gridRoundUp = n30;
            if (this._xlog) {
                gridInit4 = this._gridInit(n30, roundUp2, true, null);
                gridRoundUp = this._gridRoundUp(gridInit4, n30);
            }
            boolean xlog = this._xlog;
            for (double gridStep5 = gridRoundUp; gridStep5 <= this._xtickMax; gridStep5 = this._gridStep(gridInit4, gridStep5, roundUp2, this._xlog)) {
                String s3;
                if (this._xlog) {
                    s3 = this._formatLogNum(gridStep5, numFracDigits3);
                    if (s3.indexOf(101) != -1) {
                        xlog = false;
                    }
                }
                else {
                    s3 = this._formatNum(gridStep5, numFracDigits3);
                }
                final int n31 = this._ulx + (int)((gridStep5 - this._xtickMin) * this._xtickscale);
                graphics.drawLine(n31, this._uly, n31, n24);
                graphics.drawLine(n31, this._lry, n31, n25);
                if (this._grid && n31 != this._ulx && n31 != this._lrx) {
                    graphics.setColor(Color.lightGray);
                    graphics.drawLine(n31, n24, n31, n25);
                    graphics.setColor(this._foreground);
                }
                graphics.drawString(s3, n31 - this._labelFontMetrics.stringWidth(s3) / 2, this._lry + 3 + height2);
            }
            if (this._xlog) {
                final double n32 = (roundUp2 > 1.0) ? 1.0 : roundUp2;
                final double n33 = n32 * Math.ceil(this._xtickMin / n32);
                final Vector gridInit5 = this._gridInit(n33, n32, false, gridInit4);
                if (gridInit5.size() > 0) {
                    for (double n34 = this._gridStep(gridInit5, n33, n32, this._xlog); n34 <= this._xtickMax; n34 = this._gridStep(gridInit5, n34, n32, this._xlog)) {
                        final int n35 = this._ulx + (int)((n34 - this._xtickMin) * this._xtickscale);
                        if (this._grid && n35 != this._ulx && n35 != this._lrx) {
                            graphics.setColor(Color.lightGray);
                            graphics.drawLine(n35, this._uly + 1, n35, this._lry - 1);
                            graphics.setColor(this._foreground);
                        }
                    }
                }
                if (xlog) {
                    this._xExp = (int)Math.floor(n33);
                    graphics.setFont(this._superscriptfont);
                    graphics.drawString(Integer.toString(this._xExp), n6, n5 - n4);
                    final int n36 = n6 - this._labelFontMetrics.stringWidth("x10");
                    graphics.setFont(this._labelfont);
                    graphics.drawString("x10", n36, n5);
                }
                else {
                    this._xExp = 0;
                }
            }
        }
        else {
            final Enumeration<Double> elements4 = this._xticks.elements();
            final Enumeration<String> elements5 = (Enumeration<String>)this._xticklabels.elements();
            while (elements5.hasMoreElements()) {
                final String s4 = elements5.nextElement();
                final double doubleValue2 = elements4.nextElement();
                if (doubleValue2 <= this._xMax) {
                    if (doubleValue2 < this._xMin) {
                        continue;
                    }
                    final int n37 = this._ulx + (int)((doubleValue2 - this._xMin) * this._xscale);
                    graphics.drawLine(n37, this._uly, n37, n24);
                    graphics.drawLine(n37, this._lry, n37, n25);
                    if (this._grid && n37 != this._ulx && n37 != this._lrx) {
                        graphics.setColor(Color.lightGray);
                        graphics.drawLine(n37, n24, n37, n25);
                        graphics.setColor(this._foreground);
                    }
                    graphics.drawString(s4, n37 - this._labelFontMetrics.stringWidth(s4) / 2, this._lry + 3 + height2);
                }
            }
        }
        graphics.setColor(this._foreground);
        if (this._title != null) {
            graphics.setFont(this._titlefont);
            graphics.drawString(this._title, this._ulx + (n12 - this._titleFontMetrics.stringWidth(this._title)) / 2, n3);
        }
        graphics.setFont(this._labelfont);
        if (this._xlabel != null) {
            graphics.drawString(this._xlabel, this._ulx + (n12 - this._labelFontMetrics.stringWidth(this._xlabel)) / 2, n5);
        }
        final int n38 = 2 + this._labelFontMetrics.stringWidth("W") / 2;
        final int n39 = height2;
        if (this._ylabel != null) {
            final int length = this._ylabel.length();
            int n40 = this._uly + (this._lry - this._uly) / 2 - length * n39 / 2 + n39;
            for (int j = 0; j < length; ++j) {
                final String substring = this._ylabel.substring(j, j + 1);
                graphics.drawString(substring, n38 - this._labelFontMetrics.stringWidth(substring) / 2, n40);
                n40 += n39;
            }
        }
    }
    
    protected void _drawPoint(final Graphics graphics, final int n, final long n2, final long n3, final boolean b) {
        if ((n3 > this._lry || n3 < this._uly || n2 > this._lrx || n2 < this._ulx) && b) {
            return;
        }
        graphics.fillRect((int)n2 - 6, (int)n3 - 6, 6, 6);
    }
    
    protected boolean _parseLine(final String s) {
        final String s2 = new String(s.toLowerCase());
        if (s2.startsWith("#")) {
            return true;
        }
        if (s2.startsWith("titletext:")) {
            this.setTitle(s.substring(10).trim());
            return true;
        }
        if (s2.startsWith("xlabel:")) {
            this.setXLabel(s.substring(7).trim());
            return true;
        }
        if (s2.startsWith("ylabel:")) {
            this.setYLabel(s.substring(7).trim());
            return true;
        }
        if (s2.startsWith("xrange:")) {
            final int index = s.indexOf(",", 7);
            if (index > 0) {
                final String trim = s.substring(7, index).trim();
                final String trim2 = s.substring(index + 1).trim();
                try {
                    this.setXRange(new Double(trim), new Double(trim2));
                }
                catch (NumberFormatException ex) {}
            }
            return true;
        }
        if (s2.startsWith("yrange:")) {
            final int index2 = s.indexOf(",", 7);
            if (index2 > 0) {
                final String trim3 = s.substring(7, index2).trim();
                final String trim4 = s.substring(index2 + 1).trim();
                try {
                    this.setYRange(new Double(trim3), new Double(trim4));
                }
                catch (NumberFormatException ex2) {}
            }
            return true;
        }
        if (s2.startsWith("xticks:")) {
            this._parsePairs(s.substring(7), true);
            return true;
        }
        if (s2.startsWith("yticks:")) {
            this._parsePairs(s.substring(7), false);
            return true;
        }
        if (s2.startsWith("xlog:")) {
            if (s2.indexOf("off", 5) >= 0) {
                this._xlog = false;
            }
            else {
                this._xlog = true;
            }
            return true;
        }
        if (s2.startsWith("ylog:")) {
            if (s2.indexOf("off", 5) >= 0) {
                this._ylog = false;
            }
            else {
                this._ylog = true;
            }
            return true;
        }
        if (s2.startsWith("grid:")) {
            if (s2.indexOf("off", 5) >= 0) {
                this._grid = false;
            }
            else {
                this._grid = true;
            }
            return true;
        }
        if (s2.startsWith("color:")) {
            if (s2.indexOf("off", 6) >= 0) {
                this._usecolor = false;
            }
            else {
                this._usecolor = true;
            }
            return true;
        }
        return false;
    }
    
    protected void _setButtonsVisibility(final boolean visible) {
        this._fillButton.setVisible(visible);
    }
    
    protected void _write(final PrintWriter printWriter) {
        printWriter.println("# Ptolemy plot, version 2.0");
        if (this._title != null) {
            printWriter.println("TitleText: " + this._title);
        }
        if (this._xlabel != null) {
            printWriter.println("XLabel: " + this._xlabel);
        }
        if (this._ylabel != null) {
            printWriter.println("YLabel: " + this._ylabel);
        }
        if (this._xRangeGiven) {
            printWriter.println("XRange: " + this._xlowgiven + ", " + this._xhighgiven);
        }
        if (this._yRangeGiven) {
            printWriter.println("YRange: " + this._ylowgiven + ", " + this._yhighgiven);
        }
        if (this._xticks != null && this._xticks.size() > 0) {
            printWriter.print("XTicks: ");
            final int n = this._xticks.size() - 1;
            for (int i = 0; i < n; ++i) {
                printWriter.print("\"" + (String)this._xticklabels.elementAt(i) + "\" " + this._xticks.elementAt(i) + ", ");
            }
            printWriter.println("\"" + (String)this._xticklabels.elementAt(n) + "\" " + this._xticks.elementAt(n));
        }
        if (this._yticks != null && this._yticks.size() > 0) {
            printWriter.print("YTicks: ");
            final int n2 = this._yticks.size() - 1;
            for (int j = 0; j < n2; ++j) {
                printWriter.print("\"" + (String)this._yticklabels.elementAt(j) + "\" " + this._yticks.elementAt(j) + ", ");
            }
            printWriter.println("\"" + (String)this._yticklabels.elementAt(n2) + "\" " + this._yticks.elementAt(n2));
        }
        if (this._xlog) {
            printWriter.println("XLog: on");
        }
        if (this._ylog) {
            printWriter.println("YLog: on");
        }
        if (!this._grid) {
            printWriter.println("Grid: off");
        }
        if (!this._usecolor) {
            printWriter.println("Color: off");
        }
    }
    
    private int _drawLegend(final Graphics graphics, final int n, final int n2) {
        graphics.setFont(this._labelfont);
        final int height = this._labelFontMetrics.getHeight();
        final Enumeration<String> elements = this._legendStrings.elements();
        final Enumeration<Integer> elements2 = this._legendDatasets.elements();
        int n3 = n2 + height;
        int n4 = 0;
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            final int intValue = elements2.nextElement();
            if (intValue >= 0) {
                if (this._usecolor) {
                    graphics.setColor(PlotBox._colors[intValue % PlotBox._colors.length]);
                }
                this._drawPoint(graphics, intValue, n - 3, n3 - 3, false);
                graphics.setColor(this._foreground);
                final int stringWidth = this._labelFontMetrics.stringWidth(s);
                if (stringWidth > n4) {
                    n4 = stringWidth;
                }
                graphics.drawString(s, n - 15 - stringWidth, n3);
                n3 += height;
            }
        }
        return 22 + n4;
    }
    
    private String _formatLogNum(final double n, final int n2) {
        final int n3 = (int)n;
        String s;
        if (n3 >= 0 && n3 < 10) {
            s = "0" + n3;
        }
        else if (n3 < 0 && n3 > -10) {
            s = "-0" + -n3;
        }
        else {
            s = Integer.toString(n3);
        }
        String s2;
        if (n >= 0.0) {
            if (n - (int)n < 0.001) {
                s2 = "1e" + s;
            }
            else {
                s2 = this._formatNum(Math.pow(10.0, n - (int)n), n2);
            }
        }
        else if (-n - (int)(-n) < 0.001) {
            s2 = "1e" + s;
        }
        else {
            s2 = this._formatNum(Math.pow(10.0, n - (int)n) * 10.0, n2);
        }
        return s2;
    }
    
    private String _formatNum(final double n, final int n2) {
        if (this._numberFormat == null) {
            this._numberFormat = NumberFormat.getInstance();
        }
        this._numberFormat.setMinimumFractionDigits(n2);
        this._numberFormat.setMaximumFractionDigits(n2);
        return this._numberFormat.format(n);
    }
    
    private Vector _gridInit(double n, final double n2, final boolean b, final Vector vector) {
        final Vector vector2 = new Vector<Double>(10);
        final double pow = Math.pow(10.0, n2);
        int n3 = 1;
        if (b) {
            if (pow <= 3.5) {
                if (pow > 2.0) {
                    n3 = 2;
                }
                else if (pow > 1.26) {
                    n3 = 5;
                }
                else if (pow > 1.125) {
                    n3 = 10;
                }
                else {
                    n3 = (int)Math.rint(1.0 / n2);
                }
            }
        }
        else if (pow > 10.0) {
            n3 = 1;
        }
        else if (pow > 3.0) {
            n3 = 2;
        }
        else if (pow > 2.0) {
            n3 = 5;
        }
        else if (pow > 1.125) {
            n3 = 10;
        }
        else {
            n3 = 100;
        }
        int n4 = 0;
        for (int i = 0; i < n3; ++i) {
            double n5 = PlotBox._LOG10SCALE * Math.log(i * 1.0 / n3 * 10.0);
            if (n5 == Double.NEGATIVE_INFINITY) {
                n5 = 0.0;
            }
            if (vector != null && n4 < vector.size()) {
                while (n4 < vector.size() && vector.elementAt(n4) < n5) {
                    ++n4;
                }
                if (n4 < vector.size()) {
                    if (Math.abs(vector.elementAt(n4) - n5) > 1.0E-5) {
                        vector2.addElement(new Double(n5));
                    }
                }
                else {
                    vector2.addElement(new Double(n5));
                }
            }
            else {
                vector2.addElement(new Double(n5));
            }
        }
        this._gridCurJuke = 0;
        if (n == -0.0) {
            n = 0.0;
        }
        this._gridBase = Math.floor(n);
        final double n6 = n - this._gridBase;
        this._gridCurJuke = -1;
        while (this._gridCurJuke + 1 < vector2.size() && n6 >= vector2.elementAt(this._gridCurJuke + 1)) {
            ++this._gridCurJuke;
        }
        return vector2;
    }
    
    private double _gridRoundUp(final Vector vector, final double n) {
        double n2;
        int n3;
        for (n2 = n - Math.floor(n), n3 = 0; n3 < vector.size() && n2 >= vector.elementAt(n3); ++n3) {}
        if (n3 >= vector.size()) {
            return n;
        }
        return Math.floor(n) + vector.elementAt(n3);
    }
    
    private double _gridStep(final Vector vector, final double n, final double n2, final boolean b) {
        if (!b) {
            return n + n2;
        }
        if (++this._gridCurJuke >= vector.size()) {
            this._gridCurJuke = 0;
            this._gridBase += Math.ceil(n2);
        }
        if (this._gridCurJuke >= vector.size()) {
            return n + n2;
        }
        return this._gridBase + vector.elementAt(this._gridCurJuke);
    }
    
    private void _measureFonts() {
        if (this._labelfont == null) {
            this._labelfont = new Font("Helvetica", 0, 12);
        }
        if (this._superscriptfont == null) {
            this._superscriptfont = new Font("Helvetica", 0, 9);
        }
        if (this._titlefont == null) {
            this._titlefont = new Font("Helvetica", 1, 14);
        }
        this._labelFontMetrics = this.getFontMetrics(this._labelfont);
        this._superscriptFontMetrics = this.getFontMetrics(this._superscriptfont);
        this._titleFontMetrics = this.getFontMetrics(this._titlefont);
    }
    
    private int _numFracDigits(double n) {
        int n2;
        for (n2 = 0; n2 <= 15 && n != Math.floor(n); n *= 10.0, ++n2) {}
        return n2;
    }
    
    private int _numIntDigits(double n) {
        int n2;
        for (n2 = 0; n2 <= 15 && (int)n != 0.0; n /= 10.0, ++n2) {}
        return n2;
    }
    
    private void _parsePairs(final String s, final boolean b) {
        int n = 0;
        boolean b2 = true;
        while (b2) {
            final int index = s.indexOf(",", n);
            String s2;
            if (index > n) {
                s2 = s.substring(n, index).trim();
            }
            else {
                s2 = s.substring(n).trim();
                b2 = false;
            }
            int n2 = 0;
            int n3;
            if (s2.startsWith("\"")) {
                n3 = s2.indexOf("\"", 1);
                n2 = 1;
            }
            else {
                n3 = s2.indexOf(" ");
            }
            if (n3 > 0) {
                final String substring = s2.substring(n2, n3);
                final String trim = s2.substring(n3 + 1).trim();
                try {
                    final double doubleValue = Double.valueOf(trim);
                    if (b) {
                        this.addXTick(substring, doubleValue);
                    }
                    else {
                        this.addYTick(substring, doubleValue);
                    }
                }
                catch (NumberFormatException ex) {}
            }
            n = index + 1;
            s.indexOf(",", n);
        }
    }
    
    private double _roundUp(double n) {
        final int n2 = (int)Math.floor(Math.log(n) * PlotBox._LOG10SCALE);
        n *= Math.pow(10.0, -n2);
        if (n > 5.0) {
            n = 10.0;
        }
        else if (n > 2.0) {
            n = 5.0;
        }
        else if (n > 1.0) {
            n = 2.0;
        }
        n *= Math.pow(10.0, n2);
        return n;
    }
    
    private void _setXRange(double n, double n2) {
        if (n > n2) {
            n = -1.0;
            n2 = 1.0;
        }
        else if (n == n2) {
            --n;
            ++n2;
        }
        this._xMin = n - (n2 - n) * 0.05;
        this._xMax = n2 + (n2 - n) * 0.05;
        this._xExp = (int)Math.floor(Math.log(Math.max(Math.abs(this._xMin), Math.abs(this._xMax))) * PlotBox._LOG10SCALE);
        if (this._xExp > 1 || this._xExp < -1) {
            final double n3 = 1.0 / Math.pow(10.0, this._xExp);
            this._xtickMin = this._xMin * n3;
            this._xtickMax = this._xMax * n3;
        }
        else {
            this._xtickMin = this._xMin;
            this._xtickMax = this._xMax;
            this._xExp = 0;
        }
    }
    
    private void _setYRange(double n, double n2) {
        if (n > n2) {
            n = -1.0;
            n2 = 1.0;
        }
        else if (n == n2) {
            n -= 0.1;
            n2 += 0.1;
        }
        this._yMin = n - (n2 - n) * 0.05;
        this._yMax = n2 + (n2 - n) * 0.05;
        this._yExp = (int)Math.floor(Math.log(Math.max(Math.abs(this._yMin), Math.abs(this._yMax))) * PlotBox._LOG10SCALE);
        if (this._yExp > 1 || this._yExp < -1) {
            final double n3 = 1.0 / Math.pow(10.0, this._yExp);
            this._ytickMin = this._yMin * n3;
            this._ytickMax = this._yMax * n3;
        }
        else {
            this._ytickMin = this._yMin;
            this._ytickMax = this._yMax;
            this._yExp = 0;
        }
    }
    
    public synchronized void _zoom(int n, int n2) {
        final Graphics graphics = this.getGraphics();
        if (this._zoomin && this._drawn) {
            if (this._zoomxn != -1 || this._zoomyn != -1) {
                final int min = Math.min(this._zoomx, this._zoomxn);
                final int max = Math.max(this._zoomx, this._zoomxn);
                final int min2 = Math.min(this._zoomy, this._zoomyn);
                final int max2 = Math.max(this._zoomy, this._zoomyn);
                graphics.setXORMode(this._background);
                graphics.drawRect(min, min2, max - min, max2 - min2);
                graphics.setPaintMode();
                if (n2 > this._lry) {
                    n2 = this._lry;
                }
                if (n2 < this._uly) {
                    n2 = this._uly;
                }
                if (n > this._lrx) {
                    n = this._lrx;
                }
                if (n < this._ulx) {
                    n = this._ulx;
                }
                if (Math.abs(this._zoomx - n) > 5 && Math.abs(this._zoomy - n2) > 5) {
                    final double n3 = this._xMin + (this._zoomx - this._ulx) / this._xscale;
                    final double n4 = this._xMin + (n - this._ulx) / this._xscale;
                    if (n3 < n4) {
                        this.setXRange(n3, n4);
                    }
                    else {
                        this.setXRange(n4, n3);
                    }
                    final double n5 = this._yMax - (this._zoomy - this._uly) / this._yscale;
                    final double n6 = this._yMax - (n2 - this._uly) / this._yscale;
                    if (n5 < n6) {
                        this.setYRange(n5, n6);
                    }
                    else {
                        this.setYRange(n6, n5);
                    }
                }
                this.repaint();
            }
        }
        else if (this._zoomout && this._drawn) {
            graphics.setXORMode(this._background);
            final int abs = Math.abs(this._zoomx - this._zoomxn);
            final int abs2 = Math.abs(this._zoomy - this._zoomyn);
            graphics.drawRect(this._zoomx - 15 - abs, this._zoomy - 15 - abs2, 30 + abs * 2, 30 + abs2 * 2);
            graphics.setPaintMode();
            final double n7 = Math.abs(this._zoomx - n) / 30.0;
            final double n8 = Math.abs(this._zoomy - n2) / 30.0;
            this.zoom(this._xMin - (this._xMax - this._xMin) * n7, this._yMin - (this._yMax - this._yMin) * n8, this._xMax + (this._xMax - this._xMin) * n7, this._yMax + (this._yMax - this._yMin) * n8);
        }
        else if (!this._drawn) {
            this.repaint();
        }
        this._drawn = false;
        final boolean b = false;
        this._zoomout = b;
        this._zoomin = b;
        final int n9 = -1;
        this._zoomy = n9;
        this._zoomx = n9;
        this._zoomyn = n9;
        this._zoomxn = n9;
    }
    
    public synchronized void _zoomBox(int n, int n2) {
        final Graphics graphics = this.getGraphics();
        if (n2 > this._lry) {
            n2 = this._lry;
        }
        if (n2 < this._uly) {
            n2 = this._uly;
        }
        if (n > this._lrx) {
            n = this._lrx;
        }
        if (n < this._ulx) {
            n = this._ulx;
        }
        if (this._zoomx != -1 || this._zoomy != -1) {
            if (!this._zoomin && !this._zoomout) {
                if (n2 < this._zoomy) {
                    this._zoomout = true;
                    graphics.drawRect(this._zoomx - 15, this._zoomy - 15, 30, 30);
                }
                else if (n2 > this._zoomy) {
                    this._zoomin = true;
                }
            }
            if (this._zoomin) {
                graphics.setXORMode(this._background);
                if ((this._zoomxn != -1 || this._zoomyn != -1) && this._drawn) {
                    final int min = Math.min(this._zoomx, this._zoomxn);
                    final int max = Math.max(this._zoomx, this._zoomxn);
                    final int min2 = Math.min(this._zoomy, this._zoomyn);
                    graphics.drawRect(min, min2, max - min, Math.max(this._zoomy, this._zoomyn) - min2);
                }
                if (n2 > this._zoomy) {
                    this._zoomxn = n;
                    this._zoomyn = n2;
                    final int min3 = Math.min(this._zoomx, this._zoomxn);
                    final int max2 = Math.max(this._zoomx, this._zoomxn);
                    final int min4 = Math.min(this._zoomy, this._zoomyn);
                    graphics.drawRect(min3, min4, max2 - min3, Math.max(this._zoomy, this._zoomyn) - min4);
                    graphics.setPaintMode();
                    this._drawn = true;
                    return;
                }
                this._drawn = false;
            }
            else if (this._zoomout) {
                graphics.setXORMode(this._background);
                if ((this._zoomxn != -1 || this._zoomyn != -1) && this._drawn) {
                    final int abs = Math.abs(this._zoomx - this._zoomxn);
                    final int abs2 = Math.abs(this._zoomy - this._zoomyn);
                    graphics.drawRect(this._zoomx - 15 - abs, this._zoomy - 15 - abs2, 30 + abs * 2, 30 + abs2 * 2);
                }
                if (n2 < this._zoomy) {
                    this._zoomxn = n;
                    this._zoomyn = n2;
                    final int abs3 = Math.abs(this._zoomx - this._zoomxn);
                    final int abs4 = Math.abs(this._zoomy - this._zoomyn);
                    graphics.drawRect(this._zoomx - 15 - abs3, this._zoomy - 15 - abs4, 30 + abs3 * 2, 30 + abs4 * 2);
                    graphics.setPaintMode();
                    this._drawn = true;
                    return;
                }
                this._drawn = false;
            }
        }
        graphics.setPaintMode();
    }
    
    public synchronized void _zoomStart(int zoomx, int zoomy) {
        if (zoomy > this._lry) {
            zoomy = this._lry;
        }
        if (zoomy < this._uly) {
            zoomy = this._uly;
        }
        if (zoomx > this._lrx) {
            zoomx = this._lrx;
        }
        if (zoomx < this._ulx) {
            zoomx = this._ulx;
        }
        this._zoomx = zoomx;
        this._zoomy = zoomy;
    }
    
    static {
        _LOG10SCALE = 1.0 / Math.log(10.0);
        PlotBox._colors = new Color[] { new Color(16711680), new Color(255), new Color(43690), new Color(0), new Color(16753920), new Color(5473931), new Color(16744272), new Color(4565791), new Color(9454125), new Color(10526880), new Color(1376020) };
    }
    
    class FillButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            PlotBox.this.fillPlot();
        }
    }
    
    public class ZoomListener implements MouseListener
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            PlotBox.this._zoomStart(mouseEvent.getX(), mouseEvent.getY());
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            PlotBox.this._zoom(mouseEvent.getX(), mouseEvent.getY());
            PlotBox.this.repaint();
        }
    }
    
    public class DragListener implements MouseMotionListener
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            PlotBox.this._zoomBox(mouseEvent.getX(), mouseEvent.getY());
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
}
