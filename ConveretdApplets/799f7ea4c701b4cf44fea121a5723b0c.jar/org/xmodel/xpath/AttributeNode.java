// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath;

import org.xmodel.ModelListener;
import org.xmodel.A.B;
import java.util.Set;
import java.util.Iterator;
import org.xmodel.F;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.xmodel.IPathListener;
import org.xmodel.IPath;
import org.xmodel.IModelListener;
import org.xmodel.IAncestorListener;
import org.xmodel.IModel;
import org.xmodel.C;
import org.xmodel.IModelObject;

public class AttributeNode implements IModelObject
{
    String F;
    IModelObject G;
    C E;
    
    public AttributeNode(final String f, final IModelObject g) {
        this.F = f;
        this.G = g;
    }
    
    @Override
    public IModel getModel() {
        return this.G.getModel();
    }
    
    @Override
    public String getID() {
        return "";
    }
    
    @Override
    public void setID(final String s) {
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public IModelObject getChild(final int n) {
        return null;
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        return null;
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
        this.G.addModelListener(new _A(this, modelListener));
    }
    
    public void addPathListener(final IPath path, final IPathListener pathListener) {
    }
    
    @Override
    public IModelObject cloneObject() {
        return new AttributeNode(this.F, this.G);
    }
    
    @Override
    public IModelObject cloneTree() {
        return this.cloneObject();
    }
    
    @Override
    public IModelObject createObject(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject getReferent() {
        return this;
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        return null;
    }
    
    @Override
    public Object getAttribute(final String s) {
        if (s.length() == 0) {
            return this.G.getAttribute(this.F);
        }
        return null;
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        final ArrayList<String> list = new ArrayList<String>(1);
        list.add("");
        return list;
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public List<IModelObject> getChildren() {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        return Collections.emptyList();
    }
    
    @Override
    public Object getValue() {
        return this.G.getAttribute(this.F);
    }
    
    @Override
    public Object getChildValue(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        return null;
    }
    
    @Override
    public F getModelListeners() {
        final F modelListeners = this.G.getModelListeners();
        if (modelListeners == null) {
            return null;
        }
        F f = null;
        for (final IModelListener modelListener : modelListeners.F()) {
            if (modelListener instanceof _A) {
                final _A a = (_A)modelListener;
                if (!a.e.equals(this)) {
                    continue;
                }
                if (f == null) {
                    f = new F();
                }
                f.A(a.d);
            }
        }
        return f;
    }
    
    @Override
    public C getPathListeners() {
        if (this.E == null) {
            this.E = new C();
        }
        return this.E;
    }
    
    @Override
    public int getNumberOfChildren() {
        return 0;
    }
    
    @Override
    public int getNumberOfChildren(final String s) {
        return 0;
    }
    
    @Override
    public IModelObject getParent() {
        return this.G;
    }
    
    @Override
    public IModelObject getRoot() {
        return this.G.getRoot();
    }
    
    @Override
    public String getType() {
        return this.F;
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        return Collections.emptySet();
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject modelObject) {
        return null;
    }
    
    @Override
    public void internal_notifyParent(final IModelObject modelObject, final IModelObject modelObject2) {
    }
    
    @Override
    public void internal_notifyAddChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public void internal_notifyRemoveChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public void internal_addChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        return null;
    }
    
    @Override
    public boolean isDirty() {
        return false;
    }
    
    @Override
    public boolean isType(final String s) {
        return s.equals(this.F);
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public Object removeAttribute(final String s) {
        return null;
    }
    
    @Override
    public void removeChild(final IModelObject modelObject) {
    }
    
    @Override
    public void removeChildren() {
    }
    
    @Override
    public void removeChildren(final String s) {
    }
    
    @Override
    public void removeFromParent() {
        this.G.removeAttribute(this.F);
    }
    
    @Override
    public void removeModelListener(final IModelListener modelListener) {
        this.G.removeModelListener(new _A(this, modelListener));
    }
    
    public void removePathListener(final IPath path, final IPathListener pathListener) {
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        if (s.length() == 0) {
            return this.G.setAttribute(this.F, o);
        }
        return null;
    }
    
    @Override
    public Object setAttribute(final String s) {
        if (s.length() == 0) {
            return this.setValue("");
        }
        return null;
    }
    
    @Override
    public Object setValue(final Object o) {
        return this.G.setAttribute(this.F, o);
    }
    
    @Override
    public void revertUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void restoreUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AttributeNode) {
            final AttributeNode attributeNode = (AttributeNode)o;
            final IModelObject g = this.G;
            final IModelObject g2 = attributeNode.G;
            return this.F.equals(attributeNode.F) && g.equals(g2);
        }
        if (o instanceof AttributeHistoryNode) {
            return this.F.equals(((AttributeHistoryNode)o).A);
        }
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return this.F.hashCode() ^ this.G.hashCode();
    }
    
    @Override
    public String toString() {
        final Object attribute = this.G.getAttribute(this.F);
        return (attribute == null) ? "" : attribute.toString();
    }
    
    private class _A extends ModelListener
    {
        AttributeNode e;
        IModelListener d;
        
        public _A(final AttributeNode e, final IModelListener d) {
            this.e = e;
            this.d = d;
        }
        
        @Override
        public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
            if (s.equals(this.e.F)) {
                this.d.notifyChange(this.e, "", o, o2);
            }
        }
        
        @Override
        public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
            if (s.equals(this.e.F)) {
                this.d.notifyClear(this.e, "", o);
            }
        }
        
        @Override
        public void notifyDirty(final IModelObject modelObject, final boolean b) {
            if (b) {
                modelObject.getAttribute(this.e.F);
            }
        }
        
        @Override
        public int hashCode() {
            return this.e.hashCode() + this.d.hashCode();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof _A) {
                final _A a = (_A)o;
                return this.e.equals(a.e) && this.d.equals(a.d);
            }
            return super.equals(o);
        }
    }
}
