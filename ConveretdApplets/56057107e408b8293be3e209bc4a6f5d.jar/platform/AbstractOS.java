// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import java.util.Hashtable;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FilenameFilter;
import java.util.Vector;
import anon.util.ClassUtil;
import gui.dialog.JAPDialog;
import anon.util.Util;
import java.net.URL;
import logging.LogHolder;
import logging.LogType;
import java.io.File;
import java.util.Properties;
import gui.help.JAPHelp;

public abstract class AbstractOS implements JAPHelp.IExternalURLCaller, JAPHelp.IExternalEMailCaller
{
    public static final String URL_MAIL_TO = "mailto:";
    private static Class[] REGISTERED_PLATFORM_CLASSES;
    private static final String[] BROWSERLIST;
    private static final String WHITESPACE_ENCODED = "%20";
    private static AbstractOS ms_operatingSystem;
    private IURLErrorNotifier m_notifier;
    private AbstractURLOpener m_URLOpener;
    private Properties m_envVars;
    private static File ms_tmpDir;
    static /* synthetic */ Class class$platform$LinuxOS;
    static /* synthetic */ Class class$platform$WindowsOS;
    static /* synthetic */ Class class$platform$MacOS;
    static /* synthetic */ Class class$platform$UnknownOS;
    
    public static final AbstractOS getInstance() {
        for (int n = 0; AbstractOS.ms_operatingSystem == null && n < AbstractOS.REGISTERED_PLATFORM_CLASSES.length; ++n) {
            try {
                AbstractOS.ms_operatingSystem = (AbstractOS)AbstractOS.REGISTERED_PLATFORM_CLASSES[n].newInstance();
            }
            catch (Exception ex) {
                LogHolder.log(7, LogType.MISC, "Cannot instantiate class " + AbstractOS.REGISTERED_PLATFORM_CLASSES[n] + ". Trying to instanciate another platform class.");
            }
            if (AbstractOS.ms_operatingSystem != null) {
                AbstractOS.ms_operatingSystem.m_notifier = new IURLErrorNotifier() {
                    public void checkNotify(final URL url) {
                    }
                };
            }
        }
        return AbstractOS.ms_operatingSystem;
    }
    
    public static String createBrowserCommand(String s) {
        s = Util.replaceAll(s, "/", File.separator);
        final StringBuffer sb = new StringBuffer("");
        int i = s.indexOf("%20", 0);
        int n = 0;
        while (i != -1) {
            sb.append(s.substring(n, i));
            sb.append(" ");
            n = i + "%20".length();
            i = s.indexOf("%20", i + 1);
        }
        sb.append(s.substring(n));
        s = toAbsolutePath(sb.toString());
        return s;
    }
    
    public static String toRelativePath(String s) {
        if (s == null) {
            return null;
        }
        String s2 = System.getProperty("user.dir");
        String string = "";
        if (s2.endsWith(File.separator)) {
            s2 = s2.substring(0, s2.lastIndexOf(File.separator));
        }
        while (s2.length() != 0 && s.indexOf(s2) != 0) {
            final int lastIndex = s2.lastIndexOf(File.separator);
            if (lastIndex >= 0) {
                s2 = s2.substring(0, lastIndex);
                string = string + ".." + File.separator;
            }
            else {
                s2 = "";
            }
        }
        s = s.substring(s2.length(), s.length());
        if (s.startsWith(File.separator)) {
            s = s.substring(s.indexOf(File.separator) + 1, s.length());
        }
        s = string + s;
        return s;
    }
    
    public static String toAbsolutePath(final String s) {
        if (s == null) {
            return null;
        }
        if ((File.separator.equals("\\") && !s.startsWith(File.separator) && s.length() >= 3 && !s.substring(1, 3).equals(":" + File.separator)) || (File.separator.equals("/") && !s.startsWith(File.separator))) {
            return System.getProperty("user.dir") + File.separator + s;
        }
        return s;
    }
    
    public void init(final IURLErrorNotifier notifier, final AbstractURLOpener urlOpener) {
        if (notifier != null) {
            this.m_notifier = notifier;
        }
        if (urlOpener != null) {
            this.m_URLOpener = urlOpener;
        }
    }
    
