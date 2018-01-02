// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.text.SimpleDateFormat;
import logging.LogHolder;
import logging.LogType;
import anon.util.CountryMapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import anon.crypto.CertificateInfoStructure;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import anon.crypto.Validity;
import anon.crypto.X509Extensions;
import anon.crypto.X509UnknownExtension;
import javax.swing.JSeparator;
import java.awt.GridBagConstraints;
import anon.crypto.MyECPublicKey;
import javax.swing.Icon;
import java.util.Date;
import anon.crypto.MyRSAPublicKey;
import java.awt.Insets;
import anon.crypto.X509DistinguishedName;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import anon.util.JAPMessages;
import anon.crypto.CertPath;
import java.awt.Component;
import anon.crypto.JAPCertificate;
import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.util.Locale;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import gui.dialog.JAPDialog;

public class CertDetailsDialog extends JAPDialog implements MouseListener
{
    public static final String MSG_CERTVALID;
    public static final String MSG_CERTNOTVALID;
    public static final String MSG_CERT_VERIFIED;
    public static final String MSG_CERT_NOT_VERIFIED;
    private static final String MSG_TITLE;
    private static final String MSG_X509Attribute_ST;
    private static final String MSG_X509Attribute_L;
    private static final String MSG_X509Attribute_C;
    private static final String MSG_X509Attribute_CN;
    private static final String MSG_X509Attribute_O;
    private static final String MSG_X509Attribute_OU;
    private static final String MSG_X509Attribute_EMAIL;
    private static final String MSG_SHOW_CERT;
    private static final String MSG_CERT_HIERARCHY;
    private static final String MSG_SYMBOLS;
    private static final String MSG_DETAILS;
    private static final String MSG_X509Attribute_EMAILADDRESS;
    private static final String MSG_X509Attribute_SURNAME;
    private static final String MSG_X509Attribute_GIVENNAME;
    private static final String MSG_ALERT_CERTDATE_EXPIRED;
    private static final String MSG_ALERT_CERTDATE_NOTYET;
    private static final String MSG_ALERT_SELF_SIGNED;
    private static final String MSG_ALERT_NOT_TRUSTED;
    private static final String UNKNOWN_EXTENSION;
    private static final String TITLE_DISTINGUISHEDNAME;
    private static final String TITLE_ISSUER;
    private static final String TITLE_VALIDITY;
    private static final String TITLE_VALIDITY_GENERAL;
    private static final String TITLE_VALIDITY_TO;
    private static final String TITLE_VALIDITY_FROM;
    private static final String TITLE_EXTENSIONS;
    private static final String TITLE_IDENTIFICATION;
    private static final String TITLE_IDENTIFICATION_SHA1;
    private static final String TITLE_IDENTIFICATION_MD5;
    private static final String TITLE_IDENTIFICATION_SERIAL;
    private static final String TITLE_KEYS;
    private static final String TITLE_KEYS_ALGORITHM;
    private static final String TITLE_KEYS_KEYLENGTH;
    private static final String TITLE_KEYS_SIGNALGORITHM;
    private static final String MSG_CERT_INFO_BORDER;
    private static final String CERT_VALID_INACTIVE = "certinactive.gif";
    private static final String CERT_INVALID_INACTIVE = "certinvalidinactive.gif";
    private final JLabel LABEL;
    private final Color TITLE_COLOR;
    private final Color ALERT_COLOR;
    private final Font TITLE_FONT;
    private final Font KEY_FONT;
    private final Font VALUE_FONT;
    private final Font ALERT_FONT;
    public static final String IMG_CERTENABLEDICON = "cenabled.gif";
    public static final String IMG_CERTDISABLEDICON = "cdisabled.gif";
    public static final String IMG_WARNING = "warning.gif";
    private static final String IMG_PATH = "certs/";
    private static final String IMG_CERT_ORANGE_OK = "certs/cert_orange_ok.png";
    private static final String IMG_CERT_ORANGE_NOK = "certs/cert_orange_nok.png";
    private static final String IMG_CERT_ORANGE_INVALID = "certs/cert_orange_invalid.png";
    private static final String IMG_CERT_ORANGE_OK_DARK = "certs/cert_orange_ok_dark.png";
    private static final String IMG_CERT_ORANGE_NOK_DARK = "certs/cert_orange_nok_dark.png";
    private static final String IMG_CERT_ORANGE_INVALID_DARK = "certs/cert_orange_invalid_dark.png";
    private static final String IMG_CERT_PURPLE_OK = "certs/cert_purple_ok.png";
    private static final String IMG_CERT_PURPLE_NOK = "certs/cert_purple_nok.png";
    private static final String IMG_CERT_PURPLE_INVALID = "certs/cert_purple_invalid.png";
    private static final String IMG_CERT_PURPLE_OK_DARK = "certs/cert_purple_ok_dark.png";
    private static final String IMG_CERT_PURPLE_NOK_DARK = "certs/cert_purple_nok_dark.png";
    private static final String IMG_CERT_PURPLE_INVALID_DARK = "certs/cert_purple_invalid_dark.png";
    private static final String IMG_CERT_BLUE_OK = "certs/cert_blue_ok.png";
    private static final String IMG_CERT_BLUE_NOK = "certs/cert_blue_nok.png";
    private static final String IMG_CERT_BLUE_INVALID = "certs/cert_blue_invalid.png";
    private static final String IMG_CERT_BLUE_OK_DARK = "certs/cert_blue_ok_dark.png";
    private static final String IMG_CERT_BLUE_NOK_DARK = "certs/cert_blue_nok_dark.png";
    private static final String IMG_CERT_BLUE_INVALID_DARK = "certs/cert_orange_invalid_dark.png";
    private JLabel lbl_summaryIcon;
    private Locale m_Locale;
    private String str;
    private CertShortInfoPanel m_shortInfoPanel;
    private JList m_certList;
    private JTabbedPane m_tabbedPane;
    private DefaultListModel m_certListModel;
    private JAPCertificate m_detailedCert;
    static /* synthetic */ Class class$gui$CertDetailsDialog;
    
