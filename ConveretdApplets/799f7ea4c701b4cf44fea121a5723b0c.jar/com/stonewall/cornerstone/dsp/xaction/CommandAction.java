// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import java.util.Iterator;
import java.util.Collections;
import com.stonewall.cornerstone.dsp.command.CommandContainer;
import com.stonewall.cornerstone.dsp.command.CommandCache;
import java.util.Collection;
import org.xmodel.Xlate;
import java.util.ArrayList;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.GuardedAction;

public abstract class CommandAction extends GuardedAction implements ILog
{
    private IExpression parserExpr;
    private IExpression enableExpr;
    private IExpression cacheExpr;
    private List<IExpression> localExpectExprs;
    private List<IExpression> localPatternExprs;
    private IModelObject onSuccess;
    private IModelObject onFailure;
    private IExpression expectExprs;
    private IExpression patternExpr;
    
    public CommandAction() {
        this.expectExprs = XPath.createExpression("$docs/expect");
        this.patternExpr = XPath.createExpression("$docs/failurePattern");
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.parserExpr = document.getExpression("parser", false);
        this.cacheExpr = document.getExpression("cacheName", false);
        this.localExpectExprs = document.getExpressions("expect");
        this.localPatternExprs = document.getExpressions("failurePattern");
        this.enableExpr = document.getExpression("enable", false);
        final IModelObject root = document.getRoot();
        this.onSuccess = root.getFirstChild("onSuccess");
        this.onFailure = root.getFirstChild("onFailure");
    }
    
    protected abstract DeviceCommand createCommand(final IContext p0);
    
    @Override
    protected Object[] doAction(final IContext context) {
        final DeviceCommand command = this.createCommand(context);
        List<IExpression> exprs = null;
        if (this.localExpectExprs != null && !this.localExpectExprs.isEmpty()) {
            exprs = this.localExpectExprs;
        }
        else {
            exprs = new ArrayList<IExpression>();
            final List<IModelObject> expects = this.expectExprs.evaluateNodes(context);
            if (!expects.isEmpty()) {
                for (final IModelObject o : expects) {
                    final String s = Xlate.get(o, "");
                    exprs.add(XPath.createExpression(s));
                }
            }
        }
        for (final IExpression expr : exprs) {
            final String s2 = expr.evaluateString(context);
            if (!s2.equals("")) {
                command.addExpect(s2);
            }
        }
        final List<IExpression> patterns = new ArrayList<IExpression>();
        patterns.addAll(this.localPatternExprs);
        if (patterns.isEmpty()) {
            final List<IModelObject> patternNodes = this.patternExpr.evaluateNodes(context);
            for (final IModelObject o2 : patternNodes) {
                final String s3 = Xlate.get(o2, "");
                patterns.add(XPath.createExpression(s3));
            }
        }
        for (final IExpression expr2 : patterns) {
            command.addFailurePattern(expr2.evaluateString(context));
        }
        if (this.parserExpr != null) {
            final String parser = this.parserExpr.evaluateString(context);
            command.setParser(parser);
        }
        if (this.cacheExpr != null) {
            final String name = this.cacheExpr.evaluateString(context);
            command.setCacheName(name);
        }
        if (this.enableExpr != null) {
            final String name = this.enableExpr.evaluateString(context);
            command.setEnableExpression(name);
        }
        if (this.onSuccess != null) {
            command.setOnSuccess(this.onSuccess);
        }
        if (this.onFailure != null) {
            command.setOnFailure(this.onFailure);
        }
        final CommandCache cache = CommandCache.getCurrent();
        final CommandContainer container = new CommandContainer();
        container.addCommands(Collections.singletonList(command));
        cache.add(container);
        return null;
    }
}
