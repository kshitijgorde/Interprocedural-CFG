// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.function.Function;
import org.xmodel.xpath.function.FunctionFactory;

public class FunctionPackage
{
    public static void install() {
        final FunctionFactory factory = FunctionFactory.getInstance();
        factory.register("dsp:allocate", AllocateFunction.class);
        factory.register("dsp:resolve-ips", ResolveIPsFunction.class);
        factory.register("dsp:resolve-svc", ResolveSvcFunction.class);
        factory.register("dsp:service-name", ServiceNameFunction.class);
        factory.register("dsp:name-str", NameStrFunction.class);
        factory.register("dsp:entity-display", EntityDisplayFunction.class);
        factory.register("dsp:convert-time", ConvertTimeFunction.class);
        factory.register("dsp:bound", BoundFunction.class);
        factory.register("dsp:encrypt", EncryptFunction.class);
        factory.register("dsp:decrypt", DecryptFunction.class);
        factory.register("dsp:find-tie", FindTieFunction.class);
        factory.register("dsp:deref-tie", DerefTieFunction.class);
        factory.register("dsp:load", LoadFunction.class);
        factory.register("dsp:command-size", CommandCacheSizeFunction.class);
    }
}
