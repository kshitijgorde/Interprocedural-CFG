// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class KeyToGroupMap implements Cloneable, PublicCloneable, Serializable
{
    private Comparable defaultGroup;
    private Map keyToGroupMap;
    
    public KeyToGroupMap() {
        this("Default Group");
    }
    
    public KeyToGroupMap(final Comparable defaultGroup) {
        if (defaultGroup == null) {
            throw new IllegalArgumentException("Null 'defaultGroup' argument.");
        }
        this.defaultGroup = defaultGroup;
        this.keyToGroupMap = new HashMap();
    }
    
    public List getGroups() {
        final Collection values = this.keyToGroupMap.values();
        final List result = new ArrayList();
        result.add(this.defaultGroup);
        for (final Comparable group : values) {
            if (!result.contains(group)) {
                result.add(group);
            }
        }
        return result;
    }
    
    public int getGroupCount() {
        return this.getGroups().size();
    }
    
    public int getGroupIndex(final Comparable group) {
        return this.getGroups().indexOf(group);
    }
    
    public Comparable getGroup(final Comparable key) {
        Comparable result = this.defaultGroup;
        final Comparable group = this.keyToGroupMap.get(key);
        if (group != null) {
            result = group;
        }
        return result;
    }
    
    public void mapKeyToGroup(final Comparable key, Comparable group) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        if (group == null) {
            group = this.defaultGroup;
        }
        this.keyToGroupMap.put(key, group);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof KeyToGroupMap) {
            final KeyToGroupMap m = (KeyToGroupMap)obj;
            final boolean b0 = ObjectUtils.equal(this.defaultGroup, m.defaultGroup);
            final boolean b2 = this.keyToGroupMap.equals(m.keyToGroupMap);
            return b0 && b2;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
