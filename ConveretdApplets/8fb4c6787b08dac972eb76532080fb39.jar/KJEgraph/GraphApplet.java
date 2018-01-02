// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import KJEcalculation.Calculation;
import KJEgui.Nbr;
import javax.swing.JApplet;

public class GraphApplet extends JApplet
{
    Graph gGraph;
    
    public double getParameter(final String s, final double n) {
        double double1 = n;
        if (this.getParameter(s) != null) {
            try {
                double1 = Nbr.toDouble(this.getParameter(s));
            }
            catch (NumberFormatException ex) {
                double1 = n;
            }
        }
        return double1;
    }
    
    public int getParameter(final String s, final int n) {
        int int1 = n;
        if (this.getParameter(s) != null) {
            try {
                int1 = Integer.parseInt(this.getParameter(s));
            }
            catch (NumberFormatException ex) {
                int1 = n;
            }
        }
        return int1;
    }
    
    public String getParameter(final String s, final String s2) {
        String replace = s2;
        if (s != null) {
            final String parameter = this.getParameter(s);
            if (parameter == null) {
                replace = s2;
            }
            else {
                replace = Calculation.replace("\\'i", "\u00ed", Calculation.replace("\\'u", "\u00fa", Calculation.replace("\\'o", "\u00f3", Calculation.replace("\\?", "¿", Calculation.replace("\\~n", "\u00f1", Calculation.replace("\\'e", "\u00e9", parameter))))));
            }
        }
        return replace;
    }
    
    public boolean getParameter(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        boolean b2 = b;
        if (parameter != null) {
            final String upperCase = parameter.trim().toUpperCase();
            if ((upperCase.equals("FALSE") || upperCase.equals("NO")) && b) {
                b2 = false;
            }
            else if ((upperCase.equals("TRUE") || upperCase.equals("YES")) && !b) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public void init() {
        this.gGraph = new Graph(new GraphLine(), this.loadImage(this.getParameter("background_image", "background.jpg")));
        this.gGraph.FONT_TITLE = Font.decode(this.getParameter("titleFont", ""));
        this.gGraph.FONT_BOLD = Font.decode(this.getParameter("boldFont", ""));
        this.gGraph.FONT_PLAIN = Font.decode(this.getParameter("plainFont", ""));
        this.gGraph.setProperty(this.getParameter("graph", ""));
        this.gGraph.setBackground(Color.decode("#" + this.getParameter("PAGEBACKGROUND_COLOR", "FFFFF")));
        this.gGraph._legend.setProperty(this.getParameter("legend", ""));
        this.gGraph._titleXAxis.setProperty(this.getParameter("titleXAxis", "X"));
        this.gGraph._titleYAxis.setProperty(this.getParameter("titleYAxis", ""));
        this.gGraph._titleGraph.setProperty(this.getParameter("titleGraph", ""));
        this.gGraph._axisX.setProperty(this.getParameter("axisX", ""));
        this.gGraph._axisY.setProperty(this.getParameter("axisY", ""));
        this.gGraph._grid.setProperty(this.getParameter("grid", ""));
        if (this.getParameter("GRAPH_FOREGROUND") != null) {
            this.gGraph.setForeground(Color.decode("#" + this.getParameter("GRAPH_FOREGROUND", "000000")));
            this.setForeground(this.gGraph.getForeground());
            this.gGraph._titleXAxis.setColor(this.gGraph.getForeground());
            this.gGraph._titleGraph.setColor(this.gGraph.getForeground());
            this.gGraph._titleYAxis.setColor(this.gGraph.getForeground());
        }
        try {
            this.gGraph.add(this.getParameter("dataProperties", ""));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.getContentPane().add("Center", this.gGraph);
        this.gGraph.dataChanged(true);
    }
    
    public Image loadImage(final String s) {
        Image image = null;
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            if (s != null) {
                image = this.getImage(this.getDocumentBase(), s);
                mediaTracker.addImage(image, 1);
                mediaTracker.waitForAll();
            }
        }
        catch (Exception ex) {
            image = null;
        }
        return image;
    }
}
