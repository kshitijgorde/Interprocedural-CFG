// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import anon.pay.xml.XMLErrorMessage;
import logging.LogHolder;
import logging.LogType;
import anon.pay.BIConnection;
import java.awt.event.ActionEvent;
import anon.util.Util;
import anon.util.JAPMessages;
import java.sql.Timestamp;
import jap.JAPUtil;
import javax.swing.Box;
import java.awt.Container;
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import anon.pay.PayAccount;
import anon.pay.xml.XMLPaymentSettings;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import jap.AbstractJAPConfModule;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class FlatrateDialog extends JAPDialog implements ActionListener
{
    private static final String MSG_HEADING = "heading";
    private static final String MSG_BALANCE_LABEL = "balance_label";
    private static final String MSG_FLAT_HEADER = "flat_header";
    private static final String MSG_PRICE_LABEL = "price_label";
    private static final String MSG_VALID = "valid";
    private static final String MSG_VOLUME_LABEL = "volume_label";
    private static final String MSG_INSUFFICIENT_FUNDS = "insufficient_funds";
    private static final String MSG_UNLIMITED = "unlimited";
    private static final String MSG_BUY = "buy";
    private static final String MSG_CANCEL = "cancel";
    private static final String MSG_ERROR_CONNECTION = "error_connection";
    private static final String MSG_ERROR_INSUFFICIENT_FUNDS = "error_insufficient_funds";
    private static final String MSG_ERROR_FLATRATE_REFUSED = "error_flatrate_refused";
    private static final String MSG_ERROR_ALREADY_ACTIVE = "error_already_active";
    private static final String MSG_ERROR_NO_SETTINGS = "error_no_settings";
    private static final String MSG_FLAT_BOUGHT = "flat_bought";
    private AbstractJAPConfModule m_parent;
    private JButton m_btnBuy;
    private JButton m_btnCancel;
    private JLabel m_lHeading;
    private JLabel m_lBalanceLabel;
    private JLabel m_lBalance;
    private JLabel m_lFlatHeader;
    private JLabel m_lPriceLabel;
    private JLabel m_lPrice;
    private JLabel m_lValidtimeLabel;
    private JLabel m_lValidtime;
    private JLabel m_lVolumeLabel;
    private JLabel m_lVolume;
    private JLabel m_lInsufficientFunds;
    private JPanel buttonPanel;
    private JPanel flatPanel;
    private XMLPaymentSettings paymentSettings;
    private boolean isFlatAffordable;
    private PayAccount m_account;
    static /* synthetic */ Class class$jap$pay$FlatrateDialog;
    
    public FlatrateDialog(final Component component, final AbstractJAPConfModule parent, final String s, final boolean b, final PayAccount account) {
        super(component, s, b);
        this.isFlatAffordable = true;
        this.m_parent = parent;
        this.m_account = account;
        if (this.m_account.isFlatrateActive() && this.m_account.getCurrentCredit() > 5000L) {
            JAPDialog.showMessageDialog(this, getString("error_already_active"));
            return;
        }
        this.paymentSettings = this.getPaymentSettings();
        if (this.paymentSettings == null) {
            JAPDialog.showMessageDialog(this, getString("error_no_settings"));
            return;
        }
        this.isFlatAffordable = (this.m_account.getCurrentCredit() >= Long.parseLong(this.paymentSettings.getSettingValue("FlatratePrice")));
        this.m_lHeading = new JLabel("<html><b>" + getString("heading") + "</b></html>");
        if (this.isFlatAffordable) {
            this.m_lInsufficientFunds = new JLabel("");
        }
        else {
            this.m_lInsufficientFunds = new JLabel(getString("insufficient_funds"));
        }
        this.flatPanel = this.buildFlatPanel(this.m_account);
        this.buttonPanel = this.buildButtonPanel();
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridy = 0;
        contentPane.add(this.m_lHeading, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        contentPane.add(this.m_lInsufficientFunds, gridBagConstraints);
        this.m_lInsufficientFunds.setForeground(Color.red);
        gridBagConstraints.gridy = 2;
        contentPane.add(this.flatPanel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        contentPane.add(this.buttonPanel, gridBagConstraints);
        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
    }
    
    private JPanel buildButtonPanel() {
        this.buttonPanel = new JPanel();
        (this.m_btnBuy = new JButton(getString("buy"))).addActionListener(this);
        (this.m_btnCancel = new JButton(getString("cancel"))).addActionListener(this);
        this.buttonPanel.add(this.m_btnCancel);
        this.buttonPanel.add(Box.createHorizontalStrut(10));
        this.buttonPanel.add(this.m_btnBuy);
        return this.buttonPanel;
    }
    
    private JPanel buildFlatPanel(final PayAccount payAccount) {
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 18;
        this.m_lBalanceLabel = new JLabel(getString("balance_label"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.m_lBalanceLabel, gridBagConstraints);
        panel.add(this.m_lBalanceLabel);
        this.m_lBalance = new JLabel(JAPUtil.formatEuroCentValue(payAccount.getCurrentCredit()));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.m_lBalance, gridBagConstraints);
        panel.add(this.m_lBalance);
        this.m_lFlatHeader = new JLabel(getString("flat_header"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(15, 5, 5, 5);
        layout.setConstraints(this.m_lFlatHeader, gridBagConstraints);
        panel.add(this.m_lFlatHeader);
        this.m_lPriceLabel = new JLabel(getString("price_label"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.m_lPriceLabel, gridBagConstraints);
        panel.add(this.m_lPriceLabel);
        this.m_lPrice = new JLabel(JAPUtil.formatEuroCentValue(Long.parseLong(this.paymentSettings.getSettingValue("FlatratePrice"))));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.m_lPrice, gridBagConstraints);
        panel.add(this.m_lPrice);
        this.m_lValidtimeLabel = new JLabel(getString("valid"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.m_lValidtimeLabel, gridBagConstraints);
        panel.add(this.m_lValidtimeLabel);
        if (this.paymentSettings.getSettingValue("DurationLimited").equalsIgnoreCase("true")) {
            this.m_lValidtime = new JLabel(JAPUtil.formatTimestamp(new Timestamp(this.paymentSettings.getEndDate().getTime().getTime()), false, JAPMessages.getLocale().getLanguage()));
        }
        else {
            this.m_lValidtime = new JLabel(getString("unlimited"));
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.m_lValidtime, gridBagConstraints);
        panel.add(this.m_lValidtime);
        this.m_lVolumeLabel = new JLabel(getString("volume_label"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(this.m_lVolumeLabel, gridBagConstraints);
        panel.add(this.m_lVolumeLabel);
        if (this.paymentSettings.getSettingValue("VolumeLimited").equals("true")) {
            this.m_lVolume = new JLabel(Util.formatBytesValueWithUnit(Long.parseLong(this.paymentSettings.getSettingValue("VolumeAmount")) * 1000L));
        }
        else {
            this.m_lVolume = new JLabel(getString("unlimited"));
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(this.m_lVolume, gridBagConstraints);
        panel.add(this.m_lVolume);
        return panel;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_btnBuy) {
            this.buyFlatrate();
        }
        else if (actionEvent.getSource() == this.m_btnCancel) {
            this.setVisible(false);
        }
    }
    
    private void buyFlatrate() {
        if (!this.isFlatAffordable) {
            JAPDialog.showMessageDialog(this, getString("insufficient_funds"));
            return;
        }
        XMLErrorMessage buyFlatrate = null;
        try {
            final BIConnection biConnection = new BIConnection(this.m_account.getBI());
            biConnection.connect();
            biConnection.authenticate(this.m_account.getAccountCertificate(), this.m_account.getPrivateKey());
            buyFlatrate = biConnection.buyFlatrate(this.m_account.getAccountNumber());
            biConnection.disconnect();
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.NET, "Error buying flatrate: " + ex.getMessage());
            JAPDialog.showMessageDialog(this, getString("error_connection"));
        }
        if (buyFlatrate == null) {
            JAPDialog.showMessageDialog(this, getString("error_connection"));
        }
        if (buyFlatrate.getErrorCode() == 0) {
            JAPDialog.showMessageDialog(this, getString("flat_bought"));
            this.setVisible(false);
            try {
                ((AccountSettingsPanel)this.m_parent).updateAccountShown();
            }
            catch (Exception ex2) {}
        }
        else {
            JAPDialog.showMessageDialog(this, getString("error_flatrate_refused"));
        }
    }
    
    private XMLPaymentSettings getPaymentSettings() {
        XMLPaymentSettings paymentSettings = null;
        try {
            final BIConnection biConnection = new BIConnection(this.m_account.getBI());
            biConnection.connect();
            biConnection.authenticate(this.m_account.getAccountCertificate(), this.m_account.getPrivateKey());
            paymentSettings = biConnection.getPaymentSettings();
            biConnection.disconnect();
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.NET, "Error getting payment settings: " + ex.getMessage());
        }
        return paymentSettings;
    }
    
    private static String getString(final String s) {
        return JAPMessages.getString(((FlatrateDialog.class$jap$pay$FlatrateDialog == null) ? (FlatrateDialog.class$jap$pay$FlatrateDialog = class$("jap.pay.FlatrateDialog")) : FlatrateDialog.class$jap$pay$FlatrateDialog).getName() + "_" + s);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
