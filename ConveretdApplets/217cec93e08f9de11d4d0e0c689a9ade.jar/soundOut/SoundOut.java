// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import borland.jbcl.layout.PaneConstraints;
import java.awt.Component;
import java.awt.event.AdjustmentListener;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import sTools.SSlider3;
import borland.jbcl.control.GroupBox;
import java.awt.BorderLayout;
import borland.jbcl.control.SplitPanel;
import java.awt.TextField;
import java.awt.Label;
import borland.jbcl.control.BevelPanel;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class SoundOut extends Applet implements ActionListener
{
    boolean isStandalone;
    Button clearBtn;
    Button addBtn;
    Checkbox sndCkBox;
    BevelPanel bevelPanel3;
    Label label1;
    TextField funcField;
    SoundCanvas sonogram;
    SoundCanvas sonogramDetail;
    SoundPlayer soundPlayer;
    SoundData soundData;
    private int numPts;
    private String ampFunc;
    private boolean showControls;
    private double volume;
    private boolean mute;
    BevelPanel bevelPanel2;
    SplitPanel splitPanel1;
    BevelPanel controlPanel;
    BevelPanel bevelPanel5;
    BorderLayout borderLayout1;
    BorderLayout borderLayout2;
    BorderLayout borderLayout3;
    BorderLayout borderLayout4;
    BevelPanel bevelPanel6;
    GroupBox groupBox1;
    SSlider3 volSlider;
    GridLayout gridLayout1;
    BorderLayout borderLayout5;
    GroupBox groupBox2;
    BorderLayout borderLayout6;
    private boolean oneShot;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public SoundOut() {
        this.isStandalone = false;
        this.clearBtn = new Button();
        this.addBtn = new Button();
        this.sndCkBox = new Checkbox();
        this.bevelPanel3 = new BevelPanel();
        this.label1 = new Label();
        this.funcField = new TextField();
        this.numPts = 8000;
        this.ampFunc = "sin(400*pi*t)+sin(404*pi*t)";
        this.showControls = true;
        this.volume = 1.0;
        this.mute = true;
        this.bevelPanel2 = new BevelPanel();
        this.splitPanel1 = new SplitPanel();
        this.controlPanel = new BevelPanel();
        this.bevelPanel5 = new BevelPanel();
        this.borderLayout1 = new BorderLayout();
        this.borderLayout2 = new BorderLayout();
        this.borderLayout3 = new BorderLayout();
        this.borderLayout4 = new BorderLayout();
        this.bevelPanel6 = new BevelPanel();
        this.groupBox1 = new GroupBox();
        this.volSlider = new SSlider3();
        this.gridLayout1 = new GridLayout();
        this.borderLayout5 = new BorderLayout();
        this.groupBox2 = new GroupBox();
        this.borderLayout6 = new BorderLayout();
        this.oneShot = false;
    }
    
    public void init() {
        try {
            this.numPts = Integer.parseInt(this.getParameter("numPts", "8000"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.showControls = Boolean.valueOf(this.getParameter("showControls", "true"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.ampFunc = this.getParameter("ampFunc", "sin(2*pi*400*t)");
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.volume = Double.valueOf(this.getParameter("volume", "1.0"));
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.mute = Boolean.valueOf(this.getParameter("mute", "false"));
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
        this.soundData = new SoundData(this.numPts);
        this.funcField.setText(this.ampFunc);
        this.soundData.newAmpFunc(this.ampFunc);
        this.soundPlayer = new SoundPlayer(this.numPts, this.oneShot);
        this.calc();
        this.soundPlayer.setMute(this.sndCkBox.getState());
    }
    
    public void jbInit() throws Exception {
        this.bevelPanel2.setLayout(this.borderLayout1);
        this.clearBtn.setActionCommand("ClearSnd");
        this.clearBtn.addActionListener(new SoundOut_clearBtn_actionAdapter(this));
        this.setBackground(Color.lightGray);
        this.setSize(new Dimension(500, 500));
        this.clearBtn.setLabel("New");
        this.addBtn.setActionCommand("AddSnd");
        this.addBtn.addActionListener(new SoundOut_addBtn_actionAdapter(this));
        this.addBtn.setLabel("Add");
        this.sndCkBox.setState(this.mute);
        this.sndCkBox.setLabel("Mute");
        this.sndCkBox.addItemListener(new SoundOut_sndCKBox_itemAdapter(this));
        this.bevelPanel3.setLayout(this.borderLayout4);
        this.label1.setAlignment(1);
        this.label1.setText("f(t)=");
        this.splitPanel1.setBackground(Color.black);
        this.controlPanel.setMargins(new Insets(2, 2, 2, 2));
        this.bevelPanel5.setBevelInner(0);
        this.controlPanel.setLayout(this.borderLayout3);
        this.borderLayout1.setHgap(5);
        this.borderLayout1.setVgap(5);
        this.bevelPanel6.setBevelInner(0);
        this.bevelPanel6.setMargins(new Insets(5, 5, 5, 5));
        this.groupBox1.setLayout(this.gridLayout1);
        this.groupBox1.setLabel("Volume");
        this.volSlider.setVinset(5);
        this.volSlider.setDValue(this.volume);
        this.volSlider.addAdjustmentListener(new SoundOut_volSlider_adjustmentAdapter(this));
        this.borderLayout5.setHgap(2);
        this.groupBox2.setLabel("Function");
        this.bevelPanel6.setLayout(this.borderLayout5);
        this.bevelPanel5.setLayout(this.borderLayout6);
        this.splitPanel1.addActionListener(new SoundOut_splitPanel1_actionAdapter(this));
        this.setLayout(this.borderLayout2);
        (this.sonogram = new SoundCanvas()).addActionListener(this);
        (this.sonogramDetail = new SoundCanvas()).setNoCursors(true);
        this.add(this.splitPanel1, "Center");
        if (this.showControls) {
            this.add(this.controlPanel, "South");
        }
        this.controlPanel.add(this.bevelPanel2, "Center");
        this.bevelPanel2.add(this.bevelPanel5, "West");
        this.bevelPanel5.add(this.groupBox2, "Center");
        this.groupBox2.add(this.addBtn, null);
        this.groupBox2.add(this.clearBtn, null);
        this.bevelPanel2.add(this.bevelPanel6, "East");
        this.bevelPanel6.add(this.sndCkBox, "Center");
        this.bevelPanel2.add(this.groupBox1, "Center");
        this.groupBox1.add(this.volSlider, null);
        this.controlPanel.add(this.bevelPanel3, "North");
        this.bevelPanel3.add(this.label1, "West");
        this.bevelPanel3.add(this.funcField, "Center");
        this.splitPanel1.add(this.sonogram, new PaneConstraints("sonogram", "sonogram", "Root", 0.5f));
        this.splitPanel1.add(this.sonogramDetail, new PaneConstraints("sonogramDetail", "sonogram", "Bottom", 0.48394495f));
    }
    
    public String getAppletInfo() {
        return "SoundOut by Wolfgang Christian. email:wochristian@davidson.edu";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "numPts", "int", "Number of sample points" }, { "showControls", "boolean", "Show controls." }, { "ampFunc", "String", "Amplitude function" }, { "volume", "double", "Volume" }, { "mute", "boolean", "Mute Sound" } };
    }
    
    public void destroy() {
        this.setMute(true);
        this.soundPlayer.closeStream();
    }
    
    public void stop() {
        this.setMute(true);
    }
    
    void calc() {
        final double[] ampVec = this.soundData.getAmpVec();
        this.soundPlayer.setYVec(ampVec);
        this.sonogram.setYVec(ampVec);
        this.sonogramDetail.setYVec(ampVec, this.sonogram.getC1(), this.sonogram.getC2());
    }
    
    void addBtn_actionPerformed(final ActionEvent actionEvent) {
        final boolean mute = this.mute;
        this.ampFunc = this.funcField.getText();
        this.soundData.addAmpFunc(this.ampFunc);
        if (this.oneShot) {
            this.setMute(true);
            this.soundPlayer.closeStream();
            this.soundPlayer = new SoundPlayer(this.numPts, this.oneShot);
        }
        this.calc();
    }
    
    public void setNumPts(final int n) {
        final boolean mute = this.mute;
        this.soundPlayer.closeStream();
        this.soundData = new SoundData(this.numPts);
        this.funcField.setText(this.ampFunc);
        this.soundData.newAmpFunc(this.ampFunc);
        this.soundPlayer = new SoundPlayer(this.numPts, this.oneShot);
        this.calc();
    }
    
    public synchronized void setSound(final String s) {
        this.soundData.newAmpFunc(s);
        this.calc();
    }
    
    public synchronized void addSound(final String s) {
        final boolean mute = this.mute;
        this.soundData.addAmpFunc(s);
        if (this.oneShot) {
            this.soundPlayer.closeStream();
            this.soundPlayer = new SoundPlayer(this.numPts, this.oneShot);
        }
        this.calc();
    }
    
    public synchronized void setMute(final boolean mute) {
        this.mute = mute;
        this.sndCkBox.setState(this.mute);
        this.soundPlayer.setMute(this.sndCkBox.getState());
    }
    
    void splitPanel1_actionPerformed(final ActionEvent actionEvent) {
        this.sonogram.repaint();
        this.sonogramDetail.repaint();
    }
    
    void sndCKBox_itemStateChanged(final ItemEvent itemEvent) {
        this.soundPlayer.setMute(this.sndCkBox.getState());
    }
    
    void volSlider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.soundPlayer.setVolume(this.volSlider.getDValue());
    }
    
    void clearBtn_actionPerformed(final ActionEvent actionEvent) {
        final boolean mute = this.mute;
        this.ampFunc = this.funcField.getText();
        if (this.oneShot) {
            this.setMute(true);
            this.playOnce(this.ampFunc);
            return;
        }
        this.soundData.newAmpFunc(this.ampFunc);
        this.calc();
    }
    
    public void playOnce(final String s) {
        this.oneShot = true;
        this.ampFunc = s;
        final boolean mute = this.mute;
        this.soundPlayer.closeStream();
        this.soundData = new SoundData(this.numPts);
        this.funcField.setText(s);
        this.soundData.newAmpFunc(this.ampFunc);
        this.soundPlayer = new SoundPlayer(this.numPts, this.oneShot);
        this.calc();
    }
    
    public void playLoop(final String s) {
        this.oneShot = false;
        this.ampFunc = s;
        final boolean mute = this.mute;
        this.soundPlayer.closeStream();
        this.funcField.setText(s);
        this.soundData.newAmpFunc(this.ampFunc);
        this.soundPlayer = new SoundPlayer(this.numPts, this.oneShot);
        this.calc();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.sonogramDetail.setYVec(this.soundData.getAmpVec(), this.sonogram.getC1(), this.sonogram.getC2());
    }
}
