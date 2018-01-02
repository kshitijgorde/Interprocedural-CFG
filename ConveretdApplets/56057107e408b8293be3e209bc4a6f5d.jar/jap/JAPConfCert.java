// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.crypto.CertPath;
import java.awt.Window;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import anon.crypto.JAPCertificate;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListCellRenderer;
import gui.CAListCellRenderer;
import javax.swing.ListModel;
import anon.crypto.CertificateInfoStructure;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import gui.dialog.JAPDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import anon.util.JAPMessages;
import java.util.Observable;
import anon.crypto.SignatureVerifier;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.util.Enumeration;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import gui.CertDetailsDialog;
import javax.swing.border.TitledBorder;
import java.util.Observer;

final class JAPConfCert extends AbstractJAPConfModule implements Observer
{
    public static final String MSG_NO_CHECK_WARNING;
    private static final String MSG_DETAILS;
    private TitledBorder m_borderCert;
    private CertDetailsDialog.CertShortInfoPanel m_shortInfoPanel;
    private JButton m_bttnCertInsert;
    private JButton m_bttnCertRemove;
    private JButton m_bttnCertStatus;
    private JButton m_bttnCertDetails;
    private DefaultListModel m_listmodelCertList;
    private JList m_listCert;
    private JScrollPane m_scrpaneList;
    private Enumeration m_enumCerts;
    private JCheckBox m_cbCertCheckEnabled;
    private JPanel m_panelCAList;
    private Vector m_deletedCerts;
    private boolean m_bDoNotUpdate;
    static /* synthetic */ Class class$jap$JAPConfCert;
    
