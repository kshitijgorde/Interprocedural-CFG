// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.external.IExternalReference;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.P;
import org.xmodel.xpath.expression.I;
import org.xmodel.xpath.function.CollectionFunction;
import java.util.Collections;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.EqualityExpression;
import org.xmodel.xpath.expression.H;
import org.xmodel.xpath.expression.A;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.J;
import org.xmodel.xpath.AttributeNode;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.util.Fifo;
import org.xmodel.xpath.PathElement;
import java.util.Iterator;
import java.util.List;

public class ModelAlgorithms implements E
{
    public static IModelObject findFastSimpleMatch(final List<IModelObject> list, final IModelObject modelObject) {
        final IModelObject dereference = dereference(modelObject);
        for (final IModelObject modelObject2 : list) {
            if (dereference == dereference(modelObject2)) {
                return modelObject2;
            }
        }
        final String id = modelObject.getID();
        if (id.length() > 0) {
            for (final IModelObject modelObject3 : list) {
                if (modelObject3.getID().equals(id)) {
                    return modelObject3;
                }
            }
        }
        else {
            final Object value = modelObject.getValue();
            if (value != null) {
                for (final IModelObject modelObject4 : list) {
                    final Object value2 = modelObject4.getValue();
                    if (value2 != null && value2.equals(value)) {
                        return modelObject4;
                    }
                }
            }
            else {
                int n = 0;
                IModelObject modelObject5 = null;
                final String type = modelObject.getType();
                for (final IModelObject modelObject6 : list) {
                    if (modelObject6.getType().equals(type)) {
                        modelObject5 = modelObject6;
                        if (++n > 1) {
                            break;
                        }
                        continue;
                    }
                }
                if (n == 1) {
                    return modelObject5;
                }
            }
        }
        return null;
    }
    
    public static IModelObject findCommonAncestor(final IModelObject modelObject, final IModelObject modelObject2) {
        IModelObject modelObject3 = null;
        int n = 0;
        for (IModelObject parent = modelObject; parent != null; parent = parent.getParent()) {
            ++n;
        }
        --n;
        int n2 = 0;
        for (IModelObject parent2 = modelObject2; parent2 != null; parent2 = parent2.getParent()) {
            ++n2;
        }
        --n2;
        IModelObject modelObject4 = modelObject;
        IModelObject modelObject5 = modelObject2;
        final int n3 = (n2 < n) ? n2 : n;
        for (int i = n; i >= n3; --i) {
            modelObject4 = modelObject4.getParent();
        }
        for (int j = n2; j >= n3; --j) {
            modelObject5 = modelObject5.getParent();
        }
        for (int k = n3; k >= 0; --k) {
            if (modelObject4 == modelObject5) {
                modelObject3 = modelObject4;
                break;
            }
            modelObject4 = modelObject4.getParent();
            modelObject5 = modelObject5.getParent();
        }
        return modelObject3;
    }
    
    public static CanonicalPath createRelativePath(IModelObject modelObject, final IModelObject modelObject2) {
        if (modelObject == modelObject2) {
            final CanonicalPath canonicalPath = new CanonicalPath();
            canonicalPath.addElement(new PathElement(2));
            return canonicalPath;
        }
        if (isAncestor(modelObject, modelObject2)) {
            final CanonicalPath canonicalPath2 = new CanonicalPath();
            while (modelObject != modelObject2) {
                canonicalPath2.addElement(0, new PathElement(8));
                modelObject = modelObject.getParent();
            }
            return canonicalPath2;
        }
        final IModelObject commonAncestor = findCommonAncestor(modelObject, modelObject2);
        final CanonicalPath canonicalPath3 = new CanonicalPath();
        final CanonicalPath identityPath = createIdentityPath(modelObject2);
        int length = identityPath.length();
        for (IModelObject parent = modelObject2; parent != commonAncestor; parent = parent.getParent()) {
            canonicalPath3.addElement(0, identityPath.getPathElement(--length));
        }
        if (isAncestor(modelObject2, modelObject)) {
            canonicalPath3.removeElement(0);
        }
        else {
            if (commonAncestor == null) {
                return null;
            }
            while (modelObject != commonAncestor) {
                canonicalPath3.addElement(0, new PathElement(8));
                modelObject = modelObject.getParent();
            }
        }
        return canonicalPath3;
    }
    
    public static IModelObject cloneTree(final IModelObject modelObject) {
        return cloneTree(modelObject, null);
    }
    
