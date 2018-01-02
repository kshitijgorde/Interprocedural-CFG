import java.util.zip.ZipFile;
import java.util.Vector;
import jap.MacOSXLib;
import platform.MacOS;
import jap.JAPViewIconified;
import jap.ISplashResponse;
import jap.AbstractJAPMainView;
import anon.AnonServiceEventListener;
import jap.JAPObserver;
import jap.ConsoleJAPMainView;
import jap.JAPNewView;
import gui.JAPDll;
import jap.IJAPMainView;
import java.net.URL;
import anon.infoservice.MixCascade;
import java.util.StringTokenizer;
import anon.infoservice.ListenerInterface;
import javax.swing.UIManager;
import anon.client.crypto.KeyPool;
import java.security.SecureRandom;
import java.io.IOException;
import jap.JAPDebug;
import javax.swing.JLabel;
import java.awt.Window;
import jap.JAPSplash;
import jap.ConsoleSplash;
import java.util.Locale;
import java.io.FileNotFoundException;
import java.io.File;
import anon.util.ClassUtil;
import gui.dialog.JAPDialog;
import java.awt.Component;
import platform.WindowsOS;
import platform.AbstractOS;
import gui.JAPAWTMsgBox;
import java.awt.Frame;
import gui.GUIUtils;
import anon.util.JAPMessages;
import logging.LogType;
import logging.Log;
import logging.LogHolder;
import logging.SystemErrLog;
import jap.JAPModel;
import java.util.Hashtable;
import jap.JAPController;

// 
// Decompiled by Procyon v0.5.30
// 

public class JAP
{
    private static final String MSG_ERROR_NEED_NEWER_JAVA = "errorNeedNewerJava";
    private static final String MSG_ERROR_JONDO_ALREADY_RUNNING = "errorAlreadyRunning";
    private static final String MSG_ERROR_JONDO_ALREADY_RUNNING_WIN = "errorAlreadyRunningWin";
    private static final String MSG_GNU_NOT_COMPATIBLE;
    private static final String MSG_LOADING_INTERNATIONALISATION;
    private static final String MSG_LOADING_SETTINGS;
    private static final String MSG_STARTING_CONTROLLER;
    private static final String MSG_INIT_DLL;
    private static final String MSG_INIT_VIEW;
    private static final String MSG_INIT_ICON_VIEW;
    private static final String MSG_INIT_RANDOM;
    private static final String MSG_FINISH_RANDOM;
    private static final String MSG_START_LISTENER;
    private static final String MSG_EXPLAIN_NO_FIREFOX_FOUND;
    private static final String MSG_USE_DEFAULT_BROWSER;
    private static final String MSG_CONFIGURE_BROWSER;
    private static final String MSG_UNINSTALLING;
    private static final String OPTION_CONTEXT = "--context";
    private JAPController m_controller;
    Hashtable m_arstrCmdnLnArgs;
    String[] m_temp;
    static /* synthetic */ Class class$JAP;
    
    public JAP() {
        this.m_arstrCmdnLnArgs = null;
        this.m_temp = null;
    }
    
    JAP(final String[] temp) {
        this.m_arstrCmdnLnArgs = null;
        this.m_temp = null;
        this.m_temp = temp;
        if (temp != null) {
            if (temp.length > 0) {
                this.m_arstrCmdnLnArgs = new Hashtable(temp.length);
            }
            else {
                this.m_arstrCmdnLnArgs = new Hashtable();
            }
            for (int i = 0; i < temp.length; ++i) {
                if (i + 1 < temp.length && !temp[i + 1].startsWith("-")) {
                    this.m_arstrCmdnLnArgs.put(temp[i], temp[i + 1]);
                }
                else {
                    this.m_arstrCmdnLnArgs.put(temp[i], "");
                }
            }
        }
        else {
            this.m_arstrCmdnLnArgs = new Hashtable();
        }
    }
    
