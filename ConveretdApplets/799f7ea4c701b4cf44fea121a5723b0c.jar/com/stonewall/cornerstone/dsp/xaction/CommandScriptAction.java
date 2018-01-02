// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import org.xmodel.IModelObject;
import org.xmodel.xaction.XActionDocument;
import java.util.ArrayList;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.IXAction;
import java.util.List;
import org.xmodel.xaction.GuardedAction;

public class CommandScriptAction extends GuardedAction
{
    private List<IXAction> actions;
    private static final IExpression actionExpr;
    
    static {
        actionExpr = XPath.createExpression("*[ not( matches( name(), 'expect|when|failurePattern|package'))]");
    }
    
    public CommandScriptAction() {
        this.actions = new ArrayList<IXAction>();
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        final IModelObject root = document.getRoot();
        final List<IModelObject> actionNodes = CommandScriptAction.actionExpr.query(root, null);
        for (final IModelObject actionNode : actionNodes) {
            this.actions.add(document.getAction(actionNode));
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        for (final IXAction action : this.actions) {
            final Object[] result = action.run(context);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
