// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Label;
import sTools.SInteger;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import sGraphics.EtchedBorder;
import sTools.SGraph;
import java.awt.Panel;
import java.applet.Applet;

public class Radial extends Applet
{
    boolean isStandalone;
    Panel optionpnl;
    SGraph radialgr;
    EtchedBorder etchedbrdr;
    BorderLayout borderLayout1;
    private int sc;
    private int px;
    private int py;
    private int py2;
    private boolean multi;
    private boolean firstTime;
    int l;
    int n;
    boolean s;
    boolean sp;
    boolean phase;
    FlowLayout flowLayout2;
    SInteger angqnnb;
    Panel panel2;
    FlowLayout flowLayout4;
    Label label1;
    Label label2;
    SInteger prinqnnb;
    Panel panel1;
    Checkbox multiplecb;
    Button resetbtn;
    FlowLayout flowLayout3;
    Button plotbtn;
    BorderLayout borderLayout2;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public Radial() {
        this.isStandalone = false;
        this.optionpnl = new Panel();
        this.radialgr = new SGraph();
        this.etchedbrdr = new EtchedBorder();
        this.borderLayout1 = new BorderLayout();
        this.sc = 1;
        this.py2 = 20;
        this.multi = false;
        this.firstTime = true;
        this.phase = true;
        this.flowLayout2 = new FlowLayout();
        this.angqnnb = new SInteger();
        this.panel2 = new Panel();
        this.flowLayout4 = new FlowLayout();
        this.label1 = new Label();
        this.label2 = new Label();
        this.prinqnnb = new SInteger();
        this.panel1 = new Panel();
        this.multiplecb = new Checkbox();
        this.resetbtn = new Button();
        this.flowLayout3 = new FlowLayout();
        this.plotbtn = new Button();
        this.borderLayout2 = new BorderLayout();
    }
    
