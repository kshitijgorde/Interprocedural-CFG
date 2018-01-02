// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import org.xmodel.caching.FileCachingPolicy;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.external.ExternalReference;
import java.io.File;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelObject;
import java.util.Collections;
import java.util.ArrayList;
import org.xmodel.xsd.check.SchemaError;
import org.xmodel.xsd.check.ICheck;
import org.xmodel.xsd.check.ValueCheck;
import org.xmodel.xsd.check.ElementCheck;
import org.xmodel.xsd.check.SchemaCheck;
import org.xmodel.Xlate;
import java.util.Iterator;
import java.util.List;
import org.xmodel.xpath.AttributeNode;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IPath;

public class Schema
{
    private static final IPath E;
    private static final IPath C;
    private static final IExpression F;
    private static final IPath B;
    private static final IExpression A;
    private static final IExpression D;
    
    static {
        E = XPath.createPath("attributes/attribute");
        C = XPath.createPath("constraint/*");
        F = XPath.createExpression("ancestor::element/children/element[ @name = $name]");
        B = XPath.createPath("descendant::schema:errors");
        A = XPath.createExpression(".//element[ @name = $name]");
        D = XPath.createExpression(".//attribute[ @name = $name]");
    }
    
    public static IModelObject findSchema(final IModelObject modelObject, final IModelObject modelObject2) {
        int n = 0;
        IModelObject modelObject3 = null;
        List<IModelObject> list;
        if (modelObject2 instanceof AttributeNode) {
            Schema.D.setVariable("name", modelObject2.getType());
            list = Schema.D.query(modelObject, null);
        }
        else {
            Schema.A.setVariable("name", modelObject2.getType());
            list = Schema.A.query(modelObject, null);
        }
        for (final IModelObject modelObject4 : list) {
            final int a = A(modelObject4, modelObject2);
            if (a > n) {
                n = a;
                modelObject3 = modelObject4;
            }
        }
        return modelObject3;
    }
    
    private static int A(IModelObject parent, final IModelObject modelObject) {
        int n = 0;
        while (parent != null && modelObject != null) {
            if (!Xlate.get(parent, "name", "").equals(modelObject.getType())) {
                break;
            }
            parent = parent.getParent();
            if (parent == null) {
                break;
            }
            ++n;
        }
        return n;
    }
    
    public static boolean validate(final IModelObject modelObject, final IModelObject modelObject2) {
        final String type = modelObject.getType();
        ICheck check = null;
        if (type.equals("schema")) {
            check = new SchemaCheck(modelObject);
        }
        else if (type.equals("element")) {
            check = new ElementCheck(modelObject);
        }
        else if (type.equals("attribute") || type.equals("value")) {
            check = new ValueCheck(modelObject);
        }
        return check.validate(modelObject2);
    }
    
    public static List<SchemaError> validate(final IModelObject modelObject, final IModelObject modelObject2, final boolean b) {
        final String type = modelObject.getType();
        ICheck check = null;
        if (type.equals("schema")) {
            check = new SchemaCheck(modelObject);
        }
        else if (type.equals("element")) {
            check = new ElementCheck(modelObject);
        }
        else if (type.equals("attribute") || type.equals("value")) {
            check = new ValueCheck(modelObject);
        }
        removeAnnotations(modelObject2);
        if (!check.validate(modelObject2)) {
            final ArrayList<SchemaError> list = new ArrayList<SchemaError>();
            check.getErrors(list);
            return list;
        }
        return Collections.emptyList();
    }
    
    public static void removeAnnotations(final IModelObject modelObject) {
        final Iterator<IModelObject> iterator = Schema.B.query(modelObject, null).iterator();
        while (iterator.hasNext()) {
            iterator.next().removeFromParent();
        }
    }
    
    public static IModelObject createDocumentBranch(final SchemaTrace schemaTrace, final boolean b) {
        IModelObject document = createDocument(schemaTrace.getLeaf(), b);
        for (int i = schemaTrace.getLength() - 2; i > 0; --i) {
            final ModelObject modelObject = new ModelObject(Xlate.get(schemaTrace.getElement(i), "name", ""));
            modelObject.addChild(document);
            document = modelObject;
        }
        return document;
    }
    
    public static IModelObject createDocument(final IModelObject modelObject, final boolean b) {
        return createDocument(modelObject, null, b);
    }
    
