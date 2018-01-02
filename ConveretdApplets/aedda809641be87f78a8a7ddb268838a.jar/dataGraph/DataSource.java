// 
// Decompiled by Procyon v0.5.30
// 

package dataGraph;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import edu.davidson.display.SNumber;
import java.awt.Button;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SDataSource;
import edu.davidson.tools.SApplet;

public class DataSource extends SApplet implements SDataSource
{
    String[] varStrings;
    boolean isStandalone;
    double[][] variables;
    EtchedBorder etchedBorder1;
    Button addBtn;
    SNumber yField;
    FlowLayout flowLayout1;
    Button clearbtn;
    BorderLayout borderLayout1;
    SNumber xField;
    int n;
    Label label1;
    Label label2;
    EtchedBorder etchedBorder2;
    Panel panel1;
    Panel panel2;
    TextField hField;
    TextField vField;
    Label label3;
    Label label4;
    BorderLayout borderLayout2;
    BorderLayout borderLayout3;
    BorderLayout borderLayout4;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public DataSource() {
        this.varStrings = new String[] { "n", "x", "y" };
        this.isStandalone = false;
        this.variables = new double[1][3];
        this.etchedBorder1 = new EtchedBorder();
        this.addBtn = new Button();
        this.yField = new SNumber();
        this.flowLayout1 = new FlowLayout();
        this.clearbtn = new Button();
        this.borderLayout1 = new BorderLayout();
        this.xField = new SNumber();
        this.n = 0;
        this.label1 = new Label();
        this.label2 = new Label();
        this.etchedBorder2 = new EtchedBorder();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.hField = new TextField();
        this.vField = new TextField();
        this.label3 = new Label();
        this.label4 = new Label();
        this.borderLayout2 = new BorderLayout();
        this.borderLayout3 = new BorderLayout();
        this.borderLayout4 = new BorderLayout();
    }
    
    public void setOwner(final SApplet sApplet) {
    }
    
    public SApplet getOwner() {
        return this;
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.xField.setValue(0.0);
        this.yField.setValue(0.0);
        SApplet.addDataSource(this);
    }
    
    private void jbInit() throws Exception {
        this.etchedBorder1.setLayout(this.flowLayout1);
        this.setSize(new Dimension(401, 102));
        this.addBtn.setLabel("Add");
        this.yField.setText("0    ");
        this.addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataSource.this.addBtn_actionPerformed(actionEvent);
            }
        });
        this.clearbtn.setLabel("Clear");
        this.xField.setText("0    ");
        this.label1.setAlignment(2);
        this.label1.setText(" x=");
        this.label2.setAlignment(2);
        this.label2.setText(" y=");
        this.panel2.setLayout(this.borderLayout3);
        this.panel1.setLayout(this.borderLayout2);
        this.hField.setText("textField1");
        this.hField.setEditable(false);
        this.vField.setText("textField2");
        this.vField.setEditable(false);
        this.label3.setAlignment(1);
        this.label3.setText("Function 1=f(n,x,y)");
        this.label4.setAlignment(1);
        this.label4.setText("Funcion 2=g(n,x,y)");
        this.etchedBorder2.setLayout(this.borderLayout4);
        this.clearbtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataSource.this.clearbtn_actionPerformed(actionEvent);
            }
        });
        this.setLayout(this.borderLayout1);
        this.add(this.etchedBorder1, "North");
        this.etchedBorder1.add(this.label1, null);
        this.etchedBorder1.add(this.xField, null);
        this.etchedBorder1.add(this.label2, null);
        this.etchedBorder1.add(this.yField, null);
        this.etchedBorder1.add(this.addBtn, null);
        this.etchedBorder1.add(this.clearbtn, null);
        this.add(this.etchedBorder2, "Center");
        this.etchedBorder2.add(this.panel2, "North");
        this.panel2.add(this.label3, "West");
        this.panel2.add(this.hField, "Center");
        this.etchedBorder2.add(this.panel1, "South");
        this.panel1.add(this.label4, "West");
        this.panel1.add(this.vField, "Center");
    }
    
    public void start() {
    }
    
    public int makeDataConnection(final int n, final int n2, final int n3, final String text, final String text2) {
        this.vField.setText(text);
        this.hField.setText(text2);
        return super.makeDataConnection(n, n2, n3, text, text2);
    }
    
    public double[][] getVariables() {
        return this.variables;
    }
    
    public String[] getVarStrings() {
        return this.varStrings;
    }
    
    public void deleteDataConnection(final SDataSource sDataSource) {
        super.deleteDataConnection(sDataSource.hashCode());
    }
    
    public void deleteDataConnections() {
        super.deleteDataConnections();
    }
    
    public void stop() {
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    void addBtn_actionPerformed(final ActionEvent actionEvent) {
        this.variables[0][0] = this.n;
        ++this.n;
        this.variables[0][1] = this.xField.getValue();
        this.variables[0][2] = this.yField.getValue();
        this.updateDataConnections();
    }
    
    void clearbtn_actionPerformed(final ActionEvent actionEvent) {
        this.clearAllData();
        this.n = 0;
    }
}
