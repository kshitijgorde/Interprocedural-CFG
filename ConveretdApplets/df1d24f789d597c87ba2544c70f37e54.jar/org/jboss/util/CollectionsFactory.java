// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import EDU.oswego.cs.dl.util.concurrent.CopyOnWriteArraySet;
import java.util.Set;
import EDU.oswego.cs.dl.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap;
import java.util.Map;

public class CollectionsFactory
{
    public static final Map createConcurrentReaderMap() {
        return (Map)new ConcurrentReaderHashMap();
    }
    
    public static final List createCopyOnWriteList() {
        return (List)new CopyOnWriteArrayList();
    }
    
    public static final Set createCopyOnWriteSet() {
        return (Set)new CopyOnWriteArraySet();
    }
}