    public static IModelObject createDocument(final IModelObject modelObject, final IModelObjectFactory modelObjectFactory, final boolean b) {
        return createDocument(modelObject, modelObjectFactory, b, new ArrayList<IModelObject>());
    }
    
    public static IModelObject createDocument(final IModelObject modelObject, final IModelObjectFactory modelObjectFactory, final boolean b, final List<IModelObject> list) {
        if (!modelObject.isType("element")) {
            throw new IllegalArgumentException("Argument must be a simplified schema element tag: " + modelObject);
        }
        list.add(modelObject);
        try {
            String s = Xlate.get(modelObject, "name", (String)null);
            if (s == null) {
                s = Xlate.get(modelObject, "type", (String)null);
            }
            final IModelObject modelObject2 = (modelObjectFactory != null) ? modelObjectFactory.createObject(null, s) : new ModelObject(s);
            for (final IModelObject modelObject3 : Schema.E.query(modelObject, null)) {
                if (Xlate.get(modelObject3, "use", "optional").equals("required") || b) {
                    modelObject2.setAttribute(Xlate.get(modelObject3, "name", ""), Xlate.get(modelObject3, "default", ""));
                }
            }
            final IModelObject firstChild = modelObject.getFirstChild("value");
            if (firstChild != null) {
                modelObject2.setValue(Xlate.get(firstChild, "default", ""));
            }
            final IModelObject queryFirst = Schema.C.queryFirst(modelObject);
            if (queryFirst != null) {
                final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>();
                A(queryFirst, modelObjectFactory, list2, b, list);
                final Iterator<Object> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    modelObject2.addChild(iterator2.next());
                }
            }
            return modelObject2;
        }
        finally {
            list.remove(modelObject);
        }
    }
    
    private static void A(IModelObject queryFirst, final IModelObjectFactory modelObjectFactory, final List<IModelObject> list, final boolean b, final List<IModelObject> list2) {
        if (Xlate.get(queryFirst, "min", 1) == 0 && !b) {
            return;
        }
        final boolean b2 = true;
        if (queryFirst.isType("set") || queryFirst.isType("list") || (queryFirst.isType("choice") && b2)) {
            final Iterator<IModelObject> iterator = queryFirst.getChildren().iterator();
            while (iterator.hasNext()) {
                A(iterator.next(), modelObjectFactory, list, b, list2);
            }
        }
        else if (queryFirst.isType("choice")) {
            A(queryFirst.getChild(0), modelObjectFactory, list, b, list2);
        }
        else if (queryFirst.isType("child")) {
            final StatefulContext statefulContext = new StatefulContext(queryFirst);
            statefulContext.set("name", Xlate.get(queryFirst, ""));
            queryFirst = Schema.F.queryFirst(statefulContext);
            if (!list2.contains(queryFirst)) {
                list.add(createDocument(queryFirst, modelObjectFactory, b));
            }
        }
    }
    
    public static IModelObject removeInvalidElements(final IModelObject modelObject, IModelObject cloneTree) {
        cloneTree = cloneTree.cloneTree();
        final List<SchemaError> validate = validate(modelObject, cloneTree, false);
        if (validate.size() == 0) {
            return cloneTree;
        }
        for (final SchemaError schemaError : validate) {
            if (schemaError.isType(SchemaError.Type.illegalElement)) {
                schemaError.getDocumentLocus().removeFromParent();
            }
        }
        return cloneTree;
    }
    
    public static void main(final String[] array) throws Exception {
        if (array.length < 2) {
            System.out.println("usage: java Schema <xsd> <document>\n");
            return;
        }
        final File file = new File(array[0]);
        final ExternalReference externalReference = new ExternalReference("xsd");
        externalReference.setAttribute("url", file.toURL());
        externalReference.setCachingPolicy(new XsdCachingPolicy(new UnboundedCache()));
        externalReference.setDirty(true);
        final File file2 = new File(array[1]);
        final ExternalReference externalReference2 = new ExternalReference("doc");
        externalReference2.setAttribute("path", file2.toString());
        externalReference2.setCachingPolicy(new FileCachingPolicy(new UnboundedCache()));
        externalReference2.setDirty(true);
        final IModelObject transform = new SchemaTransform().transform(externalReference.getChild(0));
        final Iterator<SchemaError> iterator = validate(transform, externalReference2.getChild(0), true).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        final Iterator<SchemaError> iterator2 = validate(transform, externalReference2.getChild(0), true).iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}
