// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.A.B;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Collection;
import org.xmodel.xpath.AttributeNode;
import org.xmodel.log.Log;
import java.util.Map;
import java.util.List;

public class Element implements IModelObject
{
    private static final IModelObjectFactory S;
    private IModel T;
    private String X;
    private IModelObject W;
    private List<IModelObject> U;
    private Map<String, Object> R;
    private static Log V;
    
    static {
        S = new ElementFactory();
        Element.V = Log.getLog("org.xmodel");
    }
    
    public Element(final String s) {
        this.X = s.intern();
    }
    
    @Override
    public IModel getModel() {
        if (this.T == null) {
            this.T = ModelRegistry.getInstance().getModel();
        }
        return this.T;
    }
    
    @Override
    public void setID(final String s) {
        this.writeAttributeAccess("id");
        this.setAttribute("id", s);
    }
    
    @Override
    public String getID() {
        this.readAttributeAccess("id");
        return Xlate.get((IModelObject)this, "id", "");
    }
    
    @Override
    public String getType() {
        return this.X;
    }
    
    @Override
    public boolean isType(final String s) {
        return this.X.equals(s);
    }
    
    @Override
    public boolean isDirty() {
        return false;
    }
    
    @Override
    public Object setAttribute(final String s) {
        return this.setAttribute(s, "");
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        this.writeAttributeAccess(s);
        final Object attribute = this.getAttribute(s);
        if (attribute != null && o != null && attribute.equals(o)) {
            return attribute;
        }
        if (this.W != null && s.equals("id")) {
            Element.V.warnf("LATE-ID: %s, id=%s", this, o);
        }
        if (o == null) {
            this.removeAttribute(s);
            return attribute;
        }
        this.setAttributeImpl(s, o);
        return attribute;
    }
    
    @Override
    public Object getAttribute(final String s) {
        this.readAttributeAccess(s);
        if (this.R == null) {
            return null;
        }
        return this.R.get(s);
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        if (this.R != null && !this.R.containsKey(s)) {
            return null;
        }
        return new AttributeNode(s, this);
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        this.readAttributeAccess(null);
        if (this.R == null) {
            return (Collection<String>)Collections.emptyList();
        }
        return this.R.keySet();
    }
    
    @Override
    public Object removeAttribute(final String s) {
        this.writeAttributeAccess(s);
        final Object attribute = this.getAttribute(s);
        if (attribute == null) {
            return null;
        }
        this.removeAttributeImpl(s);
        return attribute;
    }
    
    @Override
    public Object setValue(final Object o) {
        return this.setAttribute("", o);
    }
    
    @Override
    public Object getValue() {
        return this.getAttribute("");
    }
    
    @Override
    public Object getChildValue(final String s) {
        final IModelObject firstChild = this.getFirstChild(s);
        if (firstChild != null) {
            return firstChild.getValue();
        }
        return null;
    }
    
    protected Object setAttributeImpl(final String s, final Object o) {
        if (this.R == null) {
            this.R = new HashMap<String, Object>();
        }
        return this.R.put(s.intern(), o);
    }
    
    protected Object removeAttributeImpl(final String s) {
        if (this.R == null) {
            return null;
        }
        return this.R.remove(s);
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
        this.addChild(modelObject, (this.U != null) ? this.U.size() : 0);
    }
    
    @Override
    public void addChild(final IModelObject modelObject, int n) {
        if (modelObject == this) {
            throw new IllegalArgumentException();
        }
        this.writeChildrenAccess();
        final IModelObject parent = modelObject.getParent();
        if (parent == this) {
            final int index = this.getChildren().indexOf(modelObject);
            if (this.removeChildImpl(index) != null && index < n) {
                --n;
            }
            this.addChildImpl(modelObject, n);
        }
        else {
            modelObject.internal_setParent(this);
            if (parent != null) {
                parent.internal_removeChild(parent.getChildren().indexOf(modelObject));
            }
            this.addChildImpl(modelObject, n);
        }
    }
    
    protected void addChildImpl(final IModelObject modelObject, final int n) {
        if (this.U == null) {
            this.U = new ArrayList<IModelObject>(1);
        }
        if (n >= 0) {
            this.U.add(n, modelObject);
        }
        else {
            this.U.add(modelObject);
        }
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        this.writeChildrenAccess();
        if (this.U == null) {
            return null;
        }
        final IModelObject modelObject = this.U.get(n);
        if (modelObject != null) {
            this.removeChildImpl(n);
            modelObject.internal_setParent(null);
        }
        return modelObject;
    }
    
    @Override
    public void removeChild(final IModelObject modelObject) {
        this.writeChildrenAccess();
        if (this.U == null) {
            return;
        }
        final int index = this.U.indexOf(modelObject);
        if (index >= 0) {
            this.removeChildImpl(index);
            modelObject.internal_setParent(null);
        }
    }
    
    protected IModelObject removeChildImpl(final int n) {
        if (this.U == null) {
            return null;
        }
        final IModelObject modelObject = this.U.remove(n);
        if (this.U.size() == 0) {
            this.U = null;
        }
        return modelObject;
    }
    
