// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.io.PrintWriter;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.Cursor;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.FileInputStream;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Plot extends PlotBox
{
    protected int _currentdataset;
    protected Vector _points;
    protected int _marks;
    protected boolean _painted;
    private int _pointsPersistence;
    private int _sweepsPersistence;
    private boolean _bars;
    private double _barwidth;
    private double _baroffset;
    private boolean _connected;
    private boolean _impulses;
    private int _maxdataset;
    private boolean _reusedatasets;
    private boolean _firstinset;
    private boolean _sawfirstdataset;
    private int _radius;
    private int _diameter;
    private boolean _pxgraphBlankLineMode;
    private static final int _NATIVE_ENDIAN = 0;
    private static final int _BIG_ENDIAN = 1;
    private static final int _LITTLE_ENDIAN = 2;
    private int _endian;
    private Vector _prevx;
    private Vector _prevy;
    private static final int _ERRORBAR_LEG_LENGTH = 5;
    private static final int _MAX_MARKS = 10;
    private boolean _xyInvalid;
    private String _filename;
    private boolean _showing;
    private Vector _formats;
    
    public Plot() {
        this._currentdataset = -1;
        this._points = new Vector();
        this._painted = false;
        this._pointsPersistence = 0;
        this._sweepsPersistence = 0;
        this._bars = false;
        this._barwidth = 0.5;
        this._baroffset = 0.05;
        this._connected = true;
        this._impulses = false;
        this._maxdataset = -1;
        this._reusedatasets = false;
        this._firstinset = true;
        this._sawfirstdataset = false;
        this._radius = 3;
        this._diameter = 6;
        this._pxgraphBlankLineMode = true;
        this._endian = 0;
        this._prevx = new Vector();
        this._prevy = new Vector();
        this._xyInvalid = false;
        this._filename = null;
        this._showing = false;
        this._formats = new Vector();
    }
    
    public void addLegend(final int n, final String s) {
        this._checkDatasetIndex(n);
        super.addLegend(n, s);
    }
    
    public synchronized void addPoint(final int n, double n2, double n3, final boolean b) {
        if (super._xlog) {
            if (n2 <= 0.0) {
                System.err.println("Can't plot non-positive X values when the logarithmic X axis value is specified: " + n2);
                return;
            }
            n2 = Math.log(n2) * PlotBox._LOG10SCALE;
        }
        if (super._ylog) {
            if (n3 <= 0.0) {
                System.err.println("Can't plot non-positive Y values when the logarithmic Y axis value is specified: " + n3);
                return;
            }
            n3 = Math.log(n3) * PlotBox._LOG10SCALE;
        }
        this._addPoint(n, n2, n3, 0.0, 0.0, b, false);
    }
    
    public synchronized void addPointWithErrorBars(final int n, double n2, double n3, double n4, double n5, final boolean b) {
        if (super._xlog) {
            if (n2 <= 0.0) {
                System.err.println("Can't plot non-positive X values when the logarithmic X axis value is specified: " + n2);
                return;
            }
            n2 = Math.log(n2) * PlotBox._LOG10SCALE;
        }
        if (super._ylog) {
            if (n3 <= 0.0 || n4 <= 0.0 || n5 <= 0.0) {
                System.err.println("Can't plot non-positive Y values when the logarithmic Y axis value is specified: " + n3);
                return;
            }
            n3 = Math.log(n3) * PlotBox._LOG10SCALE;
            n4 = Math.log(n4) * PlotBox._LOG10SCALE;
            n5 = Math.log(n5) * PlotBox._LOG10SCALE;
        }
        this._addPoint(n, n2, n3, n4, n5, b, true);
    }
    
    public synchronized void clear(final boolean b) {
        super.clear(b);
        this._currentdataset = -1;
        this._points.size();
        this._points = new Vector();
        this._prevx = new Vector();
        this._prevy = new Vector();
        this._painted = false;
        this._maxdataset = -1;
        this._firstinset = true;
        this._sawfirstdataset = false;
        this._pxgraphBlankLineMode = true;
        this._endian = 0;
        this._xyInvalid = false;
        this._filename = null;
        this._showing = false;
        if (b) {
            this._formats = new Vector();
            this._marks = 0;
            this._pointsPersistence = 0;
            this._sweepsPersistence = 0;
            this._bars = false;
            this._barwidth = 0.5;
            this._baroffset = 0.05;
            this._connected = true;
            this._impulses = false;
            this._reusedatasets = false;
        }
    }
    
    public synchronized void erasePoint(final int n, final int n2) {
        this._checkDatasetIndex(n);
        if (this.isShowing()) {
            this._erasePoint(this.getGraphics(), n, n2);
        }
        final Vector<PlotPoint> vector = this._points.elementAt(n);
        if (vector != null) {
            final PlotPoint plotPoint = vector.elementAt(n2);
            if (plotPoint != null) {
                if (plotPoint.x == super._xBottom || plotPoint.x == super._xTop || plotPoint.y == super._yBottom || plotPoint.y == super._yTop) {
                    this._xyInvalid = true;
                }
                vector.removeElementAt(n2);
            }
        }
    }
    
    public synchronized void fillPlot() {
        if (this._xyInvalid) {
            super._xBottom = Double.MAX_VALUE;
            super._xTop = -Double.MIN_VALUE;
            super._yBottom = Double.MAX_VALUE;
            super._yTop = -Double.MIN_VALUE;
            for (int i = 0; i < this._points.size(); ++i) {
                final Vector<PlotPoint> vector = this._points.elementAt(i);
                for (int j = 0; j < vector.size(); ++j) {
                    final PlotPoint plotPoint = vector.elementAt(j);
                    if (plotPoint.x < super._xBottom) {
                        super._xBottom = plotPoint.x;
                    }
                    if (plotPoint.x > super._xTop) {
                        super._xTop = plotPoint.x;
                    }
                    if (plotPoint.y < super._yBottom) {
                        super._yBottom = plotPoint.y;
                    }
                    if (plotPoint.y > super._yTop) {
                        super._yTop = plotPoint.y;
                    }
                }
            }
        }
        this._xyInvalid = false;
        if (this._bars) {
            if (super._yBottom > 0.0) {
                super._yBottom = 0.0;
            }
            if (super._yTop < 0.0) {
                super._yTop = 0.0;
            }
        }
        super.fillPlot();
    }
    
    public String getCmdLineFilename() {
        return this._filename;
    }
    
    public int getMaxDataSets() {
        return Integer.MAX_VALUE;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 50, size.height);
        graphics.fillRect(0, size.height - 50, size.width, size.height);
        graphics.fillRect(0, 0, size.width, 50);
        this._drawPlot(graphics, true);
    }
    
    public int parseArgs(final String[] array) throws CmdLineArgException, FileNotFoundException, IOException {
        int n = 0;
        boolean b = false;
        int n2 = 0;
        int marks = 0;
        boolean b2 = false;
        final String[] array2 = { "-bd", "-brb", "-bw", "-gw", "-lw", "-zg", "-zw" };
        while (n < array.length && (array[n].startsWith("-") || array[n].startsWith("="))) {
            final String s = array[n++];
            if (s.startsWith("-")) {
                boolean b3 = false;
                for (int i = 0; i < array2.length; ++i) {
                    if (s.equals(array2[i])) {
                        System.err.println("pxgraph: " + s + " is not yet supported");
                        ++n;
                        b3 = true;
                    }
                }
                if (b3) {
                    continue;
                }
                if (s.equals("-bb")) {
                    continue;
                }
                if (s.equals("-bg")) {
                    this.setBackground(PlotBox.getColorByName(array[n++]));
                    continue;
                }
                if (s.equals("-brw")) {
                    if (s.indexOf(",") == -1) {
                        if (!this._parseLine("Bars: " + array[n++] + ",0")) {
                            throw new CmdLineArgException("Failed to parse `" + s + "'");
                        }
                        continue;
                    }
                    else {
                        if (!this._parseLine("Bars: " + array[n++])) {
                            throw new CmdLineArgException("Failed to parse `" + s + "'");
                        }
                        continue;
                    }
                }
                else {
                    if (s.equals("-lf")) {
                        this.setLabelFont(array[n++]);
                        continue;
                    }
                    if (s.equals("-lx")) {
                        if (!this._parseLine("XRange: " + array[n++])) {
                            throw new CmdLineArgException("Failed to parse `" + s + "'");
                        }
                        continue;
                    }
                    else if (s.equals("-ly")) {
                        if (!this._parseLine("YRange: " + array[n++])) {
                            throw new CmdLineArgException("Failed to parse `" + s + "'");
                        }
                        continue;
                    }
                    else {
                        if (s.equals("-t")) {
                            this.setTitle(array[n++]);
                            continue;
                        }
                        if (s.equals("-tf")) {
                            this.setTitleFont(array[n++]);
                            continue;
                        }
                        if (s.equals("-x")) {
                            this.setXLabel(array[n++]);
                            continue;
                        }
                        if (s.equals("-y")) {
                            this.setYLabel(array[n++]);
                            continue;
                        }
                        if (s.equals("-bar")) {
                            b = true;
                            if (n2 != 0) {
                                this.setImpulses(true);
                            }
                            else {
                                this.setBars(true);
                                marks = this._marks;
                                this.setMarksStyle("none");
                            }
                            this.setConnected(false);
                            continue;
                        }
                        if (s.equals("-binary")) {
                            b2 = true;
                            this._endian = 0;
                            continue;
                        }
                        if (s.equals("-bigendian")) {
                            b2 = true;
                            this._endian = 1;
                            continue;
                        }
                        if (s.equals("-littleendian")) {
                            b2 = true;
                            this._endian = 2;
                            continue;
                        }
                        if (s.equals("-db")) {
                            continue;
                        }
                        if (s.equals("-debug")) {
                            continue;
                        }
                        if (s.equals("-fg")) {
                            this.setForeground(PlotBox.getColorByName(array[n++]));
                            continue;
                        }
                        if (s.equals("-help")) {
                            continue;
                        }
                        if (s.equals("-impulses")) {
                            this.setImpulses(true);
                            this.setConnected(false);
                            continue;
                        }
                        if (s.equals("-lnx")) {
                            this.setXLog(true);
                            continue;
                        }
                        if (s.equals("-lny")) {
                            this.setYLog(true);
                            continue;
                        }
                        if (s.equals("-m")) {
                            this.setMarksStyle("various");
                            continue;
                        }
                        if (s.equals("-M")) {
                            this.setMarksStyle("various");
                            continue;
                        }
                        if (s.equals("-nl")) {
                            n2 = 1;
                            if (b) {
                                this._marks = marks;
                                this.setBars(false);
                                this.setImpulses(true);
                            }
                            this.setConnected(false);
                            continue;
                        }
                        if (s.equals("-o")) {
                            ++n;
                            continue;
                        }
                        if (s.equals("-p")) {
                            this.setMarksStyle("points");
                            continue;
                        }
                        if (s.equals("-P")) {
                            this.setMarksStyle("dots");
                            continue;
                        }
                        if (s.equals("-print")) {
                            continue;
                        }
                        if (s.equals("-rv")) {
                            this.setBackground(PlotBox.getColorByName("black"));
                            this.setForeground(PlotBox.getColorByName("white"));
                            continue;
                        }
                        if (s.equals("-test")) {
                            continue;
                        }
                        if (s.equals("-tk")) {
                            this.setGrid(false);
                            continue;
                        }
                        if (s.equals("-v")) {
                            continue;
                        }
                        if (s.equals("-version")) {
                            continue;
                        }
                        if (s.length() > 1 && s.charAt(0) == '-') {
                            try {
                                final int intValue = new Integer(s.substring(1));
                                if (intValue >= 0) {
                                    this.addLegend(intValue, array[n++]);
                                    continue;
                                }
                            }
                            catch (NumberFormatException ex) {}
                        }
                    }
                }
            }
            else if (s.startsWith("=")) {
                super._width = Integer.valueOf(s.substring(1, s.indexOf(120)));
                final int index = s.indexOf(43);
                final int index2 = s.indexOf(45);
                if (index != -1 || index2 != -1) {
                    if (index != -1 && index2 != -1) {
                        int n3;
                        if (index < (n3 = index2)) {
                            n3 = index;
                        }
                        super._height = Integer.valueOf(s.substring(s.indexOf(120) + 1, n3));
                        continue;
                    }
                    if (index != -1) {
                        super._height = Integer.valueOf(s.substring(s.indexOf(120) + 1, index));
                        continue;
                    }
                    super._height = Integer.valueOf(s.substring(s.indexOf(120) + 1, index2));
                    continue;
                }
                else {
                    if (s.length() > s.indexOf(120)) {
                        super._height = Integer.valueOf(s.substring(s.indexOf(120) + 1, s.length()));
                        continue;
                    }
                    continue;
                }
            }
            throw new CmdLineArgException("Failed to parse `" + s + "'");
        }
        final int n4 = n++;
        this.setSize(super._width, super._height);
        for (int j = n4; j < array.length; ++j) {
            InputStream openStream;
            try {
                openStream = new URL(super._documentBase, array[j]).openStream();
            }
            catch (MalformedURLException ex2) {
                openStream = new FileInputStream(array[j]);
                this._filename = array[j];
            }
            if (b2) {
                this.readPxgraph(openStream);
            }
            else {
                this.read(openStream);
            }
        }
        return n4;
    }
    
    public void parseFile(final String s, final URL url) {
        this._firstinset = true;
        this._sawfirstdataset = false;
        super.parseFile(s, url);
    }
    
    public int parsePxgraphargs(final String s) throws CmdLineArgException, FileNotFoundException, IOException {
        final Vector vector = new Vector<String>();
        int n = 0;
        final StringReader stringReader = new StringReader(s);
        Label_0443: {
            try {
                final StreamTokenizer streamTokenizer = new StreamTokenizer(stringReader);
                streamTokenizer.resetSyntax();
                streamTokenizer.whitespaceChars(0, 32);
                streamTokenizer.wordChars(40, 126);
                streamTokenizer.quoteChar(34);
                streamTokenizer.quoteChar(39);
                String string = null;
                while (true) {
                    final int nextToken = streamTokenizer.nextToken();
                    switch (streamTokenizer.ttype) {
                        case -1: {
                            break Label_0443;
                        }
                        case -3: {
                            if (n != 0) {
                                n = 0;
                                if (string == null) {
                                    vector.addElement(new String("-" + streamTokenizer.sval));
                                }
                                else {
                                    vector.addElement(new String("-" + string + streamTokenizer.sval));
                                }
                            }
                            else if (string == null) {
                                vector.addElement(new String(streamTokenizer.sval));
                            }
                            else {
                                vector.addElement(new String(string + streamTokenizer.sval));
                            }
                            string = null;
                            continue;
                        }
                        case 45: {
                            n = 1;
                            continue;
                        }
                        case 35:
                        case 36:
                        case 37:
                        case 38: {
                            string = vector.lastElement() + (char)nextToken;
                            vector.removeElementAt(vector.size() - 1);
                            continue;
                        }
                        case 34:
                        case 39: {
                            vector.addElement(new String(streamTokenizer.sval));
                            continue;
                        }
                        default: {
                            throw new IOException("Failed to parse: '" + (char)nextToken + "' in `" + s + "'");
                        }
                    }
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return this.parseArgs(array);
    }
    
    public void read(final InputStream inputStream) throws IOException {
        super.read(inputStream);
        this._firstinset = true;
        this._sawfirstdataset = false;
    }
    
    public void readPxgraph(final InputStream inputStream) throws IOException {
        final Cursor cursor = this.getCursor();
        this.setCursor(new Cursor(3));
        try {
            this._firstinset = true;
            this._sawfirstdataset = false;
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
            float n = 0.0f;
            float n2 = 0.0f;
            float n3 = 0.0f;
            boolean b = false;
            boolean addLegendIfNecessary = false;
            final byte[] array = new byte[4];
            if (this._connected) {
                addLegendIfNecessary = true;
            }
            switch (this._endian) {
                case 0: {
                    try {
                        if (System.getProperty("os.arch").equals("x86")) {
                            b = true;
                        }
                    }
                    catch (SecurityException ex) {}
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    b = true;
                    break;
                }
                default: {
                    throw new IOException("Internal Error: Don't know about '" + this._endian + "' style of endian");
                }
            }
            try {
                byte b2 = dataInputStream.readByte();
                if (b2 != 100) {
                    final float intBitsToFloat = Float.intBitsToFloat((((b2 << 8) + dataInputStream.readByte() << 8) + dataInputStream.readByte() << 8) + dataInputStream.readByte());
                    final float float1 = dataInputStream.readFloat();
                    boolean b3 = this._addLegendIfNecessary(addLegendIfNecessary);
                    this.addPoint(this._currentdataset, intBitsToFloat, float1, b3);
                    if (this._connected) {
                        b3 = true;
                    }
                    while (true) {
                        final float float2 = dataInputStream.readFloat();
                        final float float3 = dataInputStream.readFloat();
                        b3 = this._addLegendIfNecessary(b3);
                        this.addPoint(this._currentdataset, float2, float3, b3);
                        if (this._connected) {
                            b3 = true;
                        }
                    }
                }
                else {
                    while (true) {
                        switch (b2) {
                            case 100: {
                                if (b) {
                                    dataInputStream.readFully(array);
                                    n = Float.intBitsToFloat((array[3] & 0xFF) << 24 | (array[2] & 0xFF) << 16 | (array[1] & 0xFF) << 8 | (array[0] & 0xFF));
                                    dataInputStream.readFully(array);
                                    n2 = Float.intBitsToFloat((array[3] & 0xFF) << 24 | (array[2] & 0xFF) << 16 | (array[1] & 0xFF) << 8 | (array[0] & 0xFF));
                                }
                                else {
                                    n = dataInputStream.readFloat();
                                    n2 = dataInputStream.readFloat();
                                }
                                ++n3;
                                addLegendIfNecessary = this._addLegendIfNecessary(addLegendIfNecessary);
                                this.addPoint(this._currentdataset, n, n2, addLegendIfNecessary);
                                if (this._connected) {
                                    addLegendIfNecessary = true;
                                    break;
                                }
                                break;
                            }
                            case 101: {
                                addLegendIfNecessary = false;
                                break;
                            }
                            case 110: {
                                final StringBuffer sb = new StringBuffer();
                                this._firstinset = true;
                                this._sawfirstdataset = true;
                                ++this._currentdataset;
                                if (this._currentdataset >= 10) {
                                    this._currentdataset = 0;
                                }
                                while (b2 != 10) {
                                    sb.append(dataInputStream.readChar());
                                }
                                this.addLegend(this._currentdataset, sb.toString());
                                this.setConnected(true);
                                break;
                            }
                            case 109: {
                                addLegendIfNecessary = false;
                                break;
                            }
                            default: {
                                throw new IOException("Don't understand `" + (char)b2 + "' character " + "(decimal value = " + b2 + ") in binary file.  Last point was (" + n + "," + n2 + ").\nProcessed " + n3 + " points successfully");
                            }
                        }
                        b2 = dataInputStream.readByte();
                    }
                }
            }
            catch (EOFException ex2) {}
        }
        finally {
            this.setCursor(cursor);
        }
    }
    
    public void setBars(final boolean bars) {
        this._bars = bars;
    }
    
    public void setBars(final double barwidth, final double baroffset) {
        this._barwidth = barwidth;
        this._baroffset = baroffset;
        this._bars = true;
    }
    
    public void setConnected(final boolean connected) {
        this._connected = connected;
    }
    
    public void setImpulses(final boolean impulses) {
        this._impulses = impulses;
    }
    
    public void setImpulses(final boolean impulses, final int n) {
        this._checkDatasetIndex(n);
        final Format format = this._formats.elementAt(n);
        format.impulses = impulses;
        format.impulsesUseDefault = false;
    }
    
    public void setMarksStyle(final String s) {
        if (s.equalsIgnoreCase("none")) {
            this._marks = 0;
        }
        else if (s.equalsIgnoreCase("points")) {
            this._marks = 1;
        }
        else if (s.equalsIgnoreCase("dots")) {
            this._marks = 2;
        }
        else if (s.equalsIgnoreCase("various")) {
            this._marks = 3;
        }
    }
    
    public void setMarksStyle(final String s, final int n) {
        this._checkDatasetIndex(n);
        final Format format = this._formats.elementAt(n);
        if (s.equalsIgnoreCase("none")) {
            format.marks = 0;
        }
        else if (s.equalsIgnoreCase("points")) {
            format.marks = 1;
        }
        else if (s.equalsIgnoreCase("dots")) {
            format.marks = 2;
        }
        else if (s.equalsIgnoreCase("various")) {
            format.marks = 3;
        }
        format.marksUseDefault = false;
    }
    
    public void setNumSets(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Number of data sets (" + n + ") must be greater than 0.");
        }
        this._currentdataset = -1;
        this._points.removeAllElements();
        this._formats.removeAllElements();
        this._prevx.removeAllElements();
        this._prevy.removeAllElements();
        for (int i = 0; i < n; ++i) {
            this._points.addElement(new Vector<Vector>());
            this._formats.addElement(new Format());
            this._prevx.addElement(new Long(0L));
            this._prevy.addElement(new Long(0L));
        }
    }
    
    public void setPointsPersistence(final int pointsPersistence) {
        this._pointsPersistence = pointsPersistence;
    }
    
    public void setSweepsPersistence(final int sweepsPersistence) {
        this._sweepsPersistence = sweepsPersistence;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    protected void _checkDatasetIndex(final int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Plot._checkDatasetIndex: Cannot give a negative number for the data set index.");
        }
        while (i >= this._points.size()) {
            this._points.addElement(new Vector<Vector>());
            this._formats.addElement(new Format());
            this._prevx.addElement(new Long(0L));
            this._prevy.addElement(new Long(0L));
        }
    }
    
    protected void _drawBar(final Graphics graphics, final int n, final long n2, long n3, final boolean b) {
        if (b) {
            if (n3 < super._uly) {
                n3 = super._uly;
            }
            if (n3 > super._lry) {
                n3 = super._lry;
            }
        }
        if (n3 <= super._lry && n2 <= super._lrx && n2 >= super._ulx) {
            int ulx = (int)(n2 - this._barwidth * super._xscale / 2.0 + n * this._baroffset * super._xscale);
            int lrx = (int)(ulx + this._barwidth * super._xscale);
            if (ulx < super._ulx) {
                ulx = super._ulx;
            }
            if (lrx > super._lrx) {
                lrx = super._lrx;
            }
            if (ulx >= lrx) {
                lrx = ulx + 1;
            }
            long n4 = super._lry - (long)((0.0 - super._yMin) * super._yscale);
            if (super._lry < n4) {
                n4 = super._lry;
            }
            if (super._uly > n4) {
                n4 = super._uly;
            }
            if (super._yMin >= 0.0 || n3 <= n4) {
                graphics.fillRect(ulx, (int)n3, lrx - ulx, (int)(n4 - n3));
            }
            else {
                graphics.fillRect(ulx, (int)n4, lrx - ulx, (int)(n3 - n4));
            }
        }
    }
    
    protected void _drawErrorBar(final Graphics graphics, final int n, final long n2, final long n3, final long n4, final boolean b) {
        this._drawLine(graphics, n, n2 - 5L, n4, n2 + 5L, n4, b);
        this._drawLine(graphics, n, n2, n3, n2, n4, b);
        this._drawLine(graphics, n, n2 - 5L, n3, n2 + 5L, n3, b);
    }
    
    protected void _drawImpulse(final Graphics graphics, final long n, long n2, final boolean b) {
        if (b) {
            if (n2 < super._uly) {
                n2 = super._uly;
            }
            if (n2 > super._lry) {
                n2 = super._lry;
            }
        }
        if (n2 <= super._lry && n <= super._lrx && n >= super._ulx) {
            double n3 = super._lry - (long)((0.0 - super._yMin) * super._yscale);
            if (super._lry < n3) {
                n3 = super._lry;
            }
            if (super._uly > n3) {
                n3 = super._uly;
            }
            graphics.drawLine((int)n, (int)n2, (int)n, (int)n3);
        }
    }
    
    protected void _drawLine(final Graphics graphics, final int n, long n2, long n3, long n4, long n5, final boolean b) {
        if (b) {
            if ((n4 > super._ulx || n2 > super._ulx) && (n4 < super._lrx || n2 < super._lrx) && (n5 > super._uly || n3 > super._uly) && (n5 < super._lry || n3 < super._lry)) {
                if (n2 != n4) {
                    if (n4 < super._ulx) {
                        n5 = (int)(n5 + (n3 - n5) * (super._ulx - n4) / (n2 - n4));
                        n4 = super._ulx;
                    }
                    else if (n4 > super._lrx) {
                        n5 = (int)(n5 + (n3 - n5) * (super._lrx - n4) / (n2 - n4));
                        n4 = super._lrx;
                    }
                }
                if (n3 != n5) {
                    if (n5 < super._uly) {
                        n4 = (int)(n4 + (n2 - n4) * (super._uly - n5) / (n3 - n5));
                        n5 = super._uly;
                    }
                    else if (n5 > super._lry) {
                        n4 = (int)(n4 + (n2 - n4) * (super._lry - n5) / (n3 - n5));
                        n5 = super._lry;
                    }
                }
                if (n2 != n4) {
                    if (n2 < super._ulx) {
                        n3 = (int)(n3 + (n5 - n3) * (super._ulx - n2) / (n4 - n2));
                        n2 = super._ulx;
                    }
                    else if (n2 > super._lrx) {
                        n3 = (int)(n3 + (n5 - n3) * (super._lrx - n2) / (n4 - n2));
                        n2 = super._lrx;
                    }
                }
                if (n3 != n5) {
                    if (n3 < super._uly) {
                        n2 = (int)(n2 + (n4 - n2) * (super._uly - n3) / (n5 - n3));
                        n3 = super._uly;
                    }
                    else if (n3 > super._lry) {
                        n2 = (int)(n2 + (n4 - n2) * (super._lry - n3) / (n5 - n3));
                        n3 = super._lry;
                    }
                }
            }
            if (n4 >= super._ulx && n4 <= super._lrx && n5 >= super._uly && n5 <= super._lry && n2 >= super._ulx && n2 <= super._lrx && n3 >= super._uly && n3 <= super._lry) {
                graphics.drawLine((int)n2, (int)n3, (int)n4, (int)n5);
            }
        }
        else {
            graphics.drawLine((int)n2, (int)n3, (int)n4, (int)n5);
        }
    }
    
    protected synchronized void _drawPlot(final Graphics graphics, final boolean b) {
        super._drawPlot(graphics, b);
        this._showing = true;
        for (int i = this._points.size() - 1; i >= 0; --i) {
            final Vector vector = this._points.elementAt(i);
            for (int j = 0; j < vector.size(); ++j) {
                this._drawPlotPoint(graphics, i, j);
            }
        }
        this._painted = true;
        this.notifyAll();
    }
    
    protected void _drawPoint(final Graphics graphics, final int n, final long n2, final long n3, final boolean b) {
        if (!b || (n3 <= super._lry && n3 >= super._uly && n2 <= super._lrx && n2 >= super._ulx)) {
            final int n4 = (int)n2;
            final int n5 = (int)n3;
            final Format format = this._formats.elementAt(n);
            int n6 = this._marks;
            if (!format.marksUseDefault) {
                n6 = format.marks;
            }
            switch (n6) {
                case 0: {
                    graphics.fillRect(n4 - 6, n5 - 6, 6, 6);
                    break;
                }
                case 1: {
                    graphics.fillOval(n4 - 1, n5 - 1, 3, 3);
                    break;
                }
                case 2: {
                    graphics.fillOval(n4 - this._radius, n5 - this._radius, this._diameter, this._diameter);
                    break;
                }
                case 3: {
                    switch (n % 10) {
                        case 0: {
                            graphics.fillOval(n4 - this._radius, n5 - this._radius, this._diameter, this._diameter);
                            break;
                        }
                        case 1: {
                            graphics.drawLine(n4 - this._radius, n5 - this._radius, n4 + this._radius, n5 + this._radius);
                            graphics.drawLine(n4 + this._radius, n5 - this._radius, n4 - this._radius, n5 + this._radius);
                            break;
                        }
                        case 2: {
                            graphics.drawRect(n4 - this._radius, n5 - this._radius, this._diameter, this._diameter);
                            break;
                        }
                        case 3: {
                            final int[] array = new int[4];
                            final int[] array2 = new int[4];
                            array[0] = n4;
                            array2[0] = n5 - this._radius;
                            array[1] = n4 + this._radius;
                            array2[1] = n5 + this._radius;
                            array[2] = n4 - this._radius;
                            array2[2] = n5 + this._radius;
                            array[3] = n4;
                            array2[3] = n5 - this._radius;
                            graphics.fillPolygon(array, array2, 4);
                            break;
                        }
                        case 4: {
                            final int[] array3 = new int[5];
                            final int[] array4 = new int[5];
                            array3[0] = n4;
                            array4[0] = n5 - this._radius;
                            array3[1] = n4 + this._radius;
                            array4[1] = n5;
                            array3[2] = n4;
                            array4[2] = n5 + this._radius;
                            array3[3] = n4 - this._radius;
                            array4[3] = n5;
                            array3[4] = n4;
                            array4[4] = n5 - this._radius;
                            graphics.drawPolygon(array3, array4, 5);
                            break;
                        }
                        case 5: {
                            graphics.drawOval(n4 - this._radius, n5 - this._radius, this._diameter, this._diameter);
                            break;
                        }
                        case 6: {
                            graphics.drawLine(n4, n5 - this._radius, n4, n5 + this._radius);
                            graphics.drawLine(n4 - this._radius, n5, n4 + this._radius, n5);
                            break;
                        }
                        case 7: {
                            graphics.fillRect(n4 - this._radius, n5 - this._radius, this._diameter, this._diameter);
                            break;
                        }
                        case 8: {
                            final int[] array5 = new int[4];
                            final int[] array6 = new int[4];
                            array5[0] = n4;
                            array6[0] = n5 - this._radius;
                            array5[1] = n4 + this._radius;
                            array6[1] = n5 + this._radius;
                            array5[2] = n4 - this._radius;
                            array6[2] = n5 + this._radius;
                            array5[3] = n4;
                            array6[3] = n5 - this._radius;
                            graphics.drawPolygon(array5, array6, 4);
                            break;
                        }
                        case 9: {
                            final int[] array7 = new int[5];
                            final int[] array8 = new int[5];
                            array7[0] = n4;
                            array8[0] = n5 - this._radius;
                            array7[1] = n4 + this._radius;
                            array8[1] = n5;
                            array7[2] = n4;
                            array8[2] = n5 + this._radius;
                            array7[3] = n4 - this._radius;
                            array8[3] = n5;
                            array7[4] = n4;
                            array8[4] = n5 - this._radius;
                            graphics.fillPolygon(array7, array8, 5);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    protected boolean _parseLine(String s) {
        boolean b = false;
        if (this._connected) {
            b = true;
        }
        if (super._parseLine(s)) {
            this._pxgraphBlankLineMode = false;
            return true;
        }
        final String s2 = new String(s.toLowerCase());
        if (s2.startsWith("marks:")) {
            final String trim = s.substring(6).trim();
            if (this._sawfirstdataset) {
                this.setMarksStyle(trim, this._currentdataset);
            }
            else {
                this.setMarksStyle(trim);
            }
            this._pxgraphBlankLineMode = false;
            return true;
        }
        if (s2.startsWith("numsets:")) {
            this._pxgraphBlankLineMode = false;
            return true;
        }
        if (s2.startsWith("reusedatasets:")) {
            if (s2.indexOf("off", 16) >= 0) {
                this._reusedatasets = false;
            }
            else {
                this._reusedatasets = true;
            }
            return true;
        }
        if (s2.startsWith("dataset:") || (this._pxgraphBlankLineMode && s2.length() == 0)) {
            if (this._reusedatasets && s2.length() > 0) {
                final String trim2 = s.substring(8).trim();
                this._currentdataset = -1;
                for (int i = 0; i <= this._maxdataset; ++i) {
                    if (this.getLegend(i).compareTo(trim2) == 0) {
                        this._currentdataset = i;
                    }
                }
                if (this._currentdataset != -1) {
                    return true;
                }
                this._currentdataset = this._maxdataset;
            }
            this._firstinset = true;
            this._sawfirstdataset = true;
            ++this._currentdataset;
            if (s2.length() > 0) {
                final String trim3 = s.substring(8).trim();
                if (trim3 != null && trim3.length() > 0) {
                    this.addLegend(this._currentdataset, trim3);
                }
                this._pxgraphBlankLineMode = false;
            }
            this._maxdataset = this._currentdataset;
            return true;
        }
        if (s2.startsWith("lines:")) {
            if (s2.indexOf("off", 6) >= 0) {
                this.setConnected(false);
            }
            else {
                this.setConnected(true);
            }
            this._pxgraphBlankLineMode = false;
            return true;
        }
        if (s2.startsWith("impulses:")) {
            if (this._sawfirstdataset) {
                if (s2.indexOf("off", 9) >= 0) {
                    this.setImpulses(false, this._currentdataset);
                }
                else {
                    this.setImpulses(true, this._currentdataset);
                }
            }
            else if (s2.indexOf("off", 9) >= 0) {
                this.setImpulses(false);
            }
            else {
                this.setImpulses(true);
            }
            this._pxgraphBlankLineMode = false;
            return true;
        }
        if (s2.startsWith("bars:")) {
            if (s2.indexOf("off", 5) >= 0) {
                this.setBars(false);
            }
            else {
                this.setBars(true);
                final int index = s.indexOf(",", 5);
                String trim4 = null;
                String s3;
                if (index > 0) {
                    s3 = s.substring(5, index).trim();
                    trim4 = s.substring(index + 1).trim();
                }
                else {
                    s3 = s.substring(5).trim();
                }
                try {
                    final Double n = new Double(s3);
                    double n2 = this._baroffset;
                    if (trim4 != null) {
                        n2 = new Double(trim4);
                    }
                    this.setBars(n, n2);
                }
                catch (NumberFormatException ex) {}
            }
            this._pxgraphBlankLineMode = false;
            return true;
        }
        if (s.startsWith("move:")) {
            b = false;
            s = s.substring(5, s.length()).trim();
        }
        else if (s.startsWith("move")) {
            b = false;
            s = s.substring(4, s.length()).trim();
        }
        else if (s.startsWith("draw:")) {
            s = s.substring(5, s.length()).trim();
        }
        else if (s.startsWith("draw")) {
            s = s.substring(4, s.length()).trim();
        }
        s = s.trim();
        int n3 = s.indexOf(",");
        if (n3 == -1) {
            n3 = s.indexOf(" ");
        }
        if (n3 == -1) {
            n3 = s.indexOf(" ");
        }
        if (n3 > 0) {
            final String trim5 = s.substring(0, n3).trim();
            String s4 = s.substring(n3 + 1).trim();
            int n4 = s4.indexOf(",");
            if (n4 == -1) {
                n4 = s4.indexOf(" ");
            }
            if (n4 == -1) {
                n4 = s4.indexOf(" ");
            }
            if (n4 > 0) {
                s = s4.substring(n4 + 1).trim();
                s4 = s4.substring(0, n4).trim();
            }
            try {
                final Double n5 = new Double(trim5);
                final Double n6 = new Double(s4);
                if (n4 <= 0) {
                    this.addPoint(this._currentdataset, n5, n6, this._addLegendIfNecessary(b));
                    return true;
                }
                int n7 = s.indexOf(",");
                if (n7 == -1) {
                    n7 = s.indexOf(" ");
                }
                if (n7 == -1) {
                    s.indexOf(" ");
                }
                if (n7 > 0) {
                    this.addPointWithErrorBars(this._currentdataset, n5, n6, new Double(s.substring(0, n7).trim()), new Double(s.substring(n7 + 1).trim()), this._addLegendIfNecessary(b));
                    return true;
                }
                this.addPoint(this._currentdataset, n5, n6, this._addLegendIfNecessary(b));
                return true;
            }
            catch (NumberFormatException ex2) {}
        }
        return false;
    }
    
    protected void _write(final PrintWriter printWriter) {
        super._write(printWriter);
        if (this._reusedatasets) {
            printWriter.println("ReuseDatasets: on");
        }
        if (!this._connected) {
            printWriter.println("Lines: off");
        }
        if (this._bars) {
            printWriter.println("Bars: " + this._barwidth + ", " + this._baroffset);
        }
        if (this._impulses) {
            printWriter.println("Impulses: on");
        }
        switch (this._marks) {
            case 1: {
                printWriter.println("Marks: points");
            }
            case 2: {
                printWriter.println("Marks: dots");
            }
            case 3: {
                printWriter.println("Marks: various");
                break;
            }
        }
        for (int i = 0; i < this._points.size(); ++i) {
            if (this.getLegend(i) != null) {
                printWriter.println("DataSet: " + this.getLegend(i));
            }
            else {
                printWriter.println("DataSet:");
            }
            final Format format = this._formats.elementAt(i);
            if (!format.impulsesUseDefault) {
                if (format.impulses) {
                    printWriter.println("Impulses: on");
                }
                else {
                    printWriter.println("Impulses: off");
                }
            }
            if (!format.marksUseDefault) {
                switch (format.marks) {
                    case 0: {
                        printWriter.println("Marks: none");
                    }
                    case 1: {
                        printWriter.println("Marks: points");
                    }
                    case 2: {
                        printWriter.println("Marks: dots");
                    }
                    case 3: {
                        printWriter.println("Marks: various");
                        break;
                    }
                }
            }
            final Vector<PlotPoint> vector = this._points.elementAt(i);
            for (int j = 0; j < vector.size(); ++j) {
                final PlotPoint plotPoint = vector.elementAt(j);
                if (!plotPoint.connected) {
                    printWriter.print("move: ");
                }
                if (plotPoint.errorBar) {
                    printWriter.println(plotPoint.x + ", " + plotPoint.y + ", " + plotPoint.yLowEB + ", " + plotPoint.yHighEB);
                }
                else {
                    printWriter.println(plotPoint.x + ", " + plotPoint.y);
                }
            }
        }
    }
    
    private boolean _addLegendIfNecessary(boolean b) {
        if (!this._sawfirstdataset || this._currentdataset < 0) {
            this._sawfirstdataset = true;
            ++this._currentdataset;
        }
        if (this.getLegend(this._currentdataset) == null) {
            this._firstinset = true;
            this._sawfirstdataset = true;
            this.addLegend(this._currentdataset, new String("Set " + this._currentdataset));
        }
        if (this._firstinset) {
            b = false;
            this._firstinset = false;
        }
        return b;
    }
    
    private synchronized void _addPoint(final int n, final double x, final double y, final double yLowEB, final double yHighEB, final boolean b, final boolean b2) {
        this._checkDatasetIndex(n);
        if (x < super._xBottom) {
            super._xBottom = x;
        }
        if (x > super._xTop) {
            super._xTop = x;
        }
        if (y < super._yBottom) {
            super._yBottom = y;
        }
        if (y > super._yTop) {
            super._yTop = y;
        }
        final PlotPoint plotPoint = new PlotPoint();
        plotPoint.x = x;
        plotPoint.y = y;
        plotPoint.connected = (b && this._connected);
        if (b2) {
            if (yLowEB < super._yBottom) {
                super._yBottom = yLowEB;
            }
            if (yLowEB > super._yTop) {
                super._yTop = yLowEB;
            }
            if (yHighEB < super._yBottom) {
                super._yBottom = yHighEB;
            }
            if (yHighEB > super._yTop) {
                super._yTop = yHighEB;
            }
            plotPoint.yLowEB = yLowEB;
            plotPoint.yHighEB = yHighEB;
            plotPoint.errorBar = true;
        }
        final Vector<PlotPoint> vector = this._points.elementAt(n);
        vector.addElement(plotPoint);
        if (this._pointsPersistence > 0 && vector.size() > this._pointsPersistence) {
            this.erasePoint(n, 0);
        }
        if (this._showing) {
            this._drawPlotPoint(this.getGraphics(), n, vector.size() - 1);
        }
    }
    
    private synchronized void _drawPlotPoint(final Graphics graphics, final int n, final int n2) {
        if (this._pointsPersistence > 0) {
            graphics.setXORMode(super._background);
        }
        if (super._usecolor) {
            graphics.setColor(PlotBox._colors[n % PlotBox._colors.length]);
        }
        else {
            graphics.setColor(super._foreground);
        }
        final PlotPoint plotPoint = this._points.elementAt(n).elementAt(n2);
        final long n3 = super._lry - (long)((plotPoint.y - super._yMin) * super._yscale);
        final long n4 = super._ulx + (long)((plotPoint.x - super._xMin) * super._xscale);
        final long longValue = this._prevx.elementAt(n);
        final long longValue2 = this._prevy.elementAt(n);
        if (plotPoint.connected) {
            this._drawLine(graphics, n, n4, n3, longValue, longValue2, true);
        }
        this._prevx.setElementAt(new Long(n4), n);
        this._prevy.setElementAt(new Long(n3), n);
        final Format format = this._formats.elementAt(n);
        if (format.impulsesUseDefault) {
            if (this._impulses) {
                this._drawImpulse(graphics, n4, n3, true);
            }
        }
        else if (format.impulses) {
            this._drawImpulse(graphics, n4, n3, true);
        }
        int n5 = this._marks;
        if (!format.marksUseDefault) {
            n5 = format.marks;
        }
        if (n5 != 0) {
            this._drawPoint(graphics, n, n4, n3, true);
        }
        if (this._bars) {
            this._drawBar(graphics, n, n4, n3, true);
        }
        if (plotPoint.errorBar) {
            this._drawErrorBar(graphics, n, n4, super._lry - (long)((plotPoint.yLowEB - super._yMin) * super._yscale), super._lry - (long)((plotPoint.yHighEB - super._yMin) * super._yscale), true);
        }
        graphics.setColor(super._foreground);
        if (this._pointsPersistence > 0) {
            graphics.setPaintMode();
        }
    }
    
    private synchronized void _erasePoint(final Graphics graphics, final int n, final int n2) {
        if (this._pointsPersistence > 0) {
            graphics.setXORMode(super._background);
        }
        if (super._usecolor) {
            graphics.setColor(PlotBox._colors[n % PlotBox._colors.length]);
        }
        else {
            graphics.setColor(super._foreground);
        }
        final Vector<PlotPoint> vector = this._points.elementAt(n);
        final PlotPoint plotPoint = vector.elementAt(n2);
        final long n3 = super._lry - (long)((plotPoint.y - super._yMin) * super._yscale);
        final long n4 = super._ulx + (long)((plotPoint.x - super._xMin) * super._xscale);
        if (n2 < vector.size() - 1) {
            final PlotPoint plotPoint2 = vector.elementAt(n2 + 1);
            final int n5 = super._ulx + (int)((plotPoint2.x - super._xMin) * super._xscale);
            final int n6 = super._lry - (int)((plotPoint2.y - super._yMin) * super._yscale);
            if (plotPoint2.connected) {
                this._drawLine(graphics, n, n5, n6, n4, n3, true);
            }
            plotPoint2.connected = false;
        }
        final Format format = this._formats.elementAt(n);
        if (format.impulsesUseDefault) {
            if (this._impulses) {
                this._drawImpulse(graphics, n4, n3, true);
            }
        }
        else if (format.impulses) {
            this._drawImpulse(graphics, n4, n3, true);
        }
        int n7 = this._marks;
        if (!format.marksUseDefault) {
            n7 = format.marks;
        }
        if (n7 != 0) {
            this._drawPoint(graphics, n, n4, n3, true);
        }
        if (this._bars) {
            this._drawBar(graphics, n, n4, n3, true);
        }
        if (plotPoint.errorBar) {
            this._drawErrorBar(graphics, n, n4, super._lry - (long)((plotPoint.yLowEB - super._yMin) * super._yscale), super._lry - (long)((plotPoint.yHighEB - super._yMin) * super._yscale), true);
        }
        graphics.setColor(super._foreground);
        if (this._pointsPersistence > 0) {
            graphics.setPaintMode();
        }
    }
    
    private class Format
    {
        public boolean impulses;
        public boolean impulsesUseDefault;
        public int marks;
        public boolean marksUseDefault;
        
        private Format() {
            this.impulsesUseDefault = true;
            this.marksUseDefault = true;
        }
    }
}
