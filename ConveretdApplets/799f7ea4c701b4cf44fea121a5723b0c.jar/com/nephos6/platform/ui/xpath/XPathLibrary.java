// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xpath;

import org.xmodel.xpath.function.Function;
import org.xmodel.xpath.function.FunctionFactory;

public class XPathLibrary
{
    public static void register() {
        FunctionFactory.getInstance().register("ui:encrypt", EncryptFunction.class);
    }
}
