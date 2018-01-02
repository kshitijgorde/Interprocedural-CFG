// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.data.expression.NotPredicate;
import prefuse.visual.VisualItem;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.ColumnExpression;

public class StartVisiblePredicate extends ColumnExpression implements Predicate
{
    public static final Predicate TRUE;
    public static final Predicate FALSE;
    
    public StartVisiblePredicate() {
        super(VisualItem.STARTVISIBLE);
    }
    
    static {
        TRUE = new StartVisiblePredicate();
        FALSE = new NotPredicate(StartVisiblePredicate.TRUE);
    }
}
