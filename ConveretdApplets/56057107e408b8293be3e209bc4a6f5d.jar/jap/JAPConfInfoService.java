// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JSeparator;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.util.Date;
import anon.infoservice.ListenerInterface;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import gui.MultiCertOverview;
import gui.MixDetailsDialog;
import anon.infoservice.AbstractDatabaseEntry;
import gui.dialog.JAPDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import gui.GUIUtils;
import javax.swing.ListCellRenderer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ListModel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.Icon;
import anon.util.JAPMessages;
import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.InfoServiceHolderMessage;
import anon.infoservice.InfoServiceHolder;
import java.util.Vector;
import javax.swing.SwingUtilities;
import anon.infoservice.DatabaseMessage;
import anon.infoservice.Database;
import java.util.Observable;
import anon.infoservice.InfoServiceDBEntry;
import javax.swing.JTabbedPane;
import anon.crypto.MultiCertPath;
import javax.swing.DefaultListModel;
import gui.JAPHtmlMultiLineLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import gui.JAPJIntField;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import gui.JAPMultilineLabel;
import java.util.Observer;

public class JAPConfInfoService extends AbstractJAPConfModule implements Observer
{
    public static final String MSG_CONNECT_TIMEOUT;
    private static final String MSG_ALLOW_DIRECT_CONNECTION;
    private static final String MSG_VIEW_CERT;
    private static final String MSG_REALLY_DELETE;
    private static final String MSG_USE_MORE_IS;
    private static final String MSG_EXPLANATION;
    private static final String MSG_ALL_INFO_SERVICES;
    private static final String MSG_INACTIVE;
    private static final Integer[] CONNECT_TIMEOUTS;
    private JAPMultilineLabel m_hostLabel;
    private JLabel m_portLabel;
    private JLabel m_lblInactive;
    private JList m_listKnownInfoServices;
    private JTextField addInfoServiceHostField;
    private JAPJIntField addInfoServicePortField;
    private JTextField addInfoServiceNameField;
    private JPanel addInfoServicePanel;
    private JPanel descriptionPanel;
    private JButton settingsInfoServiceConfigBasicSettingsRemoveButton;
    private JCheckBox m_allowAutomaticIS;
    private JComboBox m_comboAnonymousConnection;
    private JCheckBox m_cbxUseRedundantISRequests;
    private JComboBox m_cmbAskedInfoServices;
    private JAPHtmlMultiLineLabel m_lblExplanation;
    private JAPHtmlMultiLineLabel m_settingsInfoServiceConfigBasicSettingsDescriptionLabel;
    private DefaultListModel knownInfoServicesListModel;
    private boolean mb_newInfoService;
    private MultiCertPath m_selectedISCertPaths;
    private String m_selectedISName;
    private JTabbedPane m_infoServiceTabPane;
    private JComboBox m_comboTimeout;
    static /* synthetic */ Class class$jap$JAPConfInfoService;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    
    public JAPConfInfoService() {
        super(new JAPConfInfoServiceSavePoint());
        this.mb_newInfoService = true;
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            JAPModel.getInstance().addObserver(this);
            final Observer observer = new Observer() {
                private boolean m_preferredInfoServiceIsAlsoInDatabase = false;
                private InfoServiceDBEntry m_currentPreferredInfoService = null;
                static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
                
                public void update(final Observable observable, final Object o) {
                    try {
                        if (observable == Database.getInstance((JAPConfInfoService$1.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoService$1.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoService$1.class$anon$infoservice$InfoServiceDBEntry)) {
                            final int messageCode = ((DatabaseMessage)o).getMessageCode();
                            if (messageCode == 1 || messageCode == 2) {
                                final InfoServiceDBEntry infoServiceDBEntry = (InfoServiceDBEntry)((DatabaseMessage)o).getMessageData();
                                synchronized (JAPConfInfoService.this.knownInfoServicesListModel) {
                                    final int index = JAPConfInfoService.this.knownInfoServicesListModel.indexOf(infoServiceDBEntry);
                                    if (index != -1) {
                                        JAPConfInfoService.this.knownInfoServicesListModel.setElementAt(infoServiceDBEntry, index);
                                        if (infoServiceDBEntry.equals(this.m_currentPreferredInfoService)) {
                                            this.m_preferredInfoServiceIsAlsoInDatabase = true;
                                        }
                                    }
                                    else if (infoServiceDBEntry.isUserDefined()) {
                                        JAPConfInfoService.this.knownInfoServicesListModel.addElement(infoServiceDBEntry);
                                    }
                                    else {
                                        final int access$100 = JAPConfInfoService.this.findFirstUserDefinedListModelEntry(JAPConfInfoService.this.knownInfoServicesListModel);
                                        if (SwingUtilities.isEventDispatchThread()) {
                                            JAPConfInfoService.this.knownInfoServicesListModel.add(access$100, infoServiceDBEntry);
                                        }
                                        else {
                                            SwingUtilities.invokeAndWait(new Test(infoServiceDBEntry, access$100));
                                        }
                                    }
                                    if (JAPConfInfoService.this.m_listKnownInfoServices.getSelectedValue() == null && infoServiceDBEntry.equals(this.m_currentPreferredInfoService)) {
                                        JAPConfInfoService.this.m_listKnownInfoServices.setSelectedValue(infoServiceDBEntry, true);
                                    }
                                }
                            }
                            if (messageCode == 3) {
                                final InfoServiceDBEntry infoServiceDBEntry2 = (InfoServiceDBEntry)((DatabaseMessage)o).getMessageData();
                                synchronized (JAPConfInfoService.this.knownInfoServicesListModel) {
                                    if (infoServiceDBEntry2.equals(this.m_currentPreferredInfoService)) {
                                        this.m_preferredInfoServiceIsAlsoInDatabase = false;
                                    }
                                    else {
                                        JAPConfInfoService.this.knownInfoServicesListModel.removeElement(infoServiceDBEntry2);
                                    }
                                }
                            }
                            if (messageCode == 4) {
                                synchronized (JAPConfInfoService.this.knownInfoServicesListModel) {
                                    JAPConfInfoService.this.knownInfoServicesListModel.clear();
                                    JAPConfInfoService.this.knownInfoServicesListModel.addElement(this.m_currentPreferredInfoService);
                                    JAPConfInfoService.this.m_listKnownInfoServices.setSelectedIndex(0);
                                    this.m_preferredInfoServiceIsAlsoInDatabase = false;
                                }
                            }
                            if (messageCode != 5) {
                                return;
                            }
                            final Enumeration<Object> elements = ((Vector)((DatabaseMessage)o).getMessageData()).elements();
                            synchronized (JAPConfInfoService.this.knownInfoServicesListModel) {
                                int n = 0;
                                while (elements.hasMoreElements()) {
                                    JAPConfInfoService.this.knownInfoServicesListModel.add(JAPConfInfoService.this.findFirstUserDefinedListModelEntry(JAPConfInfoService.this.knownInfoServicesListModel), elements.nextElement());
                                    ++n;
                                }
                                return;
                            }
                        }
                        if (observable == InfoServiceHolder.getInstance() && ((InfoServiceHolderMessage)o).getMessageCode() == 1) {
                            final InfoServiceDBEntry infoServiceDBEntry3 = (InfoServiceDBEntry)((InfoServiceHolderMessage)o).getMessageData();
                            synchronized (JAPConfInfoService.this.knownInfoServicesListModel) {
                                if (this.m_currentPreferredInfoService != null && this.m_currentPreferredInfoService.equals(infoServiceDBEntry3)) {
                                    final int index2 = JAPConfInfoService.this.knownInfoServicesListModel.indexOf(this.m_currentPreferredInfoService);
                                    if (index2 != -1) {
                                        JAPConfInfoService.this.knownInfoServicesListModel.setElementAt(infoServiceDBEntry3, index2);
                                    }
                                    this.m_currentPreferredInfoService = infoServiceDBEntry3;
                                }
                                else {
                                    if (this.m_currentPreferredInfoService != null) {
                                        if (this.m_preferredInfoServiceIsAlsoInDatabase) {
                                            final int index3 = JAPConfInfoService.this.knownInfoServicesListModel.indexOf(this.m_currentPreferredInfoService);
                                            if (index3 != -1) {
                                                JAPConfInfoService.this.knownInfoServicesListModel.setElementAt(JAPConfInfoService.this.knownInfoServicesListModel.elementAt(index3), index3);
                                            }
                                        }
                                        else {
                                            JAPConfInfoService.this.knownInfoServicesListModel.removeElement(this.m_currentPreferredInfoService);
                                        }
                                    }
                                    if ((this.m_currentPreferredInfoService = infoServiceDBEntry3) != null) {
                                        final int index4 = JAPConfInfoService.this.knownInfoServicesListModel.indexOf(infoServiceDBEntry3);
                                        if (index4 != -1) {
                                            this.m_preferredInfoServiceIsAlsoInDatabase = true;
                                            JAPConfInfoService.this.knownInfoServicesListModel.setElementAt(infoServiceDBEntry3, index4);
                                        }
                                        else {
                                            this.m_preferredInfoServiceIsAlsoInDatabase = false;
                                            this.update(Database.getInstance((JAPConfInfoService$1.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoService$1.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoService$1.class$anon$infoservice$InfoServiceDBEntry), new DatabaseMessage(1, infoServiceDBEntry3));
                                        }
                                    }
                                    final InfoServiceDBEntry infoServiceDBEntry4 = JAPConfInfoService.this.m_listKnownInfoServices.getSelectedValue();
                                    if (infoServiceDBEntry4 != null) {
                                        JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.setEnabled(infoServiceDBEntry4.isUserDefined() && !infoServiceDBEntry4.equals(infoServiceDBEntry3));
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.GUI, ex);
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
                
                final class Test implements Runnable
                {
                    int m_Index;
                    private final /* synthetic */ InfoServiceDBEntry val$updatedEntry;
                    
                    protected Test(final InfoServiceDBEntry val$updatedEntry, final int index) {
                        this.val$updatedEntry = val$updatedEntry;
                        this.m_Index = index;
                    }
                    
                    public void run() {
                        JAPConfInfoService.this.knownInfoServicesListModel.add(this.m_Index, this.val$updatedEntry);
                    }
                }
            };
            Database.getInstance((JAPConfInfoService.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoService.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoService.class$anon$infoservice$InfoServiceDBEntry).addObserver(observer);
            synchronized (InfoServiceHolder.getInstance()) {
                InfoServiceHolder.getInstance().addObserver(observer);
                observer.update(InfoServiceHolder.getInstance(), new InfoServiceHolderMessage(1, InfoServiceHolder.getInstance().getPreferredInfoService()));
            }
            final Observer observer2 = new Observer() {
                public void update(final Observable observable, final Object o) {
                    try {
                        if (observable == InfoServiceHolder.getInstance() && ((InfoServiceHolderMessage)o).getMessageCode() == 2) {
                            JAPConfInfoService.this.m_cbxUseRedundantISRequests.setSelected((boolean)((InfoServiceHolderMessage)o).getMessageData());
                        }
                        if (observable == JAPController.getInstance() && ((JAPControllerMessage)o).getMessageCode() == 1) {
                            JAPConfInfoService.this.m_allowAutomaticIS.setSelected(!JAPModel.isInfoServiceDisabled());
                        }
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.GUI, ex);
                    }
                }
            };
            synchronized (InfoServiceHolder.getInstance()) {
                InfoServiceHolder.getInstance().addObserver(observer2);
                observer2.update(InfoServiceHolder.getInstance(), new InfoServiceHolderMessage(2, new Boolean(InfoServiceHolder.getInstance().isChangeInfoServices())));
            }
            JAPController.getInstance().addObserver(observer2);
            observer2.update(JAPController.getInstance(), new JAPControllerMessage(1));
            return true;
        }
        return false;
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        synchronized (this) {
            (this.m_infoServiceTabPane = new JTabbedPane()).insertTab(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsTabTitle"), null, this.createInfoServiceConfigPanel(), null, 0);
            this.m_infoServiceTabPane.insertTab(JAPMessages.getString("settingsInfoServiceConfigAdvancedSettingsTabTitle"), null, this.createInfoServiceAdvancedPanel(), null, 1);
            rootPanel.setLayout(new GridBagLayout());
            rootPanel.add(this.m_infoServiceTabPane, AbstractJAPConfModule.createTabbedRootPanelContraints());
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("confTreeInfoServiceLeaf");
    }
    
    public void update(final Observable observable, final Object o) {
        if (o != null) {
            if (o.equals(JAPModel.CHANGED_ALLOW_INFOSERVICE_DIRECT_CONNECTION)) {
                this.m_comboAnonymousConnection.setSelectedIndex(JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting());
            }
            else if (o.equals(JAPModel.CHANGED_INFOSERVICE_AUTO_UPDATE)) {
                this.m_allowAutomaticIS.setSelected(!JAPModel.isInfoServiceDisabled());
            }
        }
    }
    
    private JPanel createInfoServiceConfigPanel() {
        final JPanel panel = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        this.descriptionPanel = new JPanel();
        this.addInfoServicePanel = new JPanel();
        this.knownInfoServicesListModel = new DefaultListModel();
        (this.m_listKnownInfoServices = new JList(this.knownInfoServicesListModel)).setSelectionMode(0);
        this.m_listKnownInfoServices.setFixedCellWidth(15 * this.m_listKnownInfoServices.getFontMetrics(this.m_listKnownInfoServices.getFont()).charWidth('W'));
        this.m_listKnownInfoServices.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    JAPConfInfoService.this.setPreferredInfoService();
                }
            }
        });
        this.m_listKnownInfoServices.setCellRenderer(new ListCellRenderer() {
            public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
                JLabel label;
                if (((InfoServiceDBEntry)o).isUserDefined()) {
                    label = new JLabel(((InfoServiceDBEntry)o).getName(), GUIUtils.loadImageIcon("infoservicemanuell.gif", true), 2);
                }
                else {
                    label = new JLabel(((InfoServiceDBEntry)o).getName(), GUIUtils.loadImageIcon("infoservicefrominternet.gif", true), 2);
                }
                label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle() & 0xFFFFFFFE, label.getFont().getSize()));
                final InfoServiceDBEntry preferredInfoService = InfoServiceHolder.getInstance().getPreferredInfoService();
                if (preferredInfoService != null && preferredInfoService.equals(o)) {
                    label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle() | 0x1, label.getFont().getSize()));
                }
                if (b) {
                    label.setOpaque(true);
                    label.setBackground(Color.lightGray);
                }
                return label;
            }
        });
        final JScrollPane scrollPane = new JScrollPane(this.m_listKnownInfoServices);
        scrollPane.setHorizontalScrollBarPolicy(31);
        final JButton button = new JButton(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsFetchInfoServicesButton"));
        button.setIcon(GUIUtils.loadImageIcon("reload.gif", true, false));
        button.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
        button.setPressedIcon(GUIUtils.loadImageIcon("reloadrollover.gif", true, false));
        button.addActionListener(new ActionListener() {
            private final /* synthetic */ JAPConfInfoService this$0;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                button.setEnabled(false);
                final Thread thread = new Thread(new Runnable() {
                    private final /* synthetic */ JAPConfInfoService$5 this$1 = this$1;
                    
                    public void run() {
                        while (!JAPController.getInstance().updateInfoServices(false)) {
                            if (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 1 && !JAPController.getInstance().isAnonConnected()) {
                                if (JAPDialog.showConfirmDialog(this.this$1.this$0.getRootPanel(), JAPMessages.getString(JAPController.MSG_IS_NOT_ALLOWED), 0, 0) != 0) {
                                    break;
                                }
                                JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(0);
                                JAPController.getInstance().updateInfoServices(false);
                            }
                            else {
                                if (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() != 2 || !JAPController.getInstance().isAnonConnected()) {
                                    JAPDialog.showErrorDialog(this.this$1.this$0.getRootPanel(), JAPMessages.getString("settingsInfoServiceConfigBasicSettingsFetchInfoServicesError"), LogType.MISC);
                                    break;
                                }
                                if (JAPDialog.showConfirmDialog(this.this$1.this$0.getRootPanel(), JAPMessages.getString(JAPController.MSG_IS_NOT_ALLOWED_FOR_ANONYMOUS), 0, 0) != 0) {
                                    break;
                                }
                                JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(0);
                                JAPController.getInstance().updateInfoServices(false);
                            }
                        }
                        try {
                            SwingUtilities.invokeAndWait(new Runnable() {
                                public void run() {
                                    button.setEnabled(true);
                                }
                            });
                        }
                        catch (Exception ex) {}
                    }
                });
                thread.setDaemon(true);
                thread.start();
            }
        });
        final JButton button2 = new JButton(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsSetPreferredButton"));
        button2.setEnabled(false);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfInfoService.this.setPreferredInfoService();
            }
        });
        final JButton button3 = new JButton(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsAddButton"));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.setEnabled(false);
                JAPConfInfoService.this.descriptionPanel.setVisible(false);
                JAPConfInfoService.this.addInfoServiceHostField.setText("");
                JAPConfInfoService.this.addInfoServiceNameField.setText("");
                JAPConfInfoService.this.addInfoServicePortField.setText("");
                JAPConfInfoService.this.addInfoServicePanel.setVisible(true);
                JAPConfInfoService.this.mb_newInfoService = true;
            }
        });
        (this.settingsInfoServiceConfigBasicSettingsRemoveButton = new JButton(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsRemoveButton"))).setEnabled(false);
        this.settingsInfoServiceConfigBasicSettingsRemoveButton.addActionListener(new ActionListener() {
            static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JAPDialog.showYesNoDialog(JAPConfInfoService.this.getRootPanel(), JAPMessages.getString(JAPConfInfoService.MSG_REALLY_DELETE))) {
                    final InfoServiceDBEntry infoServiceDBEntry = JAPConfInfoService.this.m_listKnownInfoServices.getSelectedValue();
                    if (infoServiceDBEntry != null) {
                        Database.getInstance((JAPConfInfoService$10.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoService$10.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoService$10.class$anon$infoservice$InfoServiceDBEntry).remove(infoServiceDBEntry);
                    }
                    JAPConfInfoService.this.m_listKnownInfoServices.setSelectedIndex(0);
                    JAPConfInfoService.this.addInfoServicePanel.setVisible(false);
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
        });
        final JButton button4 = new JButton(JAPMessages.getString(MixDetailsDialog.MSG_CERTIFICATES), GUIUtils.loadImageIcon("certs/trusted_black.png"));
        button4.setEnabled(false);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JAPConfInfoService.this.m_selectedISCertPaths != null) {
                    final MultiCertOverview multiCertOverview = new MultiCertOverview(JAPConfInfoService.this.getRootPanel().getParent(), JAPConfInfoService.this.m_selectedISCertPaths, JAPConfInfoService.this.m_selectedISName, true);
                }
            }
        });
        final JLabel label = new JLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsListLabel"));
        this.m_listKnownInfoServices.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {
                    final InfoServiceDBEntry infoServiceDBEntry = JAPConfInfoService.this.m_listKnownInfoServices.getSelectedValue();
                    if (infoServiceDBEntry != null) {
                        String s = "";
                        String s2 = "";
                        final Enumeration elements = infoServiceDBEntry.getListenerInterfaces().elements();
                        while (elements.hasMoreElements()) {
                            final ListenerInterface listenerInterface = elements.nextElement();
                            if (s.indexOf(listenerInterface.getHost()) == -1) {
                                if (!s.equals("")) {
                                    s += "\n";
                                }
                                s += listenerInterface.getHost();
                            }
                            if (s2.indexOf(Integer.toString(listenerInterface.getPort())) == -1) {
                                if (!s2.equals("")) {
                                    s2 += ", ";
                                }
                                s2 += Integer.toString(listenerInterface.getPort());
                            }
                        }
                        JAPConfInfoService.this.m_hostLabel.setText(s);
                        JAPConfInfoService.this.m_portLabel.setText(s2);
                        if (JAPConfInfoService.this.m_hostLabel.getRootPane() != null) {
                            JAPConfInfoService.this.m_hostLabel.getRootPane().revalidate();
                        }
                        if (infoServiceDBEntry.isUserDefined()) {
                            JAPConfInfoService.this.addInfoServiceHostField.setText(s);
                            JAPConfInfoService.this.addInfoServicePortField.setText(s2);
                            JAPConfInfoService.this.addInfoServiceNameField.setText(infoServiceDBEntry.getName());
                            JAPConfInfoService.this.descriptionPanel.setVisible(false);
                            JAPConfInfoService.this.addInfoServicePanel.setVisible(true);
                            JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.setEnabled(true);
                            JAPConfInfoService.this.mb_newInfoService = false;
                            button4.setEnabled(false);
                            button4.setIcon(null);
                            button4.setToolTipText(null);
                        }
                        else {
                            JAPConfInfoService.this.addInfoServicePanel.setVisible(false);
                            JAPConfInfoService.this.descriptionPanel.setVisible(true);
                            final MultiCertPath certPath = infoServiceDBEntry.getCertPath();
                            if (certPath == null) {
                                button4.setEnabled(false);
                                button4.setIcon(null);
                                button4.setForeground(JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.getForeground());
                                JAPConfInfoService.this.m_lblInactive.setVisible(false);
                            }
                            else {
                                button4.setEnabled(true);
                                if (!certPath.isVerified()) {
                                    button4.setIcon(GUIUtils.loadImageIcon("certs/not_trusted.png"));
                                    button4.setForeground(Color.red);
                                    JAPConfInfoService.this.m_lblInactive.setVisible(true);
                                }
                                else {
                                    button4.setForeground(JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.getForeground());
                                    if (!certPath.isValid(new Date())) {
                                        JAPConfInfoService.this.m_lblInactive.setVisible(true);
                                        button4.setIcon(GUIUtils.loadImageIcon("certs/invalid.png"));
                                    }
                                    else if (certPath.countVerifiedAndValidPaths() > 2) {
                                        JAPConfInfoService.this.m_lblInactive.setVisible(false);
                                        button4.setIcon(GUIUtils.loadImageIcon("certs/trusted_green.png"));
                                    }
                                    else if (certPath.countVerifiedAndValidPaths() > 1) {
                                        JAPConfInfoService.this.m_lblInactive.setVisible(false);
                                        button4.setIcon(GUIUtils.loadImageIcon("certs/trusted_blue.png"));
                                    }
                                    else {
                                        JAPConfInfoService.this.m_lblInactive.setVisible(false);
                                        button4.setIcon(GUIUtils.loadImageIcon("certs/trusted_black.png"));
                                    }
                                }
                            }
                        }
                    }
                    final InfoServiceDBEntry infoServiceDBEntry2 = JAPConfInfoService.this.m_listKnownInfoServices.getSelectedValue();
                    if (infoServiceDBEntry2 != null) {
                        final InfoServiceDBEntry preferredInfoService = InfoServiceHolder.getInstance().getPreferredInfoService();
                        JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.setEnabled(infoServiceDBEntry2.isUserDefined() && !infoServiceDBEntry2.equals(preferredInfoService));
                        button2.setEnabled(!infoServiceDBEntry2.equals(preferredInfoService));
                        JAPConfInfoService.this.m_selectedISCertPaths = infoServiceDBEntry2.getCertPath();
                        JAPConfInfoService.this.m_selectedISName = infoServiceDBEntry2.getName();
                    }
                    else {
                        JAPConfInfoService.this.settingsInfoServiceConfigBasicSettingsRemoveButton.setEnabled(false);
                    }
                }
            }
        });
        final JPanel panel4 = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel4.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        layout.setConstraints(button, gridBagConstraints);
        panel4.add(button);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        layout.setConstraints(button2, gridBagConstraints);
        panel4.add(button2);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel4.add(button4, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        panel4.add(button3, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = 0;
        panel4.add(this.settingsInfoServiceConfigBasicSettingsRemoveButton, gridBagConstraints);
        final GridBagLayout layout2 = new GridBagLayout();
        panel2.setLayout(layout2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.anchor = 18;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new Insets(10, 10, 0, 5);
        layout2.setConstraints(label, gridBagConstraints2);
        panel2.add(label);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.insets = new Insets(10, 10, 0, 5);
        panel2.add(new JLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsInformationInterfacesHostInfo")), gridBagConstraints2);
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(10, 0, 0, 5);
        panel2.add(this.m_hostLabel = new JAPMultilineLabel("                                                      ", null, null), gridBagConstraints2);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.insets = new Insets(10, 10, 0, 5);
        panel2.add(new JLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsInformationInterfacesPortInfo")), gridBagConstraints2);
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(10, 0, 0, 5);
        panel2.add(this.m_portLabel = new JLabel("                                                      "), gridBagConstraints2);
        (this.m_lblInactive = new JLabel(JAPMessages.getString(JAPConfInfoService.MSG_INACTIVE))).setIcon(GUIUtils.loadImageIcon("certs/invalid.png"));
        this.m_lblInactive.setVisible(false);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints2;
        ++gridBagConstraints3.gridy;
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.insets = new Insets(10, 10, 0, 5);
        panel2.add(this.m_lblInactive, gridBagConstraints2);
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.insets = new Insets(10, 10, 5, 5);
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.gridheight = 8;
        layout2.setConstraints(scrollPane, gridBagConstraints2);
        panel2.add(scrollPane);
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 9;
        gridBagConstraints2.gridwidth = 5;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(10, 0, 5, 0);
        gridBagConstraints2.anchor = 18;
        gridBagConstraints2.fill = 2;
        layout2.setConstraints(panel4, gridBagConstraints2);
        panel2.add(panel4);
        this.m_settingsInfoServiceConfigBasicSettingsDescriptionLabel = new JAPHtmlMultiLineLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsDescriptionLabel"));
        final GridBagLayout layout3 = new GridBagLayout();
        this.descriptionPanel.setLayout(layout3);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.anchor = 18;
        gridBagConstraints4.fill = 0;
        gridBagConstraints4.weighty = 1.0;
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.insets = new Insets(10, 10, 10, 5);
        layout3.setConstraints(this.m_settingsInfoServiceConfigBasicSettingsDescriptionLabel, gridBagConstraints4);
        this.descriptionPanel.add(this.m_settingsInfoServiceConfigBasicSettingsDescriptionLabel);
        this.addInfoServiceHostField = new JTextField(20);
        this.addInfoServicePortField = new JAPJIntField(65535);
        this.addInfoServiceNameField = new JTextField(20);
        final JButton button5 = new JButton(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsAddInfoServiceAddButton"));
        button5.addActionListener(new ActionListener() {
            static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    String trim = JAPConfInfoService.this.addInfoServiceNameField.getText().trim();
                    if (trim.equals("")) {
                        trim = null;
                    }
                    if (!JAPConfInfoService.this.mb_newInfoService) {
                        final InfoServiceDBEntry infoServiceDBEntry = JAPConfInfoService.this.m_listKnownInfoServices.getSelectedValue();
                        if (infoServiceDBEntry != null) {
                            Database.getInstance((JAPConfInfoService$13.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoService$13.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoService$13.class$anon$infoservice$InfoServiceDBEntry).remove(infoServiceDBEntry);
                        }
                    }
                    final InfoServiceDBEntry infoServiceDBEntry2 = new InfoServiceDBEntry(trim, null, new ListenerInterface(JAPConfInfoService.this.addInfoServiceHostField.getText().trim(), Integer.parseInt(JAPConfInfoService.this.addInfoServicePortField.getText().trim())).toVector(), false, true, 0L, 0L, false);
                    infoServiceDBEntry2.setUserDefined(true);
                    Database.getInstance((JAPConfInfoService$13.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPConfInfoService$13.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPConfInfoService$13.class$anon$infoservice$InfoServiceDBEntry).update(infoServiceDBEntry2);
                    JAPConfInfoService.this.addInfoServicePanel.setVisible(false);
                    JAPConfInfoService.this.addInfoServiceHostField.setText("");
                    JAPConfInfoService.this.addInfoServicePortField.setText("");
                    JAPConfInfoService.this.addInfoServiceNameField.setText("");
                    JAPConfInfoService.this.descriptionPanel.setVisible(true);
                    button3.setEnabled(true);
                    JAPConfInfoService.this.m_listKnownInfoServices.setSelectedValue(infoServiceDBEntry2, true);
                }
                catch (Exception ex) {
                    JAPDialog.showErrorDialog(JAPConfInfoService.this.addInfoServicePanel, JAPMessages.getString("settingsInfoServiceConfigBasicSettingsAddInfoServiceAddError"), LogType.MISC);
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
        });
        final JButton button6 = new JButton(JAPMessages.getString("cancelButton"));
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfInfoService.this.addInfoServicePanel.setVisible(false);
                JAPConfInfoService.this.addInfoServiceHostField.setText("");
                JAPConfInfoService.this.addInfoServicePortField.setText("");
                JAPConfInfoService.this.addInfoServiceNameField.setText("");
                JAPConfInfoService.this.descriptionPanel.setVisible(true);
                button3.setEnabled(true);
            }
        });
        final JLabel label2 = new JLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsAddInfoServiceHostLabel"));
        final JLabel label3 = new JLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsAddInfoServicePortLabel"));
        final JLabel label4 = new JLabel(JAPMessages.getString("settingsInfoServiceConfigBasicSettingsAddInfoServiceNameLabel"));
        final JPanel panel5 = new JPanel();
        final FlowLayout layout4 = new FlowLayout();
        layout4.setAlignment(2);
        panel5.setLayout(layout4);
        panel5.add(button5);
        panel5.add(button6);
        final GridBagLayout layout5 = new GridBagLayout();
        this.addInfoServicePanel.setLayout(layout5);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.fill = 0;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.anchor = 18;
        gridBagConstraints5.insets = new Insets(5, 10, 0, 10);
        layout5.setConstraints(label2, gridBagConstraints5);
        this.addInfoServicePanel.add(label2);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.anchor = 18;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.insets = new Insets(0, 10, 5, 10);
        layout5.setConstraints(this.addInfoServiceHostField, gridBagConstraints5);
        this.addInfoServicePanel.add(this.addInfoServiceHostField);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.insets = new Insets(0, 10, 0, 10);
        layout5.setConstraints(label3, gridBagConstraints5);
        this.addInfoServicePanel.add(label3);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 3;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.insets = new Insets(0, 10, 5, 10);
        layout5.setConstraints(this.addInfoServicePortField, gridBagConstraints5);
        this.addInfoServicePanel.add(this.addInfoServicePortField);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 4;
        gridBagConstraints5.insets = new Insets(0, 10, 0, 10);
        layout5.setConstraints(label4, gridBagConstraints5);
        this.addInfoServicePanel.add(label4);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 5;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.insets = new Insets(0, 10, 10, 10);
        layout5.setConstraints(this.addInfoServiceNameField, gridBagConstraints5);
        this.addInfoServicePanel.add(this.addInfoServiceNameField);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 6;
        gridBagConstraints5.gridwidth = 2;
        gridBagConstraints5.weighty = 1.0;
        gridBagConstraints5.insets = new Insets(0, 10, 10, 10);
        this.addInfoServicePanel.add(panel5, gridBagConstraints5);
        final GridBagLayout layout6 = new GridBagLayout();
        panel3.setLayout(layout6);
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.anchor = 18;
        gridBagConstraints6.fill = 1;
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        layout6.setConstraints(this.descriptionPanel, gridBagConstraints6);
        panel3.add(this.descriptionPanel);
        panel3.add(this.addInfoServicePanel, gridBagConstraints6);
        panel3.setPreferredSize(new Dimension(Math.max(this.descriptionPanel.getPreferredSize().width, this.addInfoServicePanel.getPreferredSize().width), Math.max(this.descriptionPanel.getPreferredSize().height, this.addInfoServicePanel.getPreferredSize().height)));
        this.addInfoServicePanel.setVisible(false);
        final GridBagLayout layout7 = new GridBagLayout();
        panel.setLayout(layout7);
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.anchor = 18;
        gridBagConstraints7.fill = 1;
        gridBagConstraints7.weightx = 1.0;
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.weighty = 1.0;
        gridBagConstraints7.insets = new Insets(0, 0, 5, 0);
        layout7.setConstraints(panel2, gridBagConstraints7);
        panel.add(panel2);
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 2;
        gridBagConstraints7.weighty = 1.0;
        gridBagConstraints7.insets = new Insets(0, 0, 0, 0);
        layout7.setConstraints(panel3, gridBagConstraints7);
        panel.add(panel3);
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 1;
        gridBagConstraints7.weighty = 0.0;
        gridBagConstraints7.weightx = 1.0;
        gridBagConstraints7.fill = 2;
        gridBagConstraints7.insets = new Insets(0, 0, 0, 0);
        panel.add(new JSeparator(), gridBagConstraints7);
        return panel;
    }
    
    private JPanel createInfoServiceAdvancedPanel() {
        final JPanel panel = new JPanel();
        this.m_allowAutomaticIS = new JCheckBox(JAPMessages.getString("settingsInfoServiceConfigAdvancedSettingsEnableAutomaticRequestsBox"));
        (this.m_cbxUseRedundantISRequests = new JCheckBox(JAPMessages.getString(JAPConfInfoService.MSG_USE_MORE_IS) + ":")).setVisible(false);
        this.m_cbxUseRedundantISRequests.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                InfoServiceHolder.getInstance().setChangeInfoServices(JAPConfInfoService.this.m_cbxUseRedundantISRequests.isSelected());
            }
        });
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        final JPanel panel2 = new JPanel();
        gridBagConstraints.gridwidth = 3;
        panel2.add(new JLabel(JAPMessages.getString(JAPConfInfoService.MSG_ALLOW_DIRECT_CONNECTION) + ":"));
        final String[] array = new String[JAPModel.getMsgConnectionAnonymous().length];
        System.arraycopy(JAPModel.getMsgConnectionAnonymous(), 0, array, 0, array.length);
        for (int i = 0; i < array.length; ++i) {
            array[i] = JAPMessages.getString(array[i]);
        }
        panel2.add(this.m_comboAnonymousConnection = new JComboBox(array));
        panel.add(panel2, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.gridwidth = 3;
        panel.add(this.m_allowAutomaticIS, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.gridwidth = 2;
        panel.add(new JLabel(JAPMessages.getString(JAPConfInfoService.MSG_USE_MORE_IS) + ":"), gridBagConstraints);
        final Object[] array2 = new Object[10];
        for (int j = 0; j < array2.length; ++j) {
            if (j == array2.length - 1) {
                array2[j] = JAPMessages.getString(JAPConfInfoService.MSG_ALL_INFO_SERVICES);
            }
            else {
                array2[j] = new Integer(j + 1);
            }
        }
        this.m_cmbAskedInfoServices = new JComboBox(array2);
        gridBagConstraints.gridwidth = 1;
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridx;
        panel.add(this.m_cmbAskedInfoServices, gridBagConstraints);
        this.m_cbxUseRedundantISRequests.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                JAPConfInfoService.this.m_cmbAskedInfoServices.setEnabled(JAPConfInfoService.this.m_cbxUseRedundantISRequests.isSelected());
            }
        });
        this.m_lblExplanation = new JAPHtmlMultiLineLabel(JAPMessages.getString(JAPConfInfoService.MSG_EXPLANATION, new Object[] { new Integer(4) }));
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridy;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 1;
        panel.add(this.m_lblExplanation, gridBagConstraints);
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        ++gridBagConstraints7.gridy;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        panel.add(new JLabel(JAPMessages.getString(JAPConfInfoService.MSG_CONNECT_TIMEOUT) + " (s):"), gridBagConstraints);
        this.m_comboTimeout = new JComboBox((E[])JAPConfInfoService.CONNECT_TIMEOUTS);
        gridBagConstraints.fill = 0;
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridx;
        panel.add(this.m_comboTimeout, gridBagConstraints);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 3;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridy;
        panel.add(new JLabel(), gridBagConstraints);
        this.updateValues(false);
        return panel;
    }
    
    public void onResetToDefaultsPressed() {
        this.m_comboAnonymousConnection.setSelectedIndex(0);
        this.m_cmbAskedInfoServices.setSelectedIndex(3);
        this.m_cbxUseRedundantISRequests.setSelected(true);
        this.m_allowAutomaticIS.setSelected(true);
        this.setConnectionTimeout(20000);
    }
    
    protected void onRootPanelShown() {
        if (this.m_listKnownInfoServices.getSelectedIndex() < 0) {
            this.m_listKnownInfoServices.setSelectedValue(InfoServiceHolder.getInstance().getPreferredInfoService(), true);
        }
    }
    
    protected void onUpdateValues() {
        int selectedIndex = InfoServiceHolder.getInstance().getNumberOfAskedInfoServices() - 1;
        if (selectedIndex < 0) {
            selectedIndex = 0;
        }
        else if (selectedIndex >= this.m_cmbAskedInfoServices.getItemCount()) {
            selectedIndex = this.m_cmbAskedInfoServices.getItemCount() - 1;
        }
        this.m_cmbAskedInfoServices.setSelectedIndex(selectedIndex);
        this.m_allowAutomaticIS.setSelected(!JAPModel.isInfoServiceDisabled());
        this.m_comboAnonymousConnection.setSelectedIndex(JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting());
        this.m_cmbAskedInfoServices.setEnabled(InfoServiceHolder.getInstance().isChangeInfoServices());
        this.m_lblExplanation.setFont(new JLabel().getFont());
        this.m_settingsInfoServiceConfigBasicSettingsDescriptionLabel.setFont(new JLabel().getFont());
        this.setConnectionTimeout(InfoServiceDBEntry.getConnectionTimeout());
    }
    
    public String getHelpContext() {
        return "infoservice";
    }
    
    protected boolean onOkPressed() {
        JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(this.m_comboAnonymousConnection.getSelectedIndex());
        InfoServiceHolder.getInstance().setNumberOfAskedInfoServices(this.m_cmbAskedInfoServices.getSelectedIndex() + 1);
        JAPModel.getInstance().setInfoServiceDisabled(!this.m_allowAutomaticIS.isSelected());
        InfoServiceDBEntry.setConnectionTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        return true;
    }
    
    private void setPreferredInfoService() {
        final InfoServiceDBEntry preferredInfoService = this.m_listKnownInfoServices.getSelectedValue();
        if (preferredInfoService != null) {
            InfoServiceHolder.getInstance().setPreferredInfoService(preferredInfoService);
        }
    }
    
    private int findFirstUserDefinedListModelEntry(final DefaultListModel defaultListModel) {
        int n;
        for (n = 0; n < defaultListModel.size() && !defaultListModel.getElementAt(n).isUserDefined(); ++n) {}
        return n;
    }
    
    private void setConnectionTimeout(final int n) {
        final int n2 = n / 1000;
        if (n2 >= this.m_comboTimeout.getItemAt(this.m_comboTimeout.getItemCount() - 1)) {
            this.m_comboTimeout.setSelectedIndex(this.m_comboTimeout.getItemCount() - 1);
            InfoServiceDBEntry.setConnectionTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        }
        else if (n2 <= this.m_comboTimeout.getItemAt(0)) {
            this.m_comboTimeout.setSelectedIndex(0);
            InfoServiceDBEntry.setConnectionTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
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
        MSG_CONNECT_TIMEOUT = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_connectTimeout";
        MSG_ALLOW_DIRECT_CONNECTION = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_allowDirectConnection";
        MSG_VIEW_CERT = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_viewCert";
        MSG_REALLY_DELETE = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_reallyDelete";
        MSG_USE_MORE_IS = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_useMoreIS";
        MSG_EXPLANATION = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_explanation";
        MSG_ALL_INFO_SERVICES = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_allInfoServices";
        MSG_INACTIVE = ((JAPConfInfoService.class$jap$JAPConfInfoService == null) ? (JAPConfInfoService.class$jap$JAPConfInfoService = class$("jap.JAPConfInfoService")) : JAPConfInfoService.class$jap$JAPConfInfoService).getName() + "_inactive";
        CONNECT_TIMEOUTS = new Integer[] { new Integer(5), new Integer(10), new Integer(15), new Integer(20), new Integer(25), new Integer(30), new Integer(40), new Integer(50), new Integer(60) };
    }
}
