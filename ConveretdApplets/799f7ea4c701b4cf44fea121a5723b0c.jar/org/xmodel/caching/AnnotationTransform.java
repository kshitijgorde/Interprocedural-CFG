// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.ICache;
import org.xmodel.IBoundChangeRecord;
import org.xmodel.IChangeSet;
import org.xmodel.UnionChangeSet;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.xpath.expression.Context;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ConfiguredCachingPolicy;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.Xlate;
import org.xmodel.ModelAlgorithms;
import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.ModelObjectFactory;
import org.xmodel.xpath.XPath;
import org.xmodel.IModelObjectFactory;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpression;

public class AnnotationTransform
{
    private final IExpression G;
    private final IExpression D;
    private final IExpression C;
    private static Log F;
    private ClassLoader A;
    private IContext E;
    private IModelObjectFactory B;
    
    static {
        AnnotationTransform.F = Log.getLog("org.xmodel.external.caching");
    }
    
    public AnnotationTransform() {
        this.G = XPath.createExpression("ancestor-or-self::*/package");
        this.D = XPath.createExpression("descendant-or-self::extern:cache[ @extends]");
        this.C = XPath.createExpression("reverse( descendant-or-self::*[ extern:cache])");
        this.A = this.getClass().getClassLoader();
        this.B = new ModelObjectFactory();
    }
    
    public void setClassLoader(final ClassLoader a) {
        this.A = a;
    }
    
    public void setFactory(final IModelObjectFactory b) {
        this.B = b;
    }
    
    public void setParentContext(final IContext e) {
        this.E = e;
    }
    
    public IModelObject transform(final IModelObject modelObject) {
        IModelObject modelObject2 = modelObject;
        this.A(modelObject);
        for (final IModelObject modelObject3 : this.C.query(modelObject, null)) {
            final IModelObject firstChild = modelObject3.getFirstChild("extern:cache");
            if (modelObject3.isType("extern:match")) {
                modelObject3.setAttribute("extern:cp", this.A(firstChild, modelObject3.getChildren()));
            }
            else {
                final IModelObject transform = this.transform(modelObject3, firstChild);
                final IModelObject parent = modelObject3.getParent();
                if (parent != null) {
                    final int index = parent.getChildren().indexOf(modelObject3);
                    modelObject3.removeFromParent();
                    parent.addChild(transform, index);
                }
                if (modelObject3 == modelObject) {
                    modelObject2 = transform;
                }
            }
            firstChild.removeFromParent();
        }
        return modelObject2;
    }
    
    public IModelObject transform(final IModelObject modelObject, final IModelObject modelObject2) {
        final ConfiguredCachingPolicy a = this.A(modelObject2, modelObject.getChildren());
        if (a == null) {
            throw new IllegalArgumentException("Caching policy not found.");
        }
        final IExternalReference externalObject = this.B.createExternalObject(null, modelObject.getType());
        ModelAlgorithms.copyAttributes(modelObject, externalObject);
        final boolean value = Xlate.get(modelObject2, "dirty", true);
        externalObject.setCachingPolicy(a);
        externalObject.setDirty(value);
        return externalObject;
    }
    
    private void A(final IModelObject modelObject) {
        final Iterator<IModelObject> iterator = this.D.query(modelObject, null).iterator();
        while (iterator.hasNext()) {
            this.B(iterator.next());
        }
    }
    
    private void B(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "extends", (String)null);
        if (value == null) {
            return;
        }
        modelObject.removeAttribute("extends");
        final IModelObject cloneTree = XPath.createExpression(value).queryFirst(new Context(this.E, modelObject)).cloneTree();
        this.A(cloneTree);
        final XmlDiffer xmlDiffer = new XmlDiffer();
        final UnionChangeSet set = new UnionChangeSet();
        xmlDiffer.diff(modelObject, cloneTree, set);
        for (final IBoundChangeRecord boundChangeRecord : set.getRecords()) {
            final IModelObject boundObject = boundChangeRecord.getBoundObject();
            if (boundChangeRecord.isType(0) && boundObject.getAttribute(boundChangeRecord.getAttributeName()) != null) {
                continue;
            }
            boundChangeRecord.applyChange();
        }
    }
    
    private ConfiguredCachingPolicy A(final IModelObject modelObject, final List<IModelObject> list) {
        final String value = Xlate.get(modelObject, "class", (String)null);
        if (value == null) {
            return null;
        }
        final ConfiguredCachingPolicy a = this.A(this.A(Xlate.get(modelObject, "cache", "org.xmodel.external.UnboundedCache")), modelObject, value);
        if (a != null) {
            a.configure(this.E, modelObject);
            for (final IModelObject modelObject2 : modelObject.getChildren("extern:match")) {
                final IExpression value2 = Xlate.get(modelObject2, "path", (IExpression)null);
                final boolean value3 = Xlate.get(modelObject2, "dirty", true);
                ConfiguredCachingPolicy configuredCachingPolicy = (ConfiguredCachingPolicy)modelObject2.getAttribute("extern:cp");
                if (configuredCachingPolicy == null) {
                    configuredCachingPolicy = a;
                }
                a.defineNextStage(value2, configuredCachingPolicy, value3);
                modelObject2.removeFromParent();
            }
            for (final IModelObject modelObject3 : list) {
                if (!modelObject3.isType("extern:cache") && !modelObject3.isType("extern:match")) {
                    a.defineNextStage(modelObject3);
                }
            }
            return a;
        }
        return null;
    }
    
    private ConfiguredCachingPolicy A(final ICache cache, final IModelObject modelObject, final String s) {
        if (!s.contains(".")) {
            final Iterator<IModelObject> iterator = this.G.query(new Context(this.E, modelObject), null).iterator();
            while (iterator.hasNext()) {
                final ConfiguredCachingPolicy a = this.A(cache, Xlate.get((IModelObject)iterator.next(), ""), s);
                if (a != null) {
                    return a;
                }
            }
        }
        else {
            final ConfiguredCachingPolicy a2 = this.A(cache, (String)null, s);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }
    
    private ConfiguredCachingPolicy A(final ICache cache, final String s, String string) {
        if (s != null) {
            string = String.valueOf(s) + "." + string;
        }
        try {
            final ConfiguredCachingPolicy configuredCachingPolicy = (ConfiguredCachingPolicy)this.A.loadClass(string).getConstructor(ICache.class).newInstance(cache);
            configuredCachingPolicy.setFactory(this.B);
            return configuredCachingPolicy;
        }
        catch (Exception ex) {
            AnnotationTransform.F.exception(ex);
            return null;
        }
    }
    
    private ICache A(final String s) {
        try {
            return (ICache)this.A.loadClass(s).newInstance();
        }
        catch (Exception ex) {
            AnnotationTransform.F.exception(ex);
            return null;
        }
    }
}
