// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class SkypeObject
{
    private Map userDataMap;
    
    SkypeObject() {
        this.userDataMap = Collections.synchronizedMap(new HashMap<Object, Object>());
    }
    
    public final Object getData(final String name) {
        return this.userDataMap.get(name);
    }
    
    public final void setData(final String name, final Object userData) {
        this.userDataMap.put(name, userData);
    }
    
    void copyFrom(final Object base) {
        if (base instanceof SkypeObject) {
            this.userDataMap.putAll(((SkypeObject)base).userDataMap);
        }
    }
}
