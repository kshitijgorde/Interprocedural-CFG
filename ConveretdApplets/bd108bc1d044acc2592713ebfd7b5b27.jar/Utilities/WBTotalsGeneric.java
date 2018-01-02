// 
// Decompiled by Procyon v0.5.30
// 

package Utilities;

import java.beans.PropertyChangeEvent;
import S1S.WBTotals;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.beans.PropertyChangeListener;
import java.awt.LayoutManager;
import java.beans.PropertyChangeSupport;
import java.awt.List;
import java.awt.Label;
import java.awt.Panel;

public abstract class WBTotalsGeneric extends Panel
{
    private Label ivjAnalysisLabel;
    private Label ivjCG;
    private Label ivjCGLabel;
    private List ivjMsgList;
    private Label ivjMtLabel;
    private Label ivjTotalMoment;
    private Label ivjTotalWeight;
    private Label ivjWtLabel;
    protected transient PropertyChangeSupport propertyChange;
    
    public WBTotalsGeneric() {
        this.ivjAnalysisLabel = null;
        this.ivjCG = null;
        this.ivjCGLabel = null;
        this.ivjMsgList = null;
        this.ivjMtLabel = null;
        this.ivjTotalMoment = null;
        this.ivjTotalWeight = null;
        this.ivjWtLabel = null;
        this.propertyChange = new PropertyChangeSupport(this);
        this.initialize();
    }
    
    public WBTotalsGeneric(final LayoutManager layout) {
        super(layout);
        this.ivjAnalysisLabel = null;
        this.ivjCG = null;
        this.ivjCGLabel = null;
        this.ivjMsgList = null;
        this.ivjMtLabel = null;
        this.ivjTotalMoment = null;
        this.ivjTotalWeight = null;
        this.ivjWtLabel = null;
        this.propertyChange = new PropertyChangeSupport(this);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.addPropertyChangeListener(listener);
    }
    
    public abstract void analyzeResults(final String p0, final String p1);
    
    public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
        this.propertyChange.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public String formatDouble(final double dInput, final int idecplaces) {
        final NumberFormat integerFormatter = NumberFormat.getNumberInstance();
        integerFormatter.setMaximumFractionDigits(idecplaces);
        integerFormatter.setGroupingUsed(false);
        final String sX = integerFormatter.format(dInput);
        return sX;
    }
    
