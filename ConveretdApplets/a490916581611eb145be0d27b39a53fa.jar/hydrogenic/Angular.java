// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import sTools.graph.Markers;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import sTools.SNumber;
import java.awt.Checkbox;
import java.awt.BorderLayout;
import java.awt.Button;
import sGraphics.EtchedBorder;
import java.awt.Color;
import sTools.SGraph;
import java.applet.Applet;

public class Angular extends Applet
{
    boolean isStandalone;
    boolean sa;
    boolean openned;
    SGraph graph;
    private double[] datax;
    private double[] datay;
    private double[] ndatax;
    private double[] ndatay;
    private boolean compare;
    private int numpoints;
    private int l;
    private int seriesnum;
    private boolean normal;
    private int m;
    public boolean sc;
    public boolean multi;
    private boolean graphon;
    private Color forrest;
    private boolean limit;
    private boolean firstTime;
    public static final double p = 3.141592653589793;
    EtchedBorder controlPan;
    EtchedBorder etchedBorder2;
    EtchedBorder etchedBorder3;
    Button clearbtn;
    Button gobtn;
    BorderLayout borderLayout2;
    EtchedBorder etchedBorder4;
    Checkbox comparebox;
    BorderLayout borderLayout1;
    EtchedBorder etchedBorder5;
    SNumber lfield;
    Label label1;
    SNumber mfield;
    Label llabel;
    FlowLayout flowLayout2;
    BorderLayout borderLayout3;
    BorderLayout borderLayout4;
    BorderLayout borderLayout5;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public Angular() {
        this.isStandalone = false;
        this.sa = false;
        this.openned = true;
        this.graph = new SGraph();
        this.datax = new double[210];
        this.datay = new double[210];
        this.ndatax = new double[210];
        this.ndatay = new double[210];
        this.compare = false;
        this.numpoints = 210;
        this.seriesnum = 0;
        this.graphon = false;
        this.forrest = new Color(0, 128, 0);
        this.limit = false;
        this.firstTime = true;
        this.controlPan = new EtchedBorder();
        this.etchedBorder2 = new EtchedBorder();
        this.etchedBorder3 = new EtchedBorder();
        this.clearbtn = new Button();
        this.gobtn = new Button();
        this.borderLayout2 = new BorderLayout();
        this.etchedBorder4 = new EtchedBorder();
        this.comparebox = new Checkbox();
        this.borderLayout1 = new BorderLayout();
        this.etchedBorder5 = new EtchedBorder();
        this.lfield = new SNumber();
        this.label1 = new Label();
        this.mfield = new SNumber();
        this.llabel = new Label();
        this.flowLayout2 = new FlowLayout();
        this.borderLayout3 = new BorderLayout();
        this.borderLayout4 = new BorderLayout();
        this.borderLayout5 = new BorderLayout();
    }
    
