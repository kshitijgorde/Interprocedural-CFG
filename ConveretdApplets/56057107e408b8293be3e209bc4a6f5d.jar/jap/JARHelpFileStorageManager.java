// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.util.XMLParseException;
import anon.util.RecursiveFileTool;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.io.IOException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.util.Observable;
import platform.AbstractOS;
import logging.LogHolder;
import logging.LogType;
import java.io.File;
import java.util.zip.ZipFile;
import anon.util.ClassUtil;
import anon.util.ZipArchiver;
import gui.help.AbstractHelpFileStorageManager;

public final class JARHelpFileStorageManager extends AbstractHelpFileStorageManager
{
    public static final String HELP_VERSION_NODE = "jondohelp";
    public static final String HELP_VERSION_ATTRIBUTE = "version";
    public static final String HELP_VERSION_FILE = "jondohelp.xml";
    private String m_helpPath;
    private ZipArchiver m_archiver;
    
    public JARHelpFileStorageManager() {
        final ZipFile jarFile = ClassUtil.getJarFile();
        if (jarFile == null) {}
        this.m_archiver = new ZipArchiver(jarFile);
    }
    
    private void setHelpPath(final String helpPath) {
        this.m_helpPath = helpPath;
    }
    
    public boolean helpVersionMismatch() {
        final String helpVersion = this.getHelpVersion(this.m_helpPath + File.separator + "help");
        return helpVersion == null || !"00.12.005".equals(helpVersion);
    }
    
    public boolean handleHelpPathChanged(final String s, final String helpPath, final boolean b) {
        boolean installHelp = true;
        this.setHelpPath(helpPath);
        if (s != null) {
            this.removeOldHelp(s, false);
        }
        if (helpPath != null) {
            installHelp = this.installHelp(b);
        }
        return installHelp;
    }
    
    public String helpPathValidityCheck(final String s, boolean b) {
        if (s == null) {
            return "invalidHelpPathNull";
        }
        final File file = new File(s);
        if (s.indexOf("JonDo") >= 0) {
            b = true;
        }
        if (!file.exists()) {
            return "invalidHelpPathNotExists";
        }
        if (!file.isDirectory()) {
            return "helpNoDir";
        }
        int lastIndex;
        for (String substring = s; (lastIndex = substring.toLowerCase().lastIndexOf("help".toLowerCase())) >= 0; substring = substring.substring(0, lastIndex)) {
            if (!new File(substring.substring(0, lastIndex + "help".length())).exists()) {
                LogHolder.log(0, LogType.MISC, "Existing help directory was not found!");
            }
            if (this.getHelpVersion(substring.substring(0, lastIndex + "help".length())) != null) {
                return "helpNested";
            }
        }
        final String getenv = AbstractOS.getInstance().getenv("ALLUSERSPROFILE");
        if (getenv != null && file.getPath().indexOf(getenv) >= 0) {
            return "helpVirtual";
        }
        final String getenv2 = AbstractOS.getInstance().getenv("PROGRAMFILES");
        if (getenv2 != null && file.getPath().indexOf(getenv2) >= 0) {
            return "helpVirtual";
        }
        final String getenv3 = AbstractOS.getInstance().getenv("SYSTEMROOT");
        if (getenv3 != null && file.getPath().indexOf(getenv3) >= 0) {
            return "helpVirtual";
        }
        final String getenv4 = AbstractOS.getInstance().getenv("PROGRAMDATA");
        if (getenv4 != null && file.getPath().indexOf(getenv4) >= 0) {
            return "helpVirtual";
        }
        final File file2 = new File(file.getPath() + File.separator + "help" + File.separator);
        if (!file2.exists()) {
            try {
                if (!file2.mkdir()) {
                    return "invalidHelpPathNoWrite";
                }
                file2.delete();
            }
            catch (SecurityException ex3) {
                return "invalidHelpPathNoWrite";
            }
            return "HELP_IS_VALID";
        }
        final File file3 = new File(file2.getPath() + File.separator + "jondohelp.xml");
        if (!b) {
            if (!file3.exists()) {
                LogHolder.log(4, LogType.GUI, "Found help directory without this version file: " + file3);
                return "helpDirExists";
            }
        }
        try {
            if (!file2.canWrite()) {
                return "invalidHelpPathNoWrite";
            }
        }
        catch (SecurityException ex) {
            LogHolder.log(2, LogType.MISC, ex);
            return "invalidHelpPathNoWrite";
        }
        try {
            if (!file2.canRead() || file2.list() == null) {
                return "invalidHelpPathNoRead";
            }
        }
        catch (SecurityException ex2) {
            LogHolder.log(2, LogType.MISC, ex2);
            return "invalidHelpPathNoRead";
        }
        return "helpJonDoExists";
    }
    
