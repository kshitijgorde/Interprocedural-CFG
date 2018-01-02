// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.ModelRegistry;
import org.xmodel.IModel;
import java.net.URI;
import org.xmodel.xml.XmlException;
import org.xmodel.F;
import java.util.Iterator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.ModelAlgorithms;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.ModelObjectFactory;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.xml.XmlIO;
import org.xmodel.IModelObjectFactory;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.diff.IXmlDiffer;
import org.xmodel.xml.IXmlIO;

public abstract class AbstractCachingPolicy implements ICachingPolicy
{
    private ICache B;
    private IXmlIO E;
    private IXmlDiffer D;
    private String[] C;
    private List<_A> G;
    private List<IModelObject> F;
    private IModelObjectFactory A;
    
    protected AbstractCachingPolicy() {
        this(new UnboundedCache());
    }
    
    protected AbstractCachingPolicy(final ICache b) {
        this.B = b;
        this.E = new XmlIO();
        this.D = new XmlDiffer();
        this.C = new String[] { "id" };
        this.A = new ModelObjectFactory();
    }
    
    @Override
    public ICache getCache() {
        return this.B;
    }
    
    @Override
    public void setFactory(final IModelObjectFactory a) {
        this.A = a;
    }
    
    @Override
    public IModelObjectFactory getFactory() {
        return this.A;
    }
    
    public void setDiffer(final IXmlDiffer d) {
        this.D = d;
    }
    
    public IXmlDiffer getDiffer() {
        return this.D;
    }
    
    @Override
    public void defineNextStage(final IExpression d, final ICachingPolicy b, final boolean c) {
        final _A a = new _A((_A)null);
        a.D = d;
        a.B = b;
        a.C = c;
        if (this.G == null) {
            this.G = new ArrayList<_A>(1);
        }
        this.G.add(a);
    }
    
    @Override
    public void defineNextStage(final IModelObject modelObject) {
        if (this.F == null) {
            this.F = new ArrayList<IModelObject>(1);
        }
        this.F.add(modelObject);
    }
    
    @Override
    public IExternalReference createExternalTree(final IModelObject modelObject, final boolean dirty, final IExternalReference externalReference) {
        final IExternalReference externalReference2 = (IExternalReference)externalReference.createObject(modelObject.getType());
        if (dirty) {
            String[] staticAttributes;
            for (int length = (staticAttributes = this.getStaticAttributes()).length, i = 0; i < length; ++i) {
                final String s = staticAttributes[i];
                if (!s.contains("*")) {
                    externalReference2.setAttribute(s, modelObject.getAttribute(s));
                }
                else {
                    ModelAlgorithms.copyAttributes(modelObject, externalReference2);
                }
            }
        }
        else {
            ModelAlgorithms.copyAttributes(modelObject, externalReference2);
            ModelAlgorithms.moveChildren(modelObject, externalReference2);
            this.applyNextStages(externalReference2, externalReference);
        }
        externalReference2.setCachingPolicy(this);
        externalReference2.setDirty(dirty);
        return externalReference2;
    }
    
    protected void applyNextStages(final IModelObject modelObject, final IExternalReference externalReference) {
        final Context context = new Context(modelObject);
        if (this.G != null) {
            for (final _A a : this.G) {
                for (final IModelObject modelObject2 : a.D.evaluateNodes(context)) {
                    ModelAlgorithms.substitute(modelObject2, a.B.createExternalTree(modelObject2, a.C, externalReference));
                }
            }
        }
        if (this.F != null) {
            final Iterator<IModelObject> iterator3 = this.F.iterator();
            while (iterator3.hasNext()) {
                modelObject.addChild(ModelAlgorithms.cloneExternalTree(iterator3.next(), null));
            }
        }
    }
    
    protected void markNextStages(final IExternalReference externalReference) {
        if (this.G == null) {
            return;
        }
        final Context context = new Context(externalReference);
        for (final _A a : this.G) {
            if (a.C) {
                for (final IModelObject modelObject : a.D.evaluateNodes(context)) {
                    ((IExternalReference)modelObject).setDirty(false);
                    final IModelObject parent = modelObject.getParent();
                    final int index = parent.getChildren().indexOf(modelObject);
                    modelObject.removeFromParent();
                    ((IExternalReference)modelObject).setDirty(true);
                    parent.addChild(modelObject, index);
                }
            }
        }
    }
    
    protected void markCleanNextStages(final IExternalReference externalReference) {
        final NonSyncingIterator nonSyncingIterator = new NonSyncingIterator(externalReference);
        while (nonSyncingIterator.hasNext()) {
            final IModelObject dereference = ModelAlgorithms.dereference(nonSyncingIterator.next());
            if (dereference.isDirty()) {
                ((IExternalReference)dereference).setDirty(false);
            }
        }
    }
    
    @Override
    public ITransaction transaction() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear(final IExternalReference externalReference) throws CachingException {
        if (externalReference.isDirty()) {
            return;
        }
        this.markCleanNextStages(externalReference);
        final F modelListeners = externalReference.getModelListeners();
        if (modelListeners != null && modelListeners.G() > 0) {
            externalReference.removeChildren();
            this.sync(externalReference);
        }
        else {
            externalReference.removeChildren();
            externalReference.setDirty(true);
        }
    }
    
