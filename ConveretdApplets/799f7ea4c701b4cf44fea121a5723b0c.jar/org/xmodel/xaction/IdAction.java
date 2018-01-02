// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.xmodel.util.Radix;
import org.xmodel.util.Identifier;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.Iterator;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import java.util.Random;

public class IdAction extends GuardedAction
{
    private Random \u00d2;
    private String \u00d0;
    private IExpression \u00d3;
    private int \u00d1;
    
    public IdAction() {
        this.\u00d2 = new Random(System.nanoTime());
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u00d0 = Conventions.getVarName(root, false, "assign");
        this.\u00d3 = Xlate.get(root, (IExpression)null);
        this.\u00d1 = Xlate.get(root, "length", 10);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.\u00d3 != null) {
            final Iterator<IModelObject> iterator = this.\u00d3.query(context, null).iterator();
            while (iterator.hasNext()) {
                iterator.next().setValue(this.A(this.\u00d1));
            }
        }
        else if (this.\u00d0 != null) {
            final IVariableScope scope = context.getScope();
            if (scope != null) {
                scope.set(this.\u00d0, this.A(this.\u00d1));
            }
        }
        else {
            context.getObject().setValue(this.A(this.\u00d1));
        }
        return null;
    }
    
    private final String A(final int n) {
        return Identifier.generate(this.\u00d2, n);
    }
    
    public static void main(final String[] array) {
        final Random random = new Random();
        final int length = 3;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; ++i) {
            sb.setLength(0);
            for (int j = 0; j < length; j = sb.length()) {
                sb.append(Radix.convert(random.nextLong(), 36).toUpperCase());
            }
            sb.setLength(length);
            System.out.println(sb);
        }
    }
}
