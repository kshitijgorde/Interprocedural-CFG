// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import java.util.zip.ZipFile;
import anon.util.RecursiveFileTool;
import anon.util.ZipArchiver;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import anon.util.ClassUtil;
import logging.LogHolder;
import logging.LogType;
import anon.util.Util;

public class MacOS extends AbstractOS
{
    public static final String OS_NAME = "Mac OS";
    static final String BUNDLE_CONTENTS;
    static final String BUNDLE_RESOURCES;
    static final String BUNDLE_MAC_OS_EXECUTABLES;
    static final String BUNDLE_PROPERTY_FILE_NAME = "Info.plist";
    static final String BUNDLE_EXECUTABLE_PROPERTY_KEY = "CFBundleExecutable";
    static final String ROOT_SHELLSCRIPT_NAME = "rootShellScript";
    static final String OSA_EXEC_SHELLSCRIPT_STMT = "do shell script rootShellScript with administrator privileges";
    static final String OSA_APPLET_NAME = "JonDoUpdater.app";
    static final String OSA_APPLET_PATH;
    static final String[] OSASCRIPT_CMD;
    static final String[] OSACOMPILE_CMD;
    static final String[] OPEN_UPDATER_CMD;
    private String m_bundlePath;
    
    public MacOS() throws Exception {
        this.m_bundlePath = null;
        if (System.getProperty("mrj.version") == null) {
            throw new Exception("Operating system is not Mac OS");
        }
        this.setBundlePath();
    }
    
    protected boolean openLink(final String s) {
        final String encodeWhiteSpaces = Util.encodeWhiteSpaces(s);
        try {
            Runtime.getRuntime().exec("open " + encodeWhiteSpaces);
            return true;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Cannot open '" + encodeWhiteSpaces + "' in MacOS default program.");
            return false;
        }
    }
    
    public boolean isHelpAutoInstalled() {
        return true;
    }
    
    public String getConfigPath(final String s) {
        if (System.getProperty("os.name").equalsIgnoreCase("Mac OS")) {
            return System.getProperty("user.home", ".") + "/";
        }
        return System.getProperty("user.home", "") + "/Library/Preferences/";
    }
    
    public void setBundlePath() {
        final File classDirectory = ClassUtil.getClassDirectory(this.getClass());
        if (classDirectory != null) {
            String path = classDirectory.getPath();
            if (path != null) {
                if (!path.startsWith(File.separator)) {
                    final int index = path.indexOf("/");
                    path = ((index != -1) ? path.substring(index) : path);
                }
                final int index2 = path.indexOf(MacOS.BUNDLE_CONTENTS);
                if (index2 != -1) {
                    this.m_bundlePath = path.substring(0, index2 - 1);
                    return;
                }
            }
        }
        this.m_bundlePath = null;
    }
    
    public String getBundlePath() {
        return this.m_bundlePath;
    }
    
    public boolean isBundle() {
        return this.m_bundlePath != null;
    }
    
    public String getBundleExecutablePath() {
        return null;
    }
    
    private static int handleAppleScriptCmds(final String[] array, final Process process) throws IOException, InterruptedException {
        final PrintWriter printWriter = new PrintWriter(process.getOutputStream());
        for (int i = 0; i < array.length; ++i) {
            printWriter.println(array[i]);
        }
        printWriter.flush();
        printWriter.close();
        return process.waitFor();
    }
    
    public boolean copyAsRoot(final File file, final File file2, final AbstractRetryCopyProcess abstractRetryCopyProcess) {
        final String[] array = { "set rootShellScript to \"cp " + file.getAbsolutePath() + " " + file2.getAbsolutePath() + "\"", "do shell script rootShellScript with administrator privileges" };
        try {
            final Runtime runtime = Runtime.getRuntime();
            int handleAppleScriptCmds = 1;
            if (MacOS.OSACOMPILE_CMD != null) {
                handleAppleScriptCmds = handleAppleScriptCmds(array, runtime.exec(MacOS.OSACOMPILE_CMD));
            }
            if (handleAppleScriptCmds == 0) {
                final ZipFile jarFile = ClassUtil.getJarFile();
                if (jarFile != null) {
                    final ZipArchiver zipArchiver = new ZipArchiver(jarFile);
                    new File(MacOS.OSA_APPLET_PATH + File.separator + MacOS.BUNDLE_RESOURCES + "applet.icns").delete();
                    zipArchiver.extractSingleEntry("images/JUpdate.icns", MacOS.OSA_APPLET_PATH + File.separator + MacOS.BUNDLE_RESOURCES + "applet.icns");
                }
                runtime.exec(MacOS.OPEN_UPDATER_CMD).waitFor();
            }
            else {
                handleAppleScriptCmds(array, runtime.exec(MacOS.OSASCRIPT_CMD));
            }
            if (MacOS.OSA_APPLET_PATH != null) {
                final File file3 = new File(MacOS.OSA_APPLET_PATH);
                if (file3.exists() && MacOS.OSA_APPLET_PATH.endsWith("JonDoUpdater.app")) {
                    RecursiveFileTool.deleteRecursion(file3);
                }
            }
            return RecursiveFileTool.equals(file, new File(file2.getAbsolutePath() + File.separator + file.getName()), true);
        }
        catch (IOException ex) {
            LogHolder.log(6, LogType.MISC, "Mac OS root copy failed: ", ex);
            return false;
        }
        catch (InterruptedException ex2) {
            LogHolder.log(2, LogType.MISC, "Interrupted while waiting for root copy process ", ex2);
            return false;
        }
    }
    
    static {
        BUNDLE_CONTENTS = "Contents" + File.separator;
        BUNDLE_RESOURCES = MacOS.BUNDLE_CONTENTS + "Resources" + File.separator;
        BUNDLE_MAC_OS_EXECUTABLES = MacOS.BUNDLE_CONTENTS + "MacOS" + File.separator;
        OSA_APPLET_PATH = ((AbstractOS.getDefaultTempPath() != null) ? (AbstractOS.getDefaultTempPath() + "JonDoUpdater.app") : null);
        OSASCRIPT_CMD = new String[] { "osascript" };
        String[] osacompile_CMD;
        if (MacOS.OSA_APPLET_PATH != null) {
            final String[] array = osacompile_CMD = new String[3];
            array[0] = "osacompile";
            array[1] = "-xo";
            array[2] = MacOS.OSA_APPLET_PATH;
        }
        else {
            osacompile_CMD = null;
        }
        OSACOMPILE_CMD = osacompile_CMD;
        final String[] open_UPDATER_CMD;
        if (MacOS.OSA_APPLET_PATH != null) {
            open_UPDATER_CMD = new String[] { MacOS.OSA_APPLET_PATH + File.separator + MacOS.BUNDLE_MAC_OS_EXECUTABLES + "applet" };
        }
        OPEN_UPDATER_CMD = open_UPDATER_CMD;
    }
}
