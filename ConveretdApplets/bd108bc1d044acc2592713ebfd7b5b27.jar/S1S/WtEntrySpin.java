// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.Frame;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.TextEvent;
import java.util.EventObject;
import java.beans.PropertyChangeListener;
import java.awt.LayoutManager;
import java.beans.PropertyChangeSupport;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.awt.Panel;

public class WtEntrySpin extends Panel implements TextListener, SpinPanelListener
{
    String fieldSLocArm;
    String fieldSLocWeight;
    private TextField ivjArm;
    private Label ivjDesc;
    private Label ivjMoment;
    private ACMoment ivjMt;
    private SpinPanel ivjWeight;
    protected transient PropertyChangeSupport propertyChange;
    
    public WtEntrySpin() {
        this.fieldSLocArm = "";
        this.fieldSLocWeight = "";
        this.ivjArm = null;
        this.ivjDesc = null;
        this.ivjMoment = null;
        this.ivjMt = null;
        this.ivjWeight = null;
        this.propertyChange = new PropertyChangeSupport(this);
        this.initialize();
    }
    
    public WtEntrySpin(final LayoutManager layout) {
        super(layout);
        this.fieldSLocArm = "";
        this.fieldSLocWeight = "";
        this.ivjArm = null;
        this.ivjDesc = null;
        this.ivjMoment = null;
        this.ivjMt = null;
        this.ivjWeight = null;
        this.propertyChange = new PropertyChangeSupport(this);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.addPropertyChangeListener(listener);
    }
    
    private void conn11(final String result) {
        try {
            this.getMoment().setText(result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn15(final EventObject arg1) {
        try {
            this.setSLocWeight(this.getWeight().getTextField1Text());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void conn16(final TextEvent arg1) {
        try {
            this.setSLocArm(this.getArm().getText());
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private String conn2(final EventObject arg1) {
        String conn2Result = null;
        try {
            conn2Result = this.getMt().getMomentString(this.getWeight().getTextField1Text(), this.getArm().getText());
            this.conn5(conn2Result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
        return conn2Result;
    }
    
    private void conn5(final String result) {
        try {
            this.getMoment().setText(result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private String conn8(final TextEvent arg1) {
        String conn8Result = null;
        try {
            conn8Result = this.getMt().getMomentString(this.getWeight().getTextField1Text(), this.getArm().getText());
            this.conn11(conn8Result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
        return conn8Result;
    }
    
    public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
        this.propertyChange.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    private TextField getArm() {
        if (this.ivjArm == null) {
            try {
                (this.ivjArm = new TextField()).setName("Arm");
                this.ivjArm.setFont(new Font("dialog", 0, 10));
                this.ivjArm.setText("0");
                this.ivjArm.setBounds(9, 73, 48, 22);
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
                this.ivjDesc.setBounds(5, 5, 63, 34);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjDesc;
    }
    
    public Color getDescBackground() {
        return this.getDesc().getBackground();
    }
    
    public String getDescText() {
        return this.getDesc().getText();
    }
    
    private Label getMoment() {
        if (this.ivjMoment == null) {
            try {
                (this.ivjMoment = new Label()).setName("Moment");
                this.ivjMoment.setAlignment(1);
                this.ivjMoment.setFont(new Font("dialog", 0, 10));
                this.ivjMoment.setText("0");
                this.ivjMoment.setBounds(5, 96, 58, 22);
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
    
    private SpinPanel getWeight() {
        if (this.ivjWeight == null) {
            try {
                (this.ivjWeight = new SpinPanel()).setName("Weight");
                this.ivjWeight.setTextField1Text("0");
                this.ivjWeight.setBackground(Color.lightGray);
                this.ivjWeight.setBounds(5, 41, 70, 34);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjWeight;
    }
    
    public Color getWeightBackground() {
        return this.getWeight().getBackground();
    }
    
    public String getWeightIncrementText() {
        return this.getWeight().getIncrementText();
    }
    
    public Color getWeightTextField1Background() {
        return this.getWeight().getTextField1Background();
    }
    
    private void handleException(final Throwable exception) {
    }
    
    private void initConnections() {
        this.getWeight().addSpinPanelListener(this);
        this.getArm().addTextListener(this);
    }
    
    private void initialize() {
        this.setName("WtEntrySpin");
        this.setName("WtEntrySpin");
        this.setLayout(null);
        this.setSize(76, 120);
        this.add(this.getDesc(), this.getDesc().getName());
        this.add(this.getWeight(), this.getWeight().getName());
        this.add(this.getArm(), this.getArm().getName());
        this.add(this.getMoment(), this.getMoment().getName());
        this.initConnections();
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
            final WtEntrySpin aWtEntrySpin = new WtEntrySpin();
            frame.add("Center", aWtEntrySpin);
            frame.setSize(aWtEntrySpin.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.awt.Panel");
        }
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
    
    public void setDescBackground(final Color arg1) {
        this.getDesc().setBackground(arg1);
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
    
    public void setWeightBackground(final Color arg1) {
        this.getWeight().setBackground(arg1);
    }
    
    public void setWeightIncrementText(final String arg1) {
        this.getWeight().setIncrementText(arg1);
    }
    
    public void setWeightTextField1Background(final Color arg1) {
        this.getWeight().setTextField1Background(arg1);
    }
    
    public void textField1Text_textValueChanged(final EventObject newEvent) {
        if (newEvent.getSource() == this.getWeight()) {
            this.conn2(newEvent);
        }
        if (newEvent.getSource() == this.getWeight()) {
            this.conn15(newEvent);
        }
    }
    
    public void textValueChanged(final TextEvent e) {
        if (e.getSource() == this.getArm()) {
            this.conn16(e);
        }
        if (e.getSource() == this.getArm()) {
            this.conn8(e);
        }
    }
}
