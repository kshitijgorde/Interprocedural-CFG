// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.event.MouseEvent;
import anon.util.Util;
import anon.crypto.JAPCertificate;
import anon.crypto.MyECPublicKey;
import java.util.Date;
import anon.crypto.MyRSAPublicKey;
import javax.swing.JButton;
import java.util.Enumeration;
import javax.swing.JSeparator;
import anon.util.CountryMapper;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import anon.util.JAPMessages;
import java.awt.Component;
import anon.crypto.CertPathInfo;
import java.util.Hashtable;
import anon.crypto.MultiCertPath;
import java.awt.event.MouseListener;
import gui.dialog.JAPDialog;

public class MultiCertOverview extends JAPDialog implements MouseListener
{
    private static final String TITLE;
    private static final String SUMMARY;
    private static final String EXPLANATION;
    private static final String MSG_NUMBER_OF_CERTS_ONE;
    private static final String MSG_NUMBER_OF_CERTS_ONE_NOT_TRUSTED;
    private static final String MSG_NUMBER_OF_CERTS_ONE_EXPIRED;
    private static final String MSG_NUMBER_OF_CERTS;
    private static final String MSG_NUMBER_OF_TRUSTED_CERTS_ONE;
    private static final String MSG_NUMBER_OF_TRUSTED_CERTS;
    private static final String MSG_IDENTITY_ONE;
    private static final String MSG_IDENTITY;
    private static final String MSG_SHOW_DETAILS;
    private static final String MSG_SYMBOLS;
    private static final String MSG_TRUSTED;
    private static final String MSG_NOT_TRUSTED;
    private static final String MSG_VALID;
    private static final String MSG_INVALID;
    private static final String MSG_ROOT_CERTS;
    private static final String HINT_ROOT_CERTS;
    private static final String MSG_OP_CERTS;
    private static final String HINT_OP;
    private static final String MSG_MIX_CERTS;
    private static final String HINT_MIX;
    private static final String MSG_IS_CERTS;
    private static final String HINT_IS;
    private static final String HINT_ARROW;
    private static final String HINT_CERT_DETAILS;
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
    private static final String IMG_ARROW_NORTH = "certs/arrow_north_ok.png";
    private static final String IMG_ARROW_NORTH_NOK = "certs/arrow_north_nok.png";
    private static final String IMG_ARROW_NORTH_EAST = "certs/arrow_north_east_ok.png";
    private static final String IMG_ARROW_NORTH_EAST_NOK = "certs/arrow_north_east_nok.png";
    private static final String IMG_ARROW_NORTH_WEST = "certs/arrow_north_west_ok.png";
    private static final String IMG_ARROW_NORTH_WEST_NOK = "certs/arrow_north_west_nok.png";
    public static final String IMG_NOT_TRUSTED = "certs/not_trusted.png";
    public static final String IMG_TRUSTED = "certs/trusted_black.png";
    public static final String IMG_TRUSTED_DOUBLE = "certs/trusted_blue.png";
    public static final String IMG_TRUSTED_THREE_CERTS = "certs/trusted_green.png";
    public static final String IMG_INVALID = "certs/invalid.png";
    private static final String IMG_BOX_ORANGE = "certs/box_orange.png";
    private static final String IMG_BOX_PURPLE = "certs/box_purple.png";
    private static final String IMG_BOX_BLUE = "certs/box_blue.png";
    private MultiCertPath m_multiCertPath;
    private String m_name;
    private Hashtable m_buttonsAndNodes;
    private CertPathInfo[] m_pathInfos;
    private MultiCertTrustGraph m_graph;
    private JAPHtmlMultiLineLabel m_lblSummary;
    static /* synthetic */ Class class$gui$MultiCertOverview;
    
