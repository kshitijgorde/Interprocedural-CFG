// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import org.xmodel.IModelObject;
import org.xmodel.Element;

public class NullAccessGroup extends AccessGroup
{
    public NullAccessGroup() {
        super(new Element("null"));
    }
    
    @Override
    public void validateVariables() {
    }
    
    @Override
    public boolean isFeatureLocked(final Map<Mutex, Integer> locks) {
        return false;
    }
    
    @Override
    public boolean hasPermission(final Set<String> userPermissions) {
        return true;
    }
    
    @Override
    public boolean isPersistent() {
        return false;
    }
    
    @Override
    public List<Mutex> lock(final Map<Mutex, Integer> locks) {
        final List<Mutex> l = new ArrayList<Mutex>();
        return l;
    }
    
    @Override
    public String toString() {
        return "NullAccessGroup";
    }
}
