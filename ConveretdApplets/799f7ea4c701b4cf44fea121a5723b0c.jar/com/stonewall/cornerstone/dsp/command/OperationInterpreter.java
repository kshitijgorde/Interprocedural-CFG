// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import com.stonewall.cornerstone.dsp.xaction.LoadedXActionDocument;
import com.stonewall.cornerstone.entity.Opset;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.diff.AnnotatingConfigDiffer;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.entity.DeviceConfig;
import java.util.Iterator;
import java.util.Collection;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.NullContext;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.entity.Device;
import org.xmodel.log.Log;

public class OperationInterpreter
{
    public static final Log log;
    protected Device device;
    protected Loader loader;
    
    static {
        log = Log.getLog(OperationInterpreter.class);
    }
    
    public OperationInterpreter() {
    }
    
    public OperationInterpreter(final Device d) throws Exception {
        this.device = d;
        this.loader = new Loader(d.getSoftware(), d.getHardware());
    }
    
    public DeviceOperation generateOperation(final String transform) throws Exception {
        final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
        context.set("device", this.device.getRoot());
        final Collection<CommandContainer> containers = this.generateCommands(transform, context);
        final DeviceOperation op = new DeviceOperation();
        for (final CommandContainer container : containers) {
            op.setCommands(container.getCommands());
        }
        return op;
    }
    
    public List<DeviceOperation> generateOperations(final DeviceConfig pending, final DeviceConfig current) throws Exception {
        final List<DeviceOperation> deviceOps = new ArrayList<DeviceOperation>();
        final AnnotatingConfigDiffer differ = new AnnotatingConfigDiffer();
        final DeviceConfig annotated = differ.annotate(this.device, current, pending);
        if (!this.hasChanges(annotated)) {
            return deviceOps;
        }
        final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
        context.set("annotated", annotated.getRoot());
        context.set("pending", pending.getRoot());
        context.set("device", this.device.getRoot());
        final Collection<CommandContainer> containers = this.generateCommands("command.commands.xml", context);
        final IVariableScope scope = context.getScope();
        final IModelObject o = ((List)scope.get("pending")).get(0);
        pending.replaceContent(o);
        OperationInterpreter.log.debug("Post command generation:" + pending);
        for (final CommandContainer container : containers) {
            OperationInterpreter.log.debug(container.getCommands());
            final DeviceOperation op = new DeviceOperation();
            deviceOps.add(op);
            op.setCommands(container.getCommands());
        }
        return deviceOps;
    }
    
    public DeviceConfig completeCommands(DeviceConfig config, final Opset opset) throws Exception {
        final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
        context.set("config", config.getRoot());
        for (final com.stonewall.cornerstone.entity.DeviceOperation d : opset.getOperations()) {
            final DeviceOperation op = new DeviceOperation(d.getRoot());
            for (final DeviceCommand command : op.getCommands()) {
                final IModelObject action = command.getCompleteAction();
                if (action != null) {
                    final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, action);
                    context.set("attachment", command.getResult().getAttachment());
                    doc.process(context);
                }
            }
        }
        final IVariableScope scope = context.getScope();
        final IModelObject o = ((List)scope.get("config")).get(0);
        config = new DeviceConfig(o);
        return config;
    }
    
    protected void sort(final Collection<DeviceCommand> commands) {
    }
    
    private List<CommandContainer> generateCommands(final String name, final StatefulContext context) throws Exception {
        try {
            final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, name);
            doc.process(context);
            final CommandCache cache = CommandCache.getCurrent();
            final List<CommandContainer> commands = cache.getCommands();
            cache.clear();
            return commands;
        }
        catch (Exception e) {
            OperationInterpreter.log.error(this, e);
            throw e;
        }
    }
    
    protected boolean hasChanges(final DeviceConfig annotated) {
        final IExpression path = XPath.createExpression(".//*[.//processing-instruction()]");
        final List<IModelObject> l = path.query(annotated.getRoot(), null);
        return !l.isEmpty();
    }
}