    public JAPDialog.ILinkedInformation createURLLink(final URL url, final String s) {
        return this.createURLLink(url, s, null);
    }
    
    public JAPDialog.ILinkedInformation createURLLink(final URL url, final String s, final String s2) {
        if (url == null) {
            return null;
        }
        return new JAPDialog.LinkedHelpContext(s2) {
            public int getType() {
                return 1;
            }
            
            public void clicked(final boolean b) {
                AbstractOS.this.openURL(url);
            }
            
            public String getMessage() {
                if (s == null || s.trim().length() == 0) {
                    return url.toString();
                }
                return s;
            }
        };
    }
    
    public final boolean openEMail(final String s) {
        if (s == null) {
            return false;
        }
        if (!s.startsWith("mailto:")) {
            return this.openLink("mailto:" + s);
        }
        return this.openLink(s);
    }
    
    public final String getDefaultBrowserPath() {
        if (this.m_URLOpener != null) {
            return this.m_URLOpener.getBrowserPath();
        }
        return null;
    }
    
    public final boolean isDefaultURLAvailable() {
        return this.m_URLOpener != null && this.m_URLOpener.getDefaultURL() != null && this.m_URLOpener.getBrowserCommand() != null;
    }
    
    public final boolean openBrowser() {
        return this.m_URLOpener != null && this.m_URLOpener.openBrowser();
    }
    
    public final boolean openBrowser(final String s) {
        return this.m_URLOpener != null && this.m_URLOpener.openBrowser(s);
    }
    
    public final boolean openURL(final URL url) {
        boolean b = false;
        if (url == null) {
            return false;
        }
        final String[] browserlist = AbstractOS.BROWSERLIST;
        final String asString = this.getAsString(url);
        this.m_notifier.checkNotify(url);
        if (this.m_URLOpener != null) {
            b = this.m_URLOpener.openURL(url);
        }
        if (!b) {
            b = this.openLink(asString);
        }
        if (!b) {
            int i = 0;
            while (i < browserlist.length) {
                try {
                    Runtime.getRuntime().exec(new String[] { browserlist[i], asString });
                    b = true;
                    break;
                }
                catch (SecurityException ex) {
                    LogHolder.log(3, LogType.MISC, ex);
                    break;
                }
                catch (Exception ex2) {
                    ++i;
                }
            }
        }
        if (!b) {
            LogHolder.log(3, LogType.MISC, "Cannot open URL in browser");
        }
        return b;
    }
    
    public abstract String getConfigPath(final String p0);
    
    protected abstract boolean openLink(final String p0);
    
    protected String getAsString(final URL url) {
        if (url == null) {
            return null;
        }
        return url.toString();
    }
    
    public boolean isHelpAutoInstalled() {
        return false;
    }
    
    public String getDefaultHelpPath(final String s) {
        final File classDirectory = ClassUtil.getClassDirectory(this.getClass());
        if (classDirectory != null) {
            return classDirectory.getParent();
        }
        return System.getProperty("user.dir");
    }
    
    public String getAppdataDefaultDirectory(final String s) {
        return null;
    }
    
    public Vector getActiveVMs() {
        final Vector<VMPerfDataFile> vector = new Vector<VMPerfDataFile>();
        if (!AbstractOS.ms_tmpDir.isDirectory()) {
            return vector;
        }
        final String[] list = AbstractOS.ms_tmpDir.list(new FilenameFilter() {
            public boolean accept(final File file, final String s) {
                return s.startsWith("hsperfdata_");
            }
        });
        if (list == null) {
            return vector;
        }
        for (int i = 0; i < list.length; ++i) {
            final File file = new File(AbstractOS.ms_tmpDir + File.separator + list[i]);
            if (file.isDirectory()) {
                final String[] list2 = file.list();
                if (list2 != null) {
                    for (int j = 0; j < list2.length; ++j) {
                        final File file2 = new File(file + File.separator + list2[j]);
                        if (file2.isFile() && file2.canRead()) {
                            try {
                                final int int1;
                                if ((int1 = Integer.parseInt(file2.getName())) != 0) {
                                    vector.addElement(new VMPerfDataFile(int1));
                                }
                            }
                            catch (NumberFormatException ex) {}
                        }
                    }
                }
            }
        }
        return vector;
    }
    
