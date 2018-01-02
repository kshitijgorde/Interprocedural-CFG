// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;

public class SwitchAction extends GuardedAction
{
    private IExpression \u00c7;
    private IExpression[] \u00c6;
    private ScriptAction[] \u00c5;
    private ScriptAction \u00c8;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00c7 = xActionDocument.getExpression("source", true);
        final List<IModelObject> children = xActionDocument.getRoot().getChildren("case");
        this.\u00c6 = new IExpression[children.size()];
        this.\u00c5 = new ScriptAction[children.size()];
        for (int i = 0; i < children.size(); ++i) {
            final IModelObject modelObject = children.get(i);
            this.\u00c6[i] = Xlate.get(modelObject, "value", (IExpression)null);
            this.\u00c5[i] = xActionDocument.createScript(modelObject, new String[0]);
        }
        final IModelObject firstChild = this.getDocument().getRoot().getFirstChild("default");
        if (firstChild != null) {
            this.\u00c8 = xActionDocument.createScript(firstChild, new String[0]);
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.\u00c7.evaluateString(context);
        for (int i = 0; i < this.\u00c6.length; ++i) {
            if (evaluateString.equals(this.\u00c6[i].evaluateString(context))) {
                return this.\u00c5[i].run(context);
            }
        }
        if (this.\u00c8 != null) {
            return this.\u00c8.run(context);
        }
        return null;
    }
}
