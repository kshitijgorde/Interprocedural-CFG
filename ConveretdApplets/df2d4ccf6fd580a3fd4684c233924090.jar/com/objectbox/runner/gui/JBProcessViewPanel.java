// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.Observable;
import com.objectbox.runner.util.JBLogger;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import com.objectbox.gui.lwcomp.FlatButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import com.sun.java.swing.BoxLayout;
import com.objectbox.runner.model.JBProcessModel;
import java.beans.PropertyChangeSupport;
import java.awt.ScrollPane;
import com.objectbox.gui.lwcomp.DoubleBufferPanel;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class JBProcessViewPanel extends Panel implements ActionListener, Observer
{
    private DoubleBufferPanel ivjPanelButtonHolder;
    private ScrollPane ivjScrollPaneProcessView;
    public final int MIN_PROCESS_LIST_SIZE = 6;
    protected transient PropertyChangeSupport propertyChange;
    private JBProcessModel fieldModel;
    protected transient ProcessSelectedListener aProcessSelectedListener;
    protected boolean rightclick;
    private BoxLayout ivjPanelButtonHolderBoxLayout;
    
    public JBProcessViewPanel() {
        this.ivjPanelButtonHolder = null;
        this.ivjScrollPaneProcessView = null;
        this.fieldModel = new JBProcessModel();
        this.aProcessSelectedListener = null;
        this.rightclick = false;
        this.ivjPanelButtonHolderBoxLayout = null;
        this.initialize();
    }
    
    public JBProcessViewPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        this.ivjPanelButtonHolder = null;
        this.ivjScrollPaneProcessView = null;
        this.fieldModel = new JBProcessModel();
        this.aProcessSelectedListener = null;
        this.rightclick = false;
        this.ivjPanelButtonHolderBoxLayout = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final BeanRunner beanRunner = this.getModel().getBeanRunner((Component)actionEvent.getSource());
        this.rightclick = ((FlatButton)actionEvent.getSource()).rightButtonPush;
        this.getModel().setActiveBeanRunner(beanRunner);
        this.fireOnProcessSelected(new ProcessSelectedEvent(this));
    }
    
    public void addProcessSelectedListener(final ProcessSelectedListener processSelectedListener) {
        this.aProcessSelectedListener = ProcessSelectedEventMulticaster.add(this.aProcessSelectedListener, processSelectedListener);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    protected void fireOnProcessSelected(final ProcessSelectedEvent processSelectedEvent) {
        if (this.aProcessSelectedListener == null) {
            return;
        }
        this.aProcessSelectedListener.onProcessSelected(processSelectedEvent);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public Dimension getMaximumSize() {
        return new Dimension(100, 100);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(100, 100);
    }
    
    public JBProcessModel getModel() {
        return this.fieldModel;
    }
    
    private DoubleBufferPanel getPanelButtonHolder() {
        if (this.ivjPanelButtonHolder == null) {
            try {
                (this.ivjPanelButtonHolder = new DoubleBufferPanel()).setName("PanelButtonHolder");
                this.ivjPanelButtonHolder.setLayout(this.getPanelButtonHolderBoxLayout());
                this.ivjPanelButtonHolder.setBackground(SystemColor.control);
                this.ivjPanelButtonHolder.setBounds(0, 0, 71, 115);
                this.ivjPanelButtonHolder.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelButtonHolder;
    }
    
    private BoxLayout getPanelButtonHolderBoxLayout() {
        BoxLayout boxLayout = null;
        try {
            boxLayout = new BoxLayout(this.getPanelButtonHolder(), 1);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return boxLayout;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    private ScrollPane getScrollPaneProcessView() {
        if (this.ivjScrollPaneProcessView == null) {
            try {
                (this.ivjScrollPaneProcessView = new ScrollPane()).setName("ScrollPaneProcessView");
                this.getScrollPaneProcessView().add(this.getPanelButtonHolder(), this.getPanelButtonHolder().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjScrollPaneProcessView;
    }
    
    public void handleAddProcess(final BeanRunner beanRunner) {
        if (beanRunner == null) {
            return;
        }
        final FlatButton flatButton = (FlatButton)this.getModel().getComponent(beanRunner);
        flatButton.setFixedsize(new Dimension(this.getScrollPaneProcessView().getSize().width - 5, 20));
        flatButton.addActionListener(this);
        this.getPanelButtonHolder().add(flatButton);
        this.doLayout();
        this.repaint();
        this.validate();
    }
    
    private void handleException(final Throwable t) {
    }
    
    public void handleRemoveProcess(final BeanRunner beanRunner) {
        if (beanRunner == null) {
            return;
        }
        this.getPanelButtonHolder().remove(this.getModel().getLastRemovedComponent());
        this.getPanelButtonHolder().repaint();
        this.validate();
    }
    
    private void initialize() {
        this.setName("ProcessViewPanel");
        this.setLayout(new BorderLayout());
        this.setSize(143, 125);
        this.add(this.getScrollPaneProcessView(), "Center");
        this.getModel().addObserver(this);
        this.setBackground(JBee.appcolor);
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
            final JBProcessViewPanel jbProcessViewPanel = new JBProcessViewPanel();
            frame.add("Center", jbProcessViewPanel);
            frame.setSize(jbProcessViewPanel.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    public void removeProcessSelectedListener(final ProcessSelectedListener processSelectedListener) {
        this.aProcessSelectedListener = ProcessSelectedEventMulticaster.remove(this.aProcessSelectedListener, processSelectedListener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void update(final Observable observable, final Object o) {
        try {
            if (observable instanceof JBProcessModel) {
                final JBProcessModel jbProcessModel = (JBProcessModel)observable;
                if (o instanceof String) {
                    final String s = (String)o;
                    if (s.compareTo("add") == 0) {
                        this.handleAddProcess(jbProcessModel.getActiveRunner());
                    }
                    if (s.compareTo("remove") == 0) {
                        this.handleRemoveProcess(jbProcessModel.getLastRemovedRunner());
                    }
                    if (s.compareTo("active") == 0) {
                        final Container frame = jbProcessModel.getActiveRunner().getFrame();
                        if (frame != null && frame.isVisible() && frame instanceof Frame) {
                            ((Frame)frame).toFront();
                        }
                    }
                }
                this.repaint();
                this.validate();
            }
        }
        catch (Exception ex) {
            JBLogger.log("JBProcessViewPanel.update " + ex.toString());
        }
    }
}
