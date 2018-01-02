// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import java.util.Enumeration;
import anon.client.TrustModel;
import anon.infoservice.InfoServiceHolder;
import java.util.Vector;
import anon.infoservice.MixInfo;
import anon.infoservice.Database;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.MixCascade;
import java.util.Hashtable;
import anon.infoservice.AbstractDatabaseEntry;
import anon.util.Updater;

public abstract class AbstractMixCascadeUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 240000L;
    private static final long MIN_UPDATE_INTERVAL_MS = 30000L;
    private boolean m_bDoMixInfoCleanup;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$MixInfo;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    
    public AbstractMixCascadeUpdater(final ObservableInfo observableInfo) {
        super(new DynamicUpdateInterval(240000L), observableInfo);
        this.m_bDoMixInfoCleanup = true;
    }
    
    public AbstractMixCascadeUpdater(final long n, final boolean bDoMixInfoCleanup, final ObservableInfo observableInfo) {
        super(n, observableInfo);
        this.m_bDoMixInfoCleanup = true;
        this.m_bDoMixInfoCleanup = bDoMixInfoCleanup;
    }
    
    protected abstract AbstractDatabaseEntry getPreferredEntry();
    
    protected abstract void setPreferredEntry(final AbstractDatabaseEntry p0);
    
    public final Class getUpdatedClass() {
        return (AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade;
    }
    
    protected final boolean doCleanup(final Hashtable hashtable) {
        final boolean doCleanup = super.doCleanup(hashtable);
        final MixCascade mixCascade = (MixCascade)this.getPreferredEntry();
        if (this.m_bDoMixInfoCleanup) {
            LogHolder.log(7, LogType.MISC, "Do MixInfo database cleanup.");
            final Vector entryList = Database.getInstance((AbstractMixCascadeUpdater.class$anon$infoservice$MixInfo == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : AbstractMixCascadeUpdater.class$anon$infoservice$MixInfo).getEntryList();
            final Vector entryList2 = Database.getInstance((AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade).getEntryList();
            if (mixCascade != null) {
                entryList2.addElement(mixCascade);
            }
        Label_0312:
            for (int i = 0; i < entryList.size(); ++i) {
                final MixInfo mixInfo = entryList.elementAt(i);
                if (Database.getInstance((AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade).getEntryById(mixInfo.getId()) == null) {
                    if (mixCascade == null || !mixCascade.getMixIds().elementAt(0).equals(mixInfo.getId())) {
                        for (int j = 0; j < entryList2.size(); ++j) {
                            final Vector mixIds = entryList2.elementAt(j).getMixIds();
                            for (int k = 1; k < mixIds.size(); ++k) {
                                if (mixIds.elementAt(k).equals(mixInfo.getId())) {
                                    continue Label_0312;
                                }
                            }
                        }
                        Database.getInstance((AbstractMixCascadeUpdater.class$anon$infoservice$MixInfo == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : AbstractMixCascadeUpdater.class$anon$infoservice$MixInfo).remove(mixInfo);
                        LogHolder.log(5, LogType.MISC, "Cleaned MixInfo DB entry: " + mixInfo.getId());
                    }
                }
            }
        }
        return doCleanup;
    }
    
    protected final Hashtable getEntrySerials() {
        final Hashtable mixCascadeSerials = InfoServiceHolder.getInstance().getMixCascadeSerials(TrustModel.getContext());
        if (this.getUpdateInterval() instanceof DynamicUpdateInterval) {
            if (mixCascadeSerials == null) {
                ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(30000L);
            }
            else {
                ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(240000L);
            }
        }
        return mixCascadeSerials;
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final Enumeration<MixCascade> elements = Database.getInstance((AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : AbstractMixCascadeUpdater.class$anon$infoservice$MixCascade).getEntryHash().elements();
        final Hashtable<String, MixCascade> hashtable2 = new Hashtable<String, MixCascade>();
        while (elements.hasMoreElements()) {
            final MixCascade mixCascade = elements.nextElement();
            if (this.fetchCurrentStatus(mixCascade)) {
                hashtable2.put(mixCascade.getId(), mixCascade);
            }
        }
        final Hashtable<String, MixCascade> hashtable3 = hashtable2;
        final Hashtable updatedEntries_internal = this.getUpdatedEntries_internal(hashtable);
        final Enumeration<MixCascade> elements2 = updatedEntries_internal.elements();
        while (elements2.hasMoreElements()) {
            final MixCascade mixCascade2 = elements2.nextElement();
            if (!hashtable3.contains(mixCascade2)) {
                this.fetchCurrentStatus(mixCascade2);
            }
        }
        return updatedEntries_internal;
    }
    
    private final boolean fetchCurrentStatus(final MixCascade mixCascade) {
        final Object o = null;
        if (!mixCascade.isUserDefined()) {
            return Database.getInstance((AbstractMixCascadeUpdater.class$anon$infoservice$StatusInfo == null) ? (AbstractMixCascadeUpdater.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : AbstractMixCascadeUpdater.class$anon$infoservice$StatusInfo).update(mixCascade.fetchCurrentStatus(720000L));
        }
        return o != null;
    }
    
    protected final Hashtable getUpdatedEntries_internal(final Hashtable hashtable) {
        Hashtable<String, MixCascade> mixCascades;
        if (hashtable == null) {
            mixCascades = (Hashtable<String, MixCascade>)InfoServiceHolder.getInstance().getMixCascades(TrustModel.getContext());
        }
        else if (hashtable.size() == 0) {
            mixCascades = new Hashtable<String, MixCascade>();
        }
        else {
            final Hashtable<String, MixCascade> hashtable2 = new Hashtable<String, MixCascade>(hashtable.size());
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final MixCascade mixCascadeInfo = InfoServiceHolder.getInstance().getMixCascadeInfo(keys.nextElement());
                if (mixCascadeInfo != null) {
                    hashtable2.put(mixCascadeInfo.getId(), mixCascadeInfo);
                }
            }
            mixCascades = hashtable2;
        }
        return mixCascades;
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