    public void startJAP() {
        final String property = System.getProperty("java.version", "");
        final String property2 = System.getProperty("java.vendor", "");
        final String property3 = System.getProperty("os.name", "");
        final String property4 = System.getProperty("mrj.version");
        boolean b = false;
        boolean b2 = false;
        String s = null;
        int port = 0;
        MixCascade currentMixCascade = null;
        JAPModel.getInstance().setProgramName(this.getArgumentValue("--programName"));
        final String programName = JAPModel.getInstance().getProgramName();
        final String string = programName + " must run with a 1.1.3 or higher version Java!\nYou will find more information at the " + programName + " webpage!\nYour Java Version: ";
        if (this.isArgumentSet("--version") || this.isArgumentSet("-v")) {
            System.out.println(programName + " version: " + "00.12.005");
            System.out.println("Java Vendor: " + property2);
            System.out.println("Java Version: " + property);
            System.out.println("OS Version: " + property3);
            System.exit(0);
        }
        System.out.println("Starting up " + programName + " version " + "00.12.005" + ". (" + property + "/" + property2 + "/" + property3 + ((property4 != null) ? ("/" + property4) : "") + ")");
        final SystemErrLog logInstance = new SystemErrLog();
        LogHolder.setLogInstance(logInstance);
        logInstance.setLogType(LogType.ALL);
        logInstance.setLogLevel(4);
        LogHolder.log(7, LogType.MISC, "Pre configuration debug output enabled.");
        System.getProperties().remove("socksProxyHost");
        System.getProperties().remove("socksProxyPort");
        try {
            final Class<?> forName = Class.forName("java.net.ProxySelector");
            forName.getMethod("setDefault", forName).invoke(forName, null);
        }
        catch (Exception ex) {
            LogHolder.log(4, LogType.NET, "Could not reset ProxySelector!", ex);
        }
        if (property.compareTo("1.0.2") <= 0) {
            System.out.println(string + property);
            System.exit(0);
        }
        if (this.isArgumentSet("--extractHelp")) {
            if (this.getArgumentValue("--extractHelp") == null) {
                this.setArgument("--extractHelp", ".");
            }
            if (JAPModel.getInstance().extractHelpFiles(this.getArgumentValue("--extractHelp"))) {
                System.out.println("Help files were extracted to the directory '" + this.getArgumentValue("--extractHelp") + "'.");
            }
            else {
                System.out.println("Error: Help files could not be extracted to the directory '" + this.getArgumentValue("--extractHelp") + "'!");
            }
            System.exit(0);
        }
        if (this.isArgumentSet("--help") || this.isArgumentSet("-h")) {
            System.out.println("Usage:");
            System.out.println("--help, -h:                  Show this text.");
            System.out.println("--console:                   Start " + programName + " in console-only mode.");
            System.out.println("--allow-multiple, -a         Allow " + programName + " to start multiple instances.");
            System.out.println("--minimized, -m:             Minimize " + programName + " on startup.");
            System.out.println("--version, -v:               Print version information.");
            System.out.println("--showDialogFormat           Show and set dialog format options.");
            System.out.println("--noSplash, -s               Suppress splash screen on startup.");
            System.out.println("--programName {JAP|JonDo}    Show this program in different distributor modes.");
            System.out.println("--presenation, -p            Presentation mode (slight GUI changes).");
            System.out.println("--forwarder, -f {port}       Act as a forwarder on a specified port.");
            System.out.println("--listen, -l {[host][:port]} Listen on the specified interface.");
            System.out.println("--uninstall, -u              Delete all configuration and help files.");
            System.out.println("--cascade {[host][:port][:id]} Connects to the specified Mix-Cascade.");
            System.out.println("--portable [path_to_browser] Tell " + programName + " that it runs in a portable environment.");
            System.out.println("--portable-jre               Tell " + programName + " that it runs with a portable JRE.");
            System.out.println("--help-path                  Path where external html help files should be installed.");
            System.out.println("--extractHelp [directory]    Extract the internal help files to a directory.");
            System.out.println("--config, -c {Filename}:     Force " + programName + " to use a specific configuration file.");
            System.out.println("--context {Context}:         Start " + programName + " with a specific service provider context.");
            System.exit(0);
        }
        if (this.isArgumentSet("-console") || this.isArgumentSet("--console")) {
            b = true;
        }
        if (this.isArgumentSet("--uninstall") || this.isArgumentSet("-u")) {
            b2 = true;
        }
        if (property2.startsWith("Transvirtual")) {
            if (property.compareTo("1.3") <= 0) {
                if (!JAPMessages.init("JAPMessages")) {
                    GUIUtils.exitWithNoMessagesError("MixConfigMessages");
                }
                if (b) {
                    System.out.println(JAPMessages.getString("errorNeedNewerJava"));
                }
                else {
                    JAPAWTMsgBox.MsgBox(new Frame(), JAPMessages.getString("errorNeedNewerJava"), JAPMessages.getString("error"));
                }
                System.exit(0);
            }
        }
        else if (property2.toUpperCase().indexOf("FREE SOFTWARE FOUNDATION") >= 0) {
            JAPMessages.init("JAPMessages");
            System.out.println("\n" + JAPMessages.getString(JAP.MSG_GNU_NOT_COMPATIBLE) + "\n");
        }
        else {
            if (property.compareTo("1.0.2") <= 0) {
                System.out.println(string + property);
                System.exit(0);
            }
            if (property.compareTo("1.1.2") <= 0) {
                JAPMessages.init("JAPMessages");
                if (b) {
                    System.out.println(JAPMessages.getString("errorNeedNewerJava"));
                }
                else {
                    JAPAWTMsgBox.MsgBox(new Frame(), JAPMessages.getString("errorNeedNewerJava"), JAPMessages.getString("error"));
                }
                System.exit(0);
            }
        }
        if (!this.isArgumentSet("--allow-multiple") && !this.isArgumentSet("-a")) {
            LogHolder.log(7, LogType.MISC, "Allow multiple instances not set - try to detect running instances of JAP");
            final Vector activeVMs = AbstractOS.getInstance().getActiveVMs();
            int n = 0;
            for (int i = 0; i < activeVMs.size(); ++i) {
                final Object element = activeVMs.elementAt(i);
                if (element != null) {
                    if (element.toString() != null) {
                        if (element.toString().equals("JAP") || element.toString().equals("JAP.jar") || element.toString().equals("JAPMacintosh")) {
                            ++n;
                        }
                        if (n > 1) {
                            JAPMessages.init("JAPMessages");
                            final String string2 = JAPMessages.getString("errorAlreadyRunning") + ((AbstractOS.getInstance() instanceof WindowsOS) ? ("\n" + JAPMessages.getString("errorAlreadyRunningWin")) : "");
                            if (b) {
                                System.out.println(string2);
                            }
                            else {
                                JAPDialog.showErrorDialog((Component)null, string2, LogType.MISC, new JAPDialog.LinkedInformationAdapter() {
                                    public boolean isOnTop() {
                                        return true;
                                    }
                                });
                                System.exit(0);
                            }
                        }
                    }
                }
            }
            LogHolder.log(7, LogType.MISC, "Detection finished.");
        }
        this.m_controller = JAPController.getInstance();
        final boolean argumentSet = this.isArgumentSet("--portable");
        this.m_controller.setPortableMode(argumentSet);
        final String argumentValue = this.getArgumentValue("--context");
        if (argumentValue != null) {
            JAPModel.getInstance().setContext(argumentValue);
        }
        boolean b3 = false;
        String s2;
        if ((s2 = this.getArgumentValue("--config")) == null) {
            s2 = this.getArgumentValue("-c");
        }
        if (s2 == null && argumentSet && ClassUtil.getClassDirectory((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP) != null) {
            s2 = ClassUtil.getClassDirectory((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getParent() + File.separator + "jap.conf";
            b3 = true;
        }
        if (s2 != null) {
            LogHolder.log(5, LogType.MISC, "Loading config file '" + s2 + "'.");
        }
        String string3 = null;
        try {
            this.m_controller.preLoadConfigFile(s2);
        }
        catch (FileNotFoundException ex2) {
            LogHolder.log(1, LogType.MISC, ex2);
            if (!b3) {
                string3 = "File not found: " + ex2.getMessage();
            }
        }
        final Locale default1 = Locale.getDefault();
        String s3;
        if (default1.getLanguage().equals("de")) {
            s3 = "Lade Internationalisierung";
        }
        else if (default1.getLanguage().equals("fr")) {
            s3 = "Chargement des param\u00e8tres d'internationalisation";
        }
        else if (default1.getLanguage().equals("cs")) {
            s3 = "Nahr\u00e1v\u00e1m internacionalizaci";
        }
        else {
            s3 = "Loading internationalisation";
        }
        ISplashResponse splashResponse;
        if (b) {
            JAPDialog.setConsoleOnly(true);
            splashResponse = new ConsoleSplash();
            splashResponse.setText(s3);
        }
        else if (this.isArgumentSet("--noSplash") || this.isArgumentSet("-s") || !JAPModel.getInstance().getShowSplashScreen()) {
            splashResponse = new ConsoleSplash();
            splashResponse.setText(s3);
        }
        else {
            splashResponse = new JAPSplash(new Frame(), s3);
            ((JAPSplash)splashResponse).centerOnScreen();
            ((JAPSplash)splashResponse).setVisible(true);
            GUIUtils.setAlwaysOnTop((Window)splashResponse, true);
        }
        if (string3 != null) {
            splashResponse.setText(string3);
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException ex6) {}
            System.exit(-1);
        }
        if (!JAPMessages.isInitialised()) {
            JAPMessages.init("JAPMessages");
        }
        if (!b && !b2) {
            JAPModel.getInstance().setDialogFormatShown(this.isArgumentSet("--showDialogFormat"));
            GUIUtils.setIconResizer(JAPModel.getInstance().getIconResizer());
            try {
                final JLabel label = new JLabel();
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                JAPAWTMsgBox.MsgBox(new Frame(), JAPMessages.getString("errorSwingNotInstalled"), JAPMessages.getString("error"));
                System.exit(0);
            }
        }
        LogHolder.setLogInstance(JAPDebug.getInstance());
        JAPDebug.getInstance().setLogType(LogType.ALL);
        JAPDebug.getInstance().setLogLevel(4);
        if (b2) {
            int n2 = 0;
            splashResponse.setText(JAPMessages.getString(JAP.MSG_UNINSTALLING));
            try {
                this.m_controller.uninstall(s2);
            }
            catch (IOException ex3) {
                LogHolder.log(1, LogType.MISC, ex3);
                n2 = -1;
            }
            if (splashResponse instanceof JAPSplash) {
                ((JAPSplash)splashResponse).setVisible(false);
            }
            System.exit(n2);
        }
        splashResponse.setText(JAPMessages.getString(JAP.MSG_INIT_RANDOM));
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                new SecureRandom().nextInt();
                KeyPool.start();
            }
        });
        thread.setPriority(1);
        thread.start();
        try {
            thread.join();
        }
        catch (InterruptedException ex4) {
            LogHolder.log(5, LogType.CRYPTO, ex4);
        }
        if (!b && !property3.regionMatches(true, 0, "mac", 0, 3)) {
            LogHolder.log(7, LogType.GUI, "Setting Cross Platform Look-And-Feel!");
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            catch (Exception ex7) {
                LogHolder.log(2, LogType.GUI, "Exception while setting Cross Platform Look-And-Feel!");
            }
        }
        if (this.isArgumentSet("--listen") || this.isArgumentSet("-l")) {
            if ((s = this.getArgumentValue("--listen")) == null) {
                s = this.getArgumentValue("-l");
            }
            if (s != null) {
                try {
                    final ListenerInterface listenerInterface = new ListenerInterface(s);
                    s = listenerInterface.getHost();
                    port = listenerInterface.getPort();
                }
                catch (Throwable t) {}
            }
        }
        if (this.isArgumentSet("--cascade")) {
            final String argumentValue2 = this.getArgumentValue("--cascade");
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(argumentValue2, ":");
                String nextToken = null;
                String nextToken2 = null;
                int int1 = 6544;
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    int1 = Integer.parseInt(stringTokenizer.nextToken());
                }
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken2 = stringTokenizer.nextToken();
                }
                currentMixCascade = new MixCascade("Commandline Cascade", nextToken2, nextToken, int1);
            }
            catch (Throwable t2) {}
        }
        splashResponse.setText(JAPMessages.getString(JAP.MSG_STARTING_CONTROLLER));
        this.m_controller.start();
        if (this.isArgumentSet("--presentation") || this.isArgumentSet("-p")) {
            this.m_controller.setPresentationMode(true);
        }
        String[] temp = this.m_temp;
        if (this.m_temp == null || (!this.isArgumentSet("--allow-multiple") && !this.isArgumentSet("-a"))) {
            if (this.m_temp == null) {
                temp = new String[] { null };
            }
            else {
                temp = new String[this.m_temp.length + 1];
                System.arraycopy(this.m_temp, 0, temp, 0, this.m_temp.length);
            }
            temp[temp.length - 1] = "-a";
        }
        this.m_controller.initCommandLineArgs(temp);
        if (this.isArgumentSet("--portable-jre")) {
            this.m_controller.setPortableJava(true);
        }
        boolean forwardingStateModuleVisible = false;
        if (this.isArgumentSet("-forwarding_state")) {
            forwardingStateModuleVisible = true;
        }
        AbstractOS.getInstance().init(new AbstractOS.IURLErrorNotifier() {
            public void checkNotify(final URL url) {
            }
        }, new AbstractOS.AbstractURLOpener() {
            private final /* synthetic */ String val$BROWSER_CMD = JAP.this.buildPortableFFCommand(splashResponse);
            
            public boolean openURL(final URL url, final String s) {
                if (url == null || !JAP.this.m_controller.isPortableMode()) {
                    return false;
                }
                if (!super.openURL(url, s)) {
                    if (s != null && (this.getBrowserCommand() == null || !s.equals(this.getBrowserCommand()))) {
                        return false;
                    }
                    final int showConfirmDialog = JAPDialog.showConfirmDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAP.MSG_EXPLAIN_NO_FIREFOX_FOUND), new JAPDialog.Options(1) {
                        public String getYesOKText() {
                            return JAPMessages.getString(JAP.MSG_USE_DEFAULT_BROWSER);
                        }
                        
                        public String getNoText() {
                            return JAPMessages.getString(JAP.MSG_CONFIGURE_BROWSER);
                        }
                    }, 2, new JAPDialog.LinkedInformationAdapter() {
                        public boolean isApplicationModalityForced() {
                            return true;
                        }
                        
                        public boolean isOnTop() {
                            return true;
                        }
                    });
                    if (showConfirmDialog == 0) {
                        return false;
                    }
                    if (showConfirmDialog == 1) {
                        JAPController.getInstance().showConfigDialog("UI_TAB", this);
                    }
                }
                return true;
            }
            
            public URL getDefaultURL() {
                return JAPModel.getInstance().getHelpURL();
            }
            
            public String getBrowserPath() {
                return JAP.this.getArgumentValue("--portable");
            }
            
            public String getBrowserCommand() {
                return this.val$BROWSER_CMD;
            }
        });
        JAPModel.getInstance().setForwardingStateModuleVisible(forwardingStateModuleVisible);
        splashResponse.setText(JAPMessages.getString(JAP.MSG_LOADING_SETTINGS));
        this.m_controller.loadConfigFile(s2, splashResponse);
        String s4;
        if ((s4 = this.getArgumentValue("--forwarder")) == null) {
            s4 = this.getArgumentValue("-f");
        }
        if (s4 != null) {
            try {
                JAPModel.getInstance().getRoutingSettings().setServerPort(Integer.parseInt(s4));
            }
            catch (NumberFormatException ex5) {
                LogHolder.log(2, LogType.MISC, ex5);
            }
        }
        splashResponse.setText(JAPMessages.getString(JAP.MSG_INIT_DLL));
        this.m_controller.setView(null, splashResponse);
        Window window = null;
        if (splashResponse instanceof Window) {
            window = (JAPSplash)splashResponse;
        }
        JAPDll.init(this.isArgumentSet("--dllAdminUpdate"), this.getArgumentValue("--dllAdminUpdate"), window);
        LogHolder.log(6, LogType.MISC, "Welcome! This is version 00.12.005 of JAP.");
        LogHolder.log(6, LogType.MISC, "Java " + property + " running on " + property3 + ".");
        if (property4 != null) {
            LogHolder.log(6, LogType.MISC, "MRJ Version is " + property4 + ".");
        }
        splashResponse.setText(JAPMessages.getString(JAP.MSG_INIT_VIEW));
        IJAPMainView ijapMainView;
        if (!b) {
            ijapMainView = new JAPNewView(JAPModel.getInstance().getProgramName(), this.m_controller);
            ijapMainView.create(true);
        }
        else {
            ijapMainView = new ConsoleJAPMainView();
        }
        this.m_controller.addJAPObserver(ijapMainView);
        this.m_controller.addEventListener(ijapMainView);
        if (splashResponse instanceof JAPSplash) {
            this.m_controller.setView(ijapMainView, new JAPSplash((Frame)ijapMainView, JAPMessages.getString(JAPController.MSG_FINISHING)));
        }
        else {
            this.m_controller.setView(ijapMainView, new ConsoleSplash());
        }
        if (!b) {
            splashResponse.setText(JAPMessages.getString(JAP.MSG_INIT_ICON_VIEW));
            ijapMainView.registerViewIconified(new JAPViewIconified((AbstractJAPMainView)ijapMainView));
        }
        if (this.isArgumentSet("--forwarder") || this.isArgumentSet("-f")) {
            this.m_controller.enableForwardingServer(true);
        }
        boolean moveToSystrayOnStartup = JAPModel.getMoveToSystrayOnStartup();
        if (this.isArgumentSet("-minimized") || this.isArgumentSet("--minimized") || this.isArgumentSet("-m")) {
            moveToSystrayOnStartup = true;
        }
        splashResponse.setText(JAPMessages.getString(JAP.MSG_START_LISTENER));
        if (!this.m_controller.startHTTPListener(s, port)) {
            ijapMainView.disableSetAnonMode();
        }
        if (!b) {
            final AbstractJAPMainView abstractJAPMainView = (AbstractJAPMainView)ijapMainView;
            if (moveToSystrayOnStartup) {
                final String dllVersion = JAPDll.getDllVersion();
                boolean b4 = false;
                if (dllVersion == null || dllVersion.compareTo("00.02.00") < 0) {
                    abstractJAPMainView.setVisible(true);
                    abstractJAPMainView.toFront();
                    b4 = true;
                }
                if (!abstractJAPMainView.hideWindowInTaskbar() && !b4) {
                    abstractJAPMainView.setVisible(true);
                    abstractJAPMainView.toFront();
                }
            }
            else if (JAPModel.getMinimizeOnStartup()) {
                abstractJAPMainView.setVisible(true);
                abstractJAPMainView.showIconifiedView();
            }
            else {
                GUIUtils.setAlwaysOnTop(abstractJAPMainView, true);
                abstractJAPMainView.setVisible(true);
                abstractJAPMainView.toFront();
                GUIUtils.setAlwaysOnTop(abstractJAPMainView, false);
            }
            if (splashResponse instanceof JAPSplash) {
                ((JAPSplash)splashResponse).dispose();
            }
        }
        JAPDll.checkDllVersion(true);
        if (currentMixCascade != null) {
            try {
                this.m_controller.setCurrentMixCascade(currentMixCascade);
            }
            catch (Throwable t3) {
                LogHolder.log(2, LogType.MISC, "Could not set Cascade specified on the Command line! Ignoring information given and continue...");
            }
        }
        this.m_controller.initialRun(s, port);
        if (b) {
            ijapMainView.setVisible(true);
        }
        if (AbstractOS.getInstance() instanceof MacOS) {
            MacOSXLib.init();
        }
    }
    
    private String getArgumentValue(final String s) {
        String s2 = this.m_arstrCmdnLnArgs.get(s);
        if (s2 != null && s2.trim().length() == 0) {
            s2 = null;
        }
        return s2;
    }
    
    private void setArgument(final String s, final String s2) {
        this.m_arstrCmdnLnArgs.put(s, s2);
    }
    
    private boolean isArgumentSet(final String s) {
        return this.m_arstrCmdnLnArgs.containsKey(s);
    }
    
    private String buildPortableFFCommand(final ISplashResponse splashResponse) {
        String portableBrowserpath;
        if (this.isArgumentSet("--portable")) {
            portableBrowserpath = JAPModel.getInstance().getPortableBrowserpath();
            if (portableBrowserpath == null) {
                portableBrowserpath = this.getArgumentValue("--portable");
                JAPModel.getInstance().setPortableBrowserpath(portableBrowserpath);
            }
            if (portableBrowserpath != null) {
                portableBrowserpath = AbstractOS.createBrowserCommand(portableBrowserpath);
            }
        }
        else {
            portableBrowserpath = null;
        }
        String s;
        if (this.isArgumentSet("--help-path")) {
            s = this.getArgumentValue("--help-path");
        }
        else if (this.isArgumentSet("--portable-help-path")) {
            s = this.getArgumentValue("--portable-help-path");
        }
        else {
            s = null;
        }
        if (this.isArgumentSet("--portable")) {
            if (s == null && this.isArgumentSet("--jar-path")) {
                String s2 = this.getArgumentValue("--jar-path");
                final String string = ".." + File.separator;
                try {
                    if (this.m_temp != null && this.m_temp.length > 0 && s2 != null) {
                        while (s2.startsWith(string)) {
                            final String substring = s2.substring(string.length(), s2.length());
                            final int index = substring.indexOf(File.separator);
                            if (index >= 0 && substring.length() >= index + 1) {
                                s2 = substring.substring(substring.indexOf(File.separator) + File.separator.length(), substring.length());
                            }
                            else {
                                s2 = "";
                            }
                        }
                        if (s2.trim().length() > 0) {
                            final String s3 = this.m_temp[0];
                            final int index2 = s3.indexOf(s2);
                            if (index2 > 0) {
                                final String substring2 = s3.substring(0, index2);
                                final String[] list = new File(substring2).list();
                                for (int i = 0; i < list.length; ++i) {
                                    if (list[i].toUpperCase().equals("help".toUpperCase())) {
                                        s = substring2;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.MISC, ex);
                }
            }
            if (s == null) {
                final ZipFile jarFile = ClassUtil.getJarFile();
                if (jarFile != null) {
                    s = new File(jarFile.getName()).getParent();
                }
                else if (!JAPModel.getInstance().isHelpPathChangeable()) {
                    s = ClassUtil.getClassDirectory(this.getClass()).getParent();
                }
            }
        }
        if (s != null) {
            final String text = splashResponse.getText();
            splashResponse.setText(JAPMessages.getString(JAPController.MSG_UPDATING_HELP));
            JAPModel.getInstance().setHelpPath(new File(s), true);
            splashResponse.setText(text);
        }
        return portableBrowserpath;
    }
    
    public static void main(final String[] array) {
        try {
            new JAP(array).startJAP();
        }
        catch (Throwable t) {
            System.out.println("A severe problem was encountered on startup!");
            t.printStackTrace();
            System.exit(-1);
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
        MSG_GNU_NOT_COMPATIBLE = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_gnuNotCompatible";
        MSG_LOADING_INTERNATIONALISATION = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_loadingInternationalisation";
        MSG_LOADING_SETTINGS = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_loadingSettings";
        MSG_STARTING_CONTROLLER = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_startingController";
        MSG_INIT_DLL = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_initLibrary";
        MSG_INIT_VIEW = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_initView";
        MSG_INIT_ICON_VIEW = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_initIconView";
        MSG_INIT_RANDOM = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_initRandom";
        MSG_FINISH_RANDOM = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_finishRandom";
        MSG_START_LISTENER = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_startListener";
        MSG_EXPLAIN_NO_FIREFOX_FOUND = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_explainNoFirefoxFound";
        MSG_USE_DEFAULT_BROWSER = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_useDefaultBrowser";
        MSG_CONFIGURE_BROWSER = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_configureBrowser";
        MSG_UNINSTALLING = ((JAP.class$JAP == null) ? (JAP.class$JAP = class$("JAP")) : JAP.class$JAP).getName() + "_uninstalling";
    }
}
