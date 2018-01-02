import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.StringTokenizer;
import com.jinsight.jetchart.SliceLegend;
import com.jinsight.jetchart.Note;
import com.jinsight.jetchart.Slice;
import com.jinsight.jetchart.AbstractSerie;
import com.jinsight.jetchart.PieSerie;
import com.jinsight.jetchart.ToolTip;
import com.jinsight.jetchart.Legend;
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

public class PieAppletDemo extends Applet implements ActionListener, Runnable, MouseMotionListener, MouseListener
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
        final Label label2 = new Label("PieApplet Demo", 1);
        label2.setFont(new Font("SansSerif", 1, 12));
        label2.setForeground(Color.red);
        final Label label3 = new Label("Press the 'Load chart' button below to view pie chart.", 1);
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
        this.getLegendParameters();
        this.getToolTipParameters();
        this.getPieSerieParameters();
        for (int i = 1; i <= 20; ++i) {
            final String string = Integer.toString(i);
            final String parameter = this.getParameter("note" + i);
            if (parameter != null) {
                this.getNoteParameters(string, this.tokenizeParam(parameter));
            }
        }
    }
    
    private void getAppletParameters() {
        final String parameter = this.getParameter("showborder");
        if (parameter != null) {
            this.showBorder = parameter.trim().toLowerCase().equals("yes");
        }
        final String parameter2 = this.getParameter("bordercolor");
        if (parameter2 != null) {
            this.borderColor = Color.decode("#" + parameter2);
        }
    }
    
    private void getGraphParameters() {
        final String parameter = this.getParameter("labels");
        if (parameter != null) {
            this.graph.setLabels(this.tokenizeParam(parameter));
        }
        final String parameter2 = this.getParameter("color");
        if (parameter2 != null) {
            this.graph.setBackground(Color.decode("#" + parameter2));
        }
        final String parameter3 = this.getParameter("gradientcolors");
        if (parameter3 != null) {
            this.graph.setGradientColors(Color.decode("#" + parameter3.substring(0, parameter3.indexOf(",")).trim()), Color.decode("#" + parameter3.substring(parameter3.indexOf(",") + 1).trim()));
        }
        final String parameter4 = this.getParameter("gradientorientation");
        if (parameter4 != null) {
            this.graph.setGradientOrientation(Integer.valueOf(parameter4));
        }
        final String parameter5 = this.getParameter("topmargin");
        if (parameter5 != null) {
            this.graph.setTopMargin(Integer.valueOf(parameter5));
        }
        final String parameter6 = this.getParameter("rightmargin");
        if (parameter6 != null) {
            this.graph.setRightMargin(Integer.valueOf(parameter6));
        }
        final String parameter7 = this.getParameter("bottommargin");
        if (parameter7 != null) {
            this.graph.setBottomMargin(Integer.valueOf(parameter7));
        }
        final String parameter8 = this.getParameter("leftmargin");
        if (parameter8 != null) {
            this.graph.setLeftMargin(Integer.valueOf(parameter8));
        }
        final String parameter9 = this.getParameter("draggable");
        if (parameter9 != null) {
            this.graph.setDraggingEnabled(parameter9.trim().toLowerCase().equals("yes"));
        }
        final String parameter10 = this.getParameter("3D");
        if (parameter10 != null) {
            this.graph.set3DEnabled(parameter10.trim().toLowerCase().equals("yes"));
        }
        final String parameter11 = this.getParameter("title");
        if (parameter11 != null) {
            this.graph.setTitle(this.getTitle(parameter11));
        }
        final String parameter12 = this.getParameter("titleColor");
        if (parameter12 != null) {
            this.graph.setTitleForeground(Color.decode("#" + parameter12));
        }
        final String parameter13 = this.getParameter("titleFont");
        if (parameter13 != null) {
            this.graph.setTitleFont(this.getFont(parameter13));
        }
        final String parameter14 = this.getParameter("xaxistitle");
        if (parameter14 != null) {
            this.graph.getXAxis().setTitle(parameter14);
        }
        final String parameter15 = this.getParameter("xaxistitlefont");
        if (parameter15 != null) {
            this.graph.getXAxis().setTitleFont(this.getFont(parameter15));
        }
        final String parameter16 = this.getParameter("xaxistitlecolor");
        if (parameter16 != null) {
            this.graph.getXAxis().setTitleForeground(Color.decode("#" + parameter16));
        }
        final String parameter17 = this.getParameter("xaxistitlebackcolor");
        if (parameter17 != null) {
            this.graph.getXAxis().setTitleBackground(Color.decode("#" + parameter17));
        }
        final String parameter18 = this.getParameter("yaxistitle");
        if (parameter18 != null) {
            this.graph.getYAxis().setTitle(parameter18);
        }
        final String parameter19 = this.getParameter("yaxistitlefont");
        if (parameter19 != null) {
            this.graph.getYAxis().setTitleFont(this.getFont(parameter19));
        }
        final String parameter20 = this.getParameter("yaxistitlecolor");
        if (parameter20 != null) {
            this.graph.getYAxis().setTitleForeground(Color.decode("#" + parameter20));
        }
        final String parameter21 = this.getParameter("yaxistitlebackcolor");
        if (parameter21 != null) {
            this.graph.getYAxis().setTitleBackground(Color.decode("#" + parameter21));
        }
        final String parameter22 = this.getParameter("valueFormat");
        if (parameter22 != null) {
            this.graph.setValueFormat(parameter22);
        }
        final String parameter23 = this.getParameter("showLegend");
        if (parameter23 != null) {
            this.graph.setLegendEnabled(parameter23.trim().toLowerCase().equals("yes"));
        }
        final String parameter24 = this.getParameter("showToolTip");
        if (parameter24 != null) {
            this.graph.setToolTipEnabled(parameter24.trim().toLowerCase().equals("yes"));
        }
        final String parameter25 = this.getParameter("tooltipdelay");
        if (parameter25 != null) {
            this.graph.setToolTipDelay(Integer.valueOf(parameter25));
        }
        final String parameter26 = this.getParameter("tooltiptimeroff");
        if (parameter26 != null) {
            this.graph.setToolTipTimerEnabled(!parameter26.trim().toLowerCase().equals("yes"));
        }
        final String parameter27 = this.getParameter("tooltiptype");
        if (parameter27 != null) {
            this.graph.setToolTipType(Integer.valueOf(parameter27));
        }
        final String parameter28 = this.getParameter("locale");
        if (parameter28 != null) {
            final String[] tokenizeParam = this.tokenizeParam(parameter28);
            this.graph.setLocale(new Locale(tokenizeParam[0], tokenizeParam[1], ""));
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
        this.paramValue = this.getParameter("showtooltipborder");
        if (this.paramValue != null) {
            toolTip.setBorderEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
    }
    
    private void getPieSerieParameters() {
        double[] serieValues = null;
        this.paramValue = this.getParameter("values");
        if (this.paramValue != null) {
            serieValues = this.getSerieValues(this.paramValue);
        }
        final PieSerie pieSerie = new PieSerie(serieValues);
        this.graph.addSerie(pieSerie);
        this.paramValue = this.getParameter("xradiusinset");
        if (this.paramValue != null) {
            pieSerie.setXRadiusInset(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("yradiusinset");
        if (this.paramValue != null) {
            pieSerie.setYRadiusInset(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("circled");
        if (this.paramValue != null) {
            pieSerie.setCircledEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("hasborder");
        if (this.paramValue != null) {
            pieSerie.setBorderEnabled(this.paramValue.trim().toLowerCase().equals("yes"));
        }
        this.paramValue = this.getParameter("slicelegendposition");
        if (this.paramValue != null) {
            pieSerie.setSliceLegendPosition(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("percentageformat");
        if (this.paramValue != null) {
            pieSerie.setPercentageFormat(this.paramValue);
        }
        this.paramValue = this.getParameter("angleoffset");
        if (this.paramValue != null) {
            pieSerie.setAngleOffset(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("3ddepth");
        if (this.paramValue != null) {
            pieSerie.set3DDepth(Integer.valueOf(this.paramValue));
        }
        this.paramValue = this.getParameter("slicesposition");
        if (this.paramValue != null) {
            final int[] slicesPosition = this.getSlicesPosition(this.paramValue);
            final Slice[] slices = pieSerie.getSlices();
            for (int i = 0; i < slices.length; ++i) {
                slices[i].setPosition(slicesPosition[i]);
            }
        }
        this.paramValue = this.getParameter("slicecolors");
        if (this.paramValue != null) {
            final Slice[] slices2 = pieSerie.getSlices();
            final String[] tokenizeParam = this.tokenizeParam(this.paramValue);
            if (slices2.length == tokenizeParam.length) {
                final Color[] sliceColors = new Color[tokenizeParam.length];
                for (int j = 0; j < tokenizeParam.length; ++j) {
                    sliceColors[j] = Color.decode("#" + tokenizeParam[j]);
                }
                pieSerie.setSliceColors(sliceColors);
            }
        }
        this.getEachSliceLegendParameter(pieSerie);
        this.getGlobalSliceLegendParameters(pieSerie);
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
    
    private double[] getSerieValues(final String s) {
        final String[] tokenizeParam = this.tokenizeParam(s);
        final double[] array = new double[tokenizeParam.length];
        for (int i = 0; i < tokenizeParam.length; ++i) {
            array[i] = Double.valueOf(tokenizeParam[i]);
        }
        return array;
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
    
    private int[] getSlicesPosition(final String s) {
        final String[] tokenizeParam = this.tokenizeParam(s);
        final int[] array = new int[tokenizeParam.length];
        for (int i = 0; i < tokenizeParam.length; ++i) {
            array[i] = Integer.valueOf(tokenizeParam[i]);
        }
        return array;
    }
    
    private void getGlobalSliceLegendParameters(final PieSerie pieSerie) {
        final Slice[] slices = pieSerie.getSlices();
        final String parameter = this.getParameter("showslicelegend");
        if (parameter != null) {
            for (int i = 0; i < slices.length; ++i) {
                slices[i].setSliceLegendEnabled(parameter.trim().toLowerCase().equals("yes"));
            }
        }
        final String parameter2 = this.getParameter("slicelegendopaque");
        if (parameter2 != null) {
            for (int j = 0; j < slices.length; ++j) {
                slices[j].getSliceLegend().setOpacityEnabled(parameter2.trim().toLowerCase().equals("yes"));
            }
        }
        final String parameter3 = this.getParameter("slicelegendbackcolor");
        if (parameter3 != null) {
            for (int k = 0; k < slices.length; ++k) {
                slices[k].getSliceLegend().setBackground(Color.decode("#" + parameter3));
            }
        }
        final String parameter4 = this.getParameter("slicelegendcolor");
        if (parameter4 != null) {
            for (int l = 0; l < slices.length; ++l) {
                slices[l].getSliceLegend().setForeground(Color.decode("#" + parameter4));
            }
        }
        final String parameter5 = this.getParameter("slicelegendfont");
        if (parameter5 != null) {
            final Font font = this.getFont(parameter5);
            for (int n = 0; n < slices.length; ++n) {
                slices[n].getSliceLegend().setFont(font);
            }
        }
        final String parameter6 = this.getParameter("slicelegendcontent");
        if (parameter6 != null) {
            final int intValue = Integer.valueOf(parameter6);
            for (int n2 = 0; n2 < slices.length; ++n2) {
                slices[n2].getSliceLegend().setContent(intValue);
            }
        }
        final String parameter7 = this.getParameter("slicelegendborder");
        if (parameter7 != null) {
            final int intValue2 = Integer.valueOf(parameter7);
            for (int n3 = 0; n3 < slices.length; ++n3) {
                slices[n3].getSliceLegend().setBorder(intValue2);
            }
        }
        final String parameter8 = this.getParameter("slicelegendvertexdistance");
        if (parameter8 != null) {
            final double doubleValue = Double.valueOf(parameter8);
            for (int n4 = 0; n4 < slices.length; ++n4) {
                slices[n4].getSliceLegend().setVertexDistance(doubleValue);
            }
        }
        final String parameter9 = this.getParameter("slicelegendarrowcolor");
        if (parameter9 != null) {
            for (int n5 = 0; n5 < slices.length; ++n5) {
                slices[n5].getSliceLegend().setArrowColor(Color.decode("#" + parameter9));
            }
        }
    }
    
    private void getEachSliceLegendParameter(final PieSerie pieSerie) {
        final Slice[] slices = pieSerie.getSlices();
        for (int i = 0; i < slices.length; ++i) {
            final String string = "slicelegend" + Integer.toString(i) + "_";
            final SliceLegend sliceLegend = slices[i].getSliceLegend();
            final String parameter = this.getParameter(string + "showslicelegend");
            if (parameter != null) {
                slices[i].setSliceLegendEnabled(parameter.trim().toLowerCase().equals("yes"));
            }
            final String parameter2 = this.getParameter(string + "opaque");
            if (parameter2 != null) {
                sliceLegend.setOpacityEnabled(parameter2.trim().toLowerCase().equals("yes"));
            }
            final String parameter3 = this.getParameter(string + "color");
            if (parameter3 != null) {
                sliceLegend.setForeground(Color.decode("#" + parameter3));
            }
            final String parameter4 = this.getParameter(string + "backcolor");
            if (parameter4 != null) {
                sliceLegend.setBackground(Color.decode("#" + parameter4));
            }
            final String parameter5 = this.getParameter(string + "font");
            if (parameter5 != null) {
                sliceLegend.setFont(this.getFont(parameter5));
            }
            final String parameter6 = this.getParameter(string + "content");
            if (parameter6 != null) {
                sliceLegend.setContent(Integer.valueOf(parameter6));
            }
            final String parameter7 = this.getParameter(string + "border");
            if (parameter7 != null) {
                sliceLegend.setBorder(Integer.valueOf(parameter7));
            }
            final String parameter8 = this.getParameter(string + "vertexdistance");
            if (parameter8 != null) {
                sliceLegend.setVertexDistance(Double.valueOf(parameter8));
            }
            final String parameter9 = this.getParameter(string + "arrowcolor");
            if (parameter9 != null) {
                sliceLegend.setArrowColor(Color.decode("#" + parameter9));
            }
        }
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
    
    public PieAppletDemo() {
        this.showBorder = true;
        this.borderColor = Color.blue;
        this.foreground = Color.yellow;
        this.background = Color.black;
        this.banner = "The JetChart Library - A product from Jinsight Software Solutions - www.jinsight.com";
    }
}
