// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsICertOverrideService;
import org.eclipse.swt.internal.mozilla.nsIX509CertValidity;
import org.eclipse.swt.internal.mozilla.nsIX509Cert;
import org.eclipse.swt.internal.mozilla.nsISSLStatus;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.internal.mozilla.nsIDOMKeyEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.internal.mozilla.nsIWebNavigationInfo;
import org.eclipse.swt.internal.mozilla.nsIWebBrowserSetup;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.internal.mozilla.nsIDOMMouseEvent;
import org.eclipse.swt.internal.mozilla.nsIDOMEvent;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.mozilla.nsIWebProgress;
import org.eclipse.swt.internal.mozilla.nsIChannel;
import org.eclipse.swt.internal.mozilla.nsIRequest;
import org.eclipse.swt.internal.mozilla.nsIBadCertListener2;
import org.eclipse.swt.internal.mozilla.nsITooltipListener;
import org.eclipse.swt.internal.mozilla.nsIURIContentListener;
import org.eclipse.swt.internal.mozilla.nsIContextMenuListener;
import org.eclipse.swt.internal.mozilla.nsISupportsWeakReference;
import org.eclipse.swt.internal.mozilla.nsIWebBrowserChromeFocus;
import org.eclipse.swt.internal.mozilla.nsIWeakReference;
import org.eclipse.swt.internal.mozilla.nsIDOMWindowCollection;
import org.eclipse.swt.internal.mozilla.nsIDOMEventTarget;
import org.eclipse.swt.internal.mozilla.nsIMIMEInputStream;
import org.eclipse.swt.internal.mozilla.nsIWebBrowserStream;
import org.eclipse.swt.internal.mozilla.nsIWebBrowserFocus;
import java.util.Enumeration;
import org.eclipse.swt.internal.LONG;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.mozilla.nsIBaseWindow;
import java.util.Locale;
import org.eclipse.swt.internal.mozilla.nsDynamicFunctionLoad;
import org.eclipse.swt.internal.Compatibility;
import java.lang.reflect.Method;
import org.eclipse.swt.internal.mozilla.nsICategoryManager;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import org.eclipse.swt.internal.mozilla.init.GREVersionRange;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.swt.internal.mozilla.nsIDOMSerializer;
import org.eclipse.swt.internal.mozilla.nsIDOMSerializer_1_7;
import org.eclipse.swt.internal.mozilla.nsIProperties;
import org.eclipse.swt.internal.mozilla.nsIDirectoryService;
import org.eclipse.swt.internal.mozilla.nsIEmbeddingSiteWindow;
import org.eclipse.swt.internal.mozilla.nsIWebBrowserChrome;
import org.eclipse.swt.internal.mozilla.nsIWindowWatcher;
import org.eclipse.swt.internal.mozilla.nsIDOMWindow;
import org.eclipse.swt.internal.mozilla.nsIJSContextStack;
import org.eclipse.swt.internal.mozilla.nsIScriptSecurityManager_1_9;
import org.eclipse.swt.internal.mozilla.nsIPrincipal;
import org.eclipse.swt.internal.mozilla.nsIScriptSecurityManager_1_9_1;
import org.eclipse.swt.internal.mozilla.nsIWebNavigation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.internal.mozilla.nsIWebProgressListener;
import org.eclipse.swt.internal.mozilla.nsIDocShell_1_8;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.nsIDocShell;
import org.eclipse.swt.internal.mozilla.nsIInterfaceRequestor;
import org.eclipse.swt.internal.mozilla.nsIComponentRegistrar;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.Library;
import org.eclipse.swt.widgets.Composite;
import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsICookieService_1_9;
import org.eclipse.swt.internal.mozilla.nsICookieService;
import org.eclipse.swt.internal.mozilla.nsIURI;
import org.eclipse.swt.internal.mozilla.nsIIOService;
import org.eclipse.swt.internal.mozilla.nsICookie;
import org.eclipse.swt.internal.mozilla.nsISimpleEnumerator;
import org.eclipse.swt.internal.mozilla.nsICookieManager;
import org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString;
import org.eclipse.swt.internal.mozilla.nsIComponentManager;
import org.eclipse.swt.internal.mozilla.nsIPrefBranch;
import org.eclipse.swt.internal.mozilla.init.XPCOMInit;
import org.eclipse.swt.internal.mozilla.nsIPrefService;
import org.eclipse.swt.internal.mozilla.nsIFile;
import org.eclipse.swt.internal.mozilla.nsILocalFile;
import org.eclipse.swt.internal.mozilla.nsEmbedString;
import org.eclipse.swt.internal.mozilla.nsIObserverService;
import org.eclipse.swt.internal.mozilla.nsIServiceManager;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.widgets.Event;
import java.util.Hashtable;
import org.eclipse.swt.internal.mozilla.nsIAppShell;
import java.util.Vector;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.mozilla.XPCOMObject;
import org.eclipse.swt.internal.mozilla.nsIWebBrowser;

