// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;

public class ThrowAction extends GuardedAction
{
    private Class<RuntimeException> ª;
    private IExpression µ;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final String value = Xlate.get(xActionDocument.getRoot(), "class", "");
        try {
            this.ª = (Class<RuntimeException>)xActionDocument.getClassLoader().loadClass(value);
        }
        catch (ClassNotFoundException ex) {}
        try {
            if (this.ª == null) {
                this.ª = (Class<RuntimeException>)ThrowAction.class.getClassLoader().loadClass(value);
            }
        }
        catch (ClassNotFoundException ex2) {}
        if (this.ª == null) {
            this.ª = RuntimeException.class;
        }
        this.µ = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        RuntimeException ex;
        try {
            if (this.µ != null) {
                ex = this.ª.getConstructor(String.class).newInstance(this.µ.evaluateString(context));
            }
            else {
                ex = this.ª.newInstance();
            }
        }
        catch (Exception ex2) {
            ex = new XActionException("Unable to create instance of clss: " + this.ª, ex2);
        }
        throw ex;
    }
}
