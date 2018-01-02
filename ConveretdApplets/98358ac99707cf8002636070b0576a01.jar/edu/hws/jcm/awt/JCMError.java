// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

public class JCMError extends RuntimeException
{
    public Object object;
    
    public JCMError(final String s) {
        this(s, (Object)null);
    }
    
    public JCMError(final String s, final Object object) {
        super(s);
        this.object = object;
    }
}
