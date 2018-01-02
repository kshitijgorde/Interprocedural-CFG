// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.Xlate;
import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.IPath;

public class ElementCheck extends AbstractCheck
{
    private IPath X;
    private IPath W;
    private List<ValueCheck> Y;
    private List<IModelObject> V;
    private RootConstraint Z;
    private List<IModelObject> U;
    
    public ElementCheck(final IModelObject modelObject) {
        super(modelObject);
        this.X = XPath.createPath("attributes/attribute");
        this.W = XPath.createPath("children/element[ @name = $name]");
        this.Y = new ArrayList<ValueCheck>();
        this.V = this.X.query(modelObject, null);
        final Iterator<IModelObject> iterator = this.V.iterator();
        while (iterator.hasNext()) {
            this.Y.add(new ValueCheck(iterator.next()));
        }
        final IModelObject firstChild = modelObject.getFirstChild("value");
        if (firstChild != null) {
            this.Y.add(new ValueCheck(firstChild));
        }
        final IModelObject firstChild2 = modelObject.getFirstChild("constraint");
        if (firstChild2 != null) {
            this.Z = new RootConstraint(firstChild2);
        }
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        if (this.U != null) {
            this.U.clear();
        }
        for (final ValueCheck valueCheck : this.Y) {
            if (!valueCheck.validate(modelObject)) {
                this.addFailed(valueCheck);
            }
        }
        for (final String s : modelObject.getAttributeNames()) {
            if (s.length() > 0 && !s.startsWith("xmlns") && !this.B(s)) {
                if (this.U == null) {
                    this.U = new ArrayList<IModelObject>();
                }
                this.U.add(modelObject.getAttributeNode(s));
                return false;
            }
        }
        if (this.Z != null && !this.Z.validate(modelObject)) {
            this.addFailed(this.Z);
        }
        for (final IModelObject modelObject2 : modelObject.getChildren()) {
            if (modelObject2.getType().charAt(0) == '?') {
                continue;
            }
            final ElementCheck a = this.A(modelObject2.getType());
            if (a == null || a.validate(modelObject2)) {
                continue;
            }
            this.addFailed(a);
        }
        return this.errored == null || this.errored.size() == 0;
    }
    
    private boolean B(final String s) {
        final Iterator<IModelObject> iterator = this.V.iterator();
        while (iterator.hasNext()) {
            if (Xlate.get((IModelObject)iterator.next(), "name", (String)null).equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    private ElementCheck A(final String s) {
        this.W.setVariable("name", s);
        final IModelObject queryFirst = this.W.queryFirst(this.getSchemaLocus());
        return (queryFirst != null) ? new ElementCheck(queryFirst) : null;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        super.getErrors(list);
        if (this.U != null) {
            final Iterator<IModelObject> iterator = this.U.iterator();
            while (iterator.hasNext()) {
                list.add(new SchemaError(SchemaError.Type.illegalAttribute, this.getSchemaLocus(), iterator.next()));
            }
        }
    }
}
