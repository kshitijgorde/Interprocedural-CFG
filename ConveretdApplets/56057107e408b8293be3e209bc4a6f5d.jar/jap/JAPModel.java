// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.client.TrustModel;
import java.net.UnknownHostException;
import java.net.InetAddress;
import anon.util.Util;
import anon.util.JAPMessages;
import anon.util.RecursiveFileTool;
import java.net.MalformedURLException;
import logging.LogHolder;
import logging.LogType;
import java.net.URL;
import platform.AbstractOS;
import gui.JAPDll;
import gui.dialog.JAPDialog;
import anon.util.IPasswordReader;
import java.io.File;
import gui.help.LocalHelpFileStorageManager;
import anon.util.ClassUtil;
import anon.infoservice.ImmutableProxyInterface;
import anon.infoservice.IProxyInterfaceGetter;
import anon.util.ResourceLoader;
import java.util.Hashtable;
import gui.help.AbstractHelpFileStorageManager;
import java.math.BigInteger;
import jap.forward.JAPRoutingSettings;
import java.awt.Dimension;
import anon.mixminion.mmrdescription.MMRList;
import anon.crypto.JAPCertificate;
import gui.GUIUtils;
import javax.swing.UIManager;
import java.util.Vector;
import java.awt.Point;
import anon.infoservice.IMutableProxyInterface;
import anon.infoservice.ProxyInterface;
import anon.infoservice.IServiceContextContainer;
import gui.help.IHelpModel;
import java.util.Observable;

public final class JAPModel extends Observable implements IHelpModel, IServiceContextContainer
{
    public static final String MACOSX_LIB_NEEDS_UPDATE = "macOSXLibNeedsUpdate";
    public static final String DLL_VERSION_UPDATE = "dllVersionUpdate";
    public static final String DLL_VERSION_WARNING_BELOW = "dllWarningVersion";
    public static final int CONNECTION_ALLOW_ANONYMOUS = 0;
    public static final int CONNECTION_FORCE_ANONYMOUS = 1;
    public static final int CONNECTION_BLOCK_ANONYMOUS = 2;
    public static final String XML_ANONYMIZED_HTTP_HEADERS = "anonymizedHttpHeaders";
    public static final String XML_REMIND_OPTIONAL_UPDATE = "remindOptionalUpdate";
    public static final String XML_REMIND_JAVA_UPDATE = "remindJavaUpdate";
    public static final String XML_RESTRICT_CASCADE_AUTO_CHANGE = "restrictCascadeAutoChange";
    public static final String XML_ASK_FOR_NON_ANONYMOUS_SURFING = "askForUnprotectedSurfing";
    public static final String XML_ATTR_ACTIVATED = "activated";
    public static final String XML_FONT_SIZE = "fontSize";
    public static final String XML_CONFIG_WINDOW = "ConfigWindow";
    public static final String XML_SIZE = "Size";
    public static final String XML_ICONIFIED_WINDOW = "IconifiedWindow";
    public static final String XML_ATTR_ICONIFIED_ON_TOP = "alwaysOnTop";
    public static final String XML_HELP_WINDOW = "HelpWindow";
    public static final String XML_ATTR_WIDTH = "width";
    public static final String XML_ATTR_HEIGHT = "height";
    public static final String XML_ATTR_SAVE = "save";
    public static final String AUTO_CHANGE_NO_RESTRICTION = "none";
    public static final String AUTO_CHANGE_RESTRICT_TO_PAY = "pay";
    public static final String AUTO_CHANGE_RESTRICT = "restrict";
    public static final String NO_HELP_STORAGE_MANAGER = "help_internal";
    public static final int MAX_FONT_SIZE = 3;
    public static final Integer CHANGED_INFOSERVICE_AUTO_UPDATE;
    public static final Integer CHANGED_ALLOW_INFOSERVICE_DIRECT_CONNECTION;
    public static final Integer CHANGED_ALLOW_UPDATE_DIRECT_CONNECTION;
    public static final Integer CHANGED_NOTIFY_JAP_UPDATES;
    public static final Integer CHANGED_NOTIFY_JAVA_UPDATES;
    public static final Integer CHANGED_AUTO_CONNECT;
    public static final Integer CHANGED_AUTO_RECONNECT;
    public static final Integer CHANGED_CASCADE_AUTO_CHANGE;
    public static final Integer CHANGED_ASK_FOR_NON_ANONYMOUS;
    public static final Integer CHANGED_HELP_PATH;
    public static final Integer CHANGED_DLL_UPDATE;
    public static final Integer CHANGED_MACOSX_LIBRARY_UPDATE;
    public static final Integer CHANGED_ANONYMIZED_HTTP_HEADERS;
    public static final Integer CHANGED_CONTEXT;
    private static final String[] MSG_CONNECTION_ANONYMOUS;
    private static final int DIRECT_CONNECTION_INFOSERVICE = 0;
    private static final int DIRECT_CONNECTION_PAYMENT = 1;
    private static final int DIRECT_CONNECTION_UPDATE = 2;
    private int m_HttpListenerPortNumber;
    private boolean m_bHttpListenerIsLocal;
    private ProxyInterface m_proxyInterface;
    private ProxyInterface m_proxyAnon;
    private final Object SYNC_ANON_PROXY;
    private IMutableProxyInterface m_mutableProxyInterface;
    private boolean m_bAutoConnect;
    private boolean m_bAutoReConnect;
    private int m_iDummyTrafficIntervall;
    private boolean m_bSmallDisplay;
    private boolean m_bInfoServiceDisabled;
    private boolean m_bMinimizeOnStartup;
    private boolean m_bMoveToSystrayOnStartup;
    private int m_iDefaultView;
    private boolean m_bSaveMainWindowPosition;
    private boolean m_bSaveConfigWindowPosition;
    private boolean m_bSaveIconifiedWindowPosition;
    private boolean m_bSaveHelpWindowPosition;
    private Point m_OldMainWindowLocation;
    private Point m_iconifiedWindowLocation;
    private Point m_configWindowLocation;
    private Point m_helpWindowLocation;
    private boolean m_bGoodByMessageNeverRemind;
    private int m_iPaymentAnonymousConnectionSetting;
    private int m_iInfoServiceAnonymousConnectionSetting;
    private int m_iUpdateAnonymousConnectionSetting;
    private boolean m_bAskForAnyNonAnonymousRequest;
    private boolean m_bRemindOptionalUpdate;
    private boolean m_bRemindJavaUpdate;
    private boolean m_bTorActivated;
    private boolean m_bMixMinionActivated;
    private boolean m_bChooseCascasdeConnectionAutomatically;
    private boolean m_bChooseCascasdeAutomaticallyOnStartup;
    private boolean m_bMiniViewOnTop;
    private String m_strLookAndFeel;
    private Vector m_vecLookAndFeels;
    private UIManager.LookAndFeelInfo[] m_systemLookAndFeels;
    private Object LOOK_AND_FEEL_SYNC;
    private boolean m_bShowDialogFormat;
    private boolean m_bAnonymizedHttpHeaders;
    private String m_context;
    private String m_strDistributorMode;
    private String m_strRelativeBrowserPath;
    private int m_fontSize;
    private GUIUtils.IIconResizer m_resizer;
    private static JAPModel ms_TheModel;
    private JAPCertificate m_certJAPCodeSigning;
    private int m_TorMaxConnectionsPerRoute;
    private int m_TorMaxRouteLen;
    private int m_TorMinRouteLen;
    private boolean m_bTorUseNoneDefaultDirServer;
    private int m_mixminionRouteLen;
    private String m_mixminionMyEMail;
    private String m_mixminionPassword;
    private byte[] m_mixminionPasswordHash;
    private String m_mixminionKeyring;
    private Vector m_mixminionMessages;
    private MMRList m_mixminionRouters;
    private Vector m_mixminionFragments;
    private boolean m_bPreCreateAnonRoutes;
    private boolean m_bUseProxyAuthentication;
    private JAPController.AnonConnectionChecker m_connectionChecker;
    private boolean m_bShowSplashScreen;
    private boolean m_bShowSplashDisabled;
    private boolean m_bStartPortableFirefox;
    private String m_helpPath;
    private boolean m_bPortableHelp;
    private Dimension m_iconifiedSize;
    private Dimension m_configSize;
    private Dimension m_helpSize;
    private boolean m_bSaveHelpSize;
    private boolean m_bSaveConfigSize;
    private JAPRoutingSettings m_routingSettings;
    private String m_configFileName;
    private boolean m_forwardingStateModuleVisible;
    private String m_paymentPassword;
    private String m_bDllUpdatePath;
    private long m_noWarningForDllVersionBelow;
    private boolean m_bMacOSXLibraryUpdateAtStartupNeeded;
    private BigInteger m_iDialogVersion;
    private AbstractHelpFileStorageManager m_helpFileStorageManager;
    private Hashtable m_acceptedTCs;
    static /* synthetic */ Class class$jap$JAPModel;
    
