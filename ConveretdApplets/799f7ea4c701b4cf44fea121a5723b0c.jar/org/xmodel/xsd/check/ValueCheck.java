// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.xpath.TextNode;
import org.xmodel.xpath.AttributeNode;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class ValueCheck extends AbstractCheck
{
    private String J;
    private boolean K;
    private TypeCheck[] I;
    
    public ValueCheck(final IModelObject modelObject) {
        super(modelObject);
        this.J = "";
        this.K = false;
        if (modelObject.getType().equals("attribute")) {
            this.J = Xlate.get(modelObject, "name", (String)null);
            this.K = Xlate.get(modelObject, "use", "optional").equals("required");
        }
        final IModelObject firstChild = modelObject.getFirstChild("type");
        if (firstChild != null) {
            final List<IModelObject> children = firstChild.getChildren();
            this.I = new TypeCheck[children.size()];
            for (int i = 0; i < this.I.length; ++i) {
                this.I[i] = new TypeCheck(children.get(i));
            }
        }
        else {
            this.I = new TypeCheck[0];
        }
    }
    
    @Override
    protected boolean validateImpl(IModelObject modelObject) {
        if (!(modelObject instanceof AttributeNode) && !(modelObject instanceof TextNode)) {
            IModelObject modelObject2 = modelObject.getAttributeNode(this.J);
            if (modelObject2 == null && this.J.length() == 0) {
                modelObject.setValue("");
                modelObject2 = modelObject.getAttributeNode(this.J);
            }
            modelObject = modelObject2;
        }
        final String value = Xlate.get(modelObject, "");
        if (this.J.length() > 0) {
            if (value == null && this.K) {
                return false;
            }
            if (value != null) {
                for (int i = 0; i < this.I.length; ++i) {
                    if (!this.I[i].validate(modelObject)) {
                        this.addFailed(this.I[i]);
                        return false;
                    }
                }
            }
        }
        else {
            for (int j = 0; j < this.I.length; ++j) {
                if (!this.I[j].validate(modelObject)) {
                    this.addFailed(this.I[j]);
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        super.getErrors(list);
        final List<ICheck> failed = this.getFailed();
        if (failed == null || failed.size() == 0) {
            list.add(new SchemaError(SchemaError.Type.missingAttribute, this.getSchemaLocus(), this.errorLocus));
        }
    }
}
