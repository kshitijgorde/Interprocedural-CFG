// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.IBrowserIdentification;
import anon.util.IReturnRunnable;
import anon.proxy.HTTPConnectionEvent;
import anon.proxy.HttpConnectionListenerAdapter;
import anon.client.AbstractAutoSwitchedMixCascadeContainer;
import anon.AnonServiceEventAdapter;
import anon.infoservice.AbstractMixCascadeContainer;
import anon.mixminion.MixminionServiceDescription;
import anon.tor.TorAnonServerDescription;
import anon.terms.TermsAndConditionsResponseHandler;
import anon.crypto.JAPCertificate;
import anon.util.ResourceLoader;
import anon.util.Util;
import anon.proxy.JonDoFoxHeader;
import anon.proxy.AbstractHTTPConnectionListener;
import anon.infoservice.DatabaseMessage;
import anon.infoservice.JAPMinVersion;
import java.security.SignatureException;
import anon.crypto.ExpiredSignatureException;
import anon.infoservice.InfoServiceHolderMessage;
import jap.forward.JAPRoutingMessage;
import update.JAPUpdateWizard;
import java.net.MalformedURLException;
import java.net.URL;
import update.JAPWelcomeWizardPage;
import anon.infoservice.JAPVersionInfo;
import javax.swing.SwingUtilities;
import gui.dialog.DialogContentPane;
import java.awt.Window;
import anon.pay.PayAccount;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import anon.mixminion.mmrdescription.MMRList;
import forward.server.ForwardServerManager;
import anon.transport.address.IAddress;
import jap.forward.JAPRoutingEstablishForwardedConnectionDialog;
import anon.infoservice.ServiceOperator;
import javax.swing.Icon;
import anon.infoservice.ImmutableProxyInterface;
import java.net.InetAddress;
import anon.AnonServerDescription;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import platform.MacOS;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import anon.util.RecursiveFileTool;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import anon.terms.template.TermsAndConditionsTemplate;
import anon.terms.TermsAndConditions;
import anon.util.Base64;
import anon.pay.PayAccountsFile;
import java.util.Enumeration;
import gui.dialog.PasswordContentPane;
import java.awt.Frame;
import anon.util.IMiscPasswordReader;
import anon.pay.PaymentInstanceDBEntry;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import anon.client.TrustModel;
import anon.infoservice.BlacklistedCascadeIDEntry;
import java.util.Hashtable;
import anon.infoservice.ProxyInterface;
import anon.client.AnonClient;
import java.awt.Component;
import org.w3c.dom.Element;
import gui.GUIUtils;
import java.util.Locale;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.io.File;
import platform.AbstractOS;
import java.io.FileNotFoundException;
import gui.help.JAPHelp;
import anon.infoservice.HTTPConnectionFactory;
import anon.crypto.SignatureVerifier;
import anon.infoservice.InfoServiceHolder;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.PreviouslyKnownCascadeIDEntry;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.CascadeIDEntry;
import anon.infoservice.ListenerInterface;
import anon.util.JAPMessages;
import gui.dialog.JAPDialog;
import proxy.DirectProxy.AllowProxyConnectionCallback;
import anon.pay.BIConnection;
import anon.infoservice.InfoServiceDBEntry;
import anon.infoservice.Database;
import anon.infoservice.IDistributable;
import anon.infoservice.IDistributor;
import java.io.IOException;
import anon.util.ClassUtil;
import anon.util.IPasswordReader;
import anon.infoservice.update.PerformanceInfoUpdater;
import anon.infoservice.update.MessageUpdater;
import anon.infoservice.update.JavaVersionUpdater;
import anon.infoservice.update.MinVersionUpdater;
import anon.infoservice.update.PaymentInstanceUpdater;
import anon.infoservice.update.InfoServiceUpdater;
import anon.infoservice.update.AccountUpdater;
import anon.util.Updater;
import anon.proxy.AnonProxy;
import proxy.DirectProxy;
import java.net.ServerSocket;
import anon.infoservice.MixCascade;
import anon.util.JobQueue;
import java.util.Vector;
import anon.terms.TermsAndConditionConfirmation;
import anon.AnonServiceEventListener;
import java.util.Observer;
import anon.proxy.IProxyListener;
import java.util.Observable;

public final class JAPController extends Observable implements IProxyListener, Observer, AnonServiceEventListener, TermsAndConditionConfirmation
{
    public static final String MSG_ERROR_SAVING_CONFIG;
    public static final String MSG_NO_WRITING;
    public static final String MSG_NO_WRITING_PORTABLE;
    private static final String MSG_DIALOG_ACCOUNT_PASSWORD;
    private static final String MSG_ACCOUNT_PASSWORD;
    private static final String MSG_ENCRYPTACCOUNT;
    private static final String MSG_ENCRYPTACCOUNTTITLE;
    private static final String MSG_ACCPASSWORDTITLE;
    private static final String MSG_ACCPASSWORD;
    private static final String MSG_ACCPASSWORDENTERTITLE;
    private static final String MSG_ACCPASSWORDENTER;
    private static final String MSG_LOSEACCOUNTDATA;
    private static final String MSG_REPEAT_ENTER_ACCOUNT_PASSWORD;
    private static final String MSG_DISABLE_GOODBYE;
    private static final String MSG_NEW_OPTIONAL_VERSION;
    private static final String MSG_CASCADE_NOT_TRUSTED;
    private static final String MSG_ALLOWUNPROTECTED;
    private static final String MSG_ALLOWUNPROTECTED_ALL;
    private static final String MSG_EXPLAIN_ALLOWUNPROTECTED_ALL;
    public static final String MSG_IS_NOT_ALLOWED;
    public static final String MSG_IS_NOT_ALLOWED_FOR_ANONYMOUS;
    public static final String MSG_ASK_SWITCH;
    public static final String MSG_ASK_RECONNECT;
    public static final String MSG_ASK_AUTO_CONNECT;
    public static final String MSG_FINISHING;
    public static final String MSG_SAVING_CONFIG;
    public static final String MSG_CLOSING_DIALOGS;
    public static final String MSG_FINISHING_IS_UPDATES;
    public static final String MSG_FINISHING_ANON;
    public static final String MSG_WAITING_IS;
    public static final String MSG_WAITING_ANON;
    public static final String MSG_STOPPING_PROXY;
    public static final String MSG_STOPPING_LISTENER;
    public static final String MSG_RESTARTING;
    public static final String MSG_FINISH_FORWARDING_SERVER;
    public static final String MSG_VERSION_RELEASE;
    public static final String MSG_VERSION_DEVELOPER;
    public static final String MSG_ASK_WHICH_VERSION;
    private static final String MSG_CASCADE_NOT_PARSABLE;
    public static final String MSG_PAYMENT_DAMAGED;
    public static final String MSG_ACCOUNT_NOT_SAVED;
    public static final String MSG_UPDATING_HELP;
    public static final String MSG_FORWARDER_REGISTRATION_ERROR_HEADER;
    public static final String MSG_FORWARDER_REGISTRATION_ERROR_FOOTER;
    public static final String MSG_FORWARDER_REG_ERROR_SHORT;
    public static final String MSG_READ_NEW_HELP;
    public static final String MSG_WARNING_IS_CERTS_EXPIRED;
    public static final String MSG_WARNING_IS_CERTS_INVALID;
    public static final String MSG_WARNING_INSUFFICIENT_BALANCE;
    public static final String MSG_WARNING_SHORT_BALANCE;
    public static final String MSG_WARNING_SHORT_BALANCE_CONTINUE;
    public static final String MSG_WARNING_BROWSER_NOT_OPTIMIZED;
    private static final String XML_ELEM_LOOK_AND_FEEL = "LookAndFeel";
    private static final String XML_ELEM_LOOK_AND_FEELS = "LookAndFeels";
    private static final String XML_ATTR_LOOK_AND_FEEL = "current";
    private static final String XML_ALLOW_NON_ANONYMOUS_CONNECTION = "AllowDirectConnection";
    private static final String XML_ALLOW_NON_ANONYMOUS_UPDATE = "AllowDirectUpdate";
    private static final String XML_ATTR_AUTO_CHOOSE_CASCADES = "AutoSwitchCascades";
    private static final String XML_ATTR_AUTO_CHOOSE_CASCADES_ON_STARTUP = "autoSwitchCascadesOnStartup";
    private static final String XML_ATTR_SHOW_CONFIG_ASSISTANT = "showConfigAssistant";
    private static final String XML_ATTR_LOGIN_TIMEOUT = "loginTimeout";
    private static final String XML_ATTR_INFOSERVICE_CONNECT_TIMEOUT = "isConnectionTimeout";
    private static final String XML_ATTR_ASK_SAVE_PAYMENT = "askIfNotSaved";
    private static final String XML_ATTR_SHOW_SPLASH_SCREEN = "ShowSplashScreen";
    private static final String XML_ATTR_PORTABLE_BROWSER_PATH = "portableBrowserPath";
    private static final String XML_ATTR_WARN_ON_INSECURE_BRWOSER = "warnInsecureBrowser";
    private static final String XML_ATTR_HELP_PATH = "helpPath";
    private final String CLASS_PATH;
    private final Object PROXY_SYNC;
    private String[] m_commandLineArgs;
    boolean m_firstPortableFFStart;
    private boolean m_bShutdown;
    private Vector m_programExitListeners;
    private boolean m_bShowConfigAssistant;
    private boolean m_bAssistantClicked;
    private boolean m_bAllowPaidServices;
    private boolean m_bShowHelpAdvise;
    private JobQueue m_anonJobQueue;
    private boolean m_bConnecting;
    private JobQueue queueFetchAccountInfo;
    private long m_lastBalanceUpdateMS;
    private long m_lastBalanceUpdateBytes;
    private boolean m_bConnectionUnused;
    private MixCascade m_currentMixCascade;
    private ServerSocket m_socketHTTPListener;
    private DirectProxy m_proxyDirect;
    private AnonProxy m_proxyAnon;
    private Updater.ObservableInfo m_observableInfo;
    private AccountUpdater m_AccountUpdater;
    private InfoServiceUpdater m_InfoServiceUpdater;
    private PaymentInstanceUpdater m_paymentInstanceUpdater;
    private MixCascadeUpdater m_MixCascadeUpdater;
    private MinVersionUpdater m_minVersionUpdater;
    private JavaVersionUpdater m_javaVersionUpdater;
    private MessageUpdater m_messageUpdater;
    private PerformanceInfoUpdater m_perfInfoUpdater;
    private Object LOCK_VERSION_UPDATE;
    private boolean m_bShowingVersionUpdate;
    private boolean m_bAskAutoConnect;
    private boolean isRunningHTTPListener;
    private boolean mbActCntMessageNotRemind;
    private boolean mbActCntMessageNeverRemind;
    private boolean mbDoNotAbuseReminder;
    private boolean m_bForwarderNotExplain;
    private boolean m_bExpiredISCertificatesShown;
    private final Object SYNC_EXPIRED_IS_CERTS;
    private boolean m_bAskSavePayment;
    private boolean m_bPresentationMode;
    private boolean m_bPortableJava;
    private boolean m_bPortable;
    private long m_nrOfBytesWWW;
    private long m_nrOfBytesOther;
    private IJAPMainView m_View;
    private boolean m_bMainView;
    private Object SYNC_VIEW;
    private static JAPController m_Controller;
    private static JAPModel m_Model;
    private static JAPFeedback m_feedback;
    private Vector observerVector;
    private Vector m_anonServiceListener;
    private IPasswordReader m_passwordReader;
    private Object m_finishSync;
    private ISplashResponse m_finishSplash;
    private IRestarter m_restarter;
    private DirectProxy.AllowProxyConnectionCallback m_proxyCallback;
    private WarnSmallBalanceOnDownloadListener m_smallBalanceWarningListener;
    private WarnNoJonDoFoxHttpListener m_warnNoJonDoFoxHttpListener;
    private int m_iStatusPanelMsgIdForwarderServerStatus;
    static /* synthetic */ Class class$jap$JAPController;
    static /* synthetic */ Class class$anon$infoservice$CascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$PreviouslyKnownCascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    static /* synthetic */ Class class$anon$infoservice$PerformanceInfo;
    static /* synthetic */ Class class$anon$infoservice$JAPMinVersion;
    static /* synthetic */ Class class$anon$infoservice$BlacklistedCascadeIDEntry;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    static /* synthetic */ Class class$anon$infoservice$DeletedMessageIDDBEntry;
    static /* synthetic */ Class class$anon$infoservice$ClickedMessageIDDBEntry;
    static /* synthetic */ Class class$anon$pay$PaymentInstanceDBEntry;
    static /* synthetic */ Class class$anon$terms$template$TermsAndConditionsTemplate;
    static /* synthetic */ Class class$java$net$InetAddress;
    static /* synthetic */ Class class$anon$infoservice$JAPVersionInfo;
    static /* synthetic */ Class class$anon$client$TrustModel$SpeedAttribute;
    static /* synthetic */ Class class$anon$client$TrustModel$DelayAttribute;
    
    private JAPController() {
        this.CLASS_PATH = ClassUtil.getClassPath().trim();
        this.PROXY_SYNC = new Object();
        this.m_commandLineArgs = new String[0];
        this.m_firstPortableFFStart = false;
        this.m_bShutdown = false;
        this.m_programExitListeners = new Vector();
        this.m_bShowConfigAssistant = false;
        this.m_bAssistantClicked = false;
        this.m_bAllowPaidServices = true;
        this.m_bShowHelpAdvise = false;
        this.m_bConnecting = false;
        this.m_lastBalanceUpdateMS = 0L;
        this.m_lastBalanceUpdateBytes = 0L;
        this.m_bConnectionUnused = true;
        this.m_currentMixCascade = null;
        this.m_socketHTTPListener = null;
        this.m_proxyDirect = null;
        this.m_proxyAnon = null;
        this.LOCK_VERSION_UPDATE = new Object();
        this.m_bShowingVersionUpdate = false;
        this.m_bAskAutoConnect = false;
        this.isRunningHTTPListener = false;
        this.mbActCntMessageNotRemind = false;
        this.mbActCntMessageNeverRemind = false;
        this.mbDoNotAbuseReminder = false;
        this.m_bForwarderNotExplain = false;
        this.m_bExpiredISCertificatesShown = false;
        this.SYNC_EXPIRED_IS_CERTS = new Object();
        this.m_bPresentationMode = false;
        this.m_bPortableJava = false;
        this.m_bPortable = false;
        this.m_nrOfBytesWWW = 0L;
        this.m_nrOfBytesOther = 0L;
        this.m_View = null;
        this.m_bMainView = true;
        this.SYNC_VIEW = new Object();
        this.observerVector = new Vector();
        this.m_finishSync = new Object();
        this.m_restarter = new IRestarter() {
            public boolean hideWarnings() {
                return false;
            }
            
            public boolean isConfigFileSaved() {
                return true;
            }
            
            public void exec(final String[] array) throws IOException {
                if (array != null) {
                    Runtime.getRuntime().exec(array);
                }
            }
        };
        JAPController.m_Model = JAPModel.getInstance();
    }
    
