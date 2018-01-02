// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.objectbox.runner.model.JBProperties;
import java.awt.event.WindowEvent;
import java.awt.Panel;
import com.objectbox.runner.model.JBPropertyEditor;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class AppletPropertiesEditorFrame extends Frame implements WindowListener, JBPropertyEditor
{
    private Panel ivjContentsPane;
    protected AppletPropertiesEditor ape;
    
    public AppletPropertiesEditorFrame() {
        this.ivjContentsPane = null;
        this.ape = null;
        this.initialize();
    }
    
    public AppletPropertiesEditorFrame(final String s) {
        super(s);
        this.ivjContentsPane = null;
        this.ape = null;
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void edit(final JBProperties jbProperties) {
        this.ape.edit(jbProperties);
        if (!this.isVisible()) {
            this.setVisible(true);
        }
        this.repaint();
    }
    
    private Panel getContentsPane() {
        if (this.ivjContentsPane == null) {
            try {
                (this.ivjContentsPane = new Panel()).setName("ContentsPane");
                this.ivjContentsPane.setLayout(new BorderLayout());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjContentsPane;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.addWindowListener(this);
    }
    
    private void initialize() {
        this.setIconImage(JBee.getIcon());
        this.setName("TestEditorFrame");
        this.setLayout(new BorderLayout());
        this.setSize(350, 400);
        this.setResizable(true);
        this.add(this.getContentsPane(), "Center");
        this.initConnections();
        this.setIconImage(JBee.getIcon());
        this.setTitle("Properties");
        this.setSize(500, 400);
        this.ape = new AppletPropertiesEditor();
        this.getContentsPane().add(this.ape, "Center");
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().height / 2);
    }
    
    public static void main(final String[] array) {
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
