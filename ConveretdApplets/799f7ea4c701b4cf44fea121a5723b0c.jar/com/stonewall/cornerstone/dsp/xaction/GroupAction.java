// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import com.stonewall.cornerstone.dsp.command.CommandContainer;
import com.stonewall.cornerstone.dsp.command.CommandCache;
import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import org.xmodel.xaction.IXAction;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.GuardedAction;

public class GroupAction extends GuardedAction implements ILog
{
    private static final IExpression actionExpr;
    private List<IXAction> actions;
    
    static {
        actionExpr = XPath.createExpression("*[ not( matches( name(), '\\Awhen|condition\\Z'))]");
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.actions = new ArrayList<IXAction>();
        for (final IModelObject actionSpec : GroupAction.actionExpr.query(document.getRoot(), null)) {
            this.actions.add(document.getAction(actionSpec));
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final CommandCache tmp = new CommandCache();
        final CommandCache cache = CommandCache.swap(tmp);
        Object[] result = null;
        for (final IXAction action : this.actions) {
            result = action.run(context);
            if (result != null) {
                break;
            }
        }
        final CommandContainer group = new CommandContainer();
        for (final CommandContainer container : tmp.getCommands()) {
            group.addCommands(container.getCommands());
        }
        cache.add(group);
        CommandCache.swap(cache);
        return result;
    }
}
