// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import java.util.Hashtable;
import anon.infoservice.InfoServiceDBEntry;
import anon.infoservice.InfoServiceHolder;
import anon.infoservice.AbstractDatabaseEntry;
import anon.util.Updater;

public class InfoServiceUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 300000L;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    
    public InfoServiceUpdater(final ObservableInfo observableInfo) {
        super(new ConstantUpdateInterval(300000L), observableInfo);
    }
    
    public Class getUpdatedClass() {
        return (InfoServiceUpdater.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceUpdater.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceUpdater.class$anon$infoservice$InfoServiceDBEntry;
    }
    
    protected AbstractDatabaseEntry getPreferredEntry() {
        return InfoServiceHolder.getInstance().getPreferredInfoService();
    }
    
    protected void setPreferredEntry(final AbstractDatabaseEntry abstractDatabaseEntry) {
        if (abstractDatabaseEntry instanceof InfoServiceDBEntry) {
            InfoServiceHolder.getInstance().setPreferredInfoService((InfoServiceDBEntry)abstractDatabaseEntry);
        }
    }
    
    protected void updateInternal() {
        super.updateInternal();
    }
    
    protected Hashtable getEntrySerials() {
        return new Hashtable();
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        return InfoServiceHolder.getInstance().getInfoServices();
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
