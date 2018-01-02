// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import anon.infoservice.InfoServiceHolder;
import java.util.Hashtable;
import anon.util.Updater;

public class PerformanceInfoUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL = 240000L;
    private static final long MIN_UPDATE_INTERVAL_MS = 20000L;
    static /* synthetic */ Class class$anon$infoservice$PerformanceInfo;
    
    public PerformanceInfoUpdater(final ObservableInfo observableInfo) {
        super(new DynamicUpdateInterval(240000L), observableInfo);
    }
    
    public PerformanceInfoUpdater(final long n, final ObservableInfo observableInfo) {
        super(n, observableInfo);
    }
    
    protected Hashtable getEntrySerials() {
        return new Hashtable();
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final Hashtable performanceInfos = InfoServiceHolder.getInstance().getPerformanceInfos();
        if (this.getUpdateInterval() instanceof DynamicUpdateInterval) {
            if (performanceInfos == null) {
                ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(20000L);
            }
            else {
                ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(240000L);
            }
        }
        return performanceInfos;
    }
    
    public Class getUpdatedClass() {
        return (PerformanceInfoUpdater.class$anon$infoservice$PerformanceInfo == null) ? (PerformanceInfoUpdater.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : PerformanceInfoUpdater.class$anon$infoservice$PerformanceInfo;
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
