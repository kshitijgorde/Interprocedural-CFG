// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class UndoAction extends GuardedAction
{
    private IExpression historyExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.historyExpr = xActionDocument.getExpression("history", true);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.historyExpr.queryFirst(context);
        int value = Xlate.get(queryFirst, "index", 0);
        if (value == 0) {
            return null;
        }
        --value;
        final CommandAction.Command command = (CommandAction.Command)queryFirst.getChild(value).getAttribute("instance");
        if (command.canUndo()) {
            Xlate.set(queryFirst, "index", value);
            command.undo();
        }
        return null;
    }
}