    public JAPConfCert() {
        super(new JAPConfCertSavePoint());
        this.m_bDoNotUpdate = false;
        this.m_deletedCerts = new Vector();
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                SignatureVerifier.getInstance().getVerificationCertificateStore().addObserver(this);
                this.update(SignatureVerifier.getInstance().getVerificationCertificateStore(), null);
                return true;
            }
        }
        return false;
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        rootPanel.setBorder(this.m_borderCert = new TitledBorder(JAPMessages.getString("confCertTab")));
        final JPanel caLabel = this.createCALabel();
        this.m_panelCAList = this.createCertCAPanel();
        this.m_shortInfoPanel = new CertDetailsDialog.CertShortInfoPanel();
        rootPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        rootPanel.add(caLabel, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        rootPanel.add(this.m_panelCAList, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        rootPanel.add(new JSeparator(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        rootPanel.add(this.m_shortInfoPanel, gridBagConstraints);
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("confCertTab");
    }
    
    private JPanel createCALabel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final JLabel label = new JLabel(JAPMessages.getString("certTrust") + ":");
        (this.m_cbCertCheckEnabled = new JCheckBox()).setSelected(true);
        this.m_cbCertCheckEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (!JAPConfCert.this.m_cbCertCheckEnabled.isSelected()) {
                    JAPDialog.showWarningDialog(JAPConfCert.this.m_cbCertCheckEnabled, JAPMessages.getString(JAPConfCert.MSG_NO_CHECK_WARNING));
                }
            }
        });
        this.m_cbCertCheckEnabled.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                final boolean selected = JAPConfCert.this.m_cbCertCheckEnabled.isSelected();
                JAPConfCert.this.m_shortInfoPanel.setEnabled(selected);
                JAPConfCert.this.m_bttnCertInsert.setEnabled(selected);
                final Object selectedValue = JAPConfCert.this.m_listCert.getSelectedValue();
                boolean enabled = false;
                if (selectedValue != null) {
                    enabled = (selected && !JAPConfCert.this.m_listCert.getSelectedValue().isNotRemovable());
                }
                JAPConfCert.this.m_bttnCertRemove.setEnabled(enabled);
                JAPConfCert.this.m_bttnCertStatus.setEnabled(selected);
                JAPConfCert.this.m_bttnCertDetails.setEnabled(selected);
                JAPConfCert.this.m_listCert.setEnabled(selected);
                JAPConfCert.this.m_panelCAList.setEnabled(selected);
            }
        });
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel.add(this.m_cbCertCheckEnabled, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        panel.add(label, gridBagConstraints);
        return panel;
    }
    
    private JPanel createCertCAPanel() {
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_listmodelCertList = new DefaultListModel();
        (this.m_listCert = new JList(this.m_listmodelCertList)).setSelectionMode(0);
        this.m_listCert.setCellRenderer(new CAListCellRenderer());
        this.m_listCert.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                if (JAPConfCert.this.m_listmodelCertList.getSize() == 0 || JAPConfCert.this.m_listCert.getSelectedValue() == null) {
                    JAPConfCert.this.m_shortInfoPanel.update((JAPCertificate)null);
                    JAPConfCert.this.m_bttnCertRemove.setEnabled(false);
                    JAPConfCert.this.m_bttnCertStatus.setEnabled(false);
                    JAPConfCert.this.m_bttnCertDetails.setEnabled(false);
                }
                else {
                    final CertificateInfoStructure certificateInfoStructure = JAPConfCert.this.m_listCert.getSelectedValue();
                    JAPConfCert.this.m_shortInfoPanel.update(certificateInfoStructure.getCertificate());
                    if (certificateInfoStructure.isEnabled()) {
                        JAPConfCert.this.m_bttnCertStatus.setText(JAPMessages.getString("certBttnDisable"));
                    }
                    else {
                        JAPConfCert.this.m_bttnCertStatus.setText(JAPMessages.getString("certBttnEnable"));
                    }
                    JAPConfCert.this.m_bttnCertStatus.setEnabled(true);
                    JAPConfCert.this.m_bttnCertRemove.setEnabled(!certificateInfoStructure.isNotRemovable());
                    JAPConfCert.this.m_bttnCertDetails.setEnabled(true);
                }
            }
        });
        this.m_listCert.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    JAPConfCert.this.m_bttnCertDetails.doClick();
                }
            }
        });
        this.m_scrpaneList = new JScrollPane();
        this.m_scrpaneList.getViewport().add(this.m_listCert, null);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new Insets(0, 10, 10, 10);
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.m_scrpaneList, gridBagConstraints);
        panel.add(this.m_scrpaneList);
        (this.m_bttnCertInsert = new JButton(JAPMessages.getString("certBttnInsert"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                boolean b = false;
                JAPCertificate openCertificate = null;
                try {
                    openCertificate = JAPUtil.openCertificate(new JFrame());
                }
                catch (Exception ex) {
                    b = true;
                }
                if (openCertificate == null && b) {
                    JAPDialog.showMessageDialog(JAPConfCert.this.getRootPanel(), JAPMessages.getString("certInputErrorTitle"));
                }
                if (openCertificate != null) {
                    JAPConfCert.this.m_listmodelCertList.addElement(new CertificateInfoStructure(CertPath.getRootInstance(openCertificate), null, 1, true, false, true, false));
                    JAPConfCert.this.m_listCert.setSelectedIndex(JAPConfCert.this.m_listmodelCertList.getSize());
                }
            }
        });
        (this.m_bttnCertRemove = new JButton(JAPMessages.getString("certBttnRemove"))).setEnabled(false);
        this.m_bttnCertRemove.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JAPConfCert.this.m_listmodelCertList.getSize() > 0) {
                    JAPConfCert.this.m_deletedCerts.addElement(JAPConfCert.this.m_listmodelCertList.getElementAt(JAPConfCert.this.m_listCert.getSelectedIndex()));
                    JAPConfCert.this.m_listmodelCertList.remove(JAPConfCert.this.m_listCert.getSelectedIndex());
                }
                if (JAPConfCert.this.m_listmodelCertList.getSize() == 0) {
                    JAPConfCert.this.m_bttnCertRemove.setEnabled(false);
                    JAPConfCert.this.m_bttnCertStatus.setEnabled(false);
                    JAPConfCert.this.m_bttnCertDetails.setEnabled(false);
                    JAPConfCert.this.m_shortInfoPanel.update((JAPCertificate)null);
                }
                else {
                    JAPConfCert.this.m_shortInfoPanel.update((JAPCertificate)null);
                    JAPConfCert.this.m_listCert.setSelectedIndex(0);
                    JAPConfCert.this.m_shortInfoPanel.update(JAPConfCert.this.m_listCert.getSelectedValue().getCertificate());
                }
            }
        });
        (this.m_bttnCertStatus = new JButton(JAPMessages.getString("certBttnEnable"))).setEnabled(false);
        this.m_bttnCertStatus.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final CertificateInfoStructure certificateInfoStructure = JAPConfCert.this.m_listCert.getSelectedValue();
                if (certificateInfoStructure.isEnabled()) {
                    certificateInfoStructure.setEnabled(false);
                    JAPConfCert.this.m_bttnCertStatus.setText(JAPMessages.getString("certBttnEnable"));
                }
                else {
                    certificateInfoStructure.setEnabled(true);
                    JAPConfCert.this.m_bttnCertStatus.setText(JAPMessages.getString("certBttnDisable"));
                }
                JAPConfCert.this.m_listCert.repaint();
            }
        });
        (this.m_bttnCertDetails = new JButton(JAPMessages.getString(JAPConfCert.MSG_DETAILS))).setEnabled(false);
        this.m_bttnCertDetails.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final CertificateInfoStructure certificateInfoStructure = JAPConfCert.this.m_listCert.getSelectedValue();
                if (certificateInfoStructure == null) {
                    return;
                }
                final CertDetailsDialog certDetailsDialog = new CertDetailsDialog(JAPConfCert.this.getRootPanel().getParent(), certificateInfoStructure.getCertificate().getX509Certificate(), true, JAPMessages.getLocale());
                certDetailsDialog.pack();
                certDetailsDialog.setVisible(true);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        layout.setConstraints(this.m_bttnCertInsert, gridBagConstraints);
        panel.add(this.m_bttnCertInsert);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.m_bttnCertRemove, gridBagConstraints);
        panel.add(this.m_bttnCertRemove);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.m_bttnCertStatus, gridBagConstraints);
        panel.add(this.m_bttnCertStatus);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.m_bttnCertDetails, gridBagConstraints);
        panel.add(this.m_bttnCertDetails);
        return panel;
    }
    
    public void update(final Observable observable, final Object o) {
        if (this.m_bDoNotUpdate) {
            return;
        }
        if (observable == SignatureVerifier.getInstance().getVerificationCertificateStore() && (o == null || (o instanceof Integer && (int)o == 1))) {
            final Enumeration elements = SignatureVerifier.getInstance().getVerificationCertificateStore().getAllCertificates().elements();
            synchronized (this) {
                final int selectedIndex = this.m_listCert.getSelectedIndex();
                this.m_listmodelCertList.clear();
                this.m_enumCerts = elements;
                while (this.m_enumCerts.hasMoreElements()) {
                    final CertificateInfoStructure certificateInfoStructure = this.m_enumCerts.nextElement();
                    if (certificateInfoStructure.getCertificateType() == 1) {
                        this.m_listmodelCertList.addElement(certificateInfoStructure);
                    }
                }
                if (this.m_listmodelCertList.getSize() > 0 && selectedIndex >= 0 && selectedIndex < this.m_listmodelCertList.getSize()) {
                    this.m_listCert.setSelectedIndex(selectedIndex);
                }
            }
        }
    }
    
    protected void onUpdateValues() {
        if (this.m_cbCertCheckEnabled.isSelected() != SignatureVerifier.getInstance().isCheckSignatures()) {
            this.m_cbCertCheckEnabled.setSelected(SignatureVerifier.getInstance().isCheckSignatures());
        }
    }
    
    protected boolean onOkPressed() {
        if (this.m_bDoNotUpdate) {
            return true;
        }
        synchronized (this) {
            this.m_bDoNotUpdate = true;
            SignatureVerifier.getInstance().setCheckSignatures(this.m_cbCertCheckEnabled.isSelected());
            final Enumeration<CertificateInfoStructure> elements = this.m_deletedCerts.elements();
            while (elements.hasMoreElements()) {
                SignatureVerifier.getInstance().getVerificationCertificateStore().removeCertificate(elements.nextElement());
            }
            this.m_deletedCerts.removeAllElements();
            final Enumeration<CertificateInfoStructure> elements2 = this.m_listmodelCertList.elements();
            while (elements2.hasMoreElements()) {
                final CertificateInfoStructure certificateInfoStructure = elements2.nextElement();
                final CertificateInfoStructure certificateInfoStructure2 = SignatureVerifier.getInstance().getVerificationCertificateStore().getCertificateInfoStructure(certificateInfoStructure.getCertificate(), 1);
                if (certificateInfoStructure2 != null) {
                    if (certificateInfoStructure2.isEnabled() == certificateInfoStructure.isEnabled()) {
                        continue;
                    }
                    SignatureVerifier.getInstance().getVerificationCertificateStore().setEnabled(certificateInfoStructure, certificateInfoStructure.isEnabled());
                }
                else {
                    SignatureVerifier.getInstance().getVerificationCertificateStore().addCertificateWithoutVerification(certificateInfoStructure.getCertificate(), 1, true, false);
                    SignatureVerifier.getInstance().getVerificationCertificateStore().setEnabled(certificateInfoStructure, certificateInfoStructure.isEnabled());
                }
            }
            super.m_savePoint.createSavePoint();
            this.m_bDoNotUpdate = false;
        }
        this.update(SignatureVerifier.getInstance().getVerificationCertificateStore(), null);
        return true;
    }
    
    protected void onCancelPressed() {
        this.m_cbCertCheckEnabled.setSelected(SignatureVerifier.getInstance().isCheckSignatures());
        this.update(SignatureVerifier.getInstance().getVerificationCertificateStore(), null);
        this.m_deletedCerts.removeAllElements();
    }
    
    protected void onResetToDefaultsPressed() {
        super.onResetToDefaultsPressed();
        this.m_cbCertCheckEnabled.setSelected(true);
    }
    
    public String getHelpContext() {
        return "cert";
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
        MSG_NO_CHECK_WARNING = ((JAPConfCert.class$jap$JAPConfCert == null) ? (JAPConfCert.class$jap$JAPConfCert = class$("jap.JAPConfCert")) : JAPConfCert.class$jap$JAPConfCert).getName() + "_noCheckWarning";
        MSG_DETAILS = ((JAPConfCert.class$jap$JAPConfCert == null) ? (JAPConfCert.class$jap$JAPConfCert = class$("jap.JAPConfCert")) : JAPConfCert.class$jap$JAPConfCert).getName() + "_details";
    }
}
