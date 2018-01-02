// 
// Decompiled by Procyon v0.5.30
// 

package bpac;

import java.util.Map;
import java.beans.Expression;

public class oo extends Expression implements Map.Entry
{
    Map.Entry entry;
    
    public oo(final Object target, final String methodName, final Object[] arguments) {
        super(target, methodName, arguments);
    }
    
    public Object getKey() {
        return null;
    }
}
