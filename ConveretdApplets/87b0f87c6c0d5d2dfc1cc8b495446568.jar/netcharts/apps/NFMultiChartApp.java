// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import netcharts.util.NFKeyValue;
import java.net.URL;
import java.applet.AppletStub;
import java.awt.Image;
import netcharts.gui.NFImageCanvas;
import netcharts.gui.NFSashPanel;
import netcharts.util.NFContext;
import netcharts.util.NFParamException;
import java.util.Enumeration;
import netcharts.util.NFDebug;
import netcharts.chart.NFChart;
import netcharts.util.NFCdf;
import java.awt.Point;
import java.awt.Container;
import netcharts.graphics.NFGraph;
import netcharts.gui.NFSash;
import java.awt.Component;
import netcharts.util.NFUtil;
import netcharts.util.NFGlobal;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Dimension;
import netcharts.util.NFParam;
import java.util.Hashtable;
import java.awt.Color;
import netcharts.gui.NFMultiSash;
import java.util.Vector;
import netcharts.graphics.NFMultiChart;
import netcharts.gui.NFGuiObserver;
import netcharts.util.NFParamObserver;

public class NFMultiChartApp extends NFApplet implements NFParamObserver, NFGuiObserver
{
    public NFMultiChart mc;
    public Vector charts;
    private int a;
    private NFMultiSash b;
    private boolean c;
    private int d;
    private Color e;
    private boolean f;
    private Hashtable g;
    private boolean h;
    private NFGuiObserver i;
    private NFParam j;
    private Dimension k;
    private boolean l;
    private boolean m;
    private boolean n;
    private Hashtable o;
    
    public NFMultiChartApp() {
        this.charts = new Vector();
        this.a = 1;
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = Color.lightGray;
        this.f = true;
        this.g = new Hashtable();
        this.h = true;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = true;
        this.n = false;
        this.o = null;
    }
    
    public NFMultiChartApp(final Applet applet) {
        super(applet);
        this.charts = new Vector();
        this.a = 1;
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = Color.lightGray;
        this.f = true;
        this.g = new Hashtable();
        this.h = true;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = true;
        this.n = false;
        this.o = null;
    }
    
    public void init() {
        final NFMultiChart nfMultiChart = new NFMultiChart(super.app, this);
        this.mc = nfMultiChart;
        super.graph = nfMultiChart;
        super.init();
    }
    
    public void destroy() {
        this.removeAllCharts(false);
        this.removeAll();
        this.addObserver(null);
        this.b.destroy();
        this.mc.removeAll();
        this.b = null;
        super.destroy();
    }
    
    public void addObserver(final NFGuiObserver i) {
        this.i = i;
    }
    
    public void printAll(final Graphics graphics) {
        compPrint(graphics, this, NFGlobal.getVendor() == 4 && NFUtil.getBoolean(super.graph.getRuntimeProperties().getProperty("NetscapePrintFix"), false));
    }
    
    public static void compPrint(final Graphics graphics, final Component component, final boolean b) {
        Dimension size = component.size();
        if (b) {
            size = new Dimension((int)NFUtil.rint(size.width * 0.75), (int)NFUtil.rint(size.height * 0.75));
        }
        if (component instanceof NFSash) {
            ((NFSash)component).print(graphics, size);
            return;
        }
        if (component instanceof NFGraph) {
            ((NFGraph)component).print(graphics);
        }
        else if (!(component instanceof Container)) {
            component.print(graphics);
            return;
        }
        for (int countComponents = ((Container)component).countComponents(), i = 0; i < countComponents; ++i) {
            final Component component2 = ((Container)component).getComponent(i);
            if (component2.isVisible()) {
                final Point location = component2.location();
                Dimension size2 = component2.size();
                if (b) {
                    location.x = (int)NFUtil.rint(location.x * 0.75);
                    location.y = (int)NFUtil.rint(location.y * 0.75);
                    size2 = new Dimension((int)NFUtil.rint(size2.width * 0.75), (int)NFUtil.rint(size2.height * 0.75));
                }
                final Graphics create = graphics.create(location.x, location.y, size2.width, size2.height);
                create.clipRect(0, 0, size2.width, size2.height);
                compPrint(create, component2, b);
                create.dispose();
            }
        }
        if (component instanceof NFMultiSash) {
            ((NFMultiSash)component).print(graphics, size);
        }
    }
    
