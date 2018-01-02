// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.JSeparator;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import anon.infoservice.ProxyInterface;
import anon.infoservice.ListenerInterface;
import javax.swing.JButton;
import gui.JAPHtmlMultiLineLabel;
import java.awt.Insets;
import javax.swing.border.Border;
import gui.dialog.JAPDialog;
import java.awt.event.ActionListener;
import anon.util.JAPMessages;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import logging.LogHolder;
import logging.LogType;
import jap.forward.JAPRoutingMessage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import gui.JAPJIntField;

public class JAPConfNetwork extends AbstractJAPConfModule
{
    private static final String MSG_LISTENER_CHANGED;
    private static final String MSG_ACCESS_TO_JAP;
    public static final String MSG_SLOW_ANTI_CENSORSHIP;
    private static final String MSG_SLOW_ANTI_CENSORSHIP_Q;
    private JAPJIntField m_tfListenerPortNumber;
    private JCheckBox m_cbListenerIsLocal;
    private JLabel m_labelPortnumber1;
    private TitledBorder m_borderSettingsListener;
    private JCheckBox m_cbProxy;
    private JCheckBox m_settingsForwardingClientConfigNeedForwarderBox;
    private JAPJIntField m_tfProxyPortNumber;
    private JTextField m_tfProxyHost;
    private JComboBox m_comboProxyType;
    private JCheckBox m_cbProxyAuthentication;
    private JTextField m_tfProxyAuthenticationUserID;
    private JLabel m_labelProxyHost;
    private JLabel m_labelProxyPort;
    private JLabel m_labelProxyType;
    private JLabel m_labelProxyAuthUserID;
    static /* synthetic */ Class class$jap$JAPConfNetwork;
    
