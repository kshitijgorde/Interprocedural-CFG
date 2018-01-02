// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.IExpression;
import java.util.List;
import org.xmodel.xpath.expression.L;

public abstract class Function extends L
{
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append('(');
        final List<IExpression> arguments = this.getArguments();
        final boolean b = arguments.size() > 1;
        for (int i = 0; i < arguments.size(); ++i) {
            if (i > 0 && b) {
                sb.append(", ");
            }
            sb.append(this.getArgument(i).toString());
        }
        sb.append(')');
        return sb.toString();
    }
}
