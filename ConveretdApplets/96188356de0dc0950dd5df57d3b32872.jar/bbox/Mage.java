// 
// Decompiled by Procyon v0.5.30
// 

package bbox;

import java.util.Map;
import java.beans.Expression;

public class Mage extends Expression implements Map.Entry
{
    Map.Entry entry;
    
    public Mage(final Object target, final String methodName, final Object[] arguments) {
        super(target, methodName, arguments);
    }
    
    public Object getKey() {
        return null;
    }
}
