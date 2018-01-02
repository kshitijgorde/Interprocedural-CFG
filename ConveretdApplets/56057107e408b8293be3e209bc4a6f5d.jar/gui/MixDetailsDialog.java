// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import logging.LogHolder;
import logging.LogType;
import java.net.URL;
import platform.AbstractOS;
import java.awt.event.ActionEvent;
import anon.infoservice.ServiceLocation;
import anon.infoservice.ServiceOperator;
import java.util.Date;
import java.awt.Color;
import anon.util.CountryMapper;
import javax.swing.Icon;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import anon.util.JAPMessages;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import anon.infoservice.MixInfo;
import anon.infoservice.MixCascade;
import gui.dialog.JAPDialog;

public class MixDetailsDialog extends JAPDialog
{
    public static final String MSG_NOT_VERIFIED;
    public static final String MSG_INVALID;
    public static final String MSG_VALID;
    public static final String MSG_INDEPENDENT_CERTIFICATIONS;
    public static final String MSG_MIX_X_OF_Y;
    public static String MSG_MIX_NAME;
    public static String MSG_LOCATION;
    public static String MSG_HOMEPAGE;
    public static String MSG_E_MAIL;
    public static String MSG_CERTIFICATES;
    public static String MSG_BTN_DATA_RETENTION;
    private static String MSG_TITLE;
    private MixCascade m_mixCascade;
    private MixInfo m_mixInfo;
    private int m_mixPosition;
    private ActionListener m_buttonListener;
    private JButton m_btnHomepage;
    private JButton m_btnEMail;
    private JButton m_btnCertificates;
    private JButton m_btnDataRetention;
    static /* synthetic */ Class class$gui$MixDetailsDialog;
    
