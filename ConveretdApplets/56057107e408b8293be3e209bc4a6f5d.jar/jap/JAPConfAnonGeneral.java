// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.JPanel;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Dictionary;
import anon.client.AnonClient;
import anon.infoservice.BlacklistedCascadeIDEntry;
import javax.swing.JLabel;
import java.util.Observable;
import anon.util.JAPMessages;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import java.util.Observer;

public final class JAPConfAnonGeneral extends AbstractJAPConfModule implements Observer
{
    public static final String MSG_CONNECTION_TIMEOUT;
    public static final String MSG_DENY_NON_ANONYMOUS_SURFING;
    public static final String MSG_ANONYMIZED_HTTP_HEADERS;
    private static final String MSG_AUTO_CHOOSE_CASCADES;
    private static final String MSG_RESTRICT_AUTO_CHOOSE;
    private static final String MSG_DO_NOT_RESTRICT_AUTO_CHOOSE;
    private static final String MSG_RESTRICT_AUTO_CHOOSE_PAY;
    private static final String MSG_KNOWN_CASCADES;
    private static final String MSG_ALLOWED_CASCADES;
    private static final String MSG_AUTO_CHOOSE_ON_START;
    private static final String MSG_TITLE_ASSIGN_SERVICES;
    private static final String MSG_EXPLAIN_ASSIGN_SERVICES;
    private static final String MSG_EXPLAIN_ASSIGN_SERVICES_BETA;
    private static final String MSG_SERVICE_HTTP;
    private static final String MSG_SERVICE_FTP;
    private static final String MSG_SERVICE_EMAIL;
    private static final String MSG_SERVICE_SOCKS;
    private static final String MSG_PASSIVE_FTP;
    private static final String MSG_TOOLTIP_SERVICE_DEACTIVATED;
    private static final String MSG_EVERY_SECONDS;
    private static final String MSG_LBL_WHITELIST;
    private static final String MSG_AUTO_CHOOSE_ON_STARTUP;
    private static final String IMG_ARROW_RIGHT;
    private static final String IMG_ARROW_LEFT;
    private static final int DT_INTERVAL_STEPLENGTH = 2;
    private static final int DT_INTERVAL_STEPS = 15;
    private static final int DT_INTERVAL_DEFAULT = 10;
    private static final int DT_INTERVAL_MIN_STEP = 6;
    public static final int DEFAULT_DUMMY_TRAFFIC_INTERVAL_SECONDS = 20000;
    private static final Integer[] LOGIN_TIMEOUTS;
    private JCheckBox m_cbDenyNonAnonymousSurfing;
    private JCheckBox m_cbAnonymizedHttpHeaders;
    private JCheckBox m_cbAutoConnect;
    private JCheckBox m_cbAutoReConnect;
    private JCheckBox m_cbAutoBlacklist;
    private JCheckBox m_cbAutoChooseCascades;
    private JCheckBox m_cbAutoChooseCascadesOnStartup;
    private JSlider m_sliderDummyTrafficIntervall;
    private JAPController m_Controller;
    private JComboBox m_comboTimeout;
    static /* synthetic */ Class class$jap$JAPConfAnonGeneral;
    
