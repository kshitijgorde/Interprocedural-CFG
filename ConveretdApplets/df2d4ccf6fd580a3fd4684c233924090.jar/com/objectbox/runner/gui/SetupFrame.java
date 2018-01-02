// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.Hashtable;
import com.objectbox.runner.util.JBLogger;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.Properties;
import com.objectbox.gui.lwcomp.FlatButton;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class SetupFrame extends Frame implements ActionListener, WindowListener
{
    private Panel ivjContentsPane;
    private SetupPanel ivjSetupPanel1;
    private FlatButton ivjFlatButtonCancel;
    private FlatButton ivjFlatButtonSave;
    private Panel ivjPanelButtons;
    private Frame owner;
    private Properties props;
    static Class class$java$awt$Window;
    
    public SetupFrame() {
        this.ivjContentsPane = null;
        this.ivjSetupPanel1 = null;
        this.ivjFlatButtonCancel = null;
        this.ivjFlatButtonSave = null;
        this.ivjPanelButtons = null;
        this.owner = null;
        this.props = null;
        this.initialize();
    }
    
    public SetupFrame(final Frame owner, final Properties props) {
        this.ivjContentsPane = null;
        this.ivjSetupPanel1 = null;
        this.ivjFlatButtonCancel = null;
        this.ivjFlatButtonSave = null;
        this.ivjPanelButtons = null;
        this.owner = null;
        this.props = null;
        this.owner = owner;
        this.props = props;
        this.initialize();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getFlatButtonCancel()) {
            this.connEtoM1(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonSave()) {
            this.connEtoC2(actionEvent);
        }
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC2(final ActionEvent actionEvent) {
        try {
            this.flatButtonSave_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoM1(final ActionEvent actionEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void flatButtonSave_ActionPerformed(final ActionEvent actionEvent) {
        this.getPanel().setProperties();
        final JBee bee = (JBee)this.owner;
        JBee.preferences = this.getPanel().getProperties();
        final JBee bee2 = (JBee)this.owner;
        if (JBee.preferences.getProperty("useproxy").equals("true")) {
            ((Hashtable<String, String>)System.getProperties()).put("proxySet", "true");
            final Properties properties = System.getProperties();
            final String s = "proxyHost";
            final JBee bee3 = (JBee)this.owner;
            ((Hashtable<String, String>)properties).put(s, JBee.preferences.getProperty("proxyserver"));
            final Properties properties2 = System.getProperties();
            final String s2 = "proxyPort";
            final JBee bee4 = (JBee)this.owner;
            ((Hashtable<String, String>)properties2).put(s2, JBee.preferences.getProperty("proxyport"));
        }
        else {
            ((Hashtable<String, String>)System.getProperties()).put("proxySet", "false");
        }
        this.setVisible(false);
        this.dispose();
    }
    
    private Panel getContentsPane() {
        if (this.ivjContentsPane == null) {
            try {
                (this.ivjContentsPane = new Panel()).setName("ContentsPane");
                this.ivjContentsPane.setLayout(new BorderLayout());
                this.getContentsPane().add(this.getSetupPanel1(), "Center");
                this.getContentsPane().add(this.getPanelButtons(), "South");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjContentsPane;
    }
    
    private FlatButton getFlatButtonCancel() {
        if (this.ivjFlatButtonCancel == null) {
            try {
                (this.ivjFlatButtonCancel = new FlatButton()).setName("FlatButtonCancel");
                this.ivjFlatButtonCancel.setFixedsize(new Dimension(75, 30));
                this.ivjFlatButtonCancel.setLabel("Cancel");
                this.ivjFlatButtonCancel.setImageResource("/images/cancel.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonCancel;
    }
    
    private FlatButton getFlatButtonSave() {
        if (this.ivjFlatButtonSave == null) {
            try {
                (this.ivjFlatButtonSave = new FlatButton()).setName("FlatButtonSave");
                this.ivjFlatButtonSave.setFixedsize(new Dimension(75, 30));
                this.ivjFlatButtonSave.setLabel("OK");
                this.ivjFlatButtonSave.setImageResource("/images/ok.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonSave;
    }
    
    public SetupPanel getPanel() {
        return this.getSetupPanel1();
    }
    
    private Panel getPanelButtons() {
        if (this.ivjPanelButtons == null) {
            try {
                (this.ivjPanelButtons = new Panel()).setName("PanelButtons");
                this.ivjPanelButtons.setLayout(new FlowLayout());
                this.ivjPanelButtons.setBackground(SystemColor.control);
                this.getPanelButtons().add(this.getFlatButtonSave(), this.getFlatButtonSave().getName());
                this.getPanelButtons().add(this.getFlatButtonCancel(), this.getFlatButtonCancel().getName());
                this.getPanelButtons().setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelButtons;
    }
    
    private SetupPanel getSetupPanel1() {
        if (this.ivjSetupPanel1 == null) {
            try {
                (this.ivjSetupPanel1 = new SetupPanel()).setName("SetupPanel1");
                this.ivjSetupPanel1.setBackground(new Color(177, 156, 204));
                this.ivjSetupPanel1.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjSetupPanel1;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.addWindowListener(this);
        this.getFlatButtonCancel().addActionListener(this);
        this.getFlatButtonSave().addActionListener(this);
    }
    
    private void initialize() {
        this.setIconImage(JBee.getIcon());
        this.setName("SetupFrame");
        this.setTitle("JBee setup");
        this.setLayout(new BorderLayout());
        this.setSize(308, 272);
        this.setResizable(false);
        this.add(this.getContentsPane(), "Center");
        this.initConnections();
        this.getPanel().setProperties(this.props);
        this.setBackground(JBee.appcolor);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().height / 2);
    }
    
    public static void main(final String[] array) {
        try {
            final SetupFrame setupFrame = new SetupFrame(new Frame(), null);
            try {
                final Class<?> forName = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                final Class[] array2 = { null };
                final int n = 0;
                Class class$java$awt$Window;
                if ((class$java$awt$Window = SetupFrame.class$java$awt$Window) == null) {
                    try {
                        class$java$awt$Window = (SetupFrame.class$java$awt$Window = Class.forName("java.awt.Window"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array2[n] = class$java$awt$Window;
                forName.getConstructor((Class<?>[])array2).newInstance(setupFrame);
            }
            catch (Throwable t) {}
            setupFrame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Frame");
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.connEtoC1(windowEvent);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