    public CertDetailsDialog(final Component component, final JAPCertificate detailedCert, final boolean b, final Locale locale, final CertPath certPath) {
        super(component, JAPMessages.getString(CertDetailsDialog.MSG_TITLE));
        this.LABEL = new JLabel();
        this.TITLE_COLOR = Color.blue;
        this.ALERT_COLOR = Color.red;
        this.TITLE_FONT = new Font(this.LABEL.getFont().getName(), 1, (int)(this.LABEL.getFont().getSize() * 1.2));
        this.KEY_FONT = new Font(this.LABEL.getFont().getName(), 1, this.LABEL.getFont().getSize());
        this.VALUE_FONT = new Font(this.LABEL.getFont().getName(), 0, this.LABEL.getFont().getSize());
        this.ALERT_FONT = new Font(this.LABEL.getFont().getName(), 1, this.LABEL.getFont().getSize());
        this.m_Locale = locale;
        final JTabbedPane tabbedPane = new JTabbedPane();
        final TitledGridBagPanel drawDetailsPanel = this.drawDetailsPanel(detailedCert, b);
        final JPanel drawCertPathPanel = this.drawCertPathPanel(certPath);
        tabbedPane.add(JAPMessages.getString(CertDetailsDialog.MSG_DETAILS), drawDetailsPanel);
        tabbedPane.add(JAPMessages.getString(CertDetailsDialog.MSG_CERT_HIERARCHY), drawCertPathPanel);
        this.getContentPane().add(new JScrollPane(tabbedPane, 20, 30));
        this.m_tabbedPane = tabbedPane;
        this.m_detailedCert = detailedCert;
        this.setSize();
        this.getContentPane().setVisible(true);
    }
    
    public CertDetailsDialog(final Component component, final JAPCertificate japCertificate, final boolean b, final Locale locale) {
        super(component, JAPMessages.getString(CertDetailsDialog.MSG_TITLE));
        this.LABEL = new JLabel();
        this.TITLE_COLOR = Color.blue;
        this.ALERT_COLOR = Color.red;
        this.TITLE_FONT = new Font(this.LABEL.getFont().getName(), 1, (int)(this.LABEL.getFont().getSize() * 1.2));
        this.KEY_FONT = new Font(this.LABEL.getFont().getName(), 1, this.LABEL.getFont().getSize());
        this.VALUE_FONT = new Font(this.LABEL.getFont().getName(), 0, this.LABEL.getFont().getSize());
        this.ALERT_FONT = new Font(this.LABEL.getFont().getName(), 1, this.LABEL.getFont().getSize());
        this.m_Locale = locale;
        this.getContentPane().add(new JScrollPane(this.drawDetailsPanel(japCertificate, b), 20, 30));
        this.setSize();
        this.setVisible(true);
    }
    
    private void setSize() {
        this.pack();
        if (this.getSize().height > 480) {
            this.setSize(this.getSize().width, 480);
        }
        if (this.getSize().width > 640) {
            this.setSize(640, this.getSize().height);
        }
    }
    