    public void start() {
        Database.registerDistributor(new IDistributor() {
            public void addJob(final IDistributable distributable) {
            }
        });
        InfoServiceDBEntry.setJVMNetworkErrorHandling(new Runnable() {
            public void run() {
                JAPController.goodBye(false);
            }
        }, 60000L);
        this.m_observableInfo = new Updater.ObservableInfo((Observable)JAPModel.getInstance()) {
            static /* synthetic */ Class class$anon$infoservice$MixCascade;
            static /* synthetic */ Class class$anon$infoservice$PerformanceInfo;
            
            public Integer getUpdateChanged() {
                return JAPModel.CHANGED_INFOSERVICE_AUTO_UPDATE;
            }
            
            public boolean isUpdateDisabled() {
                return JAPModel.isInfoServiceDisabled();
            }
            
            public void notifyAdditionalObserversOnUpdate(final Class clazz) {
                if (clazz == null) {
                    throw new NullPointerException("No class given!");
                }
                if (clazz == ((JAPController$4.class$anon$infoservice$MixCascade == null) ? (JAPController$4.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPController$4.class$anon$infoservice$MixCascade)) {
                    JAPController.getInstance().notifyJAPObservers();
                }
                else if (clazz == ((JAPController$4.class$anon$infoservice$PerformanceInfo == null) ? (JAPController$4.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : JAPController$4.class$anon$infoservice$PerformanceInfo)) {
                    JAPController.getInstance().notifyJAPObservers();
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
        JAPController.m_feedback = new JAPFeedback();
        this.m_InfoServiceUpdater = new InfoServiceUpdater(this.m_observableInfo);
        this.m_perfInfoUpdater = new PerformanceInfoUpdater(this.m_observableInfo);
        this.m_paymentInstanceUpdater = new PaymentInstanceUpdater(this.m_observableInfo);
        this.m_MixCascadeUpdater = new MixCascadeUpdater(this.m_observableInfo);
        this.m_minVersionUpdater = new MinVersionUpdater(this.m_observableInfo);
        this.m_javaVersionUpdater = new JavaVersionUpdater(this.m_observableInfo);
        this.m_messageUpdater = new MessageUpdater(this.m_observableInfo);
        this.m_anonJobQueue = new JobQueue("Anon mode job queue");
        JAPController.m_Model.setAnonConnectionChecker(new AnonConnectionChecker());
        InfoServiceDBEntry.setMutableProxyInterface(JAPController.m_Model.getInfoServiceProxyInterface());
        BIConnection.setMutableProxyInterface(JAPController.m_Model.getPaymentProxyInterface());
        this.queueFetchAccountInfo = new JobQueue("FetchAccountInfoJobQueue");
        this.m_anonServiceListener = new Vector();
        if (!JAPModel.isSmallDisplay()) {
            DirectProxy.setAllowUnprotectedConnectionCallback(this.m_proxyCallback = new DirectProxy.AllowProxyConnectionCallback() {
                public Answer callback(final DirectProxy.RequestInfo requestInfo) {
                    String s = null;
                    if (JAPController.this.m_View == null) {
                        return new Answer(false, false);
                    }
                    JAPDialog.LinkedCheckBox linkedCheckBox = null;
                    JAPDialog.LinkedInformationAdapter linkedInformationAdapter;
                    if (JAPModel.getInstance().isAskForAnyNonAnonymousRequest()) {
                        linkedCheckBox = (JAPDialog.LinkedCheckBox)(linkedInformationAdapter = (JAPController$7)new JAPDialog.LinkedCheckBox(JAPMessages.getString(JAPDialog.LinkedCheckBox.MSG_DO_NOT_SHOW_AGAIN), false) {
                            public boolean isOnTop() {
                                return true;
                            }
                        });
                    }
                    else {
                        linkedInformationAdapter = new JAPDialog.LinkedInformationAdapter() {
                            public boolean isOnTop() {
                                return true;
                            }
                        };
                    }
                    final String string = requestInfo.getURI() + ((requestInfo.getPort() != 80) ? (":" + requestInfo.getPort()) : "");
                    String s2;
                    if (JAPModel.getInstance().isAskForAnyNonAnonymousRequest()) {
                        s2 = JAPMessages.getString(JAPController.MSG_ALLOWUNPROTECTED, "<b>" + string + "</b>");
                        s = string;
                    }
                    else {
                        s2 = JAPMessages.getString(JAPController.MSG_ALLOWUNPROTECTED_ALL);
                    }
                    final boolean b = !JAPDialog.showYesNoDialog(JAPController.getInstance().getCurrentView(), s2, s, linkedInformationAdapter);
                    if (linkedCheckBox != null) {
                        return new Answer(!b, linkedCheckBox.getState());
                    }
                    return new Answer(!b, true);
                }
            });
        }
        this.m_smallBalanceWarningListener = new WarnSmallBalanceOnDownloadListener(-10);
        this.m_warnNoJonDoFoxHttpListener = new WarnNoJonDoFoxHttpListener(-15, true);
        try {
            final Vector<ListenerInterface> vector = new Vector<ListenerInterface>();
            for (int i = 0; i < JAPConstants.DEFAULT_ANON_HOSTS.length; ++i) {
                for (int j = 0; j < JAPConstants.DEFAULT_ANON_PORT_NUMBERS.length; ++j) {
                    vector.addElement(new ListenerInterface(JAPConstants.DEFAULT_ANON_HOSTS[i], JAPConstants.DEFAULT_ANON_PORT_NUMBERS[j], 2));
                }
            }
            final Vector<String> vector2 = new Vector<String>(JAPConstants.DEFAULT_ANON_MIX_IDs.length);
            for (int k = 0; k < JAPConstants.DEFAULT_ANON_MIX_IDs.length; ++k) {
                vector2.addElement(JAPConstants.DEFAULT_ANON_MIX_IDs[k]);
            }
            (this.m_currentMixCascade = new MixCascade(JAPMessages.getString("noCascadesAvail"), JAPConstants.DEFAULT_ANON_MIX_IDs[0], vector2, vector, System.currentTimeMillis())).setUserDefined(false, null);
            this.m_currentMixCascade.showAsTrusted(true);
            Database.getInstance((JAPController.class$anon$infoservice$CascadeIDEntry == null) ? (JAPController.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPController.class$anon$infoservice$CascadeIDEntry).update(new CascadeIDEntry(this.m_currentMixCascade));
            Database.getInstance((JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).update(new PreviouslyKnownCascadeIDEntry(this.m_currentMixCascade));
        }
        catch (Exception ex) {
            LogHolder.log(0, LogType.NET, ex);
            System.exit(-1);
        }
        try {
            final InfoServiceDBEntry[] defaultInfoServices = createDefaultInfoServices();
            for (int l = 0; l < defaultInfoServices.length; ++l) {
                Database.getInstance((JAPController.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPController.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPController.class$anon$infoservice$InfoServiceDBEntry).update(defaultInfoServices[l]);
            }
            InfoServiceHolder.getInstance().setPreferredInfoService(defaultInfoServices[0]);
        }
        catch (Exception ex2) {
            LogHolder.log(0, LogType.NET, "JAPController: Constructor - default info service: " + ex2.getMessage());
        }
        this.setInfoServiceDisabled(false);
        addDefaultCertificates();
        SignatureVerifier.getInstance().setCheckSignatures(true);
        HTTPConnectionFactory.getInstance().setTimeout(30);
        this.m_proxyDirect = null;
        this.m_proxyAnon = null;
        this.m_passwordReader = new JAPFirewallPasswdDlg();
        JAPModel.getInstance().getRoutingSettings().addObserver(this);
        JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().addObserver(this);
        JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver().addObserver(this);
        JAPController.m_Model.addObserver(this);
        Database.getInstance((JAPController.class$anon$infoservice$PerformanceInfo == null) ? (JAPController.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : JAPController.class$anon$infoservice$PerformanceInfo).addObserver(this);
        InfoServiceHolder.getInstance().addObserver(this);
        this.m_iStatusPanelMsgIdForwarderServerStatus = -1;
    }
    
    public static JAPController getInstance() {
        if (JAPController.m_Controller == null) {
            JAPController.m_Controller = new JAPController();
        }
        return JAPController.m_Controller;
    }
    
    public IRestarter getRestarter() {
        return this.m_restarter;
    }
    
    public void setRestarter(final IRestarter restarter) {
        if (restarter != null) {
            this.m_restarter = restarter;
        }
    }
    
    public void addProgramExitListener(final ProgramExitListener programExitListener) {
        if (programExitListener != null && !this.m_programExitListeners.contains(programExitListener)) {
            this.m_programExitListeners.addElement(programExitListener);
        }
    }
    
    public void setPresentationMode(final boolean bPresentationMode) {
        this.m_bPresentationMode = bPresentationMode;
    }
    
    public void setPortableJava(final boolean bPortableJava) {
        this.m_bPortableJava = bPortableJava;
    }
    
    public boolean hasPortableJava() {
        return this.m_bPortableJava;
    }
    
    public void setPortableMode(final boolean bPortable) {
        this.m_bPortable = bPortable;
    }
    
    public boolean isPortableMode() {
        return this.m_bPortable;
    }
    
    public void initCommandLineArgs(final String[] commandLineArgs) {
        if (commandLineArgs != null) {
            this.m_commandLineArgs = commandLineArgs;
        }
    }
    
    public String[] getCommandlineArgs() {
        return this.m_commandLineArgs;
    }
    
    public IPasswordReader getPasswordReader() {
        return this.m_passwordReader;
    }
    
    public void initialRun(final String s, final int n) {
        LogHolder.log(6, LogType.MISC, "Initial run of JAP...");
        Database.getInstance((JAPController.class$anon$infoservice$JAPMinVersion == null) ? (JAPController.class$anon$infoservice$JAPMinVersion = class$("anon.infoservice.JAPMinVersion")) : JAPController.class$anon$infoservice$JAPMinVersion).addObserver(this);
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                JAPController.m_feedback.start(false);
                if (JAPModel.isInfoServiceDisabled()) {
                    JAPController.this.m_InfoServiceUpdater.start(false);
                    JAPController.this.m_perfInfoUpdater.start(false);
                    JAPController.this.m_paymentInstanceUpdater.start(false);
                    JAPController.this.m_MixCascadeUpdater.start(false);
                    JAPController.this.m_minVersionUpdater.start(false);
                    JAPController.this.m_javaVersionUpdater.start(false);
                    JAPController.this.m_messageUpdater.start(false);
                    JAPController.this.m_AccountUpdater.start(false);
                }
                else {
                    if (!JAPController.this.m_InfoServiceUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_InfoServiceUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_perfInfoUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_perfInfoUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_paymentInstanceUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_paymentInstanceUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_MixCascadeUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_MixCascadeUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_minVersionUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_minVersionUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_javaVersionUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_javaVersionUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_messageUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_messageUpdater.updateAsync();
                    }
                    if (!JAPController.this.m_AccountUpdater.isFirstUpdateDone()) {
                        JAPController.this.m_AccountUpdater.updateAsync();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        if (!this.startHTTPListener(s, n)) {
            JAPDialog.showErrorDialog(this.getCurrentView(), JAPMessages.getString("errorListenerPort", new Object[] { new Integer((n <= 0) ? JAPModel.getHttpListenerPortNumber() : n) }) + "<br><br>" + JAPMessages.getString(JAPConf.MSG_READ_PANEL_HELP, new Object[] { JAPMessages.getString("confButton"), JAPMessages.getString("confListenerTab") }), LogType.NET, new JAPDialog.LinkedHelpContext("portlistener") {
                public boolean isOnTop() {
                    return true;
                }
            });
            this.setAnonMode(false);
            this.m_View.disableSetAnonMode();
            this.notifyJAPObservers();
        }
        else if (!SignatureVerifier.getInstance().isCheckSignatures()) {
            this.setAnonMode(false);
            JAPDialog.showWarningDialog(this.getCurrentView(), JAPMessages.getString(JAPConfCert.MSG_NO_CHECK_WARNING), new JAPDialog.LinkedHelpContext("cert") {
                public boolean isOnTop() {
                    return true;
                }
            });
        }
        else {
            new Thread(new Runnable() {
                public void run() {
                    if (JAPController.getInstance().isConfigAssistantShown() && !JAPDialog.isConsoleOnly() && !JAPModel.isSmallDisplay()) {
                        JAPController.this.showInstallationAssistant();
                    }
                }
            }).start();
            if (this.m_bAskAutoConnect) {
                if (JAPDialog.showYesNoDialog(this.getCurrentView(), JAPMessages.getString(JAPController.MSG_ASK_AUTO_CONNECT), new JAPDialog.LinkedHelpContext("services_general"))) {
                    JAPModel.getInstance().setAutoConnect(true);
                }
                else {
                    JAPModel.getInstance().setAutoConnect(false);
                }
            }
            if (JAPModel.isAutoConnect()) {
                this.startAnonymousMode(this.getCurrentView());
            }
        }
        if (this.m_bShowHelpAdvise) {
            JAPDialog.showMessageDialog(this.getCurrentView(), JAPMessages.getString(JAPController.MSG_READ_NEW_HELP, "00.12.005"));
            JAPHelp.getInstance().setContext("index", this.getCurrentView());
            JAPHelp.getInstance().setVisible(true);
        }
    }
    
    public boolean isAskSavePayment() {
        return this.m_bAskSavePayment;
    }
    
    public void setAskSavePayment(final boolean bAskSavePayment) {
        synchronized (this) {
            if (this.m_bAskSavePayment != bAskSavePayment) {
                this.m_bAskSavePayment = bAskSavePayment;
                this.setChanged();
                this.notifyObservers(new JAPControllerMessage(3));
            }
        }
    }
    
    public boolean isShuttingDown() {
        return this.m_bShutdown;
    }
    
    public synchronized void loadConfigFile(final String configFile, final ISplashResponse splashResponse) {
        boolean lookForConfigFile = false;
        try {
            lookForConfigFile = this.lookForConfigFile(configFile);
        }
        catch (FileNotFoundException ex9) {}
        if (configFile != null) {
            JAPModel.getInstance().setConfigFile(configFile);
        }
        else if (!lookForConfigFile) {
            JAPModel.getInstance().setConfigFile(AbstractOS.getInstance().getConfigPath("JonDo") + "jap.conf");
        }
        Document document = null;
        if (lookForConfigFile) {
            try {
                document = XMLUtil.readXMLDocument(new File(JAPModel.getInstance().getConfigFile()));
            }
            catch (Exception ex10) {
                LogHolder.log(5, LogType.MISC, "Error while loading the configuration file!");
            }
        }
        if (document == null) {
            document = XMLUtil.createDocument();
        }
        try {
            final Element documentElement = document.getDocumentElement();
            XMLUtil.removeComments(documentElement);
            if (!JAPMessages.init(new Locale(XMLUtil.parseAttribute(documentElement, "Locale", JAPMessages.getLocale().getLanguage()), ""), "JAPMessages")) {
                GUIUtils.exitWithNoMessagesError("MixConfigMessages");
            }
            final Element element = (Element)XMLUtil.getFirstChildByName(documentElement, "Debug");
            if (element != null) {
                try {
                    JAPDebug.getInstance().setLogLevel(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Level"), JAPDebug.getInstance().getLogLevel()));
                    LogHolder.setDetailLevel(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Detail"), LogHolder.getDetailLevel()));
                    final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "Type");
                    if (element2 != null) {
                        int nul = LogType.NUL;
                        final int[] availableLogTypes = LogType.getAvailableLogTypes();
                        for (int i = 0; i < availableLogTypes.length; ++i) {
                            if (XMLUtil.parseAttribute(element2, LogType.getLogTypeName(availableLogTypes[i]), true)) {
                                nul |= availableLogTypes[i];
                            }
                        }
                        JAPDebug.getInstance().setLogType(nul);
                    }
                    final Node firstChildByName = XMLUtil.getFirstChildByName(element, "Output");
                    if (firstChildByName != null) {
                        new Thread(new Runnable() {
                            public void run() {
                                JAPDebug.showConsole(XMLUtil.parseAttribute(firstChildByName, "showWindow", false), JAPController.this.getViewWindow());
                            }
                        }).start();
                        JAPDebug.setLogToFile(XMLUtil.parseValue(XMLUtil.getLastChildByName(firstChildByName, "File"), null));
                    }
                }
                catch (Exception ex11) {
                    LogHolder.log(6, LogType.MISC, " Error loading Debug Settings.");
                }
            }
            final String attribute = XMLUtil.parseAttribute(documentElement, "version", null);
            JAPController.m_Model.setDLLupdate(XMLUtil.parseAttribute(documentElement, "dllVersionUpdate", null));
            JAPController.m_Model.setDllWarningVersion(XMLUtil.parseAttribute(documentElement, "dllWarningVersion", 0));
            JAPController.m_Model.setMacOSXLibraryUpdateAtStartupNeeded(XMLUtil.parseAttribute(documentElement, "macOSXLibNeedsUpdate", false));
            if (XMLUtil.parseAttribute(documentElement, "AllowDirectUpdate", true)) {
                JAPModel.getInstance().setUpdateAnonymousConnectionSetting(XMLUtil.parseAttribute(documentElement, "AllowDirectUpdate", 0));
            }
            else {
                JAPModel.getInstance().setUpdateAnonymousConnectionSetting(1);
            }
            JAPModel.getInstance().setAnonymizedHttpHeaders(XMLUtil.parseAttribute(documentElement, "anonymizedHttpHeaders", true));
            this.m_warnNoJonDoFoxHttpListener = new WarnNoJonDoFoxHttpListener(-15, XMLUtil.parseAttribute(documentElement, "warnInsecureBrowser", this.m_warnNoJonDoFoxHttpListener.isWarningShownOnInsecureBrowser()));
            JAPModel.getInstance().setReminderForOptionalUpdate(XMLUtil.parseAttribute(documentElement, "remindOptionalUpdate", true));
            JAPModel.getInstance().setReminderForJavaUpdate(XMLUtil.parseAttribute(documentElement, "remindJavaUpdate", !this.isPortableMode()));
            if (!this.m_bShowConfigAssistant) {
                this.m_bShowConfigAssistant = XMLUtil.parseAttribute(documentElement, "showConfigAssistant", false);
            }
            AnonClient.setLoginTimeout(XMLUtil.parseAttribute(documentElement, "loginTimeout", 30000));
            InfoServiceDBEntry.setConnectionTimeout(XMLUtil.parseAttribute(documentElement, "isConnectionTimeout", 20000));
            JAPModel.getInstance().setCascadeAutoSwitch(XMLUtil.parseAttribute(documentElement, "AutoSwitchCascades", true));
            JAPModel.getInstance().setAutoChooseCascadeOnStartup(XMLUtil.parseAttribute(documentElement, "autoSwitchCascadesOnStartup", true));
            JAPModel.getInstance().setAskForAnyNonAnonymousRequest(XMLUtil.parseAttribute(documentElement, "askForUnprotectedSurfing", true));
            JAPModel.getInstance().initHelpPath(XMLUtil.restoreFilteredXMLChars(XMLUtil.parseAttribute(documentElement, "helpPath", null)));
            if (!JAPDialog.isConsoleOnly()) {
                final String text = splashResponse.getText();
                splashResponse.setText(JAPMessages.getString(JAPController.MSG_UPDATING_HELP));
                try {
                    JAPModel.getInstance().getHelpURL();
                    if (!JAPModel.getInstance().isHelpPathDefined() && AbstractOS.getInstance().isHelpAutoInstalled() && !JAPDialog.isConsoleOnly()) {
                        JAPModel.getInstance().setHelpPath(new File(AbstractOS.getInstance().getDefaultHelpPath("JonDo")));
                    }
                }
                catch (Throwable t) {
                    LogHolder.log(2, LogType.MISC, "Error while installing help");
                    LogHolder.log(2, LogType.MISC, t);
                }
                splashResponse.setText(text);
            }
            JAPController.m_Model.setHttpListenerPortNumber(XMLUtil.parseAttribute(documentElement, "portNumber", JAPModel.getHttpListenerPortNumber()));
            JAPModel.getInstance().setHttpListenerIsLocal(XMLUtil.parseAttribute(documentElement, "listenerIsLocal", true));
            try {
                this.mbActCntMessageNeverRemind = XMLUtil.parseAttribute(documentElement, "neverRemindActiveContent", false);
                this.mbDoNotAbuseReminder = XMLUtil.parseAttribute(documentElement, "doNotAbuseReminder", false);
                if (this.mbActCntMessageNeverRemind && this.mbDoNotAbuseReminder) {
                    this.mbActCntMessageNotRemind = true;
                }
                JAPController.m_Model.setNeverRemindGoodbye(XMLUtil.parseAttribute(documentElement, "neverRemindGoodBye", false));
                this.m_bForwarderNotExplain = XMLUtil.parseAttribute(documentElement, "neverExplainForward", false);
            }
            catch (Exception ex12) {
                LogHolder.log(6, LogType.MISC, "Error loading reminder message ins setAnonMode.");
            }
            this.setInfoServiceDisabled(XMLUtil.parseAttribute(documentElement, "infoServiceDisabled", JAPModel.isInfoServiceDisabled()));
            final int attribute2 = XMLUtil.parseAttribute(documentElement, "infoserviceTimeout", 10);
            try {
                if (attribute2 >= 1 && attribute2 <= 60) {
                    HTTPConnectionFactory.getInstance().setTimeout(attribute2);
                }
            }
            catch (Exception ex13) {
                LogHolder.log(6, LogType.MISC, "Error loading InfoService timeout.");
            }
            ProxyInterface proxyInterface = null;
            try {
                String attribute3 = XMLUtil.parseAttribute(documentElement, "proxyType", "HTTP/TCP");
                if (attribute3.equalsIgnoreCase("HTTP")) {
                    attribute3 = "HTTP/TCP";
                }
                else if (attribute3.equalsIgnoreCase("SOCKS")) {
                    attribute3 = "socks";
                }
                JAPModel.getInstance().setUseProxyAuthentication(XMLUtil.parseAttribute(documentElement, "proxyAuthorization", false));
                proxyInterface = new ProxyInterface(XMLUtil.parseAttribute(documentElement, "proxyHostName", null), XMLUtil.parseAttribute(documentElement, "proxyPortNumber", -1), attribute3, XMLUtil.parseAttribute(documentElement, "proxyAuthUserID", null), this.getPasswordReader(), JAPModel.getInstance().isProxyAuthenticationUsed(), XMLUtil.parseAttribute(documentElement, "proxyMode", false));
            }
            catch (Exception ex) {
                LogHolder.log(5, LogType.NET, "Could not load proxy settings!", ex);
            }
            this.changeProxyInterface(proxyInterface, XMLUtil.parseAttribute(documentElement, "proxyAuthorization", false), this.getCurrentView());
            this.setDummyTraffic(XMLUtil.parseAttribute(documentElement, "DummytrafficInterval", 20000));
            JAPModel.getInstance().setAutoConnect(XMLUtil.parseAttribute(documentElement, "autoconnect", true));
            JAPController.m_Model.setAutoReConnect(XMLUtil.parseAttribute(documentElement, "autoReconnect", true));
            JAPController.m_Model.setMinimizeOnStartup(XMLUtil.parseAttribute(documentElement, "minimizedStartup", false));
            try {
                final Element element3 = (Element)XMLUtil.getFirstChildByName(documentElement, SignatureVerifier.getXmlSettingsRootNodeName());
                if (element3 == null) {
                    throw new Exception("No SignatureVerification node found. Using default settings for signature verification.");
                }
                final Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>();
                hashtable.put(new Integer(7), new Integer(7));
                SignatureVerifier.getInstance().loadSettingsFromXml(element3, hashtable);
            }
            catch (Exception ex2) {
                LogHolder.log(3, LogType.MISC, ex2);
            }
            Database.getInstance((JAPController.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (JAPController.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : JAPController.class$anon$infoservice$BlacklistedCascadeIDEntry).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "BlacklistedCascades"));
            final boolean attribute4 = XMLUtil.parseAttribute(XMLUtil.getFirstChildByName(documentElement, "BlacklistedCascades"), "autoBlacklistNewCascades", false);
            BlacklistedCascadeIDEntry.putNewCascadesInBlacklist(false);
            final Node firstChildByName2 = XMLUtil.getFirstChildByName(documentElement, "MixCascades");
            if (firstChildByName2 != null) {
                for (Node node = firstChildByName2.getFirstChild(); node != null; node = node.getNextSibling()) {
                    if (node.getNodeName().equals("MixCascade")) {
                        try {
                            final MixCascade mixCascade = new MixCascade((Element)node, Long.MAX_VALUE);
                            final String context = mixCascade.getContext();
                            Label_1581: {
                                if (!JAPController.m_Model.getContext().startsWith("jondonym") || !context.equals("jondonym.premium")) {
                                    if (!context.equals(JAPController.m_Model.getContext())) {
                                        LogHolder.log(5, LogType.MISC, "No service context match " + context + "." + mixCascade.getName());
                                        break Label_1581;
                                    }
                                }
                                try {
                                    Database.getInstance((JAPController.class$anon$infoservice$MixCascade == null) ? (JAPController.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPController.class$anon$infoservice$MixCascade).update(mixCascade);
                                }
                                catch (Exception ex14) {}
                                Database.getInstance((JAPController.class$anon$infoservice$CascadeIDEntry == null) ? (JAPController.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPController.class$anon$infoservice$CascadeIDEntry).update(new CascadeIDEntry(mixCascade));
                            }
                        }
                        catch (Exception ex15) {}
                    }
                }
            }
            BlacklistedCascadeIDEntry.putNewCascadesInBlacklist(attribute4);
            Database.getInstance((JAPController.class$anon$infoservice$CascadeIDEntry == null) ? (JAPController.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPController.class$anon$infoservice$CascadeIDEntry).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "KnownCascades"));
            Database.getInstance((JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "PreviouslyKnownCascades"));
            TrustModel.fromXmlElement((Element)XMLUtil.getFirstChildByName(documentElement, "TrustModels"));
            Database.getInstance((JAPController.class$anon$infoservice$StatusInfo == null) ? (JAPController.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : JAPController.class$anon$infoservice$StatusInfo).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "MixCascadeStatusList"));
            Database.getInstance((JAPController.class$anon$infoservice$PerformanceInfo == null) ? (JAPController.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : JAPController.class$anon$infoservice$PerformanceInfo).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "PerformanceInfoList"), true);
            Database.getInstance((JAPController.class$anon$infoservice$DeletedMessageIDDBEntry == null) ? (JAPController.class$anon$infoservice$DeletedMessageIDDBEntry = class$("anon.infoservice.DeletedMessageIDDBEntry")) : JAPController.class$anon$infoservice$DeletedMessageIDDBEntry).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "DeletedMessageIDEntries"));
            Database.getInstance((JAPController.class$anon$infoservice$ClickedMessageIDDBEntry == null) ? (JAPController.class$anon$infoservice$ClickedMessageIDDBEntry = class$("anon.infoservice.ClickedMessageIDDBEntry")) : JAPController.class$anon$infoservice$ClickedMessageIDDBEntry).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, "ClickedMessageIDDBEntries"));
            if (!JAPModel.isSmallDisplay() && !JAPDialog.isConsoleOnly()) {
                JAPModel.getInstance().updateSystemLookAndFeels();
            }
            String s = XMLUtil.parseAttribute(documentElement, "LookAndFeel", null);
            if (s == null) {
                final Node firstChildByName3 = XMLUtil.getFirstChildByName(documentElement, "LookAndFeels");
                s = XMLUtil.parseAttribute(firstChildByName3, "current", "unknown");
                if (firstChildByName3 != null) {
                    final NodeList elementsByTagName = ((Element)firstChildByName3).getElementsByTagName("LookAndFeel");
                    for (int j = 0; j < elementsByTagName.getLength(); ++j) {
                        try {
                            final File file = new File(XMLUtil.parseValue(elementsByTagName.item(j), null));
                            try {
                                if (JAPModel.isSmallDisplay() || JAPDialog.isConsoleOnly() || GUIUtils.registerLookAndFeelClasses(file).size() > 0) {
                                    JAPModel.getInstance().addLookAndFeelFile(file);
                                }
                            }
                            catch (IllegalAccessException ex16) {
                                JAPModel.getInstance().addLookAndFeelFile(file);
                            }
                        }
                        catch (Exception ex17) {
                            LogHolder.log(3, LogType.MISC, "Error while parsing Look&Feels!");
                        }
                    }
                }
            }
            if (!JAPModel.isSmallDisplay() && !JAPDialog.isConsoleOnly()) {
                final UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
                int k = 0;
                while (k < installedLookAndFeels.length) {
                    if (!installedLookAndFeels[k].getName().equals(s)) {
                        if (!installedLookAndFeels[k].getClassName().equals(s)) {
                            ++k;
                            continue;
                        }
                    }
                    try {
                        UIManager.setLookAndFeel(installedLookAndFeels[k].getClassName());
                    }
                    catch (Throwable t2) {
                        try {
                            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        }
                        catch (UnsupportedLookAndFeelException ex18) {}
                        catch (IllegalAccessException ex19) {}
                        catch (InstantiationException ex20) {}
                        catch (ClassNotFoundException ex21) {}
                        LogHolder.log(4, LogType.GUI, "Exception while setting look-and-feel '" + installedLookAndFeels[k].getClassName() + "'");
                    }
                    break;
                }
                JAPModel.getInstance().setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());
            }
            final Element element4 = (Element)XMLUtil.getFirstChildByName(documentElement, "GUI");
            JAPModel.getInstance().setFontSize(XMLUtil.parseAttribute(element4, "fontSize", JAPModel.getInstance().getFontSize()));
            JAPDialog.setOptimizedFormat(XMLUtil.parseAttribute(element4, "dialogOptFormat", JAPDialog.getOptimizedFormat()));
            JAPModel.getInstance().setFontSize(XMLUtil.parseAttribute(documentElement, "fontSize", JAPModel.getInstance().getFontSize()));
            JAPDialog.setOptimizedFormat(XMLUtil.parseAttribute(documentElement, "dialogOptFormat", JAPDialog.getOptimizedFormat()));
            final Point point = new Point(0, 0);
            final Node firstChildByName4 = XMLUtil.getFirstChildByName(element4, "ConfigWindow");
            XMLUtil.getFirstChildByName(firstChildByName4, "Size");
            if (!JAPDialog.isConsoleOnly()) {
                final Dimension dimension = new Dimension();
                Dimension configSize = this.parseWindowSize(firstChildByName4, dimension, false, false);
                JAPModel.getInstance().setSaveConfigWindowSize(configSize != null);
                if (configSize == null) {
                    configSize = this.parseWindowSize(firstChildByName4, dimension, false, true);
                }
                if (configSize != dimension) {
                    JAPModel.getInstance().setConfigSize(configSize);
                }
            }
            final Point windowLocation = this.parseWindowLocation(firstChildByName4, point, false);
            JAPModel.getInstance().setSaveConfigWindowPosition(windowLocation != null);
            if (windowLocation != point) {
                JAPModel.getInstance().setConfigWindowLocation(windowLocation);
            }
            final Node firstChildByName5 = XMLUtil.getFirstChildByName(element4, "IconifiedWindow");
            JAPModel.getInstance().setMiniViewOnTop(XMLUtil.parseAttribute(firstChildByName5, "alwaysOnTop", true));
            final Node firstChildByName6 = XMLUtil.getFirstChildByName(firstChildByName5, "Size");
            if (!JAPDialog.isConsoleOnly()) {
                JAPModel.getInstance().setIconifiedSize(new Dimension(XMLUtil.parseAttribute(firstChildByName6, "width", 0), XMLUtil.parseAttribute(firstChildByName6, "height", 0)));
            }
            final Point windowLocation2 = this.parseWindowLocation(firstChildByName5, point, true);
            JAPModel.getInstance().setSaveIconifiedWindowPosition(windowLocation2 != null);
            if (windowLocation2 != point) {
                JAPModel.getInstance().setIconifiedWindowLocation(windowLocation2);
            }
            final Node firstChildByName7 = XMLUtil.getFirstChildByName(element4, "HelpWindow");
            final Point windowLocation3 = this.parseWindowLocation(firstChildByName7, point, false);
            JAPModel.getInstance().setSaveHelpWindowPosition(windowLocation3 != null);
            if (windowLocation3 != point) {
                JAPModel.getInstance().setHelpWindowLocation(windowLocation3);
            }
            if (!JAPDialog.isConsoleOnly()) {
                final Dimension dimension2 = new Dimension();
                final Dimension windowSize = this.parseWindowSize(firstChildByName7, dimension2, false, false);
                JAPModel.getInstance().setSaveHelpWindowSize(windowSize != null);
                if (windowSize != dimension2) {
                    JAPModel.getInstance().setHelpWindowSize(windowSize);
                }
            }
            final Element element5 = (Element)XMLUtil.getFirstChildByName(element4, "MainWindow");
            final Point windowLocation4 = this.parseWindowLocation(element5, point, true);
            JAPModel.getInstance().setSaveMainWindowPosition(windowLocation4 != null);
            if (windowLocation4 != point) {
                JAPModel.getInstance().setMainWindowLocation(windowLocation4);
            }
            this.setMoveToSystrayOnStartup(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element5, "MoveToSystray"), false));
            JAPModel.getInstance().setStartPortableFirefox(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element5, "StartPortableFirefox"), true));
            final Element element6 = (Element)XMLUtil.getFirstChildByName(element5, "DefaultView");
            String s2;
            if (this.m_bShowConfigAssistant) {
                s2 = XMLUtil.parseValue(element6, "Simplified");
            }
            else {
                s2 = XMLUtil.parseValue(element6, "Normal");
                if (element6 == null) {
                    this.m_bShowHelpAdvise = true;
                }
            }
            if (s2.equals("Simplified")) {
                this.setDefaultView(2);
            }
            else {
                this.setDefaultView(1);
            }
            try {
                final Element element7 = (Element)XMLUtil.getFirstChildByName(documentElement, InfoServiceHolder.getXmlSettingsRootNodeName());
                if (XMLUtil.parseAttribute(element7, "AllowDirectConnection", true)) {
                    JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(XMLUtil.parseAttribute(element7, "AllowDirectConnection", 0));
                }
                else {
                    JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(1);
                }
                if (element7 == null) {
                    throw new Exception("No InfoServiceManagement node found. Using default settings for infoservice management in InfoServiceHolder.");
                }
                InfoServiceHolder.getInstance().loadSettingsFromXml(element7, true);
            }
            catch (Exception ex3) {
                LogHolder.log(3, LogType.MISC, ex3);
            }
            try {
                final Element element8 = (Element)XMLUtil.getFirstChildByName(documentElement, "Payment");
                if (XMLUtil.parseAttribute(element8, "AllowDirectConnection", true)) {
                    JAPModel.getInstance().setPaymentAnonymousConnectionSetting(XMLUtil.parseAttribute(element8, "AllowDirectConnection", 0));
                }
                else {
                    JAPModel.getInstance().setPaymentAnonymousConnectionSetting(1);
                }
                this.m_bAskSavePayment = XMLUtil.parseAttribute(element8, "askIfNotSaved", true);
                BIConnection.setConnectionTimeout(XMLUtil.parseAttribute(element8, "timeout", 30000));
                final Element element9 = (Element)XMLUtil.getFirstChildByName(element8, "PayAccounts");
                final Node firstChildByName8 = XMLUtil.getFirstChildByName(element8, "PaymentInstances");
                if (firstChildByName8 != null) {
                    for (Node node2 = firstChildByName8.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                        if (node2.getNodeName().equals("PaymentInstance")) {
                            try {
                                final PaymentInstanceDBEntry paymentInstanceDBEntry = new PaymentInstanceDBEntry((Element)node2, Long.MAX_VALUE);
                                if (paymentInstanceDBEntry.isVerified()) {
                                    Database.getInstance((JAPController.class$anon$pay$PaymentInstanceDBEntry == null) ? (JAPController.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : JAPController.class$anon$pay$PaymentInstanceDBEntry).update(paymentInstanceDBEntry);
                                }
                            }
                            catch (Exception ex4) {
                                LogHolder.log(3, LogType.MISC, ex4);
                            }
                        }
                    }
                }
                final Hashtable<Object, String> hashtable2 = new Hashtable<Object, String>();
                JAPDialog japDialog = null;
                IMiscPasswordReader miscPasswordReader;
                if (JAPDialog.isConsoleOnly()) {
                    miscPasswordReader = new IMiscPasswordReader() {
                        public String readPassword(final Object o) {
                            return null;
                        }
                    };
                }
                else {
                    final JAPDialog.LinkedInformationAdapter linkedInformationAdapter = new JAPDialog.LinkedInformationAdapter() {
                        public boolean isOnTop() {
                            return true;
                        }
                    };
                    Component component;
                    if (splashResponse instanceof Component) {
                        component = (Component)splashResponse;
                    }
                    else {
                        component = new Frame();
                    }
                    final JAPDialog japDialog2 = new JAPDialog(component, "JAP: " + JAPMessages.getString(JAPController.MSG_ACCPASSWORDENTERTITLE), true);
                    japDialog2.setResizable(false);
                    japDialog2.setAlwaysOnTop(true);
                    japDialog = japDialog2;
                    japDialog2.setDefaultCloseOperation(1);
                    new PasswordContentPane(japDialog2, 2, JAPMessages.getString(JAPController.MSG_ACCPASSWORDENTER, new Long(Long.MAX_VALUE))).updateDialog();
                    japDialog2.pack();
                    miscPasswordReader = new IMiscPasswordReader() {
                        private Vector pwMatches = new Vector();
                        private Enumeration currentPWIteration = null;
                        private Object lastAccount = null;
                        private boolean skipAll = false;
                        private final /* synthetic */ Hashtable val$completedAccounts = hashtable2;
                        private final /* synthetic */ JAPDialog.LinkedInformationAdapter val$onTopAdapter = linkedInformationAdapter;
                        
                        public String readPassword(final Object o) {
                            final PasswordContentPane passwordContentPane = new PasswordContentPane(japDialog2, 2, JAPMessages.getString(JAPController.MSG_ACCPASSWORDENTER, o));
                            passwordContentPane.setDefaultButtonOperation(1);
                            if (this.skipAll) {
                                return null;
                            }
                            if (this.lastAccount == null) {
                                this.lastAccount = o;
                            }
                            if (!o.equals(this.lastAccount)) {
                                this.pwMatches.addElement(this.val$completedAccounts.get(this.lastAccount));
                                this.currentPWIteration = null;
                                this.currentPWIteration = this.pwMatches.elements();
                                this.lastAccount = o;
                            }
                            if (this.currentPWIteration != null) {
                                if (this.currentPWIteration.hasMoreElements()) {
                                    return this.currentPWIteration.nextElement();
                                }
                                this.currentPWIteration = null;
                            }
                            String password;
                            do {
                                password = passwordContentPane.readPassword(null);
                                if (password != null) {
                                    this.val$completedAccounts.put(o, password);
                                    return password;
                                }
                                this.val$completedAccounts.remove(o);
                            } while (!JAPDialog.showYesNoDialog((Component)splashResponse, JAPMessages.getString(JAPController.MSG_LOSEACCOUNTDATA), this.val$onTopAdapter));
                            this.skipAll = true;
                            return password;
                        }
                    };
                }
                PayAccountsFile.init(element9, miscPasswordReader, true, 1);
                this.m_AccountUpdater = new AccountUpdater();
                if (japDialog != null) {
                    japDialog.dispose();
                }
                if (hashtable2.size() > 0) {
                    this.setPaymentPassword(hashtable2.elements().nextElement());
                }
            }
            catch (Exception ex5) {
                LogHolder.log(1, LogType.PAY, "Error loading Payment configuration.", ex5);
                if (JAPDialog.isConsoleOnly()) {
                    LogHolder.log(1, LogType.PAY, "Exiting...");
                    System.exit(1);
                }
                else if (JAPDialog.showConfirmDialog(new Frame(), JAPMessages.getString(JAPController.MSG_PAYMENT_DAMAGED), 0, 0, new JAPDialog.LinkedInformationAdapter() {
                    public boolean isOnTop() {
                        return true;
                    }
                }) != 0) {
                    System.exit(1);
                }
            }
            PayAccountsFile.init(null, null, true, 1);
            PayAccountsFile.getInstance();
            try {
                final Element element10 = (Element)XMLUtil.getFirstChildByName(documentElement, "TOR");
                JAPModel.getInstance().setTorActivated(false);
                setTorMaxConnectionsPerRoute(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element10, "MaxConnectionsPerRoute"), JAPModel.getTorMaxConnectionsPerRoute()));
                final Element element11 = (Element)XMLUtil.getFirstChildByName(element10, "RouteLen");
                setTorRouteLen(XMLUtil.parseAttribute(element11, "min", JAPModel.getTorMinRouteLen()), XMLUtil.parseAttribute(element11, "max", JAPModel.getTorMaxRouteLen()));
                setPreCreateAnonRoutes(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element10, "PreCreateAnonRoutes"), JAPModel.isPreCreateAnonRoutesEnabled()));
                setTorUseNoneDefaultDirServer(XMLUtil.parseAttribute(XMLUtil.getFirstChildByName(element10, "DirectoryServer"), "useNoneDefault", JAPModel.isTorNoneDefaultDirServerEnabled()));
            }
            catch (Exception ex6) {
                LogHolder.log(3, LogType.MISC, "Error loading Tor configuration.", ex6);
            }
            try {
                final Element element12 = (Element)XMLUtil.getFirstChildByName(documentElement, "MixMinion");
                JAPModel.getInstance().setMixMinionActivated(false);
                JAPModel.getInstance().setMixminionRouteLen(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element12, "RouteLen"), JAPModel.getMixminionRouteLen()));
                String attribute5 = XMLUtil.parseAttribute(XMLUtil.getFirstChildByName(element12, "MixminionREPLYMail"), "MixminionSender", "");
                if ((attribute == null || attribute.compareTo("00.12.005") < 0) && attribute5.equals("none")) {
                    attribute5 = "";
                }
                JAPModel.getInstance().setMixminionMyEMail(attribute5);
                final String value = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element12, "MixminionPasswordHash"), null);
                if (value != null) {
                    JAPModel.getInstance().setMixinionPasswordHash(Base64.decode(value));
                }
                JAPModel.getInstance().setMixminionKeyring(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element12, "MixminionKeyring"), ""));
            }
            catch (Exception ex7) {
                LogHolder.log(3, LogType.MISC, "Error loading Mixminion configuration.", ex7);
            }
            final Element element13 = (Element)XMLUtil.getFirstChildByName(documentElement, "JapForwardingSettings");
            if (element13 != null) {
                JAPModel.getInstance().getRoutingSettings().loadSettingsFromXml(element13);
            }
            else {
                LogHolder.log(3, LogType.MISC, "No JapForwardingSettings node found. Using default settings for forwarding.");
            }
            if (JAPModel.getInstance().isCascadeAutoSwitched() && JAPModel.getInstance().isCascadeAutoChosenOnStartup()) {
                this.setCurrentMixCascade(new AutoSwitchedMixCascadeContainer(true).getNextCascade());
            }
            else {
                final Element element14 = (Element)XMLUtil.getFirstChildByName(documentElement, "MixCascade");
                try {
                    this.m_currentMixCascade = new MixCascade(element14, Long.MAX_VALUE);
                }
                catch (Exception ex22) {
                    this.m_currentMixCascade = new AutoSwitchedMixCascadeContainer().getNextCascade();
                }
            }
            Database.getInstance((JAPController.class$anon$infoservice$MixCascade == null) ? (JAPController.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPController.class$anon$infoservice$MixCascade).update(this.m_currentMixCascade);
            Database.getInstance((JAPController.class$anon$infoservice$CascadeIDEntry == null) ? (JAPController.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPController.class$anon$infoservice$CascadeIDEntry).update(new CascadeIDEntry(this.m_currentMixCascade));
            TermsAndConditions.loadTermsAndConditionsFromXMLElement((Element)XMLUtil.getFirstChildByName(documentElement, "TermsAndConditionsList"));
            Database.getInstance((JAPController.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (JAPController.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : JAPController.class$anon$terms$template$TermsAndConditionsTemplate).loadFromXml((Element)XMLUtil.getFirstChildByName(documentElement, TermsAndConditionsTemplate.XML_ELEMENT_CONTAINER_NAME));
        }
        catch (Exception ex8) {
            LogHolder.log(3, LogType.MISC, "Error loading configuration! ", ex8);
        }
        this.notifyJAPObservers();
    }
    
    public void uninstall(final String s) throws IOException {
        try {
            while (this.lookForConfigFile(s)) {
                if (JAPModel.getInstance().getConfigFile() == null) {
                    LogHolder.log(1, LogType.MISC, "Config file found, but path was not set in model!");
                    break;
                }
                final File file = new File(JAPModel.getInstance().getConfigFile());
                if (file.exists()) {
                    Document xmlDocument;
                    try {
                        xmlDocument = XMLUtil.readXMLDocument(file);
                        if (xmlDocument == null) {
                            throw new IOException("Error while loading the configuration file!");
                        }
                    }
                    catch (Exception ex) {
                        throw new IOException(ex.getMessage());
                    }
                    JAPModel.getInstance().initHelpPath(XMLUtil.restoreFilteredXMLChars(XMLUtil.parseAttribute(xmlDocument.getDocumentElement(), "helpPath", null)));
                    JAPModel.getInstance().resetHelpPath();
                    try {
                        file.delete();
                        continue;
                    }
                    catch (SecurityException ex2) {
                        throw new IOException(ex2.getMessage());
                    }
                }
                LogHolder.log(1, LogType.MISC, "Config file found but does not exist!");
                break;
            }
        }
        catch (FileNotFoundException ex3) {}
        final String appdataDefaultDirectory = AbstractOS.getInstance().getAppdataDefaultDirectory("JonDo");
        if (appdataDefaultDirectory != null) {
            final File file2 = new File(appdataDefaultDirectory);
            final File classDirectory = ClassUtil.getClassDirectory((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController);
            if (file2.exists() && file2.isDirectory() && file2.getPath().indexOf("JonDo") >= 0 && (classDirectory == null || !classDirectory.equals(file2))) {
                RecursiveFileTool.deleteRecursion(file2);
            }
            else {
                LogHolder.log(1, LogType.MISC, "There was a problem while deleting the app data directory: " + file2);
            }
        }
    }
    
    public void preLoadConfigFile(final String s) throws FileNotFoundException {
        if (this.lookForConfigFile(s)) {
            try {
                String s2;
                while ((s2 = new BufferedReader(new FileReader(JAPController.m_Model.getConfigFile())).readLine()) != null && s2.indexOf("<JAP") < 0) {}
                if (s2 == null) {
                    LogHolder.log(2, LogType.MISC, "Unable to pre-load config file " + JAPController.m_Model.getConfigFile() + ".");
                    return;
                }
                final int index = s2.indexOf("?>");
                if (index >= 0) {
                    s2 = s2.substring(index + 2, s2.length());
                }
                if (s2.indexOf("</JAP>") < 0) {
                    final int index2 = s2.indexOf(">");
                    if (index2 <= 0 || s2.length() < index2 + 1) {
                        LogHolder.log(2, LogType.MISC, "Unable to pre-load config file " + JAPController.m_Model.getConfigFile() + ". Invalid XML structure.");
                        return;
                    }
                    s2 = s2.substring(0, index2 + 1) + "</JAP>";
                }
                final Document xmlDocument = XMLUtil.toXMLDocument(s2);
                JAPController.m_Model.setShowSplashScreen(XMLUtil.parseAttribute(xmlDocument, "ShowSplashScreen", true));
                JAPController.m_Model.setPortableBrowserpath(XMLUtil.parseAttribute(xmlDocument, "portableBrowserPath", null));
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.MISC, "Unable to pre-load config file " + JAPController.m_Model.getConfigFile() + ".", ex);
            }
        }
    }
    
    private boolean lookForConfigFile(final String s) throws FileNotFoundException {
        boolean b = false;
        if (s != null) {
            b = this.loadConfigFileCommandLine(s);
            if (!b) {
                if (!this.isPortableMode() || AbstractOS.getInstance().getDefaultBrowserPath() == null) {
                    this.m_bShowConfigAssistant = true;
                    this.m_bAllowPaidServices = false;
                }
                throw new FileNotFoundException("Could not initialise with specified config file: " + s);
            }
        }
        if (!b) {
            b = this.loadConfigFileOSdependent();
        }
        if (!b) {
            b = this.loadConfigFileHome();
        }
        if (!b) {
            b = this.loadConfigFileCurrentDir();
        }
        if (!b) {
            JAPController.m_Model.setConfigFile(AbstractOS.getInstance().getConfigPath("JonDo") + "jap.conf");
            this.m_bShowConfigAssistant = true;
            this.m_bAllowPaidServices = false;
        }
        return b;
    }
    
    private boolean loadConfigFileCommandLine(final String configFile) {
        LogHolder.log(6, LogType.MISC, "Trying to load configuration from: " + configFile);
        try {
            final FileInputStream fileInputStream = new FileInputStream(configFile);
            JAPModel.getInstance().setConfigFile(configFile);
            try {
                fileInputStream.close();
            }
            catch (Exception ex) {}
            return true;
        }
        catch (Exception ex2) {
            LogHolder.log(3, LogType.MISC, "Configuration file \"" + configFile + "\" not found.");
            return false;
        }
    }
    
    private Dimension parseWindowSize(final Node node, final Dimension dimension, final boolean b, final boolean b2) {
        final Element element = (Element)XMLUtil.getFirstChildByName(node, "Size");
        final Dimension dimension2 = new Dimension();
        final boolean attribute = XMLUtil.parseAttribute(element, "save", b);
        dimension2.width = XMLUtil.parseAttribute(element, "width", 0);
        dimension2.height = XMLUtil.parseAttribute(element, "height", 0);
        if (dimension2.width > 0 && dimension2.height > 0 && (attribute || b2)) {
            return dimension2;
        }
        if (attribute) {
            return dimension;
        }
        return null;
    }
    
    private Point parseWindowLocation(final Node node, final Point point, final boolean b) {
        final Element element = (Element)XMLUtil.getFirstChildByName(node, "Location");
        final boolean attribute = XMLUtil.parseAttribute(element, "save", b);
        if (element != null && element.getAttribute("x") != null && element.getAttribute("x").trim().length() != 0 && element.getAttribute("y") != null && element.getAttribute("y").trim().length() != 0 && attribute) {
            final Point point2 = new Point();
            point2.x = XMLUtil.parseAttribute(element, "x", 0);
            point2.y = XMLUtil.parseAttribute(element, "y", 0);
            return point2;
        }
        if (attribute) {
            return point;
        }
        return null;
    }
    
    private boolean loadConfigFileOSdependent() {
        final String string = AbstractOS.getInstance().getConfigPath("JonDo") + "jap.conf";
        LogHolder.log(6, LogType.MISC, "Trying to load configuration from: " + string);
        try {
            final FileInputStream fileInputStream = new FileInputStream(string);
            JAPModel.getInstance().setConfigFile(string);
            try {
                fileInputStream.close();
            }
            catch (Exception ex) {}
            return true;
        }
        catch (Exception ex2) {
            LogHolder.log(3, LogType.MISC, "JAPController: loadConfigFileOSdependent: Configuration file \"" + string + "\" not found.");
            return false;
        }
    }
    
    private boolean loadConfigFileHome() {
        final String string = System.getProperty("user.home", "") + File.separator + "jap.conf";
        LogHolder.log(6, LogType.MISC, "JAPController: loadConfigFile: Trying to load configuration from: " + string);
        try {
            final FileInputStream fileInputStream = new FileInputStream(string);
            JAPModel.getInstance().setConfigFile(string);
            try {
                fileInputStream.close();
            }
            catch (Exception ex) {}
            return true;
        }
        catch (Exception ex2) {
            LogHolder.log(3, LogType.MISC, "JAPController: loadConfigFile: Configuration file \"" + string + "\" not found.");
            return false;
        }
    }
    
    private boolean loadConfigFileCurrentDir() {
        final String configFile = "jap.conf";
        LogHolder.log(6, LogType.MISC, "JAPController: loadConfigFile: Trying to load configuration from: " + configFile);
        try {
            final FileInputStream fileInputStream = new FileInputStream(configFile);
            JAPModel.getInstance().setConfigFile(configFile);
            try {
                fileInputStream.close();
            }
            catch (Exception ex) {}
            return true;
        }
        catch (Exception ex2) {
            LogHolder.log(3, LogType.MISC, "JAPController: loadConfigFile: Configuration file \"" + configFile + "\" not found.");
            return false;
        }
    }
    
    private void restartJAP() {
        final MacOS macOS = (AbstractOS.getInstance() instanceof MacOS) ? ((MacOS)AbstractOS.getInstance()) : null;
        final String[] array = new String[4 + this.m_commandLineArgs.length];
        if (this.m_commandLineArgs.length > 0) {
            System.arraycopy(this.m_commandLineArgs, 0, array, 4, this.m_commandLineArgs.length);
        }
        array[2] = this.CLASS_PATH;
        array[3] = ((macOS != null) ? "JAPMacintosh" : "JAP");
        String s;
        String s2;
        String s3;
        if (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") != -1) {
            s = System.getProperty("com.ms.sysdir") + File.separator;
            s2 = "jview";
            s3 = "/cp";
        }
        else {
            s = AbstractOS.getInstance().getProperty("java.home") + File.separator + "bin" + File.separator;
            s2 = "javaw";
            s3 = "-cp";
        }
        final boolean b = macOS != null && macOS.isBundle();
        try {
            array[0] = s + s2;
            array[1] = s3;
            if (!b) {
                this.m_restarter.exec(array);
            }
            else {
                this.m_restarter.exec(new String[] { "open", "-n", macOS.getBundlePath() });
            }
        }
        catch (Exception ex) {
            final String s4 = "java";
            final String s5 = "-cp";
            array[0] = s + s4;
            array[1] = s5;
            try {
                this.m_restarter.exec(array);
            }
            catch (Exception ex2) {
                LogHolder.log(2, LogType.ALL, "Error auto-restart JAP: " + ex);
            }
        }
    }
    
    public synchronized void changeProxyInterface(final ProxyInterface proxyListener, final boolean b, final Component component) {
        if (proxyListener != null && (JAPController.m_Model.getProxyInterface() == null || !JAPController.m_Model.getProxyInterface().equals(proxyListener))) {
            JAPController.m_Model.setProxyListener(proxyListener);
            this.applyProxySettingsToInfoService(b);
            this.applyProxySettingsToAnonService(component);
            this.notifyJAPObservers();
        }
    }
    
    public boolean saveConfigFile() {
        boolean b = false;
        LogHolder.log(6, LogType.MISC, "Try saving configuration.");
        try {
            final Document configurationAsXmlString = this.getConfigurationAsXmlString();
            if (configurationAsXmlString == null) {
                LogHolder.log(3, LogType.MISC, "Could not transform the configuration to a string.");
                b = true;
            }
            else {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                XMLUtil.write(configurationAsXmlString, byteArrayOutputStream);
                final FileOutputStream fileOutputStream = new FileOutputStream(JAPModel.getInstance().getConfigFile());
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                fileOutputStream.close();
            }
        }
        catch (Throwable t) {
            LogHolder.log(3, LogType.MISC, t);
            b = true;
        }
        return b;
    }
    
    private void addWindowLocationToConf(final Element element, final Point point) {
        if (element != null) {
            final Element element2 = element.getOwnerDocument().createElement("Location");
            element.appendChild(element2);
            XMLUtil.setAttribute(element2, "save", point != null);
            if (point != null) {
                XMLUtil.setAttribute(element2, "x", Integer.toString(point.x));
                XMLUtil.setAttribute(element2, "y", Integer.toString(point.y));
            }
        }
    }
    
    private void addWindowSizeToConf(final Element element, final Dimension dimension, final boolean b) {
        if (element != null) {
            final Element element2 = element.getOwnerDocument().createElement("Size");
            element.appendChild(element2);
            XMLUtil.setAttribute(element2, "save", dimension != null && b);
            if (dimension != null) {
                XMLUtil.setAttribute(element2, "width", Integer.toString(dimension.width));
                XMLUtil.setAttribute(element2, "height", Integer.toString(dimension.height));
            }
        }
    }
    
    private Document getConfigurationAsXmlString() {
        try {
            final Document document = XMLUtil.createDocument();
            final Element element = document.createElement("JAP");
            document.appendChild(element);
            XMLUtil.setAttribute(element, "version", "00.12.005");
            if (JAPController.m_Model.getDllUpdatePath() != null) {
                XMLUtil.setAttribute(element, "dllVersionUpdate", JAPController.m_Model.getDllUpdatePath());
            }
            XMLUtil.setAttribute(element, "macOSXLibNeedsUpdate", JAPController.m_Model.isMacOSXLibraryUpdateAtStartupNeeded());
            XMLUtil.setAttribute(element, "dllWarningVersion", JAPController.m_Model.getDLLWarningVersion());
            XMLUtil.setAttribute(element, "warnInsecureBrowser", this.m_warnNoJonDoFoxHttpListener.isWarningShownOnInsecureBrowser());
            XMLUtil.setAttribute(element, "anonymizedHttpHeaders", JAPModel.getInstance().isAnonymizedHttpHeaders());
            XMLUtil.setAttribute(element, "AllowDirectUpdate", JAPModel.getInstance().getUpdateAnonymousConnectionSetting());
            XMLUtil.setAttribute(element, "remindOptionalUpdate", JAPModel.getInstance().isReminderForOptionalUpdateActivated());
            XMLUtil.setAttribute(element, "remindJavaUpdate", JAPModel.getInstance().isReminderForJavaUpdateActivated());
            XMLUtil.setAttribute(element, "AutoSwitchCascades", JAPModel.getInstance().isCascadeAutoSwitched());
            XMLUtil.setAttribute(element, "autoSwitchCascadesOnStartup", JAPModel.getInstance().isCascadeAutoChosenOnStartup());
            XMLUtil.setAttribute(element, "askForUnprotectedSurfing", JAPModel.getInstance().isAskForAnyNonAnonymousRequest());
            XMLUtil.setAttribute(element, "showConfigAssistant", this.m_bShowConfigAssistant);
            XMLUtil.setAttribute(element, "ShowSplashScreen", JAPController.m_Model.getShowSplashScreen());
            if (JAPController.m_Model.getPortableBrowserpath() != null && (AbstractOS.getInstance().getDefaultBrowserPath() == null || !AbstractOS.toAbsolutePath(AbstractOS.getInstance().getDefaultBrowserPath()).equals(AbstractOS.toAbsolutePath(JAPController.m_Model.getPortableBrowserpath())))) {
                XMLUtil.setAttribute(element, "portableBrowserPath", JAPController.m_Model.getPortableBrowserpath());
            }
            XMLUtil.setAttribute(element, "loginTimeout", AnonClient.getLoginTimeout());
            XMLUtil.setAttribute(element, "isConnectionTimeout", InfoServiceDBEntry.getConnectionTimeout());
            if (JAPModel.getInstance().isHelpPathDefined() && JAPModel.getInstance().isHelpPathChangeable()) {
                XMLUtil.setAttribute(element, "helpPath", XMLUtil.filterXMLChars(JAPModel.getInstance().getHelpPath()));
            }
            try {
                final PayAccountsFile instance = PayAccountsFile.getInstance();
                if (instance != null) {
                    final Element element2 = document.createElement("Payment");
                    XMLUtil.setAttribute(element2, "AllowDirectConnection", JAPModel.getInstance().getPaymentAnonymousConnectionSetting());
                    XMLUtil.setAttribute(element2, "timeout", BIConnection.getConnectionTimeout());
                    XMLUtil.setAttribute(element2, "askIfNotSaved", this.m_bAskSavePayment);
                    element.appendChild(element2);
                    element2.appendChild(Database.getInstance((JAPController.class$anon$pay$PaymentInstanceDBEntry == null) ? (JAPController.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : JAPController.class$anon$pay$PaymentInstanceDBEntry).toXmlElement(document, "PaymentInstances"));
                    element2.appendChild(instance.toXmlElement(document, this.getPaymentPassword()));
                }
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.MISC, "Error saving payment configuration", ex);
                return null;
            }
            XMLUtil.setAttribute(element, "portNumber", JAPModel.getHttpListenerPortNumber());
            XMLUtil.setAttribute(element, "listenerIsLocal", JAPModel.isHttpListenerLocal());
            final ProxyInterface proxyInterface = JAPController.m_Model.getProxyInterface();
            XMLUtil.setAttribute(element, "proxyMode", proxyInterface != null && proxyInterface.isValid());
            if (proxyInterface != null) {
                XMLUtil.setAttribute(element, "proxyType", JAPController.m_Model.getProxyInterface().getProtocolAsString().toUpperCase());
                XMLUtil.setAttribute(element, "proxyHostName", JAPController.m_Model.getProxyInterface().getHost());
                XMLUtil.setAttribute(element, "proxyPortNumber", JAPController.m_Model.getProxyInterface().getPort());
                XMLUtil.setAttribute(element, "proxyAuthorization", JAPController.m_Model.getProxyInterface().isAuthenticationUsed());
                XMLUtil.setAttribute(element, "proxyAuthUserID", JAPController.m_Model.getProxyInterface().getAuthenticationUserID());
            }
            XMLUtil.setAttribute(element, "infoServiceDisabled", JAPModel.isInfoServiceDisabled());
            XMLUtil.setAttribute(element, "infoserviceTimeout", HTTPConnectionFactory.getInstance().getTimeout());
            XMLUtil.setAttribute(element, "DummytrafficInterval", JAPModel.getDummyTraffic());
            XMLUtil.setAttribute(element, "autoconnect", JAPModel.isAutoConnect());
            XMLUtil.setAttribute(element, "autoReconnect", JAPModel.isAutomaticallyReconnected());
            XMLUtil.setAttribute(element, "minimizedStartup", JAPModel.getMinimizeOnStartup());
            XMLUtil.setAttribute(element, "neverRemindActiveContent", this.mbActCntMessageNeverRemind);
            XMLUtil.setAttribute(element, "neverExplainForward", this.m_bForwarderNotExplain);
            XMLUtil.setAttribute(element, "doNotAbuseReminder", this.mbDoNotAbuseReminder);
            XMLUtil.setAttribute(element, "neverRemindGoodBye", JAPModel.getInstance().isNeverRemindGoodbye());
            XMLUtil.setAttribute(element, "Locale", JAPMessages.getLocale().getLanguage());
            final Element element3 = document.createElement("LookAndFeels");
            XMLUtil.setAttribute(element3, "current", JAPModel.getInstance().getLookAndFeel());
            element.appendChild(element3);
            final Vector lookAndFeelFiles = JAPModel.getInstance().getLookAndFeelFiles();
            for (int i = 0; i < lookAndFeelFiles.size(); ++i) {
                final Element element4 = document.createElement("LookAndFeel");
                XMLUtil.setValue(element4, lookAndFeelFiles.elementAt(i).getAbsolutePath());
                element3.appendChild(element4);
            }
            element.appendChild(TrustModel.toXmlElement(document, "TrustModels"));
            final Element element5 = document.createElement("MixCascades");
            element.appendChild(element5);
            final Enumeration entrySnapshotAsEnumeration = Database.getInstance((JAPController.class$anon$infoservice$MixCascade == null) ? (JAPController.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPController.class$anon$infoservice$MixCascade).getEntrySnapshotAsEnumeration();
            while (entrySnapshotAsEnumeration.hasMoreElements()) {
                element5.appendChild(entrySnapshotAsEnumeration.nextElement().toXmlElement(document));
            }
            element.appendChild(Database.getInstance((JAPController.class$anon$infoservice$CascadeIDEntry == null) ? (JAPController.class$anon$infoservice$CascadeIDEntry = class$("anon.infoservice.CascadeIDEntry")) : JAPController.class$anon$infoservice$CascadeIDEntry).toXmlElement(document));
            element.appendChild(Database.getInstance((JAPController.class$anon$infoservice$StatusInfo == null) ? (JAPController.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : JAPController.class$anon$infoservice$StatusInfo).toXmlElement(document));
            element.appendChild(Database.getInstance((JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry == null) ? (JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry = class$("anon.infoservice.PreviouslyKnownCascadeIDEntry")) : JAPController.class$anon$infoservice$PreviouslyKnownCascadeIDEntry).toXmlElement(document));
            final Element xmlElement = Database.getInstance((JAPController.class$anon$infoservice$BlacklistedCascadeIDEntry == null) ? (JAPController.class$anon$infoservice$BlacklistedCascadeIDEntry = class$("anon.infoservice.BlacklistedCascadeIDEntry")) : JAPController.class$anon$infoservice$BlacklistedCascadeIDEntry).toXmlElement(document);
            XMLUtil.setAttribute(xmlElement, "autoBlacklistNewCascades", BlacklistedCascadeIDEntry.areNewCascadesInBlacklist());
            element.appendChild(xmlElement);
            final MixCascade currentMixCascade = this.getCurrentMixCascade();
            if (currentMixCascade != null) {
                element.appendChild(currentMixCascade.toXmlElement(document));
            }
            element.appendChild(Database.getInstance((JAPController.class$anon$infoservice$PerformanceInfo == null) ? (JAPController.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : JAPController.class$anon$infoservice$PerformanceInfo).toXmlElement(document));
            element.appendChild(Database.getInstance((JAPController.class$anon$infoservice$DeletedMessageIDDBEntry == null) ? (JAPController.class$anon$infoservice$DeletedMessageIDDBEntry = class$("anon.infoservice.DeletedMessageIDDBEntry")) : JAPController.class$anon$infoservice$DeletedMessageIDDBEntry).toXmlElement(document));
            element.appendChild(Database.getInstance((JAPController.class$anon$infoservice$ClickedMessageIDDBEntry == null) ? (JAPController.class$anon$infoservice$ClickedMessageIDDBEntry = class$("anon.infoservice.ClickedMessageIDDBEntry")) : JAPController.class$anon$infoservice$ClickedMessageIDDBEntry).toXmlElement(document));
            final Element element6 = document.createElement("GUI");
            element.appendChild(element6);
            XMLUtil.setAttribute(element6, "fontSize", JAPModel.getInstance().getFontSize());
            XMLUtil.setAttribute(element6, "dialogOptFormat", JAPDialog.getOptimizedFormat());
            if (this.m_View instanceof AbstractJAPMainView) {
                ((AbstractJAPMainView)this.m_View).saveWindowPositions();
            }
            final Element element7 = document.createElement("ConfigWindow");
            this.addWindowLocationToConf(element7, JAPModel.getInstance().getConfigWindowLocation());
            this.addWindowSizeToConf(element7, JAPModel.getInstance().getConfigSize(), JAPModel.getInstance().isConfigWindowSizeSaved());
            element6.appendChild(element7);
            final Element element8 = document.createElement("IconifiedWindow");
            XMLUtil.setAttribute(element8, "alwaysOnTop", JAPModel.getInstance().isMiniViewOnTop());
            if (JAPModel.getInstance().getIconifiedSize() != null) {
                final Element element9 = document.createElement("Size");
                XMLUtil.setAttribute(element9, "width", JAPModel.getInstance().getIconifiedSize().width);
                XMLUtil.setAttribute(element9, "height", JAPModel.getInstance().getIconifiedSize().height);
                element8.appendChild(element9);
            }
            this.addWindowLocationToConf(element8, JAPModel.getInstance().getIconifiedWindowLocation());
            element6.appendChild(element8);
            final Element element10 = document.createElement("MainWindow");
            element6.appendChild(element10);
            this.addWindowLocationToConf(element10, JAPModel.getMainWindowLocation());
            if (JAPModel.getMoveToSystrayOnStartup()) {
                final Element element11 = document.createElement("MoveToSystray");
                XMLUtil.setValue(element11, true);
                element10.appendChild(element11);
            }
            if (!JAPModel.getInstance().getStartPortableFirefox()) {
                final Element element12 = document.createElement("StartPortableFirefox");
                XMLUtil.setValue(element12, false);
                element10.appendChild(element12);
            }
            if (JAPModel.getDefaultView() == 2) {
                final Element element13 = document.createElement("DefaultView");
                XMLUtil.setValue(element13, "Simplified");
                element10.appendChild(element13);
            }
            else {
                final Element element14 = document.createElement("DefaultView");
                XMLUtil.setValue(element14, "Normal");
                element10.appendChild(element14);
            }
            final Element element15 = document.createElement("Debug");
            element.appendChild(element15);
            final Element element16 = document.createElement("Level");
            element16.appendChild(document.createTextNode(Integer.toString(JAPDebug.getInstance().getLogLevel())));
            element15.appendChild(element16);
            final Element element17 = document.createElement("Detail");
            XMLUtil.setValue(element17, LogHolder.getDetailLevel());
            element15.appendChild(element17);
            final Element element18 = document.createElement("Type");
            final int logType = JAPDebug.getInstance().getLogType();
            final int[] availableLogTypes = LogType.getAvailableLogTypes();
            for (int j = 1; j < availableLogTypes.length; ++j) {
                XMLUtil.setAttribute(element18, LogType.getLogTypeName(availableLogTypes[j]), (logType & availableLogTypes[j]) != 0x0);
            }
            element15.appendChild(element18);
            if (JAPDebug.isShowConsole() || JAPDebug.isLogToFile()) {
                final Element element19 = document.createElement("Output");
                element15.appendChild(element19);
                if (JAPDebug.isShowConsole()) {
                    XMLUtil.setAttribute(element19, "showWindow", true);
                }
                if (JAPDebug.isLogToFile()) {
                    final Element element20 = document.createElement("File");
                    element19.appendChild(element20);
                    XMLUtil.setValue(element20, JAPDebug.getLogFilename());
                }
            }
            element.appendChild(SignatureVerifier.getInstance().toXmlElement(document));
            final Element xmlElement2 = InfoServiceHolder.getInstance().toXmlElement(document);
            XMLUtil.setAttribute(xmlElement2, "AllowDirectConnection", JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting());
            element.appendChild(xmlElement2);
            final Element element21 = document.createElement("TOR");
            XMLUtil.setAttribute(element21, "activated", JAPModel.getInstance().isTorActivated());
            final Element element22 = document.createElement("MaxConnectionsPerRoute");
            XMLUtil.setValue(element22, JAPModel.getTorMaxConnectionsPerRoute());
            element21.appendChild(element22);
            final Element element23 = document.createElement("RouteLen");
            XMLUtil.setAttribute(element23, "min", JAPModel.getTorMinRouteLen());
            XMLUtil.setAttribute(element23, "max", JAPModel.getTorMaxRouteLen());
            element21.appendChild(element23);
            final Element element24 = document.createElement("PreCreateAnonRoutes");
            XMLUtil.setValue(element24, JAPModel.isPreCreateAnonRoutesEnabled());
            element21.appendChild(element24);
            final Element element25 = document.createElement("DirectoryServer");
            XMLUtil.setAttribute(element25, "useNoneDefault", JAPModel.isTorNoneDefaultDirServerEnabled());
            element21.appendChild(element25);
            element.appendChild(element21);
            try {
                final Element element26 = document.createElement("MixMinion");
                XMLUtil.setAttribute(element26, "activated", JAPModel.getInstance().isMixMinionActivated());
                final Element element27 = document.createElement("RouteLen");
                XMLUtil.setValue(element27, JAPModel.getMixminionRouteLen());
                final Element element28 = document.createElement("MixminionREPLYMail");
                XMLUtil.setAttribute(element28, "MixminionSender", JAPModel.getMixminionMyEMail());
                final Element element29 = document.createElement("MixminionPasswordHash");
                XMLUtil.setValue(element29, Base64.encodeBytes(JAPModel.getMixMinionPasswordHash()));
                final Element element30 = document.createElement("MixminionKeyring");
                XMLUtil.setValue(element30, JAPModel.getMixminionKeyring());
                element26.appendChild(element27);
                element26.appendChild(element28);
                element26.appendChild(element29);
                element26.appendChild(element30);
                element.appendChild(element26);
            }
            catch (Exception ex2) {
                LogHolder.log(2, LogType.MISC, "Error in savin Mixminion settings -- ignoring...", ex2);
            }
            try {
                final Element element31 = document.createElement("Dialog");
                XMLUtil.setValue(element31, JAPModel.getInstance().getDialogVersion());
                element.appendChild(element31);
            }
            catch (Exception ex3) {}
            element.appendChild(JAPModel.getInstance().getRoutingSettings().toXmlElement(document));
            element.appendChild(TermsAndConditions.getAllTermsAndConditionsAsXMLElement(document));
            element.appendChild(Database.getInstance((JAPController.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (JAPController.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : JAPController.class$anon$terms$template$TermsAndConditionsTemplate).toXmlElement(document));
            return document;
        }
        catch (Throwable t) {
            LogHolder.log(2, LogType.MISC, t);
            return null;
        }
    }
    
    public void setMinimizeOnStartup(final boolean minimizeOnStartup) {
        synchronized (this) {
            JAPController.m_Model.setMinimizeOnStartup(minimizeOnStartup);
        }
    }
    
    public void setMoveToSystrayOnStartup(final boolean moveToSystrayOnStartup) {
        synchronized (this) {
            JAPController.m_Model.setMoveToSystrayOnStartup(moveToSystrayOnStartup);
        }
    }
    
    public void setDefaultView(final int defaultView) {
        synchronized (this) {
            JAPController.m_Model.setDefaultView(defaultView);
        }
    }
    
    public MixCascade switchTrustFilter(final TrustModel currentTrustModel) {
        TrustModel.setCurrentTrustModel(currentTrustModel);
        if (!currentTrustModel.isTrusted(this.getCurrentMixCascade())) {
            return this.switchToNextMixCascade();
        }
        return this.getCurrentMixCascade();
    }
    
    public MixCascade switchToNextMixCascade() {
        return this.switchToNextMixCascade(false);
    }
    
    public MixCascade switchToNextMixCascade(final boolean b) {
        final AutoSwitchedMixCascadeContainer autoSwitchedMixCascadeContainer = new AutoSwitchedMixCascadeContainer(true);
        MixCascade currentMixCascade;
        if (b) {
            currentMixCascade = autoSwitchedMixCascadeContainer.getNextRandomCascade();
        }
        else {
            currentMixCascade = autoSwitchedMixCascadeContainer.getNextCascade();
        }
        this.setCurrentMixCascade(currentMixCascade);
        return currentMixCascade;
    }
    
    public void setCurrentMixCascade(final MixCascade currentMixCascade) {
        if (currentMixCascade == null) {
            return;
        }
        if (!this.m_currentMixCascade.equals(currentMixCascade)) {
            synchronized (this) {
                if (this.getAnonMode() && this.m_currentMixCascade != null) {
                    this.connecting(this.m_currentMixCascade = currentMixCascade);
                    LogHolder.log(7, LogType.MISC, "MixCascade changed while in anonymity mode.");
                    this.setAnonMode(true);
                }
                else {
                    this.m_currentMixCascade = currentMixCascade;
                }
            }
            this.notifyJAPObservers();
        }
        else {
            this.m_currentMixCascade = currentMixCascade;
        }
    }
    
    public InetAddress getListenerInetAddress() {
        return (this.m_socketHTTPListener == null) ? null : this.m_socketHTTPListener.getInetAddress();
    }
    
    public int getListenerPort() {
        return (this.m_socketHTTPListener == null) ? -1 : this.m_socketHTTPListener.getLocalPort();
    }
    
    public MixCascade getCurrentMixCascade() {
        return this.m_currentMixCascade;
    }
    
    public void applyProxySettingsToInfoService(final boolean b) {
        if (JAPController.m_Model.getProxyInterface() != null && JAPController.m_Model.getProxyInterface().isValid()) {
            HTTPConnectionFactory.getInstance().setNewProxySettings(JAPController.m_Model.getProxyInterface(), b);
        }
        else {
            HTTPConnectionFactory.getInstance().setNewProxySettings(null, false);
        }
    }
    
    private void applyProxySettingsToAnonService(final Component component) {
        if (JAPModel.getInstance().getProxyInterface() != null && JAPModel.getInstance().getProxyInterface().isValid() && this.getAnonMode() && JAPDialog.showConfirmDialog(component, JAPMessages.getString("reconnectAfterProxyChangeMsg"), JAPMessages.getString("reconnectAfterProxyChangeTitle"), new JAPDialog.Options(0) {
            public String getYesOKText() {
                return JAPMessages.getString("reconnect");
            }
            
            public String getNoText() {
                return JAPMessages.getString("later");
            }
        }, 2, null, null) == 0) {
            this.setAnonMode(false);
            this.setAnonMode(true);
        }
    }
    
    public static String getFirewallAuthPasswd_() {
        return null;
    }
    
    public void setInfoServiceDisabled(final boolean infoServiceDisabled) {
        JAPController.m_Model.setInfoServiceDisabled(infoServiceDisabled);
        synchronized (this) {
            this.setChanged();
            this.notifyObservers(new JAPControllerMessage(1));
        }
    }
    
    public static void setPreCreateAnonRoutes(final boolean preCreateAnonRoutes) {
        JAPController.m_Model.setPreCreateAnonRoutes(preCreateAnonRoutes);
    }
    
    public static void setTorUseNoneDefaultDirServer(final boolean torUseNoneDefaultDirServer) {
        JAPController.m_Model.setTorUseNoneDefaultDirServer(torUseNoneDefaultDirServer);
    }
    
    public boolean isConnecting() {
        return this.m_bConnecting;
    }
    
    public boolean getAnonMode() {
        return this.m_proxyAnon != null;
    }
    
    public boolean isConfigAssistantShown() {
        return this.m_bShowConfigAssistant;
    }
    
    public void setAllowPaidServices(final boolean bAllowPaidServices) {
        this.m_bAllowPaidServices = bAllowPaidServices;
    }
    
    public void setConfigAssistantShown() {
        this.m_bShowConfigAssistant = false;
    }
    
    public MixCascade getConnectedCascade() {
        final AnonProxy proxyAnon = this.m_proxyAnon;
        if (proxyAnon == null) {
            return null;
        }
        final MixCascade currentMixCascade = this.getCurrentMixCascade();
        final MixCascade mixCascade = proxyAnon.getMixCascade();
        if (proxyAnon != null && proxyAnon.isConnected() && currentMixCascade != null && mixCascade != null && mixCascade.equals(currentMixCascade)) {
            return currentMixCascade;
        }
        return null;
    }
    
    public boolean isOperatorOfConnectedMix(final ServiceOperator serviceOperator) {
        final MixCascade connectedCascade = this.getConnectedCascade();
        if (connectedCascade != null) {
            for (int i = 0; i < connectedCascade.getNumberOfMixes(); ++i) {
                if (connectedCascade.getMixInfo(i).getServiceOperator().equals(serviceOperator)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isAnonConnected() {
        final AnonProxy proxyAnon = this.m_proxyAnon;
        if (proxyAnon == null) {
            return false;
        }
        final MixCascade currentMixCascade = this.getCurrentMixCascade();
        final MixCascade mixCascade = proxyAnon.getMixCascade();
        return proxyAnon != null && proxyAnon.isConnected() && currentMixCascade != null && mixCascade != null && mixCascade.equals(currentMixCascade);
    }
    
    public void stopAnonModeWait() {
        synchronized (JAPController.m_Controller.m_finishSync) {
            while (JAPController.m_Controller.getAnonMode() || JAPController.m_Controller.isAnonConnected()) {
                JAPController.m_Controller.setAnonMode(false);
                LogHolder.log(5, LogType.THREAD, "Waiting for finish of AN.ON connection...");
                try {
                    JAPController.m_Controller.m_finishSync.wait(2000L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void setAnonMode(final boolean b) {
        if (!this.m_bShutdown || !b) {
            this.m_anonJobQueue.addJob(new SetAnonModeAsync(b));
        }
    }
    
    public Updater.ObservableInfo getObservableInfo() {
        return this.m_observableInfo;
    }
    
    public void startAnonymousMode(final Component component) {
        if (JAPModel.getInstance().getRoutingSettings().isConnectViaForwarder()) {
            final IAddress userProvidetForwarder = JAPModel.getInstance().getRoutingSettings().getUserProvidetForwarder();
            if (userProvidetForwarder != null) {
                new JAPRoutingEstablishForwardedConnectionDialog(component, userProvidetForwarder);
            }
            else {
                new JAPRoutingEstablishForwardedConnectionDialog(component);
            }
            this.notifyJAPObservers();
        }
        else {
            this.setAnonMode(true);
        }
    }
    
    public void setDummyTraffic(final int dummyTraffic) {
        JAPController.m_Model.setDummyTraffic(dummyTraffic);
        ForwardServerManager.getInstance().setDummyTrafficInterval(dummyTraffic);
        synchronized (this.PROXY_SYNC) {
            if (this.m_proxyAnon != null) {
                this.m_proxyAnon.setDummyTraffic(dummyTraffic);
            }
        }
    }
    
    public static void setTorMaxConnectionsPerRoute(final int torMaxConnectionsPerRoute) {
        JAPController.m_Model.setTorMaxConnectionsPerRoute(torMaxConnectionsPerRoute);
    }
    
    public static void setTorRouteLen(final int torMinRouteLen, final int torMaxRouteLen) {
        JAPController.m_Model.setTorMaxRouteLen(torMaxRouteLen);
        JAPController.m_Model.setTorMinRouteLen(torMinRouteLen);
    }
    
    public static void setMixminionPassword(final String mixMinionPassword) {
        JAPController.m_Model.setMixMinionPassword(mixMinionPassword);
    }
    
    public static void setMixminionPasswordHash(final byte[] mixinionPasswordHash) {
        JAPController.m_Model.setMixinionPasswordHash(mixinionPasswordHash);
    }
    
    public static void resetMixminionPassword() {
        JAPController.m_Model.resetMixMinionKeyringandPw();
    }
    
    public static void setMixminionKeyring(final String mixminionKeyring) {
        JAPController.m_Model.setMixminionKeyring(mixminionKeyring);
    }
    
    public static void setMixminionMessages(final Vector mixminionMessages) {
        JAPController.m_Model.setMixminionMessages(mixminionMessages);
    }
    
    public static void setMixminionMMRList(final MMRList mixminionMMRList) {
        JAPController.m_Model.setMixminionMMRList(mixminionMMRList);
    }
    
    public static void setMixminionFragments(final Vector mixminionFragments) {
        JAPController.m_Model.setMixminionFragments(mixminionFragments);
    }
    
    private ServerSocket intern_startListener(final int n, final String s) {
        LogHolder.log(7, LogType.MISC, "JAPModel:startListener on port: " + n);
        ServerSocket serverSocket = null;
        int i = 0;
        while (i < 10) {
            try {
                if (!JAPModel.isHttpListenerLocal()) {
                    if (s == null) {
                        LogHolder.log(5, LogType.NET, "Try binding Listener on default host.");
                        serverSocket = new ServerSocket(n);
                    }
                    else {
                        InetAddress byName = InetAddress.getAllByName(s)[0];
                        try {
                            if (((JAPController.class$java$net$InetAddress == null) ? (JAPController.class$java$net$InetAddress = class$("java.net.InetAddress")) : JAPController.class$java$net$InetAddress).getMethod("isLoopbackAddress", (Class[])null).invoke(byName, (Object[])null)) {
                                LogHolder.log(4, LogType.NET, "Host is explicitly set, but it is a loopback address!");
                                byName = InetAddress.getByName(null);
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(5, LogType.NET, ex);
                        }
                        LogHolder.log(5, LogType.NET, "Try binding Listener on host: " + byName);
                        serverSocket = new ServerSocket(n, 50, byName);
                    }
                }
                else {
                    if (s != null) {
                        LogHolder.log(4, LogType.NET, "Local listener forced, but host name was given (will be ignored): " + s);
                    }
                    final InetAddress byName2 = InetAddress.getByName(null);
                    LogHolder.log(5, LogType.NET, "Try binding Listener on host: " + byName2);
                    serverSocket = new ServerSocket(n, 50, byName2);
                }
                LogHolder.log(5, LogType.NET, "Started listener on host " + serverSocket.getInetAddress() + " and port " + n + ".");
                break;
            }
            catch (Exception ex2) {
                LogHolder.log(5, LogType.NET, ex2);
                serverSocket = null;
                ++i;
            }
        }
        return serverSocket;
    }
    
    public boolean startHTTPListener(final String s, final int n) {
        if (!this.isRunningHTTPListener) {
            LogHolder.log(7, LogType.NET, "Start HTTP Listener");
            this.m_socketHTTPListener = this.intern_startListener((n <= 0) ? JAPModel.getHttpListenerPortNumber() : n, s);
            this.isRunningHTTPListener = true;
        }
        return this.m_socketHTTPListener != null;
    }
    
    public void showInstallationAssistant() {
        if (this.m_bAssistantClicked) {
            return;
        }
        this.m_bAssistantClicked = true;
        final ConfigAssistant configAssistant = new ConfigAssistant(this.getViewWindow());
        configAssistant.addWindowListener(new WindowAdapter() {
            private final /* synthetic */ JAPDialog val$configAssistant = configAssistant;
            
            public void windowClosed(final WindowEvent windowEvent) {
                this.val$configAssistant.removeWindowListener(this);
                JAPController.this.m_bAssistantClicked = false;
                JAPController.this.getViewWindow().setVisible(true);
            }
        });
        configAssistant.setVisible(true);
    }
    
    public static void goodBye(final boolean b) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                int showConfirmDialog;
                if (!JAPModel.getInstance().isNeverRemindGoodbye() && b && !JAPController.getInstance().isPortableMode() && !JAPController.getInstance().m_restarter.hideWarnings()) {
                    final JAPDialog.LinkedCheckBox linkedCheckBox = new JAPDialog.LinkedCheckBox(false) {
                        public boolean isOnTop() {
                            return true;
                        }
                    };
                    showConfirmDialog = JAPDialog.showConfirmDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAPController.MSG_DISABLE_GOODBYE), 2, 1, linkedCheckBox);
                    if (showConfirmDialog == 0) {
                        JAPModel.getInstance().setNeverRemindGoodbye(linkedCheckBox.getState());
                    }
                }
                else {
                    showConfirmDialog = 0;
                }
                if (showConfirmDialog == 0 && JAPController.getInstance().getViewWindow() != null && JAPController.getInstance().m_bAskSavePayment && b && !JAPController.getInstance().m_restarter.hideWarnings()) {
                    final Enumeration accounts = PayAccountsFile.getInstance().getAccounts();
                    while (accounts.hasMoreElements()) {
                        if (!accounts.nextElement().isBackupDone()) {
                            final JAPDialog.LinkedCheckBox linkedCheckBox2 = new JAPDialog.LinkedCheckBox(false, "payment") {
                                public boolean isOnTop() {
                                    return true;
                                }
                            };
                            if (!JAPDialog.showYesNoDialog(JAPController.getInstance().getViewWindow(), JAPMessages.getString(JAPController.MSG_ACCOUNT_NOT_SAVED), linkedCheckBox2)) {
                                JAPController.getInstance().setAskSavePayment(!linkedCheckBox2.getState());
                                new Thread(new Runnable() {
                                    public void run() {
                                        JAPController.getInstance().m_View.showConfigDialog("PAYMENT_TAB", Boolean.FALSE);
                                    }
                                }).start();
                                return;
                            }
                            JAPController.getInstance().setAskSavePayment(!linkedCheckBox2.getState());
                            break;
                        }
                    }
                }
                if (showConfirmDialog == 0 || JAPDialog.isConsoleOnly()) {
                    if (JAPController.getInstance().getViewWindow() != null) {
                        JAPController.getInstance().getViewWindow().setEnabled(false);
                        final JAPViewIconified viewIconified = JAPController.getInstance().m_View.getViewIconified();
                        if (viewIconified != null) {
                            viewIconified.setEnabled(false);
                        }
                    }
                    JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_SAVING_CONFIG));
                    if (JAPController.getInstance().m_finishSplash instanceof JAPSplash) {
                        if (JAPController.getInstance().getViewWindow() instanceof AbstractJAPMainView && JAPController.getInstance().getViewWindow().isVisible()) {
                            GUIUtils.centerOnWindow((Window)JAPController.getInstance().m_finishSplash, (Window)JAPController.m_Controller.m_View);
                        }
                        else {
                            ((JAPSplash)JAPController.getInstance().m_finishSplash).centerOnScreen();
                        }
                        ((JAPSplash)JAPController.getInstance().m_finishSplash).setVisible(true);
                    }
                    Window viewWindow = JAPController.getInstance().getViewWindow();
                    if (JAPController.getInstance().m_finishSplash instanceof JAPSplash) {
                        viewWindow = (JAPSplash)JAPController.getInstance().m_finishSplash;
                    }
                    final Vector vector = (Vector)JAPController.getInstance().m_programExitListeners.clone();
                    for (int i = 0; i < vector.size(); ++i) {
                        vector.elementAt(i).programExiting();
                    }
                    int showConfirmDialog2 = 1;
                    while (JAPController.m_Controller.m_restarter.isConfigFileSaved() && JAPController.m_Controller.saveConfigFile() && b && !JAPController.getInstance().m_restarter.hideWarnings() && showConfirmDialog2 == 1) {
                        String s = JAPMessages.getString(JAPController.MSG_ERROR_SAVING_CONFIG, JAPModel.getInstance().getConfigFile()) + " " + JAPMessages.getString(JAPController.MSG_NO_WRITING);
                        if (JAPController.getInstance().isPortableMode()) {
                            s = s + "<br><br><b>" + JAPMessages.getString(JAPController.MSG_NO_WRITING_PORTABLE) + "</b>";
                        }
                        showConfirmDialog2 = JAPDialog.showConfirmDialog(viewWindow, s, new JAPDialog.Options(1) {
                            public String getYesOKText() {
                                return JAPMessages.getString(DialogContentPane.MSG_OK);
                            }
                            
                            public String getNoText() {
                                return JAPMessages.getString(JAPDialog.MSG_BTN_RETRY);
                            }
                        }, 0);
                        if (showConfirmDialog2 == 0) {
                            break;
                        }
                        if (showConfirmDialog2 == 2) {
                            if (JAPController.getInstance().getViewWindow() != null) {
                                JAPController.getInstance().getViewWindow().setEnabled(true);
                                final JAPViewIconified viewIconified2 = JAPController.getInstance().m_View.getViewIconified();
                                if (viewIconified2 != null) {
                                    viewIconified2.setEnabled(true);
                                }
                            }
                            if (JAPController.getInstance().m_finishSplash instanceof JAPSplash) {
                                ((JAPSplash)JAPController.getInstance().m_finishSplash).setVisible(false);
                            }
                            return;
                        }
                    }
                    JAPModel.getInstance().setAutoReConnect(false);
                    JAPModel.getInstance().setCascadeAutoSwitch(false);
                    JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_CLOSING_DIALOGS));
                    JAPDialog.setConsoleOnly(true);
                    if (!b) {
                        GUIUtils.setLoadImages(false);
                    }
                    JAPController.m_Controller.m_bShutdown = true;
                    JAPModel.getInstance().setInfoServiceDisabled(true);
                    final Thread thread = new Thread(new Runnable() {
                        public void run() {
                            LogHolder.log(5, LogType.THREAD, "Stopping InfoService auto-update threads...");
                            JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_FINISHING_IS_UPDATES));
                            JAPController.m_feedback.stop();
                            JAPController.m_Controller.m_AccountUpdater.stop();
                            JAPController.m_Controller.m_MixCascadeUpdater.stop();
                            JAPController.m_Controller.m_InfoServiceUpdater.stop();
                            JAPController.m_Controller.m_paymentInstanceUpdater.stop();
                            JAPController.m_Controller.m_minVersionUpdater.stop();
                            JAPController.m_Controller.m_javaVersionUpdater.stop();
                            JAPController.m_Controller.m_messageUpdater.stop();
                            JAPController.m_Controller.m_perfInfoUpdater.stop();
                        }
                    }, "Finish IS threads");
                    thread.start();
                    JAPController.m_Controller.m_proxyCallback = null;
                    DirectProxy.setAllowUnprotectedConnectionCallback(null);
                    final Thread thread2 = new Thread(new Runnable() {
                        public void run() {
                            try {
                                JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_FINISHING_ANON));
                                JAPController.m_Controller.setAnonMode(false);
                                JAPController.m_Controller.stopAnonModeWait();
                                LogHolder.log(5, LogType.THREAD, "Finishing all AN.ON jobs...");
                                JAPController.m_Controller.m_anonJobQueue.stop();
                                JAPController.m_Controller.queueFetchAccountInfo.stop();
                            }
                            catch (Throwable t) {
                                LogHolder.log(0, LogType.MISC, t);
                            }
                        }
                    }, "Finish anon thread");
                    thread2.start();
                    if (JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2) {
                        JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_FINISH_FORWARDING_SERVER));
                        JAPController.getInstance().enableForwardingServer(false);
                    }
                    while (thread.isAlive() || thread2.isAlive()) {
                        try {
                            if (thread.isAlive()) {
                                JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_WAITING_IS));
                            }
                            if (thread2.isAlive()) {
                                JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_WAITING_ANON));
                            }
                            thread.join();
                            thread2.join();
                        }
                        catch (InterruptedException ex2) {}
                    }
                    try {
                        LogHolder.log(5, LogType.THREAD, "Shutting down direct proxy...");
                        JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_STOPPING_PROXY));
                        final DirectProxy access$1500 = JAPController.m_Controller.m_proxyDirect;
                        if (access$1500 != null) {
                            access$1500.shutdown(true);
                        }
                        LogHolder.log(5, LogType.THREAD, "Shutting down direct proxy - Done!");
                    }
                    catch (Exception ex) {
                        LogHolder.log(7, LogType.THREAD, "Shutting down direct proxy - exception", ex);
                    }
                    try {
                        JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_STOPPING_LISTENER));
                        JAPController.m_Controller.m_socketHTTPListener.close();
                    }
                    catch (Exception ex3) {}
                    JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_FINISHING));
                    LogHolder.log(5, LogType.NET, "Interrupting all network communication threads...");
                    ((Hashtable<String, String>)System.getProperties()).put("socksProxyPort", "0");
                    ((Hashtable<String, String>)System.getProperties()).put("socksProxyHost", "localhost");
                    JAPController.getInstance().switchViewWindow(true);
                    if (JAPController.getInstance().getViewWindow() != null) {
                        JAPController.getInstance().getViewWindow().dispose();
                    }
                    if (JAPController.getInstance().m_finishSplash instanceof JAPSplash) {
                        ((JAPSplash)JAPController.m_Controller.m_finishSplash).dispose();
                    }
                    LogHolder.log(6, LogType.GUI, "View has been disposed. Finishing...");
                    if (!b) {
                        JAPController.getInstance().m_finishSplash.setText(JAPMessages.getString(JAPController.MSG_RESTARTING));
                        LogHolder.log(6, LogType.ALL, "Try to restart JAP...");
                        JAPController.m_Controller.restartJAP();
                    }
                    System.exit(0);
                }
            }
        });
        if (!JAPDialog.isConsoleOnly() && SwingUtilities.isEventDispatchThread()) {
            thread.start();
        }
        else {
            thread.run();
        }
    }
    
    public static void aboutJAP() {
        try {
            if (getInstance().m_bPresentationMode) {
                new JAPAbout(getInstance().getViewWindow());
            }
            else {
                new JAPAboutNew(getInstance().getViewWindow()).setVisible(true);
            }
        }
        catch (Throwable t) {
            LogHolder.log(2, LogType.GUI, t);
        }
    }
    
    public boolean updatePaymentInstances(final boolean b) {
        return (b && this.m_paymentInstanceUpdater.isFirstUpdateDone()) || this.m_paymentInstanceUpdater.update();
    }
    
    public boolean updateInfoServices(final boolean b) {
        return (b && this.m_InfoServiceUpdater.isFirstUpdateDone()) || this.m_InfoServiceUpdater.update();
    }
    
    public boolean updatePerformanceInfo(final boolean b) {
        return (b && this.m_perfInfoUpdater.isFirstUpdateDone()) || this.m_perfInfoUpdater.update();
    }
    
    public boolean fetchMixCascades(final boolean b, final boolean b2) {
        if (b2 && this.m_MixCascadeUpdater.isFirstUpdateDone()) {
            return true;
        }
        LogHolder.log(6, LogType.MISC, "Trying to fetch mixcascades from infoservice.");
        while (!this.m_MixCascadeUpdater.update() && !this.m_bExpiredISCertificatesShown) {
            LogHolder.log(3, LogType.NET, "No connection to infoservices.");
            if (!JAPModel.isSmallDisplay() && (b || Database.getInstance((JAPController.class$anon$infoservice$MixCascade == null) ? (JAPController.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPController.class$anon$infoservice$MixCascade).getNumberOfEntries() == 0)) {
                if (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 1 && !this.isAnonConnected()) {
                    if (JAPDialog.showConfirmDialog(this.getCurrentView(), JAPMessages.getString(JAPController.MSG_IS_NOT_ALLOWED), 0, 0) == 0) {
                        JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(0);
                        this.updateInfoServices(false);
                        continue;
                    }
                }
                else if (JAPModel.getInstance().getInfoServiceAnonymousConnectionSetting() == 2 && this.isAnonConnected()) {
                    if (JAPDialog.showConfirmDialog(this.getCurrentView(), JAPMessages.getString(JAPController.MSG_IS_NOT_ALLOWED_FOR_ANONYMOUS), 0, 0) == 0) {
                        JAPModel.getInstance().setInfoServiceAnonymousConnectionSetting(0);
                        this.updateInfoServices(false);
                        continue;
                    }
                }
                else if (this.getCurrentView() != null) {
                    JAPDialog.showErrorDialog(this.getCurrentView(), JAPMessages.getString("errorConnectingInfoService"), LogType.NET);
                }
                else {
                    LogHolder.log(2, LogType.NET, JAPMessages.getString("errorConnectingInfoService"));
                }
            }
            return false;
        }
        return true;
    }
    
    private int versionCheck(final String s, final boolean b) {
        boolean b2 = false;
        String s2;
        if (b) {
            s2 = "mandatory";
        }
        else {
            s2 = "optional";
        }
        LogHolder.log(5, LogType.MISC, "Checking if new " + s2 + " version of JAP is available...");
        Database.getInstance((JAPController.class$anon$infoservice$JAPVersionInfo == null) ? (JAPController.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPController.class$anon$infoservice$JAPVersionInfo).update(InfoServiceHolder.getInstance().getJAPVersionInfo(1));
        Database.getInstance((JAPController.class$anon$infoservice$JAPVersionInfo == null) ? (JAPController.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPController.class$anon$infoservice$JAPVersionInfo).update(InfoServiceHolder.getInstance().getJAPVersionInfo(2));
        JAPVersionInfo japVersionInfo = (JAPVersionInfo)Database.getInstance((JAPController.class$anon$infoservice$JAPVersionInfo == null) ? (JAPController.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPController.class$anon$infoservice$JAPVersionInfo).getEntryById("/japRelease.jnlp");
        final JAPVersionInfo recommendedUpdate = JAPVersionInfo.getRecommendedUpdate("00.12.005", true);
        if (japVersionInfo == null) {
            LogHolder.log(3, LogType.MISC, "Could not get the current JAP version from infoservice.");
            return 1;
        }
        String s3;
        if (recommendedUpdate != null && !japVersionInfo.equals(recommendedUpdate)) {
            b2 = true;
            s3 = recommendedUpdate.getJapVersion();
        }
        else {
            s3 = japVersionInfo.getJapVersion();
        }
        if (s3.compareTo("00.12.005") <= 0) {
            return 0;
        }
        if (!b && !b2 && (this.isConfigAssistantShown() || !JAPModel.getInstance().isReminderForOptionalUpdateActivated())) {
            return 0;
        }
        final String s4 = ")";
        String s5;
        JAPDialog.LinkedInformationAdapter linkedInformationAdapter;
        if (b) {
            s5 = JAPMessages.getString("newVersionAvailable", s3 + s4);
            linkedInformationAdapter = new JAPDialog.LinkedInformationAdapter() {
                public boolean isOnTop() {
                    return true;
                }
            };
        }
        else {
            s5 = JAPMessages.getString(JAPController.MSG_NEW_OPTIONAL_VERSION, s3 + s4);
            try {
                URL url;
                if (japVersionInfo.getId().equals("/japRelease.jnlp") || (recommendedUpdate != null && recommendedUpdate.equals("/japRelease.jnlp"))) {
                    url = new URL(JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG_URL));
                }
                else {
                    url = new URL(JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG_URL_BETA));
                }
                linkedInformationAdapter = new JAPDialog.LinkedURLCheckBox(false, url, JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG));
            }
            catch (MalformedURLException ex) {
                LogHolder.log(1, LogType.GUI, ex);
                linkedInformationAdapter = new JAPDialog.LinkedCheckBox(false);
            }
        }
        JAPDialog.Options options;
        if (b2) {
            s5 = s5 + "<br><br>" + JAPMessages.getString(JAPController.MSG_ASK_WHICH_VERSION);
            options = new JAPDialog.Options(0) {
                public String getYesOKText() {
                    return JAPMessages.getString(JAPController.MSG_VERSION_DEVELOPER);
                }
                
                public String getNoText() {
                    return JAPMessages.getString(JAPController.MSG_VERSION_RELEASE);
                }
            };
        }
        else {
            options = new JAPDialog.Options(2) {
                public String getYesOKText() {
                    return JAPMessages.getString(DialogContentPane.MSG_YES);
                }
                
                public String getCancelText() {
                    return JAPMessages.getString(DialogContentPane.MSG_NO);
                }
            };
        }
        final int showConfirmDialog = JAPDialog.showConfirmDialog(this.getCurrentView(), s5, JAPMessages.getString("newVersionAvailableTitle"), options, 3, linkedInformationAdapter);
        if (linkedInformationAdapter instanceof JAPDialog.LinkedCheckBox) {
            JAPModel.getInstance().setReminderForOptionalUpdate(!((JAPDialog.LinkedCheckBox)linkedInformationAdapter).getState());
        }
        if (showConfirmDialog == 0 || showConfirmDialog == 1) {
            if (showConfirmDialog == 1) {
                japVersionInfo = recommendedUpdate;
            }
            this.saveConfigFile();
            if (new JAPUpdateWizard(japVersionInfo, this.getCurrentView()).getStatus() == -1) {
                LogHolder.log(3, LogType.MISC, "Some update problem.");
                JAPDialog.showErrorDialog(this.getCurrentView(), JAPMessages.getString("downloadFailed") + JAPMessages.getString("infoURL"), LogType.MISC);
                if (b) {
                    this.notifyJAPObservers();
                }
                return -1;
            }
            return 0;
        }
        else {
            if (b) {
                JAPDialog.showWarningDialog(this.getCurrentView(), JAPMessages.getString("youShouldUpdate", JAPMessages.getString(JAPNewView.MSG_UPDATE)), JAPUtil.createDialogBrowserLink(JAPMessages.getString("infoURL")));
                return -1;
            }
            return 0;
        }
    }
    
    public void setView(final IJAPMainView view, final ISplashResponse finishSplash) {
        synchronized (this.SYNC_VIEW) {
            this.m_View = view;
            this.m_finishSplash = finishSplash;
        }
    }
    
    public void switchViewWindow(final boolean bMainView) {
        synchronized (this.SYNC_VIEW) {
            this.m_bMainView = bMainView;
        }
    }
    
    public Component getCurrentView() {
        synchronized (this.SYNC_VIEW) {
            if (this.m_finishSplash != null && this.m_finishSplash instanceof Component && ((Component)this.m_finishSplash).isVisible()) {
                return (Component)this.m_finishSplash;
            }
            final Window viewWindow = this.getViewWindow();
            if (viewWindow instanceof AbstractJAPMainView) {
                return ((AbstractJAPMainView)viewWindow).getCurrentView();
            }
            return viewWindow;
        }
    }
    
    public Window getViewWindow() {
        synchronized (this.SYNC_VIEW) {
            if (!(this.m_View instanceof Window)) {
                return null;
            }
            if (this.m_bMainView) {
                return (Window)this.m_View;
            }
            return this.m_View.getViewIconified();
        }
    }
    
    public void showConfigDialog(final String s, final Object o) {
        if (this.m_View != null && this.m_View instanceof AbstractJAPMainView) {
            ((AbstractJAPMainView)this.m_View).showConfigDialog(s, o);
        }
    }
    
    public final void showConfigDialog() {
        if (this.m_View != null && this.m_View instanceof AbstractJAPMainView) {
            ((AbstractJAPMainView)this.m_View).showConfigDialog();
        }
    }
    
    public void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
        this.m_anonServiceListener.removeElement(anonServiceEventListener);
    }
    
    public void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
        synchronized (this.m_anonServiceListener) {
            final Enumeration<Object> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                if (anonServiceEventListener.equals(elements.nextElement())) {
                    return;
                }
            }
            this.m_anonServiceListener.addElement(anonServiceEventListener);
        }
    }
    
    public void addJAPObserver(final JAPObserver japObserver) {
        this.observerVector.addElement(japObserver);
    }
    
    public void notifyJAPObservers() {
        LogHolder.log(7, LogType.MISC, "JAPModel:notifyJAPObservers()");
        synchronized (this.observerVector) {
            try {
                final Enumeration<JAPObserver> elements = (Enumeration<JAPObserver>)this.observerVector.elements();
                int n = 0;
                while (elements.hasMoreElements()) {
                    final JAPObserver japObserver = elements.nextElement();
                    LogHolder.log(7, LogType.MISC, "JAPModel:notifyJAPObservers: " + n);
                    japObserver.updateValues(false);
                    ++n;
                }
            }
            catch (Throwable t) {
                LogHolder.log(0, LogType.MISC, "JAPModel:notifyJAPObservers - critical exception: " + t.getMessage());
            }
        }
        LogHolder.log(7, LogType.MISC, "JAPModel:notifyJAPObservers()-ended");
    }
    
    public synchronized void channelsChanged(final int n) {
        final Enumeration<JAPObserver> elements = this.observerVector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().channelsChanged(n);
        }
    }
    
    public synchronized void transferedBytes(final long n, final int n2) {
        long n3;
        if (n2 == 1) {
            this.m_nrOfBytesWWW += n;
            n3 = this.m_nrOfBytesWWW;
        }
        else {
            if (n2 != 0) {
                return;
            }
            this.m_nrOfBytesOther += n;
            n3 = this.m_nrOfBytesOther;
        }
        final Enumeration<JAPObserver> elements = (Enumeration<JAPObserver>)this.observerVector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().transferedBytes(n3, n2);
        }
    }
    
    public void update(final Observable observable, final Object o) {
        try {
            if (observable == JAPModel.getInstance().getRoutingSettings()) {
                if (((JAPRoutingMessage)o).getMessageCode() == 1) {
                    this.notifyJAPObservers();
                }
                if (((JAPRoutingMessage)o).getMessageCode() == 16) {
                    this.notifyJAPObservers();
                }
            }
            else if (observable == JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver()) {
                if (((JAPRoutingMessage)o).getMessageCode() == 14) {
                    this.notifyJAPObservers();
                }
            }
            else {
                if (observable == InfoServiceHolder.getInstance()) {
                    final InfoServiceHolderMessage infoServiceHolderMessage = (InfoServiceHolderMessage)o;
                    synchronized (this.SYNC_EXPIRED_IS_CERTS) {
                        if (!this.m_bExpiredISCertificatesShown && infoServiceHolderMessage != null && infoServiceHolderMessage.getMessageData() != null) {
                            this.m_bExpiredISCertificatesShown = true;
                            new Thread(new Runnable() {
                                public void run() {
                                    final JAPDialog.LinkedHelpContext linkedHelpContext = new JAPDialog.LinkedHelpContext("certificates");
                                    if (infoServiceHolderMessage.getMessageData() instanceof ExpiredSignatureException) {
                                        JAPDialog.showWarningDialog(JAPController.this.getCurrentView(), JAPMessages.getString(JAPController.MSG_WARNING_IS_CERTS_EXPIRED), linkedHelpContext);
                                    }
                                    else if (infoServiceHolderMessage.getMessageData() instanceof SignatureException) {
                                        JAPDialog.showWarningDialog(JAPController.this.getCurrentView(), JAPMessages.getString(JAPController.MSG_WARNING_IS_CERTS_INVALID), linkedHelpContext);
                                    }
                                    JAPController.this.m_bExpiredISCertificatesShown = false;
                                }
                            }).start();
                        }
                        return;
                    }
                }
                if (observable == Database.getInstance((JAPController.class$anon$infoservice$JAPMinVersion == null) ? (JAPController.class$anon$infoservice$JAPMinVersion = class$("anon.infoservice.JAPMinVersion")) : JAPController.class$anon$infoservice$JAPMinVersion) && o != null && ((DatabaseMessage)o).getMessageData() instanceof JAPMinVersion) {
                    final String trim = ((JAPMinVersion)((DatabaseMessage)o).getMessageData()).getJapSoftware().getVersion().trim();
                    new Thread(new Runnable() {
                        private final /* synthetic */ boolean val$bForce = trim.compareTo("00.12.005") > 0;
                        
                        public void run() {
                            synchronized (JAPController.this.LOCK_VERSION_UPDATE) {
                                if (JAPController.this.m_bShowingVersionUpdate) {
                                    return;
                                }
                                JAPController.this.m_bShowingVersionUpdate = true;
                            }
                            try {
                                JAPController.this.versionCheck(trim, this.val$bForce);
                            }
                            catch (Throwable t) {
                                LogHolder.log(2, LogType.MISC, t);
                            }
                            synchronized (JAPController.this.LOCK_VERSION_UPDATE) {
                                JAPController.this.m_bShowingVersionUpdate = false;
                            }
                        }
                    }).start();
                }
                else {
                    if (observable == JAPController.m_Model && o != null) {
                        if (!o.equals(JAPModel.CHANGED_ANONYMIZED_HTTP_HEADERS)) {
                            return;
                        }
                        synchronized (this.PROXY_SYNC) {
                            if (this.m_proxyAnon != null) {
                                this.m_proxyAnon.setHTTPHeaderProcessingEnabled(true);
                                this.m_proxyAnon.addHTTPConnectionListener(this.m_smallBalanceWarningListener);
                                this.m_proxyAnon.addHTTPConnectionListener(this.m_warnNoJonDoFoxHttpListener);
                                if (JAPModel.getInstance().isAnonymizedHttpHeaders()) {
                                    this.m_proxyAnon.addHTTPConnectionListener(new JonDoFoxHeader(0));
                                    this.m_proxyAnon.setHTTPDecompressionEnabled(true);
                                }
                                else {
                                    this.m_proxyAnon.removeHTTPConnectionListener(new JonDoFoxHeader(0));
                                    this.m_proxyAnon.setHTTPDecompressionEnabled(false);
                                }
                            }
                            return;
                        }
                    }
                    if (observable == Database.getInstance((JAPController.class$anon$infoservice$PerformanceInfo == null) ? (JAPController.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : JAPController.class$anon$infoservice$PerformanceInfo) && o != null && !o.equals(new Integer(5)) && this.m_bConnectionUnused && JAPModel.getInstance().isCascadeAutoSwitched() && (!TrustModel.getCurrentTrustModel().getAttribute((JAPController.class$anon$client$TrustModel$SpeedAttribute == null) ? (JAPController.class$anon$client$TrustModel$SpeedAttribute = class$("anon.client.TrustModel$SpeedAttribute")) : JAPController.class$anon$client$TrustModel$SpeedAttribute).isTrusted(this.getCurrentMixCascade()) || !TrustModel.getCurrentTrustModel().getAttribute((JAPController.class$anon$client$TrustModel$DelayAttribute == null) ? (JAPController.class$anon$client$TrustModel$DelayAttribute = class$("anon.client.TrustModel$DelayAttribute")) : JAPController.class$anon$client$TrustModel$DelayAttribute).isTrusted(this.getCurrentMixCascade()))) {
                        this.switchToNextMixCascade();
                        LogHolder.log(4, LogType.NET, "Automatically switched service due to bad performance!");
                    }
                }
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.THREAD, ex);
        }
    }
    
    public synchronized boolean enableForwardingServer(final boolean b) {
        if (!this.m_bForwarderNotExplain && b) {
            final JAPDialog.LinkedCheckBox linkedCheckBox = new JAPDialog.LinkedCheckBox(false, "forwarding_server") {
                public boolean isOnTop() {
                    return true;
                }
            };
            if (JAPDialog.showConfirmDialog(getInstance().getCurrentView(), JAPMessages.getString("forwardingExplainMessage"), JAPMessages.getString("forwardingExplainMessageTitle"), new JAPDialog.Options(2), 1, linkedCheckBox) != 0) {
                JAPModel.getInstance().getRoutingSettings().setRoutingMode(0);
                return false;
            }
            this.m_bForwarderNotExplain = linkedCheckBox.getState();
        }
        if (this.m_iStatusPanelMsgIdForwarderServerStatus != -1) {
            this.m_View.removeStatusMsg(this.m_iStatusPanelMsgIdForwarderServerStatus);
            this.m_iStatusPanelMsgIdForwarderServerStatus = -1;
        }
        if (JAPModel.getInstance().getRoutingSettings().getRoutingMode() != 1) {
            if (b) {
                if (JAPModel.getInstance().getRoutingSettings().setRoutingMode(2)) {
                    final Thread thread = new Thread(new Runnable() {
                        public void run() {
                            try {
                                final String string = "<font color='red'>" + JAPMessages.getString(JAPController.MSG_FORWARDER_REGISTRATION_ERROR_HEADER) + "</font><br><br>";
                                final String string2 = "<br><br>" + JAPMessages.getString(JAPController.MSG_FORWARDER_REGISTRATION_ERROR_FOOTER);
                                String s = null;
                                final int addStatusMsg = JAPController.this.m_View.addStatusMsg(JAPMessages.getString("controllerStatusMsgRoutingStartServer"), 1, false);
                                final int startPropaganda = JAPModel.getInstance().getRoutingSettings().startPropaganda(true);
                                JAPController.this.m_View.removeStatusMsg(addStatusMsg);
                                switch (startPropaganda) {
                                    case 1: {
                                        s = string + JAPMessages.getString("settingsRoutingServerRegistrationEmptyListError") + string2;
                                        break;
                                    }
                                    case 2: {
                                        s = string + JAPMessages.getString("settingsRoutingServerRegistrationUnknownError") + string2;
                                        break;
                                    }
                                    case 3: {
                                        s = string + JAPMessages.getString("settingsRoutingServerRegistrationInfoservicesError") + string2;
                                        break;
                                    }
                                    case 4: {
                                        s = string + JAPMessages.getString("settingsRoutingServerRegistrationVerificationError", "<b>" + JAPModel.getInstance().getRoutingSettings().getServerPort() + "</b>") + string2;
                                        break;
                                    }
                                    case 0: {
                                        JAPController.this.m_iStatusPanelMsgIdForwarderServerStatus = JAPController.this.m_View.addStatusMsg(JAPMessages.getString("controllerStatusMsgRoutingStartServerSuccess"), 1, true);
                                        break;
                                    }
                                }
                                if (s != null) {
                                    JAPDialog.showErrorDialog(JAPController.this.getCurrentView(), s, LogType.MISC, new JAPDialog.LinkedHelpContext("forwarding_server"));
                                }
                            }
                            catch (Exception ex) {
                                LogHolder.log(2, LogType.THREAD, ex);
                            }
                        }
                    });
                    thread.setDaemon(true);
                    thread.start();
                }
                else {
                    this.m_iStatusPanelMsgIdForwarderServerStatus = this.m_View.addStatusMsg(JAPMessages.getString("controllerStatusMsgRoutingStartServerError"), 0, true);
                    JAPDialog.showErrorDialog(this.getCurrentView(), JAPMessages.getString("settingsRoutingStartServerError"), LogType.MISC);
                }
            }
            else {
                JAPModel.getInstance().getRoutingSettings().setRoutingMode(0);
                this.m_iStatusPanelMsgIdForwarderServerStatus = this.m_View.addStatusMsg(JAPMessages.getString("controllerStatusMsgRoutingServerStopped"), 1, true);
            }
        }
        return true;
    }
    
    public static InfoServiceDBEntry[] createDefaultInfoServices() throws Exception {
        return Util.createDefaultInfoServices(JAPConstants.DEFAULT_INFOSERVICE_NAMES, JAPConstants.DEFAULT_INFOSERVICE_HOSTNAMES, JAPConstants.DEFAULT_INFOSERVICE_PORT_NUMBERS);
    }
    
    private static void addDefaultCertificates(final String s, final String[] array, final int n) {
        anon.crypto.Util.addDefaultCertificates(s, array, n, ".dev");
    }
    
    public static void addDefaultCertificates() {
        addDefaultCertificates("acceptedMixCAs/", JAPConstants.MIX_ROOT_CERTS, 1);
        addDefaultCertificates("acceptedMixOperators/", null, 2);
        addDefaultCertificates("acceptedInfoServiceCAs/", JAPConstants.INFOSERVICE_ROOT_CERTS, 5);
        addDefaultCertificates("acceptedTaCTemplates/", JAPConstants.TERMS_CERTS, 9);
        addDefaultCertificates("acceptedPaymentCAs/", JAPConstants.PAYMENT_ROOT_CERTS, 8);
        addDefaultCertificates("acceptedPIs/", JAPConstants.PI_CERTS, 7);
        final JAPCertificate instance = JAPCertificate.getInstance(ResourceLoader.loadResource("certificates/japupdatemessages.cer"));
        if (instance != null) {
            SignatureVerifier.getInstance().getVerificationCertificateStore().addCertificateWithoutVerification(instance, 4, true, true);
        }
        else {
            LogHolder.log(3, LogType.MISC, "Error loading default update messages certificate.");
        }
    }
    
    public void connecting(final AnonServerDescription anonServerDescription) {
        if (anonServerDescription instanceof MixCascade && !this.m_currentMixCascade.equals(anonServerDescription)) {
            this.m_currentMixCascade = (MixCascade)anonServerDescription;
            this.setChanged();
            this.notifyObservers(new JAPControllerMessage(2));
            this.notifyJAPObservers();
        }
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().connecting(anonServerDescription);
            }
        }
    }
    
    public void connectionEstablished(final AnonServerDescription anonServerDescription) {
        if (!JAPModel.isInfoServiceDisabled()) {
            JAPController.m_feedback.updateAsync();
        }
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().connectionEstablished(anonServerDescription);
            }
        }
        this.transferedBytes(this.m_lastBalanceUpdateBytes = 0L, 1);
        this.transferedBytes(0L, 0);
        if (this.isPortableMode() && JAPController.m_Model.getStartPortableFirefox() && !this.m_firstPortableFFStart && AbstractOS.getInstance().isDefaultURLAvailable()) {
            LogHolder.log(7, LogType.MISC, "First browser start");
            this.m_firstPortableFFStart = true;
            AbstractOS.getInstance().openBrowser();
        }
    }
    
    public void dataChainErrorSignaled() {
        this.connectionError();
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().dataChainErrorSignaled();
            }
        }
    }
    
    public void disconnected() {
        synchronized (this.m_finishSync) {
            synchronized (this.PROXY_SYNC) {
                if (this.m_proxyAnon != null) {}
                this.m_proxyAnon = null;
                this.m_nrOfBytesWWW = 0L;
                this.transferedBytes(this.m_nrOfBytesOther = 0L, 1);
                this.transferedBytes(0L, 0);
            }
            synchronized (this.m_anonServiceListener) {
                final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().disconnected();
                }
            }
            this.m_finishSync.notifyAll();
        }
    }
    
    public void connectionError() {
        LogHolder.log(3, LogType.NET, "JAPController received connectionError");
        if (!JAPModel.isAutomaticallyReconnected()) {
            this.setAnonMode(false);
        }
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().connectionError();
            }
        }
    }
    
    public String getPaymentPassword() {
        return JAPModel.getInstance().getPaymentPassword();
    }
    
    public void setPaymentPassword(final String paymentPassword) {
        JAPModel.getInstance().setPaymentPassword(paymentPassword);
    }
    
    public void packetMixed(final long n) {
        if (n == 0L) {
            this.m_bConnectionUnused = true;
        }
        else {
            this.m_bConnectionUnused = false;
        }
        this.queueFetchAccountInfo.addJob(new JobQueue.Job() {
            public void runJob() {
                final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
                final MixCascade currentMixCascade = JAPController.this.getCurrentMixCascade();
                if (activeAccount == null || !currentMixCascade.isPayment()) {
                    return;
                }
                if (System.currentTimeMillis() - 60000L <= JAPController.this.m_lastBalanceUpdateMS) {
                    if (n - currentMixCascade.getPrepaidInterval() / 2L <= JAPController.this.m_lastBalanceUpdateBytes) {
                        return;
                    }
                }
                try {
                    activeAccount.fetchAccountInfo(false);
                }
                catch (Exception ex) {
                    if (!JAPController.this.isShuttingDown()) {
                        LogHolder.log(4, LogType.PAY, ex);
                    }
                }
                JAPController.this.m_lastBalanceUpdateMS = System.currentTimeMillis();
                JAPController.this.m_lastBalanceUpdateBytes = n;
            }
        });
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().packetMixed(n);
            }
        }
    }
    
    public boolean confirmTermsAndConditions(final Vector vector, final Vector vector2) {
        final TermsAndConditionsInfoDialog termsAndConditionsInfoDialog = new TermsAndConditionsInfoDialog(getInstance().getViewWindow(), vector, (this.getCurrentMixCascade() != null) ? this.getCurrentMixCascade().getName() : "");
        termsAndConditionsInfoDialog.setVisible(true);
        TermsAndConditionsResponseHandler.get().notifyAboutChanges();
        return termsAndConditionsInfoDialog.areAllAccepted();
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
        MSG_ERROR_SAVING_CONFIG = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_errorSavingConfig";
        MSG_NO_WRITING = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_noWriting";
        MSG_NO_WRITING_PORTABLE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_noWritingPortable";
        MSG_DIALOG_ACCOUNT_PASSWORD = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_dialog_account_password";
        MSG_ACCOUNT_PASSWORD = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_account_password";
        MSG_ENCRYPTACCOUNT = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_encryptaccount";
        MSG_ENCRYPTACCOUNTTITLE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_encryptaccounttitle";
        MSG_ACCPASSWORDTITLE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_accpasswordtitle";
        MSG_ACCPASSWORD = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_accpassword";
        MSG_ACCPASSWORDENTERTITLE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_accpasswordentertitle";
        MSG_ACCPASSWORDENTER = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_accpasswordenter";
        MSG_LOSEACCOUNTDATA = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_loseaccountdata";
        MSG_REPEAT_ENTER_ACCOUNT_PASSWORD = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_repeatEnterAccountPassword";
        MSG_DISABLE_GOODBYE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_disableGoodByMessage";
        MSG_NEW_OPTIONAL_VERSION = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_newOptionalVersion";
        MSG_CASCADE_NOT_TRUSTED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_cascadeNotTrusted";
        MSG_ALLOWUNPROTECTED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_allowunprotected";
        MSG_ALLOWUNPROTECTED_ALL = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_allowunprotectedAll";
        MSG_EXPLAIN_ALLOWUNPROTECTED_ALL = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_allowunprotectedAllExplain";
        MSG_IS_NOT_ALLOWED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_isNotAllowed";
        MSG_IS_NOT_ALLOWED_FOR_ANONYMOUS = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_isNotAllowedForAnonymous";
        MSG_ASK_SWITCH = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_askForSwitchOnError";
        MSG_ASK_RECONNECT = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_askForReconnectOnError";
        MSG_ASK_AUTO_CONNECT = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_reallyAutoConnect";
        MSG_FINISHING = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_finishing";
        MSG_SAVING_CONFIG = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_savingConfig";
        MSG_CLOSING_DIALOGS = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_closingDialogs";
        MSG_FINISHING_IS_UPDATES = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_finishISUpdates";
        MSG_FINISHING_ANON = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_finishAnon";
        MSG_WAITING_IS = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_waitingIS";
        MSG_WAITING_ANON = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_waitingAnon";
        MSG_STOPPING_PROXY = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_stoppingProxy";
        MSG_STOPPING_LISTENER = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_stoppingListener";
        MSG_RESTARTING = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_restarting";
        MSG_FINISH_FORWARDING_SERVER = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_finishForwardingServer";
        MSG_VERSION_RELEASE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_versionRelease";
        MSG_VERSION_DEVELOPER = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_versionDeveloper";
        MSG_ASK_WHICH_VERSION = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_askWhichVersion";
        MSG_CASCADE_NOT_PARSABLE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_cascadeNotParsable";
        MSG_PAYMENT_DAMAGED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_paymentDamaged";
        MSG_ACCOUNT_NOT_SAVED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_accountNotSaved";
        MSG_UPDATING_HELP = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_updatingHelp";
        MSG_FORWARDER_REGISTRATION_ERROR_HEADER = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_forwardErrorHead";
        MSG_FORWARDER_REGISTRATION_ERROR_FOOTER = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_forwardErrorFoot";
        MSG_FORWARDER_REG_ERROR_SHORT = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_forwardErrorShort";
        MSG_READ_NEW_HELP = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_readNewHelp";
        MSG_WARNING_IS_CERTS_EXPIRED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_warningISCertsExpired";
        MSG_WARNING_IS_CERTS_INVALID = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_warningISCertsInvalid";
        MSG_WARNING_INSUFFICIENT_BALANCE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_warningInsufficientBalance";
        MSG_WARNING_SHORT_BALANCE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_warningShortBalance";
        MSG_WARNING_SHORT_BALANCE_CONTINUE = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_warningShortBalanceContinue";
        MSG_WARNING_BROWSER_NOT_OPTIMIZED = ((JAPController.class$jap$JAPController == null) ? (JAPController.class$jap$JAPController = class$("jap.JAPController")) : JAPController.class$jap$JAPController).getName() + "_browserNotOptimized";
        JAPController.m_Controller = null;
        JAPController.m_Model = null;
        JAPController.m_feedback = null;
    }
    
    public class AnonConnectionChecker
    {
        public boolean checkAnonConnected() {
            return JAPController.this.isAnonConnected();
        }
    }
    
    private final class SetAnonModeAsync extends JobQueue.Job
    {
        private boolean m_startServer;
        static /* synthetic */ Class class$anon$infoservice$MixCascade;
        
        public SetAnonModeAsync(final boolean startServer) {
            this.m_startServer = startServer;
        }
        
        public boolean isInterrupting() {
            return !this.m_startServer;
        }
        
        public boolean equals(final Object o) {
            return o instanceof SetAnonModeAsync && o != null && ((SetAnonModeAsync)o).isStartServerJob() == this.isStartServerJob();
        }
        
        public int hashCode() {
            if (this.isStartServerJob()) {
                return 1;
            }
            return 0;
        }
        
        public String getAddedJobLogMessage() {
            return "Added a job for changing the anonymity mode to '" + new Boolean(this.isStartServerJob()).toString() + "' to the job queue.";
        }
        
        public boolean isStartServerJob() {
            return this.m_startServer;
        }
        
        public void runJob() {
            if (!Thread.currentThread().isInterrupted()) {
                try {
                    if (this.m_startServer) {
                        JAPController.this.m_bConnecting = true;
                    }
                    this.setServerMode(this.m_startServer);
                }
                catch (Throwable t) {
                    LogHolder.log(2, LogType.NET, "Error while setting server mode to " + this.m_startServer + "!", t);
                }
                JAPController.this.m_bConnecting = false;
                LogHolder.log(7, LogType.MISC, "Job for changing the anonymity mode to '" + new Boolean(this.m_startServer).toString() + "' was executed.");
            }
        }
        
        private synchronized void setServerMode(final boolean b) {
            int addStatusMsg = 0;
            if (!b) {
                if (JAPController.this.m_proxyDirect == null) {
                    addStatusMsg = JAPController.this.m_View.addStatusMsg(JAPMessages.getString("setAnonModeSplashDisconnect"), 1, false);
                }
                try {
                    JAPController.this.m_proxyAnon.stop();
                }
                catch (NullPointerException ex) {}
                if (addStatusMsg != 0) {
                    JAPController.this.m_View.removeStatusMsg(addStatusMsg);
                }
            }
            synchronized (JAPController.this.PROXY_SYNC) {
                boolean b2 = true;
                LogHolder.log(7, LogType.MISC, "setAnonMode(" + b + ")");
                if (b && (JAPController.this.m_proxyAnon == null || !JAPController.this.m_proxyAnon.getMixCascade().equals(JAPController.this.getCurrentMixCascade()))) {
                    final boolean b3 = JAPController.this.m_proxyAnon != null;
                    final int addStatusMsg2 = JAPController.this.m_View.addStatusMsg(JAPMessages.getString("setAnonModeSplashConnect"), 1, false);
                    boolean b4 = false;
                    if (JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 1) {
                        b4 = true;
                        JAPController.this.m_proxyAnon = JAPModel.getInstance().getRoutingSettings().getAnonProxyInstance(JAPController.this.m_socketHTTPListener);
                    }
                    else if (!b3) {
                        JAPController.this.m_proxyAnon = new AnonProxy(JAPController.this.m_socketHTTPListener, JAPModel.getInstance().getMutableProxyInterface(), JAPController.getInstance());
                    }
                    JAPController.this.m_proxyAnon.setHTTPHeaderProcessingEnabled(true);
                    JAPController.this.m_proxyAnon.addHTTPConnectionListener(JAPController.this.m_smallBalanceWarningListener);
                    JAPController.this.m_proxyAnon.addHTTPConnectionListener(JAPController.this.m_warnNoJonDoFoxHttpListener);
                    if (JAPModel.getInstance().isAnonymizedHttpHeaders()) {
                        JAPController.this.m_proxyAnon.addHTTPConnectionListener(new JonDoFoxHeader(0));
                        JAPController.this.m_proxyAnon.setHTTPDecompressionEnabled(true);
                    }
                    else {
                        JAPController.this.m_proxyAnon.removeHTTPConnectionListener(new JonDoFoxHeader(0));
                        JAPController.this.m_proxyAnon.setHTTPDecompressionEnabled(false);
                    }
                    if (!JAPModel.isInfoServiceDisabled()) {
                        JAPController.m_feedback.updateAsync();
                    }
                    JAPController.this.m_proxyAnon.addEventListener(JAPController.getInstance());
                    final AutoSwitchedMixCascadeContainer autoSwitchedMixCascadeContainer = new AutoSwitchedMixCascadeContainer();
                    if (!b3) {
                        if (!JAPModel.getInstance().isTorActivated() || !b4) {}
                        JAPController.this.m_proxyAnon.setTorParams(null);
                        if (!JAPModel.getInstance().isMixMinionActivated() || !b4) {}
                        JAPController.this.m_proxyAnon.setMixminionParams(null);
                        JAPController.this.m_proxyAnon.setProxyListener(JAPController.m_Controller);
                        JAPController.this.m_proxyAnon.setDummyTraffic(JAPModel.getDummyTraffic());
                        LogHolder.log(7, LogType.NET, "Try to start AN.ON service...");
                    }
                    if (JAPController.this.m_proxyDirect != null) {
                        JAPController.this.m_proxyDirect.shutdown(true);
                    }
                    JAPController.this.m_proxyDirect = null;
                    final int start = JAPController.this.m_proxyAnon.start(autoSwitchedMixCascadeContainer);
                    final JAPDialog.LinkedInformationAdapter linkedInformationAdapter = new JAPDialog.LinkedInformationAdapter() {
                        public boolean isOnTop() {
                            return true;
                        }
                    };
                    if (start == -2) {
                        b2 = false;
                        JAPController.this.m_proxyAnon.stop();
                        JAPController.this.m_proxyAnon = null;
                        JAPDialog.showErrorDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString("errorListenerPort", new Object[] { new Integer(JAPModel.getHttpListenerPortNumber()) }) + "<br><br>" + JAPMessages.getString(JAPConf.MSG_READ_PANEL_HELP, new Object[] { JAPMessages.getString("confButton"), JAPMessages.getString("confListenerTab") }), LogType.NET, new JAPDialog.LinkedHelpContext("portlistener") {
                            public boolean isOnTop() {
                                return true;
                            }
                        });
                        if (JAPController.this.m_View != null) {
                            JAPController.this.m_View.disableSetAnonMode();
                        }
                    }
                    else if ((start == -10 || start == -22 || start == -23 || start == -26 || start == -27) && (!autoSwitchedMixCascadeContainer.isReconnectedAutomatically() || !autoSwitchedMixCascadeContainer.isServiceAutoSwitched())) {
                        b2 = false;
                        JAPController.this.m_proxyAnon.stop();
                        JAPController.this.m_proxyAnon = null;
                        String s;
                        if (start == -10) {
                            s = JAPMessages.getString("errorMixProtocolNotSupported");
                        }
                        else if (start == -22) {
                            s = JAPMessages.getString("errorMixFirstMixSigCheckFailed");
                        }
                        else if (start == -23) {
                            s = JAPMessages.getString("errorMixOtherMixSigCheckFailed");
                        }
                        else if (start == -26) {
                            s = JAPMessages.getString(JAPController.MSG_CASCADE_NOT_TRUSTED);
                        }
                        else {
                            s = JAPMessages.getString(JAPController.MSG_CASCADE_NOT_PARSABLE);
                        }
                        if (JAPDialog.showConfirmDialog(JAPController.this.getCurrentView(), s + "<br><br>" + JAPMessages.getString(JAPController.MSG_ASK_SWITCH), 0, 0, linkedInformationAdapter) == 0) {
                            JAPModel.getInstance().setAutoReConnect(true);
                            JAPModel.getInstance().setCascadeAutoSwitch(true);
                        }
                        else if (JAPController.this.m_View != null) {
                            JAPController.this.m_View.doClickOnCascadeChooser();
                        }
                    }
                    else if (start == 0 || (start != -24 && autoSwitchedMixCascadeContainer.isReconnectedAutomatically())) {
                        final AnonProxy access$1600 = JAPController.this.m_proxyAnon;
                        final AnonServiceEventAdapter anonServiceEventAdapter = new AnonServiceEventAdapter() {
                            boolean bWaitingForConnection = true;
                            
                            public synchronized void connectionEstablished(final AnonServerDescription anonServerDescription) {
                                if (this.bWaitingForConnection) {
                                    JAPController.getInstance().removeEventListener(this);
                                    this.bWaitingForConnection = false;
                                }
                            }
                        };
                        if (start == 0) {
                            LogHolder.log(7, LogType.NET, "AN.ON service started successfully");
                            anonServiceEventAdapter.connectionEstablished(access$1600.getMixCascade());
                        }
                        else {
                            JAPController.getInstance().addEventListener(anonServiceEventAdapter);
                            LogHolder.log(6, LogType.NET, "AN.ON service not connected. Trying reconnect...");
                        }
                        if (!JAPModel.isInfoServiceDisabled()) {
                            JAPController.m_feedback.updateAsync();
                        }
                    }
                    else {
                        b2 = false;
                        JAPController.this.m_proxyAnon.stop();
                        JAPController.this.m_proxyAnon = null;
                        if (!JAPModel.isSmallDisplay() && start != -24) {
                            LogHolder.log(3, LogType.NET, "Error starting AN.ON service! - ErrorCode: " + Integer.toString(start));
                            if (JAPDialog.showConfirmDialog(JAPController.this.getCurrentView(), JAPMessages.getString("errorConnectingFirstMix") + "<br><br>" + JAPMessages.getString(JAPController.MSG_ASK_RECONNECT), JAPMessages.getString("errorConnectingFirstMixTitle"), 0, 0, linkedInformationAdapter) == 0) {
                                JAPModel.getInstance().setAutoReConnect(true);
                            }
                        }
                    }
                    JAPController.this.notifyJAPObservers();
                    JAPController.this.m_View.removeStatusMsg(addStatusMsg2);
                    if (!b2 && (start != -24 || (start == -24 && autoSwitchedMixCascadeContainer.getInitialCascade().equals(JAPController.getInstance().getCurrentMixCascade())))) {
                        JAPController.this.setAnonMode(false);
                    }
                    if (b2 && !JAPModel.isInfoServiceDisabled()) {
                        MixCascade mixCascade = null;
                        try {
                            mixCascade = JAPController.this.m_proxyAnon.getMixCascade();
                        }
                        catch (NullPointerException ex2) {}
                        if (!JAPModel.isInfoServiceDisabled() && mixCascade != null && !mixCascade.isUserDefined() && (mixCascade.isFromCascade() || Database.getInstance((SetAnonModeAsync.class$anon$infoservice$MixCascade == null) ? (SetAnonModeAsync.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : SetAnonModeAsync.class$anon$infoservice$MixCascade).getEntryById(mixCascade.getId()) == null)) {
                            Database.getInstance((SetAnonModeAsync.class$anon$infoservice$MixCascade == null) ? (SetAnonModeAsync.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : SetAnonModeAsync.class$anon$infoservice$MixCascade).update(InfoServiceHolder.getInstance().getMixCascadeInfo(mixCascade.getId()));
                        }
                    }
                }
                else if (JAPController.this.m_proxyDirect == null && !b) {
                    final AnonProxy access$1601 = JAPController.this.m_proxyAnon;
                    if (access$1601 != null) {
                        final int addStatusMsg3 = JAPController.this.m_View.addStatusMsg(JAPMessages.getString("setAnonModeSplashDisconnect"), 1, false);
                        access$1601.stop();
                        JAPController.this.m_View.removeStatusMsg(addStatusMsg3);
                    }
                    synchronized (JAPController.this.m_finishSync) {
                        JAPController.this.m_proxyAnon = null;
                        JAPController.this.m_finishSync.notifyAll();
                    }
                    if (!JAPController.this.isShuttingDown()) {
                        JAPController.this.m_proxyDirect = new DirectProxy(JAPController.this.m_socketHTTPListener);
                        DirectProxy.setAllowUnprotectedConnectionCallback(null);
                        JAPController.this.m_proxyDirect.startService();
                        try {
                            Thread.sleep(300L);
                        }
                        catch (InterruptedException ex3) {}
                        DirectProxy.setAllowUnprotectedConnectionCallback(JAPController.this.m_proxyCallback);
                    }
                    JAPModel.getInstance().getRoutingSettings().anonConnectionClosed();
                    JAPController.this.notifyJAPObservers();
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
    }
    
    private class AutoSwitchedMixCascadeContainer extends AbstractAutoSwitchedMixCascadeContainer
    {
        public AutoSwitchedMixCascadeContainer(final boolean b) {
            super(b, JAPController.getInstance().getCurrentMixCascade());
        }
        
        public AutoSwitchedMixCascadeContainer(final JAPController japController) {
            this(japController, false);
        }
        
        public boolean isPaidServiceAllowed() {
            return !JAPController.this.isConfigAssistantShown() && JAPController.this.m_bAllowPaidServices;
        }
        
        public boolean isServiceAutoSwitched() {
            return JAPModel.getInstance().isCascadeAutoSwitched();
        }
        
        public boolean isReconnectedAutomatically() {
            return JAPModel.isAutomaticallyReconnected();
        }
    }
    
    private class WarnSmallBalanceOnDownloadListener extends HttpConnectionListenerAdapter
    {
        private final /* synthetic */ JAPController this$0;
        
        public WarnSmallBalanceOnDownloadListener(final int n) {
            super(n);
        }
        
        public void responseHeadersReceived(final HTTPConnectionEvent httpConnectionEvent) {
            if (JAPDialog.isConsoleOnly() || httpConnectionEvent == null || httpConnectionEvent.getConnectionHeader() == null) {
                return;
            }
            final String[] responseHeader = httpConnectionEvent.getConnectionHeader().getResponseHeader("Content-Length");
            if (responseHeader == null || responseHeader.length == 0) {
                return;
            }
            long long1;
            try {
                long1 = Long.parseLong(responseHeader[0]);
            }
            catch (NumberFormatException ex) {
                LogHolder.log(4, LogType.FILTER, ex);
                return;
            }
            final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
            if (activeAccount == null) {
                return;
            }
            if (long1 > 10000000L && JAPController.this.isAnonConnected() && JAPController.this.getCurrentMixCascade().isPayment()) {
                final JAPDialog.LinkedInformationAdapter linkedInformationAdapter = new JAPDialog.LinkedInformationAdapter() {
                    public boolean isOnTop() {
                        return true;
                    }
                };
                final JAPDialog.Options options = new JAPDialog.Options(2) {
                    public String getYesOKText() {
                        return JAPMessages.getString(JAPController.MSG_WARNING_SHORT_BALANCE_CONTINUE);
                    }
                };
                int n = 0;
                final long n2 = long1;
                if (long1 * 1.1 > activeAccount.getCurrentCredit() + JAPController.this.getCurrentMixCascade().getPrepaidInterval()) {
                    LogHolder.log(4, LogType.PAY, "Insufficient balance for downloading file!");
                    final IReturnRunnable returnRunnable = new IReturnRunnable() {
                        private Integer m_retVal = null;
                        private final /* synthetic */ JAPDialog.Options val$options = options;
                        private final /* synthetic */ JAPDialog.LinkedInformationAdapter val$adapter = linkedInformationAdapter;
                        private final /* synthetic */ WarnSmallBalanceOnDownloadListener this$1 = this$1;
                        
                        public void run() {
                            this.m_retVal = new Integer(JAPDialog.showConfirmDialog(this.this$1.this$0.getCurrentView(), JAPMessages.getString(JAPController.MSG_WARNING_INSUFFICIENT_BALANCE, Util.formatBytesValueWithUnit(n2)), this.val$options, 2, this.val$adapter));
                        }
                        
                        public Object getValue() {
                            return this.m_retVal;
                        }
                    };
                    final Thread thread = new Thread(returnRunnable);
                    thread.start();
                    try {
                        thread.join(10000L);
                    }
                    catch (InterruptedException ex2) {}
                    if (returnRunnable.getValue() == null) {
                        while (thread.isAlive()) {
                            thread.interrupt();
                            try {
                                thread.join(200L);
                            }
                            catch (InterruptedException ex3) {}
                            Thread.yield();
                        }
                        n = 2;
                    }
                    else {
                        n = (int)returnRunnable.getValue();
                    }
                }
                else if (long1 * 1.3 > activeAccount.getCurrentCredit()) {
                    LogHolder.log(4, LogType.PAY, "Balance might be insufficient balance for downloading file.");
                    final IReturnRunnable returnRunnable2 = new IReturnRunnable() {
                        private Integer m_retVal = null;
                        private final /* synthetic */ JAPDialog.Options val$options = options;
                        private final /* synthetic */ JAPDialog.LinkedInformationAdapter val$adapter = linkedInformationAdapter;
                        private final /* synthetic */ WarnSmallBalanceOnDownloadListener this$1 = this$1;
                        
                        public void run() {
                            this.m_retVal = new Integer(JAPDialog.showConfirmDialog(this.this$1.this$0.getCurrentView(), JAPMessages.getString(JAPController.MSG_WARNING_SHORT_BALANCE, Util.formatBytesValueWithUnit(n2)), this.val$options, 2, this.val$adapter));
                        }
                        
                        public Object getValue() {
                            return this.m_retVal;
                        }
                    };
                    final Thread thread2 = new Thread(returnRunnable2);
                    thread2.start();
                    try {
                        thread2.join(60000L);
                    }
                    catch (InterruptedException ex4) {}
                    if (returnRunnable2.getValue() == null) {
                        while (thread2.isAlive()) {
                            thread2.interrupt();
                            try {
                                thread2.join(200L);
                            }
                            catch (InterruptedException ex5) {}
                            Thread.yield();
                        }
                        n = 0;
                    }
                    else {
                        n = (int)returnRunnable2.getValue();
                    }
                }
                if (n != 0) {
                    httpConnectionEvent.getConnectionHeader().replaceResponseHeader("Connection", "close");
                    httpConnectionEvent.getConnectionHeader().replaceResponseHeader("Content-Length", "0");
                    if (httpConnectionEvent.getConnectionHeader().getResponseLine() != null && httpConnectionEvent.getConnectionHeader().getResponseLine().indexOf("HTTP/1.1") >= 0) {
                        httpConnectionEvent.getConnectionHeader().replaceResponseLine("HTTP/1.1 204 No Content");
                    }
                    else {
                        httpConnectionEvent.getConnectionHeader().replaceResponseLine("HTTP/1.0 204 No Content");
                    }
                }
            }
        }
    }
    
    private static class WarnNoJonDoFoxHttpListener extends HttpConnectionListenerAdapter implements IBrowserIdentification
    {
        public static final int BROWSER_RECOGNITION_UNINITIALISED = -1;
        public static final int BROWSER_UNKNOWN = 0;
        public static final int BROWSER_TORBUTTON = 1;
        public static final int BROWSER_JONDOFOX = 2;
        public static final int BROWSER_INTERNET_EXPLORER = 3;
        public static final int BROWSER_FIREFOX = 4;
        public static final int BROWSER_OPERA = 5;
        public static final int BROWSER_SAFARI = 6;
        public static final int BROWSER_KONQUEROR = 7;
        public static final int BROWSER_CHROME = 8;
        private static final long[] BROWSER_OCCURENCE;
        private static final String[] BROWSER_NAME;
        private static boolean ms_bWarned;
        private static boolean ms_bShowWarning;
        
        public WarnNoJonDoFoxHttpListener(final int n, final boolean ms_bShowWarning) {
            super(n);
            WarnNoJonDoFoxHttpListener.ms_bWarned = !ms_bShowWarning;
            WarnNoJonDoFoxHttpListener.ms_bShowWarning = ms_bShowWarning;
            InfoServiceDBEntry.setBrowserIdentification(this);
        }
        
        public int getMostFrequentBrowser() {
            int n = -1;
            long n2 = 0L;
            for (int i = 0; i < WarnNoJonDoFoxHttpListener.BROWSER_OCCURENCE.length; ++i) {
                if (WarnNoJonDoFoxHttpListener.BROWSER_OCCURENCE[i] > n2) {
                    n2 = WarnNoJonDoFoxHttpListener.BROWSER_OCCURENCE[i];
                    n = i;
                }
            }
            return n;
        }
        
        public String getBrowserName() {
            final int mostFrequentBrowser = this.getMostFrequentBrowser();
            if (mostFrequentBrowser == -1) {
                return null;
            }
            return WarnNoJonDoFoxHttpListener.BROWSER_NAME[mostFrequentBrowser];
        }
        
        public boolean isWarningShownOnInsecureBrowser() {
            return WarnNoJonDoFoxHttpListener.ms_bShowWarning;
        }
        
        public void requestHeadersReceived(final HTTPConnectionEvent httpConnectionEvent) {
            if (!httpConnectionEvent.getConnectionHeader().getRequestLine().startsWith("CONNECT")) {
                int n = -1;
                final String[] requestHeader = httpConnectionEvent.getConnectionHeader().getRequestHeader("User-Agent");
                if (requestHeader != null && requestHeader.length > 0) {
                    final String lowerCase = requestHeader[0].toLowerCase();
                    if (lowerCase.indexOf("firefox") >= 0) {
                        if (requestHeader[0].equals("Mozilla/5.0 (en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2") || requestHeader[0].equals("Mozilla/5.0 (en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7")) {
                            n = 2;
                        }
                        else if (requestHeader[0].equals("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7") || requestHeader[0].equals("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.16) Gecko/20080702 Firefox/2.0.0.16")) {
                            n = 1;
                        }
                        else {
                            n = 4;
                        }
                    }
                    else if (lowerCase.indexOf("msie") >= 0) {
                        n = 3;
                    }
                    else if (lowerCase.indexOf("opera") >= 0) {
                        n = 5;
                    }
                    else if (lowerCase.indexOf("konqueror") >= 0) {
                        n = 7;
                    }
                    else if (lowerCase.indexOf("safari") >= 0) {
                        n = 6;
                    }
                    else if (lowerCase.indexOf("chrome") >= 0) {
                        n = 8;
                    }
                    else if (lowerCase.indexOf("httpclient") < 0) {
                        n = 0;
                    }
                    if (n > -1) {
                        final long[] browser_OCCURENCE = WarnNoJonDoFoxHttpListener.BROWSER_OCCURENCE;
                        final int n2 = n;
                        ++browser_OCCURENCE[n2];
                    }
                }
                if (!WarnNoJonDoFoxHttpListener.ms_bWarned && WarnNoJonDoFoxHttpListener.BROWSER_OCCURENCE[2] == 0L && n > 2 && WarnNoJonDoFoxHttpListener.BROWSER_OCCURENCE[n] > 200L) {
                    WarnNoJonDoFoxHttpListener.ms_bWarned = true;
                    new Thread(new Runnable() {
                        public void run() {
                            final JAPDialog.LinkedCheckBox linkedCheckBox = new JAPDialog.LinkedCheckBox(false, "jondofox") {
                                public boolean isOnTop() {
                                    return true;
                                }
                            };
                            JAPDialog.showWarningDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAPController.MSG_WARNING_BROWSER_NOT_OPTIMIZED), linkedCheckBox);
                            if (linkedCheckBox.getState()) {
                                WarnNoJonDoFoxHttpListener.ms_bShowWarning = false;
                            }
                        }
                    }).start();
                }
            }
        }
        
        static {
            BROWSER_OCCURENCE = new long[9];
            BROWSER_NAME = new String[] { "other", "Tor", "JonDoFox", "Internet Explorer", "Firefox", "Opera", "Safari", "Konqueror", "Chrome" };
        }
    }
    
    public interface IRestarter
    {
        void exec(final String[] p0) throws IOException;
        
        boolean isConfigFileSaved();
        
        boolean hideWarnings();
    }
    
    public interface ProgramExitListener
    {
        void programExiting();
    }
}
