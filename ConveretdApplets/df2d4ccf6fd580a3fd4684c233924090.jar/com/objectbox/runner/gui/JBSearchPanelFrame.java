// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.model.JBAppletProperties;
import com.objectbox.runner.util.JBLogger;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class JBSearchPanelFrame extends Frame implements WindowListener
{
    private Panel ivjContentsPane;
    protected JBManagerPanel jbmanager;
    private JBSearchPanel ivJBSearchPanel;
    private JBSearchPanel ivjJBSearchPanel1;
    static Class class$java$awt$Window;
    
    public JBSearchPanelFrame() {
        this.ivjContentsPane = null;
        this.jbmanager = null;
        this.ivJBSearchPanel = null;
        this.ivjJBSearchPanel1 = null;
        this.initialize();
    }
    
    public JBSearchPanelFrame(final String s) {
        super(s);
        this.ivjContentsPane = null;
        this.jbmanager = null;
        this.ivJBSearchPanel = null;
        this.ivjJBSearchPanel1 = null;
        this.initialize();
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private Panel getContentsPane() {
        if (this.ivjContentsPane == null) {
            try {
                (this.ivjContentsPane = new Panel()).setName("ContentsPane");
                this.ivjContentsPane.setLayout(new BorderLayout());
                this.getContentsPane().add(this.getJBSearchPanel1(), "Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjContentsPane;
    }
    
    private JBSearchPanel getJBSearchPanel1() {
        if (this.ivjJBSearchPanel1 == null) {
            try {
                (this.ivjJBSearchPanel1 = new JBSearchPanel()).setName("JBSearchPanel1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjJBSearchPanel1;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.addWindowListener(this);
    }
    
    private void initialize() {
        AppRegistry.getInstance().put("SearchFrame", this);
        this.setName("JBSearchPanelFrame");
        this.setLayout(new BorderLayout());
        this.setSize(660, 503);
        this.add(this.getContentsPane(), "Center");
        this.initConnections();
        this.setSize(640, 470);
        this.setIconImage(JBee.getIcon());
        this.setTitle("JBee Search and Admin");
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().height / 2);
        AppRegistry.getInstance().put("JBSearchPanelFrame", this);
    }
    
    public static void main(final String[] array) {
        try {
            final JBSearchPanelFrame jbSearchPanelFrame = new JBSearchPanelFrame();
            try {
                final Class<?> forName = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                final Class[] array2 = { null };
                final int n = 0;
                Class class$java$awt$Window;
                if ((class$java$awt$Window = JBSearchPanelFrame.class$java$awt$Window) == null) {
                    try {
                        class$java$awt$Window = (JBSearchPanelFrame.class$java$awt$Window = Class.forName("java.awt.Window"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array2[n] = class$java$awt$Window;
                forName.getConstructor((Class<?>[])array2).newInstance(jbSearchPanelFrame);
            }
            catch (Throwable t) {}
            jbSearchPanelFrame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Frame");
        }
    }
    
    public void saveState() {
        this.getJBSearchPanel1().saveHistory();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        ((JBManagerPanel)AppRegistry.getInstance().lookup("Manager")).getNameEditor().setVisible(false);
        JBAppletProperties.editorFrame.setVisible(false);
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
