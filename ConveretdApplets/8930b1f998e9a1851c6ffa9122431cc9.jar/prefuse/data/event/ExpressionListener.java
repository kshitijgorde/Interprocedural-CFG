// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.event;

import prefuse.data.expression.Expression;
import java.util.EventListener;

public interface ExpressionListener extends EventListener
{
    void expressionChanged(final Expression p0);
}
