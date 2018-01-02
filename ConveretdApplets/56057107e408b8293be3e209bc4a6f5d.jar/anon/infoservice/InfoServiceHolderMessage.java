// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.AbstractMessage;

public class InfoServiceHolderMessage extends AbstractMessage
{
    public static final int PREFERRED_INFOSERVICE_CHANGED = 1;
    public static final int INFOSERVICE_MANAGEMENT_CHANGED = 2;
    public static final int INFOSERVICES_NOT_VERIFYABLE = 3;
    
    public InfoServiceHolderMessage(final int n) {
        super(n);
    }
    
    public InfoServiceHolderMessage(final int n, final Object o) {
        super(n, o);
    }
}
