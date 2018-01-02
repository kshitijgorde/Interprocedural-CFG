// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.log.Log;

public class InvokeAction extends RunAction
{
    private static Log \u0107;
    
    static {
        InvokeAction.\u0107 = Log.getLog("org.xmodel.xaction");
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        InvokeAction.\u0107.warn("<invoke> is deprecated, use <run> instead.");
    }
}
