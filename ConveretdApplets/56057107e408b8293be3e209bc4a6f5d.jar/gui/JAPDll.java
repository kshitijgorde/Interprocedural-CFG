// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import javax.swing.filechooser.FileFilter;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import java.util.StringTokenizer;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import anon.util.ResourceLoader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import anon.util.Util;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import anon.util.ClassUtil;
import jap.JAPController;
import java.awt.Component;
import jap.JAPModel;
import logging.LogHolder;
import logging.LogType;
import anon.util.RecursiveFileTool;
import java.io.File;
import platform.AbstractOS;
import java.awt.Window;
import jap.SystrayPopupMenu;
import java.util.Hashtable;

public final class JAPDll
{
    public static final String MSG_IGNORE_UPDATE;
    public static final String JAP_DLL_REQUIRED_VERSION = "00.04.009";
    public static final String START_PARAMETER_ADMIN = "--dllAdminUpdate";
    private static final String UPDATE_PATH;
    private static final String DLL_LIBRARY_NAME = "japdll";
    private static final String DLL_LIBRARY_NAME_32bit = "japdll";
    private static final String DLL_LIBRARY_NAME_64bit = "japdll_x64";
    private static final String JAP_DLL = "japdll.dll";
    private static final String JAP_DLL_NEW_32bit = "japdll.dll.00.04.009";
    private static final String JAP_DLL_NEW_64bit = "japdll.dll.00.04.009";
    private static final String JAP_DLL_OLD = "japdll.old";
    private static final String MSG_DLL_UPDATE;
    private static final String MSG_DLL_UPDATE_SUCCESS_ADMIN;
    private static final String MSG_DLL_UPDATE_FAILED;
    private static final String MSG_CONFIRM_OVERWRITE;
    private static final String MSG_PERMISSION_PROBLEM;
    private static final String MSG_COULD_NOT_SAVE;
    private static Hashtable ms_hashOnTop;
    private static boolean ms_bInTaskbar;
    private static final Object SYNC_POPUP;
    private static SystrayPopupMenu ms_popupMenu;
    private static Window ms_popupWindow;
    private static boolean m_sbHasOnTraffic;
    private static boolean m_bStartedAsAdmin;
    private static final String STR_HIDDEN_WINDOW;
    static /* synthetic */ Class class$gui$JAPDll;
    
