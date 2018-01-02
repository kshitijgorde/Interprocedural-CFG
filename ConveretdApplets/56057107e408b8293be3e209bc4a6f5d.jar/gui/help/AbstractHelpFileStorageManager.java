// 
// Decompiled by Procyon v0.5.30
// 

package gui.help;

import java.util.Observable;
import anon.util.LanguageMapper;
import java.util.Locale;
import anon.util.JAPMessages;
import java.util.Hashtable;

public abstract class AbstractHelpFileStorageManager
{
    public static final String HELP_INVALID_NULL = "invalidHelpPathNull";
    public static final String HELP_INVALID_PATH_NOT_EXISTS = "invalidHelpPathNotExists";
    public static final String HELP_INVALID_NOWRITE = "invalidHelpPathNoWrite";
    public static final String HELP_INVALID_NOREAD = "invalidHelpPathNoRead";
    public static final String HELP_NO_DIR = "helpNoDir";
    public static final String HELP_DIR_EXISTS = "helpDirExists";
    public static final String HELP_JONDO_EXISTS = "helpJonDoExists";
    public static final String HELP_NESTED = "helpNested";
    public static final String HELP_VIRTUAL = "helpVirtual";
    public static final String HELP_VALID = "HELP_IS_VALID";
    private Hashtable m_hashLocalisedHelpDirs;
    public static final String HELP_FOLDER = "help";
    
    public AbstractHelpFileStorageManager() {
        this.m_hashLocalisedHelpDirs = new Hashtable();
    }
    
    public final String getLocalisedHelpDir() {
        synchronized (this.m_hashLocalisedHelpDirs) {
            if (this.m_hashLocalisedHelpDirs.size() == 0) {
                int n = 1;
                while (true) {
                    try {
                        final String string = JAPMessages.getString(JAPHelp.MSG_LANGUAGE_CODE + String.valueOf(n));
                        final LanguageMapper languageMapper = new LanguageMapper(string, new Locale(string, ""));
                        this.m_hashLocalisedHelpDirs.put(languageMapper.getISOCode(), "help/" + languageMapper.getISOCode() + "/" + "help");
                    }
                    catch (Exception ex) {
                        break;
                    }
                    ++n;
                }
            }
            String s = this.m_hashLocalisedHelpDirs.get(JAPMessages.getLocale().getLanguage());
            if (s == null) {
                s = this.m_hashLocalisedHelpDirs.get(new LanguageMapper("EN").getISOCode());
            }
            return s;
        }
    }
    
    public boolean extractHelpFiles(final String s) {
        return false;
    }
    
    public abstract boolean handleHelpPathChanged(final String p0, final String p1, final boolean p2);
    
    public abstract String helpPathValidityCheck(final String p0, final boolean p1);
    
    public abstract Observable getStorageObservable();
    
    public abstract boolean ensureMostRecentVersion(final String p0);
    
    public abstract boolean helpInstallationExists(final String p0);
    
    public String getInitPath() {
        return null;
    }
}
