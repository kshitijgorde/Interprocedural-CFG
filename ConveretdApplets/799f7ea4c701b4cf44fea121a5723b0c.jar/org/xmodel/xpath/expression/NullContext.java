// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModelObject;
import org.xmodel.NullObject;

public class NullContext extends Context
{
    private static ThreadLocal<NullContext> I;
    
    static {
        NullContext.I = new ThreadLocal<NullContext>();
    }
    
    protected NullContext() {
        super(new NullObject());
    }
    
    public static NullContext getInstance() {
        NullContext nullContext = NullContext.I.get();
        if (nullContext == null) {
            nullContext = new NullContext();
            NullContext.I.set(nullContext);
        }
        return nullContext;
    }
}
