// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.data.expression.NotPredicate;
import prefuse.data.expression.Expression;
import prefuse.visual.VisualItem;
import prefuse.data.expression.Function;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.ColumnExpression;

public class ValidatedPredicate extends ColumnExpression implements Predicate, Function
{
    public static final Predicate TRUE;
    public static final Predicate FALSE;
    
    public ValidatedPredicate() {
        super(VisualItem.VALIDATED);
    }
    
    public String getName() {
        return "VALIDATED";
    }
    
    public void addParameter(final Expression expression) {
        throw new IllegalStateException("This function takes 0 parameters");
    }
    
    public int getParameterCount() {
        return 0;
    }
    
    public String toString() {
        return this.getName() + "()";
    }
    
    static {
        TRUE = new ValidatedPredicate();
        FALSE = new NotPredicate(ValidatedPredicate.TRUE);
    }
}
