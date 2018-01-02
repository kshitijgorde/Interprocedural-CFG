// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.util.AbstractMessage;

public class JAPControllerMessage extends AbstractMessage
{
    public static final int INFOSERVICE_POLICY_CHANGED = 1;
    public static final int CURRENT_MIXCASCADE_CHANGED = 2;
    public static final int ASK_SAVE_PAYMENT_CHANGED = 3;
    
    public JAPControllerMessage(final int n) {
        super(n);
    }
    
    public JAPControllerMessage(final int n, final Object o) {
        super(n, o);
    }
}
