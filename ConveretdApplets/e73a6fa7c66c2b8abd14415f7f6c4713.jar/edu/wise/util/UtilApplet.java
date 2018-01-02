// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.util;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import edu.wise.graph.StyleSheet;
import edu.wise.stats.distributions.Distribution;
import edu.wise.stats.distributions.NormalDistribution;
import java.applet.Applet;

public class UtilApplet extends Applet
{
    private Util util;
    protected static RawDists rawDists;
    protected static RawDists wtdDists;
    protected static ControlPanelHF controlPanel;
    protected static ReportPanel report;
    protected static NormalDistribution G1Dist;
    protected static NormalDistribution G2Dist;
    private static final boolean isStandalone = false;
    
    public static void main(final String[] array) {
    }
    
    public UtilApplet() {
        this.util = new Util(0.0, 1.0, 1.0, 1.0, 50.0, 100.0, 1.0, -1.0, -1.0, 1.0);
        UtilApplet.G1Dist = new NormalDistribution(this.util.getM1(), this.util.getS1());
        UtilApplet.G2Dist = new NormalDistribution(this.util.getM2(), this.util.getS2());
        UtilApplet.rawDists = new RawDists(UtilApplet.G1Dist, UtilApplet.G2Dist, this, false, "Raw Distributions");
        UtilApplet.wtdDists = new RawDists(UtilApplet.G1Dist, UtilApplet.G2Dist, this, true, "Weighted Distributions");
        UtilApplet.report = new ReportPanel(this, 200, 150);
        UtilApplet.controlPanel = new ControlPanelHF(UtilApplet.G1Dist, UtilApplet.G2Dist, this);
        System.out.println("Claremont Graduate University\nWeb Interface for Statistics Education (http://wise.cgu.edu/)\nCutting Scores Applet, v.99, updated 4/26/03\nJava Implementation: Michael.Healy@cgu.edu\nOriginal Program: Dale.Berger@cgu.edu");
        this.update();
    }
    
    public String getParameter(final String s, final String s2) {
        return (this.getParameter(s) != null) ? this.getParameter(s) : s2;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "G1Mean", "double", "G1Mean" }, { "G1Sd", "double", "G1Sd" }, { "G1N", "int", "G21" }, { "G2Mean", "double", "G2Mean" }, { "G2Sd", "double", "G2Sd" }, { "G2N", "int", "G2N" }, { "U11", "double", "U11" }, { "U12", "double", "U12" }, { "U21", "double", "U21" }, { "U22", "double", "U22" } };
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public void init() {
        try {
            this.util.setM1(Double.valueOf(this.getParameter("G1Mean", "0")));
        }
        catch (Exception ex2) {
            System.err.println("Failed to read G1Mean parameter");
        }
        try {
            this.util.setS1(Double.valueOf(this.getParameter("G1Sd", "2")));
        }
        catch (Exception ex3) {
            System.err.println("Failed to read G1Sd parameter");
        }
        try {
            this.util.setN1((int)(double)Double.valueOf(this.getParameter("G1N", "50")));
        }
        catch (Exception ex4) {
            System.err.println("Failed to read G1N parameter");
        }
        try {
            this.util.setM2(Double.valueOf(this.getParameter("G2Mean", "-1")));
        }
        catch (Exception ex5) {
            System.err.println("Failed to read G2Mean parameter");
        }
        try {
            this.util.setS2(Double.valueOf(this.getParameter("G2Sd", "2")));
        }
        catch (Exception ex6) {
            System.err.println("Failed to read G2Sd parameter");
        }
        try {
            this.util.setN2((int)(double)Double.valueOf(this.getParameter("G2N", "10")));
        }
        catch (Exception ex7) {
            System.err.println("Failed to read G2N parameter");
        }
        try {
            this.util.setu11(Double.valueOf(this.getParameter("U11", "1")));
        }
        catch (Exception ex8) {
            System.err.println("Failed to read U11 parameter");
        }
        try {
            this.util.setu12(Double.valueOf(this.getParameter("U12", "1")));
        }
        catch (Exception ex9) {
            System.err.println("Failed to read U21 parameter");
        }
        try {
            this.util.setu21(Double.valueOf(this.getParameter("U21", "1")));
        }
        catch (Exception ex10) {
            System.err.println("Failed to read U21 parameter");
        }
        try {
            this.util.setu22(Double.valueOf(this.getParameter("U22", "1")));
        }
        catch (Exception ex11) {
            System.err.println("Failed to read U22 parameter");
        }
        try {
            this.initComponents();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setBackground(StyleSheet.ENABLED_BACKGROUND);
        this.update();
    }
    
    public void initComponents() throws Exception {
        this.setLocation(new Point(0, 0));
        this.setLayout(null);
        this.setSize(new Dimension(600, 350));
        UtilApplet.rawDists.setSize(new Dimension(300, 200));
        UtilApplet.rawDists.setLocation(new Point(0, 0));
        UtilApplet.wtdDists.setSize(new Dimension(300, 200));
        UtilApplet.wtdDists.setLocation(new Point(300, 0));
        UtilApplet.controlPanel.setSize(new Dimension(400, 150));
        UtilApplet.controlPanel.setLocation(new Point(0, 200));
        UtilApplet.report.setSize(new Dimension(200, 150));
        UtilApplet.report.setLocation(new Point(400, 200));
        UtilApplet.rawDists.setVisible(true);
        UtilApplet.wtdDists.setVisible(true);
        UtilApplet.controlPanel.setVisible(true);
        UtilApplet.report.setVisible(true);
        this.add(UtilApplet.rawDists);
        this.add(UtilApplet.wtdDists);
        this.add(UtilApplet.report);
        this.add(UtilApplet.controlPanel);
        this.showStatus("Utility Applet (http://wise.cgu.edu/util/).");
    }
    
    public void update() {
        UtilApplet.rawDists.update();
        UtilApplet.wtdDists.update();
        UtilApplet.controlPanel.update();
        UtilApplet.report.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        graphics.drawRect(400, 0, 199, 199);
    }
    
    public Util getUtil() {
        return this.util;
    }
}
