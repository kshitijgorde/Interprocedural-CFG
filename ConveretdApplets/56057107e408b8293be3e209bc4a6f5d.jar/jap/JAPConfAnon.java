// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.AnonServerDescription;
import anon.infoservice.BlacklistedCascadeIDEntry;
import javax.swing.table.AbstractTableModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.AbstractButton;
import java.awt.GridLayout;
import gui.JAPJIntField;
import anon.crypto.JAPCertificate;
import anon.crypto.CertPath;
import org.w3c.dom.Node;
import java.net.MalformedURLException;
import anon.util.CountryMapper;
import anon.infoservice.ServiceSoftware;
import anon.crypto.AbstractX509AlternativeName;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import anon.infoservice.PerformanceInfo;
import anon.infoservice.StatusInfo;
import anon.infoservice.InfoServiceHolder;
import anon.infoservice.DatabaseMessage;
import jap.forward.JAPRoutingMessage;
import java.util.Observable;
import java.awt.event.KeyEvent;
import anon.util.Util;
import java.security.SignatureException;
import anon.client.TrustException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.swing.event.ListSelectionEvent;
import anon.crypto.SignatureVerifier;
import javax.swing.BorderFactory;
import gui.MapBox;
import gui.MultiCertOverview;
import gui.CertDetailsDialog;
import java.net.URL;
import platform.AbstractOS;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.PreviouslyKnownCascadeIDEntry;
import anon.infoservice.Database;
import javax.swing.JMenuItem;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.DataRetentionInformation;
import anon.infoservice.ServiceLocation;
import anon.infoservice.ServiceOperator;
import java.util.Date;
import gui.MixDetailsDialog;
import java.awt.event.ItemEvent;
import anon.infoservice.PerformanceEntry;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import java.awt.Cursor;
import gui.DataRetentionDialog;
import javax.swing.Icon;
import gui.GUIUtils;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellRenderer;
import java.awt.Dimension;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ListCellRenderer;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Component;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import anon.AnonServiceEventListener;
import anon.client.TrustModel;
import java.util.Vector;
import anon.infoservice.MixCascade;
import anon.infoservice.MixInfo;
import anon.crypto.MultiCertPath;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.util.Observer;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

class JAPConfAnon extends AbstractJAPConfModule implements MouseListener, ActionListener, ListSelectionListener, ItemListener, KeyListener, Observer
{
    private static final String MSG_X_OF_Y_CERTS_TRUSTED;
    private static final String MSG_REALLY_DELETE;
    private static final String MSG_MIX_VERSION;
    private static final String MSG_MIX_ID;
    private static final String MSG_BUTTONEDITSHOW;
    private static final String MSG_PAYCASCADE;
    private static final String MSG_MIX_POSITION;
    private static final String MSG_OF_THE_SERVICE;
    private static final String MSG_MIX_FIRST;
    private static final String MSG_MIX_SINGLE;
    private static final String MSG_MIX_MIDDLE;
    private static final String MSG_MIX_LAST;
    private static final String MSG_SHOW_ON_MAP;
    private static final String MSG_EXPLAIN_MIX_TT;
    private static final String MSG_FIRST_MIX_TEXT;
    private static final String MSG_SINGLE_MIX_TEXT;
    private static final String MSG_MIDDLE_MIX_TEXT;
    private static final String MSG_LAST_MIX_TEXT;
    private static final String MSG_NOT_TRUSTWORTHY;
    private static final String MSG_EXPLAIN_NOT_TRUSTWORTHY;
    private static final String MSG_EXPLAIN_BLACKLISTED;
    private static final String MSG_EXPLAIN_PI_UNAVAILABLE;
    private static final String MSG_EXPLAIN_NO_CASCADES;
    private static final String MSG_EXPLAIN_CURRENT_CASCADE_NOT_TRUSTED;
    private static final String MSG_WHAT_IS_THIS;
    private static final String MSG_FILTER;
    private static final String MSG_FILTER_CANCEL = "cancelButton";
    private static final String MSG_EDIT_FILTER;
    private static final String MSG_ANON_LEVEL;
    private static final String MSG_SUPPORTS_SOCKS;
    private static final String MSG_FILTER_PAYMENT;
    private static final String MSG_FILTER_CASCADES;
    private static final String MSG_FILTER_INTERNATIONALITY;
    private static final String MSG_FILTER_OPERATORS;
    private static final String MSG_FILTER_SPEED;
    private static final String MSG_FILTER_LATENCY;
    private static final String MSG_FILTER_ALL;
    private static final String MSG_FILTER_PAYMENT_ONLY;
    private static final String MSG_FILTER_PREMIUM_PRIVATE_ONLY;
    private static final String MSG_FILTER_BUSINESS_ONLY;
    private static final String MSG_FILTER_NO_PAYMENT_ONLY;
    private static final String MSG_FILTER_AT_LEAST_3_MIXES;
    private static final String MSG_FILTER_AT_LEAST_2_MIXES;
    private static final String MSG_FILTER_AT_LEAST_2_COUNTRIES;
    private static final String MSG_FILTER_AT_LEAST_3_COUNTRIES;
    private static final String MSG_FILTER_AT_LEAST;
    private static final String MSG_FILTER_AT_MOST;
    private static final String MSG_FILTER_SELECT_ALL_OPERATORS;
    private static final String MSG_FILTER_OTHER;
    private static final String MSG_FILTER_SOCKS_ONLY;
    private static final String MSG_FILTER_NO_DATA_RETENTION;
    private static final String MSG_CONNECTED;
    private static final String MSG_LBL_AVAILABILITY;
    private static final String MSG_USER_LIMIT;
    private static final String MSG_UNSTABLE;
    private static final String MSG_HARDLY_REACHABLE;
    private static final String MSG_UNREACHABLE;
    private static final String MSG_BAD_AVAILABILITY;
    private static final String MSG_GOOD_AVAILABILITY;
    private static final int FILTER_SPEED_MAJOR_TICK = 100;
    private static final int FILTER_SPEED_MAX = 400;
    private static final int FILTER_SPEED_STEPS = 5;
    private static final int FILTER_LATENCY_STEPS = 5;
    private static final int FILTER_LATENCY_MAJOR_TICK = 1000;
    private static final int FILTER_LATENCY_MAX = 5000;
    private boolean m_bUpdateServerPanel;
    JComboBox m_cmbCascadeFilter;
    private JList m_listMixCascade;
    private JTable m_tableMixCascade;
    private JTable m_listOperators;
    private ServerListPanel m_serverList;
    private JPanel pRoot;
    private JPanel m_cascadesPanel;
    private ServerPanel m_serverPanel;
    private JPanel m_serverInfoPanel;
    private ManualPanel m_manualPanel;
    private FilterPanel m_filterPanel;
    private JLabel m_lblSpeed;
    private JLabel m_lblDelay;
    private JLabel m_anonLevelLabel;
    private JLabel m_numOfUsersLabel;
    private JLabel m_lblVDS;
    private JLabel m_lblSocks;
    private GridBagLayout m_rootPanelLayout;
    private GridBagConstraints m_rootPanelConstraints;
    private JLabel m_lblMix;
    private JLabel m_lblMixOfService;
    private JPanel m_nrPanel;
    private JLabel m_nrLabel;
    private JPanel m_pnlMixInfoButtons;
    private JLabel m_operatorLabel;
    private JButton m_btnEmail;
    private JButton m_btnHomepage;
    private JButton m_btnMap;
    private JButton m_btnDataRetention;
    private JButton m_moveMixLeft;
    private JButton m_moveMixRight;
    private JLabel m_locationLabel;
    private JLabel m_lblAvailability;
    private boolean m_blacklist;
    private boolean m_unknownPI;
    private JButton m_btnViewCert;
    private JButton m_manualCascadeButton;
    private JButton m_reloadCascadesButton;
    private JButton m_selectCascadeButton;
    private JButton m_editCascadeButton;
    private JButton m_deleteCascadeButton;
    private JButton m_cancelCascadeButton;
    private JButton m_showEditPanelButton;
    private JButton m_showEditFilterButton;
    private JPopupMenu m_opPopupMenu;
    private JTextField m_manHostField;
    private JTextField m_manPortField;
    private JSlider m_filterSpeedSlider;
    private JSlider m_filterLatencySlider;
    private JRadioButton m_filterAllCountries;
    private JRadioButton m_filterAtLeast2Countries;
    private JRadioButton m_filterAtLeast3Countries;
    private JRadioButton m_filterAllMixes;
    private JRadioButton m_filterAtLeast2Mixes;
    private JRadioButton m_filterAtLeast3Mixes;
    private JTextField m_filterNameField;
    private JCheckBox m_cbxSocks5;
    private JCheckBox m_cbxDataRetention;
    private JCheckBox m_cbxFreeOfCharge;
    private ButtonGroup m_filterCascadeGroup;
    private ButtonGroup m_filterInternationalGroup;
    private boolean mb_backSpacePressed;
    private boolean mb_manualCascadeNew;
    private String m_oldCascadeHost;
    private String m_oldCascadePort;
    private boolean m_bMixInfoShown;
    private boolean m_mapShown;
    private MultiCertPath m_serverCertPaths;
    private MixInfo m_serverInfo;
    private MixCascade m_cascadeInfo;
    private Vector m_locationCoordinates;
    private TrustModel m_previousTrustModel;
    private TrustModel m_trustModelCopy;
    static /* synthetic */ Class class$jap$JAPConfAnon;
    static /* synthetic */ Class class$anon$client$TrustModel$SocksAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$DataRetentionAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$PremiumAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$NumberOfMixesAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$InternationalAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$SpeedAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$DelayAttribute;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$PreviouslyKnownCascadeIDEntry;
    static /* synthetic */ Class class$anon$client$TrustModel$OperatorBlacklistAttribute;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    static /* synthetic */ Class class$anon$infoservice$MixInfo;
    static /* synthetic */ Class class$anon$infoservice$PerformanceInfo;
    
    protected JAPConfAnon(final IJAPConfSavePoint ijapConfSavePoint) {
        super(null);
        this.m_bUpdateServerPanel = true;
        this.m_bMixInfoShown = false;
        this.m_mapShown = false;
        JAPController.getInstance().addEventListener(new LocalAnonServiceEventListener());
    }
    
