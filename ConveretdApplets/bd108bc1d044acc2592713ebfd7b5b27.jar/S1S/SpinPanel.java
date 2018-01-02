// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.event.TextEvent;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Color;
import java.util.EventObject;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class SpinPanel extends Panel implements ActionListener, TextListener
{
    private Button ivjAdd;
    private TextField ivjIncrement;
    protected transient SpinPanelListener ivjSpinPanelListenerEventMulticaster;
    private Button ivjSubtract;
    private TextField ivjTextField1;
    
    public SpinPanel() {
        this.ivjAdd = null;
        this.ivjIncrement = null;
        this.ivjSpinPanelListenerEventMulticaster = null;
        this.ivjSubtract = null;
        this.ivjTextField1 = null;
        this.initialize();
    }
    
    public SpinPanel(final LayoutManager layout) {
        super(layout);
        this.ivjAdd = null;
        this.ivjIncrement = null;
        this.ivjSpinPanelListenerEventMulticaster = null;
        this.ivjSubtract = null;
        this.ivjTextField1 = null;
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.getAdd()) {
            this.conn0(e);
        }
        if (e.getSource() == this.getSubtract()) {
            this.conn4(e);
        }
    }
    
    public void addSpinPanelListener(final SpinPanelListener newListener) {
        this.ivjSpinPanelListenerEventMulticaster = SpinPanelListenerEventMulticaster.add(this.ivjSpinPanelListenerEventMulticaster, newListener);
    }
    
    private String conn0(final ActionEvent arg1) {
        String conn0Result = null;
        try {
            conn0Result = this.spunPlus(this.getTextField1().getText(), this.getIncrement().getText());
            this.conn3(conn0Result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
        return conn0Result;
    }
    
    private void conn3(final String result) {
        try {
            this.getTextField1().setText(result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private String conn4(final ActionEvent arg1) {
        String conn4Result = null;
        try {
            conn4Result = this.spunMinus(this.getTextField1().getText(), this.getIncrement().getText());
            this.conn7(conn4Result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
        return conn4Result;
    }
    
    private void conn7(final String result) {
        try {
            this.getTextField1().setText(result);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    protected void fireTextField1Text_textValueChanged(final EventObject newEvent) {
        if (this.ivjSpinPanelListenerEventMulticaster == null) {
            return;
        }
        this.ivjSpinPanelListenerEventMulticaster.textField1Text_textValueChanged(newEvent);
    }
    
    private Button getAdd() {
        if (this.ivjAdd == null) {
            try {
                (this.ivjAdd = new Button()).setName("Add");
                this.ivjAdd.setBackground(Color.lightGray);
                this.ivjAdd.setBounds(51, 2, 13, 13);
                this.ivjAdd.setForeground(Color.black);
                this.ivjAdd.setLabel("+");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjAdd;
    }
    
    private TextField getIncrement() {
        if (this.ivjIncrement == null) {
            try {
                (this.ivjIncrement = new TextField()).setName("Increment");
                this.ivjIncrement.setText("1");
                this.ivjIncrement.setBounds(27, 78, 50, 26);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjIncrement;
    }
    
    public String getIncrementText() {
        return this.getIncrement().getText();
    }
    
    private Button getSubtract() {
        if (this.ivjSubtract == null) {
            try {
                (this.ivjSubtract = new Button()).setName("Subtract");
                this.ivjSubtract.setBackground(Color.lightGray);
                this.ivjSubtract.setBounds(51, 15, 13, 13);
                this.ivjSubtract.setForeground(Color.black);
                this.ivjSubtract.setLabel("-");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjSubtract;
    }
    
    private TextField getTextField1() {
        if (this.ivjTextField1 == null) {
            try {
                (this.ivjTextField1 = new TextField()).setName("TextField1");
                this.ivjTextField1.setText("1110");
                this.ivjTextField1.setBounds(3, 2, 48, 26);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTextField1;
    }
    
    public Color getTextField1Background() {
        return this.getTextField1().getBackground();
    }
    
    public String getTextField1Text() {
        return this.getTextField1().getText();
    }
    
    private void handleException(final Throwable exception) {
    }
    
    private void initConnections() {
        this.getAdd().addActionListener(this);
        this.getSubtract().addActionListener(this);
        this.getTextField1().addTextListener(this);
    }
    
    private void initialize() {
        this.setName("SpinPanel");
        this.setName("SpinPanel");
        this.setLayout(null);
        this.setSize(66, 31);
        this.add(this.getTextField1(), this.getTextField1().getName());
        this.add(this.getAdd(), this.getAdd().getName());
        this.add(this.getSubtract(), this.getSubtract().getName());
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
            final SpinPanel aSpinPanel = new SpinPanel();
            frame.add("Center", aSpinPanel);
            frame.setSize(aSpinPanel.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    private void promConn8(final TextEvent arg1) {
        try {
            this.fireTextField1Text_textValueChanged(new EventObject(this));
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    public void removeSpinPanelListener(final SpinPanelListener newListener) {
        this.ivjSpinPanelListenerEventMulticaster = SpinPanelListenerEventMulticaster.remove(this.ivjSpinPanelListenerEventMulticaster, newListener);
    }
    
    public void setIncrementText(final String arg1) {
        this.getIncrement().setText(arg1);
    }
    
    public void setTextField1Background(final Color arg1) {
        this.getTextField1().setBackground(arg1);
    }
    
    public void setTextField1Text(final String arg1) {
        this.getTextField1().setText(arg1);
    }
    
    private String spunMinus(final String sOldInt, final String sIncrement) {
        int i = Integer.parseInt(sOldInt);
        i -= Integer.parseInt(sIncrement);
        return String.valueOf(i);
    }
    
    private String spunPlus(final String sOldInt, final String sIncrement) {
        int i = Integer.parseInt(sOldInt);
        i += Integer.parseInt(sIncrement);
        return String.valueOf(i);
    }
    
    public void textValueChanged(final TextEvent e) {
        if (e.getSource() == this.getTextField1()) {
            this.promConn8(e);
        }
    }
}
