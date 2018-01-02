// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.beans.PropertyChangeEvent;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.TextEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.applet.Applet;

public class WtEntryNew extends Applet implements TextListener
{
    String fieldSLocArm;
    String fieldSLocWeight;
    private TextField ivjArm;
    private Label ivjDesc;
    private Label ivjMoment;
    private ACMoment ivjMt;
    private TextField ivjWeight;
    protected transient PropertyChangeSupport propertyChange;
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.addPropertyChangeListener(listener);
    }
    
    private double conn0(final TextEvent arg1) {
        double conn0Result = 0.0;
        try {
            conn0Result = this.getMt().getMoment(this.getWeight().getText(), this.getArm().getText());
            this.conn3(conn0Result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
        return conn0Result;
    }
    
    private void conn10(final TextEvent arg1) {
        try {
            this.setSLocArm(this.getArm().getText());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn3(final double result) {
        try {
            this.getMoment().setText(String.valueOf(result));
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private double conn4(final TextEvent arg1) {
        double conn4Result = 0.0;
        try {
            conn4Result = this.getMt().getMoment(this.getWeight().getText(), this.getArm().getText());
            this.conn7(conn4Result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
        return conn4Result;
    }
    
    private void conn7(final double result) {
        try {
            this.getMoment().setText(String.valueOf(result));
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn8(final TextEvent arg1) {
        try {
            this.setSLocWeight(this.getWeight().getText());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
        this.propertyChange.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public String getAppletInfo() {
        return "S1S.WtEntryNew created using VisualAge for Java.";
    }
    
    private TextField getArm() {
        if (this.ivjArm == null) {
            try {
                (this.ivjArm = new TextField()).setName("Arm");
                this.ivjArm.setFont(new Font("dialog", 0, 10));
                this.ivjArm.setBounds(4, 67, 45, 26);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjArm;
    }
    
    public Color getArmBackground() {
        return this.getArm().getBackground();
    }
    
    public boolean getArmEditable() {
        return this.getArm().isEditable();
    }
    
    public String getArmText() {
        return this.getArm().getText();
    }
    
    private Label getDesc() {
        if (this.ivjDesc == null) {
            try {
                (this.ivjDesc = new Label()).setName("Desc");
                this.ivjDesc.setAlignment(1);
                this.ivjDesc.setFont(new Font("dialog", 0, 10));
                this.ivjDesc.setText("Desc");
                this.ivjDesc.setBounds(2, 1, 53, 35);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjDesc;
    }
    
    public String getDescText() {
        return this.getDesc().getText();
    }
    
    private Label getMoment() {
        if (this.ivjMoment == null) {
            try {
                (this.ivjMoment = new Label()).setName("Moment");
                this.ivjMoment.setFont(new Font("dialog", 0, 10));
                this.ivjMoment.setAlignment(1);
                this.ivjMoment.setText("0.0");
                this.ivjMoment.setBounds(4, 98, 48, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjMoment;
    }
    
    private ACMoment getMt() {
        if (this.ivjMt == null) {
            try {
                this.ivjMt = new ACMoment();
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjMt;
    }
    
    public String getSLocArm() {
        if (this.fieldSLocArm == null) {
            try {
                this.fieldSLocArm = new String();
            }
            catch (Throwable t) {
                System.err.println("Exception creating sLocArm property.");
            }
        }
        return this.fieldSLocArm;
    }
    
    public String getSLocWeight() {
        if (this.fieldSLocWeight == null) {
            try {
                this.fieldSLocWeight = new String();
            }
            catch (Throwable t) {
                System.err.println("Exception creating sLocWeight property.");
            }
        }
        return this.fieldSLocWeight;
    }
    
    private TextField getWeight() {
        if (this.ivjWeight == null) {
            try {
                (this.ivjWeight = new TextField()).setName("Weight");
                this.ivjWeight.setBackground(Color.white);
                this.ivjWeight.setBounds(4, 37, 45, 26);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjWeight;
    }
    
    public String getWeightText() {
        return this.getWeight().getText();
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public void init() {
        super.init();
        try {
            this.setName("WtEntryNew");
            this.setLayout(null);
            this.setBackground(Color.lightGray);
            this.setSize(58, 127);
            this.add(this.getDesc(), this.getDesc().getName());
            this.add(this.getWeight(), this.getWeight().getName());
            this.add(this.getArm(), this.getArm().getName());
            this.add(this.getMoment(), this.getMoment().getName());
            this.initConnections();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void initConnections() {
        this.getWeight().addTextListener(this);
        this.getArm().addTextListener(this);
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
            final WtEntryNew aWtEntryNew = new WtEntryNew();
            frame.add("Center", aWtEntryNew);
            frame.setSize(aWtEntryNew.getSize());
            aWtEntryNew.init();
            aWtEntryNew.start();
            frame.setVisible(true);
            aWtEntryNew.destroy();
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
        }
    }
    
    public void propertyChange(final PropertyChangeEvent evt) {
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.removePropertyChangeListener(listener);
    }
    
    public void setArmBackground(final Color arg1) {
        this.getArm().setBackground(arg1);
    }
    
    public void setArmEditable(final boolean arg1) {
        this.getArm().setEditable(arg1);
    }
    
    public void setArmText(final String arg1) {
        this.getArm().setText(arg1);
    }
    
    public void setDescText(final String arg1) {
        this.getDesc().setText(arg1);
    }
    
    public void setSLocArm(final String sLocArm) {
        final String oldValue = this.fieldSLocArm;
        this.firePropertyChange("sLocArm", oldValue, this.fieldSLocArm = sLocArm);
    }
    
    public void setSLocWeight(final String sLocWeight) {
        final String oldValue = this.fieldSLocWeight;
        this.firePropertyChange("sLocWeight", oldValue, this.fieldSLocWeight = sLocWeight);
    }
    
    public void setWeightText(final String arg1) {
        this.getWeight().setText(arg1);
    }
    
    public void textValueChanged(final TextEvent e) {
        if (e.getSource() == this.getWeight()) {
            this.conn0(e);
        }
        if (e.getSource() == this.getArm()) {
            this.conn4(e);
        }
        if (e.getSource() == this.getWeight()) {
            this.conn8(e);
        }
        if (e.getSource() == this.getArm()) {
            this.conn10(e);
        }
    }
    
    public WtEntryNew() {
        this.fieldSLocArm = "";
        this.fieldSLocWeight = "";
        this.ivjArm = null;
        this.ivjDesc = null;
        this.ivjMoment = null;
        this.ivjMt = null;
        this.ivjWeight = null;
        this.propertyChange = new PropertyChangeSupport(this);
    }
}
