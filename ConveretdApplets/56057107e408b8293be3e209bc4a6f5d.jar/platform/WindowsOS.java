// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import java.lang.reflect.Method;
import anon.util.RecursiveFileTool;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.net.URL;
import logging.LogHolder;
import logging.LogType;

public class WindowsOS extends AbstractOS
{
    public static final int HKEY_CLASSES_ROOT = Integer.MIN_VALUE;
    public static final int HKEY_CURRENT_USER = -2147483647;
    public static final int HKEY_LOCAL_MACHINE = -2147483646;
    public static final int DELETE = 65536;
    public static final int KEY_QUERY_VALUE = 1;
    public static final int KEY_SET_VALUE = 2;
    public static final int KEY_CREATE_SUB_KEY = 4;
    public static final int KEY_ENUMERATE_SUB_KEYS = 8;
    public static final int KEY_READ = 131097;
    public static final int KEY_WRITE = 131078;
    public static final int KEY_ALL_ACCESS = 983103;
    public static final int ERROR_SUCCESS = 0;
    public static final int ERROR_FILE_NOT_FOUND = 2;
    public static final int ERROR_ACCESS_DENIED = 5;
    static /* synthetic */ Class class$java$io$File;
    
    public WindowsOS() throws Exception {
        final String lowerCase = System.getProperty("os.name", "").toLowerCase();
        if (lowerCase.indexOf("win") == -1) {
            throw new Exception("Operating system is not Windows");
        }
        if (lowerCase.indexOf("windows 9") > -1) {
            this.initEnv("command.com /c set");
        }
        else {
            this.initEnv("cmd.exe /c set");
        }
        LogHolder.log(7, LogType.MISC, "platform.WindowsOS instantiated.");
    }
    
    protected boolean openLink(final String s) {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + s);
            return true;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Cannot open '" + s + "' in Windows default program.", ex);
            return false;
        }
    }
    
    protected String getAsString(final URL url) {
        final String asString = super.getAsString(url);
        if (new StringTokenizer(asString).countTokens() > 1) {
            return "\"" + asString + "\"";
        }
        return asString;
    }
    
    public boolean isHelpAutoInstalled() {
        return true;
    }
    
    public String getDefaultHelpPath(final String s) {
        String s2 = this.getAppdataDefaultDirectory(s);
        if (s2 == null) {
            s2 = super.getDefaultHelpPath(s);
        }
        return s2;
    }
    
    public String getConfigPath(final String s) {
        final String property = System.getProperty("java.vendor", "unknown");
        String s2 = "";
        if (property.trim().toLowerCase().startsWith("microsoft")) {
            try {
                String line;
                while ((line = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("CMD /C SET").getInputStream())).readLine()) != null && !line.startsWith("USERPROFILE")) {}
                if (line != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, "=");
                    stringTokenizer.nextToken();
                    s2 = stringTokenizer.nextToken().trim();
                }
            }
            catch (Exception ex) {}
            if (s2 == null) {
                s2 = System.getProperty("user.dir");
            }
        }
        else {
            s2 = this.getAppdataDefaultDirectory(s);
            if (s2 == null) {
                s2 = System.getProperty("user.home");
            }
        }
        return s2 + File.separator;
    }
    
    public String getAppdataDefaultDirectory(final String s) {
        return this.getEnvPath(s, "APPDATA");
    }
    
    public boolean copyAsRoot(final File file, final File file2, final AbstractRetryCopyProcess abstractRetryCopyProcess) {
        try {
            final Method method = Class.forName("gui.JAPDll").getMethod("xcopy", (WindowsOS.class$java$io$File == null) ? (WindowsOS.class$java$io$File = class$("java.io.File")) : WindowsOS.class$java$io$File, (WindowsOS.class$java$io$File == null) ? (WindowsOS.class$java$io$File = class$("java.io.File")) : WindowsOS.class$java$io$File, Boolean.TYPE);
            final Object[] array = { file, file2, Boolean.TRUE };
            final File file3 = new File(file2.getPath() + File.separator + file.getName());
            final long fileSize = RecursiveFileTool.getFileSize(file);
            final long length = file3.length();
            byte[] md5Digest;
            try {
                md5Digest = RecursiveFileTool.createMD5Digest(file3);
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.MISC, ex);
                md5Digest = null;
            }
            final boolean booleanValue = (boolean)method.invoke(null, array);
            boolean b = false;
            if (!booleanValue) {
                if (abstractRetryCopyProcess != null && abstractRetryCopyProcess.checkRetry()) {
                    return this.copyAsRoot(file, file2, abstractRetryCopyProcess);
                }
                LogHolder.log(3, LogType.MISC, "Root copy failed!");
                return false;
            }
            else {
                if (abstractRetryCopyProcess == null || abstractRetryCopyProcess.getMaxProgressSteps() <= 0) {
                    return true;
                }
                long fileSize2;
                long n = fileSize2 = -1 * abstractRetryCopyProcess.getMaxProgressSteps();
                while (abstractRetryCopyProcess.incrementProgress()) {
                    try {
                        if (fileSize2 > 0L || !RecursiveFileTool.equals(file3, md5Digest, length)) {
                            fileSize2 = RecursiveFileTool.getFileSize(file3);
                        }
                        else {
                            ++fileSize2;
                        }
                        if (fileSize2 == fileSize) {
                            if (RecursiveFileTool.equals(file3, file, true)) {
                                while (abstractRetryCopyProcess.incrementProgress()) {}
                                return true;
                            }
                            LogHolder.log(3, LogType.MISC, "Root copy failed!");
                            b = true;
                            break;
                        }
                        else {
                            if ((abstractRetryCopyProcess.getCurrentStep() > 1 || abstractRetryCopyProcess.getMaxProgressSteps() == 1) && fileSize2 <= n) {
                                LogHolder.log(3, LogType.MISC, "Root copy failed!");
                                b = true;
                                break;
                            }
                            n = fileSize2;
                        }
                    }
                    catch (SecurityException ex2) {
                        LogHolder.log(3, LogType.MISC, ex2);
                        b = true;
                        break;
                    }
                    Thread.sleep(abstractRetryCopyProcess.getProgressLoopWaitMilliseconds());
                    Thread.yield();
                }
                if (RecursiveFileTool.equals(file3, file, true)) {
                    return true;
                }
                if (b && abstractRetryCopyProcess.checkRetry()) {
                    abstractRetryCopyProcess.reset();
                    return this.copyAsRoot(file, file2, abstractRetryCopyProcess);
                }
            }
        }
        catch (Throwable t) {
            LogHolder.log(2, LogType.MISC, t);
        }
        return false;
    }
    
    public String getTempPath() {
        String s = super.getTempPath();
        if (s == null) {
            s = this.getenv("TEMP");
        }
        if (s == null) {
            s = this.getenv("TMP");
        }
        if (s != null && !s.endsWith(File.separator)) {
            s += File.separator;
        }
        return s;
    }
    
    private String getEnvPath(final String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException("Application name is null!");
        }
        final String getenv = this.getenv(s2);
        String string;
        if (getenv != null && getenv.trim().length() > 0 && new File(getenv).exists()) {
            string = getenv + File.separator + s;
            final File file = new File(string + File.separator);
            if (!file.exists() && !file.mkdir()) {
                LogHolder.log(3, LogType.MISC, "Could not create storage directory: " + string);
                string = null;
            }
        }
        else {
            string = null;
        }
        return string;
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
