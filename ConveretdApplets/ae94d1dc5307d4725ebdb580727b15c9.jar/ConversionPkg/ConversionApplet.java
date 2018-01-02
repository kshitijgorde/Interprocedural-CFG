// 
// Decompiled by Procyon v0.5.30
// 

package ConversionPkg;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Component;
import borland.jbcl.layout.XYConstraints;
import java.awt.LayoutManager;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import borland.jbcl.control.StatusBar;
import borland.jbcl.control.BevelPanel;
import java.awt.Label;
import java.awt.Choice;
import java.awt.TextField;
import borland.jbcl.control.ButtonControl;
import java.awt.List;
import borland.jbcl.layout.XYLayout;
import java.applet.Applet;

public class ConversionApplet extends Applet
{
    XYLayout xYLayout1;
    boolean isStandalone;
    List lstFrom;
    List lstTo;
    ButtonControl butConvert;
    TextField txtResult;
    Choice chCategory;
    TextField txtNumber;
    Label lblTo;
    Label lblConvertNum;
    Label lblCategory;
    BevelPanel bevelPanel1;
    Label lblConverter;
    String strCategory;
    double iNum;
    double[][] arryLength;
    double[][] arryArea;
    double[][] arryVolume;
    double[][] arryMass;
    StatusBar statusBar;
    BevelPanel bevelPanel2;
    
