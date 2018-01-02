// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import anon.util.RecursiveFileTool;
import java.io.File;
import logging.LogHolder;
import logging.LogType;
import java.util.Properties;

public class LinuxOS extends AbstractOS
{
    private boolean m_bKDE;
    private boolean m_bGnome;
    public static final String[] BROWSERLIST;
    
    public LinuxOS() throws Exception {
        this.m_bKDE = false;
        this.m_bGnome = false;
        if (System.getProperty("os.name", "").toLowerCase().toLowerCase().indexOf("linux") == -1) {
            throw new Exception("Operating system is not Linux");
        }
        final Properties properties = new Properties();
        try {
            properties.load(Runtime.getRuntime().exec("env").getInputStream());
            this.m_bKDE = Boolean.valueOf(properties.getProperty("KDE_FULL_SESSION"));
        }
        catch (Exception ex) {}
        this.m_bGnome = (properties.getProperty("GNOME_DESKTOP_SESSION_ID") != null);
        this.initEnv("env");
    }
    
    protected boolean openLink(final String s) {
        if (s == null) {
            return false;
        }
        if (this.m_bKDE) {
            try {
                Runtime.getRuntime().exec("kfmclient exec " + s);
                return true;
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.MISC, "Cannot open '" + s + "' in KDE default program.");
                return false;
            }
        }
        if (this.m_bGnome) {
            try {
                Runtime.getRuntime().exec("gnome-open " + s);
                return true;
            }
            catch (Exception ex2) {
                LogHolder.log(3, LogType.MISC, "Cannot open '" + s + "' in Gnome default program.");
            }
        }
        return false;
    }
    
    public String getConfigPath(final String s) {
        return System.getProperty("user.home", "") + "/.";
    }
    
    public boolean copyAsRoot(final File file, final File file2, final AbstractRetryCopyProcess abstractRetryCopyProcess) {
        if (file == null || file2 == null || !file2.isDirectory()) {
            return false;
        }
        boolean b = false;
        final String path = file.getPath();
        final String string = file2.getPath() + "/";
        abstractRetryCopyProcess.incrementProgress();
        if (this.m_bKDE) {
            this.executeShell("kdesu 'cp -r " + path + " " + string + "'");
        }
        else if (this.m_bGnome) {
            this.executeShell("gksu 'cp -r " + path + " " + string + "'");
        }
        else {
            this.executeShell("xterm -e su -c 'cp -r " + path + " " + string + "'");
            b = true;
        }
        if (RecursiveFileTool.equals(new File(string + file.getName()), file, true)) {
            while (abstractRetryCopyProcess.incrementProgress()) {}
            return true;
        }
        if (b && abstractRetryCopyProcess != null && abstractRetryCopyProcess.checkRetry()) {
            abstractRetryCopyProcess.reset();
            return this.copyAsRoot(file, file2, abstractRetryCopyProcess);
        }
        return false;
    }
    
    private void executeShell(final String s) {
        try {
            Runtime.getRuntime().exec(new String[] { "sh", "-c", s }).waitFor();
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
        }
    }
    
    static {
        BROWSERLIST = new String[] { "firefox", "iexplore", "explorer", "mozilla", "konqueror", "mozilla-firefox", "opera" };
    }
}
