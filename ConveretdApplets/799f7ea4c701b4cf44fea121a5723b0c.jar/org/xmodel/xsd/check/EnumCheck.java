// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.Iterator;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.List;

public class EnumCheck extends AbstractCheck
{
    private List<IModelObject> L;
    
    public EnumCheck(final IModelObject modelObject) {
        super(modelObject);
        this.L = modelObject.getChildren("value");
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "");
        final Iterator<IModelObject> iterator = this.L.iterator();
        while (iterator.hasNext()) {
            if (value.equals(Xlate.get((IModelObject)iterator.next(), ""))) {
                return true;
            }
        }
        return false;
    }
}