    private Vector idsToNames(final Vector vector) {
        final Vector<String> vector2 = new Vector<String>(vector.size());
        if (vector != null && vector.size() > 0) {
            for (int i = 0; i < vector.size(); ++i) {
                final String attributeNameFromAttributeIdentifier = X509DistinguishedName.getAttributeNameFromAttributeIdentifier(vector.elementAt(i));
                String s;
                if (attributeNameFromAttributeIdentifier.equals("ST")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_ST);
                }
                else if (attributeNameFromAttributeIdentifier.equals("L")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_L);
                }
                else if (attributeNameFromAttributeIdentifier.equals("C")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_C);
                }
                else if (attributeNameFromAttributeIdentifier.equals("CN")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_CN);
                }
                else if (attributeNameFromAttributeIdentifier.equals("O")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_O);
                }
                else if (attributeNameFromAttributeIdentifier.equals("OU")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_OU);
                }
                else if (attributeNameFromAttributeIdentifier.equals("E")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_EMAIL);
                }
                else if (attributeNameFromAttributeIdentifier.equals("EmailAddress")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_EMAILADDRESS);
                }
                else if (attributeNameFromAttributeIdentifier.equals("SURNAME")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_SURNAME);
                }
                else if (attributeNameFromAttributeIdentifier.equals("GIVENNAME")) {
                    s = JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_GIVENNAME);
                }
                else {
                    s = attributeNameFromAttributeIdentifier;
                }
                if (!s.equals(attributeNameFromAttributeIdentifier)) {
                    s = s + " (" + attributeNameFromAttributeIdentifier + ")";
                }
                vector2.addElement(s);
            }
        }
        return vector2;
    }
    
    private TitledGridBagPanel drawDetailsPanel(final JAPCertificate japCertificate, final boolean b) {
        final Insets insets = new Insets(2, 5, 2, 5);
        final TitledGridBagPanel titledGridBagPanel = new TitledGridBagPanel(null, insets);
        titledGridBagPanel.addMouseListener(this);
        this.lbl_summaryIcon = new JLabel();
        if (japCertificate.getPublicKey() instanceof MyRSAPublicKey) {
            if (b) {
                if (japCertificate.getValidity().isValid(new Date())) {
                    this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_orange_ok.png", true, false));
                }
                else {
                    this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_orange_invalid.png", true, false));
                }
            }
            else {
                this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_orange_nok.png", true, false));
            }
        }
        else if (japCertificate.getPublicKey() instanceof MyECPublicKey) {
            if (b) {
                if (japCertificate.getValidity().isValid(new Date())) {
                    this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_blue_ok.png", true, false));
                }
                else {
                    this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_blue_invalid.png", true, false));
                }
            }
            else {
                this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_blue_nok.png", true, false));
            }
        }
        else if (b) {
            if (japCertificate.getValidity().isValid(new Date())) {
                this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_purple_ok.png", true, false));
            }
            else {
                this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_purple_invalid.png", true, false));
            }
        }
        else {
            this.lbl_summaryIcon.setIcon(GUIUtils.loadImageIcon("certs/cert_purple_nok.png", true, false));
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.insets = new Insets(1, 10, 1, 10);
        titledGridBagPanel.add(this.lbl_summaryIcon, gridBagConstraints);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = insets;
        final JLabel label = new JLabel(japCertificate.getSubject().getCommonName(), 2);
        label.setForeground(this.TITLE_COLOR);
        label.setFont(this.TITLE_FONT);
        gridBagConstraints.gridwidth = 2;
        titledGridBagPanel.add(label, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        titledGridBagPanel.add(new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_ISSUER), 4), gridBagConstraints);
        gridBagConstraints.gridx = 2;
        this.str = japCertificate.getIssuer().getOrganisation();
        if (this.str == null || this.str.equals("")) {
            this.str = japCertificate.getIssuer().getCommonName();
        }
        titledGridBagPanel.add(new JLabel(this.str), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        titledGridBagPanel.add(new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_VALIDITY_TO), 4), gridBagConstraints);
        gridBagConstraints.gridx = 2;
        titledGridBagPanel.add(new JLabel(japCertificate.getValidity().getValidTo().toString()), gridBagConstraints);
        titledGridBagPanel.addDummyRows(5);
        final Date date = new Date();
        if (!japCertificate.getValidity().isValid(date)) {
            if (japCertificate.getValidity().getValidFrom().getTime() < date.getTime()) {
                final JLabel label2 = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_ALERT_CERTDATE_EXPIRED), 2);
                label2.setFont(this.ALERT_FONT);
                label2.setForeground(this.ALERT_COLOR);
                titledGridBagPanel.addRow(null, null, label2, null);
            }
            else if (japCertificate.getValidity().getValidTo().getTime() > date.getTime()) {
                final JLabel label3 = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_ALERT_CERTDATE_NOTYET), 2);
                label3.setFont(this.ALERT_FONT);
                label3.setForeground(this.ALERT_COLOR);
                titledGridBagPanel.addRow(null, null, label3, null);
            }
        }
        if (!b) {
            String s = JAPMessages.getString(CertDetailsDialog.MSG_ALERT_NOT_TRUSTED);
            if (japCertificate.verify(japCertificate)) {
                s = JAPMessages.getString(CertDetailsDialog.MSG_ALERT_SELF_SIGNED);
            }
            final JLabel label4 = new JLabel(s, 2);
            label4.setFont(this.ALERT_FONT);
            label4.setForeground(this.ALERT_COLOR);
            titledGridBagPanel.addRow(null, null, label4, null);
        }
        final X509DistinguishedName subject = japCertificate.getSubject();
        final Vector attributeIdentifiers = subject.getAttributeIdentifiers();
        final Vector attributeValues = subject.getAttributeValues();
        this.replaceCountryCodeByCountryName(attributeValues, attributeIdentifiers);
        final Vector idsToNames = this.idsToNames(attributeIdentifiers);
        final JLabel label5 = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_DISTINGUISHEDNAME), 4);
        label5.setFont(this.TITLE_FONT);
        label5.setForeground(this.TITLE_COLOR);
        titledGridBagPanel.addRow(label5, null, new JSeparator(0));
        for (int i = 0; i < idsToNames.size(); ++i) {
            final JLabel label6 = new JLabel(idsToNames.elementAt(i).toString(), 4);
            label6.setFont(this.KEY_FONT);
            final JLabel label7 = new JLabel(attributeValues.elementAt(i).toString(), 2);
            label7.setFont(this.VALUE_FONT);
            titledGridBagPanel.addRow(label6, null, label7);
        }
        final Vector attributeIdentifiers2 = japCertificate.getIssuer().getAttributeIdentifiers();
        final Vector attributeValues2 = japCertificate.getIssuer().getAttributeValues();
        this.replaceCountryCodeByCountryName(attributeValues2, attributeIdentifiers2);
        final Vector idsToNames2 = this.idsToNames(attributeIdentifiers2);
        final JLabel label8 = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_ISSUER), 4);
        label8.setFont(this.TITLE_FONT);
        label8.setForeground(this.TITLE_COLOR);
        titledGridBagPanel.addRow(label8, null, new JSeparator(0));
        for (int j = 0; j < idsToNames2.size(); ++j) {
            final JLabel label9 = new JLabel(idsToNames2.elementAt(j).toString(), 4);
            label9.setFont(this.KEY_FONT);
            final JLabel label10 = new JLabel(attributeValues2.elementAt(j).toString(), 2);
            label10.setFont(this.VALUE_FONT);
            titledGridBagPanel.addRow(label9, null, label10);
        }
        final X509Extensions extensions = japCertificate.getExtensions();
        final JLabel label11 = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_EXTENSIONS), 4);
        label11.setFont(this.TITLE_FONT);
        label11.setForeground(this.TITLE_COLOR);
        if (extensions.getSize() > 0) {
            titledGridBagPanel.addRow(label11, null, new JSeparator(0));
        }
        for (int k = 0; k < extensions.getExtensions().size(); ++k) {
            if (extensions.getExtension(k) instanceof X509UnknownExtension) {
                String s2;
                if (extensions.getExtension(k).isCritical()) {
                    s2 = "*";
                }
                else {
                    s2 = "";
                }
                final JLabel label12 = new JLabel(JAPMessages.getString(CertDetailsDialog.UNKNOWN_EXTENSION) + s2, 4);
                label12.setFont(this.KEY_FONT);
                final StringBuffer sb = new StringBuffer();
                for (int l = 0; l < extensions.getExtension(k).getValues().size(); ++l) {
                    sb.append(extensions.getExtension(k).getIdentifier());
                }
                final JLabel label13 = new JLabel(sb.toString(), 2);
                label12.setFont(this.KEY_FONT);
                label13.setFont(this.VALUE_FONT);
                titledGridBagPanel.addRow(label12, null, label13);
            }
            else {
                final JLabel label14 = new JLabel(extensions.getExtension(k).getName(), 4);
                label14.setFont(this.KEY_FONT);
                final Vector values = extensions.getExtension(k).getValues();
                if (values.size() == 0) {
                    titledGridBagPanel.addRow(label14, null, null);
                }
                else {
                    final JLabel label15 = new JLabel(values.elementAt(0).toString());
                    label15.setFont(this.VALUE_FONT);
                    titledGridBagPanel.addRow(label14, null, label15);
                    for (int n = 1; n < values.size(); ++n) {
                        final JLabel label16 = new JLabel(values.elementAt(n).toString());
                        label16.setFont(this.VALUE_FONT);
                        titledGridBagPanel.addRow(null, null, label16);
                    }
                }
            }
        }
        final Validity validity = japCertificate.getValidity();
        final Vector vector = new Vector<String>();
        vector.addElement(new String(JAPMessages.getString(CertDetailsDialog.TITLE_VALIDITY_GENERAL)));
        vector.addElement(new String(JAPMessages.getString(CertDetailsDialog.TITLE_VALIDITY_FROM)));
        vector.addElement(new String(JAPMessages.getString(CertDetailsDialog.TITLE_VALIDITY_TO)));
        final Vector<String> vector2 = new Vector<String>();
        if (validity.isValid(new Date())) {
            vector2.addElement(JAPMessages.getString(CertDetailsDialog.MSG_CERTVALID));
        }
        else {
            vector2.addElement(JAPMessages.getString(CertDetailsDialog.MSG_CERTNOTVALID));
        }
        vector2.addElement(validity.getValidFrom().toString());
        vector2.addElement(validity.getValidTo().toString());
        final JLabel label17 = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_VALIDITY), 4);
        label17.setFont(this.TITLE_FONT);
        label17.setForeground(this.TITLE_COLOR);
        titledGridBagPanel.addRow(label17, null, new JSeparator(0));
        for (int n2 = 0; n2 < vector.size(); ++n2) {
            final JLabel label18 = new JLabel(vector.elementAt(n2).toString(), 4);
            final JLabel label19 = new JLabel(vector2.elementAt(n2).toString(), 2);
            label18.setFont(this.KEY_FONT);
            label19.setFont(this.VALUE_FONT);
            titledGridBagPanel.addRow(label18, null, label19);
        }
        final Vector vector3 = new Vector<String>();
        vector3.addElement(JAPMessages.getString(CertDetailsDialog.TITLE_IDENTIFICATION_SHA1));
        vector3.addElement(JAPMessages.getString(CertDetailsDialog.TITLE_IDENTIFICATION_MD5));
        vector3.addElement(JAPMessages.getString(CertDetailsDialog.TITLE_IDENTIFICATION_SERIAL));
        final Vector<String> vector4 = new Vector<String>();
        vector4.addElement(japCertificate.getSHA1Fingerprint());
        vector4.addElement(japCertificate.getMD5Fingerprint());
        vector4.addElement((String)japCertificate.getSerialNumber());
        final JLabel label20 = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_IDENTIFICATION), 4);
        label20.setFont(this.TITLE_FONT);
        label20.setForeground(this.TITLE_COLOR);
        titledGridBagPanel.addRow(label20, null, new JSeparator(0));
        for (int n3 = 0; n3 < vector3.size(); ++n3) {
            final JLabel label21 = new JLabel(vector3.elementAt(n3).toString(), 4);
            final JLabel label22 = new JLabel(vector4.elementAt(n3).toString());
            label21.setFont(this.KEY_FONT);
            label22.setFont(this.VALUE_FONT);
            titledGridBagPanel.addRow(label21, null, label22);
        }
        final Vector vector5 = new Vector<String>();
        vector5.addElement(JAPMessages.getString(CertDetailsDialog.TITLE_KEYS_ALGORITHM));
        final Vector<String> vector6 = new Vector<String>();
        vector6.addElement(new String(japCertificate.getPublicKey().getAlgorithm()));
        final int keyLength = japCertificate.getPublicKey().getKeyLength();
        vector5.addElement(JAPMessages.getString(CertDetailsDialog.TITLE_KEYS_KEYLENGTH));
        vector6.addElement(new Integer(keyLength).toString() + " Bit");
        vector5.addElement(JAPMessages.getString(CertDetailsDialog.TITLE_KEYS_SIGNALGORITHM));
        vector6.addElement(japCertificate.getSignatureAlgorithmName());
        final JLabel label23 = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_KEYS), 4);
        label23.setFont(this.TITLE_FONT);
        label23.setForeground(this.TITLE_COLOR);
        titledGridBagPanel.addRow(label23, null, new JSeparator(0));
        for (int n4 = 0; n4 < vector5.size(); ++n4) {
            final JLabel label24 = new JLabel(vector5.elementAt(n4).toString(), 4);
            final JLabel label25 = new JLabel(vector6.elementAt(n4).toString());
            label24.setFont(this.KEY_FONT);
            label25.setFont(this.VALUE_FONT);
            titledGridBagPanel.addRow(label24, null, label25);
        }
        return titledGridBagPanel;
    }
    
    private JPanel drawCertPathPanel(final CertPath certPath) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(10, 10, 5, 10);
        final JLabel label = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_CERT_HIERARCHY), 4);
        label.setFont(this.TITLE_FONT);
        label.setForeground(this.TITLE_COLOR);
        panel.add(label, gridBagConstraints);
        this.m_certListModel = new DefaultListModel();
        (this.m_certList = new JList(this.m_certListModel)).setFont(this.VALUE_FONT);
        this.m_certList.setSelectionMode(0);
        this.m_certList.setCellRenderer(new CertPathListCellRenderer());
        this.m_certList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                if (CertDetailsDialog.this.m_certListModel.getSize() != 0 && CertDetailsDialog.this.m_certList.getSelectedValue() != null) {
                    CertDetailsDialog.this.m_shortInfoPanel.update(CertDetailsDialog.this.m_certList.getSelectedValue().getCertificate());
                }
            }
        });
        this.m_certList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    CertDetailsDialog.this.showCert();
                }
            }
        });
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(30);
        scrollPane.setVerticalScrollBarPolicy(20);
        scrollPane.getViewport().add(this.m_certList);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new Insets(5, 20, 10, 20);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 11;
        panel.add(scrollPane, gridBagConstraints);
        final JButton button = new JButton(JAPMessages.getString(CertDetailsDialog.MSG_SHOW_CERT) + "...");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CertDetailsDialog.this.showCert();
            }
        });
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 5, 10, 20);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 18;
        panel.add(button, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 16;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(new JAPHtmlMultiLineLabel(JAPMessages.getString(CertDetailsDialog.MSG_SYMBOLS)), gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 15, 5, 5);
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        panel.add(new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_CERTVALID), GUIUtils.loadImageIcon("cenabled.gif", false, false), 2), gridBagConstraints);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridy;
        panel.add(new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_CERTNOTVALID), GUIUtils.loadImageIcon("warning.gif", false, false), 2), gridBagConstraints);
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        ++gridBagConstraints7.gridy;
        gridBagConstraints.insets = new Insets(5, 15, 20, 5);
        final JLabel label2 = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_CERT_NOT_VERIFIED), GUIUtils.loadImageIcon("cdisabled.gif", false, false), 2);
        label2.setForeground(Color.red);
        panel.add(label2, gridBagConstraints);
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        --gridBagConstraints8.gridx;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridy;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 20, 10, 10);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 11;
        panel.add(new JSeparator(), gridBagConstraints);
        this.m_shortInfoPanel = new CertShortInfoPanel();
        if (certPath != null && this.m_certListModel.getSize() > 0) {
            this.m_certList.setSelectedValue(this.m_certListModel.lastElement(), true);
        }
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        ++gridBagConstraints10.gridy;
        gridBagConstraints.weighty = 1.0;
        panel.add(this.m_shortInfoPanel, gridBagConstraints);
        return panel;
    }
    
    private void showCert() {
        boolean enabled = true;
        if (this.m_shortInfoPanel.getShownCertificate() != null) {
            if (this.m_shortInfoPanel.getShownCertificate().equals(this.m_detailedCert)) {
                this.m_tabbedPane.setSelectedIndex(0);
            }
            else {
                if (this.m_certList.getSelectedIndex() == 0) {
                    enabled = this.m_certListModel.firstElement().isEnabled();
                }
                new CertDetailsDialog(this.getContentPane(), this.m_shortInfoPanel.getShownCertificate(), enabled, this.m_Locale).setVisible(true);
            }
        }
    }
    
    private void replaceCountryCodeByCountryName(final Vector vector, final Vector vector2) {
        for (int i = 0; i < vector.size(); ++i) {
            if (vector2.elementAt(i).equals(X509DistinguishedName.IDENTIFIER_C)) {
                try {
                    vector.setElementAt(new CountryMapper(vector.elementAt(i).toString(), this.m_Locale).toString(), i);
                }
                catch (IllegalArgumentException ex) {
                    LogHolder.log(7, LogType.GUI, "Invalid / Unknown country code");
                    vector.setElementAt(vector.elementAt(i), i);
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent != null && mouseEvent.getClickCount() >= 2) {
            this.dispose();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
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
        MSG_CERTVALID = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_certValid";
        MSG_CERTNOTVALID = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_certNotValid";
        MSG_CERT_VERIFIED = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_certVerified";
        MSG_CERT_NOT_VERIFIED = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_certNotVerified";
        MSG_TITLE = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName();
        MSG_X509Attribute_ST = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeST";
        MSG_X509Attribute_L = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeL";
        MSG_X509Attribute_C = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeC";
        MSG_X509Attribute_CN = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeCN";
        MSG_X509Attribute_O = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeO";
        MSG_X509Attribute_OU = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeOU";
        MSG_X509Attribute_EMAIL = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeEMAIL";
        MSG_SHOW_CERT = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_showCert";
        MSG_CERT_HIERARCHY = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_certHierarchy";
        MSG_SYMBOLS = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_symbols";
        MSG_DETAILS = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_detailsTab";
        MSG_X509Attribute_EMAILADDRESS = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeEMAIL";
        MSG_X509Attribute_SURNAME = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeSURNAME";
        MSG_X509Attribute_GIVENNAME = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_attributeGIVENNAME";
        MSG_ALERT_CERTDATE_EXPIRED = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_alertCertValidityExpired";
        MSG_ALERT_CERTDATE_NOTYET = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_alertCertNotYetValid";
        MSG_ALERT_SELF_SIGNED = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_alertSelfSigned";
        MSG_ALERT_NOT_TRUSTED = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_alertSignatureNotTrusted";
        UNKNOWN_EXTENSION = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_alertUnknownExtension";
        TITLE_DISTINGUISHEDNAME = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleDistinguishedName";
        TITLE_ISSUER = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleIssuer";
        TITLE_VALIDITY = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleValidity";
        TITLE_VALIDITY_GENERAL = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleValidityGeneral";
        TITLE_VALIDITY_TO = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleValidityTo";
        TITLE_VALIDITY_FROM = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleValidityFrom";
        TITLE_EXTENSIONS = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleExtensions";
        TITLE_IDENTIFICATION = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleIdentification";
        TITLE_IDENTIFICATION_SHA1 = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleIdentificationSHA1";
        TITLE_IDENTIFICATION_MD5 = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleIdentificationMD5";
        TITLE_IDENTIFICATION_SERIAL = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleIdentificationSerial";
        TITLE_KEYS = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleKeys";
        TITLE_KEYS_ALGORITHM = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleKeysAlgorithm";
        TITLE_KEYS_KEYLENGTH = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleKeysKeylength";
        TITLE_KEYS_SIGNALGORITHM = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_titleKeysSignatureAlgorithm";
        MSG_CERT_INFO_BORDER = ((CertDetailsDialog.class$gui$CertDetailsDialog == null) ? (CertDetailsDialog.class$gui$CertDetailsDialog = class$("gui.CertDetailsDialog")) : CertDetailsDialog.class$gui$CertDetailsDialog).getName() + "_certInfoBorder";
    }
    
    public static class CertShortInfoPanel extends JPanel
    {
        private JLabel m_labelDate;
        private JLabel m_labelCN;
        private JLabel m_labelE;
        private JLabel m_labelCSTL;
        private JLabel m_labelO;
        private JLabel m_labelOU;
        private JLabel m_labelDateData;
        private JLabel m_labelCNData;
        private JLabel m_labelEData;
        private JLabel m_labelCSTLData;
        private JLabel m_labelOData;
        private JLabel m_labelOUData;
        private JLabel m_lblCertTitle;
        private JAPCertificate m_selectedCert;
        
        public CertShortInfoPanel() {
            final GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 10, 0, 0);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 2;
            this.add(this.m_lblCertTitle = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_CERT_INFO_BORDER)), gridBagConstraints);
            this.m_labelDate = new JLabel(JAPMessages.getString(CertDetailsDialog.TITLE_VALIDITY) + ":");
            this.m_labelCN = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_CN) + ":");
            this.m_labelE = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_EMAIL) + ":");
            this.m_labelCSTL = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_L) + ":");
            this.m_labelO = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_O) + ":");
            this.m_labelOU = new JLabel(JAPMessages.getString(CertDetailsDialog.MSG_X509Attribute_OU) + ":");
            this.m_labelDateData = new JLabel();
            this.m_labelCNData = new JLabel();
            this.m_labelEData = new JLabel();
            this.m_labelCSTLData = new JLabel();
            this.m_labelOData = new JLabel();
            this.m_labelOUData = new JLabel();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 15, 0, 0);
            layout.setConstraints(this.m_labelCN, gridBagConstraints);
            this.add(this.m_labelCN);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 10);
            layout.setConstraints(this.m_labelCNData, gridBagConstraints);
            this.add(this.m_labelCNData);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 15, 0, 0);
            layout.setConstraints(this.m_labelO, gridBagConstraints);
            this.add(this.m_labelO);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 10);
            layout.setConstraints(this.m_labelOData, gridBagConstraints);
            this.add(this.m_labelOData);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 15, 0, 0);
            layout.setConstraints(this.m_labelOU, gridBagConstraints);
            this.add(this.m_labelOU);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 10);
            layout.setConstraints(this.m_labelOUData, gridBagConstraints);
            this.add(this.m_labelOUData);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 15, 0, 0);
            layout.setConstraints(this.m_labelCSTL, gridBagConstraints);
            this.add(this.m_labelCSTL);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 10);
            layout.setConstraints(this.m_labelCSTLData, gridBagConstraints);
            this.add(this.m_labelCSTLData);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 15, 0, 0);
            layout.setConstraints(this.m_labelE, gridBagConstraints);
            this.add(this.m_labelE);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 10);
            layout.setConstraints(this.m_labelEData, gridBagConstraints);
            this.add(this.m_labelEData);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 15, 10, 0);
            layout.setConstraints(this.m_labelDate, gridBagConstraints);
            this.add(this.m_labelDate);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 10, 10, 10);
            layout.setConstraints(this.m_labelDateData, gridBagConstraints);
            this.add(this.m_labelDateData);
            gridBagConstraints.anchor = 17;
        }
        
        public JAPCertificate getShownCertificate() {
            return this.m_selectedCert;
        }
        
        public void setEnabled(final boolean b) {
            this.m_lblCertTitle.setEnabled(b);
            this.m_labelDate.setEnabled(b);
            this.m_labelCN.setEnabled(b);
            this.m_labelE.setEnabled(b);
            this.m_labelCSTL.setEnabled(b);
            this.m_labelO.setEnabled(b);
            this.m_labelOU.setEnabled(b);
            this.m_labelDateData.setEnabled(b);
            this.m_labelCNData.setEnabled(b);
            this.m_labelEData.setEnabled(b);
            this.m_labelCSTLData.setEnabled(b);
            this.m_labelOData.setEnabled(b);
            this.m_labelOUData.setEnabled(b);
            super.setEnabled(b);
        }
        
        public void update(final JAPCertificate selectedCert) {
            String text = null;
            this.m_selectedCert = selectedCert;
            this.m_labelCNData.setText("");
            this.m_labelEData.setText("");
            this.m_labelCSTLData.setText("");
            this.m_labelOData.setText("");
            this.m_labelOUData.setText("");
            this.m_labelDateData.setText("");
            if (selectedCert == null) {
                return;
            }
            final StringBuffer sb = new StringBuffer();
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            sb.append(simpleDateFormat.format(selectedCert.getValidity().getValidFrom()));
            sb.append(" - ");
            sb.append(simpleDateFormat.format(selectedCert.getValidity().getValidTo()));
            this.m_labelDateData.setText(sb.toString());
            final X509DistinguishedName subject = selectedCert.getSubject();
            if (subject.getCommonName() != null && subject.getCommonName().trim().length() > 0) {
                this.m_labelCNData.setText(subject.getCommonName().trim());
            }
            if (subject.getEmailAddress() != null && subject.getEmailAddress().trim().length() > 0) {
                this.m_labelEData.setText(subject.getEmailAddress().trim());
            }
            else if (subject.getE_EmailAddress() != null && subject.getE_EmailAddress().trim().length() > 0) {
                this.m_labelEData.setText(subject.getE_EmailAddress());
            }
            if (subject.getLocalityName() != null && subject.getLocalityName().trim().length() > 0) {
                text = subject.getLocalityName().trim();
            }
            if (subject.getStateOrProvince() != null && subject.getStateOrProvince().trim().length() > 0) {
                String string;
                if (text != null) {
                    string = text + ", ";
                }
                else {
                    string = "";
                }
                text = string + subject.getStateOrProvince().trim();
            }
            if (subject.getCountryCode() != null) {
                String s;
                try {
                    s = new CountryMapper(subject.getCountryCode(), JAPMessages.getLocale()).toString();
                }
                catch (IllegalArgumentException ex) {
                    s = subject.getCountryCode();
                }
                if (s.trim().length() > 0) {
                    String string2;
                    if (text != null) {
                        string2 = text + ", ";
                    }
                    else {
                        string2 = "";
                    }
                    text = string2 + s.trim();
                }
            }
            this.m_labelCSTLData.setText(text);
            if (subject.getOrganisation() != null && subject.getOrganisation().trim().length() > 0) {
                this.m_labelOData.setText(subject.getOrganisation().trim());
            }
            if (subject.getOrganisationalUnit() != null && subject.getOrganisationalUnit().trim().length() > 0) {
                this.m_labelOUData.setText(subject.getOrganisationalUnit().trim());
            }
        }
    }
    
    private final class CertPathListCellRenderer implements ListCellRenderer
    {
        private int m_itemcount;
        
        private CertPathListCellRenderer() {
            this.m_itemcount = 0;
        }
        
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean opaque, final boolean b) {
            final JPanel panel = new JPanel(new GridBagLayout());
            final JLabel label = new JLabel();
            final JLabel label2 = new JLabel();
            final JLabel label3 = new JLabel();
            final JLabel label4 = new JLabel();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final int n2 = n * 2;
            if (n2 > 0) {
                final char[] array = new char[n2];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = ' ';
                }
                label.setText(new String(array));
            }
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.anchor = 17;
            panel.add(label, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridx;
            panel.add(label2, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridx;
            panel.add(label3, gridBagConstraints);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridx;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 2;
            final JLabel label5 = new JLabel(" ");
            panel.add(label5, gridBagConstraints);
            ++this.m_itemcount;
            final CertificateInfoStructure certificateInfoStructure = (CertificateInfoStructure)o;
            String text = certificateInfoStructure.getCertificate().getSubject().getCommonName();
            if (text == null) {
                text = certificateInfoStructure.getCertificate().getSubject().toString();
            }
            label3.setText(text);
            label3.setEnabled(list.isEnabled());
            label2.setEnabled(list.isEnabled());
            label.setEnabled(list.isEnabled());
            label5.setEnabled(list.isEnabled());
            if (opaque) {
                label3.setBackground(list.getSelectionBackground());
                label3.setForeground(list.getSelectionForeground());
                panel.setBackground(list.getSelectionBackground());
                panel.setForeground(list.getSelectionForeground());
                label2.setBackground(list.getSelectionBackground());
                label2.setForeground(list.getSelectionForeground());
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionBackground());
                label5.setBackground(list.getSelectionBackground());
                label5.setForeground(list.getSelectionForeground());
            }
            else {
                label3.setBackground(list.getBackground());
                label3.setForeground(list.getForeground());
                panel.setBackground(list.getBackground());
                panel.setForeground(list.getForeground());
                label2.setBackground(list.getBackground());
                label2.setForeground(list.getForeground());
                label.setBackground(list.getBackground());
                label.setForeground(list.getBackground());
                label5.setBackground(list.getBackground());
                label5.setForeground(list.getForeground());
            }
            label3.setOpaque(opaque);
            panel.setOpaque(opaque);
            label2.setOpaque(opaque);
            label.setOpaque(opaque);
            label5.setOpaque(opaque);
            if (certificateInfoStructure.isEnabled()) {
                if (certificateInfoStructure.getCertificate().getValidity().isValid(new Date())) {
                    label2.setIcon(GUIUtils.loadImageIcon("cenabled.gif", false, false));
                }
                else {
                    label2.setIcon(GUIUtils.loadImageIcon("warning.gif", false, false));
                }
            }
            else {
                label3.setForeground(Color.red);
                label2.setIcon(GUIUtils.loadImageIcon("cdisabled.gif", false, false));
            }
            if (certificateInfoStructure.equals(list.getModel().getElementAt(list.getModel().getSize() - 1))) {
                label3.setFont(new Font(label3.getFont().getName(), 1, label3.getFont().getSize()));
            }
            else {
                label3.setFont(list.getFont());
            }
            return panel;
        }
    }
}
