// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

public class PlotApplet extends Applet
{
    private transient Plot _myPlot;
    
    public String getAppletInfo() {
        return "PlotApplet 2.0: A data plotter.\nBy: Edward A. Lee, eal@eecs.berkeley.edu and\n Christopher Hylands, cxh@eecs.berkeley.edu\n($Id: PlotApplet.java,v 1.29 1998/11/18 07:43:10 cxh Exp $)";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "hexcolor value", "background color" }, { "foreground", "hexcolor value", "foreground color" }, { "dataurl", "url", "the URL of the data to plot" }, { "pxgraphargs", "args", "pxgraph style command line arguments" } };
    }
    
    public void init() {
        super.init();
        this.setLayout(new BorderLayout());
        if (this._myPlot == null) {
            this._myPlot = this.newPlot();
        }
        this.add("Center", this.plot());
        final String parameter = this.getParameter("width");
        int int1;
        if (parameter != null) {
            int1 = Integer.parseInt(parameter);
        }
        else {
            int1 = 400;
        }
        final String parameter2 = this.getParameter("height");
        int int2;
        if (parameter2 != null) {
            int2 = Integer.parseInt(parameter2);
        }
        else {
            int2 = 400;
        }
        this.plot().setSize(int1, int2);
        this.plot().setButtons(true);
        Color color = Color.white;
        final String parameter3 = this.getParameter("background");
        if (parameter3 != null) {
            color = PlotBox.getColorByName(parameter3);
        }
        this.setBackground(color);
        this.plot().setBackground(color);
        Color color2 = Color.black;
        final String parameter4 = this.getParameter("foreground");
        if (parameter4 != null) {
            color2 = PlotBox.getColorByName(parameter4);
        }
        this.setForeground(color2);
        this.plot().setForeground(color2);
        this.plot().setVisible(true);
        final String parameter5 = this.getParameter("pxgraphargs");
        if (parameter5 != null) {
            try {
                this.showStatus("Reading arguments");
                this.plot()._documentBase = this.getDocumentBase();
                this.plot().parsePxgraphargs(parameter5);
                this.showStatus("Done");
            }
            catch (CmdLineArgException ex) {
                System.err.println("PlotApplet: failed to parse `" + parameter5 + "': " + ex);
            }
            catch (FileNotFoundException ex2) {
                System.err.println("PlotApplet: file not found: " + ex2);
            }
            catch (IOException ex3) {
                System.err.println("PlotApplet: error reading input file: " + ex3);
            }
        }
        final String parameter6 = this.getParameter("dataurl");
        if (parameter6 != null) {
            try {
                this.showStatus("Reading data");
                this.plot().read(new URL(this.getDocumentBase(), parameter6).openStream());
                this.showStatus("Done");
            }
            catch (MalformedURLException ex4) {
                System.err.println(ex4.toString());
            }
            catch (FileNotFoundException ex5) {
                System.err.println("PlotApplet: file not found: " + ex5);
            }
            catch (IOException ex6) {
                System.err.println("PlotApplet: error reading input file: " + ex6);
            }
        }
    }
    
    public Plot newPlot() {
        return new Plot();
    }
    
    public Plot plot() {
        return this._myPlot;
    }
}
