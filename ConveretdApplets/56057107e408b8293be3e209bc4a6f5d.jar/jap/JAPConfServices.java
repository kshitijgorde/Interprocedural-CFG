// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.MixCascade;
import anon.util.JAPMessages;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JTabbedPane;

public class JAPConfServices extends AbstractJAPConfModule
{
    private JAPConfAnon m_anonModule;
    private JAPConfTor m_torModule;
    private JAPConfMixminion m_mixminionModule;
    private JAPConfAnonGeneral m_anonGeneralModule;
    private JAPConfTC m_tcModule;
    private JTabbedPane m_tabsAnon;
    private Vector m_tabbedModules;
    
    public JAPConfServices() {
        super(null);
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                this.m_anonModule.initObservers();
                this.m_torModule.initObservers();
                this.m_mixminionModule.initObservers();
                this.m_anonGeneralModule.initObservers();
                this.m_tcModule.initObservers();
            }
            return true;
        }
        return false;
    }
    
    public synchronized void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        final JAPConfAnon anonModule = this.getAnonModule();
        this.getTorModule();
        this.getMixminionModule();
        final JAPConfAnonGeneral anonGeneralModule = this.getAnonGeneralModule();
        final JAPConfTC tcModule = this.getTCModule();
        synchronized (this) {
            rootPanel.removeAll();
            this.m_tabsAnon = new JTabbedPane();
            this.m_tabbedModules = new Vector();
            rootPanel.setLayout(new GridBagLayout());
            final GridBagConstraints tabbedRootPanelContraints = AbstractJAPConfModule.createTabbedRootPanelContraints();
            if (JAPModel.getDefaultView() != 2) {
                this.m_tabsAnon.addTab(anonModule.getTabTitle(), anonModule.getRootPanel());
                this.m_tabbedModules.addElement(anonModule);
                this.m_tabsAnon.addTab(tcModule.getTabTitle(), tcModule.getRootPanel());
                this.m_tabbedModules.addElement(tcModule);
                this.m_tabsAnon.addTab(anonGeneralModule.getTabTitle(), anonGeneralModule.getRootPanel());
                this.m_tabbedModules.addElement(anonGeneralModule);
                rootPanel.add(this.m_tabsAnon, tabbedRootPanelContraints);
            }
            else {
                tabbedRootPanelContraints.weightx = 0.0;
                tabbedRootPanelContraints.weighty = 0.0;
                rootPanel.add(anonModule.getRootPanel(), tabbedRootPanelContraints);
                tabbedRootPanelContraints.weightx = 1.0;
                tabbedRootPanelContraints.weighty = 1.0;
                rootPanel.add(new JLabel(), tabbedRootPanelContraints);
            }
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("ngAnonymitaet");
    }
    
    protected boolean onOkPressed() {
        final boolean okPressed = this.m_anonModule.okPressed();
        final boolean okPressed2 = this.m_torModule.okPressed();
        final boolean okPressed3 = this.m_mixminionModule.okPressed();
        final boolean okPressed4 = this.m_anonGeneralModule.okPressed();
        final boolean okPressed5 = this.m_tcModule.okPressed();
        return okPressed && okPressed2 && okPressed3 && okPressed4 && okPressed5;
    }
    
    protected void onCancelPressed() {
        this.m_anonModule.cancelPressed();
        this.m_torModule.cancelPressed();
        this.m_mixminionModule.cancelPressed();
        this.m_anonGeneralModule.cancelPressed();
        this.m_tcModule.cancelPressed();
    }
    
    public String getHelpContext() {
        if (JAPModel.getDefaultView() != 2) {
            return this.m_tabbedModules.elementAt(this.m_tabsAnon.getSelectedIndex()).getHelpContext();
        }
        return this.m_anonModule.getHelpContext();
    }
    
    protected void onRootPanelShown() {
        if (JAPModel.getDefaultView() != 2) {
            this.m_tabbedModules.elementAt(this.m_tabsAnon.getSelectedIndex()).onRootPanelShown();
        }
        else {
            this.m_anonModule.onRootPanelShown();
        }
    }
    
    protected void onResetToDefaultsPressed() {
        this.m_anonModule.resetToDefaultsPressed();
        this.m_torModule.resetToDefaultsPressed();
        this.m_mixminionModule.resetToDefaultsPressed();
        this.m_anonGeneralModule.resetToDefaultsPressed();
        this.m_tcModule.resetToDefaultsPressed();
    }
    
    protected void onUpdateValues() {
        this.m_anonModule.updateValues(false);
        this.m_torModule.updateValues(false);
        this.m_mixminionModule.updateValues(false);
        this.m_anonGeneralModule.updateValues(false);
        this.m_tcModule.updateValues(false);
    }
    
    private JAPConfAnon getAnonModule() {
        synchronized (this) {
            if (this.m_anonModule == null) {
                this.m_anonModule = new JAPConfAnon(null);
            }
        }
        return this.m_anonModule;
    }
    
    private JAPConfTor getTorModule() {
        synchronized (this) {
            if (this.m_torModule == null) {
                this.m_torModule = new JAPConfTor();
            }
        }
        return this.m_torModule;
    }
    
    private JAPConfMixminion getMixminionModule() {
        synchronized (this) {
            if (this.m_mixminionModule == null) {
                this.m_mixminionModule = new JAPConfMixminion();
            }
        }
        return this.m_mixminionModule;
    }
    
    private JAPConfAnonGeneral getAnonGeneralModule() {
        synchronized (this) {
            if (this.m_anonGeneralModule == null) {
                this.m_anonGeneralModule = new JAPConfAnonGeneral(null);
            }
        }
        return this.m_anonGeneralModule;
    }
    
    private JAPConfTC getTCModule() {
        synchronized (this) {
            if (this.m_tcModule == null) {
                this.m_tcModule = new JAPConfTC(null);
            }
        }
        return this.m_tcModule;
    }
    
    public synchronized void selectAnonTab(final MixCascade selectedCascade, final boolean b) {
        if (JAPModel.getDefaultView() != 2) {
            this.m_tabsAnon.setSelectedIndex(0);
        }
        this.m_anonModule.setSelectedCascade(selectedCascade);
        if (b) {
            this.m_anonModule.showFilter();
        }
    }
}
