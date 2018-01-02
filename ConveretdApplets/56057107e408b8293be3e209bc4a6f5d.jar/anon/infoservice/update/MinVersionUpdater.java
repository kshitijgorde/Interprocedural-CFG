// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import anon.infoservice.InfoServiceHolder;
import anon.infoservice.JAPMinVersion;
import java.util.Hashtable;
import anon.util.Updater;

public class MinVersionUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 43200000L;
    private static final long UPDATE_INTERVAL_MS_SHORT = 480000L;
    static /* synthetic */ Class class$anon$infoservice$JAPMinVersion;
    
    public MinVersionUpdater(final ObservableInfo observableInfo) {
        super(new DynamicUpdateInterval(480000L), observableInfo);
    }
    
    public Class getUpdatedClass() {
        return (MinVersionUpdater.class$anon$infoservice$JAPMinVersion == null) ? (MinVersionUpdater.class$anon$infoservice$JAPMinVersion = class$("anon.infoservice.JAPMinVersion")) : MinVersionUpdater.class$anon$infoservice$JAPMinVersion;
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final Hashtable<String, JAPMinVersion> hashtable2 = new Hashtable<String, JAPMinVersion>();
        final JAPMinVersion newVersionNumber = InfoServiceHolder.getInstance().getNewVersionNumber();
        if (newVersionNumber != null) {
            ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(480000L);
            hashtable2.put(newVersionNumber.getId(), newVersionNumber);
        }
        ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(43200000L);
        return hashtable2;
    }
    
    protected Hashtable getEntrySerials() {
        return new Hashtable();
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
