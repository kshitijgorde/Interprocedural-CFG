// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.util.ClassUtil;
import java.util.Vector;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import java.io.OutputStream;
import java.io.InputStream;
import anon.util.Util;
import java.io.FileOutputStream;
import java.io.File;
import anon.util.ResourceLoader;
import logging.LogHolder;
import logging.LogType;
import javax.swing.SwingUtilities;
import anon.infoservice.Database;
import anon.infoservice.MixCascade;
import anon.client.TrustModel;
import java.util.StringTokenizer;
import java.awt.Component;
import gui.JAPHelpContext;
import gui.help.JAPHelp;

public class MacOSXLib
{
    public static final String JAP_MACOSX_LIB_REQUIRED_VERSION = "00.00.005";
    public static final String JAP_MACOSX_LIB = "MacOSX";
    public static final String JAP_MACOSX_LIB_FILENAME = "libMacOSX.jnilib";
    private static final String JAP_MACOSX_LIB_OLD_FILENAME = "libMacOSX.jnilib.old";
    public static final String JAP_MACOSX_LIB_REQUIRED_VERSION_FILENAME = "libMacOSX.jnilib.00.00.005";
    private static final String MSG_MACOSX_LIB_UPDATE;
    private static final String UPDATE_PATH;
    private static final String MSG_SETTINGS;
    private static final String MSG_ANONYMITY_MODE;
    private static final String MSG_SHOW_DETAILS;
    private static boolean ms_bLibraryLoaded;
    static /* synthetic */ Class class$jap$MacOSXLib;
    static /* synthetic */ Class class$jap$SystrayPopupMenu;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    
    public static void dockMenuCallback(final String s) {
        SwingUtilities.invokeLater(new Runnable() {
            static /* synthetic */ Class class$anon$infoservice$MixCascade;
            
            public void run() {
                if (s.equals(MacOSXLib.MSG_ANONYMITY_MODE)) {
                    if (JAPController.getInstance().getAnonMode()) {
                        JAPController.getInstance().setAnonMode(false);
                    }
                    else {
                        JAPController.getInstance().setAnonMode(true);
                    }
                }
                else if (s.equals(MacOSXLib.MSG_SHOW_DETAILS)) {
                    JAPController.getInstance().showConfigDialog("ANON_TAB", JAPController.getInstance().getCurrentMixCascade());
                }
                else if (s.equals(MacOSXLib.MSG_SETTINGS)) {
                    JAPController.getInstance().showConfigDialog();
                }
                else if (s.equals(JAPHelp.MSG_HELP_MENU_ITEM)) {
                    final JAPHelp instance = JAPHelp.getInstance();
                    instance.setContext(JAPHelpContext.createHelpContext("index", JAPController.getInstance().getViewWindow()));
                    instance.loadCurrentContext();
                }
                else {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                    if (stringTokenizer.countTokens() == 2) {
                        TrustModel.setCurrentTrustModel(Long.parseLong(stringTokenizer.nextToken()));
                        final MixCascade currentMixCascade = (MixCascade)Database.getInstance((MacOSXLib$1.class$anon$infoservice$MixCascade == null) ? (MacOSXLib$1.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : MacOSXLib$1.class$anon$infoservice$MixCascade).getEntryById(stringTokenizer.nextToken());
                        if (currentMixCascade != null) {
                            JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
                        }
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
        });
    }
    
    public static void init() {
        if (getUpdatePath() != null && JAPModel.getInstance().isMacOSXLibraryUpdateAtStartupNeeded()) {
            update();
        }
        load();
        checkLibVersion();
        if (MacOSXLib.ms_bLibraryLoaded) {
            nativeInit();
            nativeInitDockMenu();
        }
    }
    
    private static void load() {
        try {
            System.loadLibrary("MacOSX");
            MacOSXLib.ms_bLibraryLoaded = true;
        }
        catch (Throwable t) {
            LogHolder.log(2, LogType.GUI, "Could not initialise MacOSXLib", t);
            MacOSXLib.ms_bLibraryLoaded = false;
        }
    }
    
    public static void checkLibVersion() {
        boolean b = false;
        String libVersion = null;
        if (MacOSXLib.ms_bLibraryLoaded) {
            try {
                libVersion = getLibVersion();
                LogHolder.log(6, LogType.GUI, "Existing libMacOSX.jnilib version: " + libVersion);
            }
            catch (Throwable t) {
                LogHolder.log(6, LogType.GUI, "libMacOSX.jnilib does not support version check. Update needed.");
                b = true;
            }
        }
        else {
            LogHolder.log(6, LogType.GUI, "libMacOSX.jnilib does not exist or failed to load. Update needed.");
            b = true;
        }
        LogHolder.log(6, LogType.GUI, "Required libMacOSX.jnilib version: 00.00.005");
        if (libVersion != null && libVersion.compareTo("00.00.005") < 0) {
            b = true;
        }
        if (b && JAPModel.getInstance().isMacOSXLibraryUpdateAtStartupNeeded()) {
            LogHolder.log(6, LogType.GUI, "Update failed twice. Giving up.");
            JAPModel.getInstance().setMacOSXLibraryUpdateAtStartupNeeded(false);
            JAPController.getInstance().saveConfigFile();
            return;
        }
        if (b) {
            LogHolder.log(6, LogType.GUI, "Trying to fetch libMacOSX.jnilib.00.00.005 from JAP.jar.");
            if (ResourceLoader.getResourceURL("libMacOSX.jnilib.00.00.005") == null || getUpdatePath() == null) {
                LogHolder.log(6, LogType.GUI, "Required version not available in JAP.jar. Update aborted.");
                return;
            }
            if (update()) {
                JAPModel.getInstance().setMacOSXLibraryUpdateAtStartupNeeded(false);
                JAPController.getInstance().saveConfigFile();
                load();
                String libVersion2;
                try {
                    libVersion2 = getLibVersion();
                }
                catch (Throwable t2) {
                    libVersion2 = null;
                }
                if (libVersion2 != null && libVersion2.compareTo("00.00.005") >= 0) {
                    LogHolder.log(6, LogType.GUI, "libMacOSX.jnilib successfully updated to version 00.00.005.");
                    return;
                }
                LogHolder.log(6, LogType.GUI, "libMacOSX.jnilib successfully updated to version 00.00.005. Restart needed.");
                informUserAboutJapRestart();
            }
            LogHolder.log(6, LogType.GUI, "Update failed, trying to restart JAP to retry update.");
            JAPModel.getInstance().setMacOSXLibraryUpdateAtStartupNeeded(true);
            JAPController.getInstance().saveConfigFile();
            informUserAboutJapRestart();
        }
        else if (JAPModel.getInstance().isMacOSXLibraryUpdateAtStartupNeeded()) {
            JAPModel.getInstance().setMacOSXLibraryUpdateAtStartupNeeded(false);
            JAPController.getInstance().saveConfigFile();
        }
    }
    
    private static boolean update() {
        LogHolder.log(6, LogType.GUI, "Trying to update libMacOSX.jnilib to version 00.00.005.");
        if (renameLib("libMacOSX.jnilib", "libMacOSX.jnilib.old") && extractDLL(new File(getLibFileName()))) {
            JAPModel.getInstance().setMacOSXLibraryUpdateAtStartupNeeded(false);
            JAPController.getInstance().saveConfigFile();
            return true;
        }
        renameLib("libMacOSX.jnilib.old", "libMacOSX.jnilib");
        return false;
    }
    
    private static String getUpdatePath() {
        String s = getLibFileName();
        if (s != null) {
            s = new File(s).getParent();
        }
        return s;
    }
    
    public static String getLibFileName() {
        if (MacOSXLib.UPDATE_PATH == null) {
            return null;
        }
        if (!MacOSXLib.UPDATE_PATH.endsWith(File.separator)) {
            return MacOSXLib.UPDATE_PATH + File.separator + "libMacOSX.jnilib";
        }
        return MacOSXLib.UPDATE_PATH + "libMacOSX.jnilib";
    }
    
    private static boolean renameLib(final String s, final String s2) {
        try {
            final File file = new File(getUpdatePath() + File.separator + s);
            if (file.exists()) {
                file.renameTo(new File(getUpdatePath() + File.separator + s2));
                return true;
            }
            return true;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.GUI, "Unable to copy " + getUpdatePath() + File.separator + s + ".", ex);
            return false;
        }
    }
    
    private static boolean extractDLL(final File file) {
        final boolean b = false;
        InputStream loadResourceAsStream = null;
        OutputStream outputStream = null;
        LogHolder.log(7, LogType.GUI, "Extracting libMacOSX.jnilib.00.00.005 from jar-file to: " + file);
        try {
            loadResourceAsStream = ResourceLoader.loadResourceAsStream("libMacOSX.jnilib.00.00.005");
            outputStream = new FileOutputStream(file);
            Util.copyStream(loadResourceAsStream, outputStream);
            return true;
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
            Util.closeStream(loadResourceAsStream);
            Util.closeStream(outputStream);
            return b;
        }
    }
    
    private static void informUserAboutJapRestart() {
        JAPDialog.showMessageDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(MacOSXLib.MSG_MACOSX_LIB_UPDATE));
        JAPController.goodBye(false);
    }
    
    public static JMenu showDockMenu() {
        final JMenu menu = new JMenu();
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(JAPMessages.getString(MacOSXLib.MSG_ANONYMITY_MODE));
        checkBoxMenuItem.setSelected(JAPController.getInstance().getAnonMode());
        checkBoxMenuItem.setActionCommand(MacOSXLib.MSG_ANONYMITY_MODE);
        menu.add(checkBoxMenuItem);
        final JMenuItem menuItem = new JMenuItem(JAPMessages.getString(MacOSXLib.MSG_SHOW_DETAILS));
        menuItem.setActionCommand(MacOSXLib.MSG_SHOW_DETAILS);
        menu.add(menuItem);
        menu.add(new JSeparator());
        final JMenuItem menuItem2 = new JMenuItem(JAPMessages.getString(MacOSXLib.MSG_SETTINGS));
        menuItem2.setActionCommand(MacOSXLib.MSG_SETTINGS);
        menu.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem(JAPMessages.getString(JAPHelp.MSG_HELP_MENU_ITEM));
        menuItem3.setActionCommand(JAPHelp.MSG_HELP_MENU_ITEM);
        menu.add(menuItem3);
        menu.add(new JSeparator());
        final Vector trustModels = TrustModel.getTrustModels();
        for (int i = 0; i < trustModels.size(); ++i) {
            final TrustModel trustModel = trustModels.elementAt(i);
            if (trustModel.isAdded()) {
                JMenu menu2;
                if (trustModel == TrustModel.getCurrentTrustModel()) {
                    menu2 = new JMenu(trustModel.getName() + " (" + JAPMessages.getString("active") + ")");
                }
                else {
                    menu2 = new JMenu(trustModel.getName());
                }
                final Vector entryList = Database.getInstance((MacOSXLib.class$anon$infoservice$MixCascade == null) ? (MacOSXLib.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : MacOSXLib.class$anon$infoservice$MixCascade).getEntryList();
                for (int j = 0; j < entryList.size(); ++j) {
                    final MixCascade mixCascade = entryList.elementAt(j);
                    if (trustModel.isTrusted(mixCascade)) {
                        final JMenuItem menuItem4 = new JMenuItem(mixCascade.getName());
                        if (JAPController.getInstance().getCurrentMixCascade() == mixCascade) {
                            menuItem4.setSelected(true);
                        }
                        menuItem4.setActionCommand(trustModel.getId() + "," + mixCascade.getId());
                        menu2.add(menuItem4);
                    }
                }
                menu.add(menu2);
            }
        }
        return menu;
    }
    
    private static native void nativeInit();
    
    private static native void nativeInitDockMenu();
    
    private static native String getLibVersion();
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_MACOSX_LIB_UPDATE = ((MacOSXLib.class$jap$MacOSXLib == null) ? (MacOSXLib.class$jap$MacOSXLib = class$("jap.MacOSXLib")) : MacOSXLib.class$jap$MacOSXLib).getName() + "_macOSXLibUpdate";
        MSG_SETTINGS = ((MacOSXLib.class$jap$SystrayPopupMenu == null) ? (MacOSXLib.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : MacOSXLib.class$jap$SystrayPopupMenu).getName() + "_settings";
        MSG_ANONYMITY_MODE = ((MacOSXLib.class$jap$SystrayPopupMenu == null) ? (MacOSXLib.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : MacOSXLib.class$jap$SystrayPopupMenu).getName() + "_anonymityMode";
        MSG_SHOW_DETAILS = ((MacOSXLib.class$jap$SystrayPopupMenu == null) ? (MacOSXLib.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : MacOSXLib.class$jap$SystrayPopupMenu).getName() + "_showDetails";
        MacOSXLib.ms_bLibraryLoaded = false;
        final File classDirectory = ClassUtil.getClassDirectory((MacOSXLib.class$jap$MacOSXLib == null) ? (MacOSXLib.class$jap$MacOSXLib = class$("jap.MacOSXLib")) : MacOSXLib.class$jap$MacOSXLib);
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
    }
}
