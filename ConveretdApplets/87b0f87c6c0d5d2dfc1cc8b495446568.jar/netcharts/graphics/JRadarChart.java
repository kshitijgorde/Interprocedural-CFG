// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFParamException;
import netcharts.util.NFCompare;
import netcharts.util.NFSort;
import java.awt.Image;
import netcharts.util.NFParamImage;
import java.util.Hashtable;
import java.net.URL;
import java.awt.Color;
import netcharts.util.NFParamDef;
import netcharts.util.NFParam;
import netcharts.util.NFUtil;
import netcharts.util.NFDataSet;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Dimension;
import netcharts.util.NFDebug;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.applet.Applet;
import java.util.Vector;

public class JRadarChart extends JGraph
{
    private static final boolean a = false;
    protected Vector radialAxes;
    protected Vector dataSeries;
    protected Vector radiusGrids;
    protected int MaxDataSets;
    protected static double CENTER_PERCENTAGE_DEFAULT;
    protected double centerPercentage;
    protected static boolean ALWAYS_CIRCLE_DEFAULT;
    protected boolean alwaysCircle;
    protected static double LABEL_RATIO_DEFAULT;
    protected double labelRatio;
    public static final int LINE_VALUE_LABEL_STYLE_DEFAULT = 0;
    protected NFLabel lineValueLabel;
    
    public JRadarChart(final Applet applet) {
        this.radialAxes = new Vector();
        this.dataSeries = new Vector();
        this.radiusGrids = new Vector();
        this.MaxDataSets = 50;
        this.centerPercentage = JRadarChart.CENTER_PERCENTAGE_DEFAULT;
        this.alwaysCircle = JRadarChart.ALWAYS_CIRCLE_DEFAULT;
        this.labelRatio = JRadarChart.LABEL_RATIO_DEFAULT;
        this.lineValueLabel = null;
        this.initGraph(applet);
    }
    