    public void init() {
        try {
            this.l = Integer.parseInt(this.getParameter("l", "2"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.m = Integer.parseInt(this.getParameter("m", "1"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.sc = Boolean.valueOf(this.getParameter("ShowControls", "true"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.normal = Boolean.valueOf(this.getParameter("Normalize", "true"));
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.multi = Boolean.valueOf(this.getParameter("MultPlot", "false"));
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
        this.showPanel(this.sc);
        this.comparebox.setState(this.multi);
        this.setLM(this.l, this.m);
        this.graph.setEnableMouse(true);
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(257, 264));
        this.setLayout(this.borderLayout5);
        this.graph.setBackground(SystemColor.control);
        this.graph.setMarkers(null);
        this.graph.setDataBackground(Color.white);
        this.graph.setSampleData(false);
        this.graph.setBorders("0,5,10,0");
        this.graph.setDrawZero(true);
        this.graph.setLabelY("Z");
        this.controlPan.setLayout(this.borderLayout4);
        this.graph.setLabelX("X");
        this.etchedBorder2.setLayout(this.borderLayout3);
        this.etchedBorder3.setLayout(this.borderLayout2);
        this.clearbtn.setLabel("Clear");
        this.gobtn.setLabel("Go!");
        this.etchedBorder4.setLayout(this.borderLayout1);
        this.comparebox.setBackground(SystemColor.control);
        this.comparebox.setLabel("Multiple");
        this.etchedBorder5.setLayout(this.flowLayout2);
        this.lfield.setText("0");
        this.label1.setBackground(SystemColor.control);
        this.label1.setAlignment(2);
        this.label1.setText("m =");
        this.mfield.setText("0");
        this.llabel.setText("l =");
        this.llabel.setBackground(SystemColor.control);
        this.llabel.setAlignment(2);
        this.gobtn.addActionListener(new 1());
        this.clearbtn.addActionListener(new 2());
        this.add(this.graph, "Center");
        this.add(this.controlPan, "South");
        this.controlPan.add(this.etchedBorder3, "West");
        this.etchedBorder3.add(this.gobtn, "North");
        this.etchedBorder3.add(this.clearbtn, "Center");
        this.controlPan.add(this.etchedBorder2, "Center");
        this.etchedBorder2.add(this.etchedBorder4, "Center");
        this.etchedBorder4.add(this.comparebox, "Center");
        this.etchedBorder2.add(this.etchedBorder5, "North");
        this.etchedBorder5.add(this.llabel, null);
        this.etchedBorder5.add(this.lfield, null);
        this.etchedBorder5.add(this.label1, null);
        this.etchedBorder5.add(this.mfield, null);
    }
    
    public void start() {
        if (this.firstTime) {
            this.setLM(this.l, this.m);
        }
        this.firstTime = false;
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void calcdata() {
        if (this.m < 0) {
            this.m = -this.m;
        }
        final double sqrt = Math.sqrt((2 * this.l + 1) * SpecialFunctions.factorial(this.l - this.m) / (SpecialFunctions.factorial(this.l + this.m) * 4 * 3.141592653589793));
        for (int i = 0; i < this.numpoints; ++i) {
            final double legendre = SpecialFunctions.legendre(this.l, this.m, Math.cos(3.141592653589793 * i / (2.0 * this.numpoints)));
            if (this.sa) {
                this.datax[i] = sqrt * legendre * Math.sin(3.141592653589793 * i / (2.0 * this.numpoints)) * (sqrt * legendre) * Math.sin(3.141592653589793 * i / (2.0 * this.numpoints));
                this.datay[i] = sqrt * legendre * Math.cos(3.141592653589793 * i / (2.0 * this.numpoints)) * (sqrt * legendre) * Math.cos(3.141592653589793 * i / (2.0 * this.numpoints));
            }
            else {
                this.datax[i] = sqrt * legendre * Math.sin(3.141592653589793 * i / (2.0 * this.numpoints));
                this.datay[i] = sqrt * legendre * Math.cos(3.141592653589793 * i / (2.0 * this.numpoints));
            }
        }
        this.unitScale();
        for (int j = 0; j < this.numpoints; ++j) {
            this.ndatax[j] = -this.datax[j];
            this.ndatay[j] = -this.datay[j];
        }
    }
    
    private void unitScale() {
        double n = 0.0;
        for (int i = 0; i < this.numpoints; ++i) {
            final double sqrt = Math.sqrt(this.datax[i] * this.datax[i] + this.datay[i] * this.datay[i]);
            if (sqrt > n) {
                n = sqrt;
            }
        }
        for (int j = 0; j < this.numpoints; ++j) {
            final double[] datax = this.datax;
            final int n2 = j;
            datax[n2] /= n;
            final double[] datay = this.datay;
            final int n3 = j;
            datay[n3] /= n;
        }
    }
    
    public void setLM(final int l, final int m) {
        if (this.openned) {
            this.seriesnum = 0;
            this.graph.deleteAllSeries();
            this.openned = false;
        }
        ++this.seriesnum;
        this.l = l;
        this.m = m;
        this.mfield.setValue(m);
        this.lfield.setValue(l);
        this.compare = this.comparebox.getState();
        this.calcdata();
        this.graphdata();
        this.graph.square = true;
    }
    
    public void showPanel(final boolean visible) {
        this.controlPan.setVisible(visible);
        this.controlPan.setBackground(SystemColor.control);
    }
    
    public void graphdata() {
        Color color = Color.red;
        if (!this.compare) {
            this.graph.deleteAllSeries();
            this.seriesnum = 0;
        }
        this.graph.setAutoscaleX(false);
        this.graph.setAutoscaleY(false);
        switch (this.seriesnum) {
            case 0: {
                color = Color.red;
                break;
            }
            case 1: {
                color = Color.black;
                break;
            }
            case 2: {
                color = Color.blue;
                break;
            }
            case 3: {
                color = this.forrest;
                break;
            }
            case 4: {
                color = Color.gray;
                break;
            }
            case 5: {
                color = Color.red;
                this.seriesnum = 0;
                break;
            }
        }
        this.graph.deleteSeries(4 * this.seriesnum + 1);
        this.graph.deleteSeries(4 * this.seriesnum + 2);
        this.graph.deleteSeries(4 * this.seriesnum + 3);
        this.graph.deleteSeries(4 * this.seriesnum + 4);
        this.graph.setSeriesStyle(4 * this.seriesnum + 1, color, true, 0);
        this.graph.setSeriesStyle(4 * this.seriesnum + 2, color, true, 0);
        this.graph.setSeriesStyle(4 * this.seriesnum + 3, color, true, 0);
        this.graph.setSeriesStyle(4 * this.seriesnum + 4, color, true, 0);
        this.graph.addData(4 * this.seriesnum + 1, this.datax, this.datay);
        this.graph.addData(4 * this.seriesnum + 2, this.ndatax, this.datay);
        this.graph.addData(4 * this.seriesnum + 3, this.ndatax, this.ndatay);
        this.graph.addData(4 * this.seriesnum + 4, this.datax, this.ndatay);
        this.graph.setSeriesLegend(this.seriesnum + 1, color, 50 + this.graph.getSize().width / 2, 30 + this.seriesnum * 11, String.valueOf(String.valueOf(String.valueOf("l = ").concat(String.valueOf(this.l))).concat(String.valueOf(" m = "))).concat(String.valueOf(this.m)));
    }
    
    void gobtn_actionPerformed(final ActionEvent actionEvent) {
        int n = (int)this.lfield.getValue();
        if (n < 0) {
            n = -n;
        }
        final int n2 = (int)this.mfield.getValue();
        if (n < Math.abs(n2)) {
            this.mfield.setBackground(Color.red);
        }
        else {
            this.setLM(n, n2);
        }
    }
    
    void clearbtn_actionPerformed(final ActionEvent actionEvent) {
        this.mfield.setValue(this.m);
        this.lfield.setValue(this.l);
        this.graph.deleteAllSeries();
        this.seriesnum = 0;
    }
    
    class 1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Angular.this.gobtn_actionPerformed(actionEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Angular.this.clearbtn_actionPerformed(actionEvent);
        }
    }
}
