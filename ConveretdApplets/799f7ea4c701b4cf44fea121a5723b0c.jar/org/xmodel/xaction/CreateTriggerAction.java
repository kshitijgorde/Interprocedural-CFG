// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.ModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.xaction.trigger.EntityTrigger;
import org.xmodel.xaction.trigger.WhenTrigger;
import org.xmodel.xaction.trigger.SourceTrigger;
import org.xmodel.Xlate;
import org.xmodel.xaction.trigger.ITrigger;

public class CreateTriggerAction extends XAction
{
    private String H;
    private ITrigger G;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.H = Conventions.getVarName(root, true, "assign");
        if (Xlate.get(root, "source", (String)null) != null) {
            this.G = new SourceTrigger();
        }
        if (Xlate.get(root, "when", (String)null) != null) {
            this.G = new WhenTrigger();
        }
        if (Xlate.get(root, "entity", (String)null) != null) {
            this.G = new EntityTrigger();
        }
        this.G.configure(xActionDocument);
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        final IVariableScope scope = context.getScope();
        if (scope != null) {
            final ModelObject modelObject = new ModelObject("trigger");
            modelObject.setValue(this.G);
            scope.set(this.H, modelObject);
        }
        this.G.activate(context);
        return null;
    }
}
