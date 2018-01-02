// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.util.EventListener;
import java.util.EventObject;

public abstract class DispatchableEvent extends EventObject
{
    private ExceptionChain chain;
    
    public DispatchableEvent(final Object source) {
        super(source);
    }
    
    public abstract void dispatch(final EventListener p0);
    
    public void appendException(final Exception ex) {
        if (this.chain == null) {
            this.chain = new ExceptionChain();
        }
        this.chain.append(ex);
    }
    
    public ExceptionChain getExceptionChain() {
        return this.chain;
    }
    
    public String toString() {
        final String cn = this.getClass().getName();
        return String.valueOf(String.valueOf(String.valueOf(cn.substring(cn.lastIndexOf(46) + 1)).concat(String.valueOf("["))).concat(String.valueOf(this.paramString()))).concat(String.valueOf("]"));
    }
    
    protected String paramString() {
        return "";
    }
}
