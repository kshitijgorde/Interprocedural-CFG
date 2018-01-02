// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.regex.Pattern;

public class PatternCheck extends AbstractCheck
{
    private Pattern b;
    
    public PatternCheck(final IModelObject modelObject) {
        super(modelObject);
        this.b = Pattern.compile(Xlate.get(modelObject, ""));
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        return this.b.matcher(Xlate.get(modelObject, "")).matches();
    }
}
