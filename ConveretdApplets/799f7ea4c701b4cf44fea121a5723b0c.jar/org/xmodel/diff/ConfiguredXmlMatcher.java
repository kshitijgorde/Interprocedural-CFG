// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import org.xmodel.IModelObject;
import java.util.Set;

public class ConfiguredXmlMatcher extends DefaultXmlMatcher
{
    private Set<IModelObject> J;
    private Set<IModelObject> K;
    
    public ConfiguredXmlMatcher() {
        this.J = new HashSet<IModelObject>();
        this.K = new HashSet<IModelObject>();
    }
    
    public void ignore(final IModelObject modelObject) {
        this.J.add(modelObject);
    }
    
    public void regard(final IModelObject modelObject) {
        this.J.remove(modelObject);
    }
    
    public void ignore(final List<IModelObject> list) {
        this.J.addAll(list);
    }
    
    public void regard(final List<IModelObject> list) {
        this.J.removeAll(list);
    }
    
    public void setOrdered(final IModelObject modelObject) {
        this.K.add(modelObject);
    }
    
    public void setUnordered(final IModelObject modelObject) {
        this.K.remove(modelObject);
    }
    
    public void setOrdered(final List<IModelObject> list) {
        this.K.addAll(list);
    }
    
    public void setUnordered(final List<IModelObject> list) {
        this.K.removeAll(list);
    }
    
    @Override
    public boolean isList(final IModelObject modelObject) {
        return (this.K != null && this.K.contains(modelObject)) || super.isList(modelObject);
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final boolean b) {
        if (this.J != null) {
            return !this.J.contains(modelObject);
        }
        return super.shouldDiff(modelObject, b);
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final String s, final boolean b) {
        if (this.J == null) {
            return super.shouldDiff(modelObject, s, b);
        }
        if (s == null) {
            return !this.J.contains(null);
        }
        return !this.J.contains(modelObject.getAttributeNode(s));
    }
}
