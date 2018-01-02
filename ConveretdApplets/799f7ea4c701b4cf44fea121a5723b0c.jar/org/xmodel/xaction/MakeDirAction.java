// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.File;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class MakeDirAction extends GuardedAction
{
    private IExpression \u0119;
    private IExpression \u0118;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u0118 = xActionDocument.getExpression("recurse", true);
        this.\u0119 = xActionDocument.getExpression("path", true);
        if (this.\u0119 == null) {
            this.\u0119 = xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final File file = new File(this.\u0119.evaluateString(context));
        if (!file.exists()) {
            if (this.\u0118 == null || !this.\u0118.evaluateBoolean(context)) {
                file.mkdir();
            }
            else {
                file.mkdirs();
            }
        }
        return null;
    }
}
