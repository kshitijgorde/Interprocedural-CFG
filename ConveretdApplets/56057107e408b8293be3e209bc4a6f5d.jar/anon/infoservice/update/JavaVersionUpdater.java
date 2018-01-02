// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import anon.infoservice.InfoServiceHolder;
import java.util.Hashtable;
import anon.util.Updater;

public class JavaVersionUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 43200000L;
    private static final long UPDATE_INTERVAL_MS_SHORT = 600000L;
    static /* synthetic */ Class class$anon$infoservice$JavaVersionDBEntry;
    
    public JavaVersionUpdater(final ObservableInfo observableInfo) {
        super(new DynamicUpdateInterval(600000L), observableInfo);
    }
    
    public Class getUpdatedClass() {
        return (JavaVersionUpdater.class$anon$infoservice$JavaVersionDBEntry == null) ? (JavaVersionUpdater.class$anon$infoservice$JavaVersionDBEntry = class$("anon.infoservice.JavaVersionDBEntry")) : JavaVersionUpdater.class$anon$infoservice$JavaVersionDBEntry;
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final Hashtable latestJavaVersions = InfoServiceHolder.getInstance().getLatestJavaVersions();
        if (latestJavaVersions == null) {
            ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(600000L);
            return new Hashtable();
        }
        ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(43200000L);
        return latestJavaVersions;
    }
    
    protected Hashtable getEntrySerials() {
        final Hashtable latestJavaVersionSerials = InfoServiceHolder.getInstance().getLatestJavaVersionSerials();
        if (latestJavaVersionSerials == null) {
            ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(600000L);
            return new Hashtable();
        }
        ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(43200000L);
        return latestJavaVersionSerials;
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
