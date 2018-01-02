// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.awt.Image;
import java.awt.Label;
import java.beans.PropertyChangeListener;
import java.applet.Applet;

public class Wb6 extends Applet implements PropertyChangeListener
{
    private WtEntrySpin ivjBaggage;
    private WtEntrySpin ivjBattery;
    private WtEntrySpin ivjFuel;
    private WtEntrySpin ivjFuel1;
    private Label ivjLabel1;
    private Label ivjLabel11;
    private Label ivjLabel21;
    private Label ivjLabel211;
    private Label ivjLabel221;
    private Label ivjLabel2211;
    private Label ivjLabel22111;
    private Label ivjLabel2212;
    private WtEntrySpin ivjPilot;
    private WtEntrySpin ivjTailPost;
    private WBTotals ivjTotals;
    private WtEntrySpin ivjw1;
    private WtEntrySpin ivjw11;
    Image image1;
    
    private void conn0(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getw1().getArmText(), this.getw1().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn12(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getw11().getArmText(), this.getw11().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn15(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getPilot().getArmText(), this.getPilot().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn18(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getBaggage().getArmText(), this.getBaggage().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn21(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getTailPost().getArmText(), this.getTailPost().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn3(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getFuel().getArmText(), this.getFuel().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn6(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getFuel1().getArmText(), this.getFuel1().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn9(final PropertyChangeEvent arg1) {
        try {
            this.getTotals().updTotals(arg1, this.getBattery().getArmText(), this.getBattery().getSLocWeight());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    public String getAppletInfo() {
        return "S1S.Wb6 created using VisualAge for Java.";
    }
    
    private WtEntrySpin getBaggage() {
        if (this.ivjBaggage == null) {
            try {
                (this.ivjBaggage = new WtEntrySpin()).setName("Baggage");
                this.ivjBaggage.setArmText("113.5");
                this.ivjBaggage.setDescText("Baggage");
                this.ivjBaggage.setArmEditable(false);
                this.ivjBaggage.setWeightIncrementText("1");
                this.ivjBaggage.setWeightTextField1Background(Color.white);
                this.ivjBaggage.setDescBackground(Color.lightGray);
                this.ivjBaggage.setArmBackground(Color.lightGray);
                this.ivjBaggage.setBounds(331, 2, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjBaggage;
    }
    
    private WtEntrySpin getBattery() {
        if (this.ivjBattery == null) {
            try {
                (this.ivjBattery = new WtEntrySpin()).setName("Battery");
                this.ivjBattery.setArmText("108.0");
                this.ivjBattery.setDescText("Battery");
                this.ivjBattery.setArmEditable(false);
                this.ivjBattery.setWeightIncrementText("1");
                this.ivjBattery.setWeightTextField1Background(Color.white);
                this.ivjBattery.setDescBackground(Color.lightGray);
                this.ivjBattery.setArmBackground(Color.lightGray);
                this.ivjBattery.setBounds(122, 155, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjBattery;
    }
    
    private WtEntrySpin getFuel() {
        if (this.ivjFuel == null) {
            try {
                (this.ivjFuel = new WtEntrySpin()).setName("Fuel");
                this.ivjFuel.setArmText("59.63");
                this.ivjFuel.setDescText("Fuel");
                this.ivjFuel.setArmEditable(false);
                this.ivjFuel.setWeightIncrementText("6");
                this.ivjFuel.setWeightTextField1Background(Color.white);
                this.ivjFuel.setDescBackground(Color.lightGray);
                this.ivjFuel.setArmBackground(Color.lightGray);
                this.ivjFuel.setBounds(171, 2, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjFuel;
    }
    
    private WtEntrySpin getFuel1() {
        if (this.ivjFuel1 == null) {
            try {
                (this.ivjFuel1 = new WtEntrySpin()).setName("Fuel1");
                this.ivjFuel1.setArmText("0");
                this.ivjFuel1.setDescText("Mod 1");
                this.ivjFuel1.setArmEditable(true);
                this.ivjFuel1.setWeightIncrementText("1");
                this.ivjFuel1.setWeightTextField1Background(Color.white);
                this.ivjFuel1.setDescBackground(Color.lightGray);
                this.ivjFuel1.setArmBackground(Color.white);
                this.ivjFuel1.setBounds(193, 155, 73, 117);
                this.ivjFuel1.setWeightBackground(Color.lightGray);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjFuel1;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setFont(new Font("dialog", 1, 12));
                this.ivjLabel1.setText("Basic Airplane");
                this.ivjLabel1.setBounds(6, 6, 92, 21);
                this.ivjLabel1.setForeground(Color.red);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel1;
    }
    
    private Label getLabel11() {
        if (this.ivjLabel11 == null) {
            try {
                (this.ivjLabel11 = new Label()).setName("Label11");
                this.ivjLabel11.setFont(new Font("dialog", 1, 12));
                this.ivjLabel11.setText("Modifications");
                this.ivjLabel11.setBounds(64, 132, 92, 21);
                this.ivjLabel11.setForeground(Color.red);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel11;
    }
    
    private Label getLabel21() {
        if (this.ivjLabel21 == null) {
            try {
                (this.ivjLabel21 = new Label()).setName("Label21");
                this.ivjLabel21.setFont(new Font("dialog", 0, 10));
                this.ivjLabel21.setAlignment(2);
                this.ivjLabel21.setText("Weight");
                this.ivjLabel21.setBounds(5, 197, 39, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel21;
    }
    
    private Label getLabel211() {
        if (this.ivjLabel211 == null) {
            try {
                (this.ivjLabel211 = new Label()).setName("Label211");
                this.ivjLabel211.setFont(new Font("dialog", 0, 10));
                this.ivjLabel211.setAlignment(2);
                this.ivjLabel211.setText("Weight");
                this.ivjLabel211.setBounds(48, 43, 39, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel211;
    }
    
    private Label getLabel221() {
        if (this.ivjLabel221 == null) {
            try {
                (this.ivjLabel221 = new Label()).setName("Label221");
                this.ivjLabel221.setFont(new Font("dialog", 0, 10));
                this.ivjLabel221.setAlignment(2);
                this.ivjLabel221.setText("Arm");
                this.ivjLabel221.setBounds(5, 221, 39, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel221;
    }
    
    private Label getLabel2211() {
        if (this.ivjLabel2211 == null) {
            try {
                (this.ivjLabel2211 = new Label()).setName("Label2211");
                this.ivjLabel2211.setFont(new Font("dialog", 0, 10));
                this.ivjLabel2211.setAlignment(2);
                this.ivjLabel2211.setText("Moment");
                this.ivjLabel2211.setBounds(5, 245, 39, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel2211;
    }
    
    private Label getLabel22111() {
        if (this.ivjLabel22111 == null) {
            try {
                (this.ivjLabel22111 = new Label()).setName("Label22111");
                this.ivjLabel22111.setFont(new Font("dialog", 0, 10));
                this.ivjLabel22111.setAlignment(2);
                this.ivjLabel22111.setText("Moment");
                this.ivjLabel22111.setBounds(48, 91, 39, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel22111;
    }
    
    private Label getLabel2212() {
        if (this.ivjLabel2212 == null) {
            try {
                (this.ivjLabel2212 = new Label()).setName("Label2212");
                this.ivjLabel2212.setFont(new Font("dialog", 0, 10));
                this.ivjLabel2212.setAlignment(2);
                this.ivjLabel2212.setText("Arm");
                this.ivjLabel2212.setBounds(48, 67, 39, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel2212;
    }
    
    private WtEntrySpin getPilot() {
        if (this.ivjPilot == null) {
            try {
                (this.ivjPilot = new WtEntrySpin()).setName("Pilot");
                this.ivjPilot.setArmText("91.5");
                this.ivjPilot.setDescText("Pilot");
                this.ivjPilot.setArmEditable(false);
                this.ivjPilot.setWeightIncrementText("5");
                this.ivjPilot.setWeightTextField1Background(Color.white);
                this.ivjPilot.setDescBackground(Color.lightGray);
                this.ivjPilot.setArmBackground(Color.lightGray);
                this.ivjPilot.setBounds(248, 2, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPilot;
    }
    
    private WtEntrySpin getTailPost() {
        if (this.ivjTailPost == null) {
            try {
                (this.ivjTailPost = new WtEntrySpin()).setName("TailPost");
                this.ivjTailPost.setArmText("171.0");
                this.ivjTailPost.setDescText("Tail Post");
                this.ivjTailPost.setArmEditable(false);
                this.ivjTailPost.setWeightIncrementText("1");
                this.ivjTailPost.setWeightTextField1Background(Color.white);
                this.ivjTailPost.setDescBackground(Color.lightGray);
                this.ivjTailPost.setArmBackground(Color.lightGray);
                this.ivjTailPost.setBounds(501, 3, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTailPost;
    }
    
    private WBTotals getTotals() {
        if (this.ivjTotals == null) {
            try {
                (this.ivjTotals = new WBTotals()).setName("Totals");
                this.ivjTotals.setAnalysisLabelBackground(Color.lightGray);
                this.ivjTotals.setCGLabelForeground(Color.red);
                this.ivjTotals.setMtLabelBackground(Color.lightGray);
                this.ivjTotals.setTotalWeightBackground(Color.lightGray);
                this.ivjTotals.setMsgListFont(new Font("dialog", 1, 12));
                this.ivjTotals.setMsgListBackground(Color.white);
                this.ivjTotals.setMtLabelForeground(Color.red);
                this.ivjTotals.setWtLabelForeground(Color.red);
                this.ivjTotals.setCGLabelBackground(Color.lightGray);
                this.ivjTotals.setWtLabelBackground(Color.lightGray);
                this.ivjTotals.setBounds(268, 153, 391, 118);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTotals;
    }
    
    private WtEntrySpin getw1() {
        if (this.ivjw1 == null) {
            try {
                (this.ivjw1 = new WtEntrySpin()).setName("w1");
                this.ivjw1.setArmText("17.3");
                this.ivjw1.setDescText("Prop Mod");
                this.ivjw1.setArmEditable(false);
                this.ivjw1.setWeightIncrementText("1");
                this.ivjw1.setWeightTextField1Background(Color.white);
                this.ivjw1.setDescBackground(Color.lightGray);
                this.ivjw1.setArmBackground(Color.lightGray);
                this.ivjw1.setBounds(48, 155, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjw1;
    }
    
    private WtEntrySpin getw11() {
        if (this.ivjw11 == null) {
            try {
                (this.ivjw11 = new WtEntrySpin()).setName("w11");
                this.ivjw11.setArmText("50.44");
                this.ivjw11.setDescText("Main Gear");
                this.ivjw11.setArmEditable(false);
                this.ivjw11.setWeightIncrementText("1");
                this.ivjw11.setWeightTextField1Background(Color.white);
                this.ivjw11.setDescBackground(Color.lightGray);
                this.ivjw11.setArmBackground(Color.lightGray);
                this.ivjw11.setBounds(96, 2, 73, 117);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjw11;
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public void init() {
        try {
            super.init();
            this.setName("Wb6");
            this.setLayout(null);
            this.setBackground(Color.lightGray);
            this.setSize(672, 409);
            this.add(this.getw1(), this.getw1().getName());
            this.add(this.getTotals(), this.getTotals().getName());
            this.add(this.getFuel(), this.getFuel().getName());
            this.add(this.getFuel1(), this.getFuel1().getName());
            this.add(this.getLabel1(), this.getLabel1().getName());
            this.add(this.getw11(), this.getw11().getName());
            this.add(this.getPilot(), this.getPilot().getName());
            this.add(this.getBaggage(), this.getBaggage().getName());
            this.add(this.getTailPost(), this.getTailPost().getName());
            this.add(this.getLabel21(), this.getLabel21().getName());
            this.add(this.getLabel221(), this.getLabel221().getName());
            this.add(this.getLabel2211(), this.getLabel2211().getName());
            this.add(this.getLabel22111(), this.getLabel22111().getName());
            this.add(this.getLabel2212(), this.getLabel2212().getName());
            this.add(this.getLabel211(), this.getLabel211().getName());
            this.add(this.getBattery(), this.getBattery().getName());
            this.add(this.getLabel11(), this.getLabel11().getName());
            this.initConnections();
        }
        catch (Throwable ivjExc) {
            this.image1 = this.getImage(this.getCodeBase(), "N22XP_SideView16.gif");
            this.handleException(ivjExc);
        }
    }
    
    private void initConnections() throws Exception {
        this.getw1().addPropertyChangeListener(this);
        this.getFuel().addPropertyChangeListener(this);
        this.getFuel1().addPropertyChangeListener(this);
        this.getBattery().addPropertyChangeListener(this);
        this.getw11().addPropertyChangeListener(this);
        this.getPilot().addPropertyChangeListener(this);
        this.getBaggage().addPropertyChangeListener(this);
        this.getTailPost().addPropertyChangeListener(this);
    }
    
    public static void main(final String[] args) {
        try {
            Frame frame;
            try {
                final Class aFrameClass = Class.forName("uvm.abt.edit.TestFrame");
                frame = aFrameClass.newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final Wb6 aWb6 = new Wb6();
            frame.add("Center", aWb6);
            frame.setSize(aWb6.getSize());
            aWb6.init();
            aWb6.start();
            frame.setVisible(true);
            aWb6.destroy();
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
        }
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.image1, 0, 0, this);
    }
    
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getSource() == this.getw1()) {
            this.conn0(evt);
        }
        if (evt.getSource() == this.getFuel()) {
            this.conn3(evt);
        }
        if (evt.getSource() == this.getFuel1()) {
            this.conn6(evt);
        }
        if (evt.getSource() == this.getBattery()) {
            this.conn9(evt);
        }
        if (evt.getSource() == this.getw11()) {
            this.conn12(evt);
        }
        if (evt.getSource() == this.getPilot()) {
            this.conn15(evt);
        }
        if (evt.getSource() == this.getBaggage()) {
            this.conn18(evt);
        }
        if (evt.getSource() == this.getTailPost()) {
            this.conn21(evt);
        }
    }
    
    public Wb6() {
        this.ivjBaggage = null;
        this.ivjBattery = null;
        this.ivjFuel = null;
        this.ivjFuel1 = null;
        this.ivjLabel1 = null;
        this.ivjLabel11 = null;
        this.ivjLabel21 = null;
        this.ivjLabel211 = null;
        this.ivjLabel221 = null;
        this.ivjLabel2211 = null;
        this.ivjLabel22111 = null;
        this.ivjLabel2212 = null;
        this.ivjPilot = null;
        this.ivjTailPost = null;
        this.ivjTotals = null;
        this.ivjw1 = null;
        this.ivjw11 = null;
    }
}
