// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import java.util.Iterator;
import java.net.URL;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.xpath.function.Function;
import org.xmodel.xpath.function.FunctionFactory;
import com.stonewall.cornerstone.utility.ILog;

public class FunctionPackage implements ILog
{
    public static void install() {
        final FunctionFactory factory = FunctionFactory.getInstance();
        factory.register("cm:formatDateTime", FormatDateTimeFunction.class);
        factory.register("cm:ip-create", IPCreateFunction.class);
        factory.register("cm:ip-contains", IPContainsFunction.class);
        factory.register("cm:parse-ip", ParseIPFunction.class);
        factory.register("cm:parse-mask", ParseMaskFunction.class);
        factory.register("cm:security", SecurityFunction.class);
        factory.register("cm:displayMap", DisplayMapFunction.class);
        try {
            final ModelBuilder builder = new ModelBuilder();
            final URL url = FunctionPackage.class.getResource("functions.xml");
            final IModelObject specs = builder.buildModel(url.openStream());
            for (final IModelObject spec : specs.getChildren("function")) {
                final ConfiguredFunction prototype = new ConfiguredFunction(spec);
                factory.register(prototype.getName(), prototype);
            }
        }
        catch (Exception e) {
            FunctionPackage.log.info("Unable to parse functions", e);
        }
    }
}
