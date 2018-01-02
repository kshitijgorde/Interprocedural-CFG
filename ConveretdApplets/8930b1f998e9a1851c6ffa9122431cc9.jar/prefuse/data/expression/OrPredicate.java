// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import java.util.Iterator;
import prefuse.data.Tuple;

public class OrPredicate extends CompositePredicate
{
    public OrPredicate() {
    }
    
    public OrPredicate(final Predicate predicate) {
        this.add(predicate);
    }
    
    public OrPredicate(final Predicate predicate, final Predicate predicate2) {
        super(predicate, predicate2);
    }
    
    public boolean getBoolean(final Tuple tuple) {
        if (this.m_clauses.size() == 0) {
            return false;
        }
        final Iterator<Predicate> iterator = this.m_clauses.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getBoolean(tuple)) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        return (this.size() == 0) ? "FALSE" : this.toString("OR");
    }
}