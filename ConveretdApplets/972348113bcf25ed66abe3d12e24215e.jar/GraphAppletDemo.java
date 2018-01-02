import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.StringTokenizer;
import com.jinsight.jetchart.Note;
import com.jinsight.jetchart.AbstractSerie;
import com.jinsight.jetchart.GraphException;
import com.jinsight.jetchart.StackBarSerie;
import com.jinsight.jetchart.GraphSerie;
import com.jinsight.jetchart.OHLCSerie;
import com.jinsight.jetchart.PointSerie;
import com.jinsight.jetchart.AreaSerie;
import com.jinsight.jetchart.BarSerie;
import com.jinsight.jetchart.ImageSerie;
import com.jinsight.jetchart.LineSerie;
import com.jinsight.jetchart.YAxis;
import com.jinsight.jetchart.XAxis;
import com.jinsight.jetchart.ToolTip;
import com.jinsight.jetchart.Legend;
import com.jinsight.jetchart.Grid;
import java.util.Locale;
import java.awt.Font;
import java.awt.Label;
import java.awt.GridLayout;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Image;
import java.awt.Color;
import com.jinsight.jetchart.Graph;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphAppletDemo extends Applet implements ActionListener, Runnable, MouseMotionListener, MouseListener
{
    private Graph graph;
    private boolean showBorder;
    private Color borderColor;
    private Color foreground;
    private Color background;
    Image img;
    int posXString;
    int stringBannerWidth;
    private String paramValue;
    private String banner;
    private Button nagButton;
    private boolean buttonPressed;
    private Panel nagScreenPanel;
    Thread bannerThread;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.createNagScreen();
        this.graph = new Graph();
        this.getParameters();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void start() {
        this.posXString = this.getSize().width;
        if (this.buttonPressed) {
            if (this.bannerThread == null) {
                this.bannerThread = new Thread(this);
            }
            this.bannerThread.start();
        }
    }
    
    public void stop() {
        this.bannerThread = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.bannerThread) {
            try {
                Thread.sleep(20L);
                --this.posXString;
                if (this.stringBannerWidth != 0 && this.posXString == -this.stringBannerWidth) {
                    this.posXString = this.getSize().width;
                }
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.remove(this.nagScreenPanel);
        this.remove(this.nagButton);
        this.add("Center", this.graph);
        this.validate();
        this.buttonPressed = true;
        (this.bannerThread = new Thread(this)).start();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int n = this.showBorder ? 2 : 0;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= n && y >= this.getSize().height - 14 && y <= this.getSize().height - 2) {
            this.foreground = Color.black;
            this.background = Color.yellow;
        }
        else {
            this.foreground = Color.yellow;
            this.background = Color.black;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.foreground = Color.yellow;
        this.background = Color.black;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int n = this.showBorder ? 2 : 0;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= n && y >= this.getSize().height - 14 && y <= this.getSize().height - 2) {
            final AppletContext appletContext = this.getAppletContext();
            try {
                appletContext.showDocument(new URL("http://www.jinsight.com"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void createNagScreen() {
        this.nagScreenPanel = new Panel(new GridLayout(3, 1));
        final Label label = new Label("The JetChart Library - version 1.5", 1);
        label.setFont(new Font("SansSerif", 1, 14));
        label.setForeground(Color.blue);
        final Label label2 = new Label("GraphApplet Demo", 1);
        label2.setFont(new Font("SansSerif", 1, 12));
        label2.setForeground(Color.red);
        final Label label3 = new Label("Press the 'Load chart' button below to view chart.", 1);
        this.nagScreenPanel.add(label);
        this.nagScreenPanel.add(label2);
        this.nagScreenPanel.add(label3);
        (this.nagButton = new Button("Load chart")).addActionListener(this);
        this.add("Center", this.nagScreenPanel);
        this.add("South", this.nagButton);
    }
    
    private void getParameters() {
        this.getAppletParameters();
        this.getGraphParameters();
        this.getXAxisParameters();
        this.getYAxisParameters();
        this.getGridParameters();
        this.getLegendParameters();
        this.getToolTipParameters();
        for (int i = 1; i <= 20; ++i) {
            final String string = Integer.toString(i);
            final String parameter = this.getParameter("serie" + i);
            if (parameter != null) {
                this.getGraphSerieParameters(string, parameter);
            }
        }
        for (int j = 1; j <= 20; ++j) {
            final String string2 = Integer.toString(j);
            final String parameter2 = this.getParameter("note" + j);
            if (parameter2 != null) {
                this.getNoteParameters(string2, this.tokenizeParam(parameter2));
            }
        }
    }
    
    private void getAppletParameters() {
        this.paramValue = this.getParameter("showborder");
        if (this.paramValue != null) {
            this.showBorder = this.paramValue.trim().toLowerCase().equals("yes");
        }
        this.paramValue = this.getParameter("bordercolor");
        if (this.paramValue != null) {
            this.borderColor = Color.decode("#" + this.paramValue);
        }
    }
    
    private void getGraphParameters() {
        this.paramValue = this.getParameter("labels");
        if (this.paramValue != null) {
            this.graph.setLabels(this.tokenizeParam(this.paramValue));
        }
        this.paramValue = this.getParameter("color");
        if (this.paramValue != null) {
            this.graph.setBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("gradientcolors");
        if (this.paramValue != null) {
            this.graph.setGradientColors(Color.decode("#" + this.paramValue.substring(0, this.paramValue.indexOf(",")).trim()), Color.decode("#" + this.paramValue.substring(this.paramValue.indexOf(",") + 1).trim()));
        }
        this.paramValue = this.getParameter("gradientorientation");
        if (this.paramValue != null) {
            this.graph.setGradientOrientation(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("topmargin");
        if (this.paramValue != null) {
            this.graph.setTopMargin(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("rightmargin");
        if (this.paramValue != null) {
            this.graph.setRightMargin(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("bottommargin");
        if (this.paramValue != null) {
            this.graph.setBottomMargin(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("leftmargin");
        if (this.paramValue != null) {
            this.graph.setLeftMargin(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("draggable");
        if (this.paramValue != null) {
            this.graph.setDraggingEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("3D");
        if (this.paramValue != null) {
            this.graph.set3DEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("depth");
        if (this.paramValue != null) {
            final int intValue = Integer.valueOf(this.paramValue);
            this.graph.setHDepth(intValue);
            this.graph.setVDepth(intValue);
        }
        this.paramValue = this.getParameter("horizontal");
        if (this.paramValue != null) {
            this.graph.setHorizontalGraphEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("showValues");
        if (this.paramValue != null) {
            this.graph.setShowValuesEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("showlabels");
        if (this.paramValue != null) {
            this.graph.setShowLabelsEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("3DSeriesInLine");
        if (this.paramValue != null) {
            this.graph.set3DSeriesInLineEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("title");
        if (this.paramValue != null) {
            this.graph.setTitle(this.getTitle(this.paramValue));
        }
        this.paramValue = this.getParameter("titleColor");
        if (this.paramValue != null) {
            this.graph.setTitleForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("titleFont");
        if (this.paramValue != null) {
            this.graph.setTitleFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("valueFormat");
        if (this.paramValue != null) {
            this.graph.setValueFormat(this.paramValue);
        }
        this.paramValue = this.getParameter("showgrid");
        if (this.paramValue != null) {
            this.graph.setGridEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("showLegend");
        if (this.paramValue != null) {
            this.graph.setLegendEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("showToolTip");
        if (this.paramValue != null) {
            this.graph.setToolTipEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("tooltipdelay");
        if (this.paramValue != null) {
            this.graph.setToolTipDelay(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("tooltiptimeroff");
        if (this.paramValue != null) {
            this.graph.setToolTipTimerEnabled(!this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("showmultipletooltips");
        if (this.paramValue != null) {
            this.graph.setMultipleToolTipsEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("labelstitle");
        if (this.paramValue != null) {
            this.graph.setLabelsTitle(this.paramValue);
        }
        this.paramValue = this.getParameter("labelstitlecolor");
        if (this.paramValue != null) {
            this.graph.setLabelsTitleForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("labelstitlebackcolor");
        if (this.paramValue != null) {
            this.graph.setLabelsTitleBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("labelstitlefont");
        if (this.paramValue != null) {
            this.graph.setLabelsTitleFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("valuestitle");
        if (this.paramValue != null) {
            this.graph.setValuesTitle(this.paramValue);
        }
        this.paramValue = this.getParameter("valuestitlecolor");
        if (this.paramValue != null) {
            this.graph.setValuesTitleForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("valuestitlebackcolor");
        if (this.paramValue != null) {
            this.graph.setValuesTitleBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("valuestitlefont");
        if (this.paramValue != null) {
            this.graph.setValuesTitleFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("startonaxis");
        if (this.paramValue != null) {
            this.graph.setStartOnAxisEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("startdate");
        if (this.paramValue != null) {
            this.graph.setStartDate(this.paramValue);
        }
        this.paramValue = this.getParameter("labelsections");
        if (this.paramValue != null) {
            this.graph.setLabelSections(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("locale");
        if (this.paramValue != null) {
            final String[] tokenizeParam = this.tokenizeParam(this.paramValue);
            this.graph.setLocale(new Locale(tokenizeParam[0], tokenizeParam[1], ""));
        }
        this.paramValue = this.getParameter("dateincrement");
        if (this.paramValue != null) {
            final String[] tokenizeParam2 = this.tokenizeParam(this.paramValue);
            if (tokenizeParam2.length == 2) {
                this.graph.setDateIncrement(Integer.valueOf(tokenizeParam2[0]), Integer.valueOf(tokenizeParam2[1]));
            }
            else if (tokenizeParam2.length == 1) {
                this.graph.setDateIncrement(Integer.valueOf(tokenizeParam2[0]));
            }
        }
        this.paramValue = this.getParameter("tooltiptype");
        if (this.paramValue != null) {
            this.graph.setToolTipType(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("autorangeoff");
        if (this.paramValue != null) {
            this.graph.setAutoRangeOff(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("rangeincrement");
        if (this.paramValue != null) {
            this.graph.setRangeIncrement(Double.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("minrangevalue");
        if (this.paramValue != null) {
            this.graph.setMinRangeValue(Double.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("maxrangevalue");
        if (this.paramValue != null) {
            this.graph.setMaxRangeValue(Double.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("showlabelmark");
        if (this.paramValue != null) {
            this.graph.setLabelMarkEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("showvaluemark");
        if (this.paramValue != null) {
            this.graph.setValueMarkEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("autolabelspacingoff");
        if (this.paramValue != null) {
            this.graph.setAutoLabelSpacingEnabled(!this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("verticallabelson");
        if (this.paramValue != null) {
            this.graph.setVerticalLabelsEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("verticallabelsopaque");
        if (this.paramValue != null) {
            this.graph.setVerticalLabelsOpacityEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("axistitleopaque");
        if (this.paramValue != null) {
            this.graph.setAxisTitleOpacityEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
    }
    
    private void getGridParameters() {
        final Grid grid = this.graph.getGrid();
        this.paramValue = this.getParameter("crossedgrid");
        if (this.paramValue != null) {
            grid.setCrossedLinesEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("gridcolor");
        if (this.paramValue != null) {
            grid.setColor(Color.decode("#" + this.paramValue));
        }
    }
    
    private void getLegendParameters() {
        final Legend legend = this.graph.getLegend();
        this.paramValue = this.getParameter("legendfont");
        if (this.paramValue != null) {
            legend.setFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("legendOrientation");
        if (this.paramValue != null) {
            legend.setOrientation(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("legendPosition");
        if (this.paramValue != null) {
            legend.setPosition(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("legendBackColor");
        if (this.paramValue != null) {
            legend.setBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("legendColor");
        if (this.paramValue != null) {
            legend.setForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("legendHGap");
        if (this.paramValue != null) {
            legend.setHGap(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("legendVGap");
        if (this.paramValue != null) {
            legend.setVGap(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("legendOpaque");
        if (this.paramValue != null) {
            legend.setOpacityEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("legendautoorientationoff");
        if (this.paramValue != null) {
            legend.setAutoOrientationEnabled(!this.paramValue.trim().toLowerCase().equals("yes"));
        }
    }
    
    private void getToolTipParameters() {
        final ToolTip toolTip = this.graph.getToolTip();
        this.paramValue = this.getParameter("tooltiplabel");
        if (this.paramValue != null) {
            this.graph.setToolTipLabel(this.paramValue);
        }
        this.paramValue = this.getParameter("tooltipfont");
        if (this.paramValue != null) {
            toolTip.setFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("tooltipcolor");
        if (this.paramValue != null) {
            toolTip.setForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("tooltipbackcolor");
        if (this.paramValue != null) {
            toolTip.setBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("tooltipopaque");
        if (this.paramValue != null) {
            toolTip.setOpacityEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("tooltippointer");
        if (this.paramValue != null) {
            toolTip.setPointerType(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("tooltippointercolor");
        if (this.paramValue != null) {
            toolTip.setPointerColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("tooltipgap");
        if (this.paramValue != null) {
            toolTip.setGap(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("showtooltipborder");
        if (this.paramValue != null) {
            toolTip.setBorderEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
    }
    
    private void getXAxisParameters() {
        final XAxis xAxis = this.graph.getXAxis();
        this.paramValue = this.getParameter("xaxisfont");
        if (this.paramValue != null) {
            xAxis.setFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("xaxiscolor");
        if (this.paramValue != null) {
            xAxis.setColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("xaxis3dcolor");
        if (this.paramValue != null) {
            xAxis.setFill3DColor(Color.decode("#" + this.paramValue));
        }
    }
    
    private void getYAxisParameters() {
        final YAxis yAxis = this.graph.getYAxis();
        this.paramValue = this.getParameter("yaxisfont");
        if (this.paramValue != null) {
            yAxis.setFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("yaxiscolor");
        if (this.paramValue != null) {
            yAxis.setColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("yaxis3dcolor");
        if (this.paramValue != null) {
            yAxis.setFill3DColor(Color.decode("#" + this.paramValue));
        }
    }
    
    private void getGraphSerieParameters(final String s, final String s2) {
        final String[] initialSerieParameters = this.getInitialSerieParameters(s2);
        final String s3 = initialSerieParameters[0];
        final String s4 = initialSerieParameters[1];
        final Color decode = Color.decode("#" + initialSerieParameters[2]);
        double[] serieValues = null;
        this.paramValue = this.getParameter("serie" + s + "_values");
        if (this.paramValue != null) {
            serieValues = this.getSerieValues(this.paramValue);
        }
        final GraphSerie graphSerie = this.createGraphSerie(s3, s4, decode, serieValues);
        this.paramValue = this.getParameter("serie" + s + "_font");
        if (this.paramValue != null) {
            graphSerie.setFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("serie" + s + "_showmarklegend");
        if (this.paramValue != null) {
            graphSerie.setMarkLegendEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("serie" + s + "_marklegendcolor");
        if (this.paramValue != null) {
            graphSerie.setMarkLegendForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("serie" + s + "_marklegendbackcolor");
        if (this.paramValue != null) {
            graphSerie.setMarkLegendBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("serie" + s + "_marklegendopaque");
        if (this.paramValue != null) {
            graphSerie.setMarkLegendOpacityEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("serie" + s + "_highlighton");
        if (this.paramValue != null) {
            graphSerie.setHighlightEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("serie" + s + "_highlightcolor");
        if (this.paramValue != null) {
            graphSerie.setHighlightColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("serie" + s + "_startpoint");
        if (this.paramValue != null) {
            graphSerie.setStartPoint(Integer.valueOf(this.paramValue));
        }
        if (graphSerie instanceof LineSerie && !(graphSerie instanceof ImageSerie)) {
            this.getLineSerieParameters(graphSerie, s);
        }
        else if (graphSerie instanceof BarSerie) {
            this.getBarSerieParameters(graphSerie, s);
        }
        else if (graphSerie instanceof AreaSerie) {
            this.getAreaSerieParameters(graphSerie, s);
        }
        else if (graphSerie instanceof PointSerie) {
            this.getPointSerieParameters(graphSerie, s);
        }
        else if (graphSerie instanceof ImageSerie) {
            this.getImageSerieParameters(graphSerie, s);
        }
        else if (graphSerie instanceof OHLCSerie) {
            this.getOHLCSerieParameters(graphSerie, s);
        }
    }
    
    private GraphSerie createGraphSerie(final String s, final String s2, final Color color, final double[] array) {
        GraphSerie graphSerie = null;
        final String lowerCase = s.toLowerCase();
        if (lowerCase.equals("bar")) {
            graphSerie = new BarSerie(array, s2);
        }
        else if (lowerCase.equals("line")) {
            graphSerie = new LineSerie(array, s2);
        }
        else if (lowerCase.equals("area")) {
            graphSerie = new AreaSerie(array, s2);
        }
        else if (lowerCase.equals("stackbar")) {
            graphSerie = new StackBarSerie(array, s2);
        }
        else if (lowerCase.equals("point")) {
            graphSerie = new PointSerie(array, s2);
        }
        else if (lowerCase.equals("image")) {
            graphSerie = new ImageSerie(array);
        }
        else if (lowerCase.equals("ohlc")) {
            if (array.length / 4 * 4 != array.length) {
                throw new GraphException("Number of values must be a multiple of 4.");
            }
            final int n = array.length / 4;
            final double[][] array2 = new double[array.length / 4][4];
            int n2 = 0;
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = new double[] { array[n2], array[n2 + 1], array[n2 + 2], array[n2 + 3] };
                n2 += 4;
            }
            graphSerie = new OHLCSerie(array2, s2);
        }
        if (graphSerie != null) {
            graphSerie.setColor(color);
            this.graph.addSerie(graphSerie);
        }
        return graphSerie;
    }
    
    private String[] getInitialSerieParameters(final String s) {
        return this.tokenizeParam(s);
    }
    
    private void getLineSerieParameters(final GraphSerie graphSerie, final String s) {
        final LineSerie lineSerie = (LineSerie)graphSerie;
        final String string = "serie" + s + "_";
        this.paramValue = this.getParameter(string + "showmarks");
        if (this.paramValue != null) {
            lineSerie.setMarksEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter(string + "marksColor");
        if (this.paramValue != null) {
            lineSerie.setMarksColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter(string + "thickness");
        if (this.paramValue != null) {
            lineSerie.setThickness(Integer.valueOf(this.paramValue));
        }
    }
    
    private void getBarSerieParameters(final GraphSerie graphSerie, final String s) {
        final BarSerie barSerie = (BarSerie)graphSerie;
        this.paramValue = this.getParameter("serie" + s + "_width");
        if (this.paramValue != null) {
            if (!(barSerie instanceof StackBarSerie)) {
                barSerie.setWidth(Integer.valueOf(this.paramValue));
            }
            else {
                StackBarSerie.setStackBarWidth(Integer.valueOf(this.paramValue));
            }
        }
        this.paramValue = this.getParameter("serie" + s + "_colors");
        if (this.paramValue != null) {
            final String[] tokenizeParam = this.tokenizeParam(this.paramValue);
            final Color[] colors = new Color[tokenizeParam.length];
            for (int i = 0; i < tokenizeParam.length; ++i) {
                colors[i] = Color.decode("#" + tokenizeParam[i]);
            }
            barSerie.setColors(colors);
        }
    }
    
    private void getAreaSerieParameters(final GraphSerie graphSerie, final String s) {
        final AreaSerie areaSerie = (AreaSerie)graphSerie;
        this.paramValue = this.getParameter("serie" + s + "_showlines");
        if (this.paramValue != null) {
            areaSerie.setAreaLinesEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
    }
    
    private void getPointSerieParameters(final GraphSerie graphSerie, final String s) {
        final PointSerie pointSerie = (PointSerie)graphSerie;
        this.paramValue = this.getParameter("serie" + s + "_shape");
        if (this.paramValue != null) {
            pointSerie.setShape(Integer.valueOf(this.paramValue));
        }
    }
    
    private void getImageSerieParameters(final GraphSerie graphSerie, final String s) {
        this.paramValue = this.getParameter("serie" + s + "_images");
        final ImageSerie imageSerie = (ImageSerie)graphSerie;
        if (this.paramValue != null) {
            imageSerie.setImages(this.getImg(this.paramValue));
        }
    }
    
    private void getOHLCSerieParameters(final GraphSerie graphSerie, final String s) {
        final String string = "serie" + s + "_";
        final OHLCSerie ohlcSerie = (OHLCSerie)graphSerie;
        this.paramValue = this.getParameter(string + "type");
        if (this.paramValue != null) {
            ohlcSerie.setType(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter(string + "width");
        if (this.paramValue != null) {
            ohlcSerie.setWidth(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter(string + "dashsize");
        if (this.paramValue != null) {
            ohlcSerie.setDashSize(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter(string + "bearishcolor");
        if (this.paramValue != null) {
            ohlcSerie.setBearishColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter(string + "bullishcolor");
        if (this.paramValue != null) {
            ohlcSerie.setBullishColor(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter(string + "tooltiptext");
        if (this.paramValue != null) {
            ohlcSerie.setToolTipText(this.tokenizeParam(this.paramValue));
        }
        this.paramValue = this.getParameter(string + "linethickness");
        if (this.paramValue != null) {
            ohlcSerie.setLineThickness(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter(string + "showlinemarks");
        if (this.paramValue != null) {
            ohlcSerie.setMarksEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
    }
    
    private double[] getSerieValues(final String s) {
        final String[] tokenizeParam = this.tokenizeParam(s);
        final double[] array = new double[tokenizeParam.length];
        for (int i = 0; i < tokenizeParam.length; ++i) {
            array[i] = Double.valueOf(tokenizeParam[i]);
        }
        return array;
    }
    
    private Image[] getImg(final String s) {
        final String[] tokenizeParam = this.tokenizeParam(s);
        final Image[] array = new Image[tokenizeParam.length];
        for (int i = 0; i < tokenizeParam.length; ++i) {
            array[i] = this.getImage(this.getCodeBase(), tokenizeParam[i]);
        }
        return array;
    }
    
    private void getNoteParameters(final String s, final String[] array) {
        final Note note = new Note(array, 0, 0);
        this.paramValue = this.getParameter("note" + s + "_location");
        if (this.paramValue != null) {
            final String[] tokenizeParam = this.tokenizeParam(this.paramValue);
            note.setLocation(Integer.valueOf(tokenizeParam[0]), Integer.valueOf(tokenizeParam[1]));
        }
        this.paramValue = this.getParameter("note" + s + "_font");
        if (this.paramValue != null) {
            note.setFont(this.getFont(this.paramValue));
        }
        this.paramValue = this.getParameter("note" + s + "_color");
        if (this.paramValue != null) {
            note.setForeground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("note" + s + "_backcolor");
        if (this.paramValue != null) {
            note.setBackground(Color.decode("#" + this.paramValue));
        }
        this.paramValue = this.getParameter("note" + s + "_opaque");
        if (this.paramValue != null) {
            note.setOpacityEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("note" + s + "_showborder");
        if (this.paramValue != null) {
            note.setBorderEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.graph.addNote(note);
    }
    
    private String[] getTitle(final String s) {
        return this.tokenizeParam(s);
    }
    
    private Font getFont(final String s) {
        final String[] tokenizeParam = this.tokenizeParam(s);
        String s2 = null;
        int intValue = 0;
        int intValue2 = 0;
        for (int i = 0; i < tokenizeParam.length; ++i) {
            switch (i) {
                case 0: {
                    s2 = tokenizeParam[i];
                    break;
                }
                case 1: {
                    intValue = Integer.valueOf(tokenizeParam[i]);
                    break;
                }
                case 2: {
                    intValue2 = Integer.valueOf(tokenizeParam[i]);
                    break;
                }
            }
        }
        return new Font(s2, intValue2, intValue);
    }
    
    private String[] tokenizeParam(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreElements()) {
            array[n] = stringTokenizer.nextToken().trim();
            ++n;
        }
        return array;
    }
    
    public Insets getInsets() {
        if (this.showBorder) {
            return new Insets(2, 2, 15, 2);
        }
        return new Insets(0, 0, 15, 2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.showBorder) {
            graphics.setColor(this.borderColor);
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
            graphics.drawRect(1, 1, this.getSize().width - 3, this.getSize().height - 3);
        }
        final int n = this.getSize().width - (this.showBorder ? 4 : 2);
        final int n2 = 14;
        this.img = this.createImage(n, n2);
        final Graphics graphics2 = this.img.getGraphics();
        final FontMetrics fontMetrics = graphics2.getFontMetrics();
        final int n3 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        graphics2.setColor(this.background);
        graphics2.fillRect(0, 0, n, n2);
        graphics2.setColor(this.foreground);
        this.stringBannerWidth = fontMetrics.stringWidth(this.banner);
        graphics2.drawString(this.banner, this.posXString, (n2 - n3) / 2 + fontMetrics.getMaxAscent());
        graphics.drawImage(this.img, this.showBorder ? 2 : 0, this.getSize().height - 16, this);
    }
    
    public GraphAppletDemo() {
        this.showBorder = true;
        this.borderColor = Color.blue;
        this.foreground = Color.yellow;
        this.background = Color.black;
        this.banner = "The JetChart Library - A product from Jinsight Software Solutions - www.jinsight.com";
    }
}