    public static IModelObject cloneTree(final IModelObject modelObject, final IModelObjectFactory modelObjectFactory) {
        final IModelObject modelObject2 = (modelObjectFactory == null) ? modelObject.cloneObject() : modelObjectFactory.createClone(modelObject);
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject);
        fifo.push(modelObject2);
        while (!fifo.empty()) {
            final IModelObject modelObject3 = fifo.pop();
            final IModelObject modelObject4 = fifo.pop();
            final List<IModelObject> children = modelObject3.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final IModelObject modelObject5 = children.get(i);
                final IModelObject modelObject6 = (modelObjectFactory == null) ? modelObject5.cloneObject() : modelObjectFactory.createClone(modelObject5);
                if (modelObject6 != null) {
                    modelObject4.addChild(modelObject6);
                    fifo.push(modelObject5);
                    fifo.push(modelObject6);
                }
            }
        }
        return modelObject2;
    }
    
    public static IModelObject cloneExternalTree(final IModelObject modelObject, final IModelObjectFactory d) {
        final _A a = new _A(null);
        a.D = d;
        final IModelObject modelObject2 = (a == null) ? modelObject.cloneObject() : a.createClone(modelObject);
        if (modelObject.isDirty()) {
            return modelObject2;
        }
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject);
        fifo.push(modelObject2);
        while (!fifo.empty()) {
            final IModelObject modelObject3 = fifo.pop();
            final IModelObject modelObject4 = fifo.pop();
            final List<IModelObject> children = modelObject3.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final IModelObject modelObject5 = children.get(i);
                final IModelObject modelObject6 = (a == null) ? modelObject5.cloneObject() : a.createClone(modelObject5);
                modelObject4.addChild(modelObject6);
                if (!modelObject5.isDirty()) {
                    fifo.push(modelObject5);
                    fifo.push(modelObject6);
                }
            }
        }
        return modelObject2;
    }
    
    public static void copyAttributes(final IModelObject modelObject, final IModelObject modelObject2) {
        for (final String s : modelObject.getAttributeNames()) {
            if (s.equals("id") && modelObject.getID().length() > 0 && modelObject2.getID().equals(modelObject.getID())) {
                continue;
            }
            modelObject2.setAttribute(s, modelObject.getAttribute(s));
        }
    }
    
    public static void moveChildren(final IModelObject modelObject, final IModelObject modelObject2) {
        final Iterator<IModelObject> iterator = new ArrayList<IModelObject>(modelObject.getChildren()).iterator();
        while (iterator.hasNext()) {
            modelObject2.addChild(iterator.next());
        }
    }
    
    public static void substitute(final IModelObject modelObject, final IModelObject modelObject2) {
        final IModelObject parent = modelObject.getParent();
        if (parent != null) {
            final int index = parent.getChildren().indexOf(modelObject);
            modelObject.removeFromParent();
            parent.addChild(modelObject2, index);
        }
    }
    
    public static CanonicalPath createIdentityPath(IModelObject parent) {
        final CanonicalPath canonicalPath = new CanonicalPath();
        while (parent != null) {
            final IModelObject parent2 = parent.getParent();
            int n = (parent2 == null) ? 1 : 16;
            if (parent instanceof AttributeNode) {
                n = 128;
            }
            final String value = Xlate.get(parent, "id", (String)null);
            if (value != null) {
                final J j = new J(canonicalPath);
                final A a = new A(XPath.createPath("@id"));
                final H h = new H(canonicalPath);
                h.A(value);
                final EqualityExpression equalityExpression = new EqualityExpression(EqualityExpression.Operator.EQ);
                equalityExpression.addArgument(a);
                equalityExpression.addArgument(h);
                j.addArgument(equalityExpression);
                canonicalPath.addElement(0, new PathElement(n, parent.getType(), j));
            }
            else {
                Object o = Collections.emptyList();
                if (parent2 != null) {
                    o = parent2.getChildren(parent.getType());
                }
                if (((List)o).size() > 1 && !(parent instanceof AttributeNode)) {
                    final J i = new J(canonicalPath);
                    final H h2 = new H();
                    h2.A(((List)o).indexOf(parent) + 1);
                    i.addArgument(h2);
                    canonicalPath.addElement(0, new PathElement(n, parent.getType(), i));
                }
                else {
                    canonicalPath.addElement(0, new PathElement(n, parent.getType()));
                }
            }
            parent = parent.getParent();
        }
        return canonicalPath;
    }
    
    public static IExpression createIdentityExpression(final IModelObject modelObject) {
        final IModelObject root = modelObject.getRoot();
        final String type = root.getType();
        final CanonicalPath relativePath = createRelativePath(root, modelObject);
        final CollectionFunction collectionFunction = new CollectionFunction();
        collectionFunction.addArgument(new H(type));
        final I i = new I();
        i.addArgument(collectionFunction);
        i.addArgument(new A(relativePath));
        return new P(i);
    }
    
    public static CanonicalPath createTypePath(IModelObject parent) {
        final CanonicalPath canonicalPath = new CanonicalPath();
        while (parent != null) {
            int n = (parent.getParent() == null) ? 1 : 16;
            if (parent instanceof AttributeNode) {
                n = 128;
            }
            canonicalPath.addElement(0, new PathElement(n, parent.getType()));
            parent = parent.getParent();
        }
        return canonicalPath;
    }
    
    public static CanonicalPath createCandidatePath(final IPath path, final int n) {
        if (n > 0) {
            final CanonicalPath canonicalPath = new CanonicalPath(path, 0, n);
            final B pathElement = path.getPathElement(n);
            canonicalPath.addElement(new PathElement(pathElement.axis(), pathElement.type()));
            return canonicalPath;
        }
        final CanonicalPath canonicalPath2 = new CanonicalPath();
        final B pathElement2 = path.getPathElement(n);
        canonicalPath2.addElement(new PathElement(pathElement2.axis(), pathElement2.type()));
        return canonicalPath2;
    }
    
    public static String pathToString(final IPath path) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < path.length(); ++i) {
            final B pathElement = path.getPathElement(i);
            if (i > 0) {
                sb.append('/');
            }
            sb.append(pathElement.toString());
        }
        return sb.toString();
    }
    
    public static boolean isAncestor(final IModelObject modelObject, final IModelObject modelObject2) {
        for (IModelObject modelObject3 = modelObject.getParent(); modelObject3 != null; modelObject3 = modelObject3.getParent()) {
            if (modelObject3 == modelObject2) {
                return true;
            }
        }
        return false;
    }
    
    public static void createPathSubtree(final IContext context, final IExpression expression, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        expression.createSubtree(context, modelObjectFactory, set);
    }
    
    public static void createPathSubtree(final IModelObject modelObject, final IPath path, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        createPathSubtree(new Context(modelObject), path, modelObjectFactory, set);
    }
    
    public static void createPathSubtree(final IContext context, final IPath path, IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        if (modelObjectFactory == null) {
            modelObjectFactory = new ModelObjectFactory();
        }
        final int length = path.length();
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final boolean b = length % 2 == 0;
        List<IModelObject> list2 = b ? new ArrayList<IModelObject>() : list;
        List<IModelObject> list3 = b ? list : new ArrayList<IModelObject>();
        list3.add(context.getObject());
        for (int i = 0; i < length; ++i) {
            final B pathElement = path.getPathElement(i);
            final List<IModelObject> list4 = list2;
            list2 = list3;
            list3 = list4;
            list3.clear();
            final int size = list2.size();
            for (int j = 0; j < size; ++j) {
                pathElement.query(context, list2.get(j), list3);
            }
            if (pathElement.predicate() == null && list3.size() == 0) {
                for (int k = 0; k < size; ++k) {
                    final IModelObject modelObject = list2.get(k);
                    if ((pathElement.axis() & 0x80) != 0x0) {
                        modelObject.setAttribute(pathElement.type(), "");
                        list3.add(modelObject.getAttributeNode(pathElement.type()));
                        if (set != null) {
                            set.removeAttribute(modelObject, pathElement.type());
                        }
                    }
                    else {
                        if (pathElement.type() == null) {
                            return;
                        }
                        final IModelObject object = modelObjectFactory.createObject(modelObject, pathElement.type());
                        modelObject.addChild(object);
                        list3.add(object);
                        if (set != null) {
                            set.removeChild(modelObject, object);
                        }
                    }
                }
            }
        }
    }
    
    public static IModelObject dereference(IModelObject modelObject) {
        IModelObject modelObject2;
        for (modelObject2 = modelObject.getReferent(); modelObject2 != modelObject; modelObject = modelObject2, modelObject2 = modelObject2.getReferent()) {}
        return modelObject2;
    }
    
    private static class _A extends ModelObjectFactory
    {
        public IModelObjectFactory D;
        
        @Override
        public IModelObject createClone(final IModelObject modelObject) {
            if (modelObject instanceof IExternalReference) {
                final IExternalReference externalReference = (IExternalReference)modelObject;
                final IExternalReference externalReference2 = (IExternalReference)externalReference.createObject(externalReference.getType());
                if (externalReference.isDirty()) {
                    String[] staticAttributes;
                    for (int length = (staticAttributes = externalReference.getStaticAttributes()).length, i = 0; i < length; ++i) {
                        final String s = staticAttributes[i];
                        externalReference2.setAttribute(s, externalReference.getAttribute(s));
                    }
                }
                else {
                    ModelAlgorithms.copyAttributes(externalReference, externalReference2);
                }
                externalReference2.setCachingPolicy(externalReference.getCachingPolicy());
                externalReference2.setDirty(externalReference.isDirty());
                return externalReference2;
            }
            if (this.D != null) {
                return this.D.createClone(modelObject);
            }
            return super.createClone(modelObject);
        }
    }
}
