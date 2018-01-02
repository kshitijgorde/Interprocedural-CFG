// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.SwingUtilities;
import java.util.Observable;
import javax.swing.event.AncestorEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.util.Observer;
import javax.swing.event.AncestorListener;
import logging.LogHolder;
import logging.LogType;
import javax.swing.JPanel;
import gui.AWTUpdateQueue;
import gui.JAPHelpContext;

public abstract class AbstractJAPConfModule implements JAPHelpContext.IHelpContext
{
    private final AWTUpdateQueue AWT_UPDATE_QUEUE;
    private JPanel m_rootPanel;
    protected IJAPConfSavePoint m_savePoint;
    private boolean m_bObserversInitialised;
    protected final Object LOCK_OBSERVABLE;
    
    protected AbstractJAPConfModule(final IJAPConfSavePoint savePoint) {
        this.AWT_UPDATE_QUEUE = new AWTUpdateQueue(new Runnable() {
            public void run() {
                try {
                    AbstractJAPConfModule.this.onUpdateValues();
                    AbstractJAPConfModule.this.m_rootPanel.validate();
                }
                catch (Throwable t) {
                    LogHolder.log(1, LogType.GUI, t);
                }
            }
        });
        this.m_bObserversInitialised = false;
        this.LOCK_OBSERVABLE = new Object();
        (this.m_rootPanel = new JPanel()).addAncestorListener(new RootPanelAncestorListener());
        this.m_savePoint = savePoint;
        this.recreateRootPanel();
        final FontSizeObserver fontSizeObserver = new FontSizeObserver();
        JAPModel.getInstance().addObserver(fontSizeObserver);
        this.fontSizeChanged(new JAPModel.FontResize(JAPModel.getInstance().getFontSize(), JAPModel.getInstance().getFontSize()), fontSizeObserver.getDummyLabel());
    }
    
    public static GridBagConstraints createTabbedRootPanelContraints() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        return gridBagConstraints;
    }
    
    public abstract String getTabTitle();
    
    public abstract void recreateRootPanel();
    
    public final JPanel getRootPanel() {
        return this.m_rootPanel;
    }
    
    public final void createSavePoint() {
        if (this.m_savePoint != null) {
            this.m_savePoint.createSavePoint();
        }
    }
    
    public final boolean okPressed() {
        return this.onOkPressed();
    }
    
    public void fontSizeChanged(final JAPModel.FontResize fontResize, final JLabel label) {
    }
    
    public final void cancelPressed() {
        if (this.m_savePoint != null) {
            this.m_savePoint.restoreSavePoint();
        }
        this.onCancelPressed();
    }
    
    public final void resetToDefaultsPressed() {
        if (this.m_savePoint != null) {
            this.m_savePoint.restoreDefaults();
        }
        this.onResetToDefaultsPressed();
    }
    
    public final void updateValues(final boolean b) {
        if (this instanceof JAPConfAnon) {}
        this.AWT_UPDATE_QUEUE.update(b);
    }
    
    protected void onRootPanelShown() {
    }
    
    protected boolean onOkPressed() {
        return true;
    }
    
    protected void onCancelPressed() {
    }
    
    protected void onResetToDefaultsPressed() {
    }
    
    protected void onUpdateValues() {
    }
    
    protected boolean initObservers() {
        final boolean bObserversInitialised = this.m_bObserversInitialised;
        this.m_bObserversInitialised = true;
        return !bObserversInitialised;
    }
    
    public Component getHelpExtractionDisplayContext() {
        return JAPConf.getInstance().getContentPane();
    }
    
    public abstract String getHelpContext();
    
    private class RootPanelAncestorListener implements AncestorListener
    {
        public void ancestorAdded(final AncestorEvent ancestorEvent) {
            if (ancestorEvent.getAncestor() == AbstractJAPConfModule.this.getRootPanel() && AbstractJAPConfModule.this.getRootPanel().isVisible()) {
                AbstractJAPConfModule.this.onRootPanelShown();
            }
        }
        
        public void ancestorMoved(final AncestorEvent ancestorEvent) {
        }
        
        public void ancestorRemoved(final AncestorEvent ancestorEvent) {
        }
    }
    
    protected class FontSizeObserver implements Observer
    {
        private JLabel DUMMY_LABEL;
        
        protected FontSizeObserver() {
            this.DUMMY_LABEL = new JLabel();
        }
        
        public JLabel getDummyLabel() {
            return this.DUMMY_LABEL;
        }
        
        public void update(final Observable observable, final Object o) {
            if (o instanceof JAPModel.FontResize && o != null) {
                SwingUtilities.updateComponentTreeUI(this.DUMMY_LABEL);
                AbstractJAPConfModule.this.fontSizeChanged((JAPModel.FontResize)o, this.DUMMY_LABEL);
            }
        }
    }
}
