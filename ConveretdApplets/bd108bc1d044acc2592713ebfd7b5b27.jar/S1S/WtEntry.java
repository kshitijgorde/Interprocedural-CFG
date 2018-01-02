// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.TextEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Font;
import java.beans.PropertyChangeSupport;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.applet.Applet;

public class WtEntry extends Applet implements TextListener
{
    protected transient PropertyChangeListener aPropertyChangeListener;
    String fieldSArm;
    String fieldSLocArm;
    String fieldSLocationArm;
    String fieldSLocMoment;
    String fieldSLocWeight;
    private TextField ivjArm;
    private Label ivjDesc;
    private Label ivjMoment;
    private ACMoment ivjMt;
    private TextField ivjWeight;
    protected transient PropertyChangeSupport propertyChange;
    
    public synchronized void removePropertyChangeListener(final java.beans.PropertyChangeListener propertyChangeListener) {
        this.propertyChange.removePropertyChangeListener(propertyChangeListener);
    }
    
    private TextField getArm() {
        if (this.ivjArm == null) {
            try {
                (this.ivjArm = new TextField()).setName("Arm");
                this.ivjArm.setFont(new Font("dialog", 0, 10));
                this.ivjArm.setBounds(2, 70, 43, 24);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjArm;
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final WtEntryFixed wtEntryFixed = new WtEntryFixed();
            frame.add("Center", wtEntryFixed);
            frame.setSize(wtEntryFixed.getSize());
            wtEntryFixed.init();
            wtEntryFixed.start();
            frame.setVisible(true);
            wtEntryFixed.destroy();
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
        }
    }
    
    private void initConnections() {
        this.getWeight().addTextListener(this);
        this.getArm().addTextListener(this);
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
    
    private void conn3(final double n) {
        try {
            this.getMoment().setText(String.valueOf(n));
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public WtEntry() {
        this.aPropertyChangeListener = null;
        this.fieldSArm = "";
        this.fieldSLocArm = "";
        this.fieldSLocationArm = "";
        this.fieldSLocMoment = "";
        this.fieldSLocWeight = "";
        this.ivjArm = null;
        this.ivjDesc = null;
        this.ivjMoment = null;
        this.ivjMt = null;
        this.ivjWeight = null;
        this.propertyChange = new PropertyChangeSupport(this);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.propertyChange.firePropertyChange(s, o, o2);
    }
    
    private double conn0(final TextEvent textEvent) {
        double moment = 0.0;
        try {
            moment = this.getMt().getMoment(this.getWeight().getText(), this.getArm().getText());
            this.conn3(moment);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return moment;
    }
    
    private TextField getWeight() {
        if (this.ivjWeight == null) {
            try {
                (this.ivjWeight = new TextField()).setName("Weight");
                this.ivjWeight.setFont(new Font("dialog", 0, 12));
                this.ivjWeight.setBackground(Color.white);
                this.ivjWeight.setBounds(2, 45, 43, 24);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjWeight;
    }
    
    private void conn9(final double n) {
        try {
            this.getMoment().setText(String.valueOf(n));
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private Label getDesc() {
        if (this.ivjDesc == null) {
            try {
                (this.ivjDesc = new Label()).setName("Desc");
                this.ivjDesc.setAlignment(1);
                this.ivjDesc.setFont(new Font("dialog", 0, 10));
                this.ivjDesc.setText("Desc");
                this.ivjDesc.setBounds(-2, 1, 47, 43);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjDesc;
    }
    
    private ACMoment getMt() {
        if (this.ivjMt == null) {
            try {
                this.ivjMt = new ACMoment();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjMt;
    }
    
    private Label getMoment() {
        if (this.ivjMoment == null) {
            try {
                (this.ivjMoment = new Label()).setName("Moment");
                this.ivjMoment.setFont(new Font("dialog", 0, 10));
                this.ivjMoment.setAlignment(1);
                this.ivjMoment.setText("0");
                this.ivjMoment.setBounds(2, 95, 45, 22);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjMoment;
    }
    
    public void setSLocWeight(final String fieldSLocWeight) {
        this.firePropertyChange("sLocWeight", this.fieldSLocWeight, this.fieldSLocWeight = fieldSLocWeight);
    }
    
    private double conn6(final TextEvent textEvent) {
        double moment = 0.0;
        try {
            moment = this.getMt().getMoment(this.getWeight().getText(), this.getArm().getText());
            this.conn9(moment);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return moment;
    }
    
    public synchronized void addPropertyChangeListener(final java.beans.PropertyChangeListener propertyChangeListener) {
        this.propertyChange.addPropertyChangeListener(propertyChangeListener);
    }
    
    protected void fireUpdTotals(final PropertyChangeEvent propertyChangeEvent) {
        if (this.aPropertyChangeListener == null) {
            return;
        }
        this.aPropertyChangeListener.updTotals(propertyChangeEvent);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener b) {
        this.aPropertyChangeListener = PropertyChangeEventMulticaster.remove(this.aPropertyChangeListener, b);
    }
    
    public void init() {
        super.init();
        try {
            this.setName("WtEntry");
            this.setLayout(null);
            this.setBackground(Color.lightGray);
            this.setSize(47, 121);
            this.add(this.getDesc(), this.getDesc().getName());
            this.add(this.getWeight(), this.getWeight().getName());
            this.add(this.getMoment(), this.getMoment().getName());
            this.add(this.getArm(), this.getArm().getName());
            this.initConnections();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void handleException(final Throwable t) {
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (textEvent.getSource() == this.getWeight()) {
            this.conn0(textEvent);
        }
        if (textEvent.getSource() == this.getArm()) {
            this.conn6(textEvent);
        }
    }
    
    public String getAppletInfo() {
        return "S1S.WtEntryFixed created using VisualAge for Java.";
    }
}
