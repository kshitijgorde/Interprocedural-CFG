// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.pay.PayMessage;
import java.awt.Font;
import anon.crypto.MultiCertPath;
import anon.infoservice.MixInfo;
import anon.infoservice.PerformanceEntry;
import anon.client.TrustException;
import anon.infoservice.PerformanceInfo;
import gui.MixDetailsDialog;
import java.util.Date;
import anon.util.CountryMapper;
import anon.AnonServerDescription;
import java.util.Enumeration;
import jap.forward.JAPRoutingMessage;
import anon.infoservice.ClickedMessageIDDBEntry;
import anon.infoservice.DeletedMessageIDDBEntry;
import java.net.URL;
import gui.dialog.DialogContentPane;
import anon.infoservice.MessageDBEntry;
import anon.infoservice.NewCascadeIDEntry;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.CascadeIDEntry;
import anon.infoservice.DatabaseMessage;
import java.lang.reflect.InvocationTargetException;
import anon.util.Util;
import jap.forward.JAPRoutingServerStatisticsListener;
import java.util.Observable;
import javax.swing.LookAndFeel;
import javax.swing.ImageIcon;
import gui.help.IHelpModel;
import java.awt.Frame;
import anon.client.TrustModel;
import anon.pay.PayAccountsFile;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import gui.JAPHelpContext;
import gui.help.JAPHelp;
import anon.infoservice.StatusInfo;
import anon.infoservice.MixCascade;
import gui.DataRetentionDialog;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import platform.WindowsOS;
import javax.swing.JSeparator;
import platform.AbstractOS;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import javax.swing.Icon;
import gui.GUIUtils;
import java.text.MessageFormat;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import update.JAPUpdateWizard;
import anon.infoservice.JavaVersionDBEntry;
import anon.infoservice.Database;
import anon.infoservice.JAPVersionInfo;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Point;
import gui.PopupMenu;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import gui.JAPDll;
import logging.LogHolder;
import logging.LogType;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import jap.pay.PaymentMainPanel;
import gui.FlippingPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import gui.JAPProgressBar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import anon.util.JobQueue;
import anon.pay.IMessageListener;
import java.util.Observer;
import java.awt.event.ActionListener;

public final class JAPNewView extends AbstractJAPMainView implements IJAPMainView, ActionListener, JAPObserver, Observer, IMessageListener
{
    private static final long serialVersionUID = 1L;
    public static final String MSG_UPDATE;
    public static final String MSG_NO_REAL_PAYMENT;
    public static final String MSG_UNKNOWN_PERFORMANCE;
    public static final String MSG_USERS;
    public static final String MSG_SERVICE_NAME;
    private static final String MSG_ANONYMETER_TOOL_TIP;
    private static final String MSG_ERROR_DISCONNECTED;
    private static final String MSG_ERROR_PROXY;
    private static final String MSG_TITLE_OLD_JAVA;
    private static final String MSG_OLD_JAVA;
    private static final String MSG_OLD_JAVA_HINT;
    private static final String MSG_LBL_NEW_SERVICES_FOUND;
    private static final String MSG_TOOLTIP_NEW_SERVICES_FOUND;
    private static final String MSG_NEW_SERVICES_FOUND_EXPLAIN;
    private static final String MSG_NO_COSTS;
    private static final String MSG_WITH_COSTS;
    private static final String MSG_BTN_ASSISTANT;
    private static final String MSG_MN_ASSISTANT;
    private static final String MSG_MN_DETAILS;
    private static final String MSG_IS_DISABLED_EXPLAIN;
    private static final String MSG_IS_DEACTIVATED;
    private static final String MSG_IS_TOOLTIP;
    private static final String MSG_IS_TRUST_PARANOID;
    private static final String MSG_IS_TRUST_SUSPICIOUS;
    private static final String MSG_IS_TRUST_HIGH;
    private static final String MSG_IS_TRUST_ALL;
    private static final String MSG_IS_EDIT_TRUST;
    private static final String MSG_TRUST_FILTER;
    private static final String MSG_CONNECTED;
    private static final String MSG_DELETE_MESSAGE;
    private static final String MSG_HIDE_MESSAGE_SHORT;
    private static final String MSG_DELETE_MESSAGE_EXPLAIN;
    private static final String MSG_DELETE_MESSAGE_SHORT;
    private static final String MSG_VIEW_MESSAGE;
    private static final String MSG_ANTI_CENSORSHIP;
    private static final String MSG_DATA_RETENTION_EXPLAIN;
    private static final String MSG_OBSERVABLE_EXPLAIN;
    private static final String MSG_OBSERVABLE_TITLE;
    private static final String MSG_DISTRIBUTION;
    private static final String MSG_USER_ACTIVITY;
    private static final String MSG_JAVA_FORCED_TITLE;
    private static final String MSG_JAVA_FORCED_EXPLAIN;
    private static final String MSG_JAVA_FORCED_OS;
    private static final String MSG_JAVA_FORCED_QUESTION;
    private static final String MSG_LBL_ENCRYPTED_DATA;
    private static final String MSG_LBL_HTTP_DATA;
    private static final String MSG_LBL_OTHER_DATA;
    private static final String IMG_ICONIFY;
    private static final String IMG_ABOUT;
    private static final String MSG_OPEN_FIREFOX;
    private JobQueue m_blinkJobs;
    private JobQueue m_transferedBytesJobs;
    private JobQueue m_channelsChangedJobs;
    private JobQueue m_packetMixedJobs;
    private static final String HLP_ANONYMETER;
    private static final String IMG_METER = "anonym-o-meter/JAP.NewView_m{0}.anim.gif";
    private static final String IMG_METER_NO_MEASURE = "anonym-o-meter/JAP.no.measure.anim{0}.gif";
    private static final String IMG_METER_DEACTIVATED = "anonym-o-meter/JAP.deactivated.anim{0}.gif";
    private static final String IMG_METER_CONNECTING = "anonym-o-meter/JAP.connecting.anim.gif";
    private final JLabel DEFAULT_LABEL;
    private JLabel m_labelVersion;
    private JPanel m_pnlVersion;
    private JButton m_bttnHelp;
    private JButton m_bttnQuit;
    private JButton m_bttnIconify;
    private JButton m_bttnConf;
    private JButton m_btnAssistant;
    private JButton m_btnAbout;
    private JLabel m_buttonDeleteMessage;
    private JAPConf m_dlgConfig;
    private Object LOCK_CONFIG;
    private boolean m_bConfigActive;
    private JAPViewIconified m_ViewIconified;
    private Object SYNC_ICONIFIED_VIEW;
    private boolean m_bIsIconified;
    private boolean m_bWithPayment;
    private JAPMixCascadeComboBox m_comboAnonServices;
    private JLabel m_labelAnonService;
    private JLabel m_labelAnonymity;
    private JLabel m_labelAnonymitySmall;
    private JLabel m_labelAnonymityOnOff;
    private JLabel m_labelAnonMeter;
    private JLabel m_labelAnonymityLow;
    private JLabel m_labelAnonymityHigh;
    private JLabel m_labelSpeed;
    private JLabel m_labelDelay;
    private JLabel m_labelSpeedLabel;
    private JLabel m_labelDelayLabel;
    private JLabel m_labelOperatorCountries;
    private JLabel m_lblUsers;
    private JLabel m_lblUsersLabel;
    private JLabel[] m_labelOperatorFlags;
    private MixMouseAdapter[] m_adapterOperator;
    private LawListener m_lawListener;
    private JLabel[] m_lawFlags;
    private JLabel m_labelOwnTraffic;
    private JLabel m_labelOwnTrafficSmall;
    private JLabel m_labelOwnActivity;
    private JLabel m_labelForwarderActivity;
    private JLabel m_labelOwnActivitySmall;
    private JLabel m_labelForwarderActivitySmall;
    private JLabel m_labelOwnTrafficBytes;
    private JLabel m_labelOwnTrafficUnit;
    private JLabel m_labelOwnTrafficBytesSmall;
    private JLabel m_labelOwnTrafficUnitSmall;
    private JLabel m_labelOwnTrafficWWW;
    private JLabel m_labelOwnTrafficOther;
    private JLabel m_labelOwnTrafficBytesWWW;
    private JLabel m_labelOwnTrafficUnitWWW;
    private JLabel m_labelOwnTrafficBytesOther;
    private JLabel m_labelOwnTrafficUnitOther;
    private JLabel m_labelForwarding;
    private JLabel m_labelForwardingSmall;
    private JLabel m_labelForwardedTrafficBytes;
    private JLabel m_labelForwardedTrafficBytesUnit;
    private JLabel m_labelForwarderCurrentConnections;
    private JLabel m_labelForwarderAcceptedConnections;
    private JLabel m_labelForwarderRejectedConnections;
    private JLabel m_labelForwardedTraffic;
    private JLabel m_labelForwarderUsedBandwidth;
    private JLabel m_labelForwarderCurrentConnectionsLabel;
    private JLabel m_labelForwarderAcceptedConnectionsLabel;
    private JLabel m_labelForwarderRejectedConnectionsLabel;
    private JLabel m_labelForwarderUsedBandwidthLabel;
    private JLabel m_labelForwarderConnections;
    private JLabel m_labelForwardingErrorSmall;
    private JLabel m_labelForwardingError;
    private JAPProgressBar m_progressOwnTrafficActivity;
    private JAPProgressBar m_progressOwnTrafficActivitySmall;
    private JAPProgressBar m_progressAnonLevel;
    private JAPProgressBar m_progressDistribution;
    private JButton m_bttnAnonDetails;
    private JButton m_bttnReload;
    private JButton m_firefox;
    private JCheckBox m_cbAnonymityOn;
    private JRadioButton m_rbAnonOff;
    private JRadioButton m_rbAnonOn;
    private JCheckBox m_cbForwarding;
    private JCheckBox m_cbForwardingSmall;
    private FlippingPanel m_flippingpanelAnon;
    private FlippingPanel m_flippingpanelOwnTraffic;
    private JPanel m_flippingpanelForward;
    private StatusPanel m_StatusPanel;
    private JPanel m_panelAnonService;
    private Object SYNC_DISCONNECTED_ERROR;
    private boolean m_bDisconnectedErrorShown;
    private boolean m_bIgnoreAnonComboEvents;
    private PaymentMainPanel m_flippingPanelPayment;
    private Object m_connectionEstablishedSync;
    private boolean m_bShowConnecting;
    private JAPProgressBar m_progForwarderActivity;
    private JAPProgressBar m_progForwarderActivitySmall;
    private int m_ForwardingID;
    private int m_updateAvailableID;
    private Hashtable m_messageIDs;
    private int m_enableInfoServiceID;
    private int m_newServicesID;
    private final Object SYNC_STATUS_ENABLE_IS;
    private final Object SYNC_STATUS_UPDATE_AVAILABLE;
    private final Object SYNC_NEW_SERVICES;
    private ActionListener m_listenerUpdate;
    private ActionListener m_listenerEnableIS;
    private ActionListener m_listenerNewServices;
    private volatile long m_lTrafficWWW;
    private volatile long m_lTrafficOther;
    private Object SYNC_ACTION;
    private boolean m_bActionPerformed;
    private ComponentMovedAdapter m_mainMovedAdapter;
    private ComponentMovedAdapter m_configMovedAdapter;
    private ComponentMovedAdapter m_helpMovedAdapter;
    private ComponentMovedAdapter m_miniMovedAdapter;
    private boolean m_bTrustChanged;
    private boolean m_bIsSimpleView;
    private int m_msgIDInsecure;
    private int m_msgForwardServer;
    private int m_msgForwardServerStatus;
    private MouseListener m_mouseForwardError;
    private final Object SYNC_FORWARD_MSG;
    private int m_currentChannels;
    private Hashtable m_messagesShown;
    static /* synthetic */ Class class$jap$JAPNewView;
    static /* synthetic */ Class class$jap$JAPConfAnon;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    static /* synthetic */ Class class$anon$infoservice$JAPVersionInfo;
    static /* synthetic */ Class class$anon$infoservice$JavaVersionDBEntry;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$NewCascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$CascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$BlacklistedCascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$MessageDBEntry;
    static /* synthetic */ Class class$anon$infoservice$DeletedMessageIDDBEntry;
    static /* synthetic */ Class class$anon$client$TrustModel$SpeedAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$DelayAttribute;
    
    public JAPNewView(final String s, final JAPController japController) {
        super(s, japController);
        this.DEFAULT_LABEL = new JLabel();
        this.LOCK_CONFIG = new Object();
        this.m_bConfigActive = false;
        this.SYNC_ICONIFIED_VIEW = new Object();
        this.m_bWithPayment = false;
        this.SYNC_DISCONNECTED_ERROR = new Object();
        this.m_bDisconnectedErrorShown = false;
        this.m_bIgnoreAnonComboEvents = false;
        this.m_connectionEstablishedSync = new Object();
        this.m_bShowConnecting = false;
        this.m_ForwardingID = -1;
        this.m_updateAvailableID = -1;
        this.m_messageIDs = new Hashtable();
        this.m_enableInfoServiceID = -1;
        this.m_newServicesID = -1;
        this.SYNC_STATUS_ENABLE_IS = new Object();
        this.SYNC_STATUS_UPDATE_AVAILABLE = new Object();
        this.SYNC_NEW_SERVICES = new Object();
        this.SYNC_ACTION = new Object();
        this.m_bActionPerformed = false;
        this.m_bTrustChanged = false;
        this.m_msgForwardServer = -1;
        this.m_msgForwardServerStatus = 0;
        this.SYNC_FORWARD_MSG = new Object();
        this.m_currentChannels = 0;
        this.m_messagesShown = new Hashtable();
        this.m_bIsSimpleView = (JAPModel.getDefaultView() == 2);
        super.m_Controller = JAPController.getInstance();
        this.m_dlgConfig = null;
        this.m_bIsIconified = false;
        this.m_blinkJobs = new JobQueue("Blink job queue");
        this.m_transferedBytesJobs = new JobQueue("Transfered bytes update job queue");
        this.m_packetMixedJobs = new JobQueue("packet mixed update job queue");
        this.m_channelsChangedJobs = new JobQueue("channels changed job queue");
        this.m_lTrafficWWW = 0L;
        this.m_lTrafficOther = 0L;
    }
    
    public void create(final boolean bWithPayment) {
        this.m_bWithPayment = bWithPayment;
        LogHolder.log(6, LogType.GUI, "Initializing view...");
        this.init();
        this.setTitle(Double.toString(Math.random()));
        JAPDll.setWindowIcon(this.getTitle());
        this.setTitle(super.m_Title);
        LogHolder.log(6, LogType.GUI, "View initialized!");
    }
    
