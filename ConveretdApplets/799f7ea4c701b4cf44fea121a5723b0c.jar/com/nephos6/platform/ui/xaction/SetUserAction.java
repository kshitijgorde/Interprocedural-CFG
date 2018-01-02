// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xaction;

import com.stonewall.cornerstone.security.User;
import org.xmodel.util.Identifier;
import java.util.Random;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class SetUserAction extends GuardedAction
{
    private IExpression userExpr;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.userExpr = document.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        User.setDefault(new User(this.userExpr.evaluateString(context), Identifier.generate(new Random(), 13)));
        return null;
    }
}
