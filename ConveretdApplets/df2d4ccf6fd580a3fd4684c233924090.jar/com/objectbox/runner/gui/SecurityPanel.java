// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.util.JBLogger;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.LayoutManager;
import com.objectbox.gui.lwcomp.LWSeparator;
import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Panel;

public class SecurityPanel extends Panel
{
    private Checkbox ivjCheckboxHighSecurity;
    private Checkbox ivjCheckboxMediumSecurity;
    private Checkbox ivjCheckboxNoSecurity;
    private FocusLabel ivjLabel2;
    private CheckboxGroup ivjCheckboxGroupSecurity;
    public static final int HI = 1;
    public static final int MEDIUM = 2;
    public static final int LOW = 3;
    private Panel ivjPanelCheck;
    private GridLayout ivjPanelCheckGridLayout;
    private Panel ivjPanelHolder;
    private LWSeparator ivjLWSeparator1;
    private LWSeparator ivjLWSeparator11;
    
    public SecurityPanel() {
        this.ivjCheckboxHighSecurity = null;
        this.ivjCheckboxMediumSecurity = null;
        this.ivjCheckboxNoSecurity = null;
        this.ivjLabel2 = null;
        this.ivjCheckboxGroupSecurity = null;
        this.ivjPanelCheck = null;
        this.ivjPanelCheckGridLayout = null;
        this.ivjPanelHolder = null;
        this.ivjLWSeparator1 = null;
        this.ivjLWSeparator11 = null;
        this.initialize();
    }
    
