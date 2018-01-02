// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import java.util.HashMap;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Map;

public class NamedContexts
{
    private static ThreadLocal<Map<String, StatefulContext>> threadMap;
    
    static {
        NamedContexts.threadMap = new ThreadLocal<Map<String, StatefulContext>>();
    }
    
    protected NamedContexts() {
        throw new InternalError("Badly shrinked");
    }
    
    public static StatefulContext get(final String s) {
        return getMap().get(s);
    }
    
    public static void set(final String s, final StatefulContext statefulContext) {
        getMap().put(s, statefulContext);
    }
    
    public static void remove(final String s) {
        getMap().remove(s);
    }
    
    private static Map<String, StatefulContext> getMap() {
        Map<String, StatefulContext> map = NamedContexts.threadMap.get();
        if (map == null) {
            map = new HashMap<String, StatefulContext>();
            NamedContexts.threadMap.set(map);
        }
        return map;
    }
}
