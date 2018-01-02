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
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;

public class ConfigToNat extends EntityTransform<NatPolicy, DeviceConfig>
{
    protected static final Log log;
    
    static {
        log = Log.getLog(ConfigToNat.class);
    }
    
    public ConfigToNat(final NatPolicy result, final Loader loader) {
        super(result, loader);
    }
    
    @Override
    public NatPolicy run(final DeviceConfig config) {
        try {
            final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, "transform.configToNat.xml");
            final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
            context.set("policy", ((NatPolicy)this.result).getRoot());
            context.set("config", config.getRoot());
            doc.process(context);
            final IVariableScope scope = context.getScope();
            final IModelObject o = ((List)scope.get("policy")).get(0);
            this.result = (R)new NatPolicy(o);
            ConfigToNat.log.debug("ConfigToNat: \n" + this.result);
        }
        catch (Exception e) {
            ConfigToNat.log.error(this, e);
        }
        return (NatPolicy)this.result;
    }
}