class Mozilla extends WebBrowser
{
    int embedHandle;
    nsIWebBrowser webBrowser;
    Object webBrowserObject;
    MozillaDelegate delegate;
    XPCOMObject supports;
    XPCOMObject weakReference;
    XPCOMObject webProgressListener;
    XPCOMObject webBrowserChrome;
    XPCOMObject webBrowserChromeFocus;
    XPCOMObject embeddingSiteWindow;
    XPCOMObject interfaceRequestor;
    XPCOMObject supportsWeakReference;
    XPCOMObject contextMenuListener;
    XPCOMObject uriContentListener;
    XPCOMObject tooltipListener;
    XPCOMObject domEventListener;
    XPCOMObject badCertListener;
    int chromeFlags;
    int registerFunctionsOnState;
    int refCount;
    int lastKeyCode;
    int lastCharCode;
    int authCount;
    int request;
    Point location;
    Point size;
    boolean visible;
    boolean isChild;
    boolean ignoreDispose;
    boolean isRetrievingBadCert;
    boolean isViewingErrorPage;
    boolean ignoreAllMessages;
    boolean untrustedText;
    boolean updateLastNavigateUrl;
    Shell tip;
    Listener listener;
    Vector unhookedDOMWindows;
    String lastNavigateURL;
    byte[] htmlBytes;
    static nsIAppShell AppShell;
    static AppFileLocProvider LocationProvider;
    static WindowCreator2 WindowCreator;
    static int BrowserCount;
    static int NextJSFunctionIndex;
    static Hashtable AllFunctions;
    static Listener DisplayListener;
    static boolean Initialized;
    static boolean IsPre_1_8;
    static boolean IsPre_1_9;
    static boolean PerformedVersionCheck;
    static boolean XPCOMWasGlued;
    static boolean XPCOMInitWasGlued;
    static String oldProxyHostFTP;
    static String oldProxyHostHTTP;
    static String oldProxyHostSSL;
    static int oldProxyPortFTP;
    static int oldProxyPortHTTP;
    static int oldProxyPortSSL;
    static int oldProxyType;
    static byte[] pathBytes_JSEvaluateUCScriptForPrincipals;
    static byte[] pathBytes_NSFree;
    static final String GRERANGE_LOWER = "1.8.1.2";
    static final String GRERANGE_LOWER_FALLBACK = "1.8";
    static final boolean LowerRangeInclusive = true;
    static final String GRERANGE_UPPER = "1.9.*";
    static final boolean UpperRangeInclusive = true;
    static final int MAX_PORT = 65535;
    static final String DEFAULTVALUE_STRING = "default";
    static final char SEPARATOR_OS;
    static final String ABOUT_BLANK = "about:blank";
    static final String DISPOSE_LISTENER_HOOKED = "org.eclipse.swt.browser.Mozilla.disposeListenerHooked";
    static final String HEADER_CONTENTTYPE = "Content-Type";
    static final String MIMETYPE_FORMURLENCODED = "application/x-www-form-urlencoded";
    static final String PREFIX_JAVASCRIPT = "javascript:";
    static final String PREFERENCE_CHARSET = "intl.charset.default";
    static final String PREFERENCE_DISABLEOPENDURINGLOAD = "dom.disable_open_during_load";
    static final String PREFERENCE_DISABLEOPENWINDOWSTATUSHIDE = "dom.disable_window_open_feature.status";
    static final String PREFERENCE_DISABLEWINDOWSTATUSCHANGE = "dom.disable_window_status_change";
    static final String PREFERENCE_JAVASCRIPTENABLED = "javascript.enabled";
    static final String PREFERENCE_LANGUAGES = "intl.accept_languages";
    static final String PREFERENCE_PROXYHOST_FTP = "network.proxy.ftp";
    static final String PREFERENCE_PROXYPORT_FTP = "network.proxy.ftp_port";
    static final String PREFERENCE_PROXYHOST_HTTP = "network.proxy.http";
    static final String PREFERENCE_PROXYPORT_HTTP = "network.proxy.http_port";
    static final String PREFERENCE_PROXYHOST_SSL = "network.proxy.ssl";
    static final String PREFERENCE_PROXYPORT_SSL = "network.proxy.ssl_port";
    static final String PREFERENCE_PROXYTYPE = "network.proxy.type";
    static final String PROFILE_AFTER_CHANGE = "profile-after-change";
    static final String PROFILE_BEFORE_CHANGE = "profile-before-change";
    static final String PROFILE_DIR;
    static final String PROFILE_DO_CHANGE = "profile-do-change";
    static final String PROPERTY_PROXYPORT = "network.proxy_port";
    static final String PROPERTY_PROXYHOST = "network.proxy_host";
    static final String SEPARATOR_LOCALE = "-";
    static final String SHUTDOWN_PERSIST = "shutdown-persist";
    static final String STARTUP = "startup";
    static final String TOKENIZER_LOCALE = ",";
    static final String TRUE = "true";
    static final String URI_FILEROOT = "file:///";
    static final String XULRUNNER_PATH = "org.eclipse.swt.browser.XULRunnerPath";
    static final String FACTORIES_REGISTERED = "org.eclipse.swt.browser.MozillaFactoriesRegistered";
    static final String GRE_INITIALIZED = "org.eclipse.swt.browser.XULRunnerInitialized";
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        Mozilla.NextJSFunctionIndex = 1;
        Mozilla.AllFunctions = new Hashtable();
        Mozilla.oldProxyPortFTP = -1;
        Mozilla.oldProxyPortHTTP = -1;
        Mozilla.oldProxyPortSSL = -1;
        Mozilla.oldProxyType = -1;
        SEPARATOR_OS = System.getProperty("file.separator").charAt(0);
        PROFILE_DIR = String.valueOf(Mozilla.SEPARATOR_OS) + "eclipse" + Mozilla.SEPARATOR_OS;
        Mozilla.DisplayListener = new Listener() {
            public void handleEvent(final Event event) {
                if (Mozilla.BrowserCount > 0) {
                    return;
                }
                final int[] array = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
                if (ns_GetServiceManager != 0) {
                    Mozilla.error(ns_GetServiceManager);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
                array[0] = 0;
                final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/observer-service;1", true), nsIObserverService.NS_IOBSERVERSERVICE_IID, array);
                if (getServiceByContractID != 0) {
                    Mozilla.error(getServiceByContractID);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIObserverService nsIObserverService = new nsIObserverService(array[0]);
                array[0] = 0;
                final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "profile-before-change", true);
                final int length = "shutdown-persist".length();
                final char[] array2 = new char[length + 1];
                "shutdown-persist".getChars(0, length, array2, 0);
                final int notifyObservers = nsIObserverService.NotifyObservers(0, wcsToMbcs, array2);
                if (notifyObservers != 0) {
                    Mozilla.error(notifyObservers);
                }
                nsIObserverService.Release();
                if (Mozilla.LocationProvider != null) {
                    final nsEmbedString nsEmbedString = new nsEmbedString(String.valueOf(Mozilla.LocationProvider.profilePath) + "prefs.js");
                    final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array);
                    if (ns_NewLocalFile != 0) {
                        Mozilla.error(ns_NewLocalFile);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467261);
                    }
                    nsEmbedString.dispose();
                    final nsILocalFile nsILocalFile = new nsILocalFile(array[0]);
                    array[0] = 0;
                    final int queryInterface = nsILocalFile.QueryInterface(nsIFile.NS_IFILE_IID, array);
                    if (queryInterface != 0) {
                        Mozilla.error(queryInterface);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    nsILocalFile.Release();
                    final nsIFile nsIFile = new nsIFile(array[0]);
                    array[0] = 0;
                    final int getServiceByContractID2 = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/preferences-service;1", true), nsIPrefService.NS_IPREFSERVICE_IID, array);
                    if (getServiceByContractID2 != 0) {
                        Mozilla.error(getServiceByContractID2);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    final nsIPrefService nsIPrefService = new nsIPrefService(array[0]);
                    array[0] = 0;
                    this.revertProxySettings(nsIPrefService);
                    nsIPrefService.SavePrefFile(nsIFile.getAddress());
                    nsIPrefService.Release();
                    nsIFile.Release();
                }
                nsIServiceManager.Release();
                if (Mozilla.XPCOMWasGlued) {
                    Mozilla.XPCOMWasGlued = false;
                }
                if (Mozilla.XPCOMInitWasGlued) {
                    XPCOMInit.XPCOMGlueShutdown();
                    Mozilla.XPCOMInitWasGlued = false;
                }
                Mozilla.Initialized = (Mozilla.PerformedVersionCheck = false);
            }
            
            void revertProxySettings(final nsIPrefService nsIPrefService) {
                final boolean b = Mozilla.oldProxyHostFTP != null || Mozilla.oldProxyHostHTTP != null || Mozilla.oldProxyHostSSL != null;
                if (!b && Mozilla.oldProxyPortFTP == -1 && Mozilla.oldProxyPortHTTP == -1 && Mozilla.oldProxyPortSSL == -1 && Mozilla.oldProxyType == -1) {
                    return;
                }
                final int[] array = { 0 };
                final int getBranch = nsIPrefService.GetBranch(new byte[1], array);
                if (getBranch != 0) {
                    Mozilla.error(getBranch);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIPrefBranch nsIPrefBranch = new nsIPrefBranch(array[0]);
                array[0] = 0;
                if (b) {
                    final int ns_GetComponentManager = XPCOM.NS_GetComponentManager(array);
                    if (ns_GetComponentManager != 0) {
                        Mozilla.error(ns_GetComponentManager);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    final nsIComponentManager nsIComponentManager = new nsIComponentManager(array[0]);
                    array[0] = 0;
                    final int createInstanceByContractID = nsIComponentManager.CreateInstanceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/pref-localizedstring;1", true), 0, nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array);
                    if (createInstanceByContractID != 0) {
                        Mozilla.error(createInstanceByContractID);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    final nsIPrefLocalizedString nsIPrefLocalizedString = new nsIPrefLocalizedString(array[0]);
                    array[0] = 0;
                    if (Mozilla.oldProxyHostFTP != null) {
                        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "network.proxy.ftp", true);
                        if (Mozilla.oldProxyHostFTP.equals("default")) {
                            final int clearUserPref = nsIPrefBranch.ClearUserPref(wcsToMbcs);
                            if (clearUserPref != 0) {
                                Mozilla.error(clearUserPref);
                            }
                        }
                        else {
                            final int length = Mozilla.oldProxyHostFTP.length();
                            final char[] array2 = new char[length];
                            Mozilla.oldProxyHostFTP.getChars(0, length, array2, 0);
                            final int setDataWithLength = nsIPrefLocalizedString.SetDataWithLength(length, array2);
                            if (setDataWithLength != 0) {
                                Mozilla.error(setDataWithLength);
                            }
                            final int setComplexValue = nsIPrefBranch.SetComplexValue(wcsToMbcs, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString.getAddress());
                            if (setComplexValue != 0) {
                                Mozilla.error(setComplexValue);
                            }
                        }
                    }
                    if (Mozilla.oldProxyHostHTTP != null) {
                        final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, "network.proxy.http", true);
                        if (Mozilla.oldProxyHostHTTP.equals("default")) {
                            final int clearUserPref2 = nsIPrefBranch.ClearUserPref(wcsToMbcs2);
                            if (clearUserPref2 != 0) {
                                Mozilla.error(clearUserPref2);
                            }
                        }
                        else {
                            final int length2 = Mozilla.oldProxyHostHTTP.length();
                            final char[] array3 = new char[length2];
                            Mozilla.oldProxyHostHTTP.getChars(0, length2, array3, 0);
                            final int setDataWithLength2 = nsIPrefLocalizedString.SetDataWithLength(length2, array3);
                            if (setDataWithLength2 != 0) {
                                Mozilla.error(setDataWithLength2);
                            }
                            final int setComplexValue2 = nsIPrefBranch.SetComplexValue(wcsToMbcs2, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString.getAddress());
                            if (setComplexValue2 != 0) {
                                Mozilla.error(setComplexValue2);
                            }
                        }
                    }
                    if (Mozilla.oldProxyHostSSL != null) {
                        final byte[] wcsToMbcs3 = MozillaDelegate.wcsToMbcs(null, "network.proxy.ssl", true);
                        if (Mozilla.oldProxyHostSSL.equals("default")) {
                            final int clearUserPref3 = nsIPrefBranch.ClearUserPref(wcsToMbcs3);
                            if (clearUserPref3 != 0) {
                                Mozilla.error(clearUserPref3);
                            }
                        }
                        else {
                            final int length3 = Mozilla.oldProxyHostSSL.length();
                            final char[] array4 = new char[length3];
                            Mozilla.oldProxyHostSSL.getChars(0, length3, array4, 0);
                            final int setDataWithLength3 = nsIPrefLocalizedString.SetDataWithLength(length3, array4);
                            if (setDataWithLength3 != 0) {
                                Mozilla.error(setDataWithLength3);
                            }
                            final int setComplexValue3 = nsIPrefBranch.SetComplexValue(wcsToMbcs3, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString.getAddress());
                            if (setComplexValue3 != 0) {
                                Mozilla.error(setComplexValue3);
                            }
                        }
                    }
                    nsIPrefLocalizedString.Release();
                    nsIComponentManager.Release();
                }
                if (Mozilla.oldProxyPortFTP != -1) {
                    final int setIntPref = nsIPrefBranch.SetIntPref(MozillaDelegate.wcsToMbcs(null, "network.proxy.ftp_port", true), Mozilla.oldProxyPortFTP);
                    if (setIntPref != 0) {
                        Mozilla.error(setIntPref);
                    }
                }
                if (Mozilla.oldProxyPortHTTP != -1) {
                    final int setIntPref2 = nsIPrefBranch.SetIntPref(MozillaDelegate.wcsToMbcs(null, "network.proxy.http_port", true), Mozilla.oldProxyPortHTTP);
                    if (setIntPref2 != 0) {
                        Mozilla.error(setIntPref2);
                    }
                }
                if (Mozilla.oldProxyPortSSL != -1) {
                    final int setIntPref3 = nsIPrefBranch.SetIntPref(MozillaDelegate.wcsToMbcs(null, "network.proxy.ssl_port", true), Mozilla.oldProxyPortSSL);
                    if (setIntPref3 != 0) {
                        Mozilla.error(setIntPref3);
                    }
                }
                if (Mozilla.oldProxyType != -1) {
                    final int setIntPref4 = nsIPrefBranch.SetIntPref(MozillaDelegate.wcsToMbcs(null, "network.proxy.type", true), Mozilla.oldProxyType);
                    if (setIntPref4 != 0) {
                        Mozilla.error(setIntPref4);
                    }
                }
                nsIPrefBranch.Release();
            }
        };
        Mozilla.MozillaClearSessions = new Runnable() {
            public void run() {
                if (!Mozilla.Initialized) {
                    return;
                }
                final int[] array = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
                if (ns_GetServiceManager != 0) {
                    Mozilla.error(ns_GetServiceManager);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
                array[0] = 0;
                final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/cookiemanager;1", true), nsICookieManager.NS_ICOOKIEMANAGER_IID, array);
                if (getServiceByContractID != 0) {
                    Mozilla.error(getServiceByContractID);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                nsIServiceManager.Release();
                final nsICookieManager nsICookieManager = new nsICookieManager(array[0]);
                array[0] = 0;
                final int getEnumerator = nsICookieManager.GetEnumerator(array);
                if (getEnumerator != 0) {
                    Mozilla.error(getEnumerator);
                }
                final nsISimpleEnumerator nsISimpleEnumerator = new nsISimpleEnumerator(array[0]);
                final int[] array2 = { 0 };
                final int hasMoreElements = nsISimpleEnumerator.HasMoreElements(array2);
                if (hasMoreElements != 0) {
                    Mozilla.error(hasMoreElements);
                }
                while (array2[0] != 0) {
                    array[0] = 0;
                    final int getNext = nsISimpleEnumerator.GetNext(array);
                    if (getNext != 0) {
                        Mozilla.error(getNext);
                    }
                    final nsICookie nsICookie = new nsICookie(array[0]);
                    final long[] array3 = { 0L };
                    nsICookie.GetExpires(array3);
                    if (array3[0] == 0L) {
                        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
                        final int nsEmbedCString_new2 = XPCOM.nsEmbedCString_new();
                        final int nsEmbedCString_new3 = XPCOM.nsEmbedCString_new();
                        nsICookie.GetHost(nsEmbedCString_new);
                        nsICookie.GetName(nsEmbedCString_new2);
                        nsICookie.GetPath(nsEmbedCString_new3);
                        final int remove = nsICookieManager.Remove(nsEmbedCString_new, nsEmbedCString_new2, nsEmbedCString_new3, 0);
                        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
                        XPCOM.nsEmbedCString_delete(nsEmbedCString_new2);
                        XPCOM.nsEmbedCString_delete(nsEmbedCString_new3);
                        if (remove != 0) {
                            Mozilla.error(remove);
                        }
                    }
                    nsICookie.Release();
                    final int hasMoreElements2 = nsISimpleEnumerator.HasMoreElements(array2);
                    if (hasMoreElements2 != 0) {
                        Mozilla.error(hasMoreElements2);
                    }
                }
                nsISimpleEnumerator.Release();
                nsICookieManager.Release();
            }
        };
        Mozilla.MozillaGetCookie = new Runnable() {
            public void run() {
                if (!Mozilla.Initialized) {
                    return;
                }
                final int[] array = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
                if (ns_GetServiceManager != 0) {
                    Mozilla.error(ns_GetServiceManager);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
                array[0] = 0;
                final int getService = nsIServiceManager.GetService(XPCOM.NS_IOSERVICE_CID, nsIIOService.NS_IIOSERVICE_IID, array);
                if (getService != 0) {
                    Mozilla.error(getService);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIIOService nsIIOService = new nsIIOService(array[0]);
                array[0] = 0;
                final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, Mozilla.CookieUrl, false);
                final int nsEmbedCString_new = XPCOM.nsEmbedCString_new(wcsToMbcs, wcsToMbcs.length);
                final int newURI = nsIIOService.NewURI(nsEmbedCString_new, null, 0, array);
                XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
                nsIIOService.Release();
                if (newURI != 0) {
                    nsIServiceManager.Release();
                    return;
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467261);
                }
                final nsIURI nsIURI = new nsIURI(array[0]);
                array[0] = 0;
                final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, "@mozilla.org/cookieService;1", true);
                int n;
                if (nsIServiceManager.GetServiceByContractID(wcsToMbcs2, nsICookieService.NS_ICOOKIESERVICE_IID, array) == 0 && array[0] != 0) {
                    final nsICookieService nsICookieService = new nsICookieService(array[0]);
                    array[0] = 0;
                    final int getCookieString = nsICookieService.GetCookieString(nsIURI.getAddress(), 0, array);
                    nsICookieService.Release();
                    if (getCookieString != 0) {
                        Mozilla.error(getCookieString);
                    }
                    if (array[0] == 0) {
                        nsIURI.Release();
                        nsIServiceManager.Release();
                        return;
                    }
                    n = array[0];
                }
                else {
                    array[0] = 0;
                    final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(wcsToMbcs2, nsICookieService_1_9.NS_ICOOKIESERVICE_IID, array);
                    if (getServiceByContractID != 0) {
                        Mozilla.error(getServiceByContractID);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    final nsICookieService_1_9 nsICookieService_1_9 = new nsICookieService_1_9(array[0]);
                    array[0] = 0;
                    final int getCookieString2 = nsICookieService_1_9.GetCookieString(nsIURI.getAddress(), 0, array);
                    nsICookieService_1_9.Release();
                    if (getCookieString2 != 0) {
                        Mozilla.error(getCookieString2);
                    }
                    if (array[0] == 0) {
                        nsIURI.Release();
                        nsIServiceManager.Release();
                        return;
                    }
                    n = array[0];
                }
                nsIURI.Release();
                nsIServiceManager.Release();
                array[0] = 0;
                final int strlen = C.strlen(n);
                final byte[] array2 = new byte[strlen];
                C.memmove(array2, n, strlen);
                if (Mozilla.pathBytes_NSFree == null) {
                    final String string = String.valueOf(Mozilla.getMozillaPath()) + MozillaDelegate.getLibraryName() + '\0';
                    try {
                        Mozilla.pathBytes_NSFree = string.getBytes("UTF-8");
                    }
                    catch (UnsupportedEncodingException ex) {
                        Mozilla.pathBytes_NSFree = string.getBytes();
                    }
                }
                if (!XPCOM.NS_Free(Mozilla.pathBytes_NSFree, n)) {
                    C.free(n);
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(new String(MozillaDelegate.mbcsToWcs(null, array2)), ";");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index = nextToken.indexOf(61);
                    if (index != -1 && nextToken.substring(0, index).trim().equals(Mozilla.CookieName)) {
                        Mozilla.CookieValue = nextToken.substring(index + 1).trim();
                    }
                }
            }
        };
        Mozilla.MozillaSetCookie = new Runnable() {
            public void run() {
                if (!Mozilla.Initialized) {
                    return;
                }
                final int[] array = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
                if (ns_GetServiceManager != 0) {
                    Mozilla.error(ns_GetServiceManager);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
                array[0] = 0;
                final int getService = nsIServiceManager.GetService(XPCOM.NS_IOSERVICE_CID, nsIIOService.NS_IIOSERVICE_IID, array);
                if (getService != 0) {
                    Mozilla.error(getService);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIIOService nsIIOService = new nsIIOService(array[0]);
                array[0] = 0;
                final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, Mozilla.CookieUrl, false);
                final int nsEmbedCString_new = XPCOM.nsEmbedCString_new(wcsToMbcs, wcsToMbcs.length);
                final int newURI = nsIIOService.NewURI(nsEmbedCString_new, null, 0, array);
                XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
                nsIIOService.Release();
                if (newURI != 0) {
                    nsIServiceManager.Release();
                    return;
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467261);
                }
                final nsIURI nsIURI = new nsIURI(array[0]);
                array[0] = 0;
                final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, Mozilla.CookieValue, true);
                final byte[] wcsToMbcs3 = MozillaDelegate.wcsToMbcs(null, "@mozilla.org/cookieService;1", true);
                int n;
                if (nsIServiceManager.GetServiceByContractID(wcsToMbcs3, nsICookieService.NS_ICOOKIESERVICE_IID, array) == 0 && array[0] != 0) {
                    final nsICookieService nsICookieService = new nsICookieService(array[0]);
                    n = nsICookieService.SetCookieString(nsIURI.getAddress(), 0, wcsToMbcs2, 0);
                    nsICookieService.Release();
                }
                else {
                    array[0] = 0;
                    final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(wcsToMbcs3, nsICookieService_1_9.NS_ICOOKIESERVICE_IID, array);
                    if (getServiceByContractID != 0) {
                        Mozilla.error(getServiceByContractID);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    final nsICookieService_1_9 nsICookieService_1_9 = new nsICookieService_1_9(array[0]);
                    n = nsICookieService_1_9.SetCookieString(nsIURI.getAddress(), 0, wcsToMbcs2, 0);
                    nsICookieService_1_9.Release();
                }
                array[0] = 0;
                nsIURI.Release();
                nsIServiceManager.Release();
                Mozilla.CookieResult = (n == 0);
            }
        };
    }
    
    Mozilla() {
        this.chromeFlags = 1;
        this.registerFunctionsOnState = 0;
        this.tip = null;
        this.unhookedDOMWindows = new Vector();
    }
    
    public void create(final Composite composite, final int n) {
        this.delegate = new MozillaDelegate(this.browser);
        final Display display = composite.getDisplay();
        final int[] array = { 0 };
        if (!Mozilla.Initialized) {
            boolean b = false;
            boolean b2 = false;
            if ("true".equals(System.getProperty("org.eclipse.swt.browser.XULRunnerInitialized"))) {
                Mozilla.Initialized = true;
            }
            String s = System.getProperty("org.eclipse.swt.browser.XULRunnerPath");
            if (s == null) {
                try {
                    Class.forName("org.eclipse.swt.browser.XULRunnerInitializer");
                    s = System.getProperty("org.eclipse.swt.browser.XULRunnerPath");
                }
                catch (ClassNotFoundException ex) {}
            }
            if (s == null) {
                try {
                    Library.loadLibrary(this.delegate.getSWTInitLibraryName());
                    b = true;
                }
                catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
            }
            else {
                String s2;
                if (Mozilla.SEPARATOR_OS == '/') {
                    s2 = s.replace('\\', Mozilla.SEPARATOR_OS);
                }
                else {
                    s2 = s.replace('/', Mozilla.SEPARATOR_OS);
                }
                s = String.valueOf(s2) + Mozilla.SEPARATOR_OS + MozillaDelegate.getLibraryName();
                b2 = true;
            }
            if (b) {
                s = this.initDiscoverXULRunner();
                b2 = (s.length() > 0);
                if (b2) {
                    if (XPCOMInit.XPCOMGlueStartup(MozillaDelegate.wcsToMbcs(null, s, true)) != 0) {
                        s = s.substring(0, s.lastIndexOf(Mozilla.SEPARATOR_OS));
                        if (Device.DEBUG) {
                            System.out.println("cannot use detected XULRunner: " + s);
                        }
                        final int getenv = C.getenv(MozillaDelegate.wcsToMbcs(null, "MOZILLA_FIVE_HOME", true));
                        if (getenv == 0) {
                            b2 = false;
                        }
                        else {
                            final int strlen = C.strlen(getenv);
                            final byte[] array2 = new byte[strlen];
                            C.memmove(array2, getenv, strlen);
                            s = new String(MozillaDelegate.mbcsToWcs(null, array2));
                            if (s.indexOf("xulrunner") == -1) {
                                b2 = false;
                            }
                            else {
                                final String string = String.valueOf(s) + Mozilla.SEPARATOR_OS + MozillaDelegate.getLibraryName();
                                if (XPCOMInit.XPCOMGlueStartup(MozillaDelegate.wcsToMbcs(null, string, true)) == 0) {
                                    if (Mozilla.SEPARATOR_OS == '/') {
                                        s = string.replace('\\', Mozilla.SEPARATOR_OS);
                                    }
                                    else {
                                        s = string.replace('/', Mozilla.SEPARATOR_OS);
                                    }
                                }
                                else {
                                    b2 = false;
                                    s = string.substring(0, string.lastIndexOf(Mozilla.SEPARATOR_OS));
                                    if (Device.DEBUG) {
                                        System.out.println("failed to start as XULRunner: " + s);
                                    }
                                }
                            }
                        }
                    }
                    if (b2) {
                        Mozilla.XPCOMInitWasGlued = true;
                    }
                }
            }
            String s3;
            if (b2) {
                s3 = this.initXULRunner(s);
            }
            else {
                if ((n & 0x8000) != 0x0) {
                    this.browser.dispose();
                    SWT.error(2, null, (s != null && s.length() > 0) ? (" [Failed to use detected XULRunner: " + s + "]") : " [Could not detect registered XULRunner to use]");
                }
                s3 = this.initMozilla(s);
            }
            if (!Mozilla.Initialized) {
                final String profilePath = this.delegate.getProfilePath();
                (Mozilla.LocationProvider = new AppFileLocProvider(s3, profilePath, b2)).AddRef();
                this.initExternal(profilePath);
                this.initXPCOM(s3, b2);
            }
            if (b2) {
                this.initJavaXPCOM(s3);
            }
            final int ns_GetComponentManager = XPCOM.NS_GetComponentManager(array);
            if (ns_GetComponentManager != 0) {
                this.browser.dispose();
                error(ns_GetComponentManager);
            }
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            final nsIComponentManager nsIComponentManager = new nsIComponentManager(array[0]);
            array[0] = 0;
            final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
            if (ns_GetServiceManager != 0) {
                this.browser.dispose();
                error(ns_GetServiceManager);
            }
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
            array[0] = 0;
            this.initSpinup(nsIComponentManager);
            boolean b3 = false;
            if ("true".equals(System.getProperty("org.eclipse.swt.browser.MozillaFactoriesRegistered"))) {
                b3 = true;
            }
            if (!b3) {
                this.initWindowCreator(nsIServiceManager);
            }
            this.initProfile(nsIServiceManager, b2);
            this.initPreferences(nsIServiceManager, nsIComponentManager);
            if (!b3) {
                this.initFactories(nsIServiceManager, nsIComponentManager, b2);
            }
            nsIServiceManager.Release();
            nsIComponentManager.Release();
            if (Mozilla.MozillaPendingCookies != null) {
                WebBrowser.SetPendingCookies(Mozilla.MozillaPendingCookies);
            }
            Mozilla.MozillaPendingCookies = null;
            Mozilla.Initialized = true;
        }
        ++Mozilla.BrowserCount;
        if (display.getData("org.eclipse.swt.browser.Mozilla.disposeListenerHooked") == null) {
            display.setData("org.eclipse.swt.browser.Mozilla.disposeListenerHooked", "org.eclipse.swt.browser.Mozilla.disposeListenerHooked");
            display.addListener(12, Mozilla.DisplayListener);
        }
        final int ns_GetComponentManager2 = XPCOM.NS_GetComponentManager(array);
        if (ns_GetComponentManager2 != 0) {
            this.browser.dispose();
            error(ns_GetComponentManager2);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIComponentManager nsIComponentManager2 = new nsIComponentManager(array[0]);
        array[0] = 0;
        final int createInstance = nsIComponentManager2.CreateInstance(new nsID("F1EAC761-87E9-11d3-AF80-00A024FFC08C"), 0, nsIWebBrowser.NS_IWEBBROWSER_IID, array);
        if (createInstance != 0) {
            this.browser.dispose();
            error(createInstance);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        this.webBrowser = new nsIWebBrowser(array[0]);
        array[0] = 0;
        this.createCOMInterfaces();
        this.AddRef();
        this.initWebBrowserWindows();
        if (!Mozilla.PerformedVersionCheck) {
            Mozilla.PerformedVersionCheck = true;
            final int queryInterface = nsIComponentManager2.QueryInterface(nsIComponentRegistrar.NS_ICOMPONENTREGISTRAR_IID, array);
            if (queryInterface != 0) {
                this.browser.dispose();
                error(queryInterface);
            }
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            final nsIComponentRegistrar nsIComponentRegistrar = new nsIComponentRegistrar(array[0]);
            array[0] = 0;
            boolean b4 = false;
            if ("true".equals(System.getProperty("org.eclipse.swt.browser.MozillaFactoriesRegistered"))) {
                b4 = true;
            }
            if (!b4) {
                final HelperAppLauncherDialogFactory helperAppLauncherDialogFactory = new HelperAppLauncherDialogFactory();
                helperAppLauncherDialogFactory.AddRef();
                final int registerFactory = nsIComponentRegistrar.RegisterFactory(XPCOM.NS_HELPERAPPLAUNCHERDIALOG_CID, MozillaDelegate.wcsToMbcs(null, "swtHelperAppLauncherDialog", true), MozillaDelegate.wcsToMbcs(null, "@mozilla.org/helperapplauncherdialog;1", true), helperAppLauncherDialogFactory.getAddress());
                if (registerFactory != 0) {
                    this.browser.dispose();
                    error(registerFactory);
                }
                helperAppLauncherDialogFactory.Release();
            }
            if (this.webBrowser.QueryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID, array) != 0) {
                this.browser.dispose();
                error(-2147467259);
            }
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            final nsIInterfaceRequestor nsIInterfaceRequestor = new nsIInterfaceRequestor(array[0]);
            array[0] = 0;
            if (nsIInterfaceRequestor.GetInterface(nsIDocShell.NS_IDOCSHELL_IID, array) == 0 && array[0] != 0) {
                Mozilla.IsPre_1_8 = true;
                new nsISupports(array[0]).Release();
            }
            array[0] = 0;
            Mozilla.IsPre_1_9 = true;
            if (!Mozilla.IsPre_1_8) {
                if (nsIInterfaceRequestor.GetInterface(nsIDocShell_1_8.NS_IDOCSHELL_IID, array) == 0 && array[0] != 0) {
                    new nsISupports(array[0]).Release();
                    array[0] = 0;
                    if (!b4) {
                        final DownloadFactory_1_8 downloadFactory_1_8 = new DownloadFactory_1_8();
                        downloadFactory_1_8.AddRef();
                        final int registerFactory2 = nsIComponentRegistrar.RegisterFactory(XPCOM.NS_DOWNLOAD_CID, MozillaDelegate.wcsToMbcs(null, "swtTransfer", true), MozillaDelegate.wcsToMbcs(null, "@mozilla.org/transfer;1", true), downloadFactory_1_8.getAddress());
                        if (registerFactory2 != 0) {
                            this.browser.dispose();
                            error(registerFactory2);
                        }
                        downloadFactory_1_8.Release();
                    }
                }
                else {
                    Mozilla.IsPre_1_9 = false;
                }
            }
            array[0] = 0;
            nsIInterfaceRequestor.Release();
            nsIComponentRegistrar.Release();
            System.setProperty("org.eclipse.swt.browser.MozillaFactoriesRegistered", "true");
        }
        nsIComponentManager2.Release();
        if (!Mozilla.IsPre_1_9) {
            this.delegate.addWindowSubclass();
        }
        final int addWebBrowserListener = this.webBrowser.AddWebBrowserListener(this.weakReference.getAddress(), nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID);
        if (addWebBrowserListener != 0) {
            this.browser.dispose();
            error(addWebBrowserListener);
        }
        final int setParentURIContentListener = this.webBrowser.SetParentURIContentListener(this.uriContentListener.getAddress());
        if (setParentURIContentListener != 0) {
            this.browser.dispose();
            error(setParentURIContentListener);
        }
        this.delegate.init();
        this.listener = new Listener() {
            final /* synthetic */ Mozilla this$0;
            
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        if (Mozilla.this.ignoreDispose) {
                            Mozilla.this.ignoreDispose = false;
                            break;
                        }
                        Mozilla.this.ignoreDispose = true;
                        Mozilla.this.browser.notifyListeners(event.type, event);
                        event.type = 0;
                        Mozilla.this.onDispose(event.display);
                        break;
                    }
                    case 11: {
                        Mozilla.this.onResize();
                        break;
                    }
                    case 15: {
                        Mozilla.this.Activate();
                        break;
                    }
                    case 26: {
                        Mozilla.this.Activate();
                        break;
                    }
                    case 27: {
                        if (Mozilla.this.browser == event.display.getFocusControl()) {
                            Mozilla.this.Deactivate();
                            break;
                        }
                        break;
                    }
                    case 22: {
                        event.display.asyncExec(new Runnable() {
                            final /* synthetic */ Mozilla$5 this$1 = this$1;
                            
                            public void run() {
                                if (this.this$1.this$0.browser.isDisposed()) {
                                    return;
                                }
                                this.this$1.this$0.onResize();
                            }
                        });
                        break;
                    }
                }
            }
        };
        final int[] array3 = { 12, 11, 15, 26, 27, 22, 1 };
        for (int i = 0; i < array3.length; ++i) {
            this.browser.addListener(array3[i], this.listener);
        }
    }
    
    public boolean back() {
        this.htmlBytes = null;
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int goBack = nsIWebNavigation.GoBack();
        nsIWebNavigation.Release();
        return goBack == 0;
    }
    
    public boolean close() {
        final boolean[] array = { false };
        final LocationListener[] locationListeners = this.locationListeners;
        this.locationListeners = new LocationListener[] { new LocationAdapter() {
                public void changing(final LocationEvent locationEvent) {
                    array[0] = true;
                }
            } };
        this.execute("window.location.replace('about:blank');");
        this.locationListeners = locationListeners;
        return array[0];
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
        };
        this.weakReference = new XPCOMObject(new int[] { 2, 0, 0, 2 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.QueryReferent(array[0], array[1]);
            }
        };
        this.webProgressListener = new XPCOMObject(new int[] { 2, 0, 0, 4, 6, 3, 4, 3 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.OnStateChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return Mozilla.this.OnProgressChange(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method5(final int[] array) {
                return Mozilla.this.OnLocationChange(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return Mozilla.this.OnStatusChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Mozilla.this.OnSecurityChange(array[0], array[1], array[2]);
            }
        };
        this.webBrowserChrome = new XPCOMObject(new int[] { 2, 0, 0, 2, 1, 1, 1, 1, 0, 2, 0, 1, 1 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.SetStatus(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return Mozilla.this.GetWebBrowser(array[0]);
            }
            
            public int method5(final int[] array) {
                return Mozilla.this.SetWebBrowser(array[0]);
            }
            
            public int method6(final int[] array) {
                return Mozilla.this.GetChromeFlags(array[0]);
            }
            
            public int method7(final int[] array) {
                return Mozilla.this.SetChromeFlags(array[0]);
            }
            
            public int method8(final int[] array) {
                return Mozilla.this.DestroyBrowserWindow();
            }
            
            public int method9(final int[] array) {
                return Mozilla.this.SizeBrowserTo(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return Mozilla.this.ShowAsModal();
            }
            
            public int method11(final int[] array) {
                return Mozilla.this.IsWindowModal(array[0]);
            }
            
            public int method12(final int[] array) {
                return Mozilla.this.ExitModalEventLoop(array[0]);
            }
        };
        final int[] array = new int[5];
        array[0] = 2;
        this.webBrowserChromeFocus = new XPCOMObject(array) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.FocusNextElement();
            }
            
            public int method4(final int[] array) {
                return Mozilla.this.FocusPrevElement();
            }
        };
        this.embeddingSiteWindow = new XPCOMObject(new int[] { 2, 0, 0, 5, 5, 0, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.SetDimensions(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method4(final int[] array) {
                return Mozilla.this.GetDimensions(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method5(final int[] array) {
                return Mozilla.this.SetFocus();
            }
            
            public int method6(final int[] array) {
                return Mozilla.this.GetVisibility(array[0]);
            }
            
            public int method7(final int[] array) {
                return Mozilla.this.SetVisibility(array[0]);
            }
            
            public int method8(final int[] array) {
                return Mozilla.this.GetTitle(array[0]);
            }
            
            public int method9(final int[] array) {
                return Mozilla.this.SetTitle(array[0]);
            }
            
            public int method10(final int[] array) {
                return Mozilla.this.GetSiteWindow(array[0]);
            }
        };
        this.interfaceRequestor = new XPCOMObject(new int[] { 2, 0, 0, 2 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.GetInterface(array[0], array[1]);
            }
        };
        this.supportsWeakReference = new XPCOMObject(new int[] { 2, 0, 0, 1 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.GetWeakReference(array[0]);
            }
        };
        this.contextMenuListener = new XPCOMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.OnShowContextMenu(array[0], array[1], array[2]);
            }
        };
        this.uriContentListener = new XPCOMObject(new int[] { 2, 0, 0, 2, 5, 3, 4, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.OnStartURIOpen(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return Mozilla.this.DoContent(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method5(final int[] array) {
                return Mozilla.this.IsPreferred(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return Mozilla.this.CanHandleContent(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Mozilla.this.GetLoadCookie(array[0]);
            }
            
            public int method8(final int[] array) {
                return Mozilla.this.SetLoadCookie(array[0]);
            }
            
            public int method9(final int[] array) {
                return Mozilla.this.GetParentContentListener(array[0]);
            }
            
            public int method10(final int[] array) {
                return Mozilla.this.SetParentContentListener(array[0]);
            }
        };
        this.tooltipListener = new XPCOMObject(new int[] { 2, 0, 0, 3, 0 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.OnShowTooltip(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return Mozilla.this.OnHideTooltip();
            }
        };
        this.domEventListener = new XPCOMObject(new int[] { 2, 0, 0, 1 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.HandleEvent(array[0]);
            }
        };
        this.badCertListener = new XPCOMObject(new int[] { 2, 0, 0, 4 }) {
            public int method0(final int[] array) {
                return Mozilla.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Mozilla.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Mozilla.this.Release();
            }
            
            public int method3(final int[] array) {
                return Mozilla.this.NotifyCertProblem(array[0], array[1], array[2], array[3]);
            }
        };
    }
    
    void deregisterFunction(final BrowserFunction browserFunction) {
        super.deregisterFunction(browserFunction);
        Mozilla.AllFunctions.remove(new Integer(browserFunction.index));
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.weakReference != null) {
            this.weakReference.dispose();
            this.weakReference = null;
        }
        if (this.webProgressListener != null) {
            this.webProgressListener.dispose();
            this.webProgressListener = null;
        }
        if (this.webBrowserChrome != null) {
            this.webBrowserChrome.dispose();
            this.webBrowserChrome = null;
        }
        if (this.webBrowserChromeFocus != null) {
            this.webBrowserChromeFocus.dispose();
            this.webBrowserChromeFocus = null;
        }
        if (this.embeddingSiteWindow != null) {
            this.embeddingSiteWindow.dispose();
            this.embeddingSiteWindow = null;
        }
        if (this.interfaceRequestor != null) {
            this.interfaceRequestor.dispose();
            this.interfaceRequestor = null;
        }
        if (this.supportsWeakReference != null) {
            this.supportsWeakReference.dispose();
            this.supportsWeakReference = null;
        }
        if (this.contextMenuListener != null) {
            this.contextMenuListener.dispose();
            this.contextMenuListener = null;
        }
        if (this.uriContentListener != null) {
            this.uriContentListener.dispose();
            this.uriContentListener = null;
        }
        if (this.tooltipListener != null) {
            this.tooltipListener.dispose();
            this.tooltipListener = null;
        }
        if (this.domEventListener != null) {
            this.domEventListener.dispose();
            this.domEventListener = null;
        }
        if (this.badCertListener != null) {
            this.badCertListener.dispose();
            this.badCertListener = null;
        }
    }
    
    public boolean execute(final String s) {
        this.delegate.removeWindowSubclass();
        final int[] array = { 0 };
        if (!Mozilla.IsPre_1_9) {
            final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
            if (ns_GetServiceManager != 0) {
                error(ns_GetServiceManager);
            }
            if (array[0] == 0) {
                error(-2147467262);
            }
            final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
            array[0] = 0;
            nsIPrincipal nsIPrincipal = null;
            final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "@mozilla.org/scriptsecuritymanager;1", true);
            if (nsIServiceManager.GetServiceByContractID(wcsToMbcs, nsIScriptSecurityManager_1_9_1.NS_ISCRIPTSECURITYMANAGER_IID, array) == 0 && array[0] != 0) {
                final nsIScriptSecurityManager_1_9_1 nsIScriptSecurityManager_1_9_1 = new nsIScriptSecurityManager_1_9_1(array[0]);
                array[0] = 0;
                final int getSystemPrincipal = nsIScriptSecurityManager_1_9_1.GetSystemPrincipal(array);
                if (getSystemPrincipal != 0) {
                    error(getSystemPrincipal);
                }
                if (array[0] == 0) {
                    error(-2147467261);
                }
                nsIPrincipal = new nsIPrincipal(array[0]);
                array[0] = 0;
                nsIScriptSecurityManager_1_9_1.Release();
            }
            else if (nsIServiceManager.GetServiceByContractID(wcsToMbcs, nsIScriptSecurityManager_1_9.NS_ISCRIPTSECURITYMANAGER_IID, array) == 0 && array[0] != 0) {
                final nsIScriptSecurityManager_1_9 nsIScriptSecurityManager_1_9 = new nsIScriptSecurityManager_1_9(array[0]);
                array[0] = 0;
                final int getSystemPrincipal2 = nsIScriptSecurityManager_1_9.GetSystemPrincipal(array);
                if (getSystemPrincipal2 != 0) {
                    error(getSystemPrincipal2);
                }
                if (array[0] == 0) {
                    error(-2147467261);
                }
                nsIPrincipal = new nsIPrincipal(array[0]);
                array[0] = 0;
                nsIScriptSecurityManager_1_9.Release();
            }
            if (nsIPrincipal != null) {
                final int queryInterface = this.webBrowser.QueryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID, array);
                if (queryInterface != 0) {
                    error(queryInterface);
                }
                if (array[0] == 0) {
                    error(-2147467262);
                }
                final nsIInterfaceRequestor nsIInterfaceRequestor = new nsIInterfaceRequestor(array[0]);
                array[0] = 0;
                int n = nsIInterfaceRequestor.GetInterface(new nsID("6afecd40-0b9a-4cfd-8c42-0f645cd91829"), array);
                if (n != 0 || array[0] == 0) {
                    array[0] = 0;
                    n = nsIInterfaceRequestor.GetInterface(new nsID("e9f3f2c1-2d94-4722-bbd4-2bf6fdf42f48"), array);
                }
                nsIInterfaceRequestor.Release();
                if (n == 0 && array[0] != 0) {
                    final int n2 = array[0];
                    array[0] = 0;
                    final int nsIScriptGlobalObject_EnsureScriptEnvironment = XPCOM.nsIScriptGlobalObject_EnsureScriptEnvironment(n2, 2);
                    if (nsIScriptGlobalObject_EnsureScriptEnvironment != 0) {
                        error(nsIScriptGlobalObject_EnsureScriptEnvironment);
                    }
                    final int nsIScriptGlobalObject_GetScriptContext = XPCOM.nsIScriptGlobalObject_GetScriptContext(n2, 2);
                    final int nsIScriptGlobalObject_GetScriptGlobal = XPCOM.nsIScriptGlobalObject_GetScriptGlobal(n2, 2);
                    new nsISupports(n2).Release();
                    if (nsIScriptGlobalObject_GetScriptContext != 0 && nsIScriptGlobalObject_GetScriptGlobal != 0) {
                        int n3 = new nsISupports(nsIScriptGlobalObject_GetScriptContext).QueryInterface(new nsID("e7b9871d-3adc-4bf7-850d-7fb9554886bf"), array);
                        if (n3 != 0 || array[0] == 0) {
                            array[0] = 0;
                            n3 = new nsISupports(nsIScriptGlobalObject_GetScriptContext).QueryInterface(new nsID("87482b5e-e019-4df5-9bc2-b2a51b1f2d28"), array);
                        }
                        if (n3 == 0 && array[0] != 0) {
                            new nsISupports(array[0]).Release();
                            array[0] = 0;
                            final int nsIScriptContext_GetNativeContext = XPCOM.nsIScriptContext_GetNativeContext(nsIScriptGlobalObject_GetScriptContext);
                            if (nsIScriptContext_GetNativeContext != 0) {
                                final int length = s.length();
                                final char[] array2 = new char[length];
                                s.getChars(0, length, array2, 0);
                                final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, this.getUrl(), true);
                                if (nsIPrincipal.GetJSPrincipals(nsIScriptContext_GetNativeContext, array) == 0 && array[0] != 0) {
                                    final int n4 = array[0];
                                    array[0] = 0;
                                    nsIPrincipal.Release();
                                    if (Mozilla.pathBytes_JSEvaluateUCScriptForPrincipals == null) {
                                        final String string = String.valueOf(getMozillaPath()) + this.delegate.getJSLibraryName() + '\0';
                                        try {
                                            Mozilla.pathBytes_JSEvaluateUCScriptForPrincipals = string.getBytes("UTF-8");
                                        }
                                        catch (UnsupportedEncodingException ex) {
                                            Mozilla.pathBytes_JSEvaluateUCScriptForPrincipals = string.getBytes();
                                        }
                                    }
                                    final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/js/xpc/ContextStack;1", true), nsIJSContextStack.NS_IJSCONTEXTSTACK_IID, array);
                                    if (getServiceByContractID != 0) {
                                        error(getServiceByContractID);
                                    }
                                    if (array[0] == 0) {
                                        error(-2147467262);
                                    }
                                    nsIServiceManager.Release();
                                    final nsIJSContextStack nsIJSContextStack = new nsIJSContextStack(array[0]);
                                    array[0] = 0;
                                    final int push = nsIJSContextStack.Push(nsIScriptContext_GetNativeContext);
                                    if (push != 0) {
                                        error(push);
                                    }
                                    final boolean b = XPCOM.JS_EvaluateUCScriptForPrincipals(Mozilla.pathBytes_JSEvaluateUCScriptForPrincipals, nsIScriptContext_GetNativeContext, nsIScriptGlobalObject_GetScriptGlobal, n4, array2, length, wcsToMbcs2, 0, array) != 0;
                                    array[0] = 0;
                                    final int pop = nsIJSContextStack.Pop(array);
                                    if (pop != 0) {
                                        error(pop);
                                    }
                                    nsIJSContextStack.Release();
                                    return b;
                                }
                            }
                        }
                    }
                }
                nsIPrincipal.Release();
            }
            nsIServiceManager.Release();
        }
        final String string2 = "javascript:" + s + ";void(0);";
        final int queryInterface2 = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface2 != 0) {
            error(queryInterface2);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final char[] charArray = string2.toCharArray();
        final char[] array3 = new char[charArray.length + 1];
        System.arraycopy(charArray, 0, array3, 0, charArray.length);
        final int loadURI = nsIWebNavigation.LoadURI(array3, 0, 0, 0, 0);
        nsIWebNavigation.Release();
        return loadURI == 0;
    }
    
    static Browser findBrowser(final int n) {
        return MozillaDelegate.findBrowser(n);
    }
    
    static Browser findBrowser(final nsIDOMWindow nsIDOMWindow) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/embedcomp/window-watcher;1", true), nsIWindowWatcher.NS_IWINDOWWATCHER_IID, array);
        if (getServiceByContractID != 0) {
            error(getServiceByContractID);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIWindowWatcher nsIWindowWatcher = new nsIWindowWatcher(array[0]);
        array[0] = 0;
        final int getTop = nsIDOMWindow.GetTop(array);
        if (getTop != 0) {
            error(getTop);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final int n = array[0];
        array[0] = 0;
        final int getChromeForWindow = nsIWindowWatcher.GetChromeForWindow(n, array);
        if (getChromeForWindow != 0) {
            error(getChromeForWindow);
        }
        new nsISupports(n).Release();
        nsIWindowWatcher.Release();
        if (array[0] == 0) {
            return null;
        }
        final nsIWebBrowserChrome nsIWebBrowserChrome = new nsIWebBrowserChrome(array[0]);
        array[0] = 0;
        final int queryInterface = nsIWebBrowserChrome.QueryInterface(nsIEmbeddingSiteWindow.NS_IEMBEDDINGSITEWINDOW_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIWebBrowserChrome.Release();
        final nsIEmbeddingSiteWindow nsIEmbeddingSiteWindow = new nsIEmbeddingSiteWindow(array[0]);
        array[0] = 0;
        final int getSiteWindow = nsIEmbeddingSiteWindow.GetSiteWindow(array);
        if (getSiteWindow != 0) {
            error(getSiteWindow);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIEmbeddingSiteWindow.Release();
        return findBrowser(array[0]);
    }
    
    public boolean forward() {
        this.htmlBytes = null;
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int goForward = nsIWebNavigation.GoForward();
        nsIWebNavigation.Release();
        return goForward == 0;
    }
    
    public String getBrowserType() {
        return "mozilla";
    }
    
    static String getMozillaPath() {
        if (Mozilla.LocationProvider != null) {
            return Mozilla.LocationProvider.mozillaPath;
        }
        if (!Mozilla.Initialized) {
            return "";
        }
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/file/directory_service;1", true), nsIDirectoryService.NS_IDIRECTORYSERVICE_IID, array);
        if (getServiceByContractID != 0) {
            error(getServiceByContractID);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIDirectoryService nsIDirectoryService = new nsIDirectoryService(array[0]);
        array[0] = 0;
        final int queryInterface = nsIDirectoryService.QueryInterface(nsIProperties.NS_IPROPERTIES_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIDirectoryService.Release();
        final nsIProperties nsIProperties = new nsIProperties(array[0]);
        array[0] = 0;
        final int get = nsIProperties.Get(MozillaDelegate.wcsToMbcs(null, "GreD", true), nsIFile.NS_IFILE_IID, array);
        if (get != 0) {
            error(get);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIProperties.Release();
        final nsIFile nsIFile = new nsIFile(array[0]);
        array[0] = 0;
        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
        final int getNativePath = nsIFile.GetNativePath(nsEmbedCString_new);
        if (getNativePath != 0) {
            error(getNativePath);
        }
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final byte[] array2 = new byte[nsEmbedCString_Length];
        C.memmove(array2, nsEmbedCString_get, nsEmbedCString_Length);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        nsIFile.Release();
        return String.valueOf(new String(MozillaDelegate.mbcsToWcs(null, array2))) + Mozilla.SEPARATOR_OS;
    }
    
    int getNextFunctionIndex() {
        return Mozilla.NextJSFunctionIndex++;
    }
    
    public String getText() {
        final int[] array = { 0 };
        final int getContentDOMWindow = this.webBrowser.GetContentDOMWindow(array);
        if (getContentDOMWindow != 0) {
            error(getContentDOMWindow);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIDOMWindow nsIDOMWindow = new nsIDOMWindow(array[0]);
        array[0] = 0;
        final int getDocument = nsIDOMWindow.GetDocument(array);
        if (getDocument != 0) {
            error(getDocument);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIDOMWindow.Release();
        final int n = array[0];
        array[0] = 0;
        final int ns_GetComponentManager = XPCOM.NS_GetComponentManager(array);
        if (ns_GetComponentManager != 0) {
            error(ns_GetComponentManager);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIComponentManager nsIComponentManager = new nsIComponentManager(array[0]);
        array[0] = 0;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xmlextras/xmlserializer;1", true);
        final char[] array2 = null;
        char[] array3;
        if (nsIComponentManager.CreateInstanceByContractID(wcsToMbcs, 0, nsIDOMSerializer_1_7.NS_IDOMSERIALIZER_IID, array) == 0) {
            if (array[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMSerializer_1_7 nsIDOMSerializer_1_7 = new nsIDOMSerializer_1_7(array[0]);
            array[0] = 0;
            final int nsEmbedString_new = XPCOM.nsEmbedString_new();
            nsIDOMSerializer_1_7.SerializeToString(n, nsEmbedString_new);
            nsIDOMSerializer_1_7.Release();
            final int nsEmbedString_Length = XPCOM.nsEmbedString_Length(nsEmbedString_new);
            final int nsEmbedString_get = XPCOM.nsEmbedString_get(nsEmbedString_new);
            array3 = new char[nsEmbedString_Length];
            C.memmove(array3, nsEmbedString_get, nsEmbedString_Length * 2);
            XPCOM.nsEmbedString_delete(nsEmbedString_new);
        }
        else {
            final int createInstanceByContractID = nsIComponentManager.CreateInstanceByContractID(wcsToMbcs, 0, nsIDOMSerializer.NS_IDOMSERIALIZER_IID, array);
            if (createInstanceByContractID != 0) {
                error(createInstanceByContractID);
            }
            if (array[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMSerializer nsIDOMSerializer = new nsIDOMSerializer(array[0]);
            array[0] = 0;
            nsIDOMSerializer.SerializeToString(n, array);
            nsIDOMSerializer.Release();
            final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(array[0]);
            array3 = new char[strlen_PRUnichar];
            C.memmove(array3, array[0], strlen_PRUnichar * 2);
        }
        nsIComponentManager.Release();
        new nsISupports(n).Release();
        return new String(array3);
    }
    
    public String getUrl() {
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int[] array2 = { 0 };
        final int getCurrentURI = nsIWebNavigation.GetCurrentURI(array2);
        if (getCurrentURI != 0) {
            error(getCurrentURI);
        }
        nsIWebNavigation.Release();
        byte[] array3 = null;
        if (array2[0] != 0) {
            final nsIURI nsIURI = new nsIURI(array2[0]);
            final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
            final int getSpec = nsIURI.GetSpec(nsEmbedCString_new);
            if (getSpec != 0) {
                error(getSpec);
            }
            final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
            final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
            array3 = new byte[nsEmbedCString_Length];
            C.memmove(array3, nsEmbedCString_get, nsEmbedCString_Length);
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
            nsIURI.Release();
        }
        if (array3 == null) {
            return "";
        }
        String string = new String(array3);
        if (string.equals("file:///")) {
            string = "about:blank";
        }
        else {
            final int length = "file:///".length();
            if (string.startsWith("file:///") && string.charAt(length) == '#') {
                string = "about:blank" + string.substring(length);
            }
        }
        return string;
    }
    
    public Object getWebBrowser() {
        if ((this.browser.getStyle() & 0x8000) == 0x0) {
            return null;
        }
        if (this.webBrowserObject != null) {
            return this.webBrowserObject;
        }
        try {
            final Class<?> forName = Class.forName("org.mozilla.xpcom.Mozilla");
            final Object invoke = forName.getMethod("getInstance", (Class[])new Class[0]).invoke(null, new Object[0]);
            final Class<?> clazz = forName;
            final String s = "wrapXPCOMObject";
            final Class[] array = { Long.TYPE, null };
            final int n = 1;
            Class class$0;
            if ((class$0 = Mozilla.class$0) == null) {
                try {
                    class$0 = (Mozilla.class$0 = Class.forName("java.lang.String"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n] = class$0;
            this.webBrowserObject = clazz.getMethod(s, (Class[])array).invoke(invoke, new Long(this.webBrowser.getAddress()), "69e5df00-7b8b-11d3-af61-00a024ffc08c");
            this.webBrowser.AddRef();
            return this.webBrowserObject;
        }
        catch (ClassNotFoundException ex2) {}
        catch (NoSuchMethodException ex3) {}
        catch (IllegalArgumentException ex4) {}
        catch (IllegalAccessException ex5) {}
        catch (InvocationTargetException ex6) {}
        return null;
    }
    
    String initDiscoverXULRunner() {
        final GREVersionRange greVersionRange = new GREVersionRange();
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "1.8.1.2", true);
        int n = C.malloc(wcsToMbcs.length);
        C.memmove(n, wcsToMbcs, wcsToMbcs.length);
        greVersionRange.lower = n;
        greVersionRange.lowerInclusive = true;
        final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, "1.9.*", true);
        final int malloc = C.malloc(wcsToMbcs2.length);
        C.memmove(malloc, wcsToMbcs2, wcsToMbcs2.length);
        greVersionRange.upper = malloc;
        greVersionRange.upperInclusive = true;
        final int n2 = 4096;
        final int malloc2 = C.malloc(n2);
        final int malloc3 = C.malloc(2 * C.PTR_SIZEOF);
        int n3 = XPCOMInit.GRE_GetGREPathWithProperties(greVersionRange, 1, malloc3, 0, malloc2, n2);
        if (n3 != 0) {
            C.free(n);
            final byte[] wcsToMbcs3 = MozillaDelegate.wcsToMbcs(null, "1.8", true);
            n = C.malloc(wcsToMbcs3.length);
            C.memmove(n, wcsToMbcs3, wcsToMbcs3.length);
            greVersionRange.lower = n;
            n3 = XPCOMInit.GRE_GetGREPathWithProperties(greVersionRange, 1, malloc3, 0, malloc2, n2);
        }
        C.free(n);
        C.free(malloc);
        C.free(malloc3);
        String s;
        if (n3 == 0) {
            final int strlen = C.strlen(malloc2);
            final byte[] array = new byte[strlen];
            C.memmove(array, malloc2, strlen);
            s = new String(MozillaDelegate.mbcsToWcs(null, array));
        }
        else {
            s = "";
        }
        C.free(malloc2);
        return s;
    }
    
    void initExternal(final String s) {
        final File file = new File(s, "components");
        Class class$1;
        if ((class$1 = Mozilla.class$1) == null) {
            try {
                class$1 = (Mozilla.class$1 = Class.forName("org.eclipse.swt.internal.Library"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final InputStream resourceAsStream = class$1.getResourceAsStream("/external.xpt");
        if (resourceAsStream != null) {
            if (!file.exists()) {
                file.mkdirs();
            }
            final byte[] array = new byte[4096];
            final File file2 = new File(file, "external.xpt");
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                int read;
                while ((read = resourceAsStream.read(array)) != -1) {
                    fileOutputStream.write(array, 0, read);
                }
                fileOutputStream.close();
                resourceAsStream.close();
            }
            catch (FileNotFoundException ex2) {}
            catch (IOException ex3) {}
        }
    }
    
    void initFactories(final nsIServiceManager nsIServiceManager, final nsIComponentManager nsIComponentManager, final boolean b) {
        final int[] array = { 0 };
        final int queryInterface = nsIComponentManager.QueryInterface(nsIComponentRegistrar.NS_ICOMPONENTREGISTRAR_IID, array);
        if (queryInterface != 0) {
            this.browser.dispose();
            error(queryInterface);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIComponentRegistrar nsIComponentRegistrar = new nsIComponentRegistrar(array[0]);
        nsIComponentRegistrar.AutoRegister(array[0] = 0);
        final PromptService2Factory promptService2Factory = new PromptService2Factory();
        promptService2Factory.AddRef();
        final int registerFactory = nsIComponentRegistrar.RegisterFactory(XPCOM.NS_PROMPTSERVICE_CID, MozillaDelegate.wcsToMbcs(null, "swtPromptService", true), MozillaDelegate.wcsToMbcs(null, "@mozilla.org/embedcomp/prompt-service;1", true), promptService2Factory.getAddress());
        if (registerFactory != 0) {
            this.browser.dispose();
            error(registerFactory);
        }
        promptService2Factory.Release();
        final ExternalFactory externalFactory = new ExternalFactory();
        externalFactory.AddRef();
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "@eclipse.org/external;1", true);
        final int registerFactory2 = nsIComponentRegistrar.RegisterFactory(XPCOM.EXTERNAL_CID, MozillaDelegate.wcsToMbcs(null, "External", true), wcsToMbcs, externalFactory.getAddress());
        if (registerFactory2 != 0) {
            this.browser.dispose();
            error(registerFactory2);
        }
        externalFactory.Release();
        final int getService = nsIServiceManager.GetService(XPCOM.NS_CATEGORYMANAGER_CID, nsICategoryManager.NS_ICATEGORYMANAGER_IID, array);
        if (getService != 0) {
            error(getService);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsICategoryManager nsICategoryManager = new nsICategoryManager(array[0]);
        array[0] = 0;
        nsICategoryManager.AddCategoryEntry(MozillaDelegate.wcsToMbcs(null, "JavaScript global property", true), MozillaDelegate.wcsToMbcs(null, "external", true), wcsToMbcs, 1, 1, array);
        array[0] = 0;
        nsICategoryManager.Release();
        final DownloadFactory downloadFactory = new DownloadFactory();
        downloadFactory.AddRef();
        final int registerFactory3 = nsIComponentRegistrar.RegisterFactory(XPCOM.NS_DOWNLOAD_CID, MozillaDelegate.wcsToMbcs(null, "swtDownload", true), MozillaDelegate.wcsToMbcs(null, "@mozilla.org/download;1", true), downloadFactory.getAddress());
        if (registerFactory3 != 0) {
            this.browser.dispose();
            error(registerFactory3);
        }
        downloadFactory.Release();
        final FilePickerFactory filePickerFactory = b ? new FilePickerFactory_1_8() : new FilePickerFactory();
        filePickerFactory.AddRef();
        final int registerFactory4 = nsIComponentRegistrar.RegisterFactory(XPCOM.NS_FILEPICKER_CID, MozillaDelegate.wcsToMbcs(null, "swtFilePicker", true), MozillaDelegate.wcsToMbcs(null, "@mozilla.org/filepicker;1", true), filePickerFactory.getAddress());
        if (registerFactory4 != 0) {
            this.browser.dispose();
            error(registerFactory4);
        }
        filePickerFactory.Release();
        nsIComponentRegistrar.Release();
    }
    
    void initJavaXPCOM(final String s) {
        try {
            final Class<?> forName = Class.forName("org.mozilla.xpcom.Mozilla");
            final Object invoke = forName.getMethod("getInstance", (Class[])new Class[0]).invoke(null, new Object[0]);
            final Method method = forName.getMethod("getComponentManager", (Class[])new Class[0]);
            try {
                method.invoke(invoke, new Object[0]);
            }
            catch (InvocationTargetException ex2) {
                final Class<?> forName2 = Class.forName("java.io.File");
                final Method method2 = forName.getMethod("initialize", forName2);
                final Class<?> clazz = forName2;
                final Class[] array = { null };
                final int n = 0;
                Class class$0;
                if ((class$0 = Mozilla.class$0) == null) {
                    try {
                        class$0 = (Mozilla.class$0 = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$0;
                method2.invoke(invoke, clazz.getDeclaredConstructor((Class<?>[])array).newInstance(s));
            }
        }
        catch (ClassNotFoundException ex3) {}
        catch (NoSuchMethodException ex4) {}
        catch (IllegalArgumentException ex5) {}
        catch (IllegalAccessException ex6) {}
        catch (InvocationTargetException ex7) {}
        catch (InstantiationException ex8) {}
    }
    
    String initMozilla(String s) {
        final int getenv = C.getenv(MozillaDelegate.wcsToMbcs(null, "MOZILLA_FIVE_HOME", true));
        if (getenv != 0) {
            final int strlen = C.strlen(getenv);
            final byte[] array = new byte[strlen];
            C.memmove(array, getenv, strlen);
            s = new String(MozillaDelegate.mbcsToWcs(null, array));
            if (Mozilla.SEPARATOR_OS == '/') {
                s = s.replace('\\', Mozilla.SEPARATOR_OS);
            }
            else {
                s = s.replace('/', Mozilla.SEPARATOR_OS);
            }
        }
        else {
            this.browser.dispose();
            SWT.error(2, null, " [Unknown Mozilla path (MOZILLA_FIVE_HOME not set)]");
        }
        if (Device.DEBUG) {
            System.out.println("Mozilla path: " + s);
        }
        if (Compatibility.fileExists(s, "components/libwidget_gtk.so")) {
            this.browser.dispose();
            SWT.error(2, null, " [Mozilla GTK2 required (GTK1.2 detected)]");
        }
        try {
            Library.loadLibrary("swt-mozilla");
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            try {
                Library.loadLibrary("swt-mozilla-gcc3");
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError2) {
                this.browser.dispose();
                SWT.error(2, unsatisfiedLinkError, " [MOZILLA_FIVE_HOME='" + s + "']");
            }
        }
        return s;
    }
    
    void initXPCOM(final String s, final boolean b) {
        final int[] array = { 0 };
        final nsEmbedString nsEmbedString = new nsEmbedString(s);
        final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array);
        nsEmbedString.dispose();
        if (ns_NewLocalFile != 0) {
            this.browser.dispose();
            error(ns_NewLocalFile);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467261);
        }
        final nsILocalFile nsILocalFile = new nsILocalFile(array[0]);
        array[0] = 0;
        int n2;
        if (b) {
            final int nsDynamicFunctionLoad_sizeof = XPCOM.nsDynamicFunctionLoad_sizeof();
            final int malloc = C.malloc(nsDynamicFunctionLoad_sizeof * 2);
            C.memset(malloc, 0, nsDynamicFunctionLoad_sizeof * 2);
            final nsDynamicFunctionLoad nsDynamicFunctionLoad = new nsDynamicFunctionLoad();
            final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "XRE_InitEmbedding", true);
            C.memmove(nsDynamicFunctionLoad.functionName = C.malloc(wcsToMbcs.length), wcsToMbcs, wcsToMbcs.length);
            C.memmove(nsDynamicFunctionLoad.function = C.malloc(C.PTR_SIZEOF), new int[1], C.PTR_SIZEOF);
            XPCOM.memmove(malloc, nsDynamicFunctionLoad, XPCOM.nsDynamicFunctionLoad_sizeof());
            XPCOM.XPCOMGlueLoadXULFunctions(malloc);
            C.memmove(array, nsDynamicFunctionLoad.function, C.PTR_SIZEOF);
            final int n = array[0];
            array[0] = 0;
            C.free(nsDynamicFunctionLoad.function);
            C.free(nsDynamicFunctionLoad.functionName);
            C.free(malloc);
            if (n == 0) {
                this.browser.dispose();
                error(-2147467261);
            }
            n2 = XPCOM.Call(n, nsILocalFile.getAddress(), nsILocalFile.getAddress(), Mozilla.LocationProvider.getAddress(), 0, 0);
            if (n2 == 0) {
                System.setProperty("org.eclipse.swt.browser.XULRunnerPath", s);
            }
        }
        else {
            n2 = XPCOM.NS_InitXPCOM2(0, nsILocalFile.getAddress(), Mozilla.LocationProvider.getAddress());
        }
        nsILocalFile.Release();
        if (n2 != 0) {
            this.browser.dispose();
            SWT.error(2, null, " [MOZILLA_FIVE_HOME may not point at an embeddable GRE] [NS_InitEmbedding " + s + " error " + n2 + "]");
        }
        System.setProperty("org.eclipse.swt.browser.XULRunnerInitialized", "true");
    }
    
    void initPreferences(final nsIServiceManager nsIServiceManager, final nsIComponentManager nsIComponentManager) {
        final int[] array = { 0 };
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/preferences-service;1", true), nsIPrefService.NS_IPREFSERVICE_IID, array);
        if (getServiceByContractID != 0) {
            this.browser.dispose();
            error(getServiceByContractID);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIPrefService nsIPrefService = new nsIPrefService(array[0]);
        array[0] = 0;
        final int getBranch = nsIPrefService.GetBranch(new byte[1], array);
        nsIPrefService.Release();
        if (getBranch != 0) {
            this.browser.dispose();
            error(getBranch);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIPrefBranch nsIPrefBranch = new nsIPrefBranch(array[0]);
        array[0] = 0;
        nsIPrefLocalizedString nsIPrefLocalizedString = null;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "intl.accept_languages", true);
        String string;
        if (nsIPrefBranch.GetComplexValue(wcsToMbcs, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array) != 0) {
            string = "en-us,en,";
        }
        else {
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            nsIPrefLocalizedString = new nsIPrefLocalizedString(array[0]);
            array[0] = 0;
            final int toString = nsIPrefLocalizedString.ToString(array);
            if (toString != 0) {
                this.browser.dispose();
                error(toString);
            }
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(array[0]);
            final char[] array2 = new char[strlen_PRUnichar];
            C.memmove(array2, array[0], strlen_PRUnichar * 2);
            string = String.valueOf(new String(array2)) + ",";
        }
        array[0] = 0;
        final Locale default1 = Locale.getDefault();
        final String language = default1.getLanguage();
        final String country = default1.getCountry();
        final StringBuffer sb = new StringBuffer(language);
        sb.append("-");
        sb.append(country.toLowerCase());
        sb.append(",");
        sb.append(language);
        sb.append(",");
        final String string2 = sb.toString();
        int i = -1;
        do {
            final int n = i + 1;
            i = string.indexOf(",", n);
            String s;
            if (i == -1) {
                s = string.substring(n);
            }
            else {
                s = string.substring(n, i);
            }
            if (s.length() > 0) {
                final String trim = (String.valueOf(s) + ",").trim();
                if (string2.indexOf(trim) != -1) {
                    continue;
                }
                sb.append(trim);
            }
        } while (i != -1);
        final String string3 = sb.toString();
        if (!string3.equals(string)) {
            final String substring = string3.substring(0, string3.length() - ",".length());
            final int length = substring.length();
            final char[] array3 = new char[length + 1];
            substring.getChars(0, length, array3, 0);
            if (nsIPrefLocalizedString == null) {
                final int createInstanceByContractID = nsIComponentManager.CreateInstanceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/pref-localizedstring;1", true), 0, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array);
                if (createInstanceByContractID != 0) {
                    this.browser.dispose();
                    error(createInstanceByContractID);
                }
                if (array[0] == 0) {
                    this.browser.dispose();
                    error(-2147467262);
                }
                nsIPrefLocalizedString = new nsIPrefLocalizedString(array[0]);
                array[0] = 0;
            }
            nsIPrefLocalizedString.SetDataWithLength(length, array3);
            nsIPrefBranch.SetComplexValue(wcsToMbcs, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString.getAddress());
        }
        if (nsIPrefLocalizedString != null) {
            nsIPrefLocalizedString.Release();
            nsIPrefLocalizedString = null;
        }
        final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, "intl.charset.default", true);
        String s2;
        if (nsIPrefBranch.GetComplexValue(wcsToMbcs2, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array) != 0) {
            s2 = "ISO-8859-1";
        }
        else {
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            nsIPrefLocalizedString = new nsIPrefLocalizedString(array[0]);
            array[0] = 0;
            final int toString2 = nsIPrefLocalizedString.ToString(array);
            if (toString2 != 0) {
                this.browser.dispose();
                error(toString2);
            }
            if (array[0] == 0) {
                this.browser.dispose();
                error(-2147467262);
            }
            final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(array[0]);
            final char[] array4 = new char[strlen_PRUnichar2];
            C.memmove(array4, array[0], strlen_PRUnichar2 * 2);
            s2 = new String(array4);
        }
        array[0] = 0;
        final String property = System.getProperty("file.encoding");
        if (!property.equals(s2)) {
            final int length2 = property.length();
            final char[] array5 = new char[length2 + 1];
            property.getChars(0, length2, array5, 0);
            if (nsIPrefLocalizedString == null) {
                final int createInstanceByContractID2 = nsIComponentManager.CreateInstanceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/pref-localizedstring;1", true), 0, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array);
                if (createInstanceByContractID2 != 0) {
                    this.browser.dispose();
                    error(createInstanceByContractID2);
                }
                if (array[0] == 0) {
                    this.browser.dispose();
                    error(-2147467262);
                }
                nsIPrefLocalizedString = new nsIPrefLocalizedString(array[0]);
                array[0] = 0;
            }
            nsIPrefLocalizedString.SetDataWithLength(length2, array5);
            nsIPrefBranch.SetComplexValue(wcsToMbcs2, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString.getAddress());
        }
        if (nsIPrefLocalizedString != null) {
            nsIPrefLocalizedString.Release();
        }
        final String property2 = System.getProperty("network.proxy_host");
        final String property3 = System.getProperty("network.proxy_port");
        int n2 = -1;
        if (property3 != null) {
            try {
                final int intValue = Integer.valueOf(property3);
                if (intValue >= 0 && intValue <= 65535) {
                    n2 = intValue;
                }
            }
            catch (NumberFormatException ex) {}
        }
        if (property2 != null) {
            final int createInstanceByContractID3 = nsIComponentManager.CreateInstanceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/pref-localizedstring;1", true), 0, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array);
            if (createInstanceByContractID3 != 0) {
                error(createInstanceByContractID3);
            }
            if (array[0] == 0) {
                error(-2147467262);
            }
            final nsIPrefLocalizedString nsIPrefLocalizedString2 = new nsIPrefLocalizedString(array[0]);
            array[0] = 0;
            final int length3 = property2.length();
            final char[] array6 = new char[length3];
            property2.getChars(0, length3, array6, 0);
            final int setDataWithLength = nsIPrefLocalizedString2.SetDataWithLength(length3, array6);
            if (setDataWithLength != 0) {
                error(setDataWithLength);
            }
            final byte[] wcsToMbcs3 = MozillaDelegate.wcsToMbcs(null, "network.proxy.ftp", true);
            if (nsIPrefBranch.GetComplexValue(wcsToMbcs3, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array) == 0 && array[0] != 0) {
                final nsIPrefLocalizedString nsIPrefLocalizedString3 = new nsIPrefLocalizedString(array[0]);
                array[0] = 0;
                final int toString3 = nsIPrefLocalizedString3.ToString(array);
                if (toString3 != 0) {
                    error(toString3);
                }
                if (array[0] == 0) {
                    error(-2147467261);
                }
                final int strlen_PRUnichar3 = XPCOM.strlen_PRUnichar(array[0]);
                final char[] array7 = new char[strlen_PRUnichar3];
                C.memmove(array7, array[0], strlen_PRUnichar3 * 2);
                Mozilla.oldProxyHostFTP = new String(array7);
            }
            else {
                Mozilla.oldProxyHostFTP = "default";
            }
            array[0] = 0;
            final int setComplexValue = nsIPrefBranch.SetComplexValue(wcsToMbcs3, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString2.getAddress());
            if (setComplexValue != 0) {
                error(setComplexValue);
            }
            final byte[] wcsToMbcs4 = MozillaDelegate.wcsToMbcs(null, "network.proxy.http", true);
            if (nsIPrefBranch.GetComplexValue(wcsToMbcs4, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array) == 0 && array[0] != 0) {
                final nsIPrefLocalizedString nsIPrefLocalizedString4 = new nsIPrefLocalizedString(array[0]);
                array[0] = 0;
                final int toString4 = nsIPrefLocalizedString4.ToString(array);
                if (toString4 != 0) {
                    error(toString4);
                }
                if (array[0] == 0) {
                    error(-2147467261);
                }
                final int strlen_PRUnichar4 = XPCOM.strlen_PRUnichar(array[0]);
                final char[] array8 = new char[strlen_PRUnichar4];
                C.memmove(array8, array[0], strlen_PRUnichar4 * 2);
                Mozilla.oldProxyHostHTTP = new String(array8);
            }
            else {
                Mozilla.oldProxyHostHTTP = "default";
            }
            array[0] = 0;
            final int setComplexValue2 = nsIPrefBranch.SetComplexValue(wcsToMbcs4, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString2.getAddress());
            if (setComplexValue2 != 0) {
                error(setComplexValue2);
            }
            final byte[] wcsToMbcs5 = MozillaDelegate.wcsToMbcs(null, "network.proxy.ssl", true);
            if (nsIPrefBranch.GetComplexValue(wcsToMbcs5, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, array) == 0 && array[0] != 0) {
                final nsIPrefLocalizedString nsIPrefLocalizedString5 = new nsIPrefLocalizedString(array[0]);
                array[0] = 0;
                final int toString5 = nsIPrefLocalizedString5.ToString(array);
                if (toString5 != 0) {
                    error(toString5);
                }
                if (array[0] == 0) {
                    error(-2147467261);
                }
                final int strlen_PRUnichar5 = XPCOM.strlen_PRUnichar(array[0]);
                final char[] array9 = new char[strlen_PRUnichar5];
                C.memmove(array9, array[0], strlen_PRUnichar5 * 2);
                Mozilla.oldProxyHostSSL = new String(array9);
            }
            else {
                Mozilla.oldProxyHostSSL = "default";
            }
            array[0] = 0;
            final int setComplexValue3 = nsIPrefBranch.SetComplexValue(wcsToMbcs5, org.eclipse.swt.internal.mozilla.nsIPrefLocalizedString.NS_IPREFLOCALIZEDSTRING_IID, nsIPrefLocalizedString2.getAddress());
            if (setComplexValue3 != 0) {
                error(setComplexValue3);
            }
            nsIPrefLocalizedString2.Release();
        }
        final int[] array10 = { 0 };
        if (n2 != -1) {
            final byte[] wcsToMbcs6 = MozillaDelegate.wcsToMbcs(null, "network.proxy.ftp_port", true);
            final int getIntPref = nsIPrefBranch.GetIntPref(wcsToMbcs6, array10);
            if (getIntPref != 0) {
                error(getIntPref);
            }
            Mozilla.oldProxyPortFTP = array10[0];
            array10[0] = 0;
            final int setIntPref = nsIPrefBranch.SetIntPref(wcsToMbcs6, n2);
            if (setIntPref != 0) {
                error(setIntPref);
            }
            final byte[] wcsToMbcs7 = MozillaDelegate.wcsToMbcs(null, "network.proxy.http_port", true);
            final int getIntPref2 = nsIPrefBranch.GetIntPref(wcsToMbcs7, array10);
            if (getIntPref2 != 0) {
                error(getIntPref2);
            }
            Mozilla.oldProxyPortHTTP = array10[0];
            array10[0] = 0;
            final int setIntPref2 = nsIPrefBranch.SetIntPref(wcsToMbcs7, n2);
            if (setIntPref2 != 0) {
                error(setIntPref2);
            }
            final byte[] wcsToMbcs8 = MozillaDelegate.wcsToMbcs(null, "network.proxy.ssl_port", true);
            final int getIntPref3 = nsIPrefBranch.GetIntPref(wcsToMbcs8, array10);
            if (getIntPref3 != 0) {
                error(getIntPref3);
            }
            Mozilla.oldProxyPortSSL = array10[0];
            array10[0] = 0;
            final int setIntPref3 = nsIPrefBranch.SetIntPref(wcsToMbcs8, n2);
            if (setIntPref3 != 0) {
                error(setIntPref3);
            }
        }
        if (property2 != null || n2 != -1) {
            final byte[] wcsToMbcs9 = MozillaDelegate.wcsToMbcs(null, "network.proxy.type", true);
            final int getIntPref4 = nsIPrefBranch.GetIntPref(wcsToMbcs9, array10);
            if (getIntPref4 != 0) {
                error(getIntPref4);
            }
            Mozilla.oldProxyType = array10[0];
            array10[0] = 0;
            final int setIntPref4 = nsIPrefBranch.SetIntPref(wcsToMbcs9, 1);
            if (setIntPref4 != 0) {
                error(setIntPref4);
            }
        }
        final int setBoolPref = nsIPrefBranch.SetBoolPref(MozillaDelegate.wcsToMbcs(null, "dom.disable_open_during_load", true), 0);
        if (setBoolPref != 0) {
            this.browser.dispose();
            error(setBoolPref);
        }
        final int setBoolPref2 = nsIPrefBranch.SetBoolPref(MozillaDelegate.wcsToMbcs(null, "dom.disable_window_status_change", true), 0);
        if (setBoolPref2 != 0) {
            this.browser.dispose();
            error(setBoolPref2);
        }
        final int setBoolPref3 = nsIPrefBranch.SetBoolPref(MozillaDelegate.wcsToMbcs(null, "dom.disable_window_open_feature.status", true), 0);
        if (setBoolPref3 != 0) {
            this.browser.dispose();
            error(setBoolPref3);
        }
        final int setBoolPref4 = nsIPrefBranch.SetBoolPref(MozillaDelegate.wcsToMbcs(null, "javascript.enabled", true), 1);
        if (setBoolPref4 != 0) {
            this.browser.dispose();
            error(setBoolPref4);
        }
        nsIPrefBranch.Release();
    }
    
    void initProfile(final nsIServiceManager nsIServiceManager, final boolean b) {
        final int[] array = { 0 };
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/observer-service;1", true), nsIObserverService.NS_IOBSERVERSERVICE_IID, array);
        if (getServiceByContractID != 0) {
            this.browser.dispose();
            error(getServiceByContractID);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIObserverService nsIObserverService = new nsIObserverService(array[0]);
        array[0] = 0;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "profile-do-change", true);
        final int length = "startup".length();
        final char[] array2 = new char[length + 1];
        "startup".getChars(0, length, array2, 0);
        final int notifyObservers = nsIObserverService.NotifyObservers(0, wcsToMbcs, array2);
        if (notifyObservers != 0) {
            this.browser.dispose();
            error(notifyObservers);
        }
        final int notifyObservers2 = nsIObserverService.NotifyObservers(0, MozillaDelegate.wcsToMbcs(null, "profile-after-change", true), array2);
        if (notifyObservers2 != 0) {
            this.browser.dispose();
            error(notifyObservers2);
        }
        nsIObserverService.Release();
        if (b) {
            final int nsDynamicFunctionLoad_sizeof = XPCOM.nsDynamicFunctionLoad_sizeof();
            final int malloc = C.malloc(nsDynamicFunctionLoad_sizeof * 2);
            C.memset(malloc, 0, nsDynamicFunctionLoad_sizeof * 2);
            final nsDynamicFunctionLoad nsDynamicFunctionLoad = new nsDynamicFunctionLoad();
            final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, "XRE_NotifyProfile", true);
            C.memmove(nsDynamicFunctionLoad.functionName = C.malloc(wcsToMbcs2.length), wcsToMbcs2, wcsToMbcs2.length);
            C.memmove(nsDynamicFunctionLoad.function = C.malloc(C.PTR_SIZEOF), new int[1], C.PTR_SIZEOF);
            XPCOM.memmove(malloc, nsDynamicFunctionLoad, XPCOM.nsDynamicFunctionLoad_sizeof());
            XPCOM.XPCOMGlueLoadXULFunctions(malloc);
            C.memmove(array, nsDynamicFunctionLoad.function, C.PTR_SIZEOF);
            final int n = array[0];
            array[0] = 0;
            C.free(nsDynamicFunctionLoad.function);
            C.free(nsDynamicFunctionLoad.functionName);
            C.free(malloc);
            if (n != 0) {
                final int call = XPCOM.Call(n);
                if (call != 0) {
                    this.browser.dispose();
                    error(call);
                }
            }
        }
    }
    
    void initSpinup(final nsIComponentManager nsIComponentManager) {
        if (this.delegate.needsSpinup()) {
            final int[] array = { 0 };
            final int createInstance = nsIComponentManager.CreateInstance(XPCOM.NS_APPSHELL_CID, 0, nsIAppShell.NS_IAPPSHELL_IID, array);
            if (createInstance != -2147467262) {
                if (createInstance != 0) {
                    this.browser.dispose();
                    error(createInstance);
                }
                if (array[0] == 0) {
                    this.browser.dispose();
                    error(-2147467262);
                }
                Mozilla.AppShell = new nsIAppShell(array[0]);
                array[0] = 0;
                final int create = Mozilla.AppShell.Create(0, null);
                if (create != 0) {
                    this.browser.dispose();
                    error(create);
                }
                final int spinup = Mozilla.AppShell.Spinup();
                if (spinup != 0) {
                    this.browser.dispose();
                    error(spinup);
                }
            }
        }
    }
    
    void initWebBrowserWindows() {
        final int setContainerWindow = this.webBrowser.SetContainerWindow(this.webBrowserChrome.getAddress());
        if (setContainerWindow != 0) {
            this.browser.dispose();
            error(setContainerWindow);
        }
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIBaseWindow.NS_IBASEWINDOW_IID, array);
        if (queryInterface != 0) {
            this.browser.dispose();
            error(queryInterface);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIBaseWindow nsIBaseWindow = new nsIBaseWindow(array[0]);
        array[0] = 0;
        final Rectangle clientArea = this.browser.getClientArea();
        if (clientArea.isEmpty()) {
            clientArea.width = 1;
            clientArea.height = 1;
        }
        this.embedHandle = this.delegate.getHandle();
        if (nsIBaseWindow.InitWindow(this.embedHandle, 0, 0, 0, clientArea.width, clientArea.height) != 0) {
            this.browser.dispose();
            error(-2147467259);
        }
        if (this.delegate.createBaseWindow(nsIBaseWindow) != 0) {
            this.browser.dispose();
            error(-2147467259);
        }
        if (nsIBaseWindow.SetVisibility(1) != 0) {
            this.browser.dispose();
            error(-2147467259);
        }
        nsIBaseWindow.Release();
    }
    
    void initWindowCreator(final nsIServiceManager nsIServiceManager) {
        (Mozilla.WindowCreator = new WindowCreator2()).AddRef();
        final int[] array = { 0 };
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/embedcomp/window-watcher;1", true), nsIWindowWatcher.NS_IWINDOWWATCHER_IID, array);
        if (getServiceByContractID != 0) {
            this.browser.dispose();
            error(getServiceByContractID);
        }
        if (array[0] == 0) {
            this.browser.dispose();
            error(-2147467262);
        }
        final nsIWindowWatcher nsIWindowWatcher = new nsIWindowWatcher(array[0]);
        array[0] = 0;
        final int setWindowCreator = nsIWindowWatcher.SetWindowCreator(Mozilla.WindowCreator.getAddress());
        if (setWindowCreator != 0) {
            this.browser.dispose();
            error(setWindowCreator);
        }
        nsIWindowWatcher.Release();
    }
    
    String initXULRunner(final String s) {
        if (Device.DEBUG) {
            System.out.println("XULRunner path: " + s);
        }
        try {
            Library.loadLibrary("swt-xulrunner");
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            SWT.error(2, unsatisfiedLinkError);
        }
        final int xpcomGlueStartup = XPCOM.XPCOMGlueStartup(MozillaDelegate.wcsToMbcs(null, s, true));
        if (xpcomGlueStartup != 0) {
            this.browser.dispose();
            error(xpcomGlueStartup);
        }
        Mozilla.XPCOMWasGlued = true;
        return s.substring(0, s.lastIndexOf(Mozilla.SEPARATOR_OS));
    }
    
    public boolean isBackEnabled() {
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int[] array2 = { 0 };
        nsIWebNavigation.GetCanGoBack(array2);
        nsIWebNavigation.Release();
        return array2[0] != 0;
    }
    
    public boolean isForwardEnabled() {
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int[] array2 = { 0 };
        nsIWebNavigation.GetCanGoForward(array2);
        nsIWebNavigation.Release();
        return array2[0] != 0;
    }
    
    static String error(final int n) {
        throw new SWTError("XPCOM error " + n);
    }
    
    void onDispose(final Display display) {
        if (!this.browser.isClosing && !this.browser.isDisposed()) {
            final LocationListener[] locationListeners = this.locationListeners;
            this.locationListeners = new LocationListener[0];
            this.ignoreAllMessages = true;
            this.execute("window.location.replace('about:blank');");
            this.ignoreAllMessages = false;
            this.locationListeners = locationListeners;
        }
        final int removeWebBrowserListener = this.webBrowser.RemoveWebBrowserListener(this.weakReference.getAddress(), nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID);
        if (removeWebBrowserListener != 0) {
            error(removeWebBrowserListener);
        }
        final int setParentURIContentListener = this.webBrowser.SetParentURIContentListener(0);
        if (setParentURIContentListener != 0) {
            error(setParentURIContentListener);
        }
        final int setContainerWindow = this.webBrowser.SetContainerWindow(0);
        if (setContainerWindow != 0) {
            error(setContainerWindow);
        }
        this.unhookDOMListeners();
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIBaseWindow.NS_IBASEWINDOW_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIBaseWindow nsIBaseWindow = new nsIBaseWindow(array[0]);
        final int destroy = nsIBaseWindow.Destroy();
        if (destroy != 0) {
            error(destroy);
        }
        nsIBaseWindow.Release();
        this.Release();
        this.webBrowser.Release();
        this.webBrowser = null;
        this.webBrowserObject = null;
        this.lastNavigateURL = null;
        this.htmlBytes = null;
        this.listener = null;
        if (this.tip != null && !this.tip.isDisposed()) {
            this.tip.dispose();
        }
        this.tip = null;
        final Point point = null;
        this.size = point;
        this.location = point;
        final Enumeration<LONG> elements = (Enumeration<LONG>)this.unhookedDOMWindows.elements();
        while (elements.hasMoreElements()) {
            new nsISupports(elements.nextElement().value).Release();
        }
        this.unhookedDOMWindows = null;
        final Enumeration<BrowserFunction> elements2 = (Enumeration<BrowserFunction>)this.functions.elements();
        while (elements2.hasMoreElements()) {
            final BrowserFunction browserFunction = elements2.nextElement();
            Mozilla.AllFunctions.remove(new Integer(browserFunction.index));
            browserFunction.dispose(false);
        }
        this.functions = null;
        this.delegate.onDispose(this.embedHandle);
        this.delegate = null;
        this.embedHandle = 0;
        --Mozilla.BrowserCount;
    }
    
    void Activate() {
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebBrowserFocus.NS_IWEBBROWSERFOCUS_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebBrowserFocus nsIWebBrowserFocus = new nsIWebBrowserFocus(array[0]);
        final int activate = nsIWebBrowserFocus.Activate();
        if (activate != 0) {
            error(activate);
        }
        nsIWebBrowserFocus.Release();
    }
    
    void Deactivate() {
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebBrowserFocus.NS_IWEBBROWSERFOCUS_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebBrowserFocus nsIWebBrowserFocus = new nsIWebBrowserFocus(array[0]);
        final int deactivate = nsIWebBrowserFocus.Deactivate();
        if (deactivate != 0) {
            error(deactivate);
        }
        nsIWebBrowserFocus.Release();
    }
    
    void onResize() {
        final Rectangle clientArea = this.browser.getClientArea();
        final int max = Math.max(1, clientArea.width);
        final int max2 = Math.max(1, clientArea.height);
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIBaseWindow.NS_IBASEWINDOW_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        this.delegate.setSize(this.embedHandle, max, max2);
        final nsIBaseWindow nsIBaseWindow = new nsIBaseWindow(array[0]);
        final int setPositionAndSize = nsIBaseWindow.SetPositionAndSize(0, 0, max, max2, 1);
        if (setPositionAndSize != 0) {
            error(setPositionAndSize);
        }
        nsIBaseWindow.Release();
    }
    
    public void refresh() {
        this.htmlBytes = null;
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int reload = nsIWebNavigation.Reload(0);
        nsIWebNavigation.Release();
        switch (reload) {
            case -2147467261:
            case -2142568446:
            case -2142109678:
            case 0: {}
            default: {
                error(reload);
            }
        }
    }
    
    void registerFunction(final BrowserFunction browserFunction) {
        super.registerFunction(browserFunction);
        Mozilla.AllFunctions.put(new Integer(browserFunction.index), browserFunction);
    }
    
    public boolean setText(final String s, final boolean b) {
        if (this.browser != this.browser.getDisplay().getFocusControl()) {
            this.Deactivate();
        }
        final byte[] array = null;
        byte[] bytes;
        try {
            bytes = s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return false;
        }
        this.delegate.removeWindowSubclass();
        final int[] array2 = { 0 };
        if (this.webBrowser.QueryInterface(nsIWebBrowserStream.NS_IWEBBROWSERSTREAM_IID, array2) == 0 && array2[0] != 0) {
            new nsISupports(array2[0]).Release();
            array2[0] = 0;
            final boolean b2 = this.htmlBytes != null;
            this.htmlBytes = bytes;
            this.untrustedText = !b;
            if (b2) {
                return true;
            }
            final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array2);
            if (queryInterface != 0) {
                error(queryInterface);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array2[0]);
            array2[0] = 0;
            final char[] array3 = new char["about:blank".length() + 1];
            "about:blank".getChars(0, "about:blank".length(), array3, 0);
            final int loadURI = nsIWebNavigation.LoadURI(array3, 0, 0, 0, 0);
            if (loadURI != 0) {
                error(loadURI);
            }
            nsIWebNavigation.Release();
        }
        else {
            final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "UTF-8", false);
            final int nsEmbedCString_new = XPCOM.nsEmbedCString_new(wcsToMbcs, wcsToMbcs.length);
            final byte[] wcsToMbcs2 = MozillaDelegate.wcsToMbcs(null, "text/html", false);
            final int nsEmbedCString_new2 = XPCOM.nsEmbedCString_new(wcsToMbcs2, wcsToMbcs2.length);
            final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array2);
            if (ns_GetServiceManager != 0) {
                error(ns_GetServiceManager);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIServiceManager nsIServiceManager = new nsIServiceManager(array2[0]);
            array2[0] = 0;
            final int getService = nsIServiceManager.GetService(XPCOM.NS_IOSERVICE_CID, nsIIOService.NS_IIOSERVICE_IID, array2);
            if (getService != 0) {
                error(getService);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIIOService nsIIOService = new nsIIOService(array2[0]);
            array2[0] = 0;
            byte[] array4;
            if (b) {
                array4 = MozillaDelegate.wcsToMbcs(null, "file:///", false);
            }
            else {
                array4 = MozillaDelegate.wcsToMbcs(null, "about:blank", false);
            }
            final int nsEmbedCString_new3 = XPCOM.nsEmbedCString_new(array4, array4.length);
            final int newURI = nsIIOService.NewURI(nsEmbedCString_new3, null, 0, array2);
            if (newURI != 0) {
                error(newURI);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new3);
            nsIIOService.Release();
            final nsIURI nsIURI = new nsIURI(array2[0]);
            array2[0] = 0;
            final int queryInterface2 = this.webBrowser.QueryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID, array2);
            if (queryInterface2 != 0) {
                error(queryInterface2);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIInterfaceRequestor nsIInterfaceRequestor = new nsIInterfaceRequestor(array2[0]);
            array2[0] = 0;
            final org.eclipse.swt.browser.InputStream inputStream = new org.eclipse.swt.browser.InputStream(bytes);
            inputStream.AddRef();
            final int getInterface = nsIInterfaceRequestor.GetInterface(nsIDocShell.NS_IDOCSHELL_IID, array2);
            if (getInterface != 0) {
                error(getInterface);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIDocShell nsIDocShell = new nsIDocShell(array2[0]);
            array2[0] = 0;
            nsIDocShell.LoadStream(inputStream.getAddress(), nsIURI.getAddress(), nsEmbedCString_new2, nsEmbedCString_new, 0);
            nsIDocShell.Release();
            inputStream.Release();
            nsIInterfaceRequestor.Release();
            nsIURI.Release();
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new2);
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        }
        return true;
    }
    
    public boolean setUrl(final String s, final String s2, final String[] array) {
        this.htmlBytes = null;
        final int[] array2 = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array2);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array2[0] == 0) {
            error(-2147467262);
        }
        this.delegate.removeWindowSubclass();
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array2[0]);
        array2[0] = 0;
        final char[] array3 = new char[s.length() + 1];
        s.getChars(0, s.length(), array3, 0);
        nsIMIMEInputStream nsIMIMEInputStream = null;
        org.eclipse.swt.browser.InputStream inputStream = null;
        if (s2 != null) {
            final int ns_GetComponentManager = XPCOM.NS_GetComponentManager(array2);
            if (ns_GetComponentManager != 0) {
                error(ns_GetComponentManager);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIComponentManager nsIComponentManager = new nsIComponentManager(array2[0]);
            array2[0] = 0;
            final int createInstanceByContractID = nsIComponentManager.CreateInstanceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/network/mime-input-stream;1", true), 0, org.eclipse.swt.internal.mozilla.nsIMIMEInputStream.NS_IMIMEINPUTSTREAM_IID, array2);
            nsIComponentManager.Release();
            if (createInstanceByContractID == 0 && array2[0] != 0) {
                inputStream = new org.eclipse.swt.browser.InputStream(MozillaDelegate.wcsToMbcs(null, s2, false));
                inputStream.AddRef();
                nsIMIMEInputStream = new nsIMIMEInputStream(array2[0]);
                final int setData = nsIMIMEInputStream.SetData(inputStream.getAddress());
                if (setData != 0) {
                    error(setData);
                }
                final int setAddContentLength = nsIMIMEInputStream.SetAddContentLength(1);
                if (setAddContentLength != 0) {
                    error(setAddContentLength);
                }
                final int addHeader = nsIMIMEInputStream.AddHeader(MozillaDelegate.wcsToMbcs(null, "Content-Type", true), MozillaDelegate.wcsToMbcs(null, "application/x-www-form-urlencoded", true));
                if (addHeader != 0) {
                    error(addHeader);
                }
            }
            array2[0] = 0;
        }
        org.eclipse.swt.browser.InputStream inputStream2 = null;
        if (array != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                final String s3 = array[i];
                if (s3 != null) {
                    final int index = s3.indexOf(58);
                    if (index != -1) {
                        final String trim = s3.substring(0, index).trim();
                        final String trim2 = s3.substring(index + 1).trim();
                        if (trim.length() > 0 && trim2.length() > 0) {
                            sb.append(trim);
                            sb.append(':');
                            sb.append(trim2);
                            sb.append("\r\n");
                        }
                    }
                }
            }
            inputStream2 = new org.eclipse.swt.browser.InputStream(MozillaDelegate.wcsToMbcs(null, sb.toString(), true));
            inputStream2.AddRef();
        }
        final int loadURI = nsIWebNavigation.LoadURI(array3, 0, 0, (nsIMIMEInputStream == null) ? 0 : nsIMIMEInputStream.getAddress(), (inputStream2 == null) ? 0 : inputStream2.getAddress());
        if (inputStream != null) {
            inputStream.Release();
        }
        if (inputStream2 != null) {
            inputStream2.Release();
        }
        nsIWebNavigation.Release();
        return loadURI == 0;
    }
    
    public void stop() {
        this.htmlBytes = null;
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIWebNavigation.NS_IWEBNAVIGATION_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIWebNavigation nsIWebNavigation = new nsIWebNavigation(array[0]);
        final int stop = nsIWebNavigation.Stop(3);
        if (stop != 0) {
            error(stop);
        }
        nsIWebNavigation.Release();
    }
    
    void hookDOMListeners(final nsIDOMEventTarget nsIDOMEventTarget, final boolean b) {
        final nsEmbedString nsEmbedString = new nsEmbedString("focus");
        nsIDOMEventTarget.AddEventListener(nsEmbedString.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString.dispose();
        final nsEmbedString nsEmbedString2 = new nsEmbedString("unload");
        nsIDOMEventTarget.AddEventListener(nsEmbedString2.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString2.dispose();
        final nsEmbedString nsEmbedString3 = new nsEmbedString("mousedown");
        nsIDOMEventTarget.AddEventListener(nsEmbedString3.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString3.dispose();
        final nsEmbedString nsEmbedString4 = new nsEmbedString("mouseup");
        nsIDOMEventTarget.AddEventListener(nsEmbedString4.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString4.dispose();
        final nsEmbedString nsEmbedString5 = new nsEmbedString("mousemove");
        nsIDOMEventTarget.AddEventListener(nsEmbedString5.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString5.dispose();
        final nsEmbedString nsEmbedString6 = new nsEmbedString("DOMMouseScroll");
        nsIDOMEventTarget.AddEventListener(nsEmbedString6.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString6.dispose();
        final nsEmbedString nsEmbedString7 = new nsEmbedString("draggesture");
        nsIDOMEventTarget.AddEventListener(nsEmbedString7.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString7.dispose();
        if (b && this.delegate.hookEnterExit()) {
            final nsEmbedString nsEmbedString8 = new nsEmbedString("mouseover");
            nsIDOMEventTarget.AddEventListener(nsEmbedString8.getAddress(), this.domEventListener.getAddress(), 0);
            nsEmbedString8.dispose();
            final nsEmbedString nsEmbedString9 = new nsEmbedString("mouseout");
            nsIDOMEventTarget.AddEventListener(nsEmbedString9.getAddress(), this.domEventListener.getAddress(), 0);
            nsEmbedString9.dispose();
        }
        final nsEmbedString nsEmbedString10 = new nsEmbedString("keydown");
        nsIDOMEventTarget.AddEventListener(nsEmbedString10.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString10.dispose();
        final nsEmbedString nsEmbedString11 = new nsEmbedString("keypress");
        nsIDOMEventTarget.AddEventListener(nsEmbedString11.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString11.dispose();
        final nsEmbedString nsEmbedString12 = new nsEmbedString("keyup");
        nsIDOMEventTarget.AddEventListener(nsEmbedString12.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString12.dispose();
    }
    
    void unhookDOMListeners() {
        final int[] array = { 0 };
        if (this.webBrowser.GetContentDOMWindow(array) != 0 || array[0] == 0) {
            return;
        }
        final nsIDOMWindow nsIDOMWindow = new nsIDOMWindow(array[0]);
        array[0] = 0;
        final int queryInterface = nsIDOMWindow.QueryInterface(nsIDOMEventTarget.NS_IDOMEVENTTARGET_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIDOMEventTarget nsIDOMEventTarget = new nsIDOMEventTarget(array[0]);
        array[0] = 0;
        this.unhookDOMListeners(nsIDOMEventTarget);
        nsIDOMEventTarget.Release();
        final int getFrames = nsIDOMWindow.GetFrames(array);
        if (getFrames != 0) {
            error(getFrames);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIDOMWindowCollection collection = new nsIDOMWindowCollection(array[0]);
        array[0] = 0;
        final int[] array2 = { 0 };
        final int getLength = collection.GetLength(array2);
        if (getLength != 0) {
            error(getLength);
        }
        final int n = array2[0];
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                final int item = collection.Item(i, array);
                if (item != 0) {
                    error(item);
                }
                if (array[0] == 0) {
                    error(-2147467262);
                }
                final nsIDOMWindow nsIDOMWindow2 = new nsIDOMWindow(array[0]);
                array[0] = 0;
                final int queryInterface2 = nsIDOMWindow2.QueryInterface(org.eclipse.swt.internal.mozilla.nsIDOMEventTarget.NS_IDOMEVENTTARGET_IID, array);
                if (queryInterface2 != 0) {
                    error(queryInterface2);
                }
                if (array[0] == 0) {
                    error(-2147467262);
                }
                final nsIDOMEventTarget nsIDOMEventTarget2 = new nsIDOMEventTarget(array[0]);
                array[0] = 0;
                this.unhookDOMListeners(nsIDOMEventTarget2);
                nsIDOMEventTarget2.Release();
                nsIDOMWindow2.Release();
            }
        }
        collection.Release();
        nsIDOMWindow.Release();
    }
    
    void unhookDOMListeners(final nsIDOMEventTarget nsIDOMEventTarget) {
        final nsEmbedString nsEmbedString = new nsEmbedString("focus");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString.dispose();
        final nsEmbedString nsEmbedString2 = new nsEmbedString("unload");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString2.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString2.dispose();
        final nsEmbedString nsEmbedString3 = new nsEmbedString("mousedown");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString3.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString3.dispose();
        final nsEmbedString nsEmbedString4 = new nsEmbedString("mouseup");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString4.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString4.dispose();
        final nsEmbedString nsEmbedString5 = new nsEmbedString("mousemove");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString5.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString5.dispose();
        final nsEmbedString nsEmbedString6 = new nsEmbedString("DOMMouseScroll");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString6.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString6.dispose();
        final nsEmbedString nsEmbedString7 = new nsEmbedString("draggesture");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString7.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString7.dispose();
        final nsEmbedString nsEmbedString8 = new nsEmbedString("mouseover");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString8.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString8.dispose();
        final nsEmbedString nsEmbedString9 = new nsEmbedString("mouseout");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString9.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString9.dispose();
        final nsEmbedString nsEmbedString10 = new nsEmbedString("keydown");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString10.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString10.dispose();
        final nsEmbedString nsEmbedString11 = new nsEmbedString("keypress");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString11.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString11.dispose();
        final nsEmbedString nsEmbedString12 = new nsEmbedString("keyup");
        nsIDOMEventTarget.RemoveEventListener(nsEmbedString12.getAddress(), this.domEventListener.getAddress(), 0);
        nsEmbedString12.dispose();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.supports.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWeakReference.NS_IWEAKREFERENCE_IID)) {
            C.memmove(n2, new int[] { this.weakReference.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID)) {
            C.memmove(n2, new int[] { this.webProgressListener.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWebBrowserChrome.NS_IWEBBROWSERCHROME_IID)) {
            C.memmove(n2, new int[] { this.webBrowserChrome.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWebBrowserChromeFocus.NS_IWEBBROWSERCHROMEFOCUS_IID)) {
            C.memmove(n2, new int[] { this.webBrowserChromeFocus.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIEmbeddingSiteWindow.NS_IEMBEDDINGSITEWINDOW_IID)) {
            C.memmove(n2, new int[] { this.embeddingSiteWindow.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID)) {
            C.memmove(n2, new int[] { this.interfaceRequestor.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsISupportsWeakReference.NS_ISUPPORTSWEAKREFERENCE_IID)) {
            C.memmove(n2, new int[] { this.supportsWeakReference.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIContextMenuListener.NS_ICONTEXTMENULISTENER_IID)) {
            C.memmove(n2, new int[] { this.contextMenuListener.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIURIContentListener.NS_IURICONTENTLISTENER_IID)) {
            C.memmove(n2, new int[] { this.uriContentListener.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsITooltipListener.NS_ITOOLTIPLISTENER_IID)) {
            C.memmove(n2, new int[] { this.tooltipListener.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIBadCertListener2.NS_IBADCERTLISTENER2_IID)) {
            C.memmove(n2, new int[] { this.badCertListener.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    int QueryReferent(final int n, final int n2) {
        return this.QueryInterface(n, n2);
    }
    
    int GetInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsIDOMWindow.NS_IDOMWINDOW_IID)) {
            final int[] array = { 0 };
            final int getContentDOMWindow = this.webBrowser.GetContentDOMWindow(array);
            if (getContentDOMWindow != 0) {
                error(getContentDOMWindow);
            }
            if (array[0] == 0) {
                error(-2147467262);
            }
            C.memmove(n2, array, C.PTR_SIZEOF);
            return getContentDOMWindow;
        }
        return this.QueryInterface(n, n2);
    }
    
    int GetWeakReference(final int n) {
        C.memmove(n, new int[] { this.weakReference.getAddress() }, C.PTR_SIZEOF);
        this.AddRef();
        return 0;
    }
    
    int OnStateChange(final int n, final int request, final int n2, final int n3) {
        if (this.registerFunctionsOnState != 0 && (n2 & this.registerFunctionsOnState) == this.registerFunctionsOnState) {
            this.registerFunctionsOnState = 0;
            final Enumeration<BrowserFunction> elements = (Enumeration<BrowserFunction>)this.functions.elements();
            while (elements.hasMoreElements()) {
                this.execute(elements.nextElement().functionString);
            }
        }
        if (this.updateLastNavigateUrl && n2 == 65537) {
            final nsIRequest nsIRequest = new nsIRequest(request);
            final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
            if (nsIRequest.GetName(nsEmbedCString_new) == 0) {
                final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
                final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
                final byte[] array = new byte[nsEmbedCString_Length];
                C.memmove(array, nsEmbedCString_get, nsEmbedCString_Length);
                final String lastNavigateURL = new String(array);
                if (lastNavigateURL.indexOf(":/") != -1) {
                    this.lastNavigateURL = lastNavigateURL;
                }
            }
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        }
        if ((n2 & 0x20000) == 0x0) {
            return 0;
        }
        if ((n2 & 0x1) != 0x0) {
            final int[] array2 = { 0 };
            if (this.isRetrievingBadCert) {
                this.isRetrievingBadCert = false;
                final int queryInterface = new nsIRequest(request).QueryInterface(nsIChannel.NS_ICHANNEL_IID, array2);
                if (queryInterface != 0) {
                    error(queryInterface);
                }
                if (array2[0] == 0) {
                    error(-2147467262);
                }
                final nsIChannel nsIChannel = new nsIChannel(array2[0]);
                array2[0] = 0;
                final int setNotificationCallbacks = nsIChannel.SetNotificationCallbacks(this.interfaceRequestor.getAddress());
                if (setNotificationCallbacks != 0) {
                    error(setNotificationCallbacks);
                }
                nsIChannel.Release();
                return 0;
            }
            if (this.request == 0) {
                this.request = request;
            }
            this.registerFunctionsOnState = 65537;
            final int getDOMWindow = new nsIWebProgress(n).GetDOMWindow(array2);
            if (getDOMWindow != 0) {
                error(getDOMWindow);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            this.unhookedDOMWindows.addElement(new LONG(array2[0]));
        }
        else if ((n2 & 0x2) != 0x0) {
            if (this.request == request) {
                this.request = 0;
            }
            this.registerFunctionsOnState = 4;
            this.updateLastNavigateUrl = true;
        }
        else if ((n2 & 0x10) != 0x0) {
            final int[] array3 = { 0 };
            final int getDOMWindow2 = new nsIWebProgress(n).GetDOMWindow(array3);
            if (getDOMWindow2 != 0) {
                error(getDOMWindow2);
            }
            if (array3[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMWindow nsIDOMWindow = new nsIDOMWindow(array3[0]);
            final LONG long1 = new LONG(array3[0]);
            array3[0] = 0;
            if (this.unhookedDOMWindows.indexOf(long1) != -1) {
                final int getContentDOMWindow = this.webBrowser.GetContentDOMWindow(array3);
                if (getContentDOMWindow != 0) {
                    error(getContentDOMWindow);
                }
                if (array3[0] == 0) {
                    error(-2147467262);
                }
                final boolean b = array3[0] == nsIDOMWindow.getAddress();
                new nsISupports(array3[0]).Release();
                array3[0] = 0;
                final int queryInterface2 = nsIDOMWindow.QueryInterface(nsIDOMEventTarget.NS_IDOMEVENTTARGET_IID, array3);
                if (queryInterface2 != 0) {
                    error(queryInterface2);
                }
                if (array3[0] == 0) {
                    error(-2147467262);
                }
                final nsIDOMEventTarget nsIDOMEventTarget = new nsIDOMEventTarget(array3[0]);
                array3[0] = 0;
                this.hookDOMListeners(nsIDOMEventTarget, b);
                nsIDOMEventTarget.Release();
                this.unhookedDOMWindows.remove(long1);
                new nsISupports(long1.value).Release();
            }
            boolean b2 = false;
            if (this.htmlBytes != null) {
                final nsIRequest nsIRequest2 = new nsIRequest(request);
                final int nsEmbedCString_new2 = XPCOM.nsEmbedCString_new();
                final int getName = nsIRequest2.GetName(nsEmbedCString_new2);
                if (getName != 0) {
                    error(getName);
                }
                final int nsEmbedCString_Length2 = XPCOM.nsEmbedCString_Length(nsEmbedCString_new2);
                final int nsEmbedCString_get2 = XPCOM.nsEmbedCString_get(nsEmbedCString_new2);
                final byte[] array4 = new byte[nsEmbedCString_Length2];
                C.memmove(array4, nsEmbedCString_get2, nsEmbedCString_Length2);
                final String s = new String(array4);
                XPCOM.nsEmbedCString_delete(nsEmbedCString_new2);
                if (s.startsWith("about:blank")) {
                    this.unhookDOMListeners();
                    final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array3);
                    if (ns_GetServiceManager != 0) {
                        error(ns_GetServiceManager);
                    }
                    if (array3[0] == 0) {
                        error(-2147467262);
                    }
                    final nsIServiceManager nsIServiceManager = new nsIServiceManager(array3[0]);
                    array3[0] = 0;
                    final int getService = nsIServiceManager.GetService(XPCOM.NS_IOSERVICE_CID, nsIIOService.NS_IIOSERVICE_IID, array3);
                    if (getService != 0) {
                        error(getService);
                    }
                    if (array3[0] == 0) {
                        error(-2147467262);
                    }
                    nsIServiceManager.Release();
                    final nsIIOService nsIIOService = new nsIIOService(array3[0]);
                    array3[0] = 0;
                    byte[] array5;
                    if (this.untrustedText) {
                        array5 = MozillaDelegate.wcsToMbcs(null, "about:blank", false);
                    }
                    else {
                        array5 = MozillaDelegate.wcsToMbcs(null, "file:///", false);
                    }
                    final int nsEmbedCString_new3 = XPCOM.nsEmbedCString_new(array5, array5.length);
                    final int newURI = nsIIOService.NewURI(nsEmbedCString_new3, null, 0, array3);
                    if (newURI != 0) {
                        error(newURI);
                    }
                    if (array3[0] == 0) {
                        error(-2147467262);
                    }
                    XPCOM.nsEmbedCString_delete(nsEmbedCString_new3);
                    nsIIOService.Release();
                    final nsIURI nsIURI = new nsIURI(array3[0]);
                    array3[0] = 0;
                    final int queryInterface3 = this.webBrowser.QueryInterface(nsIWebBrowserStream.NS_IWEBBROWSERSTREAM_IID, array3);
                    if (queryInterface3 != 0) {
                        error(queryInterface3);
                    }
                    if (array3[0] == 0) {
                        error(-2147467262);
                    }
                    final nsIWebBrowserStream nsIWebBrowserStream = new nsIWebBrowserStream(array3[0]);
                    array3[0] = 0;
                    final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "text/html", false);
                    final int nsEmbedCString_new4 = XPCOM.nsEmbedCString_new(wcsToMbcs, wcsToMbcs.length);
                    final int openStream = nsIWebBrowserStream.OpenStream(nsIURI.getAddress(), nsEmbedCString_new4);
                    if (openStream != 0) {
                        error(openStream);
                    }
                    final Enumeration<BrowserFunction> elements2 = (Enumeration<BrowserFunction>)this.functions.elements();
                    while (elements2.hasMoreElements()) {
                        this.execute(elements2.nextElement().functionString);
                    }
                    this.registerFunctionsOnState = 65537;
                    final int malloc = C.malloc(this.htmlBytes.length);
                    C.memmove(malloc, this.htmlBytes, this.htmlBytes.length);
                    final int n4 = 8192;
                    final int n5 = this.htmlBytes.length / n4 + 1;
                    int n6 = malloc;
                    for (int i = 0; i < n5; ++i) {
                        final int n7 = (i == n5 - 1) ? (this.htmlBytes.length % n4) : n4;
                        if (n7 > 0) {
                            final int appendToStream = nsIWebBrowserStream.AppendToStream(n6, n7);
                            if (appendToStream != 0) {
                                error(appendToStream);
                            }
                        }
                        n6 += n4;
                    }
                    final int closeStream = nsIWebBrowserStream.CloseStream();
                    if (closeStream != 0) {
                        error(closeStream);
                    }
                    C.free(malloc);
                    XPCOM.nsEmbedCString_delete(nsEmbedCString_new4);
                    nsIWebBrowserStream.Release();
                    nsIURI.Release();
                    this.htmlBytes = null;
                    b2 = true;
                    final int getContentDOMWindow2 = this.webBrowser.GetContentDOMWindow(array3);
                    if (getContentDOMWindow2 != 0) {
                        error(getContentDOMWindow2);
                    }
                    if (array3[0] == 0) {
                        error(-2147467262);
                    }
                    final boolean b3 = array3[0] == nsIDOMWindow.getAddress();
                    new nsISupports(array3[0]).Release();
                    array3[0] = 0;
                    final int queryInterface4 = nsIDOMWindow.QueryInterface(nsIDOMEventTarget.NS_IDOMEVENTTARGET_IID, array3);
                    if (queryInterface4 != 0) {
                        error(queryInterface4);
                    }
                    if (array3[0] == 0) {
                        error(-2147467262);
                    }
                    final nsIDOMEventTarget nsIDOMEventTarget2 = new nsIDOMEventTarget(array3[0]);
                    array3[0] = 0;
                    this.hookDOMListeners(nsIDOMEventTarget2, b3);
                    nsIDOMEventTarget2.Release();
                }
                else {
                    this.registerFunctionsOnState = 0;
                }
            }
            nsIDOMWindow.Release();
            if (this.request == request || this.request == 0) {
                this.request = 0;
                final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
                statusTextEvent.display = this.browser.getDisplay();
                statusTextEvent.widget = this.browser;
                statusTextEvent.text = "";
                for (int j = 0; j < this.statusTextListeners.length; ++j) {
                    this.statusTextListeners[j].changed(statusTextEvent);
                }
                final Display display = this.browser.getDisplay();
                final ProgressEvent progressEvent = new ProgressEvent(this.browser);
                progressEvent.display = display;
                progressEvent.widget = this.browser;
                final Runnable runnable = new Runnable() {
                    public void run() {
                        if (Mozilla.this.browser.isDisposed()) {
                            return;
                        }
                        for (int i = 0; i < Mozilla.this.progressListeners.length; ++i) {
                            Mozilla.this.progressListeners[i].completed(progressEvent);
                        }
                    }
                };
                if (b2) {
                    display.asyncExec(runnable);
                }
                else {
                    display.syncExec(runnable);
                }
            }
        }
        else if ((n2 & 0x4) != 0x0) {
            if (this.updateLastNavigateUrl) {
                this.updateLastNavigateUrl = false;
                final int[] array6 = { 0 };
                final int queryInterface5 = new nsIRequest(request).QueryInterface(nsIChannel.NS_ICHANNEL_IID, array6);
                if (queryInterface5 != 0) {
                    error(queryInterface5);
                }
                if (array6[0] == 0) {
                    error(-2147467262);
                }
                final nsIChannel nsIChannel2 = new nsIChannel(array6[0]);
                array6[0] = 0;
                final int getURI = nsIChannel2.GetURI(array6);
                if (getURI != 0) {
                    error(getURI);
                }
                if (array6[0] == 0) {
                    error(-2147467261);
                }
                nsIChannel2.Release();
                final nsIURI nsIURI2 = new nsIURI(array6[0]);
                array6[0] = 0;
                final int nsEmbedCString_new5 = XPCOM.nsEmbedCString_new();
                final int getSpec = nsIURI2.GetSpec(nsEmbedCString_new5);
                if (getSpec != 0) {
                    error(getSpec);
                }
                final int nsEmbedCString_Length3 = XPCOM.nsEmbedCString_Length(nsEmbedCString_new5);
                final int nsEmbedCString_get3 = XPCOM.nsEmbedCString_get(nsEmbedCString_new5);
                final byte[] array7 = new byte[nsEmbedCString_Length3];
                C.memmove(array7, nsEmbedCString_get3, nsEmbedCString_Length3);
                this.lastNavigateURL = new String(array7);
                XPCOM.nsEmbedCString_delete(nsEmbedCString_new5);
                nsIURI2.Release();
            }
            final int[] array8 = { 0 };
            final int getDOMWindow3 = new nsIWebProgress(n).GetDOMWindow(array8);
            if (getDOMWindow3 != 0) {
                error(getDOMWindow3);
            }
            if (array8[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMWindow nsIDOMWindow2 = new nsIDOMWindow(array8[0]);
            final LONG long2 = new LONG(array8[0]);
            array8[0] = 0;
            if (this.unhookedDOMWindows.indexOf(long2) != -1) {
                final int getContentDOMWindow3 = this.webBrowser.GetContentDOMWindow(array8);
                if (getContentDOMWindow3 != 0) {
                    error(getContentDOMWindow3);
                }
                if (array8[0] == 0) {
                    error(-2147467262);
                }
                final boolean b4 = array8[0] == nsIDOMWindow2.getAddress();
                new nsISupports(array8[0]).Release();
                array8[0] = 0;
                final int queryInterface6 = nsIDOMWindow2.QueryInterface(nsIDOMEventTarget.NS_IDOMEVENTTARGET_IID, array8);
                if (queryInterface6 != 0) {
                    error(queryInterface6);
                }
                if (array8[0] == 0) {
                    error(-2147467262);
                }
                final nsIDOMEventTarget nsIDOMEventTarget3 = new nsIDOMEventTarget(array8[0]);
                array8[0] = 0;
                this.hookDOMListeners(nsIDOMEventTarget3, b4);
                nsIDOMEventTarget3.Release();
                this.unhookedDOMWindows.remove(long2);
                new nsISupports(long2.value).Release();
            }
            nsIDOMWindow2.Release();
        }
        return 0;
    }
    
    int OnProgressChange(final int n, final int n2, final int n3, final int n4, final int current, final int total) {
        if (this.progressListeners.length == 0) {
            return 0;
        }
        final ProgressEvent progressEvent = new ProgressEvent(this.browser);
        progressEvent.display = this.browser.getDisplay();
        progressEvent.widget = this.browser;
        progressEvent.current = current;
        progressEvent.total = total;
        for (int i = 0; i < this.progressListeners.length; ++i) {
            this.progressListeners[i].changed(progressEvent);
        }
        return 0;
    }
    
    int OnLocationChange(final int n, final int request, final int n2) {
        if (this.request != 0 && this.request != request) {
            this.request = request;
        }
        if (this.locationListeners.length == 0) {
            return 0;
        }
        final nsIWebProgress nsIWebProgress = new nsIWebProgress(n);
        final int[] array = { 0 };
        final int getDOMWindow = nsIWebProgress.GetDOMWindow(array);
        if (getDOMWindow != 0) {
            error(getDOMWindow);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIDOMWindow nsIDOMWindow = new nsIDOMWindow(array[0]);
        final int[] array2 = { 0 };
        final int getTop = nsIDOMWindow.GetTop(array2);
        if (getTop != 0) {
            error(getTop);
        }
        if (array2[0] == 0) {
            error(-2147467262);
        }
        nsIDOMWindow.Release();
        new nsIDOMWindow(array2[0]).Release();
        final nsIURI nsIURI = new nsIURI(n2);
        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
        nsIURI.GetSpec(nsEmbedCString_new);
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final byte[] array3 = new byte[nsEmbedCString_Length];
        C.memmove(array3, nsEmbedCString_get, nsEmbedCString_Length);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        final String location = new String(array3);
        if (!Mozilla.IsPre_1_8 && request == 0 && location.startsWith("about:blank")) {
            return 0;
        }
        final LocationEvent locationEvent = new LocationEvent(this.browser);
        locationEvent.display = this.browser.getDisplay();
        locationEvent.widget = this.browser;
        locationEvent.location = location;
        if (locationEvent.location.equals("file:///")) {
            locationEvent.location = "about:blank";
        }
        else {
            final int length = "file:///".length();
            if (locationEvent.location.startsWith("file:///") && locationEvent.location.charAt(length) == '#') {
                locationEvent.location = "about:blank" + locationEvent.location.substring(length);
            }
        }
        locationEvent.top = (array2[0] == array[0]);
        for (int i = 0; i < this.locationListeners.length; ++i) {
            this.locationListeners[i].changed(locationEvent);
        }
        return 0;
    }
    
    int OnStatusChange(final int n, final int n2, final int n3, final int n4) {
        if (this.statusTextListeners.length == 0) {
            return 0;
        }
        final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
        statusTextEvent.display = this.browser.getDisplay();
        statusTextEvent.widget = this.browser;
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n4);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n4, strlen_PRUnichar * 2);
        statusTextEvent.text = new String(array);
        for (int i = 0; i < this.statusTextListeners.length; ++i) {
            this.statusTextListeners[i].changed(statusTextEvent);
        }
        return 0;
    }
    
    int OnSecurityChange(final int n, final int n2, final int n3) {
        return 0;
    }
    
    int SetStatus(final int n, final int n2) {
        if (this.statusTextListeners.length == 0) {
            return 0;
        }
        final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
        statusTextEvent.display = this.browser.getDisplay();
        statusTextEvent.widget = this.browser;
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n2, strlen_PRUnichar * 2);
        statusTextEvent.text = new String(array);
        for (int i = 0; i < this.statusTextListeners.length; ++i) {
            this.statusTextListeners[i].changed(statusTextEvent);
        }
        return 0;
    }
    
    int GetWebBrowser(final int n) {
        final int[] array = { 0 };
        if (this.webBrowser != null) {
            this.webBrowser.AddRef();
            array[0] = this.webBrowser.getAddress();
        }
        C.memmove(n, array, C.PTR_SIZEOF);
        return 0;
    }
    
    int SetWebBrowser(final int n) {
        if (this.webBrowser != null) {
            this.webBrowser.Release();
        }
        this.webBrowser = ((n != 0) ? new nsIWebBrowser(n) : null);
        return 0;
    }
    
    int GetChromeFlags(final int n) {
        C.memmove(n, new int[] { this.chromeFlags }, 4);
        return 0;
    }
    
    int SetChromeFlags(final int chromeFlags) {
        this.chromeFlags = chromeFlags;
        return 0;
    }
    
    int DestroyBrowserWindow() {
        final WindowEvent windowEvent = new WindowEvent(this.browser);
        windowEvent.display = this.browser.getDisplay();
        windowEvent.widget = this.browser;
        for (int i = 0; i < this.closeWindowListeners.length; ++i) {
            this.closeWindowListeners[i].close(windowEvent);
        }
        this.browser.dispose();
        return 0;
    }
    
    int SizeBrowserTo(final int n, final int n2) {
        this.size = new Point(n, n2);
        if ((this.chromeFlags & Integer.MIN_VALUE) != 0x0) {
            final Shell shell = this.browser.getShell();
            shell.setSize(shell.computeSize(this.size.x, this.size.y));
        }
        return 0;
    }
    
    int ShowAsModal() {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/js/xpc/ContextStack;1", true), nsIJSContextStack.NS_IJSCONTEXTSTACK_IID, array);
        if (getServiceByContractID != 0) {
            error(getServiceByContractID);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIJSContextStack nsIJSContextStack = new nsIJSContextStack(array[0]);
        array[0] = 0;
        final int push = nsIJSContextStack.Push(0);
        if (push != 0) {
            error(push);
        }
        final Shell shell = this.browser.getShell();
        final Display display = this.browser.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        final int pop = nsIJSContextStack.Pop(array);
        if (pop != 0) {
            error(pop);
        }
        nsIJSContextStack.Release();
        return 0;
    }
    
    int IsWindowModal(final int n) {
        C.memmove(n, new int[] { (this.chromeFlags & 0x20000000) != 0x0 }, 4);
        return 0;
    }
    
    int ExitModalEventLoop(final int n) {
        return 0;
    }
    
    int SetDimensions(final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x1) != 0x0) {
            this.location = new Point(n2, n3);
            this.browser.getShell().setLocation(n2, n3);
        }
        if ((n & 0x2) != 0x0) {
            this.browser.setSize(n4, n5);
        }
        if ((n & 0x4) != 0x0) {
            this.browser.getShell().setSize(n4, n5);
        }
        return 0;
    }
    
    int GetDimensions(final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x1) != 0x0) {
            final Point location = this.browser.getShell().getLocation();
            if (n2 != 0) {
                C.memmove(n2, new int[] { location.x }, 4);
            }
            if (n3 != 0) {
                C.memmove(n3, new int[] { location.y }, 4);
            }
        }
        if ((n & 0x2) != 0x0) {
            final Point size = this.browser.getSize();
            if (n4 != 0) {
                C.memmove(n4, new int[] { size.x }, 4);
            }
            if (n5 != 0) {
                C.memmove(n5, new int[] { size.y }, 4);
            }
        }
        if ((n & 0x4) != 0x0) {
            final Point size2 = this.browser.getShell().getSize();
            if (n4 != 0) {
                C.memmove(n4, new int[] { size2.x }, 4);
            }
            if (n5 != 0) {
                C.memmove(n5, new int[] { size2.y }, 4);
            }
        }
        return 0;
    }
    
    int SetFocus() {
        final int[] array = { 0 };
        final int queryInterface = this.webBrowser.QueryInterface(nsIBaseWindow.NS_IBASEWINDOW_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIBaseWindow nsIBaseWindow = new nsIBaseWindow(array[0]);
        final int setFocus = nsIBaseWindow.SetFocus();
        if (setFocus != 0) {
            error(setFocus);
        }
        nsIBaseWindow.Release();
        return 0;
    }
    
    int GetVisibility(final int n) {
        C.memmove(n, new int[] { this.browser.isVisible() && !this.browser.getShell().getMinimized() }, 4);
        return 0;
    }
    
    int SetVisibility(final int n) {
        if (this.isChild) {
            final WindowEvent windowEvent = new WindowEvent(this.browser);
            windowEvent.display = this.browser.getDisplay();
            windowEvent.widget = this.browser;
            if (n != 0) {
                if (!this.visible) {
                    this.visible = true;
                    windowEvent.location = this.location;
                    windowEvent.size = this.size;
                    windowEvent.addressBar = ((this.chromeFlags & 0x40) != 0x0);
                    windowEvent.menuBar = ("win32".equals("cocoa") || "win32".equals("carbon") || (this.chromeFlags & 0x10) != 0x0);
                    windowEvent.statusBar = ((this.chromeFlags & 0x80) != 0x0);
                    windowEvent.toolBar = ((this.chromeFlags & 0x20) != 0x0);
                    for (int i = 0; i < this.visibilityWindowListeners.length; ++i) {
                        this.visibilityWindowListeners[i].show(windowEvent);
                    }
                    this.location = null;
                    this.size = null;
                }
            }
            else {
                this.visible = false;
                for (int j = 0; j < this.visibilityWindowListeners.length; ++j) {
                    this.visibilityWindowListeners[j].hide(windowEvent);
                }
            }
        }
        return 0;
    }
    
    int GetTitle(final int n) {
        return 0;
    }
    
    int SetTitle(final int n) {
        if (this.titleListeners.length == 0) {
            return 0;
        }
        final TitleEvent titleEvent = new TitleEvent(this.browser);
        titleEvent.display = this.browser.getDisplay();
        titleEvent.widget = this.browser;
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n);
        if (strlen_PRUnichar > 0) {
            final char[] array = new char[strlen_PRUnichar];
            C.memmove(array, n, strlen_PRUnichar * 2);
            titleEvent.title = new String(array);
        }
        else {
            titleEvent.title = this.getUrl();
        }
        for (int i = 0; i < this.titleListeners.length; ++i) {
            this.titleListeners[i].changed(titleEvent);
        }
        return 0;
    }
    
    int GetSiteWindow(final int n) {
        C.memmove(n, new int[] { this.embedHandle }, C.PTR_SIZEOF);
        return 0;
    }
    
    int FocusNextElement() {
        this.browser.getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (Mozilla.this.browser.isDisposed()) {
                    return;
                }
                Mozilla.this.browser.traverse(16);
            }
        });
        return 0;
    }
    
    int FocusPrevElement() {
        this.browser.getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (Mozilla.this.browser.isDisposed()) {
                    return;
                }
                Mozilla.this.browser.traverse(8);
            }
        });
        return 0;
    }
    
    int OnShowContextMenu(final int n, final int n2, final int n3) {
        final nsIDOMEvent nsIDOMEvent = new nsIDOMEvent(n2);
        final int[] array = { 0 };
        final int queryInterface = nsIDOMEvent.QueryInterface(nsIDOMMouseEvent.NS_IDOMMOUSEEVENT_IID, array);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array[0] == 0) {
            error(-2147467262);
        }
        final nsIDOMMouseEvent nsIDOMMouseEvent = new nsIDOMMouseEvent(array[0]);
        final int[] array2 = { 0 };
        final int[] array3 = { 0 };
        final int getScreenX = nsIDOMMouseEvent.GetScreenX(array2);
        if (getScreenX != 0) {
            error(getScreenX);
        }
        final int getScreenY = nsIDOMMouseEvent.GetScreenY(array3);
        if (getScreenY != 0) {
            error(getScreenY);
        }
        nsIDOMMouseEvent.Release();
        final Event event = new Event();
        event.x = array2[0];
        event.y = array3[0];
        this.browser.notifyListeners(35, event);
        if (!event.doit || this.browser.isDisposed()) {
            return 0;
        }
        final Menu menu = this.browser.getMenu();
        if (menu != null && !menu.isDisposed()) {
            if (array2[0] != event.x || array3[0] != event.y) {
                menu.setLocation(event.x, event.y);
            }
            menu.setVisible(true);
        }
        return 0;
    }
    
    int OnStartURIOpen(final int n, final int n2) {
        if (this.isRetrievingBadCert) {
            return 0;
        }
        this.authCount = 0;
        final nsIURI nsIURI = new nsIURI(n);
        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
        nsIURI.GetSpec(nsEmbedCString_new);
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
        XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final byte[] array = new byte[nsEmbedCString_Length];
        C.memmove(array, nsEmbedCString_get, nsEmbedCString_Length);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        final String s = new String(array);
        if (s.indexOf("aboutCertError.xhtml") != -1 || (this.isViewingErrorPage && s.indexOf("javascript:showSecuritySection") != -1)) {
            C.memmove(n2, new int[] { 1 }, 4);
            this.isRetrievingBadCert = true;
            this.setUrl(this.lastNavigateURL, null, null);
            return 0;
        }
        this.isViewingErrorPage = (s.indexOf("netError.xhtml") != -1);
        boolean doit = true;
        if (this.request == 0 && !s.startsWith("javascript:")) {
            if (this.locationListeners.length > 0) {
                final LocationEvent locationEvent = new LocationEvent(this.browser);
                locationEvent.display = this.browser.getDisplay();
                locationEvent.widget = this.browser;
                locationEvent.location = s;
                if (locationEvent.location.equals("file:///")) {
                    locationEvent.location = "about:blank";
                }
                else {
                    final int length = "file:///".length();
                    if (locationEvent.location.startsWith("file:///") && locationEvent.location.charAt(length) == '#') {
                        locationEvent.location = "about:blank" + locationEvent.location.substring(length);
                    }
                }
                locationEvent.doit = doit;
                for (int i = 0; i < this.locationListeners.length; ++i) {
                    this.locationListeners[i].changing(locationEvent);
                }
                doit = (locationEvent.doit && !this.browser.isDisposed());
            }
            if (doit) {
                if (this.jsEnabledChanged) {
                    this.jsEnabledChanged = false;
                    final int[] array2 = { 0 };
                    final int queryInterface = this.webBrowser.QueryInterface(nsIWebBrowserSetup.NS_IWEBBROWSERSETUP_IID, array2);
                    if (queryInterface != 0) {
                        error(queryInterface);
                    }
                    if (array2[0] == 0) {
                        error(-2147467262);
                    }
                    final nsIWebBrowserSetup nsIWebBrowserSetup = new nsIWebBrowserSetup(array2[0]);
                    array2[0] = 0;
                    final int setProperty = nsIWebBrowserSetup.SetProperty(2, this.jsEnabled ? 1 : 0);
                    if (setProperty != 0) {
                        error(setProperty);
                    }
                    nsIWebBrowserSetup.Release();
                }
                if (!this.isViewingErrorPage) {
                    this.lastNavigateURL = s;
                }
            }
        }
        C.memmove(n2, new int[] { !doit }, 4);
        return 0;
    }
    
    int DoContent(final int n, final int n2, final int n3, final int n4, final int n5) {
        return -2147467263;
    }
    
    int IsPreferred(final int n, final int n2, final int n3) {
        boolean b = false;
        final int strlen = C.strlen(n);
        if (strlen > 0) {
            final byte[] array = new byte[strlen + 1];
            C.memmove(array, n, strlen);
            final String s = new String(array, 0, strlen);
            if (!s.equals("application/x-vnd.mozilla.maybe-text") && !s.equals("multipart/x-mixed-replace")) {
                final int[] array2 = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array2);
                if (ns_GetServiceManager != 0) {
                    error(ns_GetServiceManager);
                }
                if (array2[0] == 0) {
                    error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array2[0]);
                array2[0] = 0;
                if (nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/webnavigation-info;1", true), nsIWebNavigationInfo.NS_IWEBNAVIGATIONINFO_IID, array2) == 0) {
                    final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, s, false);
                    final int nsEmbedCString_new = XPCOM.nsEmbedCString_new(wcsToMbcs, wcsToMbcs.length);
                    final nsIWebNavigationInfo nsIWebNavigationInfo = new nsIWebNavigationInfo(array2[0]);
                    array2[0] = 0;
                    final int[] array3 = { 0 };
                    final int isTypeSupported = nsIWebNavigationInfo.IsTypeSupported(nsEmbedCString_new, 0, array3);
                    if (isTypeSupported != 0) {
                        error(isTypeSupported);
                    }
                    nsIWebNavigationInfo.Release();
                    XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
                    b = (array3[0] != 0);
                }
                else {
                    array2[0] = 0;
                    final int getService = nsIServiceManager.GetService(XPCOM.NS_CATEGORYMANAGER_CID, nsICategoryManager.NS_ICATEGORYMANAGER_IID, array2);
                    if (getService != 0) {
                        error(getService);
                    }
                    if (array2[0] == 0) {
                        error(-2147467262);
                    }
                    final nsICategoryManager nsICategoryManager = new nsICategoryManager(array2[0]);
                    array2[0] = 0;
                    final int getCategoryEntry = nsICategoryManager.GetCategoryEntry(MozillaDelegate.wcsToMbcs(null, "Gecko-Content-Viewers", true), array, array2);
                    nsICategoryManager.Release();
                    b = (getCategoryEntry == 0);
                }
                nsIServiceManager.Release();
            }
        }
        C.memmove(n3, new int[] { b }, 4);
        if (b) {
            C.memmove(n2, new int[1], C.PTR_SIZEOF);
        }
        return 0;
    }
    
    int CanHandleContent(final int n, final int n2, final int n3, final int n4) {
        return -2147467263;
    }
    
    int GetLoadCookie(final int n) {
        return -2147467263;
    }
    
    int SetLoadCookie(final int n) {
        return -2147467263;
    }
    
    int GetParentContentListener(final int n) {
        return -2147467263;
    }
    
    int SetParentContentListener(final int n) {
        return -2147467263;
    }
    
    int OnShowTooltip(final int n, final int n2, final int n3) {
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n3);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n3, strlen_PRUnichar * 2);
        final String text = new String(array);
        if (this.tip != null && !this.tip.isDisposed()) {
            this.tip.dispose();
        }
        final Display display = this.browser.getDisplay();
        (this.tip = new Shell(this.browser.getShell(), 16384)).setLayout(new FillLayout());
        final Label label = new Label(this.tip, 16777216);
        label.setForeground(display.getSystemColor(28));
        label.setBackground(display.getSystemColor(29));
        label.setText(text);
        final Point cursorLocation;
        final Point location = cursorLocation = display.getCursorLocation();
        cursorLocation.y += 21;
        this.tip.setLocation(location);
        this.tip.pack();
        this.tip.setVisible(true);
        return 0;
    }
    
    int OnHideTooltip() {
        if (this.tip != null && !this.tip.isDisposed()) {
            this.tip.dispose();
        }
        this.tip = null;
        return 0;
    }
    
    int HandleEvent(final int n) {
        final nsIDOMEvent nsIDOMEvent = new nsIDOMEvent(n);
        final int nsEmbedString_new = XPCOM.nsEmbedString_new();
        final int getType = nsIDOMEvent.GetType(nsEmbedString_new);
        if (getType != 0) {
            error(getType);
        }
        final int nsEmbedString_Length = XPCOM.nsEmbedString_Length(nsEmbedString_new);
        final int nsEmbedString_get = XPCOM.nsEmbedString_get(nsEmbedString_new);
        final char[] array = new char[nsEmbedString_Length];
        C.memmove(array, nsEmbedString_get, nsEmbedString_Length * 2);
        final String s = new String(array);
        XPCOM.nsEmbedString_delete(nsEmbedString_new);
        if ("unload".equals(s)) {
            final int[] array2 = { 0 };
            final int getCurrentTarget = nsIDOMEvent.GetCurrentTarget(array2);
            if (getCurrentTarget != 0) {
                error(getCurrentTarget);
            }
            if (array2[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMEventTarget nsIDOMEventTarget = new nsIDOMEventTarget(array2[0]);
            this.unhookDOMListeners(nsIDOMEventTarget);
            nsIDOMEventTarget.Release();
            return 0;
        }
        if ("focus".equals(s)) {
            this.delegate.handleFocus();
            return 0;
        }
        if ("keydown".equals(s)) {
            final int[] array3 = { 0 };
            final int queryInterface = nsIDOMEvent.QueryInterface(nsIDOMKeyEvent.NS_IDOMKEYEVENT_IID, array3);
            if (queryInterface != 0) {
                error(queryInterface);
            }
            if (array3[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMKeyEvent nsIDOMKeyEvent = new nsIDOMKeyEvent(array3[0]);
            array3[0] = 0;
            final int[] array4 = { 0 };
            final int getKeyCode = nsIDOMKeyEvent.GetKeyCode(array4);
            if (getKeyCode != 0) {
                error(getKeyCode);
            }
            final int translateKey = this.translateKey(array4[0]);
            if (translateKey != this.lastKeyCode) {
                switch (this.lastKeyCode = translateKey) {
                    case 65536:
                    case 131072:
                    case 262144:
                    case 4194304:
                    case 16777298:
                    case 16777299:
                    case 16777300: {
                        final int[] array5 = { 0 };
                        final int[] array6 = { 0 };
                        final int[] array7 = { 0 };
                        final int[] array8 = { 0 };
                        final int getAltKey = nsIDOMKeyEvent.GetAltKey(array5);
                        if (getAltKey != 0) {
                            error(getAltKey);
                        }
                        final int getCtrlKey = nsIDOMKeyEvent.GetCtrlKey(array6);
                        if (getCtrlKey != 0) {
                            error(getCtrlKey);
                        }
                        final int getShiftKey = nsIDOMKeyEvent.GetShiftKey(array7);
                        if (getShiftKey != 0) {
                            error(getShiftKey);
                        }
                        final int getMetaKey = nsIDOMKeyEvent.GetMetaKey(array8);
                        if (getMetaKey != 0) {
                            error(getMetaKey);
                        }
                        final Event event = new Event();
                        event.widget = this.browser;
                        event.type = 1;
                        event.keyCode = translateKey;
                        event.stateMask = (((array5[0] != 0) ? 65536 : 0) | ((array6[0] != 0) ? 262144 : 0) | ((array7[0] != 0) ? 131072 : 0) | ((array8[0] != 0) ? 4194304 : 0));
                        final Event event2 = event;
                        event2.stateMask &= ~translateKey;
                        this.browser.notifyListeners(event.type, event);
                        if (!event.doit || this.browser.isDisposed()) {
                            nsIDOMEvent.PreventDefault();
                            break;
                        }
                        break;
                    }
                    default: {
                        final int[] array9 = { 0 };
                        final int getMetaKey2 = nsIDOMKeyEvent.GetMetaKey(array9);
                        if (getMetaKey2 != 0) {
                            error(getMetaKey2);
                        }
                        if (array9[0] == 0) {
                            break;
                        }
                        final int[] array10 = { 0 };
                        final int getCtrlKey2 = nsIDOMKeyEvent.GetCtrlKey(array10);
                        if (getCtrlKey2 != 0) {
                            error(getCtrlKey2);
                        }
                        if (array10[0] != 0) {
                            break;
                        }
                        final int[] array11 = { 0 };
                        final int[] array12 = { 0 };
                        final int getAltKey2 = nsIDOMKeyEvent.GetAltKey(array11);
                        if (getAltKey2 != 0) {
                            error(getAltKey2);
                        }
                        final int getShiftKey2 = nsIDOMKeyEvent.GetShiftKey(array12);
                        if (getShiftKey2 != 0) {
                            error(getShiftKey2);
                        }
                        final Event event3 = new Event();
                        event3.widget = this.browser;
                        event3.type = 1;
                        event3.keyCode = this.lastKeyCode;
                        event3.stateMask = (((array11[0] != 0) ? 65536 : 0) | ((array10[0] != 0) ? 262144 : 0) | ((array12[0] != 0) ? 131072 : 0) | ((array9[0] != 0) ? 4194304 : 0));
                        this.browser.notifyListeners(event3.type, event3);
                        if (!event3.doit || this.browser.isDisposed()) {
                            nsIDOMEvent.PreventDefault();
                            break;
                        }
                        break;
                    }
                }
            }
            nsIDOMKeyEvent.Release();
            return 0;
        }
        if ("keypress".equals(s)) {
            if (this.lastKeyCode == 0) {
                return 0;
            }
            switch (this.lastKeyCode) {
                case 16777298:
                case 16777299:
                case 16777300: {
                    return 0;
                }
                default: {
                    final int[] array13 = { 0 };
                    final int queryInterface2 = nsIDOMEvent.QueryInterface(nsIDOMKeyEvent.NS_IDOMKEYEVENT_IID, array13);
                    if (queryInterface2 != 0) {
                        error(queryInterface2);
                    }
                    if (array13[0] == 0) {
                        error(-2147467262);
                    }
                    final nsIDOMKeyEvent nsIDOMKeyEvent2 = new nsIDOMKeyEvent(array13[0]);
                    array13[0] = 0;
                    final int[] array14 = { 0 };
                    final int[] array15 = { 0 };
                    final int[] array16 = { 0 };
                    final int[] array17 = { 0 };
                    final int getAltKey3 = nsIDOMKeyEvent2.GetAltKey(array14);
                    if (getAltKey3 != 0) {
                        error(getAltKey3);
                    }
                    final int getCtrlKey3 = nsIDOMKeyEvent2.GetCtrlKey(array15);
                    if (getCtrlKey3 != 0) {
                        error(getCtrlKey3);
                    }
                    final int getShiftKey3 = nsIDOMKeyEvent2.GetShiftKey(array16);
                    if (getShiftKey3 != 0) {
                        error(getShiftKey3);
                    }
                    final int getMetaKey3 = nsIDOMKeyEvent2.GetMetaKey(array17);
                    if (getMetaKey3 != 0) {
                        error(getMetaKey3);
                    }
                    nsIDOMKeyEvent2.Release();
                    final int[] array18 = { 0 };
                    final int getCharCode = nsIDOMKeyEvent2.GetCharCode(array18);
                    if (getCharCode != 0) {
                        error(getCharCode);
                    }
                    this.lastCharCode = array18[0];
                    if (this.lastCharCode == 0) {
                        switch (this.lastKeyCode) {
                            case 9: {
                                this.lastCharCode = 9;
                                break;
                            }
                            case 13: {
                                this.lastCharCode = 13;
                                break;
                            }
                            case 8: {
                                this.lastCharCode = 8;
                                break;
                            }
                            case 27: {
                                this.lastCharCode = 27;
                                break;
                            }
                            case 127: {
                                this.lastCharCode = 127;
                                break;
                            }
                        }
                    }
                    if (array15[0] != 0 && this.lastCharCode >= 0 && this.lastCharCode <= 127) {
                        if (97 <= this.lastCharCode && this.lastCharCode <= 122) {
                            this.lastCharCode -= 32;
                        }
                        if (64 <= this.lastCharCode && this.lastCharCode <= 95) {
                            this.lastCharCode -= 64;
                        }
                    }
                    final Event event4 = new Event();
                    event4.widget = this.browser;
                    event4.type = 1;
                    event4.keyCode = this.lastKeyCode;
                    event4.character = (char)this.lastCharCode;
                    event4.stateMask = (((array14[0] != 0) ? 65536 : 0) | ((array15[0] != 0) ? 262144 : 0) | ((array16[0] != 0) ? 131072 : 0) | ((array17[0] != 0) ? 4194304 : 0));
                    boolean b;
                    if (this.delegate.sendTraverse()) {
                        b = this.sendKeyEvent(event4);
                    }
                    else {
                        this.browser.notifyListeners(event4.type, event4);
                        b = event4.doit;
                    }
                    if (!b || this.browser.isDisposed()) {
                        nsIDOMEvent.PreventDefault();
                    }
                    return 0;
                }
            }
        }
        else if ("keyup".equals(s)) {
            final int[] array19 = { 0 };
            final int queryInterface3 = nsIDOMEvent.QueryInterface(nsIDOMKeyEvent.NS_IDOMKEYEVENT_IID, array19);
            if (queryInterface3 != 0) {
                error(queryInterface3);
            }
            if (array19[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMKeyEvent nsIDOMKeyEvent3 = new nsIDOMKeyEvent(array19[0]);
            array19[0] = 0;
            final int[] array20 = { 0 };
            final int getKeyCode2 = nsIDOMKeyEvent3.GetKeyCode(array20);
            if (getKeyCode2 != 0) {
                error(getKeyCode2);
            }
            final int translateKey2 = this.translateKey(array20[0]);
            if (translateKey2 == 0) {
                nsIDOMKeyEvent3.Release();
                return 0;
            }
            if (translateKey2 != this.lastKeyCode) {
                this.lastKeyCode = translateKey2;
                this.lastCharCode = 0;
            }
            final int[] array21 = { 0 };
            final int[] array22 = { 0 };
            final int[] array23 = { 0 };
            final int[] array24 = { 0 };
            final int getAltKey4 = nsIDOMKeyEvent3.GetAltKey(array21);
            if (getAltKey4 != 0) {
                error(getAltKey4);
            }
            final int getCtrlKey4 = nsIDOMKeyEvent3.GetCtrlKey(array22);
            if (getCtrlKey4 != 0) {
                error(getCtrlKey4);
            }
            final int getShiftKey4 = nsIDOMKeyEvent3.GetShiftKey(array23);
            if (getShiftKey4 != 0) {
                error(getShiftKey4);
            }
            final int getMetaKey4 = nsIDOMKeyEvent3.GetMetaKey(array24);
            if (getMetaKey4 != 0) {
                error(getMetaKey4);
            }
            nsIDOMKeyEvent3.Release();
            final Event event5 = new Event();
            event5.widget = this.browser;
            event5.type = 2;
            event5.keyCode = this.lastKeyCode;
            event5.character = (char)this.lastCharCode;
            event5.stateMask = (((array21[0] != 0) ? 65536 : 0) | ((array22[0] != 0) ? 262144 : 0) | ((array23[0] != 0) ? 131072 : 0) | ((array24[0] != 0) ? 4194304 : 0));
            switch (this.lastKeyCode) {
                case 65536:
                case 131072:
                case 262144:
                case 4194304: {
                    final Event event6 = event5;
                    event6.stateMask |= this.lastKeyCode;
                    break;
                }
            }
            this.browser.notifyListeners(event5.type, event5);
            if (!event5.doit || this.browser.isDisposed()) {
                nsIDOMEvent.PreventDefault();
            }
            final boolean b2 = false;
            this.lastCharCode = (b2 ? 1 : 0);
            this.lastKeyCode = (b2 ? 1 : 0);
            return 0;
        }
        else {
            final int[] array25 = { 0 };
            final int queryInterface4 = nsIDOMEvent.QueryInterface(nsIDOMMouseEvent.NS_IDOMMOUSEEVENT_IID, array25);
            if (queryInterface4 != 0) {
                error(queryInterface4);
            }
            if (array25[0] == 0) {
                error(-2147467262);
            }
            final nsIDOMMouseEvent nsIDOMMouseEvent = new nsIDOMMouseEvent(array25[0]);
            array25[0] = 0;
            if ("mouseover".equals(s) || "mouseout".equals(s)) {
                final int getRelatedTarget = nsIDOMMouseEvent.GetRelatedTarget(array25);
                if (getRelatedTarget != 0) {
                    error(getRelatedTarget);
                }
                if (array25[0] != 0) {
                    nsIDOMMouseEvent.Release();
                    return 0;
                }
            }
            final int[] array26 = { 0 };
            final int[] array27 = { 0 };
            final int getScreenX = nsIDOMMouseEvent.GetScreenX(array26);
            if (getScreenX != 0) {
                error(getScreenX);
            }
            final int getScreenY = nsIDOMMouseEvent.GetScreenY(array27);
            if (getScreenY != 0) {
                error(getScreenY);
            }
            final Point map = this.browser.getDisplay().map(null, this.browser, new Point(array26[0], array27[0]));
            final int[] array28 = { 0 };
            final int getDetail = nsIDOMMouseEvent.GetDetail(array28);
            if (getDetail != 0) {
                error(getDetail);
            }
            final short[] array29 = { 0 };
            final int getButton = nsIDOMMouseEvent.GetButton(array29);
            if (getButton != 0) {
                error(getButton);
            }
            final int[] array30 = { 0 };
            final int[] array31 = { 0 };
            final int[] array32 = { 0 };
            final int[] array33 = { 0 };
            final int getAltKey5 = nsIDOMMouseEvent.GetAltKey(array30);
            if (getAltKey5 != 0) {
                error(getAltKey5);
            }
            final int getCtrlKey5 = nsIDOMMouseEvent.GetCtrlKey(array31);
            if (getCtrlKey5 != 0) {
                error(getCtrlKey5);
            }
            final int getShiftKey5 = nsIDOMMouseEvent.GetShiftKey(array32);
            if (getShiftKey5 != 0) {
                error(getShiftKey5);
            }
            final int getMetaKey5 = nsIDOMMouseEvent.GetMetaKey(array33);
            if (getMetaKey5 != 0) {
                error(getMetaKey5);
            }
            nsIDOMMouseEvent.Release();
            final Event event7 = new Event();
            event7.widget = this.browser;
            event7.x = map.x;
            event7.y = map.y;
            event7.stateMask = (((array30[0] != 0) ? 65536 : 0) | ((array31[0] != 0) ? 262144 : 0) | ((array32[0] != 0) ? 131072 : 0) | ((array33[0] != 0) ? 4194304 : 0));
            if ("mousedown".equals(s)) {
                this.delegate.handleMouseDown();
                event7.type = 3;
                event7.button = array29[0] + 1;
                event7.count = array28[0];
            }
            else if ("mouseup".equals(s)) {
                final short button = (short)(array29[0] + 1);
                final int count = array28[0];
                if (count == 0 && button == 3) {
                    return 0;
                }
                event7.type = 4;
                event7.button = button;
                event7.count = count;
            }
            else if ("mousemove".equals(s)) {
                event7.type = 5;
            }
            else if ("DOMMouseScroll".equals(s)) {
                event7.type = 37;
                event7.count = -array28[0];
            }
            else if ("mouseover".equals(s)) {
                event7.type = 6;
            }
            else if ("mouseout".equals(s)) {
                event7.type = 7;
            }
            else if ("draggesture".equals(s)) {
                event7.type = 29;
                switch (event7.button = array29[0] + 1) {
                    case 1: {
                        final Event event8 = event7;
                        event8.stateMask |= 0x80000;
                        break;
                    }
                    case 2: {
                        final Event event9 = event7;
                        event9.stateMask |= 0x100000;
                        break;
                    }
                    case 3: {
                        final Event event10 = event7;
                        event10.stateMask |= 0x200000;
                        break;
                    }
                    case 4: {
                        final Event event11 = event7;
                        event11.stateMask |= 0x800000;
                        break;
                    }
                    case 5: {
                        final Event event12 = event7;
                        event12.stateMask |= 0x2000000;
                        break;
                    }
                }
            }
            this.browser.notifyListeners(event7.type, event7);
            if (this.browser.isDisposed()) {
                return 0;
            }
            if (array28[0] == 2 && "mousedown".equals(s)) {
                final Event event13 = new Event();
                event13.widget = this.browser;
                event13.x = map.x;
                event13.y = map.y;
                event13.stateMask = (((array30[0] != 0) ? 65536 : 0) | ((array31[0] != 0) ? 262144 : 0) | ((array32[0] != 0) ? 131072 : 0) | ((array33[0] != 0) ? 4194304 : 0));
                event13.type = 8;
                event13.button = array29[0] + 1;
                event13.count = array28[0];
                this.browser.notifyListeners(event13.type, event13);
            }
            return 0;
        }
    }
    
    int NotifyCertProblem(final int n, final int n2, final int n3, final int n4) {
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(n3);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(n3);
        final byte[] array = new byte[nsEmbedCString_Length];
        C.memmove(array, nsEmbedCString_get, nsEmbedCString_Length);
        final String s = new String(array);
        final int index = s.indexOf(58);
        final String substring = s.substring(0, index);
        final int intValue = Integer.valueOf(s.substring(index + 1));
        final int[] array2 = { 0 };
        final int queryInterface = new nsISupports(n2).QueryInterface(nsISSLStatus.NS_ISSLSTATUS_IID, array2);
        if (queryInterface != 0) {
            error(queryInterface);
        }
        if (array2[0] == 0) {
            error(-2147467262);
        }
        final nsISSLStatus nsISSLStatus = new nsISSLStatus(array2[0]);
        array2[0] = 0;
        final int getServerCert = nsISSLStatus.GetServerCert(array2);
        if (getServerCert != 0) {
            error(getServerCert);
        }
        if (array2[0] == 0) {
            error(-2147467261);
        }
        final nsIX509Cert nsIX509Cert = new nsIX509Cert(array2[0]);
        array2[0] = 0;
        final String[] array3 = new String[3];
        int n5 = 0;
        int n6 = 0;
        final int[] array4 = { 0 };
        nsISSLStatus.GetIsDomainMismatch(array4);
        if (array4[0] != 0) {
            final int nsEmbedString_new = XPCOM.nsEmbedString_new();
            final int getCommonName = nsIX509Cert.GetCommonName(nsEmbedString_new);
            if (getCommonName != 0) {
                SWT.error(getCommonName);
            }
            final int nsEmbedString_Length = XPCOM.nsEmbedString_Length(nsEmbedString_new);
            final int nsEmbedString_get = XPCOM.nsEmbedString_get(nsEmbedString_new);
            final char[] array5 = new char[nsEmbedString_Length];
            C.memmove(array5, nsEmbedString_get, nsEmbedString_Length * 2);
            array3[n5++] = Compatibility.getMessage("SWT_InvalidCert_InvalidName", new String[] { new String(array5) });
            n6 |= 0x2;
            XPCOM.nsEmbedString_delete(nsEmbedString_new);
        }
        array4[0] = 0;
        nsISSLStatus.GetIsNotValidAtThisTime(array4);
        if (array4[0] != 0) {
            final int getValidity = nsIX509Cert.GetValidity(array2);
            if (getValidity != 0) {
                SWT.error(getValidity);
            }
            if (array2[0] == 0) {
                error(-2147467261);
            }
            final nsIX509CertValidity nsIX509CertValidity = new nsIX509CertValidity(array2[0]);
            array2[0] = 0;
            final int nsEmbedString_new2 = XPCOM.nsEmbedString_new();
            final int getNotBeforeGMT = nsIX509CertValidity.GetNotBeforeGMT(nsEmbedString_new2);
            if (getNotBeforeGMT != 0) {
                SWT.error(getNotBeforeGMT);
            }
            final int nsEmbedString_Length2 = XPCOM.nsEmbedString_Length(nsEmbedString_new2);
            final int nsEmbedString_get2 = XPCOM.nsEmbedString_get(nsEmbedString_new2);
            final char[] array6 = new char[nsEmbedString_Length2];
            C.memmove(array6, nsEmbedString_get2, nsEmbedString_Length2 * 2);
            final String s2 = new String(array6);
            XPCOM.nsEmbedString_delete(nsEmbedString_new2);
            final int nsEmbedString_new3 = XPCOM.nsEmbedString_new();
            final int getNotAfterGMT = nsIX509CertValidity.GetNotAfterGMT(nsEmbedString_new3);
            if (getNotAfterGMT != 0) {
                SWT.error(getNotAfterGMT);
            }
            final int nsEmbedString_Length3 = XPCOM.nsEmbedString_Length(nsEmbedString_new3);
            final int nsEmbedString_get3 = XPCOM.nsEmbedString_get(nsEmbedString_new3);
            final char[] array7 = new char[nsEmbedString_Length3];
            C.memmove(array7, nsEmbedString_get3, nsEmbedString_Length3 * 2);
            final String s3 = new String(array7);
            XPCOM.nsEmbedString_delete(nsEmbedString_new3);
            array3[n5++] = Compatibility.getMessage("SWT_InvalidCert_NotValid", new String[] { String.valueOf(s2) + " - " + s3 });
            n6 |= 0x4;
            nsIX509CertValidity.Release();
        }
        array4[0] = 0;
        nsISSLStatus.GetIsUntrusted(array4);
        if (array4[0] != 0) {
            final int nsEmbedString_new4 = XPCOM.nsEmbedString_new();
            final int getIssuerCommonName = nsIX509Cert.GetIssuerCommonName(nsEmbedString_new4);
            if (getIssuerCommonName != 0) {
                SWT.error(getIssuerCommonName);
            }
            final int nsEmbedString_Length4 = XPCOM.nsEmbedString_Length(nsEmbedString_new4);
            final int nsEmbedString_get4 = XPCOM.nsEmbedString_get(nsEmbedString_new4);
            final char[] array8 = new char[nsEmbedString_Length4];
            C.memmove(array8, nsEmbedString_get4, nsEmbedString_Length4 * 2);
            array3[n5++] = Compatibility.getMessage("SWT_InvalidCert_NotTrusted", new String[] { new String(array8) });
            n6 |= 0x1;
            XPCOM.nsEmbedString_delete(nsEmbedString_new4);
        }
        array4[0] = 0;
        nsISSLStatus.Release();
        final int n7 = n6;
        final String[] array9 = new String[n5];
        System.arraycopy(array3, 0, array9, 0, n5);
        this.browser.getDisplay().asyncExec(new Runnable() {
            private final /* synthetic */ String val$url = Mozilla.this.lastNavigateURL;
            
            public void run() {
                if (Mozilla.this.browser.isDisposed()) {
                    return;
                }
                if (!this.val$url.equals(Mozilla.this.lastNavigateURL)) {
                    return;
                }
                if (new PromptDialog(Mozilla.this.browser.getShell()).invalidCert(Mozilla.this.browser, Compatibility.getMessage("SWT_InvalidCert_Message", new String[] { s }), array9, nsIX509Cert)) {
                    final int[] array = { 0 };
                    final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
                    if (ns_GetServiceManager != 0) {
                        Mozilla.error(ns_GetServiceManager);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
                    array[0] = 0;
                    final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/security/certoverride;1", true), nsICertOverrideService.NS_ICERTOVERRIDESERVICE_IID, array);
                    if (getServiceByContractID != 0) {
                        Mozilla.error(getServiceByContractID);
                    }
                    if (array[0] == 0) {
                        Mozilla.error(-2147467262);
                    }
                    nsIServiceManager.Release();
                    final nsICertOverrideService nsICertOverrideService = new nsICertOverrideService(array[0]);
                    array[0] = 0;
                    final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, substring, false);
                    final int nsEmbedCString_new = XPCOM.nsEmbedCString_new(wcsToMbcs, wcsToMbcs.length);
                    nsICertOverrideService.RememberValidityOverride(nsEmbedCString_new, intValue, nsIX509Cert.getAddress(), n7, 1);
                    Mozilla.this.browser.setUrl(this.val$url);
                    XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
                    nsICertOverrideService.Release();
                }
                nsIX509Cert.Release();
            }
        });
        C.memmove(n4, new int[] { 1 }, 4);
        return 0;
    }
}
