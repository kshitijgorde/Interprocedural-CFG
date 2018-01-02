// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Dimension;
import sTools.SInteger;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.CheckboxGroup;
import java.awt.BorderLayout;
import sGraphics.EtchedBorder;
import java.applet.Applet;

public class Density extends Applet
{
    private boolean firstTime;
    boolean isStandalone;
    int m;
    int n;
    int l;
    boolean sc;
    boolean ph;
    EtchedBorder southetchdbrdr;
    DensityCanvas densityCanvas;
    BorderLayout borderLayout1;
    CheckboxGroup phasegroup;
    Panel panel1;
    FlowLayout flowLayout2;
    Button plotBtn;
    Checkbox yphase;
    Checkbox nphase;
    Label label4;
    Panel panel2;
    FlowLayout flowLayout3;
    SInteger nValue;
    SInteger mValue;
    SInteger lValue;
    Label label1;
    Label label2;
    Label label3;
    BorderLayout borderLayout2;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public Density() {
        this.firstTime = true;
        this.isStandalone = false;
        this.southetchdbrdr = new EtchedBorder();
        this.densityCanvas = new DensityCanvas();
        this.borderLayout1 = new BorderLayout();
        this.phasegroup = new CheckboxGroup();
        this.panel1 = new Panel();
        this.flowLayout2 = new FlowLayout();
        this.plotBtn = new Button();
        this.yphase = new Checkbox();
        this.nphase = new Checkbox();
        this.label4 = new Label();
        this.panel2 = new Panel();
        this.flowLayout3 = new FlowLayout();
        this.nValue = new SInteger();
        this.mValue = new SInteger();
        this.lValue = new SInteger();
        this.label1 = new Label();
        this.label2 = new Label();
        this.label3 = new Label();
        this.borderLayout2 = new BorderLayout();
    }
    
    public void init() {
        try {
            this.m = Integer.parseInt(this.getParameter("m", "0"));
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
            this.sc = Boolean.valueOf(this.getParameter("ShowControls", "true"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.l = Integer.parseInt(this.getParameter("l", "0"));
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            try {
                this.ph = Boolean.valueOf(this.getParameter("ShowPhase", "false"));
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
            }
            this.jbInit();
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
        this.mValue.setValue(this.m);
        this.nValue.setValue(this.n);
        this.lValue.setValue(this.l);
        this.yphase.setState(this.ph);
        this.nphase.setState(!this.ph);
        this.densityCanvas.setM(this.mValue.getValue());
        this.densityCanvas.setN(this.nValue.getValue());
        this.densityCanvas.setL(this.lValue.getValue());
        this.densityCanvas.setPhase(this.ph);
        this.southetchdbrdr.setVisible(this.sc);
    }
    
    public void setShowControls(final boolean visible) {
        this.southetchdbrdr.setVisible(visible);
        this.invalidate();
        this.validate();
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(359, 343));
        this.flowLayout2.setVgap(2);
        this.flowLayout2.setHgap(2);
        this.plotBtn.setLabel("Plot");
        this.yphase.setCheckboxGroup(this.phasegroup);
        this.nphase.setCheckboxGroup(this.phasegroup);
        this.label4.setText("Phase:");
        this.flowLayout3.setVgap(2);
        this.flowLayout3.setHgap(2);
        this.label1.setAlignment(2);
        this.label1.setText("m=");
        this.label2.setText("n=");
        this.label3.setText("l=");
        this.label3.setAlignment(2);
        this.label2.setAlignment(2);
        this.panel2.setLayout(this.flowLayout3);
        this.label4.setAlignment(2);
        this.nphase.setLabel("N");
        this.yphase.setLabel("Y");
        this.plotBtn.addActionListener(new 1());
        this.panel1.setLayout(this.flowLayout2);
        this.southetchdbrdr.setLayout(this.borderLayout2);
        this.setLayout(this.borderLayout1);
        this.add(this.southetchdbrdr, "South");
        this.southetchdbrdr.add(this.panel2, "Center");
        this.panel2.add(this.label2, null);
        this.panel2.add(this.nValue, null);
        this.panel2.add(this.label3, null);
        this.panel2.add(this.lValue, null);
        this.panel2.add(this.label1, null);
        this.panel2.add(this.mValue, null);
        this.southetchdbrdr.add(this.panel1, "South");
        this.panel1.add(this.label4, null);
        this.panel1.add(this.yphase, null);
        this.panel1.add(this.nphase, null);
        this.panel1.add(this.plotBtn, null);
        this.add(this.densityCanvas, "Center");
    }
    
    public void start() {
        if (this.firstTime) {
            this.setNLM(this.n, this.l, this.m);
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
        return new String[][] { { "m", "int", "magnetic quantum number" }, { "n", "int", "principle quantum number" }, { "l", "int", "angular quantum number" } };
    }
    
    public void recalculate() {
        this.densityCanvas.stop();
        this.densityCanvas.start();
    }
    
    public void setNLM(final int value, final int value2, final int value3) {
        this.nValue.setValue(value);
        this.lValue.setValue(value2);
        this.mValue.setValue(value3);
        this.densityCanvas.setM(this.mValue.getValue());
        this.densityCanvas.setN(this.nValue.getValue());
        this.densityCanvas.setL(this.lValue.getValue());
        this.recalculate();
    }
    
    void plotBtn_actionPerformed(final ActionEvent actionEvent) {
        this.n = this.nValue.getValue();
        this.l = this.lValue.getValue();
        this.m = this.mValue.getValue();
        if (this.n > 50) {
            this.nValue.setBackground(Color.red);
        }
        else if (this.l > this.n - 1 | this.l < 0) {
            this.lValue.setBackground(Color.red);
        }
        else if (Math.abs(this.m) > this.l) {
            this.mValue.setBackground(Color.red);
        }
        else {
            this.densityCanvas.setM(this.mValue.getValue());
            this.densityCanvas.setN(this.nValue.getValue());
            this.densityCanvas.setL(this.lValue.getValue());
            this.densityCanvas.setPhase(this.yphase.getState());
            this.recalculate();
        }
    }
    
    class 1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Density.this.plotBtn_actionPerformed(actionEvent);
        }
    }
}
