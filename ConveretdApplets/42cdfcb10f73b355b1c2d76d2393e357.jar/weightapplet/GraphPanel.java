// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class GraphPanel extends JTabbedPane
{
    public Color[] colors;
    private boolean defaultcolors;
    private Color graphbackground;
    private Color graphforeground;
    private Color graphgridlines;
    private int fontSize;
    private int width;
    private int height;
    private double xMin;
    private double xMax;
    private double graphedweightMax;
    private double graphedweightMin;
    private double graphedbfpMax;
    private double graphedbfpMin;
    private double graphedenergyMax;
    private double graphedenergyMin;
    private double startwt_pos;
    private double startbfp_pos;
    private double startenergy_pos;
    private double maximumbfp_fordisplay;
    private double eps;
    private double y0;
    private double x0;
    private double goaltime;
    private BodyModel[] bodytraj;
    private BodyModel[] upperbodytraj;
    private BodyModel[] lowerbodytraj;
    private DailyParams[] paramtraj;
    private double calorie_spread;
    private boolean showspread;
    private boolean showgrid;
    private int gridspacing;
    private boolean showlegend;
    private int legendwidth;
    private int legendheight;
    private int legendgap;
    private boolean longoutput;
    private Object[][] tabledata;
    private double simlength;
    public WeightPanel wpanel;
    public BfpPanel bpanel;
    public EnergyPanel epanel;
    public JScrollPane datapanel;
    public JTable datatable;
    private double maxweight_kgs;
    private double minweight_kgs;
    private double maxweight_fordisplay;
    private double maxenergy_fordisplay;
    private double minweight_inrange;
    private double maxweight_inrange;
    private double averageweight_inrange;
    private double averagebfp_inrange;
    private double averageenergy_inrange;
    private double weightunits;
    private String weightunitsstring;
    private double energyunits;
    private String energyunitsstring;
    private Color maincurve;
    private Color uppercurvedefault;
    private Color uppercurvealt;
    private Color lowercurvedefault;
    private Color lowercurvealt;
    private String[] columnNames;
    private int yaxis_space;
    private int xaxis_space;
    private Font rotatedfont;
    private boolean warningdisplayed;
    private MainPanel MainPanel;
    
    public GraphPanel(final Baseline baseline, final Intervention intervention, final Intervention intervention2, final double n, final int[] array) {
        this.colors = new Color[] { Color.yellow, Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange, Color.pink, Color.red, Color.lightGray, Color.white, Color.gray, Color.darkGray };
        this.defaultcolors = true;
        this.graphbackground = Color.black;
        this.graphforeground = Color.white;
        this.graphgridlines = Color.darkGray;
        this.fontSize = 10;
        this.width = 0;
        this.height = 0;
        this.xMin = 0.0;
        this.startwt_pos = 0.5;
        this.startbfp_pos = 0.5;
        this.startenergy_pos = 0.5;
        this.maximumbfp_fordisplay = 70.0;
        this.eps = 0.001;
        this.calorie_spread = 200.0;
        this.showspread = false;
        this.showgrid = false;
        this.gridspacing = 10;
        this.showlegend = true;
        this.legendwidth = 150;
        this.legendheight = 80;
        this.legendgap = 20;
        this.longoutput = false;
        this.weightunits = 1.0;
        this.weightunitsstring = "kg";
        this.energyunits = 1.0;
        this.energyunitsstring = "kJ";
        this.maincurve = Color.RED;
        this.uppercurvedefault = Color.CYAN;
        this.uppercurvealt = Color.BLUE;
        this.lowercurvedefault = Color.GREEN;
        this.lowercurvealt = Color.GREEN;
        this.columnNames = new String[] { "Day", "Weight (" + this.weightunitsstring + ")", "Upper weight (" + this.weightunitsstring + ")", " Lower weight (" + this.weightunitsstring + ")", "Body Fat %", "BMI", "Fat Mass (" + this.weightunitsstring + ")", "Fat Free Mass (" + this.weightunitsstring + ")", "Intake (" + this.energyunitsstring + "/day)", "Expenditure (" + this.energyunitsstring + "/day)" };
        this.yaxis_space = 50;
        this.xaxis_space = 30;
        this.wpanel = new WeightPanel();
        this.bpanel = new BfpPanel();
        this.rotatedfont = new Font("Lucinda Grande", 0, 13).deriveFont(AffineTransform.getRotateInstance(-1.5707963267948966));
        try {
            this.calculate(baseline, intervention, intervention2, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.tabledata = this.maketabledata_new();
        (this.datatable = new JTable(this.tabledata, this.columnNames)).setPreferredScrollableViewportSize(new Dimension(300, 300));
        (this.datapanel = new JScrollPane()).setViewportView(this.datatable);
        this.addTab("Weight", this.wpanel);
        this.addTab("Body Fat %", this.bpanel);
        this.addTab("Model Data", this.datapanel);
        this.repaint();
    }
    
    public GraphPanel(final MainPanel mainPanel, final Baseline baseline, final Intervention intervention, final Intervention intervention2, final double n, final int[] array) {
        this.colors = new Color[] { Color.yellow, Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange, Color.pink, Color.red, Color.lightGray, Color.white, Color.gray, Color.darkGray };
        this.defaultcolors = true;
        this.graphbackground = Color.black;
        this.graphforeground = Color.white;
        this.graphgridlines = Color.darkGray;
        this.fontSize = 10;
        this.width = 0;
        this.height = 0;
        this.xMin = 0.0;
        this.startwt_pos = 0.5;
        this.startbfp_pos = 0.5;
        this.startenergy_pos = 0.5;
        this.maximumbfp_fordisplay = 70.0;
        this.eps = 0.001;
        this.calorie_spread = 200.0;
        this.showspread = false;
        this.showgrid = false;
        this.gridspacing = 10;
        this.showlegend = true;
        this.legendwidth = 150;
        this.legendheight = 80;
        this.legendgap = 20;
        this.longoutput = false;
        this.weightunits = 1.0;
        this.weightunitsstring = "kg";
        this.energyunits = 1.0;
        this.energyunitsstring = "kJ";
        this.maincurve = Color.RED;
        this.uppercurvedefault = Color.CYAN;
        this.uppercurvealt = Color.BLUE;
        this.lowercurvedefault = Color.GREEN;
        this.lowercurvealt = Color.GREEN;
        this.columnNames = new String[] { "Day", "Weight (" + this.weightunitsstring + ")", "Upper weight (" + this.weightunitsstring + ")", " Lower weight (" + this.weightunitsstring + ")", "Body Fat %", "BMI", "Fat Mass (" + this.weightunitsstring + ")", "Fat Free Mass (" + this.weightunitsstring + ")", "Intake (" + this.energyunitsstring + "/day)", "Expenditure (" + this.energyunitsstring + "/day)" };
        this.yaxis_space = 50;
        this.xaxis_space = 30;
        this.MainPanel = mainPanel;
        this.wpanel = new WeightPanel();
        this.bpanel = new BfpPanel();
        this.epanel = new EnergyPanel();
        this.rotatedfont = new Font("Lucinda Grande", 0, 13).deriveFont(AffineTransform.getRotateInstance(-1.5707963267948966));
        try {
            this.calculate(baseline, intervention, intervention2, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.tabledata = this.maketabledata_new();
        (this.datatable = new JTable(this.tabledata, this.columnNames)).setPreferredScrollableViewportSize(new Dimension(300, 300));
        (this.datapanel = new JScrollPane()).setViewportView(this.datatable);
        this.addTab("Weight", this.wpanel);
        this.addTab("Body Fat %", this.bpanel);
        this.addTab("Energy Intake & Expenditure", this.epanel);
        this.addTab("Tabulated Data", this.datapanel);
        this.repaint();
    }
    
    public void setcalspread(final double calorie_spread) {
        this.calorie_spread = calorie_spread;
    }
    
    public Object[][] maketabledata_new() {
        final Baseline baseline = this.MainPanel.getBaseline();
        final Object[][] array = new Object[(int)this.simlength][this.columnNames.length];
        for (int i = 0; i < (int)this.simlength; ++i) {
            final double weight = this.bodytraj[i].getWeight(baseline);
            final double weight2 = this.upperbodytraj[i].getWeight(baseline);
            final double weight3 = this.lowerbodytraj[i].getWeight(baseline);
            final double bmi = this.bodytraj[i].getBMI(baseline);
            final double fat = this.bodytraj[i].getFat();
            this.upperbodytraj[i].getFat();
            this.lowerbodytraj[i].getFat();
            final double fatPercent = this.bodytraj[i].getFatPercent(baseline);
            this.upperbodytraj[i].getFatPercent(baseline);
            this.lowerbodytraj[i].getFatPercent(baseline);
            final double fatFree = this.bodytraj[i].getFatFree(baseline);
            final double tee = this.bodytraj[i].getTEE(baseline, this.paramtraj[i]);
            final double calories = this.paramtraj[i].getCalories();
            array[i][0] = i;
            if (!this.longoutput) {
                array[i][1] = Math.round(weight * this.weightunits * 10.0) / 10.0;
                array[i][2] = Math.round(weight2 * this.weightunits * 10.0) / 10.0;
                array[i][3] = Math.round(weight3 * this.weightunits * 10.0) / 10.0;
                array[i][4] = Math.round(fatPercent * 10.0) / 10.0;
                array[i][5] = Math.round(bmi * 10.0) / 10.0;
                array[i][6] = Math.round(fat * this.weightunits * 10.0) / 10.0;
                array[i][7] = Math.round(fatFree * this.weightunits * 10.0) / 10.0;
                array[i][8] = Math.round(calories * this.energyunits * 10.0) / 10.0;
                array[i][9] = Math.round(tee * this.energyunits * 10.0) / 10.0;
            }
            else {
                array[i][1] = weight * this.weightunits;
                array[i][2] = weight2 * this.weightunits;
                array[i][3] = weight3 * this.weightunits;
                array[i][4] = fatPercent;
                array[i][5] = bmi;
                array[i][6] = fat * this.weightunits;
                array[i][7] = fatFree * this.weightunits;
                array[i][8] = calories * this.energyunits;
                array[i][9] = tee * this.energyunits;
            }
        }
        return array;
    }
    
    public Object[][] gettabledata() {
        return this.tabledata;
    }
    
    public String[] getcolumnnames() {
        return this.columnNames;
    }
    
    public void setdefaultcolors(final boolean b) {
        if (b) {
            this.defaultcolors = true;
            this.graphbackground = Color.black;
            this.graphforeground = Color.white;
            this.graphgridlines = Color.darkGray;
        }
        else {
            this.defaultcolors = false;
            this.graphbackground = Color.white;
            this.graphforeground = Color.black;
            this.graphgridlines = Color.lightGray;
        }
        this.repaint();
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.bpanel.repaint();
        this.wpanel.repaint();
        this.epanel.repaint();
        this.datapanel.remove(this.datatable);
        this.tabledata = this.maketabledata_new();
        (this.datatable = new JTable(this.tabledata, this.columnNames) {
            @Override
            public boolean isCellEditable(final int n, final int n2) {
                return false;
            }
        }).setPreferredScrollableViewportSize(this.getPreferredSize());
        this.datapanel.add(this.datatable);
        this.datapanel.setViewportView(this.datatable);
        System.out.println("END of gpanel paint");
    }
    
    public void setparams(final double n, final double n2, final double n3) {
        if (n <= 1.0 && n >= 0.0 && n2 <= 1.0 && n2 >= 0.0 && n3 <= 1.0) {
            if (n3 >= 0.0) {
                System.out.println("timestart" + n + "timezoom=" + n2 + "verticalzoom" + n3);
                this.xMin = (int)(n * this.simlength);
                if (this.xMin == (int)this.simlength) {
                    this.xMin = (int)this.simlength - 2;
                }
                this.xMax = (int)(this.simlength - 1.0 + n2 * (this.xMin + 1.0 - (this.simlength - 1.0)));
                if (this.xMax == this.xMin) {
                    this.xMax = this.xMin + 1.0;
                }
                if (this.xMax >= (int)this.simlength) {
                    this.xMax = this.simlength - 1.0;
                }
                this.reset_max();
                final Baseline baseline = this.MainPanel.getBaseline();
                final double weight = this.upperbodytraj[0].getWeight(baseline);
                final double fatPercent = this.upperbodytraj[0].getFatPercent(baseline);
                final double weight2 = this.lowerbodytraj[0].getWeight(baseline);
                final double fatPercent2 = this.lowerbodytraj[0].getFatPercent(baseline);
                final double abs = Math.abs(weight - weight2);
                final double abs2 = Math.abs(fatPercent - fatPercent2);
                final double weight3 = this.upperbodytraj[(int)this.xMin + 1].getWeight(baseline);
                final double fatPercent3 = this.upperbodytraj[(int)this.xMin + 1].getFatPercent(baseline);
                final double weight4 = this.lowerbodytraj[(int)this.xMin + 1].getWeight(baseline);
                final double fatPercent4 = this.lowerbodytraj[(int)this.xMin + 1].getFatPercent(baseline);
                final double abs3 = Math.abs(weight3 - weight4);
                final double abs4 = Math.abs(fatPercent3 - fatPercent4);
                final double max = Math.max(abs, abs3);
                final double max2 = Math.max(abs2, abs4);
                final double n4 = this.maxweight_fordisplay + n3 * (max - this.maxweight_fordisplay);
                final double weight5 = this.bodytraj[(int)this.xMin].getWeight(baseline);
                System.out.println("Average weight in range=" + this.averageweight_inrange);
                this.graphedweightMax = (this.averageweight_inrange + n4 / 2.0) * this.weightunits;
                this.graphedweightMin = (this.averageweight_inrange - n4 / 2.0) * this.weightunits;
                if (weight5 * this.weightunits < this.graphedweightMin || (weight5 * this.weightunits > this.graphedweightMin && (weight5 * this.weightunits - this.graphedweightMin) / n4 < 0.1)) {
                    System.out.println("Graph low");
                    this.startwt_pos = 0.9;
                    this.graphedweightMax = (weight5 + this.startwt_pos * n4) * this.weightunits;
                    this.graphedweightMin = (weight5 - (1.0 - this.startwt_pos) * n4) * this.weightunits;
                }
                else if (weight5 * this.weightunits > this.graphedweightMax || (weight5 * this.weightunits < this.graphedweightMax && (this.graphedweightMax - weight5 * this.weightunits) / n4 < 0.1)) {
                    System.out.println("Graph high");
                    this.startwt_pos = 0.1;
                    this.graphedweightMax = (weight5 + this.startwt_pos * n4) * this.weightunits;
                    this.graphedweightMin = (weight5 - (1.0 - this.startwt_pos) * n4) * this.weightunits;
                }
                final boolean b = this.graphedweightMax > this.maxweight_fordisplay * this.weightunits;
                if (this.graphedweightMin < 0.0) {
                    System.out.println("Hit bottom");
                    this.graphedweightMin = 0.0;
                    this.graphedweightMax = this.graphedweightMin + n4 * this.weightunits;
                }
                else if (b) {
                    System.out.println("Hit top");
                    this.graphedweightMax = this.maxweight_fordisplay * this.weightunits;
                    this.graphedweightMin = this.graphedweightMax - n4 * this.weightunits;
                }
                System.out.println("In setparams, weightmax=" + this.graphedweightMax + " ,weightmin=" + this.graphedweightMin);
                final double n5 = this.maximumbfp_fordisplay + n3 * (max2 - this.maximumbfp_fordisplay);
                final double fatPercent5 = this.bodytraj[(int)this.xMin].getFatPercent(baseline);
                this.graphedbfpMax = this.averagebfp_inrange + n5 / 2.0;
                this.graphedbfpMin = this.averagebfp_inrange - n5 / 2.0;
                if (fatPercent5 < this.graphedbfpMin || (fatPercent5 > this.graphedbfpMin && (fatPercent5 - this.graphedbfpMin) / n5 < 0.1)) {
                    this.startbfp_pos = 0.9;
                    this.graphedbfpMax = fatPercent5 + this.startbfp_pos * n5;
                    this.graphedbfpMin = fatPercent5 - (1.0 - this.startbfp_pos) * n5;
                }
                else if (fatPercent5 > this.graphedbfpMax || (fatPercent5 < this.graphedbfpMax && (this.graphedbfpMax - fatPercent5) / n5 < 0.1)) {
                    this.startbfp_pos = 0.1;
                    this.graphedbfpMax = fatPercent5 + this.startbfp_pos * n5;
                    this.graphedbfpMin = fatPercent5 - (1.0 - this.startbfp_pos) * n5;
                }
                final boolean b2 = this.graphedbfpMax > this.maximumbfp_fordisplay;
                if (this.graphedbfpMin < 0.0) {
                    this.graphedbfpMin = 0.0;
                    this.graphedbfpMax = this.graphedbfpMin + n5;
                }
                else if (b2) {
                    this.graphedbfpMax = 70.0;
                    this.graphedbfpMin = this.graphedbfpMax - n5;
                }
                final double n6 = this.maxenergy_fordisplay + n3 * (Math.max(Math.abs(this.paramtraj[(int)this.xMin].getCalories() - this.getTEE((int)this.xMin)), Math.abs(this.paramtraj[(int)this.xMin + 1].getCalories() - this.getTEE((int)this.xMin + 1))) - this.maxenergy_fordisplay);
                final double n7 = (this.paramtraj[(int)this.xMin].getCalories() + this.getTEE((int)this.xMin)) / 2.0;
                this.graphedenergyMax = (this.averageenergy_inrange + n6 / 2.0) * this.energyunits;
                this.graphedenergyMin = (this.averageenergy_inrange - n6 / 2.0) * this.energyunits;
                if (n7 * this.energyunits < this.graphedenergyMin || (n7 * this.energyunits > this.graphedenergyMin && (n7 * this.energyunits - this.graphedenergyMin) / n6 < 0.1)) {
                    System.out.println("Energy graph low");
                    this.startenergy_pos = 0.9;
                    this.graphedenergyMax = (n7 + this.startenergy_pos * n6) * this.energyunits;
                    this.graphedenergyMin = (n7 - (1.0 - this.startenergy_pos) * n6) * this.energyunits;
                }
                else if (n7 > this.graphedenergyMax || (n7 * this.energyunits < this.graphedenergyMax && (this.graphedenergyMax - n7 * this.energyunits) / n6 < 0.1)) {
                    System.out.println("Energy graph high");
                    this.startenergy_pos = 0.1;
                    this.graphedenergyMax = (n7 + this.startenergy_pos * n6) * this.energyunits;
                    this.graphedenergyMin = (n7 - (1.0 - this.startenergy_pos) * n6) * this.energyunits;
                }
                final boolean b3 = this.graphedenergyMax > this.maxenergy_fordisplay * this.energyunits;
                if (this.graphedenergyMin < 0.0) {
                    System.out.println("Energy hit bottom");
                    this.graphedenergyMin = 0.0;
                    this.graphedenergyMax = (this.graphedenergyMin + n6) * this.energyunits;
                }
                else if (b3) {
                    System.out.println("Energy hit top");
                    this.graphedenergyMax = this.maxenergy_fordisplay * this.energyunits;
                    this.graphedenergyMin = this.graphedenergyMax - n6 * this.energyunits;
                }
                this.wpanel.repaint();
                this.bpanel.repaint();
                this.epanel.repaint();
            }
        }
    }
    
    public void setspread(final boolean showspread) {
        this.showspread = showspread;
        this.wpanel.repaint();
        this.bpanel.repaint();
        this.epanel.repaint();
    }
    
    public void setgrid(final boolean showgrid) {
        this.showgrid = showgrid;
        this.wpanel.repaint();
        this.bpanel.repaint();
        this.epanel.repaint();
    }
    
    public void setlegend(final boolean showlegend) {
        this.showlegend = showlegend;
        this.wpanel.repaint();
        this.bpanel.repaint();
        this.epanel.repaint();
    }
    
    public void remaketable() {
        System.out.println("Remaking table in GraphPanel");
        this.datapanel.remove(this.datatable);
        this.tabledata = this.maketabledata_new();
        (this.datatable = new JTable(this.tabledata, this.columnNames)).setPreferredScrollableViewportSize(new Dimension(200, 200));
        this.datapanel.add(this.datatable);
        this.datapanel.setViewportView(this.datatable);
    }
    
    public BodyModel getfinalbc() {
        return this.bodytraj[(int)this.simlength - 1];
    }
    
    public String getweightstring() {
        return this.weightunitsstring;
    }
    
    public BodyModel getgoalbc() throws Exception {
        if (this.goaltime < this.simlength) {
            return this.bodytraj[(int)this.goaltime];
        }
        throw new Exception();
    }
    
    public void reset_max() {
        final Baseline baseline = this.MainPanel.getBaseline();
        final double weight = this.upperbodytraj[0].getWeight(baseline);
        this.maxweight_kgs = weight;
        this.minweight_kgs = weight;
        this.minweight_inrange = weight;
        this.maxweight_inrange = weight;
        double n = Math.max(this.paramtraj[0].getCalories(), this.bodytraj[0].getTEE(baseline, this.paramtraj[0]));
        double n2 = (this.xMin == 0.0) ? weight : 0.0;
        double n3 = (this.xMin == 0.0) ? this.bodytraj[0].getFatPercent(baseline) : 0.0;
        double n4 = (this.xMin == 0.0) ? ((this.paramtraj[0].getCalories() + this.bodytraj[0].getTEE(baseline, this.paramtraj[0])) / 2.0) : 0.0;
        for (int i = 1; i < this.upperbodytraj.length; ++i) {
            final double weight2 = this.upperbodytraj[i].getWeight(baseline);
            final double weight3 = this.bodytraj[i].getWeight(baseline);
            final double fatPercent = this.bodytraj[i].getFatPercent(baseline);
            final double calories = this.paramtraj[i].getCalories();
            final double tee = this.bodytraj[i].getTEE(baseline, this.paramtraj[i]);
            if (weight2 > this.maxweight_kgs) {
                this.maxweight_kgs = weight2;
            }
            if (weight3 < this.maxweight_kgs) {
                this.minweight_kgs = weight3;
            }
            if (calories > n || tee > n) {
                n = Math.max(calories, tee);
            }
            if (i >= this.xMin && i <= this.xMax) {
                n2 += weight3;
                n3 += fatPercent;
                n4 += (calories + tee) / 2.0;
                if (weight2 > this.maxweight_inrange) {
                    this.maxweight_inrange = weight2;
                }
                if (weight3 < this.minweight_inrange) {
                    this.minweight_inrange = weight3;
                }
            }
        }
        this.MainPanel.getBaseline().getnewBMI(this.minweight_kgs);
        this.averageweight_inrange = n2 / (int)(this.xMax - this.xMin + 1.0);
        this.averagebfp_inrange = n3 / (int)(this.xMax - this.xMin + 1.0);
        this.averageenergy_inrange = n4 / (int)(this.xMax - this.xMin + 1.0);
        this.maxweight_fordisplay = this.maxweight_kgs + 10.0;
        this.maxenergy_fordisplay = n + 1000.0;
    }
    
    public double getminweight_kgs() {
        return this.minweight_kgs;
    }
    
    public void setweightunits(final double weightunits) {
        this.graphedweightMax /= this.weightunits;
        this.graphedweightMin /= this.weightunits;
        if (weightunits == 1.0 || weightunits == 2.20462) {
            this.weightunits = weightunits;
        }
        if (this.weightunits == 1.0) {
            this.weightunitsstring = "kg";
        }
        else {
            this.weightunitsstring = "lbs";
        }
        this.columnNames = new String[] { "Day", "Weight (" + this.weightunitsstring + ")", "Upper weight (" + this.weightunitsstring + ")", "Lower weight (" + this.weightunitsstring + ")", "Body Fat %", "BMI", "Fat Mass (" + this.weightunitsstring + ")", "Fat Free Mass (" + this.weightunitsstring + ")", "Intake (" + this.energyunitsstring + "/day)", "Expenditure (" + this.energyunitsstring + "/day)" };
        this.graphedweightMax *= this.weightunits;
        this.graphedweightMin *= this.weightunits;
        this.repaint();
    }
    
    public void setenergyunits(final double energyunits) {
        this.graphedenergyMax /= this.energyunits;
        this.graphedenergyMin /= this.energyunits;
        if (energyunits == 1.0 || energyunits == 4.184) {
            this.energyunits = energyunits;
        }
        if (this.energyunits == 1.0) {
            this.energyunitsstring = "Calories";
        }
        else {
            this.energyunitsstring = "kJ";
        }
        this.columnNames = new String[] { "Day", "Weight (" + this.weightunitsstring + ")", "Upper weight (" + this.weightunitsstring + ")", "Lower weight (" + this.weightunitsstring + ")", "Body Fat %", "BMI", "Fat Mass (" + this.weightunitsstring + ")", "Fat Free Mass (" + this.weightunitsstring + ")", "Intake (" + this.energyunitsstring + "/day)", "Expenditure (" + this.energyunitsstring + "/day)" };
        this.graphedenergyMax *= this.energyunits;
        this.graphedenergyMin *= this.energyunits;
        this.repaint();
    }
    
    public void calculate(final Baseline baseline, final Intervention intervention, final Intervention intervention2, final double simlength) throws Exception {
        this.simlength = simlength;
        this.paramtraj = DailyParams.makeparamtrajectory(baseline, intervention, intervention2, simlength);
        final Baseline baseline2 = (Baseline)baseline.clone();
        baseline2.setdeltaE(this.calorie_spread);
        final Baseline baseline3 = (Baseline)baseline.clone();
        baseline3.setdeltaE(-this.calorie_spread);
        this.bodytraj = new BodyModel[(int)simlength];
        this.upperbodytraj = new BodyModel[(int)simlength];
        this.lowerbodytraj = new BodyModel[(int)simlength];
        this.bodytraj[0] = new BodyModel(baseline);
        this.upperbodytraj[0] = new BodyModel(baseline2);
        this.lowerbodytraj[0] = new BodyModel(baseline3);
        final boolean b = true;
        for (int n = 1; n < simlength; ++n) {
            final DailyParams dailyParams = this.paramtraj[n];
            if (b) {
                this.bodytraj[n] = BodyModel.RungeKatta(this.bodytraj[n - 1], baseline, dailyParams);
                this.upperbodytraj[n] = BodyModel.RungeKatta(this.upperbodytraj[n - 1], baseline2, dailyParams);
                this.lowerbodytraj[n] = BodyModel.RungeKatta(this.lowerbodytraj[n - 1], baseline3, dailyParams);
            }
            else {
                this.bodytraj[n] = BodyModel.Euler(this.bodytraj[n - 1], baseline, this.paramtraj[n]);
                this.upperbodytraj[n] = BodyModel.Euler(this.upperbodytraj[n - 1], baseline2, dailyParams);
                this.lowerbodytraj[n] = BodyModel.Euler(this.lowerbodytraj[n - 1], baseline3, dailyParams);
            }
        }
        this.reset_max();
    }
    
    public double getTEE(final int n) {
        return this.bodytraj[n].getTEE(this.MainPanel.getBaseline(), this.paramtraj[n]);
    }
    
    private void drawXtics(final Graphics graphics, final double n, final double n2, final int n3, final int n4) {
        graphics.setColor(this.graphforeground);
        graphics.setFont(new Font("Helvetica", 0, this.fontSize));
        int n5 = (int)n;
        final double n6 = (n3 - this.yaxis_space) / (n2 - n);
        final int length = String.valueOf((int)n2).length();
        double n7 = this.yaxis_space;
        double n8 = 0.0;
        while (n7 <= n3) {
            if (n7 - n8 > this.fontSize * length + 1 || n7 == 0.0) {
                graphics.setColor(this.graphforeground);
                graphics.drawLine((int)n7, n4 - this.xaxis_space, (int)n7, n4 - 4 - this.xaxis_space);
                graphics.drawString(String.valueOf(n5), (int)n7 + 1, n4 - this.xaxis_space + this.fontSize);
                if (this.showgrid && n7 != this.yaxis_space) {
                    graphics.setColor(this.graphgridlines);
                    int i = n4 - this.xaxis_space - this.gridspacing;
                    do {
                        graphics.drawLine((int)n7, i, (int)n7, i - this.gridspacing);
                        i -= 1 * this.gridspacing;
                    } while (i > 0);
                }
                n8 = n7;
            }
            n7 += n6;
            ++n5;
        }
    }
    
    private void drawYtics(final Graphics graphics, final double n, final double n2, final double n3, final int n4, final int n5) {
        graphics.setColor(this.graphforeground);
        graphics.setFont(new Font("Helvetica", 0, this.fontSize));
        int n6 = n4;
        final double abs = Math.abs(n2 - n);
        double n7;
        if (abs < 1.0) {
            n7 = 1.0;
        }
        else if (abs >= 1.0 && abs < 15.0) {
            n7 = 1.0;
        }
        else if (abs >= 15.0 && abs < 30.0) {
            n7 = 2.0;
        }
        else if (abs >= 30.0 && abs < 60.0) {
            n7 = 5.0;
        }
        else if (abs >= 60.0 && abs < 150.0) {
            n7 = 10.0;
        }
        else if (abs >= 150.0 && abs < 300.0) {
            n7 = 20.0;
        }
        else if (abs >= 300.0 && abs < 600.0) {
            n7 = 50.0;
        }
        else if (abs >= 600.0 && abs < 1000.0) {
            n7 = 100.0;
        }
        else if (abs >= 1000.0 && abs < 2000.0) {
            n7 = 200.0;
        }
        else if (abs >= 2000.0 && abs < 5000.0) {
            n7 = 500.0;
        }
        else if (abs >= 5000.0 && abs < 10000.0) {
            n7 = 1000.0;
        }
        else if (abs >= 10000.0 && abs < 20000.0) {
            n7 = 2000.0;
        }
        else if (abs >= 20000.0 && abs < 50000.0) {
            n7 = 5000.0;
        }
        else {
            n7 = 10000.0;
        }
        final double n8 = n - n % n7 + n7;
        final double n9 = n3 * (n8 - n);
        final String value = String.valueOf((int)n2);
        value.length();
        System.out.println("y_tic inc=" + n7 + ", maxysting=" + value);
        final double n10 = graphics.getFontMetrics().stringWidth(value);
        for (double n11 = n4 - this.xaxis_space - n9, n12 = n8; n11 > 0.0; n11 -= n7 * n3, n12 += n7) {
            graphics.setColor(this.graphforeground);
            graphics.drawLine((int)this.x0, (int)n11, (int)this.x0 + 4, (int)n11);
            if (n6 - n11 > 10.0 * n3 + 1.0) {
                n6 = (int)n11;
                graphics.drawString(String.valueOf((int)n12), (int)(this.x0 - n10), (int)n11 - 1);
            }
            else {
                graphics.drawString(String.valueOf((int)n12), (int)(this.x0 - n10), (int)n11 - 1);
            }
            if (this.showgrid && n11 != n4 - this.xaxis_space) {
                graphics.setColor(this.graphgridlines);
                int i = this.yaxis_space + this.gridspacing;
                do {
                    graphics.drawLine(i, (int)n11, i + this.gridspacing, (int)n11);
                    i += 1 * this.gridspacing;
                } while (i < n5);
            }
        }
    }
    
    private void drawAxes(final Graphics graphics, final int n, final int n2, final String s) {
        graphics.setColor(this.graphforeground);
        final String s2 = "Time (days)";
        graphics.drawLine((int)this.x0, n - this.xaxis_space, n2, n - this.xaxis_space);
        graphics.drawString(s2, (n2 - this.yaxis_space - s2.length()) / 2, n - 2);
        final Font font = graphics.getFont();
        graphics.setFont(this.rotatedfont);
        graphics.drawLine((int)this.x0, n - this.xaxis_space, (int)this.x0, 0);
        graphics.drawString(s, 15, n / 2);
        graphics.setFont(font);
    }
    
    private void drawlegend(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.graphbackground);
        graphics.fillRect(n - this.legendwidth - this.legendgap, this.legendgap, this.legendwidth, this.legendheight);
        graphics.setColor(this.graphforeground);
        graphics.drawRect(n - this.legendwidth - this.legendgap, this.legendgap, this.legendwidth, this.legendheight);
        final int n3 = n - this.legendwidth - this.legendgap;
        graphics.setColor(this.graphforeground);
        graphics.drawString("Upper Estimate", n3 + 60, this.legendgap + 20);
        graphics.drawString("Best Estimate", n3 + 60, this.legendgap + 40);
        graphics.drawString("Lower Estimate", n3 + 60, this.legendgap + 60);
        graphics.setColor(this.MainPanel.defaultcolors() ? this.uppercurvedefault : this.uppercurvealt);
        graphics.drawLine(n3 + 20, this.legendgap + 20 - 3, n3 + 40, this.legendgap + 20 - 3);
        final Stroke stroke = ((Graphics2D)graphics).getStroke();
        ((Graphics2D)graphics).setStroke(new BasicStroke(1.5f, 1, 1));
        graphics.setColor(this.maincurve);
        graphics.drawLine(n3 + 20, this.legendgap + 40 - 3, n3 + 40, this.legendgap + 40 - 3);
        ((Graphics2D)graphics).setStroke(stroke);
        graphics.setColor(this.MainPanel.defaultcolors() ? this.lowercurvedefault : this.lowercurvealt);
        graphics.drawLine(n3 + 20, this.legendgap + 60 - 3, n3 + 40, this.legendgap + 60 - 3);
    }
    
    private void initComponents() {
        this.setLayout(new GridBagLayout());
    }
    
    public class WeightPanel extends JPanel
    {
        private int wpanel_width;
        private int wpanel_height;
        private int wpanel_graph_width;
        private int wpanel_graph_height;
        private double weight_x_stretch;
        private double weight_y_stretch;
        private String weight_ylabel;
        
        public WeightPanel() {
            this.weight_ylabel = "Weight (" + GraphPanel.this.weightunitsstring + ")";
        }
        
        public void paintComponent(final Graphics graphics) {
            GraphPanel.this.yaxis_space = 10 + GraphPanel.this.rotatedfont.getSize() + 2 * GraphPanel.this.fontSize;
            GraphPanel.this.x0 = GraphPanel.this.yaxis_space;
            GraphPanel.this.y0 = GraphPanel.this.height - GraphPanel.this.xaxis_space;
            this.wpanel_width = this.getWidth();
            this.wpanel_height = this.getHeight();
            this.wpanel_graph_width = this.wpanel_width - GraphPanel.this.yaxis_space;
            this.wpanel_graph_height = this.wpanel_height - GraphPanel.this.xaxis_space;
            this.weight_y_stretch = this.wpanel_graph_height / (GraphPanel.this.graphedweightMax - GraphPanel.this.graphedweightMin);
            System.out.println("Weight graph max=" + GraphPanel.this.graphedweightMax + ", min=" + GraphPanel.this.graphedweightMin + "");
            this.weight_x_stretch = this.wpanel_graph_width / (GraphPanel.this.xMax - GraphPanel.this.xMin);
            this.weight_ylabel = "Weight (" + GraphPanel.this.weightunitsstring + ")";
            final Image image = this.createImage(this.getWidth(), this.getHeight());
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(GraphPanel.this.graphbackground);
            graphics2.fillRect(0, 0, this.wpanel_width, this.wpanel_height);
            GraphPanel.this.drawAxes(graphics2, this.wpanel_height, this.wpanel_width, this.weight_ylabel);
            GraphPanel.this.drawXtics(graphics2, GraphPanel.this.xMin, GraphPanel.this.xMax, this.wpanel_width, this.wpanel_height);
            GraphPanel.this.drawYtics(graphics2, GraphPanel.this.graphedweightMin, GraphPanel.this.graphedweightMax, this.weight_y_stretch, this.wpanel_height, this.wpanel_width);
            this.drawWeight(graphics2);
            graphics.drawImage(image, 0, 0, this);
        }
        
        private void drawWeight(final Graphics graphics) {
            final Stroke stroke = ((Graphics2D)graphics).getStroke();
            ((Graphics2D)graphics).setStroke(new BasicStroke(1.5f, 1, 1));
            final Baseline baseline = GraphPanel.this.MainPanel.getBaseline();
            final double n = GraphPanel.this.yaxis_space;
            final double n2 = this.wpanel_height - GraphPanel.this.xaxis_space;
            double n3 = n;
            double n4 = n2 - (GraphPanel.this.bodytraj[(int)GraphPanel.this.xMin].getWeight(baseline) - GraphPanel.this.graphedweightMin / GraphPanel.this.weightunits) * GraphPanel.this.weightunits * this.weight_y_stretch;
            double n5 = n2 - (GraphPanel.this.upperbodytraj[(int)GraphPanel.this.xMin].getWeight(baseline) * GraphPanel.this.weightunits - GraphPanel.this.graphedweightMin) * this.weight_y_stretch;
            double n6 = n2 - (GraphPanel.this.lowerbodytraj[(int)GraphPanel.this.xMin].getWeight(baseline) * GraphPanel.this.weightunits - GraphPanel.this.graphedweightMin) * this.weight_y_stretch;
            for (int i = (int)GraphPanel.this.xMin + 1; i <= (int)GraphPanel.this.xMax; ++i) {
                final double weight = GraphPanel.this.bodytraj[i].getWeight(baseline);
                final double weight2 = GraphPanel.this.upperbodytraj[i].getWeight(baseline);
                final double weight3 = GraphPanel.this.lowerbodytraj[i].getWeight(baseline);
                final double n7 = n + (i - GraphPanel.this.xMin) * this.weight_x_stretch;
                final double n8 = n2 - (weight * GraphPanel.this.weightunits - GraphPanel.this.graphedweightMin) * this.weight_y_stretch;
                final double n9 = n2 - (weight2 * GraphPanel.this.weightunits - GraphPanel.this.graphedweightMin) * this.weight_y_stretch;
                final double n10 = n2 - (weight3 * GraphPanel.this.weightunits - GraphPanel.this.graphedweightMin) * this.weight_y_stretch;
                if (n8 < this.wpanel_height - GraphPanel.this.xaxis_space) {
                    ((Graphics2D)graphics).setStroke(new BasicStroke(1.5f, 1, 1));
                    graphics.setColor(GraphPanel.this.maincurve);
                    graphics.drawLine((int)n3, (int)n4, (int)n7, (int)n8);
                    ((Graphics2D)graphics).setStroke(stroke);
                }
                if (GraphPanel.this.showspread) {
                    if (n9 < this.wpanel_height - GraphPanel.this.xaxis_space) {
                        graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? GraphPanel.this.uppercurvedefault : GraphPanel.this.uppercurvealt);
                        graphics.drawLine((int)n3, (int)n5, (int)n7, (int)n9);
                    }
                    if (n10 < this.wpanel_height - GraphPanel.this.xaxis_space) {
                        graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? GraphPanel.this.lowercurvedefault : GraphPanel.this.lowercurvealt);
                        graphics.drawLine((int)n3, (int)n6, (int)n7, (int)n10);
                    }
                }
                n4 = n8;
                n3 = n7;
                n5 = n9;
                n6 = n10;
            }
            if (GraphPanel.this.showspread && GraphPanel.this.showlegend) {
                GraphPanel.this.drawlegend(graphics, this.wpanel_width, this.wpanel_height);
            }
        }
    }
    
    public class BfpPanel extends JPanel
    {
        public int bpanel_width;
        public int bpanel_height;
        private double bfp_x_stretch;
        private double bfp_y_stretch;
        private int bpanel_graph_width;
        private int bpanel_graph_height;
        String bfp_ylabel;
        
        public BfpPanel() {
            this.bfp_ylabel = "Body Fat %";
        }
        
        public void paintComponent(final Graphics graphics) {
            GraphPanel.this.yaxis_space = 3 + graphics.getFont().getSize() + GraphPanel.this.fontSize * String.valueOf((int)GraphPanel.this.simlength).length();
            GraphPanel.this.x0 = GraphPanel.this.yaxis_space;
            GraphPanel.this.y0 = GraphPanel.this.height - GraphPanel.this.xaxis_space;
            this.bpanel_width = this.getWidth();
            this.bpanel_height = this.getHeight();
            this.bpanel_graph_width = this.bpanel_width - GraphPanel.this.yaxis_space;
            this.bpanel_graph_height = this.bpanel_height - GraphPanel.this.xaxis_space;
            this.bfp_x_stretch = this.bpanel_graph_width / (GraphPanel.this.xMax - GraphPanel.this.xMin);
            this.bfp_y_stretch = this.bpanel_graph_height / (GraphPanel.this.graphedbfpMax - GraphPanel.this.graphedbfpMin);
            final Image image = this.createImage(this.bpanel_width, this.bpanel_height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(GraphPanel.this.graphbackground);
            graphics2.fillRect(0, 0, this.bpanel_width, this.bpanel_height);
            GraphPanel.this.drawAxes(graphics2, this.bpanel_height, this.bpanel_width, this.bfp_ylabel);
            GraphPanel.this.drawXtics(graphics2, GraphPanel.this.xMin, GraphPanel.this.xMax, this.bpanel_width, this.bpanel_height);
            System.out.println("Drawing Bfp Y-tics with:");
            GraphPanel.this.drawYtics(graphics2, GraphPanel.this.graphedbfpMin, GraphPanel.this.graphedbfpMax, this.bfp_y_stretch, this.bpanel_height, this.bpanel_width);
            this.drawBfp(graphics2);
            graphics.drawImage(image, 0, 0, this);
        }
        
        private void drawBfp(final Graphics graphics) {
            final Baseline baseline = GraphPanel.this.MainPanel.getBaseline();
            final Stroke stroke = ((Graphics2D)graphics).getStroke();
            final double n = GraphPanel.this.yaxis_space;
            final double n2 = this.bpanel_height - GraphPanel.this.xaxis_space;
            double n3 = n;
            double n4 = n2 - (GraphPanel.this.bodytraj[(int)GraphPanel.this.xMin].getFatPercent(baseline) - GraphPanel.this.graphedbfpMin) * this.bfp_y_stretch;
            double n5 = n2 - (GraphPanel.this.upperbodytraj[(int)GraphPanel.this.xMin].getFatPercent(baseline) - GraphPanel.this.graphedbfpMin) * this.bfp_y_stretch;
            double n6 = n2 - (GraphPanel.this.lowerbodytraj[(int)GraphPanel.this.xMin].getFatPercent(baseline) - GraphPanel.this.graphedbfpMin) * this.bfp_y_stretch;
            for (int n7 = (int)GraphPanel.this.xMin; n7 <= GraphPanel.this.xMax; ++n7) {
                final double n8 = n + (n7 - GraphPanel.this.xMin) * this.bfp_x_stretch;
                final double n9 = n2 - (GraphPanel.this.bodytraj[n7].getFatPercent(baseline) - GraphPanel.this.graphedbfpMin) * this.bfp_y_stretch;
                final double n10 = n2 - (GraphPanel.this.upperbodytraj[n7].getFatPercent(baseline) - GraphPanel.this.graphedbfpMin) * this.bfp_y_stretch;
                final double n11 = n2 - (GraphPanel.this.lowerbodytraj[n7].getFatPercent(baseline) - GraphPanel.this.graphedbfpMin) * this.bfp_y_stretch;
                if (n9 < this.bpanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.maincurve);
                    ((Graphics2D)graphics).setStroke(new BasicStroke(1.5f, 1, 1));
                    graphics.drawLine((int)n3, (int)n4, (int)n8, (int)n9);
                    ((Graphics2D)graphics).setStroke(stroke);
                }
                if (GraphPanel.this.showspread) {
                    if (n10 < this.bpanel_height - GraphPanel.this.xaxis_space) {
                        graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? GraphPanel.this.uppercurvedefault : GraphPanel.this.uppercurvealt);
                        graphics.drawLine((int)n3, (int)n5, (int)n8, (int)n10);
                    }
                    if (n11 < this.bpanel_height - GraphPanel.this.xaxis_space) {
                        graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? GraphPanel.this.lowercurvedefault : GraphPanel.this.lowercurvealt);
                        graphics.drawLine((int)n3, (int)n6, (int)n8, (int)n11);
                    }
                }
                n4 = n9;
                n5 = n10;
                n6 = n11;
                n3 = n8;
            }
            if (GraphPanel.this.showspread && GraphPanel.this.showlegend) {
                GraphPanel.this.drawlegend(graphics, this.bpanel_width, this.bpanel_height);
            }
        }
    }
    
    public class EnergyPanel extends JPanel
    {
        private int epanel_width;
        private int epanel_height;
        private int epanel_graph_width;
        private int epanel_graph_height;
        private double energy_x_stretch;
        private double energy_y_stretch;
        private String energy_ylabel;
        private Color intakedefault;
        private Color intakealt;
        private Color expenddefault;
        private Color expendalt;
        
        public EnergyPanel() {
            this.energy_ylabel = "Intake and Expenditure (" + GraphPanel.this.energyunitsstring + "/day)";
            this.intakedefault = GraphPanel.this.colors[0];
            this.intakealt = Color.BLUE;
            this.expenddefault = GraphPanel.this.colors[7];
            this.expendalt = GraphPanel.this.colors[7];
        }
        
        public void paintComponent(final Graphics graphics) {
            GraphPanel.this.yaxis_space = 10 + GraphPanel.this.rotatedfont.getSize() + 4 * GraphPanel.this.fontSize;
            GraphPanel.this.x0 = GraphPanel.this.yaxis_space;
            GraphPanel.this.y0 = GraphPanel.this.height - GraphPanel.this.xaxis_space;
            this.epanel_width = this.getWidth();
            this.epanel_height = this.getHeight();
            this.epanel_graph_width = this.epanel_width - GraphPanel.this.yaxis_space;
            this.epanel_graph_height = this.epanel_height - GraphPanel.this.xaxis_space;
            this.energy_y_stretch = this.epanel_graph_height / (GraphPanel.this.graphedenergyMax - GraphPanel.this.graphedenergyMin);
            System.out.println("energy graph max=" + GraphPanel.this.graphedenergyMax + ", min=" + GraphPanel.this.graphedenergyMin + "");
            this.energy_x_stretch = this.epanel_graph_width / (GraphPanel.this.xMax - GraphPanel.this.xMin);
            this.energy_ylabel = GraphPanel.this.energyunitsstring + "/day";
            final Image image = this.createImage(this.getWidth(), this.getHeight());
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(GraphPanel.this.graphbackground);
            graphics2.fillRect(0, 0, this.epanel_width, this.epanel_height);
            GraphPanel.this.drawAxes(graphics2, this.epanel_height, this.epanel_width, this.energy_ylabel);
            GraphPanel.this.drawXtics(graphics2, GraphPanel.this.xMin, GraphPanel.this.xMax, this.epanel_width, this.epanel_height);
            GraphPanel.this.drawYtics(graphics2, GraphPanel.this.graphedenergyMin, GraphPanel.this.graphedenergyMax, this.energy_y_stretch, this.epanel_height, this.epanel_width);
            this.drawEnergy(graphics2);
            graphics.drawImage(image, 0, 0, this);
        }
        
        private void drawEnergy(final Graphics graphics) {
            GraphPanel.this.MainPanel.getBaseline();
            final double n = GraphPanel.this.yaxis_space;
            final double n2 = this.epanel_height - GraphPanel.this.xaxis_space;
            double n3 = n;
            double n4 = n2 - (GraphPanel.this.paramtraj[(int)GraphPanel.this.xMin].getCalories() * GraphPanel.this.energyunits - GraphPanel.this.graphedenergyMin) * this.energy_y_stretch;
            double n5 = n2 - (GraphPanel.this.getTEE((int)GraphPanel.this.xMin) * GraphPanel.this.energyunits - GraphPanel.this.graphedenergyMin) * this.energy_y_stretch;
            final Stroke stroke = ((Graphics2D)graphics).getStroke();
            ((Graphics2D)graphics).setStroke(new BasicStroke(1.5f, 1, 1));
            for (int i = (int)GraphPanel.this.xMin + 1; i <= (int)GraphPanel.this.xMax; ++i) {
                final double n6 = n + (i - GraphPanel.this.xMin) * this.energy_x_stretch;
                final double n7 = n2 - (GraphPanel.this.paramtraj[i].getCalories() * GraphPanel.this.energyunits - GraphPanel.this.graphedenergyMin) * this.energy_y_stretch;
                final double n8 = n2 - (GraphPanel.this.getTEE(i) * GraphPanel.this.energyunits - GraphPanel.this.graphedenergyMin) * this.energy_y_stretch;
                if (n4 < this.epanel_height - GraphPanel.this.xaxis_space && n7 < this.epanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.intakedefault : this.intakealt);
                    graphics.drawLine((int)n3, (int)n4, (int)n6, (int)n7);
                }
                else if (n4 < this.epanel_height - GraphPanel.this.xaxis_space && n7 >= this.epanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.intakedefault : this.intakealt);
                    graphics.drawLine((int)n3, (int)n4, (int)n6, this.epanel_height - GraphPanel.this.xaxis_space - 1);
                }
                else if (n4 >= this.epanel_height - GraphPanel.this.xaxis_space && n7 < this.epanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.intakedefault : this.intakealt);
                    graphics.drawLine((int)n3, this.epanel_height - GraphPanel.this.xaxis_space - 1, (int)n6, (int)n7);
                }
                else if (n4 == this.epanel_height - GraphPanel.this.xaxis_space && n7 == this.epanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.intakedefault : this.intakealt);
                    graphics.drawLine((int)n3, (int)n4 - 1, (int)n6, (int)n7 - 1);
                }
                if (n8 < this.epanel_height - GraphPanel.this.xaxis_space && n5 < this.epanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.expenddefault : this.expendalt);
                    graphics.drawLine((int)n3, (int)n5, (int)n6, (int)n8);
                }
                else if (n5 == this.epanel_height - GraphPanel.this.xaxis_space && n8 == this.epanel_height - GraphPanel.this.xaxis_space) {
                    graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.expenddefault : this.expendalt);
                    graphics.drawLine((int)n3, (int)n5 - 1, (int)n6, (int)n8 - 1);
                }
                n4 = n7;
                n5 = n8;
                n3 = n6;
            }
            ((Graphics2D)graphics).setStroke(stroke);
            if (GraphPanel.this.showlegend) {
                this.drawenergylegend(graphics, this.epanel_width, this.epanel_height);
            }
        }
        
        private void drawenergylegend(final Graphics graphics, final int n, final int n2) {
            graphics.setColor(GraphPanel.this.graphbackground);
            graphics.fillRect(n - GraphPanel.this.legendwidth - GraphPanel.this.legendgap, GraphPanel.this.legendgap, GraphPanel.this.legendwidth, GraphPanel.this.legendheight - 30);
            graphics.setColor(GraphPanel.this.graphforeground);
            graphics.drawRect(n - GraphPanel.this.legendwidth - GraphPanel.this.legendgap, GraphPanel.this.legendgap, GraphPanel.this.legendwidth, GraphPanel.this.legendheight - 30);
            final int n3 = n - GraphPanel.this.legendwidth - GraphPanel.this.legendgap;
            graphics.setColor(GraphPanel.this.graphforeground);
            graphics.drawString("Intake", n3 + 60, GraphPanel.this.legendgap + 20);
            graphics.drawString("Expenditure", n3 + 60, GraphPanel.this.legendgap + 40);
            final Stroke stroke = ((Graphics2D)graphics).getStroke();
            ((Graphics2D)graphics).setStroke(new BasicStroke(1.5f, 1, 1));
            graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.intakedefault : this.intakealt);
            graphics.drawLine(n3 + 20, GraphPanel.this.legendgap + 20 - 3, n3 + 40, GraphPanel.this.legendgap + 20 - 3);
            graphics.setColor(GraphPanel.this.MainPanel.defaultcolors() ? this.expenddefault : this.expendalt);
            graphics.drawLine(n3 + 20, GraphPanel.this.legendgap + 40 - 3, n3 + 40, GraphPanel.this.legendgap + 40 - 3);
            ((Graphics2D)graphics).setStroke(stroke);
        }
    }
}