    public boolean copyAsRoot(final File file, final File file2, final AbstractRetryCopyProcess abstractRetryCopyProcess) {
        return false;
    }
    
    public String getTempPath() {
        return getDefaultTempPath();
    }
    
    public static String getDefaultTempPath() {
        String s = null;
        try {
            s = System.getProperty("java.io.tmpdir", null);
            if (s != null && !s.endsWith(File.separator)) {
                s += File.separator;
            }
        }
        catch (Throwable t) {
            LogHolder.log(3, LogType.MISC, t);
        }
        return s;
    }
    
    public String getProperty(final String s) {
        String property = null;
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        try {
            property = System.getProperty(s, null);
        }
        catch (Throwable t) {
            LogHolder.log(3, LogType.MISC, "Could not get system property " + s);
        }
        return property;
    }
    
    public String getenv(final String s) {
        String s2 = null;
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        try {
            s2 = System.getenv(s);
        }
        catch (SecurityException ex) {
            LogHolder.log(3, LogType.MISC, ex);
        }
        catch (Error error) {}
        if (s2 == null && this.m_envVars != null) {
            s2 = this.m_envVars.getProperty(s);
        }
        if (s2 == null) {
            try {
                s2 = System.getProperty(s);
            }
            catch (Throwable t) {
                LogHolder.log(3, LogType.MISC, t);
            }
        }
        return s2;
    }
    
    protected void initEnv(final String envCommand) {
        try {
            this.m_envVars = new Properties();
            final InitEnvRunner initEnvRunner = new InitEnvRunner();
            initEnvRunner.m_envCommand = envCommand;
            final Thread thread = new Thread(initEnvRunner);
            thread.setDaemon(true);
            thread.start();
            LogHolder.log(7, LogType.MISC, "initEnv -  killing the environment process starts sleeping");
            thread.join(5000L);
            LogHolder.log(7, LogType.MISC, "initEnv -  killing the environment process ends sleeping");
            if (initEnvRunner.envProcess != null) {
                initEnvRunner.envProcess.destroy();
            }
            LogHolder.log(7, LogType.MISC, "initEnv -  killing the environment process -- killed.");
            thread.interrupt();
            LogHolder.log(7, LogType.MISC, "initEnv -  killing the environment process ended.");
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.MISC, "initEnv - excpetion while killing the environment process");
            LogHolder.log(7, LogType.MISC, t);
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
        AbstractOS.REGISTERED_PLATFORM_CLASSES = new Class[] { (AbstractOS.class$platform$LinuxOS == null) ? (AbstractOS.class$platform$LinuxOS = class$("platform.LinuxOS")) : AbstractOS.class$platform$LinuxOS, (AbstractOS.class$platform$WindowsOS == null) ? (AbstractOS.class$platform$WindowsOS = class$("platform.WindowsOS")) : AbstractOS.class$platform$WindowsOS, (AbstractOS.class$platform$MacOS == null) ? (AbstractOS.class$platform$MacOS = class$("platform.MacOS")) : AbstractOS.class$platform$MacOS, (AbstractOS.class$platform$UnknownOS == null) ? (AbstractOS.class$platform$UnknownOS = class$("platform.UnknownOS")) : AbstractOS.class$platform$UnknownOS };
        BROWSERLIST = new String[] { "firefox", "iexplore", "explorer", "mozilla", "konqueror", "mozilla-firefox", "opera" };
        String s = System.getProperty("java.io.tmpdir");
        if (s != null) {
            if (s.compareTo("/var/tmp/") == 0) {
                s = "/tmp/";
            }
            if (s.lastIndexOf(File.pathSeparator) != s.length() - 1) {
                s += File.separator;
            }
        }
        else {
            s = "." + File.separator;
        }
        AbstractOS.ms_tmpDir = new File(s);
    }
    
    public abstract static class AbstractURLOpener
    {
        private Process m_portableFirefoxProcess;
        private boolean m_bOneSessionOnly;
        
        public AbstractURLOpener() {
            this.m_portableFirefoxProcess = null;
            this.m_bOneSessionOnly = false;
        }
        
