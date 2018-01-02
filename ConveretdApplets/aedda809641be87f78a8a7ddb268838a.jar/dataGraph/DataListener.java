// 
// Decompiled by Procyon v0.5.30
// 

package dataGraph;

import edu.davidson.tools.SDataSource;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Label;
import edu.davidson.display.SNumber;
import java.awt.Panel;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SDataListener;
import edu.davidson.tools.SApplet;

public class DataListener extends SApplet implements SDataListener
{
    boolean isStandalone;
    int numFields;
    EtchedBorder etchedBorder1;
    Panel panel1;
    Panel panel2;
    SNumber fField;
    SNumber gField;
    Label label1;
    Label label2;
    BorderLayout borderLayout1;
    BorderLayout borderLayout2;
    BorderLayout borderLayout3;
    FlowLayout flowLayout1;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public DataListener() {
        this.isStandalone = false;
        this.etchedBorder1 = new EtchedBorder();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.fField = new SNumber();
        this.gField = new SNumber();
        this.label1 = new Label();
        this.label2 = new Label();
        this.borderLayout1 = new BorderLayout();
        this.borderLayout2 = new BorderLayout();
        this.borderLayout3 = new BorderLayout();
        this.flowLayout1 = new FlowLayout();
    }
    
    public void init() {
        try {
            this.numFields = Integer.parseInt(this.getParameter("NumFields", "2"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        SApplet.addDataListener(this);
    }
    
    public void setOwner(final SApplet sApplet) {
    }
    
    public SApplet getOwner() {
        return this;
    }
    
    private void jbInit() throws Exception {
        this.panel1.setLayout(this.borderLayout2);
        this.panel2.setLayout(this.borderLayout1);
        this.etchedBorder1.setLayout(this.borderLayout3);
        this.fField.setText("0");
        this.gField.setText("0");
        this.label1.setAlignment(1);
        this.label1.setText("Func. 2 =");
        this.label2.setAlignment(1);
        this.label2.setText("Func. 1 =");
        this.setLayout(this.flowLayout1);
        this.add(this.etchedBorder1, null);
        this.etchedBorder1.add(this.panel1, "West");
        this.panel1.add(this.label2, "West");
        this.panel1.add(this.fField, "Center");
        this.etchedBorder1.add(this.panel2, "East");
        this.panel2.add(this.label1, "West");
        this.panel2.add(this.gField, "Center");
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "NumFields", "int", "Number of output fields.  Must be 1 or 2." } };
    }
    
    public void addDatum(final int n, final double value, final double value2) {
        this.fField.setValue(value);
        this.gField.setValue(value2);
    }
    
    public void addDatum(final SDataSource sDataSource, final int n, final double n2, final double n3) {
        this.addDatum(n, n2, n3);
    }
    
    public void addData(final int n, final double[] array, final double[] array2) {
        final int length = array.length;
        this.fField.setValue(array[length - 1]);
        this.gField.setValue(array2[length - 1]);
    }
    
    public void addData(final SDataSource sDataSource, final int n, final double[] array, final double[] array2) {
        this.addData(n, array, array2);
    }
    
    public void deleteSeries(final int n) {
        this.fField.setValue(0.0);
        this.gField.setValue(0.0);
    }
    
    public void clearSeries(final int n) {
        this.fField.setValue(0.0);
        this.gField.setValue(0.0);
    }
}
