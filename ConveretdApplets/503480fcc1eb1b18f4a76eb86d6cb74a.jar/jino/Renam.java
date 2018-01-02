// 
// Decompiled by Procyon v0.5.30
// 

package jino;

import java.util.Map;
import java.beans.Expression;

public class Renam extends Expression implements Map.Entry
{
    Map.Entry entry;
    
    public Renam(final Object target, final String methodName, final Object[] arguments) {
        super(target, methodName, arguments);
    }
    
    @Override
    public Object getKey() {
        return null;
    }
}