    public JAPConfNetwork() {
        super(new JAPConfNetworkSavePoint());
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                final Observer observer = new Observer() {
                    public void update(final Observable observable, final Object o) {
                        try {
                            if (observable == JAPModel.getInstance().getRoutingSettings() && ((JAPRoutingMessage)o).getMessageCode() == 16) {
                                if (JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder()) {
                                    JAPConfNetwork.this.m_settingsForwardingClientConfigNeedForwarderBox.setSelected(true);
                                }
                                else {
                                    JAPConfNetwork.this.m_settingsForwardingClientConfigNeedForwarderBox.setSelected(false);
                                }
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.GUI, ex);
                        }
                    }
                };
                JAPModel.getInstance().getRoutingSettings().addObserver(observer);
                observer.update(JAPModel.getInstance().getRoutingSettings(), new JAPRoutingMessage(16));
                return true;
            }
        }
        return false;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_cbProxyAuthentication) {
            if (this.m_cbProxyAuthentication.isSelected()) {
                JAPModel.getInstance().setUseProxyAuthentication(true);
            }
            else {
                JAPModel.getInstance().setUseProxyAuthentication(false);
            }
        }
    }
    
    public void onResetToDefaultsPressed() {
        this.m_tfListenerPortNumber.setInt(4001);
        this.m_cbListenerIsLocal.setSelected(true);
        this.m_cbProxy.setSelected(false);
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        synchronized (this) {
            rootPanel.removeAll();
            final JPanel forwardingClientConfigPanel = this.createForwardingClientConfigPanel();
            final GridBagLayout layout = new GridBagLayout();
            rootPanel.setLayout(layout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(forwardingClientConfigPanel, gridBagConstraints);
            rootPanel.add(forwardingClientConfigPanel);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weighty = 0.0;
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            rootPanel.add(this.buildPortPanel(), gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.fill = 1;
            rootPanel.add(this.buildProxyPanel(), gridBagConstraints);
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("ngTreeNetwork");
    }
    
    private JPanel createForwardingClientConfigPanel() {
        final JPanel panel = new JPanel();
        (this.m_settingsForwardingClientConfigNeedForwarderBox = new JCheckBox(JAPMessages.getString("settingsForwardingClientConfigNeedForwarderBox"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JAPConfNetwork.this.m_settingsForwardingClientConfigNeedForwarderBox.isSelected()) {
                    if (JAPDialog.showYesNoDialog(JAPConfNetwork.this.getRootPanel(), JAPMessages.getString(JAPConfNetwork.MSG_SLOW_ANTI_CENSORSHIP) + "<br><br>" + JAPMessages.getString(JAPConfNetwork.MSG_SLOW_ANTI_CENSORSHIP_Q))) {
                        if (JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2) {
                            JAPConfNetwork.this.showForwardingClientConfirmServerShutdownDialog(panel);
                            if (!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder()) {
                                JAPConfNetwork.this.m_settingsForwardingClientConfigNeedForwarderBox.setSelected(false);
                            }
                        }
                        else {
                            JAPModel.getInstance().getRoutingSettings().setConnectViaForwarder(true);
                        }
                    }
                    else {
                        JAPConfNetwork.this.m_settingsForwardingClientConfigNeedForwarderBox.setSelected(false);
                    }
                }
                else {
                    JAPModel.getInstance().getRoutingSettings().setConnectViaForwarder(false);
                }
            }
        });
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsForwardingClientConfigBorder")));
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0E-4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.m_settingsForwardingClientConfigNeedForwarderBox, gridBagConstraints);
        panel.add(this.m_settingsForwardingClientConfigNeedForwarderBox);
        gridBagConstraints.insets = new Insets(0, 20, 5, 5);
        return panel;
    }
    
    private void showForwardingClientConfirmServerShutdownDialog(final Component component) {
        final JAPDialog japDialog = new JAPDialog(component, JAPMessages.getString("settingsForwardingClientConfigConfirmServerShutdownDialogTitle"));
        japDialog.setDefaultCloseOperation(0);
        final JPanel panel = new JPanel();
        japDialog.getContentPane().add(panel);
        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel(JAPMessages.getString("settingsForwardingClientConfigConfirmServerShutdownLabel"));
        final JButton button = new JButton(JAPMessages.getString("settingsForwardingClientConfigConfirmServerShutdownShutdownButton"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().setRoutingMode(0);
                JAPModel.getInstance().getRoutingSettings().setConnectViaForwarder(true);
                japDialog.dispose();
            }
        });
        final JButton button2 = new JButton(JAPMessages.getString("cancelButton"));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                japDialog.dispose();
            }
        });
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 11;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 5, 20, 5);
        layout.setConstraints(japHtmlMultiLineLabel, gridBagConstraints);
        panel.add(japHtmlMultiLineLabel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 5, 15, 5);
        layout.setConstraints(button, gridBagConstraints);
        panel.add(button);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 5, 15, 5);
        layout.setConstraints(button2, gridBagConstraints);
        panel.add(button2);
        japDialog.pack();
        japDialog.setVisible(true);
    }
    
    protected boolean onOkPressed() {
        int int1;
        try {
            int1 = Integer.parseInt(this.m_tfListenerPortNumber.getText().trim());
        }
        catch (Exception ex) {
            int1 = -1;
        }
        if (!ListenerInterface.isValidPort(int1)) {
            JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("errorListenerPortWrong"), LogType.MISC);
            return false;
        }
        if (this.m_cbProxy.isSelected()) {
            final String trim = this.m_tfProxyHost.getText().trim();
            if (trim == null || trim.equals("")) {
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("errorFirewallHostNotNull"), LogType.MISC);
                JAPConf.getInstance().selectCard("NETWORK_TAB", null);
                return false;
            }
            int int2;
            try {
                int2 = Integer.parseInt(this.m_tfProxyPortNumber.getText().trim());
            }
            catch (Exception ex2) {
                int2 = -1;
            }
            if (!ListenerInterface.isValidPort(int2)) {
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("errorFirewallServicePortWrong"), LogType.MISC);
                JAPConf.getInstance().selectCard("NETWORK_TAB", null);
                return false;
            }
            if (this.m_cbProxyAuthentication.isSelected()) {
                final String trim2 = this.m_tfProxyAuthenticationUserID.getText().trim();
                if (trim2 == null || trim2.equals("")) {
                    JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("errorFirewallAuthUserIDNotNull"), LogType.MISC);
                    JAPConf.getInstance().selectCard("NETWORK_TAB", null);
                    return false;
                }
            }
        }
        if (JAPModel.getHttpListenerPortNumber() != this.m_tfListenerPortNumber.getInt()) {
            JAPConf.getInstance().addNeedRestart(new JAPConf.AbstractRestartNeedingConfigChange() {
                public String getName() {
                    return JAPMessages.getString("confListenerTab");
                }
                
                public String getMessage() {
                    return JAPMessages.getString(JAPConfNetwork.MSG_LISTENER_CHANGED);
                }
                
                public void doChange() {
                    JAPModel.getInstance().setHttpListenerPortNumber(JAPConfNetwork.this.m_tfListenerPortNumber.getInt());
                }
            });
        }
        if (JAPModel.isHttpListenerLocal() != this.m_cbListenerIsLocal.isSelected()) {
            JAPConf.getInstance().addNeedRestart(new JAPConf.AbstractRestartNeedingConfigChange() {
                public String getName() {
                    return JAPMessages.getString(JAPMessages.getString(JAPConfNetwork.MSG_ACCESS_TO_JAP));
                }
                
                public void doChange() {
                    JAPModel.getInstance().setHttpListenerIsLocal(JAPConfNetwork.this.m_cbListenerIsLocal.isSelected());
                }
            });
        }
        int int3 = -1;
        try {
            int3 = Integer.parseInt(this.m_tfProxyPortNumber.getText().trim());
        }
        catch (Exception ex3) {}
        int n = 1;
        if (this.m_comboProxyType.getSelectedIndex() == 1) {
            n = 3;
        }
        JAPController.getInstance().changeProxyInterface(new ProxyInterface(this.m_tfProxyHost.getText().trim(), int3, n, this.m_tfProxyAuthenticationUserID.getText().trim(), JAPController.getInstance().getPasswordReader(), this.m_cbProxyAuthentication.isSelected(), this.m_cbProxy.isSelected()), this.m_cbProxyAuthentication.isSelected(), this.getRootPanel());
        return true;
    }
    
    protected void onUpdateValues() {
        this.m_tfListenerPortNumber.setInt(JAPModel.getHttpListenerPortNumber());
        this.m_cbListenerIsLocal.setSelected(JAPModel.isHttpListenerLocal());
        final ProxyInterface proxyInterface = JAPModel.getInstance().getProxyInterface();
        final boolean enabled = proxyInterface != null && proxyInterface.isValid();
        this.m_cbProxy.setSelected(enabled);
        this.m_tfProxyHost.setEnabled(enabled);
        this.m_tfProxyPortNumber.setEnabled(enabled);
        this.m_comboProxyType.setEnabled(enabled);
        this.m_tfProxyAuthenticationUserID.setEnabled(enabled);
        this.m_labelProxyHost.setEnabled(enabled);
        this.m_labelProxyPort.setEnabled(enabled);
        this.m_labelProxyType.setEnabled(enabled);
        if (proxyInterface == null || proxyInterface.getProtocol() == 1) {
            this.m_comboProxyType.setSelectedIndex(0);
        }
        else {
            this.m_comboProxyType.setSelectedIndex(1);
        }
        this.m_cbProxyAuthentication.setEnabled(enabled);
        if (proxyInterface != null) {
            this.m_tfProxyHost.setText(proxyInterface.getHost());
            this.m_tfProxyPortNumber.setText(String.valueOf(proxyInterface.getPort()));
            this.m_tfProxyAuthenticationUserID.setText(proxyInterface.getAuthenticationUserID());
            this.m_cbProxyAuthentication.setSelected(proxyInterface.isAuthenticationUsed());
        }
        this.m_labelProxyAuthUserID.setEnabled(this.m_cbProxyAuthentication.isSelected() & enabled);
        this.m_tfProxyAuthenticationUserID.setEnabled(this.m_cbProxyAuthentication.isSelected() & enabled);
        if (this.m_tfProxyPortNumber.getText().trim().equalsIgnoreCase("-1")) {
            this.m_tfProxyPortNumber.setText("");
        }
    }
    
    JPanel buildPortPanel() {
        this.m_labelPortnumber1 = new JLabel(JAPMessages.getString("settingsPort"));
        this.m_tfListenerPortNumber = new JAPJIntField(65535);
        (this.m_cbListenerIsLocal = new JCheckBox(JAPMessages.getString("settingsListenerCheckBox"))).setForeground(this.m_labelPortnumber1.getForeground());
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(this.m_borderSettingsListener = new TitledBorder(JAPMessages.getString("settingsListenerBorder")));
        final JPanel panel2 = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel2.setLayout(layout);
        panel2.setBorder(new EmptyBorder(5, 10, 10, 10));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.m_tfListenerPortNumber, gridBagConstraints);
        panel2.add(this.m_tfListenerPortNumber);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        final Insets insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.insets = insets;
        layout.setConstraints(this.m_labelPortnumber1, gridBagConstraints);
        panel2.add(this.m_labelPortnumber1);
        gridBagConstraints.insets = insets;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        layout.setConstraints(this.m_cbListenerIsLocal, gridBagConstraints);
        panel2.add(this.m_cbListenerIsLocal);
        panel.add(panel2, "North");
        return panel;
    }
    
    JPanel buildProxyPanel() {
        this.m_cbProxy = new JCheckBox(JAPMessages.getString("settingsProxyCheckBox"));
        (this.m_comboProxyType = new JComboBox()).addItem(JAPMessages.getString("settingsProxyTypeHTTP"));
        this.m_comboProxyType.addItem(JAPMessages.getString("settingsProxyTypeSOCKS"));
        this.m_tfProxyHost = new JTextField(20);
        this.m_tfProxyPortNumber = new JAPJIntField(65535);
        final ProxyInterface proxyInterface = JAPModel.getInstance().getProxyInterface();
        final boolean b = proxyInterface != null && proxyInterface.isValid();
        this.m_tfProxyHost.setEnabled(b);
        this.m_tfProxyPortNumber.setEnabled(b);
        this.m_cbProxy.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                final boolean selected = JAPConfNetwork.this.m_cbProxy.isSelected();
                JAPConfNetwork.this.m_comboProxyType.setEnabled(selected);
                JAPConfNetwork.this.m_tfProxyHost.setEnabled(selected);
                JAPConfNetwork.this.m_tfProxyPortNumber.setEnabled(selected);
                JAPConfNetwork.this.m_cbProxyAuthentication.setEnabled(selected);
                JAPConfNetwork.this.m_labelProxyHost.setEnabled(selected);
                JAPConfNetwork.this.m_labelProxyPort.setEnabled(selected);
                JAPConfNetwork.this.m_labelProxyType.setEnabled(selected);
                JAPConfNetwork.this.m_labelProxyAuthUserID.setEnabled(JAPConfNetwork.this.m_cbProxyAuthentication.isSelected() & selected);
                JAPConfNetwork.this.m_tfProxyAuthenticationUserID.setEnabled(JAPConfNetwork.this.m_cbProxyAuthentication.isSelected() & selected);
            }
        });
        this.m_cbProxyAuthentication = new JCheckBox(JAPMessages.getString("settingsProxyAuthenticationCheckBox"));
        this.m_tfProxyAuthenticationUserID = new JTextField(10);
        this.m_cbProxyAuthentication.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfNetwork.this.m_tfProxyAuthenticationUserID.setEnabled(JAPConfNetwork.this.m_cbProxyAuthentication.isSelected());
                JAPConfNetwork.this.m_labelProxyAuthUserID.setEnabled(JAPConfNetwork.this.m_cbProxyAuthentication.isSelected());
            }
        });
        this.m_labelProxyHost = new JLabel(JAPMessages.getString("settingsProxyHost"));
        this.m_labelProxyPort = new JLabel(JAPMessages.getString("settingsProxyPort"));
        this.m_labelProxyType = new JLabel(JAPMessages.getString("settingsProxyType"));
        this.m_labelProxyAuthUserID = new JLabel(JAPMessages.getString("settingsProxyAuthUserID"));
        this.m_cbProxy.setForeground(this.m_labelProxyPort.getForeground());
        this.m_cbProxyAuthentication.setForeground(this.m_labelProxyPort.getForeground());
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsProxyBorder")));
        final JPanel panel2 = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel2.setLayout(layout);
        if (JAPModel.isSmallDisplay()) {
            panel2.setBorder(new EmptyBorder(1, 10, 1, 10));
        }
        else {
            panel2.setBorder(new EmptyBorder(5, 10, 10, 10));
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        Insets insets;
        if (JAPModel.isSmallDisplay()) {
            insets = new Insets(0, 0, 1, 0);
        }
        else {
            insets = new Insets(0, 0, 3, 0);
        }
        gridBagConstraints.insets = insets;
        layout.setConstraints(this.m_cbProxy, gridBagConstraints);
        panel2.add(this.m_cbProxy);
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.m_labelProxyType, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        panel2.add(this.m_labelProxyType);
        layout.setConstraints(this.m_comboProxyType, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        panel2.add(this.m_comboProxyType);
        layout.setConstraints(this.m_labelProxyHost, gridBagConstraints);
        panel2.add(this.m_labelProxyHost);
        gridBagConstraints.gridy = 4;
        layout.setConstraints(this.m_tfProxyHost, gridBagConstraints);
        panel2.add(this.m_tfProxyHost);
        gridBagConstraints.gridy = 5;
        layout.setConstraints(this.m_labelProxyPort, gridBagConstraints);
        panel2.add(this.m_labelProxyPort);
        gridBagConstraints.gridy = 6;
        layout.setConstraints(this.m_tfProxyPortNumber, gridBagConstraints);
        panel2.add(this.m_tfProxyPortNumber);
        final JSeparator separator = new JSeparator();
        gridBagConstraints.gridy = 7;
        if (JAPModel.isSmallDisplay()) {
            gridBagConstraints.insets = new Insets(5, 0, 1, 0);
        }
        else {
            gridBagConstraints.insets = new Insets(10, 0, 3, 0);
        }
        layout.setConstraints(separator, gridBagConstraints);
        panel2.add(separator);
        gridBagConstraints.insets = insets;
        gridBagConstraints.gridy = 8;
        layout.setConstraints(this.m_cbProxyAuthentication, gridBagConstraints);
        panel2.add(this.m_cbProxyAuthentication);
        gridBagConstraints.gridy = 9;
        layout.setConstraints(this.m_labelProxyAuthUserID, gridBagConstraints);
        panel2.add(this.m_labelProxyAuthUserID);
        gridBagConstraints.gridy = 10;
        layout.setConstraints(this.m_tfProxyAuthenticationUserID, gridBagConstraints);
        panel2.add(this.m_tfProxyAuthenticationUserID);
        gridBagConstraints.gridy = 11;
        panel.add(panel2, "North");
        return panel;
    }
    
    public String getHelpContext() {
        return "net";
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
        MSG_LISTENER_CHANGED = ((JAPConfNetwork.class$jap$JAPConfNetwork == null) ? (JAPConfNetwork.class$jap$JAPConfNetwork = class$("jap.JAPConfNetwork")) : JAPConfNetwork.class$jap$JAPConfNetwork).getName() + "_listenerChanged";
        MSG_ACCESS_TO_JAP = ((JAPConfNetwork.class$jap$JAPConfNetwork == null) ? (JAPConfNetwork.class$jap$JAPConfNetwork = class$("jap.JAPConfNetwork")) : JAPConfNetwork.class$jap$JAPConfNetwork).getName() + "_accessToJAP";
        MSG_SLOW_ANTI_CENSORSHIP = ((JAPConfNetwork.class$jap$JAPConfNetwork == null) ? (JAPConfNetwork.class$jap$JAPConfNetwork = class$("jap.JAPConfNetwork")) : JAPConfNetwork.class$jap$JAPConfNetwork).getName() + "_slowAntiCensorship";
        MSG_SLOW_ANTI_CENSORSHIP_Q = ((JAPConfNetwork.class$jap$JAPConfNetwork == null) ? (JAPConfNetwork.class$jap$JAPConfNetwork = class$("jap.JAPConfNetwork")) : JAPConfNetwork.class$jap$JAPConfNetwork).getName() + "_slowAntiCensorshipQuestion";
    }
}
