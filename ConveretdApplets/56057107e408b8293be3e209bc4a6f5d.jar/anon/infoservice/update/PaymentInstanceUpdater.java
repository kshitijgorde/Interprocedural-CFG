// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import anon.pay.PayAccountsFile;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.InfoServiceHolder;
import java.util.Hashtable;
import anon.util.Updater;

public class PaymentInstanceUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 900000L;
    private static final long MIN_UPDATE_INTERVAL_MS = 60000L;
    static /* synthetic */ Class class$anon$pay$PaymentInstanceDBEntry;
    
    public PaymentInstanceUpdater(final ObservableInfo observableInfo) {
        super(new DynamicUpdateInterval(900000L), observableInfo);
    }
    
    public PaymentInstanceUpdater(final long n, final ObservableInfo observableInfo) {
        super(n, observableInfo);
    }
    
    public Class getUpdatedClass() {
        return (PaymentInstanceUpdater.class$anon$pay$PaymentInstanceDBEntry == null) ? (PaymentInstanceUpdater.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : PaymentInstanceUpdater.class$anon$pay$PaymentInstanceDBEntry;
    }
    
    protected Hashtable getEntrySerials() {
        return new Hashtable();
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        Hashtable paymentInstances = InfoServiceHolder.getInstance().getPaymentInstances();
        if (this.getUpdateInterval() instanceof DynamicUpdateInterval) {
            if (paymentInstances == null) {
                ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(60000L);
            }
            else {
                ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(900000L);
            }
        }
        if (paymentInstances != null && paymentInstances.size() == 0) {
            paymentInstances = null;
        }
        return paymentInstances;
    }
    
    protected boolean protectFromCleanup(final AbstractDatabaseEntry abstractDatabaseEntry) {
        return PayAccountsFile.getInstance().getAccounts(abstractDatabaseEntry.getId()).size() > 0;
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
