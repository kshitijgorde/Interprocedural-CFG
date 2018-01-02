// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.TextEvent;
import java.beans.PropertyChangeSupport;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.TextListener;
import java.applet.Applet;

public class WtEntryFixed extends Applet implements TextListener
{
    protected transient PropertyChangeListener aPropertyChangeListener;
    String fieldSArm;
    String fieldSLocMoment;
    String fieldSLocWeight;
    private Label ivjArm;
    private Label ivjDesc;
    private Label ivjMoment;
    private ACMoment ivjMt;
    private TextField ivjWeight;
    protected transient PropertyChangeSupport propertyChange;
    
    public synchronized void addPropertyChangeListener(final java.beans.PropertyChangeListener listener) {
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
    
    private void conn3(final double result) {
        try {
            this.getMoment().setText(String.valueOf(result));
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn4(final TextEvent arg1) {
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
    
    protected void fireUpdTotals(final PropertyChangeEvent event) {
        if (this.aPropertyChangeListener == null) {
            return;
        }
        this.aPropertyChangeListener.updTotals(event);
    }
    
    public String getAppletInfo() {
        return "S1S.WtEntryFixed created using VisualAge for Java.";
    }
    
    private Label getArm() {
        if (this.ivjArm == null) {
            try {
                (this.ivjArm = new Label()).setName("Arm");
                this.ivjArm.setAlignment(1);
                this.ivjArm.setFont(new Font("dialog", 0, 10));
                this.ivjArm.setText("0");
                this.ivjArm.setBounds(2, 73, 46, 20);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjArm;
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
                this.ivjDesc.setBounds(0, 3, 47, 43);
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
                this.ivjMoment.setText("0");
                this.ivjMoment.setBounds(3, 94, 45, 22);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjMoment;
    }
    
    public String getMomentText() {
        return this.getMoment().getText();
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
    
    public String getSArm() {
        if (this.fieldSArm == null) {
            try {
                this.fieldSArm = new String();
            }
            catch (Throwable t) {
                System.err.println("Exception creating sArm property.");
            }
        }
        return this.fieldSArm;
    }
    
    public String getSLocMoment() {
        if (this.fieldSLocMoment == null) {
            try {
                this.fieldSLocMoment = new String();
            }
            catch (Throwable t) {
                System.err.println("Exception creating sLocMoment property.");
            }
        }
        return this.fieldSLocMoment;
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
                this.ivjWeight.setFont(new Font("dialog", 0, 12));
                this.ivjWeight.setBackground(Color.white);
                this.ivjWeight.setBounds(3, 45, 43, 24);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjWeight;
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public void init() {
        super.init();
        try {
            this.setName("WtEntryFixed");
            this.setLayout(null);
            this.setBackground(Color.lightGray);
            this.setSize(50, 115);
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
            final WtEntryFixed aWtEntryFixed = new WtEntryFixed();
            frame.add("Center", aWtEntryFixed);
            frame.setSize(aWtEntryFixed.getSize());
            aWtEntryFixed.init();
            aWtEntryFixed.start();
            frame.setVisible(true);
            aWtEntryFixed.destroy();
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
        }
    }
    
    public void propertyChange(final java.beans.PropertyChangeEvent evt) {
    }
    
    public synchronized void removePropertyChangeListener(final java.beans.PropertyChangeListener listener) {
        this.propertyChange.removePropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener newListener) {
        this.aPropertyChangeListener = PropertyChangeEventMulticaster.remove(this.aPropertyChangeListener, newListener);
    }
    
    public void setArmText(final String arg1) {
        this.getArm().setText(arg1);
    }
    
    public void setDescText(final String arg1) {
        this.getDesc().setText(arg1);
    }
    
    public void setMomentText(final String arg1) {
        this.getMoment().setText(arg1);
    }
    
    public void setSLocWeight(final String sLocWeight) {
        final String oldValue = this.fieldSLocWeight;
        this.firePropertyChange("sLocWeight", oldValue, this.fieldSLocWeight = sLocWeight);
    }
    
    public void textValueChanged(final TextEvent e) {
        if (e.getSource() == this.getWeight()) {
            this.conn0(e);
        }
        if (e.getSource() == this.getWeight()) {
            this.conn4(e);
        }
    }
    
    public WtEntryFixed() {
        this.aPropertyChangeListener = null;
        this.fieldSArm = "";
        this.fieldSLocMoment = "";
        this.fieldSLocWeight = "";
        this.ivjArm = null;
        this.ivjDesc = null;
        this.ivjMoment = null;
        this.ivjMt = null;
        this.ivjWeight = null;
        this.propertyChange = new PropertyChangeSupport(this);
    }
}
