// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import java.util.List;
import org.xmodel.IModelObject;

public class Bootstrap extends com.stonewall.cornerstone.component.Bootstrap
{
    static final String[] required;
    
    static {
        required = new String[0];
    }
    
    public Bootstrap() {
    }
    
    public Bootstrap(final IModelObject document) {
        super(document);
    }
    
    @Override
    protected List<String> getRequiredProperties() {
        final List<String> list = super.getRequiredProperties();
        String[] required;
        for (int length = (required = Bootstrap.required).length, i = 0; i < length; ++i) {
            final String p = required[i];
            list.add(p);
        }
        return list;
    }
}