    private static void loadDll() {
        String property;
        try {
            property = System.getProperty("sun.arch.data.model");
        }
        catch (SecurityException ex) {
            property = null;
        }
        if (property != null && property.equals("64")) {
            try {
                System.loadLibrary("japdll_x64");
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        else {
            try {
                System.loadLibrary("japdll");
            }
            catch (Throwable t2) {
                t2.printStackTrace();
            }
        }
    }
    
    public static void init(final boolean bStartedAsAdmin, final String s, final Window window) {
        final String property = System.getProperty("os.name", "");
        JAPDll.m_bStartedAsAdmin = bStartedAsAdmin;
        try {
            if (property == null || property.toLowerCase().indexOf("win") > -1) {
                GUIUtils.setNativeGUILibrary(new GUIUtils.NativeGUILibrary() {
                    public boolean setAlwaysOnTop(final Window window, final boolean b) {
                        return setWindowOnTop(window, b);
                    }
                    
                    public boolean isAlwaysOnTop(final Window window) {
                        return isWindowOnTop(window);
                    }
                });
                try {
                    final String tempPath = AbstractOS.getInstance().getTempPath();
                    if (tempPath != null) {
                        final File file = new File(tempPath + "japdll");
                        if (file.exists() && !RecursiveFileTool.deleteRecursion(file)) {
                            throw new Exception("Delete recursive");
                        }
                    }
                }
                catch (Throwable t) {
                    LogHolder.log(2, LogType.MISC, "Could not delete temporary DLL!", t);
                }
                boolean b = false;
                if (getUpdatePath() != null && JAPModel.getInstance().getDllUpdatePath() != null) {
                    update(window);
                    b = true;
                }
                loadDll();
                if (getUpdatePath() == null) {
                    LogHolder.log(3, LogType.GUI, "Could not get DLL update path. Maybe Java Webstart?");
                    return;
                }
                final String dllVersion = getDllVersion();
                if (b && (dllVersion == null || dllVersion.compareTo("00.04.009") < 0)) {
                    JAPModel.getInstance().setDLLupdate(getUpdatePath());
                    JAPController.getInstance().saveConfigFile();
                }
                else {
                    JAPModel.getInstance().setDLLupdate(null);
                }
                JAPController.getInstance().addProgramExitListener(new JAPController.ProgramExitListener() {
                    public void programExiting() {
                        try {
                            if (JAPDll.getDllVersion() != null) {
                                hideSystray_dll();
                            }
                        }
                        catch (Throwable t) {
                            LogHolder.log(2, LogType.GUI, t);
                        }
                    }
                });
                if (JAPDll.m_bStartedAsAdmin && s != null) {
                    final File classDirectory = ClassUtil.getClassDirectory((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll);
                    if (classDirectory != null && classDirectory.getPath().endsWith(".jar")) {
                        final File file2 = new File(s + "\\AppData\\Local\\VirtualStore" + classDirectory.getPath().substring(2, classDirectory.getPath().length()));
                        if (file2.exists() && !file2.equals(classDirectory)) {
                            final String string = AbstractOS.getInstance().getProperty("java.home") + File.separator + "bin" + File.separator + "javaw -jar ";
                            final String line = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(string + "\"" + file2.getPath() + "\" --version").getInputStream())).readLine();
                            final String line2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(string + "\"" + classDirectory.getPath() + "\" --version").getInputStream())).readLine();
                            if (line != null && line2 != null && !line.equals(line2)) {
                                Util.copyStream(new FileInputStream(file2), new FileOutputStream(classDirectory));
                                JAPController.getInstance().setRestarter(new JAPController.IRestarter() {
                                    private final /* synthetic */ JAPController.IRestarter val$origRestarter = JAPController.getInstance().getRestarter();
                                    
                                    public void exec(final String[] array) throws IOException {
                                        this.val$origRestarter.exec(array);
                                    }
                                    
                                    public boolean isConfigFileSaved() {
                                        return false;
                                    }
                                    
                                    public boolean hideWarnings() {
                                        return true;
                                    }
                                });
                                JAPController.goodBye(false);
                            }
                        }
                    }
                }
            }
        }
        catch (Throwable t2) {
            LogHolder.log(2, LogType.GUI, t2);
        }
    }
    
    public static void checkDllVersion(boolean b) {
        if (System.getProperty("os.name", "").toLowerCase().indexOf("win") < 0) {
            return;
        }
        LogHolder.log(6, LogType.GUI, "Existing japdll.dll version: " + getDllVersion());
        LogHolder.log(6, LogType.GUI, "Required japdll.dll version: 00.04.009");
        if (getDllVersion() != null && getDllVersion().compareTo("00.04.009") < 0 && ResourceLoader.getResourceURL("japdll.dll.00.04.009") != null && getUpdatePath() != null) {
            if (!new File(getDllFileName()).exists()) {
                askUserWhatToDo();
                return;
            }
            if (JAPModel.getInstance().getDllUpdatePath() != null) {
                if (b) {
                    askUserWhatToDo();
                }
                return;
            }
            if (update(JAPController.getInstance().getCurrentView()) && getDllVersion() != null && getDllVersion().compareTo("00.04.009") < 0) {
                LogHolder.log(6, LogType.GUI, "Update successful, existing japdll.dll version: " + getDllVersion());
                loadDll();
                if (getDllVersion().compareTo("00.04.009") >= 0) {
                    return;
                }
                b = true;
            }
            JAPModel.getInstance().setDLLupdate(getUpdatePath());
            JAPController.getInstance().saveConfigFile();
            if (b) {
                informUserAboutJapRestart();
            }
        }
        else if (JAPModel.getInstance().getDllUpdatePath() != null) {
            JAPModel.getInstance().setDLLupdate(null);
            JAPController.getInstance().saveConfigFile();
        }
    }
    
    private static boolean update(final Component component) {
        if (renameDLL("japdll.dll", "japdll.old") && extractDLL(new File(getDllFileName()))) {
            JAPModel.getInstance().setDLLupdate(null);
            JAPController.getInstance().saveConfigFile();
            if (JAPDll.m_bStartedAsAdmin) {
                if (component != null) {
                    JAPDialog.showMessageDialog(component, JAPMessages.getString(JAPDll.MSG_DLL_UPDATE_SUCCESS_ADMIN));
                }
                JAPController.getInstance().setRestarter(new JAPController.IRestarter() {
                    private final /* synthetic */ JAPController.IRestarter val$origRestarter = JAPController.getInstance().getRestarter();
                    
                    public void exec(final String[] array) throws IOException {
                        this.val$origRestarter.exec(array);
                    }
                    
                    public boolean isConfigFileSaved() {
                        return true;
                    }
                    
                    public boolean hideWarnings() {
                        return true;
                    }
                });
                JAPController.goodBye(true);
            }
            return true;
        }
        renameDLL("japdll.old", "japdll.dll");
        return false;
    }
    
    private static boolean renameDLL(final String s, final String s2) {
        try {
            final File file = new File(getUpdatePath() + File.separator + s);
            if (file.exists()) {
                file.renameTo(new File(getUpdatePath() + File.separator + s2));
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.GUI, "Unable to copy " + getUpdatePath() + File.separator + s + ".", ex);
            return false;
        }
    }
    
    private static boolean extractDLL(final File file) {
        boolean b = false;
        InputStream loadResourceAsStream = null;
        OutputStream outputStream = null;
        LogHolder.log(7, LogType.GUI, "Extracting japdll.dll.00.04.009 from jar-file to: " + file);
        try {
            loadResourceAsStream = ResourceLoader.loadResourceAsStream("japdll.dll.00.04.009");
            outputStream = new FileOutputStream(file);
            Util.copyStream(loadResourceAsStream, outputStream);
            b = true;
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
        }
        Util.closeStream(loadResourceAsStream);
        Util.closeStream(outputStream);
        return b;
    }
    
    private static void askUserWhatToDo() {
        if (!JAPModel.getInstance().isDLLWarningActive()) {
            return;
        }
        final JAPDialog.LinkedCheckBox linkedCheckBox = new JAPDialog.LinkedCheckBox(JAPMessages.getString(JAPDll.MSG_IGNORE_UPDATE), false);
        final int showConfirmDialog = JAPDialog.showConfirmDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAPDll.MSG_DLL_UPDATE_FAILED, new String[] { "japdll.dll", getUpdatePath() }) + "<br>&nbsp;", JAPMessages.getString(JAPDialog.MSG_TITLE_ERROR), 2, 2, linkedCheckBox);
        JAPModel.getInstance().setDllWarning(!linkedCheckBox.getState());
        if (showConfirmDialog == 0) {
            JAPController.getInstance().setRestarter(new JAPController.IRestarter() {
                public boolean isConfigFileSaved() {
                    return true;
                }
                
                public boolean hideWarnings() {
                    return false;
                }
                
                public void exec(final String[] array) throws IOException {
                    String string = null;
                    String s = "";
                    final String property = AbstractOS.getInstance().getProperty("user.home");
                    if (array != null && array.length > 1) {
                        string = "\"" + array[0] + "\"";
                        for (int i = 1; i < array.length; ++i) {
                            if (new StringTokenizer(array[i]).countTokens() > 1) {
                                s = s + " \"" + array[i] + "\"";
                            }
                            else {
                                s = s + " " + array[i];
                            }
                        }
                        if (!JAPDll.m_bStartedAsAdmin) {
                            s += " --dllAdminUpdate";
                            if (property != null) {
                                s = s + " " + property;
                            }
                        }
                    }
                    if (string == null || !JAPDll.shellExecute(string, s, true)) {
                        this.showExplorerFiles();
                    }
                }
                
                private void showExplorerFiles() {
                    boolean b = false;
                    String s = AbstractOS.getInstance().getTempPath();
                    if (s == null) {
                        s = AbstractOS.getInstance().getConfigPath("JonDo");
                    }
                    final String string = s + "japdll" + File.separator;
                    try {
                        final File file = new File(string);
                        if (file.exists() && !file.isDirectory()) {
                            file.delete();
                        }
                        b = (file.exists() || new File(string).mkdir());
                    }
                    catch (SecurityException ex) {
                        LogHolder.log(2, LogType.MISC, "Could not create temporary directory!", ex);
                    }
                    if (b && extractDLL(new File(string + "japdll.dll"))) {
                        try {
                            Runtime.getRuntime().exec(new String[] { "CMD", "/C", "EXPLORER.EXE", string });
                            Runtime.getRuntime().exec(new String[] { "CMD", "/C", "EXPLORER.EXE", getUpdatePath() });
                        }
                        catch (IOException ex2) {
                            LogHolder.log(2, LogType.MISC, ex2);
                        }
                    }
                }
            });
            JAPController.goodBye(false);
        }
    }
    
    private static void informUserAboutJapRestart() {
        JAPDialog.showMessageDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(JAPDll.MSG_DLL_UPDATE, "'japdll.dll'"));
        JAPController.goodBye(false);
    }
    
    private static boolean isWindowOnTop(final Window window) {
        return window != null && JAPDll.ms_hashOnTop.contains(window.getName());
    }
    
    private static boolean setWindowOnTop(final Window window, final boolean b) {
        if (window == null) {
            return false;
        }
        final String name = window.getName();
        if (name == null) {
            return false;
        }
        try {
            synchronized (JAPDll.ms_hashOnTop) {
                setWindowOnTop_dll(name, b);
                if (b) {
                    JAPDll.ms_hashOnTop.put(name, name);
                }
                else {
                    JAPDll.ms_hashOnTop.remove(name);
                }
            }
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static synchronized boolean showWindowFromTaskbar() {
        try {
            if (JAPDll.ms_bInTaskbar) {
                JAPDll.ms_bInTaskbar = false;
                final boolean showWindowFromTaskbar_dll = showWindowFromTaskbar_dll();
                showMainWindow();
                JAPDll.ms_bInTaskbar = !showWindowFromTaskbar_dll;
                return showWindowFromTaskbar_dll;
            }
            return false;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static synchronized boolean hideWindowInTaskbar(final String s) {
        try {
            final boolean hideWindowInTaskbar_dll = hideWindowInTaskbar_dll(s);
            if (!JAPDll.ms_bInTaskbar) {
                JAPDll.ms_bInTaskbar = hideWindowInTaskbar_dll;
            }
            return hideWindowInTaskbar_dll;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static boolean setWindowIcon(final String windowIcon_dll) {
        try {
            return setWindowIcon_dll(windowIcon_dll);
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static boolean onTraffic() {
        if (JAPDll.m_sbHasOnTraffic) {
            try {
                onTraffic_dll();
                return true;
            }
            catch (Throwable t) {
                return JAPDll.m_sbHasOnTraffic = false;
            }
        }
        return false;
    }
    
    public static boolean xcopy(final File file, final File file2, final boolean b) {
        if (file == null || file2 == null || !file2.isDirectory()) {
            return false;
        }
        String s = "";
        if (file.isDirectory()) {
            s = "/E ";
        }
        final String string = " /Y /R /Q /I /H " + s + "\"" + file + "\" \"" + file2 + "\"";
        LogHolder.log(5, LogType.MISC, "Doing xcopy: " + string);
        return shellExecute("xcopy", string, b);
    }
    
    public static String getDllVersion() {
        String s = null;
        try {
            s = getDllVersion_dll();
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            if (stringTokenizer.countTokens() > 1) {
                s = "";
                final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                if (int1 < 10) {
                    s += "0";
                }
                s = s + int1 + ".";
                final int int2 = Integer.parseInt(stringTokenizer.nextToken());
                if (int2 < 10) {
                    s += "0";
                }
                s = s + int2 + ".";
                final int int3 = Integer.parseInt(stringTokenizer.nextToken());
                if (int3 < 10) {
                    s += "0";
                }
                if (int3 < 100) {
                    s += "0";
                }
                s += int3;
            }
        }
        catch (Throwable t) {}
        return s;
    }
    
    private static String getUpdatePath() {
        String s = getDllFileName();
        if (s != null) {
            s = new File(s).getParent();
        }
        return s;
    }
    
    public static String getDllFileName() {
        String s = JAPModel.getInstance().getDllUpdatePath();
        if (s == null) {
            try {
                final String dllFileName_dll = getDllFileName_dll();
                if (dllFileName_dll == null || dllFileName_dll.length() == 0) {
                    return null;
                }
                return dllFileName_dll;
            }
            catch (Throwable t) {
                s = JAPDll.UPDATE_PATH;
            }
        }
        if (s != null) {
            if (!s.endsWith(File.separator)) {
                s = s + File.separator + "japdll.dll";
            }
            else {
                s += "japdll.dll";
            }
        }
        return s;
    }
    
    public static long showMainWindow() {
        final Window viewWindow = JAPController.getInstance().getViewWindow();
        viewWindow.setVisible(true);
        viewWindow.toFront();
        viewWindow.repaint();
        return 0L;
    }
    
    public static long closePopupMenu() {
        synchronized (JAPDll.SYNC_POPUP) {
            if (JAPDll.ms_popupMenu != null) {
                final Runnable runnable = new Runnable() {
                    public void run() {
                        JAPDll.ms_popupMenu.setVisible(false);
                        JAPDll.ms_popupWindow.setVisible(false);
                    }
                };
                if (SwingUtilities.isEventDispatchThread()) {
                    runnable.run();
                }
                else {
                    SwingUtilities.invokeLater(runnable);
                }
            }
        }
        return 0L;
    }
    
    public static long showPopupMenu(final long n, final long n2) {
        synchronized (JAPDll.SYNC_POPUP) {
            if (JAPDll.ms_popupWindow == null) {
                (JAPDll.ms_popupWindow = new JDialog(new Frame(JAPDll.STR_HIDDEN_WINDOW), false)).setName(JAPDll.STR_HIDDEN_WINDOW);
                JAPDll.ms_popupWindow.pack();
                JAPDll.ms_popupWindow.setLocation(20000, 20000);
            }
            final Point point = new Point((int)n, (int)n2);
            JAPDll.ms_popupMenu = new SystrayPopupMenu(new SystrayPopupMenu.MainWindowListener() {
                public void onShowMainWindow() {
                    JAPDll.showWindowFromTaskbar();
                }
                
                public void onShowHelp() {
                }
                
                public void onShowSettings(final String s, final Object o) {
                    new Thread(new Runnable() {
                        public void run() {
                            JAPController.getInstance().showConfigDialog(s, o);
                        }
                    }).start();
                }
            });
            GUIUtils.setAlwaysOnTop(JAPDll.ms_popupWindow, true);
            JAPDll.ms_popupWindow.setVisible(true);
            JAPDll.ms_popupMenu.addPopupMenuListener(new PopupMenuListener() {
                public void popupMenuWillBecomeVisible(final PopupMenuEvent popupMenuEvent) {
                }
                
                public void popupMenuWillBecomeInvisible(final PopupMenuEvent popupMenuEvent) {
                    popupClosed_dll();
                }
                
                public void popupMenuCanceled(final PopupMenuEvent popupMenuEvent) {
                }
            });
            JAPDll.ms_popupMenu.show(JAPDll.ms_popupWindow, new Point(point.x, point.y - JAPDll.ms_popupMenu.getHeight()));
            JAPDll.ms_popupMenu.repaint();
            return 0L;
        }
    }
    
    public static void setSystrayTooltip(String tooltipText_dll) {
        if (tooltipText_dll == null) {
            return;
        }
        if (tooltipText_dll.length() >= 60) {
            tooltipText_dll = tooltipText_dll.substring(0, 60);
        }
        tooltipText_dll = tooltipText_dll.trim();
        if (tooltipText_dll.length() == 0) {
            return;
        }
        try {
            setTooltipText_dll(tooltipText_dll);
        }
        catch (Throwable t) {}
    }
    
    public static boolean shellExecute(final String s, final String s2, final boolean b) {
        boolean shellExecute_dll;
        try {
            shellExecute_dll = shellExecute_dll(s, s2, b);
        }
        catch (Throwable t) {
            LogHolder.log(2, LogType.GUI, t);
            shellExecute_dll = false;
        }
        return shellExecute_dll;
    }
    
    private static native void setWindowOnTop_dll(final String p0, final boolean p1);
    
    private static native boolean hideWindowInTaskbar_dll(final String p0);
    
    private static native boolean showWindowFromTaskbar_dll();
    
    private static native boolean setTooltipText_dll(final String p0);
    
    private static native boolean setWindowIcon_dll(final String p0);
    
    private static native void onTraffic_dll();
    
    private static native void popupClosed_dll();
    
    private static native void hideSystray_dll();
    
    private static native String getDllVersion_dll();
    
    private static native String getDllFileName_dll();
    
    private static native boolean shellExecute_dll(final String p0, final String p1, final boolean p2);
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_IGNORE_UPDATE = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_ignoreUpdate";
        MSG_DLL_UPDATE = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_updateRestartMessage";
        MSG_DLL_UPDATE_SUCCESS_ADMIN = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_dllUpdateSuccessAdmin";
        MSG_DLL_UPDATE_FAILED = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_updateFailed";
        MSG_CONFIRM_OVERWRITE = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_confirmOverwrite";
        MSG_PERMISSION_PROBLEM = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_permissionProblem";
        MSG_COULD_NOT_SAVE = ((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll).getName() + "_couldNotSave";
        JAPDll.ms_hashOnTop = new Hashtable();
        JAPDll.ms_bInTaskbar = false;
        SYNC_POPUP = new Object();
        JAPDll.m_sbHasOnTraffic = true;
        JAPDll.m_bStartedAsAdmin = false;
        final File classDirectory = ClassUtil.getClassDirectory((JAPDll.class$gui$JAPDll == null) ? (JAPDll.class$gui$JAPDll = class$("gui.JAPDll")) : JAPDll.class$gui$JAPDll);
        if (classDirectory == null) {
            String property = null;
            try {
                property = System.getProperty("user.dir", null);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            UPDATE_PATH = property;
        }
        else {
            UPDATE_PATH = classDirectory.getParent();
        }
        STR_HIDDEN_WINDOW = Double.toString(Math.random());
    }
    
    private static class MyFileFilter extends FileFilter
    {
        public static final String DLL_EXTENSION = ".dll";
        private final String ACCOUNT_DESCRIPTION = "JAP dll file (*.dll)";
        private int filterType;
        
        public int getFilterType() {
            return this.filterType;
        }
        
        public boolean accept(final File file) {
            return file.isDirectory() || file.getName().endsWith(".dll");
        }
        
        public String getDescription() {
            return "JAP dll file (*.dll)";
        }
    }
}