    public void init() {
        try {
            this.l = Integer.parseInt(this.getParameter("l", "0"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.n = Integer.parseInt(this.getParameter("n", "1"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.s = Boolean.valueOf(this.getParameter("ShowControls", "true"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.multi = Boolean.valueOf(this.getParameter("MultPlot", "false"));
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.sp = Boolean.valueOf(this.getParameter("showAmplitude", "true"));
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
        this.setShowControls(this.s);
        this.angqnnb.setValue(this.l);
        this.prinqnnb.setValue(this.n);
        this.multiplecb.setState(this.multi);
    }
    
    private void jbInit() throws Exception {
        this.optionpnl.setLayout(this.flowLayout2);
        this.setBackground(Color.white);
        this.setSize(new Dimension(371, 333));
        this.optionpnl.setBackground(SystemColor.control);
        this.radialgr.setLabelY("[Rnl(r)*r]^2");
        this.radialgr.setEnableMouse(true);
        this.etchedbrdr.setBackground(SystemColor.control);
        this.etchedbrdr.setThickness(3);
        this.flowLayout2.setVgap(0);
        this.angqnnb.setBackground(SystemColor.control);
        this.panel2.setLayout(this.flowLayout4);
        this.flowLayout4.setHgap(3);
        this.flowLayout4.setVgap(2);
        this.label1.setText("n=");
        this.label2.setText("l=");
        this.prinqnnb.setValue(1);
        this.panel1.setLayout(this.flowLayout3);
        this.multiplecb.addItemListener(new 1());
        this.multiplecb.setLabel("Show Multiple");
        this.resetbtn.setLabel("Reset");
        this.flowLayout3.setHgap(2);
        this.flowLayout3.setVgap(1);
        this.plotbtn.setLabel("Plot");
        this.plotbtn.addActionListener(new 2());
        this.resetbtn.addActionListener(new 3());
        this.label2.setAlignment(2);
        this.label1.setAlignment(2);
        this.etchedbrdr.setLayout(this.borderLayout2);
        this.radialgr.setBackground(SystemColor.control);
        this.radialgr.setDataBackground(Color.white);
        this.radialgr.setSampleData(false);
        this.radialgr.setLabelX(" r/ao ");
        this.setLayout(this.borderLayout1);
        this.add(this.radialgr, "Center");
        this.add(this.optionpnl, "North");
        this.add(this.etchedbrdr, "South");
        this.etchedbrdr.add(this.panel1, "South");
        this.panel1.add(this.multiplecb, null);
        this.panel1.add(this.resetbtn, null);
        this.panel1.add(this.plotbtn, null);
        this.etchedbrdr.add(this.panel2, "Center");
        this.panel2.add(this.label1, null);
        this.panel2.add(this.prinqnnb, null);
        this.panel2.add(this.label2, null);
        this.panel2.add(this.angqnnb, null);
    }
    
    public void start() {
        if (this.firstTime) {
            this.setNL(this.n, this.l);
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
    
    public void setShowControls(final boolean visible) {
        this.etchedbrdr.setVisible(visible);
        this.invalidate();
        this.validate();
    }
    
    public void showAmplitude(final boolean phase) {
        this.phase = phase;
    }
    
    public void clearGraph() {
        this.radialgr.deleteAllSeries();
        this.multiplecb.setState(false);
        this.sc = 1;
        this.py2 = 20;
    }
    
    public void setNL(final int value, final int value2) {
        this.angqnnb.setValue(value2);
        this.prinqnnb.setValue(value);
        double n;
        if (value < 3) {
            n = 2 * ((value + 1) * (value + 1));
        }
        else {
            n = 2 * ((value + 2) * (value + 2));
        }
        final int width = this.radialgr.getSize().width;
        final double[] array = new double[width];
        final double[] array2 = new double[width];
        final double n2 = n / width;
        double n3 = 0.0;
        this.px = this.radialgr.getSize().width - 100;
        this.py = this.radialgr.getSize().height - (this.radialgr.getSize().height - this.py2);
        this.radialgr.setSeriesStyle(1, Color.black, true, 0);
        this.radialgr.setSeriesStyle(2, Color.red, true, 0);
        this.radialgr.setSeriesStyle(3, Color.green, true, 0);
        this.radialgr.setSeriesStyle(4, Color.blue, true, 0);
        this.radialgr.setAutoscaleX(false);
        this.radialgr.setAutoscaleY(false);
        this.radialgr.setMinMaxX(0.0, n);
        if (this.sc >= 5) {
            this.sc = 1;
            this.py2 = 20;
        }
        final double n4 = 2.0 / (value * value) * Math.sqrt(SpecialFunctions.factorial(value - value2 - 1) / SpecialFunctions.factorial(value + value2));
        if (value == 1 && value2 == 0) {
            for (int i = 1; i < width; ++i) {
                final double n5 = 2 * Math.exp(-n3);
                if (!this.phase) {
                    array2[i] = n5 * n5 * n3 * n3;
                }
                else {
                    array2[i] = n5;
                }
                array[i] = n3;
                n3 += n2;
            }
        }
        else if (value == 2 && value2 == 0) {
            for (int j = 1; j < width; ++j) {
                final double n6 = 0.7071067812 * (1 - n3 / 2) * Math.exp(-n3 / 2);
                if (!this.phase) {
                    array2[j] = n6 * n6 * n3 * n3;
                }
                else {
                    array2[j] = n6;
                }
                array[j] = n3;
                n3 += n2;
            }
        }
        else if (value == 2 && value2 == 1) {
            for (int k = 1; k < width; ++k) {
                final double n7 = 0.2041241452 * n3 * Math.exp(-n3 / 2);
                if (!this.phase) {
                    array2[k] = n7 * n7 * n3 * n3;
                }
                else {
                    array2[k] = n7;
                }
                array[k] = n3;
                n3 += n2;
            }
        }
        else {
            for (int l = 1; l < width; ++l) {
                final double n8 = n4 * Math.pow(2.0 * n3 / value, value2) * Math.exp(-n3 / value) * SpecialFunctions.laguerre(2 * value2 + 1, value - value2 - 1, 2.0 * n3 / value);
                if (!this.phase) {
                    array2[l] = n8 * n8 * n3 * n3;
                }
                else {
                    array2[l] = n8;
                }
                array[l] = n3;
                n3 += n2;
            }
        }
        if (this.multiplecb.getState()) {
            this.radialgr.clearSeriesData(this.sc);
            if (this.phase) {
                this.radialgr.setAutoscaleY(true);
            }
            else {
                this.radialgr.setAutoscaleY(false);
            }
            this.radialgr.addData(this.sc, array, array2);
            this.radialgr.setMinMaxY(0.0, this.radialgr.getYmax() + 0.1 * this.radialgr.getYmax());
            if (!this.phase) {
                this.radialgr.setLabelY("[Rnl(r)*r]^2", Color.black);
            }
            else {
                this.radialgr.setLabelY("Rnl(r)", Color.black);
            }
            this.radialgr.setSeriesLegend(this.sc, Color.black, this.px, this.py, String.valueOf(String.valueOf(String.valueOf("n=").concat(String.valueOf(value))).concat(String.valueOf("  l="))).concat(String.valueOf(value2)));
            ++this.sc;
            this.py2 += 15;
        }
        else {
            this.radialgr.clearSeriesData(1);
            if (this.phase) {
                this.radialgr.setAutoscaleY(true);
            }
            else {
                this.radialgr.setAutoscaleY(false);
            }
            this.radialgr.addData(1, array, array2);
            this.radialgr.setMinMaxY(0.0, this.radialgr.getYmax() + 0.05 * this.radialgr.getYmax());
            if (!this.phase) {
                this.radialgr.setLabelY("[Rnl(r)*r]^2", Color.black);
            }
            else {
                this.radialgr.setLabelY("Rnl(r)", Color.black);
            }
            this.radialgr.setSeriesLegend(1, Color.black, this.px, this.py, String.valueOf(String.valueOf(String.valueOf("n=").concat(String.valueOf(value))).concat(String.valueOf("  l="))).concat(String.valueOf(value2)));
        }
    }
    
    void plotbtn_actionPerformed(final ActionEvent actionEvent) {
        final int value = this.angqnnb.getValue();
        final int value2 = this.prinqnnb.getValue();
        if (value > value2 - 1 | value < 0) {
            this.angqnnb.setBackground(Color.red);
        }
        else if (value2 > 50) {
            this.prinqnnb.setBackground(Color.red);
        }
        else {
            this.setNL(value2, value);
        }
    }
    
    void resetbtn_actionPerformed(final ActionEvent actionEvent) {
        this.clearGraph();
    }
    
    void multiplecb_itemStateChanged(final ItemEvent itemEvent) {
        if (this.multiplecb.getState()) {
            this.sc = 2;
        }
        else {
            this.sc = 2;
            this.radialgr.deleteAllSeries();
            this.py2 = 20;
            this.setNL(this.prinqnnb.getValue(), this.angqnnb.getValue());
        }
    }
    
    class 1 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            Radial.this.multiplecb_itemStateChanged(itemEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Radial.this.plotbtn_actionPerformed(actionEvent);
        }
    }
    
    class 3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Radial.this.resetbtn_actionPerformed(actionEvent);
        }
    }
}
