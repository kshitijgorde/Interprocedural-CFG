// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public abstract class ConstraintCheck extends AbstractCheck
{
    private int C;
    private int B;
    protected int index;
    protected ConstraintCheck[] constraints;
    protected int occurrences;
    
    public ConstraintCheck(final IModelObject modelObject) {
        super(modelObject);
        this.constraints = this.A();
        this.C = Xlate.get(modelObject, "min", 1);
        final String value = Xlate.get(modelObject, "max", "1");
        this.B = (value.equals("unbounded") ? -1 : Integer.parseInt(value));
    }
    
    public int getMinOccurrences() {
        return this.C;
    }
    
    public int getMaxOccurrences() {
        return this.B;
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        return this.validate(modelObject, 0, modelObject.getNumberOfChildren());
    }
    
    public boolean validate(final IModelObject modelObject, int index, final int n) {
        this.errorLocus = null;
        if (this.errored != null) {
            this.errored.clear();
        }
        this.index = index;
        this.occurrences = 0;
        while (this.B < 0 || this.occurrences <= this.B) {
            index = this.index;
            if (!this.validateOnce(modelObject, this.index, n)) {
                break;
            }
            if (this.getIndex() == index) {
                return true;
            }
            ++this.occurrences;
        }
        if (this.C <= this.occurrences && (this.B < 0 || this.occurrences <= this.B)) {
            this.errored = null;
            return true;
        }
        if (this.B >= 0 && this.occurrences > this.B) {
            --this.index;
        }
        if (this.errorLocus == null) {
            this.errorLocus = modelObject.getChild(this.index);
        }
        return false;
    }
    
    protected abstract boolean validateOnce(final IModelObject p0, final int p1, final int p2);
    
    public int getIndex() {
        return this.index;
    }
    
    public int getOccurrences() {
        return this.occurrences;
    }
    
    public int anyCount() {
        return -1;
    }
    
    private ConstraintCheck[] A() {
        final List<IModelObject> children = this.getSchemaLocus().getChildren();
        final ConstraintCheck[] array = new ConstraintCheck[children.size()];
        for (int i = 0; i < children.size(); ++i) {
            array[i] = this.A(children.get(i));
        }
        return array;
    }
    
    private ConstraintCheck A(final IModelObject modelObject) {
        final String type = modelObject.getType();
        if (type.equals("child")) {
            return new ChildCheck(modelObject);
        }
        if (type.equals("constraint")) {
            return new RootConstraint(modelObject);
        }
        if (type.equals("list")) {
            return new ListCheck(modelObject);
        }
        if (type.equals("set")) {
            return new SetCheck(modelObject);
        }
        if (type.equals("choice")) {
            return new ChoiceCheck(modelObject);
        }
        if (type.equals("any")) {
            return new AnyCheck(modelObject);
        }
        throw new IllegalStateException("Unrecognized constraint type.");
    }
}
