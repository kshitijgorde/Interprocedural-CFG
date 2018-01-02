// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.objectbox.runner.util.JBLogger;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.applet.Applet;
import com.objectbox.gui.lwcomp.JBPopupMenu;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class AppletFrame extends Frame implements IAppletWindow, WindowListener
{
    private Panel ivjContentsPane;
    private Label ivjStatusLabel;
    private Panel ivjToolbar;
    private JBPopupMenu popup;
    private boolean done;
    private Applet applettorun;
    private String frametitle;
    public static int statusheight;
    static Class class$java$awt$Window;
    
    static {
        AppletFrame.statusheight = 55;
    }
    
    public AppletFrame() {
        this.ivjContentsPane = null;
        this.ivjStatusLabel = null;
        this.ivjToolbar = null;
        this.popup = null;
        this.done = false;
        this.applettorun = null;
        this.frametitle = "";
        this.initialize();
    }
    
    public AppletFrame(final String frametitle) {
        super(frametitle);
        this.ivjContentsPane = null;
        this.ivjStatusLabel = null;
        this.ivjToolbar = null;
        this.popup = null;
        this.done = false;
        this.applettorun = null;
        this.frametitle = "";
        this.frametitle = frametitle;
        this.initialize();
    }
    
    public void addApplet(final Applet applettorun) {
        this.applettorun = applettorun;
        this.setSize(this.applettorun.getSize().width + 4, this.applettorun.getSize().height + AppletFrame.statusheight);
        this.getContentsPane().add(this.applettorun, "Center");
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    protected void finalize() {
        try {
            super.finalize();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in finalizing AppletFrame " + t.toString());
        }
    }
    
    private Panel getContentsPane() {
        if (this.ivjContentsPane == null) {
            try {
                (this.ivjContentsPane = new Panel()).setName("ContentsPane");
                this.ivjContentsPane.setLayout(new BorderLayout());
                this.getContentsPane().add(this.getToolbar(), "South");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjContentsPane;
    }
    
    private Label getStatusLabel() {
        if (this.ivjStatusLabel == null) {
            try {
                (this.ivjStatusLabel = new Label()).setName("StatusLabel");
                this.ivjStatusLabel.setText("Status");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjStatusLabel;
    }
    
    private Panel getToolbar() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        if (this.ivjToolbar == null) {
            try {
                (this.ivjToolbar = new Panel()).setName("Toolbar");
                this.ivjToolbar.setLayout(new GridBagLayout());
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 0.0;
                this.getToolbar().add(this.getStatusLabel(), gridBagConstraints);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjToolbar;
    }
    
    private void handleException(final Throwable t) {
        JBLogger.log("--------- UNCAUGHT EXCEPTION ---------");
        t.printStackTrace(System.out);
    }
    
    private void initConnections() {
        this.addWindowListener(this);
    }
    
    private void initialize() {
        this.setName("AppletFrame");
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(182, 182, 200));
        this.setSize(332, 280);
        this.setVisible(false);
        this.setTitle("AppletFrame");
        this.add(this.getContentsPane(), "Center");
        this.initConnections();
        this.setIconImage(JBee.getIcon());
        this.setBackground(JBee.appcolor);
        this.setTitle(this.frametitle);
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public void kill() {
        try {
            if (this.applettorun != null) {
                this.applettorun.stop();
                this.applettorun.destroy();
                this.remove(this.applettorun);
                this.applettorun = null;
            }
            System.gc();
            this.done = true;
            JBLogger.log("Appletframe Stopping applet");
        }
        catch (Exception ex) {
            JBLogger.log("Appletframe.kill " + ex.toString());
        }
    }
    
    public static void main(final String[] array) {
        try {
            final AppletFrame appletFrame = new AppletFrame();
            try {
                final Class<?> forName = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                final Class[] array2 = { null };
                final int n = 0;
                Class class$java$awt$Window;
                if ((class$java$awt$Window = AppletFrame.class$java$awt$Window) == null) {
                    try {
                        class$java$awt$Window = (AppletFrame.class$java$awt$Window = Class.forName("java.awt.Window"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array2[n] = class$java$awt$Window;
                forName.getConstructor((Class<?>[])array2).newInstance(appletFrame);
            }
            catch (Throwable t) {}
            appletFrame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Frame");
        }
    }
    
    public void setStatus(final String text) {
        this.getStatusLabel().setText(text);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.kill();
        this.done = true;
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