    private JAPModel() {
        this.m_HttpListenerPortNumber = 4001;
        this.m_bHttpListenerIsLocal = true;
        this.m_proxyInterface = null;
        this.SYNC_ANON_PROXY = new Object();
        this.m_iDummyTrafficIntervall = -1;
        this.m_bSmallDisplay = false;
        this.m_bInfoServiceDisabled = false;
        this.m_bMinimizeOnStartup = false;
        this.m_bMoveToSystrayOnStartup = false;
        this.m_iDefaultView = 2;
        this.m_OldMainWindowLocation = null;
        this.m_iconifiedWindowLocation = null;
        this.m_configWindowLocation = null;
        this.m_helpWindowLocation = null;
        this.m_bGoodByMessageNeverRemind = false;
        this.m_vecLookAndFeels = new Vector();
        this.LOOK_AND_FEEL_SYNC = new Object();
        this.m_bShowDialogFormat = false;
        this.m_bAnonymizedHttpHeaders = true;
        this.m_context = "jondonym";
        this.m_strDistributorMode = "JAP/JonDo";
        this.m_fontSize = 0;
        this.m_resizer = new GUIUtils.IIconResizer() {
            public double getResizeFactor() {
                return 1.0 + JAPModel.this.getFontSize() * 0.1;
            }
        };
        this.m_certJAPCodeSigning = null;
        this.m_TorMaxConnectionsPerRoute = 1000;
        this.m_TorMaxRouteLen = 3;
        this.m_TorMinRouteLen = 2;
        this.m_bTorUseNoneDefaultDirServer = false;
        this.m_mixminionRouteLen = 2;
        this.m_mixminionMyEMail = "";
        this.m_mixminionPassword = null;
        this.m_mixminionPasswordHash = null;
        this.m_mixminionKeyring = "";
        this.m_mixminionMessages = null;
        this.m_mixminionRouters = null;
        this.m_mixminionFragments = null;
        this.m_bPreCreateAnonRoutes = false;
        this.m_bUseProxyAuthentication = false;
        this.m_bShowSplashScreen = true;
        this.m_bShowSplashDisabled = false;
        this.m_bStartPortableFirefox = true;
        this.m_helpPath = null;
        this.m_bPortableHelp = false;
        this.m_noWarningForDllVersionBelow = 0L;
        this.m_bMacOSXLibraryUpdateAtStartupNeeded = false;
        this.m_iDialogVersion = new BigInteger("-1");
        this.m_acceptedTCs = new Hashtable();
        try {
            this.m_certJAPCodeSigning = JAPCertificate.getInstance(ResourceLoader.loadResource("certificates/japcodesigning.cer"));
        }
        catch (Throwable t) {
            this.m_certJAPCodeSigning = null;
        }
        this.m_routingSettings = new JAPRoutingSettings();
        this.m_configFileName = null;
        this.m_forwardingStateModuleVisible = false;
        this.m_mutableProxyInterface = new IMutableProxyInterface() {
            private final /* synthetic */ JAPModel this$0;
            
            public IProxyInterfaceGetter getProxyInterface(final boolean b) {
                return new IProxyInterfaceGetter() {
                    private final /* synthetic */ JAPModel$2 this$1 = this$1;
                    
                    public ImmutableProxyInterface getProxyInterface() {
                        final ProxyInterface access$100 = this.this$1.this$0.m_proxyInterface;
                        if (access$100 != null && access$100.isValid()) {
                            return access$100;
                        }
                        return null;
                    }
                };
            }
        };
        if (ClassUtil.getJarFile() == null) {
            this.m_helpFileStorageManager = new LocalHelpFileStorageManager("JonDo");
        }
        else {
            this.m_helpFileStorageManager = new JARHelpFileStorageManager();
        }
    }
    
    public static JAPModel getInstance() {
        if (JAPModel.ms_TheModel == null) {
            JAPModel.ms_TheModel = new JAPModel();
        }
        return JAPModel.ms_TheModel;
    }
    
    public String getPortableBrowserpath() {
        return this.m_strRelativeBrowserPath;
    }
    
    public void setPortableBrowserpath(final String strRelativeBrowserPath) {
        if (strRelativeBrowserPath == null || strRelativeBrowserPath.trim().length() <= 0) {
            this.m_strRelativeBrowserPath = null;
        }
        else {
            this.m_strRelativeBrowserPath = strRelativeBrowserPath;
        }
    }
    
    public static String[] getMsgConnectionAnonymous() {
        return JAPModel.MSG_CONNECTION_ANONYMOUS;
    }
    
    public ProxyInterface getProxyInterface() {
        return this.m_proxyInterface;
    }
    
    public IMutableProxyInterface getMutableProxyInterface() {
        return this.m_mutableProxyInterface;
    }
    