        public final synchronized boolean openURL(final URL url) {
            return this.openURL(url, this.getBrowserCommand());
        }
        
        public synchronized boolean openURL(final URL url, final String s) {
            if (s == null || url == null) {
                return false;
            }
            if (this.m_portableFirefoxProcess != null && this.m_bOneSessionOnly) {
                try {
                    final int exitValue = this.m_portableFirefoxProcess.exitValue();
                    LogHolder.log(6, LogType.MISC, "previous portable firefox process exited " + ((exitValue == 0) ? "normally " : "anormally ") + "(exit value " + exitValue + ").");
                }
                catch (IllegalThreadStateException ex3) {
                    LogHolder.log(4, LogType.MISC, "Portable Firefox process is still running!");
                    return true;
                }
            }
            final String[] array = { s, url.toString() };
            try {
                this.m_portableFirefoxProcess = Runtime.getRuntime().exec(array);
                return true;
            }
            catch (SecurityException ex) {
                LogHolder.log(4, LogType.MISC, "You are not allowed to launch portable firefox: ", ex);
            }
            catch (IOException ex2) {
                LogHolder.log(4, LogType.MISC, "Error occured while launching portable browser with command '" + array[0] + " " + array[1] + "'", ex2);
            }
            return false;
        }
        
        public abstract String getBrowserCommand();
        
        public abstract String getBrowserPath();
        
        public abstract URL getDefaultURL();
        
        public final synchronized boolean openBrowser() {
            return this.openBrowser(this.getBrowserCommand());
        }
        
        public final synchronized boolean openBrowser(final String s) {
            this.m_bOneSessionOnly = true;
            final boolean openURL = this.openURL(this.getDefaultURL(), s);
            this.m_bOneSessionOnly = false;
            return openURL;
        }
    }
    
    public abstract static class AbstractRetryCopyProcess
    {
        private int m_maxSteps;
        private int m_currentStep;
        
        public AbstractRetryCopyProcess(final int maxSteps) {
            if (maxSteps <= 0) {
                throw new IllegalArgumentException("Max steps <=0! Value: " + maxSteps);
            }
            this.m_maxSteps = maxSteps;
            this.m_currentStep = 0;
        }
        
        public abstract boolean checkRetry();
        
        public final int getMaxProgressSteps() {
            return this.m_maxSteps;
        }
        
        public final long getProgressLoopWaitMilliseconds() {
            return 500L;
        }
        
        public final int getCurrentStep() {
            return this.m_currentStep;
        }
        
        public void reset() {
            this.m_currentStep = 0;
        }
        
        public boolean incrementProgress() {
            if (this.m_currentStep < this.m_maxSteps) {
                ++this.m_currentStep;
                return true;
            }
            return false;
        }
    }
    
    private class InitEnvRunner implements Runnable
    {
        public Process envProcess;
        public BufferedReader br;
        public InputStream inProcess;
        public String m_envCommand;
        
        public void run() {
            try {
                this.envProcess = Runtime.getRuntime().exec(this.m_envCommand);
                this.inProcess = this.envProcess.getInputStream();
                this.br = new BufferedReader(new InputStreamReader(this.inProcess));
                String line;
                while ((line = this.br.readLine()) != null) {
                    LogHolder.log(7, LogType.MISC, "initEnv - read evironment line: " + line);
                    final int index = line.indexOf(61);
                    ((Hashtable<String, String>)AbstractOS.this.m_envVars).put(line.substring(0, index), line.substring(index + 1));
                }
                LogHolder.log(7, LogType.MISC, "initEnv - read evironment lines finished.");
            }
            catch (IOException ex) {
                LogHolder.log(2, LogType.MISC, "Could not parse environment variables.", ex);
            }
            catch (SecurityException ex2) {
                LogHolder.log(3, LogType.MISC, "Could not parse environment variables.", ex2);
            }
            catch (Throwable t) {
                LogHolder.log(7, LogType.MISC, "initEnv - excpetion");
                LogHolder.log(7, LogType.MISC, t);
            }
        }
    }
    
    public interface IURLErrorNotifier
    {
        void checkNotify(final URL p0);
    }
}
