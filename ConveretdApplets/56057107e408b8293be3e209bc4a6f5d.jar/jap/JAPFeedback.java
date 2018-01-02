// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.StatusInfo;
import java.util.Hashtable;
import java.util.Observable;
import anon.util.Updater;
import anon.infoservice.update.AbstractDatabaseUpdater;

final class JAPFeedback extends AbstractDatabaseUpdater
{
    public static final long UPDATE_INTERVAL_MS = 70000L;
    private static final long MIN_UPDATE_INTERVAL_MS = 20000L;
    private DynamicUpdateInterval m_updateInterval;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    
    public JAPFeedback() {
        super(new DynamicUpdateInterval(70000L), new ObservableInfo() {
            public Integer getUpdateChanged() {
                return JAPController.getInstance().getObservableInfo().getUpdateChanged();
            }
            
            public boolean isUpdateDisabled() {
                return JAPController.getInstance().getObservableInfo().isUpdateDisabled();
            }
            
            public void notifyAdditionalObserversOnUpdate(final Class clazz) {
                JAPController.getInstance().getObservableInfo().notifyAdditionalObserversOnUpdate(clazz);
            }
            
            public boolean updateImmediately() {
                return true;
            }
        });
        this.m_updateInterval = (DynamicUpdateInterval)this.getUpdateInterval();
    }
    
    public Class getUpdatedClass() {
        return (JAPFeedback.class$anon$infoservice$StatusInfo == null) ? (JAPFeedback.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : JAPFeedback.class$anon$infoservice$StatusInfo;
    }
    
    protected boolean doCleanup(final Hashtable hashtable) {
        return false;
    }
    
    protected boolean isUpdatePaused() {
        return !JAPController.getInstance().getAnonMode() || JAPController.getInstance().getCurrentMixCascade().isUserDefined();
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final StatusInfo fetchCurrentStatus = JAPController.getInstance().getCurrentMixCascade().fetchCurrentStatus();
        final Hashtable<String, StatusInfo> hashtable2 = new Hashtable<String, StatusInfo>();
        if (fetchCurrentStatus != null) {
            if (fetchCurrentStatus.getExpireTime() <= System.currentTimeMillis() + 70000L) {
                this.m_updateInterval.setUpdateInterval(20000L);
            }
            else if (fetchCurrentStatus.getExpireTime() <= System.currentTimeMillis() + 105000.0) {
                this.m_updateInterval.setUpdateInterval(Math.max(35000L, 20000L));
            }
            else {
                this.m_updateInterval.setUpdateInterval(70000L);
            }
            hashtable2.put(fetchCurrentStatus.getId(), fetchCurrentStatus);
        }
        else {
            this.m_updateInterval.setUpdateInterval(20000L);
        }
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