    void setProxyListener(final ProxyInterface proxyInterface) {
        this.m_proxyInterface = proxyInterface;
    }
    
    void setAutoConnect(final boolean bAutoConnect) {
        synchronized (this) {
            if (this.m_bAutoConnect != bAutoConnect) {
                this.m_bAutoConnect = bAutoConnect;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_AUTO_CONNECT);
        }
    }
    
    public static boolean isAutoConnect() {
        return JAPModel.ms_TheModel.m_bAutoConnect;
    }
    
    public void setAutoReConnect(final boolean bAutoReConnect) {
        synchronized (this) {
            if (this.m_bAutoReConnect != bAutoReConnect) {
                this.m_bAutoReConnect = bAutoReConnect;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_AUTO_RECONNECT);
        }
    }
    
    public static boolean isAutomaticallyReconnected() {
        return JAPModel.ms_TheModel.m_bAutoReConnect;
    }
    
    public void setLookAndFeel(final String strLookAndFeel) {
        this.m_strLookAndFeel = strLookAndFeel;
    }
    
    public Vector getLookAndFeelFiles() {
        return (Vector)this.m_vecLookAndFeels.clone();
    }
    
    public boolean addLookAndFeelFile(final File file) {
        if (file != null) {
            synchronized (this.m_vecLookAndFeels) {
                if (!this.m_vecLookAndFeels.contains(file)) {
                    this.m_vecLookAndFeels.addElement(file);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean removeLookAndFeelFile(final File file) {
        return this.m_vecLookAndFeels.removeElement(file);
    }
    
    public String getLookAndFeel() {
        return this.m_strLookAndFeel;
    }
    
    public boolean isTorActivated() {
        return this.m_bTorActivated;
    }
    
    public void setTorActivated(final boolean bTorActivated) {
        this.m_bTorActivated = bTorActivated;
    }
    
    public void setMixMinionActivated(final boolean bMixMinionActivated) {
        this.m_bMixMinionActivated = bMixMinionActivated;
    }
    
    public boolean isMixMinionActivated() {
        return this.m_bMixMinionActivated;
    }
    
    protected void setMinimizeOnStartup(final boolean bMinimizeOnStartup) {
        this.m_bMinimizeOnStartup = bMinimizeOnStartup;
    }
    
    public static boolean getMinimizeOnStartup() {
        return JAPModel.ms_TheModel.m_bMinimizeOnStartup;
    }
    
    protected void setMoveToSystrayOnStartup(final boolean bMoveToSystrayOnStartup) {
        this.m_bMoveToSystrayOnStartup = bMoveToSystrayOnStartup;
    }
    
    public static boolean getMoveToSystrayOnStartup() {
        return JAPModel.ms_TheModel.m_bMoveToSystrayOnStartup;
    }
    
    protected void setDefaultView(final int iDefaultView) {
        this.m_iDefaultView = iDefaultView;
    }
    
    public static int getDefaultView() {
        return JAPModel.ms_TheModel.m_iDefaultView;
    }
    
    protected void setSaveMainWindowPosition(final boolean bSaveMainWindowPosition) {
        this.m_bSaveMainWindowPosition = bSaveMainWindowPosition;
    }
    
    public void setSaveConfigWindowPosition(final boolean bSaveConfigWindowPosition) {
        this.m_bSaveConfigWindowPosition = bSaveConfigWindowPosition;
    }
    
    public void setSaveIconifiedWindowPosition(final boolean bSaveIconifiedWindowPosition) {
        this.m_bSaveIconifiedWindowPosition = bSaveIconifiedWindowPosition;
    }
    
    public void setSaveHelpWindowPosition(final boolean bSaveHelpWindowPosition) {
        this.m_bSaveHelpWindowPosition = bSaveHelpWindowPosition;
    }
    
    public void updateSystemLookAndFeels() {
        synchronized (this.LOOK_AND_FEEL_SYNC) {
            this.m_systemLookAndFeels = UIManager.getInstalledLookAndFeels();
        }
    }
    
    public boolean isSystemLookAndFeel(final String s) {
        synchronized (this.LOOK_AND_FEEL_SYNC) {
            if (this.m_systemLookAndFeels == null || s == null) {
                return false;
            }
            for (int i = 0; i < this.m_systemLookAndFeels.length; ++i) {
                if (this.m_systemLookAndFeels[i] != null) {
                    if (this.m_systemLookAndFeels[i].getClassName().equals(s)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isIconifiedWindowLocationSaved() {
        return this.m_bSaveIconifiedWindowPosition;
    }
    
    public void setIconifiedWindowLocation(final Point iconifiedWindowLocation) {
        this.m_iconifiedWindowLocation = iconifiedWindowLocation;
    }
    
    public Point getIconifiedWindowLocation() {
        if (this.isIconifiedWindowLocationSaved()) {
            return this.m_iconifiedWindowLocation;
        }
        return null;
    }
    
    public boolean isHelpWindowLocationSaved() {
        return this.m_bSaveHelpWindowPosition;
    }
    
    public void setHelpWindowLocation(final Point helpWindowLocation) {
        this.m_helpWindowLocation = helpWindowLocation;
    }
    
    public Point getHelpWindowLocation() {
        if (this.isHelpWindowLocationSaved()) {
            return this.m_helpWindowLocation;
        }
        return null;
    }
    
    public boolean isConfigWindowLocationSaved() {
        return this.m_bSaveConfigWindowPosition;
    }
    
    public void setConfigWindowLocation(final Point configWindowLocation) {
        this.m_configWindowLocation = configWindowLocation;
    }
    
    public Point getConfigWindowLocation() {
        if (this.isConfigWindowLocationSaved()) {
            return this.m_configWindowLocation;
        }
        return null;
    }
    
    public static boolean isMainWindowLocationSaved() {
        return JAPModel.ms_TheModel.m_bSaveMainWindowPosition;
    }
    
    protected void setMainWindowLocation(final Point oldMainWindowLocation) {
        this.m_OldMainWindowLocation = oldMainWindowLocation;
    }
    
    public static Point getMainWindowLocation() {
        if (isMainWindowLocationSaved()) {
            return JAPModel.ms_TheModel.m_OldMainWindowLocation;
        }
        return null;
    }
    
    public boolean isDialogFormatShown() {
        return this.m_bShowDialogFormat;
    }
    
    public void setDialogFormatShown(final boolean bShowDialogFormat) {
        this.m_bShowDialogFormat = bShowDialogFormat;
    }
    
    protected void setDummyTraffic(final int iDummyTrafficIntervall) {
        this.m_iDummyTrafficIntervall = iDummyTrafficIntervall;
    }
    
    public static int getDummyTraffic() {
        return JAPModel.ms_TheModel.m_iDummyTrafficIntervall;
    }
    
    protected void setHttpListenerPortNumber(final int httpListenerPortNumber) {
        this.m_HttpListenerPortNumber = httpListenerPortNumber;
    }
    
    public void setAnonConnectionChecker(final JAPController.AnonConnectionChecker connectionChecker) {
        this.m_connectionChecker = connectionChecker;
    }
    
    public boolean isReminderForOptionalUpdateActivated() {
        return this.m_bRemindOptionalUpdate;
    }
    
    public void setReminderForOptionalUpdate(final boolean bRemindOptionalUpdate) {
        synchronized (this) {
            if (this.m_bRemindOptionalUpdate != bRemindOptionalUpdate) {
                this.m_bRemindOptionalUpdate = bRemindOptionalUpdate;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_NOTIFY_JAP_UPDATES);
        }
    }
    
    public boolean isReminderForJavaUpdateActivated() {
        return this.m_bRemindJavaUpdate;
    }
    
    public void setReminderForJavaUpdate(final boolean bRemindJavaUpdate) {
        synchronized (this) {
            if (this.m_bRemindJavaUpdate != bRemindJavaUpdate) {
                this.m_bRemindJavaUpdate = bRemindJavaUpdate;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_NOTIFY_JAVA_UPDATES);
        }
    }
    
    public void setCascadeAutoSwitch(final boolean bChooseCascasdeConnectionAutomatically) {
        synchronized (this) {
            if (this.m_bChooseCascasdeConnectionAutomatically != bChooseCascasdeConnectionAutomatically) {
                this.m_bChooseCascasdeConnectionAutomatically = bChooseCascasdeConnectionAutomatically;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_CASCADE_AUTO_CHANGE);
        }
    }
    
    public boolean isCascadeAutoSwitched() {
        return this.m_bChooseCascasdeConnectionAutomatically;
    }
    
    public void setAutoChooseCascadeOnStartup(final boolean bChooseCascasdeAutomaticallyOnStartup) {
        this.m_bChooseCascasdeAutomaticallyOnStartup = bChooseCascasdeAutomaticallyOnStartup;
    }
    
    public boolean isCascadeAutoChosenOnStartup() {
        return this.m_bChooseCascasdeAutomaticallyOnStartup;
    }
    
    public boolean isAnonConnected() {
        return this.m_connectionChecker.checkAnonConnected();
    }
    
    public boolean isAskForAnyNonAnonymousRequest() {
        return this.m_bAskForAnyNonAnonymousRequest;
    }
    
    public void setAskForAnyNonAnonymousRequest(final boolean bAskForAnyNonAnonymousRequest) {
        synchronized (this) {
            if (this.m_bAskForAnyNonAnonymousRequest != bAskForAnyNonAnonymousRequest) {
                this.m_bAskForAnyNonAnonymousRequest = bAskForAnyNonAnonymousRequest;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_ASK_FOR_NON_ANONYMOUS);
        }
    }
    
    public int getPaymentAnonymousConnectionSetting() {
        return this.m_iPaymentAnonymousConnectionSetting;
    }
    
    public int getUpdateAnonymousConnectionSetting() {
        return this.m_iUpdateAnonymousConnectionSetting;
    }
    
    public void setUpdateAnonymousConnectionSetting(final int iUpdateAnonymousConnectionSetting) {
        synchronized (this) {
            if (this.m_iUpdateAnonymousConnectionSetting != iUpdateAnonymousConnectionSetting) {
                this.m_iUpdateAnonymousConnectionSetting = iUpdateAnonymousConnectionSetting;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_ALLOW_UPDATE_DIRECT_CONNECTION);
        }
    }
    
    public int getInfoServiceAnonymousConnectionSetting() {
        return this.m_iInfoServiceAnonymousConnectionSetting;
    }
    
    public void setInfoServiceAnonymousConnectionSetting(final int iInfoServiceAnonymousConnectionSetting) {
        synchronized (this) {
            if (this.m_iInfoServiceAnonymousConnectionSetting != iInfoServiceAnonymousConnectionSetting) {
                this.m_iInfoServiceAnonymousConnectionSetting = iInfoServiceAnonymousConnectionSetting;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_ALLOW_INFOSERVICE_DIRECT_CONNECTION);
        }
    }
    
    public void setPaymentAnonymousConnectionSetting(final int iPaymentAnonymousConnectionSetting) {
        this.m_iPaymentAnonymousConnectionSetting = iPaymentAnonymousConnectionSetting;
    }
    
    public IMutableProxyInterface getInfoServiceProxyInterface() {
        return new IMutableProxyInterface() {
            public IProxyInterfaceGetter getProxyInterface(final boolean b) {
                return JAPModel.getInstance().getProxyInterface(0, b);
            }
        };
    }
    
    public IMutableProxyInterface getPaymentProxyInterface() {
        return new IMutableProxyInterface() {
            public IProxyInterfaceGetter getProxyInterface(final boolean b) {
                return JAPModel.getInstance().getProxyInterface(1, b);
            }
        };
    }
    
    public IMutableProxyInterface getUpdateProxyInterface() {
        return new IMutableProxyInterface() {
            public IProxyInterfaceGetter getProxyInterface(final boolean b) {
                return JAPModel.getInstance().getProxyInterface(2, b);
            }
        };
    }
    
    public ImmutableProxyInterface getTorProxyInterface() {
        return new ProxyInterface("localhost", getHttpListenerPortNumber(), 3, null);
    }
    
    public static int getHttpListenerPortNumber() {
        return JAPModel.ms_TheModel.m_HttpListenerPortNumber;
    }
    
    protected void setHttpListenerIsLocal(final boolean bHttpListenerIsLocal) {
        this.m_bHttpListenerIsLocal = bHttpListenerIsLocal;
    }
    
    public static boolean isHttpListenerLocal() {
        return JAPModel.ms_TheModel.m_bHttpListenerIsLocal;
    }
    
    public void setSmallDisplay(final boolean bSmallDisplay) {
        this.m_bSmallDisplay = bSmallDisplay;
    }
    
    public static boolean isSmallDisplay() {
        return JAPModel.ms_TheModel.m_bSmallDisplay;
    }
    
    public boolean isNeverRemindGoodbye() {
        return this.m_bGoodByMessageNeverRemind;
    }
    
    public void setNeverRemindGoodbye(final boolean bGoodByMessageNeverRemind) {
        this.m_bGoodByMessageNeverRemind = bGoodByMessageNeverRemind;
    }
    
    protected void setInfoServiceDisabled(final boolean bInfoServiceDisabled) {
        synchronized (this) {
            if (this.m_bInfoServiceDisabled != bInfoServiceDisabled) {
                this.m_bInfoServiceDisabled = bInfoServiceDisabled;
                this.setChanged();
            }
            this.notifyObservers(JAPModel.CHANGED_INFOSERVICE_AUTO_UPDATE);
        }
    }
    
    public static boolean isInfoServiceDisabled() {
        return JAPModel.ms_TheModel.m_bInfoServiceDisabled;
    }
    
    public boolean isMiniViewOnTop() {
        return this.m_bMiniViewOnTop;
    }
    
    public void setMiniViewOnTop(final boolean bMiniViewOnTop) {
        this.m_bMiniViewOnTop = bMiniViewOnTop;
    }
    
    public GUIUtils.IIconResizer getIconResizer() {
        return this.m_resizer;
    }
    
    public int getFontSize() {
        return this.m_fontSize;
    }
    
    public boolean setFontSize(int fontSize) {
        if (fontSize < 0) {
            fontSize = 0;
        }
        else if (fontSize > 3) {
            fontSize = 3;
        }
        if (this.m_fontSize != fontSize) {
            synchronized (this) {
                final FontResize fontResize = new FontResize(this.m_fontSize, fontSize);
                if (!JAPDialog.isConsoleOnly()) {
                    GUIUtils.resizeAllFonts(1.0f / (1.0f + 0.1f * fontResize.getOldSize()));
                    GUIUtils.resizeAllFonts(1.0f + 0.1f * fontResize.getNewSize());
                }
                this.m_fontSize = fontSize;
                this.setChanged();
                this.notifyObservers(fontResize);
            }
            return true;
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(2048);
        sb.append("Configuration for JAP Version ");
        sb.append("00.12.005");
        sb.append("\n");
        final String dllVersion = JAPDll.getDllVersion();
        if (dllVersion != null) {
            sb.append("Using JAPDll Version: ");
            sb.append(dllVersion);
            sb.append("\n");
        }
        final String dllFileName = JAPDll.getDllFileName();
        if (dllFileName != null) {
            sb.append("Using JAPDll File: ");
            sb.append(dllFileName);
            sb.append("\n");
        }
        sb.append("Config path: ");
        sb.append(this.getConfigFile());
        sb.append("\n");
        sb.append("Help path: ");
        sb.append(this.getHelpPath());
        sb.append("\n");
        if (this.m_bDllUpdatePath != null) {
            sb.append("DLL update path: ");
            sb.append(this.m_bDllUpdatePath);
            sb.append("\n");
        }
        sb.append("Command line arguments: '");
        for (int i = 0; i < JAPController.getInstance().getCommandlineArgs().length; ++i) {
            sb.append(JAPController.getInstance().getCommandlineArgs()[i]);
            if (i < JAPController.getInstance().getCommandlineArgs().length - 1) {
                sb.append(" ");
            }
        }
        sb.append("'\n");
        sb.append("HttpListenerPortNumber: ");
        sb.append(this.m_HttpListenerPortNumber);
        sb.append("\n");
        sb.append("HttpListenerIsLocal: ");
        sb.append(this.m_bHttpListenerIsLocal);
        sb.append("\n");
        sb.append("UseFirewall: ");
        final boolean b = this.m_proxyInterface != null && this.m_proxyInterface.isValid();
        sb.append(b);
        sb.append("\n");
        if (b) {
            sb.append("FirewallType: ");
            sb.append(this.m_proxyInterface.getProtocol());
            sb.append("\n");
            sb.append("FirewallHost: ");
            sb.append(this.m_proxyInterface.getHost());
            sb.append("\n");
            sb.append("FirewallPort: ");
            sb.append(this.m_proxyInterface.getPort());
            sb.append("\n");
        }
        sb.append("AutoConnect: ");
        sb.append(this.m_bAutoConnect);
        sb.append("\n");
        sb.append("AutoReConnect: ");
        sb.append(this.m_bAutoReConnect);
        sb.append("\n");
        return sb.toString();
    }
    
    public static boolean isPreCreateAnonRoutesEnabled() {
        return JAPModel.ms_TheModel.m_bPreCreateAnonRoutes;
    }
    
    void setPreCreateAnonRoutes(final boolean bPreCreateAnonRoutes) {
        this.m_bPreCreateAnonRoutes = bPreCreateAnonRoutes;
    }
    
    public static JAPCertificate getJAPCodeSigningCert() {
        return JAPModel.ms_TheModel.m_certJAPCodeSigning;
    }
    
    public void setConfigFile(final String configFileName) {
        this.m_configFileName = configFileName;
    }
    
    public void setIconifiedSize(final Dimension iconifiedSize) {
        this.m_iconifiedSize = iconifiedSize;
    }
    
    public Dimension getIconifiedSize() {
        return this.m_iconifiedSize;
    }
    
    public void setHelpWindowSize(final Dimension helpSize) {
        this.m_helpSize = helpSize;
    }
    
    public Dimension getHelpWindowSize() {
        return this.m_helpSize;
    }
    
    public boolean isHelpWindowSizeSaved() {
        return this.m_bSaveHelpSize;
    }
    
    public void setSaveHelpWindowSize(final boolean bSaveHelpSize) {
        this.m_bSaveHelpSize = bSaveHelpSize;
    }
    
    public void setSaveConfigWindowSize(final boolean bSaveConfigSize) {
        this.m_bSaveConfigSize = bSaveConfigSize;
    }
    
    public boolean isConfigWindowSizeSaved() {
        return this.m_bSaveConfigSize;
    }
    
    public void setConfigSize(final Dimension configSize) {
        this.m_configSize = configSize;
    }
    
    public Dimension getConfigSize() {
        return this.m_configSize;
    }
    
    public String getConfigFile() {
        return this.m_configFileName;
    }
    
    public JAPRoutingSettings getRoutingSettings() {
        return this.m_routingSettings;
    }
    
    public void setForwardingStateModuleVisible(final boolean forwardingStateModuleVisible) {
        this.m_forwardingStateModuleVisible = forwardingStateModuleVisible;
    }
    
    public boolean isForwardingStateModuleVisible() {
        return this.m_forwardingStateModuleVisible;
    }
    
    public static int getTorMaxConnectionsPerRoute() {
        return JAPModel.ms_TheModel.m_TorMaxConnectionsPerRoute;
    }
    
    protected void setTorMaxConnectionsPerRoute(final int torMaxConnectionsPerRoute) {
        this.m_TorMaxConnectionsPerRoute = torMaxConnectionsPerRoute;
    }
    
    public static int getTorMaxRouteLen() {
        return JAPModel.ms_TheModel.m_TorMaxRouteLen;
    }
    
    protected void setTorMaxRouteLen(final int torMaxRouteLen) {
        this.m_TorMaxRouteLen = torMaxRouteLen;
    }
    
    public static int getTorMinRouteLen() {
        return JAPModel.ms_TheModel.m_TorMinRouteLen;
    }
    
    protected void setTorMinRouteLen(final int torMinRouteLen) {
        this.m_TorMinRouteLen = torMinRouteLen;
    }
    
    public static boolean isTorNoneDefaultDirServerEnabled() {
        return JAPModel.ms_TheModel.m_bTorUseNoneDefaultDirServer;
    }
    
    protected void setTorUseNoneDefaultDirServer(final boolean bTorUseNoneDefaultDirServer) {
        this.m_bTorUseNoneDefaultDirServer = bTorUseNoneDefaultDirServer;
    }
    
    protected void setMixminionRouteLen(final int mixminionRouteLen) {
        this.m_mixminionRouteLen = mixminionRouteLen;
    }
    
    public static int getMixminionRouteLen() {
        return JAPModel.ms_TheModel.m_mixminionRouteLen;
    }
    
    protected void setMixminionMyEMail(final String mixminionMyEMail) {
        this.m_mixminionMyEMail = mixminionMyEMail;
    }
    
    public static String getMixminionMyEMail() {
        return JAPModel.ms_TheModel.m_mixminionMyEMail;
    }
    
    protected void setMixMinionPassword(final String mixminionPassword) {
        this.m_mixminionPassword = mixminionPassword;
    }
    
    public static String getMixMinionPassword() {
        return JAPModel.ms_TheModel.m_mixminionPassword;
    }
    
    protected void setMixinionPasswordHash(final byte[] mixminionPasswordHash) {
        this.m_mixminionPasswordHash = mixminionPasswordHash;
    }
    
    public static byte[] getMixMinionPasswordHash() {
        return JAPModel.ms_TheModel.m_mixminionPasswordHash;
    }
    
    protected void resetMixMinionKeyringandPw() {
        this.m_mixminionPasswordHash = null;
        this.m_mixminionPassword = null;
        this.m_mixminionKeyring = "";
    }
    
    protected void setMixminionMessages(final Vector mixminionMessages) {
        this.m_mixminionMessages = mixminionMessages;
    }
    
    public static Vector getMixminionMessages() {
        return JAPModel.ms_TheModel.m_mixminionMessages;
    }
    
    protected void setMixminionKeyring(final String mixminionKeyring) {
        this.m_mixminionKeyring = mixminionKeyring;
    }
    
    public static String getMixminionKeyring() {
        return JAPModel.ms_TheModel.m_mixminionKeyring;
    }
    
    protected void setMixminionMMRList(final MMRList mixminionRouters) {
        this.m_mixminionRouters = mixminionRouters;
    }
    
    public static MMRList getMixminionMMRlist() {
        return JAPModel.ms_TheModel.m_mixminionRouters;
    }
    
    protected void setMixminionFragments(final Vector mixminionFragments) {
        this.m_mixminionFragments = mixminionFragments;
    }
    
    public static Vector getMixminionFragments() {
        return JAPModel.ms_TheModel.m_mixminionFragments;
    }
    
    protected void setUseProxyAuthentication(final boolean bUseProxyAuthentication) {
        this.m_bUseProxyAuthentication = bUseProxyAuthentication;
    }
    
    public boolean isProxyAuthenticationUsed() {
        return this.m_bUseProxyAuthentication;
    }
    
    public void setPaymentPassword(final String paymentPassword) {
        this.m_paymentPassword = paymentPassword;
    }
    
    public String getPaymentPassword() {
        return this.m_paymentPassword;
    }
    
    public synchronized String getHelpPath() {
        return (this.m_helpPath != null || this.m_bPortableHelp) ? this.m_helpPath : AbstractOS.getInstance().getDefaultHelpPath("JonDo");
    }
    
    public synchronized URL getHelpURL(final String s) {
        URL url = null;
        if (s != null && this.isHelpPathDefined() && this.m_helpFileStorageManager.ensureMostRecentVersion(this.m_helpPath)) {
            try {
                if (new File(this.m_helpPath + File.separator + this.m_helpFileStorageManager.getLocalisedHelpDir() + File.separator + s).exists()) {
                    url = new URL("file://" + this.m_helpPath + "/" + this.m_helpFileStorageManager.getLocalisedHelpDir() + "/" + s);
                }
            }
            catch (SecurityException ex) {
                LogHolder.log(4, LogType.MISC, ex);
            }
            catch (MalformedURLException ex2) {
                LogHolder.log(4, LogType.MISC, ex2);
            }
        }
        return url;
    }
    
    public URL getHelpURL() {
        return this.getHelpURL("index.html");
    }
    
    synchronized void initHelpPath(String helpPath) {
        if (this.m_bPortableHelp) {
            return;
        }
        final String getenv = AbstractOS.getInstance().getenv("ALLUSERSPROFILE");
        if (getenv != null && helpPath != null && helpPath.startsWith(getenv)) {
            if (helpPath.indexOf("JonDo") >= 0) {
                RecursiveFileTool.deleteRecursion(new File(helpPath));
            }
            helpPath = null;
        }
        final String helpPathValidityCheck = this.helpPathValidityCheck(helpPath);
        if (helpPathValidityCheck.equals("HELP_IS_VALID") || helpPathValidityCheck.equals("helpJonDoExists") || helpPathValidityCheck.equals("help_internal")) {
            this.m_helpPath = helpPath;
        }
        else {
            this.m_helpPath = this.m_helpFileStorageManager.getInitPath();
        }
    }
    
    public synchronized void setHelpPath(final File file) {
        this.setHelpPath(file, false);
    }
    
    public synchronized void setHelpPath(File o, final boolean b) {
        if (this.m_bPortableHelp && !b) {
            return;
        }
        if (o == null) {
            this.resetHelpPath();
        }
        else {
            o = new File(((File)o).getAbsolutePath());
            if (b) {
                this.m_bPortableHelp = true;
                if (((File)o).isFile()) {
                    int n;
                    if ((n = ((File)o).getPath().toUpperCase().indexOf(("help" + File.pathSeparator + "de" + File.pathSeparator + "help").toUpperCase())) >= 0 || (n = ((File)o).getPath().toUpperCase().indexOf(("help" + File.pathSeparator + "en" + File.pathSeparator + "help").toUpperCase())) >= 0) {
                        if (n > 0) {
                            o = new File(((File)o).getPath().substring(0, n));
                        }
                        else {
                            o = null;
                        }
                    }
                    else {
                        final String parent = ((File)o).getParent();
                        if (parent != null) {
                            o = new File(parent);
                        }
                        else {
                            o = null;
                        }
                    }
                }
                if (o != null && ((File)o).isDirectory()) {
                    final String helpPathValidityCheck = this.m_helpFileStorageManager.helpPathValidityCheck(((File)o).getPath(), true);
                    if (helpPathValidityCheck.equals("HELP_IS_VALID") || helpPathValidityCheck.equals("helpJonDoExists")) {
                        if (this.m_helpFileStorageManager.handleHelpPathChanged(this.m_helpPath, ((File)o).getPath(), true)) {
                            if (this.m_helpPath == null || !this.m_helpPath.equals(((File)o).getPath())) {
                                this.m_helpPath = ((File)o).getPath();
                                this.setChanged();
                            }
                        }
                        else {
                            this.resetHelpPath();
                            LogHolder.log(4, LogType.GUI, "Help path resetted because we could not change it.");
                        }
                    }
                    else {
                        this.resetHelpPath();
                        LogHolder.log(4, LogType.GUI, "Help path resetted because it was invalid.");
                    }
                }
                else {
                    this.resetHelpPath();
                    LogHolder.log(4, LogType.GUI, "Help path resetted because it was no directory.");
                }
            }
            else {
                if (((File)o).getPath().toUpperCase().endsWith("help".toUpperCase()) && ((File)o).getParent() != null) {
                    final File file = new File(((File)o).getParent());
                    if (file.isDirectory()) {
                        o = file;
                    }
                }
                this.setHelpPath(((File)o).getPath());
            }
        }
        this.notifyObservers(JAPModel.CHANGED_HELP_PATH);
    }
    
    private synchronized void setHelpPath(final String helpPath) {
        if (helpPath == null) {
            this.resetHelpPath();
            return;
        }
        if (helpPath.equals("")) {
            this.resetHelpPath();
            return;
        }
        final String helpPathValidityCheck = this.helpPathValidityCheck(helpPath);
        if (helpPathValidityCheck.equals("HELP_IS_VALID") || helpPathValidityCheck.equals("helpJonDoExists")) {
            final String helpPath2 = this.m_helpPath;
            if ((!this.isHelpPathDefined() || !this.m_helpPath.equals(helpPath)) && this.m_helpFileStorageManager.handleHelpPathChanged(helpPath2, helpPath, false)) {
                this.m_helpPath = helpPath;
                this.setChanged();
            }
        }
    }
    
    public boolean extractHelpFiles(final String s) {
        return this.m_helpFileStorageManager.extractHelpFiles(s);
    }
    
    protected synchronized void resetHelpPath() {
        final String helpPath = this.m_helpPath;
        if (helpPath != null && !this.m_bPortableHelp) {
            this.m_helpFileStorageManager.handleHelpPathChanged(helpPath, null, false);
            this.setChanged();
            this.m_helpPath = null;
        }
    }
    
    public synchronized String helpPathValidityCheck(final String s) {
        return this.m_helpFileStorageManager.helpPathValidityCheck(s, false);
    }
    
    public synchronized String helpPathValidityCheck(final File file) {
        if (file == null) {
            return JAPMessages.getString("invalidHelpPathNull");
        }
        return this.helpPathValidityCheck(file.getPath());
    }
    
    public boolean isHelpPathChangeable() {
        return !(this.m_helpFileStorageManager instanceof LocalHelpFileStorageManager) && !this.m_bPortableHelp;
    }
    
    public synchronized boolean isHelpPathDefined() {
        final boolean b = this.m_helpPath != null;
        String helpPathValidityCheck = null;
        final boolean helpInstallationExists;
        boolean b2 = (helpInstallationExists = this.m_helpFileStorageManager.helpInstallationExists(this.m_helpPath)) && (helpPathValidityCheck = this.helpPathValidityCheck(this.m_helpPath)).equals("helpJonDoExists");
        if (b && !b2) {
            LogHolder.log(4, LogType.MISC, "Help path " + this.m_helpPath + " configured but no valid help could be found! Exists: " + helpInstallationExists + " Valid: " + helpPathValidityCheck);
            this.m_helpPath = null;
            this.setChanged();
        }
        if (!this.m_bPortableHelp && this.m_helpPath == null && this.m_helpFileStorageManager.helpInstallationExists(AbstractOS.getInstance().getDefaultHelpPath("JonDo")) && this.helpPathValidityCheck(AbstractOS.getInstance().getDefaultHelpPath("JonDo")).equals("helpJonDoExists")) {
            this.m_helpPath = AbstractOS.getInstance().getDefaultHelpPath("JonDo");
            b2 = true;
            this.setChanged();
        }
        this.notifyObservers(JAPModel.CHANGED_HELP_PATH);
        return b2;
    }
    
    public Observable getHelpFileStorageObservable() {
        return this.m_helpFileStorageManager.getStorageObservable();
    }
    
    public synchronized void setDLLupdate(final String s) {
        if (s != null && (this.m_bDllUpdatePath == null || !this.m_bDllUpdatePath.equals(s))) {
            final File file = new File(s);
            if (file.exists() && file.isDirectory()) {
                this.m_bDllUpdatePath = file.getAbsolutePath();
                this.setChanged();
            }
        }
        else if (s == null && this.m_bDllUpdatePath != null) {
            this.m_bDllUpdatePath = null;
            this.setChanged();
        }
        this.notifyObservers(JAPModel.CHANGED_DLL_UPDATE);
    }
    
    public synchronized void setMacOSXLibraryUpdateAtStartupNeeded(final boolean bMacOSXLibraryUpdateAtStartupNeeded) {
        if (this.m_bMacOSXLibraryUpdateAtStartupNeeded != bMacOSXLibraryUpdateAtStartupNeeded) {
            this.m_bMacOSXLibraryUpdateAtStartupNeeded = bMacOSXLibraryUpdateAtStartupNeeded;
            this.setChanged();
        }
        this.notifyObservers(JAPModel.CHANGED_MACOSX_LIBRARY_UPDATE);
    }
    
    public synchronized void setAnonymizedHttpHeaders(final boolean bAnonymizedHttpHeaders) {
        if (this.m_bAnonymizedHttpHeaders != bAnonymizedHttpHeaders) {
            this.m_bAnonymizedHttpHeaders = bAnonymizedHttpHeaders;
            this.setChanged();
        }
        this.notifyObservers(JAPModel.CHANGED_ANONYMIZED_HTTP_HEADERS);
    }
    
    public boolean isAnonymizedHttpHeaders() {
        return this.m_bAnonymizedHttpHeaders;
    }
    
    public boolean isMacOSXLibraryUpdateAtStartupNeeded() {
        return this.m_bMacOSXLibraryUpdateAtStartupNeeded;
    }
    
    public String getDllUpdatePath() {
        return this.m_bDllUpdatePath;
    }
    
    public synchronized void setDllWarning(final boolean b) {
        final String s = "00.04.009";
        long noWarningForDllVersionBelow = this.m_noWarningForDllVersionBelow;
        if (b) {
            noWarningForDllVersionBelow = 0L;
        }
        else if (s != null) {
            noWarningForDllVersionBelow = Util.convertVersionStringToNumber(s);
        }
        if (this.m_noWarningForDllVersionBelow != noWarningForDllVersionBelow) {
            this.m_noWarningForDllVersionBelow = noWarningForDllVersionBelow;
            this.setChanged();
        }
        this.notifyObservers(JAPModel.CHANGED_DLL_UPDATE);
    }
    
    protected synchronized void setDllWarningVersion(final long noWarningForDllVersionBelow) {
        if (this.m_noWarningForDllVersionBelow != noWarningForDllVersionBelow) {
            this.m_noWarningForDllVersionBelow = noWarningForDllVersionBelow;
            this.setChanged();
        }
        this.notifyObservers(JAPModel.CHANGED_DLL_UPDATE);
    }
    
    protected long getDLLWarningVersion() {
        return this.m_noWarningForDllVersionBelow;
    }
    
    public boolean isDLLWarningActive() {
        return this.m_noWarningForDllVersionBelow != Util.convertVersionStringToNumber("00.04.009");
    }
    
    public void setShowSplashScreen(final boolean bShowSplashScreen) {
        this.m_bShowSplashScreen = bShowSplashScreen;
    }
    
    public boolean getShowSplashScreen() {
        return this.m_bShowSplashScreen;
    }
    
    public void setShowSplashDisabled(final boolean bShowSplashDisabled) {
        this.m_bShowSplashDisabled = bShowSplashDisabled;
    }
    
    public boolean getShowSplashDisabled() {
        return this.m_bShowSplashDisabled;
    }
    
    public void setStartPortableFirefox(final boolean bStartPortableFirefox) {
        this.m_bStartPortableFirefox = bStartPortableFirefox;
    }
    
    public boolean getStartPortableFirefox() {
        return this.m_bStartPortableFirefox;
    }
    
    public boolean isShuttingDown() {
        return JAPController.getInstance().isShuttingDown();
    }
    
    private IProxyInterfaceGetter getProxyInterface(final int n, final boolean b) {
        if (this.isShuttingDown()) {
            return null;
        }
        final IProxyInterfaceGetter proxyInterfaceGetter = new IProxyInterfaceGetter() {
            public ImmutableProxyInterface getProxyInterface() {
                return JAPModel.getInstance().getProxyInterface();
            }
        };
        final IProxyInterfaceGetter proxyInterfaceGetter2 = new IProxyInterfaceGetter() {
            public ImmutableProxyInterface getProxyInterface() {
                synchronized (JAPModel.this.SYNC_ANON_PROXY) {
                    if (JAPModel.this.m_proxyAnon == null || JAPModel.this.m_proxyAnon.getPort() != JAPModel.getHttpListenerPortNumber()) {
                        final InetAddress listenerInetAddress = JAPController.getInstance().getListenerInetAddress();
                        if (listenerInetAddress != null) {
                            String s = listenerInetAddress.getHostAddress();
                            if (listenerInetAddress.getHostAddress().equals("0.0.0.0")) {
                                try {
                                    s = InetAddress.getLocalHost().getHostAddress();
                                }
                                catch (UnknownHostException ex) {
                                    s = "127.0.0.1";
                                }
                            }
                            JAPModel.this.m_proxyAnon = new ProxyInterface(s, JAPModel.getHttpListenerPortNumber(), null);
                        }
                    }
                }
                return JAPModel.this.m_proxyAnon;
            }
        };
        if ((1 == n && this.m_iPaymentAnonymousConnectionSetting == 1) || (0 == n && this.m_iInfoServiceAnonymousConnectionSetting == 1) || (2 == n && this.m_iUpdateAnonymousConnectionSetting == 1)) {
            if (!this.m_connectionChecker.checkAnonConnected()) {
                return null;
            }
            if (b) {
                return proxyInterfaceGetter2;
            }
            return null;
        }
        else if (!this.m_connectionChecker.checkAnonConnected()) {
            if (b) {
                return null;
            }
            return proxyInterfaceGetter;
        }
        else {
            if (!b) {
                return proxyInterfaceGetter;
            }
            if ((1 == n && 2 == this.m_iPaymentAnonymousConnectionSetting) || (0 == n && 2 == this.m_iInfoServiceAnonymousConnectionSetting) || (2 == n && 2 == this.m_iUpdateAnonymousConnectionSetting)) {
                return null;
            }
            return proxyInterfaceGetter2;
        }
    }
    
    public BigInteger getDialogVersion() {
        return this.m_iDialogVersion;
    }
    
    public void setDialogVersion(final BigInteger iDialogVersion) {
        this.m_iDialogVersion = iDialogVersion;
    }
    
    public Hashtable getAcceptedTCs() {
        return this.m_acceptedTCs;
    }
    
    public String getContext() {
        return this.m_context;
    }
    
    public String getProgramName() {
        return this.m_strDistributorMode;
    }
    
    public void setProgramName(final String strDistributorMode) {
        if (strDistributorMode != null && (strDistributorMode.equals("JAP") || strDistributorMode.equals("JonDo"))) {
            this.m_strDistributorMode = strDistributorMode;
        }
    }
    
    public synchronized void setContext(final String s) {
        TrustModel.updateContext(s);
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
        CHANGED_INFOSERVICE_AUTO_UPDATE = new Integer(0);
        CHANGED_ALLOW_INFOSERVICE_DIRECT_CONNECTION = new Integer(1);
        CHANGED_ALLOW_UPDATE_DIRECT_CONNECTION = new Integer(2);
        CHANGED_NOTIFY_JAP_UPDATES = new Integer(3);
        CHANGED_NOTIFY_JAVA_UPDATES = new Integer(4);
        CHANGED_AUTO_CONNECT = new Integer(5);
        CHANGED_AUTO_RECONNECT = new Integer(6);
        CHANGED_CASCADE_AUTO_CHANGE = new Integer(7);
        CHANGED_ASK_FOR_NON_ANONYMOUS = new Integer(8);
        CHANGED_HELP_PATH = new Integer(9);
        CHANGED_DLL_UPDATE = new Integer(10);
        CHANGED_MACOSX_LIBRARY_UPDATE = new Integer(11);
        CHANGED_ANONYMIZED_HTTP_HEADERS = new Integer(12);
        CHANGED_CONTEXT = new Integer(13);
        MSG_CONNECTION_ANONYMOUS = new String[] { ((JAPModel.class$jap$JAPModel == null) ? (JAPModel.class$jap$JAPModel = class$("jap.JAPModel")) : JAPModel.class$jap$JAPModel).getName() + "_anonymousConnectionAllow", ((JAPModel.class$jap$JAPModel == null) ? (JAPModel.class$jap$JAPModel = class$("jap.JAPModel")) : JAPModel.class$jap$JAPModel).getName() + "_anonymousConnectionForce", ((JAPModel.class$jap$JAPModel == null) ? (JAPModel.class$jap$JAPModel = class$("jap.JAPModel")) : JAPModel.class$jap$JAPModel).getName() + "_anonymousConnectionBlock" };
        JAPModel.ms_TheModel = null;
    }
    
    public static class FontResize
    {
        private int m_oldSize;
        private int m_newSize;
        
        public FontResize(final int oldSize, final int newSize) {
            this.m_oldSize = oldSize;
            this.m_newSize = newSize;
        }
        
        public int getOldSize() {
            return this.m_oldSize;
        }
        
        public int getNewSize() {
            return this.m_newSize;
        }
    }
}
