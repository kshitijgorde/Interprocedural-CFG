// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice.update;

import anon.infoservice.InfoServiceHolder;
import java.util.Hashtable;
import anon.util.Updater;

public class MessageUpdater extends AbstractDatabaseUpdater
{
    private static final long UPDATE_INTERVAL_MS = 3600000L;
    private static final long UPDATE_INTERVAL_MS_SHORT = 600000L;
    static /* synthetic */ Class class$anon$infoservice$MessageDBEntry;
    
    public MessageUpdater(final ObservableInfo observableInfo) {
        super(new DynamicUpdateInterval(600000L), observableInfo);
    }
    
    public Class getUpdatedClass() {
        return (MessageUpdater.class$anon$infoservice$MessageDBEntry == null) ? (MessageUpdater.class$anon$infoservice$MessageDBEntry = class$("anon.infoservice.MessageDBEntry")) : MessageUpdater.class$anon$infoservice$MessageDBEntry;
    }
    
    protected Hashtable getUpdatedEntries(final Hashtable hashtable) {
        final Hashtable messages = InfoServiceHolder.getInstance().getMessages();
        if (messages == null) {
            ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(600000L);
            return new Hashtable();
        }
        ((DynamicUpdateInterval)this.getUpdateInterval()).setUpdateInterval(3600000L);
        return messages;
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