    private void init() {
        new SystrayPopupMenu(new SystrayPopupMenu.MainWindowListener() {
            public void onShowMainWindow() {
            }
            
            public void onShowSettings(final String s, final Object o) {
                JAPNewView.this.showConfigDialog(s, o);
            }
            
            public void onShowHelp() {
            }
        });
        this.addMouseListener(new MouseAdapter() {
            private final /* synthetic */ JAPNewView this$0;
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent == null) {
                    return;
                }
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                if (SwingUtilities.isRightMouseButton(mouseEvent) || mouseEvent.isPopupTrigger()) {
                    final SystrayPopupMenu systrayPopupMenu = new SystrayPopupMenu(new SystrayPopupMenu.MainWindowListener() {
                        private final /* synthetic */ JAPNewView$2 this$1 = this$1;
                        
                        public void onShowMainWindow() {
                        }
                        
                        public void onShowSettings(final String s, final Object o) {
                            this.this$1.this$0.showConfigDialog(s, o);
                        }
                        
                        public void onShowHelp() {
                        }
                    });
                    systrayPopupMenu.registerExitHandler(new PopupMenu.ExitHandler() {
                        public void exited() {
                            systrayPopupMenu.dispose();
                        }
                    });
                    systrayPopupMenu.show(JAPNewView.this, new Point(mouseEvent.getX() + JAPNewView.this.getLocation().x, mouseEvent.getY() + JAPNewView.this.getLocation().y));
                }
                else if (mouseEvent.getClickCount() == 2) {
                    JAPNewView.this.showIconifiedView();
                }
            }
        });
        this.m_listenerUpdate = new ActionListener() {
            static /* synthetic */ Class class$anon$infoservice$JAPVersionInfo;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                boolean b = false;
                JAPVersionInfo recommendedUpdate = JAPVersionInfo.getRecommendedUpdate("00.12.005", true);
                if (recommendedUpdate == null) {
                    recommendedUpdate = (JAPVersionInfo)Database.getInstance((JAPNewView$5.class$anon$infoservice$JAPVersionInfo == null) ? (JAPNewView$5.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPNewView$5.class$anon$infoservice$JAPVersionInfo).getEntryById("/japRelease.jnlp");
                }
                JavaVersionDBEntry newJavaVersion = null;
                Label_0097: {
                    if (!JAPController.getInstance().hasPortableJava() && (newJavaVersion = JavaVersionDBEntry.getNewJavaVersion()) != null) {
                        if (newJavaVersion.isUpdateForced()) {
                            break Label_0097;
                        }
                        if (JAPModel.getInstance().isReminderForJavaUpdateActivated()) {
                            break Label_0097;
                        }
                    }
                    newJavaVersion = null;
                }
                if (recommendedUpdate != null && recommendedUpdate.getJapVersion().compareTo("00.12.005") > 0) {
                    final JAPUpdateWizard japUpdateWizard = new JAPUpdateWizard(recommendedUpdate, JAPController.getInstance().getCurrentView());
                    if (japUpdateWizard.getStatus() == -1) {
                        LogHolder.log(3, LogType.MISC, "Some update problem.");
                        JAPDialog.showErrorDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString("downloadFailed") + JAPMessages.getString("infoURL"), LogType.MISC);
                    }
                    else if (japUpdateWizard.getStatus() == 0) {
                        b = true;
                    }
                }
                if (!b && newJavaVersion != null) {
                    JAPNewView.this.showJavaUpdateDialog(newJavaVersion);
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
        };
        this.m_listenerEnableIS = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                if (JAPModel.isInfoServiceDisabled()) {
                    String s = "";
                    if (JAPMessages.getLocale().getLanguage() == "de") {
                        s = "_de";
                    }
                    if (JAPDialog.showConfirmDialog(JAPNewView.this, JAPMessages.getString(JAPNewView.MSG_IS_DISABLED_EXPLAIN), 0, 2, GUIUtils.loadImageIcon(MessageFormat.format("anonym-o-meter/JAP.no.measure.anim{0}.gif", s), true, true)) == 0) {
                        JAPModel.getInstance().setInfoServiceDisabled(false);
                    }
                }
                if (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 1 && !JAPController.getInstance().isAnonConnected()) {
                    if (JAPDialog.showConfirmDialog(JAPNewView.this, JAPMessages.getString(JAPController.MSG_IS_NOT_ALLOWED), 0, 2) == 0) {
                        JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(0);
                    }
                }
                else if (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 2 && JAPController.getInstance().isAnonConnected() && JAPDialog.showConfirmDialog(JAPNewView.this, JAPMessages.getString(JAPController.MSG_IS_NOT_ALLOWED_FOR_ANONYMOUS), 0, 2) == 0) {
                    JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(0);
                }
            }
        };
        this.m_listenerNewServices = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                JAPDialog.showMessageDialog(JAPNewView.this, JAPMessages.getString(JAPNewView.MSG_NEW_SERVICES_FOUND_EXPLAIN, JAPMessages.getString(JAPNewView.MSG_SERVICE_NAME)));
                JAPNewView.this.m_comboAnonServices.showPopup();
            }
        };
        this.m_flippingpanelOwnTraffic = new FlippingPanel(this);
        this.m_flippingpanelForward = new FlippingPanel(this);
        String s;
        if (JAPModel.getInstance().getProgramName().equals("JonDo")) {
            s = "JonDo.ico.gif";
        }
        else {
            s = "icon16.gif";
        }
        final ImageIcon loadImageIcon = GUIUtils.loadImageIcon(s, true, false);
        if (loadImageIcon != null) {
            this.setIconImage(loadImageIcon.getImage());
        }
        final JLabel label = new JLabel(GUIUtils.loadImageIcon("JonDo.png", true, false));
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(5, 5, 0, 0);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 2;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.anchor = 13;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 0;
        this.m_pnlVersion = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.anchor = 14;
        gridBagConstraints4.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 14;
        gridBagConstraints.weighty = 0.0;
        (this.m_labelVersion = new JLabel("00.12.005")).setForeground(Color.blue);
        this.m_labelVersion.setCursor(Cursor.getPredefinedCursor(12));
        this.m_labelVersion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                JAPController.aboutJAP();
            }
        });
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints4;
        ++gridBagConstraints5.gridx;
        gridBagConstraints4.insets = new Insets(0, 0, 0, 0);
        this.m_pnlVersion.add(this.m_labelVersion, gridBagConstraints4);
        if (super.m_Controller.isPortableMode() && AbstractOS.getInstance().isDefaultURLAvailable()) {
            (this.m_firefox = new JButton(GUIUtils.loadImageIcon("firefox.png", true, false))).setOpaque(false);
            this.m_firefox.setToolTipText(JAPMessages.getString(JAPNewView.MSG_OPEN_FIREFOX));
            this.m_firefox.setMnemonic('W');
            this.m_firefox.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                    AbstractOS.getInstance().openBrowser();
                }
            });
            panel.add(this.m_firefox, gridBagConstraints);
        }
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridy;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        panel.add(new JSeparator(), gridBagConstraints);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        this.m_panelAnonService = new JPanel(gridBagLayout);
        this.m_labelAnonService = new JLabel(JAPMessages.getString(JAPNewView.MSG_SERVICE_NAME) + ":");
        gridBagConstraints7.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints7.anchor = 17;
        gridBagConstraints7.weightx = 0.0;
        gridBagConstraints7.fill = 0;
        this.m_panelAnonService.add(this.m_labelAnonService, gridBagConstraints7);
        this.m_comboAnonServices = new JAPMixCascadeComboBox();
        if (AbstractOS.getInstance() instanceof WindowsOS) {
            this.addComponentListener(new ComponentAdapter() {
                public void componentMoved(final ComponentEvent componentEvent) {
                    JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                }
            });
        }
        gridBagConstraints7.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints7.gridwidth = 3;
        gridBagConstraints7.fill = 2;
        gridBagConstraints7.weightx = 1.0;
        this.m_panelAnonService.add(this.m_comboAnonServices, gridBagConstraints7);
        gridBagConstraints7.gridwidth = 1;
        (this.m_bttnReload = new JButton(GUIUtils.loadImageIcon("reload.gif", true, false))).setOpaque(false);
        final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        if (lookAndFeel != null && UIManager.getCrossPlatformLookAndFeelClassName().equals(lookAndFeel.getClass().getName())) {
            this.m_bttnReload.setBackground(this.m_panelAnonService.getBackground());
        }
        this.m_bttnReload.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                JAPNewView.this.fetchMixCascadesAsync(true);
            }
        });
        this.m_bttnReload.setRolloverEnabled(true);
        this.m_bttnReload.setToolTipText(JAPMessages.getString("ngCascadeReloadTooltip"));
        final ImageIcon loadImageIcon2 = GUIUtils.loadImageIcon("reloadrollover.gif", true, false);
        this.m_bttnReload.setRolloverIcon(loadImageIcon2);
        this.m_bttnReload.setSelectedIcon(loadImageIcon2);
        this.m_bttnReload.setRolloverSelectedIcon(loadImageIcon2);
        this.m_bttnReload.setPressedIcon(loadImageIcon2);
        this.m_bttnReload.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
        this.m_bttnReload.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.m_bttnReload.setFocusPainted(false);
        this.m_bttnReload.setBorderPainted(true);
        this.m_bttnReload.setContentAreaFilled(false);
        gridBagConstraints7.gridx = 4;
        gridBagConstraints7.weightx = 0.0;
        gridBagConstraints7.fill = 0;
        this.m_panelAnonService.add(this.m_bttnReload, gridBagConstraints7);
        (this.m_bttnAnonDetails = new JButton(JAPMessages.getString("ngBttnAnonDetails"))).setToolTipText(JAPMessages.getString("ngBttnAnonDetails"));
        this.m_bttnAnonDetails.setMnemonic(JAPMessages.getString(JAPNewView.MSG_MN_DETAILS).charAt(0));
        this.m_bttnAnonDetails.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPNewView.this.showConfigDialog("ANON_TAB", JAPController.getInstance().getCurrentMixCascade());
            }
        });
        gridBagConstraints7.gridx = 5;
        gridBagConstraints7.weightx = 0.0;
        gridBagConstraints7.fill = 0;
        this.m_panelAnonService.add(this.m_bttnAnonDetails, gridBagConstraints7);
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 1;
        gridBagConstraints7.anchor = 17;
        gridBagConstraints7.insets = new Insets(5, 5, 0, 0);
        gridBagConstraints7.insets = new Insets(5, 20, 0, 0);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 2;
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridy;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = 17;
        panel.add(this.m_panelAnonService, gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridy;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(new JSeparator(), gridBagConstraints);
        this.m_flippingpanelAnon = new FlippingPanel(this);
        final JPanel fullPanel = new JPanel();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.anchor = 18;
        fullPanel.setLayout(layout2);
        this.m_labelAnonymity = new JLabel(JAPMessages.getString("ngCascadeInfo"));
        gridBagConstraints10.insets = new Insets(0, 5, 0, 0);
        fullPanel.add(this.m_labelAnonymity, gridBagConstraints10);
        this.m_lblUsersLabel = new JLabel(JAPMessages.getString(JAPNewView.MSG_USERS) + ":");
        gridBagConstraints10.gridy = 1;
        gridBagConstraints10.anchor = 17;
        gridBagConstraints10.insets = new Insets(5, 15, 0, 10);
        fullPanel.add(this.m_lblUsersLabel, gridBagConstraints10);
        this.m_labelSpeedLabel = new JLabel(JAPMessages.getString(((JAPNewView.class$jap$JAPConfAnon == null) ? (JAPNewView.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPNewView.class$jap$JAPConfAnon).getName() + "_speed") + ":");
        gridBagConstraints10.gridy = 2;
        fullPanel.add(this.m_labelSpeedLabel, gridBagConstraints10);
        this.m_labelDelayLabel = new JLabel(JAPMessages.getString(((JAPNewView.class$jap$JAPConfAnon == null) ? (JAPNewView.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : JAPNewView.class$jap$JAPConfAnon).getName() + "_latency") + ":");
        gridBagConstraints10.gridy = 3;
        fullPanel.add(this.m_labelDelayLabel, gridBagConstraints10);
        this.m_labelOperatorCountries = new JLabel(JAPMessages.getString("ngOperatorCountries"));
        gridBagConstraints10.gridy = 4;
        fullPanel.add(this.m_labelOperatorCountries, gridBagConstraints10);
        this.m_lblUsers = new JLabel("9999 / 9999", 2);
        gridBagConstraints10.insets = new Insets(5, 0, 0, 10);
        gridBagConstraints10.anchor = 17;
        gridBagConstraints10.weightx = 0.0;
        gridBagConstraints10.fill = 2;
        gridBagConstraints10.gridy = 1;
        gridBagConstraints10.gridx = 1;
        gridBagConstraints10.gridwidth = 4;
        fullPanel.add(this.m_lblUsers, gridBagConstraints10);
        this.m_labelSpeed = new JLabel("1500 - 1500 kbit/s", 2);
        gridBagConstraints10.weightx = 0.0;
        gridBagConstraints10.gridy = 2;
        fullPanel.add(this.m_labelSpeed, gridBagConstraints10);
        this.m_labelDelay = new JLabel("8000 - 8000 ms", 2);
        gridBagConstraints10.weightx = 0.0;
        gridBagConstraints10.gridy = 3;
        fullPanel.add(this.m_labelDelay, gridBagConstraints10);
        this.m_labelOperatorFlags = new JLabel[3];
        this.m_adapterOperator = new MixMouseAdapter[3];
        this.m_lawFlags = new JLabel[3];
        this.m_lawListener = new LawListener();
        gridBagConstraints10.gridwidth = 1;
        gridBagConstraints10.fill = 0;
        for (int i = 0; i < this.m_labelOperatorFlags.length; ++i) {
            gridBagConstraints10.insets = new Insets(5, 2, 0, 5);
            gridBagConstraints10.gridx = i + 1;
            gridBagConstraints10.gridy = 4;
            (this.m_labelOperatorFlags[i] = new JLabel("")).setBorder(BorderFactory.createEmptyBorder());
            fullPanel.add(this.m_labelOperatorFlags[i], gridBagConstraints10);
            this.m_labelOperatorFlags[i].addMouseListener(this.m_adapterOperator[i] = new MixMouseAdapter(this.m_labelOperatorFlags[i]));
            this.m_labelOperatorFlags[i].setCursor(Cursor.getPredefinedCursor(12));
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints10;
            ++gridBagConstraints11.gridx;
            (this.m_lawFlags[i] = new JLabel(GUIUtils.loadImageIcon("certs/invalid.png", true))).setCursor(Cursor.getPredefinedCursor(12));
            this.m_lawFlags[i].setToolTipText(JAPMessages.getString(DataRetentionDialog.MSG_DATA_RETENTION_EXPLAIN_SHORT));
            this.m_lawFlags[i].addMouseListener(this.m_lawListener);
            gridBagConstraints10.insets = new Insets(3, 2, 0, 5);
            fullPanel.add(this.m_lawFlags[i], gridBagConstraints10);
            if (i < this.m_labelOperatorFlags.length - 1) {
                this.m_lawFlags[i].setVisible(false);
            }
        }
        gridBagConstraints10.fill = 2;
        (this.m_labelAnonMeter = new JLabel(this.getMeterImage(null, null))).setToolTipText(JAPMessages.getString(JAPNewView.MSG_ANONYMETER_TOOL_TIP));
        this.m_labelAnonMeter.setCursor(Cursor.getPredefinedCursor(12));
        this.m_labelAnonMeter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                JAPHelp.getInstance().setContext(JAPHelpContext.createHelpContext(JAPNewView.HLP_ANONYMETER, JAPNewView.this));
                JAPHelp.getInstance().setVisible(true);
            }
        });
        final GridBagConstraints gridBagConstraints12 = gridBagConstraints10;
        ++gridBagConstraints12.gridx;
        gridBagConstraints10.gridy = 0;
        gridBagConstraints10.gridheight = 5;
        gridBagConstraints10.anchor = 13;
        gridBagConstraints10.weightx = 1.0;
        gridBagConstraints10.fill = 0;
        gridBagConstraints10.insets = new Insets(0, 10, 0, 10);
        fullPanel.add(this.m_labelAnonMeter, gridBagConstraints10);
        final GridBagLayout gridBagLayout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        final JPanel panel2 = new JPanel(gridBagLayout2);
        panel2.setBorder(LineBorder.createBlackLineBorder());
        this.m_labelAnonymityOnOff = new JLabel(JAPMessages.getString("ngAnonymitaet"));
        gridBagConstraints13.anchor = 18;
        gridBagConstraints13.insets = new Insets(2, 2, 2, 2);
        panel2.add(this.m_labelAnonymityOnOff, gridBagConstraints13);
        (this.m_rbAnonOn = new JRadioButton(JAPMessages.getString("ngAnonOn"))).addActionListener(this);
        (this.m_rbAnonOff = new JRadioButton(JAPMessages.getString("ngAnonOff"))).addActionListener(this);
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.m_rbAnonOn);
        buttonGroup.add(this.m_rbAnonOff);
        this.m_rbAnonOff.setSelected(true);
        gridBagConstraints13.gridy = 1;
        gridBagConstraints13.insets = new Insets(0, 7, 0, 0);
        panel2.add(this.m_rbAnonOn, gridBagConstraints13);
        gridBagConstraints13.gridy = 2;
        panel2.add(this.m_rbAnonOff, gridBagConstraints13);
        final GridBagConstraints gridBagConstraints14 = gridBagConstraints10;
        ++gridBagConstraints14.gridx;
        gridBagConstraints10.weightx = 0.0;
        gridBagConstraints10.anchor = 17;
        gridBagConstraints10.insets = new Insets(0, 10, 0, 0);
        fullPanel.add(panel2, gridBagConstraints10);
        this.m_flippingpanelAnon.setFullPanel(fullPanel);
        final GridBagLayout gridBagLayout3 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
        final JPanel smallPanel = new JPanel(gridBagLayout3);
        this.m_labelAnonymitySmall = new JLabel(JAPMessages.getString("ngAnonymitaet") + ":");
        gridBagConstraints15.gridx = 0;
        gridBagConstraints15.anchor = 17;
        gridBagConstraints15.weightx = 0.0;
        gridBagConstraints15.insets = new Insets(0, 5, 0, 0);
        smallPanel.add(this.m_labelAnonymitySmall, gridBagConstraints15);
        (this.m_cbAnonymityOn = new JCheckBox(JAPMessages.getString("ngAnonOn"))).setBorder(null);
        this.m_cbAnonymityOn.addActionListener(this);
        gridBagConstraints15.gridx = 1;
        gridBagConstraints15.insets = new Insets(0, 10, 0, 0);
        smallPanel.add(this.m_cbAnonymityOn, gridBagConstraints15);
        final JPanel panel3 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
        this.m_labelAnonymityLow = new JLabel(JAPMessages.getString(JAPNewView.MSG_DISTRIBUTION), 4);
        gridBagConstraints16.gridx = 0;
        gridBagConstraints16.gridy = 0;
        gridBagConstraints16.fill = 2;
        gridBagConstraints16.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints16.weightx = 0.0;
        panel3.add(this.m_labelAnonymityLow, gridBagConstraints16);
        (this.m_progressDistribution = new JAPProgressBar()).setMinimum(0);
        this.m_progressDistribution.setMaximum(6);
        this.m_progressDistribution.setBorderPainted(false);
        final GridBagConstraints gridBagConstraints17 = gridBagConstraints16;
        ++gridBagConstraints17.gridx;
        gridBagConstraints16.weightx = 1.0;
        panel3.add(this.m_progressDistribution, gridBagConstraints16);
        gridBagConstraints15.gridx = 2;
        gridBagConstraints15.weightx = 0.75;
        gridBagConstraints15.fill = 2;
        gridBagConstraints15.anchor = 17;
        gridBagConstraints15.insets = new Insets(0, 20, 0, 0);
        final GridBagConstraints gridBagConstraints18 = gridBagConstraints15;
        ++gridBagConstraints18.gridx;
        smallPanel.add(panel3, gridBagConstraints15);
        final JPanel panel4 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
        gridBagConstraints19.gridx = 0;
        gridBagConstraints19.gridy = 0;
        gridBagConstraints19.fill = 2;
        gridBagConstraints19.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints19.weightx = 0.0;
        panel4.add(this.m_labelAnonymityHigh = new JLabel(JAPMessages.getString(JAPNewView.MSG_USER_ACTIVITY)), gridBagConstraints19);
        (this.m_progressAnonLevel = new JAPProgressBar()).setMinimum(0);
        this.m_progressAnonLevel.setMaximum(6);
        this.m_progressAnonLevel.setBorderPainted(false);
        final GridBagConstraints gridBagConstraints20 = gridBagConstraints19;
        ++gridBagConstraints20.gridx;
        gridBagConstraints19.weightx = 1.0;
        panel4.add(this.m_progressAnonLevel, gridBagConstraints19);
        final GridBagConstraints gridBagConstraints21 = gridBagConstraints15;
        ++gridBagConstraints21.gridx;
        gridBagConstraints15.weightx = 0.75;
        gridBagConstraints15.anchor = 13;
        gridBagConstraints15.insets = new Insets(0, 0, 0, 0);
        smallPanel.add(panel4, gridBagConstraints15);
        this.m_flippingpanelAnon.setSmallPanel(smallPanel);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 18;
        final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
        ++gridBagConstraints22.gridy;
        this.m_flippingpanelAnon.setFlipped(true);
        if (this.m_bIsSimpleView) {
            panel.add(this.m_flippingpanelAnon.getFullPanel(), gridBagConstraints);
        }
        else {
            panel.add(this.m_flippingpanelAnon, gridBagConstraints);
        }
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints23 = gridBagConstraints;
        ++gridBagConstraints23.gridy;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(new JSeparator(), gridBagConstraints);
        this.m_labelOwnActivity = new JLabel(JAPMessages.getString("ngActivity"), 4);
        if (this.m_bWithPayment) {
            this.m_flippingPanelPayment = new PaymentMainPanel(this, this.m_labelOwnActivity);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.anchor = 18;
            final GridBagConstraints gridBagConstraints24 = gridBagConstraints;
            ++gridBagConstraints24.gridy;
            this.m_flippingPanelPayment.setFlipped(false);
            if (this.m_bIsSimpleView) {
                panel.add(this.m_flippingPanelPayment.getSmallPanel(), gridBagConstraints);
            }
            else {
                panel.add(this.m_flippingPanelPayment, gridBagConstraints);
            }
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints25 = gridBagConstraints;
            ++gridBagConstraints25.gridy;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            panel.add(new JSeparator(), gridBagConstraints);
        }
        final GridBagLayout gridBagLayout4 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
        final JPanel fullPanel2 = new JPanel(gridBagLayout4);
        this.m_labelOwnTraffic = new JLabel(JAPMessages.getString(JAPNewView.MSG_LBL_ENCRYPTED_DATA) + ":");
        gridBagConstraints26.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints26.anchor = 17;
        gridBagConstraints26.weightx = 0.0;
        gridBagConstraints26.fill = 2;
        fullPanel2.add(this.m_labelOwnTraffic, gridBagConstraints26);
        final JPanel panel5 = new JPanel();
        final Dimension preferredSize = new Dimension(this.m_labelVersion.getFontMetrics(this.m_labelVersion.getFont()).charWidth('9') * 6, 1);
        panel5.setPreferredSize(preferredSize);
        gridBagConstraints26.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints26.gridx = 1;
        gridBagConstraints26.fill = 0;
        gridBagConstraints26.weightx = 1.0;
        fullPanel2.add(panel5, gridBagConstraints26);
        (this.m_labelOwnTrafficBytes = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints26.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints26.weightx = 0.0;
        gridBagConstraints26.fill = 2;
        gridBagConstraints26.gridx = 2;
        fullPanel2.add(this.m_labelOwnTrafficBytes, gridBagConstraints26);
        this.m_labelOwnTrafficUnit = new JLabel(JAPMessages.getString("Byte"));
        gridBagConstraints26.gridx = 3;
        fullPanel2.add(this.m_labelOwnTrafficUnit, gridBagConstraints26);
        gridBagConstraints26.weightx = 0.0;
        gridBagConstraints26.fill = 2;
        gridBagConstraints26.gridx = 4;
        gridBagConstraints26.insets = new Insets(0, 10, 0, 0);
        fullPanel2.add(this.m_labelOwnActivity, gridBagConstraints26);
        (this.m_progressOwnTrafficActivity = new JAPProgressBar()).setMinimum(0);
        this.m_progressOwnTrafficActivity.setMaximum(5);
        this.m_progressOwnTrafficActivity.setBorderPainted(false);
        gridBagConstraints26.gridx = 5;
        gridBagConstraints26.weightx = 0.0;
        gridBagConstraints26.fill = 0;
        gridBagConstraints26.insets = new Insets(0, 5, 0, 0);
        fullPanel2.add(this.m_progressOwnTrafficActivity, gridBagConstraints26);
        this.m_labelOwnTrafficWWW = new JLabel(JAPMessages.getString(JAPNewView.MSG_LBL_HTTP_DATA) + ":");
        gridBagConstraints26.insets = new Insets(10, 20, 0, 0);
        gridBagConstraints26.gridx = 0;
        gridBagConstraints26.gridy = 1;
        gridBagConstraints26.anchor = 17;
        gridBagConstraints26.weightx = 0.0;
        fullPanel2.add(this.m_labelOwnTrafficWWW, gridBagConstraints26);
        final JPanel panel6 = new JPanel();
        panel6.setPreferredSize(preferredSize);
        gridBagConstraints26.gridx = 1;
        gridBagConstraints26.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints26.weightx = 1.0;
        gridBagConstraints26.fill = 0;
        fullPanel2.add(panel6, gridBagConstraints26);
        (this.m_labelOwnTrafficBytesWWW = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints26.insets = new Insets(10, 5, 0, 0);
        gridBagConstraints26.gridx = 2;
        gridBagConstraints26.fill = 2;
        gridBagConstraints26.weightx = 0.0;
        fullPanel2.add(this.m_labelOwnTrafficBytesWWW, gridBagConstraints26);
        this.m_labelOwnTrafficUnitWWW = new JLabel(JAPMessages.getString("Byte"));
        gridBagConstraints26.gridx = 3;
        fullPanel2.add(this.m_labelOwnTrafficUnitWWW, gridBagConstraints26);
        this.m_labelOwnTrafficOther = new JLabel(JAPMessages.getString(JAPNewView.MSG_LBL_OTHER_DATA) + ":");
        gridBagConstraints26.insets = new Insets(7, 20, 0, 0);
        gridBagConstraints26.gridx = 0;
        gridBagConstraints26.gridy = 2;
        fullPanel2.add(this.m_labelOwnTrafficOther, gridBagConstraints26);
        final JPanel panel7 = new JPanel();
        panel7.setPreferredSize(preferredSize);
        gridBagConstraints26.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints26.weightx = 1.0;
        gridBagConstraints26.gridx = 1;
        gridBagConstraints26.fill = 0;
        fullPanel2.add(panel7, gridBagConstraints26);
        (this.m_labelOwnTrafficBytesOther = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints26.fill = 2;
        gridBagConstraints26.weightx = 0.0;
        gridBagConstraints26.insets = new Insets(7, 5, 0, 0);
        gridBagConstraints26.gridx = 2;
        fullPanel2.add(this.m_labelOwnTrafficBytesOther, gridBagConstraints26);
        this.m_labelOwnTrafficUnitOther = new JLabel(JAPMessages.getString("Byte"));
        gridBagConstraints26.gridx = 3;
        fullPanel2.add(this.m_labelOwnTrafficUnitOther, gridBagConstraints26);
        this.m_flippingpanelOwnTraffic.setFullPanel(fullPanel2);
        final GridBagLayout gridBagLayout5 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
        final JPanel smallPanel2 = new JPanel(gridBagLayout5);
        this.m_labelOwnTrafficSmall = new JLabel(JAPMessages.getString(JAPNewView.MSG_LBL_ENCRYPTED_DATA) + ":");
        gridBagConstraints27.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints27.weightx = 0.0;
        gridBagConstraints27.fill = 0;
        gridBagConstraints27.anchor = 17;
        smallPanel2.add(this.m_labelOwnTrafficSmall, gridBagConstraints27);
        (this.m_labelOwnTrafficBytesSmall = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints27.weightx = 1.0;
        gridBagConstraints27.fill = 2;
        gridBagConstraints27.gridx = 1;
        smallPanel2.add(this.m_labelOwnTrafficBytesSmall, gridBagConstraints27);
        this.m_labelOwnTrafficUnitSmall = new JLabel(JAPMessages.getString("Byte"));
        gridBagConstraints27.gridx = 2;
        gridBagConstraints27.weightx = 0.0;
        gridBagConstraints27.fill = 0;
        smallPanel2.add(this.m_labelOwnTrafficUnitSmall, gridBagConstraints27);
        this.m_labelOwnActivitySmall = new JLabel(JAPMessages.getString("ngActivity"), 4);
        gridBagConstraints27.insets = new Insets(0, 10, 0, 0);
        gridBagConstraints27.gridx = 3;
        smallPanel2.add(this.m_labelOwnActivitySmall, gridBagConstraints27);
        (this.m_progressOwnTrafficActivitySmall = new JAPProgressBar()).setMinimum(0);
        this.m_progressOwnTrafficActivitySmall.setMaximum(5);
        this.m_progressOwnTrafficActivitySmall.setBorderPainted(false);
        gridBagConstraints27.weightx = 0.0;
        gridBagConstraints27.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints27.fill = 0;
        gridBagConstraints27.gridx = 4;
        smallPanel2.add(this.m_progressOwnTrafficActivitySmall, gridBagConstraints27);
        this.m_flippingpanelOwnTraffic.setSmallPanel(smallPanel2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 18;
        final GridBagConstraints gridBagConstraints28 = gridBagConstraints;
        ++gridBagConstraints28.gridy;
        if (this.m_bIsSimpleView) {
            panel.add(this.m_flippingpanelOwnTraffic.getSmallPanel(), gridBagConstraints);
        }
        else {
            panel.add(this.m_flippingpanelOwnTraffic, gridBagConstraints);
        }
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints29 = gridBagConstraints;
        ++gridBagConstraints29.gridy;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(new JSeparator(), gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 18;
        final GridBagConstraints gridBagConstraints30 = gridBagConstraints;
        ++gridBagConstraints30.gridy;
        panel.add(this.m_flippingpanelForward = this.buildForwarderPanel(), gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints31 = gridBagConstraints;
        ++gridBagConstraints31.gridy;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(new JSeparator(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints32 = gridBagConstraints;
        ++gridBagConstraints32.gridy;
        final JPanel panel8 = new JPanel(new GridBagLayout());
        (this.m_buttonDeleteMessage = new JLabel(JAPMessages.getString(JAPNewView.MSG_HIDE_MESSAGE_SHORT))).setCursor(Cursor.getPredefinedCursor(12));
        this.m_buttonDeleteMessage.setToolTipText(JAPMessages.getString(JAPNewView.MSG_DELETE_MESSAGE));
        this.m_StatusPanel = new StatusPanel(this.m_buttonDeleteMessage);
        final GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
        gridBagConstraints33.gridx = 0;
        gridBagConstraints33.gridy = 0;
        gridBagConstraints33.weightx = 1.0;
        gridBagConstraints33.fill = 2;
        panel8.add(this.m_StatusPanel, gridBagConstraints33);
        gridBagConstraints33.weightx = 0.0;
        gridBagConstraints33.gridx = 1;
        gridBagConstraints33.anchor = 13;
        panel8.add(this.m_buttonDeleteMessage, gridBagConstraints33);
        panel.add(panel8, gridBagConstraints);
        final GridBagLayout gridBagLayout6 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
        final JPanel panel9 = new JPanel(gridBagLayout6);
        (this.m_bttnHelp = new JButton(JAPMessages.getString(JAPHelp.MSG_HELP_BUTTON))).setToolTipText(JAPMessages.getString(JAPHelp.MSG_HELP_BUTTON));
        (this.m_btnAbout = new JButton()).setToolTipText(JAPMessages.getString("aboutBox"));
        (this.m_bttnQuit = new JButton(JAPMessages.getString("quitButton"))).setToolTipText(JAPMessages.getString("quitButton"));
        (this.m_btnAssistant = new JButton(JAPMessages.getString(JAPNewView.MSG_BTN_ASSISTANT))).setToolTipText(JAPMessages.getString(JAPNewView.MSG_BTN_ASSISTANT));
        (this.m_bttnConf = new JButton(JAPMessages.getString("confButton"))).setToolTipText(JAPMessages.getString("confButton"));
        (this.m_bttnIconify = new JButton()).setToolTipText(JAPMessages.getString("iconifyWindow"));
        gridBagConstraints34.fill = 3;
        panel9.add(this.m_bttnIconify, gridBagConstraints34);
        gridBagConstraints34.gridx = 1;
        gridBagConstraints34.insets = new Insets(0, 5, 0, 0);
        panel9.add(this.m_btnAbout, gridBagConstraints34);
        final GridBagConstraints gridBagConstraints35 = gridBagConstraints34;
        ++gridBagConstraints35.gridx;
        panel9.add(this.m_bttnHelp, gridBagConstraints34);
        final GridBagConstraints gridBagConstraints36 = gridBagConstraints34;
        ++gridBagConstraints36.gridx;
        panel9.add(this.m_btnAssistant, gridBagConstraints34);
        final GridBagConstraints gridBagConstraints37 = gridBagConstraints34;
        ++gridBagConstraints37.gridx;
        panel9.add(this.m_bttnConf, gridBagConstraints34);
        final GridBagConstraints gridBagConstraints38 = gridBagConstraints34;
        ++gridBagConstraints38.gridx;
        gridBagConstraints34.fill = 2;
        panel9.add(new JLabel(), gridBagConstraints34);
        final GridBagConstraints gridBagConstraints39 = gridBagConstraints34;
        ++gridBagConstraints39.gridx;
        panel9.add(this.m_bttnQuit, gridBagConstraints34);
        this.m_bttnIconify.addActionListener(this);
        this.m_bttnConf.addActionListener(this);
        this.m_btnAbout.addActionListener(this);
        this.m_bttnHelp.addActionListener(this);
        this.m_bttnQuit.addActionListener(this);
        this.m_btnAssistant.addActionListener(this);
        JAPUtil.setMnemonic(this.m_bttnIconify, JAPMessages.getString("iconifyButtonMn"));
        JAPUtil.setMnemonic(this.m_bttnConf, JAPMessages.getString("confButtonMn"));
        JAPUtil.setMnemonic(this.m_bttnHelp, JAPMessages.getString("helpButtonMn"));
        JAPUtil.setMnemonic(this.m_bttnQuit, JAPMessages.getString("quitButtonMn"));
        JAPUtil.setMnemonic(this.m_btnAssistant, JAPMessages.getString(JAPNewView.MSG_MN_ASSISTANT));
        final GridBagConstraints gridBagConstraints40 = gridBagConstraints;
        ++gridBagConstraints40.gridy;
        panel.add(panel9, gridBagConstraints);
        this.getContentPane().setBackground(panel9.getBackground());
        this.getContentPane().add(panel, "Center");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                if (JAPNewView.this.isEnabled()) {
                    JAPController.goodBye(true);
                }
            }
            
            public void windowDeiconified(final WindowEvent windowEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                JAPNewView.this.m_bIsIconified = false;
                JAPNewView.this.updateValues(false);
            }
            
            public void windowIconified(final WindowEvent windowEvent) {
                JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                JAPNewView.this.hideWindowInTaskbar();
                JAPNewView.this.m_bIsIconified = true;
                JAPNewView.this.updateValues(false);
            }
        });
        this.updateFonts();
        this.setOptimalSize();
        this.m_comboAnonServices.addItemListener(new ItemListener() {
            private final /* synthetic */ JAPNewView this$0;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                final MixCascade mixCascade = (MixCascade)JAPNewView.this.m_comboAnonServices.getSelectedItem();
                if (JAPNewView.this.m_bIgnoreAnonComboEvents) {
                    return;
                }
                if (itemEvent.getStateChange() == 1) {
                    SwingUtilities.invokeLater(new Runnable() {
                        private final /* synthetic */ JAPNewView$15 this$1 = this$1;
                        
                        public void run() {
                            this.this$1.this$0.m_Controller.setCurrentMixCascade(mixCascade);
                        }
                    });
                }
            }
        });
        PayAccountsFile.getInstance().addMessageListener(this);
        PayAccountsFile.fireKnownMessages();
        this.updateValues(true);
        GUIUtils.centerOnScreen(this);
        GUIUtils.restoreLocation(this, JAPModel.getMainWindowLocation());
        Database.getInstance((JAPNewView.class$anon$infoservice$StatusInfo == null) ? (JAPNewView.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : JAPNewView.class$anon$infoservice$StatusInfo).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$JAPVersionInfo == null) ? (JAPNewView.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPNewView.class$anon$infoservice$JAPVersionInfo).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$JavaVersionDBEntry == null) ? (JAPNewView.class$anon$infoservice$JavaVersionDBEntry = class$("anon.infoservice.JavaVersionDBEntry")) : JAPNewView.class$anon$infoservice$JavaVersionDBEntry).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$MixCascade == null) ? (JAPNewView.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPNewView.class$anon$infoservice$MixCascade).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$CascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPNewView.class$anon$infoservice$CascadeIDEntry).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : JAPNewView.class$anon$infoservice$BlacklistedCascadeIDEntry).addObserver(this);
        Database.getInstance((JAPNewView.class$anon$infoservice$MessageDBEntry == null) ? (JAPNewView.class$anon$infoservice$MessageDBEntry = class$("anon.infoservice.MessageDBEntry")) : JAPNewView.class$anon$infoservice$MessageDBEntry).addObserver(this);
        TrustModel.addModelObserver(this);
        JAPModel.getInstance().addObserver(this);
        JAPModel.getInstance().getRoutingSettings().addObserver(this);
        JAPHelp.init(this, JAPModel.getInstance());
        if (JAPHelp.getHelpDialog() != null) {
            JAPHelp.getHelpDialog().setLocationCenteredOnOwner();
            JAPHelp.getHelpDialog().resetAutomaticLocation(JAPModel.getInstance().isHelpWindowLocationSaved());
            JAPHelp.getHelpDialog().restoreLocation(JAPModel.getInstance().getHelpWindowLocation());
            JAPHelp.getHelpDialog().restoreSize(JAPModel.getInstance().getHelpWindowSize());
        }
        this.m_mainMovedAdapter = new ComponentMovedAdapter();
        this.m_helpMovedAdapter = new ComponentMovedAdapter();
        this.m_configMovedAdapter = new ComponentMovedAdapter();
        this.addComponentListener(this.m_mainMovedAdapter);
        if (JAPHelp.getHelpDialog() != null) {
            JAPHelp.getHelpDialog().addComponentListener(this.m_helpMovedAdapter);
        }
        synchronized (this.LOCK_CONFIG) {
            if (this.m_dlgConfig == null) {
                (this.m_dlgConfig = new JAPConf(this, this.m_bWithPayment)).addComponentListener(this.m_configMovedAdapter);
            }
        }
        if (!JAPModel.isInfoServiceDisabled()) {
            this.fetchMixCascadesAsync(false);
        }
    }
    
    private JPanel buildForwarderPanel() {
        final FlippingPanel flippingPanel = new FlippingPanel(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = 17;
        final JPanel fullPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final JPanel panel = new JPanel(new GridBagLayout());
        this.m_labelForwarding = new JLabel(JAPMessages.getString("ngForwarding"));
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints2.anchor = 17;
        panel.add(this.m_labelForwarding, gridBagConstraints2);
        (this.m_cbForwarding = new JCheckBox(JAPMessages.getString("ngForwardingOn"))).setBorder(null);
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (!JAPNewView.this.m_Controller.enableForwardingServer(((JCheckBox)actionEvent.getSource()).isSelected())) {
                    ((JCheckBox)actionEvent.getSource()).setSelected(false);
                }
            }
        };
        this.m_cbForwarding.addActionListener(actionListener);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.insets = new Insets(0, 5, 0, 0);
        panel.add(this.m_cbForwarding, gridBagConstraints2);
        this.m_labelForwardingError = new JLabel();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.insets = new Insets(0, 15, 0, 0);
        panel.add(this.m_labelForwardingError, gridBagConstraints2);
        this.m_labelForwarderActivity = new JLabel(JAPMessages.getString("ngActivity"));
        gridBagConstraints2.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        panel.add(this.m_labelForwarderActivity, gridBagConstraints2);
        (this.m_progForwarderActivity = new JAPProgressBar()).setMinimum(0);
        this.m_progForwarderActivity.setMaximum(5);
        this.m_progForwarderActivity.setBorderPainted(false);
        gridBagConstraints2.gridx = 4;
        panel.add(this.m_progForwarderActivity, gridBagConstraints2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        fullPanel.add(panel, gridBagConstraints);
        this.m_labelForwarderConnections = new JLabel(JAPMessages.getString("ngForwardedConnections"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(10, 5, 0, 0);
        fullPanel.add(this.m_labelForwarderConnections, gridBagConstraints);
        final JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(this.m_labelForwarderConnections.getFontMetrics(this.m_labelForwarderConnections.getFont()).charWidth('9') * 6, 1));
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        fullPanel.add(panel2, gridBagConstraints);
        (this.m_labelForwarderCurrentConnections = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints.insets = new Insets(10, 5, 0, 0);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridx = 2;
        fullPanel.add(this.m_labelForwarderCurrentConnections, gridBagConstraints);
        this.m_labelForwarderCurrentConnectionsLabel = new JLabel(JAPMessages.getString("ngForwardedCurrentConnections"));
        gridBagConstraints.gridx = 3;
        fullPanel.add(this.m_labelForwarderCurrentConnectionsLabel, gridBagConstraints);
        (this.m_labelForwarderAcceptedConnections = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints.insets = new Insets(7, 5, 0, 0);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        fullPanel.add(this.m_labelForwarderAcceptedConnections, gridBagConstraints);
        this.m_labelForwarderAcceptedConnectionsLabel = new JLabel(JAPMessages.getString("ngForwardedAcceptedConnections"));
        gridBagConstraints.gridx = 3;
        fullPanel.add(this.m_labelForwarderAcceptedConnectionsLabel, gridBagConstraints);
        (this.m_labelForwarderRejectedConnections = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        fullPanel.add(this.m_labelForwarderRejectedConnections, gridBagConstraints);
        this.m_labelForwarderRejectedConnectionsLabel = new JLabel(JAPMessages.getString("ngForwardedRejectedConnections"));
        gridBagConstraints.gridx = 3;
        fullPanel.add(this.m_labelForwarderRejectedConnectionsLabel, gridBagConstraints);
        this.m_labelForwardedTraffic = new JLabel(JAPMessages.getString("ngForwardedTraffic"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        fullPanel.add(this.m_labelForwardedTraffic, gridBagConstraints);
        (this.m_labelForwardedTrafficBytes = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints.gridx = 2;
        fullPanel.add(this.m_labelForwardedTrafficBytes, gridBagConstraints);
        this.m_labelForwardedTrafficBytesUnit = new JLabel(JAPMessages.getString("Byte"));
        gridBagConstraints.gridx = 3;
        fullPanel.add(this.m_labelForwardedTrafficBytesUnit, gridBagConstraints);
        this.m_labelForwarderUsedBandwidthLabel = new JLabel(JAPMessages.getString("ngForwardedUsedBandwidth"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        fullPanel.add(this.m_labelForwarderUsedBandwidthLabel, gridBagConstraints);
        (this.m_labelForwarderUsedBandwidth = new JLabel("0")).setHorizontalAlignment(4);
        gridBagConstraints.gridx = 2;
        fullPanel.add(this.m_labelForwarderUsedBandwidth, gridBagConstraints);
        final JLabel label = new JLabel("Byte/s");
        gridBagConstraints.gridx = 3;
        fullPanel.add(label, gridBagConstraints);
        flippingPanel.setFullPanel(fullPanel);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final JPanel smallPanel = new JPanel(new GridBagLayout());
        this.m_labelForwardingSmall = new JLabel(JAPMessages.getString("ngForwarding"));
        gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints3.anchor = 17;
        smallPanel.add(this.m_labelForwardingSmall, gridBagConstraints3);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.fill = 2;
        (this.m_cbForwardingSmall = new JCheckBox(JAPMessages.getString("ngForwardingOn"))).setBorder(null);
        this.m_cbForwardingSmall.addActionListener(actionListener);
        smallPanel.add(this.m_cbForwardingSmall, gridBagConstraints3);
        this.m_labelForwardingErrorSmall = new JLabel();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.fill = 0;
        gridBagConstraints3.insets = new Insets(0, 15, 0, 0);
        smallPanel.add(this.m_labelForwardingErrorSmall, gridBagConstraints3);
        this.m_labelForwarderActivitySmall = new JLabel(JAPMessages.getString("ngActivity"));
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.fill = 0;
        gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
        smallPanel.add(this.m_labelForwarderActivitySmall, gridBagConstraints3);
        (this.m_progForwarderActivitySmall = new JAPProgressBar()).setMinimum(0);
        this.m_progForwarderActivitySmall.setMaximum(5);
        this.m_progForwarderActivitySmall.setBorderPainted(false);
        gridBagConstraints3.gridx = 4;
        smallPanel.add(this.m_progForwarderActivitySmall, gridBagConstraints3);
        flippingPanel.setSmallPanel(smallPanel);
        JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().addObserver(new Observer() {
            public void update(final Observable observable, final Object o) {
                try {
                    if (observable instanceof JAPRoutingServerStatisticsListener) {
                        final JAPRoutingServerStatisticsListener japRoutingServerStatisticsListener = (JAPRoutingServerStatisticsListener)observable;
                        final long transferedBytes = japRoutingServerStatisticsListener.getTransferedBytes();
                        JAPNewView.this.m_labelForwardedTrafficBytes.setText(Util.formatBytesValueWithoutUnit(transferedBytes));
                        JAPNewView.this.m_labelForwardedTrafficBytesUnit.setText(Util.formatBytesValueOnlyUnit(transferedBytes));
                        JAPNewView.this.m_labelForwarderAcceptedConnections.setText(Integer.toString(japRoutingServerStatisticsListener.getAcceptedConnections()));
                        JAPNewView.this.m_labelForwarderRejectedConnections.setText(Integer.toString(japRoutingServerStatisticsListener.getRejectedConnections()));
                        JAPNewView.this.m_labelForwarderCurrentConnections.setText(Integer.toString(japRoutingServerStatisticsListener.getCurrentlyForwardedConnections()));
                        JAPNewView.this.m_labelForwarderUsedBandwidth.setText(Integer.toString(japRoutingServerStatisticsListener.getCurrentBandwidthUsage()));
                    }
                }
                catch (Throwable t) {}
            }
        });
        if (!this.m_bIsSimpleView) {
            return flippingPanel;
        }
        return smallPanel;
    }
    
    public void disableSetAnonMode() {
        synchronized (this.SYNC_ICONIFIED_VIEW) {
            this.m_ViewIconified.disableSetAnonMode();
        }
        this.m_rbAnonOn.setEnabled(false);
        this.m_rbAnonOff.setEnabled(false);
    }
    
    private Icon getMeterImage(final MixCascade mixCascade, final StatusInfo statusInfo) {
        final boolean anonMode = super.m_Controller.getAnonMode();
        final boolean anonConnected = super.m_Controller.isAnonConnected();
        final boolean b = super.m_Controller.isConnecting() || this.m_bShowConnecting;
        String s = "";
        if (JAPMessages.getLocale().getLanguage() == "de") {
            s = "_de";
        }
        if (anonMode && anonConnected && mixCascade != null) {
            if (mixCascade.getDistribution() > 0) {
                return GUIUtils.loadImageIcon(MessageFormat.format("anonym-o-meter/JAP.NewView_m{0}.anim.gif", mixCascade.getDistribution() + "" + Math.max(0, statusInfo.getAnonLevel())), true, true);
            }
            return GUIUtils.loadImageIcon(MessageFormat.format("anonym-o-meter/JAP.no.measure.anim{0}.gif", s), true, true);
        }
        else {
            if (anonMode && !anonConnected && b && mixCascade != null) {
                return GUIUtils.loadImageIcon("anonym-o-meter/JAP.connecting.anim.gif", true, true);
            }
            if (mixCascade == null) {
                GUIUtils.loadImageIcon("anonym-o-meter/JAP.connecting.anim.gif", true, true);
            }
            return GUIUtils.loadImageIcon(MessageFormat.format("anonym-o-meter/JAP.deactivated.anim{0}.gif", s), true, true);
        }
    }
    
    private void blink(final long n) {
        this.m_blinkJobs.addJob(new JobQueue.Job() {
            private final /* synthetic */ JAPNewView this$0;
            
            public void runJob() {
                if (n > 0L && JAPNewView.this.m_ViewIconified != null) {
                    JAPNewView.this.m_ViewIconified.blink();
                }
                if (JAPNewView.this.isVisible()) {
                    final Runnable runnable = new Runnable() {
                        private final /* synthetic */ JAPNewView$19 this$1 = this$1;
                        
                        public void run() {
                            synchronized (this.this$1.this$0.m_progressOwnTrafficActivity) {
                                if (this.this$1.this$0.m_currentChannels == 0) {
                                    return;
                                }
                                if (this.this$1.this$0.m_Controller.isAnonConnected()) {
                                    this.this$1.this$0.m_progressOwnTrafficActivity.setValue(Math.min(this.this$1.this$0.m_currentChannels, this.this$1.this$0.m_progressOwnTrafficActivity.getMaximum()) - 1);
                                    this.this$1.this$0.m_progressOwnTrafficActivitySmall.setValue(Math.min(this.this$1.this$0.m_currentChannels, this.this$1.this$0.m_progressOwnTrafficActivity.getMaximum()) - 1);
                                    try {
                                        this.this$1.this$0.m_progressOwnTrafficActivity.wait(250L);
                                    }
                                    catch (InterruptedException ex) {}
                                }
                            }
                        }
                    };
                    try {
                        SwingUtilities.invokeAndWait(runnable);
                    }
                    catch (InvocationTargetException ex) {}
                    catch (InterruptedException ex2) {}
                    if (JAPNewView.this.m_Controller.isAnonConnected()) {
                        try {
                            Thread.sleep(250L);
                        }
                        catch (InterruptedException ex3) {}
                    }
                    final Runnable runnable2 = new Runnable() {
                        private final /* synthetic */ JAPNewView$19 this$1 = this$1;
                        
                        public void run() {
                            synchronized (this.this$1.this$0.m_progressOwnTrafficActivity) {
                                if (!this.this$1.this$0.m_Controller.isAnonConnected()) {
                                    this.this$1.this$0.m_currentChannels = 0;
                                }
                                this.this$1.this$0.m_progressOwnTrafficActivity.setValue(this.this$1.this$0.m_currentChannels);
                                this.this$1.this$0.m_progressOwnTrafficActivitySmall.setValue(this.this$1.this$0.m_currentChannels);
                            }
                        }
                    };
                    try {
                        SwingUtilities.invokeAndWait(runnable2);
                    }
                    catch (InvocationTargetException ex4) {}
                    catch (InterruptedException ex5) {}
                }
            }
        });
    }
    
    public void update(final Observable observable, final Object o) {
        Runnable runnable = null;
        Label_2014: {
            if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$StatusInfo == null) ? (JAPNewView.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : JAPNewView.class$anon$infoservice$StatusInfo)) {
                final Object messageData = ((DatabaseMessage)o).getMessageData();
                if (messageData instanceof StatusInfo && ((StatusInfo)messageData).getId().equals(JAPController.getInstance().getCurrentMixCascade().getId())) {
                    this.updateValues(false);
                }
            }
            else if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$JAPVersionInfo == null) ? (JAPNewView.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPNewView.class$anon$infoservice$JAPVersionInfo)) {
                this.updateValues(false);
            }
            else if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : JAPNewView.class$anon$infoservice$BlacklistedCascadeIDEntry)) {
                final DatabaseMessage databaseMessage = (DatabaseMessage)o;
                if (databaseMessage == null) {
                    return;
                }
                if (databaseMessage.getMessageCode() != 5) {
                    this.m_bTrustChanged = true;
                    this.updateValues(false);
                }
            }
            else if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$MixCascade == null) ? (JAPNewView.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPNewView.class$anon$infoservice$MixCascade)) {
                final DatabaseMessage databaseMessage2 = (DatabaseMessage)o;
                if (databaseMessage2.getMessageData() == null || !(databaseMessage2.getMessageData() instanceof MixCascade)) {
                    return;
                }
                final MixCascade currentMixCascade = (MixCascade)databaseMessage2.getMessageData();
                if (databaseMessage2.getMessageCode() != 2 && databaseMessage2.getMessageCode() != 5 && currentMixCascade.isUserDefined()) {
                    this.m_bTrustChanged = true;
                }
                Label_0713: {
                    if (databaseMessage2.getMessageCode() == 1 || databaseMessage2.getMessageCode() == 2) {
                        final MixCascade currentMixCascade2 = JAPController.getInstance().getCurrentMixCascade();
                        if (currentMixCascade2.equals(currentMixCascade) && TrustModel.getCurrentTrustModel().isTrusted(currentMixCascade2) != TrustModel.getCurrentTrustModel().isTrusted(currentMixCascade)) {
                            JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
                            this.m_bTrustChanged = true;
                        }
                        Database.getInstance((JAPNewView.class$anon$infoservice$CascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPNewView.class$anon$infoservice$CascadeIDEntry).update(new CascadeIDEntry(currentMixCascade));
                        if (Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).getEntryById(currentMixCascade.getMixIDsAsString()) == null) {
                            break Label_0713;
                        }
                        this.m_bTrustChanged = true;
                        if (JAPController.getInstance().getCurrentMixCascade().isPayment()) {
                            break Label_0713;
                        }
                        synchronized (this.SYNC_NEW_SERVICES) {
                            if (this.m_newServicesID < 0) {
                                this.m_newServicesID = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_LBL_NEW_SERVICES_FOUND), 1, false, this.m_listenerNewServices);
                            }
                            break Label_0713;
                        }
                    }
                    if ((databaseMessage2.getMessageCode() == 3 || databaseMessage2.getMessageCode() == 4) && Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).getEntryById(currentMixCascade.getMixIDsAsString()) != null) {
                        this.m_bTrustChanged = true;
                        final Enumeration entrySnapshotAsEnumeration = Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).getEntrySnapshotAsEnumeration();
                        boolean b = true;
                        while (entrySnapshotAsEnumeration.hasMoreElements()) {
                            final NewCascadeIDEntry newCascadeIDEntry = entrySnapshotAsEnumeration.nextElement();
                            if (Database.getInstance((JAPNewView.class$anon$infoservice$MixCascade == null) ? (JAPNewView.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPNewView.class$anon$infoservice$MixCascade).getEntryById(newCascadeIDEntry.getCascadeId()) != null && !newCascadeIDEntry.getCascadeId().equals(currentMixCascade.getId())) {
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            synchronized (this.SYNC_NEW_SERVICES) {
                                if (this.m_newServicesID >= 0) {
                                    this.m_StatusPanel.removeStatusMsg(this.m_newServicesID);
                                    this.m_newServicesID = -1;
                                }
                            }
                        }
                    }
                }
                this.updateValues(false);
            }
            else if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$CascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPNewView.class$anon$infoservice$CascadeIDEntry)) {
                final DatabaseMessage databaseMessage3 = (DatabaseMessage)o;
                if (databaseMessage3.getMessageData() == null) {
                    return;
                }
                if (databaseMessage3.getMessageCode() == 1) {
                    Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).update(new NewCascadeIDEntry((CascadeIDEntry)databaseMessage3.getMessageData()));
                }
            }
            else {
                if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry)) {
                    final DatabaseMessage databaseMessage4 = (DatabaseMessage)o;
                    if (databaseMessage4.getMessageData() == null) {
                        return;
                    }
                    boolean b2 = false;
                    Label_1071: {
                        if (databaseMessage4.getMessageCode() == 1 || databaseMessage4.getMessageCode() == 2) {
                            if (JAPController.getInstance().getCurrentMixCascade().isPayment()) {
                                break Label_1071;
                            }
                            synchronized (this.SYNC_NEW_SERVICES) {
                                if (this.m_newServicesID < 0) {
                                    this.m_newServicesID = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_LBL_NEW_SERVICES_FOUND), 1, false, this.m_listenerNewServices);
                                }
                                break Label_1071;
                            }
                        }
                        if (databaseMessage4.getMessageCode() == 3) {
                            final Enumeration entrySnapshotAsEnumeration2 = Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).getEntrySnapshotAsEnumeration();
                            b2 = true;
                            while (entrySnapshotAsEnumeration2.hasMoreElements()) {
                                if (Database.getInstance((JAPNewView.class$anon$infoservice$MixCascade == null) ? (JAPNewView.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPNewView.class$anon$infoservice$MixCascade).getEntryById(entrySnapshotAsEnumeration2.nextElement().getCascadeId()) != null) {
                                    b2 = false;
                                    break;
                                }
                            }
                        }
                        else if (databaseMessage4.getMessageCode() == 4) {
                            b2 = true;
                        }
                    }
                    if (!b2) {
                        break Label_2014;
                    }
                    synchronized (this.SYNC_NEW_SERVICES) {
                        if (this.m_newServicesID >= 0) {
                            this.m_StatusPanel.removeStatusMsg(this.m_newServicesID);
                            this.m_newServicesID = -1;
                        }
                        break Label_2014;
                    }
                }
                if (observable instanceof TrustModel.InnerObservable) {
                    this.m_bTrustChanged = true;
                    this.updateValues(false);
                }
                else if (o != null && (o.equals(JAPModel.CHANGED_INFOSERVICE_AUTO_UPDATE) || o.equals(JAPModel.CHANGED_ALLOW_INFOSERVICE_DIRECT_CONNECTION))) {
                    runnable = new Runnable() {
                        public void run() {
                            if (!JAPController.getInstance().isShuttingDown() && (JAPModel.isInfoServiceDisabled() || (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 1 && !JAPController.getInstance().isAnonConnected()))) {
                                synchronized (JAPNewView.this.SYNC_STATUS_ENABLE_IS) {
                                    if (JAPNewView.this.m_enableInfoServiceID < 0) {
                                        JAPNewView.this.m_enableInfoServiceID = JAPNewView.this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_IS_DEACTIVATED), 2, false, JAPNewView.this.m_listenerEnableIS);
                                    }
                                    return;
                                }
                            }
                            synchronized (JAPNewView.this.SYNC_STATUS_ENABLE_IS) {
                                if (JAPNewView.this.m_enableInfoServiceID >= 0) {
                                    JAPNewView.this.m_StatusPanel.removeStatusMsg(JAPNewView.this.m_enableInfoServiceID);
                                    JAPNewView.this.m_enableInfoServiceID = -1;
                                }
                            }
                        }
                    };
                }
                else {
                    if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$MessageDBEntry == null) ? (JAPNewView.class$anon$infoservice$MessageDBEntry = class$("anon.infoservice.MessageDBEntry")) : JAPNewView.class$anon$infoservice$MessageDBEntry)) {
                        final DatabaseMessage databaseMessage5 = (DatabaseMessage)o;
                        if (databaseMessage5.getMessageData() == null) {
                            return;
                        }
                        if (databaseMessage5.getMessageCode() == 5) {
                            return;
                        }
                        final MessageDBEntry messageDBEntry = (MessageDBEntry)databaseMessage5.getMessageData();
                        if (messageDBEntry.isForFreeCascadesOnly() && JAPController.getInstance().getCurrentMixCascade().isPayment()) {
                            return;
                        }
                        synchronized (this.m_messageIDs) {
                            if (messageDBEntry != null && (databaseMessage5.getMessageCode() == 1 || databaseMessage5.getMessageCode() == 2)) {
                                if (messageDBEntry.isDummy()) {
                                    final MessageDBEntry messageDBEntry2 = this.m_messageIDs.remove(messageDBEntry.getId());
                                    if (messageDBEntry2 != null) {
                                        this.m_StatusPanel.removeStatusMsg(messageDBEntry2.getExternalIdentifier());
                                    }
                                    return;
                                }
                                final StatusPanel.ButtonListener buttonListener = new StatusPanel.ButtonListener() {
                                    static /* synthetic */ Class class$anon$infoservice$DeletedMessageIDDBEntry;
                                    static /* synthetic */ Class class$anon$infoservice$ClickedMessageIDDBEntry;
                                    
                                    public void actionPerformed(final ActionEvent actionEvent) {
                                        if (JAPDialog.showConfirmDialog(JAPNewView.this, (actionEvent != null) ? JAPMessages.getString(JAPNewView.MSG_DELETE_MESSAGE_EXPLAIN) : ((messageDBEntry.getPopupText(JAPMessages.getLocale()) != null) ? messageDBEntry.getPopupText(JAPMessages.getLocale()) : messageDBEntry.getText(JAPMessages.getLocale())), JAPMessages.getString(JAPDialog.MSG_TITLE_INFO), new JAPDialog.Options(2) {
                                            public String getCancelText() {
                                                return JAPMessages.getString(DialogContentPane.MSG_OK);
                                            }
                                            
                                            public String getYesOKText() {
                                                return JAPMessages.getString(JAPNewView.MSG_DELETE_MESSAGE);
                                            }
                                        }, 1, new JAPDialog.AbstractLinkedURLAdapter() {
                                            public boolean isOnTop() {
                                                return true;
                                            }
                                            
                                            public URL getUrl() {
                                                return messageDBEntry.getURL(JAPMessages.getLocale());
                                            }
                                            
                                            public String getMessage() {
                                                return JAPMessages.getString(JAPNewView.MSG_VIEW_MESSAGE);
                                            }
                                        }) == 0) {
                                            synchronized (JAPNewView.this.m_messageIDs) {
                                                JAPNewView.this.m_StatusPanel.removeStatusMsg(messageDBEntry.getExternalIdentifier());
                                                JAPNewView.this.m_messageIDs.remove(messageDBEntry.getId());
                                                Database.getInstance((JAPNewView$23.class$anon$infoservice$DeletedMessageIDDBEntry == null) ? (JAPNewView$23.class$anon$infoservice$DeletedMessageIDDBEntry = class$("anon.infoservice.DeletedMessageIDDBEntry")) : JAPNewView$23.class$anon$infoservice$DeletedMessageIDDBEntry).update(new DeletedMessageIDDBEntry(messageDBEntry));
                                            }
                                        }
                                    }
                                    
                                    public boolean isButtonShown() {
                                        final ClickedMessageIDDBEntry clickedMessageIDDBEntry = (ClickedMessageIDDBEntry)Database.getInstance((JAPNewView$23.class$anon$infoservice$ClickedMessageIDDBEntry == null) ? (JAPNewView$23.class$anon$infoservice$ClickedMessageIDDBEntry = class$("anon.infoservice.ClickedMessageIDDBEntry")) : JAPNewView$23.class$anon$infoservice$ClickedMessageIDDBEntry).getEntryById(messageDBEntry.getId());
                                        return clickedMessageIDDBEntry != null && clickedMessageIDDBEntry.getVersionNumber() >= messageDBEntry.getVersionNumber();
                                    }
                                    
                                    static /* synthetic */ Class class$(final String s) {
                                        try {
                                            return Class.forName(s);
                                        }
                                        catch (ClassNotFoundException ex) {
                                            throw new NoClassDefFoundError(ex.getMessage());
                                        }
                                    }
                                };
                                final DeletedMessageIDDBEntry deletedMessageIDDBEntry = (DeletedMessageIDDBEntry)Database.getInstance((JAPNewView.class$anon$infoservice$DeletedMessageIDDBEntry == null) ? (JAPNewView.class$anon$infoservice$DeletedMessageIDDBEntry = class$("anon.infoservice.DeletedMessageIDDBEntry")) : JAPNewView.class$anon$infoservice$DeletedMessageIDDBEntry).getEntryById(messageDBEntry.getId());
                                if ((deletedMessageIDDBEntry == null || deletedMessageIDDBEntry.getVersionNumber() < messageDBEntry.getVersionNumber()) && this.m_messageIDs.get(messageDBEntry.getId()) == null && !messageDBEntry.isDummy()) {
                                    messageDBEntry.setExternalIdentifier(this.m_StatusPanel.addStatusMsg(messageDBEntry.getText(JAPMessages.getLocale()), 1, false, new ActionListener() {
                                        static /* synthetic */ Class class$anon$infoservice$ClickedMessageIDDBEntry;
                                        
                                        public void actionPerformed(final ActionEvent actionEvent) {
                                            Database.getInstance((JAPNewView$26.class$anon$infoservice$ClickedMessageIDDBEntry == null) ? (JAPNewView$26.class$anon$infoservice$ClickedMessageIDDBEntry = class$("anon.infoservice.ClickedMessageIDDBEntry")) : JAPNewView$26.class$anon$infoservice$ClickedMessageIDDBEntry).update(new ClickedMessageIDDBEntry(messageDBEntry));
                                            AbstractOS.getInstance().openURL(messageDBEntry.getURL(JAPMessages.getLocale()));
                                        }
                                        
                                        static /* synthetic */ Class class$(final String s) {
                                            try {
                                                return Class.forName(s);
                                            }
                                            catch (ClassNotFoundException ex) {
                                                throw new NoClassDefFoundError(ex.getMessage());
                                            }
                                        }
                                    }, buttonListener));
                                    this.m_messageIDs.put(messageDBEntry.getId(), messageDBEntry);
                                }
                                if (messageDBEntry.isPopupShown() && !buttonListener.isButtonShown()) {
                                    new Thread(new Runnable() {
                                        static /* synthetic */ Class class$anon$infoservice$ClickedMessageIDDBEntry;
                                        private final /* synthetic */ StatusPanel.ButtonListener val$buttonListener = buttonListener;
                                        
                                        public void run() {
                                            Database.getInstance((JAPNewView$27.class$anon$infoservice$ClickedMessageIDDBEntry == null) ? (JAPNewView$27.class$anon$infoservice$ClickedMessageIDDBEntry = class$("anon.infoservice.ClickedMessageIDDBEntry")) : JAPNewView$27.class$anon$infoservice$ClickedMessageIDDBEntry).update(new ClickedMessageIDDBEntry(messageDBEntry));
                                            this.val$buttonListener.actionPerformed(null);
                                        }
                                        
                                        static /* synthetic */ Class class$(final String s) {
                                            try {
                                                return Class.forName(s);
                                            }
                                            catch (ClassNotFoundException ex) {
                                                throw new NoClassDefFoundError(ex.getMessage());
                                            }
                                        }
                                    }).start();
                                }
                            }
                            else {
                                if (messageDBEntry != null && databaseMessage5.getMessageCode() == 3) {
                                    final MessageDBEntry messageDBEntry3 = this.m_messageIDs.remove(messageDBEntry.getId());
                                    if (messageDBEntry3 != null) {
                                        this.m_StatusPanel.removeStatusMsg(messageDBEntry3.getExternalIdentifier());
                                    }
                                    return;
                                }
                                if (databaseMessage5.getMessageCode() == 4) {
                                    final Enumeration<MessageDBEntry> elements = (Enumeration<MessageDBEntry>)this.m_messageIDs.elements();
                                    while (elements.hasMoreElements()) {
                                        this.m_StatusPanel.removeStatusMsg(elements.nextElement().getExternalIdentifier());
                                    }
                                    this.m_StatusPanel.removeAll();
                                }
                            }
                            break Label_2014;
                        }
                    }
                    if (observable == Database.getInstance((JAPNewView.class$anon$infoservice$JavaVersionDBEntry == null) ? (JAPNewView.class$anon$infoservice$JavaVersionDBEntry = class$("anon.infoservice.JavaVersionDBEntry")) : JAPNewView.class$anon$infoservice$JavaVersionDBEntry)) {
                        if (JAPController.getInstance().hasPortableJava()) {
                            return;
                        }
                        final DatabaseMessage databaseMessage6 = (DatabaseMessage)o;
                        if (databaseMessage6.getMessageData() == null) {
                            return;
                        }
                        if (databaseMessage6.getMessageCode() == 1 || databaseMessage6.getMessageCode() == 2) {
                            final JavaVersionDBEntry javaVersionDBEntry = (JavaVersionDBEntry)databaseMessage6.getMessageData();
                            if (javaVersionDBEntry != null && (javaVersionDBEntry.isJavaTooOld() || javaVersionDBEntry.isJavaNoMoreSupported())) {
                                if (javaVersionDBEntry.isUpdateForced() || JAPModel.getInstance().isReminderForJavaUpdateActivated()) {
                                    synchronized (this.SYNC_STATUS_UPDATE_AVAILABLE) {
                                        if (this.m_updateAvailableID < 0) {
                                            this.m_updateAvailableID = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_UPDATE), 1, false, this.m_listenerUpdate);
                                        }
                                    }
                                }
                                if ((javaVersionDBEntry.isUpdateForced() || javaVersionDBEntry.isJavaNoMoreSupported() || JAPModel.getInstance().isReminderForJavaUpdateActivated()) && !JAPController.getInstance().isConfigAssistantShown()) {
                                    new Runnable() {
                                        public void run() {
                                            int n = 3;
                                            int n2 = 0;
                                            String s = JAPMessages.getString(JAPNewView.MSG_TITLE_OLD_JAVA);
                                            String s2;
                                            if (javaVersionDBEntry.isUpdateForced() || javaVersionDBEntry.isJavaNoMoreSupported()) {
                                                n = 2;
                                                s = JAPMessages.getString(JAPNewView.MSG_JAVA_FORCED_TITLE);
                                                final String string = "<p><b>" + JAPMessages.getString(JAPNewView.MSG_JAVA_FORCED_EXPLAIN, JavaVersionDBEntry.CURRENT_JAVA_VERSION) + "</b></p>";
                                                if (javaVersionDBEntry.isJavaNoMoreSupported() && !javaVersionDBEntry.isJavaTooOld()) {
                                                    n2 = -1;
                                                    s2 = string + "<br><p><b>" + JAPMessages.getString(JAPNewView.MSG_JAVA_FORCED_OS, "\u2265 " + javaVersionDBEntry.getLastSupportedJREVersion()) + "</b></p>";
                                                }
                                                else {
                                                    s2 = string + "<br><p>" + JAPMessages.getString(JAPNewView.MSG_JAVA_FORCED_QUESTION) + "</p>";
                                                }
                                            }
                                            else {
                                                s2 = JAPMessages.getString(JAPNewView.MSG_OLD_JAVA_HINT, new Object[] { javaVersionDBEntry.getJREVersion() });
                                            }
                                            JAPDialog.LinkedCheckBox linkedCheckBox = null;
                                            if (!javaVersionDBEntry.isUpdateForced() && !javaVersionDBEntry.isJavaNoMoreSupported()) {
                                                linkedCheckBox = new JAPDialog.LinkedCheckBox(false);
                                            }
                                            if (JAPDialog.showConfirmDialog(JAPController.getInstance().getCurrentView(), s2, s, n2, n, linkedCheckBox) == 0 && n2 != -1) {
                                                JAPNewView.this.showJavaUpdateDialog(javaVersionDBEntry);
                                            }
                                            if (linkedCheckBox != null && linkedCheckBox.getState()) {
                                                JAPModel.getInstance().setReminderForJavaUpdate(false);
                                                synchronized (JAPNewView.this.SYNC_STATUS_UPDATE_AVAILABLE) {
                                                    if (JAPVersionInfo.getRecommendedUpdate("00.12.005", true) == null && JAPNewView.this.m_updateAvailableID >= 0) {
                                                        JAPNewView.this.m_StatusPanel.removeStatusMsg(JAPNewView.this.m_updateAvailableID);
                                                        JAPNewView.this.m_updateAvailableID = -1;
                                                    }
                                                }
                                            }
                                        }
                                    }.run();
                                }
                            }
                        }
                    }
                    else if (observable == JAPModel.getInstance().getRoutingSettings()) {
                        final JAPRoutingMessage japRoutingMessage = (JAPRoutingMessage)o;
                        synchronized (JAPModel.getInstance().getRoutingSettings()) {
                            if (japRoutingMessage != null && japRoutingMessage.getMessageCode() == 16) {
                                if (JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder() && this.m_ForwardingID < 0) {
                                    this.m_ForwardingID = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_ANTI_CENSORSHIP), 2, false, new ActionListener() {
                                        public void actionPerformed(final ActionEvent actionEvent) {
                                            JAPDialog.showMessageDialog(JAPNewView.this, JAPMessages.getString(JAPConfNetwork.MSG_SLOW_ANTI_CENSORSHIP), new JAPDialog.LinkedHelpContext("forwarding_client"));
                                        }
                                    });
                                }
                                else if (!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder() && this.m_ForwardingID >= 0) {
                                    this.m_StatusPanel.removeStatusMsg(this.m_ForwardingID);
                                    this.m_ForwardingID = -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (runnable != null) {
            if (SwingUtilities.isEventDispatchThread()) {
                runnable.run();
            }
            else {
                try {
                    SwingUtilities.invokeAndWait(runnable);
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.GUI, ex);
                }
            }
        }
    }
    
    public void showIconifiedView() {
        this.m_comboAnonServices.closeCascadePopupMenu();
        synchronized (this.SYNC_ICONIFIED_VIEW) {
            if (this.m_ViewIconified != null) {
                this.m_ViewIconified.setVisible(true);
                this.setVisible(false);
                this.m_ViewIconified.toFront();
            }
        }
    }
    
    public void connectionEstablished(final AnonServerDescription anonServerDescription) {
        this.removeStatusMsg(this.m_msgIDInsecure);
        if (anonServerDescription != null && anonServerDescription instanceof MixCascade) {
            final MixCascade mixCascade = (MixCascade)anonServerDescription;
            Database.getInstance((JAPNewView.class$anon$infoservice$NewCascadeIDEntry == null) ? (JAPNewView.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : JAPNewView.class$anon$infoservice$NewCascadeIDEntry).remove(mixCascade.getMixIDsAsString());
            if (mixCascade.getNumberOfOperators() <= 1 || mixCascade.getDataRetentionInformation() != null) {
                this.m_msgIDInsecure = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_OBSERVABLE_TITLE), 2, false, new ActionListener() {
                    public void actionPerformed(final ActionEvent actionEvent) {
                        if (mixCascade.getDataRetentionInformation() != null) {
                            JAPDialog.showWarningDialog(JAPNewView.this, JAPMessages.getString(JAPNewView.MSG_DATA_RETENTION_EXPLAIN, new String[] { "<b>" + mixCascade.getName() + "</b>", "<i>" + JAPMessages.getString("ngBttnAnonDetails") + "</i>" }));
                        }
                        else {
                            JAPDialog.showWarningDialog(JAPNewView.this, JAPMessages.getString(JAPNewView.MSG_OBSERVABLE_EXPLAIN, "<b>" + mixCascade.getName() + "</b>"));
                        }
                        JAPNewView.this.doClickOnCascadeChooser();
                    }
                });
            }
        }
        new Thread(new Runnable() {
            public void run() {
                synchronized (JAPNewView.this.m_connectionEstablishedSync) {
                    JAPNewView.this.m_connectionEstablishedSync.notifyAll();
                }
            }
        }).start();
    }
    
    public void dataChainErrorSignaled() {
        this.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_ERROR_PROXY), 0, true);
    }
    
    public void dispose() {
        this.m_blinkJobs.stop();
        this.m_transferedBytesJobs.stop();
        this.m_channelsChangedJobs.stop();
        this.m_packetMixedJobs.stop();
        this.m_flippingPanelPayment.stopUpdateQueue();
        super.dispose();
    }
    
    public void disconnected() {
        this.removeStatusMsg(this.m_msgIDInsecure);
        new Thread(new Runnable() {
            public void run() {
                synchronized (JAPNewView.this.m_connectionEstablishedSync) {
                    JAPNewView.this.m_connectionEstablishedSync.notifyAll();
                }
            }
        }).start();
    }
    
    public void connecting(final AnonServerDescription anonServerDescription) {
        this.removeStatusMsg(this.m_msgIDInsecure);
        this.showConnecting(false);
    }
    
    public void connectionError() {
        this.removeStatusMsg(this.m_msgIDInsecure);
        this.showConnecting(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.m_comboAnonServices.closeCascadePopupMenu();
        synchronized (this.SYNC_ACTION) {
            if (this.m_bActionPerformed) {
                return;
            }
            this.m_bActionPerformed = true;
        }
        new Thread(new Runnable() {
            private final /* synthetic */ JAPNewView val$view;
            private final /* synthetic */ JAPNewView this$0;
            
            public void run() {
                Runnable runnable = null;
                final Object source = actionEvent.getSource();
                if (source == JAPNewView.this.m_bttnQuit) {
                    JAPController.goodBye(true);
                }
                else if (source == JAPNewView.this.m_bttnIconify) {
                    JAPNewView.this.showIconifiedView();
                }
                else if (source == JAPNewView.this.m_bttnConf) {
                    JAPNewView.this.showConfigDialog();
                }
                else if (source == JAPNewView.this.m_btnAbout) {
                    JAPNewView.this.m_comboAnonServices.closeCascadePopupMenu();
                    JAPController.aboutJAP();
                }
                else if (source == JAPNewView.this.m_btnAssistant) {
                    JAPController.getInstance().showInstallationAssistant();
                }
                else if (source == JAPNewView.this.m_bttnHelp) {
                    JAPNewView.this.showHelpWindow();
                }
                else if (source == JAPNewView.this.m_rbAnonOn || source == JAPNewView.this.m_rbAnonOff) {
                    JAPNewView.this.m_bActionPerformed = false;
                    runnable = new Runnable() {
                        private final /* synthetic */ JAPNewView$33 this$1 = this$1;
                        
                        public void run() {
                            if (this.this$1.this$0.m_rbAnonOn.isSelected()) {
                                this.this$1.this$0.m_Controller.startAnonymousMode(this.this$1.val$view);
                            }
                            else {
                                this.this$1.this$0.m_Controller.setAnonMode(false);
                            }
                        }
                    };
                }
                else if (source == JAPNewView.this.m_cbAnonymityOn) {
                    JAPNewView.this.m_bActionPerformed = false;
                    runnable = new Runnable() {
                        private final /* synthetic */ JAPNewView$33 this$1 = this$1;
                        
                        public void run() {
                            if (this.this$1.this$0.m_cbAnonymityOn.isSelected()) {
                                this.this$1.this$0.m_Controller.startAnonymousMode(this.this$1.val$view);
                            }
                            else {
                                this.this$1.this$0.m_Controller.setAnonMode(false);
                            }
                        }
                    };
                }
                else {
                    LogHolder.log(7, LogType.GUI, "Event ?????: " + actionEvent.getSource());
                }
                if (runnable != null) {
                    try {
                        SwingUtilities.invokeAndWait(runnable);
                    }
                    catch (Exception ex) {
                        LogHolder.log(3, LogType.GUI, ex);
                    }
                }
                JAPNewView.this.m_bActionPerformed = false;
            }
        }).start();
    }
    
    private void showConnecting(final boolean b) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                boolean b = false;
                synchronized (JAPNewView.this.m_connectionEstablishedSync) {
                    if (!b || JAPModel.isAutomaticallyReconnected()) {
                        if (JAPNewView.this.m_Controller.getAnonMode() && !JAPNewView.this.m_Controller.isAnonConnected()) {
                            if (JAPNewView.this.m_bShowConnecting) {
                                return;
                            }
                            JAPNewView.this.m_bShowConnecting = true;
                            JAPNewView.this.updateValues(true);
                            final int addStatusMsg = JAPNewView.this.addStatusMsg(JAPMessages.getString("setAnonModeSplashConnect"), 1, false);
                            try {
                                JAPNewView.this.m_connectionEstablishedSync.wait();
                            }
                            catch (InterruptedException ex) {}
                            JAPNewView.this.removeStatusMsg(addStatusMsg);
                            JAPNewView.this.m_bShowConnecting = false;
                        }
                        JAPNewView.this.updateValues(false);
                    }
                    else {
                        if (!JAPNewView.this.m_rbAnonOff.isSelected()) {
                            JAPNewView.this.m_rbAnonOff.setSelected(true);
                        }
                        b = true;
                    }
                    JAPNewView.this.m_connectionEstablishedSync.notifyAll();
                }
                if (b) {
                    synchronized (JAPNewView.this.SYNC_DISCONNECTED_ERROR) {
                        if (JAPNewView.this.m_bDisconnectedErrorShown) {
                            return;
                        }
                        JAPNewView.this.m_bDisconnectedErrorShown = true;
                    }
                    JAPDialog.showErrorDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAPNewView.MSG_ERROR_DISCONNECTED), LogType.NET, new JAPDialog.LinkedInformationAdapter() {
                        public boolean isOnTop() {
                            return true;
                        }
                    });
                    synchronized (JAPNewView.this.SYNC_DISCONNECTED_ERROR) {
                        JAPNewView.this.m_bDisconnectedErrorShown = false;
                    }
                }
            }
        }, "Wait for connecting");
        thread.setDaemon(true);
        thread.start();
    }
    
    private void showHelpWindow() {
        this.m_comboAnonServices.closeCascadePopupMenu();
        final JAPHelp instance = JAPHelp.getInstance();
        instance.setContext(JAPHelpContext.createHelpContext("index", this));
        instance.loadCurrentContext();
    }
    
    public void setVisible(final boolean visible) {
        int n = 1;
        if (visible && !this.isVisible()) {
            n = (JAPDll.showWindowFromTaskbar() ? 0 : 1);
        }
        if (n != 0) {
            super.setVisible(visible);
        }
    }
    
    public void saveWindowPositions() {
        JAPModel.getInstance().setMainWindowLocation(this.getLocation());
        synchronized (this.SYNC_ICONIFIED_VIEW) {
            if (this.getViewIconified() != null && this.m_miniMovedAdapter != null) {
                JAPModel.getInstance().setIconifiedWindowLocation(this.getViewIconified().getLocation());
            }
        }
        if (this.m_dlgConfig != null) {
            JAPModel.getInstance().setConfigWindowLocation(this.m_dlgConfig.getLocation());
        }
        if (JAPHelp.getHelpDialog() != null) {
            JAPModel.getInstance().setHelpWindowLocation(JAPHelp.getHelpDialog().getLocation());
        }
        if (JAPHelp.getHelpDialog() != null) {
            JAPModel.getInstance().setHelpWindowSize(JAPHelp.getHelpDialog().getSize());
        }
    }
    
    public void showConfigDialog(final String s, final Object o) {
        this.m_comboAnonServices.closeCascadePopupMenu();
        if (this.m_bConfigActive) {
            return;
        }
        this.m_bConfigActive = true;
        synchronized (this.LOCK_CONFIG) {
            if (!this.m_bConfigActive) {
                return;
            }
            if (this.m_dlgConfig == null) {
                final Cursor cursor = this.getCursor();
                this.setCursor(Cursor.getPredefinedCursor(3));
                (this.m_dlgConfig = new JAPConf(this, this.m_bWithPayment)).addComponentListener(this.m_configMovedAdapter);
                this.setCursor(cursor);
            }
            this.m_dlgConfig.selectCard(s, o);
            new Thread(new Runnable() {
                public void run() {
                    JAPNewView.this.m_dlgConfig.setVisible(true);
                }
            }).start();
            this.m_bConfigActive = false;
        }
    }
    
    public Component getCurrentView() {
        if (this.m_dlgConfig != null && this.m_dlgConfig.isVisible()) {
            return this.m_dlgConfig.getContentPane();
        }
        return this.getContentPane();
    }
    
    private void setOptimalSize() {
        try {
            if (!JAPModel.isSmallDisplay()) {
                this.pack();
                this.setResizable(true);
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.GUI, "Hm.. Error by Pack - Has To be fixed!!");
        }
    }
    
    public void doClickOnCascadeChooser() {
        this.m_comboAnonServices.showPopup();
    }
    
    public void onUpdateValues() {
        synchronized (this.SYNC_ICONIFIED_VIEW) {
            if (this.m_ViewIconified != null) {
                this.m_ViewIconified.updateValues(false);
            }
        }
        boolean b = JAPVersionInfo.getRecommendedUpdate("00.12.005", true) != null;
        final JavaVersionDBEntry newJavaVersion;
        if (!JAPController.getInstance().hasPortableJava() && (newJavaVersion = JavaVersionDBEntry.getNewJavaVersion()) != null && (newJavaVersion.isUpdateForced() || JAPModel.getInstance().isReminderForJavaUpdateActivated())) {
            b = true;
        }
        synchronized (this.SYNC_STATUS_UPDATE_AVAILABLE) {
            if (b) {
                if (this.m_updateAvailableID < 0) {
                    this.m_updateAvailableID = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_UPDATE), 1, false, this.m_listenerUpdate);
                }
            }
            else if (this.m_updateAvailableID >= 0) {
                this.m_StatusPanel.removeStatusMsg(this.m_updateAvailableID);
                this.m_updateAvailableID = -1;
            }
        }
        Label_0298: {
            if (!JAPController.getInstance().isShuttingDown() && (JAPModel.isInfoServiceDisabled() || (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 1 && !JAPController.getInstance().isAnonConnected()))) {
                synchronized (this.SYNC_STATUS_ENABLE_IS) {
                    if (this.m_enableInfoServiceID < 0) {
                        this.m_enableInfoServiceID = this.m_StatusPanel.addStatusMsg(JAPMessages.getString(JAPNewView.MSG_IS_DEACTIVATED), 2, false, this.m_listenerEnableIS);
                    }
                    break Label_0298;
                }
            }
            synchronized (this.SYNC_STATUS_ENABLE_IS) {
                if (this.m_enableInfoServiceID >= 0) {
                    this.m_StatusPanel.removeStatusMsg(this.m_enableInfoServiceID);
                    this.m_enableInfoServiceID = -1;
                }
            }
        }
        final MixCascade currentMixCascade = super.m_Controller.getCurrentMixCascade();
        final Hashtable entryHash = Database.getInstance((JAPNewView.class$anon$infoservice$MixCascade == null) ? (JAPNewView.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPNewView.class$anon$infoservice$MixCascade).getEntryHash();
        if (!entryHash.containsKey(currentMixCascade)) {
            entryHash.put(currentMixCascade.getId(), currentMixCascade);
        }
        this.m_bIgnoreAnonComboEvents = true;
        if (currentMixCascade == null) {
            this.m_comboAnonServices.setNoDataAvailable();
        }
        else if (this.m_bTrustChanged || !equals(currentMixCascade, this.m_comboAnonServices.getMixCascade())) {
            this.m_bTrustChanged = false;
            final boolean popupVisible = this.m_comboAnonServices.isPopupVisible();
            this.m_comboAnonServices.setMixCascade(currentMixCascade);
            if (popupVisible) {
                this.m_comboAnonServices.showStaticPopup();
            }
        }
        this.m_comboAnonServices.setToolTipText(TrustModel.getCurrentTrustModel().getName());
        if (this.m_comboAnonServices.getSelectedItem() == null) {
            this.m_comboAnonServices.setSelectedItem(currentMixCascade);
        }
        this.m_comboAnonServices.validate();
        this.m_bIgnoreAnonComboEvents = false;
        LogHolder.log(7, LogType.GUI, "Start updateValues");
        try {
            this.m_rbAnonOn.setSelected(super.m_Controller.getAnonMode());
            this.m_rbAnonOff.setSelected(!super.m_Controller.getAnonMode());
            this.m_cbAnonymityOn.setSelected(super.m_Controller.getAnonMode());
            final StatusInfo currentStatus = currentMixCascade.getCurrentStatus();
            if (!GUIUtils.isLoadingImagesStopped()) {
                this.m_labelAnonMeter.setIcon(this.getMeterImage(currentMixCascade, currentStatus));
            }
            Color filledBarColor = Color.blue;
            if (currentStatus.getAnonLevel() > 3) {
                filledBarColor = Color.green;
            }
            this.m_progressAnonLevel.setFilledBarColor(filledBarColor);
            if (currentStatus.getAnonLevel() < 0) {
                this.m_progressAnonLevel.setValue(0);
            }
            else {
                this.m_progressAnonLevel.setValue(currentStatus.getAnonLevel());
            }
            Color filledBarColor2 = Color.blue;
            if (currentMixCascade.getDistribution() > 3) {
                filledBarColor2 = Color.green;
            }
            this.m_progressDistribution.setFilledBarColor(filledBarColor2);
            this.m_progressDistribution.setValue(currentMixCascade.getDistribution());
            String systrayTooltip = "JonDo" + "\n" + GUIUtils.trim(currentMixCascade.getName(), 25);
            if (currentStatus.getNrOfActiveUsers() > -1) {
                this.m_lblUsers.setText(Integer.toString(currentStatus.getNrOfActiveUsers()) + ((currentMixCascade.getMaxUsers() > 0) ? (" / " + currentMixCascade.getMaxUsers()) : ""));
            }
            else {
                this.m_lblUsers.setText("");
            }
            if (super.m_Controller.isAnonConnected() && currentStatus.getNrOfActiveUsers() > -1) {
                systrayTooltip = systrayTooltip + "\n" + JAPMessages.getString(SystrayPopupMenu.MSG_ANONYMITY_ASCII) + ": " + currentMixCascade.getDistribution() + "," + currentStatus.getAnonLevel() + " / 6,6";
                if (!this.isChangingTitle()) {
                    if (this.m_bIsIconified) {
                        this.setTitle(JAPModel.getInstance().getProgramName() + " (" + currentMixCascade.getDistribution() + "," + currentStatus.getAnonLevel() + " / 6,6" + ")");
                    }
                    else {
                        this.setTitle(super.m_Title);
                    }
                }
            }
            this.m_lawListener.setCascadeInfo(currentMixCascade);
            final int numberOfOperatorsShown = currentMixCascade.getNumberOfOperatorsShown();
            for (int n = 0; n < numberOfOperatorsShown && n < this.m_labelOperatorFlags.length; ++n) {
                final MixInfo mixInfo = currentMixCascade.getMixInfo(n);
                Color color = this.m_panelAnonService.getBackground();
                if (mixInfo != null && mixInfo.getCertPath() != null && mixInfo.getCertPath().getIssuer() != null) {
                    final MultiCertPath certPath = mixInfo.getCertPath();
                    final String countryCode = certPath.getIssuer().getCountryCode();
                    String toolTipText = new CountryMapper(countryCode, JAPMessages.getLocale()).toString();
                    this.m_labelOperatorFlags[n].setIcon(GUIUtils.loadImageIcon("flags/" + countryCode + ".png"));
                    this.m_adapterOperator[n].setMixInfo(currentMixCascade, n);
                    if (certPath.isVerified()) {
                        if (!certPath.isValid(new Date())) {
                            color = Color.yellow;
                            toolTipText = toolTipText + ", " + JAPMessages.getString(MixDetailsDialog.MSG_INVALID);
                        }
                        else if (certPath.countVerifiedAndValidPaths() > 2) {
                            color = Color.green;
                            toolTipText = toolTipText + ", " + JAPMessages.getString(MixDetailsDialog.MSG_INDEPENDENT_CERTIFICATIONS, "" + certPath.countVerifiedAndValidPaths());
                        }
                        else if (certPath.countVerifiedAndValidPaths() > 1) {
                            color = new Color(100, 215, 255);
                            toolTipText = toolTipText + ", " + JAPMessages.getString(MixDetailsDialog.MSG_INDEPENDENT_CERTIFICATIONS, "" + certPath.countVerifiedAndValidPaths());
                        }
                    }
                    else {
                        color = Color.red;
                        toolTipText = toolTipText + ", " + JAPMessages.getString(MixDetailsDialog.MSG_NOT_VERIFIED);
                    }
                    this.m_labelOperatorFlags[n].setToolTipText(toolTipText);
                }
                else {
                    this.m_labelOperatorFlags[n].setIcon(null);
                }
                synchronized (this.m_labelOperatorFlags[n]) {
                    this.m_labelOperatorFlags[n].setBorder(BorderFactory.createLineBorder(color, 2));
                }
            }
            for (int i = numberOfOperatorsShown; i < this.m_labelOperatorFlags.length; ++i) {
                this.m_labelOperatorFlags[i].setIcon(null);
                this.m_labelOperatorFlags[i].setBorder(BorderFactory.createLineBorder(this.m_panelAnonService.getBackground(), 2));
            }
            for (int j = 0; j < this.m_lawFlags.length; ++j) {
                if (j == numberOfOperatorsShown - 1 && currentMixCascade.getDataRetentionInformation() != null) {
                    this.m_lawFlags[j].setVisible(true);
                }
                else {
                    this.m_lawFlags[j].setVisible(false);
                }
            }
            final PerformanceEntry lowestCommonBoundEntry = PerformanceInfo.getLowestCommonBoundEntry(currentMixCascade.getId());
            if (lowestCommonBoundEntry != null) {
                boolean b2;
                try {
                    TrustModel.getCurrentTrustModel().getAttribute((JAPNewView.class$anon$client$TrustModel$SpeedAttribute == null) ? (JAPNewView.class$anon$client$TrustModel$SpeedAttribute = class$("anon.client.TrustModel$SpeedAttribute")) : JAPNewView.class$anon$client$TrustModel$SpeedAttribute).checkTrust(currentMixCascade);
                    b2 = true;
                }
                catch (TrustException ex) {
                    b2 = false;
                }
                final int bound = lowestCommonBoundEntry.getBound(0).getBound();
                int bestBound = lowestCommonBoundEntry.getBestBound(0);
                if (bestBound < bound) {
                    bestBound = bound;
                }
                if (bound < 0 || bound == Integer.MAX_VALUE) {
                    this.m_labelSpeed.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                }
                else if (bound == 0) {
                    this.m_labelSpeed.setText("< " + Util.formatKbitPerSecValueWithUnit(PerformanceEntry.BOUNDARIES[0][1], 0));
                }
                else if (PerformanceEntry.BOUNDARIES[0][PerformanceEntry.BOUNDARIES[0].length - 1] == bestBound) {
                    if (System.getProperty("java.version").compareTo("1.4") >= 0) {
                        this.m_labelSpeed.setText("\u2265 " + Util.formatKbitPerSecValueWithUnit(bound, 0));
                    }
                    else {
                        this.m_labelSpeed.setText("> " + Util.formatKbitPerSecValueWithUnit(bound, 0));
                    }
                }
                else if (bestBound == bound || bestBound == Integer.MAX_VALUE) {
                    this.m_labelSpeed.setText(Util.formatKbitPerSecValueWithUnit(bound, 0));
                }
                else {
                    this.m_labelSpeed.setText(Util.formatKbitPerSecValueWithoutUnit(bound, 0) + "-" + Util.formatKbitPerSecValueWithUnit(bestBound, 0));
                }
                if (b2) {
                    this.m_labelSpeed.setForeground(this.m_lblUsers.getForeground());
                }
                else {
                    this.m_labelSpeed.setForeground(Color.red);
                }
                boolean b3;
                try {
                    TrustModel.getCurrentTrustModel().getAttribute((JAPNewView.class$anon$client$TrustModel$DelayAttribute == null) ? (JAPNewView.class$anon$client$TrustModel$DelayAttribute = class$("anon.client.TrustModel$DelayAttribute")) : JAPNewView.class$anon$client$TrustModel$DelayAttribute).checkTrust(currentMixCascade);
                    b3 = true;
                }
                catch (TrustException ex2) {
                    b3 = false;
                }
                final int bound2 = lowestCommonBoundEntry.getBound(1).getBound();
                int bestBound2 = lowestCommonBoundEntry.getBestBound(1);
                if (bestBound2 > bound2) {
                    bestBound2 = bound2;
                }
                if (bound2 <= 0) {
                    this.m_labelDelay.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                }
                else if (bound2 == Integer.MAX_VALUE) {
                    this.m_labelDelay.setText("> " + PerformanceEntry.BOUNDARIES[1][PerformanceEntry.BOUNDARIES[1].length - 2] + " ms");
                }
                else if (PerformanceEntry.BOUNDARIES[1][0] == bestBound2) {
                    if (System.getProperty("java.version").compareTo("1.4") >= 0) {
                        this.m_labelDelay.setText("\u2264 " + bound2 + " ms");
                    }
                    else {
                        this.m_labelDelay.setText("< " + bound2 + " ms");
                    }
                }
                else if (bestBound2 == bound2 || bestBound2 == 0) {
                    this.m_labelDelay.setText(bound2 + " ms");
                }
                else {
                    this.m_labelDelay.setText(bound2 + "-" + bestBound2 + " ms");
                }
                if (b3) {
                    this.m_labelDelay.setForeground(this.m_lblUsers.getForeground());
                }
                else {
                    this.m_labelDelay.setForeground(Color.red);
                }
            }
            else {
                this.m_labelSpeed.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                this.m_labelDelay.setText(JAPMessages.getString(JAPNewView.MSG_UNKNOWN_PERFORMANCE));
                this.m_labelSpeed.setForeground(this.m_lblUsers.getForeground());
                this.m_labelDelay.setForeground(this.m_lblUsers.getForeground());
            }
            JAPDll.setSystrayTooltip(systrayTooltip);
            LogHolder.log(7, LogType.GUI, "Finished updateValues");
            final boolean b4 = JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2;
            this.m_cbForwarding.setSelected(b4);
            this.m_cbForwardingSmall.setSelected(b4);
            Icon loadImageIcon = null;
            String s = null;
            synchronized (this.SYNC_FORWARD_MSG) {
                if (b4) {
                    final int currentState = JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver().getCurrentState();
                    final int currentErrorCode = JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver().getCurrentErrorCode();
                    if (currentState != this.m_msgForwardServerStatus) {
                        this.removeStatusMsg(this.m_msgForwardServer);
                        this.m_msgForwardServerStatus = 3;
                        if (this.m_mouseForwardError != null) {
                            this.m_labelForwardingErrorSmall.removeMouseListener(this.m_mouseForwardError);
                            this.m_labelForwardingError.removeMouseListener(this.m_mouseForwardError);
                            this.m_labelForwardingErrorSmall.setCursor(Cursor.getDefaultCursor());
                            this.m_labelForwardingError.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                    if (currentState == 2) {
                        final String string = "<font color='red'>" + JAPMessages.getString(JAPController.MSG_FORWARDER_REGISTRATION_ERROR_HEADER) + "</font><br><br>";
                        final String string2 = "<br><br>" + JAPMessages.getString(JAPController.MSG_FORWARDER_REGISTRATION_ERROR_FOOTER);
                        if (!GUIUtils.isLoadingImagesStopped()) {
                            loadImageIcon = GUIUtils.loadImageIcon("warning.gif", true);
                        }
                        if (currentErrorCode == 1) {
                            s = string + JAPMessages.getString("settingsRoutingServerRegistrationEmptyListError") + string2;
                        }
                        else if (currentErrorCode == 2) {
                            s = string + JAPMessages.getString("settingsRoutingServerRegistrationInfoservicesError") + string2;
                        }
                        else if (currentErrorCode == 3) {
                            s = string + JAPMessages.getString("settingsRoutingServerRegistrationVerificationError", "<b>" + JAPModel.getInstance().getRoutingSettings().getServerPort() + "</b>") + string2;
                        }
                        else if (currentErrorCode == 4) {
                            s = string + JAPMessages.getString("settingsRoutingServerRegistrationUnknownError") + string2;
                        }
                        if (s != null) {
                            s = JAPMessages.getString(s);
                            if (this.m_msgForwardServerStatus == 3) {
                                final String s2 = s;
                                this.m_msgForwardServer = this.addStatusMsg(JAPMessages.getString(JAPController.MSG_FORWARDER_REG_ERROR_SHORT), 2, false, new ActionListener() {
                                    public void actionPerformed(final ActionEvent actionEvent) {
                                        JAPDialog.showErrorDialog(JAPNewView.this.getCurrentView(), s2, LogType.MISC, new JAPDialog.LinkedHelpContext("forwarding_server"));
                                    }
                                });
                                this.m_mouseForwardError = new MouseAdapter() {
                                    public void mouseClicked(final MouseEvent mouseEvent) {
                                        JAPDialog.showErrorDialog(JAPNewView.this.getCurrentView(), s2, LogType.MISC, new JAPDialog.LinkedHelpContext("forwarding_server"));
                                    }
                                };
                                this.m_labelForwardingErrorSmall.addMouseListener(this.m_mouseForwardError);
                                this.m_labelForwardingError.addMouseListener(this.m_mouseForwardError);
                                this.m_labelForwardingErrorSmall.setCursor(Cursor.getPredefinedCursor(12));
                                this.m_labelForwardingError.setCursor(Cursor.getPredefinedCursor(12));
                            }
                        }
                    }
                }
                else {
                    this.removeStatusMsg(this.m_msgForwardServer);
                    if (this.m_mouseForwardError != null) {
                        this.m_labelForwardingErrorSmall.removeMouseListener(this.m_mouseForwardError);
                        this.m_labelForwardingError.removeMouseListener(this.m_mouseForwardError);
                        this.m_labelForwardingErrorSmall.setCursor(Cursor.getDefaultCursor());
                        this.m_labelForwardingError.setCursor(Cursor.getDefaultCursor());
                    }
                    this.m_msgForwardServerStatus = 3;
                }
                if (!GUIUtils.isLoadingImagesStopped()) {
                    this.m_labelForwardingError.setIcon(loadImageIcon);
                    this.m_labelForwardingErrorSmall.setIcon(loadImageIcon);
                }
                this.m_labelForwardingError.setToolTipText("<html>" + s + "</html>");
                this.m_labelForwardingErrorSmall.setToolTipText("<html>" + s + "</html>");
                this.m_cbForwarding.setEnabled(!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder());
                this.m_cbForwardingSmall.setEnabled(!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder());
                this.m_comboAnonServices.setEnabled(!JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder());
            }
            this.validate();
        }
        catch (Throwable t) {
            LogHolder.log(0, LogType.GUI, t);
        }
    }
    
    public JAPViewIconified getViewIconified() {
        return this.m_ViewIconified;
    }
    
    public void registerViewIconified(final JAPViewIconified viewIconified) {
        synchronized (this.SYNC_ICONIFIED_VIEW) {
            if (this.m_ViewIconified != null) {
                this.m_ViewIconified.removeComponentListener(this.m_miniMovedAdapter);
            }
            this.m_ViewIconified = viewIconified;
            this.m_miniMovedAdapter = new ComponentMovedAdapter();
            this.m_ViewIconified.addComponentListener(this.m_miniMovedAdapter);
        }
    }
    
    public void channelsChanged(final int n) {
        this.m_channelsChangedJobs.addJob(new JobQueue.Job() {
            public void runJob() {
                synchronized (JAPNewView.this.m_progressOwnTrafficActivity) {
                    JAPNewView.this.m_currentChannels = n;
                    final int min = Math.min(n, JAPNewView.this.m_progressOwnTrafficActivity.getMaximum());
                    JAPNewView.this.m_progressOwnTrafficActivity.setValue(min);
                    JAPNewView.this.m_progressOwnTrafficActivitySmall.setValue(min);
                    JAPNewView.this.m_progressOwnTrafficActivity.notify();
                }
            }
        });
    }
    
    public void packetMixed(final long n) {
        this.m_packetMixedJobs.addJob(new JobQueue.Job() {
            private final /* synthetic */ JAPNewView this$0;
            
            public void runJob() {
                synchronized (JAPNewView.this.SYNC_ICONIFIED_VIEW) {
                    if (JAPNewView.this.m_ViewIconified != null) {
                        JAPNewView.this.m_ViewIconified.packetMixed(n);
                    }
                }
                final Runnable runnable = new Runnable() {
                    private final /* synthetic */ JAPNewView$42 this$1 = this$1;
                    
                    public void run() {
                        final String formatBytesValueOnlyUnit = Util.formatBytesValueOnlyUnit(n);
                        this.this$1.this$0.m_labelOwnTrafficUnit.setText(formatBytesValueOnlyUnit);
                        this.this$1.this$0.m_labelOwnTrafficUnit.revalidate();
                        this.this$1.this$0.m_labelOwnTrafficUnitSmall.setText(formatBytesValueOnlyUnit);
                        this.this$1.this$0.m_labelOwnTrafficUnitSmall.revalidate();
                        final String formatBytesValueWithoutUnit = Util.formatBytesValueWithoutUnit(n);
                        this.this$1.this$0.m_labelOwnTrafficBytes.setText(formatBytesValueWithoutUnit);
                        this.this$1.this$0.m_labelOwnTrafficBytes.revalidate();
                        this.this$1.this$0.m_labelOwnTrafficBytesSmall.setText(formatBytesValueWithoutUnit);
                        this.this$1.this$0.m_labelOwnTrafficBytesSmall.revalidate();
                    }
                };
                try {
                    SwingUtilities.invokeAndWait(runnable);
                }
                catch (InvocationTargetException ex) {}
                catch (InterruptedException ex2) {}
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex3) {}
            }
        });
    }
    
    public void transferedBytes(final long n, final int n2) {
        if (n2 == 1) {
            this.m_lTrafficWWW = n;
        }
        else if (n2 == 0) {
            this.m_lTrafficOther = n;
        }
        this.blink(n);
        this.m_transferedBytesJobs.addJob(new JobQueue.Job() {
            private final /* synthetic */ JAPNewView this$0;
            
            public void runJob() {
                final Runnable runnable = new Runnable() {
                    private final /* synthetic */ JAPNewView$44 this$1 = this$1;
                    
                    public void run() {
                        this.this$1.this$0.m_labelOwnTrafficUnitWWW.setText(Util.formatBytesValueOnlyUnit(this.this$1.this$0.m_lTrafficWWW));
                        this.this$1.this$0.m_labelOwnTrafficUnitWWW.revalidate();
                        this.this$1.this$0.m_labelOwnTrafficBytesWWW.setText(Util.formatBytesValueWithoutUnit(this.this$1.this$0.m_lTrafficWWW));
                        this.this$1.this$0.m_labelOwnTrafficBytesWWW.revalidate();
                        this.this$1.this$0.m_labelOwnTrafficUnitOther.setText(Util.formatBytesValueOnlyUnit(this.this$1.this$0.m_lTrafficOther));
                        this.this$1.this$0.m_labelOwnTrafficUnitOther.revalidate();
                        this.this$1.this$0.m_labelOwnTrafficBytesOther.setText(Util.formatBytesValueWithoutUnit(this.this$1.this$0.m_lTrafficOther));
                        this.this$1.this$0.m_labelOwnTrafficBytesOther.revalidate();
                        JAPDll.onTraffic();
                    }
                };
                try {
                    SwingUtilities.invokeAndWait(runnable);
                }
                catch (InvocationTargetException ex) {}
                catch (InterruptedException ex2) {}
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex3) {}
            }
        });
    }
    
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
    
    public int addStatusMsg(final String s, final int n, final boolean b) {
        return this.m_StatusPanel.addStatusMsg(s, n, b);
    }
    
    public int addStatusMsg(final String s, final int n, final boolean b, final ActionListener actionListener) {
        return this.m_StatusPanel.addStatusMsg(s, n, b, actionListener);
    }
    
    public void removeStatusMsg(final int n) {
        this.m_StatusPanel.removeStatusMsg(n);
    }
    
    private void showJavaUpdateDialog(final JavaVersionDBEntry javaVersionDBEntry) {
        this.m_comboAnonServices.closeCascadePopupMenu();
        JAPDialog.showMessageDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAPNewView.MSG_OLD_JAVA, new Object[] { JavaVersionDBEntry.CURRENT_JAVA_VERSION, JavaVersionDBEntry.CURRENT_JAVA_VENDOR, javaVersionDBEntry.getJREVersion(), javaVersionDBEntry.getVendorLongName(), javaVersionDBEntry.getVendor() }) + ((javaVersionDBEntry.getJREVersionName() == null) ? "" : ("<br>" + javaVersionDBEntry.getJREVersionName())), JAPMessages.getString(JAPNewView.MSG_TITLE_OLD_JAVA), AbstractOS.getInstance().createURLLink(javaVersionDBEntry.getDownloadURL(), null, "updateJava"));
    }
    
    private synchronized void fetchMixCascadesAsync(final boolean b) {
        this.m_bttnReload.setEnabled(false);
        final Thread thread = new Thread(new Runnable() {
            private final /* synthetic */ JAPNewView this$0;
            
            public void run() {
                JAPNewView.this.m_Controller.updateInfoServices(!b);
                JAPNewView.this.m_Controller.updatePaymentInstances(!b);
                JAPNewView.this.m_Controller.updatePerformanceInfo(!b);
                JAPNewView.this.m_Controller.fetchMixCascades(b, !b);
                SwingUtilities.invokeLater(new Runnable() {
                    private final /* synthetic */ JAPNewView$46 this$1 = this$1;
                    
                    public void run() {
                        this.this$1.this$0.m_bttnReload.setEnabled(true);
                    }
                });
            }
        }, "DoFetchMixCascades");
        thread.setDaemon(true);
        thread.start();
    }
    
    private void updateFonts() {
        final Font font = this.DEFAULT_LABEL.getFont();
        this.m_labelVersion.setFont(new Font(font.getName(), font.getStyle(), (int)(font.getSize() * 0.8)));
        this.m_bttnIconify.setIcon(GUIUtils.loadImageIcon(JAPNewView.IMG_ICONIFY, true));
        this.m_btnAbout.setIcon(GUIUtils.loadImageIcon(JAPNewView.IMG_ABOUT, true));
    }
    
    private static boolean equals(final MixCascade mixCascade, final MixCascade mixCascade2) {
        return (mixCascade != null || mixCascade2 == null) && (mixCascade == null || mixCascade2 != null) && (mixCascade == null || (mixCascade.equals(mixCascade2) && mixCascade.isPayment() == mixCascade2.isPayment() && mixCascade.getName().equals(mixCascade2.getName())));
    }
    
    public void messageReceived(final PayMessage payMessage) {
        final URL messageLink = payMessage.getMessageLink();
        final String messageText = payMessage.getMessageText();
        final String shortMessage = payMessage.getShortMessage();
        final boolean b = messageLink != null;
        final boolean b2 = messageText != null && !messageText.equals("");
        ActionListener actionListener;
        if (!b && !b2) {
            actionListener = null;
        }
        else if (b && !b2) {
            actionListener = new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    AbstractOS.getInstance().openURL(messageLink);
                }
            };
        }
        else {
            final String s = shortMessage;
            final String s2 = messageText;
            if (b) {
                actionListener = new ActionListener() {
                    private final /* synthetic */ JAPDialog.Options val$dialogOptions = new JAPDialog.Options(2) {
                        public String getYesOKText() {
                            return JAPMessages.getString("bttnOk");
                        }
                        
                        public String getCancelText() {
                            return JAPMessages.getString("bttnCancel");
                        }
                    };
                    private final /* synthetic */ JAPDialog.LinkedInformationAdapter val$infoLink = new JAPDialog.LinkedInformationAdapter(messageLink) {
                        private final /* synthetic */ URL val$messageLink = val$messageLink;
                        
                        public void clicked(final boolean b) {
                            AbstractOS.getInstance().openURL(this.val$messageLink);
                        }
                        
                        public String getMessage() {
                            return Util.replaceAll(Util.replaceAll(this.val$messageLink.toString(), "mailto:", "Email:"), "http://", "Link:");
                        }
                        
                        public int getType() {
                            return 2;
                        }
                    };
                    
                    public void actionPerformed(final ActionEvent actionEvent) {
                        if (JAPDialog.showConfirmDialog(JAPNewView.this, s2, s, this.val$dialogOptions, 1, this.val$infoLink) == 0) {
                            AbstractOS.getInstance().openURL(messageLink);
                        }
                    }
                };
            }
            else {
                actionListener = new ActionListener() {
                    public void actionPerformed(final ActionEvent actionEvent) {
                        JAPDialog.showMessageDialog(JAPNewView.this, s2, s);
                    }
                };
            }
        }
        this.m_messagesShown.put(shortMessage, new Integer(this.m_StatusPanel.addStatusMsg(shortMessage, 1, false, actionListener)));
    }
    
    public void messageRemoved(final PayMessage payMessage) {
        final Integer n = this.m_messagesShown.get(payMessage.getShortMessage());
        if (n == null) {
            LogHolder.log(7, LogType.PAY, "Tried to remove a message, but failed, since no id exists, message is: " + payMessage);
        }
        else {
            this.m_StatusPanel.removeStatusMsg(n);
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
        MSG_UPDATE = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_update";
        MSG_NO_REAL_PAYMENT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_noRealPayment";
        MSG_UNKNOWN_PERFORMANCE = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_unknownPerformance";
        MSG_USERS = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_users";
        MSG_SERVICE_NAME = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_ngAnonymisierungsdienst";
        MSG_ANONYMETER_TOOL_TIP = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_anonymeterToolTip";
        MSG_ERROR_DISCONNECTED = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_errorDisconnected";
        MSG_ERROR_PROXY = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_errorProxy";
        MSG_TITLE_OLD_JAVA = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_titleOldJava";
        MSG_OLD_JAVA = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_oldJava";
        MSG_OLD_JAVA_HINT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_oldJavaHint";
        MSG_LBL_NEW_SERVICES_FOUND = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_newServicesFound";
        MSG_TOOLTIP_NEW_SERVICES_FOUND = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_tooltipNewServicesFound";
        MSG_NEW_SERVICES_FOUND_EXPLAIN = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_newServicesFoundExplanation";
        MSG_NO_COSTS = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_noCosts";
        MSG_WITH_COSTS = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_withCosts";
        MSG_BTN_ASSISTANT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_btnAssistant";
        MSG_MN_ASSISTANT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_mnAssistant";
        MSG_MN_DETAILS = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_mnDetails";
        MSG_IS_DISABLED_EXPLAIN = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_isDisabledExplain";
        MSG_IS_DEACTIVATED = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_isDisabled";
        MSG_IS_TOOLTIP = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_isDisabledTooltip";
        MSG_IS_TRUST_PARANOID = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_trustParanoid";
        MSG_IS_TRUST_SUSPICIOUS = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_trustSuspicious";
        MSG_IS_TRUST_HIGH = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_trustHigh";
        MSG_IS_TRUST_ALL = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_trustAll";
        MSG_IS_EDIT_TRUST = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_editTrust";
        MSG_TRUST_FILTER = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_trustFilter";
        MSG_CONNECTED = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_connected";
        MSG_DELETE_MESSAGE = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_deleteMessage";
        MSG_HIDE_MESSAGE_SHORT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_hideMessageShort";
        MSG_DELETE_MESSAGE_EXPLAIN = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_deleteMessageExplain";
        MSG_DELETE_MESSAGE_SHORT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_deleteMessageShort";
        MSG_VIEW_MESSAGE = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_viewMessage";
        MSG_ANTI_CENSORSHIP = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_antiCensorship";
        MSG_DATA_RETENTION_EXPLAIN = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_dataRetentionExplain";
        MSG_OBSERVABLE_EXPLAIN = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_observableExplain";
        MSG_OBSERVABLE_TITLE = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_observableTitle";
        MSG_DISTRIBUTION = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_lblDistribution";
        MSG_USER_ACTIVITY = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_lblUserActivity";
        MSG_JAVA_FORCED_TITLE = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_javaForcedTitle";
        MSG_JAVA_FORCED_EXPLAIN = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_javaForcedExplain";
        MSG_JAVA_FORCED_OS = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_javaForcedOS";
        MSG_JAVA_FORCED_QUESTION = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_javaForcedQuestion";
        MSG_LBL_ENCRYPTED_DATA = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_lblEncryptedData";
        MSG_LBL_HTTP_DATA = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_lblHTTPData";
        MSG_LBL_OTHER_DATA = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_lblOtherData";
        IMG_ICONIFY = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_iconify.gif";
        IMG_ABOUT = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_about.gif";
        MSG_OPEN_FIREFOX = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_openFirefox";
        HLP_ANONYMETER = ((JAPNewView.class$jap$JAPNewView == null) ? (JAPNewView.class$jap$JAPNewView = class$("jap.JAPNewView")) : JAPNewView.class$jap$JAPNewView).getName() + "_anonymometer";
    }
    
    private final class ComponentMovedAdapter extends ComponentAdapter
    {
        private boolean m_bMoved;
        
        private ComponentMovedAdapter() {
            this.m_bMoved = false;
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
            this.m_bMoved = true;
        }
        
        public boolean hasMoved() {
            return this.m_bMoved;
        }
    }
    
    private final class LawListener extends MouseAdapter
    {
        private MixCascade m_cascade;
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            DataRetentionDialog.show(JAPNewView.this, this.m_cascade);
        }
        
        public void setCascadeInfo(final MixCascade cascade) {
            this.m_cascade = cascade;
        }
    }
    
    private final class MixMouseAdapter extends MouseAdapter
    {
        private MixCascade m_mixInfo;
        private int m_mixPosition;
        private JLabel m_registeredLabel;
        private LineBorder m_borderOriginal;
        
        public MixMouseAdapter(final JLabel registeredLabel) {
            this.m_registeredLabel = registeredLabel;
        }
        
        public synchronized void mouseClicked(final MouseEvent mouseEvent) {
            final MixDetailsDialog mixDetailsDialog = new MixDetailsDialog(JAPNewView.this, this.m_mixInfo, this.m_mixPosition);
            mixDetailsDialog.pack();
            mixDetailsDialog.setVisible(true);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            synchronized (this.m_registeredLabel) {
                if (this.m_borderOriginal == null) {
                    final Border border = this.m_registeredLabel.getBorder();
                    if (border != null && border instanceof LineBorder) {
                        this.m_borderOriginal = (LineBorder)border;
                        this.m_registeredLabel.setBorder(new LineBorder(this.m_borderOriginal.getLineColor().darker(), this.m_borderOriginal.getThickness()));
                    }
                }
                else {
                    this.m_borderOriginal = null;
                }
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            synchronized (this.m_registeredLabel) {
                if (this.m_borderOriginal != null) {
                    this.m_registeredLabel.setBorder(this.m_borderOriginal);
                    this.m_borderOriginal = null;
                }
            }
        }
        
        public synchronized void setMixInfo(final MixCascade mixInfo, final int mixPosition) {
            this.m_mixInfo = mixInfo;
            this.m_mixPosition = mixPosition;
        }
    }
}
