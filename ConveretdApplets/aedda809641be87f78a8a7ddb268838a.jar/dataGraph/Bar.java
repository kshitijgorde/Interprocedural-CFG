// 
// Decompiled by Procyon v0.5.30
// 

package dataGraph;

import java.awt.Graphics;
import edu.davidson.tools.SDataSource;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Panel;
import edu.davidson.display.SNumber;
import java.awt.Color;
import java.awt.BorderLayout;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SDataListener;
import edu.davidson.tools.SApplet;

public class Bar extends SApplet implements SDataListener
{
    boolean isStandalone;
    double min;
    double max;
    boolean vert;
    int numBars;
    EtchedBorder etchedBorder;
    BorderLayout borderLayout1;
    double value;
    Color negColor;
    Color posColor;
    BarGraph bar;
    BorderLayout borderLayout2;
    SNumber valField;
    boolean showControls;
    boolean autoScale;
    Panel panel1;
    Label barLabel;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public Bar() {
        this.isStandalone = false;
        this.numBars = 1;
        this.etchedBorder = new EtchedBorder();
        this.borderLayout1 = new BorderLayout();
        this.value = 0.0;
        this.negColor = Color.blue;
        this.posColor = Color.red;
        this.bar = new BarGraph();
        this.borderLayout2 = new BorderLayout();
        this.valField = new SNumber();
        this.showControls = false;
        this.autoScale = false;
        this.panel1 = new Panel();
        this.barLabel = new Label();
    }
    
    public void init() {
        int int1 = 10;
        String parameter = "";
        try {
            this.min = Double.valueOf(this.getParameter("Min", "0"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.max = Double.valueOf(this.getParameter("Max", "100"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.value = Double.valueOf(this.getParameter("Value", "10"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.vert = Boolean.valueOf(this.getParameter("Vertical", "true"));
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.autoScale = Boolean.valueOf(this.getParameter("AutoScale", "false"));
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
        try {
            this.showControls = Boolean.valueOf(this.getParameter("ShowControls", "true"));
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
        try {
            int1 = Integer.parseInt(this.getParameter("BarWidth", "10"));
        }
        catch (Exception ex7) {
            ex7.printStackTrace();
        }
        try {
            this.numBars = Integer.parseInt(this.getParameter("NumSeries", "1"));
        }
        catch (Exception ex8) {
            ex8.printStackTrace();
        }
        try {
            parameter = this.getParameter("Label", "");
        }
        catch (Exception ex9) {
            ex9.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception ex10) {
            ex10.printStackTrace();
        }
        if (!this.showControls) {
            this.panel1.setVisible(false);
        }
        this.valField.setValue(this.value);
        this.bar.barWidth = int1;
        if (!parameter.equals("")) {
            this.barLabel.setText(parameter);
            this.barLabel.setVisible(true);
        }
        SApplet.addDataListener(this);
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout1);
        this.bar.setLayout(this.borderLayout2);
        this.valField.setEditable(false);
        this.barLabel.setVisible(false);
        this.barLabel.setAlignment(2);
        this.add(this.etchedBorder, "Center");
        this.add(this.panel1, "South");
        this.panel1.add(this.barLabel, null);
        this.panel1.add(this.valField, null);
        this.etchedBorder.add(this.bar, "Center");
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Min", "double", "Minimum value" }, { "Max", "double", "Maximum value" }, { "Vertical", "boolean", "Orientation" } };
    }
    
    public void setAutoscale(final boolean autoScale) {
        this.autoScale = autoScale;
        this.bar.repaint();
    }
    
    public void setLabel(final String text) {
        this.barLabel.setText(text);
        if (text.equals("")) {
            this.barLabel.setVisible(false);
        }
        this.invalidate();
        this.validate();
    }
    
    public void setBarWidth(final int barWidth) {
        this.bar.barWidth = barWidth;
        this.bar.repaint();
    }
    
    public void setMax(final double max) {
        this.max = max;
        this.bar.repaint();
    }
    
    public void setMin(final double min) {
        this.min = min;
        this.bar.repaint();
    }
    
    public void setValue(final double n) {
        if (this.autoScale) {
            if (n > this.max) {
                this.max = n;
            }
            if (n < this.min) {
                this.min = n;
            }
        }
        this.value = n;
        this.valField.setValue(n);
        this.bar.repaint();
    }
    
    public void setPosRGB(final int n, final int n2, final int n3) {
        this.posColor = new Color(n, n2, n3);
    }
    
    public void setNegRGB(final int n, final int n2, final int n3) {
        this.negColor = new Color(n, n2, n3);
    }
    
    public void setNumericFormat(final String format) {
        this.valField.setFormat(format);
    }
    
    public void addDatum(final int n, final double value, final double n2) {
        this.setValue(value);
    }
    
    public void addDatum(final SDataSource sDataSource, final int n, final double value, final double n2) {
        this.setValue(value);
    }
    
    public void addData(final int n, final double[] array, final double[] array2) {
        if (array == null) {
            return;
        }
        final int length = array.length;
        if (length < 1) {
            return;
        }
        this.setValue(array[length - 1]);
    }
    
    public void addData(final SDataSource sDataSource, final int n, final double[] array, final double[] array2) {
        this.addData(n, array, array2);
    }
    
    public void deleteSeries(final int n) {
        this.setValue(0.0);
    }
    
    public void clearSeries(final int n) {
        this.setValue(0.0);
    }
    
    public void setOwner(final SApplet sApplet) {
    }
    
    public SApplet getOwner() {
        return this;
    }
    
    class BarGraph extends Panel
    {
        int barWidth;
        
        BarGraph() {
            this.barWidth = 10;
        }
        
        public void paint(final Graphics graphics) {
            double n = 0.0;
            if (Bar.this.min <= 0 && Bar.this.max > 0) {
                n = 0.0;
            }
            final int width = this.getBounds().width;
            final int height = this.getBounds().height;
            if (Bar.this.vert) {
                final int n2 = (int)(0.4999999 + height * Bar.this.max / (Bar.this.max - Bar.this.min));
                int n3 = (int)(height * (Bar.this.value - n) / (Bar.this.max - Bar.this.min));
                int n4;
                if (n3 >= 0) {
                    n4 = n2 - n3;
                    graphics.setColor(Bar.this.posColor);
                }
                else {
                    n4 = n2;
                    n3 = -n3;
                    graphics.setColor(Bar.this.negColor);
                }
                graphics.fillRect(width / 2 - this.barWidth, n4, 2 * this.barWidth, n3);
                graphics.setColor(Color.black);
                graphics.drawLine(0, n2, width - 1, n2);
            }
            else {
                final int n5 = (int)(0.4999999 - width * Bar.this.min / (Bar.this.max - Bar.this.min));
                int n6 = (int)(width * (Bar.this.value - n) / (Bar.this.max - Bar.this.min));
                int n7;
                if (n6 >= 0) {
                    n7 = n5;
                    graphics.setColor(Bar.this.posColor);
                }
                else {
                    n6 = -n6;
                    n7 = n5 - n6;
                    graphics.setColor(Bar.this.negColor);
                }
                graphics.fillRect(n7, height / 2 - this.barWidth, n6, 2 * this.barWidth);
                graphics.setColor(Color.black);
                graphics.drawLine(n5, 0, n5, height - 1);
            }
        }
    }
}