    public Observable getStorageObservable() {
        return this.m_archiver;
    }
    
    public boolean extractHelpFiles(final String s) {
        return this.extractHelpFiles(s, true);
    }
    
    private boolean extractHelpFiles(final String s, final boolean b) {
        if (s == null) {
            LogHolder.log(3, LogType.MISC, "Invalid directory for help extraction: " + s);
            return false;
        }
        if (this.m_archiver.extractArchive("help/", s)) {
            createHelpVersionDoc(s);
            return true;
        }
        LogHolder.log(3, LogType.MISC, "Extracting help files was not succesful.");
        return false;
    }
    
    private boolean installHelp(final boolean b) {
        final File helpFolder = this.getHelpFolder();
        if (helpFolder == null) {
            LogHolder.log(0, LogType.MISC, "Destination folder is null: Aborting help installation");
            return false;
        }
        if (this.m_archiver == null) {
            LogHolder.log(0, LogType.MISC, "JARStorageManager does only work when started from a Jar file");
            return false;
        }
        if (helpFolder.exists()) {
            if (!this.helpVersionMismatch()) {
                LogHolder.log(5, LogType.MISC, "Previous help installation restored.");
                return true;
            }
            this.removeOldHelp(this.m_helpPath, b);
            if (helpFolder.exists()) {
                LogHolder.log(0, LogType.MISC, "Could not delete old help directory!");
                return false;
            }
        }
        return this.extractHelpFiles(this.m_helpPath, false);
    }
    
    private static void createHelpVersionDoc(final String s) {
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement("jondohelp");
        XMLUtil.setAttribute(element, "version", "00.12.005");
        document.appendChild(element);
        final File file = new File(s + File.separator + "help" + File.separator + "jondohelp.xml");
        try {
            XMLUtil.write(document, file);
        }
        catch (IOException ex) {
            LogHolder.log(4, LogType.MISC, "Could not write help version due to an I/O error: ", ex);
        }
    }
    
    private boolean removeOldHelp(final String s, final boolean b) {
        if (s == null) {
            return true;
        }
        final File file = new File(s + File.separator + "help" + File.separator);
        final File file2 = new File(s + File.separator + "help" + File.separator + "jondohelp.xml");
        try {
            if (!file.exists() || (!b && !file2.exists() && file.list().length > 0)) {
                LogHolder.log(6, LogType.MISC, "No old help found in " + file.getPath());
                return true;
            }
        }
        catch (SecurityException ex) {
            LogHolder.log(6, LogType.MISC, "No old help found in " + file.getPath(), ex);
            return false;
        }
        if (!RecursiveFileTool.deleteRecursion(file)) {
            LogHolder.log(4, LogType.MISC, "Failed to delete old help at first try - try again!");
            RecursiveFileTool.deleteRecursion(file);
        }
        if (!file.exists()) {
            LogHolder.log(7, LogType.MISC, "removed old help from " + s);
            return true;
        }
        return false;
    }
    
    private boolean isHelpInstalled() {
        final File helpFolder = this.getHelpFolder();
        if (helpFolder == null) {
            return false;
        }
        if (helpFolder.exists()) {
            return true;
        }
        LogHolder.log(4, LogType.GUI, "Checked for help folder " + helpFolder + " but it did not exist");
        return false;
    }
    
    private String getHelpVersion(final String s) {
        try {
            final File file = new File(s + File.separator + "jondohelp.xml");
            if (!file.exists()) {
                return null;
            }
            return XMLUtil.parseAttribute(XMLUtil.getFirstChildByName(XMLUtil.readXMLDocument(file), "jondohelp"), "version", null);
        }
        catch (IOException ex) {
            LogHolder.log(3, LogType.MISC, "Error: an I/O error occured while parsing help version file: ", ex);
        }
        catch (XMLParseException ex2) {
            LogHolder.log(3, LogType.MISC, "Error: help version file cannot be parsed: ", ex2);
        }
        return null;
    }
    
    private File getHelpFolder() {
        if (this.m_helpPath == null) {
            return null;
        }
        return new File(this.m_helpPath + File.separator + "help" + File.separator);
    }
    
    public boolean ensureMostRecentVersion(final String helpPath) {
        this.setHelpPath(helpPath);
        if (!this.helpVersionMismatch() && this.isHelpInstalled()) {
            return true;
        }
        if (this.m_helpPath != null && this.m_helpPath.indexOf("JonDo") >= 0) {
            return this.installHelp(true);
        }
        return this.installHelp(false);
    }
    
    public boolean helpInstallationExists(final String helpPath) {
        this.setHelpPath(helpPath);
        return this.isHelpInstalled();
    }
}