    public void addChart(final NFCdf nfCdf) {
        this.addChart(nfCdf.name, nfCdf.type, nfCdf.width, nfCdf.height, null);
    }
    
    public void addChart(final String s, final int n, final int n2, final int n3) {
        this.addChart(s, n, n2, n3, null);
    }
    
    public void addChart(final NFChart nfChart) {
        final NFCdf cdf = nfChart.getCdf();
        this.addChart(cdf.name, cdf.type, cdf.width, cdf.height, nfChart);
    }
    
    public void addChart(final String s, final int n, final int n2, final int n3, final NFChart nfChart) {
        if (this.charts == null) {
            this.charts = new Vector();
        }
        try {
            this.createChart(s, n, nfChart);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
        if (!s.equals("BREAK") && n != -2) {
            this.layoutCharts();
        }
    }
    
    public void addCharts(final Vector vector) {
        if (this.charts == null) {
            this.charts = new Vector();
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFChart nfChart = vector.elementAt(i);
            final NFCdf cdf = nfChart.getCdf();
            try {
                this.createChart(cdf.name, cdf.type, nfChart);
            }
            catch (Exception ex) {
                NFParam.printError(ex);
            }
        }
        this.layoutCharts();
    }
    
    public void addChart(final NFMultiChartApp nfMultiChartApp) {
        if (this.charts == null) {
            this.charts = new Vector();
        }
        for (int size = nfMultiChartApp.charts.size(), i = 0; i < size; ++i) {
            final NFChart nfChart = nfMultiChartApp.charts.elementAt(i);
            final NFCdf cdf = nfChart.getCdf();
            try {
                this.createChart(cdf.name, cdf.type, nfChart);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.layoutCharts();
    }
    
    public void addBreak() {
        final NFCdf nfCdf = new NFCdf();
        nfCdf.name = "BREAK";
        nfCdf.type = -2;
        nfCdf.width = 0;
        nfCdf.height = 0;
        this.addChart(nfCdf);
    }
    
    public void insertChart(final NFChart nfChart, final int n) {
        final NFCdf cdf = nfChart.getCdf();
        this.insertChart(cdf.name, cdf.type, cdf.width, cdf.height, nfChart, n);
    }
    
    public void insertChart(final String name, final int type, final int width, final int height, final NFChart nfChart, final int n) {
        if (this.charts == null) {
            this.charts = new Vector();
        }
        final NFCdf nfCdf = new NFCdf();
        nfCdf.name = name;
        nfCdf.type = type;
        nfCdf.width = width;
        nfCdf.height = height;
        try {
            this.createChart(name, type, nfChart);
            this.charts.removeElement(nfChart);
            this.charts.insertElementAt(nfChart, n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            NFDebug.print("NFMultiChartApp: Tried to insert a chart at invalid index: " + ex);
        }
        catch (Exception ex2) {
            NFParam.printError(ex2);
        }
        this.layoutCharts();
    }
    
    public void removeChart(final String s) {
        this.removeChart(this.getChart(s));
    }
    
    public void removeChart(final NFChart nfChart) {
        if (nfChart == null) {
            return;
        }
        this.g.remove(nfChart.getCdf().name);
        this.charts.removeElement(nfChart);
        this.a();
        this.layoutCharts();
    }
    
    public void removeAllCharts() {
        this.removeAllCharts(true);
    }
    
    public void removeAllCharts(final boolean b) {
        this.g.clear();
        if (this.charts != null) {
            this.charts.removeAllElements();
        }
        if (b) {
            this.layoutCharts();
        }
    }
    
    private void a() {
        int n = 0;
        final Enumeration<NFChart> elements = (Enumeration<NFChart>)this.charts.elements();
        while (elements.hasMoreElements()) {
            final NFChart nextElement = elements.nextElement();
            if (nextElement instanceof NFChart) {
                final NFChart nfChart = nextElement;
                final NFCdf cdf = nfChart.getCdf();
                if (cdf.type == -2 || cdf.name.equals("BREAK")) {
                    if (n != 0) {
                        this.charts.removeElement(nfChart);
                    }
                    else {
                        n = 1;
                    }
                }
                else {
                    n = 0;
                }
            }
        }
        if (this.charts.size() == 0) {
            return;
        }
        if (this.charts.lastElement() instanceof NFChart) {
            final NFChart nfChart2 = this.charts.lastElement();
            final NFCdf cdf2 = nfChart2.getCdf();
            if (cdf2.type == -2 || cdf2.name.equals("BREAK")) {
                this.charts.removeElement(nfChart2);
            }
        }
    }
    
    public void replaceChart(final NFChart nfChart, final int n) {
        final NFCdf cdf = nfChart.getCdf();
        this.replaceChart(cdf.name, cdf.type, cdf.width, cdf.height, nfChart, n);
    }
    
    public void replaceChart(final String s, final int n, final int n2, final int n3, final NFChart nfChart, final int n4) {
        if (this.charts == null || this.charts.size() <= n4) {
            return;
        }
        this.replaceChart(this.charts.elementAt(n4).getCdf().name, s, n, n2, n3, nfChart);
    }
    
    public void replaceChart(final String s, final String s2, final int n, final int n2, final int n3, final NFChart nfChart) {
        if (this.charts == null) {
            return;
        }
        final NFChart chart = this.getChart(s);
        this.g.remove(s);
        try {
            this.createChart(s2, n, nfChart);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
        if (chart == null) {
            return;
        }
        final int lastIndex = this.charts.lastIndexOf(chart);
        this.charts.removeElementAt(this.charts.size() - 1);
        this.charts.setElementAt(nfChart, lastIndex);
        this.layoutCharts();
    }
    
    public void enableDrag(final boolean l) {
        this.l = l;
    }
    
    public void setDragImageSize(final Dimension k) {
        this.k = k;
    }
    
    public void useImage(final boolean n) {
        this.n = n;
    }
    
    public void createChart(final String s, final int n) throws NFParamException {
        this.createChart(s, n, null);
    }
    
    public void createChart(final String s, int n, NFChart chart) throws NFParamException {
        if (s.equals("BREAK")) {
            n = -2;
            final NFCdf cdf = new NFCdf();
            cdf.name = s;
            cdf.type = n;
            chart = new NFChart();
            chart.setCdf(cdf);
        }
        final int n2 = this.charts.size() + 1;
        if (chart == null) {
            final NFCdf nfCdf = new NFCdf();
            nfCdf.name = s;
            nfCdf.type = n;
            final NFContext nfContext = new NFContext(super.context.getDocumentBase(), super.context.getCodeBase());
            nfContext.setApplet(this);
            chart = NFChart.getChart(nfCdf, nfContext, false);
            if (chart == null) {
                throw new NFParamException("Chart" + n2 + " type is not defined");
            }
            chart.setLoadAppletParamsOnInit(false);
            chart.init();
            if (!this.h) {
                chart.start();
            }
        }
        this.charts.addElement(chart);
        this.g.put(s, chart);
    }
    
    private void a(final Vector vector) {
        if (this.charts == null) {
            this.charts = new Vector();
        }
        else {
            this.charts.removeAllElements();
        }
        this.g.clear();
        for (int i = 0; i < vector.size(); ++i) {
            final Vector<String> vector2 = vector.elementAt(i);
            if (vector2 != null) {
                if (vector2.size() != 0) {
                    final String name = vector2.elementAt(0);
                    final int a = a(vector2.elementAt(1));
                    final int intValue = ((Number)vector2.elementAt(2)).intValue();
                    final int intValue2 = ((Number)vector2.elementAt(3)).intValue();
                    final NFCdf nfCdf = new NFCdf();
                    nfCdf.name = name;
                    nfCdf.type = a;
                    nfCdf.width = intValue;
                    nfCdf.height = intValue2;
                    if (nfCdf.type == -1) {
                        try {
                            if (nfCdf.name.toLowerCase().endsWith(".gif")) {
                                final NFChart nfChart = (NFChart)Class.forName("chartworks.cv.NFImageChart").newInstance();
                            }
                            else if (nfCdf.name.toLowerCase().endsWith(".htm") || nfCdf.name.toLowerCase().endsWith(".html")) {
                                final NFChart nfChart2 = (NFChart)Class.forName("chartworks.cv.NFHtmlPage").newInstance();
                            }
                            else {
                                NFDebug.print("Unknown chart type: " + nfCdf.type + " for url: " + nfCdf.url);
                            }
                        }
                        catch (Exception ex) {
                            NFDebug.print("Exception creating chart for: " + nfCdf.name + " exception: " + ex);
                        }
                    }
                    else {
                        try {
                            final NFContext nfContext = new NFContext(super.context.getDocumentBase(), super.context.getCodeBase());
                            nfContext.setApplet(this);
                            final NFChart chart = NFChart.getChart(nfCdf, nfContext, false);
                            this.charts.addElement(chart);
                            this.g.put(nfCdf.name, chart);
                        }
                        catch (Exception ex2) {
                            NFDebug.print("unknown chart type: " + nfCdf.type);
                        }
                    }
                }
            }
        }
    }
    
    private static int a(final String s) {
        if (s.equalsIgnoreCase("BREAK")) {
            return -2;
        }
        if (s.equalsIgnoreCase("DEFAULT")) {
            return -1;
        }
        for (int i = 0; i < NFGlobal.classSymbol.length; ++i) {
            if (s.equalsIgnoreCase(NFGlobal.classSymbol[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public void releaseCharts() {
        if (this.b == null) {
            return;
        }
        this.b.removeAll();
    }
    
    public void layout() {
        super.layout();
        if (this.isVisible()) {
            this.layoutCharts();
        }
    }
    
    public void layoutCharts() {
        if (this.charts == null || this.charts.size() == 0) {
            if (this.b != null) {
                this.b.removeAll();
                this.mc.layout();
                super.layout();
            }
            return;
        }
        if (this.o == null) {
            this.o = new Hashtable();
        }
        else {
            this.o.clear();
        }
        final int size = this.charts.size();
        final int[] array = new int[size];
        final int[] array2 = new int[size];
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < size; ++i) {
            array[i] = (array2[i] = 0);
        }
        int n3 = 0;
        int n4 = 0;
        for (int j = 0; j < size; ++j) {
            final NFCdf cdf = this.charts.elementAt(j).getCdf();
            if (cdf.width <= 0) {
                cdf.width = 1;
            }
            if (cdf.height <= 0) {
                cdf.height = 1;
            }
            if (cdf.name.equals("BREAK") || cdf.type == -2) {
                if (n4 != 0) {
                    if (this.a == 1) {
                        n += array[n3];
                    }
                    else {
                        n2 += array2[n3];
                    }
                    ++n3;
                    n4 = 0;
                }
            }
            else {
                if (this.a == 1) {
                    if (cdf.width > array[n3]) {
                        array[n3] = cdf.width;
                    }
                    final int[] array3 = array2;
                    final int n5 = n3;
                    array3[n5] += cdf.height;
                }
                else {
                    if (cdf.height > array2[n3]) {
                        array2[n3] = cdf.height;
                    }
                    final int[] array4 = array;
                    final int n6 = n3;
                    array4[n6] += cdf.width;
                }
                n4 = 1;
            }
        }
        if (this.a == 1) {
            n += array[n3];
        }
        else {
            n2 += array2[n3];
        }
        int n7 = 0;
        int n8 = 0;
        switch (this.a) {
            case 2: {
                n7 = 1;
                n8 = 2;
                break;
            }
            case 1: {
                n7 = 2;
                n8 = 1;
                break;
            }
        }
        boolean b = false;
        if (this.b != null) {
            b = true;
            this.mc.remove(this.b);
            this.b = null;
        }
        (this.b = new NFMultiSash(n7)).addObserver(this);
        this.b.setSashWidth(this.d);
        this.b.enableDrag(this.l);
        this.b.setDragImageSize(this.k);
        this.mc.add("Center", this.b);
        final Dimension size2 = this.size();
        NFSashPanel nfSashPanel = null;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        for (int k = 0; k < size; ++k) {
            final NFChart nfChart = this.charts.elementAt(k);
            final NFCdf cdf2 = nfChart.getCdf();
            if (cdf2.width <= 0) {
                cdf2.width = 1;
            }
            if (cdf2.height <= 0) {
                cdf2.height = 1;
            }
            if (cdf2.name.equals("BREAK") || cdf2.type == -2) {
                ++n10;
                if (n11 != 0) {
                    ++n9;
                    if (nfSashPanel != null) {
                        nfSashPanel.layout();
                    }
                    nfSashPanel = null;
                    n11 = 0;
                }
            }
            else {
                if (nfSashPanel == null) {
                    nfSashPanel = new NFSashPanel(n8);
                    nfSashPanel.enable(this.c);
                    nfSashPanel.setWidth(this.d);
                    if (this.a == 1) {
                        nfSashPanel.resize(size2.width * array[n9] / n, size2.height);
                    }
                    else {
                        nfSashPanel.resize(size2.width, size2.height * array2[n9] / n2);
                    }
                    this.b.addSashPanel(nfSashPanel);
                }
                if (nfChart != null) {
                    nfChart.show();
                }
                ++n10;
                int n12;
                int n13;
                if (this.a == 1) {
                    n12 = size2.width * array[n9] / n;
                    n13 = size2.height * cdf2.height / array2[n9];
                }
                else {
                    n12 = size2.width * cdf2.width / array[n9];
                    n13 = size2.height * array2[n9] / n2;
                }
                if (this.n) {
                    final Image drawImage = nfChart.drawImage(n12, n13);
                    final NFImageCanvas nfImageCanvas = new NFImageCanvas(this);
                    nfImageCanvas.setImage(drawImage);
                    nfImageCanvas.setJustify("Fill");
                    nfImageCanvas.resize(n12, n13);
                    this.o.put(nfChart, nfImageCanvas);
                    nfSashPanel.add(nfImageCanvas);
                }
                else {
                    nfChart.resize(n12, n13);
                    nfSashPanel.add(nfChart);
                }
                n11 = 1;
            }
        }
        if (nfSashPanel != null) {
            nfSashPanel.layout();
        }
        this.mc.show();
        if (b) {
            super.layout();
            this.mc.layout();
            this.b.layout();
        }
        this.updateChartsParam();
    }
    
    public Enumeration getChartNames() {
        return this.g.keys();
    }
    
    public NFChart getChart(final String s) {
        return this.g.get(s);
    }
    
    public NFChart getChartAt(int n) {
        for (int i = 0; i < this.charts.size(); ++i) {
            final Object element = this.charts.elementAt(i);
            if (element instanceof NFChart) {
                final NFChart nfChart = (NFChart)element;
                final NFCdf cdf = nfChart.getCdf();
                if (cdf.type != -2) {
                    if (!cdf.name.equals("BREAK")) {
                        if (--n < 0) {
                            return nfChart;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public void adoptLayout(final NFMultiChartApp nfMultiChartApp) {
        this.charts.removeAllElements();
        for (int i = 0; i < nfMultiChartApp.charts.size(); ++i) {
            final NFChart nfChart = nfMultiChartApp.charts.elementAt(i);
            nfChart.getCdf();
            if (nfChart != null) {
                this.charts.addElement(nfChart);
            }
        }
        this.setModified(true);
        this.layoutCharts();
        this.validate();
    }
    
    public void paramChanged(final Object o, final String s, final Object o2) {
        if (s.equals("START")) {
            this.start();
        }
        else if (s.equals("STOP")) {
            this.stop();
        }
        else if (s.equals("LOADPARAMS")) {
            try {
                this.loadParams((NFParam)o2);
            }
            catch (Exception ex) {
                NFDebug.print("NFMultiChartApp: Error loading parameters: " + ex);
                ex.printStackTrace();
            }
        }
    }
    
    public void start() {
        if (this.charts == null) {
            return;
        }
        for (int i = 0; i < this.charts.size(); ++i) {
            if (this.charts.elementAt(i) instanceof NFChart) {
                final NFChart nfChart = this.charts.elementAt(i);
                if (nfChart != null) {
                    nfChart.start();
                }
            }
        }
    }
    
    public void stop() {
        if (this.charts == null) {
            return;
        }
        for (int i = 0; i < this.charts.size(); ++i) {
            if (this.charts.elementAt(i) instanceof NFChart) {
                final NFChart nfChart = this.charts.elementAt(i);
                if (nfChart != null) {
                    nfChart.stop();
                }
            }
        }
    }
    
    public void loadParams(final NFParam j) throws Exception {
        this.j = j;
        boolean b = false;
        if (j.changed("Charts")) {
            this.a((Vector)j.get("Charts"));
            b = true;
        }
        if (j.changed("Layout")) {
            final Vector vector = (Vector)j.get("Layout");
            if (vector != null && vector.size() > 0) {
                this.a = vector.elementAt(0).intValue();
                b = true;
            }
        }
        if (j.changed("Sash")) {
            final Vector vector2 = (Vector)j.get("Sash");
            if (vector2 != null && vector2.size() > 0) {
                this.e = vector2.elementAt(0);
                this.d = ((Number)vector2.elementAt(1)).intValue();
                this.c = (((Number)vector2.elementAt(2)).intValue() == 2);
                b = true;
            }
        }
        this.mc.setBackground(this.e);
        if (b) {
            this.layoutCharts();
        }
        if (this.charts == null || this.charts.size() == 0) {
            return;
        }
        int n = 0;
        for (int i = 0; i < this.charts.size(); ++i) {
            final NFChart nfChart = this.charts.elementAt(i);
            final NFCdf cdf = nfChart.getCdf();
            if (cdf.type != -2) {
                if (!cdf.name.equals("BREAK")) {
                    boolean b2 = false;
                    ++n;
                    if (j.changed("ChartURL" + n)) {
                        final String s = (String)j.get("ChartURL" + n);
                        if (s != null && s.length() > 0) {
                            try {
                                final URL fileURL = NFUtil.getFileURL(s, super.context);
                                final NFContext nfContext = new NFContext(fileURL, super.context.getCodeBase());
                                nfContext.setApplet(this);
                                nfContext.mergeAuthInfo(super.context);
                                nfChart.setStub(nfContext);
                                nfChart.setContext(nfContext);
                                nfChart.loadParams(fileURL);
                                b2 = true;
                            }
                            catch (Exception ex2) {
                                throw new NFParamException("ChartURL" + n + ": Invalid URL <" + s + ">");
                            }
                        }
                    }
                    if (j.changed("ChartScript" + n)) {
                        final String s2 = (String)j.get("ChartScript" + n);
                        if (s2 != null && s2.length() > 0) {
                            try {
                                try {
                                    final NFContext nfContext2 = new NFContext(super.context.getDocumentBase(), super.context.getCodeBase());
                                    nfContext2.setApplet(this);
                                    nfContext2.mergeAuthInfo(super.context);
                                    nfChart.setStub(nfContext2);
                                    nfChart.setContext(nfContext2);
                                }
                                catch (Exception ex3) {}
                                nfChart.loadParams(s2);
                                b2 = true;
                            }
                            catch (Exception ex) {
                                throw new NFParamException("ChartScript" + n + ": " + NFGraph.getErrorMsg(ex));
                            }
                        }
                    }
                    if (b2) {
                        nfChart.loadParams("Update");
                    }
                }
            }
        }
    }
    
    public void valueChanged(final Object o) {
        if (o == this.mc) {
            return;
        }
        this.charts.removeAllElements();
        new NFKeyValue().key = "Charts";
        final Vector vector = new Vector();
        final Vector managedComponents = this.b.getManagedComponents();
        for (int i = 0; i < managedComponents.size(); ++i) {
            final Vector managedComponents2 = managedComponents.elementAt(i).getManagedComponents();
            for (int j = 0; j < managedComponents2.size(); ++j) {
                if (managedComponents2.elementAt(j) instanceof NFChart) {
                    this.charts.addElement(managedComponents2.elementAt(j));
                }
                else {
                    final Enumeration<NFChart> keys = this.o.keys();
                    while (keys.hasMoreElements()) {
                        final NFChart nfChart = keys.nextElement();
                        if (this.o.get(nfChart) == managedComponents2.elementAt(j)) {
                            this.charts.addElement(nfChart);
                        }
                    }
                }
            }
            if (i + 1 < managedComponents.size()) {
                this.addBreak();
            }
        }
        this.layoutCharts();
        this.validate();
        this.setModified(true);
        this.mc.validate();
        this.mc.repaint();
        if (this.i != null) {
            this.i.buttonPressed(this, null);
        }
    }
    
    protected void updateChartsParam() {
        final NFKeyValue nfKeyValue = new NFKeyValue();
        nfKeyValue.key = "Charts";
        final Vector<Vector<String>> vector = new Vector<Vector<String>>();
        final Vector managedComponents = this.b.getManagedComponents();
        for (int i = 0; i < managedComponents.size(); ++i) {
            final Vector managedComponents2 = managedComponents.elementAt(i).getManagedComponents();
            for (int j = 0; j < managedComponents2.size(); ++j) {
                final NFChart element = managedComponents2.elementAt(j);
                NFCdf cdf = null;
                if (element instanceof NFChart) {
                    cdf = element.getCdf();
                }
                else if (element instanceof NFImageCanvas) {
                    final NFImageCanvas nfImageCanvas = (NFImageCanvas)element;
                    cdf = new NFCdf();
                    cdf.width = nfImageCanvas.size().width;
                    cdf.height = nfImageCanvas.size().height;
                }
                final Vector<String> vector2 = new Vector<String>();
                vector2.addElement(cdf.name);
                if (cdf.type != -1) {
                    vector2.addElement(NFGlobal.classSymbol[cdf.type]);
                }
                else {
                    vector2.addElement("DEFAULT");
                }
                vector2.addElement((String)new Integer(cdf.width));
                vector2.addElement((String)new Integer(cdf.height));
                vector.addElement(vector2);
            }
            final Vector<String> vector3 = new Vector<String>();
            vector3.addElement("BREAK");
            vector3.addElement("BREAK");
            vector3.addElement((String)new Integer(0));
            vector3.addElement((String)new Integer(0));
            vector.addElement(vector3);
        }
        vector.removeElement(vector.lastElement());
        try {
            nfKeyValue.value = this.j.toString("Charts", vector);
        }
        catch (NFParamException ex) {
            NFDebug.print("Error updating Charts Parameter: " + ex);
        }
        for (int k = 0; k < super.cdf.script.size(); ++k) {
            if (((NFKeyValue)super.cdf.script.elementAt(k)).key.equals("Charts")) {
                super.cdf.script.setElementAt(nfKeyValue, k);
            }
        }
    }
    
    private void b() {
        for (int i = 0; i < this.charts.size(); ++i) {
            final Object element = this.charts.elementAt(i);
            if (element instanceof NFChart) {
                final NFChart nfChart = (NFChart)element;
                final NFCdf cdf = nfChart.getCdf();
                if (cdf.type != -2) {
                    if (cdf.name != "BREAK") {
                        if (this.n) {
                            final NFImageCanvas nfImageCanvas = this.o.get(nfChart);
                            if (cdf.width != nfImageCanvas.size().width || cdf.height != nfImageCanvas.size().height) {
                                nfImageCanvas.setImage(nfChart.drawImage(nfImageCanvas.size().width, nfImageCanvas.size().height));
                                nfImageCanvas.setJustify("Fill");
                                nfImageCanvas.resize(nfImageCanvas.size().width, nfImageCanvas.size().height);
                                cdf.width = nfImageCanvas.size().width;
                                cdf.height = nfImageCanvas.size().height;
                            }
                        }
                        else {
                            cdf.width = nfChart.size().width;
                            cdf.height = nfChart.size().height;
                        }
                    }
                }
            }
        }
        this.updateChartsParam();
    }
    
    public void buttonPressed(final Object o, final String s) {
        if (o != this.b || !s.equals("SASHDRAG")) {
            return;
        }
        this.b();
        this.setModified(true);
    }
}