    public void flush(final IExternalReference externalReference) throws CachingException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void insert(final IExternalReference externalReference, final String s, final int n, final boolean b) throws CachingException {
        try {
            this.insert(externalReference, this.E.read(s), -1, b);
        }
        catch (XmlException ex) {
            throw new CachingException("Unable to insert entity: " + s, ex);
        }
    }
    
    @Override
    public void remove(final IExternalReference externalReference, final String s) throws CachingException {
        try {
            this.remove(externalReference, this.E.read(s));
        }
        catch (XmlException ex) {
            throw new CachingException("Unable to remove entity: " + s, ex);
        }
    }
    
    @Override
    public void update(final IExternalReference externalReference, final String s) throws CachingException {
        try {
            this.update(externalReference, this.E.read(s));
        }
        catch (XmlException ex) {
            throw new CachingException("Unable to update entity: " + s, ex);
        }
    }
    
    @Override
    public void insert(final IExternalReference externalReference, final IModelObject modelObject, final int n, final boolean dirty) throws CachingException {
        final IModelObject cloneObject = externalReference.cloneObject();
        cloneObject.addChild(modelObject);
        this.applyNextStages(cloneObject, externalReference);
        final IModelObject child = cloneObject.getChild(0);
        if (child instanceof IExternalReference) {
            ((IExternalReference)child).setDirty(dirty);
        }
        if (n >= 0) {
            externalReference.addChild(child, n);
        }
        else {
            externalReference.addChild(child);
        }
    }
    
    @Override
    public void update(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
        this.applyNextStages(modelObject, externalReference);
        final boolean syncLock = externalReference.getModel().getSyncLock();
        try {
            externalReference.getModel().setSyncLock(true);
            this.D.diffAndApply(externalReference, modelObject);
        }
        finally {
            externalReference.getModel().setSyncLock(syncLock);
        }
        externalReference.getModel().setSyncLock(syncLock);
    }
    
    @Override
    public void remove(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
        final IModelObject fastSimpleMatch = ModelAlgorithms.findFastSimpleMatch(externalReference.getChildren(), modelObject);
        if (fastSimpleMatch != null) {
            fastSimpleMatch.removeFromParent();
        }
    }
    
    @Override
    public String[] getStaticAttributes() {
        return this.C;
    }
    
    @Override
    public void readAttributeAccess(final IExternalReference externalReference, final String s) {
        if (!this.isStaticAttribute(s)) {
            if (this.B != null) {
                this.B.touch(externalReference);
            }
            if (externalReference.isDirty()) {
                this.internal_sync(externalReference);
            }
        }
    }
    
    @Override
    public void readChildrenAccess(final IExternalReference externalReference) {
        if (this.B != null) {
            this.B.touch(externalReference);
        }
        if (externalReference.isDirty()) {
            this.internal_sync(externalReference);
        }
    }
    
    @Override
    public void writeAttributeAccess(final IExternalReference externalReference, final String s) {
        if (!this.isStaticAttribute(s)) {
            if (this.B != null) {
                this.B.touch(externalReference);
            }
            if (externalReference.isDirty()) {
                this.internal_sync(externalReference);
            }
        }
    }
    
    @Override
    public void writeChildrenAccess(final IExternalReference externalReference) {
        if (this.B != null) {
            this.B.touch(externalReference);
        }
        if (externalReference.isDirty()) {
            this.internal_sync(externalReference);
        }
    }
    
    @Override
    public URI getURI(final IExternalReference externalReference) throws CachingException {
        return null;
    }
    
    protected void setStaticAttributes(final String[] c) {
        this.C = c;
    }
    
    public boolean isStaticAttribute(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.C.length; ++i) {
            final String s2 = this.C[i];
            if (s2.endsWith(":*")) {
                if (s.startsWith(s2.substring(0, s2.length() - 2))) {
                    return true;
                }
            }
            else if ((s2.equals("*") && s.length() > 0) || s.equals(s2)) {
                return true;
            }
        }
        return false;
    }
    
    protected void internal_sync(final IExternalReference externalReference) throws CachingException {
        final IModel model = externalReference.getModel();
        if (model.getSyncLock()) {
            return;
        }
        externalReference.setDirty(false);
        final boolean b = model.isLocked(externalReference) != null;
        model.unlock(externalReference);
        try {
            this.sync(externalReference);
            if (this.B != null) {
                this.B.add(externalReference);
            }
        }
        catch (CachingException ex) {
            externalReference.setDirty(true);
            throw ex;
        }
        finally {
            if (b) {
                model.lock(externalReference);
            }
        }
        if (b) {
            model.lock(externalReference);
        }
    }
    
    @Override
    public String toString(final String s) {
        final IModel model = ModelRegistry.getInstance().getModel();
        final boolean syncLock = model.getSyncLock();
        model.setSyncLock(true);
        try {
            return this.getClass().getSimpleName();
        }
        finally {
            model.setSyncLock(syncLock);
        }
    }
    
    @Override
    public String toString() {
        return this.toString("");
    }
    
    private class _A
    {
        IExpression D;
        ICachingPolicy B;
        boolean C;
    }
}
