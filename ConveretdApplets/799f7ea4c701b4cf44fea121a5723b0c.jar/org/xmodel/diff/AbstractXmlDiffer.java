// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.Iterator;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;

public abstract class AbstractXmlDiffer implements IXmlDiffer
{
    protected IXmlMatcher matcher;
    private int A;
    
    public AbstractXmlDiffer() {
        this.matcher = new DefaultXmlMatcher();
        this.A = 0;
    }
    
    @Override
    public void setMatcher(final IXmlMatcher matcher) {
        this.matcher = matcher;
    }
    
    @Override
    public IXmlMatcher getMatcher() {
        return this.matcher;
    }
    
    @Override
    public boolean diff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        try {
            if (this.A++ == 0) {
                this.matcher.startDiff(modelObject, modelObject2, set);
            }
            this.matcher.enterDiff(modelObject, modelObject2, set);
            if (set != null) {
                final int size = set.getSize();
                this.diffAttributes(modelObject, modelObject2, set);
                this.internal_diffChildren(modelObject, modelObject2, set);
                return set.getSize() == size;
            }
            return this.diffAttributes(modelObject, modelObject2, set) && this.internal_diffChildren(modelObject, modelObject2, set);
        }
        finally {
            this.matcher.exitDiff(modelObject, modelObject2, set);
            final int a = this.A - 1;
            this.A = a;
            if (a == 0) {
                this.matcher.endDiff(modelObject, modelObject2, set);
            }
        }
    }
    
    @Override
    public boolean diffAndApply(final IModelObject modelObject, final IModelObject modelObject2) {
        final RegularChangeSet set = new RegularChangeSet();
        final boolean diff = this.diff(modelObject, modelObject2, set);
        set.applyChanges();
        return diff;
    }
    
    @Override
    public boolean diffAttributes(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        final int n = (set != null) ? set.getSize() : 0;
        if (this.matcher.shouldDiff(modelObject, null, true)) {
            for (final String s : modelObject.getAttributeNames()) {
                if (!this.matcher.shouldDiff(modelObject, s, true)) {
                    continue;
                }
                final Object attribute = modelObject2.getAttribute(s);
                if (attribute == null) {
                    if (set == null) {
                        return false;
                    }
                    set.removeAttribute(modelObject, s);
                }
                else {
                    if (modelObject.getAttribute(s).equals(attribute)) {
                        continue;
                    }
                    if (set == null) {
                        return false;
                    }
                    set.setAttribute(modelObject, s, attribute);
                }
            }
        }
        if (this.matcher.shouldDiff(modelObject2, null, false)) {
            for (final String s2 : modelObject2.getAttributeNames()) {
                if (!this.matcher.shouldDiff(modelObject2, s2, false)) {
                    continue;
                }
                final Object attribute2 = modelObject2.getAttribute(s2);
                if (modelObject.getAttribute(s2) != null) {
                    continue;
                }
                if (set == null) {
                    return false;
                }
                set.setAttribute(modelObject, s2, attribute2);
            }
        }
        return set == null || set.getSize() == n;
    }
    
    @Override
    public boolean diffChildren(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        return this.internal_diffChildren(modelObject, modelObject2, set);
    }
    
    protected boolean internal_diffChildren(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        final int n = (set != null) ? set.getSize() : 0;
        if (this.matcher.shouldDiff(modelObject, true) && this.matcher.shouldDiff(modelObject2, false)) {
            if (this.matcher.isList(modelObject)) {
                final boolean diffList = this.diffList(modelObject, modelObject2, set);
                if (set == null && !diffList) {
                    return false;
                }
            }
            else {
                final boolean diffSet = this.diffSet(modelObject, modelObject2, set);
                if (set == null && !diffSet) {
                    return false;
                }
            }
        }
        return set == null || set.getSize() == n;
    }
    
    protected abstract boolean diffSet(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    protected abstract boolean diffList(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
}
