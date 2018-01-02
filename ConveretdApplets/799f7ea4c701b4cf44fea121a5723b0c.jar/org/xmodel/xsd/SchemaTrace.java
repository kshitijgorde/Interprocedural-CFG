// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import java.util.ArrayList;
import java.util.Collections;
import org.xmodel.Xlate;
import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IPath;

public class SchemaTrace
{
    private IPath B;
    private IPath A;
    private IPath F;
    private IPath E;
    private IExpression C;
    private List<IModelObject> D;
    
    protected SchemaTrace() {
        this.B = XPath.createPath("element[ @name = $name]");
        this.A = XPath.createPath("children/element[ @name = $name]");
        this.F = XPath.createPath("attributes/attribute[ @name = $name]");
        this.E = XPath.createPath("constraint//child[ . = $name]");
        this.C = XPath.createExpression("let $name := @name;let $constraint := ../../constraint//child[ . = $name];let $element := ../../children/element[ @name = $name];($constraint/@min = 0) and not( $element/constraint//child[ not( @min = 0)])");
    }
    
    public IModelObject getElement(final int n) {
        return this.D.get(n);
    }
    
    public IModelObject getLeaf() {
        return this.D.get(this.D.size() - 1);
    }
    
    public int getLength() {
        return this.D.size();
    }
    
    public boolean isOptional() {
        if (this.D.size() <= 2) {
            return true;
        }
        IModelObject parent = this.D.get(this.D.size() - 1);
        if (parent.isType("value")) {
            parent = parent.getParent();
        }
        if (parent.isType("attribute")) {
            return Xlate.get(parent, "use", "optional").equals("optional");
        }
        if (this.isOptional(parent)) {
            return true;
        }
        for (int i = this.D.size() - 2; i >= 0; --i) {
            if (this.isOptional(this.D.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public List<IModelObject> getOptionals() {
        if (this.D.size() == 0) {
            return Collections.emptyList();
        }
        if (this.D.size() <= 2) {
            return Collections.singletonList(this.D.get(0));
        }
        IModelObject parent = this.D.get(this.D.size() - 1);
        if (parent.isType("value")) {
            parent = parent.getParent();
        }
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        if (parent.isType("attribute") && Xlate.get(parent, "use", "optional").equals("optional")) {
            list.add(parent);
        }
        int i = this.D.size() - 2;
        final IModelObject modelObject = this.D.get(i);
        this.E.setVariable("name", Xlate.get(parent, "name", ""));
        if (Xlate.get(this.E.queryFirst(modelObject), "min", 1) == 0) {
            list.add(parent);
        }
        while (i >= 0) {
            final IModelObject modelObject2 = this.D.get(i);
            this.E.setVariable("name", Xlate.get((IModelObject)this.D.get(i + 1), "name", ""));
            if (Xlate.get(this.E.queryFirst(modelObject2), "min", 1) == 0) {
                list.add(modelObject2);
            }
            --i;
        }
        return list;
    }
    
    private List<IModelObject> A(final IModelObject modelObject, final IModelObject modelObject2) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        list.add(modelObject);
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>();
        for (IModelObject parent = modelObject2; parent != null; parent = parent.getParent()) {
            list2.add(0, parent);
        }
        int i = 0;
        IModelObject modelObject3 = null;
        while (i < list2.size()) {
            this.B.setVariable("name", ((IModelObject)list2.get(i)).getType());
            modelObject3 = this.B.queryFirst(modelObject);
            if (modelObject3 != null) {
                break;
            }
            ++i;
        }
        if (modelObject3 != null) {
            list.add(modelObject3);
        }
        ++i;
        while (i < list2.size() && modelObject3 != null) {
            final IModelObject modelObject4 = list2.get(i);
            final String type = modelObject4.getType();
            if (type.length() > 0) {
                this.A.setVariable("name", type);
                IModelObject modelObject5 = this.A.queryFirst(modelObject3);
                if (modelObject5 == null) {
                    this.F.setVariable("name", modelObject4.getType());
                    modelObject5 = this.F.queryFirst(modelObject3);
                    if (modelObject5 == null) {
                        return null;
                    }
                }
                modelObject3 = modelObject5;
            }
            else {
                modelObject3 = modelObject3.getFirstChild("value");
            }
            list.add(modelObject3);
            ++i;
        }
        if (list.size() == 1) {
            return Collections.emptyList();
        }
        return list;
    }
    
    public boolean isOptional(final IModelObject modelObject) {
        return this.C.evaluateBoolean(new Context(modelObject));
    }
    
    public static SchemaTrace getInstance(final IModelObject modelObject, final IModelObject modelObject2) {
        final SchemaTrace schemaTrace = new SchemaTrace();
        schemaTrace.D = schemaTrace.A(modelObject, modelObject2);
        return (schemaTrace.D != null && schemaTrace.D.size() > 0) ? schemaTrace : null;
    }
}