    public ConversionApplet() {
        this.xYLayout1 = new XYLayout();
        this.isStandalone = false;
        this.lstFrom = new List();
        this.lstTo = new List();
        this.butConvert = new ButtonControl();
        this.txtResult = new TextField();
        this.chCategory = new Choice();
        this.txtNumber = new TextField();
        this.lblTo = new Label();
        this.lblConvertNum = new Label();
        this.lblCategory = new Label();
        this.bevelPanel1 = new BevelPanel();
        this.lblConverter = new Label();
        this.strCategory = "";
        this.iNum = 0.0;
        this.statusBar = new StatusBar();
        this.bevelPanel2 = new BevelPanel();
        this.arryLength = new double[10][10];
        this.populateArryLength();
        this.arryArea = new double[10][10];
        this.populateArryArea();
        this.arryVolume = new double[10][10];
        this.populateArryVolume();
        this.arryMass = new double[10][10];
        this.populateArryMass();
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setBackground(new Color(192, 255, 192));
        this.xYLayout1.setWidth(437);
        this.xYLayout1.setHeight(327);
        this.lstFrom.setBackground(Color.white);
        this.lblTo.setForeground(Color.blue);
        this.lblTo.setFont(new Font("Dialog", 1, 14));
        this.lblTo.setText("TO");
        this.butConvert.setLabel("Convert Now");
        this.butConvert.setFont(new Font("Dialog", 1, 12));
        this.txtResult.setForeground(Color.green);
        this.txtResult.setBackground(Color.darkGray);
        this.txtResult.setEditable(false);
        this.butConvert.addActionListener(new ConversionApplet_butConvert_actionAdapter(this));
        this.txtNumber.setText("1.0");
        this.chCategory.addItemListener(new ConversionApplet_chCategory_itemAdapter(this));
        this.lblConvertNum.setFont(new Font("Dialog", 1, 12));
        this.lblConvertNum.setText("Convert Number:");
        this.lblCategory.setFont(new Font("Dialog", 1, 12));
        this.lblCategory.setText("Category:");
        this.lblConverter.setBackground(Color.lightGray);
        this.lblConverter.setForeground(Color.red);
        this.lblConverter.setFont(new Font("Dialog", 1, 13));
        this.lblConverter.setText("METRIC CONVERTER");
        this.statusBar.setBackground(Color.darkGray);
        this.statusBar.setFont(new Font("Dialog", 1, 12));
        this.statusBar.setForeground(Color.red);
        this.statusBar.setText("Happy Converting..");
        this.setLayout(this.xYLayout1);
        this.add(this.bevelPanel1, new XYConstraints(1, 3, 434, 294));
        this.bevelPanel1.add(this.lblTo, new XYConstraints(197, 147, 34, 30));
        this.bevelPanel1.add(this.lstFrom, new XYConstraints(42, 102, 133, 136));
        this.bevelPanel1.add(this.lstTo, new XYConstraints(244, 100, 133, 136));
        this.bevelPanel1.add(this.butConvert, new XYConstraints(43, 255, 80, 26));
        this.bevelPanel1.add(this.txtResult, new XYConstraints(136, 256, 241, 25));
        this.bevelPanel1.add(this.chCategory, new XYConstraints(42, 61, 133, 25));
        this.bevelPanel1.add(this.txtNumber, new XYConstraints(244, 61, 133, -1));
        this.bevelPanel1.add(this.lblConvertNum, new XYConstraints(244, 41, 110, 18));
        this.bevelPanel1.add(this.lblCategory, new XYConstraints(42, 41, 133, 18));
        this.bevelPanel1.add(this.bevelPanel2, new XYConstraints(3, 3, 427, 34));
        this.bevelPanel2.add(this.lblConverter, new XYConstraints(144, 9, 169, 15));
        this.add(this.statusBar, new XYConstraints(2, 299, 434, 23));
        this.InitializeComponents();
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void InitializeComponents() {
        this.chCategory.addItem("Length");
        this.chCategory.addItem("Temperature");
        this.chCategory.addItem("Area");
        this.chCategory.addItem("Volume");
        this.chCategory.addItem("Mass");
    }
    
    public void populateArryLength() {
        this.arryLength[0][0] = 1.0;
        this.arryLength[0][1] = 0.01;
        this.arryLength[0][2] = 0.3937008;
        this.arryLength[0][3] = 0.0328084;
        this.arryLength[0][4] = 0.01093613;
        this.arryLength[0][5] = 6.21372E-6;
        this.arryLength[1][0] = 100.0;
        this.arryLength[1][1] = 1.0;
        this.arryLength[1][2] = 39.37008;
        this.arryLength[1][3] = 3.28084;
        this.arryLength[1][4] = 1.093613;
        this.arryLength[1][5] = 6.213712E-4;
        this.arryLength[2][0] = 2.54;
        this.arryLength[2][1] = 0.0254;
        this.arryLength[2][2] = 1.0;
        this.arryLength[2][3] = 0.08333333;
        this.arryLength[2][4] = 0.02777777;
        this.arryLength[2][5] = 1.578283E-5;
        this.arryLength[3][0] = 30.48;
        this.arryLength[3][1] = 0.3048;
        this.arryLength[3][2] = 12.0;
        this.arryLength[3][3] = 1.0;
        this.arryLength[3][4] = 0.333333333;
        this.arryLength[3][5] = 1.893939E-4;
        this.arryLength[4][0] = 91.44;
        this.arryLength[4][1] = 0.9144;
        this.arryLength[4][2] = 36.0;
        this.arryLength[4][3] = 3.0;
        this.arryLength[4][4] = 1.0;
        this.arryLength[4][5] = 5.681818E-4;
        this.arryLength[5][0] = 160934.4;
        this.arryLength[5][1] = 1609.344;
        this.arryLength[5][2] = 63360.0;
        this.arryLength[5][3] = 5280.0;
        this.arryLength[5][4] = 1760.0;
        this.arryLength[5][5] = 1.0;
    }
    
    public void populateArryArea() {
        this.arryArea[0][0] = 1.0;
        this.arryArea[0][1] = 4.0E-4;
        this.arryArea[0][2] = 0.1550003;
        this.arryArea[0][3] = 0.001076391;
        this.arryArea[0][4] = 1.19599E-4;
        this.arryArea[0][5] = 3.861022E-11;
        this.arryArea[1][0] = 10000.0;
        this.arryArea[1][1] = 1.0;
        this.arryArea[1][2] = 1550.003;
        this.arryArea[1][3] = 10.76391;
        this.arryArea[1][4] = 1.19599;
        this.arryArea[1][5] = 3.861022E-7;
        this.arryArea[2][0] = 6.4516;
        this.arryArea[2][1] = 6.4516E-4;
        this.arryArea[2][2] = 1.0;
        this.arryArea[2][3] = 0.00694444444;
        this.arryArea[2][4] = 7.716049E-4;
        this.arryArea[2][5] = 2.490977E-10;
        this.arryArea[3][0] = 929.0304;
        this.arryArea[3][1] = 0.09290304;
        this.arryArea[3][2] = 144.0;
        this.arryArea[3][3] = 1.0;
        this.arryArea[3][4] = 0.111111111;
        this.arryArea[3][5] = 3.587007E-8;
        this.arryArea[4][0] = 8361.273;
        this.arryArea[4][1] = 0.8361273;
        this.arryArea[4][2] = 1296.0;
        this.arryArea[4][3] = 9.0;
        this.arryArea[4][4] = 1.0;
        this.arryArea[4][5] = 3.228306E-7;
        this.arryArea[5][0] = 2.589988E10;
        this.arryArea[5][1] = 2589988.0;
        this.arryArea[5][2] = 4.01449E9;
        this.arryArea[5][3] = 2.78784E7;
        this.arryArea[5][4] = 3097600.0;
        this.arryArea[5][5] = 1.0;
    }
    
    public void populateArryVolume() {
        this.arryVolume[0][0] = 1.0;
        this.arryVolume[0][1] = 1000000.0;
        this.arryVolume[0][2] = 1000.0;
        this.arryVolume[0][3] = 61023.74;
        this.arryVolume[0][4] = 35.31467;
        this.arryVolume[0][5] = 1056.688;
        this.arryVolume[0][6] = 264.1721;
        this.arryVolume[1][0] = 1.0E-6;
        this.arryVolume[1][1] = 1.0;
        this.arryVolume[1][2] = 0.001;
        this.arryVolume[1][3] = 0.06102374;
        this.arryVolume[1][4] = 3.531467E-4;
        this.arryVolume[1][5] = 0.001056688;
        this.arryVolume[1][6] = 2.64172E-4;
        this.arryVolume[2][0] = 0.001;
        this.arryVolume[2][1] = 1000.0;
        this.arryVolume[2][2] = 1.0;
        this.arryVolume[2][3] = 61.02374;
        this.arryVolume[2][4] = 0.03531467;
        this.arryVolume[2][5] = 1.056688;
        this.arryVolume[2][6] = 0.2641721;
        this.arryVolume[3][0] = 1.638706E-5;
        this.arryVolume[3][1] = 16.38706;
        this.arryVolume[3][2] = 0.01638706;
        this.arryVolume[3][3] = 1.0;
        this.arryVolume[3][4] = 5.787037E-4;
        this.arryVolume[3][5] = 0.01731602;
        this.arryVolume[3][6] = 0.004329004;
        this.arryVolume[4][0] = 0.02831685;
        this.arryVolume[4][1] = 28316.85;
        this.arryVolume[4][2] = 28.31685;
        this.arryVolume[4][3] = 1728.0;
        this.arryVolume[4][4] = 1.0;
        this.arryVolume[4][5] = 2.992208;
        this.arryVolume[4][6] = 7.48052;
        this.arryVolume[5][0] = 9.46353E-4;
        this.arryVolume[5][1] = 946.353;
        this.arryVolume[5][2] = 0.946353;
        this.arryVolume[5][3] = 57.75;
        this.arryVolume[5][4] = 0.0342014;
        this.arryVolume[5][5] = 1.0;
        this.arryVolume[5][6] = 0.25;
        this.arryVolume[6][0] = 0.003785412;
        this.arryVolume[6][1] = 3785.412;
        this.arryVolume[6][2] = 3.785412;
        this.arryVolume[6][3] = 231.0;
        this.arryVolume[6][4] = 0.1336806;
        this.arryVolume[6][5] = 4.0;
        this.arryVolume[6][6] = 1.0;
    }
    
    public void populateArryMass() {
        this.arryMass[0][0] = 1.0;
        this.arryMass[0][1] = 0.001;
        this.arryMass[0][2] = 0.03527396;
        this.arryMass[0][3] = 0.002204623;
        this.arryMass[0][4] = 1.0E-6;
        this.arryMass[0][5] = 1.102311E-6;
        this.arryMass[1][0] = 1000.0;
        this.arryMass[1][1] = 1.0;
        this.arryMass[1][2] = 35.27396;
        this.arryMass[1][3] = 2.204623;
        this.arryMass[1][4] = 0.001;
        this.arryMass[1][5] = 0.001102311;
        this.arryMass[2][0] = 28.34925;
        this.arryMass[2][1] = 0.02834952;
        this.arryMass[2][2] = 1.0;
        this.arryMass[2][3] = 0.0625;
        this.arryMass[2][4] = 2.834952E-4;
        this.arryMass[2][5] = 3.125E-5;
        this.arryMass[3][0] = 453.5924;
        this.arryMass[3][1] = 0.4535924;
        this.arryMass[3][2] = 16.0;
        this.arryMass[3][3] = 1.0;
        this.arryMass[3][4] = 4.535924E-4;
        this.arryMass[3][5] = 5.0E-4;
        this.arryMass[4][0] = 1000000.0;
        this.arryMass[4][1] = 1000.0;
        this.arryMass[4][2] = 35273.96;
        this.arryMass[4][3] = 2204.623;
        this.arryMass[4][4] = 1.0;
        this.arryMass[4][5] = 1.102311;
        this.arryMass[5][0] = 907184.7;
        this.arryMass[5][1] = 907.1847;
        this.arryMass[5][2] = 32000.0;
        this.arryMass[5][3] = 2000.0;
        this.arryMass[5][4] = 0.9071847;
        this.arryMass[5][5] = 1.0;
    }
    
    void chCategory_itemStateChanged(final ItemEvent e) {
        this.LoadListBoxes(this.strCategory = this.chCategory.getSelectedItem());
    }
    
    public void LoadListBoxes(final String strCategory) {
        this.lstFrom.removeAll();
        this.lstTo.removeAll();
        if (strCategory.equalsIgnoreCase("Length")) {
            this.lstFrom.addItem("Centimeter(cm)");
            this.lstFrom.addItem("Meter(m)");
            this.lstFrom.addItem("Inch(in)");
            this.lstFrom.addItem("Foot(ft)");
            this.lstFrom.addItem("Yard(yd)");
            this.lstFrom.addItem("mile(mile)");
            this.lstTo.addItem("Centimeter(cm)");
            this.lstTo.addItem("Meter(m)");
            this.lstTo.addItem("Inch(in)");
            this.lstTo.addItem("Foot(ft)");
            this.lstTo.addItem("Yard(yd)");
            this.lstTo.addItem("mile(mile)");
        }
        if (strCategory.equalsIgnoreCase("Temperature")) {
            this.lstFrom.addItem("Celcius(C)");
            this.lstFrom.addItem("Fatherenite(F)");
            this.lstTo.addItem("Celcius(C)");
            this.lstTo.addItem("Fatherenite(F)");
        }
        if (strCategory.equalsIgnoreCase("Area")) {
            this.lstFrom.addItem("CentimeterSquare(cm2)");
            this.lstFrom.addItem("meterSquare(m2)");
            this.lstFrom.addItem("inchSquare(in2)");
            this.lstFrom.addItem("footSquare(ft2)");
            this.lstFrom.addItem("yardSquare(yd2)");
            this.lstFrom.addItem("mileSquare(mile2)");
            this.lstTo.addItem("CentimeterSquare(cm2)");
            this.lstTo.addItem("meterSquare(m2)");
            this.lstTo.addItem("inchSquare(in2)");
            this.lstTo.addItem("footSquare(ft2)");
            this.lstTo.addItem("yardSquare(yd2)");
            this.lstTo.addItem("mileSquare(mile2)");
        }
        if (strCategory.equalsIgnoreCase("Volume")) {
            this.lstFrom.addItem("MeterCube(m3)");
            this.lstFrom.addItem("CentimeterCube(cm3)");
            this.lstFrom.addItem("Liter(liter)");
            this.lstFrom.addItem("InchCube(in3)");
            this.lstFrom.addItem("footCube(ft3)");
            this.lstFrom.addItem("QT(qt)");
            this.lstFrom.addItem("Gallon(ga1)");
            this.lstTo.addItem("MeterCube(m3)");
            this.lstTo.addItem("CentimeterCube(cm3)");
            this.lstTo.addItem("Liter(liter)");
            this.lstTo.addItem("InchCube(in3)");
            this.lstTo.addItem("footCube(ft3)");
            this.lstTo.addItem("QT(qt)");
            this.lstTo.addItem("Gallon(ga1)");
        }
        if (strCategory.equalsIgnoreCase("Mass")) {
            this.lstFrom.addItem("Gram(g)");
            this.lstFrom.addItem("KiloGram(Kg)");
            this.lstFrom.addItem("OZ (avdp)");
            this.lstFrom.addItem("lb (avdp)");
            this.lstFrom.addItem("metric ton");
            this.lstFrom.addItem("Ton(ton)");
            this.lstTo.addItem("Gram(g)");
            this.lstTo.addItem("KiloGram(Kg)");
            this.lstTo.addItem("OZ (avdp)");
            this.lstTo.addItem("lb (avdp)");
            this.lstTo.addItem("metric ton");
            this.lstTo.addItem("Ton(ton)");
        }
    }
    
    void butConvert_actionPerformed(final ActionEvent e) {
        int ilstFrom = -1;
        int ilstTo = -1;
        double dbFactor = 0.0;
        double resultNum = 0.0;
        try {
            this.iNum = new Double(this.txtNumber.getText());
        }
        catch (NumberFormatException nfe) {
            this.statusBar.setText("Please Enter A Valid Number");
            System.exit(-1);
        }
        ilstFrom = this.lstFrom.getSelectedIndex();
        ilstTo = this.lstTo.getSelectedIndex();
        if (ilstFrom == -1 || ilstTo == -1) {
            this.statusBar.setText("Please Select Both the List Items");
            System.exit(-1);
        }
        if (this.strCategory.equalsIgnoreCase("Length")) {
            dbFactor = this.arryLength[ilstFrom][ilstTo];
            resultNum = dbFactor * this.iNum;
            this.txtResult.setText(String.valueOf("").concat(String.valueOf(resultNum)));
            this.statusBar.setText("Happy Converting...");
        }
        if (this.strCategory.equalsIgnoreCase("Temperature")) {
            if (ilstFrom == 0 && ilstTo == 0) {
                resultNum = this.iNum;
            }
            if (ilstFrom == 0 && ilstTo == 1) {
                resultNum = this.iNum * 1.8 + 32;
            }
            if (ilstFrom == 1 && ilstTo == 0) {
                resultNum = 0.55 * (this.iNum - 32);
            }
            if (ilstFrom == 1 && ilstTo == 1) {
                resultNum = this.iNum;
            }
            this.txtResult.setText(String.valueOf("").concat(String.valueOf(resultNum)));
            this.statusBar.setText("Happy Converting...");
        }
        if (this.strCategory.equalsIgnoreCase("Area")) {
            dbFactor = this.arryArea[ilstFrom][ilstTo];
            resultNum = dbFactor * this.iNum;
            this.txtResult.setText(String.valueOf("").concat(String.valueOf(resultNum)));
            this.statusBar.setText("Happy Converting...");
        }
        if (this.strCategory.equalsIgnoreCase("Volume")) {
            dbFactor = this.arryVolume[ilstFrom][ilstTo];
            resultNum = dbFactor * this.iNum;
            this.txtResult.setText(String.valueOf("").concat(String.valueOf(resultNum)));
            this.statusBar.setText("Happy Converting...");
        }
        if (this.strCategory.equalsIgnoreCase("mass")) {
            dbFactor = this.arryMass[ilstFrom][ilstTo];
            resultNum = dbFactor * this.iNum;
            this.txtResult.setText(String.valueOf("").concat(String.valueOf(resultNum)));
            this.statusBar.setText("Happy Converting...");
        }
    }
}