    public void recreateRootPanel() {
        this.m_lblMix = new JLabel();
        this.m_lblMixOfService = new JLabel();
        this.m_lblMix.setForeground(Color.blue);
        this.m_lblMix.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (JAPConfAnon.this.m_bMixInfoShown || JAPConfAnon.this.m_lblMix.getText().trim().length() == 0) {
                    return;
                }
                JAPConfAnon.this.m_bMixInfoShown = true;
                if (JAPConfAnon.this.m_serverList.getSelectedIndex() == 0) {
                    if (JAPConfAnon.this.m_serverList.getNumberOfMixes() == 1) {
                        JAPDialog.showMessageDialog(JAPConfAnon.this.getRootPanel(), JAPMessages.getString(JAPConfAnon.MSG_SINGLE_MIX_TEXT), JAPMessages.getString(JAPConfAnon.MSG_MIX_SINGLE));
                    }
                    else {
                        JAPDialog.showMessageDialog(JAPConfAnon.this.getRootPanel(), JAPMessages.getString(JAPConfAnon.MSG_FIRST_MIX_TEXT), JAPMessages.getString(JAPConfAnon.MSG_MIX_FIRST));
                    }
                }
                else if (JAPConfAnon.this.m_serverList.getSelectedIndex() == JAPConfAnon.this.m_serverList.getNumberOfMixes() - 1) {
                    JAPDialog.showMessageDialog(JAPConfAnon.this.getRootPanel(), JAPMessages.getString(JAPConfAnon.MSG_LAST_MIX_TEXT), JAPMessages.getString(JAPConfAnon.MSG_MIX_LAST));
                }
                else {
                    JAPDialog.showMessageDialog(JAPConfAnon.this.getRootPanel(), JAPMessages.getString(JAPConfAnon.MSG_MIDDLE_MIX_TEXT), JAPMessages.getString(JAPConfAnon.MSG_MIX_MIDDLE));
                }
                JAPConfAnon.this.m_bMixInfoShown = false;
            }
        });
        this.drawCompleteDialog();
    }
    
    private void drawServerPanel(final int n, final String cascadeName, final boolean b, final int n2) {
        if (this.m_manualPanel != null && this.m_manualPanel.isVisible()) {
            this.m_manualPanel.setVisible(false);
        }
        if (this.m_filterPanel != null && this.m_filterPanel.isVisible()) {
            this.m_filterPanel.setVisible(false);
        }
        if (this.m_serverPanel == null) {
            this.m_serverPanel = new ServerPanel(this);
            this.m_rootPanelConstraints.gridx = 0;
            this.m_rootPanelConstraints.gridy = 2;
            this.m_rootPanelConstraints.weightx = 1.0;
            this.m_rootPanelConstraints.weighty = 0.0;
            this.m_rootPanelConstraints.anchor = 18;
            this.m_rootPanelConstraints.fill = 1;
            this.pRoot.add(this.m_serverPanel, this.m_rootPanelConstraints);
        }
        else if (!this.m_serverPanel.isVisible()) {
            this.m_serverPanel.setVisible(true);
        }
        this.m_serverPanel.setCascadeName(cascadeName);
        this.m_serverPanel.updateServerList(n, b, n2);
        this.pRoot.validate();
    }
    
    private void drawServerInfoPanel() {
        if (this.m_manualPanel != null) {
            this.m_manualPanel.setVisible(false);
        }
        if (this.m_filterPanel != null) {
            this.m_filterPanel.setVisible(false);
        }
        if (this.m_serverInfoPanel == null) {
            this.m_serverInfoPanel = new ServerInfoPanel(this);
            this.m_rootPanelConstraints.anchor = 18;
            this.m_rootPanelConstraints.gridx = 0;
            this.m_rootPanelConstraints.gridy = 3;
            this.m_rootPanelConstraints.weightx = 1.0;
            this.m_rootPanelConstraints.weighty = 0.0;
            this.m_rootPanelConstraints.fill = 1;
            this.pRoot.add(this.m_serverInfoPanel, this.m_rootPanelConstraints);
        }
        else if (!this.m_serverInfoPanel.isVisible()) {
            this.m_serverInfoPanel.setVisible(true);
        }
    }
    
    private void drawManualPanel(final String hostName, final String port) {
        if (this.m_serverPanel != null) {
            this.m_serverPanel.setVisible(false);
            this.m_serverInfoPanel.setVisible(false);
        }
        if (this.m_filterPanel != null) {
            this.m_filterPanel.setVisible(false);
        }
        if (this.m_manualPanel == null) {
            this.m_manualPanel = new ManualPanel(this);
            this.m_rootPanelConstraints.gridx = 0;
            this.m_rootPanelConstraints.gridy = 2;
            this.m_rootPanelConstraints.weightx = 0.0;
            this.m_rootPanelConstraints.weighty = 1.0;
            this.m_rootPanelConstraints.anchor = 18;
            this.m_rootPanelConstraints.fill = 1;
            this.pRoot.add(this.m_manualPanel, this.m_rootPanelConstraints);
        }
        this.m_manualPanel.setHostName(hostName);
        this.m_manualPanel.setPort(port);
        this.m_manualPanel.setVisible(true);
        this.pRoot.validate();
    }
    
    private void drawFilterPanel() {
        if (this.m_serverPanel != null) {
            this.m_serverPanel.setVisible(false);
            this.m_serverInfoPanel.setVisible(false);
        }
        if (this.m_manualPanel != null) {
            this.m_manualPanel.setVisible(false);
        }
        this.m_trustModelCopy = new TrustModel(TrustModel.getCurrentTrustModel());
        if (this.m_filterPanel == null) {
            this.m_filterPanel = new FilterPanel(this);
            this.m_rootPanelConstraints.anchor = 14;
            this.m_rootPanelConstraints.gridx = 0;
            this.m_rootPanelConstraints.gridy = 3;
            this.m_rootPanelConstraints.weightx = 1.0;
            this.m_rootPanelConstraints.weighty = 0.5;
            this.m_rootPanelConstraints.fill = 1;
            this.pRoot.add(this.m_filterPanel, this.m_rootPanelConstraints);
        }
        if (this.m_trustModelCopy != null) {
            this.m_filterNameField.setText(this.m_trustModelCopy.getName());
            final boolean selected = this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$SocksAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$SocksAttribute = class$("anon.client.TrustModel$SocksAttribute")) : JAPConfAnon.class$anon$client$TrustModel$SocksAttribute).getTrustCondition() == 2;
            if (selected != this.m_cbxSocks5.isSelected()) {
                this.m_cbxSocks5.setSelected(selected);
            }
            final boolean selected2 = this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$DataRetentionAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$DataRetentionAttribute = class$("anon.client.TrustModel$DataRetentionAttribute")) : JAPConfAnon.class$anon$client$TrustModel$DataRetentionAttribute).getTrustCondition() == 1;
            if (selected2 != this.m_cbxDataRetention.isSelected()) {
                this.m_cbxDataRetention.setSelected(selected2);
            }
            final boolean selected3 = this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$PremiumAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : JAPConfAnon.class$anon$client$TrustModel$PremiumAttribute).getTrustCondition() == 1;
            if (selected3 != this.m_cbxFreeOfCharge.isSelected()) {
                this.m_cbxFreeOfCharge.setSelected(selected3);
            }
            final int trustCondition = this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute = class$("anon.client.TrustModel$NumberOfMixesAttribute")) : JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute).getTrustCondition();
            final Integer n = (Integer)this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute = class$("anon.client.TrustModel$NumberOfMixesAttribute")) : JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute).getConditionValue();
            this.m_filterPanel.selectRadioButton(this.m_filterCascadeGroup, String.valueOf(trustCondition));
            if (n != null) {
                if (trustCondition == 3 && n == 0) {
                    this.m_filterCascadeGroup.setSelected(this.m_filterAllMixes.getModel(), true);
                }
                else if (trustCondition == 3 && n == 2) {
                    this.m_filterCascadeGroup.setSelected(this.m_filterAtLeast2Mixes.getModel(), true);
                }
                else if (trustCondition == 3 && n == 3) {
                    this.m_filterCascadeGroup.setSelected(this.m_filterAtLeast3Mixes.getModel(), true);
                }
            }
            final int trustCondition2 = this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute = class$("anon.client.TrustModel$InternationalAttribute")) : JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute).getTrustCondition();
            final Integer n2 = (Integer)this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute = class$("anon.client.TrustModel$InternationalAttribute")) : JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute).getConditionValue();
            this.m_filterPanel.selectRadioButton(this.m_filterInternationalGroup, String.valueOf(trustCondition2));
            if (n2 != null) {
                if (trustCondition2 == 3 && n2 == 0) {
                    this.m_filterInternationalGroup.setSelected(this.m_filterAllCountries.getModel(), true);
                }
                else if (trustCondition2 == 3 && n2 == 2) {
                    this.m_filterAllMixes.setEnabled(false);
                    this.m_filterInternationalGroup.setSelected(this.m_filterAtLeast2Countries.getModel(), true);
                }
                else if (trustCondition2 == 3 && n2 == 3) {
                    this.m_filterAtLeast2Mixes.setEnabled(false);
                    this.m_filterInternationalGroup.setSelected(this.m_filterAtLeast3Countries.getModel(), true);
                }
            }
            this.m_filterSpeedSlider.setValue((int)this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute = class$("anon.client.TrustModel$SpeedAttribute")) : JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute).getConditionValue() / 100);
            this.m_filterLatencySlider.setValue(this.convertDelayValue((int)this.m_trustModelCopy.getAttribute((JAPConfAnon.class$anon$client$TrustModel$DelayAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$DelayAttribute = class$("anon.client.TrustModel$DelayAttribute")) : JAPConfAnon.class$anon$client$TrustModel$DelayAttribute).getConditionValue(), false));
            ((OperatorsTableModel)this.m_listOperators.getModel()).update();
        }
        this.m_showEditFilterButton.setText(JAPMessages.getString("cancelButton"));
        this.m_filterPanel.setVisible(true);
        this.pRoot.validate();
    }
    
    private void drawCascadesPanel() {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        if (this.m_cascadesPanel != null) {
            this.m_cascadesPanel.removeAll();
        }
        else {
            this.m_cascadesPanel = new JPanel();
        }
        this.m_cascadesPanel.setLayout(layout);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = 12;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 0.2;
        this.m_cmbCascadeFilter = new JComboBox(TrustModel.getTrustModels());
        final JLabel label = new JLabel();
        label.setOpaque(true);
        this.m_cmbCascadeFilter.setRenderer(new ListCellRenderer() {
            public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
                if (b) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                }
                else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }
                if (TrustModel.getCurrentTrustModel() == o) {
                    label.setFont(new Font(list.getFont().getName(), 1, list.getFont().getSize()));
                }
                else {
                    label.setFont(new Font(list.getFont().getName(), 0, list.getFont().getSize()));
                }
                if (o == null) {
                    label.setText("");
                }
                else {
                    label.setText(o.toString());
                }
                return label;
            }
        });
        this.m_cmbCascadeFilter.setSelectedItem(TrustModel.getCurrentTrustModel());
        this.m_cmbCascadeFilter.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (TrustModel.getCurrentTrustModel() == null || !TrustModel.getCurrentTrustModel().equals(JAPConfAnon.this.m_cmbCascadeFilter.getSelectedItem())) {
                    TrustModel.setCurrentTrustModel((TrustModel)JAPConfAnon.this.m_cmbCascadeFilter.getSelectedItem());
                    JAPConfAnon.this.updateValues(false);
                    if (JAPConfAnon.this.m_filterPanel != null && JAPConfAnon.this.m_filterPanel.isVisible()) {
                        JAPConfAnon.this.hideEditFilter();
                    }
                }
            }
        });
        this.m_cascadesPanel.add(this.m_cmbCascadeFilter, gridBagConstraints);
        (this.m_showEditFilterButton = new JButton(JAPMessages.getString(JAPConfAnon.MSG_EDIT_FILTER))).addActionListener(this);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = 18;
        this.m_cascadesPanel.add(this.m_showEditFilterButton, gridBagConstraints);
        this.m_listMixCascade = new JList();
        (this.m_tableMixCascade = new JTable()).setModel(new MixCascadeTableModel());
        this.m_tableMixCascade.setTableHeader(null);
        this.m_tableMixCascade.setIntercellSpacing(new Dimension(0, 0));
        this.m_tableMixCascade.setShowGrid(false);
        this.m_tableMixCascade.setSelectionMode(0);
        this.m_tableMixCascade.getColumnModel().getColumn(0).setMaxWidth(1);
        this.m_tableMixCascade.getColumnModel().getColumn(0).setPreferredWidth(1);
        this.m_tableMixCascade.getColumnModel().getColumn(1).setCellRenderer(new MixCascadeCellRenderer());
        this.m_tableMixCascade.addMouseListener(this);
        this.m_tableMixCascade.getSelectionModel().addListSelectionListener(this);
        this.m_listMixCascade.setFixedCellWidth(30);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        final JScrollPane scrollPane = new JScrollPane(this.m_listMixCascade);
        scrollPane.setHorizontalScrollBarPolicy(31);
        final Dimension preferredSize = scrollPane.getPreferredSize();
        final JScrollPane scrollPane2 = new JScrollPane(this.m_tableMixCascade);
        scrollPane2.setHorizontalScrollBarPolicy(31);
        scrollPane2.setPreferredSize(preferredSize);
        this.m_cascadesPanel.add(scrollPane2, gridBagConstraints);
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = 3;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 10);
        (this.m_reloadCascadesButton = new JButton(JAPMessages.getString("reloadCascades"))).setIcon(GUIUtils.loadImageIcon("reload.gif", true, false));
        this.m_reloadCascadesButton.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
        this.m_reloadCascadesButton.setPressedIcon(GUIUtils.loadImageIcon("reloadrollover.gif", true, false));
        this.m_reloadCascadesButton.addActionListener(this);
        panel.add(this.m_reloadCascadesButton, gridBagConstraints2);
        (this.m_selectCascadeButton = new JButton(JAPMessages.getString("selectCascade"))).setEnabled(!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder());
        this.m_selectCascadeButton.addActionListener(this);
        gridBagConstraints2.gridx = 1;
        panel.add(this.m_selectCascadeButton, gridBagConstraints2);
        (this.m_manualCascadeButton = new JButton(JAPMessages.getString("manualCascade"))).addActionListener(this);
        gridBagConstraints2.gridx = 2;
        panel.add(this.m_manualCascadeButton, gridBagConstraints2);
        (this.m_showEditPanelButton = new JButton(JAPMessages.getString(JAPConfAnon.MSG_BUTTONEDITSHOW))).addActionListener(this);
        gridBagConstraints2.gridx = 3;
        panel.add(this.m_showEditPanelButton, gridBagConstraints2);
        (this.m_deleteCascadeButton = new JButton(JAPMessages.getString("manualServiceDelete"))).addActionListener(this);
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.weightx = 1.0;
        panel.add(this.m_deleteCascadeButton, gridBagConstraints2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        this.m_cascadesPanel.add(panel, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 20, 0, 5);
        final JLabel label2 = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_ANON_LEVEL) + ":");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        this.m_cascadesPanel.add(label2, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.m_anonLevelLabel = new JLabel("");
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(this.m_anonLevelLabel, gridBagConstraints);
        (this.m_lblVDS = new JLabel()).setIcon(GUIUtils.loadImageIcon("certs/invalid.png", true));
        this.m_lblVDS.setToolTipText(JAPMessages.getString(DataRetentionDialog.MSG_DATA_RETENTION_EXPLAIN_SHORT));
        this.m_lblVDS.setForeground(Color.red);
        this.m_lblVDS.setCursor(Cursor.getPredefinedCursor(12));
        this.m_lblVDS.addMouseListener(this);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        this.m_cascadesPanel.add(this.m_lblVDS, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 20, 0, 5);
        final JLabel label3 = new JLabel(JAPMessages.getString("numOfUsersOnCascade") + ":");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        this.m_cascadesPanel.add(label3, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.m_numOfUsersLabel = new JLabel("");
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(this.m_numOfUsersLabel, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 20, 0, 5);
        final JLabel label4 = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER_SPEED) + ":");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(label4, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 5, 0, 0);
        this.m_lblSpeed = new JLabel("");
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(this.m_lblSpeed, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 20, 0, 5);
        final JLabel label5 = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER_LATENCY) + ":");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(label5, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 5, 0, 0);
        this.m_lblDelay = new JLabel("");
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(this.m_lblDelay, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 20, 0, 5);
        final JLabel label6 = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_LBL_AVAILABILITY) + ":");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 2;
        this.m_cascadesPanel.add(label6, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 5, 0, 0);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        (this.m_lblAvailability = new JLabel("")).addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (JAPConfAnon.this.m_lblAvailability.getCursor() != Cursor.getDefaultCursor() && JAPConfAnon.this.m_lblAvailability.getForeground() == Color.red) {
                    if (JAPConfAnon.this.m_blacklist) {
                        JAPDialog.showMessageDialog(JAPConfAnon.this.m_lblAvailability, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_BLACKLISTED));
                    }
                    else if (JAPConfAnon.this.m_unknownPI) {
                        JAPDialog.showMessageDialog(JAPConfAnon.this.m_lblAvailability, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_PI_UNAVAILABLE));
                    }
                    else {
                        JAPDialog.showMessageDialog(JAPConfAnon.this.m_lblAvailability, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_NOT_TRUSTWORTHY, TrustModel.getCurrentTrustModel().getName()), new JAPDialog.LinkedHelpContext("services_anon"));
                    }
                }
            }
        });
        this.m_cascadesPanel.add(this.m_lblAvailability, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 20, 0, 5);
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 4;
        (this.m_lblSocks = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_SUPPORTS_SOCKS))).setIcon(GUIUtils.loadImageIcon("socks_icon.gif", true));
        this.m_cascadesPanel.add(this.m_lblSocks, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridy = 7;
        this.m_cascadesPanel.add(new JLabel("                                               "), gridBagConstraints);
        this.m_rootPanelConstraints.gridx = 0;
        this.m_rootPanelConstraints.gridy = 0;
        this.m_rootPanelConstraints.insets = new Insets(10, 10, 0, 10);
        this.m_rootPanelConstraints.anchor = 18;
        this.m_rootPanelConstraints.fill = 1;
        this.m_rootPanelConstraints.weightx = 1.0;
        this.m_rootPanelConstraints.weighty = 1.0;
        this.pRoot.add(this.m_cascadesPanel, this.m_rootPanelConstraints);
        this.m_rootPanelConstraints.weightx = 1.0;
        this.m_rootPanelConstraints.weighty = 0.0;
        final JSeparator separator = new JSeparator();
        this.m_rootPanelConstraints.gridy = 1;
        this.m_rootPanelConstraints.fill = 2;
        this.pRoot.add(separator, this.m_rootPanelConstraints);
    }
    
    private void drawCompleteDialog() {
        this.m_rootPanelLayout = new GridBagLayout();
        this.m_rootPanelConstraints = new GridBagConstraints();
        this.m_cascadesPanel = null;
        this.m_serverPanel = null;
        this.m_serverInfoPanel = null;
        this.m_manualPanel = null;
        (this.pRoot = this.getRootPanel()).removeAll();
        this.pRoot.setLayout(this.m_rootPanelLayout);
        if (JAPModel.getDefaultView() == 2) {
            this.pRoot.setBorder(new TitledBorder(JAPMessages.getString("confAnonTab")));
        }
        this.m_rootPanelConstraints.anchor = 18;
        this.drawManualPanel("", "");
        this.drawCascadesPanel();
        this.drawFilterPanel();
        this.drawServerPanel(3, "", false, 0);
        this.drawServerInfoPanel();
        this.hideEditFilter();
    }
    
    private void setAvailabilityLabel(final MixCascade mixCascade, final PerformanceEntry performanceEntry) {
        final StringBuffer sb = new StringBuffer();
        final PerformanceEntry.StabilityAttributes stabilityAttributes = performanceEntry.getStabilityAttributes();
        final MixCascade connectedCascade = JAPController.getInstance().getConnectedCascade();
        if (connectedCascade != null && connectedCascade.equals(mixCascade)) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(this.m_anonLevelLabel.getForeground());
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_CONNECTED));
            this.m_lblAvailability.setToolTipText(null);
        }
        else if (!TrustModel.getCurrentTrustModel().isTrusted(mixCascade, sb)) {
            this.m_lblAvailability.setForeground(Color.red);
            this.m_lblAvailability.setCursor(Cursor.getPredefinedCursor(12));
            if (TrustModel.isBlacklisted(mixCascade)) {
                this.m_lblAvailability.setText(JAPMessages.getString(TrustModel.MSG_BLACKLISTED));
                this.m_lblAvailability.setToolTipText(JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_BLACKLISTED, TrustModel.getCurrentTrustModel().getName()));
                this.m_blacklist = true;
                this.m_unknownPI = false;
            }
            else if (TrustModel.isNoPaymentInstanceFound(mixCascade)) {
                this.m_lblAvailability.setText(JAPMessages.getString(TrustModel.MSG_PI_UNAVAILABLE));
                this.m_lblAvailability.setToolTipText(JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_PI_UNAVAILABLE, TrustModel.getCurrentTrustModel().getName()));
                this.m_blacklist = false;
                this.m_unknownPI = true;
            }
            else {
                this.m_lblAvailability.setText(sb.toString());
                this.m_lblAvailability.setToolTipText("<html>" + JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_NOT_TRUSTWORTHY, TrustModel.getCurrentTrustModel().getName()) + "</html>");
                this.m_blacklist = false;
                this.m_unknownPI = false;
            }
        }
        else if (stabilityAttributes.getBoundUnknown() + stabilityAttributes.getBoundErrors() > 75) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(Color.red);
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_UNREACHABLE));
            this.m_lblAvailability.setToolTipText(null);
        }
        else if (InfoServiceTempLayer.isUserLimitReached(mixCascade)) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(Color.red);
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_USER_LIMIT));
            this.m_lblAvailability.setToolTipText(null);
        }
        else if (stabilityAttributes.getBoundUnknown() + stabilityAttributes.getBoundErrors() > 25) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(Color.red);
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_HARDLY_REACHABLE));
            this.m_lblAvailability.setToolTipText(null);
        }
        else if (stabilityAttributes.getBoundResets() > 5 || stabilityAttributes.getBoundErrors() > 10) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(Color.red);
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_UNSTABLE));
            this.m_lblAvailability.setToolTipText(null);
        }
        else if (stabilityAttributes.getBoundUnknown() + stabilityAttributes.getBoundErrors() > 5) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(this.m_anonLevelLabel.getForeground());
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_BAD_AVAILABILITY));
            this.m_lblAvailability.setToolTipText(null);
        }
        else if (stabilityAttributes.getValueSize() == 0) {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(this.m_anonLevelLabel.getForeground());
            this.m_lblAvailability.setToolTipText(null);
            this.m_lblAvailability.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
        }
        else {
            this.m_lblAvailability.setCursor(Cursor.getDefaultCursor());
            this.m_lblAvailability.setForeground(this.m_anonLevelLabel.getForeground());
            this.m_lblAvailability.setToolTipText(null);
            this.m_lblAvailability.setText(JAPMessages.getString(JAPConfAnon.MSG_GOOD_AVAILABILITY));
        }
    }
    
    public synchronized void itemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.m_serverList.getSelectedIndex();
        final MixCascade cascadeInfo = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
        this.m_cascadeInfo = cascadeInfo;
        String s = null;
        if (cascadeInfo != null) {
            s = cascadeInfo.getMixIds().elementAt(selectedIndex);
        }
        if (s != null) {
            final String trim = GUIUtils.trim(InfoServiceTempLayer.getMixVersion(cascadeInfo, s));
            String string;
            if (trim != null) {
                string = ", " + JAPMessages.getString(JAPConfAnon.MSG_MIX_VERSION) + "=" + trim;
            }
            else {
                string = "";
            }
            this.m_nrLabel.setToolTipText(JAPMessages.getString(JAPConfAnon.MSG_MIX_ID) + "=" + s + string);
            final String trim2 = GUIUtils.trim(InfoServiceTempLayer.getName(cascadeInfo, s), 80);
            if (trim2 == null) {
                this.m_nrLabel.setText("N/A");
            }
            else {
                this.m_nrLabel.setText(trim2);
            }
        }
        else {
            this.m_nrLabel.setToolTipText("");
        }
        if (this.m_serverList.areMixButtonsEnabled()) {
            this.m_lblMix.setText(JAPMessages.getString(MixDetailsDialog.MSG_MIX_X_OF_Y, new Object[] { new Integer(selectedIndex + 1), new Integer(this.m_serverList.getNumberOfMixes()) }));
            if (cascadeInfo != null) {
                this.m_lblMixOfService.setText(JAPMessages.getString(JAPConfAnon.MSG_OF_THE_SERVICE, "\"" + cascadeInfo.getName() + "\""));
            }
            else {
                this.m_lblMixOfService.setText("");
            }
            this.m_moveMixLeft.setVisible(true);
            this.m_moveMixRight.setVisible(true);
            this.m_lblMix.setCursor(Cursor.getPredefinedCursor(12));
            this.m_lblMix.setToolTipText(JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_MIX_TT));
        }
        else {
            this.m_lblMix.setCursor(Cursor.getPredefinedCursor(0));
            this.m_lblMix.setToolTipText(null);
            this.m_lblMix.setText(" ");
            this.m_lblMixOfService.setText("");
            this.m_moveMixLeft.setVisible(false);
            this.m_moveMixRight.setVisible(false);
        }
        if (cascadeInfo != null) {
            for (int n = 0; n < this.m_serverList.getNumberOfMixes() && n < cascadeInfo.getMixIds().size(); ++n) {
                final String s2 = cascadeInfo.getMixIds().elementAt(n);
                this.m_serverList.update(n, getServiceOperator(cascadeInfo, s2), getServiceLocation(cascadeInfo, s2));
            }
        }
        this.m_operatorLabel.setText(GUIUtils.trim(InfoServiceTempLayer.getOperator(cascadeInfo, s)));
        this.m_operatorLabel.setToolTipText(this.m_operatorLabel.getText());
        final ServiceOperator access$2300 = getServiceOperator(cascadeInfo, s);
        if (access$2300 != null && access$2300.getCountryCode() != null) {
            this.m_operatorLabel.setIcon(GUIUtils.loadImageIcon("flags/" + access$2300.getCountryCode() + ".png"));
        }
        else {
            this.m_operatorLabel.setIcon(null);
        }
        this.m_btnHomepage.setToolTipText(InfoServiceTempLayer.getUrl(cascadeInfo, s));
        if (getUrlFromLabel(this.m_btnHomepage) != null) {
            this.m_btnHomepage.setEnabled(true);
        }
        else {
            this.m_btnHomepage.setEnabled(false);
        }
        this.m_btnEmail.setToolTipText(GUIUtils.trim(InfoServiceTempLayer.getEMail(cascadeInfo, s)));
        if (getEMailFromLabel(this.m_btnEmail) != null) {
            this.m_btnEmail.setEnabled(true);
        }
        else {
            this.m_btnEmail.setEnabled(false);
        }
        this.m_locationCoordinates = InfoServiceTempLayer.getCoordinates(cascadeInfo, s);
        this.m_locationLabel.setText(GUIUtils.trim(InfoServiceTempLayer.getLocation(cascadeInfo, s)));
        this.m_btnMap.setToolTipText(GUIUtils.trim(InfoServiceTempLayer.getLocation(cascadeInfo, s)));
        if (this.m_locationCoordinates != null) {
            this.m_btnMap.setEnabled(true);
        }
        else {
            this.m_btnMap.setEnabled(false);
        }
        final ServiceLocation access$2301 = getServiceLocation(cascadeInfo, s);
        if (access$2301 != null) {
            this.m_locationLabel.setIcon(GUIUtils.loadImageIcon("flags/" + access$2301.getCountryCode() + ".png"));
        }
        else {
            this.m_locationLabel.setIcon(null);
        }
        this.m_locationLabel.setToolTipText(InfoServiceTempLayer.getLocation(cascadeInfo, s));
        this.m_serverInfo = getMixInfo(cascadeInfo, s);
        this.m_serverCertPaths = InfoServiceTempLayer.getMixCertPath(cascadeInfo, s);
        if (this.m_serverCertPaths != null && this.m_serverInfo != null) {
            final boolean serverCertVerified = this.isServerCertVerified();
            final boolean valid = this.m_serverCertPaths.isValid(new Date());
            if (!serverCertVerified) {
                this.m_btnViewCert.setIcon(GUIUtils.loadImageIcon("certs/not_trusted.png"));
                this.m_btnViewCert.setForeground(Color.red);
                this.m_btnViewCert.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_NOT_VERIFIED));
            }
            else if (!valid) {
                this.m_btnViewCert.setIcon(GUIUtils.loadImageIcon("certs/invalid.png"));
                this.m_btnViewCert.setForeground(this.m_btnEmail.getForeground());
                this.m_btnViewCert.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_INVALID));
            }
            else {
                this.m_btnViewCert.setForeground(this.m_btnEmail.getForeground());
                if (this.m_serverCertPaths.countVerifiedAndValidPaths() > 2) {
                    this.m_btnViewCert.setIcon(GUIUtils.loadImageIcon("certs/trusted_green.png"));
                    this.m_btnViewCert.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_INDEPENDENT_CERTIFICATIONS, "" + this.m_serverCertPaths.countVerifiedAndValidPaths()));
                }
                else if (this.m_serverCertPaths.countVerifiedAndValidPaths() > 1) {
                    this.m_btnViewCert.setIcon(GUIUtils.loadImageIcon("certs/trusted_blue.png"));
                    this.m_btnViewCert.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_INDEPENDENT_CERTIFICATIONS, "" + this.m_serverCertPaths.countVerifiedAndValidPaths()));
                }
                else {
                    this.m_btnViewCert.setToolTipText(JAPMessages.getString(MixDetailsDialog.MSG_VALID));
                    this.m_btnViewCert.setIcon(GUIUtils.loadImageIcon("certs/trusted_black.png"));
                }
            }
            this.m_btnViewCert.setEnabled(true);
        }
        else {
            this.m_btnViewCert.setToolTipText("N/A");
            this.m_btnViewCert.setIcon(null);
            this.m_btnViewCert.setEnabled(false);
        }
        DataRetentionInformation dataRetentionInformation = null;
        if (this.m_serverInfo != null) {
            dataRetentionInformation = this.m_serverInfo.getDataRetentionInformation();
        }
        if (dataRetentionInformation == null) {
            this.m_btnDataRetention.setVisible(false);
            this.m_btnDataRetention.setToolTipText(null);
        }
        else {
            this.m_btnDataRetention.setVisible(true);
            this.m_btnDataRetention.setToolTipText(JAPMessages.getString(DataRetentionDialog.MSG_DATA_RETENTION_MIX_EXPLAIN_SHORT));
        }
        this.pRoot.validate();
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("confAnonTab");
    }
    
    public void onResetToDefaultsPressed() {
        if (this.m_filterPanel != null && this.m_filterPanel.isVisible()) {
            this.hideEditFilter();
        }
        this.m_filterAllMixes.setEnabled(true);
        this.m_filterAtLeast2Mixes.setEnabled(true);
        TrustModel.restoreDefault();
    }
    
    protected void onCancelPressed() {
        if (this.m_filterPanel != null && this.m_filterPanel.isVisible()) {
            if (this.m_previousTrustModel != TrustModel.getCustomFilter()) {
                this.m_cmbCascadeFilter.setSelectedItem(this.m_previousTrustModel);
            }
            this.hideEditFilter();
        }
    }
    
    public boolean onOkPressed() {
        if (this.m_filterPanel != null && this.m_filterPanel.isVisible()) {
            this.applyFilter();
            this.hideEditFilter();
        }
        return true;
    }
    
    protected void onUpdateValues() {
        ((MixCascadeTableModel)this.m_tableMixCascade.getModel()).update();
    }
    
    private void fetchCascades(final boolean b, final boolean b2) {
        this.m_reloadCascadesButton.setEnabled(false);
        new Thread(new Runnable() {
            public void run() {
                if (JAPController.getInstance().fetchMixCascades(b, false)) {
                    JAPController.getInstance().updatePerformanceInfo(b);
                }
                JAPConfAnon.this.updateValues(false);
                LogHolder.log(7, LogType.GUI, "Enabling reload button");
                JAPConfAnon.this.m_reloadCascadesButton.setEnabled(true);
            }
        }).start();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof JMenuItem && actionEvent.getActionCommand() != null && actionEvent.getActionCommand().equals(JAPConfAnon.MSG_FILTER_SELECT_ALL_OPERATORS)) {
            ((OperatorsTableModel)this.m_listOperators.getModel()).reset();
            this.m_listOperators.updateUI();
        }
        if (actionEvent.getSource() == this.m_cancelCascadeButton) {
            if (this.mb_manualCascadeNew) {
                this.m_manualPanel.setVisible(false);
                this.m_serverPanel.setVisible(true);
                this.m_serverInfoPanel.setVisible(true);
                this.updateValues(false);
            }
            else {
                this.m_manHostField.setText(this.m_oldCascadeHost);
                this.m_manPortField.setText(this.m_oldCascadePort);
                this.m_cancelCascadeButton.setEnabled(false);
            }
        }
        else if (actionEvent.getSource() == this.m_reloadCascadesButton) {
            this.fetchCascades(true, false);
        }
        else if (actionEvent.getSource() == this.m_selectCascadeButton) {
            MixCascade currentMixCascade;
            try {
                currentMixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
            }
            catch (Exception ex) {
                currentMixCascade = null;
            }
            if (currentMixCascade != null) {
                if (!TrustModel.getCurrentTrustModel().isTrusted(currentMixCascade)) {
                    JAPDialog.showMessageDialog(this.m_lblAvailability, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_NOT_TRUSTWORTHY, TrustModel.getCurrentTrustModel().getName()), new JAPDialog.LinkedHelpContext("services_anon"));
                }
                else {
                    JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
                    this.m_selectCascadeButton.setEnabled(false);
                    this.m_tableMixCascade.repaint();
                }
            }
        }
        else if (actionEvent.getSource() == this.m_manualCascadeButton) {
            this.drawManualPanel(null, null);
            this.mb_manualCascadeNew = true;
            this.m_deleteCascadeButton.setEnabled(false);
            this.m_cancelCascadeButton.setEnabled(true);
        }
        else if (actionEvent.getSource() == this.m_editCascadeButton) {
            if (this.mb_manualCascadeNew) {
                this.enterManualCascade();
            }
            else {
                this.editManualCascade();
            }
        }
        else if (actionEvent.getSource() == this.m_deleteCascadeButton) {
            this.deleteManualCascade();
        }
        else if (actionEvent.getSource() == this.m_showEditPanelButton) {
            final MixCascade mixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
            this.drawManualPanel(mixCascade.getListenerInterface(0).getHost(), String.valueOf(mixCascade.getListenerInterface(0).getPort()));
            this.mb_manualCascadeNew = false;
            this.m_deleteCascadeButton.setEnabled(!JAPController.getInstance().getCurrentMixCascade().equals(mixCascade));
            this.m_cancelCascadeButton.setEnabled(false);
            this.m_oldCascadeHost = this.m_manHostField.getText();
            this.m_oldCascadePort = this.m_manPortField.getText();
        }
        else if (actionEvent.getSource() == this.m_showEditFilterButton) {
            if (this.m_filterPanel == null || !this.m_filterPanel.isVisible()) {
                this.showFilter();
            }
            else if (this.m_filterPanel != null && this.m_filterPanel.isVisible()) {
                if (this.m_previousTrustModel != TrustModel.getCustomFilter()) {
                    this.m_cmbCascadeFilter.setSelectedItem(this.m_previousTrustModel);
                }
                this.hideEditFilter();
            }
        }
        else if (actionEvent.getSource() == this.m_filterAllCountries) {
            this.m_filterAllMixes.setEnabled(true);
            this.m_filterAtLeast2Mixes.setEnabled(true);
        }
        else if (actionEvent.getSource() == this.m_filterAtLeast2Countries) {
            this.m_filterAllMixes.setEnabled(false);
            this.m_filterAtLeast2Mixes.setEnabled(true);
        }
        else if (actionEvent.getSource() == this.m_filterAtLeast3Countries) {
            this.m_filterAllMixes.setEnabled(false);
            this.m_filterAtLeast2Mixes.setEnabled(false);
        }
    }
    
    public void showFilter() {
        this.m_previousTrustModel = (TrustModel)this.m_cmbCascadeFilter.getSelectedItem();
        this.m_cmbCascadeFilter.setSelectedItem(TrustModel.getCustomFilter());
        this.drawFilterPanel();
    }
    
    private void hideEditFilter() {
        this.m_showEditFilterButton.setText(JAPMessages.getString(JAPConfAnon.MSG_EDIT_FILTER));
        this.m_filterPanel.setVisible(false);
        this.m_serverPanel.setVisible(true);
        this.m_serverInfoPanel.setVisible(true);
        this.updateValues(false);
    }
    
    private boolean isServerCertVerified() {
        return this.m_serverInfo != null && this.m_serverInfo.getCertPath().isVerified();
    }
    
    private void editManualCascade() {
        boolean b = true;
        try {
            final MixCascade mixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
            final MixCascade currentMixCascade = new MixCascade(this.m_manHostField.getText(), Integer.parseInt(this.m_manPortField.getText()));
            final Vector entryList = Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).getEntryList();
            for (int i = 0; i < entryList.size(); ++i) {
                final MixCascade mixCascade2 = entryList.elementAt(i);
                if (mixCascade2.getListenerInterface(0).getHost().equalsIgnoreCase(currentMixCascade.getListenerInterface(0).getHost()) && mixCascade2.getListenerInterface(0).getPort() == currentMixCascade.getListenerInterface(0).getPort() && mixCascade2.isUserDefined()) {
                    b = false;
                }
            }
            if (b) {
                Database.getInstance((JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).update(new PreviouslyKnownCascadeIDEntry(currentMixCascade));
                Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).update(currentMixCascade);
                Database.getInstance((JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).remove(new PreviouslyKnownCascadeIDEntry(mixCascade));
                Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).remove(mixCascade);
                if (JAPController.getInstance().getCurrentMixCascade().equals(mixCascade)) {
                    JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
                }
                new Thread(new Runnable() {
                    private final /* synthetic */ JAPConfAnon this$0;
                    
                    public void run() {
                        JAPConfAnon.this.updateValues(true);
                        SwingUtilities.invokeLater(new Runnable() {
                            private final /* synthetic */ JAPConfAnon$6 this$1 = this$1;
                            
                            public void run() {
                                this.this$1.this$0.setSelectedCascade(currentMixCascade);
                            }
                        });
                    }
                }).start();
            }
            else {
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("cascadeExistsDesc"), LogType.MISC);
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Cannot edit cascade");
            JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("errorCreateCascadeDesc"), LogType.MISC, ex);
        }
    }
    
    private void deleteManualCascade() {
        try {
            final MixCascade mixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
            if (JAPController.getInstance().getCurrentMixCascade().equals(mixCascade)) {
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString("activeCascadeDelete"), LogType.MISC);
            }
            else if (JAPDialog.showYesNoDialog(this.getRootPanel(), JAPMessages.getString(JAPConfAnon.MSG_REALLY_DELETE))) {
                Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).remove(mixCascade);
                if (TrustModel.getCurrentTrustModel() == TrustModel.getTrustModelUserDefined()) {
                    Enumeration<MixCascade> entrySnapshotAsEnumeration = (Enumeration<MixCascade>)Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).getEntrySnapshotAsEnumeration();
                    while (entrySnapshotAsEnumeration.hasMoreElements()) {
                        if (entrySnapshotAsEnumeration.nextElement().isUserDefined()) {
                            entrySnapshotAsEnumeration = null;
                            break;
                        }
                    }
                    if (entrySnapshotAsEnumeration != null) {
                        TrustModel.setCurrentTrustModel(TrustModel.getTrustModelDefault());
                    }
                }
                if (this.m_tableMixCascade.getRowCount() >= 0) {
                    this.m_tableMixCascade.getSelectionModel().setSelectionInterval(0, 0);
                }
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Cannot delete cascade");
        }
    }
    
    private void enterManualCascade() {
        try {
            final MixCascade mixCascade = new MixCascade(this.m_manHostField.getText(), Integer.parseInt(this.m_manPortField.getText()));
            Database.getInstance((JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : JAPConfAnon.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).update(new PreviouslyKnownCascadeIDEntry(mixCascade));
            Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).update(mixCascade);
            ((MixCascadeTableModel)this.m_tableMixCascade.getModel()).addElement(mixCascade);
            TrustModel.setCurrentTrustModel(TrustModel.getTrustModelUserDefined());
            if (!JAPController.getInstance().isAnonConnected()) {
                JAPController.getInstance().setCurrentMixCascade(mixCascade);
            }
            this.setSelectedCascade(mixCascade);
            new Thread(new Runnable() {
                private final /* synthetic */ JAPConfAnon this$0;
                
                public void run() {
                    JAPConfAnon.this.updateValues(true);
                    SwingUtilities.invokeLater(new Runnable() {
                        private final /* synthetic */ JAPConfAnon$8 this$1 = this$1;
                        
                        public void run() {
                            this.this$1.this$0.setSelectedCascade(mixCascade);
                        }
                    });
                }
            }).start();
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Cannot create cascade");
        }
    }
    
    private void applyFilter() {
        if (!this.m_trustModelCopy.isEditable() || !TrustModel.getCurrentTrustModel().isEditable()) {
            return;
        }
        try {
            int n = 0;
            int n2;
            if (this.m_cbxSocks5.isSelected()) {
                n2 = 2;
            }
            else {
                n2 = 0;
            }
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$SocksAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$SocksAttribute = class$("anon.client.TrustModel$SocksAttribute")) : JAPConfAnon.class$anon$client$TrustModel$SocksAttribute, n2);
            int n3;
            if (this.m_cbxDataRetention.isSelected()) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$DataRetentionAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$DataRetentionAttribute = class$("anon.client.TrustModel$DataRetentionAttribute")) : JAPConfAnon.class$anon$client$TrustModel$DataRetentionAttribute, n3);
            int n4;
            if (this.m_cbxFreeOfCharge.isSelected()) {
                n4 = 1;
            }
            else {
                n4 = 0;
            }
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$PremiumAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$PremiumAttribute = class$("anon.client.TrustModel$PremiumAttribute")) : JAPConfAnon.class$anon$client$TrustModel$PremiumAttribute, n4);
            final String actionCommand = this.m_filterCascadeGroup.getSelection().getActionCommand();
            if (this.m_filterAtLeast2Mixes.isSelected()) {
                n = 2;
            }
            else if (this.m_filterAtLeast3Mixes.isSelected()) {
                n = 3;
            }
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute = class$("anon.client.TrustModel$NumberOfMixesAttribute")) : JAPConfAnon.class$anon$client$TrustModel$NumberOfMixesAttribute, Integer.parseInt(actionCommand), n);
            int n5 = 0;
            final String actionCommand2 = this.m_filterInternationalGroup.getSelection().getActionCommand();
            if (this.m_filterAtLeast2Countries.isSelected()) {
                n5 = 2;
            }
            else if (this.m_filterAtLeast3Countries.isSelected()) {
                n5 = 3;
            }
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute = class$("anon.client.TrustModel$InternationalAttribute")) : JAPConfAnon.class$anon$client$TrustModel$InternationalAttribute, Integer.parseInt(actionCommand2), n5);
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$OperatorBlacklistAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$OperatorBlacklistAttribute = class$("anon.client.TrustModel$OperatorBlacklistAttribute")) : JAPConfAnon.class$anon$client$TrustModel$OperatorBlacklistAttribute, 6, ((OperatorsTableModel)this.m_listOperators.getModel()).getBlacklist());
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute = class$("anon.client.TrustModel$SpeedAttribute")) : JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute, 3, this.m_filterSpeedSlider.getValue() * 100);
            this.m_trustModelCopy.setAttribute((JAPConfAnon.class$anon$client$TrustModel$DelayAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$DelayAttribute = class$("anon.client.TrustModel$DelayAttribute")) : JAPConfAnon.class$anon$client$TrustModel$DelayAttribute, 5, this.convertDelayValue(this.m_filterLatencySlider.getValue(), true));
            if (this.m_filterNameField.getText().length() > 0) {
                this.m_trustModelCopy.setName(this.m_filterNameField.getText());
            }
            TrustModel.getCurrentTrustModel().copyFrom(this.m_trustModelCopy);
            if (!TrustModel.getCurrentTrustModel().hasTrustedCascades() && !JAPController.getInstance().getCurrentMixCascade().isShownAsTrusted()) {
                JAPDialog.showWarningDialog(this.m_filterPanel, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_NO_CASCADES));
            }
            else if (JAPController.getInstance().isAnonConnected() && !TrustModel.getCurrentTrustModel().isTrusted(JAPController.getInstance().getCurrentMixCascade()) && (JAPModel.getInstance().isCascadeAutoSwitched() || JAPDialog.showYesNoDialog(this.m_filterPanel, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_CURRENT_CASCADE_NOT_TRUSTED)))) {
                JAPController.getInstance().switchToNextMixCascade(true);
            }
        }
        catch (NumberFormatException ex) {
            LogHolder.log(3, LogType.GUI, "Error parsing trust condition from filter settings");
        }
        this.updateValues(false);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.m_btnHomepage) {
            final String urlFromLabel = getUrlFromLabel((JButton)mouseEvent.getSource());
            if (urlFromLabel == null) {
                return;
            }
            final AbstractOS instance = AbstractOS.getInstance();
            try {
                instance.openURL(new URL(urlFromLabel));
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.MISC, "Error opening URL in browser");
            }
        }
        else if (mouseEvent.getSource() == this.m_btnDataRetention) {
            DataRetentionDialog.show(this.getRootPanel().getParent(), this.m_cascadeInfo, this.m_serverList.getSelectedIndex());
        }
        else if (mouseEvent.getSource() == this.m_btnEmail) {
            AbstractOS.getInstance().openEMail(getEMailFromLabel(this.m_btnEmail));
        }
        else if (mouseEvent.getSource() == this.m_listOperators) {
            if (mouseEvent.getClickCount() == 2) {
                ServiceOperator serviceOperator = null;
                synchronized (this.m_listOperators.getModel()) {
                    serviceOperator = (ServiceOperator)this.m_listOperators.getValueAt(this.m_listOperators.rowAtPoint(mouseEvent.getPoint()), 1);
                }
                if (serviceOperator != null && serviceOperator.getCertificate() != null) {
                    final CertDetailsDialog certDetailsDialog = new CertDetailsDialog(this.getRootPanel().getParent(), serviceOperator.getCertificate(), true, JAPMessages.getLocale());
                    certDetailsDialog.pack();
                    certDetailsDialog.setVisible(true);
                }
            }
        }
        else if (mouseEvent.getSource() == this.m_tableMixCascade) {
            if (mouseEvent.getClickCount() == 2) {
                MixCascade currentMixCascade = null;
                synchronized (this.m_tableMixCascade.getModel()) {
                    currentMixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.rowAtPoint(mouseEvent.getPoint()), 1);
                }
                if (currentMixCascade != null) {
                    if (!TrustModel.getCurrentTrustModel().isTrusted(currentMixCascade)) {
                        JAPDialog.showMessageDialog(this.m_lblAvailability, JAPMessages.getString(JAPConfAnon.MSG_EXPLAIN_NOT_TRUSTWORTHY, TrustModel.getCurrentTrustModel().getName()), new JAPDialog.LinkedHelpContext("services_anon"));
                    }
                    else {
                        JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
                        this.m_deleteCascadeButton.setEnabled(false);
                        this.m_showEditPanelButton.setEnabled(false);
                        this.m_selectCascadeButton.setEnabled(false);
                        this.m_tableMixCascade.repaint();
                    }
                }
            }
        }
        else if (mouseEvent.getSource() == this.m_btnViewCert) {
            if (this.m_serverCertPaths != null && this.m_serverInfo != null) {
                final MultiCertOverview multiCertOverview = new MultiCertOverview(this.getRootPanel().getParent(), this.m_serverCertPaths, this.m_serverInfo.getName(), false);
            }
        }
        else if (mouseEvent.getSource() == this.m_btnMap) {
            if (this.m_locationCoordinates != null && !this.m_mapShown) {
                new Thread(new Runnable() {
                    public void run() {
                        JAPConfAnon.this.m_mapShown = true;
                        JAPConfAnon.this.getRootPanel().setCursor(Cursor.getPredefinedCursor(3));
                        new MapBox(JAPConfAnon.this.getRootPanel(), JAPConfAnon.this.m_locationCoordinates.elementAt(0), JAPConfAnon.this.m_locationCoordinates.elementAt(1), 8).setVisible(true);
                        JAPConfAnon.this.getRootPanel().setCursor(Cursor.getDefaultCursor());
                        JAPConfAnon.this.m_mapShown = false;
                    }
                }).start();
            }
        }
        else if (mouseEvent.getSource() == this.m_moveMixLeft) {
            this.m_serverList.moveToPrevious();
        }
        else if (mouseEvent.getSource() == this.m_moveMixRight) {
            this.m_serverList.moveToNext();
        }
        else if (mouseEvent.getSource() == this.m_lblVDS) {
            DataRetentionDialog.show(this.getRootPanel().getParent(), this.m_cascadeInfo);
        }
    }
    
    private int convertDelayValue(int n, final boolean b) {
        if (b && n == this.m_filterLatencySlider.getMinimum()) {
            return Integer.MAX_VALUE;
        }
        if (!b && n == Integer.MAX_VALUE) {
            return this.m_filterLatencySlider.getMinimum();
        }
        if (b) {
            n = (5 - n) * 1000;
        }
        else if (n < 1000) {
            n = 1000;
        }
        else if (n > 5000) {
            n = 5000;
        }
        else {
            n = 5 - n / 1000;
        }
        return n;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
        if (mouseEvent.getSource() == this.m_moveMixRight || mouseEvent.getSource() == this.m_moveMixLeft) {
            ((JButton)mouseEvent.getSource()).setBorder(BorderFactory.createLoweredBevelBorder());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
        if (mouseEvent.getSource() == this.m_moveMixRight || mouseEvent.getSource() == this.m_moveMixLeft) {
            ((JButton)mouseEvent.getSource()).setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }
    
    private void maybeShowPopup(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() && mouseEvent.getSource() == this.m_listOperators) {
            this.m_opPopupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public String getHelpContext() {
        return "services_anon";
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                JAPController.getInstance().addObserver(this);
                JAPModel.getInstance().getRoutingSettings().addObserver(this);
                SignatureVerifier.getInstance().getVerificationCertificateStore().addObserver(this);
                Database.getInstance((JAPConfAnon.class$anon$infoservice$MixCascade == null) ? (JAPConfAnon.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfAnon.class$anon$infoservice$MixCascade).addObserver(this);
                Database.getInstance((JAPConfAnon.class$anon$infoservice$StatusInfo == null) ? (JAPConfAnon.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : JAPConfAnon.class$anon$infoservice$StatusInfo).addObserver(this);
                Database.getInstance((JAPConfAnon.class$anon$infoservice$MixInfo == null) ? (JAPConfAnon.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : JAPConfAnon.class$anon$infoservice$MixInfo).addObserver(this);
                Database.getInstance((JAPConfAnon.class$anon$infoservice$PerformanceInfo == null) ? (JAPConfAnon.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : JAPConfAnon.class$anon$infoservice$PerformanceInfo).addObserver(this);
                this.m_cmbCascadeFilter.setSelectedItem(TrustModel.getCurrentTrustModel());
                TrustModel.addModelObserver(this);
            }
            return true;
        }
        return false;
    }
    
    protected void onRootPanelShown() {
        if (this.m_tableMixCascade.getRowCount() > 0 && this.m_tableMixCascade.getSelectedRow() < 0) {
            this.m_tableMixCascade.getSelectionModel().setSelectionInterval(0, 0);
        }
    }
    
    public void setSelectedCascade(final MixCascade selectedCascade) {
        ((MixCascadeTableModel)this.m_tableMixCascade.getModel()).setSelectedCascade(selectedCascade);
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        final boolean bUpdateServerPanel;
        synchronized (((MixCascadeTableModel)this.m_tableMixCascade.getModel()).SYNC_UPDATE_SERVER_PANEL) {
            bUpdateServerPanel = this.m_bUpdateServerPanel;
        }
        if (listSelectionEvent == null || (!listSelectionEvent.getValueIsAdjusting() && bUpdateServerPanel)) {
            final MixCascade mixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
            if (mixCascade != null) {
                final int selectedIndex = this.m_serverList.getSelectedIndex();
                if (mixCascade == null) {
                    this.m_deleteCascadeButton.setEnabled(false);
                    this.m_showEditPanelButton.setEnabled(false);
                    this.m_selectCascadeButton.setEnabled(false);
                    return;
                }
                final String id = mixCascade.getId();
                if (this.m_filterPanel == null || !this.m_filterPanel.isVisible()) {
                    if (mixCascade.getNumberOfMixes() <= 1) {
                        this.drawServerPanel(3, "", false, 0);
                    }
                    else if (!mixCascade.isUserDefined() && mixCascade.getNumberOfOperatorsShown() <= 1) {
                        this.drawServerPanel(1, mixCascade.getName(), true, selectedIndex);
                    }
                    else {
                        this.drawServerPanel(mixCascade.getNumberOfMixes(), mixCascade.getName(), true, selectedIndex);
                    }
                }
                final PerformanceEntry access$3200 = getPerformanceEntry(id);
                ((DecimalFormat)NumberFormat.getInstance(JAPMessages.getLocale())).applyPattern("#,####0.00");
                if (access$3200 != null) {
                    boolean b;
                    try {
                        TrustModel.getCurrentTrustModel().getAttribute((JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute = class$("anon.client.TrustModel$SpeedAttribute")) : JAPConfAnon.class$anon$client$TrustModel$SpeedAttribute).checkTrust(mixCascade);
                        b = true;
                    }
                    catch (TrustException ex) {
                        b = false;
                    }
                    catch (SignatureException ex2) {
                        b = false;
                    }
                    final int bound = access$3200.getBound(0).getBound();
                    int bestBound = access$3200.getBestBound(0);
                    if (bestBound < bound) {
                        bestBound = bound;
                    }
                    if (bound < 0 || bound == Integer.MAX_VALUE) {
                        this.m_lblSpeed.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                    }
                    else if (bound == 0) {
                        this.m_lblSpeed.setText("< " + Util.formatKbitPerSecValueWithUnit(PerformanceEntry.BOUNDARIES[0][1], 0));
                    }
                    else if (PerformanceEntry.BOUNDARIES[0][PerformanceEntry.BOUNDARIES[0].length - 1] == bestBound) {
                        if (System.getProperty("java.version").compareTo("1.4") >= 0) {
                            this.m_lblSpeed.setText("\u2265 " + Util.formatKbitPerSecValueWithUnit(bound, 0));
                        }
                        else {
                            this.m_lblSpeed.setText("> " + Util.formatKbitPerSecValueWithUnit(bound, 0));
                        }
                    }
                    else if (bestBound == bound || bestBound == Integer.MAX_VALUE) {
                        this.m_lblSpeed.setText(Util.formatKbitPerSecValueWithUnit(bound, 0));
                    }
                    else {
                        this.m_lblSpeed.setText(Util.formatKbitPerSecValueWithoutUnit(bound, 0) + "-" + Util.formatKbitPerSecValueWithUnit(bestBound, 0));
                    }
                    if (b) {
                        this.m_lblSpeed.setForeground(this.m_anonLevelLabel.getForeground());
                    }
                    else {
                        this.m_lblSpeed.setForeground(Color.red);
                    }
                    boolean b2;
                    try {
                        TrustModel.getCurrentTrustModel().getAttribute((JAPConfAnon.class$anon$client$TrustModel$DelayAttribute == null) ? (JAPConfAnon.class$anon$client$TrustModel$DelayAttribute = class$("anon.client.TrustModel$DelayAttribute")) : JAPConfAnon.class$anon$client$TrustModel$DelayAttribute).checkTrust(mixCascade);
                        b2 = true;
                    }
                    catch (TrustException ex3) {
                        b2 = false;
                    }
                    catch (SignatureException ex4) {
                        b2 = false;
                    }
                    final int bound2 = access$3200.getBound(1).getBound();
                    int bestBound2 = access$3200.getBestBound(1);
                    if (bestBound2 > bound2) {
                        bestBound2 = bound2;
                    }
                    if (bound2 <= 0) {
                        this.m_lblDelay.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                    }
                    else if (bound2 == Integer.MAX_VALUE) {
                        this.m_lblDelay.setText("> " + PerformanceEntry.BOUNDARIES[1][PerformanceEntry.BOUNDARIES[1].length - 2] + " ms");
                    }
                    else if (PerformanceEntry.BOUNDARIES[1][0] == bestBound2) {
                        if (System.getProperty("java.version").compareTo("1.4") >= 0) {
                            this.m_lblDelay.setText("\u2264 " + bound2 + " ms");
                        }
                        else {
                            this.m_lblDelay.setText("< " + bound2 + " ms");
                        }
                    }
                    else if (bestBound2 == bound2 || bestBound2 == 0) {
                        this.m_lblDelay.setText(bound2 + " ms");
                    }
                    else {
                        this.m_lblDelay.setText(bound2 + "-" + bestBound2 + " ms");
                    }
                    if (b2) {
                        this.m_lblDelay.setForeground(this.m_anonLevelLabel.getForeground());
                    }
                    else {
                        this.m_lblDelay.setForeground(Color.red);
                    }
                }
                else {
                    this.m_lblSpeed.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                    this.m_lblDelay.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                    this.m_lblSpeed.setForeground(this.m_anonLevelLabel.getForeground());
                    this.m_lblDelay.setForeground(this.m_anonLevelLabel.getForeground());
                }
                this.m_anonLevelLabel.setText(mixCascade.getDistribution() + "," + InfoServiceTempLayer.getAnonLevel(id) + " / " + 6 + "," + 6);
                this.m_numOfUsersLabel.setText(InfoServiceTempLayer.getNumOfUsers(mixCascade));
                this.m_lblVDS.setVisible(mixCascade.getDataRetentionInformation() != null);
                this.setAvailabilityLabel(mixCascade, access$3200);
                this.m_lblSocks.setVisible(mixCascade.isSocks5Supported());
                if (mixCascade.isUserDefined()) {
                    this.m_deleteCascadeButton.setEnabled(!JAPController.getInstance().getCurrentMixCascade().equals(mixCascade));
                    this.m_showEditPanelButton.setEnabled(true);
                }
                else {
                    this.m_deleteCascadeButton.setEnabled(false);
                    this.m_showEditPanelButton.setEnabled(false);
                }
                final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
                if (currentMixCascade != null && currentMixCascade.equals(mixCascade)) {
                    this.m_selectCascadeButton.setEnabled(false);
                }
                else {
                    this.m_selectCascadeButton.setEnabled(true);
                }
            }
            if (this.m_filterPanel == null || !this.m_filterPanel.isVisible()) {
                this.drawServerInfoPanel();
            }
            this.itemStateChanged(null);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.m_manPortField) {
            final char keyChar = keyEvent.getKeyChar();
            if ((keyChar < '0' || keyChar > '9') && !this.mb_backSpacePressed) {
                keyEvent.consume();
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.m_manHostField || keyEvent.getSource() == this.m_manPortField) {
            this.m_editCascadeButton.setVisible(true);
            this.m_cancelCascadeButton.setEnabled(true);
        }
        if (keyEvent.getSource() == this.m_manPortField) {
            if (keyEvent.getKeyCode() == 8) {
                this.mb_backSpacePressed = true;
            }
            else {
                this.mb_backSpacePressed = false;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void update(final Observable observable, final Object o) {
        try {
            boolean b = false;
            MixCascade mixCascade = null;
            if (this.m_tableMixCascade.getSelectedRow() >= 0) {
                try {
                    mixCascade = (MixCascade)this.m_tableMixCascade.getValueAt(this.m_tableMixCascade.getSelectedRow(), 1);
                }
                catch (Exception ex4) {}
            }
            final int selectedIndex = this.m_serverList.getSelectedIndex();
            Label_0855: {
                if (observable == JAPModel.getInstance().getRoutingSettings()) {
                    if (((JAPRoutingMessage)o).getMessageCode() == 16) {
                        final JButton selectCascadeButton = this.m_selectCascadeButton;
                        if (selectCascadeButton != null) {
                            selectCascadeButton.setEnabled(!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder());
                        }
                    }
                }
                else if (o != null && o instanceof DatabaseMessage) {
                    final DatabaseMessage databaseMessage = (DatabaseMessage)o;
                    if (databaseMessage.getMessageData() instanceof MixCascade) {
                        if (databaseMessage.getMessageCode() == 1 || databaseMessage.getMessageCode() == 3 || databaseMessage.getMessageCode() == 4) {
                            b = true;
                        }
                        else if (databaseMessage.getMessageCode() == 2 && mixCascade != null && mixCascade.equals(databaseMessage.getMessageData())) {
                            b = true;
                        }
                        if (databaseMessage.getMessageCode() == 4) {
                            Database.getInstance((JAPConfAnon.class$anon$infoservice$MixInfo == null) ? (JAPConfAnon.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : JAPConfAnon.class$anon$infoservice$MixInfo).removeAll();
                        }
                        else if (databaseMessage.getMessageCode() == 3) {
                            try {
                                final MixCascade mixCascade2 = (MixCascade)((DatabaseMessage)o).getMessageData();
                                if (!JAPController.getInstance().getCurrentMixCascade().equals(mixCascade2)) {
                                    final Vector mixIds = mixCascade2.getMixIds();
                                    for (int i = 0; i < mixIds.size(); ++i) {
                                        Database.getInstance((JAPConfAnon.class$anon$infoservice$MixInfo == null) ? (JAPConfAnon.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : JAPConfAnon.class$anon$infoservice$MixInfo).remove(mixIds.elementAt(i));
                                    }
                                }
                            }
                            catch (Exception ex) {
                                LogHolder.log(2, LogType.MISC, ex);
                            }
                        }
                        else {
                            if (databaseMessage.getMessageCode() != 1) {
                                if (databaseMessage.getMessageCode() != 2) {
                                    break Label_0855;
                                }
                            }
                            try {
                                final MixCascade mixCascade3 = (MixCascade)((DatabaseMessage)o).getMessageData();
                                final Vector mixIds2 = mixCascade3.getMixIds();
                                for (int j = 0; j < mixIds2.size(); ++j) {
                                    final String s = mixIds2.elementAt(j);
                                    MixInfo mixInfo = mixCascade3.getMixInfo(j);
                                    if (mixInfo == null || mixInfo.getVersionNumber() <= 0L) {
                                        mixInfo = (MixInfo)Database.getInstance((JAPConfAnon.class$anon$infoservice$MixInfo == null) ? (JAPConfAnon.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : JAPConfAnon.class$anon$infoservice$MixInfo).getEntryById(s);
                                    }
                                    if (!JAPModel.isInfoServiceDisabled() && !mixCascade3.isUserDefined() && (mixInfo == null || mixInfo.isFromCascade())) {
                                        final MixInfo mixInfo2 = InfoServiceHolder.getInstance().getMixInfo(s);
                                        if (mixInfo2 == null) {
                                            LogHolder.log(5, LogType.GUI, "Did not get Mix info from InfoService for Mix " + s + "!");
                                        }
                                        else {
                                            Database.getInstance((JAPConfAnon.class$anon$infoservice$MixInfo == null) ? (JAPConfAnon.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : JAPConfAnon.class$anon$infoservice$MixInfo).update(mixInfo2);
                                        }
                                    }
                                }
                            }
                            catch (Exception ex2) {
                                LogHolder.log(2, LogType.MISC, ex2);
                            }
                        }
                    }
                    else if (databaseMessage.getMessageData() instanceof StatusInfo) {
                        if (mixCascade != null && mixCascade.getId().equals(((StatusInfo)databaseMessage.getMessageData()).getId())) {
                            b = true;
                        }
                    }
                    else if (databaseMessage.getMessageData() instanceof MixInfo) {
                        if (mixCascade != null && selectedIndex >= 0 && mixCascade.getMixIds().size() > 0 && mixCascade.getMixIds().elementAt(selectedIndex).equals(((MixInfo)databaseMessage.getMessageData()).getId())) {
                            b = true;
                        }
                    }
                    else if (databaseMessage.getMessageData() instanceof PerformanceInfo) {
                        b = true;
                    }
                }
                else if (observable == JAPController.getInstance() && o != null) {
                    if (((JAPControllerMessage)o).getMessageCode() == 2) {
                        b = true;
                    }
                }
                else if (observable == SignatureVerifier.getInstance().getVerificationCertificateStore()) {
                    if (o == null || (o instanceof Integer && (int)o == 1)) {
                        b = true;
                    }
                }
                else if (observable == TrustModel.getObservable()) {
                    if (o == TrustModel.NOTIFY_TRUST_MODEL_ADDED || o == TrustModel.NOTIFY_TRUST_MODEL_REMOVED) {
                        this.m_cmbCascadeFilter.setModel(new DefaultComboBoxModel(TrustModel.getTrustModels()));
                    }
                    this.m_cmbCascadeFilter.setSelectedItem(TrustModel.getCurrentTrustModel());
                    b = true;
                }
            }
            if (b) {
                this.updateValues(false);
            }
        }
        catch (Exception ex3) {
            LogHolder.log(0, LogType.GUI, ex3);
        }
    }
    
    private static String getEMailFromLabel(final JButton button) {
        final String toolTipText = button.getToolTipText();
        if (AbstractX509AlternativeName.isValidEMail(toolTipText)) {
            return toolTipText;
        }
        return null;
    }
    
    private static String getUrlFromLabel(final JButton button) {
        try {
            return new URL(button.getToolTipText()).toString();
        }
        catch (Exception ex) {
            return null;
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
        MSG_X_OF_Y_CERTS_TRUSTED = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_certXofYtrusted";
        MSG_REALLY_DELETE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_reallyDelete";
        MSG_MIX_VERSION = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_mixVersion";
        MSG_MIX_ID = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_mixID";
        MSG_BUTTONEDITSHOW = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_buttoneditshow";
        MSG_PAYCASCADE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_paycascade";
        MSG_MIX_POSITION = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_mixPosition";
        MSG_OF_THE_SERVICE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_ofTheService";
        MSG_MIX_FIRST = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_mixFirst";
        MSG_MIX_SINGLE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_singleMix";
        MSG_MIX_MIDDLE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_mixMiddle";
        MSG_MIX_LAST = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_mixLast";
        MSG_SHOW_ON_MAP = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_showOnMap";
        MSG_EXPLAIN_MIX_TT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_explainMixTT";
        MSG_FIRST_MIX_TEXT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_firstMixText";
        MSG_SINGLE_MIX_TEXT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_singleMixText";
        MSG_MIDDLE_MIX_TEXT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_middleMixText";
        MSG_LAST_MIX_TEXT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_lastMixText";
        MSG_NOT_TRUSTWORTHY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_notTrustworthy";
        MSG_EXPLAIN_NOT_TRUSTWORTHY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_explainNotTrustworthy";
        MSG_EXPLAIN_BLACKLISTED = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_explainBlacklisted";
        MSG_EXPLAIN_PI_UNAVAILABLE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_explainPiUnavailable";
        MSG_EXPLAIN_NO_CASCADES = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_explainNoCascades";
        MSG_EXPLAIN_CURRENT_CASCADE_NOT_TRUSTED = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_explainCurrentCascadeNotTrusted";
        MSG_WHAT_IS_THIS = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_whatIsThis";
        MSG_FILTER = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_filter";
        MSG_EDIT_FILTER = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_editFilter";
        MSG_ANON_LEVEL = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_anonLevel";
        MSG_SUPPORTS_SOCKS = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_socksSupported";
        MSG_FILTER_PAYMENT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_payment";
        MSG_FILTER_CASCADES = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_cascades";
        MSG_FILTER_INTERNATIONALITY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_internationality";
        MSG_FILTER_OPERATORS = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_operators";
        MSG_FILTER_SPEED = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_speed";
        MSG_FILTER_LATENCY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_latency";
        MSG_FILTER_ALL = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_all";
        MSG_FILTER_PAYMENT_ONLY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_paymentOnly";
        MSG_FILTER_PREMIUM_PRIVATE_ONLY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_premiumPrivateOnly";
        MSG_FILTER_BUSINESS_ONLY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_businessOnly";
        MSG_FILTER_NO_PAYMENT_ONLY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_noPaymentOnly";
        MSG_FILTER_AT_LEAST_3_MIXES = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_atLeast3Mixes";
        MSG_FILTER_AT_LEAST_2_MIXES = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_atLeast2Mixes";
        MSG_FILTER_AT_LEAST_2_COUNTRIES = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_atLeast2Countries";
        MSG_FILTER_AT_LEAST_3_COUNTRIES = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_atLeast3Countries";
        MSG_FILTER_AT_LEAST = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_atLeast";
        MSG_FILTER_AT_MOST = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_atMost";
        MSG_FILTER_SELECT_ALL_OPERATORS = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_selectAllOperators";
        MSG_FILTER_OTHER = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_filterOther";
        MSG_FILTER_SOCKS_ONLY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_filterSOCKSOnly";
        MSG_FILTER_NO_DATA_RETENTION = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_filterNoDataRetention";
        MSG_CONNECTED = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_connected";
        MSG_LBL_AVAILABILITY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityLbl";
        MSG_USER_LIMIT = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityUserLimit";
        MSG_UNSTABLE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityUnstable";
        MSG_HARDLY_REACHABLE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityHardlyReachable";
        MSG_UNREACHABLE = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityUnreachable";
        MSG_BAD_AVAILABILITY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityBad";
        MSG_GOOD_AVAILABILITY = ((JAPConfAnon.class$jap$JAPConfAnon == null) ? (JAPConfAnon.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPConfAnon.class$jap$JAPConfAnon).getName() + "_availabilityGood";
    }
    
    private static final class InfoServiceTempLayer
    {
        static /* synthetic */ Class class$anon$infoservice$StatusInfo;
        static /* synthetic */ Class class$anon$infoservice$MixCascade;
        static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
        static /* synthetic */ Class class$anon$infoservice$MixInfo;
        
        public static String getAnonLevel(final String s) {
            final StatusInfo statusInfo = getStatusInfo(s);
            if (statusInfo != null && statusInfo.getAnonLevel() >= 0) {
                return "" + statusInfo.getAnonLevel();
            }
            return "?";
        }
        
        public static String getNumOfUsers(final MixCascade mixCascade) {
            if (mixCascade != null) {
                final StatusInfo statusInfo = getStatusInfo(mixCascade.getId());
                if (statusInfo != null) {
                    final int maxUsers = mixCascade.getMaxUsers();
                    return "" + statusInfo.getNrOfActiveUsers() + ((maxUsers != 0) ? (" / " + maxUsers) : "");
                }
            }
            return "N/A";
        }
        
        public static boolean isUserLimitReached(final MixCascade mixCascade) {
            if (mixCascade != null) {
                final StatusInfo statusInfo = getStatusInfo(mixCascade.getId());
                if (statusInfo != null) {
                    final int maxUsers = mixCascade.getMaxUsers();
                    if (maxUsers > 0 && maxUsers - statusInfo.getNrOfActiveUsers() <= 3) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        public static String getHosts(final MixCascade mixCascade) {
            if (mixCascade == null || mixCascade.getHostsAsString() == null) {
                return "N/A";
            }
            return mixCascade.getHostsAsString();
        }
        
        public static String getPorts(final MixCascade mixCascade) {
            if (mixCascade == null || mixCascade.getPortsAsString() == null) {
                return "N/A";
            }
            return mixCascade.getPortsAsString();
        }
        
        public static String getMixVersion(final MixCascade mixCascade, final String s) {
            final MixInfo mixInfo = getMixInfo(mixCascade, s);
            if (mixInfo != null) {
                final ServiceSoftware serviceSoftware = mixInfo.getServiceSoftware();
                if (serviceSoftware != null) {
                    return serviceSoftware.getVersion();
                }
            }
            return null;
        }
        
        public static MultiCertPath getMixCertPath(final MixCascade mixCascade, final String s) {
            final MixInfo mixInfo = getMixInfo(mixCascade, s);
            MultiCertPath certPath = null;
            if (mixInfo != null) {
                certPath = mixInfo.getCertPath();
            }
            return certPath;
        }
        
        public static String getEMail(final MixCascade mixCascade, final String s) {
            String eMail = null;
            final MixInfo mixInfo = getMixInfo(mixCascade, s);
            if (mixInfo != null) {
                final ServiceOperator serviceOperator = mixInfo.getServiceOperator();
                if (serviceOperator != null) {
                    eMail = serviceOperator.getEMail();
                }
            }
            if (eMail == null || !AbstractX509AlternativeName.isValidEMail(eMail)) {
                return "N/A";
            }
            return eMail;
        }
        
        public static String getOperator(final MixCascade mixCascade, final String s) {
            final ServiceOperator serviceOperator = getServiceOperator(mixCascade, s);
            String s2 = null;
            if (serviceOperator != null) {
                s2 = serviceOperator.getOrganization();
            }
            if (s2 == null || s2.trim().length() == 0) {
                return "N/A";
            }
            final String countryCode = serviceOperator.getCountryCode();
            if (countryCode != null && countryCode.trim().length() > 0) {
                String s3 = s2 + "  (";
                try {
                    s3 += new CountryMapper(countryCode, JAPMessages.getLocale()).toString();
                }
                catch (IllegalArgumentException ex) {
                    s3 += countryCode.trim();
                }
                s2 = s3 + ")";
            }
            return s2;
        }
        
        public static String getUrl(final MixCascade mixCascade, final String s) {
            final ServiceOperator serviceOperator = getServiceOperator(mixCascade, s);
            String url = null;
            if (serviceOperator != null) {
                url = serviceOperator.getUrl();
            }
            try {
                if (url != null && url.toLowerCase().startsWith("https")) {
                    final URL url2 = new URL("http" + url.substring(5, url.length()));
                }
                else {
                    new URL(url);
                }
            }
            catch (MalformedURLException ex) {
                url = null;
            }
            if (url == null) {
                return "N/A";
            }
            return url;
        }
        
        public static String getName(final MixCascade mixCascade, final String s) {
            final MixInfo mixInfo = getMixInfo(mixCascade, s);
            if (mixInfo == null) {
                return null;
            }
            String name = mixInfo.getName();
            if (name == null || name.trim().length() == 0) {
                name = null;
            }
            return name;
        }
        
        public static String getLocation(final MixCascade mixCascade, final String s) {
            final ServiceLocation serviceLocation = getServiceLocation(mixCascade, s);
            if (serviceLocation != null) {
                return GUIUtils.getCountryFromServiceLocation(serviceLocation);
            }
            return "N/A";
        }
        
        public static boolean isPay(final String s) {
            final MixCascade mixCascade = getMixCascade(s);
            return mixCascade != null && mixCascade.isPayment();
        }
        
        public static Vector getCoordinates(final MixCascade mixCascade, final String s) {
            final ServiceLocation serviceLocation = getServiceLocation(mixCascade, s);
            if (serviceLocation == null || serviceLocation.getLatitude() == null || serviceLocation.getLongitude() == null) {
                return null;
            }
            try {
                Double.valueOf(serviceLocation.getLatitude());
                Double.valueOf(serviceLocation.getLongitude());
            }
            catch (NumberFormatException ex) {
                return null;
            }
            final Vector<String> vector = new Vector<String>();
            vector.addElement(serviceLocation.getLatitude());
            vector.addElement(serviceLocation.getLongitude());
            return vector;
        }
        
        private static StatusInfo getStatusInfo(final String s) {
            return (StatusInfo)Database.getInstance((InfoServiceTempLayer.class$anon$infoservice$StatusInfo == null) ? (InfoServiceTempLayer.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : InfoServiceTempLayer.class$anon$infoservice$StatusInfo).getEntryById(s);
        }
        
        private static MixCascade getMixCascade(final String s) {
            return (MixCascade)Database.getInstance((InfoServiceTempLayer.class$anon$infoservice$MixCascade == null) ? (InfoServiceTempLayer.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : InfoServiceTempLayer.class$anon$infoservice$MixCascade).getEntryById(s);
        }
        
        private static ServiceLocation getServiceLocation(final MixCascade mixCascade, final String s) {
            final MixInfo mixInfo = getMixInfo(mixCascade, s);
            if (mixInfo != null) {
                return mixInfo.getServiceLocation();
            }
            final MixCascade mixCascade2 = (MixCascade)Database.getInstance((InfoServiceTempLayer.class$anon$infoservice$MixCascade == null) ? (InfoServiceTempLayer.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : InfoServiceTempLayer.class$anon$infoservice$MixCascade).getEntryById(s);
            if (mixCascade2 != null && mixCascade2.getCertPath() != null) {
                final CertPath path = mixCascade2.getCertPath().getPath();
                if (path != null) {
                    final JAPCertificate secondCertificate = path.getSecondCertificate();
                    if (secondCertificate != null) {
                        return new ServiceLocation(null, secondCertificate);
                    }
                }
            }
            return null;
        }
        
        private static ServiceOperator getServiceOperator(final MixCascade mixCascade, final String s) {
            final MixInfo mixInfo = getMixInfo(mixCascade, s);
            if (mixInfo != null) {
                return mixInfo.getServiceOperator();
            }
            final MixCascade mixCascade2 = (MixCascade)Database.getInstance((InfoServiceTempLayer.class$anon$infoservice$MixCascade == null) ? (InfoServiceTempLayer.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : InfoServiceTempLayer.class$anon$infoservice$MixCascade).getEntryById(s);
            if (mixCascade2 != null && mixCascade2.getCertPath() != null) {
                final CertPath path = mixCascade2.getCertPath().getPath();
                if (path != null) {
                    final JAPCertificate secondCertificate = path.getSecondCertificate();
                    if (secondCertificate != null) {
                        return (ServiceOperator)Database.getInstance((InfoServiceTempLayer.class$anon$infoservice$ServiceOperator == null) ? (InfoServiceTempLayer.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : InfoServiceTempLayer.class$anon$infoservice$ServiceOperator).getEntryById(secondCertificate.getId());
                    }
                }
            }
            return null;
        }
        
        private static MixInfo getMixInfo(final MixCascade mixCascade, final String s) {
            if (mixCascade == null || s == null) {
                return null;
            }
            MixInfo mixInfo = mixCascade.getMixInfo(s);
            if (mixInfo == null || mixInfo.getVersionNumber() <= 0L) {
                final MixInfo mixInfo2 = (MixInfo)Database.getInstance((InfoServiceTempLayer.class$anon$infoservice$MixInfo == null) ? (InfoServiceTempLayer.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : InfoServiceTempLayer.class$anon$infoservice$MixInfo).getEntryById(s);
                if (mixInfo2 != null) {
                    mixInfo = mixInfo2;
                }
            }
            if ((mixInfo == null || mixInfo.getCertPath() == null) && mixCascade.getCertPath() != null) {
                mixInfo = new MixInfo(mixCascade.getCertPath());
            }
            return mixInfo;
        }
        
        private static PerformanceEntry getPerformanceEntry(final String s) {
            return PerformanceInfo.getLowestCommonBoundEntry(s);
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
    
    final class TempCascade
    {
        private String m_id;
        private String m_ports;
        private String m_hosts;
        private int m_maxUsers;
        
        public TempCascade(final String id, final String hosts, final String ports, final int maxUsers) {
            this.m_id = id;
            this.m_hosts = hosts;
            this.m_ports = ports;
            this.m_maxUsers = maxUsers;
        }
        
        public int getMaxUsers() {
            return this.m_maxUsers;
        }
        
        public String getId() {
            return this.m_id;
        }
        
        public String getPorts() {
            return this.m_ports;
        }
        
        public String getHosts() {
            return this.m_hosts;
        }
    }
    
    private class ManualPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        
        public ManualPanel(final JAPConfAnon japConfAnon) {
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints.anchor = 18;
            this.setLayout(layout);
            final JLabel label = new JLabel(JAPMessages.getString("manualServiceAddHost"));
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            this.add(label, gridBagConstraints);
            final JLabel label2 = new JLabel(JAPMessages.getString("manualServiceAddPort"));
            gridBagConstraints.gridy = 1;
            this.add(label2, gridBagConstraints);
            JAPConfAnon.this.m_manHostField = new JTextField();
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 3;
            this.add(JAPConfAnon.this.m_manHostField, gridBagConstraints);
            JAPConfAnon.this.m_manPortField = new JAPJIntField(65535);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = 0;
            this.add(JAPConfAnon.this.m_manPortField, gridBagConstraints);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 12;
            JAPConfAnon.this.m_editCascadeButton = new JButton(JAPMessages.getString("okButton"));
            JAPConfAnon.this.m_editCascadeButton.addActionListener(japConfAnon);
            gridBagConstraints.gridx = 1;
            this.add(JAPConfAnon.this.m_editCascadeButton, gridBagConstraints);
            JAPConfAnon.this.m_cancelCascadeButton = new JButton(JAPMessages.getString("cancelButton"));
            JAPConfAnon.this.m_cancelCascadeButton.addActionListener(japConfAnon);
            gridBagConstraints.gridx = 2;
            this.add(JAPConfAnon.this.m_cancelCascadeButton, gridBagConstraints);
            JAPConfAnon.this.m_manHostField.addKeyListener(japConfAnon);
            JAPConfAnon.this.m_manPortField.addKeyListener(japConfAnon);
        }
        
        public void setHostName(final String text) {
            JAPConfAnon.this.m_manHostField.setText(text);
        }
        
        public void setPort(final String text) {
            JAPConfAnon.this.m_manPortField.setText(text);
        }
    }
    
    private class ServerPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        private JLabel m_lblCascadeName;
        private JAPConfAnon m_listener;
        GridBagConstraints m_constraints;
        
        public ServerPanel(final JAPConfAnon listener) {
            this.m_listener = listener;
            final GridBagLayout layout = new GridBagLayout();
            this.m_constraints = new GridBagConstraints();
            this.setLayout(layout);
            this.m_constraints.gridx = 0;
            this.m_constraints.gridy = 0;
            this.m_constraints.anchor = 18;
            this.m_constraints.fill = 2;
            this.m_constraints.weightx = 1.0;
            this.m_constraints.weighty = 0.0;
            this.m_constraints.insets = new Insets(5, 10, 5, 5);
            this.m_constraints.gridy = 1;
            this.m_lblCascadeName = new JLabel();
            this.add(new JLabel(), this.m_constraints);
            this.m_constraints.gridy = 2;
            this.m_constraints.insets = new Insets(2, 20, 2, 2);
        }
        
        public void setCascadeName(String text) {
            GUIUtils.trim(text);
            if (text == null || text.length() < 1) {
                text = " ";
            }
            this.m_lblCascadeName.setText(text);
        }
        
        public void updateServerList(final int n, final boolean b, final int selectedIndex) {
            if (JAPConfAnon.this.m_serverList != null && JAPConfAnon.this.m_serverList.areMixButtonsEnabled() == b && JAPConfAnon.this.m_serverList.getNumberOfMixes() == n) {
                JAPConfAnon.this.m_serverList.setSelectedIndex(selectedIndex);
            }
            else {
                if (JAPConfAnon.this.m_serverList != null) {
                    this.remove(JAPConfAnon.this.m_serverList);
                    JAPConfAnon.this.m_serverList.removeItemListener(this.m_listener);
                    JAPConfAnon.this.m_serverList.setVisible(false);
                }
                JAPConfAnon.this.m_serverList = new ServerListPanel(n, b, selectedIndex);
                JAPConfAnon.this.m_serverList.addItemListener(this.m_listener);
            }
            this.add(JAPConfAnon.this.m_serverList, this.m_constraints);
        }
    }
    
    private class ServerInfoPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        
        public ServerInfoPanel(final JAPConfAnon japConfAnon) {
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            this.setLayout(layout);
            final JPanel panel = new JPanel(new GridBagLayout());
            gridBagConstraints.insets = new Insets(5, 10, 5, 5);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 18;
            gridBagConstraints.insets = new Insets(5, 20, 5, 0);
            this.add(panel, gridBagConstraints);
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.weightx = 1.0;
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(5, 0, 5, 0);
            JAPConfAnon.this.m_moveMixLeft = new JButton(GUIUtils.loadImageIcon("arrowLeft.png", true));
            JAPConfAnon.this.m_moveMixLeft.setBorder(BorderFactory.createRaisedBevelBorder());
            JAPConfAnon.this.m_moveMixLeft.addMouseListener(japConfAnon);
            panel.add(JAPConfAnon.this.m_moveMixLeft, gridBagConstraints2);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints2;
            ++gridBagConstraints3.gridx;
            gridBagConstraints2.weightx = 0.0;
            gridBagConstraints2.insets = new Insets(5, 5, 5, 0);
            panel.add(JAPConfAnon.this.m_lblMix, gridBagConstraints2);
            JAPConfAnon.this.m_moveMixRight = new JButton(GUIUtils.loadImageIcon("arrowRight.png", true));
            JAPConfAnon.this.m_moveMixRight.setBorder(BorderFactory.createRaisedBevelBorder());
            JAPConfAnon.this.m_moveMixRight.addMouseListener(japConfAnon);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints2;
            ++gridBagConstraints4.gridx;
            gridBagConstraints2.weightx = 1.0;
            panel.add(JAPConfAnon.this.m_moveMixRight, gridBagConstraints2);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints2;
            ++gridBagConstraints5.gridx;
            gridBagConstraints2.weightx = 0.0;
            panel.add(JAPConfAnon.this.m_lblMixOfService, gridBagConstraints2);
            final JLabel label = new JLabel(JAPMessages.getString(MixDetailsDialog.MSG_MIX_NAME) + ":");
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            ++gridBagConstraints6.gridy;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(5, 30, 5, 5);
            this.add(label, gridBagConstraints);
            JAPConfAnon.this.m_nrPanel = new JPanel(new GridBagLayout());
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.insets = new Insets(5, 30, 5, 0);
            this.add(JAPConfAnon.this.m_nrPanel, gridBagConstraints);
            gridBagConstraints.gridx = 3;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            this.add(new JLabel(), gridBagConstraints);
            final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            JAPConfAnon.this.m_nrLabel = new JLabel();
            gridBagConstraints7.gridx = 0;
            gridBagConstraints7.gridy = 0;
            gridBagConstraints7.weightx = 0.0;
            gridBagConstraints7.insets = new Insets(0, 0, 0, 5);
            JAPConfAnon.this.m_nrPanel.add(JAPConfAnon.this.m_nrLabel, gridBagConstraints7);
            final JLabel label2 = new JLabel(JAPMessages.getString(MixDetailsDialog.MSG_LOCATION) + ":");
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridy;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(5, 30, 5, 5);
            this.add(label2, gridBagConstraints);
            JAPConfAnon.this.m_locationLabel = new JLabel();
            JAPConfAnon.this.m_locationLabel.addMouseListener(japConfAnon);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.insets = new Insets(5, 30, 5, 0);
            this.add(JAPConfAnon.this.m_locationLabel, gridBagConstraints);
            final JLabel label3 = new JLabel(JAPMessages.getString("mixOperator"));
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
            ++gridBagConstraints9.gridy;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(5, 30, 5, 5);
            this.add(label3, gridBagConstraints);
            JAPConfAnon.this.m_operatorLabel = new JLabel();
            JAPConfAnon.this.m_operatorLabel.addMouseListener(japConfAnon);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.fill = 2;
            gridBagConstraints.insets = new Insets(5, 30, 5, 0);
            gridBagConstraints.gridwidth = 2;
            this.add(JAPConfAnon.this.m_operatorLabel, gridBagConstraints);
            JAPConfAnon.this.m_pnlMixInfoButtons = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.gridx = 0;
            gridBagConstraints10.gridy = 0;
            gridBagConstraints10.anchor = 17;
            gridBagConstraints10.fill = 2;
            JAPConfAnon.this.m_btnViewCert = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_CERTIFICATES));
            JAPConfAnon.this.m_btnViewCert.addMouseListener(japConfAnon);
            gridBagConstraints10.insets = new Insets(5, 15, 5, 0);
            JAPConfAnon.this.m_pnlMixInfoButtons.add(JAPConfAnon.this.m_btnViewCert, gridBagConstraints10);
            JAPConfAnon.this.m_btnEmail = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_E_MAIL));
            JAPConfAnon.this.m_btnEmail.addMouseListener(japConfAnon);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints10;
            ++gridBagConstraints11.gridx;
            gridBagConstraints10.insets = new Insets(5, 5, 5, 0);
            JAPConfAnon.this.m_pnlMixInfoButtons.add(JAPConfAnon.this.m_btnEmail, gridBagConstraints10);
            JAPConfAnon.this.m_btnHomepage = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_HOMEPAGE));
            JAPConfAnon.this.m_btnHomepage.addMouseListener(japConfAnon);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints10;
            ++gridBagConstraints12.gridx;
            JAPConfAnon.this.m_pnlMixInfoButtons.add(JAPConfAnon.this.m_btnHomepage, gridBagConstraints10);
            JAPConfAnon.this.m_btnMap = new JButton(JAPMessages.getString(JAPConfAnon.MSG_SHOW_ON_MAP));
            JAPConfAnon.this.m_btnMap.addMouseListener(japConfAnon);
            final GridBagConstraints gridBagConstraints13 = gridBagConstraints10;
            ++gridBagConstraints13.gridx;
            JAPConfAnon.this.m_pnlMixInfoButtons.add(JAPConfAnon.this.m_btnMap, gridBagConstraints10);
            JAPConfAnon.this.m_btnDataRetention = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_BTN_DATA_RETENTION), GUIUtils.loadImageIcon("certs/invalid.png", true));
            JAPConfAnon.this.m_btnDataRetention.addMouseListener(japConfAnon);
            final GridBagConstraints gridBagConstraints14 = gridBagConstraints10;
            ++gridBagConstraints14.gridx;
            JAPConfAnon.this.m_pnlMixInfoButtons.add(JAPConfAnon.this.m_btnDataRetention, gridBagConstraints10);
            final GridBagConstraints gridBagConstraints15 = gridBagConstraints10;
            ++gridBagConstraints15.gridx;
            gridBagConstraints10.weightx = 1.0;
            JAPConfAnon.this.m_pnlMixInfoButtons.add(new JLabel(""), gridBagConstraints10);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
            ++gridBagConstraints16.gridy;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.fill = 0;
            this.add(JAPConfAnon.this.m_pnlMixInfoButtons, gridBagConstraints);
        }
    }
    
    private class FilterPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        
        public FilterPanel(final JAPConfAnon japConfAnon) {
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.setLayout(layout);
            JAPConfAnon.this.m_opPopupMenu = new JPopupMenu();
            final JMenuItem menuItem = new JMenuItem(JAPMessages.getString(JAPConfAnon.MSG_FILTER_SELECT_ALL_OPERATORS));
            menuItem.addActionListener(japConfAnon);
            menuItem.setActionCommand(JAPConfAnon.MSG_FILTER_SELECT_ALL_OPERATORS);
            JAPConfAnon.this.m_opPopupMenu.add(menuItem);
            final JLabel label = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER) + ":");
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(0, 0, 5, 5);
            gridBagConstraints.anchor = 17;
            this.add(label, gridBagConstraints);
            JAPConfAnon.this.m_filterNameField = new JTextField();
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridx;
            gridBagConstraints.fill = 2;
            gridBagConstraints.insets = new Insets(0, 0, 5, 0);
            gridBagConstraints.gridwidth = 2;
            this.add(JAPConfAnon.this.m_filterNameField, gridBagConstraints);
            gridBagConstraints.weightx = 0.0;
            final TitledBorder border = new TitledBorder(JAPMessages.getString(JAPConfAnon.MSG_FILTER_CASCADES));
            final JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.setBorder(border);
            JAPConfAnon.this.m_filterAllMixes = new JRadioButton(JAPMessages.getString(JAPConfAnon.MSG_FILTER_ALL));
            JAPConfAnon.this.m_filterAllMixes.setActionCommand(String.valueOf(0));
            JAPConfAnon.this.m_filterAllMixes.setSelected(true);
            JAPConfAnon.this.m_filterAllMixes.addActionListener(japConfAnon);
            panel.add(JAPConfAnon.this.m_filterAllMixes, gridBagConstraints);
            JAPConfAnon.this.m_filterAtLeast2Mixes = new JRadioButton(JAPMessages.getString(JAPConfAnon.MSG_FILTER_AT_LEAST_2_MIXES));
            JAPConfAnon.this.m_filterAtLeast2Mixes.setActionCommand(String.valueOf(2));
            JAPConfAnon.this.m_filterAtLeast2Mixes.addActionListener(japConfAnon);
            panel.add(JAPConfAnon.this.m_filterAtLeast2Mixes, gridBagConstraints);
            JAPConfAnon.this.m_filterAtLeast3Mixes = new JRadioButton(JAPMessages.getString(JAPConfAnon.MSG_FILTER_AT_LEAST_3_MIXES));
            JAPConfAnon.this.m_filterAtLeast3Mixes.setActionCommand(String.valueOf(1));
            JAPConfAnon.this.m_filterAtLeast3Mixes.addActionListener(japConfAnon);
            panel.add(JAPConfAnon.this.m_filterAtLeast3Mixes, gridBagConstraints);
            JAPConfAnon.this.m_filterCascadeGroup = new ButtonGroup();
            JAPConfAnon.this.m_filterCascadeGroup.add(JAPConfAnon.this.m_filterAllMixes);
            JAPConfAnon.this.m_filterCascadeGroup.add(JAPConfAnon.this.m_filterAtLeast2Mixes);
            JAPConfAnon.this.m_filterCascadeGroup.add(JAPConfAnon.this.m_filterAtLeast3Mixes);
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
            gridBagConstraints.weightx = 0.4;
            this.add(panel, gridBagConstraints);
            final TitledBorder border2 = new TitledBorder(JAPMessages.getString(JAPConfAnon.MSG_FILTER_INTERNATIONALITY));
            final JPanel panel2 = new JPanel(new GridLayout(0, 1));
            panel2.setBorder(border2);
            JAPConfAnon.this.m_filterAllCountries = new JRadioButton(JAPMessages.getString(JAPConfAnon.MSG_FILTER_ALL));
            JAPConfAnon.this.m_filterAllCountries.setActionCommand(String.valueOf(0));
            JAPConfAnon.this.m_filterAllCountries.setSelected(true);
            JAPConfAnon.this.m_filterAllCountries.addActionListener(japConfAnon);
            panel2.add(JAPConfAnon.this.m_filterAllCountries, gridBagConstraints);
            JAPConfAnon.this.m_filterAtLeast2Countries = new JRadioButton(JAPMessages.getString(JAPConfAnon.MSG_FILTER_AT_LEAST_2_COUNTRIES));
            JAPConfAnon.this.m_filterAtLeast2Countries.setActionCommand(String.valueOf(3));
            JAPConfAnon.this.m_filterAtLeast2Countries.addActionListener(japConfAnon);
            panel2.add(JAPConfAnon.this.m_filterAtLeast2Countries);
            JAPConfAnon.this.m_filterAtLeast3Countries = new JRadioButton(JAPMessages.getString(JAPConfAnon.MSG_FILTER_AT_LEAST_3_COUNTRIES));
            JAPConfAnon.this.m_filterAtLeast3Countries.setActionCommand(String.valueOf(3));
            JAPConfAnon.this.m_filterAtLeast3Countries.addActionListener(japConfAnon);
            panel2.add(JAPConfAnon.this.m_filterAtLeast3Countries);
            JAPConfAnon.this.m_filterInternationalGroup = new ButtonGroup();
            JAPConfAnon.this.m_filterInternationalGroup.add(JAPConfAnon.this.m_filterAllCountries);
            JAPConfAnon.this.m_filterInternationalGroup.add(JAPConfAnon.this.m_filterAtLeast2Countries);
            JAPConfAnon.this.m_filterInternationalGroup.add(JAPConfAnon.this.m_filterAtLeast3Countries);
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            gridBagConstraints4.gridx += 2;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weightx = 0.15;
            this.add(panel2, gridBagConstraints);
            final JPanel panel3 = new JPanel(new GridBagLayout());
            panel3.setEnabled(false);
            panel3.setBorder(new TitledBorder(JAPMessages.getString(JAPConfAnon.MSG_FILTER_SPEED)));
            final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridx = 0;
            gridBagConstraints5.gridy = 0;
            gridBagConstraints5.anchor = 18;
            gridBagConstraints5.insets = new Insets(0, 5, 5, 0);
            gridBagConstraints5.weightx = 1.0;
            panel3.add(new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER_AT_LEAST)), gridBagConstraints5);
            JAPConfAnon.this.m_filterSpeedSlider = new JSlider(1);
            JAPConfAnon.this.m_filterSpeedSlider.setMinimum(0);
            JAPConfAnon.this.m_filterSpeedSlider.setMaximum(4);
            JAPConfAnon.this.m_filterSpeedSlider.setValue(0);
            JAPConfAnon.this.m_filterSpeedSlider.setMajorTickSpacing(1);
            JAPConfAnon.this.m_filterSpeedSlider.setPaintLabels(true);
            JAPConfAnon.this.m_filterSpeedSlider.setPaintTicks(true);
            JAPConfAnon.this.m_filterSpeedSlider.setInverted(true);
            JAPConfAnon.this.m_filterSpeedSlider.setSnapToTicks(true);
            final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>(5);
            for (int i = 0; i < 5; ++i) {
                JLabel label2;
                if (i == 0) {
                    label2 = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER_ALL));
                }
                else {
                    label2 = new JLabel(i * 100 + " kbit/s");
                }
                labelTable.put(new Integer(i), label2);
            }
            JAPConfAnon.this.m_filterSpeedSlider.setLabelTable(labelTable);
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints5;
            ++gridBagConstraints6.gridy;
            gridBagConstraints5.weighty = 1.0;
            gridBagConstraints5.fill = 3;
            panel3.add(JAPConfAnon.this.m_filterSpeedSlider, gridBagConstraints5);
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            gridBagConstraints.gridheight = 2;
            gridBagConstraints.weightx = 0.175;
            this.add(panel3, gridBagConstraints);
            final JPanel panel4 = new JPanel(new GridBagLayout());
            panel4.setEnabled(false);
            panel4.setBorder(new TitledBorder(JAPMessages.getString(JAPConfAnon.MSG_FILTER_LATENCY)));
            final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.gridx = 0;
            gridBagConstraints8.gridy = 0;
            gridBagConstraints8.anchor = 18;
            gridBagConstraints8.weightx = 1.0;
            gridBagConstraints8.insets = new Insets(0, 5, 5, 0);
            panel4.add(new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER_AT_MOST)), gridBagConstraints8);
            JAPConfAnon.this.m_filterLatencySlider = new JSlider(1);
            JAPConfAnon.this.m_filterLatencySlider.setMinimum(0);
            JAPConfAnon.this.m_filterLatencySlider.setMaximum(4);
            final Hashtable<Integer, JLabel> labelTable2 = new Hashtable<Integer, JLabel>(5);
            for (int j = 0; j < 5; ++j) {
                JLabel label3;
                if (j == 0) {
                    label3 = new JLabel(JAPMessages.getString(JAPConfAnon.MSG_FILTER_ALL));
                }
                else {
                    label3 = new JLabel(5000 - j * 1000 + " ms");
                }
                labelTable2.put(new Integer(j), label3);
            }
            JAPConfAnon.this.m_filterLatencySlider.setLabelTable(labelTable2);
            JAPConfAnon.this.m_filterLatencySlider.setMajorTickSpacing(1);
            JAPConfAnon.this.m_filterLatencySlider.setMinorTickSpacing(1);
            JAPConfAnon.this.m_filterLatencySlider.setValue(0);
            JAPConfAnon.this.m_filterLatencySlider.setPaintLabels(true);
            JAPConfAnon.this.m_filterLatencySlider.setPaintTicks(true);
            JAPConfAnon.this.m_filterLatencySlider.setInverted(true);
            JAPConfAnon.this.m_filterLatencySlider.setSnapToTicks(true);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints8;
            ++gridBagConstraints9.gridy;
            gridBagConstraints8.weighty = 1.0;
            gridBagConstraints8.fill = 3;
            panel4.add(JAPConfAnon.this.m_filterLatencySlider, gridBagConstraints8);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
            ++gridBagConstraints10.gridx;
            gridBagConstraints.gridheight = 2;
            gridBagConstraints.weightx = 0.275;
            this.add(panel4, gridBagConstraints);
            final JPanel panel5 = new JPanel(new GridLayout());
            panel5.setBorder(new TitledBorder(JAPMessages.getString(JAPConfAnon.MSG_FILTER_OPERATORS)));
            JAPConfAnon.this.m_listOperators = new JTable();
            JAPConfAnon.this.m_listOperators.setModel(new OperatorsTableModel());
            JAPConfAnon.this.m_listOperators.setTableHeader(null);
            JAPConfAnon.this.m_listOperators.setIntercellSpacing(new Dimension(0, 0));
            JAPConfAnon.this.m_listOperators.setShowGrid(false);
            JAPConfAnon.this.m_listOperators.setSelectionMode(0);
            JAPConfAnon.this.m_listOperators.addMouseListener(japConfAnon);
            JAPConfAnon.this.m_listOperators.getColumnModel().getColumn(0).setMaxWidth(1);
            JAPConfAnon.this.m_listOperators.getColumnModel().getColumn(0).setPreferredWidth(1);
            JAPConfAnon.this.m_listOperators.getColumnModel().getColumn(1).setCellRenderer(new OperatorsCellRenderer());
            final JScrollPane scrollPane = new JScrollPane(JAPConfAnon.this.m_listOperators);
            scrollPane.setHorizontalScrollBarPolicy(31);
            scrollPane.setPreferredSize(new Dimension(130, 30));
            panel5.add(scrollPane);
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
            ++gridBagConstraints11.gridy;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 0.4;
            gridBagConstraints.weighty = 0.7;
            this.add(panel5, gridBagConstraints);
            final TitledBorder border3 = new TitledBorder(JAPMessages.getString(JAPConfAnon.MSG_FILTER_OTHER));
            final JPanel panel6 = new JPanel(new GridLayout(0, 1));
            panel6.setBorder(border3);
            JAPConfAnon.this.m_cbxSocks5 = new JCheckBox(JAPMessages.getString(JAPConfAnon.MSG_FILTER_SOCKS_ONLY), false);
            JAPConfAnon.this.m_cbxDataRetention = new JCheckBox(JAPMessages.getString(JAPConfAnon.MSG_FILTER_NO_DATA_RETENTION), false);
            if (JAPModel.getInstance().getContext().equals("jondonym")) {
                JAPConfAnon.this.m_cbxFreeOfCharge = new JCheckBox(JAPMessages.getString(JAPConfAnon.MSG_FILTER_NO_PAYMENT_ONLY), false);
            }
            else {
                JAPConfAnon.this.m_cbxFreeOfCharge = new JCheckBox(JAPMessages.getString(JAPConfAnon.MSG_FILTER_BUSINESS_ONLY), false);
            }
            panel6.add(JAPConfAnon.this.m_cbxSocks5);
            panel6.add(JAPConfAnon.this.m_cbxDataRetention);
            panel6.add(JAPConfAnon.this.m_cbxFreeOfCharge);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
            gridBagConstraints12.gridx += 2;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weightx = 0.15;
            this.add(panel6, gridBagConstraints);
        }
        
        private void selectRadioButton(final ButtonGroup buttonGroup, final String s) {
            final Enumeration<AbstractButton> elements = buttonGroup.getElements();
            while (elements.hasMoreElements()) {
                final AbstractButton abstractButton = elements.nextElement();
                if (s.equals(abstractButton.getActionCommand())) {
                    buttonGroup.setSelected(abstractButton.getModel(), true);
                    break;
                }
            }
        }
    }
    
    class MixCascadeCellRenderer extends DefaultTableCellRenderer
    {
        private static final long serialVersionUID = 1L;
        
        public void setValue(final Object o) {
            if (o == null) {
                this.setText("");
                return;
            }
            if (o instanceof MixCascade) {
                final MixCascade mixCascade = (MixCascade)o;
                this.setToolTipText(JAPMessages.getString("cascadeReachableBy") + ": " + InfoServiceTempLayer.getHosts(mixCascade) + " - " + JAPMessages.getString("cascadePorts") + ": " + InfoServiceTempLayer.getPorts(mixCascade));
                ImageIcon icon;
                if (mixCascade.isUserDefined()) {
                    if (TrustModel.getCurrentTrustModel().isTrusted(mixCascade)) {
                        icon = GUIUtils.loadImageIcon("servermanuell.gif", true);
                        this.setForeground(Color.black);
                    }
                    else {
                        icon = GUIUtils.loadImageIcon("cdisabled.gif", true);
                        this.setForeground(Color.gray);
                    }
                }
                else if (mixCascade.isPayment()) {
                    if (TrustModel.getCurrentTrustModel().isTrusted(mixCascade)) {
                        icon = GUIUtils.loadImageIcon("serverwithpayment.gif", true);
                        this.setForeground(Color.black);
                    }
                    else {
                        icon = GUIUtils.loadImageIcon("cdisabled.gif", true);
                        this.setForeground(Color.gray);
                    }
                }
                else if (TrustModel.getCurrentTrustModel().isTrusted(mixCascade)) {
                    icon = GUIUtils.loadImageIcon("serverfrominternet.gif", true);
                    this.setForeground(Color.black);
                }
                else {
                    icon = GUIUtils.loadImageIcon("cdisabled.gif", true);
                    this.setForeground(Color.gray);
                }
                if (mixCascade.isSocks5Supported()) {
                    icon = GUIUtils.combine(icon, GUIUtils.loadImageIcon("socks_icon.gif", true));
                }
                this.setIcon(icon);
                if (mixCascade.equals(JAPController.getInstance().getCurrentMixCascade())) {
                    GUIUtils.setFontStyle(this, 1);
                }
                else {
                    GUIUtils.setFontStyle(this, 0);
                }
            }
            this.setText(o.toString());
        }
    }
    
    private class MixCascadeTableModel extends AbstractTableModel
    {
        private static final long serialVersionUID = 1L;
        public final Object SYNC_UPDATE_SERVER_PANEL;
        private Vector m_vecCascades;
        private String[] columnNames;
        private Class[] columnClasses;
        static /* synthetic */ Class class$java$lang$Boolean;
        static /* synthetic */ Class class$java$lang$Object;
        static /* synthetic */ Class class$anon$infoservice$MixCascade;
        static /* synthetic */ Class class$anon$infoservice$BlacklistedCascadeIDEntry;
        
        private MixCascadeTableModel() {
            this.SYNC_UPDATE_SERVER_PANEL = new Object();
            this.columnNames = new String[] { "B", "Cascade" };
            this.columnClasses = new Class[] { (MixCascadeTableModel.class$java$lang$Boolean == null) ? (MixCascadeTableModel.class$java$lang$Boolean = class$("java.lang.Boolean")) : MixCascadeTableModel.class$java$lang$Boolean, (MixCascadeTableModel.class$java$lang$Object == null) ? (MixCascadeTableModel.class$java$lang$Object = class$("java.lang.Object")) : MixCascadeTableModel.class$java$lang$Object };
            this.update();
        }
        
        public synchronized void addElement(final MixCascade mixCascade) {
            this.m_vecCascades.addElement(mixCascade);
            this.fireTableDataChanged();
        }
        
        public synchronized void update() {
            final int selectedRow = JAPConfAnon.this.m_tableMixCascade.getSelectedRow();
            Object o = null;
            if (selectedRow >= 0) {
                o = this.getValueAt(selectedRow, 1);
            }
            this.m_vecCascades = Database.getInstance((MixCascadeTableModel.class$anon$infoservice$MixCascade == null) ? (MixCascadeTableModel.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : MixCascadeTableModel.class$anon$infoservice$MixCascade).getSortedEntryList(new Util.Comparable() {
                public int compare(final Object o, final Object o2) {
                    if (o == null && o2 == null) {
                        return 0;
                    }
                    if (o == null) {
                        return 1;
                    }
                    final boolean trusted = TrustModel.getCurrentTrustModel().isTrusted((MixCascade)o);
                    final boolean trusted2 = TrustModel.getCurrentTrustModel().isTrusted((MixCascade)o2);
                    if (trusted == trusted2) {
                        return o.toString().compareTo(o2.toString());
                    }
                    if (trusted && !trusted2) {
                        return -1;
                    }
                    return 1;
                }
            });
            final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
            if (!this.m_vecCascades.contains(currentMixCascade)) {
                this.m_vecCascades.addElement(currentMixCascade);
            }
            this.fireTableDataChanged();
            synchronized (this.SYNC_UPDATE_SERVER_PANEL) {
                JAPConfAnon.this.m_bUpdateServerPanel = (JAPConfAnon.this.m_manualPanel == null || !JAPConfAnon.this.m_manualPanel.isVisible());
                int index = -1;
                if (o != null) {
                    index = this.m_vecCascades.indexOf(o);
                }
                if ((o == null || index < 0) && JAPConfAnon.this.m_tableMixCascade.getRowCount() > 0) {
                    index = 0;
                }
                if (JAPConfAnon.this.m_tableMixCascade.getSelectedRow() != index) {
                    JAPConfAnon.this.m_tableMixCascade.setRowSelectionInterval(index, index);
                }
                JAPConfAnon.this.m_bUpdateServerPanel = true;
            }
        }
        
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        public int getRowCount() {
            return this.m_vecCascades.size();
        }
        
        public synchronized void setSelectedCascade(final MixCascade mixCascade) {
            if (mixCascade == null) {
                return;
            }
            final int index = this.m_vecCascades.indexOf(mixCascade);
            if (index >= 0) {
                JAPConfAnon.this.m_tableMixCascade.setRowSelectionInterval(index, index);
                JAPConfAnon.this.m_tableMixCascade.scrollRectToVisible(JAPConfAnon.this.m_tableMixCascade.getCellRect(index, index, true));
            }
        }
        
        public Object getValueAt(final int n, final int n2) {
            MixCascade mixCascade;
            try {
                mixCascade = this.m_vecCascades.elementAt(n);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                return null;
            }
            if (mixCascade == null) {
                return null;
            }
            if (n2 != 0) {
                return mixCascade;
            }
            if (Database.getInstance((MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry).getEntryById(mixCascade.getMixIDsAsString()) == null) {
                return new Boolean(true);
            }
            return new Boolean(false);
        }
        
        public Class getColumnClass(final int n) {
            return this.columnClasses[n];
        }
        
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return n2 == 0;
        }
        
        public void setValueAt(final Object o, final int n, final int n2) {
            final MixCascade mixCascade = this.m_vecCascades.elementAt(n);
            if (Boolean.FALSE.equals(o)) {
                Database.getInstance((MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry).update(new BlacklistedCascadeIDEntry(mixCascade));
            }
            else {
                Database.getInstance((MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : MixCascadeTableModel.class$anon$infoservice$BlacklistedCascadeIDEntry).remove(mixCascade.getMixIDsAsString());
            }
            this.fireTableCellUpdated(n, 1);
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
    
    private class OperatorsTableModel extends AbstractTableModel
    {
        private static final long serialVersionUID = 1L;
        private Vector m_vecOperators;
        private Vector m_vecBlacklist;
        private String[] columnNames;
        private Class[] columnClasses;
        static /* synthetic */ Class class$java$lang$Boolean;
        static /* synthetic */ Class class$java$lang$Object;
        static /* synthetic */ Class class$anon$client$TrustModel$OperatorBlacklistAttribute;
        static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
        
        private OperatorsTableModel() {
            this.m_vecOperators = new Vector();
            this.m_vecBlacklist = new Vector();
            this.columnNames = new String[] { "B", "Operator" };
            this.columnClasses = new Class[] { (OperatorsTableModel.class$java$lang$Boolean == null) ? (OperatorsTableModel.class$java$lang$Boolean = class$("java.lang.Boolean")) : OperatorsTableModel.class$java$lang$Boolean, (OperatorsTableModel.class$java$lang$Object == null) ? (OperatorsTableModel.class$java$lang$Object = class$("java.lang.Object")) : OperatorsTableModel.class$java$lang$Object };
        }
        
        public int getRowCount() {
            return this.m_vecOperators.size();
        }
        
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return n2 == 0;
        }
        
        public synchronized void update() {
            if (JAPConfAnon.this.m_trustModelCopy != null) {
                this.m_vecBlacklist = (Vector)((Vector)JAPConfAnon.this.m_trustModelCopy.getAttribute((OperatorsTableModel.class$anon$client$TrustModel$OperatorBlacklistAttribute == null) ? (OperatorsTableModel.class$anon$client$TrustModel$OperatorBlacklistAttribute = class$("anon.client.TrustModel$OperatorBlacklistAttribute")) : OperatorsTableModel.class$anon$client$TrustModel$OperatorBlacklistAttribute).getConditionValue()).clone();
            }
            this.m_vecOperators = Database.getInstance((OperatorsTableModel.class$anon$infoservice$ServiceOperator == null) ? (OperatorsTableModel.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : OperatorsTableModel.class$anon$infoservice$ServiceOperator).getSortedEntryList(new Util.Comparable() {
                private final /* synthetic */ OperatorsTableModel this$1 = this$1;
                
                public int compare(final Object o, final Object o2) {
                    if (o == null || o2 == null || ((ServiceOperator)o).getOrganization() == null || ((ServiceOperator)o2).getOrganization() == null) {
                        return 0;
                    }
                    final boolean contains = this.this$1.m_vecBlacklist.contains(o);
                    final boolean contains2 = this.this$1.m_vecBlacklist.contains(o2);
                    if (contains == contains2) {
                        return ((ServiceOperator)o).getOrganization().compareTo(((ServiceOperator)o2).getOrganization());
                    }
                    if (contains && !contains2) {
                        return -1;
                    }
                    return 1;
                }
            });
        }
        
        public synchronized void reset() {
            JAPConfAnon.this.m_trustModelCopy.setAttribute((OperatorsTableModel.class$anon$client$TrustModel$OperatorBlacklistAttribute == null) ? (OperatorsTableModel.class$anon$client$TrustModel$OperatorBlacklistAttribute = class$("anon.client.TrustModel$OperatorBlacklistAttribute")) : OperatorsTableModel.class$anon$client$TrustModel$OperatorBlacklistAttribute, 6, new Vector());
            this.update();
        }
        
        public Class getColumnClass(final int n) {
            return this.columnClasses[n];
        }
        
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public Object getValueAt(final int n, final int n2) {
            try {
                if (n2 == 0) {
                    return new Boolean(!this.m_vecBlacklist.contains(this.m_vecOperators.elementAt(n)));
                }
                if (n2 == 1) {
                    return this.m_vecOperators.elementAt(n);
                }
            }
            catch (Exception ex) {}
            return null;
        }
        
        public void setValueAt(final Object o, final int n, final int n2) {
            if (n2 == 0) {
                try {
                    final Object element = this.m_vecOperators.elementAt(n);
                    if (o == Boolean.FALSE) {
                        if (!this.m_vecBlacklist.contains(element)) {
                            this.m_vecBlacklist.addElement(element);
                        }
                    }
                    else {
                        this.m_vecBlacklist.removeElement(element);
                    }
                }
                catch (Exception ex) {}
            }
        }
        
        public Vector getBlacklist() {
            return this.m_vecBlacklist;
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
    
    private class LocalAnonServiceEventListener implements AnonServiceEventListener
    {
        public void connectionError() {
        }
        
        public void disconnected() {
            JAPConfAnon.this.updateValues(false);
        }
        
        public void connecting(final AnonServerDescription anonServerDescription) {
        }
        
        public void connectionEstablished(final AnonServerDescription anonServerDescription) {
            JAPConfAnon.this.updateValues(false);
        }
        
        public void packetMixed(final long n) {
        }
        
        public void dataChainErrorSignaled() {
        }
    }
}
