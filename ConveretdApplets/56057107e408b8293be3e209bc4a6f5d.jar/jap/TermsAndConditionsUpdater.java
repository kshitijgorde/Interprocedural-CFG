// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.InfoServiceHolder;
import java.util.Hashtable;
import anon.util.Updater;
import anon.infoservice.update.AbstractDatabaseUpdater;

public class TermsAndConditionsUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 3600000L;
    private static final long UPDATE_INTERVAL_MS_SHORT = 600000L;
    static /* synthetic */ Class class$anon$terms$TermsAndConditions;
    
    public TermsAndConditionsUpdater() {
        super(new DynamicUpdateInterval(600000L), JAPController.getInstance().getObservableInfo());
    }
    
    public Class getUpdatedClass() {
        return (TermsAndConditionsUpdater.class$anon$terms$TermsAndConditions == null) ? (TermsAndConditionsUpdater.class$anon$terms$TermsAndConditions = class$("anon.terms.TermsAndConditions")) : TermsAndConditionsUpdater.class$anon$terms$TermsAndConditions;
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final Hashtable termsAndConditions = InfoServiceHolder.getInstance().getTermsAndConditions();
        if (termsAndConditions == null) {
            ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(600000L);
            return new Hashtable();
        }
        ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(3600000L);
        termsAndConditions.elements();
        return termsAndConditions;
    }
    
    protected Hashtable getEntrySerials() {
        final Hashtable messageSerials = InfoServiceHolder.getInstance().getMessageSerials();
        if (messageSerials == null) {
            ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(600000L);
            return new Hashtable();
        }
        ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(3600000L);
        return messageSerials;
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