    private Label getAnalysisLabel() {
        if (this.ivjAnalysisLabel == null) {
            try {
                (this.ivjAnalysisLabel = new Label()).setName("AnalysisLabel");
                this.ivjAnalysisLabel.setFont(new Font("dialog", 1, 12));
                this.ivjAnalysisLabel.setAlignment(2);
                this.ivjAnalysisLabel.setText("Analysis");
                this.ivjAnalysisLabel.setBounds(7, 49, 50, 28);
                this.ivjAnalysisLabel.setForeground(Color.red);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjAnalysisLabel;
    }
    
    public Color getAnalysisLabelBackground() {
        return this.getAnalysisLabel().getBackground();
    }
    
    public Font getAnalysisLabelFont() {
        return this.getAnalysisLabel().getFont();
    }
    
    public Color getAnalysisLabelForeground() {
        return this.getAnalysisLabel().getForeground();
    }
    
    private Label getCG() {
        if (this.ivjCG == null) {
            try {
                (this.ivjCG = new Label()).setName("CG");
                this.ivjCG.setFont(new Font("dialog", 1, 12));
                this.ivjCG.setAlignment(2);
                this.ivjCG.setText("0");
                this.ivjCG.setBounds(157, 26, 57, 21);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjCG;
    }
    
    public Color getCGBackground() {
        return this.getCG().getBackground();
    }
    
    public Font getCGFont() {
        return this.getCG().getFont();
    }
    
    public Color getCGForeground() {
        return this.getCG().getForeground();
    }
    
    private Label getCGLabel() {
        if (this.ivjCGLabel == null) {
            try {
                (this.ivjCGLabel = new Label()).setName("CGLabel");
                this.ivjCGLabel.setFont(new Font("dialog", 0, 10));
                this.ivjCGLabel.setAlignment(2);
                this.ivjCGLabel.setText("C.G.");
                this.ivjCGLabel.setBounds(175, 4, 34, 21);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjCGLabel;
    }
    
    public Color getCGLabelBackground() {
        return this.getCGLabel().getBackground();
    }
    
    public Color getCGLabelForeground() {
        return this.getCGLabel().getForeground();
    }
    
    public double getDblFromString(String sArg) throws NumberFormatException {
        if (sArg.length() == 0) {
            sArg = "0";
        }
        return Double.valueOf(sArg);
    }
    
    public int getIntFromString(String sArg) throws NumberFormatException {
        if (sArg.length() == 0) {
            sArg = "0";
        }
        if (sArg.compareTo("-") == 0) {
            sArg = "0";
        }
        return Integer.parseInt(sArg);
    }
    
    public List getMsgList() {
        if (this.ivjMsgList == null) {
            try {
                (this.ivjMsgList = new List()).setName("MsgList");
                this.ivjMsgList.setBounds(59, 52, 333, 57);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjMsgList;
    }
    
    public Color getMsgListBackground() {
        return this.getMsgList().getBackground();
    }
    
    public Font getMsgListFont() {
        return this.getMsgList().getFont();
    }
    
    public Color getMsgListForeground() {
        return this.getMsgList().getForeground();
    }
    
    public int getMsgListRows() {
        return this.getMsgList().getRows();
    }
    
    private Label getMtLabel() {
        if (this.ivjMtLabel == null) {
            try {
                (this.ivjMtLabel = new Label()).setName("MtLabel");
                this.ivjMtLabel.setFont(new Font("dialog", 0, 10));
                this.ivjMtLabel.setText("Total Moment");
                this.ivjMtLabel.setBounds(88, 4, 69, 21);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjMtLabel;
    }
    
    public Color getMtLabelBackground() {
        return this.getMtLabel().getBackground();
    }
    
    public Color getMtLabelForeground() {
        return this.getMtLabel().getForeground();
    }
    
    private Label getTotalMoment() {
        if (this.ivjTotalMoment == null) {
            try {
                (this.ivjTotalMoment = new Label()).setName("TotalMoment");
                this.ivjTotalMoment.setFont(new Font("dialog", 1, 12));
                this.ivjTotalMoment.setAlignment(2);
                this.ivjTotalMoment.setText("0");
                this.ivjTotalMoment.setBounds(93, 26, 57, 21);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTotalMoment;
    }
    
    public Color getTotalMomentBackground() {
        return this.getTotalMoment().getBackground();
    }
    
    public Font getTotalMomentFont() {
        return this.getTotalMoment().getFont();
    }
    
    public Color getTotalMomentForeground() {
        return this.getTotalMoment().getForeground();
    }
    
    private Label getTotalWeight() {
        if (this.ivjTotalWeight == null) {
            try {
                (this.ivjTotalWeight = new Label()).setName("TotalWeight");
                this.ivjTotalWeight.setFont(new Font("dialog", 1, 12));
                this.ivjTotalWeight.setAlignment(2);
                this.ivjTotalWeight.setText("0");
                this.ivjTotalWeight.setBounds(7, 26, 57, 21);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTotalWeight;
    }
    
    public Color getTotalWeightBackground() {
        return this.getTotalWeight().getBackground();
    }
    
    public Font getTotalWeightFont() {
        return this.getTotalWeight().getFont();
    }
    
    public Color getTotalWeightForeground() {
        return this.getTotalWeight().getForeground();
    }
    
    private Label getWtLabel() {
        if (this.ivjWtLabel == null) {
            try {
                (this.ivjWtLabel = new Label()).setName("WtLabel");
                this.ivjWtLabel.setFont(new Font("dialog", 0, 10));
                this.ivjWtLabel.setText("Total Weight");
                this.ivjWtLabel.setBounds(8, 5, 69, 21);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjWtLabel;
    }
    
    public Color getWtLabelBackground() {
        return this.getWtLabel().getBackground();
    }
    
    public Color getWtLabelForeground() {
        return this.getWtLabel().getForeground();
    }
    
    private void handleException(final Throwable exception) {
    }
    
    private void initialize() {
        this.setName("WBTotals");
        this.setName("WBTotals");
        this.setLayout(null);
        this.setSize(395, 114);
        this.add(this.getWtLabel(), this.getWtLabel().getName());
        this.add(this.getMtLabel(), this.getMtLabel().getName());
        this.add(this.getCGLabel(), this.getCGLabel().getName());
        this.add(this.getTotalMoment(), this.getTotalMoment().getName());
        this.add(this.getCG(), this.getCG().getName());
        this.add(this.getTotalWeight(), this.getTotalWeight().getName());
        this.add(this.getMsgList(), this.getMsgList().getName());
        this.add(this.getAnalysisLabel(), this.getAnalysisLabel().getName());
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
            final WBTotals aWBTotals = new WBTotals();
            frame.add("Center", aWBTotals);
            frame.setSize(aWBTotals.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.removePropertyChangeListener(listener);
    }
    
    public void setAnalysisLabelBackground(final Color arg1) {
        this.getAnalysisLabel().setBackground(arg1);
    }
    
    public void setAnalysisLabelFont(final Font arg1) {
        this.getAnalysisLabel().setFont(arg1);
    }
    
    public void setAnalysisLabelForeground(final Color arg1) {
        this.getAnalysisLabel().setForeground(arg1);
    }
    
    public void setCGBackground(final Color arg1) {
        this.getCG().setBackground(arg1);
    }
    
    public void setCGFont(final Font arg1) {
        this.getCG().setFont(arg1);
    }
    
    public void setCGForeground(final Color arg1) {
        this.getCG().setForeground(arg1);
    }
    
    public void setCGLabelBackground(final Color arg1) {
        this.getCGLabel().setBackground(arg1);
    }
    
    public void setCGLabelForeground(final Color arg1) {
        this.getCGLabel().setForeground(arg1);
    }
    
    public void setMsgListBackground(final Color arg1) {
        this.getMsgList().setBackground(arg1);
    }
    
    public void setMsgListFont(final Font arg1) {
        this.getMsgList().setFont(arg1);
    }
    
    public void setMsgListForeground(final Color arg1) {
        this.getMsgList().setForeground(arg1);
    }
    
    public void setMtLabelBackground(final Color arg1) {
        this.getMtLabel().setBackground(arg1);
    }
    
    public void setMtLabelForeground(final Color arg1) {
        this.getMtLabel().setForeground(arg1);
    }
    
    public void setTotalMomentBackground(final Color arg1) {
        this.getTotalMoment().setBackground(arg1);
    }
    
    public void setTotalMomentFont(final Font arg1) {
        this.getTotalMoment().setFont(arg1);
    }
    
    public void setTotalMomentForeground(final Color arg1) {
        this.getTotalMoment().setForeground(arg1);
    }
    
    public void setTotalWeightBackground(final Color arg1) {
        this.getTotalWeight().setBackground(arg1);
    }
    
    public void setTotalWeightFont(final Font arg1) {
        this.getTotalWeight().setFont(arg1);
    }
    
    public void setTotalWeightForeground(final Color arg1) {
        this.getTotalWeight().setForeground(arg1);
    }
    
    public void setWtLabelBackground(final Color arg1) {
        this.getWtLabel().setBackground(arg1);
    }
    
    public void setWtLabelForeground(final Color arg1) {
        this.getWtLabel().setForeground(arg1);
    }
    
    public void updTotals(final PropertyChangeEvent evt, final String sArm, final String sWt) {
        double dMomentChange = 0.0;
        double dCG = 0.0;
        final String sNewValue = (String)evt.getNewValue();
        final String sOldValue = (String)evt.getOldValue();
        if (evt.getPropertyName().equals("sLocWeight")) {
            final int iNewWeight = this.getIntFromString(sNewValue);
            final int iOldWeight = this.getIntFromString(sOldValue);
            final int iTotalWeight = Integer.parseInt(this.getTotalWeight().getText());
            final int iNewTotal = iTotalWeight + iNewWeight - iOldWeight;
            this.getTotalWeight().setText(String.valueOf(iNewTotal));
            dMomentChange = Double.valueOf(sArm) * (iNewWeight - iOldWeight);
        }
        if (evt.getPropertyName().equals("sLocArm")) {
            final double dNewArm = this.getDblFromString(sNewValue);
            final double dOldArm = this.getDblFromString(sOldValue);
            final double dArmChange = dNewArm - dOldArm;
            final int iNewWeight2 = this.getIntFromString(sWt);
            dMomentChange = dArmChange * iNewWeight2;
        }
        final String sOldMoment = this.getTotalMoment().getText();
        final double dOldMoment = this.getDblFromString(sOldMoment);
        final double dNewMoment = dOldMoment + dMomentChange;
        final String sMtDisplay = this.formatDouble(dNewMoment, 2);
        this.getTotalMoment().setText(sMtDisplay);
        final int iCurrentWeight = Integer.parseInt(this.getTotalWeight().getText());
        if (iCurrentWeight == 0) {
            dCG = 0.0;
        }
        else {
            dCG = dNewMoment / iCurrentWeight;
        }
        final String sCGDisplay = this.formatDouble(dCG, 2);
        this.getCG().setText(sCGDisplay);
        this.analyzeResults(this.getTotalWeight().getText(), String.valueOf(dCG));
    }
}
