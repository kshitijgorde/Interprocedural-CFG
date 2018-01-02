// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.awt.event.ComponentEvent;
import prefuse.data.Tuple;
import prefuse.data.tuple.TupleSet;
import prefuse.data.expression.Expression;
import java.awt.event.ComponentListener;
import prefuse.data.event.TupleSetListener;
import prefuse.data.event.ExpressionListener;

public abstract class UpdateListener implements ExpressionListener, TupleSetListener, ComponentListener
{
    public abstract void update(final Object p0);
    
    public void expressionChanged(final Expression expression) {
        this.update(expression);
    }
    
    public void tupleSetChanged(final TupleSet set, final Tuple[] array, final Tuple[] array2) {
        this.update(set);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.update(componentEvent.getSource());
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
}
