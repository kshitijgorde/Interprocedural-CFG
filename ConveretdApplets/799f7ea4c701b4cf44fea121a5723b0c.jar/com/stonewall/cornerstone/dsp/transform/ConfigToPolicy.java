// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.transform;

import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.NullContext;
import org.xmodel.xaction.XActionDocument;
import com.stonewall.cornerstone.dsp.xaction.LoadedXActionDocument;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.DeviceConfig;
import com.stonewall.cornerstone.entity.policy.security.DevicePolicy;

public class ConfigToPolicy extends EntityTransform<DevicePolicy, DeviceConfig>
{
    protected static final Log log;
    
    static {
        log = Log.getLog(ConfigToPolicy.class);
    }
    
    public ConfigToPolicy(final DevicePolicy result, final Loader loader) {
        super(result, loader);
    }
    
    @Override
    public DevicePolicy run(final DeviceConfig config) {
        try {
            final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, "transform.configToPolicy.xml");
            final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
            context.set("policy", ((DevicePolicy)this.result).getRoot());
            context.set("config", config.getRoot());
            doc.process(context);
            final IVariableScope scope = context.getScope();
            final IModelObject o = ((List)scope.get("policy")).get(0);
            this.result = (R)new DevicePolicy(o);
            ConfigToPolicy.log.debug("ConfigToPolicy: \n" + this.result);
        }
        catch (Exception e) {
            ConfigToPolicy.log.error(this, e);
        }
        return (DevicePolicy)this.result;
    }
}
