// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFParam;
import java.awt.Component;
import java.util.Enumeration;
import netcharts.util.NFUtil;
import java.awt.Color;
import netcharts.util.NFParamDef;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.Applet;

public final class NFTimeChart extends NFDataChart
{
    private static final boolean a = false;
    protected static final boolean UNIQUE_TASK_COLORS_DEFAULT = false;
    private boolean b;
    protected static final double DEFAULT_TASK_HEIGHT_FACTOR = 0.75;
    protected double taskHeightFactor;
    private int c;
    
    public NFTimeChart(final Applet applet) {
        this.b = false;
        this.taskHeightFactor = 0.75;
        this.initGraph(applet);
        this.initTimeChart();
    }
    
    public NFTimeChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this(applet);
        this.reshape(n, n2, n3, n4);
    }
    
    protected void initTimeChart() {
        this.initChart();
        super.axes.removeElement(super.bottomAxis);
        super.bottomAxis = null;
        super.topAxis = NFAxis.defaultAxis(super.axisNames[1]);
        super.axes.addElement(super.topAxis);
        this.setNumberFormat(super.topAxis);
        super.XValuesAxis = super.topAxis;
        super.leftAxis.reverseActiveLabels = true;
    }
    
    public void clean() {
        super.clean();
        if (super.bottomAxis != null) {
            super.axes.removeElement(super.bottomAxis);
            super.bottomAxis = null;
        }
        if (super.topAxis != null) {
            super.topAxis.showTicsAndLabels(true, true);
            super.topAxis.showAxisLine(true);
        }
    }
    
    protected void loadGridParams() throws Exception {
        super.loadGridParams(super.topAxis, super.leftAxis);
    }
    
    public void setDefaultAxis(final NFAxis nfAxis) {
        if (nfAxis != super.leftAxis) {
            super.setDefaultAxis(nfAxis);
            return;
        }
        final int size = super.dataSeries.size();
        if (super.leftAxis == null || size == 0) {
            super.setDefaultAxis(nfAxis);
            return;
        }
        final Vector<String> ticLabels = new Vector<String>();
        for (int i = 0; i < size; ++i) {
            ticLabels.insertElementAt(((NFDataSeries)super.dataSeries.elementAt(i)).name, 0);
        }
        nfAxis.setTicLabels(ticLabels);
        nfAxis.setSpacing(new NFSpacing(0.0, size - 1.0, 1.0));
        if (nfAxis.autoscale) {
            nfAxis.setMinMax(-0.5, size - 0.5);
        }
        else {
            final double n = Math.ceil(nfAxis.getMin()) - 0.5;
            final double n2 = Math.floor(nfAxis.getMax()) + 0.5;
            if (n != nfAxis.getMin() || n2 != nfAxis.getMax()) {
                nfAxis.setMinMax(n, n2);
            }
        }
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        if (nfDataSeries.type != 3) {
            return super.getMinMax(nfDataSeries, nfAxis, array);
        }
        if (nfDataSeries.YAxis == nfAxis) {
            final int size = super.dataSeries.size();
            if (-0.5 < array[0]) {
                array[0] = -0.5;
            }
            if (size - 0.5 > array[1]) {
                array[1] = size - 0.5;
            }
            return array;
        }
        final NFTimeChartInfo nfTimeChartInfo = (NFTimeChartInfo)nfDataSeries.info;
        for (int size2 = nfTimeChartInfo.tasks.size(), i = 0; i < size2; ++i) {
            final NFTimeChartTask nfTimeChartTask = nfTimeChartInfo.tasks.elementAt(i);
            if (nfTimeChartTask.xmin < array[0]) {
                array[0] = nfTimeChartTask.xmin;
            }
            if (nfTimeChartTask.xmax > array[1]) {
                array[1] = nfTimeChartTask.xmax;
            }
        }
        return array;
    }
    
    protected int getDataSetSize(final NFDataSeries nfDataSeries) {
        if (nfDataSeries.info != null && nfDataSeries.info instanceof NFTimeChartInfo && ((NFTimeChartInfo)nfDataSeries.info).tasks != null) {
            return ((NFTimeChartInfo)nfDataSeries.info).tasks.size();
        }
        return super.getDataSetSize(nfDataSeries);
    }
    
    protected void reset() {
        this.setDefaultAxes();
        this.setDefaultGrid();
    }
    
    protected void drawData(final Graphics graphics) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        int n = 0;
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            final double n2 = size - 1.0 - i;
            final int y = nfDataSeries.YAxis.mapValue(n2 + this.taskHeightFactor / 2.0).y;
            final int y2 = nfDataSeries.YAxis.mapValue(n2 - this.taskHeightFactor / 2.0).y;
            final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
            this.a(clippedGraphics, nfDataSeries, i, y, y2 - y, n);
            clippedGraphics.dispose();
            final NFTimeChartInfo nfTimeChartInfo = (NFTimeChartInfo)nfDataSeries.info;
            if (nfTimeChartInfo != null) {
                n += nfTimeChartInfo.tasks.size();
            }
        }
    }
    
    public void drawDataLite(final Graphics graphics) {
        this.drawData(graphics);
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4) {
        final NFTimeChartInfo nfTimeChartInfo = (NFTimeChartInfo)nfDataSeries.info;
        final int size = nfTimeChartInfo.tasks.size();
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; ++i) {
            this.a(graphics, nfDataSeries, i, (NFTimeChartTask)nfTimeChartInfo.tasks.elementAt(i), n2, n3, n4 + i);
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final NFTimeChartTask nfTimeChartTask, final int n2, final int n3, final int n4) {
        final int x = nfDataSeries.XAxis.mapValue(nfTimeChartTask.xmin).x;
        final int n5 = nfDataSeries.XAxis.mapValue(nfTimeChartTask.xmax).x - x;
        if (nfTimeChartTask.color != null) {
            nfDataSeries.region.setColor(nfTimeChartTask.color);
        }
        else if (this.b) {
            nfDataSeries.region.setColor(this.defaultColor(n4));
        }
        else {
            nfDataSeries.region.setColor(nfDataSeries.c);
        }
        nfDataSeries.region.draw(graphics, x, n2, n5, n3);
        if (nfTimeChartTask.label != null && nfTimeChartTask.label.getLabel() != null && nfTimeChartTask.label.getLabel().length() > 0) {
            nfTimeChartTask.label.draw(graphics, x + n5 / 2, n2 + n3 / 2);
        }
        this.setActiveLabel(nfDataSeries, n, x, n2, n5, n3, "Start: " + super.topAxis.getLabel(nfTimeChartTask.start) + "\n" + "End: " + super.topAxis.getLabel(nfTimeChartTask.duration));
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        final Vector vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("DataSetName"));
        this.c = vector.size();
        super.param.defineRegion("DataSet", vector);
        super.param.defineVector("DataSets", super.param.defineTuple("DataSet", vector));
        final Vector vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineDate("TaskStart"));
        vector2.addElement(super.param.defineDate("TaskEnd"));
        vector2.addElement(super.param.defineColor("TaskColor", null));
        super.param.defineLabel("TaskLabel", vector2);
        final NFParamDef defineTuple = super.param.defineTuple("Task", vector2);
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("DataSet" + (i + 1), defineTuple);
        }
        this.defineDataAxisParams(super.param, "DataAxis");
        super.param.defineString("UniqueTaskColors");
        super.param.defineNumber("TaskHeight");
        final Vector vector3 = (Vector)vector2.clone();
        vector3.insertElementAt(super.param.defineNumber("TaskSet"), 0);
        super.param.defineActiveLabel("Active", vector3);
        super.param.defineVector("AddDataPoint", super.param.defineTuple("AddTask", vector3));
    }
    
    protected synchronized void loadParams() throws Exception {
        boolean b = false;
        for (int i = 0; i < super.axisNames.length; ++i) {
            for (int j = 0; j < super.MaxAxes; ++j) {
                if (super.param.changed(super.axisNames[i] + ((j == 0) ? "" : String.valueOf(j)) + "Format")) {
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            }
        }
        super.loadParams();
        final boolean b2 = this.loadDataSetParams(super.param, 3, "DataSets", "DataAxis", super.topAxis, super.leftAxis) || b;
        final boolean[] loadDataSets = this.loadDataSets(super.param, 3, "DataSet", b2);
        this.loadActiveLabels(super.param, 3, "ActiveLabels", loadDataSets);
        if (super.param.changed("UniqueTaskColors")) {
            super.graphChanged = true;
            this.b = NFUtil.getString(super.param.get("UniqueTaskColors"), "OFF").equalsIgnoreCase("ON");
        }
        if (super.param.changed("TaskHeight")) {
            super.graphChanged = true;
            this.taskHeightFactor = NFUtil.getNumber(super.param.get("TaskHeight"), 0.75);
            if (this.taskHeightFactor < 0.0) {
                this.taskHeightFactor = -this.taskHeightFactor;
            }
            if (this.taskHeightFactor >= 1.0) {
                this.taskHeightFactor /= 100.0;
            }
            if (this.taskHeightFactor > 1.0) {
                this.taskHeightFactor = 1.0;
            }
        }
        if (super.param.changed("AddDataPoint")) {
            final Vector vector = (Vector)super.param.get("AddDataPoint");
            if (vector == null || vector.size() == 0) {
                return;
            }
            final Enumeration<Vector> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final Vector vector2 = elements.nextElement();
                final int number = NFUtil.getNumber(vector2, 0, -1);
                final NFDataSeries dataSeries = this.getDataSeries(number);
                if (dataSeries != null) {
                    final int addTask = this.addTask(dataSeries, vector2, 1);
                    if (super.dwell != null) {
                        dataSeries.activeLabels.addElement(super.dwell.addLabel(NFActiveLabel.loadParams(super.param, vector2, addTask + 1)));
                    }
                    if (loadDataSets == null || number < 1 || number > loadDataSets.length) {
                        continue;
                    }
                    loadDataSets[number - 1] = true;
                }
            }
        }
        for (int k = 0; k < loadDataSets.length; ++k) {
            if (b2 || loadDataSets[k]) {
                this.reset();
                super.layoutChanged = true;
                break;
            }
        }
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 3) {
            super.loadDataSetParams(nfDataSeries, n, o);
            return;
        }
        if (nfDataSeries.info == null) {
            nfDataSeries.info = new NFTimeChartInfo();
        }
        final Vector vector = (Vector)o;
        nfDataSeries.name = vector.elementAt(0);
        nfDataSeries.region = NFRegion.loadParams(super.param, vector, this.c);
        if (nfDataSeries.region == null) {
            nfDataSeries.c = this.defaultColor(n);
        }
        else {
            nfDataSeries.c = nfDataSeries.region.getColor();
            if (nfDataSeries.c == null) {
                nfDataSeries.c = this.defaultColor(n);
            }
        }
    }
    
    protected void clearDataItems(final NFDataSeries nfDataSeries, final int n) {
        if (nfDataSeries.type != 3) {
            super.clearDataItems(nfDataSeries, n);
            return;
        }
        final NFTimeChartInfo nfTimeChartInfo = (NFTimeChartInfo)nfDataSeries.info;
        if (nfTimeChartInfo.tasks == null) {
            nfTimeChartInfo.tasks = new Vector();
        }
        else {
            nfTimeChartInfo.tasks.removeAllElements();
        }
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 3) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        this.addTask(nfDataSeries, (Vector)o, 0);
    }
    
    protected int addTask(final NFDataSeries nfDataSeries, final Vector vector, final int n) {
        final NFTimeChartTask nfTimeChartTask = new NFTimeChartTask();
        nfTimeChartTask.start = vector.elementAt(n);
        nfTimeChartTask.duration = vector.elementAt(n + 1);
        nfTimeChartTask.color = vector.elementAt(n + 2);
        nfTimeChartTask.label = NFLabel.loadParams(super.param, vector, n + 3);
        if (nfTimeChartTask.label != null) {
            nfTimeChartTask.label.setComponent(this);
        }
        nfTimeChartTask.setRange(nfDataSeries.XAxis);
        ((NFTimeChartInfo)nfDataSeries.info).tasks.addElement(nfTimeChartTask);
        return 3 + NFParam.labelParamCount();
    }
}
