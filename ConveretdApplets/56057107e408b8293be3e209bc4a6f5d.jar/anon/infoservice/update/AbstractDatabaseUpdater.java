// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import java.util.Enumeration;
import anon.infoservice.Database;
import anon.infoservice.AbstractDatabaseEntry;
import java.util.Hashtable;
import logging.LogHolder;
import logging.LogType;
import anon.util.Updater;

public abstract class AbstractDatabaseUpdater extends Updater
{
    public static final long KEEP_ENTRY_FACTOR = 3L;
    private boolean m_successfulUpdate;
    private boolean m_bFirstUpdateDone;
    
    public AbstractDatabaseUpdater(final long n, final ObservableInfo observableInfo) {
        this(new ConstantUpdateInterval(n), observableInfo);
    }
    
    public AbstractDatabaseUpdater(final IUpdateInterval updateInterval, final ObservableInfo observableInfo) {
        super(updateInterval, observableInfo);
        this.m_successfulUpdate = false;
        this.m_bFirstUpdateDone = false;
    }
    
    protected void updateInternal() {
        final Hashtable entrySerials = this.getEntrySerials();
        if (Thread.currentThread().isInterrupted()) {
            this.m_successfulUpdate = true;
            return;
        }
        if (entrySerials == null) {
            LogHolder.log(3, LogType.THREAD, this.getUpdatedClassName() + "update failed!");
            this.m_successfulUpdate = false;
            return;
        }
        Hashtable<String, AbstractDatabaseEntry> hashtable;
        Hashtable<String, AbstractDatabaseEntry> hashtable2;
        if (entrySerials.size() > 0) {
            hashtable = new Hashtable<String, AbstractDatabaseEntry>(entrySerials.size());
            hashtable2 = new Hashtable<String, AbstractDatabaseEntry>(entrySerials.size());
        }
        else {
            hashtable = new Hashtable<String, AbstractDatabaseEntry>();
            hashtable2 = new Hashtable<String, AbstractDatabaseEntry>();
        }
        final Enumeration<String> keys = entrySerials.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final AbstractDatabaseEntry entryById = Database.getInstance(this.getUpdatedClass()).getEntryById(s);
            if (entryById != null && entrySerials.get(s).getVersionNumber() == entryById.getVersionNumber()) {
                entryById.resetCreationTime();
                hashtable2.put(entryById.getId(), entryById);
            }
            else {
                hashtable.put(s, entrySerials.get(s));
            }
        }
        if (Thread.currentThread().isInterrupted()) {
            this.m_successfulUpdate = true;
            return;
        }
        Hashtable hashtable3;
        if (hashtable2.size() == 0) {
            hashtable3 = this.getUpdatedEntries(null);
        }
        else {
            hashtable3 = this.getUpdatedEntries(hashtable);
        }
        if (hashtable3 != null) {
            final Enumeration<String> keys2 = hashtable2.keys();
            while (keys2.hasMoreElements()) {
                final String s2 = keys2.nextElement();
                hashtable3.put(s2, hashtable2.get(s2));
            }
        }
        if (Thread.currentThread().isInterrupted()) {
            this.m_successfulUpdate = true;
        }
        else if (hashtable3 == null) {
            LogHolder.log(3, LogType.THREAD, this.getUpdatedClassName() + "update failed!");
            this.m_successfulUpdate = false;
        }
        else {
            LogHolder.log(7, LogType.THREAD, this.getUpdatedClassName() + "update was successful.");
            boolean b = false;
            this.m_bFirstUpdateDone = true;
            this.m_successfulUpdate = true;
            final Enumeration<AbstractDatabaseEntry> elements = hashtable3.elements();
            while (elements.hasMoreElements()) {
                final AbstractDatabaseEntry preferredEntry = elements.nextElement();
                if (Database.getInstance(this.getUpdatedClass()).update(preferredEntry)) {
                    b = true;
                }
                final AbstractDatabaseEntry preferredEntry2 = this.getPreferredEntry();
                if (preferredEntry2 != null && preferredEntry2.equals(preferredEntry)) {
                    this.setPreferredEntry(preferredEntry);
                }
            }
            if (this.doCleanup(hashtable3) || b) {
                this.getObservableInfo().notifyAdditionalObserversOnUpdate(this.getUpdatedClass());
            }
        }
    }
    
    protected boolean wasUpdateSuccessful() {
        return this.m_successfulUpdate;
    }
    
    protected boolean doCleanup(final Hashtable hashtable) {
        boolean b = false;
        final Enumeration<AbstractDatabaseEntry> elements = (Enumeration<AbstractDatabaseEntry>)Database.getInstance(this.getUpdatedClass()).getEntryList().elements();
        while (elements.hasMoreElements()) {
            final AbstractDatabaseEntry abstractDatabaseEntry = elements.nextElement();
            if (!this.protectFromCleanup(abstractDatabaseEntry) && !abstractDatabaseEntry.isUserDefined() && !hashtable.contains(abstractDatabaseEntry) && Database.getInstance(this.getUpdatedClass()).remove(abstractDatabaseEntry)) {
                b = true;
            }
        }
        return b;
    }
    
    public final boolean isFirstUpdateDone() {
        return this.m_bFirstUpdateDone;
    }
    
    protected boolean protectFromCleanup(final AbstractDatabaseEntry abstractDatabaseEntry) {
        return false;
    }
    
    protected AbstractDatabaseEntry getPreferredEntry() {
        return null;
    }
    
    protected void setPreferredEntry(final AbstractDatabaseEntry abstractDatabaseEntry) {
    }
    
    protected abstract Hashtable getEntrySerials();
    
    protected abstract Hashtable getUpdatedEntries(final Hashtable p0);
}