    public MixDetailsDialog(final Component component, final MixCascade mixCascade, final int mixPosition) {
        super(component, JAPMessages.getString(MixDetailsDialog.MSG_TITLE));
        this.m_mixCascade = mixCascade;
        this.m_mixPosition = mixPosition;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = (JPanel)this.getContentPane();
        final JPanel panel2 = new JPanel(new FlowLayout());
        panel.setLayout(new GridBagLayout());
        if (this.m_mixCascade == null || mixCascade.getMixInfo(mixPosition) == null) {
            return;
        }
        this.m_mixInfo = mixCascade.getMixInfo(mixPosition);
        final ServiceOperator serviceOperator = this.m_mixInfo.getServiceOperator();
        final ServiceLocation serviceLocation = this.m_mixInfo.getServiceLocation();
        if (serviceOperator == null || serviceLocation == null) {
            return;
        }
        final JLabel label = new JLabel(JAPMessages.getString(MixDetailsDialog.MSG_MIX_NAME) + ":");
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(15, 15, 10, 15);
        gridBagConstraints.anchor = 17;
        panel.add(label, gridBagConstraints);
        final JLabel label2 = new JLabel(this.m_mixInfo.getName());
        gridBagConstraints.gridx = 1;
        panel.add(label2, gridBagConstraints);
        final JLabel label3 = new JLabel(JAPMessages.getString(MixDetailsDialog.MSG_LOCATION) + ":");
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 15, 10, 15);
        panel.add(label3, gridBagConstraints);
        final JLabel label4 = new JLabel(GUIUtils.getCountryFromServiceLocation(serviceLocation));
        gridBagConstraints.gridy = 1;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        label4.setIcon(GUIUtils.loadImageIcon("flags/" + serviceLocation.getCountryCode() + ".png"));
        panel.add(label4, gridBagConstraints);
        final JLabel label5 = new JLabel(JAPMessages.getString("mixOperator"));
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.anchor = 17;
        panel.add(label5, gridBagConstraints);
        String text = serviceOperator.getOrganization();
        final JLabel label6 = new JLabel();
        if (serviceOperator.getCountryCode() != null) {
            text = text + "  (" + new CountryMapper(serviceOperator.getCountryCode(), JAPMessages.getLocale()).toString() + ")";
            label6.setIcon(GUIUtils.loadImageIcon("flags/" + serviceOperator.getCountryCode() + ".png"));
        }
        label6.setText(text);
        gridBagConstraints.gridx = 1;
        panel.add(label6, gridBagConstraints);
        this.m_buttonListener = new MyButtonListener();
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        gridBagConstraints.gridwidth = 2;
        panel.add(panel2, gridBagConstraints);
        if (this.m_mixInfo.getCertPath() != null) {
            (this.m_btnCertificates = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_CERTIFICATES))).addActionListener(this.m_buttonListener);
            if (!this.m_mixInfo.getCertPath().isVerified()) {
                this.m_btnCertificates.setIcon(GUIUtils.loadImageIcon("certs/not_trusted.png"));
                this.m_btnCertificates.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_NOT_VERIFIED));
                this.m_btnCertificates.setForeground(Color.red);
            }
            else if (!this.m_mixInfo.getCertPath().isValid(new Date())) {
                this.m_btnCertificates.setIcon(GUIUtils.loadImageIcon("certs/invalid.png"));
                this.m_btnCertificates.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_INVALID));
            }
            else if (this.m_mixInfo.getCertPath().countVerifiedAndValidPaths() > 1) {
                this.m_btnCertificates.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_INDEPENDENT_CERTIFICATIONS, "" + this.m_mixInfo.getCertPath().countVerifiedAndValidPaths()));
                if (this.m_mixInfo.getCertPath().countVerifiedAndValidPaths() > 2) {
                    this.m_btnCertificates.setIcon(GUIUtils.loadImageIcon("certs/trusted_green.png"));
                }
                else {
                    this.m_btnCertificates.setIcon(GUIUtils.loadImageIcon("certs/trusted_blue.png"));
                }
            }
            else {
                this.m_btnCertificates.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_VALID));
                this.m_btnCertificates.setIcon(GUIUtils.loadImageIcon("certs/trusted_black.png"));
            }
            panel2.add(this.m_btnCertificates, gridBagConstraints);
        }
        if (serviceOperator.getEMail() != null) {
            (this.m_btnEMail = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_E_MAIL))).setToolTipText(serviceOperator.getEMail());
            this.m_btnEMail.addActionListener(this.m_buttonListener);
            panel2.add(this.m_btnEMail, gridBagConstraints);
        }
        if (serviceOperator.getUrl() != null) {
            (this.m_btnHomepage = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_HOMEPAGE))).setToolTipText(serviceOperator.getUrl());
            this.m_btnHomepage.addActionListener(this.m_buttonListener);
            panel2.add(this.m_btnHomepage, gridBagConstraints);
        }
        if (this.m_mixInfo.getDataRetentionInformation() != null) {
            (this.m_btnDataRetention = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_BTN_DATA_RETENTION), GUIUtils.loadImageIcon("certs/invalid.png"))).setToolTipText(JAPMessages.getString(DataRetentionDialog.MSG_DATA_RETENTION_MIX_EXPLAIN_SHORT));
            this.m_btnDataRetention.addActionListener(this.m_buttonListener);
            panel2.add(this.m_btnDataRetention, gridBagConstraints);
        }
        this.pack();
        this.setResizable(false);
        panel.setVisible(true);
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
        MSG_NOT_VERIFIED = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_notVerified";
        MSG_INVALID = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_invalid";
        MSG_VALID = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_valid";
        MSG_INDEPENDENT_CERTIFICATIONS = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_independentCertifications";
        MSG_MIX_X_OF_Y = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_mixXOfY";
        MixDetailsDialog.MSG_MIX_NAME = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_mixName";
        MixDetailsDialog.MSG_LOCATION = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_mixLocation";
        MixDetailsDialog.MSG_HOMEPAGE = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_operatorHomepage";
        MixDetailsDialog.MSG_E_MAIL = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_email";
        MixDetailsDialog.MSG_CERTIFICATES = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_certificates";
        MixDetailsDialog.MSG_BTN_DATA_RETENTION = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_btnDataRetention";
        MixDetailsDialog.MSG_TITLE = ((MixDetailsDialog.class$gui$MixDetailsDialog == null) ? (MixDetailsDialog.class$gui$MixDetailsDialog = class$("gui.MixDetailsDialog")) : MixDetailsDialog.class$gui$MixDetailsDialog).getName() + "_title";
    }
    
    private class MyButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == MixDetailsDialog.this.m_btnHomepage) {
                final String toolTipText = ((JButton)actionEvent.getSource()).getToolTipText();
                if (toolTipText == null) {
                    return;
                }
                final AbstractOS instance = AbstractOS.getInstance();
                try {
                    instance.openURL(new URL(toolTipText));
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.MISC, "Error opening URL in browser");
                }
            }
            else if (actionEvent.getSource() == MixDetailsDialog.this.m_btnDataRetention) {
                DataRetentionDialog.show(MixDetailsDialog.this.getContentPane(), MixDetailsDialog.this.m_mixCascade, MixDetailsDialog.this.m_mixPosition);
            }
            else if (actionEvent.getSource() == MixDetailsDialog.this.m_btnEMail) {
                final String toolTipText2 = MixDetailsDialog.this.m_btnEMail.getToolTipText();
                if (toolTipText2 == null) {
                    return;
                }
                final AbstractOS instance2 = AbstractOS.getInstance();
                try {
                    instance2.openEMail(toolTipText2);
                }
                catch (Exception ex2) {
                    LogHolder.log(3, LogType.MISC, "Error creating E-Mail!");
                }
            }
            else if (actionEvent.getSource() == MixDetailsDialog.this.m_btnCertificates) {
                new MultiCertOverview(MixDetailsDialog.this.getContentPane(), MixDetailsDialog.this.m_mixInfo.getCertPath(), MixDetailsDialog.this.m_mixInfo.getName(), false);
            }
        }
    }
}
