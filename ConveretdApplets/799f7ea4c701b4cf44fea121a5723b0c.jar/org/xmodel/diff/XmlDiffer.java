// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import org.xmodel.ModelAlgorithms;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;

public class XmlDiffer extends AbstractXmlDiffer
{
    IModelObjectFactory B;
    _A C;
    
    public XmlDiffer() {
        this.C = new _A((_A)null);
    }
    
    public XmlDiffer(final IXmlMatcher matcher) {
        this();
        this.setMatcher(matcher);
    }
    
    public XmlDiffer(final IModelObjectFactory factory) {
        this();
        this.setFactory(factory);
    }
    
    public void setFactory(final IModelObjectFactory b) {
        this.B = b;
    }
    
    public IModelObjectFactory getFactory() {
        return this.B;
    }
    
    @Override
    protected boolean diffSet(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        final IXmlMatcher matcher = this.getMatcher();
        final ArrayList<Object> list = new ArrayList<Object>(modelObject2.getChildren());
        final List<IModelObject> children = modelObject.getChildren();
        final int n = (set != null) ? set.getSize() : 0;
        final ListIterator<IModelObject> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (!matcher.shouldDiff(listIterator.next(), false)) {
                listIterator.remove();
            }
        }
        int n2 = 0;
        for (final IModelObject modelObject3 : children) {
            if (matcher.shouldDiff(modelObject3, true)) {
                final int match = matcher.findMatch((List<IModelObject>)list, modelObject3);
                if (match >= 0) {
                    if (!this.diff(modelObject3, list.get(match), set) && set == null) {
                        return false;
                    }
                    list.remove(match);
                    ++n2;
                }
                else {
                    if (set == null) {
                        return false;
                    }
                    set.removeChild(modelObject, modelObject3, n2);
                }
            }
        }
        if (set == null && list.size() > 0) {
            return false;
        }
        final Iterator<IModelObject> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            set.addChild(modelObject, this.getFactoryClone(iterator2.next()));
        }
        return set == null || set.getSize() == n;
    }
    
    @Override
    protected boolean diffList(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        if (set == null) {
            return this.compareList(modelObject, modelObject2);
        }
        final int size = set.getSize();
        this.C.A(modelObject, modelObject2, set);
        return set.getSize() == size;
    }
    
    protected boolean compareList(final IModelObject modelObject, final IModelObject modelObject2) {
        final List<IModelObject> children = modelObject.getChildren();
        final List<IModelObject> children2 = modelObject2.getChildren();
        if (children.size() != children2.size()) {
            return false;
        }
        for (int i = 0; i < children.size(); ++i) {
            if (!this.diff(children.get(i), children2.get(i), null)) {
                return false;
            }
        }
        return true;
    }
    
    public IModelObject getFactoryClone(final IModelObject modelObject) {
        if (this.B != null) {
            return ModelAlgorithms.cloneTree(modelObject, this.B);
        }
        return modelObject;
    }
    
    private class _A extends AbstractListDiffer
    {
        IChangeSet I;
        IModelObject H;
        IModelObject J;
        
        public void A(final IModelObject h, final IModelObject j, final IChangeSet i) {
            this.I = i;
            this.H = h;
            this.J = j;
            this.diff(h.getChildren(), j.getChildren());
        }
        
        @Override
        public boolean isMatch(final Object o, final Object o2) {
            return XmlDiffer.this.matcher.isMatch((IModelObject)o, (IModelObject)o2);
        }
        
        @Override
        public void notifyEqual(final List list, int n, final int n2, final List list2, int n3, final int n4) {
            final IModelObject h = this.H;
            final IModelObject j = this.J;
            for (int i = 0; i < n4; ++i) {
                XmlDiffer.this.diff(h.getChild(n++), j.getChild(n3++), this.I);
            }
            this.H = h;
            this.J = j;
        }
        
        @Override
        public void notifyInsert(final List list, final int n, final int n2, final List list2, final int n3, final int n4) {
            for (int i = 0; i < n4; ++i) {
                this.I.addChild(this.H, XmlDiffer.this.getFactoryClone(this.J.getChild(n3 + i)), n3 + i);
            }
        }
        
        @Override
        public void notifyRemove(final List list, final int n, final int n2, final List list2, final int n3) {
            for (int i = 0; i < n3; ++i) {
                this.I.removeChild(this.H, this.H.getChild(n + i), n + n2);
            }
        }
    }
}