    @Override
    public void removeChildren() {
        while (this.U != null && this.U.size() > 0) {
            this.removeChild(this.U.size() - 1);
        }
    }
    
    @Override
    public void removeChildren(final String s) {
        if (this.U != null) {
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(this.U.size());
            list.addAll((Collection<?>)this.U);
            for (final IModelObject modelObject : list) {
                if (modelObject.isType(s)) {
                    this.removeChild(modelObject);
                }
            }
        }
    }
    
    @Override
    public void removeFromParent() {
        final IModelObject parent = this.getParent();
        if (parent != null) {
            parent.removeChild(this);
        }
    }
    
    @Override
    public IModelObject getChild(final int n) {
        this.readChildrenAccess();
        if (this.U == null) {
            return null;
        }
        if (n >= this.U.size()) {
            return null;
        }
        return this.U.get(n);
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        this.readChildrenAccess();
        if (this.U != null) {
            for (final IModelObject modelObject : this.U) {
                if (modelObject.isType(s)) {
                    return modelObject;
                }
            }
        }
        return null;
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        this.readChildrenAccess();
        if (this.U != null) {
            for (final IModelObject modelObject : this.U) {
                if (modelObject.isType(s) && modelObject.getID().equals(s2)) {
                    return modelObject;
                }
            }
        }
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        IModelObject firstChild = this.getFirstChild(s);
        if (firstChild == null) {
            firstChild = new Element(s);
            this.addChild(firstChild);
        }
        return firstChild;
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String id) {
        IModelObject child = this.getChild(s, id);
        if (child == null) {
            child = new Element(s);
            child.setID(id);
            this.addChild(child);
        }
        return child;
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        this.readChildrenAccess();
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
        if (this.U != null) {
            for (final IModelObject modelObject : this.U) {
                if (modelObject.isType(s) && modelObject.getID().equals(s2)) {
                    list.add(modelObject);
                }
            }
        }
        return list;
    }
    
    @Override
    public List<IModelObject> getChildren() {
        this.readChildrenAccess();
        List<IModelObject> list = this.U;
        if (this.U == null) {
            list = Collections.emptyList();
        }
        return list;
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        this.readChildrenAccess();
        if (this.U != null) {
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(this.U.size());
            for (final IModelObject modelObject : this.U) {
                if (modelObject.isType(s)) {
                    list.add(modelObject);
                }
            }
            return list;
        }
        return Collections.emptyList();
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        this.readChildrenAccess();
        final HashSet<String> set = new HashSet<String>();
        if (this.U != null) {
            final Iterator<IModelObject> iterator = this.U.iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next().getType());
            }
        }
        return set;
    }
    
    @Override
    public int getNumberOfChildren() {
        this.readChildrenAccess();
        return (this.U == null) ? 0 : this.U.size();
    }
    
    @Override
    public int getNumberOfChildren(final String s) {
        this.readChildrenAccess();
        return this.getChildren(s).size();
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
        this.addChildImpl(modelObject, n);
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        return this.removeChildImpl(n);
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject w) {
        final IModelObject w2 = this.W;
        this.W = w;
        return w2;
    }
    
    @Override
    public IModelObject getParent() {
        return this.W;
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        IModelObject modelObject;
        for (modelObject = this.getParent(); modelObject != null; modelObject = modelObject.getParent()) {
            if (modelObject.isType(s)) {
                return modelObject;
            }
        }
        return modelObject;
    }
    
    @Override
    public IModelObject getRoot() {
        Element element = this;
        for (IModelObject modelObject = element.getParent(); modelObject != null; modelObject = modelObject.getParent()) {
            element = (Element)modelObject;
        }
        return element;
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void removeModelListener(final IModelListener modelListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public F getModelListeners() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public C getPathListeners() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject cloneObject() {
        return Element.S.createClone(this);
    }
    
    @Override
    public IModelObject cloneTree() {
        return ModelAlgorithms.cloneTree(this, Element.S);
    }
    
    @Override
    public IModelObject createObject(final String s) {
        return new Element(s);
    }
    
    @Override
    public IModelObject getReferent() {
        return this;
    }
    
    @Override
    public void revertUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void restoreUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    protected void readAttributeAccess(final String s) {
    }
    
    protected void readChildrenAccess() {
    }
    
    protected void writeAttributeAccess(final String s) {
    }
    
    protected void writeChildrenAccess() {
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('<');
        sb.append(this.getType());
        if (this.R != null) {
            for (final Map.Entry<String, Object> entry : this.R.entrySet()) {
                sb.append(' ');
                sb.append(entry.getKey());
                sb.append("='");
                sb.append(entry.getValue());
                sb.append('\'');
            }
        }
        if (this.U != null && this.U.size() > 0) {
            sb.append(">...</>");
        }
        else {
            sb.append("/>");
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Reference) {
            return o.equals(this);
        }
        return super.equals(o);
    }
}