    public JRadarChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.radialAxes = new Vector();
        this.dataSeries = new Vector();
        this.radiusGrids = new Vector();
        this.MaxDataSets = 50;
        this.centerPercentage = JRadarChart.CENTER_PERCENTAGE_DEFAULT;
        this.alwaysCircle = JRadarChart.ALWAYS_CIRCLE_DEFAULT;
        this.labelRatio = JRadarChart.LABEL_RATIO_DEFAULT;
        this.lineValueLabel = null;
        this.initGraph(applet);
        this.reshape(n, n2, n3, n4);
    }
    
    public void clean() {
        super.clean();
        if (this.radialAxes != null) {
            this.radialAxes.removeAllElements();
        }
        this.dataSeries.removeAllElements();
        this.radiusGrids.removeAllElements();
        this.alwaysCircle = JRadarChart.ALWAYS_CIRCLE_DEFAULT;
        this.labelRatio = JRadarChart.LABEL_RATIO_DEFAULT;
        this.centerPercentage = JRadarChart.CENTER_PERCENTAGE_DEFAULT;
    }
    
    public void dwellChanged() {
        this.rebuildAllActiveLabels();
    }
    
    public void setScale(final double n) {
        super.setScale(n);
        for (int i = 0; i < this.radialAxes.size(); ++i) {
            ((NFRadialAxis)this.radialAxes.elementAt(i)).setScale(n);
        }
    }
    
    public void rebuildAllActiveLabels() {
        if (super.dwell == null) {
            return;
        }
    }
    
    private boolean a(final NFLabel nfLabel) {
        return nfLabel != null && nfLabel.getLabel() != null && nfLabel.getLabel().length() > 0 && !nfLabel.getLabel().equals("OFF");
    }
    
    protected void drawGraph(final Graphics graphics, final Rectangle rectangle) {
        if (this.radialAxes.size() == 0) {
            if (super.notesets != null && super.notesets.size() > 0) {
                NFNoteSet.drawAllNoteSets(super.notesets, graphics, null);
            }
            return;
        }
        final NFSpacing nfSpacing = new NFSpacing(0.0, 360.0, this.radialAxes.size());
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        NFRegionBorder calculateOverhang;
        do {
            calculateOverhang = this.calculateOverhang(nfSpacing, rectangle, rectangle2);
        } while (calculateOverhang != null && calculateOverhang.left + calculateOverhang.right + calculateOverhang.top + calculateOverhang.bottom > 0);
        if (rectangle2.width <= 0 || rectangle2.height <= 0) {
            NFDebug.print("*** Insufficient room to display graph ***");
            this.fireGraphTooSmall(new Dimension(rectangle2.width, rectangle2.height));
            return;
        }
        a("revisedRect = " + rectangle2);
        final Point point = new Point(rectangle2.x + rectangle2.width / 2, rectangle2.y + rectangle2.height / 2);
        for (int i = this.radiusGrids.size() - 1; i >= 0; --i) {
            ((NFRadialGrid)this.radiusGrids.elementAt(i)).draw(graphics, rectangle2, this.centerPercentage);
        }
        final Vector vector = new Vector<Vector<Polygon>>();
        for (int j = 0; j < this.dataSeries.size(); ++j) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(j);
            final Vector<Polygon> vector2 = new Vector<Polygon>();
            for (int k = 0; k < ((Vector)nfDataSeries.info).size(); ++k) {
                final NFDataSet set = ((Vector)nfDataSeries.info).elementAt(k);
                final Polygon polygon = new Polygon();
                Point point2 = null;
                boolean b = false;
                boolean b2 = false;
                for (int l = 0; l < this.radialAxes.size(); ++l) {
                    double nth;
                    if (l >= set.size()) {
                        nth = Double.NaN;
                    }
                    else {
                        nth = set.getNth(l);
                    }
                    if (Double.isNaN(nth)) {
                        b = true;
                        polygon.addPoint(Integer.MIN_VALUE, Integer.MIN_VALUE);
                        if (point2 == null) {
                            b2 = true;
                        }
                    }
                    else {
                        final NFRadialAxis nfRadialAxis = this.radialAxes.elementAt(l);
                        this.setAxisLocation(nfRadialAxis, rectangle2, -(nfSpacing.getNthValue(l) - 90.0));
                        final Point point3 = nfRadialAxis.getPoint(nth);
                        if (point2 == null) {
                            point2 = point3;
                        }
                        polygon.addPoint(point3.x, point3.y);
                    }
                }
                vector2.addElement(polygon);
                if (nfDataSeries.fillColor != null && !b) {
                    graphics.setColor(nfDataSeries.fillColor);
                    if (NFUtil.getJDKVersion() >= 1.2 && nfDataSeries.pattern != null && nfDataSeries.pattern.pattern != 0) {
                        NF12GraphicsUtil.patternFillPolygon(graphics, polygon, (nfDataSeries.line != null && nfDataSeries.line.getColor() != null) ? nfDataSeries.line.getColor() : nfDataSeries.c, nfDataSeries.pattern);
                    }
                    else {
                        graphics.fillPolygon(polygon);
                    }
                }
                if (point2 != null) {
                    if (!b2) {
                        polygon.addPoint(point2.x, point2.y);
                    }
                    if (nfDataSeries.line != null) {
                        for (int n = 1; n < polygon.npoints; ++n) {
                            if (polygon.xpoints[n - 1] != Integer.MIN_VALUE && polygon.ypoints[n - 1] != Integer.MIN_VALUE && polygon.xpoints[n] != Integer.MIN_VALUE && polygon.ypoints[n] != Integer.MIN_VALUE) {
                                nfDataSeries.line.draw(graphics, polygon.xpoints[n - 1], polygon.ypoints[n - 1], polygon.xpoints[n], polygon.ypoints[n]);
                            }
                        }
                    }
                }
            }
            vector.addElement(vector2);
        }
        for (int n2 = 0; n2 < nfSpacing.size() && n2 < this.radialAxes.size(); ++n2) {
            ((NFRadialAxis)this.radialAxes.elementAt(n2)).draw(graphics, rectangle2, -(nfSpacing.getNthValue(n2) - 90.0));
        }
        for (int n3 = 0; n3 < vector.size(); ++n3) {
            final Vector<Polygon> vector3 = vector.elementAt(n3);
            final NFDataSeries nfDataSeries2 = this.dataSeries.elementAt(n3);
            int n4 = 0;
            for (int n5 = 0; n5 < vector3.size(); ++n5) {
                final Polygon polygon2 = vector3.elementAt(n5);
                for (int n6 = 0; n6 < polygon2.npoints - 1; ++n6) {
                    ++n4;
                    if (polygon2.xpoints[n6] == Integer.MIN_VALUE || polygon2.ypoints[n6] == Integer.MIN_VALUE) {
                        this.setActiveLabel(nfDataSeries2, n4, -1, -1, 0, 0, null, nfDataSeries2.sym);
                    }
                    else {
                        int size = 1;
                        if (nfDataSeries2.sym != null) {
                            graphics.setColor((nfDataSeries2.sym.getColor() == null) ? nfDataSeries2.c : nfDataSeries2.sym.getColor());
                            nfDataSeries2.sym.draw(graphics, polygon2.xpoints[n6], polygon2.ypoints[n6]);
                            size = nfDataSeries2.sym.size;
                        }
                        final String string = "(" + this.radialAxes.elementAt(n6).getName() + ", " + ((Vector)nfDataSeries2.info).elementAt(n5).getNth(n6) + ")";
                        final int n7 = polygon2.xpoints[n6] - (super.dwellOffset + size) / 2;
                        final int n8 = polygon2.ypoints[n6] - (super.dwellOffset + size) / 2;
                        this.setActiveLabel(nfDataSeries2, n4, n7, n8, super.dwellOffset + size, super.dwellOffset + size, string, nfDataSeries2.sym);
                        if (nfDataSeries2.valueLabelStyle != 0) {
                            JValueLabel.drawPointValueLabel((this.lineValueLabel == null) ? super.dwell.getLabel() : this.lineValueLabel, graphics, nfDataSeries2, n7, n8, string);
                        }
                    }
                }
            }
            vector3.removeAllElements();
        }
        vector.removeAllElements();
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.drawAllNoteSets(super.notesets, graphics, null);
        }
    }
    
    protected static void defineLineSymbol(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        final Hashtable symbolTypeTable = nfParam.getSymbolTypeTable();
        final Hashtable symbolStyleTable = nfParam.getSymbolStyleTable();
        vector.addElement(nfParam.defineSymbol("LineSymbolType", symbolTypeTable, null));
        vector.addElement(nfParam.defineNumber("LineSymbolSize", null));
        vector.addElement(nfParam.defineSymbol("LineSymbolStyle", symbolStyleTable, null));
        vector.addElement(nfParam.defineColor("LineSymbolBorderColor", null));
        vector.addElement(nfParam.defineNumber("LineSymbolBorderWidth", null));
        vector.addElement(nfParam.defineImage("LineSymbolImage", null));
        vector.addElement(nfParam.defineColor("LineSymbolColor", null));
        nfParam.defineVector("LineSymbol", nfParam.defineTuple("LineSymbolTuple", vector));
    }
    
    protected boolean loadLineSymbol(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("LineSymbol");
        if (vector == null || vector.size() == 0) {
            return false;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i);
            if (dataSeries == null) {
                break;
            }
            if (dataSeries.sym == null) {
                dataSeries.sym = new NFGraphSymbol();
                dataSeries.sym.type = 0;
                dataSeries.sym.size = 1;
                dataSeries.sym.style = 1;
                dataSeries.sym.setScale(super.scale);
            }
            final Vector<Number> vector2 = vector.elementAt(i);
            if (vector2 != null) {
                if (vector2.size() != 0) {
                    final Number n = vector2.elementAt(0);
                    if (n != null) {
                        dataSeries.sym.type = n.intValue();
                    }
                    final Number n2 = vector2.elementAt(1);
                    if (n2 != null) {
                        dataSeries.sym.size = n2.intValue();
                    }
                    final Number n3 = vector2.elementAt(2);
                    if (n3 != null) {
                        dataSeries.sym.style = n3.intValue();
                    }
                    dataSeries.sym.setOutlineColor((Color)vector2.elementAt(3));
                    final Number n4 = vector2.elementAt(4);
                    if (n4 != null) {
                        dataSeries.sym.setOutlineWidth(n4.intValue());
                    }
                    final NFParamImage nfParamImage = (NFParamImage)vector2.elementAt(5);
                    if (nfParamImage != null) {
                        dataSeries.sym.setImage(nfParamImage.im);
                    }
                    else {
                        dataSeries.sym.setImage(null);
                    }
                    dataSeries.sym.setColor(NFUtil.getColor(vector2, 6, null));
                }
            }
        }
        return true;
    }
    
    protected static void defineLineStyle(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineSymbol("LineStyleType", nfParam.getLineStyleTable(), null));
        vector.addElement(nfParam.defineNumber("LineStyleWidth", null));
        vector.addElement(nfParam.defineColor("LineStyleColor", null));
        vector.addElement(nfParam.defineColor("LineStyleFill", null));
        nfParam.defineVector("LineStyle", nfParam.defineTuple("LineStyleTuple", vector));
    }
    
    protected boolean loadLineStyle(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("LineStyle");
        if (vector == null || vector.size() == 0) {
            return false;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i);
            if (dataSeries == null) {
                break;
            }
            if (dataSeries.line == null) {
                (dataSeries.line = new NFLine(null)).setStyle(0);
                dataSeries.line.setThickness(1);
                dataSeries.line.setColor(Color.black);
                dataSeries.line.setScale(super.scale);
            }
            final Vector<Number> vector2 = vector.elementAt(i);
            final Number n = vector2.elementAt(0);
            if (n != null) {
                dataSeries.line.setStyle(n.intValue());
            }
            final Number n2 = vector2.elementAt(1);
            if (n2 != null) {
                dataSeries.line.setThickness(n2.intValue());
            }
            dataSeries.line.setColor((Color)vector2.elementAt(2));
            if (dataSeries.line.getColor() == null) {
                dataSeries.line.setColor(dataSeries.c);
            }
            dataSeries.fillColor = (Color)vector2.elementAt(3);
        }
        return true;
    }
    
    public int getDataSeriesIndex(final NFDataSeries nfDataSeries) {
        try {
            return this.dataSeries.indexOf(nfDataSeries) + 1;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public NFDataSeries getDataSeries(final int n) {
        try {
            return this.dataSeries.elementAt(n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public NFActiveLabel getAxisActiveLabel(final int n) {
        try {
            return this.radialAxes.elementAt(n - 1).getTitleActiveLabel();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        this.MaxDataSets = NFUtil.getNumber(this.getRuntimeProperties().getProperty("MaxDataSets"), this.MaxDataSets);
        super.param.defineChartType("ChartType", 15);
        defineLineStyle(super.param);
        defineLineSymbol(super.param);
        NFPatternFill.definePatternFillParam(super.param, "LineFillPattern");
        NFRadialAxis.defineRadialAxes(super.param);
        NFRadialGrid.defineRadialGrids(super.param);
        super.param.defineNumber("CenterRadius", new Double(0.0));
        super.param.defineString("RadarSquare", new String("ON"));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("LineSetName"));
        vector.addElement(super.param.defineColor("LineSetColor", null));
        super.param.defineVector("LineSets", super.param.defineTuple("LineSet", vector));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineDate("point"));
        final NFParamDef defineTuple = super.param.defineTuple("DataPoint", vector2, true);
        for (int i = 0; i < this.MaxDataSets; ++i) {
            super.param.defineVector("LineSet" + (i + 1), defineTuple);
        }
        for (int j = 0; j < this.MaxDataSets; ++j) {
            super.param.defineActiveLabel("ActiveLabels" + (j + 1));
        }
        super.param.defineString("ActiveLabelsEnabled", "ON");
        JValueLabel.definePointValueLabel(super.param, "Line", 0);
    }
    
    protected boolean loadLineSets(final NFParam nfParam) throws Exception {
        if (!nfParam.changed("LineSets")) {
            boolean b = false;
            for (int i = 0; i < this.MaxDataSets; ++i) {
                if (nfParam.changed("LineSet" + (i + 1))) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                return false;
            }
        }
        this.dataSeries.removeAllElements();
        if (super.legend != null) {
            super.legend.clearDataSets();
        }
        final Vector vector = (Vector)nfParam.get("LineSets");
        final int size = vector.size();
        if (vector != null && size > 0) {
            for (int n = 0; n < size && n < this.MaxDataSets; ++n) {
                final NFDataSeries nfDataSeries = new NFDataSeries();
                this.dataSeries.addElement(nfDataSeries);
                if (super.legend != null) {
                    super.legend.addDataSet(nfDataSeries);
                }
                if (nfDataSeries.info == null) {
                    nfDataSeries.info = new Vector();
                }
                else {
                    ((Vector)nfDataSeries.info).removeAllElements();
                }
                final Vector<String> vector2 = vector.elementAt(n);
                nfDataSeries.name = vector2.elementAt(0);
                nfDataSeries.c = (Color)vector2.elementAt(1);
                if (nfDataSeries.c == null) {
                    nfDataSeries.c = this.defaultColor(n);
                }
                final Vector vector3 = (Vector)nfParam.get("LineSet" + (n + 1));
                for (int j = 0; j < vector3.size(); ++j) {
                    final Vector<Number> vector4 = vector3.elementAt(j);
                    final NFDataSet set = new NFDataSet();
                    for (int n2 = 0; n2 < vector4.size() && n2 < this.radialAxes.size(); ++n2) {
                        Number n3 = vector4.elementAt(n2);
                        if (n3 == null) {
                            n3 = new Double(Double.NaN);
                        }
                        set.addPoint(n3.doubleValue());
                    }
                    ((Vector)nfDataSeries.info).addElement(set);
                }
            }
        }
        return true;
    }
    
    protected void setLegendItems() {
        if (super.legend != null) {
            super.legend.clearDataSets();
        }
        for (int i = 0; i < this.dataSeries.size(); ++i) {
            if (super.legend != null) {
                super.legend.addDataSet((NFDataSeries)this.dataSeries.elementAt(i));
            }
        }
    }
    
    protected boolean loadRadialGrids(final NFParam nfParam) throws Exception {
        if (!nfParam.changed("RadialGrids")) {
            return false;
        }
        this.radiusGrids = NFRadialGrid.loadRadialGrids(nfParam);
        if (this.radiusGrids.size() > 1) {
            NFSort.qsort(this.radiusGrids, this.radiusGrids.elementAt(0));
        }
        return true;
    }
    
    protected boolean loadRadialAxes(final NFParam nfParam) throws Exception {
        if (!nfParam.changed("RadialAxes") && !nfParam.changed("RadialAxesLabel") && !nfParam.changed("RadialAxesTics") && !nfParam.changed("RadialAxesTitleActiveLabels") && !nfParam.changed("RadialAxesFormat")) {
            return false;
        }
        this.setNumberFormat(this.radialAxes = NFRadialAxis.loadRadialAxes(nfParam, super.dwell));
        return true;
    }
    
    protected void loadActiveLabels(final NFParam nfParam) throws Exception {
        final boolean equals = NFUtil.getString(nfParam.get("ActiveLabelsEnabled"), "ON").equals("ON");
        for (int i = 0; i < this.dataSeries.size(); ++i) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
            if (super.dwell != null) {
                super.dwell.removeLabel(nfDataSeries.activeLabels);
            }
            nfDataSeries.activeLabels = new Vector();
            if (equals) {
                final Vector loadAllParams = NFActiveLabel.loadAllParams(nfParam, "ActiveLabels" + (i + 1));
                if (loadAllParams == null || loadAllParams.size() == 0) {
                    for (int j = 0; j < ((Vector)nfDataSeries.info).size(); ++j) {
                        final NFDataSet set = ((Vector)nfDataSeries.info).elementAt(j);
                        for (int n = 0; n < set.size() && n < this.radialAxes.size(); ++n) {
                            nfDataSeries.activeLabels.addElement(new NFActiveLabel("(" + ((NFRadialAxis)this.radialAxes.elementAt(n)).getName() + ", " + String.valueOf(set.getNth(n)) + ")", null, null));
                        }
                    }
                }
                else {
                    NFActiveLabel nfActiveLabel = null;
                    for (int k = 0; k < ((Vector)nfDataSeries.info).size(); ++k) {
                        final NFDataSet set2 = ((Vector)nfDataSeries.info).elementAt(k);
                        if (k < loadAllParams.size()) {
                            nfActiveLabel = loadAllParams.elementAt(k);
                        }
                        if (nfActiveLabel == null) {
                            break;
                        }
                        for (int l = 0; l < set2.size(); ++l) {
                            nfDataSeries.activeLabels.addElement(new NFActiveLabel(nfActiveLabel.label, nfActiveLabel.url, nfActiveLabel.target));
                        }
                    }
                }
                if (super.dwell != null) {
                    super.dwell.addLabels(nfDataSeries.activeLabels, nfDataSeries.activeLabels.size());
                }
            }
        }
    }
    
    protected synchronized void loadParams() throws Exception {
        final boolean b = super.legend != null;
        super.loadParams();
        if (super.legend != null && !b) {
            this.setLegendItems();
        }
        if (this.loadRadialAxes(super.param)) {
            super.graphChanged = true;
        }
        if (this.loadLineSets(super.param)) {
            super.graphChanged = true;
        }
        if (this.loadLineStyle(super.param)) {}
        super.graphChanged = true;
        if (this.loadLineSymbol(super.param)) {
            super.graphChanged = true;
        }
        if (this.loadRadialGrids(super.param)) {
            super.graphChanged = true;
        }
        if (super.param.changed("CenterRadius")) {
            double number = NFUtil.getNumber(super.param.get("CenterRadius"), JRadarChart.CENTER_PERCENTAGE_DEFAULT);
            if (number < 0.0) {
                number = 0.0;
            }
            if (number > 100.0) {
                number = 100.0;
            }
            this.centerPercentage = number / 100.0;
            super.graphChanged = true;
        }
        if (super.param.changed("RadarSquare")) {
            final String string = NFUtil.getString(super.param.get("RadarSquare"), JRadarChart.ALWAYS_CIRCLE_DEFAULT ? "ON" : "OFF");
            if (string != null && string.equalsIgnoreCase("ON")) {
                this.alwaysCircle = true;
            }
            else {
                this.alwaysCircle = false;
            }
            super.graphChanged = true;
        }
        this.loadActiveLabels(super.param);
        this.loadPatternFill(super.param, "LineFillPattern");
        this.lineValueLabel = JValueLabel.loadValueLabel(this, "Line", 0, this.dataSeries, this.lineValueLabel, 0);
    }
    
    protected void loadPatternFill(final NFParam nfParam, final String s) throws NFParamException {
        if (nfParam.changed(s)) {
            super.graphChanged = true;
            final NFPatternFill[] loadPatternFillParam = NFPatternFill.loadPatternFillParam(nfParam, s);
            for (int i = 0; i < this.dataSeries.size(); ++i) {
                ((NFDataSeries)this.dataSeries.elementAt(i)).pattern = ((loadPatternFillParam == null || i >= loadPatternFillParam.length) ? null : loadPatternFillParam[i]);
            }
        }
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, int selectedItemIndex, int n, int n2, int n3, int n4, final String label, final NFGraphSymbol nfGraphSymbol) {
        if (!super.dwellOn || nfDataSeries.activeLabels == null) {
            return;
        }
        final int size = nfDataSeries.activeLabels.size();
        if (--selectedItemIndex < 0 || selectedItemIndex >= size) {
            return;
        }
        final NFActiveLabel nfActiveLabel = nfDataSeries.activeLabels.elementAt(selectedItemIndex);
        nfActiveLabel.selectedItemParam = "LineSet" + String.valueOf(this.getDataSeriesIndex(nfDataSeries));
        nfActiveLabel.selectedItemIndex = selectedItemIndex;
        if (nfActiveLabel.label == null || nfActiveLabel.label.length() < 1) {
            nfActiveLabel.autoLabel = true;
        }
        if (nfActiveLabel.autoLabel) {
            nfActiveLabel.setLabel(label);
        }
        if (n3 < 0) {
            n3 = -n3;
            n -= n3;
        }
        if (n4 < 0) {
            n4 = -n4;
            n2 -= n4;
        }
        if (nfGraphSymbol == null || (n == -1 && n2 == -1)) {
            nfActiveLabel.setBounds(n, n2, n3, n4);
            return;
        }
        final Polygon outline = nfGraphSymbol.getOutline();
        if (outline == null) {
            nfActiveLabel.setBounds(n, n2, n3, n4);
            return;
        }
        nfActiveLabel.setBounds(outline);
    }
    
    private static void a(final String s) {
    }
    
    protected static double radians(final double n) {
        return n / 180.0 * 3.141592653589793;
    }
    
    protected void setAxisLocation(final NFRadialAxis nfRadialAxis, final Rectangle rectangle, final double n) {
        final double radians = radians(n);
        final int n2 = rectangle.width / 2;
        final int n3 = rectangle.height / 2;
        final Point point = new Point(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
        nfRadialAxis.setLocation(point.x + (int)NFUtil.rint(Math.cos(radians) * n2 * this.centerPercentage), point.y - (int)NFUtil.rint(Math.sin(radians) * n3 * this.centerPercentage), point.x + (int)NFUtil.rint(Math.cos(radians) * n2), point.y - (int)NFUtil.rint(Math.sin(radians) * n3));
    }
    
    protected NFRegionBorder checkAxisLabel(final Rectangle rectangle, final NFLabel nfLabel, final NFRegionBorder nfRegionBorder) {
        final Dimension bounds = nfLabel.getBounds(null);
        a("label=" + nfLabel.getLabel() + " d=" + bounds);
        final Point point = new Point(nfLabel.x, nfLabel.y);
        if (point.x - bounds.width / 2 < rectangle.x) {
            final int left = rectangle.x - (point.x - bounds.width / 2);
            if (left > nfRegionBorder.left) {
                a("label " + nfLabel.getLabel() + " over left=" + left);
                nfRegionBorder.left = left;
            }
        }
        if (point.x + bounds.width / 2 > rectangle.x + rectangle.width) {
            final int right = point.x + bounds.width / 2 - (rectangle.x + rectangle.width);
            if (right > nfRegionBorder.right) {
                a("label " + nfLabel.getLabel() + " over right=" + right);
                nfRegionBorder.right = right;
            }
        }
        if (point.y - bounds.height / 2 < rectangle.y) {
            final int top = rectangle.y - (point.y - bounds.height / 2);
            if (top > nfRegionBorder.top) {
                a("label " + nfLabel.getLabel() + " over top=" + top);
                nfRegionBorder.top = top;
            }
        }
        if (point.y + bounds.height / 2 > rectangle.y + rectangle.height) {
            final int bottom = point.y + bounds.height / 2 - (rectangle.y + rectangle.height);
            if (bottom > nfRegionBorder.bottom) {
                a("label " + nfLabel.getLabel() + " over bottom=" + bottom);
                nfRegionBorder.bottom = bottom;
            }
        }
        return nfRegionBorder;
    }
    
    protected NFRegionBorder calculateOverhang(final NFSpacing nfSpacing, final Rectangle rectangle, final Rectangle rectangle2) {
        final NFRegionBorder nfRegionBorder4;
        final NFRegionBorder nfRegionBorder3;
        final NFRegionBorder nfRegionBorder2;
        final NFRegionBorder nfRegionBorder;
        NFRegionBorder checkAxisLabel = nfRegionBorder = (nfRegionBorder2 = (nfRegionBorder3 = (nfRegionBorder4 = new NFRegionBorder())));
        final boolean b = false;
        nfRegionBorder.right = (b ? 1 : 0);
        nfRegionBorder2.left = (b ? 1 : 0);
        nfRegionBorder3.bottom = (b ? 1 : 0);
        nfRegionBorder4.top = (b ? 1 : 0);
        for (int i = 0; i < this.radialAxes.size(); ++i) {
            final NFRadialAxis nfRadialAxis = this.radialAxes.elementAt(i);
            this.setAxisLocation(nfRadialAxis, rectangle2, -(nfSpacing.getNthValue(i) - 90.0));
            if (nfRadialAxis.getShowLabel() && this.a(nfRadialAxis.getLabel())) {
                checkAxisLabel = this.checkAxisLabel(rectangle, nfRadialAxis.getLabel(), checkAxisLabel);
            }
        }
        rectangle2.width -= checkAxisLabel.right + checkAxisLabel.left;
        rectangle2.height -= checkAxisLabel.top + checkAxisLabel.bottom;
        a("w=" + rectangle2.width + " h=" + rectangle2.height);
        if (rectangle2.width <= 0 || rectangle2.height <= 0) {
            return null;
        }
        rectangle2.x += checkAxisLabel.left;
        rectangle2.y += checkAxisLabel.top;
        if (this.alwaysCircle) {
            final int min = Math.min(rectangle2.width, rectangle2.height);
            rectangle2.x += (rectangle2.width - min) / 2;
            rectangle2.y += (rectangle2.height - min) / 2;
            final int n = min;
            rectangle2.height = n;
            rectangle2.width = n;
        }
        return checkAxisLabel;
    }
    
    protected void loadNumberFormat(final Vector vector) {
        super.loadNumberFormat(vector);
        this.setNumberFormat(this.radialAxes);
    }
    
    protected void setNumberFormat(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            this.setNumberFormat(vector.elementAt(i));
        }
    }
    
    protected void setNumberFormat(final NFRadialAxis nfRadialAxis) {
        nfRadialAxis.setNumberFormat(this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol());
    }
    
    static {
        JRadarChart.CENTER_PERCENTAGE_DEFAULT = 0.5;
        JRadarChart.ALWAYS_CIRCLE_DEFAULT = true;
        JRadarChart.LABEL_RATIO_DEFAULT = 1.0;
    }
}
