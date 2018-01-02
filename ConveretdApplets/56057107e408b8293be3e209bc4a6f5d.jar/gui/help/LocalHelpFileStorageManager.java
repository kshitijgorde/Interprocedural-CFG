// 
// Decompiled by Procyon v0.5.30
// 

package gui.help;

import java.io.File;
import anon.util.ClassUtil;
import java.util.Observable;

public class LocalHelpFileStorageManager extends AbstractHelpFileStorageManager
{
    private final Observable DUMMY;
    private final String LOCAL_HELP_PATH;
    
    public LocalHelpFileStorageManager(final String s) {
        this.DUMMY = new Observable();
        if (s == null) {
            throw new IllegalArgumentException("Application name is null!");
        }
        final File classDirectory = ClassUtil.getClassDirectory(this.getClass());
        if (classDirectory != null) {
            this.LOCAL_HELP_PATH = classDirectory.getParent();
        }
        else {
            this.LOCAL_HELP_PATH = null;
        }
    }
    
    public boolean ensureMostRecentVersion(final String s) {
        return true;
    }
    
    public Observable getStorageObservable() {
        return this.DUMMY;
    }
    
    public boolean handleHelpPathChanged(final String s, final String s2, final boolean b) {
        return s2 != null && s2.equals(this.LOCAL_HELP_PATH) && (s == null || !s.equals(s2));
    }
    
    public boolean helpInstallationExists(final String s) {
        return s != null && s.equals(this.LOCAL_HELP_PATH);
    }
    
    public String helpPathValidityCheck(final String s, final boolean b) {
        if (s == null || !s.equals(this.LOCAL_HELP_PATH)) {
            return "invalidHelpPathNoWrite";
        }
        return "helpJonDoExists";
    }
    
    public String getInitPath() {
        return this.LOCAL_HELP_PATH;
    }
}