    public SecurityPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        this.ivjCheckboxHighSecurity = null;
        this.ivjCheckboxMediumSecurity = null;
        this.ivjCheckboxNoSecurity = null;
        this.ivjLabel2 = null;
        this.ivjCheckboxGroupSecurity = null;
        this.ivjPanelCheck = null;
        this.ivjPanelCheckGridLayout = null;
        this.ivjPanelHolder = null;
        this.ivjLWSeparator1 = null;
        this.ivjLWSeparator11 = null;
    }
    
    private void connPtoP1SetTarget() {
        try {
            this.getCheckboxNoSecurity().setCheckboxGroup(this.getCheckboxGroupSecurity());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP2SetTarget() {
        try {
            this.getCheckboxMediumSecurity().setCheckboxGroup(this.getCheckboxGroupSecurity());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP3SetTarget() {
        try {
            this.getCheckboxHighSecurity().setCheckboxGroup(this.getCheckboxGroupSecurity());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP4SetTarget() {
        try {
            this.getCheckboxGroupSecurity().setSelectedCheckbox(this.getCheckboxNoSecurity());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private CheckboxGroup getCheckboxGroupSecurity() {
        if (this.ivjCheckboxGroupSecurity == null) {
            try {
                this.ivjCheckboxGroupSecurity = new CheckboxGroup();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxGroupSecurity;
    }
    
    private Checkbox getCheckboxHighSecurity() {
        if (this.ivjCheckboxHighSecurity == null) {
            try {
                (this.ivjCheckboxHighSecurity = new Checkbox()).setName("CheckboxHighSecurity");
                this.ivjCheckboxHighSecurity.setLabel("High");
                this.ivjCheckboxHighSecurity.setState(false);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxHighSecurity;
    }
    
    private Checkbox getCheckboxMediumSecurity() {
        if (this.ivjCheckboxMediumSecurity == null) {
            try {
                (this.ivjCheckboxMediumSecurity = new Checkbox()).setName("CheckboxMediumSecurity");
                this.ivjCheckboxMediumSecurity.setLabel("Medium");
                this.ivjCheckboxMediumSecurity.setState(false);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxMediumSecurity;
    }
    
    private Checkbox getCheckboxNoSecurity() {
        if (this.ivjCheckboxNoSecurity == null) {
            try {
                (this.ivjCheckboxNoSecurity = new Checkbox()).setName("CheckboxNoSecurity");
                this.ivjCheckboxNoSecurity.setLabel("Low");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxNoSecurity;
    }
    
    private FocusLabel getLabel2() {
        if (this.ivjLabel2 == null) {
            try {
                (this.ivjLabel2 = new FocusLabel()).setName("Label2");
                this.ivjLabel2.setAlignment(1);
                this.ivjLabel2.setText("Please set the security level");
                this.ivjLabel2.setBounds(13, 14, 219, 23);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel2;
    }
    
    private LWSeparator getLWSeparator1() {
        if (this.ivjLWSeparator1 == null) {
            try {
                (this.ivjLWSeparator1 = new LWSeparator()).setName("LWSeparator1");
                this.ivjLWSeparator1.setBounds(43, 46, 159, 15);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator1;
    }
    
    private LWSeparator getLWSeparator11() {
        if (this.ivjLWSeparator11 == null) {
            try {
                (this.ivjLWSeparator11 = new LWSeparator()).setName("LWSeparator11");
                this.ivjLWSeparator11.setBounds(43, 168, 159, 15);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator11;
    }
    
    private Panel getPanelCheck() {
        if (this.ivjPanelCheck == null) {
            try {
                (this.ivjPanelCheck = new Panel()).setName("PanelCheck");
                this.ivjPanelCheck.setLayout(this.getPanelCheckGridLayout());
                this.ivjPanelCheck.setBounds(77, 68, 91, 85);
                this.getPanelCheck().add(this.getCheckboxHighSecurity(), this.getCheckboxHighSecurity().getName());
                this.getPanelCheck().add(this.getCheckboxMediumSecurity(), this.getCheckboxMediumSecurity().getName());
                this.ivjPanelCheck.add(this.getCheckboxNoSecurity());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelCheck;
    }
    
    private GridLayout getPanelCheckGridLayout() {
        GridLayout gridLayout = null;
        try {
            gridLayout = new GridLayout();
            gridLayout.setRows(3);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return gridLayout;
    }
    
    private Panel getPanelHolder() {
        if (this.ivjPanelHolder == null) {
            try {
                (this.ivjPanelHolder = new Panel()).setName("PanelHolder");
                this.ivjPanelHolder.setLayout(null);
                this.ivjPanelHolder.setBackground(SystemColor.control);
                this.getPanelHolder().add(this.getLabel2(), this.getLabel2().getName());
                this.getPanelHolder().add(this.getPanelCheck(), this.getPanelCheck().getName());
                this.getPanelHolder().add(this.getLWSeparator1(), this.getLWSeparator1().getName());
                this.getPanelHolder().add(this.getLWSeparator11(), this.getLWSeparator11().getName());
                this.ivjPanelHolder.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelHolder;
    }
    
    public int getSecurityLevel() {
        if (this.getCheckboxHighSecurity().getState()) {
            return 1;
        }
        if (this.getCheckboxMediumSecurity().getState()) {
            return 2;
        }
        if (this.getCheckboxNoSecurity().getState()) {
            return 3;
        }
        return 0;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.connPtoP1SetTarget();
        this.connPtoP2SetTarget();
        this.connPtoP3SetTarget();
        this.connPtoP4SetTarget();
    }
    
    private void initialize() {
        this.setName("SecurityPanel");
        this.setLayout(new BorderLayout());
        this.setSize(240, 204);
        this.add(this.getPanelHolder(), "Center");
        this.initConnections();
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final SecurityPanel securityPanel = new SecurityPanel();
            frame.add("Center", securityPanel);
            frame.setSize(securityPanel.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    public void setSecurityLevel(final int n) {
        switch (n) {
            case 3: {
                this.getCheckboxGroupSecurity().setSelectedCheckbox(this.getCheckboxNoSecurity());
                break;
            }
            case 1: {
                this.getCheckboxGroupSecurity().setSelectedCheckbox(this.getCheckboxHighSecurity());
                break;
            }
            case 2: {
                this.getCheckboxGroupSecurity().setSelectedCheckbox(this.getCheckboxMediumSecurity());
                break;
            }
            default: {
                this.getCheckboxGroupSecurity().setSelectedCheckbox(this.getCheckboxHighSecurity());
                break;
            }
        }
        this.validate();
    }
}