    public MultiCertOverview(final Component component, final MultiCertPath multiCertPath, final String name, final boolean b) {
        super(component, JAPMessages.getString(MultiCertOverview.TITLE, (name != null) ? name : multiCertPath.getSubject().getCommonName()));
        this.m_multiCertPath = multiCertPath;
        this.m_pathInfos = this.m_multiCertPath.getPathInfos();
        this.m_graph = new MultiCertTrustGraph(this.m_pathInfos);
        if (this.m_multiCertPath.getSubject().getCommonName().startsWith("<Mix id=") && name != null) {
            this.m_name = name;
        }
        else {
            this.m_name = this.m_multiCertPath.getSubject().getCommonName();
        }
        this.m_buttonsAndNodes = new Hashtable();
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.drawOverviewPanel(b), gridBagConstraints);
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel drawSummaryPanel = this.drawSummaryPanel(b);
        tabbedPane.add(JAPMessages.getString(MultiCertOverview.SUMMARY), drawSummaryPanel);
        tabbedPane.add(JAPMessages.getString(MultiCertOverview.EXPLANATION), this.drawExplanationPanel());
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridy = 2;
        panel.add(tabbedPane, gridBagConstraints);
        this.getContentPane().add(panel);
        this.pack();
        this.finishSummaryPanel(drawSummaryPanel);
        this.setVisible(true);
    }
    
    private JPanel drawSummaryPanel(final boolean b) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final int countPaths = this.m_multiCertPath.countPaths();
        final int countVerifiedPaths = this.m_multiCertPath.countVerifiedPaths();
        final int countVerifiedAndValidPaths = this.m_multiCertPath.countVerifiedAndValidPaths();
        final String string = "<em>" + this.m_name + "</em>";
        String s;
        if (countPaths <= 1) {
            if (countVerifiedAndValidPaths == 1) {
                s = JAPMessages.getString(MultiCertOverview.MSG_NUMBER_OF_CERTS_ONE, string);
            }
            else if (countVerifiedPaths == 1) {
                s = JAPMessages.getString(MultiCertOverview.MSG_NUMBER_OF_CERTS_ONE_EXPIRED, string);
            }
            else {
                s = JAPMessages.getString(MultiCertOverview.MSG_NUMBER_OF_CERTS_ONE_NOT_TRUSTED, string);
            }
        }
        else {
            s = JAPMessages.getString(MultiCertOverview.MSG_NUMBER_OF_CERTS, new Object[] { string, new Integer(countPaths), new Integer(countVerifiedAndValidPaths) });
        }
        String s2 = s + " ";
        if (countPaths > 1) {
            if (countVerifiedPaths == 1) {
                s2 += JAPMessages.getString(MultiCertOverview.MSG_NUMBER_OF_TRUSTED_CERTS_ONE);
            }
            else {
                s2 += JAPMessages.getString(MultiCertOverview.MSG_NUMBER_OF_TRUSTED_CERTS, new Integer(countVerifiedPaths));
            }
        }
        final int countTrustedRootNodes = this.m_graph.countTrustedRootNodes();
        String s3;
        if (!b && this.m_multiCertPath.getIssuer().getOrganisation() != null) {
            s3 = this.m_multiCertPath.getIssuer().getOrganisation();
        }
        else if (b && this.m_multiCertPath.getSubject().getOrganisation() != null) {
            s3 = this.m_multiCertPath.getSubject().getOrganisation();
        }
        else {
            s3 = "";
        }
        final String string2 = "<em>" + s3 + "</em>";
        if (countTrustedRootNodes > 0) {
            final String string3 = s2 + " ";
            if (countTrustedRootNodes == 1) {
                s2 = string3 + JAPMessages.getString(MultiCertOverview.MSG_IDENTITY_ONE, string2);
            }
            else {
                s2 = string3 + JAPMessages.getString(MultiCertOverview.MSG_IDENTITY, new Object[] { string2, String.valueOf(countTrustedRootNodes) });
            }
        }
        if (countVerifiedPaths == 0) {
            this.m_lblSummary = new JAPHtmlMultiLineLabel("<font color='red'>" + s2 + "</font>");
        }
        else {
            this.m_lblSummary = new JAPHtmlMultiLineLabel(s2);
        }
        return panel;
    }
    
    private void finishSummaryPanel(final JPanel panel) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 20, 5, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(this.m_lblSummary, gridBagConstraints);
    }
    
    private JPanel drawExplanationPanel() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JLabel label = new JLabel(JAPMessages.getString(MultiCertOverview.MSG_SHOW_DETAILS));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.gridwidth = 3;
        panel.add(label, gridBagConstraints);
        final JLabel label2 = new JLabel(JAPMessages.getString(MultiCertOverview.MSG_SYMBOLS));
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        panel.add(label2, gridBagConstraints);
        final JLabel label3 = new JLabel(GUIUtils.loadImageIcon("certs/not_trusted.png", true, false));
        label3.setText(JAPMessages.getString(MultiCertOverview.MSG_NOT_TRUSTED));
        gridBagConstraints.insets.left = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        panel.add(label3, gridBagConstraints);
        final JLabel label4 = new JLabel(GUIUtils.loadImageIcon("certs/invalid.png", true, false));
        label4.setText(JAPMessages.getString(MultiCertOverview.MSG_INVALID));
        gridBagConstraints.gridy = 4;
        panel.add(label4, gridBagConstraints);
        final JLabel label5 = new JLabel(GUIUtils.loadImageIcon("certs/box_purple.png", true, false));
        label5.setText("DSA");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        panel.add(label5, gridBagConstraints);
        final JLabel label6 = new JLabel(GUIUtils.loadImageIcon("certs/box_orange.png", true, false));
        label6.setText("RSA");
        gridBagConstraints.gridy = 3;
        panel.add(label6, gridBagConstraints);
        final JLabel label7 = new JLabel(GUIUtils.loadImageIcon("certs/box_blue.png", true, false));
        label7.setText("ECC");
        gridBagConstraints.gridy = 4;
        panel.add(label7, gridBagConstraints);
        return panel;
    }
    
    private JPanel drawOverviewPanel(final boolean b) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Insets insets = new Insets(0, 0, 0, 0);
        final Insets insets2 = new Insets(10, 10, 10, 10);
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBackground(Color.white);
        final JPanel panel2 = new JPanel(new GridLayout(2, 1));
        panel2.setBackground(Color.white);
        final JLabel label = new JLabel(JAPMessages.getString(MultiCertOverview.MSG_ROOT_CERTS));
        label.setToolTipText(JAPMessages.getString(MultiCertOverview.HINT_ROOT_CERTS));
        panel2.add(label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = insets2;
        gridBagConstraints.fill = 0;
        panel.add(panel2, gridBagConstraints);
        final JPanel panel3 = new JPanel(new GridLayout(2, 1));
        panel3.setBackground(Color.white);
        JLabel label2;
        JLabel label3;
        if (b) {
            label2 = new JLabel(JAPMessages.getString(MultiCertOverview.MSG_IS_CERTS));
            final CountryMapper countryMapper = new CountryMapper(this.m_multiCertPath.getSubject().getCountryCode(), JAPMessages.getLocale());
            String s = this.m_name;
            if (s.length() > 35) {
                s = s.substring(0, 32) + "...";
            }
            label3 = new JLabel(s, GUIUtils.loadImageIcon("flags/" + countryMapper.getISOCode() + ".png", true, false), 2);
            label3.setToolTipText(JAPMessages.getString(MultiCertOverview.HINT_IS, new Object[] { this.m_name, countryMapper.toString() }));
        }
        else {
            label2 = new JLabel(JAPMessages.getString(MultiCertOverview.MSG_OP_CERTS));
            final CountryMapper countryMapper2 = new CountryMapper(this.m_multiCertPath.getIssuer().getCountryCode(), JAPMessages.getLocale());
            String s2 = this.m_multiCertPath.getIssuer().getOrganisation();
            if (s2.length() > 35) {
                s2 = s2.substring(0, 32) + "...";
            }
            label3 = new JLabel(s2, GUIUtils.loadImageIcon("flags/" + countryMapper2.getISOCode() + ".png", true, false), 2);
            label3.setToolTipText(JAPMessages.getString(MultiCertOverview.HINT_OP, new Object[] { this.m_multiCertPath.getIssuer().getOrganisation(), countryMapper2.toString() }));
        }
        panel3.add(label2);
        panel3.add(label3);
        gridBagConstraints.gridy += 2;
        panel.add(panel3, gridBagConstraints);
        if (!b) {
            final JPanel panel4 = new JPanel(new GridLayout(2, 1));
            panel4.setBackground(Color.white);
            panel4.add(new JLabel(JAPMessages.getString(MultiCertOverview.MSG_MIX_CERTS)));
            final CountryMapper countryMapper3 = new CountryMapper(this.m_multiCertPath.getSubject().getCountryCode(), JAPMessages.getLocale());
            String s3 = this.m_name;
            if (s3.length() > 35) {
                s3 = s3.substring(0, 32) + "...";
            }
            final JLabel label4 = new JLabel(s3, GUIUtils.loadImageIcon("flags/" + countryMapper3.getISOCode() + ".png", true, false), 2);
            label4.setToolTipText(JAPMessages.getString(MultiCertOverview.HINT_MIX, new Object[] { this.m_name, countryMapper3.toString() }));
            panel4.add(label4);
            gridBagConstraints.gridy += 2;
            panel.add(panel4, gridBagConstraints);
        }
        this.drawTrustGraph(panel);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints.fill = 3;
        panel.add(new JSeparator(1), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = insets;
        panel.add(new JSeparator(0), gridBagConstraints);
        for (int i = 0; i < this.m_multiCertPath.getMaxLength() - 3; ++i) {
            gridBagConstraints.gridy += 2;
            panel.add(new JSeparator(0), gridBagConstraints);
        }
        if (!b) {
            gridBagConstraints.gridy += 2;
            panel.add(new JSeparator(0), gridBagConstraints);
        }
        return panel;
    }
    
    private void drawTrustGraph(final JPanel panel) {
        int n = 2;
        final Enumeration rootNodes = this.m_graph.getRootNodes();
        while (rootNodes.hasMoreElements()) {
            n += this.drawSubGraph(panel, rootNodes.nextElement(), n, 0);
        }
        final Enumeration operatorNodes = this.m_graph.getOperatorNodes();
        while (operatorNodes.hasMoreElements()) {
            n += this.drawSubGraph(panel, operatorNodes.nextElement(), n, 2);
        }
        final Enumeration endNodes = this.m_graph.getEndNodes();
        while (endNodes.hasMoreElements()) {
            n += this.drawSubGraph(panel, endNodes.nextElement(), n, 4);
        }
    }
    
    private int drawSubGraph(final JPanel panel, final MultiCertTrustGraph.Node node, final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        if (node.hasChildNodes()) {
            final Enumeration childNodes = node.getChildNodes();
            while (childNodes.hasMoreElements()) {
                n3 += this.drawSubGraph(panel, childNodes.nextElement(), n + n4++, n2 + 2);
            }
            this.drawCertPanel(panel, n, n2, n3, node);
            for (int i = 0; i < n4; ++i) {
                final int round = Math.round(n3 / (i + 1));
                if (i + 1 == round) {
                    this.drawArrow(panel, n + i, n2 + 1, 1, node.isTrusted());
                }
                else if (i + 1 > round) {
                    this.drawArrow(panel, n + i, n2 + 1, 8, node.isTrusted());
                }
                else {
                    this.drawArrow(panel, n + i, n2 + 1, 2, node.isTrusted());
                }
            }
            return n3;
        }
        this.drawCertPanel(panel, n, n2, 1, node);
        return 1;
    }
    
    private void drawCertPanel(final JPanel panel, final int gridx, final int gridy, final int gridwidth, final MultiCertTrustGraph.Node node) {
        final JPanel panel2 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JAPCertificate certificate = node.getCertificate();
        final Color white = Color.white;
        if (certificate == null) {
            return;
        }
        final JButton button = new JButton();
        if (certificate.getPublicKey() instanceof MyRSAPublicKey) {
            if (node.isTrusted()) {
                if (certificate.getValidity().isValid(new Date())) {
                    button.setIcon(GUIUtils.loadImageIcon("certs/cert_orange_ok.png", true, false));
                    button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_orange_ok_dark.png", true, false));
                }
                else {
                    button.setIcon(GUIUtils.loadImageIcon("certs/cert_orange_invalid.png", true, false));
                    button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_orange_invalid_dark.png", true, false));
                }
            }
            else {
                button.setIcon(GUIUtils.loadImageIcon("certs/cert_orange_nok.png", true, false));
                button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_orange_nok_dark.png", true, false));
            }
        }
        else if (certificate.getPublicKey() instanceof MyECPublicKey) {
            if (node.isTrusted()) {
                if (certificate.getValidity().isValid(new Date())) {
                    button.setIcon(GUIUtils.loadImageIcon("certs/cert_blue_ok.png", true, false));
                    button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_blue_ok_dark.png", true, false));
                }
                else {
                    button.setIcon(GUIUtils.loadImageIcon("certs/cert_blue_invalid.png", true, false));
                    button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_orange_invalid_dark.png", true, false));
                }
            }
            else {
                button.setIcon(GUIUtils.loadImageIcon("certs/cert_blue_nok.png", true, false));
                button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_blue_nok_dark.png", true, false));
            }
        }
        else if (node.isTrusted()) {
            if (certificate.getValidity().isValid(new Date())) {
                button.setIcon(GUIUtils.loadImageIcon("certs/cert_purple_ok.png", true, false));
                button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_purple_ok_dark.png", true, false));
            }
            else {
                button.setIcon(GUIUtils.loadImageIcon("certs/cert_purple_invalid.png", true, false));
                button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_purple_invalid_dark.png", true, false));
            }
        }
        else {
            button.setIcon(GUIUtils.loadImageIcon("certs/cert_purple_nok.png", true, false));
            button.setRolloverIcon(GUIUtils.loadImageIcon("certs/cert_purple_nok_dark.png", true, false));
        }
        button.setToolTipText(this.getToolTipText(certificate));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(white);
        this.m_buttonsAndNodes.put(button, node);
        button.addMouseListener(this);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        panel2.add(button, gridBagConstraints);
        panel2.setBackground(white);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.anchor = 10;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 3.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridx = gridx;
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridwidth = gridwidth;
        gridBagConstraints2.insets = new Insets(5, 10, 5, 10);
        panel.add(panel2, gridBagConstraints2);
    }
    
    private void drawArrow(final JPanel panel, final int gridx, final int gridy, final int n, final boolean b) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel label;
        if (n == 1) {
            if (b) {
                label = new JLabel(GUIUtils.loadImageIcon("certs/arrow_north_ok.png", true, false));
            }
            else {
                label = new JLabel(GUIUtils.loadImageIcon("certs/arrow_north_nok.png", true, false));
            }
        }
        else if (n == 8) {
            if (b) {
                label = new JLabel(GUIUtils.loadImageIcon("certs/arrow_north_west_ok.png", true, false));
            }
            else {
                label = new JLabel(GUIUtils.loadImageIcon("certs/arrow_north_west_nok.png", true, false));
            }
        }
        else {
            if (n != 2) {
                return;
            }
            if (b) {
                label = new JLabel(GUIUtils.loadImageIcon("certs/arrow_north_east_ok.png", true, false));
            }
            else {
                label = new JLabel(GUIUtils.loadImageIcon("certs/arrow_north_east_nok.png", true, false));
            }
        }
        label.setToolTipText(JAPMessages.getString(MultiCertOverview.HINT_ARROW));
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(label, gridBagConstraints);
    }
    
    private String getToolTipText(final JAPCertificate japCertificate) {
        return JAPMessages.getString(MultiCertOverview.HINT_CERT_DETAILS, new String[] { Util.replaceAll(Util.replaceAll(japCertificate.getSubject().getCommonName(), "<", "&lt;"), ">", "&gt;"), (japCertificate.getSubject().getOrganisation() != null) ? japCertificate.getSubject().getOrganisation() : "", japCertificate.getIssuer().getCommonName(), (japCertificate.getIssuer().getOrganisation() != null) ? japCertificate.getIssuer().getOrganisation() : "", japCertificate.getValidity().isValid(new Date()) ? JAPMessages.getString(MultiCertOverview.MSG_VALID) : ("<b>" + JAPMessages.getString(MultiCertOverview.MSG_INVALID) + "</b>"), japCertificate.getValidity().getValidFrom().toString(), japCertificate.getValidity().getValidTo().toString(), japCertificate.getPublicKey().getAlgorithm(), String.valueOf(japCertificate.getPublicKey().getKeyLength()), japCertificate.getSignatureAlgorithmName() });
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.m_buttonsAndNodes.containsKey(mouseEvent.getSource())) {
            final MultiCertTrustGraph.Node node = this.m_buttonsAndNodes.get(mouseEvent.getSource());
            if (mouseEvent.getClickCount() == 1) {
                new CertDetailsDialog(this.getParentComponent(), node.getCertificate(), node.isTrusted(), JAPMessages.getLocale()).setVisible(true);
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
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
        TITLE = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_title";
        SUMMARY = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_summary";
        EXPLANATION = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_explanation";
        MSG_NUMBER_OF_CERTS_ONE = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_numberOfCertsOne";
        MSG_NUMBER_OF_CERTS_ONE_NOT_TRUSTED = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_numberOfCertsOneNotTrusted";
        MSG_NUMBER_OF_CERTS_ONE_EXPIRED = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_numberOfCertsOneExpired";
        MSG_NUMBER_OF_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_numberOfCerts";
        MSG_NUMBER_OF_TRUSTED_CERTS_ONE = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_numberOfTrustedCertsOne";
        MSG_NUMBER_OF_TRUSTED_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_numberOfTrustedCerts";
        MSG_IDENTITY_ONE = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_identityOne";
        MSG_IDENTITY = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_identity";
        MSG_SHOW_DETAILS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_details";
        MSG_SYMBOLS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_symbols";
        MSG_TRUSTED = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_trusted";
        MSG_NOT_TRUSTED = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_notTrusted";
        MSG_VALID = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_valid";
        MSG_INVALID = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_invalid";
        MSG_ROOT_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_rootCerts";
        HINT_ROOT_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_hintRootCerts";
        MSG_OP_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_opCerts";
        HINT_OP = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_hintOp";
        MSG_MIX_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_mixCerts";
        HINT_MIX = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_hintMix";
        MSG_IS_CERTS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_isCerts";
        HINT_IS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_hintIS";
        HINT_ARROW = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_hintArrow";
        HINT_CERT_DETAILS = ((MultiCertOverview.class$gui$MultiCertOverview == null) ? (MultiCertOverview.class$gui$MultiCertOverview = class$("gui.MultiCertOverview")) : MultiCertOverview.class$gui$MultiCertOverview).getName() + "_hintCertDetails";
    }
}
