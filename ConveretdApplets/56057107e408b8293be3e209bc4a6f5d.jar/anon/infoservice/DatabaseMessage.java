// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.AbstractMessage;

public class DatabaseMessage extends AbstractMessage
{
    public static final int ENTRY_ADDED = 1;
    public static final int ENTRY_RENEWED = 2;
    public static final int ENTRY_REMOVED = 3;
    public static final int ALL_ENTRIES_REMOVED = 4;
    public static final int INITIAL_OBSERVER_MESSAGE = 5;
    
    public DatabaseMessage(final int n) {
        super(n);
    }
    
    public DatabaseMessage(final int n, final Object o) {
        super(n, o);
    }
}
