// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;
import prefuse.data.Tuple;
import prefuse.data.Schema;

public class GroupSizeFunction extends GroupExpression
{
    public GroupSizeFunction() {
    }
    
    public GroupSizeFunction(final String s) {
        super(s);
    }
    
    public String getName() {
        return "GROUPSIZE";
    }
    
    public Class getType(final Schema schema) {
        return Integer.TYPE;
    }
    
    public Object get(final Tuple tuple) {
        return new Integer(this.getInt(tuple));
    }
    
    public double getDouble(final Tuple tuple) {
        return this.getInt(tuple);
    }
    
    public float getFloat(final Tuple tuple) {
        return this.getInt(tuple);
    }
    
    public int getInt(final Tuple tuple) {
        final String group = this.getGroup(tuple);
        if (group == null) {
            return -1;
        }
        final TupleSet group2 = ((VisualItem)tuple).getVisualization().getGroup(group);
        return (group2 == null) ? 0 : group2.getTupleCount();
    }
    
    public long getLong(final Tuple tuple) {
        return this.getInt(tuple);
    }
}