    protected JAPConfAnonGeneral(final IJAPConfSavePoint ijapConfSavePoint) {
        super(null);
        this.m_Controller = JAPController.getInstance();
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                JAPModel.getInstance().addObserver(this);
                return true;
            }
        }
        return false;
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("settingsInfoServiceConfigAdvancedSettingsTabTitle");
    }
    
    public void update(final Observable observable, final Object o) {
        if (o != null) {
            if (o.equals(JAPModel.CHANGED_AUTO_RECONNECT)) {
                this.m_cbAutoReConnect.setSelected(JAPModel.isAutomaticallyReconnected());
            }
            else if (o.equals(JAPModel.CHANGED_CASCADE_AUTO_CHANGE)) {
                this.m_cbAutoChooseCascades.setSelected(JAPModel.getInstance().isCascadeAutoSwitched());
            }
            else if (o.equals(JAPModel.CHANGED_AUTO_CONNECT)) {
                this.m_cbAutoConnect.setSelected(JAPModel.isAutoConnect());
            }
            else if (o.equals(JAPModel.CHANGED_ASK_FOR_NON_ANONYMOUS)) {
                this.m_cbDenyNonAnonymousSurfing.setSelected(JAPModel.getInstance().isAskForAnyNonAnonymousRequest());
            }
            else if (o.equals(JAPModel.CHANGED_ANONYMIZED_HTTP_HEADERS)) {
                this.m_cbAnonymizedHttpHeaders.setSelected(JAPModel.getInstance().isAnonymizedHttpHeaders());
            }
        }
    }
    
    protected void onUpdateValues() {
        final int dummyTraffic = JAPModel.getDummyTraffic();
        if (dummyTraffic > -1) {
            int value = dummyTraffic / 1000;
            if (value < 2) {
                value = 2;
            }
            else if (value > 30) {
                value = 30;
            }
            this.m_sliderDummyTrafficIntervall.setValue(value);
        }
        this.m_sliderDummyTrafficIntervall.setEnabled(dummyTraffic > -1);
        final Dictionary labelTable = this.m_sliderDummyTrafficIntervall.getLabelTable();
        for (int i = 1; i <= 15; ++i) {
            labelTable.get(new Integer(i * 2)).setEnabled(this.m_sliderDummyTrafficIntervall.isEnabled());
        }
        this.m_cbDenyNonAnonymousSurfing.setSelected(JAPModel.getInstance().isAskForAnyNonAnonymousRequest());
        this.m_cbAnonymizedHttpHeaders.setSelected(JAPModel.getInstance().isAnonymizedHttpHeaders());
        this.m_cbAutoConnect.setSelected(JAPModel.isAutoConnect());
        this.m_cbAutoReConnect.setSelected(JAPModel.isAutomaticallyReconnected());
        this.m_cbAutoBlacklist.setSelected(BlacklistedCascadeIDEntry.areNewCascadesInBlacklist());
        this.m_cbAutoChooseCascades.setSelected(JAPModel.getInstance().isCascadeAutoSwitched());
        this.m_cbAutoChooseCascadesOnStartup.setSelected(JAPModel.getInstance().isCascadeAutoChosenOnStartup());
        this.m_cbAutoChooseCascadesOnStartup.setEnabled(this.m_cbAutoChooseCascades.isSelected());
        this.setLoginTimeout(AnonClient.getLoginTimeout());
    }
    
    protected boolean onOkPressed() {
        new Thread(new Runnable() {
            private final /* synthetic */ int val$dtAsync = JAPConfAnonGeneral.this.m_sliderDummyTrafficIntervall.getValue() * 1000;
            
            public void run() {
                JAPConfAnonGeneral.this.m_Controller.setDummyTraffic(this.val$dtAsync);
            }
        }).start();
        JAPModel.getInstance().setAskForAnyNonAnonymousRequest(this.m_cbDenyNonAnonymousSurfing.isSelected());
        BlacklistedCascadeIDEntry.putNewCascadesInBlacklist(this.m_cbAutoBlacklist.isSelected());
        JAPModel.getInstance().setAutoConnect(this.m_cbAutoConnect.isSelected());
        JAPModel.getInstance().setAutoReConnect(this.m_cbAutoReConnect.isSelected());
        JAPModel.getInstance().setCascadeAutoSwitch(this.m_cbAutoChooseCascades.isSelected());
        JAPModel.getInstance().setAutoChooseCascadeOnStartup(this.m_cbAutoChooseCascadesOnStartup.isSelected());
        JAPModel.getInstance().setAnonymizedHttpHeaders(this.m_cbAnonymizedHttpHeaders.isSelected());
        AnonClient.setLoginTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        return true;
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        this.m_cbDenyNonAnonymousSurfing = new JCheckBox(JAPMessages.getString(JAPConfAnonGeneral.MSG_DENY_NON_ANONYMOUS_SURFING));
        this.m_cbAnonymizedHttpHeaders = new JCheckBox(JAPMessages.getString(JAPConfAnonGeneral.MSG_ANONYMIZED_HTTP_HEADERS));
        this.m_cbAutoConnect = new JCheckBox(JAPMessages.getString("settingsautoConnectCheckBox"));
        this.m_cbAutoReConnect = new JCheckBox(JAPMessages.getString("settingsautoReConnectCheckBox"));
        this.m_cbAutoChooseCascades = new JCheckBox(JAPMessages.getString(JAPConfAnonGeneral.MSG_AUTO_CHOOSE_CASCADES));
        this.m_cbAutoChooseCascadesOnStartup = new JCheckBox(JAPMessages.getString(JAPConfAnonGeneral.MSG_AUTO_CHOOSE_ON_STARTUP));
        this.m_cbAutoBlacklist = new JCheckBox(JAPMessages.getString(JAPConfAnonGeneral.MSG_LBL_WHITELIST));
        this.m_cbAutoChooseCascades.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                JAPConfAnonGeneral.this.m_cbAutoChooseCascadesOnStartup.setEnabled(itemEvent.getStateChange() == 1);
            }
        });
        rootPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weighty = 0.0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        rootPanel.add(this.m_cbAnonymizedHttpHeaders, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        rootPanel.add(this.m_cbDenyNonAnonymousSurfing, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        rootPanel.add(this.m_cbAutoConnect, gridBagConstraints);
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        rootPanel.add(this.m_cbAutoReConnect, gridBagConstraints);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridy;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        rootPanel.add(this.m_cbAutoChooseCascades, gridBagConstraints);
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        ++gridBagConstraints7.gridx;
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridx;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridx;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        rootPanel.add(this.m_cbAutoChooseCascadesOnStartup, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        ++gridBagConstraints10.gridy;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        rootPanel.add(this.m_cbAutoBlacklist, gridBagConstraints);
        final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
        ++gridBagConstraints11.gridy;
        gridBagConstraints.gridwidth = 2;
        rootPanel.add(new JLabel(JAPMessages.getString("ngConfAnonGeneralSendDummy")), gridBagConstraints);
        this.m_sliderDummyTrafficIntervall = new JSlider(0, 6, 30, 20);
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>(15);
        for (int i = 1; i <= 15; ++i) {
            labelTable.put(new Integer(i * 2), new JLabel(i * 2 + "s"));
        }
        this.m_sliderDummyTrafficIntervall.setLabelTable(labelTable);
        this.m_sliderDummyTrafficIntervall.setMajorTickSpacing(2);
        this.m_sliderDummyTrafficIntervall.setMinorTickSpacing(1);
        this.m_sliderDummyTrafficIntervall.setPaintLabels(true);
        this.m_sliderDummyTrafficIntervall.setPaintTicks(true);
        this.m_sliderDummyTrafficIntervall.setSnapToTicks(true);
        final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
        ++gridBagConstraints12.gridx;
        final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
        ++gridBagConstraints13.gridx;
        gridBagConstraints.gridwidth = 2;
        rootPanel.add(this.m_sliderDummyTrafficIntervall, gridBagConstraints);
        final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
        ++gridBagConstraints14.gridy;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        rootPanel.add(new JLabel(JAPMessages.getString(JAPConfAnonGeneral.MSG_CONNECTION_TIMEOUT) + " (s):"), gridBagConstraints);
        this.m_comboTimeout = new JComboBox((E[])JAPConfAnonGeneral.LOGIN_TIMEOUTS);
        gridBagConstraints.fill = 0;
        final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
        ++gridBagConstraints15.gridx;
        rootPanel.add(this.m_comboTimeout, gridBagConstraints);
        final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
        ++gridBagConstraints16.gridy;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        rootPanel.add(new JLabel(), gridBagConstraints);
        this.updateValues(false);
    }
    
    public void onResetToDefaultsPressed() {
        this.m_cbDenyNonAnonymousSurfing.setSelected(true);
        this.m_cbAnonymizedHttpHeaders.setSelected(true);
        this.m_cbAutoBlacklist.setSelected(false);
        this.m_sliderDummyTrafficIntervall.setEnabled(true);
        this.m_sliderDummyTrafficIntervall.setValue(10);
        this.m_cbAutoConnect.setSelected(true);
        this.m_cbAutoReConnect.setSelected(true);
        this.m_cbAutoChooseCascades.setSelected(true);
        this.m_cbAutoChooseCascadesOnStartup.setSelected(true);
        this.setLoginTimeout(30000);
    }
    
    public String getHelpContext() {
        return "services_general";
    }
    
    protected void onRootPanelShown() {
    }
    
    private void setLoginTimeout(final int n) {
        final int n2 = n / 1000;
        if (n2 >= this.m_comboTimeout.getItemAt(this.m_comboTimeout.getItemCount() - 1)) {
            this.m_comboTimeout.setSelectedIndex(this.m_comboTimeout.getItemCount() - 1);
            AnonClient.setLoginTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        }
        else if (n2 <= this.m_comboTimeout.getItemAt(0)) {
            this.m_comboTimeout.setSelectedIndex(0);
            AnonClient.setLoginTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        }
        else {
            for (int i = 1; i < this.m_comboTimeout.getItemCount(); ++i) {
                if (n2 <= (int)this.m_comboTimeout.getItemAt(i)) {
                    this.m_comboTimeout.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_CONNECTION_TIMEOUT = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_loginTimeout";
        MSG_DENY_NON_ANONYMOUS_SURFING = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_denyNonAnonymousSurfing";
        MSG_ANONYMIZED_HTTP_HEADERS = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_anonymizedHttpHeaders";
        MSG_AUTO_CHOOSE_CASCADES = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_autoChooseCascades";
        MSG_RESTRICT_AUTO_CHOOSE = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_RestrictAutoChoosing";
        MSG_DO_NOT_RESTRICT_AUTO_CHOOSE = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_doNotRestrictAutoChoosing";
        MSG_RESTRICT_AUTO_CHOOSE_PAY = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_restrictAutoChoosingPay";
        MSG_KNOWN_CASCADES = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_knownCascades";
        MSG_ALLOWED_CASCADES = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_allowedCascades";
        MSG_AUTO_CHOOSE_ON_START = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_autoChooseOnStart";
        MSG_TITLE_ASSIGN_SERVICES = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_titleAssignServices";
        MSG_EXPLAIN_ASSIGN_SERVICES = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_explainAssignServices";
        MSG_EXPLAIN_ASSIGN_SERVICES_BETA = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_explainAssignServicesBeta";
        MSG_SERVICE_HTTP = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_serviceHttp";
        MSG_SERVICE_FTP = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_serviceFtp";
        MSG_SERVICE_EMAIL = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_serviceEMail";
        MSG_SERVICE_SOCKS = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_serviceSocks";
        MSG_PASSIVE_FTP = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_passiveFTP";
        MSG_TOOLTIP_SERVICE_DEACTIVATED = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_tooltipServiceDeactivated";
        MSG_EVERY_SECONDS = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_everySeconds";
        MSG_LBL_WHITELIST = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_autoBlacklist";
        MSG_AUTO_CHOOSE_ON_STARTUP = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_autoChooseOnStartup";
        IMG_ARROW_RIGHT = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_arrowRight.gif";
        IMG_ARROW_LEFT = ((JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral == null) ? (JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral = class$("jap.JAPConfAnonGeneral")) : JAPConfAnonGeneral.class$jap$JAPConfAnonGeneral).getName() + "_arrowLeft.gif";
        LOGIN_TIMEOUTS = new Integer[] { new Integer(5), new Integer(10), new Integer(15), new Integer(20), new Integer(25), new Integer(30), new Integer(40), new Integer(50), new Integer(60) };
    }
}
