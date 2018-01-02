// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.function.custom.DelegateFunction;

public class ConfiguredFunction extends DelegateFunction
{
    public ConfiguredFunction(final IModelObject xml) {
        super(Xlate.get(xml, "name", ""), Xlate.get(xml, ""));
    }
}
